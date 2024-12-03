-- USERS TABLE
CREATE TABLE IF NOT EXISTS public.users
(
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
);

-- BUILDINGS TABLE
CREATE TABLE IF NOT EXISTS public.buildings
(
	id BIGINT NOT NULL PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	address TEXT,

	created_at timestamp without time zone,
    updated_at timestamp without time zone
);

-- BUILDINGS FLAT TABLE
CREATE TABLE IF NOT EXISTS public.building_flat
(
	building_id BIGINT NOT NULL,
	flat_no BIGINT NOT NULL,

	CONSTRAINT fk_building_flat_buildings FOREIGN KEY(building_id)
		REFERENCES public.buildings(id)
		ON UPDATE NO ACTION
		ON DELETE NO ACTION
);

-- Index: fki_fk_building_flat_buildings
CREATE INDEX IF NOT EXISTS fki_fk_building_flat_buildings
    ON public.building_flat USING btree
    (building_id ASC NULLS LAST)
    TABLESPACE pg_default;