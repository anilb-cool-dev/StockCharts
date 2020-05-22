create table price_history(ticker varchar(6), date date, price decimal(7,2));

create unique index uniquedate on price_history(ticker, date);

create table purchase_history(ticker varchar(6), date date);

create unique index uniquedate on purchase_history(date);