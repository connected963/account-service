package com.connected.accountservice.domain.eventhandler;

import com.connected.accountservice.application.service.account.AccountService;
import com.connected.accountservice.application.service.movement.MovementService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CreditAmountToReceiverFailedHandlerFactoryTest {

    @Test
    void givenHandlerData_mustCreateRespectiveHandler() {
        final var accountService = Mockito.mock(AccountService.class);
        final var movementService = Mockito.mock(MovementService.class);

        final var handlerCreated = CreditAmountToReceiverFailedHandlerFactory.createNew(
                accountService, movementService);

        final var handlerExpected = new CreditAmountToReceiverFailedHandler(
                accountService, movementService);

        Assertions.assertThat(handlerCreated).isEqualTo(handlerExpected);
    }

}