CREATE TABLE users (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    gender VARCHAR(50) NOT NULL,
    address VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    mobile VARCHAR(20) NOT NULL,
    creation_date DATE NOT NULL,
    updated_date DATE NOT NULL,
    events_of_interest VARCHAR(255),
    role enum('USER','ADMIN') NOT NULL);

INSERT INTO users (name, password, gender, address, email, mobile, creation_date, updated_date, events_of_interest, role)
VALUES
('Ashish Paghdar -1', '$2a$10$wQAvKT.23CMK9qMqJWEaxuWAO2WuHboZaS8okIbMw9c58oAnTtNFe', 'Male', '123 Main St, City', 'ashish1@example.com', '1234567890', '2024-05-03', '2024-05-03', 'Music, Sports', 'USER'),
('Ashish Paghdar -2', '$2a$10$ZtFqAL6kM.qeflA7YHA.POx2bMZ7lNpQdl9KzmyRL.ClQD74G8EbS', 'Male', '456 Elm St, Town', 'ashish2@example.com', '9876543210', '2024-05-03', '2024-05-03', 'Movies, Travel', 'USER'),
('Ashish Paghdar -3', '$2a$10$3io8kQRNSQDdgbbIU0BWj.iufPXXEgms22gXmNaOI5C3IP3IJ7OVO', 'Male', '789 Oak St, Village', 'ashish3@example.com', '5555555555', '2024-05-03', '2024-05-03', 'Books, Cooking', 'USER');
