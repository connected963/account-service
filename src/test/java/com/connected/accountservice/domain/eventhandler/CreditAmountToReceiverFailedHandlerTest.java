package com.connected.accountservice.domain.eventhandler;

import com.connected.accountservice.application.service.account.AccountService;
import com.connected.accountservice.application.service.movement.MovementService;
import com.connected.accountservice.common.defaultdata.AccountDefaultData;
import com.connected.accountservice.common.defaultdata.MovementDefaultData;
import com.connected.accountservice.domain.event.CreditAmountToReceiverFailedEventFactory;
import com.connected.accountservice.domain.model.account.AccountTestFactory;
import com.connected.accountservice.domain.model.movement.MovementTestFactory;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

class CreditAmountToReceiverFailedHandlerTest {

    private final AccountService accountServiceMock;

    private final MovementService movementServiceMock;

    private final CreditAmountToReceiverFailedHandler creditAmountToReceiverFailedHandler;

    CreditAmountToReceiverFailedHandlerTest() {
        this.accountServiceMock = Mockito.mock(AccountService.class);
        this.movementServiceMock = Mockito.mock(MovementService.class);
        this.creditAmountToReceiverFailedHandler =
                CreditAmountToReceiverFailedHandlerFactory.createNew(accountServiceMock, movementServiceMock);
    }

    @Test
    void givenCreditAmountToReceiverFailedEvent_mustRevertAccountPaymentBalanceAndAbortPaymentMovement() {
        final var accountExpected = AccountTestFactory.createAnDefault();
        Mockito.when(accountServiceMock.findAccountById(AccountDefaultData.id))
                .thenReturn(accountExpected);
        final var movementExpected = MovementTestFactory.createAnDefaultOutput();
        Mockito.when(movementServiceMock.findMovementById(MovementDefaultData.id))
                .thenReturn(movementExpected);

        final var creditAmountFailedEvent = CreditAmountToReceiverFailedEventFactory.createNew(
                AccountDefaultData.id, MovementDefaultData.id);
        creditAmountToReceiverFailedHandler.abortTransferMoney(creditAmountFailedEvent);

        final var payingAccountWithMovementRevertedExpected =
                AccountTestFactory.createAnDefaultWithBalance(BigDecimal.valueOf(2));
        Mockito.verify(accountServiceMock).update(payingAccountWithMovementRevertedExpected);

        final var paymentMovementAbortedExpected = MovementTestFactory.createAnDefaultOutputAborted();
        Mockito.verify(movementServiceMock).update(paymentMovementAbortedExpected);
    }
}