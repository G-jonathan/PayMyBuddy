
CREATE TABLE public.user_Account (
                email VARCHAR(254) NOT NULL,
                first_name VARCHAR(100) NOT NULL,
                last_name VARCHAR(100) NOT NULL,
                password VARCHAR(100) NOT NULL,
                balance NUMERIC(6,2) NOT NULL,
                is_activated BOOLEAN DEFAULT true NOT NULL,
                CONSTRAINT user_account_pk PRIMARY KEY (email)
);


CREATE SEQUENCE public.bank_account_id_seq;

CREATE TABLE public.bank_account (
                id INTEGER NOT NULL DEFAULT nextval('public.bank_account_id_seq'),
                holder_first_name VARCHAR(100) NOT NULL,
                holder_last_name VARCHAR(100) NOT NULL,
                iban VARCHAR(31) NOT NULL,
                bic VARCHAR(11) NOT NULL,
                user_account_email VARCHAR(254) NOT NULL,
                CONSTRAINT bank_account_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.bank_account_id_seq OWNED BY public.bank_account.id;

CREATE SEQUENCE public.bank_transaction_id_seq;

CREATE TABLE public.bank_transaction (
                id INTEGER NOT NULL DEFAULT nextval('public.bank_transaction_id_seq'),
                amount NUMERIC(6,2) NOT NULL,
                charges NUMERIC(4,2) NOT NULL,
                description VARCHAR(100) NOT NULL,
                date DATE NOT NULL,
                transaction_type VARCHAR(10) NOT NULL,
                bank_account_id INTEGER NOT NULL,
                user_account_email VARCHAR(254) NOT NULL,
                CONSTRAINT bank_transaction_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.bank_transaction_id_seq OWNED BY public.bank_transaction.id;

CREATE SEQUENCE public.connexion_id_seq;

CREATE TABLE public.connexion (
                id INTEGER NOT NULL DEFAULT nextval('public.connexion_id_seq'),
                user_account_email VARCHAR(254) NOT NULL,
                connexion_email VARCHAR(254) NOT NULL,
                CONSTRAINT connexion_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.connexion_id_seq OWNED BY public.connexion.id;

CREATE SEQUENCE public.buddy_transaction_id_seq;

CREATE TABLE public.buddy_transaction (
                id INTEGER NOT NULL DEFAULT nextval('public.buddy_transaction_id_seq'),
                amount NUMERIC(6,2) NOT NULL,
                charges NUMERIC(4,2) NOT NULL,
                description VARCHAR(100) NOT NULL,
                date DATE NOT NULL,
                user_account_email VARCHAR(254) NOT NULL,
                connexion_id INTEGER NOT NULL,
                CONSTRAINT buddy_transaction_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.buddy_transaction_id_seq OWNED BY public.buddy_transaction.id;

ALTER TABLE public.connexion ADD CONSTRAINT user_account_connexion_fk
FOREIGN KEY (user_account_email)
REFERENCES public.user_Account (email)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.connexion ADD CONSTRAINT user_account_connexion_fk1
FOREIGN KEY (connexion_email)
REFERENCES public.user_Account (email)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.bank_account ADD CONSTRAINT user_account_bank_account_fk
FOREIGN KEY (user_account_email)
REFERENCES public.user_Account (email)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.bank_transaction ADD CONSTRAINT user_account_bank_transaction_fk
FOREIGN KEY (user_account_email)
REFERENCES public.user_Account (email)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.buddy_transaction ADD CONSTRAINT user_account_buddy_transaction_fk
FOREIGN KEY (user_account_email)
REFERENCES public.user_Account (email)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.bank_transaction ADD CONSTRAINT bank_account_bank_transaction_fk
FOREIGN KEY (bank_account_id)
REFERENCES public.bank_account (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.buddy_transaction ADD CONSTRAINT connexion_buddy_transaction_fk
FOREIGN KEY (connexion_id)
REFERENCES public.connexion (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
