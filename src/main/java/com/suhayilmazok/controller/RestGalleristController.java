package com.suhayilmazok.controller;

import com.suhayilmazok.dto.DtoGallerist;
import com.suhayilmazok.dto.DtoGalleristIU;

import java.util.List;

public interface RestGalleristController {
    public RootEntity<DtoGallerist> saveGallerist(DtoGalleristIU dtoGalleristIU);

    public RootEntity<DtoGallerist> getGallerist(Long id);

    public RootEntity<List<DtoGallerist>> getAllGallerists();

    public void deleteGallerist(Long id);

    public RootEntity<DtoGallerist> updateGallerist(Long id,DtoGalleristIU dtoGalleristIU);
}
