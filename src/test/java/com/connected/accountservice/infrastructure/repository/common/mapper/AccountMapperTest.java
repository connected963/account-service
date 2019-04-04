package com.connected.accountservice.infrastructure.repository.common.mapper;

import com.connected.accountservice.common.defaultdata.AccountDefaultData;
import com.connected.accountservice.domain.model.account.AccountTestFactory;
import org.assertj.core.api.Assertions;
import org.jdbi.v3.core.statement.StatementContext;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

class AccountMapperTest {

    private final ResultSet resultSetMock;

    private final StatementContext statementContext;

    private final AccountMapper accountMapper;

    AccountMapperTest() {
        this.resultSetMock = Mockito.mock(ResultSet.class);
        this.statementContext = Mockito.mock(StatementContext.class);
        this.accountMapper = new AccountMapper();
    }

    @Test
    void givenResultSet_mustCreateRespectiveAccount() throws SQLException {
        Mockito.when(resultSetMock.getObject("id", UUID.class))
                .thenReturn(AccountDefaultData.id);
        Mockito.when(resultSetMock.getObject("balance", BigDecimal.class))
                .thenReturn(AccountDefaultData.balance);
        Mockito.when(resultSetMock.getObject("overdraft", BigDecimal.class))
                .thenReturn(AccountDefaultData.overdraft);

        final var accountMapped = accountMapper.map(resultSetMock, statementContext);

        final var accountExpected = AccountTestFactory.createAnDefault();

        Assertions.assertThat(accountMapped).isEqualTo(accountExpected);
    }
}