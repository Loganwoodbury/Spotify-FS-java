BEGIN TRANSACTION;

DROP TABLE IF EXISTS artist_users, album_users, artist, users, album;

CREATE TABLE users (
    user_id SERIAL,
    username varchar(50) NOT NULL UNIQUE,
    password_hash varchar(200) NOT NULL,
    role varchar(50) NOT NULL,
    CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE artist (
    artist_id serial PRIMARY KEY,
    artist_name varchar(256),
    followers int,
    artist_spotify_id varchar(256) NOT NULL UNIQUE
);

CREATE TABLE album (
    album_id serial PRIMARY KEY,
    album_name VARCHAR(256),
    album_spotify_id VARCHAR(256)
);

 -- many to many relationship between the artist table and the users table
 -- therefore need a join table
CREATE TABLE artist_users (
    user_id int,
    artist_id int,

    CONSTRAINT PK_users_artist PRIMARY KEY (user_id, artist_id),
    CONSTRAINT FK_users FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT FK_artist FOREIGN KEY (artist_id) REFERENCES artist(artist_id)
);

CREATE TABLE album_users (
    user_id int,
    album_id int,

    CONSTRAINT PK_user_album PRIMARY KEY (user_id, album_id),
    CONSTRAINT FK_users FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT FK_album FOREIGN KEY (album_id) REFERENCES album(album_id)
);

COMMIT TRANSACTION;