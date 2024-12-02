-- USERS TABLE
CREATE TABLE IF NOT EXISTS public.users(
	id BIGINT NOT NULL PRIMARY KEY,
	name VARCHAR(225),
	username VARCHAR(225) UNIQUE,
	email VARCHAR(225) UNIQUE,
	phone VARCHAR(255) UNIQUE,
	password VARCHAR(225),
	password_encoded text,
	user_type integer NOT NULL DEFAULT 0,
	address VARCHAR(255),
	signup_date timestamp without time zone,
	date_of_birth DATE,

	created_at timestamp without time zone,
    updated_at timestamp without time zone
)