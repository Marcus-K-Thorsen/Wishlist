INSERT INTO users (email, pw, givenName, lastName, amount_WL)
VALUES ('hans@email.com', 'kodeord123', 'Hans', 'Hansen', 2);
INSERT INTO users (email, pw, givenName, lastName, amount_WL)
VALUES ('kirsten@email.com', 'kodeord321', 'Kirsten', 'Kristensen', 1);

INSERT INTO wishlists (email, titel_WL, descr_WL)
VALUES ('hans@email.com', 'JULEGAVER', 'Hvad jeg ønsker til jul');
INSERT INTO wishlists (email, titel_WL, descr_WL)
VALUES ('hans@email.com', 'Fødselsdag', 'Hvad jeg ønsker til min fødselsdag');
INSERT INTO wishlists (email, titel_WL, descr_WL)
VALUES ('kirsten@email.com', 'JULEGAVER', 'Dette ønsker jeg mig til jul');

INSERT INTO wishes (id_WL, titel_W, descr_W, price, link)
VALUES (1, 'Træ Hest', 'En hest af træ', 99.95, 'https://link-til-hest.dk');
INSERT INTO wishes (id_WL, titel_W, descr_W, price, link)
VALUES (2, 'Træ And', 'En and af træ', 49.95, 'https://link-til-and.dk');
INSERT INTO wishes (id_WL, titel_W, descr_W, price, link)
VALUES (3, 'Tromme', 'En tromme til at slå på', 199.95, 'https://tromme-link.dk');