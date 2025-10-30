use dbVerein;
#task 1
alter table mitglied add column Kuerzel char(5);

#task2
alter table mitglied rename column tel to Telefonnummer;
alter table mitglied add column Vorwahl integer;

#task3
alter table mitglied add column Assess integer unsigned;<