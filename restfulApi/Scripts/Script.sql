CREATE SEQUENCE SEQ_REST;

CREATE TABLE TBL_REST(
	NUM NUMBER(10),
	ID VARCHAR2(1000) NOT NULL,
	PW VARCHAR2(2000) NOT NULL,
	NICKNAME VARCHAR2(500) NOT NULL,
	TOKEN VARCHAR(10)
);

ALTER TABLE TBL_REST ADD CONSTRAINT PK_REST PRIMARY KEY(NUM);