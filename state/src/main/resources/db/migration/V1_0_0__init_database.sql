CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    created_at DATETIME(6) NOT NULL
);

CREATE TABLE currencies (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL
);

CREATE TABLE wallets (
    user_id BIGINT NOT NULL,
    currency_id BIGINT NOT NULL,
    amount BIGINT NOT NULL,
    PRIMARY KEY(user_id, currency_id),
    FOREIGN KEY(user_id) REFERENCES users(id),
    FOREIGN KEY(currency_id) REFERENCES currencies(id)
);

CREATE TABLE inventories (
   user_id BIGINT NOT NULL,
   item_id BIGINT NOT NULL,
   quantity BIGINT NOT NULL,
   reserved BIGINT NOT NULL DEFAULT 0,
   PRIMARY KEY(user_id, item_id),
   FOREIGN KEY(user_id) REFERENCES users(id),
   FOREIGN KEY(item_id) REFERENCES items(id)
);