package com.springcommerceapi.SpringCommerceAPI.model;

public class Constants {

    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5*60*60; //5 horas
    public static final String SIGNING_KEY = "unipampa";
    public static final String TOKEN_PREFIX = "Bearer "; //tipo da autorização
    public static final String HEADER_STRING = "Authorization"; //qual o header que vai acompanhar
    public static final String AUTHORITIES_KEY = "scopes";
}