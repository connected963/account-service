create table account
(
    id        uuid          not null,
    balance   number(12, 5) not null,
    overdraft number(12, 5) not null
);

create table movement
(
    id           uuid                                        not null,
    amount       number(12, 5)                               not null,
    accountId    uuid                                        not null,
    movementType enum ('INPUT', 'OUTPUT')                    not null,
    status       enum ('PROCESSING', 'COMPLETED', 'ABORTED') not null
);