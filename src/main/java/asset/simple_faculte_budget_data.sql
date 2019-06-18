-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 18, 2019 at 07:06 AM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.1.28

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
-- Database: `simple_faculte_budget`
--

--
-- Dumping data for table `budget_compte_budgitaire`
--

INSERT INTO `budget_compte_budgitaire` (`id`, `reference`, `budget_sous_projet`, `compte_budgitaire`, `detailles_budget`) VALUES
(509, '', 507, 1, 508),
(511, NULL, 507, 2, 510),
(515, '', 513, 1, 514),
(517, NULL, 513, 2, 516),
(525, '', 507, 3, 524),
(527, NULL, 513, 5, 526);

--
-- Dumping data for table `budget_faculte`
--

INSERT INTO `budget_faculte` (`id`, `annee`, `detailles_budget`) VALUES
(158, 2018, 159),
(217, 2019, 218);

--
-- Dumping data for table `budget_projet`
--

INSERT INTO `budget_projet` (`id`, `reference_projet`, `budget_faculte`, `detailles_budget`) VALUES
(505, 'Dépenses de l\'administration', 217, 504),
(519, 'Travaux de recherche et préstations de services', 217, 518),
(531, 'Enseignements & recherche', 217, 530);

--
-- Dumping data for table `budget_sous_projet`
--

INSERT INTO `budget_sous_projet` (`id`, `reference_sous_projet`, `budget_projet`, `detailles_budget`) VALUES
(507, 'Personnel', 505, 506),
(513, 'Administration', 505, 512),
(529, 'Divers', 505, 528);

--
-- Dumping data for table `compte_budgitaire`
--

INSERT INTO `compte_budgitaire` (`id`, `code`, `libelle`) VALUES
(1, '23271000', 'Aménagement, agencement & installation'),
(2, '23272000', 'Etudes liées à l\'aménagement des bâtiments  administratifs'),
(3, '23320000', 'Achat en renouvellement de matériel d\'atelier et de l\'outillage'),
(4, '23321100', 'Achat en renouvellement du Matériel d\'enseignement '),
(5, '23321200', 'Matériel médical'),
(6, '23321300', 'Matériel scientifique '),
(7, '23321400', 'Matériel de Literie et Couchage'),
(8, '23510000', 'Achat en renouvellement de Mobilier de bureau'),
(9, '23520000', 'Achat en renouvellement de Matériel de bureau'),
(10, '23551000', 'Achat en renouvellement de Matériel informatique et logiciels'),
(11, '23580000', 'Achat en renouvellement de mobilier d\'enseignement et de laboratoire '),
(12, '23802000', 'Matériel de cuisine et de buanderie'),
(13, '23803000', 'Achat d\'ouvrages techniques et pédagogiques'),
(14, '23805000', 'Achat de matériel et mobilier pour l\'internat'),
(15, '44970000', 'Crédits à programmer'),
(16, '61211000', 'Achat de matières premières '),
(17, '61212000', 'Achat d\'animaux pour laboratoire'),
(18, '61221100', 'Denrées alimentaires pour la nourriture des étudiants'),
(19, '61222100', 'Achat de fournitures informatiques'),
(20, '61222200', 'Achat de fournitures et de produits pour les travaux de terrain et de laboratoire '),
(21, '61222400', 'Achat d\'articles de sport'),
(22, '61222500', 'Achat d\'accessoire et matériel artistiques et culturelles nationales  '),
(23, '61223000', 'Achat de combustible'),
(24, '61227000', 'Achats de fournitures de bureau'),
(25, '61227100', 'Achat de papeterie et d\'imprimés'),
(26, '61227200', 'Fournitures de badge, articles similaires et accessoires '),
(27, '61251100', 'Eau'),
(28, '61251110', 'Eau (Apurement des arrièrés)'),
(29, '61251200', 'Electricité '),
(30, '61251210', 'Eléctricité (Apurement des arrièrés)'),
(31, '61251400', 'Carburants'),
(32, '61251600', 'Achat de produits énergétiques pour le chauffage'),
(33, '61253000', 'Achats de petit outillage et de petit équipement'),
(34, '61262000', 'Frais d\'études informatiques'),
(35, '61263100', 'Frais de sécurité et de gardiennage'),
(36, '61263200', 'Frais de jardinage et de nettoyage des bâtiments administratifs'),
(37, '61263400', 'Frais d\'aconage & de magasinage'),
(38, '61263500', 'Frais d\'analyses et d\'examens médicaux'),
(39, '61263600', 'Frais de restauration des étudiants résidents dans les internats'),
(40, '61312000', 'Location de construction'),
(41, '61316000', 'Location de matériel de transport'),
(42, '61331100', 'Entretien et réparation Bâtiments scolaires et administratifs '),
(43, '61331200', 'Entretien & répartion des biens immobiliers '),
(44, '61331300', 'Entretien des éspaces verts'),
(45, '61332100', 'Entretien et réparation de matériel et mobilier de bureau'),
(46, '61332140', 'Entretien matériel de cuisine et de buanderie'),
(47, '61332200', 'Entretien et réparation de matériel d\'atelier et de l\'outillage'),
(48, '61332300', 'Entretien et réparation du matériel scientifique et informatique'),
(49, '61332400', 'Entretien et réparation du Materiel d\'enseignement et de laboratoire'),
(50, '61332500', 'Entretien et réparation des véhicules (lubrifiants, pièces et façons)'),
(51, '61342000', 'Assurance des étudiants'),
(52, '61345000', 'Assurances matériel de transport '),
(53, '61348000', 'Autres assurances'),
(54, '61351210', 'Indemnités brut des enseignants et des experts étrangers dans les domaines de la recherche scientifique et technologie, formations et travaux d\'expertise '),
(55, '61351220', 'Contribution au profit des lecteurs de langues étrangères au Maroc'),
(56, '61351230', 'Contrubition au budget du concours national commun'),
(57, '61351500', 'Rémunération des enseignants chercheurs retraités contractuels'),
(58, '61351710', 'Allocations forfaitaires des élèves maîtres et des élèves professeurs'),
(59, '61351720', 'Allocations forfaitaires des étudiants de la licence professionnelle pour l\'habilitaion aux professions d\'enseignant'),
(60, '61354410', 'Frais de vacation'),
(61, '61354430', 'Frais de vacation du personnel d\'encadrement sportif et d\'arbitrage '),
(62, '61365100', 'Frais de justice et honoraires d\'avocats '),
(63, '61365200', 'Honoraires des médecins conventionnés'),
(64, '61365300', 'Indemnités brut des membres du comité des jury des activités culturelles  '),
(65, '61370000', 'Redevances pour brevets marques, droits et valeurs similaires'),
(66, '61411200', 'Frais d\'études et d\'expertises'),
(67, '61411300', 'Frais d\'études,d\'analyses et de sous-traitance'),
(68, '61413100', 'Frais de réalisation de maquettes et prototypes'),
(69, '61415100', 'Abonnement et Documentation'),
(70, '61415300', 'Frais de reliure'),
(71, '61421100', 'Transport de personnel et des étudiantsà l\'intérieur du Royaume'),
(72, '61421200', 'Transport de personnel et des étudiants à l\'extérieur du royaume'),
(73, '61423000', 'Frais de transport du mobilier et du matériel'),
(74, '61424000', 'Frais de transport des missionnaires et chercheurs étrangers'),
(75, '61431100', 'Indémnités de déplacement à l\'intérieur du Royaume'),
(76, '61431200', 'Indemnités Kilométriques'),
(77, '61431300', 'Indemnites de sejours à l\'etranger des etudiants doctorants'),
(78, '61433000', 'Indemnités de déplacement et de changement de résidence'),
(79, '61435100', 'Indemnités de mission à l\'étranger'),
(80, '61435200', 'Frais de séjour des missionnaires étrangers'),
(81, '61436100', 'Frais d\'organisation de colloques et de séminaires '),
(82, '61436200', 'Frais d\'hébergement et de restauration'),
(83, '61436300', 'Frais de réception et de cérémonies officielles '),
(84, '61436400', 'Frais d\'organisation de manifestations sportives et culturelles nationales'),
(85, '61436500', 'Frais de distribution des prix'),
(86, '61441000', 'Annonces, insertions '),
(87, '61442000', 'Frais de démonstration et de publicité'),
(88, '61443100', 'Foires et expositions '),
(89, '61443300', 'Frais de participation et d\'inscription aux colloques et séminaires'),
(90, '61446100', 'Impression, reproduction et photographie'),
(91, '61446200', 'Publications'),
(92, '61451000', 'Frais postaux'),
(93, '61455000', 'Frais de téléphone '),
(94, '61455100', 'Frais de téléphone (appurement des arrièrés)'),
(95, '61456000', 'Frais de télex et de télégrammes   '),
(96, '61456100', 'Frais de téléx et télégramme (appurement des arrièrés)'),
(97, '61457000', 'Redevances pour l\'utilisation des lignes de réseaux spécialisés'),
(98, '61457100', 'Taxes & redevances pour l\'utilisation des lignes de réseaux spécialisées (appurement des arrièrés)'),
(99, '61461100', 'Contribution et cotisation aux organisations nationales et internationales '),
(100, '61461200', 'Cotisation aux associations culturelles et sportives '),
(101, '61470000', 'Charges financières'),
(102, '61481000', 'Frais d\'enregistrement, d\'immatriculation fonciere, de cadastre et autorisation pour construction'),
(103, '61611000', 'Taxe urbaine et taxe d\'édilité '),
(104, '61673000', 'Taxe sur les véhicules'),
(105, '61713020', 'Indemnités Horaires pour travaux supplémentaires '),
(106, '61713100', 'Indémnités aux profits des vices présidents, des doyens et vices doyens et des directeurs et directeurs adjoints des établissements'),
(107, '61713110', 'Indemnités pour charges administratives'),
(108, '61713120', 'Indemnités de logement'),
(109, '61713130', 'Indemnités de fonction'),
(110, '61713140', 'Indemnités de voiture'),
(111, '61713150', 'Indemnités accordées aux agents des services de tirage, d\'impression et de photocopie'),
(112, '61713200', 'Indemnités de caisse des régisseurs'),
(113, '61713241', 'Frais de correction des concours d\'entrée aux Ecoles des Ingénieurs '),
(114, '61713300', 'Indemnité de résponsabilité des fondés de pouvoirs'),
(115, '61713400', 'Avantages en nature accordées aux Présidents des universités'),
(116, '61713410', 'Redevance d\'eau'),
(117, '61713420', 'Redevance d\'électricité'),
(118, '61713430', 'Frais de chauffage'),
(119, '61713440', 'Redevance de télécommunication'),
(120, '61713500', 'Prestations en nature accordées aux agents des services économiques'),
(121, '61713600', 'Indemnités complémentaires liées aux travaux de recherches et de prestations de services'),
(122, '61713610', 'Bourses octroyées aux doctorants participants aux travaux de recherche'),
(123, '61714200', 'Indemnités brut des experts d\'étude des dossiers de candidatures des chefs des établissements universitaires'),
(124, '61740000', 'Charges sociales'),
(125, '61741100', 'Cotisation à la CNOPS'),
(126, '61742200', 'R. C. A. R.'),
(127, '61745000', 'Assurances accidents de travail'),
(128, '61764000', 'Habillement et vêtement de travail'),
(129, '61766000', 'Achat de produits pharmaceutiques'),
(130, '61768100', 'Frais de Formation '),
(131, '61768200', 'Frais de stages et de séminaires'),
(132, '61768300', 'Achat de lait pour les imprimeurs.'),
(133, '61831000', 'Remboursement, restitution des loyers indûment preçus'),
(134, '61832000', 'Restitution des frais d\'inscription au titre de la formation continue indument perçus'),
(135, '61833000', 'Restitution des fonds non employés'),
(136, '61834000', 'Restitution des cautions perçues'),
(137, '65611100', 'Subventions transferées aux établissements'),
(138, '65830000', 'Pénalités et Amendes fiscales ou pénales'),
(139, '65880000', 'Règlement et exécution des décisions judiciaires et administratives'),
(140, '68888000', 'Restes à payer  fonctionnement au 31/12/2017');

--
-- Dumping data for table `detailles_budget`
--

INSERT INTO `detailles_budget` (`id`, `antecedent`, `credit_ouvert_estimatif`, `credit_ouvert_reel`, `engage_non_paye`, `engage_paye`, `reliquat_estimatif`, `reliquat_non_pay_reel`, `reliquat_non_paye_estimatif`, `reliquat_paye_estimatif`, `reliquat_payereel`, `reliquat_reel`) VALUES
(159, 0, 200, 20, 0, 0, 0, 0, 0, 0, 0, 0),
(218, 0, 5555, 5555, 4206, 6006, 20400, 0, 0, 0, 0, 202200),
(516, 0, 200, 200, 2, 2, 0, 0, 0, 0, 0, 0),
(508, 0, 30000, 30000, 2000, 2000, 0, 0, 0, 0, 0, 0),
(510, 0, 20000, 20000, 2000, 2000, 0, 0, 0, 0, 0, 0),
(514, 0, 22, 2, 2, 2, 0, 0, 0, 0, 0, 0),
(524, 0, 20000, 20000, 200, 2000, 0, 0, 0, 0, 0, 0),
(504, 0, 200, 200, 4206, 6006, 710000, 0, 0, 0, 0, 457500),
(512, 0, 255000, 255000, 6, 6, 422, 0, 0, 0, 0, 402),
(526, 0, 200, 200, 2, 2, 0, 0, 0, 0, 0, 0),
(518, 0, 200, 2000, 0, 0, 0, 0, 0, 0, 0, 0),
(528, 0, 255000, 2500, 0, 0, 0, 0, 0, 0, 0, 0),
(506, 0, 200000, 200000, 4200, 6000, 70000, 0, 0, 0, 0, 70000),
(530, 0, 20000, 200000, 0, 0, 0, 0, 0, 0, 0, 0);

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(532),
(532),
(532),
(532),
(532),
(532),
(1),
(1),
(1),
(1),
(1),
(1);
SET FOREIGN_KEY_CHECKS=1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
