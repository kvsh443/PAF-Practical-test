-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 05, 2020 at 07:36 PM
-- Server version: 10.1.40-MariaDB
-- PHP Version: 7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE `doctor` (
  `id` int(6) NOT NULL,
  `firstname` varchar(15) NOT NULL,
  `middlename` varchar(15) NOT NULL,
  `lastname` varchar(15) NOT NULL,
  `contact` char(10) NOT NULL,
  `status` char(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`id`, `firstname`, `middlename`, `lastname`, `contact`, `status`) VALUES
(1, 'DocF', 'DocM', 'DocLzz', '0112534212', 'Active'),
(2, 'DocF2', 'DocM2', 'DocL2', '2112534212', 'Inactive'),
(3, 'DocF3', 'DocM3', 'DocL3', '0443456789', 'Active'),
(4, 'DocF', 'DocM', 'DocL', '9443456780', 'Active'),
(5, 'DocF', 'DocM', 'DocL', '9443456780', 'Active'),
(6, 'DocF', 'DocM', 'DocL', '9443456780', 'Active'),
(7, 'DocF', 'DocM', 'DocL', '9443456780', 'Active'),
(8, 'DocF', 'DocM', 'DocL', '9443456780', 'Active'),
(9, 'NewDocFA', 'NewDocM', 'NewDocL', '077000000', 'Active');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `doctor`
--
ALTER TABLE `doctor`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
