package com.github.piyushpatel2005.customer;

public record CustomerUpdateRequest (
        String name,
        String email,
        Integer age
){
}
