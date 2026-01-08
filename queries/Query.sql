use movie_tweets;

UPDATE tweet SET movie = SUBSTRING_INDEX(SUBSTRING_INDEX(tweet, '''', 2), '''', -1);

ALTER TABLE tweet ADD FULLTEXT(tweet);

#Anzah rezensionen pro film
SELECT COUNT(movie) AS Anzahl, movie FROM tweet
GROUP BY movie;

#Durchschnittlische Zeichenzahl
SELECT AVG(length(tweet)) FROM tweet;

#bewertung
CREATE table tags(movie text, tag text);
DELETE FROM tags;
ALTER TABLE tweet
ADD COLUMN bewertung INT;
UPDATE tweet
SET bewertung =
  CAST(
    SUBSTRING_INDEX(
      SUBSTRING_INDEX(tweet, '#Bewertung: ', -1),
      '/',
      1
    ) AS UNSIGNED
  )
WHERE tweet LIKE '%#Bewertung:%';

#
CREATE TABLE film_hashtags (
  filmname VARCHAR(255),
  hashtag  VARCHAR(255)
);
INSERT INTO film_hashtags (filmname, hashtag)
SELECT
  t.filmname,
  TRIM(
    SUBSTRING_INDEX(
      SUBSTRING_INDEX(t.tweet, '#', n.n + 1),
      ' ',
      1
    )
  ) AS hashtag
FROM tweet t
JOIN numbers n
  ON n.n <= (LENGTH(t.tweet) - LENGTH(REPLACE(t.tweet, '#', '')))
WHERE SUBSTRING_INDEX(
        SUBSTRING_INDEX(t.tweet, '#', n.n + 1),
        ' ',
        1
      ) NOT LIKE 'Bewertung:%';




SELECT * FROM tweet;