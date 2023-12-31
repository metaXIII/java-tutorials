DROP TABLE IF EXISTS post;
CREATE TABLE post
(
    id               INT PRIMARY KEY,
    title            VARCHAR(200),
    publication_date DATE
);
INSERT INTO post (id, title, publication_date)
VALUES (1, 'Facebook post', '2020-11-10');
INSERT INTO post (id, title, publication_date)
VALUES (2, 'Instagram post', '2020-12-24');
INSERT INTO post (id, title, publication_date)
VALUES (3, 'Twitter post', '2023-01-10');
INSERT INTO post (id, title, publication_date)
VALUES (4, 'tiktok post', '2023-03-18');
INSERT INTO post (id, title, publication_date)
VALUES (5, 'Pinterest post', '2023-09-09');