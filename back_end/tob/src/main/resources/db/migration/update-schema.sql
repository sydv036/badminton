CREATE TABLE court_schedules
(
    court_schedules_id VARCHAR(22)    NOT NULL,
    system_delete_flag BIT(1)         NOT NULL,
    courts             VARCHAR(22)    NOT NULL,
    schedules          VARCHAR(22)    NOT NULL,
    total_price        DECIMAL(12, 2) NOT NULL,
    CONSTRAINT pk_court_schedules PRIMARY KEY (court_schedules_id)
);