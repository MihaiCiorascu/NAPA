CREATE TABLE Owner (
    Owner_Id INT PRIMARY KEY IDENTITY(1,1),
    Owner_name VARCHAR(255)
);

CREATE TABLE Ships (
    Id INT PRIMARY KEY IDENTITY(1,1),
    Owner_Id INT,
    Ship_name VARCHAR(255),
    Imo_number INT,
    FOREIGN KEY (Owner_Id) REFERENCES Owner(Owner_Id)
);

CREATE TABLE Category (
    Id INT PRIMARY KEY IDENTITY(1,1),
    Ship_id INT,
    Ship_type VARCHAR(255),
    Ship_tonnage INT,
    FOREIGN KEY (Ship_id) REFERENCES Ships(Id)
);

CREATE TABLE Ship_Owners (
    Ship_Id INT,
    Owner_Id INT,
    PRIMARY KEY (Ship_Id, Owner_Id),
    FOREIGN KEY (Ship_Id) REFERENCES Ships(Id),
    FOREIGN KEY (Owner_Id) REFERENCES Owner(Owner_Id)
); 