create table account
(
    id        uuid          not null,
    balance   number(10, 2) not null,
    overdraft number(10, 2) not null
);

create table movement
(
    id           uuid                                        not null,
    amount       number(10, 2)                               not null,
    accountId    uuid                                        not null,
    movementType enum ('INPUT', 'OUTPUT')                    not null,
    status       enum ('PROCESSING', 'COMPLETED', 'ABORTED') not null
);