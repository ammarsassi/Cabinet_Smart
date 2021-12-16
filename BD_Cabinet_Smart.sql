use cabinet;
    create table medecins(
id bigint not null auto_increment primary key,
 specialite enum('Spec1', 'Spec2', 'Spec3'),
 nom varchar(20),
 prenom varchar(20), 
adresse varchar(250), 
numtel varchar(20)
    ) ENGINE=InnoDB;

     create table patients(
id bigint not null auto_increment primary key,
 nom varchar(20),
 prenom varchar(20),
 adresse varchar(250),
 numtel varchar(20)
    ) ENGINE=InnoDB;

   
   create table rdvs ( 
id_patient bigint not null references patients(id),
 id_medecin bigint not null references medecins(id),
 jour varchar(30),
 primary key(id_patient, id_medecin)
    ) ENGINE=InnoDB;

    create table fiches(
id bigint not null auto_increment primary key,
 id_patient bigint not null,
 id_medecin bigint not null, 
avis varchar(250),
 medec varchar(250)
    ) ENGINE=InnoDB;

alter table fiches add foreign key(id_patient) references patients(id);

alter table fiches add foreign key(id_medecin) references medecins(id);

 create table passwords( 
id bigint not null auto_increment primary key,
id_medecin bigint not null references medecins(id),
 password varchar(20)
)ENGINE=InnoDB;