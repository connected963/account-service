package com.connected.accountservice.infrastructure.repository.movement;

import com.connected.accountservice.domain.model.movement.Movement;
import com.connected.accountservice.infrastructure.repository.common.mapper.MovementMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.Optional;
import java.util.UUID;

public interface MovementRepository {

    @SqlUpdate("insert into movement(id, amount, accountId, movementType, status) " +
            "values(:id, :amount, :accountId, :movementType, :status)")
    void insert(@BindBean final Movement movement);

    @SqlUpdate("update movement set amount = :amount, accountId = :accountId," +
            " movementType = :movementType, status = :status" +
            " where id = :id")
    void update(@BindBean final Movement movement);

    @RegisterRowMapper(MovementMapper.class)
    @SqlQuery("select * from movement where id = :id")
    Optional<Movement> findMovementById(@Bind("id") final UUID movementId);

}
