CREATE TABLE ramallo_tipos_DNI(
id_tipo_doc int IDENTITY (1,1) NOT NULL,
description varchar(10),
CONSTRAINT _ramallo_pk_tipo_doc PRIMARY KEY (id_tipo_doc)
)




create table ramallo_Personas (
documento int  not null,
nombre  varchar(50),
fecha_nac datetime,
altura numeric (3,3),
id_tipo_doc int,
CONSTRAINT ramallo_pk_documento PRIMARY KEY (documento),
CONSTRAINT ramallo_fk_persona FOREIGN KEY (id_tipo_doc)
REFERENCES ramallo_tipos_DNI (id_tipo_doc)
)
