package com.connected.accountservice.domain.eventhandler;

import com.connected.accountservice.application.service.account.AccountService;
import com.connected.accountservice.application.service.movement.MovementService;
import com.connected.accountservice.common.defaultdata.AccountDefaultData;
import com.connected.accountservice.common.defaultdata.MovementDefaultData;
import com.connected.accountservice.domain.event.CreditAmountToReceiverFailedEventFactory;
import com.connected.accountservice.domain.event.PaymentApprovedEventTestBuilder;
import com.connected.accountservice.domain.model.account.AccountTestFactory;
import com.connected.accountservice.domain.model.movement.Movement;
import com.connected.accountservice.domain.model.movement.MovementTestFactory;
import com.google.common.eventbus.EventBus;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.UUID;

class PaymentApprovedHandlerTest {

    private final AccountService accountServiceMock;

    private final MovementService movementServiceMock;

    private final EventBus eventBusMock;

    private final PaymentApprovedHandler paymentApprovedHandler;

    PaymentApprovedHandlerTest() {
        this.accountServiceMock = Mockito.mock(AccountService.class);
        this.movementServiceMock = Mockito.mock(MovementService.class);
        this.eventBusMock = Mockito.mock(EventBus.class);
        this.paymentApprovedHandler = PaymentApprovedHandlerFactory.createNew(
                accountServiceMock, movementServiceMock, eventBusMock);
    }

    @Test
    void givenPaymentApprovedEvent_mustCreditAmountToReceiverAndFinishPaymentMovement() {
        final var receiverAccountExpected = AccountTestFactory.createAnDefault();
        Mockito.when(accountServiceMock.findAccountById(AccountDefaultData.id))
                .thenReturn(receiverAccountExpected);
        final var paymentMovementExpected = MovementTestFactory.createAnDefaultOutput();
        Mockito.when(movementServiceMock.findMovementById(MovementDefaultData.id))
                .thenReturn(paymentMovementExpected);

        final var paymentApprovedEvent = new PaymentApprovedEventTestBuilder()
                .withAccountIdFrom(UUID.randomUUID())
                .withAccountIdTo(AccountDefaultData.id)
                .withPaymentMovementId(MovementDefaultData.id)
                .build();
        paymentApprovedHandler.paymentApprovedHandler(paymentApprovedEvent);

        final var receiverAccountWithNewBalance =
                AccountTestFactory.createAnDefaultWithBalance(BigDecimal.valueOf(2));
        Mockito.verify(accountServiceMock).update(receiverAccountWithNewBalance);

        Mockito.verify(movementServiceMock, Mockito.atLeast(2)).save(Mockito.any(Movement.class));

        final var receiverPaymentCompleted = paymentMovementExpected.complete();
        Mockito.verify(movementServiceMock).save(receiverPaymentCompleted);
    }

    @Test
    void givenPaymentApprovedEventWithInvalidAccountToId_mustPostFailEvent() {
        final var paymentApprovedEvent = new PaymentApprovedEventTestBuilder()
                .withAccountIdFrom(AccountDefaultData.id)
                .withAccountIdTo(UUID.randomUUID())
                .withPaymentMovementId(MovementDefaultData.id)
                .build();
        paymentApprovedHandler.paymentApprovedHandler(paymentApprovedEvent);

        final var transferCreditFailEvent = CreditAmountToReceiverFailedEventFactory.createNew(
                AccountDefaultData.id, MovementDefaultData.id);

        Mockito.verify(eventBusMock).post(transferCreditFailEvent);
    }

    @Test
    void givenPaymentApprovedEventWithInvalidPaymentMovement_mustPostFailEvent() {
        final var accountReceiverId = UUID.randomUUID();
        final var receiverAccountExpected = AccountTestFactory.createAnDefault();
        Mockito.when(accountServiceMock.findAccountById(accountReceiverId))
                .thenReturn(receiverAccountExpected);

        final var paymentApprovedEvent = new PaymentApprovedEventTestBuilder()
                .withAccountIdFrom(AccountDefaultData.id)
                .withAccountIdTo(accountReceiverId)
                .withPaymentMovementId(MovementDefaultData.id)
                .build();
        paymentApprovedHandler.paymentApprovedHandler(paymentApprovedEvent);

        final var transferCreditFailEvent = CreditAmountToReceiverFailedEventFactory.createNew(
                AccountDefaultData.id, MovementDefaultData.id);

        Mockito.verify(eventBusMock).post(transferCreditFailEvent);
    }
}