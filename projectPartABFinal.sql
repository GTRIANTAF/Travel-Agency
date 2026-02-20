DROP DATABASE IF EXISTS travel_agency;
CREATE DATABASE travel_agency;
USE travel_agency;

CREATE TABLE language_ref(
lang_code VARCHAR(5) NOT NULL,
lang_name VARCHAR(50) NOT NULL,
PRIMARY KEY(lang_code)
);

CREATE TABLE destination(
dst_id INT AUTO_INCREMENT,
dst_name VARCHAR(100) NOT NULL,
dst_descr TEXT,
dst_rtype ENUM('LOCAL','ABROAD') NOT NULL,
dst_language_code VARCHAR(5),
PRIMARY KEY(dst_id),
CONSTRAINT dstolang_ref FOREIGN KEY(dst_language_code) REFERENCES language_ref(lang_code)
ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE accommodation(
acc_id INT NOT NULL AUTO_INCREMENT,
acc_name VARCHAR(250) NOT NULL,
acc_type ENUM('Hotel', 'Hostel', 'Resort', 'Apartment', 'Room') NOT NULL,
acc_rating DECIMAL(3,2) DEFAULT 0.00,
acc_stars ENUM('1','2','3','4','5') DEFAULT NULL,
acc_status ENUM('ACTIVE', 'INACTIVE') DEFAULT 'ACTIVE',
acc_street VARCHAR(50) DEFAULT NULL,
acc_street_num INT(11) DEFAULT NULL, 
acc_postcode VARCHAR(50) DEFAULT NULL,
acc_dst_id INT NOT NULL, 
acc_phone VARCHAR(20) DEFAULT NULL,
acc_email VARCHAR(50) DEFAULT NULL,
acc_roomcapacity INT DEFAULT NULL,
acc_pricepernight DECIMAL(10,2) DEFAULT NULL,
acc_facilities SET('WiFi', 'Restaurant', 'AC', 'DisabledAccess') DEFAULT NULL,
PRIMARY KEY(acc_id),
CONSTRAINT dstacc FOREIGN KEY (acc_dst_id) REFERENCES destination(dst_id)
ON DELETE CASCADE ON UPDATE CASCADE
);
  
CREATE TABLE branch(
br_code INT AUTO_INCREMENT,
br_street VARCHAR(50) NOT NULL,
br_num INT,
br_city VARCHAR(30) NOT NULL,
br_manager_AT CHAR(10),
PRIMARY KEY(br_code)
);

CREATE TABLE worker(
wrk_AT CHAR(10) NOT NULL,
wrk_name VARCHAR(30) NOT NULL,
wrk_lname VARCHAR(30) NOT NULL,
wrk_email VARCHAR(100) NOT NULL,
wrk_salary DECIMAL(10,2),
wrk_br_code INT,
PRIMARY KEY(wrk_AT),
CONSTRAINT wrkbr FOREIGN KEY(wrk_br_code) REFERENCES branch(br_code)
ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE admin(
adm_AT CHAR(10) NOT NULL,
adm_type ENUM('LOGISTICS','ADMINISTRATIVE','ACCOUNTING') NOT NULL,
adm_diploma VARCHAR(200) NOT NULL,
PRIMARY KEY(adm_AT)
);

CREATE TABLE dba (
  dba_id INT AUTO_INCREMENT PRIMARY KEY,
  dba_admin_AT CHAR(10) NOT NULL,
  dba_start_date DATETIME NOT NULL,
  dba_end_date DATETIME DEFAULT NULL,
  CONSTRAINT dbaadmin FOREIGN KEY (dba_admin_AT) REFERENCES admin(adm_AT)
  ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE system_log (
  log_id INT AUTO_INCREMENT PRIMARY KEY,
  log_username VARCHAR(50) NOT NULL,
  log_date DATETIME NOT NULL,           
  log_action VARCHAR(20) NOT NULL,      
  log_table VARCHAR(50) NOT NULL
);

CREATE TABLE driver(
drv_AT CHAR(10) NOT NULL,
drv_license ENUM('A','B','C','D') NOT NULL,
drv_route ENUM('LOCAL','ABROAD') NOT NULL,
drv_experience TINYINT,
PRIMARY KEY(drv_AT)
);

CREATE TABLE guide(
gui_AT CHAR(10) NOT NULL,
gui_cv TEXT,
PRIMARY KEY(gui_AT)
);

CREATE TABLE customer(
cust_id INT AUTO_INCREMENT,
cust_name VARCHAR(30) NOT NULL,
cust_lname VARCHAR(30) NOT NULL,
cust_email VARCHAR(100) NOT NULL,
cust_phone VARCHAR(15) NOT NULL,
cust_address TEXT,
cust_birth_date DATE,
PRIMARY KEY(cust_id)
);

CREATE TABLE trip(
tr_id INT AUTO_INCREMENT,
tr_departure DATETIME NOT NULL,
tr_return DATETIME NOT NULL,
tr_maxseats TINYINT,
tr_cost_adult DECIMAL(10,2),
tr_cost_child DECIMAL(10,2),
tr_status ENUM('PLANNED','CONFIRMED','ACTIVE','COMPLETED','CANCELLED') NOT NULL,
tr_min_participants TINYINT,
tr_br_code INT,
tr_gui_AT CHAR(10),
tr_drv_AT CHAR(10),
PRIMARY KEY(tr_id),
CONSTRAINT tripbr FOREIGN KEY(tr_br_code) REFERENCES branch(br_code)
ON UPDATE CASCADE ON DELETE SET NULL,
CONSTRAINT trgui FOREIGN KEY(tr_gui_AT) REFERENCES guide(gui_AT)
ON UPDATE CASCADE ON DELETE SET NULL,
CONSTRAINT trdr FOREIGN KEY(tr_drv_AT) REFERENCES driver(drv_AT)
ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE trip_accommodation (
  ta_id INT AUTO_INCREMENT PRIMARY KEY,
  ta_trip_id INT NOT NULL,
  ta_acc_id INT NOT NULL, 
  ta_check_in DATETIME NOT NULL,
  ta_check_out DATETIME NOT NULL,
  ta_rooms INT DEFAULT 0,
  ta_total_cost DECIMAL(10,2) DEFAULT 0.00,
  
  FOREIGN KEY (ta_trip_id) REFERENCES trip(tr_id) ON DELETE CASCADE,
  FOREIGN KEY (ta_acc_id) REFERENCES accommodation(acc_id) ON DELETE CASCADE
);

CREATE TABLE event(
ev_tr_id INT,
ev_start DATETIME NOT NULL,
ev_end DATETIME NOT NULL,
ev_descr TEXT,
PRIMARY KEY(ev_tr_id,ev_start),
CONSTRAINT eventr FOREIGN KEY(ev_tr_id) REFERENCES trip(tr_id)
ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE reservation(
res_tr_id INT NOT NULL,
res_seatnum TINYINT NOT NULL,
res_cust_id INT NOT NULL,
res_status ENUM('PENDING','CONFIRMED','PAID','CANCELLED') NOT NULL,
res_total_cost DECIMAL(10,2),
PRIMARY KEY(res_tr_id,res_seatnum),
CONSTRAINT restr FOREIGN KEY(res_tr_id) REFERENCES trip(tr_id)
ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT rescust FOREIGN KEY(res_cust_id) REFERENCES customer(cust_id)
ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE travel_to(
to_tr_id INT NOT NULL,
to_dst_id INT NOT NULL,
to_arrival DATETIME NOT NULL,
to_departure DATETIME NOT NULL,
to_sequence TINYINT,
PRIMARY KEY(to_tr_id,to_dst_id),
CONSTRAINT trav_totr FOREIGN KEY(to_tr_id) REFERENCES trip(tr_id)
ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT trav_todst FOREIGN KEY(to_dst_id) REFERENCES destination(dst_id)
ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE languages(
lng_gui_AT CHAR(10) NOT NULL,
lng_language_code VARCHAR(5) NOT NULL,
PRIMARY KEY(lng_gui_AT,lng_language_code),
CONSTRAINT langgui FOREIGN KEY(lng_gui_AT) REFERENCES guide(gui_AT)
ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT langlang_ref FOREIGN KEY(lng_language_code) REFERENCES language_ref(lang_code)
ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE manages(
mng_adm_AT CHAR(10) NOT NULL,
mng_br_code INT NOT NULL,
PRIMARY KEY(mng_adm_AT,mng_br_code),
CONSTRAINT mngadm FOREIGN KEY(mng_adm_AT) REFERENCES admin(adm_AT)
ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT mngbr FOREIGN KEY(mng_br_code) REFERENCES branch(br_code)
ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE phones(
ph_br_code INT NOT NULL,
ph_number VARCHAR(15) NOT NULL,
PRIMARY KEY(ph_br_code,ph_number),
CONSTRAINT phbr FOREIGN KEY(ph_br_code) REFERENCES branch(br_code)
ON UPDATE CASCADE ON DELETE CASCADE
);

/* 3.1.2.1 VEHICLE */
CREATE TABLE vehicle (
    veh_id INT AUTO_INCREMENT,
    veh_brand VARCHAR(50) NOT NULL,
    veh_model VARCHAR(50) NOT NULL,
    veh_plate VARCHAR(15) NOT NULL,
    veh_seats TINYINT NOT NULL,
    veh_type ENUM('BUS','MINIBUS','VAN','CAR') NOT NULL,
    veh_status ENUM('AVAILABLE','IN_USE','MAINTENANCE') NOT NULL DEFAULT 'AVAILABLE',
    veh_total_km INT NOT NULL DEFAULT 0,
    PRIMARY KEY (veh_id),
    UNIQUE (veh_plate)
);

/* 3.1.2.3  TRIP HISTORY */
CREATE TABLE trip_history (
    th_id INT AUTO_INCREMENT,
    th_trip_id INT NOT NULL,
    th_departure DATETIME NOT NULL,
    th_return DATETIME NOT NULL,
    th_destinations_count TINYINT NOT NULL,
    th_participants_count SMALLINT NOT NULL,
    th_total_revenue DECIMAL(12,2) NOT NULL,
    PRIMARY KEY (th_id)
);

/* 3.1.2.3  */
ALTER TABLE trip_history
ADD th_note VARCHAR(100) DEFAULT 'COMPLETED TRIP (ARCHIVED)';

/* 3.1.2.3. VIEW: Completed Trip History (logical) */
DROP VIEW IF EXISTS completed_trip_history;
CREATE VIEW completed_trip_history AS
SELECT
    th_id,
    th_trip_id,
    th_departure,
    th_return,
    th_destinations_count,
    th_participants_count,
    th_total_revenue
FROM trip_history
WHERE th_return < CURDATE();

ALTER TABLE admin ADD CONSTRAINT admwrk FOREIGN KEY(adm_AT) REFERENCES worker(wrk_AT)
ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE driver ADD CONSTRAINT drwrk FOREIGN KEY(drv_AT) REFERENCES worker(wrk_AT)
ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE guide ADD CONSTRAINT guiwrk FOREIGN KEY(gui_AT) REFERENCES worker(wrk_AT)
ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE branch ADD CONSTRAINT bradm FOREIGN KEY(br_manager_AT) REFERENCES admin(adm_AT)
ON UPDATE CASCADE ON DELETE SET NULL;

/* 3.1.2.1 – VEHICLE TRIP    */
ALTER TABLE trip ADD COLUMN tr_veh_id INT, ADD CONSTRAINT fk_trip_vehicle FOREIGN KEY (tr_veh_id) REFERENCES vehicle(veh_id)
ON UPDATE CASCADE ON DELETE SET NULL;

/* 3.1.4.3 */
ALTER TABLE trip ADD COLUMN tr_final_km INT NULL;

-- INSERTS --

INSERT INTO language_ref (lang_code, lang_name) VALUES
('EL','Hellenic'),('EN','Anglais'),('FR','Francais EU'),('DE','Deutsch DE'),('IT','Italiano Moderno'),('ES','Espanol Castellano');

INSERT INTO branch (br_street, br_num, br_city, br_manager_AT) VALUES
('Avenue Montaigne',120,'Paris',NULL),('Madison Avenue',410,'New York',NULL),('Regent Street',250,'London',NULL),
('Passeig del Born',60,'Barcelona',NULL),('Via Nazionale',72,'Rome',NULL),('Pelikanstrasse',33,'Zurich',NULL),
('Rue Lafayette',110,'Paris',NULL),('Broadway',900,'New York',NULL),('Praterstrasse',55,'Vienna',NULL);

INSERT INTO worker (wrk_AT, wrk_name, wrk_lname, wrk_email, wrk_salary, wrk_br_code) VALUES
('A000000001','Anna','Petropoulou','anna.pet@agency.net',45500.00,1),('A000000002','Manolis','Zachariadis','manolis.z@agency.net',54000.00,2),
('A000000003','Lia','Xenopoulou','lia.x@agency.net',38500.00,3),('A000000004','Stavros','Marketis','stavros.m@agency.net',42500.00,4),
('A000000005','Ioulia','Spanou','ioulia.s@agency.net',50500.00,5),('D000000001','Christos','Theodoridis','christos.t@agency.net',28500.00,1),
('D000000002','Myrto','Angelidou','myrto.a@agency.net',30500.00,2),('D000000003','Gerasimos','Zorbas','gerasimos.z@agency.net',32500.00,3),
('D000000004','Rafaela','Karra','rafaela.k@agency.net',29500.00,6),('G000000001','Marios','Triantafyllou','marios.t@agency.net',34500.00,1),
('G000000002','Rania','Sidiropoulou','rania.s@agency.net',37500.00,2),('G000000003','Apostolos','Karydis','apost.k@agency.net',33500.00,4),
('G000000004','Evdokia','Pavlidou','evdokia.p@agency.net',35900.00,6),('A00000101','Nikos','Papadakis','nikos.p@agency.net',46500.00,7),
('A00000102','Maria','Kalogirou','maria.kalog@agency.net',49500.00,7),('A00000103','Dimitris','Fousteris','dimitris.fous@agency.net',51200.00,8),
('A00000104','Eleni','Christou','eleni.chr@agency.net',47800.00,9),('A00000105','Giannis','Mandilaras','giannis.mand@agency.net',50500.00,8),
('D00000101','Markos','Nikolaras','markos.nik@agency.net',29000.00,7),('D00000102','Spyros','Dellios','spyros.del@agency.net',31000.00,8),
('D00000103','Vasilis','Karalis','vasilis.karal@agency.net',33000.00,9),('D00000104','Kiki','Valasi','kiki.val@agency.net',29500.00,7),
('G00000101','Aris','Liakos','aris.liak@agency.net',36000.00,8),('G00000102','Rafail','Spyridakis','raf.spyr@agency.net',35500.00,9),
('G00000103','Filareti','Kontou','fil.kontou@agency.net',34000.00,7),('G00000104','Lydia','Maragkou','lydia.marag@agency.net',37000.00,8);

INSERT INTO admin (adm_AT, adm_type, adm_diploma) VALUES
('A000000001','ADMINISTRATIVE','Master sti Dioikitiki Epistimi'),('A000000002','LOGISTICS','Metaptyxiako stis Metaf ores & SCM'),
('A000000003','ACCOUNTING','Pistopoiisi IFRS Logistikis'),('A000000004','LOGISTICS','Ptyxio Diaxeirisis Paragogis'),
('A000000005','ADMINISTRATIVE','Diploma Epixeirisiakis Ypostirixis'),('A00000101','ADMINISTRATIVE','Diploma Dioikisis Epixeirisewn'),
('A00000102','LOGISTICS','Metaptyxiako stin Efodiastiki Alisida'),('A00000103','ACCOUNTING','Ptyxio Logistikis & Xrimatodotikis'),
('A00000104','LOGISTICS','Ptyxio Organosis Paragogis'),('A00000105','ADMINISTRATIVE','Diploma Grafeiakon Syskeyon');

INSERT INTO driver (drv_AT,drv_license,drv_route,drv_experience) VALUES
('D000000001','D','LOCAL',11),('D000000002','D','ABROAD',6),('D000000003','C','LOCAL',4),('D000000004','D','ABROAD',10),
('D00000101','D','LOCAL',7),('D00000102','B','ABROAD',4),('D00000103','C','LOCAL',6),('D00000104','D','ABROAD',8);

INSERT INTO guide (gui_AT,gui_cv) VALUES
('G000000001','Xenagisi se politistika mouseia kai istorikes sylloges.'),('G000000002','Polye tis empeiria se architektonikes xenagiseis Europas.'),
('G000000003','Drastiriotita se physiolatrikes perigiseis kai thematikes ekdromes.'),('G000000004','Specialized alpine & cultural expeditions across EU regions.'),
('G00000101','Empeiria se tours stin Gallia kai Elvetia.'),('G00000102','Xenagisi se istoricous xwrous tis Italias.'),
('G00000103','Perigisi se fusiolatrikes diadromes Balkanion.'),('G00000104','Oreibatikes empeiries stin Alpi.');

UPDATE branch SET br_manager_AT='A000000001' WHERE br_code=1; UPDATE branch SET br_manager_AT='A000000002' WHERE br_code=2;
UPDATE branch SET br_manager_AT='A000000003' WHERE br_code=3; UPDATE branch SET br_manager_AT='A000000004' WHERE br_code=4;
UPDATE branch SET br_manager_AT='A000000005' WHERE br_code=5; UPDATE branch SET br_manager_AT='A00000101' WHERE br_code=7;
UPDATE branch SET br_manager_AT='A00000102' WHERE br_code=8; UPDATE branch SET br_manager_AT='A00000103' WHERE br_code=9;

INSERT INTO manages (mng_adm_AT,mng_br_code) VALUES
('A000000001',1),('A000000002',2),('A000000003',3),('A000000004',4),('A000000005',5),('A000000001',4),('A000000002',5),
('A00000101',7),('A00000102',8),('A00000103',9),('A00000101',8),('A00000102',9);

INSERT INTO phones (ph_br_code,ph_number) VALUES
(1,'+33150998811'),(1,'+33150998822'),(2,'+12126667700'),(3,'+442071238888'),(4,'+34934557788'),
(5,'+390662332211'),(6,'+41442154433'),(7,'+33142334455'),(7,'+33142334466'),(8,'+12129998877'),(8,'+12129998888'),(9,'+43123232323');

INSERT INTO customer (cust_name,cust_lname,cust_email,cust_phone,cust_address,cust_birth_date) VALUES
('Lampros','Kechagias','lampros.k@mailservice.eu','+306944556677','Patisi on 200, Athina','1984-02-18'),
('Evgenia','Sakellariou','evgenia.s@mailservice.eu','+306944332211','Komninon 18, Thessaloniki','1990-11-22'),
('Platonas','Kritikos','platon.k@mailservice.eu','+306955667788','Agiou Andreou 9, Patra','1977-07-14'),
('Isavella','Markoulidou','isavella.m@mailservice.eu','+306977889900','Daidalou 31, Irakleio','1994-03-28'),
('Timos','Asimakopoulos','timos.a@mailservice.eu','+306955443322','Trikoupi 45, Larisa','1983-06-12'),
('Avra','Parnassou','avra.p@mailservice.eu','+306934455667','Iolkou 53, Volos','1986-05-16'),
('Themistoklis','Floros','themis.f@mailservice.eu','+306921234567','Omonoias 7, Kavala','1979-09-30'),
('Ermioni','Stergiou','ermioni.ste@mailservice.eu','+306956789001','Plateia Agoras 6, Xania','1993-04-02'),
('Dinos','Karamitsos','dinos.k@mailservice.eu','+306977110022','Kifisias 80, Athina','1981-02-07'),
('Stamatina','Rizou','stamatina.r@mailservice.eu','+306934556644','Mitropoleos 12, Thessaloniki','1995-09-21'),
('Rafail','Anyfantakis','rafael.a@mailservice.eu','+306933221145','Ag. Dimitriou 10, Alexandroupoli','1989-05-23'),
('Nausika','Polyxeni','navsika.p@mailservice.eu','+306955112233','Kartali 8, Ioannina','1992-12-01'),
('Markos','Alexiou','markos.a@mail.eu','+306912341111','Athinas 20, Athina','1981-01-11'),
('Stella','Kosta','stella.k@mail.eu','+306912342222','Sygrou 44, Athina','1994-02-22'),
('Christos','Toulas','christos.t@mail.eu','+306912343333','Tsimiski 120, Thessaloniki','1987-03-15'),
('Katerina','Deli','katerina.d@mail.eu','+306912344444','Korai 7, Patra','1995-04-09'),
('Leonidas','Manos','leon.man@mail.eu','+306912345555','Ermou 55, Athina','1980-05-17'),
('Sofia','Markou','sofia.mr@mail.eu','+306912346666','Lykourgou 11, Sparti','1993-06-28'),
('Giannis','Lazos','giannis.lz@mail.eu','+306912347777','Karaoli 18, Ioannina','1979-07-30'),
('Artemis','Fotou','artemis.f@mail.eu','+306912348888','Venizelou 89, Irakleio','1991-08-19'),
('Petros','Skordas','p.skordas@mail.eu','+306912349999','Dimokratias 33, Kavala','1985-09-12'),
('Eva','Michou','eva.michou@mail.eu','+306912340000','Karolou 14, Lamia','1990-10-01'),
('Alexandros','Petas','alex.pets@mail.eu','+306912341212','Perikleous 9, Athina','1988-11-05'),
('Myrsini','Stagou','myrs.stag@mail.eu','+306912341313','Ariadnis 3, Chania','1992-12-25');

INSERT INTO destination (dst_name,dst_descr,dst_rtype,dst_language_code) VALUES
('Milos','Ifaistiako topio kai galazoprasines paralies','LOCAL','EL'),('Skiathos','Pefka kai xryses aktes, idanikos kalokairinos proorismos','LOCAL','EL'),
('Lyon','Gastronomiki protevousa tis Gallias','ABROAD','FR'),('Torino','Voreia Italia me viomixaniki kai politistiki istoria','ABROAD','IT'),
('Cambridge','Istoriki akadimaiki poli tis Anglias','ABROAD','EN'),('Madrid','Kosmopolitiki protevousa tis Hispanias','ABROAD','ES'),
('Zakynthos','Apisteutes paralies kai to Navagio','LOCAL','EL'),('Kolonia','Germaniki poli me ton diashmo Kathedriko','ABROAD','DE'),
('Napoli','Paradosiaki poli tis Italias me istoria kai gastronomia','ABROAD','IT'),('Geneva','Elvetiki poli me limni kai diethne idrymata','ABROAD','FR'),
('Valencia','Ispaniki poli me paralia kai politistiko kentro','ABROAD','ES'),('Corfu','Prasino nisi me paradosi kai istorika ktiria','LOCAL','EL'),
('Bergen','Norvigiki poli me fjords kai politistiko klima','ABROAD','EN'),('Prague','Romantiki prwtousa tis Tsexias','ABROAD','DE'),
('Nafplio','Istoriki elliniki poli me palia poli','LOCAL','EL'),('Oslo','Norvigiki prwtousa me mousia kai parousia fjord','ABROAD','EN');

INSERT INTO languages (lng_gui_AT,lng_language_code) VALUES
('G000000001','EL'),('G000000001','FR'),('G000000002','EN'),('G000000002','IT'),('G000000002','ES'),('G000000003','DE'),
('G000000003','FR'),('G000000004','EL'),('G000000004','EN'),('G000000004','IT'),('G00000101','EN'),('G00000101','FR'),
('G00000102','IT'),('G00000102','EL'),('G00000103','DE'),('G00000103','EL'),('G00000104','EN'),('G00000104','IT');

INSERT INTO trip (tr_departure,tr_return,tr_maxseats,tr_cost_adult,tr_cost_child,tr_status,tr_min_participants,tr_br_code,tr_gui_AT,tr_drv_AT) VALUES
('2026-06-10 08:00:00','2026-06-13 20:00:00',40,355.00,155.00,'CONFIRMED',15,1,'G000000001','D000000001'),
('2026-07-01 06:00:00','2026-07-08 22:00:00',30,790.00,390.00,'PLANNED',10,2,'G000000002','D000000002'),
('2026-06-10 10:00:00','2026-06-13 18:00:00',25,640.00,295.00,'CONFIRMED',8,4,'G000000003','D000000004'),
('2025-10-15 09:00:00','2025-10-22 17:00:00',50,455.00,205.00,'COMPLETED',20,3,'G000000004','D000000003'),
('2026-09-10 12:00:00','2026-09-17 19:00:00',35,740.00,345.00,'CANCELLED',12,1,'G000000001','D000000002'),
('2026-06-20 10:00:00','2026-06-23 18:00:00',30,410.00,175.00,'CONFIRMED',10,3,'G000000004','D000000003'),
('2026-10-05 07:00:00','2026-10-10 21:00:00',20,560.00,240.00,'PLANNED',5,6,'G000000004','D000000004'),
('2026-11-01 08:00:00','2026-11-05 20:00:00',30,520.00,220.00,'PLANNED',12,7,'G00000101','D00000101'),
('2026-12-10 06:00:00','2026-12-17 22:00:00',25,880.00,430.00,'CONFIRMED',10,8,'G00000102','D00000102'),
('2027-01-15 10:00:00','2027-01-22 18:00:00',20,760.00,360.00,'ACTIVE',8,9,'G00000103','D00000104'),
('2027-02-10 09:00:00','2027-02-18 17:00:00',40,430.00,210.00,'PLANNED',15,7,'G00000104','D00000103'),
('2027-03-20 12:00:00','2027-03-27 19:00:00',28,690.00,330.00,'CONFIRMED',10,8,'G00000101','D00000102'),
('2027-04-05 10:00:00','2027-04-12 18:00:00',32,450.00,190.00,'ACTIVE',10,9,'G00000103','D00000103'),
('2027-05-01 07:00:00','2027-05-07 21:00:00',22,580.00,240.00,'PLANNED',5,7,'G00000104','D00000104');

INSERT INTO event (ev_tr_id,ev_start,ev_end,ev_descr) VALUES
(1,'2026-06-11 11:00:00','2026-06-11 15:00:00','Xenagisi sta ifaistiog ena simeia tis Milou'),(1,'2026-06-12 09:00:00','2026-06-12 13:00:00','Episkepsi sto Sarakiniko'),
(2,'2026-07-04 14:00:00','2026-07-04 18:00:00','Xenagisi stin Piazza Castello'),(2,'2026-07-03 09:00:00','2026-07-03 13:00:00','Volta sto istoriko kentro tou Torinou'),
(2,'2026-07-05 10:00:00','2026-07-05 16:00:00','Perigisi sto Mouseio Aftokinitou'),(3,'2026-08-07 10:00:00','2026-08-07 15:00:00','Episkepsi sto Palati tis Mousikis'),
(3,'2026-08-09 11:00:00','2026-08-09 13:00:00','Xenagisi sti Rambla del Mar'),(4,'2025-10-17 10:00:00','2025-10-17 16:00:00','Thematiki xenagisi sta Anaktora tis Knosou'),
(6,'2026-06-21 12:00:00','2026-06-21 17:00:00','Episkepsi stin paradosiaki synoikia'),(7,'2026-10-06 09:00:00','2026-10-06 13:00:00','Perigisi sto Kastro tou Cambridge'),
(8,'2026-11-02 10:00:00','2026-11-02 14:00:00','Perigisi sti palia poli tis Napoli'),(8,'2026-11-03 10:00:00','2026-11-03 13:00:00','Peripatos sto istoriko kentro tis Napoli'),
(10,'2027-01-18 09:00:00','2027-01-18 12:00:00','Xenagisi sto Bergenhus Fortress'),(12,'2027-03-23 10:00:00','2027-03-23 14:00:00','Perigisi sto istoriko kentro tis Valencia'),
(9,'2026-12-12 09:00:00','2026-12-12 13:00:00','Xenagisi stin kentriki plateia tis Geneva'),(10,'2027-01-17 11:00:00','2027-01-17 15:00:00','Volta sta fjords konta sti Bergen'),
(11,'2027-02-12 10:00:00','2027-02-12 16:00:00','Xenagisi sto istoriko kentro tis Prague'),(12,'2027-03-22 12:00:00','2027-03-22 17:00:00','Episkepsi stin paradosiaki agora tis Valencia'),
(13,'2027-04-06 11:00:00','2027-04-06 14:00:00','Volta sto Nafplio kai stin palia poli'),(14,'2027-05-02 09:00:00','2027-05-02 13:00:00','Xenagisi sto Oslo City Center');

INSERT INTO reservation (res_tr_id,res_seatnum,res_cust_id,res_status,res_total_cost) VALUES
(1,1,1,'PAID',355.00),(1,2,2,'PAID',355.00),(1,3,3,'CONFIRMED',355.00),(1,4,4,'PENDING',355.00),
(4,1,5,'PAID',455.00),(4,2,6,'PAID',455.00),(4,3,7,'PAID',455.00),(4,4,8,'PAID',455.00),(4,5,9,'PAID',455.00),
(4,6,10,'PAID',455.00),(6,1,11,'PAID',410.00),(6,2,12,'PAID',410.00),
(8,1,13,'PAID',520.00),(8,2,14,'CONFIRMED',520.00),(9,1,15,'PAID',880.00),(9,2,16,'PAID',880.00),
(10,1,17,'PENDING',760.00),(11,1,18,'PAID',430.00),(12,1,19,'PAID',690.00),(12,2,20,'PAID',690.00),
(13,1,21,'PAID',450.00),(13,2,22,'PAID',450.00),(14,1,23,'PAID',580.00),(14,2,24,'PAID',580.00);

INSERT INTO travel_to (to_tr_id,to_dst_id,to_arrival,to_departure,to_sequence) VALUES
(1,1,'2026-06-10 10:00:00','2026-06-13 18:00:00',1),
(2,4,'2026-07-02 12:00:00','2026-07-08 18:00:00',1),
(3,6,'2026-08-05 15:00:00','2026-08-12 15:00:00',1),
(4,7,'2025-10-15 11:00:00','2025-10-22 15:00:00',1),
(5,3,'2026-09-10 14:00:00','2026-09-17 18:00:00',1),
(6,2,'2026-06-20 11:30:00','2026-06-23 16:00:00',1),
(7,5,'2026-10-05 09:30:00','2026-10-10 18:00:00',1),
(8,9,'2026-11-01 12:00:00','2026-11-03 12:00:00',1),
(8,10,'2026-11-03 14:00:00','2026-11-05 18:00:00',2),
(9,10,'2026-12-10 14:00:00','2026-12-17 18:00:00',1),
(10,11,'2027-01-15 15:00:00','2027-01-22 15:00:00',1),
(11,12,'2027-02-10 11:00:00','2027-02-18 15:00:00',1),
(12,13,'2027-03-20 14:00:00','2027-03-27 17:00:00',1),
(13,14,'2027-04-05 12:00:00','2027-04-12 16:00:00',1),
(14,15,'2027-05-01 09:30:00','2027-05-07 18:00:00',1);

INSERT INTO vehicle (veh_brand, veh_model, veh_plate, veh_seats, veh_type, veh_status, veh_total_km) VALUES
('Mercedes','Tourismo','BUS-1001',50,'BUS','AVAILABLE',125000),('Setra','S 515 HD','BUS-1002',48,'BUS','AVAILABLE',98000),
('Mercedes','Sprinter','MINI-2001',18,'MINIBUS','AVAILABLE',74000),('Ford','Transit','VAN-3001',9,'VAN','MAINTENANCE',63000),
('Volkswagen','Transporter','VAN-3002',8,'VAN','AVAILABLE',51000),('Toyota','Corolla','CAR-4001',5,'CAR','AVAILABLE',42000);

INSERT INTO accommodation (acc_name, acc_type, acc_stars, acc_rating, acc_status, acc_street, acc_dst_id, acc_pricepernight, acc_roomcapacity, acc_facilities) VALUES
('Milos Breeze Boutique', 'Hotel', '5', 4.8, 'ACTIVE', 'Pollonia', 1, 150.00, 50, 'WiFi,AC,Restaurant'),
('Skiathos Palace', 'Resort', '4', 4.5, 'ACTIVE', 'Koukounaries', 2, 120.00, 100, 'WiFi,AC,Restaurant'),
('InterContinental Lyon', 'Hotel', '5', 4.9, 'ACTIVE', 'Rue de la Republique', 3, 220.00, 80, 'WiFi,AC,DisabledAccess'),
('Turin Palace Hotel', 'Hotel', '4', 4.7, 'ACTIVE', 'Via Sacchi', 4, 140.00, 60, 'WiFi,AC'),
('University Arms', 'Hotel', '4', 4.6, 'ACTIVE', 'Regent Street', 5, 180.00, 70, 'WiFi,Restaurant'),
('Riu Plaza Espana', 'Hotel', '4', 4.5, 'ACTIVE', 'Gran Via', 6, 160.00, 200, 'WiFi,AC,DisabledAccess'),
('Lesante Cape Resort', 'Resort', '5', 5.0, 'ACTIVE', 'Akrotiri', 7, 250.00, 40, 'WiFi,AC,Restaurant'),
('Hyatt Regency Cologne', 'Hotel', '5', 4.8, 'ACTIVE', 'Kennedy-Ufer', 8, 190.00, 90, 'WiFi,AC'),
('Grand Hotel Vesuvio', 'Hotel', '5', 4.7, 'ACTIVE', 'Via Partenope', 9, 210.00, 55, 'WiFi,AC,Restaurant'),
('Hotel Beau-Rivage', 'Hotel', '5', 4.9, 'ACTIVE', 'Quai du Mont-Blanc', 10, 350.00, 45, 'WiFi,AC,Restaurant'),
('Las Arenas Balneario', 'Resort', '5', 4.8, 'ACTIVE', 'Carrer dEugenia Vines', 11, 200.00, 100, 'WiFi,AC'),
('Corfu Imperial', 'Resort', '5', 4.9, 'ACTIVE', 'Kommeno', 12, 280.00, 60, 'WiFi,AC,Restaurant'),
('Radisson Blu Royal', 'Hotel', '4', 4.4, 'ACTIVE', 'Bryggen', 13, 170.00, 80, 'WiFi,AC'),
('Cosmopolitan Hotel', 'Hotel', '5', 4.8, 'ACTIVE', 'Zlatnicka', 14, 130.00, 75, 'WiFi,AC'),
('Amphitryon Hotel', 'Hotel', '5', 4.7, 'ACTIVE', 'Spiliadou', 15, 160.00, 40, 'WiFi,AC'),
('The Thief', 'Hotel', '5', 4.9, 'ACTIVE', 'Landgangen', 16, 300.00, 50, 'WiFi,AC,Restaurant');

INSERT INTO trip_history (th_trip_id, th_departure, th_return, th_destinations_count, th_participants_count, th_total_revenue, th_note) VALUES
(101, '2023-05-01 08:00:00', '2023-05-05 20:00:00', 2, 25, 12500.00, 'ANNUAL GREEK ISLANDS'),
(102, '2023-06-12 09:00:00', '2023-06-18 18:00:00', 1, 15, 8400.00, 'EUROPEAN CAPITAL TOUR'),
(103, '2023-09-20 07:00:00', '2023-09-27 21:00:00', 3, 40, 22000.00, 'BALKAN EXPEDITION'),
(104, '2023-12-15 10:00:00', '2023-12-22 15:00:00', 1, 10, 5000.00, 'WINTER GETAWAY');

INSERT INTO trip_accommodation (ta_trip_id, ta_acc_id, ta_check_in, ta_check_out, ta_rooms) VALUES
(1, 1, '2026-06-10 14:00:00', '2026-06-13 11:00:00', 5),
(2, 4, '2026-07-02 15:00:00', '2026-07-08 10:00:00', 3),
(3, 6, '2026-08-05 14:00:00', '2026-08-12 12:00:00', 4),
(4, 7, '2025-10-15 14:00:00', '2025-10-22 11:00:00', 6);

INSERT INTO dba (dba_admin_AT, dba_start_date, dba_end_date) VALUES
('A000000001', '2024-01-01 09:00:00', NULL),
('A000000002', '2024-02-15 10:30:00', NULL),
('A00000101', '2024-05-20 08:00:00', '2025-05-20 08:00:00'),
('A00000103', '2025-01-10 09:00:00', NULL);

-- STORED PROCEDURES --

DROP PROCEDURE IF EXISTS SP_SEARCH;
DROP PROCEDURE IF EXISTS BOOKING;
DROP PROCEDURE IF EXISTS populate_trip_history;
DROP PROCEDURE IF EXISTS sum_revenue_between_dates;
DROP PROCEDURE IF EXISTS trips_by_destinations_count;
DROP PROCEDURE IF EXISTS available_vehicles_for_trip;

-- 3.1.3.2
DELIMITER $
CREATE PROCEDURE SP_SEARCH(IN dst_id INT,IN date_from DATETIME,IN date_to DATETIME,IN rooms INT, OUT acc_result INT)
BEGIN 
	SELECT 
		acc_id,acc_name, acc_type,acc_street, acc_phone, acc_stars, acc_rating, acc_pricepernight, acc_facilities,
		(acc_roomcapacity - IFNULL((
          		SELECT SUM(ta_rooms)
           		FROM trip_accommodation
        		WHERE ta_acc_id = acc_id
				AND ta_check_in < date_to 
				AND ta_check_out > date_from), 0)) 
				AS available_rooms
	FROM accommodation
	WHERE acc_dst_id = dst_id
	AND acc_status = 'ACTIVE'
	HAVING available_rooms >= rooms
	ORDER BY 
        acc_pricepernight ASC, 
        acc_stars DESC, 
        acc_rating DESC;

	SELECT acc_id INTO acc_result
	FROM (
		SELECT acc_id, acc_name, acc_type, acc_street, acc_phone, acc_stars, acc_rating, acc_pricepernight, acc_facilities,
		(acc_roomcapacity - IFNULL((
          		SELECT SUM(ta_rooms)
           		FROM trip_accommodation
        		WHERE ta_acc_id = acc_id
				AND ta_check_in < date_to 
				AND ta_check_out > date_from), 0)) 
				AS available_rooms
		FROM accommodation
		WHERE acc_dst_id = dst_id
		AND acc_status = 'ACTIVE') AS subquery
	WHERE available_rooms >= rooms
	ORDER BY acc_pricepernight ASC, acc_stars DESC, acc_rating DESC
   	LIMIT 1;

END$
DELIMITER ;

-- 3.1.3.3
DELIMITER $
CREATE PROCEDURE BOOKING(IN in_trip_id INT, IN in_rooms INT)
BEGIN
	DECLARE DONE INT DEFAULT 0;
    DECLARE error_flag INT DEFAULT 0;
    DECLARE trexon_proorismos_id INT;
    DECLARE temp_arrival DATETIME;
    DECLARE temp_departure DATETIME;
    DECLARE returned_acc_id INT;
    DECLARE msg_text VARCHAR(255);
    
	DECLARE curs_travel CURSOR FOR 
	SELECT to_dst_id, to_arrival, to_departure
    FROM travel_to
    WHERE to_tr_id = in_trip_id;
    
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET DONE = 1;
    
    OPEN curs_travel;
    
    FETCH curs_travel INTO trexon_proorismos_id ,temp_arrival ,temp_departure;
    WHILE (DONE=0 AND error_flag = 0) DO
        SET returned_acc_id = NULL;
        
		CALL SP_SEARCH(trexon_proorismos_id,temp_arrival, temp_departure,in_rooms,returned_acc_id);
        
        IF returned_acc_id IS NOT NULL THEN
			INSERT INTO trip_accommodation(ta_trip_id, ta_acc_id, ta_check_in, ta_check_out, ta_rooms)
			VALUES (in_trip_id, returned_acc_id, temp_arrival, temp_departure, in_rooms);
			FETCH curs_travel INTO trexon_proorismos_id ,temp_arrival ,temp_departure;
		ELSE
			SET error_flag=1;
            DELETE FROM trip_accommodation WHERE ta_trip_id = in_trip_id;
		END IF;
	END WHILE;
    CLOSE curs_travel;
        
	IF error_flag = 1 THEN
		SET msg_text = CONCAT('Failed Reservation, No hotel found for given ID: ', trexon_proorismos_id);
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg_text;
    ELSE
        SELECT 'Successful Reservation';
        SELECT * FROM trip_accommodation WHERE ta_trip_id = in_trip_id;
    END IF;

END$
DELIMITER ;

DELIMITER $
CREATE PROCEDURE populate_trip_history(IN rows_count INT)
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE dep DATETIME;
    DECLARE ret DATETIME;
    DECLARE dest_cnt TINYINT;
    DECLARE part_cnt SMALLINT;
    DECLARE revenue DECIMAL(12,2);
   
    -- ΑΠΕΝΕΡΓΟΠΟΙΗΣΗ AUTOCOMMIT ΓΙΑ ΤΑΧΥΤΗΤΑ
    SET autocommit = 0;

    WHILE i <= rows_count DO
        -- Γέννηση τυχαίων δεδομένων
        SET dep = DATE_ADD('2018-01-01', INTERVAL FLOOR(RAND()*2000) DAY);
        SET ret = DATE_ADD(dep, INTERVAL FLOOR(2 + RAND()*10) DAY);
        SET dest_cnt = FLOOR(1 + RAND()*5);
        SET part_cnt = FLOOR(10 + RAND()*40);
        SET revenue = part_cnt * (300 + RAND()*1200);

        INSERT INTO trip_history
        (th_trip_id, th_departure, th_return, th_destinations_count, th_participants_count, th_total_revenue)
        VALUES
        (i, dep, ret, dest_cnt, part_cnt, revenue);

        -- ΚΑΝΕ COMMIT ΚΑΘΕ 5000 ΕΓΓΡΑΦΕΣ
        IF MOD(i, 5000) = 0 THEN
            COMMIT;
        END IF;

        SET i = i + 1;
    END WHILE;
       COMMIT;
    SET autocommit = 1;
END$
DELIMITER ;

-- CALL populate_trip_history(90000);

DELIMITER $
CREATE PROCEDURE assign_vehicle_to_trip(
    IN trip_id INT,
    IN vehicle_id INT,
    IN current_km INT
)
BEGIN
    DECLARE trip_exists INT DEFAULT 0;
    DECLARE vehicle_exists INT DEFAULT 0;
    DECLARE vehicle_status VARCHAR(20);
    DECLARE reservations_count INT DEFAULT 0;
    DECLARE vehicle_seats TINYINT;

    DECLARE driver_AT CHAR(10);
    DECLARE driver_license CHAR(1);

    DECLARE trip_departure DATETIME;
    DECLARE trip_return DATETIME;
    DECLARE overlap_count INT DEFAULT 0;
    DECLARE existing_km INT;

    -- Έλεγχος ύπαρξης ταξιδιού
    SELECT COUNT(*) INTO trip_exists
    FROM trip WHERE tr_id = trip_id;

    IF trip_exists = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Σφάλμα: Το ταξίδι δεν υπάρχει';
    END IF;

    -- Έλεγχος ύπαρξης οχήματος
    SELECT COUNT(*) INTO vehicle_exists
    FROM vehicle WHERE veh_id = vehicle_id;

    IF vehicle_exists = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Σφάλμα: Το όχημα δεν υπάρχει';
    END IF;

    -- Έλεγχος διαθεσιμότητας οχήματος
    SELECT veh_status INTO vehicle_status
    FROM vehicle WHERE veh_id = vehicle_id;

    IF vehicle_status <> 'AVAILABLE' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Σφάλμα: Το όχημα δεν είναι διαθέσιμο';
    END IF;

    -- Έλεγχος επάρκειας θέσεων
    SELECT COUNT(*) INTO reservations_count
    FROM reservation WHERE res_tr_id = trip_id;

    SELECT veh_seats INTO vehicle_seats
    FROM vehicle WHERE veh_id = vehicle_id;

    IF vehicle_seats < reservations_count THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Μη επαρκείς θέσεις οχήματος';
    END IF;

    -- Έλεγχος άδειας οδηγού
    SELECT tr_drv_AT INTO driver_AT
    FROM trip WHERE tr_id = trip_id;

    IF driver_AT IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Το ταξίδι δεν έχει ορισμένο οδηγό';
    END IF;

    SELECT drv_license INTO driver_license
    FROM driver WHERE drv_AT = driver_AT;

    IF vehicle_seats > 9 AND driver_license NOT IN ('C','D') THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Ο οδηγός δεν διαθέτει κατάλληλη άδεια για το όχημα';
    END IF;

    -- Έλεγχος χρονικής επικάλυψης
    SELECT tr_departure, tr_return
    INTO trip_departure, trip_return
    FROM trip
    WHERE tr_id = trip_id;

    SELECT COUNT(*)
    INTO overlap_count
    FROM trip
    WHERE tr_veh_id = vehicle_id
      AND tr_id <> trip_id
      AND tr_departure < trip_return
      AND tr_return > trip_departure;

    IF overlap_count > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Το όχημα χρησιμοποιείται σε άλλο ταξίδι με χρονική επικάλυψη';
    END IF;

	SELECT veh_total_km INTO existing_km FROM vehicle WHERE veh_id = vehicle_id;
	IF current_km < existing_km THEN
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Τα νέα χιλιόμετρα δεν μπορούν να είναι μικρότερα από τα υπάρχοντα';
	END IF;

    -- ΤΕΛΙΚΗ ΑΝΑΘΕΣΗ
    START TRANSACTION;

    UPDATE trip
    SET tr_veh_id = vehicle_id
    WHERE tr_id = trip_id;

    UPDATE vehicle
    SET veh_status = 'IN_USE',
        veh_total_km = current_km
    WHERE veh_id = vehicle_id;

    COMMIT;
    
   SELECT CONCAT('Το όχημα ', vehicle_id,' (', (SELECT veh_brand FROM vehicle WHERE veh_id = vehicle_id),' ',
    (SELECT veh_model FROM vehicle WHERE veh_id = vehicle_id),') ανατέθηκε στο ταξίδι ', trip_id,'. Χιλιόμετρα: ', 
    current_km,'. Κατάσταση: ΣΕ ΧΡΗΣΗ') AS Result;
END$
DELIMITER ;

-- 3.1.3.4(a) synolika esoda
DELIMITER $
CREATE PROCEDURE sum_revenue_between_dates(
    IN date_from DATE,
    IN date_to DATE
)
BEGIN
    SELECT 
        SUM(th_total_revenue) AS total_revenue
    FROM trip_history
    WHERE th_departure BETWEEN date_from AND date_to;
END$
DELIMITER ;

-- 3.1.3.4(b)
DELIMITER $
CREATE PROCEDURE trips_by_destinations_count(
    IN dest_count TINYINT
)
BEGIN
    SELECT 
        th_departure,
        th_return
    FROM trip_history
    WHERE th_destinations_count = dest_count;
END$
DELIMITER ;

-- bonus
DELIMITER $
CREATE PROCEDURE available_vehicles_for_trip(IN in_trip_id INT)
BEGIN
    DECLARE required_seats INT;

    -- theseis
    SELECT COUNT(*)
    INTO required_seats
    FROM reservation
    WHERE res_tr_id = in_trip_id;

    -- maxseats otan oxi kratiseis
    IF required_seats = 0 THEN
        SELECT tr_maxseats
        INTO required_seats
        FROM trip
        WHERE tr_id = in_trip_id;
    END IF;

    SELECT
        veh_id,
        veh_brand,
        veh_model,
        veh_type,
        veh_seats,
        veh_status
    FROM vehicle
    WHERE veh_status = 'AVAILABLE'
      AND veh_seats >= required_seats;
END$
DELIMITER ;  

-- Index 3.1.3.4(a)
CREATE INDEX idx_trip_history_departure
ON trip_history (th_departure);

-- Index 3.1.3.4(b)
CREATE INDEX idx_trip_history_destinations
ON trip_history (th_destinations_count);

-- TRIGGERS --

DROP TRIGGER IF EXISTS trg_complete_trip_update_vehicle;
DROP TRIGGER IF EXISTS PRICECALC;

DROP TRIGGER IF EXISTS log_trip_acc_insert;
DROP TRIGGER IF EXISTS log_trip_acc_update;
DROP TRIGGER IF EXISTS log_trip_acc_delete;

DROP TRIGGER IF EXISTS log_trip_insert;
DROP TRIGGER IF EXISTS log_trip_update;
DROP TRIGGER IF EXISTS log_trip_delete;

DROP TRIGGER IF EXISTS log_reservation_insert;
DROP TRIGGER IF EXISTS log_reservation_update;
DROP TRIGGER IF EXISTS log_reservation_delete;

DROP TRIGGER IF EXISTS log_customer_insert;
DROP TRIGGER IF EXISTS log_customer_update;
DROP TRIGGER IF EXISTS log_customer_delete;

DROP TRIGGER IF EXISTS log_destination_insert;
DROP TRIGGER IF EXISTS log_destination_update;
DROP TRIGGER IF EXISTS log_destination_delete;

DROP TRIGGER IF EXISTS log_vehicle_insert;
DROP TRIGGER IF EXISTS log_vehicle_update;
DROP TRIGGER IF EXISTS log_vehicle_delete;

DELIMITER $
CREATE TRIGGER trg_complete_trip_update_vehicle
BEFORE UPDATE ON trip
FOR EACH ROW
BEGIN
    DECLARE current_vehicle_km INT;

    IF NEW.tr_status = 'COMPLETED' AND OLD.tr_status <> 'COMPLETED' THEN

        -- Πρέπει να υπάρχει όχημα
        IF NEW.tr_veh_id IS NULL THEN
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Δεν υπάρχει όχημα ανατεθειμένο στο ταξίδι.';
        END IF;

        -- Πρέπει να έχουν δοθεί τελικά χιλιόμετρα
        IF NEW.tr_final_km IS NULL THEN
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Πρέπει να δοθούν τα τελικά χιλιόμετρα (tr_final_km).';
        END IF;

        -- Έλεγχος ότι τα τελικά χλμ δεν είναι μικρότερα από τα ήδη καταγεγραμμένα του οχήματος
        SELECT veh_total_km INTO current_vehicle_km
        FROM vehicle
        WHERE veh_id = NEW.tr_veh_id;

        IF NEW.tr_final_km < current_vehicle_km THEN
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Τα τελικά χιλιόμετρα δεν μπορεί να είναι μικρότερα από τα υπάρχοντα του οχήματος.';
        END IF;

        -- νέα συνολικά χλμ + διαθέσιμο
        UPDATE vehicle
        SET veh_total_km = NEW.tr_final_km,
            veh_status = 'AVAILABLE'
        WHERE veh_id = NEW.tr_veh_id;

    END IF;
END$
DELIMITER ;

DELIMITER $
CREATE TRIGGER PRICECALC
BEFORE INSERT
ON trip_accommodation
FOR EACH ROW
BEGIN
	DECLARE accommodation_cost DECIMAL(10,2);
    DECLARE total_nights INT;
    
	SELECT acc_pricepernight INTO accommodation_cost
    FROM accommodation
    WHERE acc_id=NEW.ta_acc_id;
    
    SET total_nights = DATEDIFF(NEW.ta_check_out, NEW.ta_check_in);
    
    SET NEW.ta_total_cost = accommodation_cost * total_nights * NEW.ta_rooms;
END$
DELIMITER ;

DELIMITER $

-- TRIP ACCOMMODATION 
CREATE TRIGGER log_trip_acc_insert 
AFTER INSERT ON trip_accommodation
FOR EACH ROW
BEGIN
    INSERT INTO system_log(log_username,log_date,log_action, log_table)
    VALUES (USER(),NOW(),'INSERT', 'trip_accommodation');
END$

CREATE TRIGGER log_trip_acc_update 
AFTER UPDATE ON trip_accommodation
FOR EACH ROW
BEGIN
    INSERT INTO system_log (log_username, log_date, log_action, log_table)
    VALUES (USER(), NOW(), 'UPDATE', 'trip_accommodation');
END$

CREATE TRIGGER log_trip_acc_delete 
AFTER DELETE ON trip_accommodation
FOR EACH ROW
BEGIN
    INSERT INTO system_log (log_username, log_date, log_action, log_table)
    VALUES (USER(), NOW(), 'DELETE', 'trip_accommodation');
END$

--  TRIP
CREATE TRIGGER log_trip_insert 
AFTER INSERT ON trip
FOR EACH ROW
BEGIN
    INSERT INTO system_log(log_username,log_date,log_action, log_table)
    VALUES (USER(),NOW(),'INSERT','trip');
END$

CREATE TRIGGER log_trip_update 
AFTER UPDATE ON trip
FOR EACH ROW
BEGIN
    INSERT INTO system_log (log_username, log_date, log_action, log_table)
    VALUES (USER(), NOW(), 'UPDATE', 'trip');
END$

CREATE TRIGGER log_trip_delete 
AFTER DELETE ON trip
FOR EACH ROW
BEGIN
    INSERT INTO system_log (log_username, log_date, log_action, log_table)
    VALUES (USER(),NOW(), 'DELETE', 'trip');
END$

-- RESERVATION
CREATE TRIGGER log_reservation_insert 
AFTER INSERT ON reservation
FOR EACH ROW
BEGIN
    INSERT INTO system_log(log_username,log_date,log_action, log_table)
    VALUES (USER(),NOW(), 'INSERT','reservation');
END$

CREATE TRIGGER log_reservation_update 
AFTER UPDATE ON reservation
FOR EACH ROW
BEGIN
    INSERT INTO system_log (log_username, log_date, log_action, log_table)
    VALUES (USER(), NOW(), 'UPDATE', 'reservation');
END$

CREATE TRIGGER log_reservation_delete 
AFTER DELETE ON reservation
FOR EACH ROW
BEGIN
    INSERT INTO system_log (log_username, log_date, log_action, log_table)
    VALUES (USER(), NOW(), 'DELETE', 'reservation');
END$

-- CUSTOMER
CREATE TRIGGER log_customer_insert 
AFTER INSERT ON customer
FOR EACH ROW
BEGIN
    INSERT INTO system_log(log_username,log_date,log_action, log_table)
    VALUES (USER(), NOW(), 'INSERT','customer');
END$

CREATE TRIGGER log_customer_update 
AFTER UPDATE ON customer
FOR EACH ROW
BEGIN
    INSERT INTO system_log (log_username, log_date, log_action, log_table)
    VALUES (USER(), NOW(), 'UPDATE','customer');
END$

CREATE TRIGGER log_customer_delete 
AFTER DELETE ON customer
FOR EACH ROW
BEGIN
    INSERT INTO system_log (log_username, log_date, log_action, log_table)
    VALUES (USER(), NOW(), 'DELETE', 'customer');
END$

-- DESTINATION TRIGGERS
CREATE TRIGGER log_destination_insert 
AFTER INSERT ON destination
FOR EACH ROW
BEGIN
    INSERT INTO system_log(log_username,log_date,log_action, log_table)
    VALUES (USER(), NOW(), 'INSERT','destination');
END$

CREATE TRIGGER log_destination_update 
AFTER UPDATE ON destination
FOR EACH ROW
BEGIN
    INSERT INTO system_log (log_username, log_date, log_action, log_table)
    VALUES (USER(), NOW(), 'UPDATE', 'destination');
END$

CREATE TRIGGER log_destination_delete 
AFTER DELETE ON destination
FOR EACH ROW
BEGIN
    INSERT INTO system_log (log_username, log_date, log_action, log_table)
    VALUES (USER(), NOW(), 'DELETE', 'destination');
END$

-- VEHICLE TRIGGERS
CREATE TRIGGER log_vehicle_insert 
AFTER INSERT ON vehicle
FOR EACH ROW
BEGIN
    INSERT INTO system_log(log_username,log_date,log_action, log_table)
    VALUES (USER(), NOW(), 'INSERT','vehicle');
END$

CREATE TRIGGER log_vehicle_update 
AFTER UPDATE ON vehicle
FOR EACH ROW
BEGIN
    INSERT INTO system_log (log_username, log_date, log_action, log_table)
    VALUES (USER(), NOW(), 'UPDATE','vehicle');
END$

CREATE TRIGGER log_vehicle_delete 
AFTER DELETE ON vehicle
FOR EACH ROW
BEGIN
    INSERT INTO system_log (log_username, log_date, log_action, log_table)
    VALUES (USER(), NOW(), 'DELETE','vehicle');
END$

-- ACCOMMODATION 
CREATE TRIGGER log_acc_insert 
AFTER INSERT ON accommodation
FOR EACH ROW
BEGIN
    INSERT INTO system_log(log_username,log_date,log_action, log_table)
    VALUES (USER(),NOW(),'INSERT', 'accommodation');
END$

CREATE TRIGGER log_acc_update 
AFTER UPDATE ON accommodation
FOR EACH ROW
BEGIN
    INSERT INTO system_log (log_username, log_date, log_action, log_table)
    VALUES (USER(), NOW(), 'UPDATE', 'accommodation');
END$

CREATE TRIGGER log_acc_delete 
AFTER DELETE ON accommodation
FOR EACH ROW
BEGIN
    INSERT INTO system_log (log_username, log_date, log_action, log_table)
    VALUES (USER(), NOW(), 'DELETE', 'accommodation');
END$