package com.github.piyushpatel2005.customer;

public record CustomerRegistrationRequest (
    String name,
    String email,
    Integer age
) { }
