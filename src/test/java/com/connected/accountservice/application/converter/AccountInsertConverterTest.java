package com.connected.accountservice.application.converter;

import com.connected.accountservice.application.inputmodel.AccountInsertInputModelFactory;
import com.connected.accountservice.common.defaultdata.AccountDefaultData;
import com.connected.accountservice.domain.model.account.AccountBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class AccountInsertConverterTest {

    @Test
    void givenAccountInsertInputModel_mustConvertToAccount() {
        final var accountInsertInputModel =
                AccountInsertInputModelFactory.createAnDefault();

        final var accountConverted =
                AccountInsertConverter.convertToDomainObject(accountInsertInputModel);

        final var accountExpected = new AccountBuilder()
                .withId(accountConverted.getId())
                .withBalance(AccountDefaultData.balance)
                .withOverdraft(AccountDefaultData.overdraft)
                .build();

        Assertions.assertThat(accountConverted).isEqualTo(accountExpected);
    }

}