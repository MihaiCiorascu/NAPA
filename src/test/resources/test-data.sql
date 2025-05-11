INSERT INTO Owner (Owner_name) 
VALUES 
    ('Holland America Cruises'), 
    ('Royal Caribbean Cruises'), 
    ('Carnival Cruises'), 
    ('Mitsubishi Heavy Industries'), 
    ('Mitsui O.S.K. Lines');

INSERT INTO Ships (Ship_name, Imo_number, Owner_Id)
VALUES 
    ('Symphony of the Seas', 9744001, 2),
    ('Eco Arctic', 9746683, 4),
    ('Explorer Spirit', 9313486, 5),
    ('Carnival Luminosa', 9398905, 3);

INSERT INTO Category (Ship_id, Ship_type, Ship_tonnage)
VALUES 
    (1, 'Cruise', 208081),
    (2, 'Crude Oil Tanker', 19554),
    (3, 'LPG Tanker', 57657),
    (4, 'Cruise', 323872);

INSERT INTO Ship_Owners (Ship_Id, Owner_Id) VALUES (1, 2);
INSERT INTO Ship_Owners (Ship_Id, Owner_Id) VALUES (2, 4);
INSERT INTO Ship_Owners (Ship_Id, Owner_Id) VALUES (3, 5);
INSERT INTO Ship_Owners (Ship_Id, Owner_Id) VALUES (4, 3); 