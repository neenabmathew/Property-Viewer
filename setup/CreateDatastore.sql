CREATE DATABASE PropertyViewer;

CREATE TABLE Buildings 
  ( 
     building_name   VARCHAR(100) PRIMARY KEY, 
     Street      VARCHAR(100) NOT NULL,
     umber     INT,
     postcode     INT,
     city    VARCHAR(100) NOT NULL, 
    Country  VARCHAR(100) NOT NULL
     
  ); 
  
 