-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 20, 2019 at 02:47 PM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `simple_faculte_budget`
--

--
-- Dumping data for table `budget_compte_budgitaire`
--

INSERT INTO `budget_compte_budgitaire` (`id`, `reference_compte_budgitaire`, `budget_entite_administratif`, `compte_budgitaire`, `detailles_budget`) VALUES
(288, '2018-Rech-Math-1', 285, 287, 289),
(291, '2018-Rech-Math-2', 285, 290, 292),
(296, '2018-Rech-Phys-3', 293, 295, 297),
(299, '2018-Rech-Phys-4', 293, 298, 300),
(306, '2018-Enei-Info-5', 303, 305, 307),
(309, '2018-Enei-Info-6', 303, 308, 310),
(314, '2018-Enei-Phys-7', 311, 313, 315),
(317, '2018-Enei-Phys-8', 311, 316, 318);

--
-- Dumping data for table `budget_entite_administratif`
--

INSERT INTO `budget_entite_administratif` (`id`, `reference_entite_administratif`, `budget_sous_projet`, `detailles_budget`) VALUES
(285, 'Math', 283, 286),
(293, 'Physique', 283, 294),
(303, 'Informatique', 301, 304),
(311, 'Physique', 301, 312);

--
-- Dumping data for table `budget_faculte`
--

INSERT INTO `budget_faculte` (`id`, `annee`, `detailles_budget`) VALUES
(281, 2018, 282);

--
-- Dumping data for table `budget_sous_projet`
--

INSERT INTO `budget_sous_projet` (`id`, `reference_sous_projet`, `budget_faculte`, `detailles_budget`) VALUES
(283, 'Recherche', 281, 284),
(301, 'Eneignement', 281, 302);

--
-- Dumping data for table `compte_budgitaire`
--

INSERT INTO `compte_budgitaire` (`id`, `code`, `libelle`) VALUES
(295, 'EE3', 'OUTILS '),
(298, 'EE4', 'ACHAT'),
(305, 'EE7', 'ACHAT IMPRIMANTES'),
(308, 'EE8', 'MAINTENANCE'),
(290, 'EE2', 'VENTE'),
(287, 'EE1', 'ACHAT'),
(313, 'EE5', 'MATERIELS'),
(316, 'EE6', 'SORTIE');

--
-- Dumping data for table `detailles_budget`
--

INSERT INTO `detailles_budget` (`id`, `antecedent`, `credit_ouvert_estimatif`, `credit_ouvert_reel`, `engage_non_paye`, `engage_paye`, `reliquat_estimatif`, `reliquat_non_pay_reel`, `reliquat_non_paye_estimatif`, `reliquat_paye_estimatif`, `reliquat_payereel`, `reliquat_reel`) VALUES
(310, 0, 1900, 1000, 940, 0, 1900, 0, 0, 0, 0, 1000),
(312, 0, 5000, 3000, 1000, 0, 735, 0, 0, 0, 0, 680),
(315, 0, 2300, 1200, 490, 0, 2300, 0, 0, 0, 0, 1200),
(282, 0, 50000, 45000, 10000, 0, 25000, 0, 0, 0, 0, 24900),
(302, 0, 13000, 10100, 4000, 0, 3340, 0, 0, 0, 0, 3650),
(294, 0, 5900, 4890, 1300, 0, 1060, 0, 0, 0, 0, 1176),
(292, 0, 1600, 1000, 430, 0, 1600, 0, 0, 0, 0, 1000),
(284, 0, 12000, 10000, 3000, 0, 500, 0, 0, 0, 0, 910),
(297, 0, 3000, 2120, 700, 0, 3000, 0, 0, 0, 0, 2120),
(300, 0, 1840, 1594, 800, 0, 1840, 0, 0, 0, 0, 1594),
(307, 0, 2000, 1100, 820, 0, 2000, 0, 0, 0, 0, 1100),
(304, 0, 4660, 3450, 1900, 0, 760, 0, 0, 0, 0, 1350),
(318, 0, 1965, 1120, 320, 0, 1965, 0, 0, 0, 0, 1120),
(286, 0, 5600, 4200, 1500, 0, 1800, 0, 0, 0, 0, 1900),
(289, 0, 2200, 1300, 500, 0, 2200, 0, 0, 0, 0, 1300);

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(319),
(319),
(319),
(319),
(319),
(319);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
