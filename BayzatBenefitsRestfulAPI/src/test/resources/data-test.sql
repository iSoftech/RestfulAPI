-- Address Details for Bayzat
INSERT INTO BZB_T_ADDRESS (ADDRESS_ID, BUILDING_NAME, UNIT_NUMBER, STREET_ADDRESS, TOWN, CITY, STATE, COUNTRY, POSTAL_CODE) 
VALUES (BZB_SQ_ADDRESS_ID.nextval, 'Control Tower', '10-01', 'Detroid Rd', 'Motor City', 'Dubai', 'Dubai', 'United Arab Emirates', '391186');
-- Company Details for Bayzat
INSERT INTO BZB_T_COMPANY (COMPANY_ID, NAME, REGISTRATION_NUMBER, CONTACT_NUMBER, EMAIL, WEBSITE, ADDRESS_ID) 
VALUES (BZB_SQ_COMPANY_ID.nextval, 'Bayzat', 'Bzt-2013', '+97144298898', 'talktous@bayzat.com', 'http://www.bayzat.com', BZB_SQ_ADDRESS_ID.currval);

-- Address Details for Bayzat Employee 1
INSERT INTO BZB_T_ADDRESS (ADDRESS_ID, BUILDING_NAME, UNIT_NUMBER, STREET_ADDRESS, TOWN, CITY, STATE, COUNTRY, POSTAL_CODE) 
VALUES (BZB_SQ_ADDRESS_ID.nextval, 'Tower 1', '08-12', 'Baniyas Road', 'Deira', 'Dubai', 'Dubai', 'United Arab Emirates', '00594');
-- Employee Details for Bayzat Employee 1
INSERT INTO BZB_T_EMPLOYEE (EMPLOYEE_ID, EMPLOYEE_CODE, FIRST_NAME, LAST_NAME, GENDER, AGE, DATE_OF_BIRTH, CONTACT_NUMBER, EMAIL, CURRENT_SALARY, DATE_OF_JOINING, ADDRESS_ID, COMPANY_ID) 
VALUES (BZB_SQ_EMPLOYEE_ID.nextval, 'BZB-10001', 'Mohamed', 'Ahamed', 'Male', 31, to_date('21-03-1987', 'dd-mm-yyyy'), '+97152898942', 'mohamed.a@bayzat.com', '100000.00', to_date('01-07-2013', 'dd-mm-yyyy'), BZB_SQ_ADDRESS_ID.currval, BZB_SQ_COMPANY_ID.currval);
-- Dependant Details for Bayzat Employee 1
INSERT INTO BZB_T_DEPENDANT (DEPENDANT_ID, FIRST_NAME, LAST_NAME, GENDER, AGE, DATE_OF_BIRTH, CONTACT_NUMBER, EMAIL, RELATIONSHIP, ADDRESS_ID, EMPLOYEE_ID) 
VALUES (BZB_SQ_DEPENDANT_ID.nextval, 'Yasmin', 'Mohamed', 'Female', 27, to_date('22-04-1991', 'dd-mm-yyyy'), '+97159759092', 'yasmin.m@gmail.com', 'Spouse', BZB_SQ_ADDRESS_ID.currval, BZB_SQ_EMPLOYEE_ID.currval);

-- Address Details for Bayzat Employee 2
INSERT INTO BZB_T_ADDRESS (ADDRESS_ID, BUILDING_NAME, UNIT_NUMBER, STREET_ADDRESS, TOWN, CITY, STATE, COUNTRY, POSTAL_CODE) 
VALUES (BZB_SQ_ADDRESS_ID.nextval, 'Building 12', '08-27', '33rd Street Shabia10', 'Shabia', 'Abu Dhabi', 'Abu Dhabi', 'United Arab Emirates', '552370');
-- Employee Details for Bayzat Employee 2
INSERT INTO BZB_T_EMPLOYEE (EMPLOYEE_ID, EMPLOYEE_CODE, FIRST_NAME, LAST_NAME, GENDER, AGE, DATE_OF_BIRTH, CONTACT_NUMBER, EMAIL, CURRENT_SALARY, DATE_OF_JOINING, ADDRESS_ID, COMPANY_ID) 
VALUES (BZB_SQ_EMPLOYEE_ID.nextval, 'BZB-10153', 'Yunus', 'Mohamed', 'Male', 27, to_date('28-07-1990', 'dd-mm-yyyy'), '+97123498734', 'yunus.m@bayzat.com', '10000.00', to_date('01-05-2017', 'dd-mm-yyyy'), BZB_SQ_ADDRESS_ID.currval, BZB_SQ_COMPANY_ID.currval);
-- Dependant 1 Details for Bayzat Employee 2
INSERT INTO BZB_T_DEPENDANT (DEPENDANT_ID, FIRST_NAME, LAST_NAME, GENDER, AGE, DATE_OF_BIRTH, CONTACT_NUMBER, EMAIL, RELATIONSHIP, ADDRESS_ID, EMPLOYEE_ID) 
VALUES (BZB_SQ_DEPENDANT_ID.nextval, 'Sumaiya', 'Yunus', 'Female', 25, to_date('22-04-1993', 'dd-mm-yyyy'), '+97159873492', 'sumaiya.y@gmail.com', 'Spouse', BZB_SQ_ADDRESS_ID.currval, BZB_SQ_EMPLOYEE_ID.currval);
-- Dependant 2 Details for Bayzat Employee 2
INSERT INTO BZB_T_DEPENDANT (DEPENDANT_ID, FIRST_NAME, LAST_NAME, GENDER, AGE, DATE_OF_BIRTH, CONTACT_NUMBER, EMAIL, RELATIONSHIP, ADDRESS_ID, EMPLOYEE_ID) 
VALUES (BZB_SQ_DEPENDANT_ID.nextval, 'Liyana', 'Yunus', 'Female', 2, to_date('26-05-2016', 'dd-mm-yyyy'), '+97123498734', 'yunus.m@bayzat.com', 'Child', BZB_SQ_ADDRESS_ID.currval, BZB_SQ_EMPLOYEE_ID.currval);



-- Address Details for Cognizant
INSERT INTO BZB_T_ADDRESS (ADDRESS_ID, BUILDING_NAME, UNIT_NUMBER, STREET_ADDRESS, TOWN, CITY, STATE, COUNTRY, POSTAL_CODE) 
VALUES (BZB_SQ_ADDRESS_ID.nextval, 'Plaza8@CBP', '07-01/06', '1 Changi Business Park Central', 'Changi Biz Hub', 'Singapore', 'Singapore', 'Singapore', '486025');
-- Company Details for Cognizant
INSERT INTO BZB_T_COMPANY (COMPANY_ID, NAME, REGISTRATION_NUMBER, CONTACT_NUMBER, EMAIL, WEBSITE, ADDRESS_ID) 
VALUES (BZB_SQ_COMPANY_ID.nextval, 'Cognizant', 'Cts-1994', '+6568124051', 'inquiry@cognizant.com', 'http://www.cognizant.com', BZB_SQ_ADDRESS_ID.currval);

-- Address Details for Cognizant Employee 1
INSERT INTO BZB_T_ADDRESS (ADDRESS_ID, BUILDING_NAME, UNIT_NUMBER, STREET_ADDRESS, TOWN, CITY, STATE, COUNTRY, POSTAL_CODE) 
VALUES (BZB_SQ_ADDRESS_ID.nextval, '126A', '13-333', 'Edgedale Plains', 'Punggol', 'Singapore', 'Singapore', 'Singapore', '821126');
-- Employee Details for Cognizant Employee 1
INSERT INTO BZB_T_EMPLOYEE (EMPLOYEE_ID, EMPLOYEE_CODE, FIRST_NAME, LAST_NAME, GENDER, AGE, DATE_OF_BIRTH, CONTACT_NUMBER, EMAIL, CURRENT_SALARY, DATE_OF_JOINING, ADDRESS_ID, COMPANY_ID) 
VALUES (BZB_SQ_EMPLOYEE_ID.nextval, 'CTS-257520', 'Mohamed Yusuff', 'Mohamed Sultan', 'Male', 31, to_date('08-12-1986', 'dd-mm-yyyy'), '+6597197197', 'yusuff.m@cognizant.com', '8000.00', to_date('28-10-2010', 'dd-mm-yyyy'), BZB_SQ_ADDRESS_ID.currval, BZB_SQ_COMPANY_ID.currval);
-- Dependant 1 Details for Cognizant Employee 1
INSERT INTO BZB_T_DEPENDANT (DEPENDANT_ID, FIRST_NAME, LAST_NAME, GENDER, AGE, DATE_OF_BIRTH, CONTACT_NUMBER, EMAIL, RELATIONSHIP, ADDRESS_ID, EMPLOYEE_ID)  
VALUES (BZB_SQ_DEPENDANT_ID.nextval, 'Hasina', 'Yusuff', 'Female', 25, to_date('26-07-1992', 'dd-mm-yyyy'), '+658033557', 'hasina.y@gmail.com', 'Spouse', BZB_SQ_ADDRESS_ID.currval, BZB_SQ_EMPLOYEE_ID.currval);
-- Dependant 2 Details for Cognizant Employee 1
INSERT INTO BZB_T_DEPENDANT (DEPENDANT_ID, FIRST_NAME, LAST_NAME, GENDER, AGE, DATE_OF_BIRTH, CONTACT_NUMBER, EMAIL, RELATIONSHIP, ADDRESS_ID, EMPLOYEE_ID) 
VALUES (BZB_SQ_DEPENDANT_ID.nextval, 'Yashfeen', 'Yusuff', 'Female', 2, to_date('08-08-2017', 'dd-mm-yyyy'), '+6597197197', 'yusuff.m@cognizant.com', 'Child', BZB_SQ_ADDRESS_ID.currval, BZB_SQ_EMPLOYEE_ID.currval);

-- Address Details for Cognizant Employee 2
INSERT INTO BZB_T_ADDRESS (ADDRESS_ID, BUILDING_NAME, UNIT_NUMBER, STREET_ADDRESS, TOWN, CITY, STATE, COUNTRY, POSTAL_CODE) 
VALUES (BZB_SQ_ADDRESS_ID.nextval, '132', '16-22', 'Edgedale Plains', 'Punggol', 'Singapore', 'Singapore', 'Singapore', '820132');
-- Employee Details for Cognizant Employee 2
INSERT INTO BZB_T_EMPLOYEE (EMPLOYEE_ID, EMPLOYEE_CODE, FIRST_NAME, LAST_NAME, GENDER, AGE, DATE_OF_BIRTH, CONTACT_NUMBER, EMAIL, CURRENT_SALARY, DATE_OF_JOINING, ADDRESS_ID, COMPANY_ID) 
VALUES (BZB_SQ_EMPLOYEE_ID.nextval, 'CTS-527570', 'Mohamed Yakeen', 'Mohamed', 'Male', 25, to_date('08-08-1992', 'dd-mm-yyyy'), '+6595773308', 'mohamedyakeen.m@cognizant.com', '5500.00', to_date('08-08-2017', 'dd-mm-yyyy'), BZB_SQ_ADDRESS_ID.currval, BZB_SQ_COMPANY_ID.currval);