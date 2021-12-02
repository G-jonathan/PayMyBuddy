INSERT INTO user_account
VALUES ('user1@email.com', 'user1FirstName', 'user1LastName', 'user1Password', 100, true),
       ('user2@email.com', 'user2FirstName', 'user2LastName', 'user2Password', 100, true),
       ('user3@email.com', 'user3FirstName', 'user3LastName', 'user3Password', 100, true),
       ('user4@email.com', 'user4FirstName', 'user4LastName', 'user4Password', 100, true),
       ('user5@email.com', 'user5FirstName', 'user5LastName', 'user5Password', 100, true)
;
INSERT INTO authorities
VALUES ('user1@email.com', 'ROLE_USER'),
       ('user2@email.com', 'ROLE_USER'),
       ('user3@email.com', 'ROLE_USER'),
       ('user4@email.com', 'ROLE_USER'),
       ('user5@email.com', 'ROLE_USER')
;
INSERT INTO connexion (user_account_email, connexion_email)
VALUES ('user1@email.com', 'user2@email.com'),
       ('user1@email.com', 'user3@email.com'),
       ('user2@email.com', 'user3@email.com')
;
INSERT INTO buddy_transaction (amount, charges, description, date, user_account_email, connexion_id)
VALUES (10, 0, 'payment of 10 euros to user2FirstName user2LastName', CURRENT_DATE, 'user1@email.com',
        (SELECT id from connexion WHERE user_account_email = 'user1@email.com' AND connexion_email = 'user2@email.com')),
       (5, 0, 'payment of 5 euros to user3FirstName user3LastName', CURRENT_DATE, 'user1@email.com',
        (SELECT id from connexion WHERE user_account_email = 'user1@email.com' AND connexion_email = 'user3@email.com'))
       (25, 0, 'payment of 25 euros to user3FirstName user3LastName', CURRENT_DATE, 'user2@email.com',
        (SELECT id from connexion WHERE user_account_email = 'user2@email.com' AND connexion_email = 'user3@email.com'))
;