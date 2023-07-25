-- @block Create the bank_app database
CREATE DATABASE IF NOT EXISTS bank_app;

-- @block Use the bank_app database
USE bank_app;

-- @block Create the accounts table
CREATE TABLE IF NOT EXISTS accounts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    account_number VARCHAR(20) NOT NULL,
    owner_name VARCHAR(100) NOT NULL,
    balance DECIMAL(10, 2) NOT NULL
);
-- @block  Create the transactions table
CREATE TABLE IF NOT EXISTS transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    transaction_date DATETIME NOT NULL,
    sender_account_number VARCHAR(20) NOT NULL,
    receiver_account_number VARCHAR(20) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL
);

--@block  Insert into tabled
INSERT INTO accounts (account_number, owner_name, balance)
VALUES 
    ('1234567890', 'John Doe', 1000.00),
    ('9876543210', 'Jane Smith', 2000.00);