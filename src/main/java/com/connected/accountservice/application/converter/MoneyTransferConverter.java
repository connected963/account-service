package com.connected.accountservice.application.converter;

import com.connected.accountservice.application.inputmodel.MoneyTransferInputModel;
import com.connected.accountservice.domain.enums.MovementType;
import com.connected.accountservice.domain.model.movement.Movement;
import com.connected.accountservice.domain.model.movement.MovementFactory;

public class MoneyTransferConverter {

    private MoneyTransferConverter() {

    }

    public static Movement convertToAccountFromOutputMovement(
            final MoneyTransferInputModel moneyTransferInputModel) {

        return MovementFactory.createNewMovement(moneyTransferInputModel.getAmount(),
                moneyTransferInputModel.getAccountIdFrom(), MovementType.OUTPUT);
    }

}
