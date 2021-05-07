-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 07, 2021 at 09:32 PM
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `addAdmin` (IN `userID` VARCHAR(15), IN `password` VARCHAR(15), IN `fname` VARCHAR(15), IN `lname` VARCHAR(15))  BEGIN
		INSERT INTO theuser (userID, password, userType) VALUES
		(userID, password, 'A');
		INSERT INTO admin (fname, lname, userID) VALUES
		(fname, lname, userID); 
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addBackLog` (IN `pupName` VARCHAR(15), IN `String` VARCHAR(255))  BEGIN
	INSERT INTO backlogg (pupName, String) 
    VALUES
		(pupName, String);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addBid` (IN `winner1` VARCHAR(15), IN `currentBid1` DOUBLE(7,2), IN `maxBid1` DOUBLE(7,2), IN `name1` VARCHAR(15), IN `startBy1` VARCHAR(12), IN `endBy1` VARCHAR(12))  BEGIN
	INSERT INTO bids (currentBid, maxBid, endBy, startBy, winner, name, active, paidFor) VALUES
		(currentBid1, maxBid1, endBy1, startBy1, winner1, name1, 1, 0);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addCust` (IN `userID` VARCHAR(15), IN `password` VARCHAR(15), IN `email` VARCHAR(32), IN `address` VARCHAR(64))  BEGIN
	INSERT INTO theuser (userID, password, userType) VALUES
			(userID, password, 'C');
	INSERT INTO customer (payPal, address, userID) VALUES
			(email, address, userID);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addHistory` (IN `pupName` VARCHAR(15), IN `String` VARCHAR(255))  BEGIN
	INSERT INTO bidHistory (pupName, String) 
    VALUES
		(pupName, String);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addPup` (IN `name` VARCHAR(15), IN `breed` VARCHAR(15), IN `sex` VARCHAR(15), IN `pedigree` TINYINT, IN `price` DOUBLE(7,2), IN `hypo` TINYINT)  BEGIN
	INSERT INTO puppies (name, breed, sex, pedigree, price, hypo) VALUES
		(name, breed, sex, pedigree, price, hypo);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `adminInfo` ()  BEGIN
	SELECT theuser.userID AS 'User ID', theuser.password AS 'Password', admin.fname AS 'First Name', admin.lname AS 'Last 		Name'
	FROM theuser
	INNER JOIN admin ON admin.userID=theuser.userID;
    END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `allBids` ()  BEGIN
SELECT bids.name AS 'Puppy Name', bids.currentBid AS 'Current Bid', bids.winner AS 'Winner'
FROM bids;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `customerInfo` ()  BEGIN
	SELECT theuser.userID AS 'User ID', theuser.password AS 'Password', customer.address AS 'Address', customer.payPal AS 'PayPal Address'
	FROM theuser
	INNER JOIN customer ON customer.userID=theuser.userID;
    END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `dequePups` ()  BEGIN
		DROP TABLE IF EXISTS backlogg;
		CREATE TABLE backlogg (
  		pupName varchar(15) NOT NULL,
  		String varchar(255) NOT NULL
		) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `profit` ()  BEGIN
		SELECT SUM(bids.currentBid) AS 'All Current Bids Value', SUM(puppies.price) AS 'All Puppies Price', 
			(SUM(bids.currentBid)-SUM(puppies.price)) AS 'PROFIT'
		FROM bids
		INNER JOIN puppies ON bids.name=puppies.name;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateActive` (IN `name1` VARCHAR(15))  BEGIN
	UPDATE bids
    	SET bids.active=0
    	WHERE bids.name=name1;
	END$$

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

CREATE TABLE `admin` (
  fname varchar(15) NOT NULL,
  lname varchar(15) NOT NULL,
  userID varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table admin
--

INSERT INTO admin (fname, lname, userID) VALUES
('Diane', 'Wolff', 'Diane'),
('Sam', 'Jones', 'Sammy'),
('Santa', 'Klaus', 'Santa'),
('Will', 'McCoy', 'Willzy');

-- --------------------------------------------------------

--
-- Table structure for table backlogg
--

CREATE TABLE backlogg (
  pupName varchar(15) NOT NULL,
  String varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table bidhistory
--

CREATE TABLE bidhistory (
  pupName varchar(15) NOT NULL,
  String varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table bidhistory
--

INSERT INTO bidhistory (pupName, String) VALUES
('Olly', 'Olly Bid History'),
('Olly', 'Bidder 		Result 			Winner		Bid			Current Price 		Max willing to pay'),
('Olly', 'Dave		First bid		Dave		$2,000.00		$1,000.00			$2,000.00'),
('Olly', 'Dave		Updated bid 		Dave		$2,500.00		$1,000.00			$2,500.00'),
('Olly', 'Dave		Rejected (too low) 	Dave		$2,000.00		$1,000.00			$2,500.00'),
('Olly', 'Dave		Rejected (too low) 	Dave		$1,600.00		$1,000.00			$2,500.00');

-- --------------------------------------------------------

--
-- Table structure for table bids
--

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
(1400.00, 0.00, '202106011659', '202105041613', 'no one', 'BooBoo', 1, 0),
(1600.00, 0.00, '202106011659', '202105041614', 'no one', 'Bowser', 1, 0),
(100.00, 0.00, '202105151659', '202105071417', 'no one', 'Jack', 1, 0),
(1500.00, 0.00, '202105171659', '202105041612', 'no one', 'Jolly', 1, 0),
(100.00, 0.00, '202106011659', '202105041614', 'no one', 'Odie', 1, 0),
(1000.00, 2500.00, '202105151659', '202105041612', 'Dave', 'Olly', 1, 0),
(2000.00, 0.00, '202105151659', '202105041613', 'no one', 'Sophie', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table customer
--

CREATE TABLE customer (
  payPal varchar(32) NOT NULL,
  address varchar(64) NOT NULL,
  userID varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table customer
--

INSERT INTO customer (payPal, address, userID) VALUES
('bill@windows.com', 'bill@windows.com', 'Bill'),
('garfield@dave.com', '102 Garfield Lane', 'Dave'),
('dorsey@twitter.com', '10 Billionaire Drive, Palo Alto CA', 'Jack'),
('Joe.Schmoe@null.com', '123 St. Joseph\'s Street', 'Joe'),
('John@smail.com', '157 Spooner Street', 'John Doe'),
('used for new bids only', 'used for new bids only', 'No one'),
('sandra@smail.com', 'sandra.bullock@hollywood.com', 'Sandra');

-- --------------------------------------------------------

--
-- Table structure for table puppies
--

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
('BooBoo', 'Maltese', 'male', 1, 1400.00, 1),
('Bowser', 'Boxer', 'male', 1, 1600.00, 0),
('Jack', 'Tiger', 'male', 0, 100.00, 0),
('Jolly', 'Pug', 'female', 1, 1500.00, 0),
('Muffet', 'Mutt', 'female', 0, 200.00, 0),
('Odie', 'Mutt', 'male', 0, 100.00, 0),
('Olly', 'dalmation', 'male', 1, 1000.00, 0),
('Schooner', 'Boxer', 'female', 1, 1300.00, 0),
('Sophie', 'Maltese', 'female', 1, 2000.00, 1),
('Tuffet', 'Mutt', 'male', 0, 200.00, 0);

-- --------------------------------------------------------

--
-- Table structure for table theuser
--

CREATE TABLE theuser (
  userID varchar(15) NOT NULL,
  password varchar(15) NOT NULL,
  userType char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table theuser
--

INSERT INTO theuser (userID, password, userType) VALUES
('Bill', 'apple', 'C'),
('Dave', 'apple', 'C'),
('Diane', 'apple', 'A'),
('Jack', 'apple', 'C'),
('Joe', 'apple', 'C'),
('John Doe', 'apple', 'C'),
('No one', 'apple', 'C'),
('Sammy', 'apple', 'A'),
('Sandra', 'apple', 'C'),
('Santa', 'apple', 'A'),
('Willzy', 'apple', 'A');

--
-- Indexes for dumped tables
--

--
-- Indexes for table admin
--
ALTER TABLE admin
  ADD PRIMARY KEY (userID);

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
