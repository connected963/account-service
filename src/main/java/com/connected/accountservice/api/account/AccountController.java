package com.connected.accountservice.api.account;

import com.connected.accountservice.api.common.BaseController;
import com.connected.accountservice.application.service.AccountService;
import io.javalin.Context;

class AccountController extends BaseController {

    private final AccountService accountService;

    AccountController(final AccountService accountService) {
        this.accountService = accountService;
    }

    void insert(final Context context) {
    }

    void delete(final Context context) {

    }

    void findAll(final Context context) {

    }

    void transferMoney(final Context context) {

    }

}
