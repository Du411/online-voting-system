CREATE DATABASE online_voting_system;
USE online_voting_system;
CREATE TABLE voters (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);
CREATE TABLE voting_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
CREATE TABLE voting_records (
    id INT AUTO_INCREMENT PRIMARY KEY,
    voter_id INT NOT NULL,
    voting_item_id INT NOT NULL,
    FOREIGN KEY (voter_id) REFERENCES voters(id),
    FOREIGN KEY (voting_item_id) REFERENCES voting_items(id),
    UNIQUE KEY unique_vote (voter_id, voting_item_id)
);
USE online_voting_system;
INSERT INTO voters (name) VALUES
('Leo'),
('Sandy'),
('Randy'),
('RSY');

INSERT INTO voting_items (name) VALUES
('電腦'),
('滑鼠');

INSERT INTO voting_records (voter_id, voting_item_id) VALUES
(1, 1),
(2, 1),
(2, 2),
(3, 2),
(4, 2);

DELIMITER $$

CREATE PROCEDURE calculate_votes_per_item()
BEGIN
    SELECT vi.id, vi.name, IFNULL(vote_count, 0) AS vote_count
    FROM voting_items vi 
    LEFT JOIN
        (SELECT 
            voting_item_id, 
            COUNT(DISTINCT voter_id) AS vote_count
         FROM 
            voting_records
         GROUP BY 
            voting_item_id
        ) AS vote_counts
    ON 
        vi.id = vote_counts.voting_item_id;
END $$

DELIMITER ;