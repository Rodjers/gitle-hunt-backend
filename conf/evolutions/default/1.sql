# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table observation (
  id                        bigint not null,
  amount                    integer,
  animal                    varchar(255),
  longitude                 bigint,
  latitude                  bigint,
  timestamp                 timestamp,
  constraint pk_observation primary key (id))
;

create sequence observation_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists observation;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists observation_seq;

