CREATE TABLE event (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    location VARCHAR(255) NOT NULL,
    creation_date DATE NOT NULL,
    updated_date DATE NOT NULL,
    event_start_date_and_time DATETIME NOT NULL,
    event_end_date_and_time DATETIME NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);

INSERT INTO event (name, category, description, location, creation_date, updated_date, event_start_date_and_time, event_end_date_and_time, user_id)
VALUES
('Event 1', 'Category 1', 'Description 1', 'Location 1', '2024-05-03', '2024-05-03', '2024-05-03T12:00:00', '2024-05-03T14:00:00', 1),
('Event 2', 'Category 2', 'Description 2', 'Location 2', '2024-05-03', '2024-05-03', '2024-05-03T13:00:00', '2024-05-03T15:00:00', 1),
('Event 3', 'Category 3', 'Description 3', 'Location 3', '2024-05-03', '2024-05-03', '2024-05-03T14:00:00', '2024-05-03T16:00:00', 1),
('Event 4', 'Category 1', 'Description 1', 'Location 1', '2024-05-03', '2024-05-03', '2024-05-03T12:00:00', '2024-05-03T14:00:00', 1),
('Event 5', 'Category 2', 'Description 2', 'Location 2', '2024-05-03', '2024-05-03', '2024-05-03T13:00:00', '2024-05-03T15:00:00', 1),
('Event 6', 'Category 3', 'Description 3', 'Location 3', '2024-05-03', '2024-05-03', '2024-05-03T14:00:00', '2024-05-03T16:00:00', 2),
('Event 7', 'Category 1', 'Description 1', 'Location 1', '2024-05-03', '2024-05-03', '2024-05-03T12:00:00', '2024-05-03T14:00:00', 2),
('Event 8', 'Category 2', 'Description 2', 'Location 2', '2024-05-03', '2024-05-03', '2024-05-03T13:00:00', '2024-05-03T15:00:00', 2),
('Event 9', 'Category 3', 'Description 3', 'Location 3', '2024-05-03', '2024-05-03', '2024-05-03T14:00:00', '2024-05-03T16:00:00', 2),
('Event 10', 'Category 3', 'Description 3', 'Location 3', '2024-05-03', '2024-05-03', '2024-05-03T14:00:00', '2024-05-03T16:00:00', 2);