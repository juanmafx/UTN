select * from equipos


select * from fechas
where fecha between '2018-05-01' and '2018-06-06' ;

select * from fechas
where fecha between '2018-05-01' and '2018-06-06' ;

select * from fechas
where fecha between '2018-05-01' and '2018-06-06' 
order by fecha  asc ;

select c.NUMERO_FECHA , c.FECHA 
from fechas c
where NUMERO_FECHA =1 ;

select * from partidos 
where ID_ESTADO_PARTIDO = 3 or ID_ESTADO_PARTIDO = 4

select p.*
from fechas f join campeonatos c 
on f.id_campeonato = c.id_campeonato join partidos p on p.ID_PARTIDO = c.ID_CAMPEONATO
where f.NUMERO_FECHA = 1 and c.ID_CAMPEONATO = 1;

select p.*
from fechas f,  campeonatos c ,  partidos p 
where  f.id_campeonato = c.id_campeonato and p.ID_PARTIDO = c.ID_CAMPEONATO and 
 f.NUMERO_FECHA = 1 and c.ID_CAMPEONATO = 1;

