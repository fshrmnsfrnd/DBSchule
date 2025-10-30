# Aufgabe 3

CREATE USER user1;

use dbverein;

#1
GRANT SELECT ON sportart TO user1;
GRANT SELECT ON link_mitglied_sportart TO user1;

#2
GRANT UPDATE, INSERT ON sportart to user1;

#3
GRANT DELETE ON sportart to user1 WITH GRANT OPTION;

#4
GRANT ALL ON link_mitglied_sportart to user1,user2;

# Aufgabe 4
REVOKE UPDATE, INSERT ON sportart FROM user1;
REVOKE DELETE ON sportart FROM user1;
REVOKE GRANT OPTION ON sportart FROM user1;