# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table observation (
  id                        bigint not null,
  amount                    integer not null,
  animal                    varchar(255) not null,
  longitude                 double precision not null,
  latitude                  double precision not null,
  timestamp                 varchar(255),
  constraint pk_observation primary key (id))
;

create sequence observation_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists observation;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists observation_seq;

