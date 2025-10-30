/*************************************
    Trainingslager JOINS (Vorlage)

    Datenbank: dbTraining.db
    Tabellen: L und R
    Autor: ml
    Stand: 23.02.2025

**************************************/

/*** karthesischer JOIN zwischen L und R */

SELECT * FROM L CROSS JOIN R;

/*** natural JOIN zwischen L und R */

SELECT * FROM L NATURAL JOIN R;

/*** EQUI-JOIN zwischen L und R */ 

/* mit Anzeige aller Daten aus den Tabellen L und R */

SELECT * FROM L EQUI JOIN R;

/* mit Anzeige aller Daten aus Tabelle L */

SELECT L.* FROM L, R WHERE L.c = R.c;

/* mit Anzeige aller Daten aus Tabelle R */

SELECT R.* FROM L, R WHERE L.c = R.c;

/*** LEFT-JOIN zwischen L und R */

SELECT * FROM L LEFT JOIN R;

/*** RIGHT-OUTER-JOIN zwischen L und R */

/* MySQL */

SELECT * FROM L RIGHT OUTER JOIN R ON L.c = R.c;

/* SQLite*/

SELECT * FROM L RIGHT OUTER JOIN R ON L.c = R.c;

/*** FULL-OUTER-JOIN zwischen L und R */ 
SELECT * FROM L FULL OUTER JOIN R ON L.c = R.c;