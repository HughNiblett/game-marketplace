CREATE TABLE Users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    passwordHash VARCHAR(150) NOT NULL,
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

CREATE TABLE Wallet (
    userId BIGINT NOT NULL,
    currencyId BIGINT NOT NULL,
    amount BIGINT NOT NULL,
    PRIMARY KEY(userId, currencyId),
    FOREIGN KEY(userId) REFERENCES Users(id),
    FOREIGN KEY(currencyId) REFERENCES Currencies(id)
);

CREATE TABLE Inventory (
   userId BIGINT NOT NULL,
   itemId BIGINT NOT NULL,
   quantity BIGINT NOT NULL,
   reserved BIGINT NOT NULL DEFAULT 0,
   PRIMARY KEY(userId, itemId),
   FOREIGN KEY(userId) REFERENCES Users(id),
   FOREIGN KEY(itemId) REFERENCES Items(id)
);