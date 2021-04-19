-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 20, 2021 at 01:46 AM
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

-- --------------------------------------------------------

--
-- Table structure for table admin
--

DROP TABLE IF EXISTS admin;
CREATE TABLE IF NOT EXISTS `admin` (
  fname varchar(15) NOT NULL,
  lname varchar(15) NOT NULL,
  userID varchar(15) NOT NULL,
  PRIMARY KEY (userID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table admin
--

INSERT INTO admin (fname, lname, userID) VALUES
('Sally', 'Jones', 'admin1'),
('Tony', 'Loco', 'admin2');

-- --------------------------------------------------------

--
-- Table structure for table bids
--

DROP TABLE IF EXISTS bids;
CREATE TABLE IF NOT EXISTS bids (
  currentBid double NOT NULL,
  maxBid double NOT NULL,
  endBy datetime NOT NULL,
  startBy datetime NOT NULL,
  winner varchar(15) NOT NULL,
  name varchar(15) NOT NULL,
  active tinyint(1) NOT NULL,
  paidFor tinyint(1) NOT NULL,
  PRIMARY KEY (name),
  KEY bids_ibfk_2 (winner)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table bids
--

INSERT INTO bids (currentBid, maxBid, endBy, startBy, winner, name, active, paidFor) VALUES
(1500, 2000, '2021-04-30 16:37:09', '2021-04-19 16:37:09', 'customer1', 'Jolly', 1, 0),
(1000, 1500, '2021-04-30 16:41:57', '2021-04-20 01:37:09', 'customer2', 'Mack', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table customer
--

DROP TABLE IF EXISTS customer;
CREATE TABLE IF NOT EXISTS customer (
  payPal varchar(32) NOT NULL,
  address varchar(64) NOT NULL,
  userID varchar(15) NOT NULL,
  PRIMARY KEY (userID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table customer
--

INSERT INTO customer (payPal, address, userID) VALUES
('customer1@smail.com', '123 Happy Lanes, Bowling Green KY', 'customer1'),
('customer2@smail.com', '149 Elm Street, Roanoke VA', 'customer2'),
('customer3@smail.com', '901 Surfside Lane, Malibu CA', 'customer3');

-- --------------------------------------------------------

--
-- Table structure for table puppies
--

DROP TABLE IF EXISTS puppies;
CREATE TABLE IF NOT EXISTS puppies (
  name varchar(15) NOT NULL,
  breed varchar(15) NOT NULL,
  sex varchar(15) NOT NULL,
  pedigree tinyint(1) NOT NULL,
  price double(7,2) NOT NULL,
  hypo tinyint(1) NOT NULL,
  PRIMARY KEY (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table puppies
--

INSERT INTO puppies (name, breed, sex, pedigree, price, hypo) VALUES
('Jolly', 'poodle', 'male', 1, 2000.00, 1),
('Mack', 'poodle', 'male', 1, 2000.00, 1),
('Sophie', 'maltese', 'female', 1, 1500.00, 1),
('Spike', 'dalmation', 'male', 1, 1000.00, 0);

-- --------------------------------------------------------

--
-- Table structure for table theuser
--

DROP TABLE IF EXISTS theuser;
CREATE TABLE IF NOT EXISTS theuser (
  userID varchar(15) NOT NULL,
  password varchar(15) NOT NULL,
  userType char(1) NOT NULL,
  PRIMARY KEY (userID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table theuser
--

INSERT INTO theuser (userID, password, userType) VALUES
('admin1', 'apple', 'A'),
('admin2', 'apple', 'A'),
('customer1', 'apple', 'C'),
('customer2', 'apple', 'C'),
('customer3', 'apple', 'C');

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