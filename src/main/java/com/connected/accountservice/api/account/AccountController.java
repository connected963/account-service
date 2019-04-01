package com.connected.accountservice.api.account;

import com.connected.accountservice.api.common.BaseController;
import com.connected.accountservice.application.inputmodel.AccountInsertInputModel;
import com.connected.accountservice.application.service.AccountService;
import io.javalin.Context;

import java.util.UUID;

class AccountController extends BaseController {

    private final AccountService accountService;

    AccountController(final AccountService accountService) {
        this.accountService = accountService;
    }

    void insert(final Context context) {
        final var accountToInsert = context.bodyAsClass(AccountInsertInputModel.class);
        final var accountInsertedId = accountService.insert(accountToInsert);

        successResponse(context, accountInsertedId);
    }

    void delete(final Context context) {
        final var accountIdToDelete = context.bodyAsClass(UUID.class);

        accountService.delete(accountIdToDelete);
    }

    void findAll(final Context context) {
        final var accounts = accountService.findAll();

        successResponse(context, accounts);
    }

    void transferMoney(final Context context) {

    }

}
