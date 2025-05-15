package com.suhayilmazok.controller;

import com.suhayilmazok.dto.AuthRequest;
import com.suhayilmazok.dto.AuthResponse;
import com.suhayilmazok.dto.DtoUser;
import com.suhayilmazok.dto.RefreshTokenRequest;
import com.suhayilmazok.model.RefreshToken;

public interface IRestAuthenticationController {
    public RootEntity<DtoUser> register(AuthRequest input);

    public RootEntity<AuthResponse> authenticate(AuthRequest input);

    public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest input);
}
