package com.connected.accountservice.api.account;

import com.connected.accountservice.application.service.account.AccountService;
import io.javalin.Context;
import org.mockito.Mockito;

class AccountControllerTest {

    private final AccountService accountServiceMock;

    private final Context context;

    private final AccountController accountController;

    public AccountControllerTest() {
        this.accountServiceMock = Mockito.mock(AccountService.class);
        this.context = Mockito.mock(Context.class);
        this.accountController = new AccountController(accountServiceMock);
    }


}