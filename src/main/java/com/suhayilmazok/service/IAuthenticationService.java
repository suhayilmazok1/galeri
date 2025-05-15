package com.suhayilmazok.service;

import com.suhayilmazok.dto.AuthRequest;
import com.suhayilmazok.dto.AuthResponse;
import com.suhayilmazok.dto.DtoUser;
import com.suhayilmazok.dto.RefreshTokenRequest;

public interface IAuthenticationService {

    public DtoUser register(AuthRequest input);

    public AuthResponse authenticate(AuthRequest input);

    public AuthResponse refreshToken(RefreshTokenRequest input);
}
