-- USERS TABLE
CREATE TABLE IF NOT EXISTS public.users
(
	id BIGINT NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
	name VARCHAR(225),
	username VARCHAR(225) UNIQUE,
	email VARCHAR(225) UNIQUE,
	phone VARCHAR(255) UNIQUE,
	password VARCHAR(225),
	password_encoded TEXT,
	user_type INTEGER NOT NULL DEFAULT 0, -- 1 => Land lords, 2 => Renters
	address VARCHAR(255),
	signup_date DATE,
	date_of_birth DATE,
	status INTEGER DEFAULT 0,

	created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE
);

-- BUILDINGS TABLE
CREATE TABLE IF NOT EXISTS public.buildings
(
	id BIGINT NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
	name VARCHAR(255) NOT NULL,
	address TEXT,

	created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE
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

-- Index: BUILDING FLAT fki_fk_building_flat_buildings
CREATE INDEX IF NOT EXISTS fki_fk_building_flat_buildings
    ON public.building_flat USING btree
    (building_id ASC NULLS LAST)
    TABLESPACE pg_default;


-- RENTERS TABLE
CREATE TABLE IF NOT EXISTS public.renters
(
	id BIGINT NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
	user_id BIGINT NOT NULL,
	nid_no VARCHAR NOT NULL,
	deal TEXT,
	building_id BIGINT NOT NULL,
	flats TEXT,

	created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,

    CONSTRAINT fk_renters_users FOREIGN KEY (user_id)
        REFERENCES public.users (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Index: RENTERS fki_renters_users
CREATE INDEX IF NOT EXISTS fki_renters_users
    ON public.renters USING btree
    (user_id ASC NULLS LAST)
    TABLESPACE pg_default;


-- RENT PAYMENT INFO TABLE
CREATE TABLE IF NOT EXISTS public.rent_payment
(
	id BIGINT NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
	renter_id BIGINT NOT NULL,
	amount DOUBLE PRECISION NOT NULL DEFAULT 0,
	payment_date DATE,
	flat_no VARCHAR NOT NULL,
	utility_bill DOUBLE PRECISION DEFAULT 0,
	others_bill DOUBLE PRECISION DEFAULT 0,

	created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,

	CONSTRAINT fk_rent_pays_renters FOREIGN KEY (renter_id)
        REFERENCES public.renters (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Index: RENT PAYMENT INFO fki_fk_rent_pays_renters
CREATE INDEX IF NOT EXISTS fki_fk_rent_payment_renters
    ON public.rent_payment USING btree
    (renter_id ASC NULLS LAST)
    TABLESPACE pg_default;

