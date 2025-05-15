package com.suhayilmazok.service.impl;

import com.suhayilmazok.dto.*;
import com.suhayilmazok.enums.CarStatusType;
import com.suhayilmazok.enums.CurrencyType;
import com.suhayilmazok.exception.BaseException;
import com.suhayilmazok.exception.ErrorMessage;
import com.suhayilmazok.exception.MessageType;
import com.suhayilmazok.model.Account;
import com.suhayilmazok.model.Car;
import com.suhayilmazok.model.Customer;
import com.suhayilmazok.model.SaledCar;
import com.suhayilmazok.repository.CarRepository;
import com.suhayilmazok.repository.CustomerRepository;
import com.suhayilmazok.repository.GalleristRepository;
import com.suhayilmazok.repository.SaledCarRepository;
import com.suhayilmazok.service.ICurrencyRatesService;
import com.suhayilmazok.service.ISaledCarService;
import com.suhayilmazok.utils.DateUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Optional;

import static com.suhayilmazok.enums.CurrencyType.USD;

@Service
public class SaledCarServiceImpl implements ISaledCarService {

    @Autowired
    private SaledCarRepository saledCarRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ICurrencyRatesService currencyRatesService;


    public BigDecimal customerAmount(Customer customer, Car car ) {
            // Eğer hem müşteri hem araç TL ise çevirmeye gerek yok
            if (customer.getAccount().getCurrencyType() == CurrencyType.TL &&
                    car.getCurrencyType() == CurrencyType.TL) {
                return customer.getAccount().getAmount(); // Direkt TL miktarını döndür
            }

        return BigDecimal.ZERO;
    }

    public BigDecimal convertCustomerAmountToUSD(Customer customer) {


        // Para birimi dönüşümü gerekiyorsa
        CurrencyRateResponse currencyRatesResponse = currencyRatesService.getCurrencyRates("25-03-2025", "25-03-2025");
        BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());

        // Müşteri bakiyesini USD'ye çevir
        if (customer.getAccount().getCurrencyType() == CurrencyType.TL ) {
            return customer.getAccount().getAmount().divide(usd, 2, RoundingMode.HALF_UP);
        }

        // Eğer müşteri zaten USD cinsinden ise
        return customer.getAccount().getAmount();
    }




    public boolean checkCarStatus(Long carId) {
        Optional<Car> optCar = carRepository.findById(carId);
        if (optCar.isPresent() && optCar.get().getCarStatusType().name().equals(CarStatusType.SALED.name())) {
            return false;
        }
        return true;
    }

    public BigDecimal remainingCustomerAmount(Customer customer, Car car) {

        if (customer.getAccount().getCurrencyType() == CurrencyType.TL &&
                car.getCurrencyType() == CurrencyType.TL) {
            BigDecimal tl = customerAmount(customer, car);
            BigDecimal carPriceTL = car.getPrice();

            BigDecimal tlPrice = tl.subtract(carPriceTL);
            return tlPrice.setScale(2, RoundingMode.HALF_UP);
        }

        if (customer.getAccount().getCurrencyType() == CurrencyType.TL && car.getCurrencyType() == CurrencyType.USD) {
            BigDecimal customerUSDAmount = convertCustomerAmountToUSD(customer);
            BigDecimal carPriceUSD = car.getPrice();

            BigDecimal remainingUSDAmount = customerUSDAmount.subtract(carPriceUSD);

            CurrencyRateResponse currencyRatesResponse = currencyRatesService.getCurrencyRates("25-03-2025", "25-03-2025");
            BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());

            return remainingUSDAmount.multiply(usd);
        }

return BigDecimal.ZERO;
    }

    public boolean checkAmount(DtoSaledCarIU dtoSaledCarIU) {

        Optional<Customer> optCustomer = customerRepository.findById(dtoSaledCarIU.getCustomerId());
        if (optCustomer.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCustomerId().toString()));
        }

        Optional<Car> optCar = carRepository.findById(dtoSaledCarIU.getCarId());
        if (optCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCarId().toString()));
        }

        Customer customer = optCustomer.get();
        Car car = optCar.get();

        if (customer.getAccount().getCurrencyType().equals(CurrencyType.TL) && car.getCurrencyType().equals(CurrencyType.TL)) {
            return customer.getAccount().getAmount().compareTo(car.getPrice()) >= 0;
        }

        if (customer.getAccount().getCurrencyType().equals(CurrencyType.TL) && car.getCurrencyType().equals(CurrencyType.USD)) {
            BigDecimal customerUSDAmount = convertCustomerAmountToUSD(optCustomer.get());
            BigDecimal carPrice = optCar.get().getPrice();
            return customerUSDAmount.compareTo(carPrice) >= 0;
        }


        return false;
    }


    private SaledCar createSaledCar(DtoSaledCarIU dtoSaledCarIU) {
        SaledCar saledCar = new SaledCar();
        saledCar.setCreateTime(new Date());
        saledCar.setCustomer(customerRepository.findById(dtoSaledCarIU.getCustomerId()).orElse(null));
        saledCar.setCar(carRepository.findById(dtoSaledCarIU.getCarId()).orElse(null));
        saledCar.setGallerist(galleristRepository.findById(dtoSaledCarIU.getGalleristId()).orElse(null));

        return saledCar;
    }

    @Transactional
    @Override
    public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU) {

        if (!checkCarStatus(dtoSaledCarIU.getCarId())) {
            throw new BaseException(new ErrorMessage(MessageType.CAR_STATUS_IS_ALREADY_SALED, dtoSaledCarIU.getCarId().toString()));
        }

        if (!checkAmount(dtoSaledCarIU)) {
            throw new BaseException(new ErrorMessage(MessageType.NOT_ENOUGH_BALANCE, ""));
        }

        SaledCar savedSaledCar = saledCarRepository.save(createSaledCar(dtoSaledCarIU));

        Car car = savedSaledCar.getCar();
        car.setCarStatusType(CarStatusType.SALED);
        carRepository.save(car);

        Customer customer = savedSaledCar.getCustomer();
        customer.getAccount().setAmount(remainingCustomerAmount(customer, car));
        customerRepository.save(customer);

        return toDTO(savedSaledCar);
    }

    public DtoSaledCar toDTO(SaledCar saledCar) {
        if (saledCar == null) {
            return null;
        }

        DtoSaledCar dtoSaledCar = new DtoSaledCar();

        // Null kontrolü ile kopyalama
        if (saledCar.getCustomer() != null) {
            DtoCustomer dtoCustomer = new DtoCustomer();
            BeanUtils.copyProperties(saledCar.getCustomer(), dtoCustomer);
            dtoSaledCar.setCustomer(dtoCustomer);
        }

        if (saledCar.getGallerist() != null) {
            DtoGallerist dtoGallerist = new DtoGallerist();
            BeanUtils.copyProperties(saledCar.getGallerist(), dtoGallerist);
            dtoSaledCar.setGallerist(dtoGallerist);
        }

        if (saledCar.getCar() != null) {
            DtoCar dtoCar = new DtoCar();
            BeanUtils.copyProperties(saledCar.getCar(), dtoCar);
            dtoSaledCar.setCar(dtoCar);
        }

        // SaledCar'ın kendi özellikleri
        BeanUtils.copyProperties(saledCar, dtoSaledCar, "customer", "gallerist", "car");

        return dtoSaledCar;
    }
}
