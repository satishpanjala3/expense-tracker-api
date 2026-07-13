INSERT INTO rooms(name, created_date)
VALUES ('Satish''s Room', CURRENT_DATE);

INSERT INTO users
(
name,
email,
phone,
password,
role,
status,
room_id,
created_date
)
VALUES
(
'Satish',
'satishpanjala3@gmail.com',
'9876543210',
'$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy',
'ADMIN',
'ACTIVE',
1,
CURRENT_DATE
);