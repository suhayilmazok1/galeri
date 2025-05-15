package com.suhayilmazok.service.impl;

import com.suhayilmazok.dto.DtoAccount;
import com.suhayilmazok.dto.DtoAddress;
import com.suhayilmazok.dto.DtoCustomer;
import com.suhayilmazok.dto.DtoCustomerIU;
import com.suhayilmazok.exception.BaseException;
import com.suhayilmazok.exception.ErrorMessage;
import com.suhayilmazok.exception.MessageType;
import com.suhayilmazok.model.Account;
import com.suhayilmazok.model.Address;
import com.suhayilmazok.model.Customer;
import com.suhayilmazok.repository.AccountRepository;
import com.suhayilmazok.repository.AddressRepository;
import com.suhayilmazok.repository.CustomerRepository;
import com.suhayilmazok.service.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AccountRepository accountRepository;

    private Customer createCustomer(DtoCustomerIU dtoCustomerIU) {
        Optional<Address> optAddress = addressRepository.findById(dtoCustomerIU.getAddressId());
        if(optAddress.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoCustomerIU.getAddressId().toString()));
        }

        Optional<Account> optAccount = accountRepository.findById(dtoCustomerIU.getAccountId());
        if(optAccount.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoCustomerIU.getAccountId().toString()));
        }


        Customer customer = new Customer();
        customer.setCreateTime(new Date());

        customer.setAddress(optAddress.get());
        customer.setAccount(optAccount.get());


        BeanUtils.copyProperties(dtoCustomerIU, customer);

        return customer;
    }

    @Override
    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU) {
        DtoCustomer dtoCustomer = new DtoCustomer();
        DtoAddress dtoAddress = new DtoAddress();
        DtoAccount dtoAccount = new DtoAccount();

        Customer savedCustomer = customerRepository.save(createCustomer(dtoCustomerIU));

        BeanUtils.copyProperties(savedCustomer, dtoCustomer);
        BeanUtils.copyProperties(savedCustomer.getAddress(), dtoAddress);
        BeanUtils.copyProperties(savedCustomer.getAccount(), dtoAccount);

        dtoCustomer.setAddress(dtoAddress);
        dtoCustomer.setAccount(dtoAccount);
        return dtoCustomer;
    }

    @Override
    public DtoCustomer getCustomer(Long id) {

        DtoAccount dtoAccount = new DtoAccount();
        DtoAddress dtoAddress = new DtoAddress();
        DtoCustomer dtoCustomer = new DtoCustomer();

        Optional<Customer> getCustomer = customerRepository.findById(id);
        if(getCustomer.isPresent()) {
            Customer customer = getCustomer.get();
            BeanUtils.copyProperties(customer, dtoCustomer);
            BeanUtils.copyProperties(customer.getAddress(), dtoAddress);
            BeanUtils.copyProperties(customer.getAccount(), dtoAccount);
            dtoCustomer.setAddress(dtoAddress);
            dtoCustomer.setAccount(dtoAccount);
            return dtoCustomer;
        }
        return null;
    }

    @Override
    public List<DtoCustomer> getAllCustomers() {

        List<DtoCustomer> dtoCustomers = new ArrayList<>();
        List<Customer> customers = customerRepository.findAll();

        for (Customer customer : customers) {
           DtoCustomer dtoCustomer = new DtoCustomer();
           DtoAddress dtoAddress = new DtoAddress();
           DtoAccount dtoAccount = new DtoAccount();

           BeanUtils.copyProperties(customer, dtoCustomer);

           if(customer.getAddress() != null) {
               BeanUtils.copyProperties(customer.getAddress(), dtoAddress);
               dtoCustomer.setAddress(dtoAddress);
           }
           if(customer.getAccount() != null) {
               BeanUtils.copyProperties(customer.getAccount(), dtoAccount);
               dtoCustomer.setAccount(dtoAccount);
           }
           dtoCustomers.add(dtoCustomer);
        }
        return dtoCustomers;
    }

    @Override
    public void deleteCustomer(Long id) {
        Optional<Customer> optional = customerRepository.findById(id);
        if (optional.isPresent()) {
            Customer customer = optional.get();
            customerRepository.delete(customer);
        }
    }

    @Override
    public DtoCustomer updateCustomer(Long id, DtoCustomerIU dtoCustomerIU) {
        DtoCustomer dtoCustomer = new DtoCustomer();
        DtoAddress dtoAddress = new DtoAddress();
        DtoAccount dtoAccount = new DtoAccount();

        Optional<Customer> optional = customerRepository.findById(id);
        if(optional.isPresent()) {
            Customer customer = optional.get();


            Address address = addressRepository.findById(dtoCustomerIU.getAddressId()).orElse(null);
            Account account = accountRepository.findById(dtoCustomerIU.getAccountId()).orElse(null);


            // Address bilgilerini manuel olarak set et
            if (address != null) {
                dtoAddress.setCity(address.getCity());
                dtoAddress.setDistrict(address.getDistrict());
                dtoAddress.setStreet(address.getStreet());
                dtoAddress.setId(address.getId());
                dtoAddress.setNeighborhood(address.getNeighborhood());
                dtoAddress.setCreateTime(new Date());

                dtoCustomer.setAddress(dtoAddress);
            }

            // Account bilgilerini manuel olarak set et
            if (account != null) {
                dtoAccount.setAccountNo(account.getAccountNo());
                dtoAccount.setIban(account.getIban());
                dtoAccount.setAmount(account.getAmount());
                dtoAccount.setCurrencyType(account.getCurrencyType());
                dtoAccount.setCreateTime(new Date());
                dtoAccount.setId(account.getId());


                dtoCustomer.setAccount(dtoAccount);
            }

            customer.setAddress(address);
            customer.setAccount(account);
            customer.setCreateTime(new Date());
            customer.setTckn(dtoCustomerIU.getTckn());
            customer.setDateOfBirth(dtoCustomerIU.getDateOfBirth());
            customer.setFirstName(dtoCustomerIU.getFirstName());
            customer.setLastName(dtoCustomerIU.getLastName());

            customerRepository.save(customer);

            BeanUtils.copyProperties(customer, dtoCustomer);
            return dtoCustomer;
        }

        return null;
    }
}
