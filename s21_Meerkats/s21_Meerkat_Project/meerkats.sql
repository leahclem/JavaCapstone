-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 04, 2021 at 09:20 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 7.3.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: meerkats
--
CREATE DATABASE IF NOT EXISTS meerkats DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE meerkats;

DELIMITER $$
--
-- Procedures
--
DROP PROCEDURE IF EXISTS `addAdmin`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addAdmin` (IN `userID` VARCHAR(15), IN `password` VARCHAR(15), IN `fname` VARCHAR(15), IN `lname` VARCHAR(15))  BEGIN
		INSERT INTO theuser (userID, password, userType) VALUES
		(userID, password, 'A');
		INSERT INTO admin (fname, lname, userID) VALUES
		(fname, lname, userID); 
	END$$

DROP PROCEDURE IF EXISTS `addBackLog`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addBackLog` (IN `pupName1` VARCHAR(15), IN `String1` VARCHAR(255))  BEGIN
	INSERT INTO backLogg (pupName, String) VALUES
		(pupName1, String1);
END$$

DROP PROCEDURE IF EXISTS `addBid`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addBid` (IN `winner1` VARCHAR(15), IN `currentBid1` DOUBLE(7,2), IN `maxBid1` DOUBLE(7,2), IN `name1` VARCHAR(15), IN `startBy1` VARCHAR(12), IN `endBy1` VARCHAR(12))  BEGIN
	INSERT INTO bids (currentBid, maxBid, endBy, startBy, winner, name, active, paidFor) VALUES
		(currentBid1, maxBid1, endBy1, startBy1, winner1, name1, 1, 0);
END$$

DROP PROCEDURE IF EXISTS `addCust`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addCust` (IN `userID` VARCHAR(15), IN `password` VARCHAR(15), IN `email` VARCHAR(32), IN `address` VARCHAR(64))  BEGIN
	INSERT INTO theuser (userID, password, userType) VALUES
			(userID, password, 'C');
	INSERT INTO customer (payPal, address, userID) VALUES
			(email, address, userID);
END$$

DROP PROCEDURE IF EXISTS `addHistory`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addHistory` (IN `pupName1` VARCHAR(15), IN `String1` VARCHAR(255))  BEGIN
	INSERT INTO bidHitory (pupName, String) VALUES
		(pupName1, String1);
END$$

DROP PROCEDURE IF EXISTS `addPup`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addPup` (IN `name` VARCHAR(15), IN `breed` VARCHAR(15), IN `sex` VARCHAR(15), IN `pedigree` TINYINT, IN `price` DOUBLE(7,2), IN `hypo` TINYINT)  BEGIN
	INSERT INTO puppies (name, breed, sex, pedigree, price, hypo) VALUES
		(name, breed, sex, pedigree, price, hypo);
END$$

DROP PROCEDURE IF EXISTS `adminInfo`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `adminInfo` ()  BEGIN
	SELECT theuser.userID AS 'User ID', theuser.password AS 'Password', admin.fname AS 'First Name', admin.lname AS 'Last 		Name'
	FROM theuser
	INNER JOIN admin ON admin.userID=theuser.userID;
    END$$

DROP PROCEDURE IF EXISTS `allBids`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `allBids` ()  BEGIN
SELECT bids.name AS 'Puppy Name', bids.currentBid AS 'Current Bid', bids.winner AS 'Winner'
FROM bids;
END$$

DROP PROCEDURE IF EXISTS `customerInfo`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `customerInfo` ()  BEGIN
	SELECT theuser.userID AS 'User ID', theuser.password AS 'Password', customer.address AS 'Address', customer.payPal AS 'PayPal Address'
	FROM theuser
	INNER JOIN customer ON customer.userID=theuser.userID;
    END$$

DROP PROCEDURE IF EXISTS `dequePups`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `dequePups` ()  BEGIN
		DROP TABLE IF EXISTS backlogg;
		CREATE TABLE backlogg (
  		pupName varchar(15) NOT NULL,
  		String varchar(255) NOT NULL
		) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
		ALTER TABLE backlogg
  			ADD CONSTRAINT backlogg_ibfk_1 FOREIGN KEY (pupName) REFERENCES bids (name);
	END$$

DROP PROCEDURE IF EXISTS `profit`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `profit` ()  BEGIN
		SELECT SUM(bids.currentBid) AS 'All Current Bids Value', SUM(puppies.price) AS 'All Puppies Price', 
			(SUM(bids.currentBid)-SUM(puppies.price)) AS 'PROFIT'
		FROM bids
		INNER JOIN puppies ON bids.name=puppies.name;
	END$$

DROP PROCEDURE IF EXISTS `updateActive`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateActive` (IN `name1` VARCHAR(15))  BEGIN
	UPDATE bids
    	SET bids.active=0
    	WHERE bids.name=name1;
	END$$

DROP PROCEDURE IF EXISTS `updateBid`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateBid` (IN `winner1` VARCHAR(15), IN `currentBid1` DOUBLE(7,2), IN `maxBid1` DOUBLE(7,2), IN `name1` VARCHAR(15))  BEGIN
	UPDATE bids
    SET bids.winner=winner1
    WHERE bids.name=name1;
    UPDATE bids
    SET bids.maxBid=maxBid1
    WHERE bids.name=name1;
    UPDATE bids
    SET bids.currentBid=currentBid1
    WHERE bids.name=name1;
END$$

DROP PROCEDURE IF EXISTS `updatePaid`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updatePaid` (IN `name1` VARCHAR(15))  BEGIN
	UPDATE bids
    	SET bids.paidFor=1
    	WHERE bids.name=name1;
	END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table admin
--

DROP TABLE IF EXISTS admin;
CREATE TABLE `admin` (
  fname varchar(15) NOT NULL,
  lname varchar(15) NOT NULL,
  userID varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table admin
--

INSERT INTO admin (fname, lname, userID) VALUES
('Sally', 'Jones', 'admin1'),
('Tony', 'Loco', 'admin2'),
('Oscar', 'Grouch', 'admin3'),
('Santa', 'Klaus', 'Santa');

-- --------------------------------------------------------

--
-- Table structure for table backlogg
--

DROP TABLE IF EXISTS backlogg;
CREATE TABLE backlogg (
  pupName varchar(15) NOT NULL,
  String varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table bidhistory
--

DROP TABLE IF EXISTS bidhistory;
CREATE TABLE bidhistory (
  pupName varchar(15) NOT NULL,
  String varchar(255) NOT NULL,
  timeStamp datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table bids
--

DROP TABLE IF EXISTS bids;
CREATE TABLE bids (
  currentBid double(7,2) NOT NULL,
  maxBid double(7,2) NOT NULL,
  endBy varchar(12) NOT NULL,
  startBy varchar(12) NOT NULL,
  winner varchar(15) NOT NULL,
  name varchar(15) NOT NULL,
  active tinyint(1) NOT NULL,
  paidFor tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table bids
--

INSERT INTO bids (currentBid, maxBid, endBy, startBy, winner, name, active, paidFor) VALUES
(2600.00, 3000.00, '202104301637', '202104191637', 'Jack', 'Jolly', 0, 0),
(2000.00, 3100.00, '202104301641', '202104200137', 'customer2', 'Mack', 0, 0),
(1500.00, 0.00, '202105061659', '202105041508', 'no one', 'Randy', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table customer
--

DROP TABLE IF EXISTS customer;
CREATE TABLE customer (
  payPal varchar(32) NOT NULL,
  address varchar(64) NOT NULL,
  userID varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table customer
--

INSERT INTO customer (payPal, address, userID) VALUES
('customer1@smail.com', '123 Happy Lanes, Bowling Green KY', 'customer1'),
('customer2@smail.com', '149 Elm Street, Roanoke VA', 'customer2'),
('customer3@smail.com', '901 Surfside Lane, Malibu CA', 'customer3'),
('dorsey@twitter.com', '10 Billionaire Drive, Palo Alto CA', 'Jack'),
('used for new bids only', 'used for new bids only', 'No one');

-- --------------------------------------------------------

--
-- Table structure for table puppies
--

DROP TABLE IF EXISTS puppies;
CREATE TABLE puppies (
  name varchar(15) NOT NULL,
  breed varchar(15) NOT NULL,
  sex varchar(15) NOT NULL,
  pedigree tinyint(1) NOT NULL,
  price double(7,2) NOT NULL,
  hypo tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table puppies
--

INSERT INTO puppies (name, breed, sex, pedigree, price, hypo) VALUES
('doug', 'asdfas', 'male', 1, 10000.00, 0),
('Hamilton', 'Great Dane', 'Male', 1, 1500.00, 0),
('Jolly', 'poodle', 'male', 1, 2000.00, 1),
('Mack', 'poodle', 'male', 1, 2000.00, 1),
('Randy', 'corgi', 'male', 1, 1500.00, 0),
('Rover', 'Poodle', 'male', 1, 1500.00, 1),
('Sophie', 'maltese', 'female', 1, 1500.00, 1),
('Spike', 'dalmation', 'male', 1, 1000.00, 0);

-- --------------------------------------------------------

--
-- Table structure for table theuser
--

DROP TABLE IF EXISTS theuser;
CREATE TABLE theuser (
  userID varchar(15) NOT NULL,
  password varchar(15) NOT NULL,
  userType char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table theuser
--

INSERT INTO theuser (userID, password, userType) VALUES
('admin1', 'apple', 'A'),
('admin2', 'apple', 'A'),
('admin3', 'apple', 'A'),
('customer1', 'apple', 'C'),
('customer2', 'apple', 'C'),
('customer3', 'apple', 'C'),
('Jack', 'apple', 'C'),
('No one', 'apple', 'C'),
('Santa', 'apple', 'A'),
('Willzy', 'apple', 'C');

--
-- Indexes for dumped tables
--

--
-- Indexes for table admin
--
ALTER TABLE admin
  ADD PRIMARY KEY (userID);

--
-- Indexes for table backlogg
--
ALTER TABLE backlogg
  ADD PRIMARY KEY (pupName);

--
-- Indexes for table bidhistory
--
ALTER TABLE bidhistory
  ADD PRIMARY KEY (pupName);

--
-- Indexes for table bids
--
ALTER TABLE bids
  ADD PRIMARY KEY (name),
  ADD KEY bids_ibfk_2 (winner);

--
-- Indexes for table customer
--
ALTER TABLE customer
  ADD PRIMARY KEY (userID);

--
-- Indexes for table puppies
--
ALTER TABLE puppies
  ADD PRIMARY KEY (name);

--
-- Indexes for table theuser
--
ALTER TABLE theuser
  ADD PRIMARY KEY (userID);

--
-- Constraints for dumped tables
--

--
-- Constraints for table admin
--
ALTER TABLE admin
  ADD CONSTRAINT admin_ibfk_1 FOREIGN KEY (userID) REFERENCES theuser (userID);

--
-- Constraints for table backlogg
--
ALTER TABLE backlogg
  ADD CONSTRAINT backlogg_ibfk_1 FOREIGN KEY (pupName) REFERENCES bids (name);

--
-- Constraints for table bidhistory
--
ALTER TABLE bidhistory
  ADD CONSTRAINT bidhistory_ibfk_1 FOREIGN KEY (pupName) REFERENCES bids (name);

--
-- Constraints for table bids
--
ALTER TABLE bids
  ADD CONSTRAINT bids_ibfk_1 FOREIGN KEY (name) REFERENCES puppies (name),
  ADD CONSTRAINT bids_ibfk_2 FOREIGN KEY (winner) REFERENCES customer (userID);

--
-- Constraints for table customer
--
ALTER TABLE customer
  ADD CONSTRAINT customer_ibfk_1 FOREIGN KEY (userID) REFERENCES theuser (userID);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
