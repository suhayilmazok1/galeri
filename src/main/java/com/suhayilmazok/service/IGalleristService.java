package com.suhayilmazok.service;

import com.suhayilmazok.dto.DtoGallerist;
import com.suhayilmazok.dto.DtoGalleristIU;
import com.suhayilmazok.model.Gallerist;

import java.util.List;

public interface IGalleristService {

    public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU);

    public DtoGallerist getGallerist(Long id);

    public List<DtoGallerist> getAllGallerist();

    public void deleteGallerist(Long id);

    public DtoGallerist updateGallerist(Long id,DtoGalleristIU dtoGalleristIU);
}
