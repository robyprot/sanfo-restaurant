/* Table creation*/

create table APP.Room (
		RoomNumb integer not null,
		Name varchar(20) not null,
		Type varchar(15) not null check (Type like 'Fumatori' or Type like 'NonFumatori' or Type like '-----'),
		Mq integer,
		primary key (RoomNumb)
	);
	
create table APP.Client (
		ID integer not null generated always as identity(start with 1, increment by 1),
		Name varchar(20) not null,
		Surname varchar(30) not null,
		Counter integer not null,
		Allergy varchar(50),
		primary key (ID)
	);

create table APP.Food (
		Code varchar(10) not null,
		Name varchar(20) not null,
		Price numeric(5,2) not null,
		Type varchar(15) not null check (Type like 'Primo' or Type like 'Secondo' or Type like 'Contorno' or Type like 'Dolce' or Type like 'Frutta' or Type like 'Bevanda' or Type like '-----'),
		Description varchar(100),
		primary key (Code)
	);
		
create table APP.Waiter (
		Numb integer not null generated always as identity(start with 1, increment by 1),
		Surname varchar(30) not null,
		Name varchar(20) not null,
		primary key (Numb)
	);
		
create table APP.TableR (
		TableNumb integer not null,
		Seats integer not null,
		Content integer not null references APP.Room(RoomNumb),
		State varchar(10) not null check (State like 'Libero' or State like 'Occupato' or State like '-----'),
		WaiterCode integer not null references APP.Waiter(Numb),
		Joined varchar(30),
		primary key (TableNumb)
	); 	

create table APP.Reservation (
		ReservationNumb integer not null generated always as identity(start with 1, increment by 1),
		Date date not null,
		Time time not null,
		ReservedTable integer not null references APP.TableR(TableNumb),
		IDClient integer not null references APP.Client(ID),
		PeopleNumb integer not null,
		SmokingRoom varchar(5) not null check (SmokingRoom like 'Si' or SmokingRoom like 'No' or SmokingRoom like '--'),
		primary key (ReservationNumb)
	);

create table APP.OrderTable (
		OrderNumb integer not null generated always as identity(start with 1, increment by 1),
		DateOrd date not null,
		TimeOrd time not null,
		RelativeTo integer not null references APP.TableR(TableNumb),
		SeatsNumb integer not null,
		StateOrd varchar(15) not null check (StateOrd like 'InCorso' or StateOrd like 'Archiviata' or StateOrd like '-----'),
		primary key (OrderNumb)
	);
	
create table APP.SingleOrder (
		RowNumb integer not null generated always as identity(start with 1, increment by 1),		
		OrdNumb integer not null references APP.OrderTable(OrderNumb),
		Quantity integer not null,
		Dish varchar(10) not null references APP.Food(Code),
		State varchar(15) not null check (State like 'DaCompletare' or State like 'Servita' or State like '-----'),
		Notes varchar(50),
		primary key (RowNumb)
	);
		
create table APP.NearTable (
		Table1 integer not null references APP.TableR(TableNumb),
		Table2 integer not null references APP.TableR(TableNumb),
		primary key (Table1,Table2)
	);

create table APP.TablesCheck (
		ID integer not null generated always as identity(start with 1, increment by 1),
		Date date not null,
		Time time not null,
		Amount numeric(7,2) not null,
		PeopleNumb integer not null,
		primary key (ID)
	);
	
create table APP.Users (
		ID varchar(5) not null,
		Name varchar(20) not null,
		Password varchar(20) not null,
		Type varchar(8) not null check (Type like 'Admin' or Type like 'Waiter'),
		primary key (ID, Name)
	);
	

/*--------------------------------------------------------------------------------------------------------------*/
/*Cancellare tabelle*/	

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
drop table APP.Users

/*---------------------------------------------------------------------------------------------------------------*/	
/* Inserimenti */	

insert into APP.Room (RoomNumb, Name, Type, Mq) values 
(1, 'Venezia', 'Fumatori'	, 150),
(2, 'Milano' , 'NonFumatori', 100 );

insert into APP.Client (Name, Surname, Counter, Allergy) values 
('Mauro'	, 'Forlani'		  , 2, null       ),
('Marco'	, 'Santacatterina', 5, 'latticini'),
('Francesco', 'De Canio'	  , 3, null       ),
('Massimo'	, 'Milluzzo'	  , 7, null       );

insert into APP.Food (Code, Name, Price, Type, Description) values 
('P1', 'Lasagna'   , 6.50, 'Primo'	, 'Lasagna calda'		   	),
('P2', 'Maccheroni', 4.00, 'Primo'	, 'Maccheroni al ragù'		),
('S1', 'Arrosto'   , 3.50, 'Secondo', 'Arrosto di vitello'   	),
('S2', 'Pollo'	   , 4.50, 'Secondo', 'Pollo arrosto'			);

insert into APP.Waiter (Surname, Name) values 
('Ciccio', 'Pasticcio'),
('Tizio' , 'Caio'     );

insert into APP.TableR (TableNumb, Seats, Content, State, WaiterCode, Joined) values 
(1, 4, 1, 'Occupato'  , 1, null),
(2, 2, 1, 'Occupato'  , 1, null),
(3, 2, 2, 'Occupato', 1, null),
(4, 2, 2, 'Libero'  , 1, null),
(5, 4, 2, 'Libero'  , 2, null),
(6, 4, 2, 'Libero'  , 2, null);

insert into APP.Reservation (Date, Time, ReservedTable, IDClient, PeopleNumb, SmokingRoom) values 
('2011-09-28','20:10:00', 1, 1, 4, 'Si'),
('2011-09-29','17:22:00', 2, 2, 2, 'Si'),
('2011-09-30','18:36:00', 3, 3, 2, 'No'),
('2011-09-30','18:40:00', 4, 4, 2, 'No');

insert into APP.OrderTable (DateOrd, TimeOrd, RelativeTo, SeatsNumb, StateOrd) values
('2011-09-28', '19:30:00', 1, 4, 'Archiviata'),
('2011-09-28', '21:00:00', 2, 2, 'InCorso'),
('2011-09-28', '21:15:00', 3, 2, 'InCorso'),
('2011-09-28', '21:30:00', 1, 4, 'InCorso');

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

insert into APP.Users (ID, Name, Password, Type) values
('Amm', 'Username', 'Password', 'Admin');


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












/* DA CONVERTIRE IN INGLESE !!!!!!!!!!!       */


/*Stampa conto di un tavolo*/
SELECT C.Codice, C.Nome, SO.Quantità, C.Prezzo 
FROM APP.Ordinazione O join APP.SingolaOrdinazione SO on (O.NumeroOrd=SO.NumOrd) 
join APP.Tavolo T on (O.RelativaA=T.NumeroTav)
join APP.Cibo C on (SO.Piatto=C.Codice)  
WHERE T.Numerotav = 3

/*Stampa le ordinazioni non completate da un tavolo*/
SELECT C.Codice, C.Nome, SO.Quantità 
FROM APP.Ordinazione O join APP.SingolaOrdinazione SO on (O.NumeroOrd=SO.NumOrd) 
join APP.Tavolo T on (O.RelativaA=T.NumeroTav)
join APP.Cibo C on (SO.Piatto=C.Codice)  
WHERE SO.Stato = 'DaCompletare' and T.Numerotav = 5

/*Ritorna il numero di volte che un cliente è venuto al ristorante passandogli ID oppure Nome e Cognome*/
select Contatore 
from APP.Cliente 
where ID='1' or (Nome='Mauro' and Cognome='Forlani');

/*Restituisce lo stato di un tavolo (Libero/Occupato)*/
select Stato 
from APP.Tavolo 
where NumeroTav=3;

/*Ritorna il numero di righe dell'Ordinazione 1*/
select count(*)
from APP.SingolaOrdinazione
where NumOrd = 1

/*Stampa i tavoli liberi con il numero di posti */
SELECT NumeroTav, Nposti, Contenuto
FROM APP.Tavolo
WHERE Stato = 'Libero'
ORDER BY Contenuto

/*Stampa le prenotazioni di un certo giorno ordinate per orario*/
SELECT *
FROM APP.Prenotazione
WHERE Data = '2011-09-30'
ORDER BY Ora



/*
/*Ritorna i tavoli liberi da unire per avere numero di posti = 5 (funziona solo per coppie di tavoli)*/
SELECT V.Tavolo1, V.Tavolo2 
FROM APP.Tavolo T1 join APP.TavVicino V on (T1.NumeroTav=V.Tavolo1) join APP.Tavolo T2 on (T2.NumeroTav=V.Tavolo2)
WHERE T1.Stato='Libero' and T2.Stato='Libero' and (T1.Nposti + T2.Nposti)>=5 and (T1.Nposti + T2.Nposti)<=7
*/






/*
SELECT  V1.Tavolo1, V1.Tavolo2, V2.Tavolo2
FROM APP.Tavolo T1 join APP.TavVicino V1 on (T1.NumeroTav=V1.Tavolo1) 
	 join APP.TavVicino V2 on (V1.Tavolo2=V2.Tavolo1)
	 join APP.Tavolo T2 on (V2.Tavolo2=T2.NumeroTav)
	 join APP.Tavolo T3 on (V1.Tavolo2=T3.NumeroTav)
WHERE T1.Stato='Libero' and T2.Stato='Libero' and (V1.Tavolo1<>V1.Tavolo2<>V2.Tavolo2) 
*/







