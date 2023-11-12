BEGIN;
CREATE SEQUENCE article_seq;

CREATE TABLE article(
    id integer primary key NOT NULL DEFAULT nextval('article_seq'),
    title varchar not null,
    href varchar not null
);

CREATE SEQUENCE content_item_seq;

CREATE TABLE content_item(
    id integer primary key NOT NULL default nextval('content_item_seq'),
    text varchar not null,
    image_url varchar not null,
    image_description varchar not null,
    is_supplementary bool not null
);

CREATE SEQUENCE product_seq;

CREATE TABLE product(
    id integer primary key NOT NULL default nextval('product_seq'),
    title varchar not null,
    image_url varchar not null,
    description varchar not null,
    types varchar not null,
    volumes varchar not null
);

COMMIT;