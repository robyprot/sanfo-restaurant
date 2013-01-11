/* Table creation*/

create table APP.Room (
		I integer not null generated always as identity(start with 1, increment by 1),
		RoomNumb integer not null unique,
		Name varchar(20) not null,
		Mq integer,
		primary key (I)
	);

create table APP.Client (
		I integer not null generated always as identity(start with 1, increment by 1),
		ID integer not null unique,
		Name varchar(20) not null,
		Surname varchar(30) not null,
		Counter integer not null,
		Allergy varchar(50),
		primary key (I)
	);

create table APP.Food (
		I integer not null generated always as identity(start with 1, increment by 1),
		Code varchar(10) not null unique,
		Name varchar(20) not null,
		Price numeric(5,2) not null,
		Type varchar(15) not null check (Type like 'Primo' or Type like 'Secondo' or Type like 'Contorno' or Type like 'Dolce' or Type like 'Frutta' or Type like 'Bevanda' or Type like '-----'),
		Description varchar(100),
		primary key (I)
	);
	
create table APP.Waiter (
		I integer not null generated always as identity(start with 1, increment by 1),
		Numb integer not null unique,
		Surname varchar(30) not null,
		Name varchar(20) not null,
		primary key (I)
	);
	
create table APP.TableR (
		I integer not null generated always as identity(start with 1, increment by 1),
		TableNumb integer not null unique,
		Seats integer not null,
		Content integer references APP.Room(RoomNumb) 
						on delete set null
						on update restrict,
		State varchar(10) not null check (State like 'Libero' or State like 'Occupato' or State like '-----'),
		WaiterCode integer references APP.Waiter(Numb)
						   on delete set null
						   on update restrict,
		Joined varchar(30),
		primary key (I)
	); 	

create table APP.Reservation (
		I integer not null generated always as identity(start with 1, increment by 1),
		ReservationNumb integer not null unique,
		Date date not null,
		Time time not null,
		ReservedTable integer references APP.TableR(TableNumb)
							  on delete set null
						      on update restrict,
		IDClient integer references APP.Client(ID)
					     on delete set null
						 on update restrict,
		PeopleNumb integer not null,
		primary key (I)
	);

create table APP.OrderTable (
		I integer not null generated always as identity(start with 1, increment by 1),
		OrderNumb integer not null unique,
		DateOrd date not null,
		TimeOrd time not null,
		RelativeTo integer references APP.TableR(TableNumb)
					   	   on delete set null
					 	   on update restrict,
		SeatsNumb integer not null,
		StateOrd varchar(15) not null check (StateOrd like 'InCorso' or StateOrd like 'Archiviata' or StateOrd like '-----'),
		primary key (I)
	);
	
create table APP.SingleOrder (
		I integer not null generated always as identity(start with 1, increment by 1),		
		OrdNumb integer references APP.OrderTable(OrderNumb)
						on delete set null
						on update restrict,
		Quantity integer not null,
		Dish varchar(10) references APP.Food(Code)
						 on delete set null
						 on update restrict,
		State varchar(15) not null check (State like 'DaCompletare' or State like 'Servita' or State like '-----'),
		Notes varchar(50),
		primary key (I)
	);
		
create table APP.NearTable (
		I integer not null generated always as identity(start with 1, increment by 1),
		Table1 integer references APP.TableR(TableNumb)
					   on delete restrict
					   on update restrict,
		Table2 integer references APP.TableR(TableNumb)
					   on delete restrict
					   on update restrict,
		primary key (I)
	);

create table APP.TablesCheck (
		I integer not null generated always as identity(start with 1, increment by 1),
		Date date not null,
		Time time not null,
		Amount numeric(7,2) not null,
		PeopleNumb integer not null,
		primary key (I)
	);
	
create table APP.Users (
		I integer not null generated always as identity(start with 1, increment by 1),
		ID varchar(5) not null unique,
		Name varchar(40) not null,
		Password varchar(40) not null,
		Type varchar(8) not null check (Type like 'Admin' or Type like 'Waiter'),
		DBupdate boolean not null,
		primary key (I)
	);
	
create table APP.Settings (
		I integer not null generated always as identity(start with 1, increment by 1),
		NameParam varchar(50) not null,
		Value varchar(20) not null,
		primary key (I)
	);
	
	
/*--------------------------------------------------------------------------------------------------------------*/
/*Cancellare tabelle*/	

drop table APP.Settings
drop table APP.Users
drop table APP.TablesCheck
drop table APP.NearTable	
drop table APP.SingleOrder
drop table APP.OrderTable
drop table APP.Reservation
drop table APP.TableR
drop table APP.Waiter
drop table APP.Food
drop table APP.Client
drop table APP.Room


/*---------------------------------------------------------------------------------------------------------------*/	
/* Inserimenti */	

insert into APP.Room (RoomNumb, Name, Mq) values 
(1, 'Venezia', 150),
(2, 'Milano' , 100);

insert into APP.Client (ID, Name, Surname, Counter, Allergy) values 
(1, 'Mauro'		, 'Forlani'		  , 2, null       ),
(2, 'Marco'		, 'Santacatterina', 5, 'latticini'),
(3, 'Francesco' , 'De Canio'	  , 3, null       ),
(4, 'Massimo'	, 'Milluzzo'  	  , 7, null       );

insert into APP.Food (Code, Name, Price, Type, Description) values 
('P1', 'Lasagna'   , 6.50, 'Primo'	, 'Lasagna calda'		   	),
('P2', 'Maccheroni', 4.00, 'Primo'	, 'Maccheroni al ragù'		),
('S1', 'Arrosto'   , 3.50, 'Secondo', 'Arrosto di vitello'   	),
('S2', 'Pollo'	   , 4.50, 'Secondo', 'Pollo arrosto'			);

insert into APP.Waiter (Numb, Surname, Name) values 
(1, 'Ciccio', 'Pasticcio'),
(2, 'Tizio' , 'Caio'     );

insert into APP.TableR (TableNumb, Seats, Content, State, WaiterCode, Joined) values 
(1, 4, 1, 'Libero', 1, null),
(2, 2, 1, 'Libero', 1, null),
(3, 2, 2, 'Libero', 1, null),
(4, 2, 2, 'Libero', 1, null),
(5, 4, 2, 'Libero', 2, null),
(6, 4, 2, 'Libero', 2, null);

insert into APP.Reservation (ReservationNumb, Date, Time, ReservedTable, IDClient, PeopleNumb) values 
(1, '2011-09-28','20:10:00', 1, 1, 4),
(2, '2011-09-29','17:22:00', 2, 2, 2),
(3, '2011-09-30','18:36:00', 3, 3, 2),
(4, '2011-09-30','18:40:00', 4, 4, 2);

insert into APP.OrderTable (OrderNumb, DateOrd, TimeOrd, RelativeTo, SeatsNumb, StateOrd) values
(1, '2011-09-28', '19:30:00', 1, 4, 'Archiviata'),
(2, '2011-09-28', '21:00:00', 2, 2, 'InCorso'),
(3, '2011-09-28', '21:15:00', 3, 2, 'InCorso'),
(4, '2011-09-28', '21:30:00', 1, 4, 'InCorso');

insert into APP.SingleOrder (OrdNumb, Quantity, Dish, State, Notes) values 
(1, 2, 'P1', 'Servita'     , null),
(1, 1, 'S1', 'Servita'	   , null),
(2, 4, 'P2', 'Servita'     , null),
(2, 3, 'S2', 'DaCompletare', null),
(3, 2, 'P1', 'Servita'	   , null),
(3, 1, 'S1', 'DaCompletare', 'Ben cotto'),
(4, 2, 'P1', 'Servita'	   , null),
(4, 2, 'S1', 'Servita'	   , null),
(4, 1, 'P2', 'Servita'	   , null);

insert into APP.NearTable (Table1, Table2) values 
(1, 2),
(2, 3),
(4, 5),
(5, 6),
(1, 4),
(2, 5),
(3, 6);

insert into APP.TablesCheck (Date, Time, Amount, PeopleNumb) values
('2011-08-27', '19:15:00', 100.00, 4),
('2011-08-28', '19:10:00', 150.00, 2),
('2011-08-29', '19:25:00', 200.00, 4);

insert into APP.Users (ID, Name, Password, Type, DBupdate) values
('Amm1', 'Username', 'Password', 'Admin', true);

insert into APP.Settings (NameParam, Value) values
('Sconto'	 , '30'	 ),
('Contatore' , '5' 	 ),
('FirstLogin', 'true'),
('Update'	 , 'true'),
('Coperto'	 , '1'	 ),
('IsFirst'	 , 'boo' );



/*---------------------------------------------------------------------------------------------------------------*/
/* Query */

update APP.Client
set Name = 'MassimoAAA', Surname = 'MilluzzoRRR'
where ID = 4



/* Estrae il cliente più fedele*/
SELECT Name, Surname, Counter
FROM APP.Client
WHERE Counter =	( SELECT max(Counter) FROM APP.Client)



/* Estrae i clienti vips (contatore>=5)*/
SELECT Name, Surname, Counter
FROM APP.Client
WHERE Counter>=5



/* Estrae i piatti più ordinati */
/**/
/**/
/* Estraggo il numero max */
select max(NUMBER) as M
from (	select sum(Quantity) as NUMBER
		from APP.SingleOrder
		group by Dish
		order by sum(Quantity)) tab	
/* Estraggo i piatti con il numero di ordini max*/		
select *		
from APP.Food 
where Code in (select Dish		
			   from (  select SO.Dish, sum(Quantity) as NUMBER
					   from APP.SingleOrder SO join APP.Food F on (SO.Dish=F.Code)		
					   group by SO.Dish
					   order by sum(SO.Quantity)) tab1
					   where NUMBER = 4)		

					   
					   
/* Estrae i tavoli preferiti */
/**/
/**/					   
/* Estraggo il numero max */
select max(NUMBER) 
from ( select RelativeTo, count(RelativeTo) as NUMBER
	   from APP.ORDERTABLE
	   group by RelativeTo
	   order by count(RelativeTo)) tab
/* Estraggo i tavoli preferiti che sono stati occupati di un numero di volte pari a max */					   
select TableNumb, Seats, Content
from APP.TABLER
where TableNumb =
(select RelativeTo
from APP.ORDERTABLE
group by RelativeTo
having count(*) = 2)		


/* Estrae le tuple comprese in un intervallo di date */
SELECT * 
FROM APP.TABLESCHECK 
WHERE Date >= '2011-08-27' and Date <= '2011-08-29'





select DBupdate
from APP.USERS
where Type = 'Waiter' and ID = '1'	
	








select Counter
from APP.Client C join APP.Reservation R on (C.ID=R.IDClient)
where R.ReservedTable = 3 and R.Date = '2011-05-30'


delete from APP.Client where I = 5


SELECT * 
FROM APP.Reservation
WHERE Date = '2011-09-30'


select max(ID)
from APP.CLIENT


/* Per effettuare modifiche */
UPDATE APP.CLIENT
SET Counter = 7, Surname = 'Milluzzo'
WHERE ID = 4

UPDATE APP.TABLER
SET State = 'Libero', Joined = null

UPDATE APP.USERS
SET DBupdate = true

SELECT *
FROM APP.USERS


/* Cancella tutto il contenuto di una tabella */
truncate table APP.TABLESCHECK



select count(*) from APP.USERS where Type = 'Admin'


