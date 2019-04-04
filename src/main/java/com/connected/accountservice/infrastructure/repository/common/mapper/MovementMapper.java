package com.connected.accountservice.infrastructure.repository.common.mapper;

import com.connected.accountservice.domain.enums.MovementStatus;
import com.connected.accountservice.domain.enums.MovementType;
import com.connected.accountservice.domain.model.movement.Movement;
import com.connected.accountservice.domain.model.movement.MovementFactory;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class MovementMapper implements RowMapper<Movement> {

    @Override
    public Movement map(final ResultSet resultSet, final StatementContext context) throws SQLException {
        final var movementId = resultSet.getObject("id", UUID.class);
        final var amount = resultSet.getObject("amount", BigDecimal.class);
        final var accountId = resultSet.getObject("accountId", UUID.class);
        final var movementType = resultSet.getObject("movementType", String.class);
        final var status = resultSet.getObject("status", String.class);

        return MovementFactory.createExistentMovement(movementId, amount, accountId,
                MovementType.valueOf(movementType), MovementStatus.valueOf(status));
    }
}
