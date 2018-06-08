-- Address Details for Bayzat
INSERT INTO BZB_T_ADDRESS (ADDRESS_ID, BUILDING_NAME, UNIT_NUMBER, STREET_ADDRESS, TOWN, CITY, STATE, COUNTRY, POSTAL_CODE) 
VALUES (BZB_SQ_ADDRESS_ID.nextval, 'Control Tower', '10-01', 'Detroid Rd', 'Motor City', 'Dubai', 'Dubai', 'United Arab Emirates', '391186');
-- Company Details for Bayzat
INSERT INTO BZB_T_COMPANY (COMPANY_ID, NAME, REGISTRATION_NUMBER, CONTACT_NUMBER, EMAIL, WEBSITE, ADDRESS_ID) 
VALUES (BZB_SQ_COMPANY_ID.nextval, 'Bayzat', 'Bzt-2013', '+97144298898', 'talktous@bayzat.com', 'http://www.bayzat.com', BZB_SQ_ADDRESS_ID.currval);

-- Address Details for Cognizant
INSERT INTO BZB_T_ADDRESS (ADDRESS_ID, BUILDING_NAME, UNIT_NUMBER, STREET_ADDRESS, TOWN, CITY, STATE, COUNTRY, POSTAL_CODE) 
VALUES (BZB_SQ_ADDRESS_ID.nextval, 'Plaza8@CBP', '07-01/06', '1 Changi Business Park Central', 'Changi Biz Hub', 'Singapore', 'Singapore', 'Singapore', '486025');
-- Company Details for Cognizant
INSERT INTO BZB_T_COMPANY (COMPANY_ID, NAME, REGISTRATION_NUMBER, CONTACT_NUMBER, EMAIL, WEBSITE, ADDRESS_ID) 
VALUES (BZB_SQ_COMPANY_ID.nextval, 'Cognizant', 'Cts-1994', '+6568124051', 'inquiry@cognizant.com', 'http://www.cognizant.com', BZB_SQ_ADDRESS_ID.currval);
