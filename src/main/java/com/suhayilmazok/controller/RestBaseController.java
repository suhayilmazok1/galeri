package com.suhayilmazok.controller;

import jakarta.persistence.criteria.Root;

public class RestBaseController {

    public <T> RootEntity<T> ok(T payload) {
       return RootEntity.ok(payload);
    }

    public <T> RootEntity<T> error(String message) {
        return RootEntity.error(message);
    }
}
