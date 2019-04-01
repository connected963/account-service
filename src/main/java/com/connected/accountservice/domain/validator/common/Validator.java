package com.connected.accountservice.domain.validator.common;

public interface Validator<T> {

    void validate(T t);

}
