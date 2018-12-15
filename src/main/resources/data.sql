
INSERT INTO DW_CUSTOMERS (CUST_ID, FIRSTNAME, LASTNAME, ADDRESS, CITY, STATE)
VALUES (1, 'Robert','DeNiro', 'Vrtna 5', 'Čakovec', 'Croatia');
INSERT INTO DW_CUSTOMERS (CUST_ID, FIRSTNAME, LASTNAME, ADDRESS, CITY, STATE)
VALUES (2, 'Val','Kilmer', 'Vukovarska 1', 'Čakovec', 'Croatia');
INSERT INTO DW_CUSTOMERS (CUST_ID, FIRSTNAME, LASTNAME, ADDRESS, CITY, STATE)
VALUES (3, 'Tom','Cruise', 'LA boulevar 1231', 'Los Angeles', 'USA');
INSERT INTO DW_CUSTOMERS (CUST_ID, FIRSTNAME, LASTNAME, ADDRESS, CITY, STATE)
VALUES (4, 'Megan','Fox', 'LA boulevar 1231', 'Los Angeles', 'USA');

/*
create sequence cm_push_batches_seq;
create table CM_PUSH_BATCHES(
  BATCH_ID bigint default cm_push_batches_seq.nextval primary key,
  CHANNEL_ID int NOT NULL,
  CREATED TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSUMED  TIMESTAMP,
  STATUS int
);
*/
insert into CM_PUSH_BATCHES(CHANNEL_ID, STATUS, CREATED) values (3, 1,CURRENT_TIMESTAMP());
insert into CM_PUSH_BATCHES(CHANNEL_ID, STATUS, CREATED) values (7, 1,CURRENT_TIMESTAMP());
insert into CM_PUSH_BATCHES(CHANNEL_ID, STATUS, CREATED) values (55, 1,CURRENT_TIMESTAMP());

/*
create sequence CM_MESSAGES_SEQ;

CREATE TABLE CM_MESSAGES(
  MESSAGE_ID bigint default CM_MESSAGES_SEQ.nextval primary key,
  BATCH_ID bigint,
  CUST_ID bigint,
  CAMPAIGN_ID int NOT NULL,
  CHANNEL_ID int NOT NULL,
  CREATED TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  STATUS int
);
*/


insert into CM_MESSAGES(BATCH_ID, CUST_ID,CAMPAIGN_ID,CHANNEL_ID,STATUS, created)
values ((select batch_id from cm_push_batches where channel_id = 3), 1, 123,3,1,CURRENT_TIMESTAMP());

insert into CM_MESSAGES(BATCH_ID, CUST_ID,CAMPAIGN_ID,CHANNEL_ID,STATUS, created)
values ((select batch_id from cm_push_batches where channel_id = 3), 2, 123,3,1,CURRENT_TIMESTAMP());

insert into CM_MESSAGES(BATCH_ID, CUST_ID,CAMPAIGN_ID,CHANNEL_ID,STATUS, created)
values ((select batch_id from cm_push_batches where channel_id = 3), 3, 123,3,1,CURRENT_TIMESTAMP());

insert into CM_MESSAGES(BATCH_ID, CUST_ID, CAMPAIGN_ID,CHANNEL_ID,STATUS, created)
values ((select batch_id from cm_push_batches where channel_id = 3), 4, 123,3,1,CURRENT_TIMESTAMP());

insert into CM_MESSAGES(BATCH_ID, CUST_ID,CAMPAIGN_ID,CHANNEL_ID,STATUS, created)
values ((select batch_id from cm_push_batches where channel_id = 7), 1, 123,7,1,CURRENT_TIMESTAMP());

insert into CM_MESSAGES(BATCH_ID, CUST_ID,CAMPAIGN_ID,CHANNEL_ID,STATUS, created)
values ((select batch_id from cm_push_batches where channel_id = 7), 2, 123,7,1,CURRENT_TIMESTAMP());

insert into CM_MESSAGES(BATCH_ID, CUST_ID,CAMPAIGN_ID,CHANNEL_ID,STATUS, created)
values ((select batch_id from cm_push_batches where channel_id = 7), 3, 123,7,1,CURRENT_TIMESTAMP());

insert into CM_MESSAGES(BATCH_ID, CUST_ID, CAMPAIGN_ID,CHANNEL_ID,STATUS, created)
values ((select batch_id from cm_push_batches where channel_id = 7), 4, 123,7,1,CURRENT_TIMESTAMP());

insert into CM_MESSAGES(BATCH_ID, CUST_ID,CAMPAIGN_ID,CHANNEL_ID,STATUS, created)
values ((select batch_id from cm_push_batches where channel_id = 55), 1, 123,55,1,CURRENT_TIMESTAMP());

insert into CM_MESSAGES(BATCH_ID, CUST_ID,CAMPAIGN_ID,CHANNEL_ID,STATUS, created)
values ((select batch_id from cm_push_batches where channel_id = 55), 2, 123,55,1,CURRENT_TIMESTAMP());

insert into CM_MESSAGES(BATCH_ID, CUST_ID,CAMPAIGN_ID,CHANNEL_ID,STATUS, created)
values ((select batch_id from cm_push_batches where channel_id = 55), 3, 123,55,1,CURRENT_TIMESTAMP());

insert into CM_MESSAGES(BATCH_ID, CUST_ID, CAMPAIGN_ID,CHANNEL_ID,STATUS, created)
values ((select batch_id from cm_push_batches where channel_id = 55), 4, 123,55,1,CURRENT_TIMESTAMP());

/*
CREATE TABLE ORGANIZER
(
  ID int  NOT NULL AUTO_INCREMENT,NAME varchar(20)  NOT NULL,
  CREATED TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (ID)
);


CREATE TABLE VENUE
(
  ID         int NOT NULL AUTO_INCREMENT,
  CREATED    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  NAME       varchar(256),
  STREET_ADDRESS    varchar(256),
  STREET_ADDRESS2   varchar(256),
  CITY              varchar(256),
  STATE             varchar(256),
  COUNTRY           VARCHAR(256),
  POSTAL_CODE       varchar(256),
  PRIMARY KEY (ID)
);


CREATE TABLE EVENT
(
  ID              int	NOT NULL AUTO_INCREMENT,
  CREATED         TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
  NAME            varchar(256),
  DESCRIPTION     varchar(2048),
  START_TIME      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  END_TIME        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  ZONE_ID         blob,
  STARTED       int,
  ORGANIZER_ID    int                          NOT NULL,
  VENUE_ID        int,
  PRIMARY KEY (ID),
   FOREIGN KEY (ORGANIZER_ID) REFERENCES ORGANIZER(ID) ON DELETE CASCADE,
  FOREIGN KEY (VENUE_ID) REFERENCES VENUE(ID)
);


CREATE TABLE PARTICIPANT
(
  ID  	int        NOT NULL AUTO_INCREMENT,
  CREATED    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  NAME      varchar(256) NOT NULL,
  EMAIL      varchar(256) NOT NULL,
  CHECKED_IN   int,
  EVENT_ID    int        NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (EVENT_ID) REFERENCES event(ID),
  UNIQUE (EVENT_ID, EMAIL)
);
*/