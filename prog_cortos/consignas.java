/*
tres postulantes a un empleo, 

Nombre del postulante, 
cantidad total de preguntas 
que se le realizaron y 
cantidad de preguntas que contestó correctamente. 

Lea los datos de los tres postulantes, 
informe el nivel de cada uno.
Nivel Superior:  Porcentaje >= 90%
Nivel Medio:     75% <= Porcentaje < 90%     
Nivel Regular:   50% <= Porcentaje < 75%
Fuera de Nivel:  Porcentaje < 50%

Indique finalmente el nombre del postulante
 que ganó el puesto. 

*/
ppc2 = (pc/pt)*100;

if (ppc2>= 90)
	rango="superior";
if ((ppc2>= 75) && (90<ppc2))
	rango="medio";
if ((ppc2 >= 50) &&( 75<ppc2))
	rango="regular";
if (ppc2<50)
	rango="Fuera de Rango";