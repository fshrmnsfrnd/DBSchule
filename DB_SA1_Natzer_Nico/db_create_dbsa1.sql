-- Datenbank erstellen
DROP DATABASE IF EXISTS dbsa1;

CREATE DATABASE IF NOT EXISTS dbsa1;
USE dbsa1;

-- Tabelle erstellen
DROP TABLE IF EXISTS buch;

CREATE TABLE buch (
    id INT PRIMARY KEY AUTO_INCREMENT,
    isbn VARCHAR(13),
    titel VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    schlagworte TEXT
);

-- Beispieldaten einfügen
INSERT INTO buch (
titel, autor, isbn,schlagworte) VALUES
('Der Herr der Ringe', 'J.R.R. Tolkien', '9783608938306','Das E-Book "1984" von George Orwell ist ein dystopischer Roman, der die Gefahren totalitärer Regime thematisiert. Es ist in Deutsch verfasst.'),
('1984', 'George Orwell', '9783548234106','Das E-Book "1984" von George Orwell ist ein dystopischer Roman, der die Gefahren totalitärer Regime thematisiert. Es ist in Deutsch verfasst.'),
('Harry Potter', 'J.K. Rowling', '9783551551672','Das E-Book "Harry Potter und der Stein der Weisen" ist der erste Teil der beliebten Fantasy-Serie, die die Abenteuer eines jungen Zauberers erzählt. Es ist in Deutsch verfasst.'),
('Die Verwandlung', 'Franz Kafka', '9783150093504','Das Buch "Die Verwandlung" von Franz Kafka ist eine surrealistische Erzählung, die die Themen Identität und Entfremdung behandelt. Es ist in Deutsch verfasst');