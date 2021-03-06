package com.connected.accountservice.domain.model.account;

import com.connected.accountservice.common.BigDecimalScale;
import com.connected.accountservice.domain.exception.BusinessException;
import com.connected.accountservice.domain.model.movement.MovementTestFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountTest {

    @Test
    void givenNewBalanceGreaterThanCurrentBalance_mustGenerateNewAccountWithNewBalance() {
        final var account = AccountTestFactory.createAnDefault();
        final var newBalance = BigDecimal.TEN;

        final var accountWithNewBalance = account.withBalance(newBalance);

        final var accountExpected =
                AccountTestFactory.createAnDefaultWithBalance(newBalance);

        Assertions.assertThat(accountWithNewBalance).isEqualTo(accountExpected);
    }

    @Test
    void givenNewBalanceLessThanCurrentBalanceWithSufficientOverdraft_mustGenerateNewAccountWithNewBalance() {
        final var overdraft = BigDecimal.TEN;
        final var account = AccountTestFactory.createAnDefaultWithOverdraft(overdraft);
        final var newBalance = BigDecimal.TEN.negate();

        final var accountWithNewBalance = account.withBalance(newBalance);

        final var accountExpected =
                AccountTestFactory.createAnDefaultWithBalanceAndOverdraft(newBalance, overdraft);

        Assertions.assertThat(accountWithNewBalance).isEqualTo(accountExpected);
    }

    @Test
    void givenNewBalanceLessThanCurrentBalanceWithoutOverdraft_mustFailToGenerateAccountWithNewBalance() {
        final var account = AccountTestFactory.createAnDefault();
        final var newBalance = BigDecimal.ONE.negate();

        assertThrows(BusinessException.class, () -> account.withBalance(newBalance));
    }


    @Test
    void givenInputMovement_mustGenerateAccountWithMovementAmountAddedToBalance() {
        final var movement = MovementTestFactory.createAnDefault();
        final var account = AccountTestFactory.createAnDefault();

        final var accountWithBalanceRecalculated = account.recalculateBalanceWithMovement(movement);

        final var accountExpected =
                AccountTestFactory.createAnDefaultWithBalance(BigDecimalScale.TWO);

        Assertions.assertThat(accountWithBalanceRecalculated).isEqualTo(accountExpected);
    }

    @Test
    void givenOutputMovementAndAccountWithAmountAvailableSufficient_mustGenerateAccountWithMovementAmountSubtractedFromBalance() {
        final var movement = MovementTestFactory.createAnDefaultOutput();
        final var account = AccountTestFactory.createAnDefault();

        final var accountWithBalanceRecalculated = account.recalculateBalanceWithMovement(movement);

        final var accountExpected =
                AccountTestFactory.createAnDefaultWithBalance(BigDecimalScale.ZERO);

        Assertions.assertThat(accountWithBalanceRecalculated).isEqualTo(accountExpected);
    }

    @Test
    void givenOutputMovementAndAccountWithAmountAvailableInsufficient_mustFailGenerateAccountUpdated() {
        final var movement = MovementTestFactory.createAnDefaultOutput();
        final var account = AccountTestFactory.createAnDefaultWithBalance(BigDecimal.ZERO);

        assertThrows(BusinessException.class,
                () -> account.recalculateBalanceWithMovement(movement));
    }

    @Test
    void givenNonFinalizedOutputMovement_mustGenerateAccountWithMovementAmountAddedToBalance() {
        final var unfinalizedOutputMovement = MovementTestFactory.createAnDefaultOutput();
        final var account = AccountTestFactory.createAnDefault();

        final var accountWithMovementAmountCredit = account.revertMovement(unfinalizedOutputMovement);

        final var accountExpected = AccountTestFactory.createAnDefaultWithBalance(BigDecimalScale.TWO);

        Assertions.assertThat(accountWithMovementAmountCredit).isEqualTo(accountExpected);
    }

    @Test
    void givenNonFinalizedInputMovement_mustGenerateAccountWithMovementAmountSubtractedFromBalance() {
        final var unfinalizedInputMovement = MovementTestFactory.createAnDefault();
        final var account = AccountTestFactory.createAnDefault();

        final var accountWithMovementAmountCredit = account.revertMovement(unfinalizedInputMovement);

        final var accountExpected = AccountTestFactory.createAnDefaultWithBalance(BigDecimalScale.ZERO);

        Assertions.assertThat(accountWithMovementAmountCredit).isEqualTo(accountExpected);
    }

    @Test
    void givenFinalizedMovement_mustFailToRevertMovement() {
        final var finalizedMovement = MovementTestFactory.createAnDefaultCompleted();
        final var account = AccountTestFactory.createAnDefault();

        assertThrows(BusinessException.class, () -> account.revertMovement(finalizedMovement));
    }
}