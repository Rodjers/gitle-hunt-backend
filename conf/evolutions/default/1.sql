# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table observation (
  id                        bigint not null,
  amount                    integer not null,
  animal                    varchar(255) not null,
  longitude                 float not null,
  latitude                  float not null,
  date                      varchar(255),
  time                      varchar(255),
  constraint pk_observation primary key (id))
;

create sequence observation_seq;




# --- !Downs

drop table if exists observation cascade;

drop sequence if exists observation_seq;

