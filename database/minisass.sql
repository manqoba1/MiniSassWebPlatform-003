-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 11, 2015 at 02:46 PM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `minisass`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
`categoryId` int(10) unsigned NOT NULL,
  `categoryName` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`categoryId`, `categoryName`) VALUES
(9, 'Rocky Type'),
(8, 'Sandy Type');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
`commentID` int(10) unsigned NOT NULL,
  `remarks` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`commentID`, `remarks`) VALUES
(1, 'Weather'),
(2, 'Impacts'),
(3, 'Alien plants'),
(4, 'level of flow');

-- --------------------------------------------------------

--
-- Table structure for table `conditions`
--

CREATE TABLE IF NOT EXISTS `conditions` (
`conditionsID` int(10) unsigned NOT NULL,
  `conditionName` varchar(255) NOT NULL,
  `categoryID` int(10) unsigned NOT NULL,
  `low` double NOT NULL,
  `high` double NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `conditions`
--

INSERT INTO `conditions` (`conditionsID`, `conditionName`, `categoryID`, `low`, `high`) VALUES
(1, 'Unmodified(NATURAL condition)', 8, 6.9, 7),
(6, 'Largely natural/few modifications(GOOD condition)', 8, 5.8, 6.9),
(7, 'Moderately modified(FAIR condition)', 8, 4.9, 5.8),
(8, 'Largely modified(POOR condition)', 8, 4.3, 4.9),
(9, 'Seriously/critically modified', 8, 4.2, 4.3);

-- --------------------------------------------------------

--
-- Table structure for table `country`
--

CREATE TABLE IF NOT EXISTS `country` (
`countryID` int(10) unsigned NOT NULL,
  `countryName` varchar(255) NOT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `countryCode` varchar(5) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `country`
--

INSERT INTO `country` (`countryID`, `countryName`, `latitude`, `longitude`, `countryCode`) VALUES
(1, 'South Africa', 23, 27, 'ZA'),
(2, 'Mozambique', NULL, NULL, 'MZ'),
(3, 'Zimbabwe', NULL, NULL, 'ZW'),
(4, 'Namibia', NULL, NULL, 'NA'),
(5, 'Zambia', NULL, NULL, 'ZM');

-- --------------------------------------------------------

--
-- Table structure for table `errorstore`
--

CREATE TABLE IF NOT EXISTS `errorstore` (
`errorStoreID` int(10) unsigned NOT NULL,
  `statusCode` int(11) NOT NULL,
  `message` text NOT NULL,
  `dateOccured` datetime NOT NULL,
  `origin` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `errorstore`
--

INSERT INTO `errorstore` (`errorStoreID`, `statusCode`, `message`, `dateOccured`, `origin`) VALUES
(1, 909, 'Google Cloud Messaging device registration failed.\nError Code Name: MismatchSenderId', '2015-03-23 00:00:00', 'Cloud Messaging Service'),
(2, 909, 'Google Cloud Messaging device registration failed.\nError Code Name: MismatchSenderId', '2015-04-23 00:00:00', 'Cloud Messaging Service');

-- --------------------------------------------------------

--
-- Table structure for table `errorstoreandroid`
--

CREATE TABLE IF NOT EXISTS `errorstoreandroid` (
`errorStoreAndroidID` int(10) unsigned NOT NULL,
  `errorDate` datetime NOT NULL,
  `packageName` varchar(150) NOT NULL,
  `appVersionName` varchar(10) NOT NULL,
  `appVersionCode` varchar(10) NOT NULL,
  `brand` varchar(100) NOT NULL,
  `phoneModel` varchar(100) NOT NULL,
  `androidVersion` varchar(20) NOT NULL,
  `stackTrace` text NOT NULL,
  `logCat` text NOT NULL,
  `riverID` int(10) unsigned NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `evaluation`
--

CREATE TABLE IF NOT EXISTS `evaluation` (
`evaluationID` int(10) unsigned NOT NULL,
  `teamMemberID` int(10) unsigned NOT NULL,
  `evaluationSiteID` int(10) unsigned NOT NULL,
  `evaluationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remarks` text,
  `score` double DEFAULT NULL,
  `pH` double DEFAULT NULL,
  `waterTemperature` double DEFAULT NULL,
  `oxygen` double DEFAULT NULL,
  `waterClarity` double DEFAULT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `conditionsID` int(10) unsigned DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=163 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `evaluation`
--

INSERT INTO `evaluation` (`evaluationID`, `teamMemberID`, `evaluationSiteID`, `evaluationDate`, `remarks`, `score`, `pH`, `waterTemperature`, `oxygen`, `waterClarity`, `latitude`, `longitude`, `conditionsID`) VALUES
(134, 13, 135, '2015-04-29 07:00:00', 'hjfhdihchdj', 9.5, 0.5, 0.5, 0.5, 0.5, -25.7026322, 28.75191713, 1),
(135, 13, 136, '2015-04-29 07:00:00', 'sgssggdg gfhduur', 4, 0.5, 0.5, 0.5, 0.5, -25.7026322, 28.75191713, 9),
(136, 13, 137, '2015-04-29 07:00:00', 'dhdhru dhrud dhehdururu', 6, 0.5, 0.5, 1.5, 0.5, -25.7026322, 28.75191713, 6),
(137, 13, 138, '2015-04-29 07:00:00', 'fhdhf gjfj xhfh rhfh', 5, 0.5, 0.5, 1, 0.5, -25.7026322, 28.75191713, 7),
(138, 13, 139, '2015-04-29 07:00:00', 'fnfjjfj', 3.67, 0.5, 0.5, 1, 1, -25.7026322, 28.75191713, 9),
(139, 13, 140, '2015-04-29 18:11:13', 'hhfuj fjdhru feuru', 4.5, 0, 0.5, 1.5, 1, -25.7026322, 28.75191713, 8),
(140, 13, 141, '2015-04-29 07:00:00', 'fjfjfj had jdjjf fjfjfj urrurdu', 9.5, 0.5, 0.5, 1, 1, -25.7026322, 28.75191713, 1),
(141, 13, 142, '2015-04-29 07:00:00', 'fjfuuff jfjfurutitr fitf hfjffu', 17, 1.5, 1, 1.5, 0.5, -25.7026322, 28.75191713, 1),
(142, 13, 143, '2015-04-29 07:00:00', 'hfjfj jfjfjj jfkdkn nfnfnfn ncjfnnfn ncnjfj nncncnfjjcjfnfj jjcndmmdn k nfnfnfnj jfjfk kkckf', 2, 1, 1, 1, 1, -25.7026322, 28.75191713, 9),
(143, 13, 144, '2015-04-29 07:00:00', 'jdjjfjjdkf hfnjfj hcjjcjc jfjjfjj hhjfjfcjjdjdjfjdjchhchfhf', 4, 1.5, 1, 1, 1, -25.7026322, 28.75191713, 9),
(144, 13, 145, '2015-04-29 07:00:00', 'dgxhxbxbh', 9.5, 1, 0.5, 1.5, 1, -25.7026322, 28.75191713, 1),
(145, 13, 146, '2015-04-29 07:00:00', 'dhdhdjf hdjfu', 9.5, 0.5, 1, 1, 0.5, -25.7026322, 28.75191713, 1),
(146, 13, 147, '2015-04-29 07:00:00', 'dduruurur gjfuttig', 9.5, 1, 0.5, 0.5, 0.5, -25.7026322, 28.75191713, 1),
(147, 13, 148, '2015-04-29 07:00:00', 'fhfhfjj', 9.5, 0.5, 0.5, 0.5, 0.5, -25.7026322, 28.75191713, 1),
(148, 13, 149, '2015-04-29 07:00:00', 'duduururyd hdud jfdu', 7.33, 2, 0.5, 0.5, 0.5, -25.7026322, 28.75191713, 1),
(149, 13, 150, '2015-04-29 07:00:00', 'chjrjfj fhtu fhtubfjjt', 4, 1.5, 0.5, 0.5, 1, -25.7026322, 28.75191713, 9),
(150, 13, 151, '2015-04-29 07:00:00', 'dgdyd dhf fyru hfy', 8.67, 0.5, 1, 0.5, 1, -25.7026322, 28.75191713, 1),
(151, 13, 152, '2015-04-29 07:00:00', 'dheyyru bdhdcudu rufu udhx hdurcuccu u', 11.5, 1, 1, 1, 0.5, -25.7026322, 28.75191713, 1),
(152, 13, 153, '2015-04-29 07:00:00', 'grrb dhf dhr dyrbtu', 8.33, 1, 0.5, 1, 1, -25.7026322, 28.75191713, 1),
(153, 13, 154, '2015-04-29 07:00:00', 'gdhd fhf dufbfu', 9.5, 1.5, 1, 1, 1, -25.7026322, 28.75191713, 1),
(154, 13, 155, '2015-04-29 07:00:00', 'fhf fhf CBGB dug fufuyf fuf', 4, 1, 1, 0.5, 1, -25.7026322, 28.75191713, 9),
(155, 13, 156, '2015-04-29 07:00:00', 'dhryd dhd fydy d', 8.33, 1.5, 0.5, 1, 1, -25.7026322, 28.75191713, 1),
(156, 13, 157, '2015-04-29 07:00:00', 'fydy fhfu furu fuf fhfy dydyfvry', 11.5, 1, 2, 1, 1, -25.7026322, 28.75191713, 1),
(157, 13, 158, '2015-04-29 07:00:00', 'jdidi jjfjjfj jjfjfj jjdj djfufu ufjjfjj jfjfjjcjcjc jfjf', 9.5, 0.5, 1.5, 1.5, 0.5, -25.7026322, 28.75191713, 1),
(158, 13, 159, '2015-04-29 07:00:00', 'hxhxhhxhbhxhj jxhxbdj hhbdb hxbdhxj jcjj hchjdhx hjxjjxj jcbdjdjhcncnj jcjxjdj jcjdjd jcjjdjcjsjdjcj', 6, 0.5, 1, 1.5, 0.5, -25.7026322, 28.75191713, 6),
(159, 13, 160, '2015-04-29 07:00:00', 'hxjxkxj hhhx hhxhxhhx hcjjxjxj jjxjjxjxh hhxhxhxj jusjsjdy uxhxhxhxj jcjjxjdj jhcjdjjc', 4.5, 1.5, 1, 0.5, 1, -25.7026322, 28.75191713, 8),
(160, 13, 161, '2015-04-29 07:00:00', 'hjjxjxjxjxuuxuuxui uuxuxjj uxujduudy ycyxhyxyxu yxuxhxj jjcjxjxjxj hhchxhxhxh uuxuxuxhuxuxucuxuxuuxuxuxu', 6.8, 2, 1, 1, 1, -25.7026322, 28.75191713, 6),
(161, 13, 162, '2015-04-30 07:00:00', 'njjhfhdhdj hfhdjdj hdjhhdh hfhdhfj hfhfhf hdhdh hchhchf hchhch', 9.5, 1, 0.5, 0.5, 0.5, -25.75203063, 28.26296421, 1),
(162, 15, 163, '2015-05-01 16:30:49', 'good', 9.5, 2.5, 2.5, 2, 3, -25.7459564, 28.2705553, 1);

-- --------------------------------------------------------

--
-- Table structure for table `evaluationcomment`
--

CREATE TABLE IF NOT EXISTS `evaluationcomment` (
`evaluationCommentID` int(10) unsigned NOT NULL,
  `evaluationID` int(10) unsigned NOT NULL,
  `commentID` int(10) unsigned NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `evaluationimage`
--

CREATE TABLE IF NOT EXISTS `evaluationimage` (
`evaluationImageID` int(10) unsigned NOT NULL,
  `evaluationID` int(10) unsigned NOT NULL,
  `dateTaken` datetime NOT NULL,
  `fileName` varchar(255) NOT NULL,
  `longitude` double NOT NULL,
  `latitude` double NOT NULL,
  `accuracy` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `evaluationinsect`
--

CREATE TABLE IF NOT EXISTS `evaluationinsect` (
`evaluationInsectID` int(10) unsigned NOT NULL,
  `evaluationID` int(10) unsigned NOT NULL,
  `evaluationFlag` int(11) NOT NULL,
  `insectID` int(10) unsigned NOT NULL,
  `evaluationColor` int(11) DEFAULT NULL,
  `remarks` text
) ENGINE=InnoDB AUTO_INCREMENT=335 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `evaluationinsect`
--

INSERT INTO `evaluationinsect` (`evaluationInsectID`, `evaluationID`, `evaluationFlag`, `insectID`, `evaluationColor`, `remarks`) VALUES
(264, 134, 0, 5, 5, 'hjfhdihchdj'),
(265, 134, 0, 12, 5, 'hjfhdihchdj'),
(266, 135, 0, 4, 5, 'sgssggdg gfhduur'),
(267, 135, 0, 12, 5, 'sgssggdg gfhduur'),
(268, 136, 0, 3, 5, 'dhdhru dhrud dhehdururu'),
(269, 136, 0, 5, 5, 'dhdhru dhrud dhehdururu'),
(270, 136, 0, 12, 5, 'dhdhru dhrud dhehdururu'),
(271, 136, 0, 1, 5, 'dhdhru dhrud dhehdururu'),
(272, 137, 0, 14, 5, 'fhdhf gjfj xhfh rhfh'),
(273, 137, 0, 13, 5, 'fhdhf gjfj xhfh rhfh'),
(274, 138, 0, 1, 5, 'fnfjjfj'),
(275, 138, 0, 3, 5, 'fnfjjfj'),
(276, 138, 0, 4, 5, 'fnfjjfj'),
(277, 139, 0, 12, 5, 'hhfuj fjdhru feuru'),
(278, 139, 0, 13, 5, 'hhfuj fjdhru feuru'),
(279, 139, 0, 14, 5, 'hhfuj fjdhru feuru'),
(280, 139, 0, 9, 5, 'hhfuj fjdhru feuru'),
(281, 140, 0, 5, 5, 'fjfjfj had jdjjf fjfjfj urrurdu'),
(282, 140, 0, 3, 5, 'fjfjfj had jdjjf fjfjfj urrurdu'),
(283, 141, 0, 5, 5, 'fjfuuff jfjfurutitr fitf hfjffu'),
(284, 142, 0, 12, 5, 'hfjfj jfjfjj jfkdkn nfnfnfn ncjfnnfn ncnjfj nncncnfjjcjfnfj jjcndmmdn k nfnfnfnj jfjfk kkckf'),
(285, 143, 0, 14, 5, 'jdjjfjjdkf hfnjfj hcjjcjc jfjjfjj hhjfjfcjjdjdjfjdjchhchfhf'),
(286, 143, 0, 13, 5, 'jdjjfjjdkf hfnjfj hcjjcjc jfjjfjj hhjfjfcjjdjdjfjdjchhchfhf'),
(287, 143, 0, 12, 5, 'jdjjfjjdkf hfnjfj hcjjcjc jfjjfjj hhjfjfcjjdjdjfjdjchhchfhf'),
(288, 144, 0, 12, 5, 'dgxhxbxbh'),
(289, 144, 0, 5, 5, 'dgxhxbxbh'),
(290, 145, 0, 12, 5, 'dhdhdjf hdjfu'),
(291, 145, 0, 5, 5, 'dhdhdjf hdjfu'),
(292, 146, 0, 5, 5, 'dduruurur gjfuttig'),
(293, 146, 0, 3, 5, 'dduruurur gjfuttig'),
(294, 147, 0, 5, 5, 'fhfhfjj'),
(295, 147, 0, 12, 5, 'fhfhfjj'),
(296, 148, 0, 3, 5, 'duduururyd hdud jfdu'),
(297, 148, 0, 5, 5, 'duduururyd hdud jfdu'),
(298, 148, 0, 1, 5, 'duduururyd hdud jfdu'),
(299, 149, 0, 12, 5, 'chjrjfj fhtu fhtubfjjt'),
(300, 149, 0, 14, 5, 'chjrjfj fhtu fhtubfjjt'),
(301, 150, 0, 5, 5, 'dgdyd dhf fyru hfy'),
(302, 150, 0, 4, 5, 'dgdyd dhf fyru hfy'),
(303, 150, 0, 1, 5, 'dgdyd dhf fyru hfy'),
(304, 151, 0, 5, 5, 'dheyyru bdhdcudu rufu udhx hdurcuccu u'),
(305, 151, 0, 9, 5, 'dheyyru bdhdcudu rufu udhx hdurcuccu u'),
(306, 152, 0, 12, 5, 'grrb dhf dhr dyrbtu'),
(307, 152, 0, 5, 5, 'grrb dhf dhr dyrbtu'),
(308, 152, 0, 14, 5, 'grrb dhf dhr dyrbtu'),
(309, 153, 0, 5, 5, 'gdhd fhf dufbfu'),
(310, 153, 0, 12, 5, 'gdhd fhf dufbfu'),
(311, 154, 0, 12, 5, 'fhf fhf CBGB dug fufuyf fuf'),
(312, 154, 0, 14, 5, 'fhf fhf CBGB dug fufuyf fuf'),
(313, 155, 0, 5, 5, 'dhryd dhd fydy d'),
(314, 155, 0, 4, 5, 'dhryd dhd fydy d'),
(315, 155, 0, 3, 5, 'dhryd dhd fydy d'),
(316, 156, 0, 5, 5, 'fydy fhfu furu fuf fhfy dydyfvry'),
(317, 156, 0, 14, 5, 'fydy fhfu furu fuf fhfy dydyfvry'),
(318, 157, 0, 5, 5, 'jdidi jjfjjfj jjfjfj jjdj djfufu ufjjfjj jfjfjjcjcjc jfjf'),
(319, 157, 0, 12, 5, 'jdidi jjfjjfj jjfjfj jjdj djfufu ufjjfjj jfjfjjcjcjc jfjf'),
(320, 158, 0, 4, 5, 'hxhxhhxhbhxhj jxhxbdj hhbdb hxbdhxj jcjj hchjdhx hjxjjxj jcbdjdjhcncnj jcjxjdj jcjdjd jcjjdjcjsjdjcj'),
(321, 158, 0, 14, 5, 'hxhxhhxhbhxhj jxhxbdj hhbdb hxbdhxj jcjj hchjdhx hjxjjxj jcbdjdjhcncnj jcjxjdj jcjdjd jcjjdjcjsjdjcj'),
(322, 159, 0, 9, 5, 'hxjxkxj hhhx hhxhxhhx hcjjxjxj jjxjjxjxh hhxhxhxj jusjsjdy uxhxhxhxj jcjjxjdj jhcjdjjc'),
(323, 159, 0, 12, 5, 'hxjxkxj hhhx hhxhxhhx hcjjxjxj jjxjjxjxh hhxhxhxj jusjsjdy uxhxhxhxj jcjjxjdj jhcjdjjc'),
(324, 159, 0, 13, 5, 'hxjxkxj hhhx hhxhxhhx hcjjxjxj jjxjjxjxh hhxhxhxj jusjsjdy uxhxhxhxj jcjjxjdj jhcjdjjc'),
(325, 159, 0, 14, 5, 'hxjxkxj hhhx hhxhxhhx hcjjxjxj jjxjjxjxh hhxhxhxj jusjsjdy uxhxhxhxj jcjjxjdj jhcjdjjc'),
(326, 160, 0, 1, 5, 'hjjxjxjxjxuuxuuxui uuxuxjj uxujduudy ycyxhyxyxu yxuxhxj jjcjxjxjxj hhchxhxhxh uuxuxuxhuxuxucuxuxuuxuxuxu'),
(327, 160, 0, 3, 5, 'hjjxjxjxjxuuxuuxui uuxuxjj uxujduudy ycyxhyxyxu yxuxhxj jjcjxjxjxj hhchxhxhxh uuxuxuxhuxuxucuxuxuuxuxuxu'),
(328, 160, 0, 4, 5, 'hjjxjxjxjxuuxuuxui uuxuxjj uxujduudy ycyxhyxyxu yxuxhxj jjcjxjxjxj hhchxhxhxh uuxuxuxhuxuxucuxuxuuxuxuxu'),
(329, 160, 0, 9, 5, 'hjjxjxjxjxuuxuuxui uuxuxjj uxujduudy ycyxhyxyxu yxuxhxj jjcjxjxjxj hhchxhxhxh uuxuxuxhuxuxucuxuxuuxuxuxu'),
(330, 160, 0, 5, 5, 'hjjxjxjxjxuuxuuxui uuxuxjj uxujduudy ycyxhyxyxu yxuxhxj jjcjxjxjxj hhchxhxhxh uuxuxuxhuxuxucuxuxuuxuxuxu'),
(331, 161, 0, 5, 5, 'njjhfhdhdj hfhdjdj hdjhhdh hfhdhfj hfhfhf hdhdh hchhchf hchhch'),
(332, 161, 0, 12, 5, 'njjhfhdhdj hfhdjdj hdjhhdh hfhdhfj hfhfhf hdhdh hchhchf hchhch'),
(333, 162, 0, 5, 5, 'good'),
(334, 162, 0, 12, 5, 'good');

-- --------------------------------------------------------

--
-- Table structure for table `evaluationsite`
--

CREATE TABLE IF NOT EXISTS `evaluationsite` (
`evaluationSiteID` int(10) unsigned NOT NULL,
  `riverID` int(10) unsigned NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `accuracy` float NOT NULL,
  `dateRegistered` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `categoryID` int(10) unsigned NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=164 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `evaluationsite`
--

INSERT INTO `evaluationsite` (`evaluationSiteID`, `riverID`, `latitude`, `longitude`, `accuracy`, `dateRegistered`, `categoryID`) VALUES
(135, 5, -25.7026322, 28.75191713, 6, '2015-04-29 07:00:00', 8),
(136, 1, -25.7026322, 28.75191713, 6, '2015-04-29 07:00:00', 8),
(137, 1, -25.7026322, 28.75191713, 6, '2015-04-29 07:00:00', 8),
(138, 2, -25.7026322, 28.75191713, 6, '2015-04-29 07:00:00', 8),
(139, 2, -25.7026322, 28.75191713, 6, '2015-04-29 07:00:00', 8),
(140, 2, -25.7026322, 28.75191713, 6, '2015-04-29 07:00:00', 8),
(141, 4, -25.7026322, 28.75191713, 6, '2015-04-29 07:00:00', 8),
(142, 9, -25.7026322, 28.75191713, 6, '2015-04-29 07:00:00', 8),
(143, 12, -25.7026322, 28.75191713, 6, '2015-04-29 07:00:00', 8),
(144, 12, -25.7026322, 28.75191713, 6, '2015-04-29 07:00:00', 8),
(145, 3, -25.7026322, 28.75191713, 6, '2015-04-29 07:00:00', 8),
(146, 4, -25.7026322, 28.75191713, 6, '2015-04-29 07:00:00', 8),
(147, 1, -25.7026322, 28.75191713, 6, '2015-04-29 07:00:00', 8),
(148, 1, -25.7026322, 28.75191713, 6, '2015-04-29 07:00:00', 8),
(149, 10, -25.7026322, 28.75191713, 6, '2015-04-29 07:00:00', 8),
(150, 10, -25.7026322, 28.75191713, 6, '2015-04-29 07:00:00', 8),
(151, 5, -25.7026322, 28.75191713, 6, '2015-04-29 07:00:00', 8),
(152, 5, -25.7026322, 28.75191713, 6, '2015-04-29 07:00:00', 8),
(153, 4, -25.7026322, 28.75191713, 6, '2015-04-29 07:00:00', 8),
(154, 3, -25.7026322, 28.75191713, 6, '2015-04-29 07:00:00', 8),
(155, 9, -25.7026322, 28.75191713, 6, '2015-04-29 07:00:00', 8),
(156, 1, -25.7026322, 28.75191713, 6, '2015-04-29 07:00:00', 8),
(157, 1, -25.7026322, 28.75191713, 6, '2015-04-29 07:00:00', 8),
(158, 8, -25.7026322, 28.75191713, 6, '2015-04-29 07:00:00', 8),
(159, 8, -25.7026322, 28.75191713, 6, '2015-04-29 07:00:00', 8),
(160, 8, -25.7026322, 28.75191713, 6, '2015-04-29 07:00:00', 8),
(161, 8, -25.7026322, 28.75191713, 6, '2015-04-29 07:00:00', 8),
(162, 11, -25.75203063, 28.26296421, 10, '2015-04-30 07:00:00', 8),
(163, 1, -25.7459564, 28.2705553, 1199, '2015-04-29 07:00:00', 9);

-- --------------------------------------------------------

--
-- Table structure for table `gcmdevice`
--

CREATE TABLE IF NOT EXISTS `gcmdevice` (
`gcmDeviceID` int(10) unsigned NOT NULL,
  `registrationID` text NOT NULL,
  `manufacturer` varchar(100) DEFAULT NULL,
  `model` varchar(100) DEFAULT NULL,
  `product` varchar(100) DEFAULT NULL,
  `messageCount` int(10) unsigned DEFAULT NULL,
  `dateRegistered` datetime NOT NULL,
  `serialNumber` varchar(255) DEFAULT NULL,
  `androidVersion` varchar(100) DEFAULT NULL,
  `teamID` int(10) unsigned NOT NULL,
  `teamMemberID` int(10) unsigned NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gcmdevice`
--

INSERT INTO `gcmdevice` (`gcmDeviceID`, `registrationID`, `manufacturer`, `model`, `product`, `messageCount`, `dateRegistered`, `serialNumber`, `androidVersion`, `teamID`, `teamMemberID`) VALUES
(4, 'APA91bGiPWz-8yEtqczO3l3zNGN2iOvrV2MsBpqog8WbRq1kCaMEPZq6rOL07oz7D-plDO2UTuFJ58r6RNnCdcsLdiS-QxXvDVsaSpGMROC7yH-Qe2FRvQUMroPGRRVT_jg8AntwVdfF1WyzErnPECjE7Vz2udOlr8XyoNpI7q4vians4ZQxw9A', 'motorola', 'XT1068', 'titan_retgb', 0, '2015-04-29 00:00:00', 'ZX1D22CDC4', '5.0.2', 18, 13),
(5, 'APA91bGiPWz-8yEtqczO3l3zNGN2iOvrV2MsBpqog8WbRq1kCaMEPZq6rOL07oz7D-plDO2UTuFJ58r6RNnCdcsLdiS-QxXvDVsaSpGMROC7yH-Qe2FRvQUMroPGRRVT_jg8AntwVdfF1WyzErnPECjE7Vz2udOlr8XyoNpI7q4vians4ZQxw9A', 'motorola', 'XT1068', 'titan_retgb', 0, '2015-04-29 00:00:00', 'ZX1D22CDC4', '5.0.2', 18, 13),
(6, 'APA91bGiPWz-8yEtqczO3l3zNGN2iOvrV2MsBpqog8WbRq1kCaMEPZq6rOL07oz7D-plDO2UTuFJ58r6RNnCdcsLdiS-QxXvDVsaSpGMROC7yH-Qe2FRvQUMroPGRRVT_jg8AntwVdfF1WyzErnPECjE7Vz2udOlr8XyoNpI7q4vians4ZQxw9A', 'motorola', 'XT1068', 'titan_retgb', 0, '2015-04-29 00:00:00', 'ZX1D22CDC4', '5.0.2', 18, 13),
(7, 'APA91bGiPWz-8yEtqczO3l3zNGN2iOvrV2MsBpqog8WbRq1kCaMEPZq6rOL07oz7D-plDO2UTuFJ58r6RNnCdcsLdiS-QxXvDVsaSpGMROC7yH-Qe2FRvQUMroPGRRVT_jg8AntwVdfF1WyzErnPECjE7Vz2udOlr8XyoNpI7q4vians4ZQxw9A', 'motorola', 'XT1068', 'titan_retgb', 0, '2015-04-29 00:00:00', 'ZX1D22CDC4', '5.0.2', 18, 13),
(8, 'APA91bGiPWz-8yEtqczO3l3zNGN2iOvrV2MsBpqog8WbRq1kCaMEPZq6rOL07oz7D-plDO2UTuFJ58r6RNnCdcsLdiS-QxXvDVsaSpGMROC7yH-Qe2FRvQUMroPGRRVT_jg8AntwVdfF1WyzErnPECjE7Vz2udOlr8XyoNpI7q4vians4ZQxw9A', 'motorola', 'XT1068', 'titan_retgb', 0, '2015-04-29 00:00:00', 'ZX1D22CDC4', '5.0.2', 18, 13),
(9, 'APA91bGiPWz-8yEtqczO3l3zNGN2iOvrV2MsBpqog8WbRq1kCaMEPZq6rOL07oz7D-plDO2UTuFJ58r6RNnCdcsLdiS-QxXvDVsaSpGMROC7yH-Qe2FRvQUMroPGRRVT_jg8AntwVdfF1WyzErnPECjE7Vz2udOlr8XyoNpI7q4vians4ZQxw9A', 'motorola', 'XT1068', 'titan_retgb', 0, '2015-04-29 00:00:00', 'ZX1D22CDC4', '5.0.2', 18, 13),
(10, 'APA91bGiPWz-8yEtqczO3l3zNGN2iOvrV2MsBpqog8WbRq1kCaMEPZq6rOL07oz7D-plDO2UTuFJ58r6RNnCdcsLdiS-QxXvDVsaSpGMROC7yH-Qe2FRvQUMroPGRRVT_jg8AntwVdfF1WyzErnPECjE7Vz2udOlr8XyoNpI7q4vians4ZQxw9A', 'motorola', 'XT1068', 'titan_retgb', 0, '2015-04-29 00:00:00', 'ZX1D22CDC4', '5.0.2', 18, 13),
(11, 'APA91bGiPWz-8yEtqczO3l3zNGN2iOvrV2MsBpqog8WbRq1kCaMEPZq6rOL07oz7D-plDO2UTuFJ58r6RNnCdcsLdiS-QxXvDVsaSpGMROC7yH-Qe2FRvQUMroPGRRVT_jg8AntwVdfF1WyzErnPECjE7Vz2udOlr8XyoNpI7q4vians4ZQxw9A', 'motorola', 'XT1068', 'titan_retgb', 0, '2015-04-29 00:00:00', 'ZX1D22CDC4', '5.0.2', 18, 13),
(12, 'APA91bGiPWz-8yEtqczO3l3zNGN2iOvrV2MsBpqog8WbRq1kCaMEPZq6rOL07oz7D-plDO2UTuFJ58r6RNnCdcsLdiS-QxXvDVsaSpGMROC7yH-Qe2FRvQUMroPGRRVT_jg8AntwVdfF1WyzErnPECjE7Vz2udOlr8XyoNpI7q4vians4ZQxw9A', 'motorola', 'XT1068', 'titan_retgb', 0, '2015-04-29 00:00:00', 'ZX1D22CDC4', '5.0.2', 18, 13),
(13, 'APA91bGiPWz-8yEtqczO3l3zNGN2iOvrV2MsBpqog8WbRq1kCaMEPZq6rOL07oz7D-plDO2UTuFJ58r6RNnCdcsLdiS-QxXvDVsaSpGMROC7yH-Qe2FRvQUMroPGRRVT_jg8AntwVdfF1WyzErnPECjE7Vz2udOlr8XyoNpI7q4vians4ZQxw9A', 'motorola', 'XT1068', 'titan_retgb', 0, '2015-04-29 00:00:00', 'ZX1D22CDC4', '5.0.2', 18, 13),
(14, 'APA91bGiPWz-8yEtqczO3l3zNGN2iOvrV2MsBpqog8WbRq1kCaMEPZq6rOL07oz7D-plDO2UTuFJ58r6RNnCdcsLdiS-QxXvDVsaSpGMROC7yH-Qe2FRvQUMroPGRRVT_jg8AntwVdfF1WyzErnPECjE7Vz2udOlr8XyoNpI7q4vians4ZQxw9A', 'motorola', 'XT1068', 'titan_retgb', 0, '2015-04-29 00:00:00', 'ZX1D22CDC4', '5.0.2', 18, 13),
(15, 'APA91bGiPWz-8yEtqczO3l3zNGN2iOvrV2MsBpqog8WbRq1kCaMEPZq6rOL07oz7D-plDO2UTuFJ58r6RNnCdcsLdiS-QxXvDVsaSpGMROC7yH-Qe2FRvQUMroPGRRVT_jg8AntwVdfF1WyzErnPECjE7Vz2udOlr8XyoNpI7q4vians4ZQxw9A', 'motorola', 'XT1068', 'titan_retgb', 0, '2015-04-29 00:00:00', 'ZX1D22CDC4', '5.0.2', 18, 13),
(16, 'APA91bGiPWz-8yEtqczO3l3zNGN2iOvrV2MsBpqog8WbRq1kCaMEPZq6rOL07oz7D-plDO2UTuFJ58r6RNnCdcsLdiS-QxXvDVsaSpGMROC7yH-Qe2FRvQUMroPGRRVT_jg8AntwVdfF1WyzErnPECjE7Vz2udOlr8XyoNpI7q4vians4ZQxw9A', 'motorola', 'XT1068', 'titan_retgb', 0, '2015-04-29 00:00:00', 'ZX1D22CDC4', '5.0.2', 18, 13),
(17, 'APA91bGiPWz-8yEtqczO3l3zNGN2iOvrV2MsBpqog8WbRq1kCaMEPZq6rOL07oz7D-plDO2UTuFJ58r6RNnCdcsLdiS-QxXvDVsaSpGMROC7yH-Qe2FRvQUMroPGRRVT_jg8AntwVdfF1WyzErnPECjE7Vz2udOlr8XyoNpI7q4vians4ZQxw9A', 'motorola', 'XT1068', 'titan_retgb', 0, '2015-04-29 00:00:00', 'ZX1D22CDC4', '5.0.2', 18, 13),
(18, 'APA91bGiPWz-8yEtqczO3l3zNGN2iOvrV2MsBpqog8WbRq1kCaMEPZq6rOL07oz7D-plDO2UTuFJ58r6RNnCdcsLdiS-QxXvDVsaSpGMROC7yH-Qe2FRvQUMroPGRRVT_jg8AntwVdfF1WyzErnPECjE7Vz2udOlr8XyoNpI7q4vians4ZQxw9A', 'motorola', 'XT1068', 'titan_retgb', 0, '2015-04-29 00:00:00', 'ZX1D22CDC4', '5.0.2', 18, 13),
(19, 'APA91bEp10Z0J7NaRrIup2fHCuY2MAsquRi-jDC5oHl2Cf65nWWLWWny478Sj_u_Yvb-s-yUk6p1smNE_GRcDoYtuERj0-fN0dN2luMIkHUHsS7MQiHA3ApKfBb9VvmBVc7KCcFTZeNYk4B4kmCJtNCY5RPhyuuzIacCTaS_KAB5P3RQaipl0lk', 'samsung', 'GT-I9505', 'jfltexx', 0, '2015-04-29 00:00:00', '9ad6b810', '4.4.2', 18, 13),
(20, 'APA91bEgZuyjKL2nuYpFi-uuJV2TSoML7ClfZok43pksKwzLkcUfyzXzaIezO56B3tA6F9tj7J7pvj8tDpDgEwXAipkkFp24vQgwidPO5khIxbci0W61JJsVDMN_IRDPnMS9l5TDw2DQsHEsMNB9EMKyYX39AafSCiKWY8-HdDg9OljHr-1wcIY', 'samsung', 'GT-N5100', 'kona3gxx', 0, '2015-04-29 00:00:00', '4300fa1e2f8e30af', '4.1.2', 18, 13),
(21, 'APA91bGiPWz-8yEtqczO3l3zNGN2iOvrV2MsBpqog8WbRq1kCaMEPZq6rOL07oz7D-plDO2UTuFJ58r6RNnCdcsLdiS-QxXvDVsaSpGMROC7yH-Qe2FRvQUMroPGRRVT_jg8AntwVdfF1WyzErnPECjE7Vz2udOlr8XyoNpI7q4vians4ZQxw9A', 'motorola', 'XT1068', 'titan_retgb', 0, '2015-04-29 00:00:00', 'ZX1D22CDC4', '5.0.2', 18, 13),
(22, 'APA91bGiPWz-8yEtqczO3l3zNGN2iOvrV2MsBpqog8WbRq1kCaMEPZq6rOL07oz7D-plDO2UTuFJ58r6RNnCdcsLdiS-QxXvDVsaSpGMROC7yH-Qe2FRvQUMroPGRRVT_jg8AntwVdfF1WyzErnPECjE7Vz2udOlr8XyoNpI7q4vians4ZQxw9A', 'motorola', 'XT1068', 'titan_retgb', 0, '2015-04-29 00:00:00', 'ZX1D22CDC4', '5.0.2', 18, 13),
(23, 'APA91bEp10Z0J7NaRrIup2fHCuY2MAsquRi-jDC5oHl2Cf65nWWLWWny478Sj_u_Yvb-s-yUk6p1smNE_GRcDoYtuERj0-fN0dN2luMIkHUHsS7MQiHA3ApKfBb9VvmBVc7KCcFTZeNYk4B4kmCJtNCY5RPhyuuzIacCTaS_KAB5P3RQaipl0lk', 'samsung', 'GT-I9505', 'jfltexx', 0, '2015-04-30 00:00:00', '9ad6b810', '4.4.2', 20, 15),
(24, 'APA91bEp10Z0J7NaRrIup2fHCuY2MAsquRi-jDC5oHl2Cf65nWWLWWny478Sj_u_Yvb-s-yUk6p1smNE_GRcDoYtuERj0-fN0dN2luMIkHUHsS7MQiHA3ApKfBb9VvmBVc7KCcFTZeNYk4B4kmCJtNCY5RPhyuuzIacCTaS_KAB5P3RQaipl0lk', 'samsung', 'GT-I9505', 'jfltexx', 0, '2015-04-06 00:00:00', '9ad6b810', '4.4.2', 20, 15),
(25, 'APA91bGiPWz-8yEtqczO3l3zNGN2iOvrV2MsBpqog8WbRq1kCaMEPZq6rOL07oz7D-plDO2UTuFJ58r6RNnCdcsLdiS-QxXvDVsaSpGMROC7yH-Qe2FRvQUMroPGRRVT_jg8AntwVdfF1WyzErnPECjE7Vz2udOlr8XyoNpI7q4vians4ZQxw9A', 'motorola', 'XT1068', 'titan_retgb', 0, '2015-04-29 00:00:00', 'ZX1D22CDC4', '5.0.2', 18, 13),
(26, 'APA91bGiPWz-8yEtqczO3l3zNGN2iOvrV2MsBpqog8WbRq1kCaMEPZq6rOL07oz7D-plDO2UTuFJ58r6RNnCdcsLdiS-QxXvDVsaSpGMROC7yH-Qe2FRvQUMroPGRRVT_jg8AntwVdfF1WyzErnPECjE7Vz2udOlr8XyoNpI7q4vians4ZQxw9A', 'motorola', 'XT1068', 'titan_retgb', 0, '2015-04-29 00:00:00', 'ZX1D22CDC4', '5.0.2', 18, 13),
(27, 'APA91bGiPWz-8yEtqczO3l3zNGN2iOvrV2MsBpqog8WbRq1kCaMEPZq6rOL07oz7D-plDO2UTuFJ58r6RNnCdcsLdiS-QxXvDVsaSpGMROC7yH-Qe2FRvQUMroPGRRVT_jg8AntwVdfF1WyzErnPECjE7Vz2udOlr8XyoNpI7q4vians4ZQxw9A', 'motorola', 'XT1068', 'titan_retgb', 0, '2015-05-01 00:00:00', 'ZX1D22CDC4', '5.0.2', 18, 13),
(28, 'APA91bGiPWz-8yEtqczO3l3zNGN2iOvrV2MsBpqog8WbRq1kCaMEPZq6rOL07oz7D-plDO2UTuFJ58r6RNnCdcsLdiS-QxXvDVsaSpGMROC7yH-Qe2FRvQUMroPGRRVT_jg8AntwVdfF1WyzErnPECjE7Vz2udOlr8XyoNpI7q4vians4ZQxw9A', 'motorola', 'XT1068', 'titan_retgb', 0, '2015-05-01 00:00:00', 'ZX1D22CDC4', '5.0.2', 18, 13),
(29, 'APA91bGiPWz-8yEtqczO3l3zNGN2iOvrV2MsBpqog8WbRq1kCaMEPZq6rOL07oz7D-plDO2UTuFJ58r6RNnCdcsLdiS-QxXvDVsaSpGMROC7yH-Qe2FRvQUMroPGRRVT_jg8AntwVdfF1WyzErnPECjE7Vz2udOlr8XyoNpI7q4vians4ZQxw9A', 'motorola', 'XT1068', 'titan_retgb', 0, '2015-05-02 00:00:00', 'ZX1D22CDC4', '5.0.2', 18, 13),
(30, 'APA91bGiPWz-8yEtqczO3l3zNGN2iOvrV2MsBpqog8WbRq1kCaMEPZq6rOL07oz7D-plDO2UTuFJ58r6RNnCdcsLdiS-QxXvDVsaSpGMROC7yH-Qe2FRvQUMroPGRRVT_jg8AntwVdfF1WyzErnPECjE7Vz2udOlr8XyoNpI7q4vians4ZQxw9A', 'motorola', 'XT1068', 'titan_retgb', 0, '2015-05-02 00:00:00', 'ZX1D22CDC4', '5.0.2', 18, 13),
(31, 'APA91bGiPWz-8yEtqczO3l3zNGN2iOvrV2MsBpqog8WbRq1kCaMEPZq6rOL07oz7D-plDO2UTuFJ58r6RNnCdcsLdiS-QxXvDVsaSpGMROC7yH-Qe2FRvQUMroPGRRVT_jg8AntwVdfF1WyzErnPECjE7Vz2udOlr8XyoNpI7q4vians4ZQxw9A', 'motorola', 'XT1068', 'titan_retgb', 0, '2015-05-02 00:00:00', 'ZX1D22CDC4', '5.0.2', 18, 13),
(32, 'APA91bGiPWz-8yEtqczO3l3zNGN2iOvrV2MsBpqog8WbRq1kCaMEPZq6rOL07oz7D-plDO2UTuFJ58r6RNnCdcsLdiS-QxXvDVsaSpGMROC7yH-Qe2FRvQUMroPGRRVT_jg8AntwVdfF1WyzErnPECjE7Vz2udOlr8XyoNpI7q4vians4ZQxw9A', 'motorola', 'XT1068', 'titan_retgb', 0, '2015-05-02 00:00:00', 'ZX1D22CDC4', '5.0.2', 18, 13),
(33, 'APA91bGiPWz-8yEtqczO3l3zNGN2iOvrV2MsBpqog8WbRq1kCaMEPZq6rOL07oz7D-plDO2UTuFJ58r6RNnCdcsLdiS-QxXvDVsaSpGMROC7yH-Qe2FRvQUMroPGRRVT_jg8AntwVdfF1WyzErnPECjE7Vz2udOlr8XyoNpI7q4vians4ZQxw9A', 'motorola', 'XT1068', 'titan_retgb', 0, '2015-05-05 00:00:00', 'ZX1D22CDC4', '5.0.2', 18, 13),
(34, 'APA91bGiPWz-8yEtqczO3l3zNGN2iOvrV2MsBpqog8WbRq1kCaMEPZq6rOL07oz7D-plDO2UTuFJ58r6RNnCdcsLdiS-QxXvDVsaSpGMROC7yH-Qe2FRvQUMroPGRRVT_jg8AntwVdfF1WyzErnPECjE7Vz2udOlr8XyoNpI7q4vians4ZQxw9A', 'motorola', 'XT1068', 'titan_retgb', 0, '2015-05-05 00:00:00', 'ZX1D22CDC4', '5.0.2', 18, 13);

-- --------------------------------------------------------

--
-- Table structure for table `insect`
--

CREATE TABLE IF NOT EXISTS `insect` (
`insectID` int(10) unsigned NOT NULL,
  `groupName` varchar(255) NOT NULL,
  `sensitivityScore` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `insect`
--

INSERT INTO `insect` (`insectID`, `groupName`, `sensitivityScore`) VALUES
(1, 'Flat worms', 3),
(2, 'Worms', 2),
(3, 'Leeches', 2),
(4, 'Crabs', 6),
(5, 'Stoneflies', 17),
(6, 'Minnow mayflies', 5),
(7, 'Other mayflies', 11),
(8, 'Damselflies', 4),
(9, 'Dragonflies', 6),
(10, 'Bugs or beetles', 5),
(11, 'Caddisflies', 9),
(12, 'True flies', 2),
(13, 'Snails', 4),
(14, 'shrimps', 6);

-- --------------------------------------------------------

--
-- Table structure for table `insectimage`
--

CREATE TABLE IF NOT EXISTS `insectimage` (
`insectImageID` int(10) unsigned NOT NULL,
  `insectID` int(10) unsigned NOT NULL,
  `uri` varchar(255) NOT NULL,
  `dateRegistered` datetime NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `insectimage`
--

INSERT INTO `insectimage` (`insectImageID`, `insectID`, `uri`, `dateRegistered`) VALUES
(1, 1, 'worms', '2015-02-03 04:06:08'),
(2, 4, 'crab', '2015-03-03 00:00:00'),
(3, 9, 'dragonflies', '2015-03-03 00:00:00'),
(4, 3, 'hero_leech', '2015-03-03 00:00:00'),
(5, 14, 'shrimp', '2015-03-03 00:00:00'),
(6, 13, 'snail', '2015-03-03 00:00:00'),
(7, 5, 'stonefly_whole', '2015-03-03 00:00:00'),
(8, 12, 'trueflie', '2015-03-03 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `province`
--

CREATE TABLE IF NOT EXISTS `province` (
`provinceID` int(10) unsigned NOT NULL,
  `countryID` int(10) unsigned NOT NULL,
  `provinceName` varchar(255) NOT NULL,
  `lattitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `province`
--

INSERT INTO `province` (`provinceID`, `countryID`, `provinceName`, `lattitude`, `longitude`) VALUES
(1, 1, 'Eastern Cape', 32, 27),
(2, 1, 'Free State', 28, 27),
(3, 1, 'Gauteng', 26, 28),
(4, 1, 'Kwa-Zulu Natal', 29, 31),
(5, 1, 'Limpopo', 24, 29),
(6, 1, 'Mpumalanga', 26, 30),
(7, 1, 'Northern Cape', 30, 22),
(8, 1, 'North West', 27, 26),
(9, 1, 'Western Cape', 34, 20);

-- --------------------------------------------------------

--
-- Table structure for table `river`
--

CREATE TABLE IF NOT EXISTS `river` (
`riverID` int(10) unsigned NOT NULL,
  `riverName` varchar(255) NOT NULL,
  `originLatitude` double DEFAULT NULL,
  `originLongitude` double DEFAULT NULL,
  `endLatitude` double DEFAULT NULL,
  `endLongitude` double DEFAULT NULL,
  `originCountryID` int(10) unsigned DEFAULT NULL,
  `endCountryID` int(10) unsigned DEFAULT NULL,
  `dateRegistered` datetime NOT NULL,
  `imageUri` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `river`
--

INSERT INTO `river` (`riverID`, `riverName`, `originLatitude`, `originLongitude`, `endLatitude`, `endLongitude`, `originCountryID`, `endCountryID`, `dateRegistered`, `imageUri`) VALUES
(1, 'Apies_River', 25, 23, 21, 22, 1, 1, '2015-02-03 10:54:25', 'olifantsriver'),
(2, 'Great koi', -23, 28, -24, 23, 1, 2, '2015-03-09 00:00:00', 'greatkeiriver'),
(3, 'Sea River', -23.3, 28, -24.4, 28.4, 2, 3, '2015-03-09 00:00:00', 'limpoporiver'),
(4, 'Berg river', 222.22, 33.333, 222.55, 555.33, 1, 1, '2015-03-10 00:00:00', 'bergriver'),
(5, 'Blyde river', 0.25, 0.27, 0.29, 0.72, 2, 2, '2015-03-09 00:00:00', 'blyderiver'),
(8, 'Breerde river', 0.477, 0.555, 0.22, 0.28, 3, 3, '2015-03-10 00:00:00', 'breerderiver'),
(9, 'Crocodile river', 0.77, 0.88, 0.99, 0.1, 4, 4, '2015-03-10 00:00:00', 'crocodileriver'),
(10, 'Gamtoos river', 0.33, 0.55, 0.88, 0.99, 5, 5, '2015-03-10 00:00:00', 'gamtoosriver'),
(11, 'Great Fish river', 0.666, 0.333, 0.488, 0.985, 1, 1, '2015-03-10 00:00:00', 'greatfishriver'),
(12, 'Komati river', 2.33, 1.33, 10.23, 11.32, 1, 2, '2015-03-10 00:00:00', 'komatiriver');

-- --------------------------------------------------------

--
-- Table structure for table `rivertown`
--

CREATE TABLE IF NOT EXISTS `rivertown` (
`riverTownID` int(10) unsigned NOT NULL,
  `riverID` int(10) unsigned NOT NULL,
  `townID` int(10) unsigned NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rivertown`
--

INSERT INTO `rivertown` (`riverTownID`, `riverID`, `townID`) VALUES
(1, 1, 1),
(2, 1, 2),
(10, 1, 9),
(3, 2, 1),
(6, 2, 8),
(5, 3, 7),
(4, 4, 6),
(7, 5, 8),
(8, 9, 6),
(11, 9, 11),
(9, 11, 10);

-- --------------------------------------------------------

--
-- Table structure for table `team`
--

CREATE TABLE IF NOT EXISTS `team` (
`teamID` int(10) unsigned NOT NULL,
  `townID` int(10) unsigned NOT NULL,
  `teamName` varchar(255) NOT NULL,
  `dateRegistered` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `teamImage` varchar(1000) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `team`
--

INSERT INTO `team` (`teamID`, `townID`, `teamName`, `dateRegistered`, `teamImage`) VALUES
(1, 1, 'CodetribeSA', '2015-02-03 18:28:52', ''),
(2, 2, 'Magona', '2015-03-10 10:12:05', ''),
(3, 6, 'Malamulele', '2015-03-11 11:11:09', ''),
(4, 7, 'Acardia', '2015-03-10 07:00:00', ''),
(5, 1, 'Central', '2015-03-10 10:05:12', ''),
(6, 2, 'Mlab', '2015-03-10 07:00:00', ''),
(7, 2, 'Geekulcha', '2015-03-10 07:00:00', ''),
(8, 7, 'Innovation Hub', '2015-03-10 09:04:04', ''),
(9, 1, 'Sowertech', '2015-03-12 07:06:12', ''),
(18, 6, 'sina', '2015-03-23 07:00:00', 'upload'),
(19, 6, 'toby', '2015-03-31 07:00:00', 'upload'),
(20, 2, 'MinisassTeam1', '2015-04-23 07:00:00', 'upload'),
(21, 8, 'dgd', '2015-04-29 07:00:00', 'upload'),
(23, 10, 'Mini', '2015-04-29 07:00:00', 'upload');

-- --------------------------------------------------------

--
-- Table structure for table `teammember`
--

CREATE TABLE IF NOT EXISTS `teammember` (
`teamMemberID` int(10) unsigned NOT NULL,
  `teamID` int(10) unsigned NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `cellphone` varchar(45) DEFAULT NULL,
  `dateRegistered` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `pin` varchar(45) NOT NULL,
  `activeFlag` int(10) unsigned DEFAULT NULL,
  `teamMemberImage` varchar(1000) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teammember`
--

INSERT INTO `teammember` (`teamMemberID`, `teamID`, `firstName`, `lastName`, `email`, `cellphone`, `dateRegistered`, `pin`, `activeFlag`, `teamMemberImage`) VALUES
(1, 1, 'SASA', 'Chauke', 'chaukekurisani@gmail.com', '0828157702', '2015-02-03 18:33:56', '1234', 1, ''),
(2, 1, 'Ely', 'Gabon', 'Ely@geekulcha.com', '0715861234', '2015-03-10 09:08:08', '12345', 1, ''),
(3, 2, 'Rudzani', 'Baloyi', 'baloyr@gmail.com', '0828456110', '2015-03-10 07:00:00', '54321', 1, ''),
(4, 3, 'Mathapelo', 'Mpama', 'mathapelo@empirestate.com', '0725468102', '2015-03-10 07:00:00', '54321', 1, ''),
(5, 4, 'George', 'Kapoya', 'georgekapoya@gmail.com', '0828756123', '2015-03-10 10:08:09', '12300', 0, ''),
(6, 5, 'Sifiso', 'Mtshweni', 'sifiso@gmail.com', '0828155070', '2015-03-10 07:00:00', '00000', 1, ''),
(7, 7, 'Joelle', 'Umonti', 'joelle@geekulcha.com', '0112456310', '2015-03-10 07:00:00', '78945', 1, ''),
(8, 8, 'Herve', 'Ntubi', 'herve@geekulcha.com', '0213654789', '2015-03-10 07:00:00', '4561', 1, ''),
(9, 9, 'Tau', 'Motaung', 'tau@gmail.com', '0789634521', '2015-03-10 07:00:00', '9632', 1, ''),
(10, 2, 'Days', 'Mabasa', 'days@yahoo.com', '0845612310', '2015-03-10 07:00:00', '87965', 1, ''),
(13, 18, 'manga', 'lamba', 'snpeace.sifiso@gmail.com', '0612365980', '2015-03-10 07:00:00', '341288', 0, 'upload'),
(14, 19, 'sun', 'moon', 'soon@d.com', '0921425412', '2015-03-31 07:00:00', '761613', 0, 'upload'),
(15, 20, 'Chris', 'Skosana', 'nkululekocgs@gmail.com', '0767887523', '2015-04-23 07:00:00', '685581', 0, 'upload'),
(16, 21, 'fgfgf', 'fgfgg', 'dgfvv@gvv', '', '2015-04-29 07:00:00', '550850', 0, 'upload'),
(17, 23, 'Kurisani', 'Chauke', 'chaukekurisani@gmail.com', '0828157702', '2015-04-29 07:00:00', '260668', 0, 'upload');

-- --------------------------------------------------------

--
-- Table structure for table `town`
--

CREATE TABLE IF NOT EXISTS `town` (
`townID` int(10) unsigned NOT NULL,
  `provinceID` int(10) unsigned NOT NULL,
  `townName` varchar(255) NOT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `town`
--

INSERT INTO `town` (`townID`, `provinceID`, `townName`, `latitude`, `longitude`) VALUES
(1, 4, 'Adams Mission', 30, -30.05),
(2, 3, 'SouthofPretoria', 25, 20),
(6, 2, 'Southeast of Bethlehem', 25, 20),
(7, 6, 'North of Wakkerstroom', 25, 20),
(8, 2, 'sophiatown', 222.3654, 83333.3333),
(9, 3, 'dube', 100, 0.000111),
(10, 4, 'Zola', 0.235, 0.365),
(11, 5, 'Dawnpark', 0.23, 0.55),
(12, 7, 'Giyani', 0.88, 0.333),
(13, 9, 'Tzaneen', 20.33, 33.02);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
 ADD PRIMARY KEY (`categoryId`), ADD UNIQUE KEY `index2` (`categoryName`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
 ADD PRIMARY KEY (`commentID`);

--
-- Indexes for table `conditions`
--
ALTER TABLE `conditions`
 ADD PRIMARY KEY (`conditionsID`), ADD UNIQUE KEY `index2` (`conditionName`), ADD KEY `index3` (`categoryID`), ADD KEY `fk23` (`categoryID`);

--
-- Indexes for table `country`
--
ALTER TABLE `country`
 ADD PRIMARY KEY (`countryID`), ADD UNIQUE KEY `index2` (`countryName`);

--
-- Indexes for table `errorstore`
--
ALTER TABLE `errorstore`
 ADD PRIMARY KEY (`errorStoreID`), ADD KEY `dateOccured` (`dateOccured`);

--
-- Indexes for table `errorstoreandroid`
--
ALTER TABLE `errorstoreandroid`
 ADD PRIMARY KEY (`errorStoreAndroidID`), ADD KEY `riverID` (`riverID`);

--
-- Indexes for table `evaluation`
--
ALTER TABLE `evaluation`
 ADD PRIMARY KEY (`evaluationID`), ADD KEY `index2` (`teamMemberID`), ADD KEY `index3` (`evaluationSiteID`), ADD KEY `fk10` (`teamMemberID`), ADD KEY `fk11` (`evaluationSiteID`), ADD KEY `index6` (`conditionsID`), ADD KEY `fk22` (`conditionsID`);

--
-- Indexes for table `evaluationcomment`
--
ALTER TABLE `evaluationcomment`
 ADD PRIMARY KEY (`evaluationCommentID`), ADD KEY `index2` (`evaluationID`), ADD KEY `index3` (`commentID`), ADD KEY `fk24` (`evaluationID`), ADD KEY `fk25` (`commentID`);

--
-- Indexes for table `evaluationimage`
--
ALTER TABLE `evaluationimage`
 ADD PRIMARY KEY (`evaluationImageID`), ADD KEY `index2` (`evaluationID`), ADD KEY `fk18` (`evaluationID`);

--
-- Indexes for table `evaluationinsect`
--
ALTER TABLE `evaluationinsect`
 ADD PRIMARY KEY (`evaluationInsectID`), ADD UNIQUE KEY `index6` (`evaluationID`,`insectID`), ADD KEY `index2` (`evaluationID`), ADD KEY `fk13` (`evaluationID`), ADD KEY `index4` (`insectID`), ADD KEY `fk14` (`insectID`);

--
-- Indexes for table `evaluationsite`
--
ALTER TABLE `evaluationsite`
 ADD PRIMARY KEY (`evaluationSiteID`), ADD KEY `index2` (`riverID`), ADD KEY `fk9` (`categoryID`), ADD KEY `index5` (`categoryID`), ADD KEY `fk19` (`riverID`);

--
-- Indexes for table `gcmdevice`
--
ALTER TABLE `gcmdevice`
 ADD PRIMARY KEY (`gcmDeviceID`), ADD KEY `teamID` (`teamID`), ADD KEY `teamMemberID` (`teamMemberID`);

--
-- Indexes for table `insect`
--
ALTER TABLE `insect`
 ADD PRIMARY KEY (`insectID`), ADD UNIQUE KEY `index2` (`groupName`);

--
-- Indexes for table `insectimage`
--
ALTER TABLE `insectimage`
 ADD PRIMARY KEY (`insectImageID`), ADD UNIQUE KEY `index4` (`insectID`,`uri`), ADD KEY `index2` (`insectID`), ADD KEY `fk12` (`insectID`);

--
-- Indexes for table `province`
--
ALTER TABLE `province`
 ADD PRIMARY KEY (`provinceID`), ADD UNIQUE KEY `index4` (`countryID`,`provinceName`), ADD KEY `index2` (`countryID`), ADD KEY `fk1` (`countryID`);

--
-- Indexes for table `river`
--
ALTER TABLE `river`
 ADD PRIMARY KEY (`riverID`), ADD UNIQUE KEY `index6` (`riverName`), ADD KEY `index2` (`originCountryID`), ADD KEY `index3` (`endCountryID`), ADD KEY `fk3` (`originCountryID`), ADD KEY `fk4` (`endCountryID`);

--
-- Indexes for table `rivertown`
--
ALTER TABLE `rivertown`
 ADD PRIMARY KEY (`riverTownID`), ADD UNIQUE KEY `index6` (`riverID`,`townID`), ADD KEY `index2` (`riverID`), ADD KEY `index3` (`townID`), ADD KEY `fk5` (`riverID`), ADD KEY `fk6` (`townID`);

--
-- Indexes for table `team`
--
ALTER TABLE `team`
 ADD PRIMARY KEY (`teamID`), ADD UNIQUE KEY `index4` (`townID`,`teamName`), ADD KEY `index2` (`townID`), ADD KEY `fk8` (`townID`);

--
-- Indexes for table `teammember`
--
ALTER TABLE `teammember`
 ADD PRIMARY KEY (`teamMemberID`), ADD KEY `index2` (`teamID`), ADD KEY `fk7` (`teamID`);

--
-- Indexes for table `town`
--
ALTER TABLE `town`
 ADD PRIMARY KEY (`townID`), ADD UNIQUE KEY `index4` (`provinceID`,`townName`), ADD KEY `index2` (`provinceID`), ADD KEY `fk2` (`provinceID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
MODIFY `categoryId` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
MODIFY `commentID` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `conditions`
--
ALTER TABLE `conditions`
MODIFY `conditionsID` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `country`
--
ALTER TABLE `country`
MODIFY `countryID` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `errorstore`
--
ALTER TABLE `errorstore`
MODIFY `errorStoreID` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `errorstoreandroid`
--
ALTER TABLE `errorstoreandroid`
MODIFY `errorStoreAndroidID` int(10) unsigned NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `evaluation`
--
ALTER TABLE `evaluation`
MODIFY `evaluationID` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=163;
--
-- AUTO_INCREMENT for table `evaluationcomment`
--
ALTER TABLE `evaluationcomment`
MODIFY `evaluationCommentID` int(10) unsigned NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `evaluationimage`
--
ALTER TABLE `evaluationimage`
MODIFY `evaluationImageID` int(10) unsigned NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `evaluationinsect`
--
ALTER TABLE `evaluationinsect`
MODIFY `evaluationInsectID` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=335;
--
-- AUTO_INCREMENT for table `evaluationsite`
--
ALTER TABLE `evaluationsite`
MODIFY `evaluationSiteID` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=164;
--
-- AUTO_INCREMENT for table `gcmdevice`
--
ALTER TABLE `gcmdevice`
MODIFY `gcmDeviceID` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=35;
--
-- AUTO_INCREMENT for table `insect`
--
ALTER TABLE `insect`
MODIFY `insectID` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table `insectimage`
--
ALTER TABLE `insectimage`
MODIFY `insectImageID` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `province`
--
ALTER TABLE `province`
MODIFY `provinceID` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `river`
--
ALTER TABLE `river`
MODIFY `riverID` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `rivertown`
--
ALTER TABLE `rivertown`
MODIFY `riverTownID` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `team`
--
ALTER TABLE `team`
MODIFY `teamID` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=24;
--
-- AUTO_INCREMENT for table `teammember`
--
ALTER TABLE `teammember`
MODIFY `teamMemberID` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT for table `town`
--
ALTER TABLE `town`
MODIFY `townID` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=14;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `conditions`
--
ALTER TABLE `conditions`
ADD CONSTRAINT `fk23` FOREIGN KEY (`categoryID`) REFERENCES `category` (`categoryId`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `errorstoreandroid`
--
ALTER TABLE `errorstoreandroid`
ADD CONSTRAINT `errorstoreandroid_ibfk_1` FOREIGN KEY (`riverID`) REFERENCES `river` (`riverID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `evaluation`
--
ALTER TABLE `evaluation`
ADD CONSTRAINT `fk10` FOREIGN KEY (`teamMemberID`) REFERENCES `teammember` (`teamMemberID`) ON DELETE CASCADE ON UPDATE NO ACTION,
ADD CONSTRAINT `fk11` FOREIGN KEY (`evaluationSiteID`) REFERENCES `evaluationsite` (`evaluationSiteID`) ON DELETE CASCADE ON UPDATE NO ACTION,
ADD CONSTRAINT `fk22` FOREIGN KEY (`conditionsID`) REFERENCES `conditions` (`conditionsID`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `evaluationcomment`
--
ALTER TABLE `evaluationcomment`
ADD CONSTRAINT `fk24` FOREIGN KEY (`evaluationID`) REFERENCES `evaluation` (`evaluationID`) ON DELETE CASCADE ON UPDATE NO ACTION,
ADD CONSTRAINT `fk25` FOREIGN KEY (`commentID`) REFERENCES `comment` (`commentID`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `evaluationimage`
--
ALTER TABLE `evaluationimage`
ADD CONSTRAINT `fk18` FOREIGN KEY (`evaluationID`) REFERENCES `evaluation` (`evaluationID`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `evaluationinsect`
--
ALTER TABLE `evaluationinsect`
ADD CONSTRAINT `fk13` FOREIGN KEY (`evaluationID`) REFERENCES `evaluation` (`evaluationID`) ON DELETE CASCADE ON UPDATE NO ACTION,
ADD CONSTRAINT `fk14` FOREIGN KEY (`insectID`) REFERENCES `insect` (`insectID`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `evaluationsite`
--
ALTER TABLE `evaluationsite`
ADD CONSTRAINT `fk19` FOREIGN KEY (`riverID`) REFERENCES `river` (`riverID`) ON DELETE CASCADE ON UPDATE NO ACTION,
ADD CONSTRAINT `fk9` FOREIGN KEY (`categoryID`) REFERENCES `category` (`categoryId`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `gcmdevice`
--
ALTER TABLE `gcmdevice`
ADD CONSTRAINT `gcmdevice_ibfk_1` FOREIGN KEY (`teamID`) REFERENCES `team` (`teamID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `gcmdevice_ibfk_2` FOREIGN KEY (`teamMemberID`) REFERENCES `teammember` (`teamMemberID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `insectimage`
--
ALTER TABLE `insectimage`
ADD CONSTRAINT `fk12` FOREIGN KEY (`insectID`) REFERENCES `insect` (`insectID`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `province`
--
ALTER TABLE `province`
ADD CONSTRAINT `ak` FOREIGN KEY (`countryID`) REFERENCES `country` (`countryID`) ON DELETE CASCADE ON UPDATE NO ACTION,
ADD CONSTRAINT `fk1` FOREIGN KEY (`countryID`) REFERENCES `country` (`countryID`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `river`
--
ALTER TABLE `river`
ADD CONSTRAINT `fk3` FOREIGN KEY (`originCountryID`) REFERENCES `country` (`countryID`) ON DELETE CASCADE ON UPDATE NO ACTION,
ADD CONSTRAINT `fk4` FOREIGN KEY (`endCountryID`) REFERENCES `country` (`countryID`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `rivertown`
--
ALTER TABLE `rivertown`
ADD CONSTRAINT `fk5` FOREIGN KEY (`riverID`) REFERENCES `river` (`riverID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk6` FOREIGN KEY (`townID`) REFERENCES `town` (`townID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `team`
--
ALTER TABLE `team`
ADD CONSTRAINT `fk8` FOREIGN KEY (`townID`) REFERENCES `town` (`townID`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `teammember`
--
ALTER TABLE `teammember`
ADD CONSTRAINT `fk7` FOREIGN KEY (`teamID`) REFERENCES `team` (`teamID`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `town`
--
ALTER TABLE `town`
ADD CONSTRAINT `fk2` FOREIGN KEY (`provinceID`) REFERENCES `province` (`provinceID`) ON DELETE CASCADE ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
