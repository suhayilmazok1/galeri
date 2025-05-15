package com.suhayilmazok.exception;

import lombok.Getter;

@Getter
public enum MessageType {

    NO_RECORD_EXIST("1004", "kayıt bulunamadı"),
    TOKEN_IS_EXPIRED("1005", "token SÜRESİ BİTMİŞTİR"),
    USERNAME_NOT_FOUND("1006", "USERNAME BULUNAMADI"),
    USERNAME_OR_PASSWORD_INVALID("1007", "USERNAME OR PASSWORD BULUNAMADI"),
    REFRESH_TOKEN_NOT_FOUND("1008", "REFRESH TOKEN BULUNAMADI"),
    REFRESH_TOKEN_IS_EXPIRED("1009", "REFRESH TOKEN SÜRESİ BİTMİŞTİR"),
    CURRENCY_NOT_FOUND("1010", "CURRENCY BULUNAMADI"),
    NOT_ENOUGH_BALANCE("1011","YETERSİZ BAKİYE"),
    CAR_STATUS_IS_ALREADY_SALED("1012", "ARABA ZATEN SATILMIŞ"),
    GENERAL_EXCEPTION("9999" , "genel bir hata oluştu");



    private String code;

    private String message;

    MessageType(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
