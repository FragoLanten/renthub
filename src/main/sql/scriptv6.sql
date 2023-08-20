CREATE TABLE IF NOT EXISTS public.attributes
(
    id bigserial NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT attribute_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.deals
(
    id bigserial NOT NULL,
    start_date timestamp without time zone NOT NULL,
    end_date timestamp without time zone,
    estate_id bigint NOT NULL,
    type_id bigint NOT NULL,
    buyer_user_id bigint NOT NULL,
    status_id bigint NOT NULL,
    CONSTRAINT deals_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.estate
(
    id bigserial NOT NULL,
    "number" integer NOT NULL,
    square numeric NOT NULL,
    price numeric NOT NULL,
    owner_user_id bigint NOT NULL,
    street_id bigint NOT NULL,
    advertisment_id bigint NOT NULL,
    city_id bigint NOT NULL,
    CONSTRAINT estate_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.type_deal
(
    id bigserial NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT type_deal_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.atribute_value
(
    id bigserial NOT NULL,
    name character varying NOT NULL,
    CONSTRAINT atribute_value_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.estate_attribute_value
(
    attribute_id bigint NOT NULL,
    estate_id bigint NOT NULL,
    value_id bigint NOT NULL
);

CREATE TABLE IF NOT EXISTS public.status_deal
(
    id bigserial NOT NULL,
    name character varying NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.streets
(
    id bigserial NOT NULL,
    name character varying NOT NULL,
    CONSTRAINT streets_pkey PRIMARY KEY (id),
    CONSTRAINT street_key UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS public.users_role
(
    user_id bigint NOT NULL,
    role character varying NOT NULL,
    CONSTRAINT user_role_key UNIQUE (user_id, role)
);

CREATE TABLE IF NOT EXISTS public.wishlist
(
    estate_id bigserial NOT NULL,
    user_id bigserial NOT NULL
);

CREATE TABLE IF NOT EXISTS public.revenues
(
    id bigserial NOT NULL,
    date timestamp without time zone NOT NULL,
    sum numeric NOT NULL,
    user_id bigint NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.cities
(
    id bigserial NOT NULL,
    name character varying,
    CONSTRAINT cities_pkey PRIMARY KEY (id),
    CONSTRAINT cities_name_key UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS public.users
(
    id bigserial NOT NULL,
    first_name character varying NOT NULL,
    last_name character varying NOT NULL,
    login character varying NOT NULL,
    password character varying NOT NULL,
    phone character varying NOT NULL,
    is_deleted boolean NOT NULL,
    balance numeric NOT NULL,
    CONSTRAINT users_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.advertisments
(
    id bigserial NOT NULL,
    end_date timestamp without time zone,
    visible boolean NOT NULL,
    rank integer NOT NULL,
    is_moderated boolean NOT NULL,
    CONSTRAINT advertisments_pk PRIMARY KEY (id)
);

INSERT INTO type_deal (name) VALUES ('SALE'), ('RENT');

INSERT INTO status_deal (name) VALUES ('IN WORK'), ('FINISHED');

INSERT INTO users_role (user_id, role) VALUES
                                           (1, 'SELLER'), (2, 'SELLER'), (3, 'BUYER'), (4, 'SELLER'), (5, 'BUYER'),
                                           (6, 'SELLER'), (7, 'BUYER'), (8, 'BUYER'), (9, 'BUYER'), (10, 'BUYER'),
                                           (11, 'USER'), (12, 'USER'), (13, 'USER'), (14, 'ADMIN'), (15, 'USER');

INSERT INTO streets (name) VALUES
                               ('Lenina'), ('Sverdlova'), ('Kirova'), ('Lermontova'), ('Mira'), ('Oktyabrskaya');

INSERT INTO cities (name) VALUES
                              ('Moscow'), ('Yekaterinburg'), ('Minsk'), ('Gomel'), ('Novosibirsk');

INSERT INTO attributes (name) VALUES
                                  ('flat number'), ('number of floors'), ('floor'), ('number of rooms'), ('balcony');

INSERT INTO atribute_value (name) VALUES
                                      ('85'), ('30'), ('15'), ('3'), ('true'),
                                      ('3'), ('4'), ('true'),
                                      ('30'), ('5'), ('4'), ('2'), ('false'),
                                      ('134'), ('40'), ('37'), ('4'), ('true');

INSERT INTO advertisments (end_date, visible, rank, is_moderated) VALUES
                                                                      ('2023-09-07', true, 2, true),
                                                                      ('2023-09-12', true, 4, true),
                                                                      (null, true, 0, true),
                                                                      (null, false, 0, true),
                                                                      ('2023-09-27', true, 8, true),
                                                                      (null, true, 0, false);


INSERT INTO users (first_name, last_name, login, password, phone, is_deleted, balance) VALUES
                                                                                           ('Alexio', 'Fronks', 'afronks0', 'gL7(503yV.yyFO', '9603505542', false, 1131304843),
                                                                                           ('Eloise', 'Tollfree', 'etollfree1', 'xG2_4R8ZcPkLnB,,', '9027928591', false, 1969967536),
                                                                                           ('Glynnis', 'McTeague', 'gmcteague2', 'pW9(S`iT', '9242191483', false, 1489198406),
                                                                                           ('Alexina', 'Fearick', 'afearick3', 'xW8=L7x8D+|t', '9871284160', false, 1179912815),
                                                                                           ('Sula', 'Jaycock', 'sjaycock4', 'mL0*b@+ADSy', '9936861447', false, 516618958),
                                                                                           ('Yance', 'Alejandri', 'yalejandri5', 'kC6#`=wrlvk0', '9429491213', false, 353648291),
                                                                                           ('Tobye', 'Tyers', 'ttyers6', 'hZ1}3el2C"{M/o', '9892588993', false, 1612115382),
                                                                                           ('Lenka', 'Chad', 'lchad7', 'oQ1#,Q8Ig', '9379556226', false, 1878444572),
                                                                                           ('Frankie', 'Harmour', 'fharmour8', 'qT7@r''57u', '9266907671', false, 1302811959),
                                                                                           ('Lorelle', 'Massenhove', 'lmassenhove9', 'lG4`OaWGYCe', '9546410297', false, 1517591463),
                                                                                           ('Orren', 'Brimilcombe', 'obrimilcombea', 'fZ1>l+L=', '9418026004', false, 1882323479),
                                                                                           ('Janaya', 'Ecclestone', 'jecclestoneb', 'nF9{pzWD''e`=Wh8', '9597071752', false, 1794270479),
                                                                                           ('Ely', 'Sneyd', 'esneydc', 'pB7&mu?QMb', '9173140074', false, 1216655078),
                                                                                           ('Gerri', 'Wase', 'gwased', 'gX7/?/L#', '9239489967', false, 1233919226),
                                                                                           ('Rheta', 'Camfield', 'rcamfielde', 'qG5<*5(n\T*', '9799848020', true, 404884861),
                                                                                           ('Frances', 'Drury', 'fdruryf', 'uX1|u>|tK7Z6p', '9627505038', false, 1439310088),
                                                                                           ('Cindy', 'Rubanenko', 'crubanenkog', 'rO2(wHbTr', '9662412888', false, 1147282207),
                                                                                           ('Lacy', 'Dunphie', 'ldunphieh', 'yS4"PD~z8AHho''', '9712251572', true, 420101510),
                                                                                           ('Torrin', 'Dightham', 'tdighthami', 'dW3=VBR.eq_$CCT', '9757821803', true, 1154046389),
                                                                                           ('Faunie', 'Patek', 'fpatekj', 'bU5,P*7WZl?\F<t', '9771618071', false, 1944542300),
                                                                                           ('Nerita', 'Bontein', 'nbonteink', 'cK1!Dk_}{}', '9774732666', false, 458144942),
                                                                                           ('Mitch', 'Kobiela', 'mkobielal', 'xT4(SgFsFXxnNb|h', '9191046197', true, 501689186),
                                                                                           ('Dunn', 'Trengrove', 'dtrengrovem', 'fH2\o*#(C', '9219910234', false, 735941534),
                                                                                           ('Rebe', 'Quarterman', 'rquartermann', 'wX7{0PhTF_', '9073157695', false, 651790947),
                                                                                           ('Demott', 'Enden', 'dendeno', 'fU8(.B=b=O4K', '9343936746', false, 1169206912),
                                                                                           ('Clarinda', 'Cantopher', 'ccantopherp', 'vD2"{@m{9}iT', '9732267710', true, 640409791),
                                                                                           ('Wadsworth', 'Aldhouse', 'waldhouseq', 'vD3&>f(9I\y")', '9025211842', false, 1489527410),
                                                                                           ('Lorie', 'Raccio', 'lraccior', 'aM4&M@UF9', '9466389215', false, 1898977542),
                                                                                           ('Rafaello', 'Giacomasso', 'rgiacomassos', 'pI2?sLblR=M<', '9782967449', false, 910508727),
                                                                                           ('Kelvin', 'Rainger', 'kraingert', 'wJ6{@`{G%U', '9141605329', false, 573297765);

INSERT INTO revenues (date, sum, user_id) VALUES
                                              ('2023-08-13', 1272831, 1),
                                              ('2023-07-07', 3112645, 2),
                                              ('2023-06-10', 1863340, 3),
                                              ('2023-07-23', 992681, 4),
                                              ('2023-06-23', 2504324, 5),
                                              ('2023-07-25', 2772944, 6),
                                              ('2023-07-24', 1031654, 7),
                                              ('2023-07-30', 746513, 8),
                                              ('2023-08-13', 2279749, 9),
                                              ('2023-07-30', 4332099, 10),
                                              ('2023-08-12', 2844568, 11),
                                              ('2023-06-06', 2983740, 12),
                                              ('2023-06-17', 1280851, 13),
                                              ('2023-08-02', 3306925, 14),
                                              ('2023-07-16', 1325972, 15);

INSERT INTO estate (number, square, price, owner_user_id, street_id, advertisment_id, city_id) VALUES
                                                                                                   (72, 203, 815190, 1, 2, 1, 5),
                                                                                                   (96, 194, 1212421964, 2, 1, 3, 3),
                                                                                                   (193, 50, 146966861, 1, 2, 2, 1),
                                                                                                   (10, 43, 1903321, 4, 3, 6, 2),
                                                                                                   (81, 48, 982152411, 6, 5, 4, 3),
                                                                                                   (14, 49, 166185972, 2, 2, 5, 3);

INSERT INTO wishlist (user_id, estate_id) VALUES
                                              (5, 2), (9, 6), (9, 3), (10, 5), (8, 6), (3, 4), (8, 3), (7, 2),
                                              (3, 6), (7, 4), (3, 2), (5, 1), (10, 4), (10, 5), (5, 6);

INSERT INTO estate_attribute_value (attribute_id, estate_id, value_id) VALUES
                                                                           (1, 1, 1), (1, 2, 2), (1, 3, 3), (1, 4, 4), (1, 5, 5),
                                                                           (2, 1, 6), (2, 2, 7), (2, 3, 8),
                                                                           (3, 1, 9), (3, 2, 10), (3, 3, 11), (3, 4, 12), (3, 5, 13),
                                                                           (4, 1, 14), (4, 2, 15), (4, 3, 16), (4, 4, 17), (4, 5, 18);

INSERT INTO deals (start_date, end_date, estate_id, type_id, buyer_user_id, status_id) VALUES
                                                                                           ('2023-07-01', '2023-07-28', 1, 2, 3, 2),
                                                                                           ('2023-06-28', '2023-07-15', 2, 1, 9, 2),
                                                                                           ('2023-06-17', '2023-08-03', 3, 1, 5, 2),
                                                                                           ('2023-07-17', null, 4, 2, 7, 1);

ALTER TABLE IF EXISTS public.deals
    ADD CONSTRAINT deal_object_fk FOREIGN KEY (estate_id)
        REFERENCES public.estate (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;


ALTER TABLE IF EXISTS public.deals
    ADD CONSTRAINT deal_type_fk FOREIGN KEY (type_id)
        REFERENCES public.type_deal (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;


ALTER TABLE IF EXISTS public.deals
    ADD CONSTRAINT deal_status_fk FOREIGN KEY (status_id)
        REFERENCES public.status_deal (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;


ALTER TABLE IF EXISTS public.deals
    ADD CONSTRAINT buyer_user_id_fk FOREIGN KEY (buyer_user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;


ALTER TABLE IF EXISTS public.estate
    ADD CONSTRAINT street_id_fk FOREIGN KEY (street_id)
        REFERENCES public.streets (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;


ALTER TABLE IF EXISTS public.estate
    ADD CONSTRAINT owner_user_id_fk FOREIGN KEY (owner_user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;


ALTER TABLE IF EXISTS public.estate
    ADD CONSTRAINT advertisment_id_fk FOREIGN KEY (advertisment_id)
        REFERENCES public.advertisments (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;


ALTER TABLE IF EXISTS public.estate
    ADD CONSTRAINT city_id_fk FOREIGN KEY (city_id)
        REFERENCES public.cities (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;


ALTER TABLE IF EXISTS public.estate_attribute_value
    ADD CONSTRAINT estate_attribute_value_on_estate_fk FOREIGN KEY (estate_id)
        REFERENCES public.estate (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;


ALTER TABLE IF EXISTS public.estate_attribute_value
    ADD CONSTRAINT estate_attribute_value_on_attribute_fk FOREIGN KEY (attribute_id)
        REFERENCES public.attributes (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;


ALTER TABLE IF EXISTS public.estate_attribute_value
    ADD CONSTRAINT estate_attribute_value_on_value_fk FOREIGN KEY (value_id)
        REFERENCES public.atribute_value (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;


ALTER TABLE IF EXISTS public.users_role
    ADD CONSTRAINT user_id_fk FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;


ALTER TABLE IF EXISTS public.wishlist
    ADD CONSTRAINT wishlist_estate_id_fk FOREIGN KEY (estate_id)
        REFERENCES public.estate (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;


ALTER TABLE IF EXISTS public.wishlist
    ADD CONSTRAINT wishlist_user_id_fk FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;


ALTER TABLE IF EXISTS public.revenues
    ADD CONSTRAINT revenue_user_id_fk FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;