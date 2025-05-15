package com.suhayilmazok.controller.impl;

import com.suhayilmazok.controller.IRestAuthenticationController;
import com.suhayilmazok.controller.RestBaseController;
import com.suhayilmazok.controller.RootEntity;
import com.suhayilmazok.dto.AuthRequest;
import com.suhayilmazok.dto.AuthResponse;
import com.suhayilmazok.dto.DtoUser;
import com.suhayilmazok.dto.RefreshTokenRequest;
import com.suhayilmazok.service.IAuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAuthenticationControllerImpl extends RestBaseController implements IRestAuthenticationController {

    @Autowired
    private IAuthenticationService authenticationService;

    @PostMapping("/register")
    @Override
    public RootEntity<DtoUser> register(@Valid @RequestBody AuthRequest input) {
        return ok(authenticationService.register(input));
    }

    @PostMapping("/authenticate")
    @Override
    public RootEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest input) {
        return ok(authenticationService.authenticate(input));
    }

    @PostMapping("/refreshToken")
    @Override
    public RootEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest input) {
        return ok(authenticationService.refreshToken(input));
    }
}
