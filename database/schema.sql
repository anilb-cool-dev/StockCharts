create table price_history(ticker varchar(6), date datetime, price decimal(7,2));

create unique index uniquedate on price_history(date);

create table purchase_history(ticker varchar(6), date datetime);

create unique index uniquedate on purchase_history(date);