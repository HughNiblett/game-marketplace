CREATE TABLE Users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password_hash VARCHAR(150) NOT NULL,
    email VARCHAR(100) NOT NULL,
    createdAt VARCHAR(27) NOT NULL
);

CREATE TABLE Currencies (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE Items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(300) NOT NULL
);

CREATE TABLE Wallets (
    user_id BIGINT NOT NULL,
    currencyId BIGINT NOT NULL,
    amount BIGINT NOT NULL,
    PRIMARY KEY(user_id, currencyId),
    FOREIGN KEY(user_id) REFERENCES Users(id),
    FOREIGN KEY(currencyId) REFERENCES Currencies(id)
);

CREATE TABLE Inventories (
   user_id BIGINT NOT NULL,
   item_id BIGINT NOT NULL,
   quantity BIGINT NOT NULL,
   reserved BIGINT NOT NULL DEFAULT 0,
   PRIMARY KEY(user_id, item_id),
   FOREIGN KEY(user_id) REFERENCES Users(id),
   FOREIGN KEY(item_id) REFERENCES Items(id)
);