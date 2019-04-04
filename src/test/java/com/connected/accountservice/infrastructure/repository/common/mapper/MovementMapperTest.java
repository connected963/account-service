package com.connected.accountservice.infrastructure.repository.common.mapper;

import com.connected.accountservice.common.defaultdata.MovementDefaultData;
import com.connected.accountservice.domain.enums.MovementStatus;
import com.connected.accountservice.domain.enums.MovementType;
import com.connected.accountservice.domain.model.account.AccountTestFactory;
import com.connected.accountservice.domain.model.movement.MovementTestFactory;
import org.assertj.core.api.Assertions;
import org.jdbi.v3.core.statement.StatementContext;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

class MovementMapperTest {

    private final ResultSet resultSetMock;

    private final StatementContext statementContext;

    private final MovementMapper movementMapper;

    MovementMapperTest() {
        this.resultSetMock = Mockito.mock(ResultSet.class);
        this.statementContext = Mockito.mock(StatementContext.class);
        this.movementMapper = new MovementMapper();
    }

    @Test
    void givenResultSet_mustCreateRespectiveAccount() throws SQLException {
        Mockito.when(resultSetMock.getObject("id", UUID.class))
                .thenReturn(MovementDefaultData.id);
        Mockito.when(resultSetMock.getObject("amount", BigDecimal.class))
                .thenReturn(MovementDefaultData.amount);
        Mockito.when(resultSetMock.getObject("accountId", UUID.class))
                .thenReturn(MovementDefaultData.accountId);
        Mockito.when(resultSetMock.getObject("movementType", String.class))
                .thenReturn(MovementDefaultData.movementType.name());
        Mockito.when(resultSetMock.getObject("status", String.class))
                .thenReturn(MovementDefaultData.status.toString());

        final var movementMapped = movementMapper.map(resultSetMock, statementContext);

        final var movementExpected = MovementTestFactory.createAnDefault();

        Assertions.assertThat(movementMapped).isEqualTo(movementExpected);
    }
}