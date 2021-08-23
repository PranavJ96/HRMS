-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 07, 2021 at 11:21 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 7.3.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hrms`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `AddPerson` (IN `userID` INT, IN `firstName` VARCHAR(255), IN `middleName` VARCHAR(255), IN `lastName` VARCHAR(255), IN `fatherName` VARCHAR(255), IN `motherName` VARCHAR(255), IN `spouseName` VARCHAR(255), IN `dOB` DATE, IN `qualification` VARCHAR(255), IN `bloodGroup` VARCHAR(255), IN `phoneNumber` VARCHAR(255), IN `doorNumber` VARCHAR(255), IN `streetNumber` VARCHAR(255), IN `area` VARCHAR(255), IN `city` VARCHAR(255), IN `postCode` VARCHAR(255), IN `country` VARCHAR(255), IN `personalEmail` VARCHAR(255), IN `nationalID` VARCHAR(255), IN `bankAccount` VARCHAR(255), IN `candidateID` INT, IN `officialEmail` VARCHAR(255), IN `projectID` INT, IN `departmentID` INT, IN `dOJ` DATE, IN `roleLevel` INT, IN `designation` VARCHAR(255), IN `workType` INT, IN `taxID` VARCHAR(255), IN `locationID` INT, IN `companyName` VARCHAR(255), IN `contractEnd` DATE, IN `employeeTypeID` INT, IN `gender` VARCHAR(255))  BEGIN
DECLARE EMPLOYEEID INT;
DECLARE ADDRESSID INT;
DECLARE ATTENDANCEID INT;
INSERT INTO `attendance` ( `work_duration`, `default_work_duration`, `overtime_duration`, `is_active`, `created_date`, `modified_date`, `modified_by`, `total_leave`, `availed_leave`) VALUES ( NULL, '8', NULL, '1', CURRENT_DATE, CURRENT_DATE, userID, NULL, NULL);
SELECT MAX(attendance_id) FROM attendance INTO ATTENDANCEID;
IF(employeeTypeID = 1)THEN


INSERT INTO `employee` (`employee_id`, `official_email_address`, `project_id`, `department_id`, `date_of_joining`, `job_role_level`, `designation`, `wt_id`,  `tax_id`, `location_id`, `is_active`, `created_date`, `modified_by`,`attendance_id`) VALUES (NULL, officialEmail, projectID, departmentID, dOJ, roleLevel, designation, workType, taxID, locationID,1,CURRENT_TIMESTAMP ,  userID,ATTENDANCEID);
 SELECT MAX(employee_id) FROM employee INTO EMPLOYEEID;
 
INSERT INTO `address` (`address_id`, `door_number`, `street_name`, `area`, `city`, `postcode`, `country`, `is_active`, `created_date`, `modified_date`, `modified_by`) VALUES (NULL, doorNumber, streetNumber, area, city, postCode, country, '1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, userID);
SELECT MAX(address_id) FROM address INTO ADDRESSID;

INSERT INTO `person` (`person_id`, `f_name`, `m_name`, `l_name`, `father_name`, `mother_name`, `spouse_name`, `date_of_birth`, `qualification`, `blood_group`, `address_id`, `phone_number`, `personal_email_address`, `national_identity_number`, `bank_account_details`, `candidate_id`, `is_active`, `created_date`, `modified_date`, `modified_by`,`gender`) VALUES (CONCAT('EM',EMPLOYEEID), firstName, middleName, lastName, fatherName, motherName, spouseName, dOB, qualification, bloodGroup, ADDRESSID, phoneNumber, personalEmail, nationalID, bankAccount, candidateID, '1', CURRENT_TIMESTAMP,CURRENT_TIMESTAMP , userID,gender);
INSERT INTO `payroll` ( `person_id`, `attendance_id`, `modified_by`) VALUES (CONCAT('EM',EMPLOYEEID), ATTENDANCEID, userID);
END IF;
IF(employeeTypeID = 2)THEN
INSERT INTO `consultant` (`consultant_id`, `official_email_address`, `project_id`, `department_id`, `date_of_joining`, `company_name`, `designation`, `wt_id`, `tax_id`, `location_id`, `is_active`, `created_date`,  `modified_by`,`attendance_id`) VALUES (NULL, officialEmail, projectID, departmentID, dOJ, companyName, designation, workType, taxID, locationID,1,CURRENT_TIMESTAMP ,  userID,ATTENDANCEID);
 SELECT MAX(consultant_id) FROM consultant INTO EMPLOYEEID;
INSERT INTO `address` (`address_id`, `door_number`, `street_name`, `area`, `city`, `postcode`, `country`, `is_active`, `created_date`, `modified_date`, `modified_by`) VALUES (NULL, doorNumber, streetNumber, area, city, postCode, country, '1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, userID);
SELECT MAX(address_id) FROM address INTO ADDRESSID;
INSERT INTO `person` (`person_id`, `f_name`, `m_name`, `l_name`, `father_name`, `mother_name`, `spouse_name`, `date_of_birth`, `qualification`, `blood_group`, `address_id`, `phone_number`, `personal_email_address`, `national_identity_number`, `bank_account_details`, `candidate_id`, `is_active`, `created_date`, `modified_date`, `modified_by`,`gender`) VALUES (CONCAT('CS',EMPLOYEEID), firstName, middleName, lastName, fatherName, motherName, spouseName, dOB, qualification, bloodGroup, ADDRESSID, phoneNumber, personalEmail, nationalID, bankAccount, candidateID, '1', CURRENT_TIMESTAMP,CURRENT_TIMESTAMP , userID,gender);
INSERT INTO `payroll` ( `person_id`, `attendance_id`, `modified_by`) VALUES (CONCAT('CS',EMPLOYEEID), ATTENDANCEID, userID);
END IF;
IF(employeeTypeID = 3)THEN
INSERT INTO `contractor` (`contract_id`, `official_email_address`, `project_id`, `department_id`, `date_of_joining`, `job_role_level`, `designation`, `wt_id`, `tax_id`, `location_id`, `contract_end_date`, `is_active`, `created_date`, `modified_by`,`attendance_id`)VALUES (NULL, officialEmail, projectID, departmentID, dOJ, roleLevel, designation, workType, taxID, locationID,contractEnd,1,CURRENT_TIMESTAMP ,  userID,ATTENDANCEID);
 SELECT MAX(contract_id) FROM contractor INTO EMPLOYEEID;
INSERT INTO `address` (`address_id`, `door_number`, `street_name`, `area`, `city`, `postcode`, `country`, `is_active`, `created_date`, `modified_date`, `modified_by`) VALUES (NULL, doorNumber, streetNumber, area, city, postCode, country, '1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, userID);
SELECT MAX(address_id) FROM address INTO ADDRESSID;
INSERT INTO `person` (`person_id`, `f_name`, `m_name`, `l_name`, `father_name`, `mother_name`, `spouse_name`, `date_of_birth`, `qualification`, `blood_group`, `address_id`, `phone_number`, `personal_email_address`, `national_identity_number`, `bank_account_details`, `candidate_id`, `is_active`, `created_date`, `modified_date`, `modified_by`,`gender`) VALUES (CONCAT('CT',EMPLOYEEID), firstName, middleName, lastName, fatherName, motherName, spouseName, dOB, qualification, bloodGroup, ADDRESSID, phoneNumber, personalEmail, nationalID, bankAccount, candidateID, '1', CURRENT_TIMESTAMP,CURRENT_TIMESTAMP , userID,gender);
INSERT INTO `payroll` ( `person_id`, `attendance_id`, `modified_by`) VALUES (CONCAT('CT',EMPLOYEEID), ATTENDANCEID, userID);
END IF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `DeletePersonDetails` (IN `userID` INT, IN `personID` VARCHAR(255), IN `modifiedDate` DATE)  BEGIN
IF(SUBSTRING(personID, 1, 2) = 'EM') THEN
UPDATE attendance SET attendance.is_active = 0, attendance.modified_by = userID WHERE attendance.attendance_id IN (select MAX(employee.attendance_id) from employee where employee.employee_id = SUBSTRING(personID, 3, 100));
UPDATE employee SET employee.modified_date = modifiedDate, EMPLOYEE.is_active = 0, employee.modified_by=userID WHERE employee.employee_id = SUBSTRING(personID, 3, 100);
UPDATE person SET person.modified_date = modifiedDate,PERSON.is_active = 0, person.modified_by = userID WHERE person.person_id = personID;
END IF;
IF(SUBSTRING(personID, 1, 2) = 'CT') THEN
UPDATE attendance SET attendance.is_active = 0, attendance.modified_by = userID WHERE attendance.attendance_id IN (select MAX(contractor.attendance_id) from contractor where contractor.contract_id = SUBSTRING(personID, 3, 100));
UPDATE contractor SET contractor.modified_date = modifiedDate,contractor.is_active = 0, contractor.modified_by=userID  WHERE contractor.contract_id = SUBSTRING(personID, 3, 100);
UPDATE person SET person.modified_date = modifiedDate,person.modified_by=userID, PERSON.is_active = 0 WHERE person.person_id = personID;
END IF;
IF(SUBSTRING(personID, 1, 2) = 'CS') THEN
UPDATE attendance SET attendance.is_active = 0, attendance.modified_by = userID WHERE attendance.attendance_id IN (select MAX(consultant.attendance_id) from consultant where consultant.consultant_id = SUBSTRING(personID, 3, 100));
UPDATE consultant SET consultant.modified_date = modifiedDate,consultant.modified_by=userID, consultant.is_active = 0 WHERE consultant.consultant_id = SUBSTRING(personID, 3, 100);
UPDATE person SET person.modified_date = modifiedDate,person.modified_by=userID, PERSON.is_active = 0 WHERE person.person_id = personID;
END IF;
UPDATE address SET address.is_active = 0 WHERE address.address_id = (SELECT address_id FROM person WHERE person.person_id = personID);
UPDATE payroll SET payroll.modified_date = modifiedDate, payroll.is_active = 0, payroll.modified_by=userID WHERE payroll.person_id = personID;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `FilterPersonalDetails` (IN `accessLevel` INT, IN `userID` INT, IN `personID` VARCHAR(255))  BEGIN
DECLARE DEPID INT;
SELECT department_id from employee E JOIN user U on E.employee_id = U.employee_id where U.user_id = userID Into DEPID;
IF (accessLevel = 1) THEN
select person_id as Person_ID, f_name as FirstName, m_name as MiddleName, l_name as LastName,father_name as FatherName, mother_name as MotherName,spouse_Name as SpouseName, date_of_birth as DOB, qualification as Qualification, blood_group as BloodGroup, address_id as AddressID, phone_number as PhoneNumber, personal_email_address as PersonalMailID, national_identity_number as NationalID, bank_account_details as BankDetails, candidate_id As CandidateID, E.official_email_address as OfficialEmail,E.project_id as Project_ID,E.department_id as DepartmentID,E.date_of_joining as DOJ,null as ContractEndDate, E.job_role_level as RoleLevel, E.designation as Designation, E.wt_id as WorkTypeID,E.attendance_id as AttendanceId, E.tax_id as TaxId,null as CompanyName, E.location_id as LocationID from person P JOIN employee E on P.person_id = (SELECT CONCAT('EM',E.employee_id)) where E.is_active = 1 && P.is_active = 1 && P.person_id = personID
UNION ALL
select person_id as Person_ID, f_name as FirstName, m_name as MiddleName, l_name as LastName,father_name as FatherName, mother_name as MotherName,spouse_Name as SpouseName, date_of_birth as DOB, qualification as Qualification, blood_group as BloodGroup,address_id as AddressID, phone_number as PhoneNumber, personal_email_address as PersonalMailID, national_identity_number as NationalID, bank_account_details as BankDetails, candidate_id As CandidateID, C.official_email_address as OfficialEmail,C.project_id as Project_ID,C.department_id as DepartmentID,C.date_of_joining as DOJ, contract_end_date as ContractEndDate, C.job_role_level as RoleLevel, C.designation as Designation, C.wt_id as WorkTypeID,C.attendance_id as AttendanceId, C.tax_id as TaxId,null as CompanyName,C.location_id as LocationID from person P JOIN contractor C on P.person_id = (SELECT CONCAT('CT',C.contract_id)) where C.is_active = 1 && P.is_active = 1 && P.person_id = personID
UNION ALL
select person_id as Person_ID, f_name as FirstName, m_name as MiddleName, l_name as LastName,father_name as FatherName, mother_name as MotherName,spouse_Name as SpouseName, date_of_birth as DOB, qualification as Qualification, blood_group as BloodGroup,address_id as AddressID, phone_number as PhoneNumber, personal_email_address as PersonalMailID, national_identity_number as NationalID, bank_account_details as BankDetails, candidate_id As CandidateID, CS.official_email_address as OfficialEmail,CS.project_id as Project_ID,CS.department_id as DepartmentID,CS.date_of_joining as DOJ, null as ContractEndDate,  null as RoleLevel, CS.designation as Designation, CS.wt_id as WorkTypeID,CS.attendance_id as AttendanceId, CS.tax_id as TaxId,company_name as CompanyName,CS.location_id as LocationID from person P JOIN consultant CS on P.person_id = (SELECT CONCAT('CS',CS.consultant_id)) where CS.is_active = 1 && P.person_id = personID && P.is_active = 1;
END IF;
IF (accessLevel = 2) THEN
select person_id as Person_ID, f_name as FirstName, m_name as MiddleName, l_name as LastName,father_name as FatherName, mother_name as MotherName,spouse_Name as SpouseName, date_of_birth as DOB, qualification as Qualification, blood_group as BloodGroup, address_id as AddressID, phone_number as PhoneNumber, personal_email_address as PersonalMailID, national_identity_number as NationalID, bank_account_details as BankDetails, candidate_id As CandidateID, E.official_email_address as OfficialEmail,E.project_id as Project_ID,E.department_id as DepartmentID,E.date_of_joining as DOJ,null as ContractEndDate, E.job_role_level as RoleLevel, E.designation as Designation, E.wt_id as WorkTypeID,E.attendance_id as AttendanceId, E.tax_id as TaxId,null as CompanyName, E.location_id as LocationID from person P JOIN employee E on P.person_id = (SELECT CONCAT('EM',E.employee_id)) WHERE E.department_id = DEPID && E.is_active = 1 && P.is_active = 1 && P.person_id = personID
UNION ALL
select person_id as Person_ID, f_name as FirstName, m_name as MiddleName, l_name as LastName,father_name as FatherName, mother_name as MotherName,spouse_Name as SpouseName, date_of_birth as DOB, qualification as Qualification, blood_group as BloodGroup,address_id as AddressID, phone_number as PhoneNumber, personal_email_address as PersonalMailID, national_identity_number as NationalID, bank_account_details as BankDetails, candidate_id As CandidateID, C.official_email_address as OfficialEmail,C.project_id as Project_ID,C.department_id as DepartmentID,C.date_of_joining as DOJ, contract_end_date as ContractEndDate, C.job_role_level as RoleLevel, C.designation as Designation, C.wt_id as WorkTypeID,C.attendance_id as AttendanceId, C.tax_id as TaxId,null as CompanyName,C.location_id as LocationID from person P JOIN contractor C on P.person_id = (SELECT CONCAT('CT',C.contract_id)) WHERE C.department_id = DEPID && C.is_active = 1 && P.is_active = 1 && P.person_id = personID
UNION ALL
select person_id as Person_ID, f_name as FirstName, m_name as MiddleName, l_name as LastName,father_name as FatherName, mother_name as MotherName,spouse_Name as SpouseName, date_of_birth as DOB, qualification as Qualification, blood_group as BloodGroup,address_id as AddressID, phone_number as PhoneNumber, personal_email_address as PersonalMailID, national_identity_number as NationalID, bank_account_details as BankDetails, candidate_id As CandidateID, CS.official_email_address as OfficialEmail,CS.project_id as Project_ID,CS.department_id as DepartmentID,CS.date_of_joining as DOJ, null as ContractEndDate,  null as RoleLevel, CS.designation as Designation, CS.wt_id as WorkTypeID,CS.attendance_id as AttendanceId, CS.tax_id as TaxId,company_name as CompanyName,CS.location_id as LocationID from person P JOIN consultant CS on P.person_id = (SELECT CONCAT('CS',CS.consultant_id)) WHERE CS.department_id = DEPID && CS.is_active = 1 && P.is_active = 1 && P.person_id = personID;
END IF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetPersonDetails` (IN `accessLevel` INT, IN `userID` INT)  BEGIN
DECLARE DEPID INT;
SELECT department_id from employee E JOIN user U on E.employee_id = U.employee_id where U.user_id = userID Into DEPID;
IF (accessLevel = 1) THEN
select person_id as Person_ID, f_name as FirstName, m_name as MiddleName, l_name as LastName,father_name as FatherName, mother_name as MotherName,spouse_Name as SpouseName, date_of_birth as DOB,gender as Gender, qualification as Qualification, blood_group as BloodGroup, address_id as AddressID, phone_number as PhoneNumber, personal_email_address as PersonalMailID, national_identity_number as NationalID, bank_account_details as BankDetails, candidate_id As CandidateID, E.official_email_address as OfficialEmail,E.project_id as Project_ID,E.department_id as DepartmentID,E.date_of_joining as DOJ,null as ContractEndDate, E.job_role_level as RoleLevel, E.designation as Designation, E.wt_id as WorkTypeID,E.attendance_id as AttendanceId, E.tax_id as TaxId,null as CompanyName, E.location_id as LocationID from person P JOIN employee E on P.person_id = (SELECT CONCAT('EM',E.employee_id)) where E.is_active = 1 && P.is_active = 1
UNION ALL
select person_id as Person_ID, f_name as FirstName, m_name as MiddleName, l_name as LastName,father_name as FatherName, mother_name as MotherName,spouse_Name as SpouseName, date_of_birth as DOB,gender as Gender, qualification as Qualification, blood_group as BloodGroup,address_id as AddressID, phone_number as PhoneNumber, personal_email_address as PersonalMailID, national_identity_number as NationalID, bank_account_details as BankDetails, candidate_id As CandidateID, C.official_email_address as OfficialEmail,C.project_id as Project_ID,C.department_id as DepartmentID,C.date_of_joining as DOJ, contract_end_date as ContractEndDate, C.job_role_level as RoleLevel, C.designation as Designation, C.wt_id as WorkTypeID,C.attendance_id as AttendanceId, C.tax_id as TaxId,null as CompanyName,C.location_id as LocationID from person P JOIN contractor C on P.person_id = (SELECT CONCAT('CT',C.contract_id)) where C.is_active = 1 && P.is_active = 1
UNION ALL
select person_id as Person_ID, f_name as FirstName, m_name as MiddleName, l_name as LastName,father_name as FatherName, mother_name as MotherName,spouse_Name as SpouseName, date_of_birth as DOB,gender as Gender, qualification as Qualification, blood_group as BloodGroup,address_id as AddressID, phone_number as PhoneNumber, personal_email_address as PersonalMailID, national_identity_number as NationalID, bank_account_details as BankDetails, candidate_id As CandidateID, CS.official_email_address as OfficialEmail,CS.project_id as Project_ID,CS.department_id as DepartmentID,CS.date_of_joining as DOJ, null as ContractEndDate,  null as RoleLevel, CS.designation as Designation, CS.wt_id as WorkTypeID,CS.attendance_id as AttendanceId, CS.tax_id as TaxId,company_name as CompanyName,CS.location_id as LocationID from person P JOIN consultant CS on P.person_id = (SELECT CONCAT('CS',CS.consultant_id)) where CS.is_active = 1 && P.is_active = 1;
END IF;
IF (accessLevel = 2) THEN
select person_id as Person_ID, f_name as FirstName, m_name as MiddleName, l_name as LastName,father_name as FatherName, mother_name as MotherName,spouse_Name as SpouseName, date_of_birth as DOB,gender as Gender, qualification as Qualification, blood_group as BloodGroup, address_id as AddressID, phone_number as PhoneNumber, personal_email_address as PersonalMailID, national_identity_number as NationalID, bank_account_details as BankDetails, candidate_id As CandidateID, E.official_email_address as OfficialEmail,E.project_id as Project_ID,E.department_id as DepartmentID,E.date_of_joining as DOJ,null as ContractEndDate, E.job_role_level as RoleLevel, E.designation as Designation, E.wt_id as WorkTypeID,E.attendance_id as AttendanceId, E.tax_id as TaxId,null as CompanyName, E.location_id as LocationID from person P JOIN employee E on P.person_id = (SELECT CONCAT('EM',E.employee_id)) WHERE E.department_id = DEPID && E.is_active = 1 && P.is_active = 1
UNION ALL
select person_id as Person_ID, f_name as FirstName, m_name as MiddleName, l_name as LastName,father_name as FatherName, mother_name as MotherName,spouse_Name as SpouseName, date_of_birth as DOB,gender as Gender, qualification as Qualification, blood_group as BloodGroup,address_id as AddressID, phone_number as PhoneNumber, personal_email_address as PersonalMailID, national_identity_number as NationalID, bank_account_details as BankDetails, candidate_id As CandidateID, C.official_email_address as OfficialEmail,C.project_id as Project_ID,C.department_id as DepartmentID,C.date_of_joining as DOJ, contract_end_date as ContractEndDate, C.job_role_level as RoleLevel, C.designation as Designation, C.wt_id as WorkTypeID,C.attendance_id as AttendanceId, C.tax_id as TaxId,null as CompanyName,C.location_id as LocationID from person P JOIN contractor C on P.person_id = (SELECT CONCAT('CT',C.contract_id)) WHERE C.department_id = DEPID && C.is_active = 1 && P.is_active = 1
UNION ALL
select person_id as Person_ID, f_name as FirstName, m_name as MiddleName, l_name as LastName,father_name as FatherName, mother_name as MotherName,spouse_Name as SpouseName, date_of_birth as DOB,gender as Gender, qualification as Qualification, blood_group as BloodGroup,address_id as AddressID, phone_number as PhoneNumber, personal_email_address as PersonalMailID, national_identity_number as NationalID, bank_account_details as BankDetails, candidate_id As CandidateID, CS.official_email_address as OfficialEmail,CS.project_id as Project_ID,CS.department_id as DepartmentID,CS.date_of_joining as DOJ, null as ContractEndDate,  null as RoleLevel, CS.designation as Designation, CS.wt_id as WorkTypeID,CS.attendance_id as AttendanceId, CS.tax_id as TaxId,company_name as CompanyName,CS.location_id as LocationID from person P JOIN consultant CS on P.person_id = (SELECT CONCAT('CS',CS.consultant_id)) WHERE CS.department_id = DEPID && CS.is_active = 1 && P.is_active = 1;
END IF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetSinglePersonDetails` (IN `personID` VARCHAR(255))  BEGIN
IF(SUBSTRING(personID, 1, 2) = 'EM') THEN
select f_name as FirstName, m_name as MiddleName, l_name as LastName,father_name as FatherName, mother_name as MotherName,spouse_Name as SpouseName, date_of_birth as DOB, qualification as Qualification, blood_group as BloodGroup,  phone_number as PhoneNumber, personal_email_address as PersonalMailID, national_identity_number as NationalID, bank_account_details as BankDetails, candidate_id As CandidateID, E.official_email_address as OfficialEmail,E.project_id as Project_ID,E.department_id as DepartmentID,E.date_of_joining as DOJ,null as ContractEndDate, E.job_role_level as RoleLevel, E.designation as Designation, E.wt_id as WorkTypeID,E.attendance_id as AttendanceId, E.tax_id as TaxId,null as CompanyName, E.location_id as LocationID,P.gender as Gender from person P JOIN employee E on P.person_id = (SELECT CONCAT('EM',E.employee_id)) where P.person_id = personID && E.is_active = 1 && P.is_active = 1 ;
END IF;
IF(SUBSTRING(personID, 1, 2) = 'CT') THEN
select f_name as FirstName, m_name as MiddleName, l_name as LastName,father_name as FatherName, mother_name as MotherName,spouse_Name as SpouseName, date_of_birth as DOB, qualification as Qualification, blood_group as BloodGroup, phone_number as PhoneNumber, personal_email_address as PersonalMailID, national_identity_number as NationalID, bank_account_details as BankDetails, candidate_id As CandidateID, C.official_email_address as OfficialEmail,C.project_id as Project_ID,C.department_id as DepartmentID,C.date_of_joining as DOJ, contract_end_date as ContractEndDate, C.job_role_level as RoleLevel, C.designation as Designation, C.wt_id as WorkTypeID,C.attendance_id as AttendanceId, C.tax_id as TaxId,null as CompanyName,C.location_id as LocationID,P.gender as Gender  from person P JOIN contractor C on P.person_id = (SELECT CONCAT('CT',C.contract_id)) where P.person_id = personID && C.is_active = 1 && P.is_active = 1;
END IF;
IF(SUBSTRING(personID, 1, 2) = 'CS') THEN
select f_name as FirstName, m_name as MiddleName, l_name as LastName,father_name as FatherName, mother_name as MotherName,spouse_Name as SpouseName, date_of_birth as DOB, qualification as Qualification, blood_group as BloodGroup, phone_number as PhoneNumber, personal_email_address as PersonalMailID, national_identity_number as NationalID, bank_account_details as BankDetails, candidate_id As CandidateID, CS.official_email_address as OfficialEmail,CS.project_id as Project_ID,CS.department_id as DepartmentID,CS.date_of_joining as DOJ, null as ContractEndDate,  null as RoleLevel, CS.designation as Designation, CS.wt_id as WorkTypeID,CS.attendance_id as AttendanceId, CS.tax_id as TaxId,company_name as CompanyName,CS.location_id as LocationID,P.gender as Gender  from person P JOIN consultant CS on P.person_id = (SELECT CONCAT('CS',CS.consultant_id)) where P.person_id = personID && CS.is_active = 1 && P.is_active = 1;
END IF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Login` (IN `ID` VARCHAR(255), IN `userPass` VARCHAR(255), OUT `valid` BOOLEAN, OUT `accessLevel` INT, OUT `userID` INT)  BEGIN
DECLARE countvalue INT;
DECLARE access_level INT;
DECLARE user_ID INT;
SET valid = false;
SELECT COUNT(*) ,role_id , employee_id FROM user WHERE email_address = ID && password = userPass && is_active = 1 INTO countvalue,access_level,user_ID ;
IF (countvalue > 0) THEN
SET valid = true;
SET accessLevel = access_level;
SET userID = user_ID;
END IF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `test` (IN `accessLevel` INT, IN `userID` INT)  BEGIN
DECLARE DEPID INT;
SELECT DEPID = department_id from employee E JOIN user U on E.employee_id = U.employee_id where U.user_id = userID;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `UpdatePerson` (IN `userID` INT, IN `firstName` VARCHAR(255), IN `middleName` VARCHAR(255), IN `lastName` VARCHAR(255), IN `fatherName` VARCHAR(255), IN `motherName` VARCHAR(255), IN `spouseName` VARCHAR(255), IN `dOB` DATE, IN `qualification` VARCHAR(255), IN `bloodGroup` VARCHAR(255), IN `phoneNumber` VARCHAR(255), IN `personalEmail` VARCHAR(255), IN `nationalID` VARCHAR(255), IN `bankAccount` VARCHAR(255), IN `candidateID` INT, IN `officialEmail` VARCHAR(255), IN `projectID` INT, IN `departmentID` INT, IN `dOJ` DATE, IN `roleLevel` INT, IN `designation` VARCHAR(255), IN `workType` INT, IN `taxID` VARCHAR(255), IN `locationID` INT, IN `companyName` VARCHAR(255), IN `contractEnd` DATE, IN `personID` VARCHAR(255), IN `gender` VARCHAR(255))  BEGIN

IF(SUBSTRING(personID,1,2) = 'EM')THEN
UPDATE employee SET `official_email_address` = officialEmail, `project_id` = projectID, `department_id` = departmentID, `date_of_joining` = dOJ, `job_role_level` = roleLevel, `designation` = designation, `wt_id` = workType,  `tax_id` = taxID, `location_id` = locationID,  `modified_date`= CURRENT_TIMESTAMP, `modified_by` =  userID WHERE employee.employee_id = SUBSTRING(personID,3,100);
UPDATE `person` SET `f_name` = firstName, `m_name`= middleName, `l_name` = lastName, `father_name`=fatherName, `mother_name`=motherName, `spouse_name`=spouseName, `date_of_birth`=dOB, `qualification`=qualification, `blood_group`=bloodGroup, `phone_number`=phoneNumber, `personal_email_address`=personalEmail, `national_identity_number`=nationalID, `bank_account_details`=bankAccount, `candidate_id`=candidateID,  `modified_date`= CURRENT_TIMESTAMP, `modified_by`=userID,person.gender = gender where person.person_id = personID;
END IF;

IF(SUBSTRING(personID,1,2) = 'CS')THEN
UPDATE `consultant`SET `official_email_address`= officialEmail, `project_id` = projectID, `department_id` = departmentID, `date_of_joining` = dOJ, `company_name` = companyName, `designation` = designation, `wt_id` = workType, `tax_id` = taxID, `location_id` = locationID,`modified_date` =  CURRENT_TIMESTAMP, `modified_by` = userID WHERE consultant.consultant_id = SUBSTRING(personID,3,100);

UPDATE `person` SET `f_name` = firstName, `m_name`= middleName, `l_name` = lastName, `father_name`=fatherName, `mother_name`=motherName, `spouse_name`=spouseName, `date_of_birth`=dOB, `qualification`=qualification, `blood_group`=bloodGroup, `phone_number`=phoneNumber, `personal_email_address`=personalEmail, `national_identity_number`=nationalID, `bank_account_details`=bankAccount, `candidate_id`=candidateID,  `modified_date`= CURRENT_TIMESTAMP, `modified_by`=userID,person.gender = gender WHERE PERSON.person_id = personID;
END IF;

IF(SUBSTRING(personID,1,2) = 'CT')THEN
UPDATE contractor SET `official_email_address`= officialEmail, `project_id` = projectID, `department_id` = departmentID, `date_of_joining` = dOJ, `job_role_level` = roleLevel, `designation` = designation, `wt_id` = workType, `tax_id` = taxID, `location_id` = locationID,contractor.contract_end_date = contractEnd,`modified_date` = CURRENT_TIMESTAMP, `modified_by` = userID WHERE contractor.contract_id= SUBSTRING(personID,3,100);

UPDATE `person` SET `f_name` = firstName, `m_name`= middleName, `l_name` = lastName, `father_name`=fatherName, `mother_name`=motherName, `spouse_name`=spouseName, `date_of_birth`=dOB, `qualification`=qualification, `blood_group`=bloodGroup, `address_id`=ADDRESSID, `phone_number`=phoneNumber, `personal_email_address`=personalEmail, `national_identity_number`=nationalID, `bank_account_details`=bankAccount, `candidate_id`=candidateID,  `modified_date`= CURRENT_TIMESTAMP, `modified_by`=userID,person.gender = gender WHERE person.person_id = personID;
END IF;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `access_level`
--

CREATE TABLE `access_level` (
  `role_id` int(11) NOT NULL,
  `user_role_name` varchar(255) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime NOT NULL,
  `modified_by` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `access_level`
--

INSERT INTO `access_level` (`role_id`, `user_role_name`, `is_active`, `created_date`, `modified_date`, `modified_by`) VALUES
(1, 'Super Admin', 1, '2021-06-04 00:30:40', '2021-06-04 00:30:40', ''),
(2, 'Admin', 1, '2021-06-04 00:30:40', '2021-06-04 00:30:40', '');

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE `address` (
  `address_id` int(11) NOT NULL,
  `door_number` varchar(255) NOT NULL,
  `street_name` varchar(255) NOT NULL,
  `area` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `postcode` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime NOT NULL,
  `modified_by` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`address_id`, `door_number`, `street_name`, `area`, `city`, `postcode`, `country`, `is_active`, `created_date`, `modified_date`, `modified_by`) VALUES
(1, 'SEF', 'SEF', 'FSEF', 'SEF', 'FSE', 'FSE', 0, '2021-06-05 21:21:17', '2021-06-05 21:21:17', 'FSEF'),
(2, '12', 'sef', 'sef', 'sef', 'sef', 'sef', 1, '2021-06-05 22:24:02', '2021-06-07 00:00:00', '1'),
(3, 'sef', 'sef', 'sefsef', 'sef', 'sef', 'sefs', 1, '2021-06-06 00:24:06', '2021-06-06 00:24:06', '1'),
(4, 'sef', 'sef', 'sefsef', 'sef', 'sef', 'sefs', 1, '2021-06-06 00:24:10', '2021-06-06 00:24:10', '1'),
(5, 'sef', 'sef', 'drg', 'grdg', 'edsef', 'sefsef', 1, '2021-06-06 00:25:56', '2021-06-06 00:25:56', '1'),
(6, 'sef', 'sef', 'sefsef', 'sef', 'sef', 'sefs', 1, '2021-06-06 00:27:47', '2021-06-06 00:27:47', '1'),
(7, 'sef', 'sefsef', 'sef', 'sefs', 'ef', 'ef', 1, '2021-06-06 00:32:31', '2021-06-06 00:32:31', '1'),
(8, 'sef', 'sefsef', 'sef', 'sefs', 'ef', 'ef', 1, '2021-06-06 00:32:35', '2021-06-06 00:32:35', '1'),
(9, 'fsef', 'sef', 'sef', 'sef', 'sef', 'sef', 1, '2021-06-06 00:34:32', '2021-06-06 00:34:32', '1'),
(10, 'sef', 'sefsef', 'sef', 'sefs', 'ef', 'ef', 1, '2021-06-06 00:34:48', '2021-06-06 00:34:48', '1'),
(11, 'sef', 'sefse', 'sef', 'sefs', 'efs', 'efs', 1, '2021-06-06 00:36:53', '2021-06-06 00:36:53', '1'),
(12, 'sefse', 'fsef', 'sef', 'sef', 'sef', 'sef', 1, '2021-06-06 21:44:46', '2021-06-06 21:44:46', '1'),
(13, 'sefse', 'fsef', 'sef', 'sef', 'sef', 'sef', 1, '2021-06-06 21:44:54', '2021-06-06 21:44:54', '1'),
(14, 'sefse', 'fsef', 'sef', 'sef', 'sef', 'sef', 1, '2021-06-06 21:49:55', '2021-06-06 21:49:55', '1'),
(15, '', 'dw', 'd', 'wd', 'wd', 'wd', 1, '2021-06-06 21:52:21', '2021-06-06 21:52:21', '1'),
(16, 'test', 'set', 'set', 'est', 'tse', 'tsets', 1, '2021-06-07 11:27:30', '2021-06-07 11:27:30', '1'),
(17, 'sef', 'sefs', 'ef', 'sefs', 'efs', 'efse', 0, '2021-06-07 11:55:16', '2021-06-07 11:55:16', '1'),
(18, 'drg', 'drg', 'gdr', 'dg', 'drg', 'drg', 1, '2021-06-07 12:08:59', '2021-06-07 12:08:59', '1'),
(19, 'sef', 'sef', 'esf', 'ef', 'efs', 'ef', 0, '2021-06-07 12:33:40', '2021-06-07 12:33:40', '1'),
(20, 'sef', 'sef', 'sefsef', 'sef', 'sef', 'sef', 0, '2021-06-07 12:58:14', '2021-06-07 12:58:14', '1'),
(21, 'sef', 'sef', 'sef', 'sef', 'sef', 'esf', 1, '2021-06-07 13:34:05', '2021-06-07 13:34:05', '1'),
(22, 'sef', 'sef', 'sef', 'sef', 'sef', 'esf', 0, '2021-06-07 13:35:50', '2021-06-07 13:35:50', '1');

-- --------------------------------------------------------

--
-- Table structure for table `attendance`
--

CREATE TABLE `attendance` (
  `attendance_id` int(11) NOT NULL,
  `work_duration_h` double DEFAULT NULL,
  `default_work_duration_h` double NOT NULL DEFAULT 8,
  `overtime_duration_h` double DEFAULT NULL,
  `total_leaves` double DEFAULT NULL,
  `availed_leaves` double DEFAULT NULL,
  `used_leaves` int(11) NOT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT 1,
  `created_date` datetime NOT NULL DEFAULT current_timestamp(),
  `modified_date` datetime DEFAULT NULL,
  `modified_by` varchar(255) NOT NULL DEFAULT 'Admin'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `attendance`
--

INSERT INTO `attendance` (`attendance_id`, `work_duration_h`, `default_work_duration_h`, `overtime_duration_h`, `total_leaves`, `availed_leaves`, `used_leaves`, `is_active`, `created_date`, `modified_date`, `modified_by`) VALUES
(1, NULL, 8, NULL, 1, NULL, 3, 1, '2021-06-06 21:22:35', '2021-06-07 00:00:00', '1'),
(2, NULL, 8, NULL, NULL, NULL, 0, 1, '2021-06-06 00:00:00', '2021-06-06 00:00:00', '1'),
(3, NULL, 8, NULL, NULL, NULL, 0, 1, '2021-06-06 00:00:00', '2021-06-06 00:00:00', '1'),
(4, NULL, 8, NULL, NULL, NULL, 0, 1, '2021-06-06 00:00:00', '2021-06-06 00:00:00', '1'),
(5, NULL, 8, NULL, NULL, NULL, 0, 1, '2021-06-06 00:00:00', '2021-06-06 00:00:00', '1'),
(6, NULL, 8, NULL, NULL, NULL, 0, 1, '2021-06-07 00:00:00', '2021-06-07 00:00:00', '1'),
(7, NULL, 8, NULL, NULL, NULL, 0, 1, '2021-06-07 00:00:00', '2021-06-07 00:00:00', '1'),
(8, NULL, 8, NULL, NULL, NULL, 0, 1, '2021-06-07 00:00:00', '2021-06-07 00:00:00', '1'),
(9, NULL, 8, NULL, NULL, NULL, 0, 1, '2021-06-07 00:00:00', '2021-06-07 00:00:00', '1'),
(10, NULL, 8, NULL, NULL, NULL, 0, 1, '2021-06-07 00:00:00', '2021-06-07 00:00:00', '1'),
(11, NULL, 8, NULL, NULL, NULL, 0, 1, '2021-06-07 00:00:00', '2021-06-07 00:00:00', '1'),
(12, NULL, 8, NULL, NULL, NULL, 0, 0, '2021-06-07 00:00:00', '2021-06-07 00:00:00', '1'),
(13, NULL, 8, NULL, NULL, NULL, 0, 1, '2021-06-07 00:00:00', '2021-06-07 00:00:00', '1'),
(14, NULL, 8, NULL, NULL, NULL, 0, 0, '2021-06-07 00:00:00', '2021-06-07 00:00:00', '1');

-- --------------------------------------------------------

--
-- Table structure for table `candidate`
--

CREATE TABLE `candidate` (
  `candidate_id` int(11) NOT NULL,
  `f_name` varchar(255) NOT NULL,
  `m_name` varchar(255) NOT NULL,
  `l_name` varchar(255) NOT NULL,
  `father_name` varchar(255) NOT NULL,
  `mother_name` varchar(255) NOT NULL,
  `spouse_name` varchar(255) NOT NULL,
  `date_of_birth` date NOT NULL,
  `qualification` varchar(255) NOT NULL,
  `blood_group` varchar(255) NOT NULL,
  `address_id` int(11) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `personal_email_address` varchar(255) NOT NULL,
  `location_id` int(11) NOT NULL,
  `primary_skills` varchar(255) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT current_timestamp(),
  `modified_date` datetime NOT NULL DEFAULT current_timestamp(),
  `modified_by` varchar(255) NOT NULL,
  `gender` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `candidate`
--

INSERT INTO `candidate` (`candidate_id`, `f_name`, `m_name`, `l_name`, `father_name`, `mother_name`, `spouse_name`, `date_of_birth`, `qualification`, `blood_group`, `address_id`, `phone_number`, `personal_email_address`, `location_id`, `primary_skills`, `is_active`, `created_date`, `modified_date`, `modified_by`, `gender`) VALUES
(1, 'sef', 'sef', 'sefsef', 'sef', 'sef', 'sef', '2020-11-11', 'sef', 'sef', 2, 'sef', 'sef', 3, 'sef', 1, '2021-06-05 00:00:00', '2021-06-05 23:24:15', '1', 'sef');

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `client_id` int(11) NOT NULL,
  `client_name` varchar(255) NOT NULL,
  `location_id` int(11) NOT NULL,
  `is_active` tinyint(1) DEFAULT 1,
  `created_date` datetime NOT NULL DEFAULT current_timestamp(),
  `modified_date` datetime NOT NULL DEFAULT current_timestamp(),
  `modified_by` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`client_id`, `client_name`, `location_id`, `is_active`, `created_date`, `modified_date`, `modified_by`) VALUES
(1, 'testset', 1, 0, '2021-06-07 22:08:29', '2021-06-07 22:08:29', 'admin'),
(2, 'testse', 2, 1, '2021-06-07 22:08:29', '2021-06-07 22:08:29', 'admin'),
(3, 'tetstsets', 2, 1, '2021-06-07 00:00:00', '2021-06-07 22:37:44', '1');

-- --------------------------------------------------------

--
-- Table structure for table `consultant`
--

CREATE TABLE `consultant` (
  `consultant_id` int(11) NOT NULL,
  `official_email_address` varchar(255) NOT NULL,
  `project_id` int(11) NOT NULL,
  `department_id` int(11) NOT NULL,
  `date_of_joining` datetime NOT NULL,
  `company_name` varchar(255) NOT NULL,
  `designation` varchar(255) NOT NULL,
  `wt_id` int(11) NOT NULL,
  `attendance_id` int(11) DEFAULT NULL,
  `tax_id` varchar(255) NOT NULL,
  `location_id` int(11) DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime NOT NULL,
  `modified_by` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `consultant`
--

INSERT INTO `consultant` (`consultant_id`, `official_email_address`, `project_id`, `department_id`, `date_of_joining`, `company_name`, `designation`, `wt_id`, `attendance_id`, `tax_id`, `location_id`, `is_active`, `created_date`, `modified_date`, `modified_by`) VALUES
(1, 'sefsef', 223, 1232, '2021-06-05 09:07:25', 'sfedsef', 'sgdvsef', 2, 3, 'fsefsef', 34243, 1, '2021-06-05 09:07:25', '2021-06-05 09:07:25', 'dgfdrgdrg'),
(2, 'drgdrg', 2, 3, '2021-06-05 09:07:25', 'svdf', 'awdawd', 3, 4, '2das', 2, 1, '2021-06-05 09:07:25', '2021-06-05 09:07:25', 'dawd'),
(3, 'dawd', 1, 1, '2021-06-06 00:04:37', 'sefsefsefesf', 'adw', 2, 0, 'sef', 1, 1, '2021-06-06 00:04:37', '2021-06-06 00:04:37', 'sfe'),
(4, 'sefsef', 1, 1, '2021-06-06 00:06:39', 'sefsef', 'sefsef', 1, NULL, 'gssdfs', 1, 1, '2021-06-06 00:06:39', '2021-06-06 00:06:39', 'fse'),
(5, 'sgsef', 0, 0, '9999-01-11 00:00:00', 'sefsef', 'sefsef', 4, NULL, 'sefsefsefsef', 0, 1, '2021-06-06 00:29:00', '0000-00-00 00:00:00', '1'),
(6, 'sefsffesef', 0, 0, '2020-11-11 00:00:00', 'sefsef', 'sfsef', 3, NULL, 'sfesef', 0, 1, '2021-06-06 00:32:31', '0000-00-00 00:00:00', '1'),
(7, 'sefsffesef', 0, 0, '2020-11-11 00:00:00', 'sefsef', 'sfsef', 3, NULL, 'sfesef', 0, 1, '2021-06-06 00:32:35', '0000-00-00 00:00:00', '1'),
(8, 'fsef', 1, 1, '2021-11-11 00:00:00', 'fefse', 'sfe', 234, NULL, 'sefsgbd', 1, 1, '2021-06-06 00:34:32', '0000-00-00 00:00:00', '1'),
(9, 'sefsffesef', 0, 0, '2020-11-11 00:00:00', 'sefsef', 'sfsef', 3, NULL, 'sfesef', 0, 1, '2021-06-06 00:34:48', '0000-00-00 00:00:00', '1'),
(10, 'fsef', 0, 0, '2020-01-12 00:00:00', 'sefesf', 'fsef', 2, NULL, 'fsef', 0, 1, '2021-06-06 00:36:53', '2021-06-06 00:00:00', '1'),
(11, 'sdfdrg', 3, 2, '2020-11-11 00:00:00', 'srgsefsefsef', 'ddrgsrg', 6, NULL, 'sdgsf', 5, 0, '2021-06-07 11:55:16', '2021-06-07 00:00:00', '1');

-- --------------------------------------------------------

--
-- Table structure for table `contractor`
--

CREATE TABLE `contractor` (
  `contract_id` int(11) NOT NULL,
  `official_email_address` varchar(255) NOT NULL,
  `project_id` int(11) NOT NULL,
  `department_id` int(11) NOT NULL,
  `date_of_joining` datetime NOT NULL,
  `job_role_level` int(11) NOT NULL,
  `designation` varchar(255) NOT NULL,
  `wt_id` int(11) NOT NULL,
  `attendance_id` int(11) DEFAULT NULL,
  `tax_id` varchar(255) NOT NULL,
  `location_id` int(11) NOT NULL,
  `contract_end_date` datetime NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime NOT NULL,
  `modified_by` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `contractor`
--

INSERT INTO `contractor` (`contract_id`, `official_email_address`, `project_id`, `department_id`, `date_of_joining`, `job_role_level`, `designation`, `wt_id`, `attendance_id`, `tax_id`, `location_id`, `contract_end_date`, `is_active`, `created_date`, `modified_date`, `modified_by`) VALUES
(1, 'afaefewf', 2, 43, '2021-06-05 09:09:17', 23, 'sfdvsd', 2, 4, 'sfsef', 132, '2021-06-05 09:09:17', 1, '2021-06-05 09:09:17', '2021-06-05 09:09:17', 'sgsf'),
(2, 'esfsef', 23, 42, '2021-06-05 09:09:17', 23, 'fsef', 23, 324, 'sfs', 243, '2021-06-05 09:09:17', 1, '2021-06-05 09:09:17', '2021-06-05 09:09:17', 'svsef'),
(3, 'fsefs', 1, 1, '2021-06-06 00:14:45', 1, 'fesfsef', 1, 1, 'sfef1', 1, '2021-06-06 00:14:45', 1, '2021-06-06 00:14:45', '2021-06-06 00:14:45', 'sfefse'),
(4, 'fsef', 1, 1, '2021-06-06 00:16:14', 1, 'fsef', 1, NULL, 'segfsef', 1, '2021-06-06 00:16:14', 0, '2021-06-06 00:16:14', '2021-06-06 00:16:14', 'sgdr'),
(5, 'sefsef', 0, 0, '2020-11-11 00:00:00', 23, 'sefse', 3, NULL, 'sfefs', 0, '2020-02-11 00:00:00', 1, '2021-06-06 21:32:27', '0000-00-00 00:00:00', '1'),
(6, 'sefsef', 0, 0, '2020-11-11 00:00:00', 23, 'sefse', 3, NULL, 'sfefs', 0, '2020-02-11 00:00:00', 1, '2021-06-06 21:44:46', '0000-00-00 00:00:00', '1'),
(7, 'sefsef', 0, 0, '2020-11-11 00:00:00', 23, 'sefse', 3, NULL, 'sfefs', 0, '2020-02-11 00:00:00', 1, '2021-06-06 21:44:54', '0000-00-00 00:00:00', '1'),
(8, 'sefsef', 0, 0, '2020-11-11 00:00:00', 23, 'sefse', 3, NULL, 'sfefs', 0, '2020-02-11 00:00:00', 1, '2021-06-06 21:49:55', '0000-00-00 00:00:00', '1'),
(9, 'edfaedf', 0, 0, '2020-11-11 00:00:00', 23, 'aeda', 6, NULL, 'fewef', 0, '2020-11-11 00:00:00', 1, '2021-06-06 21:52:21', '0000-00-00 00:00:00', '1'),
(10, 'seser', 0, 0, '2020-11-11 00:00:00', 234, 'efsefsefsef', 6, NULL, 'sefsefes', 0, '2020-11-11 00:00:00', 1, '2021-06-07 11:27:30', '0000-00-00 00:00:00', '1'),
(11, 'sef', 2, 2, '2020-11-11 00:00:00', 23, 'sefsef', 2, NULL, 'sefesf', 4, '2020-11-11 00:00:00', 0, '2021-06-07 12:33:40', '2020-11-11 00:00:00', '1'),
(12, 'dawd', 1, 1, '2020-11-11 00:00:00', 123, 'sefesf', 5, 12, 'fsefse', 4, '2020-11-11 00:00:00', 0, '2021-06-07 12:58:14', '2021-06-07 00:00:00', '1');

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE `department` (
  `department_id` int(11) NOT NULL,
  `department_name` varchar(255) NOT NULL,
  `department_head_id` int(11) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime NOT NULL,
  `modified_by` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`department_id`, `department_name`, `department_head_id`, `is_active`, `created_date`, `modified_date`, `modified_by`) VALUES
(1, 'TEST1', 1, 1, '2021-06-05 13:15:24', '2021-06-05 13:15:24', 'SERSER'),
(2, 'ERWR', 2, 1, '2021-06-05 13:15:24', '2021-06-05 13:15:24', 'SEF');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `employee_id` int(11) NOT NULL,
  `official_email_address` varchar(255) NOT NULL,
  `project_id` int(11) NOT NULL,
  `department_id` int(11) NOT NULL,
  `date_of_joining` datetime NOT NULL,
  `job_role_level` int(11) NOT NULL,
  `designation` varchar(255) NOT NULL,
  `wt_id` int(11) NOT NULL,
  `attendance_id` int(11) NOT NULL,
  `tax_id` varchar(255) NOT NULL,
  `location_id` int(11) NOT NULL,
  `is_active` int(11) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime NOT NULL,
  `modified_by` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`employee_id`, `official_email_address`, `project_id`, `department_id`, `date_of_joining`, `job_role_level`, `designation`, `wt_id`, `attendance_id`, `tax_id`, `location_id`, `is_active`, `created_date`, `modified_date`, `modified_by`) VALUES
(1, 'pranav', 0, 0, '1996-09-13 00:00:00', 12, 'Project Manager', 1, 132, '123', 0, 1, '2021-06-05 09:06:15', '2021-06-07 00:00:00', '1'),
(2, 'rwer', 134, 2, '2021-06-05 09:06:15', 23, 'Project Manager', 12, 12, 'fesf', 123, 1, '2021-06-05 09:06:15', '2021-06-05 09:06:15', 'sefse'),
(3, 'FSEFS', 234, 234, '2021-06-05 21:07:40', 23, 'SFESEF', 234, 234, 'FSESEF', 234, 1, '2021-06-05 21:07:40', '2021-06-05 21:07:40', 'FSEF'),
(4, 'sef', 324, 12323, '2021-11-11 00:00:00', 234, '2021-11-11', 234, 0, '2021-11-11', 1, 1, '2021-06-05 22:24:02', '0000-00-00 00:00:00', '1'),
(5, 'sgsef', 0, 0, '9999-01-11 00:00:00', 12, 'sefsef', 4, 0, 'sefsefsefsef', 0, 1, '2021-06-06 00:24:06', '0000-00-00 00:00:00', '1'),
(6, 'sgsef', 0, 0, '9999-01-11 00:00:00', 12, 'sefsef', 4, 0, 'sefsefsefsef', 0, 1, '2021-06-06 00:24:10', '0000-00-00 00:00:00', '1'),
(7, 'sefsef', 2342, 2342342, '2021-11-11 00:00:00', 23423, 'sefsef', 23423, 0, 'sefsgbd', 2, 1, '2021-06-06 00:25:56', '0000-00-00 00:00:00', '1'),
(8, 'sgsef', 0, 0, '9999-01-11 00:00:00', 12, 'sefsef', 4, 0, 'sefsefsefsef', 0, 1, '2021-06-06 00:27:47', '0000-00-00 00:00:00', '1'),
(9, 'drg', 1, 1, '2021-11-11 00:00:00', 234, 'sefsef', 3, 0, 'gdr', 0, 1, '2021-06-07 12:08:59', '0000-00-00 00:00:00', '1'),
(10, 'sef', 1, 1, '2020-11-11 00:00:00', 23, 'sdv', 1, 13, 'sef', 1, 1, '2021-06-07 13:34:05', '0000-00-00 00:00:00', '1'),
(11, 'sef', 1, 1, '2020-11-11 00:00:00', 23, 'sdv', 1, 14, 'sef', 1, 0, '2021-06-07 13:35:50', '2021-06-07 00:00:00', '1');

-- --------------------------------------------------------

--
-- Table structure for table `employee_exit`
--

CREATE TABLE `employee_exit` (
  `exit_id` int(11) NOT NULL,
  `resignation_date` datetime NOT NULL,
  `notice_period` varchar(255) NOT NULL,
  `separation_date` datetime NOT NULL,
  `employee_id` int(11) NOT NULL,
  `no_dues` tinyint(1) NOT NULL,
  `is_active` tinyint(1) DEFAULT 1,
  `created_date` datetime NOT NULL DEFAULT current_timestamp(),
  `modified_date` datetime NOT NULL DEFAULT current_timestamp(),
  `modified_by` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employee_exit`
--

INSERT INTO `employee_exit` (`exit_id`, `resignation_date`, `notice_period`, `separation_date`, `employee_id`, `no_dues`, `is_active`, `created_date`, `modified_date`, `modified_by`) VALUES
(1, '2020-11-11 00:00:00', '2', '2020-12-12 00:00:00', 1, 1, 0, '2021-06-07 00:00:00', '2021-06-07 23:16:17', '1');

-- --------------------------------------------------------

--
-- Table structure for table `employee_type`
--

CREATE TABLE `employee_type` (
  `employee_type_id` int(11) NOT NULL,
  `employee_type_name` varchar(255) NOT NULL,
  `created_date` date NOT NULL DEFAULT current_timestamp(),
  `created_by` varchar(255) NOT NULL DEFAULT 'Administrator'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employee_type`
--

INSERT INTO `employee_type` (`employee_type_id`, `employee_type_name`, `created_date`, `created_by`) VALUES
(1, 'Employee', '2020-11-11', 'Administrator'),
(2, 'Consultant', '2021-05-06', 'Contractor'),
(3, 'Contractor', '2021-05-06', 'Administrator');

-- --------------------------------------------------------

--
-- Table structure for table `equipment`
--

CREATE TABLE `equipment` (
  `equipment_id` int(11) NOT NULL,
  `asset_id` varchar(255) NOT NULL,
  `asset_name` varchar(255) NOT NULL,
  `assigned_date` datetime NOT NULL,
  `location_id` int(11) NOT NULL,
  `person_id` varchar(255) NOT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT 1,
  `created_date` datetime NOT NULL DEFAULT current_timestamp(),
  `modified_date` datetime NOT NULL DEFAULT current_timestamp(),
  `modified_by` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `equipment`
--

INSERT INTO `equipment` (`equipment_id`, `asset_id`, `asset_name`, `assigned_date`, `location_id`, `person_id`, `is_active`, `created_date`, `modified_date`, `modified_by`) VALUES
(1, '1212102123', 'acer laptop', '2021-06-07 23:11:08', 1, 'EM1', 1, '2021-06-07 23:11:08', '2021-06-07 23:11:08', 'admin'),
(2, '1234124', 'iphone ', '2021-06-07 23:11:08', 2, 'EM2', 1, '2021-06-07 23:11:08', '2021-06-07 23:11:08', 'Admin');

-- --------------------------------------------------------

--
-- Table structure for table `industry`
--

CREATE TABLE `industry` (
  `industry_id` int(11) NOT NULL,
  `industry_name` varchar(255) NOT NULL,
  `industry_head_id` int(11) NOT NULL,
  `is_active` tinyint(1) DEFAULT 1,
  `created_date` datetime NOT NULL,
  `modified_date` datetime NOT NULL,
  `modified_by` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `industry`
--

INSERT INTO `industry` (`industry_id`, `industry_name`, `industry_head_id`, `is_active`, `created_date`, `modified_date`, `modified_by`) VALUES
(1, '2', 2, 1, '2021-06-07 22:07:11', '2021-06-07 00:00:00', '1'),
(2, 'testset', 2, 1, '2021-06-07 22:07:11', '2021-06-07 22:07:11', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `leave_history`
--

CREATE TABLE `leave_history` (
  `leave_id` int(11) NOT NULL,
  `leave_duration` double NOT NULL,
  `total` double NOT NULL,
  `availed` double NOT NULL,
  `leftover` double NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime NOT NULL,
  `modified_by` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `location`
--

CREATE TABLE `location` (
  `location_id` int(11) NOT NULL,
  `location_name` varchar(255) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime NOT NULL DEFAULT current_timestamp(),
  `modified_by` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `location`
--

INSERT INTO `location` (`location_id`, `location_name`, `is_active`, `created_date`, `modified_date`, `modified_by`) VALUES
(1, 'India', 1, '2021-06-05 19:02:47', '2021-06-05 19:02:47', 'adw'),
(2, 'Germany', 1, '2021-06-05 19:02:47', '2021-06-05 19:02:47', 'efe'),
(3, 'USA', 1, '2021-06-05 19:02:47', '2021-06-05 19:02:47', 'adw'),
(4, 'France', 1, '2021-06-05 19:02:47', '2021-06-05 19:02:47', 'efe'),
(5, 'Netherland', 1, '2021-06-05 19:02:47', '2021-06-05 19:02:47', 'adw'),
(6, 'Belgium', 1, '2021-06-05 19:02:47', '2021-06-05 19:02:47', 'efe'),
(7, 'Japan', 1, '2021-06-05 19:02:47', '2021-06-05 19:02:47', 'adw'),
(8, 'China', 1, '2021-06-05 19:02:47', '2021-06-05 19:02:47', 'efe');

-- --------------------------------------------------------

--
-- Table structure for table `payroll`
--

CREATE TABLE `payroll` (
  `payroll_id` int(11) NOT NULL,
  `person_id` varchar(255) NOT NULL,
  `attendance_id` int(11) NOT NULL,
  `salary` double DEFAULT NULL,
  `bonus` double DEFAULT NULL,
  `compensation` double DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT 1,
  `created_date` datetime NOT NULL DEFAULT current_timestamp(),
  `modified_date` datetime NOT NULL DEFAULT current_timestamp(),
  `modified_by` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `payroll`
--

INSERT INTO `payroll` (`payroll_id`, `person_id`, `attendance_id`, `salary`, `bonus`, `compensation`, `is_active`, `created_date`, `modified_date`, `modified_by`) VALUES
(1, 'EM1', 1, 234, 234, 234, 0, '2021-06-07 00:10:38', '2021-06-07 00:00:00', '1'),
(2, 'EM10', 6, NULL, 1000, 0, 1, '2021-06-07 11:27:30', '2021-06-07 00:00:00', '1'),
(3, 'EM11', 7, NULL, NULL, NULL, 1, '2021-06-07 11:55:16', '2021-06-07 00:00:00', '1'),
(4, 'EM9', 8, NULL, NULL, NULL, 1, '2021-06-07 12:08:59', '2021-06-07 12:08:59', '1'),
(5, 'CT11', 9, NULL, NULL, NULL, 1, '2021-06-07 12:33:40', '2020-11-11 00:00:00', '1'),
(6, 'CT12', 12, NULL, NULL, NULL, 1, '2021-06-07 12:58:14', '2021-06-07 00:00:00', '1'),
(7, 'EM11', 14, NULL, NULL, NULL, 1, '2021-06-07 13:35:50', '2021-06-07 00:00:00', '1');

-- --------------------------------------------------------

--
-- Table structure for table `pay_slab`
--

CREATE TABLE `pay_slab` (
  `job_role_level` int(11) NOT NULL,
  `job_role_name` varchar(255) NOT NULL,
  `pay_per_hour` int(11) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime NOT NULL,
  `modified_by` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `person`
--

CREATE TABLE `person` (
  `person_id` varchar(255) NOT NULL,
  `f_name` varchar(255) NOT NULL,
  `m_name` varchar(255) NOT NULL,
  `l_name` varchar(255) NOT NULL,
  `father_name` varchar(255) NOT NULL,
  `mother_name` varchar(255) NOT NULL,
  `spouse_name` varchar(255) NOT NULL,
  `date_of_birth` date NOT NULL,
  `qualification` varchar(255) NOT NULL,
  `blood_group` varchar(255) NOT NULL,
  `address_id` int(11) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `personal_email_address` varchar(255) NOT NULL,
  `national_identity_number` varchar(255) NOT NULL,
  `bank_account_details` varchar(255) NOT NULL,
  `candidate_id` int(11) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime NOT NULL,
  `modified_by` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `person`
--

INSERT INTO `person` (`person_id`, `f_name`, `m_name`, `l_name`, `father_name`, `mother_name`, `spouse_name`, `date_of_birth`, `qualification`, `blood_group`, `address_id`, `phone_number`, `personal_email_address`, `national_identity_number`, `bank_account_details`, `candidate_id`, `is_active`, `created_date`, `modified_date`, `modified_by`, `gender`) VALUES
('CS1', 'srdgf', 'sef', 'fse', 'fsef', 'sef', 'fsef', '2021-06-04', 'fsef', 'fsefsef', 23, 'sef', 'sef', 'svef', 'sef', 234, 1, '2021-06-05 09:22:28', '2021-06-05 09:22:28', 'sefsef', ''),
('CS10', 'sef', 'sef', 'efse', 'fse', 'fsef', 'sef', '2020-01-12', 'sef', 'sef', 11, 'sef', 'efs', 'efesf', 'fse', 234, 1, '2021-06-06 00:36:53', '2021-06-06 00:00:00', '1', ''),
('CS11', 'sefsef', 'sfesf', 'sefse', 'sefsef', 'fsef', 'sefsef', '2020-11-11', 'sfsef', 'sefsef', 17, 'sefsef', 'fse', 'fse', 'fe', 2323, 0, '2021-06-07 11:55:16', '2021-06-07 00:00:00', '1', ''),
('CS2', 's2222222rdgf', 'sef', 'fse', 'fsef', 'sef', 'fsef', '2021-06-04', 'fsef', 'fsefsef', 23, 'sef', 'sef', 'svef', 'sef', 234, 1, '2021-06-05 09:22:28', '2021-06-05 09:22:28', 'sefsef', ''),
('CS6', 'sef', 'sef', 'sef', 'sef', 'sef', 'sef', '2020-11-11', 'sgsef', 'sefsef', 7, 'sef', 'sef', 'esf', 'sefsef', 234234, 1, '2021-06-06 00:32:31', '2021-06-06 00:32:31', '1', ''),
('CS7', 'sef', 'sef', 'sef', 'sef', 'sef', 'sef', '2020-11-11', 'sgsef', 'sefsef', 8, 'sef', 'sef', 'esf', 'sefsef', 234234, 1, '2021-06-06 00:32:35', '2021-06-06 00:32:35', '1', ''),
('CS8', 'sef', 'sef', 'esf', 'esf', 'sef', 'sef', '2021-11-11', 'sdf', 'sef', 9, 'fsef', 'sef', 'sef', 'sef', 234, 1, '2021-06-06 00:34:32', '2021-06-06 00:34:32', '1', ''),
('CS9', 'sef', 'sef', 'sef', 'sef', 'sef', 'sef', '2020-11-11', 'sgsef', 'sefsef', 10, 'sef', 'sef', 'esf', 'sefsef', 234234, 1, '2021-06-06 00:34:48', '2021-06-06 00:34:48', '1', ''),
('CT1', 'sfd', 'seff', 'sef', 'sef', 'fsef', 'sef', '2021-06-05', 'sefsefsvsesedfsevsesec', 'fsef', 234, 'fsef', 'sef', 'sef', 'sef', 234234, 1, '2021-06-05 09:22:28', '2021-06-05 09:22:28', 'sefsef', ''),
('CT10', 'pranav', 'jaya', 'nayak', '1234', '1234', '1234', '2020-11-11', 'test', 'test', 16, 'test', 'ets', 'et', 'set', 2331, 1, '2021-06-07 11:27:30', '2021-06-07 11:27:30', '1', 'other'),
('CT11', 'sai', 'sef', 'sef', 'sef', 'sef', 'sef', '2020-11-11', 'sef', 'ef', 19, 'fsef', 'ef', 'esf', 'ef', 234, 0, '2021-06-07 12:33:40', '2020-11-11 00:00:00', '1', ''),
('CT12', 'archit', 'sefs', 'efsefs', 'fse', 'fe', 'sef', '2020-11-11', 'sef', 'sef', 20, 'sef', 'sef', 'sef', 'sef', 321, 0, '2021-06-07 12:58:14', '2021-06-07 00:00:00', '1', 'male'),
('CT2', 'ssssssfd', 'seff', 'sef', 'sef', 'fsef', 'sef', '2021-06-05', 'sefsefsvsesedfsevsesec', 'fsef', 234, 'fsef', 'sef', 'sef', 'sef', 234234, 1, '2021-06-05 09:22:28', '2021-06-05 09:22:28', 'sefsef', ''),
('CT8', 'brian', 'sef', 'sef', 'sefsef', 'sef', 'sef', '2020-11-11', 'fsef', 'sef', 14, 'sef', 'sef', 'sefse', 'fse', 123, 1, '2021-06-06 21:49:55', '2021-06-06 21:49:55', '1', ''),
('CT9', 'braian', 'fer', 'dwd', 'wd', 'wd', 'wd', '2020-11-11', 'wdw', 'wd', 15, 'wd', 'wd', 'd', 'wd', 234, 1, '2021-06-06 21:52:21', '2021-06-06 21:52:21', '1', ''),
('EM1', 'jaya', 'jaya', 'nayak', 'test', 'test', 'test', '1996-09-13', 'test', 'test', 1, '123124124', 'adawdawfadawda', 'awdawd12341234', 'sefsefqrq1324eq1edaw', 234234, 1, '2021-06-05 00:44:05', '2021-06-07 00:00:00', '1', ''),
('EM11', 'gendertest', 'sefse', 'fsef', 'sef', 'sef', 'sef', '2020-11-11', 'sfsef', 'fsef', 22, 'sef', 'sef', 'ef', 'sef', 23, 1, '2021-06-07 13:35:50', '2021-06-07 00:00:00', '1', 'Female'),
('EM2', 'ssssssfd', 'seff', 'sef', 'sef', 'fsef', 'sef', '2021-06-05', 'sefsefsvsesedfsevsesec', 'fsef', 234, 'fsef', 'sef', 'sef', 'sef', 234234, 1, '2021-06-05 09:22:28', '2021-06-05 09:22:28', 'sefsef', ''),
('EM4', 'sef', 'sef', 'esf', 'esf', 'sef', 'sef', '2021-11-11', 'sdf', 'sef', 2, 'sef', 'sef', 'sef', 'sef', 234, 1, '2021-06-05 22:24:02', '2021-06-05 22:24:02', '1', ''),
('EM5', 'dadw', 'dawdawda', 'awda', 'wd', 'wda', 'wdawd', '1198-09-12', 'sef', 'sef', 3, 'sef', 'efs', 'efsef', 'sef', 23, 1, '2021-06-06 00:24:06', '2021-06-06 00:24:06', '1', ''),
('EM6', 'dadw', 'dawdawda', 'awda', 'wd', 'wda', 'wdawd', '1198-09-12', 'sef', 'sef', 4, 'sef', 'efs', 'efsef', 'sef', 23, 1, '2021-06-06 00:24:10', '2021-06-06 00:24:10', '1', ''),
('EM7', 'sef', 'sef', 'sef', 'sef', 'sef', 'sef', '2021-11-11', 'sef', 'sef', 5, 'sef', 'sefsefse', 'sefsef', 'sefse', 12234, 1, '2021-06-06 00:25:56', '2021-06-06 00:25:56', '1', ''),
('EM8', 'dadw', 'dawdawda', 'awda', 'wd', 'wda', 'wdawd', '1198-09-12', 'sef', 'sef', 6, 'sef', 'efs', 'efsef', 'sef', 23, 1, '2021-06-06 00:27:47', '2021-06-06 00:27:47', '1', ''),
('EM9', 'tset', 'sef', 'esf', 'esf', 'sef', 'sef', '2021-11-11', 'sdf', 'sef', 18, 'erdg', 'gdr', 'dgr', 'grd', 234, 1, '2021-06-07 12:08:59', '2021-06-07 12:08:59', '1', '');

-- --------------------------------------------------------

--
-- Table structure for table `project`
--

CREATE TABLE `project` (
  `project_id` int(11) NOT NULL,
  `project_name` varchar(255) NOT NULL,
  `project_type_id` int(11) NOT NULL,
  `project_manager_id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL,
  `department_id` int(11) NOT NULL,
  `industry_id` int(11) NOT NULL,
  `location_id` int(11) NOT NULL,
  `project_start_date` datetime NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT current_timestamp(),
  `modified_date` datetime NOT NULL DEFAULT current_timestamp(),
  `modified_by` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `project`
--

INSERT INTO `project` (`project_id`, `project_name`, `project_type_id`, `project_manager_id`, `client_id`, `department_id`, `industry_id`, `location_id`, `project_start_date`, `is_active`, `created_date`, `modified_date`, `modified_by`) VALUES
(1, 'Adding', 1, 1, 1, 1, 1, 1, '2021-06-05 19:41:44', 0, '2021-06-05 19:41:44', '2021-06-05 19:41:44', 'pranav'),
(2, 'subtract', 1, 2, 1, 0, 0, 0, '2021-06-05 19:41:44', 1, '2021-06-05 19:41:44', '2021-06-05 19:41:44', 'sef'),
(3, 'multiply', 1, 2, 1, 0, 0, 0, '2021-06-05 19:41:44', 1, '2021-06-05 19:41:44', '2021-06-05 19:41:44', 'sef'),
(4, 'testFinal', 1, 1, 1, 1, 1, 1, '2020-11-11 00:00:00', 0, '2021-06-07 00:00:00', '2021-06-07 22:20:04', '1'),
(5, 'sf', 1, 1, 1, 1, 1, 1, '2020-11-11 00:00:00', 0, '2021-06-07 00:00:00', '2021-06-07 22:28:21', '1');

-- --------------------------------------------------------

--
-- Table structure for table `project_type`
--

CREATE TABLE `project_type` (
  `project_type_id` int(11) NOT NULL,
  `type_of_project` varchar(255) NOT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT 1,
  `created_date` datetime NOT NULL,
  `modified_date` datetime NOT NULL,
  `modified_by` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `project_type`
--

INSERT INTO `project_type` (`project_type_id`, `type_of_project`, `is_active`, `created_date`, `modified_date`, `modified_by`) VALUES
(1, 'test1', 1, '2021-06-07 22:04:48', '2021-06-07 22:04:48', 'admin'),
(2, 'test2', 1, '2021-06-07 22:04:48', '2021-06-07 22:04:48', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `test`
--

CREATE TABLE `test` (
  `id` int(11) NOT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `middleName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `dOB` varchar(255) NOT NULL,
  `test` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `test`
--

INSERT INTO `test` (`id`, `firstName`, `middleName`, `lastName`, `address`, `dOB`, `test`) VALUES
(3, 'qqqwqweqweqwe', 'qweqweqweqweqwe', 'qweqweqweqweqw', 'qweqweqweqwe', 'eqweqweqweqwe', 'qweqweqweqweqwe'),
(4, 'r', 'r', 'r', 'r', 'r', 'r'),
(5, 'v', 'vv', 'v', 'v', 'v', 'v');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `email_address` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `employee_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT 1,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(255) NOT NULL,
  `modified_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `email_address`, `password`, `employee_id`, `role_id`, `is_active`, `created_date`, `modified_by`, `modified_date`) VALUES
(1, 'pranav', '1234', 1, 1, 1, '2021-06-04 00:20:34', '12345', '2021-06-04 00:00:00'),
(2, 'nirmit', '1234', 98765432, 1, 1, '2021-06-04 00:20:34', '12345', '2021-06-04 00:00:00'),
(3, 'shashikaa', '1234', 22342342, 2, 1, '2021-06-04 00:27:57', '12345', '2021-06-04 00:00:00'),
(5, 'archit', '1234', 1231231, 1, 1, '2021-06-04 00:00:00', '12345', '2021-06-04 00:00:00'),
(6, 'testing', '1234', 123123, 2, 0, '2021-06-04 00:00:00', '12345', '2021-06-04 00:00:00'),
(7, 'harshini', '1234', 1234124123, 1, 1, '2021-06-04 00:00:00', '12345', '2021-06-04 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `work_type`
--

CREATE TABLE `work_type` (
  `wt_id` int(11) NOT NULL,
  `work_type_name` varchar(255) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime DEFAULT NULL,
  `modified_by` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `work_type`
--

INSERT INTO `work_type` (`wt_id`, `work_type_name`, `is_active`, `created_date`, `modified_date`, `modified_by`) VALUES
(1, 'test', 1, '2021-06-03 00:00:00', NULL, '8'),
(2, 'testqwe123', 0, '2021-06-03 00:00:00', '2021-06-03 00:00:00', '8'),
(3, 'gsdfgsrdgf', 0, '2021-06-03 00:00:00', NULL, '0'),
(4, 'sevsdvsd', 1, '2021-06-03 00:00:00', NULL, '8'),
(5, 'vsevse', 1, '2021-06-03 00:00:00', NULL, '8'),
(6, 'sevdsd', 1, '2021-06-03 00:00:00', NULL, '8'),
(7, 'tretet', 0, '2021-06-07 00:00:00', NULL, '1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `access_level`
--
ALTER TABLE `access_level`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`address_id`);

--
-- Indexes for table `attendance`
--
ALTER TABLE `attendance`
  ADD PRIMARY KEY (`attendance_id`);

--
-- Indexes for table `candidate`
--
ALTER TABLE `candidate`
  ADD PRIMARY KEY (`candidate_id`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`client_id`);

--
-- Indexes for table `consultant`
--
ALTER TABLE `consultant`
  ADD PRIMARY KEY (`consultant_id`);

--
-- Indexes for table `contractor`
--
ALTER TABLE `contractor`
  ADD PRIMARY KEY (`contract_id`);

--
-- Indexes for table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`department_id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`employee_id`);

--
-- Indexes for table `employee_exit`
--
ALTER TABLE `employee_exit`
  ADD PRIMARY KEY (`exit_id`);

--
-- Indexes for table `employee_type`
--
ALTER TABLE `employee_type`
  ADD PRIMARY KEY (`employee_type_id`);

--
-- Indexes for table `equipment`
--
ALTER TABLE `equipment`
  ADD PRIMARY KEY (`equipment_id`);

--
-- Indexes for table `industry`
--
ALTER TABLE `industry`
  ADD PRIMARY KEY (`industry_id`);

--
-- Indexes for table `leave_history`
--
ALTER TABLE `leave_history`
  ADD PRIMARY KEY (`leave_id`);

--
-- Indexes for table `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`location_id`);

--
-- Indexes for table `payroll`
--
ALTER TABLE `payroll`
  ADD PRIMARY KEY (`payroll_id`);

--
-- Indexes for table `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`person_id`);

--
-- Indexes for table `project`
--
ALTER TABLE `project`
  ADD PRIMARY KEY (`project_id`);

--
-- Indexes for table `project_type`
--
ALTER TABLE `project_type`
  ADD PRIMARY KEY (`project_type_id`);

--
-- Indexes for table `test`
--
ALTER TABLE `test`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `work_type`
--
ALTER TABLE `work_type`
  ADD PRIMARY KEY (`wt_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `access_level`
--
ALTER TABLE `access_level`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `address`
--
ALTER TABLE `address`
  MODIFY `address_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `attendance`
--
ALTER TABLE `attendance`
  MODIFY `attendance_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `candidate`
--
ALTER TABLE `candidate`
  MODIFY `candidate_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `client_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `consultant`
--
ALTER TABLE `consultant`
  MODIFY `consultant_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `contractor`
--
ALTER TABLE `contractor`
  MODIFY `contract_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `department`
--
ALTER TABLE `department`
  MODIFY `department_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `employee_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `employee_exit`
--
ALTER TABLE `employee_exit`
  MODIFY `exit_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `employee_type`
--
ALTER TABLE `employee_type`
  MODIFY `employee_type_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `equipment`
--
ALTER TABLE `equipment`
  MODIFY `equipment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `industry`
--
ALTER TABLE `industry`
  MODIFY `industry_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `leave_history`
--
ALTER TABLE `leave_history`
  MODIFY `leave_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `location`
--
ALTER TABLE `location`
  MODIFY `location_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `payroll`
--
ALTER TABLE `payroll`
  MODIFY `payroll_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `project`
--
ALTER TABLE `project`
  MODIFY `project_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `project_type`
--
ALTER TABLE `project_type`
  MODIFY `project_type_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `test`
--
ALTER TABLE `test`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `work_type`
--
ALTER TABLE `work_type`
  MODIFY `wt_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
