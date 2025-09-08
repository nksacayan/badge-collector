INSERT INTO users (id, name) VALUES 
  (1, 'Nick'),
  (2, 'Angela');

INSERT INTO badges (id, name, description) VALUES
  (101, 'Veteran Collector', 'Awarded for collecting 100 unique items.'),
  (102, 'Completionist', 'Earned for unlocking every badge in a set.');

-- Assign badge 101 to user 1
INSERT INTO user_badges (user_id, badge_id) VALUES (1, 101);

-- Assign badge 102 to user 1
INSERT INTO user_badges (user_id, badge_id) VALUES (1, 102);

-- Assign badge 101 to user 2
INSERT INTO user_badges (user_id, badge_id) VALUES (2, 101);