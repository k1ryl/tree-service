CREATE TABLE tree
(
    id        SERIAL PRIMARY KEY,
    parent_id INTEGER REFERENCES tree (id) DEFERRABLE,
    label     TEXT NOT NULL
);


INSERT INTO tree (parent_id, label)
VALUES (NULL, 'root');
