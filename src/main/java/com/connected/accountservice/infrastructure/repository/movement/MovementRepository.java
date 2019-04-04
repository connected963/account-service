package com.connected.accountservice.infrastructure.repository.movement;

import com.connected.accountservice.domain.model.movement.Movement;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.Optional;
import java.util.UUID;

public interface MovementRepository {

    @SqlUpdate("insert into movement(id, amount, accountId, movementType, status) " +
            "values(:id, :amount, :accountId, :movementType, :status) ")
    void insert(@BindBean final Movement movement);

    @SqlUpdate("update movement set amount = :amount, accountId = :accountId," +
            " movementType = :movementType, status = :status" +
            " where id = :id")
    void update(@BindBean final Movement movement);

    @RegisterBeanMapper(Movement.class)
    @SqlQuery("select * from movement where id = :movementId")
    Optional<Movement> findMovementById(final UUID movementId);

}
