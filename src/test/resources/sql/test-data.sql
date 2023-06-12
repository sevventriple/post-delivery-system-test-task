INSERT INTO ADDRESS (id, region, city, district, street, home_number)
VALUES (NEXTVAL('ADDRESS_SEQ'), 'САМАРСКАЯ ОБЛАСТЬ', 'САМАРА', 'КИРОВСКИЙ', 'СТАРА ЗАГОРА', '277'),
       (NEXTVAL('ADDRESS_SEQ'), 'САМАРСКАЯ ОБЛАСТЬ', 'САМАРА', 'КИРОВСКИЙ', 'КАРЛА МАРКСА', '480'),
       (NEXTVAL('ADDRESS_SEQ'), 'МОСКОВСКАЯ ОБЛАСТЬ', 'МОСКВА', 'ЦЕНТРАЛЬНЫЙ', 'МЯСНИЦКАЯ', '26');

INSERT INTO POST_OFFICE (post_index, name, postal_office_address_id)
VALUES (443106, 'Индекс 443106 - почтовое отделение г Самара', 1),
       (101000, 'Индекс 101000 - почтовое отделение г Москва', 3);