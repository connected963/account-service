package com.connected.accountservice.domain.eventhandler;

import com.connected.accountservice.application.service.account.AccountService;
import com.connected.accountservice.application.service.movement.MovementService;
import com.google.common.eventbus.EventBus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PaymentApprovedHandlerFactoryTest {

    @Test
    void givenHandlerData_mustCreateRespectiveHandler() {
        final var accountService = Mockito.mock(AccountService.class);
        final var movementService = Mockito.mock(MovementService.class);
        final var eventBus = Mockito.mock(EventBus.class);

        final var handlerCreated = PaymentApprovedHandlerFactory.createNew(
                accountService, movementService, eventBus);

        final var handlerExpected = new PaymentApprovedHandler(
                accountService, movementService, eventBus);

        Assertions.assertThat(handlerCreated).isEqualTo(handlerExpected);
    }

}