CREATE TABLE scryfall_cards
(
    id               UUID         NOT NULL,
    name             VARCHAR(255) NOT NULL,
    setcode          VARCHAR(255) NOT NULL,
    collector_number VARCHAR(255) NOT NULL,
    image_uri        VARCHAR(255),
    CONSTRAINT pk_scryfall_cards PRIMARY KEY (id)
);

CREATE TABLE user_card_entries
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    card_id     UUID,
    is_foil     BOOLEAN,
    owner_id    UUID,
    borrower_id UUID,
    count       BIGINT,
    CONSTRAINT pk_user_card_entries PRIMARY KEY (id)
);

CREATE TABLE users
(
    id         UUID NOT NULL,
    discord_id BIGINT,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE user_card_entries
    ADD CONSTRAINT FK_USER_CARD_ENTRIES_ON_BORROWER FOREIGN KEY (borrower_id) REFERENCES users (id);

ALTER TABLE user_card_entries
    ADD CONSTRAINT FK_USER_CARD_ENTRIES_ON_CARD FOREIGN KEY (card_id) REFERENCES scryfall_cards (id);

ALTER TABLE user_card_entries
    ADD CONSTRAINT FK_USER_CARD_ENTRIES_ON_OWNER FOREIGN KEY (owner_id) REFERENCES users (id);