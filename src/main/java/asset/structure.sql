-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  mar. 26 mars 2019 à 21:39
-- Version du serveur :  10.1.37-MariaDB
-- Version de PHP :  7.3.1

SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `simple_faculte_budget`
--

-- --------------------------------------------------------

--
-- Structure de la table `budget_compte_budgitaire`
--

CREATE TABLE `budget_compte_budgitaire` (
  `id` bigint(20) NOT NULL,
  `reference_compte_budgitaire` varchar(255) DEFAULT NULL,
  `budget_entite_administratif` bigint(20) DEFAULT NULL,
  `compte_budgitaire` bigint(20) DEFAULT NULL,
  `detailles_budget` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `budget_entite_administratif`
--

CREATE TABLE `budget_entite_administratif` (
  `id` bigint(20) NOT NULL,
  `reference_entite_administratif` varchar(255) DEFAULT NULL,
  `budget_sous_projet` bigint(20) DEFAULT NULL,
  `detailles_budget` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `budget_faculte`
--

CREATE TABLE `budget_faculte` (
  `id` bigint(20) NOT NULL,
  `annee` int(11) NOT NULL,
  `detailles_budget` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `budget_sous_projet`
--

CREATE TABLE `budget_sous_projet` (
  `id` bigint(20) NOT NULL,
  `reference_sous_projet` varchar(255) DEFAULT NULL,
  `budget_faculte` bigint(20) DEFAULT NULL,
  `detailles_budget` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `compte_budgitaire`
--

CREATE TABLE `compte_budgitaire` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `detailles_budget`
--

CREATE TABLE `detailles_budget` (
  `id` bigint(20) NOT NULL,
  `antecedent` double NOT NULL,
  `credit_ouvert_estimatif` double NOT NULL,
  `credit_ouvert_reel` double NOT NULL,
  `engage_non_paye` double NOT NULL,
  `engage_paye` double NOT NULL,
  `reliquat_estimatif` double NOT NULL,
  `reliquat_non_pay_reel` double NOT NULL,
  `reliquat_non_paye_estimatif` double NOT NULL,
  `reliquat_paye_estimatif` double NOT NULL,
  `reliquat_payereel` double NOT NULL,
  `reliquat_reel` double NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `budget_compte_budgitaire`
--
ALTER TABLE `budget_compte_budgitaire`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK84jr4m5c3jjhm52rdwl94g0ws` (`budget_entite_administratif`),
  ADD KEY `FKjdb825yrbbd921guphbe83bx8` (`compte_budgitaire`),
  ADD KEY `FKi8kxtpub82qjmos85no8t2p3e` (`detailles_budget`);

--
-- Index pour la table `budget_entite_administratif`
--
ALTER TABLE `budget_entite_administratif`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK7nvkky1m5paa8eeev4717yddm` (`budget_sous_projet`),
  ADD KEY `FKecsi7abgpj6jqsq1ejwydp20d` (`detailles_budget`);

--
-- Index pour la table `budget_faculte`
--
ALTER TABLE `budget_faculte`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK4fm7urfmu8kdk2vi1a5uw6imr` (`detailles_budget`);

--
-- Index pour la table `budget_sous_projet`
--
ALTER TABLE `budget_sous_projet`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1jnfgyovv00hph2kpc82rcffx` (`budget_faculte`),
  ADD KEY `FKgbrygfii8su2mipc2vfcvdplo` (`detailles_budget`);

--
-- Index pour la table `compte_budgitaire`
--
ALTER TABLE `compte_budgitaire`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `detailles_budget`
--
ALTER TABLE `detailles_budget`
  ADD PRIMARY KEY (`id`);
SET FOREIGN_KEY_CHECKS=1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
