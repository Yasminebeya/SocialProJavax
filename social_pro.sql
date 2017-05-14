-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Sam 08 Avril 2017 à 22:04
-- Version du serveur :  5.6.17-log
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `social_pro`
--
CREATE DATABASE IF NOT EXISTS `social_pro` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `social_pro`;

-- --------------------------------------------------------

--
-- Structure de la table `classe`
--

CREATE TABLE IF NOT EXISTS `classe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `conge` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_8F87BF962ED89348` (`conge`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=7 ;

--
-- Contenu de la table `classe`
--

INSERT INTO `classe` (`id`, `conge`) VALUES
(1, NULL),
(2, NULL),
(3, NULL),
(4, NULL),
(5, NULL),
(6, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `conge`
--

CREATE TABLE IF NOT EXISTS `conge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datedebut` date DEFAULT NULL,
  `nbrjours` int(11) DEFAULT NULL,
  `raison` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `type_conge` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user` int(11) DEFAULT NULL,
  `etat` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_2ED893488D93D649` (`user`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=136 ;

--
-- Contenu de la table `conge`
--

INSERT INTO `conge` (`id`, `datedebut`, `nbrjours`, `raison`, `type_conge`, `user`, `etat`) VALUES
(64, '2017-02-20', 6, 'malade', 'Repos', 2, 'accepter'),
(68, '2017-03-24', 2, 'hjjg', 'Repos', 12, 'accepter'),
(105, '2017-03-09', 6, 'etattaaaaaaaaaaaaaaa', 'Repos', 2, 'accepter'),
(106, '2017-02-28', 10, 'salah', 'Repos', 7, 'accepter'),
(108, '2017-04-07', 6, 'interface jdida hmd', 'Maternite', 2, 'accepter'),
(109, '2017-04-07', 69, 'yasmine ya behiaaa', 'Maternite', 11, 'refuser'),
(111, '2017-03-03', 6, 'atten', 'Maternite', 2, 'refuser'),
(112, '2017-04-07', 6, 'interface jdida', 'Maternite', 11, 'accepter'),
(113, '2017-04-06', 5, 'nchallah aad', 'Maternite', 7, 'accepter'),
(114, '2017-04-07', 5, 'bisous kbiiraa', 'Maladie', NULL, 'accepter'),
(115, '2017-04-06', 5, 'marwa', 'Repos', 6, 'En attente'),
(116, '2017-04-06', 5, 'zssssssssssssss aaaaaaaaaaaaasssssssss', 'Repos', 6, 'accepter'),
(117, '2017-04-06', 5, 'jkzedzedzedaaaaaaaaaaaaaaahayaaaaaaaaaaaaaaaaaaaaaaaa', 'Maladie', 6, 'accepter'),
(118, '2017-03-30', 5, 'kj!m', 'Maternite', 6, 'refuser'),
(119, '2017-04-14', 5, 'zaghratyy', 'Maternite', 6, 'En attente'),
(120, '2017-04-06', 54, 'edzd', 'Maternite', 6, 'En attente'),
(121, '2017-03-31', 6, 'ZDZDF', 'Repos', 6, 'En attente'),
(122, '2017-04-06', 4, 'zed', 'Maternite', 6, 'En attente'),
(123, '2017-04-20', 12, 'ds;ndesdcedc', 'Maternite', 6, 'En attente'),
(124, '2017-03-31', 6, 'sDFVSDF', 'Repos', 13, 'En attente'),
(125, '2017-04-27', 6, 'grt', 'Maternite', NULL, 'En attente'),
(126, '2017-04-07', 52, 'hhh', 'Repos', NULL, 'En attente'),
(127, '2017-04-05', 8, 'uuu', 'Repos', NULL, 'En attente'),
(128, '2017-04-19', 7, 'uuff', 'Repos', NULL, 'En attente'),
(129, '2017-03-30', 7, '888', 'Repos', 6, 'En attente'),
(130, '2017-04-16', 7, 'rrrr', 'Maternite', 6, 'En attente'),
(131, '2017-03-28', 7, 'yass', 'Repos', 6, 'En attente'),
(132, '2017-04-05', 7, 'ooo', 'Maternite', 6, 'En attente'),
(133, '2017-03-31', 9, 'power', 'Maladie', 6, 'En attente'),
(134, '2017-04-19', 9, 'it works :p ', 'Maladie', 6, 'En attente'),
(135, '2017-04-01', 30, 'wiiiiiiiw', 'Repos', 6, 'En attente');

-- --------------------------------------------------------

--
-- Structure de la table `entreprise`
--

CREATE TABLE IF NOT EXISTS `entreprise` (
  `id_entreprise` int(11) NOT NULL AUTO_INCREMENT,
  `nom_entreprise` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `description_entreprise` varchar(350) COLLATE utf8_unicode_ci NOT NULL,
  `attestation_entreprise` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `date_creation_entreprise` date NOT NULL,
  `responsable_entreprise` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `etat_entreprise` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `adresse_entreprise` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `tel_entreprise` int(11) NOT NULL,
  PRIMARY KEY (`id_entreprise`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `evenement`
--

CREATE TABLE IF NOT EXISTS `evenement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `categorie` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `lieu` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `date_debut` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `date_fin` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `forum`
--

CREATE TABLE IF NOT EXISTS `forum` (
  `id_forum` int(11) NOT NULL AUTO_INCREMENT,
  `nom_forum` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `nombre_sujet` int(11) DEFAULT NULL,
  `nombre_vu` int(11) DEFAULT NULL,
  `date_creation` datetime NOT NULL,
  PRIMARY KEY (`id_forum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `fos_user`
--

CREATE TABLE IF NOT EXISTS `fos_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `username_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `confirmation_token` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '(DC2Type:array)',
  `matricule` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cin` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `datenaissance` date DEFAULT NULL,
  `num_telephone` int(11) DEFAULT NULL,
  `poste` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dateembauche` date DEFAULT NULL,
  `salaire` int(11) DEFAULT NULL,
  `departement` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_957A647992FC23A8` (`username_canonical`),
  UNIQUE KEY `UNIQ_957A6479A0D96FBF` (`email_canonical`),
  UNIQUE KEY `UNIQ_957A6479C05FB297` (`confirmation_token`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=14 ;

--
-- Contenu de la table `fos_user`
--

INSERT INTO `fos_user` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`, `matricule`, `cin`, `datenaissance`, `num_telephone`, `poste`, `dateembauche`, `salaire`, `departement`) VALUES
(1, 'yasmine', 'yasmine', 'beya1994@live.fr', 'beya1994@live.fr', 1, 'vqKq9f/IgKDoZP.sT2uf5EwQfEe1I627P/SixtOesdE', 'yasmine{vqKq9f/IgKDoZP.sT2uf5EwQfEe1I627P/SixtOesdE}', '2017-03-24 19:25:41', NULL, NULL, 'a:1:{i:0;s:10:"ROLE_ADMIN";}', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(2, 'yass', 'yass', 'd@live.fr', 'd@live.fr', 1, 'cQvedGG.ob9bF0zTfgC6LFfTQsSJg3HVlb.zs/7AcDI', 'yass{cQvedGG.ob9bF0zTfgC6LFfTQsSJg3HVlb.zs/7AcDI}', '2017-03-24 19:26:57', NULL, NULL, 'a:0:{}', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(6, 'sinda', 'sinda', 'jhEDGJef@esprit.tn', 'jhedgjef@esprit.tn', 1, 'A7Ro2.S1v/l2U9sEL4LqvpYJ3XB20PJczYYj.uXnk3Q', 'sinda{A7Ro2.S1v/l2U9sEL4LqvpYJ3XB20PJczYYj.uXnk3Q}', '2017-02-15 11:07:10', NULL, NULL, 'a:0:{}', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(7, 'beya1', 'beya1', 'Yass.b@live.fr', 'yass.b@live.fr', 1, '2nYxgU7K.LPI/.GaYz.KkrZoFuVTCPvBsdHpyYMEefI', 'beya{2nYxgU7K.LPI/.GaYz.KkrZoFuVTCPvBsdHpyYMEefI}', '2017-02-20 00:46:48', NULL, NULL, 'a:0:{}', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(9, 'moumou', 'moumou', 'moumou@esprit.tn', 'moumou@esprit.tn', 1, 'zZn4fKm1kewTWjzQhT084q9TXN.VeQ2BjiAowu4jOjA', 'moumou{zZn4fKm1kewTWjzQhT084q9TXN.VeQ2BjiAowu4jOjA}', '2017-02-19 17:06:53', NULL, NULL, 'a:0:{}', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(11, 'lili1', 'lili1', 'hjj@live.fr', 'hjj@live.fr', 1, '16NL46stZSETOJl/3NbDaV1g4ZZxV/IuxvnrZmVaFrQ', 'lili{16NL46stZSETOJl/3NbDaV1g4ZZxV/IuxvnrZmVaFrQ}', '2017-02-20 11:16:10', NULL, NULL, 'a:0:{}', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(12, 'yassin', 'yassin', 'jha.Dze@EJHDBJe.fr', 'jha.dze@ejhdbje.fr', 1, 'gb6.kAJYlaPyV.ynJI5nDzj.5pXghN2AQeR0KmNbYKs', 'yassin{gb6.kAJYlaPyV.ynJI5nDzj.5pXghN2AQeR0KmNbYKs}', '2017-03-24 19:28:44', NULL, NULL, 'a:0:{}', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(13, 'chef_de_projet', 'chef_de_projet', 'chefprojet@gmail.com', 'chefprojet@gmail.com', 1, 'GMiME86vwqeqaoLYlKPRVD96CZO9ngzsG3JvBxtCq90', '1234{GMiME86vwqeqaoLYlKPRVD96CZO9ngzsG3JvBxtCq90}', '2017-04-03 16:38:44', NULL, NULL, 'a:1:{i:0;s:9:"ROLE_CHEF";}', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `information_entreprise`
--

CREATE TABLE IF NOT EXISTS `information_entreprise` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `specialite` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `site_web` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `abreviation` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `gouvernerat` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `attestation` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `filename` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `code_postale` int(11) NOT NULL,
  `type` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `adresse` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `nationnalite` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `numTel` int(11) NOT NULL,
  `matriculeFiscal` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `message`
--

CREATE TABLE IF NOT EXISTS `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `objet` varchar(20) NOT NULL,
  `text` varchar(500) NOT NULL,
  `user_id` int(11) NOT NULL,
  `nom_user` varchar(20) DEFAULT NULL,
  `datedenvoye` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `message`
--

INSERT INTO `message` (`id`, `objet`, `text`, `user_id`, `nom_user`, `datedenvoye`) VALUES
(1, 'convocation', 'Please set manifest.custom.codebase property to override the current default non-secure value', 6, NULL, '2017-04-08');

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE IF NOT EXISTS `produit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typeproduit` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `libelle` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prix` double NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `quantite` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `publication`
--

CREATE TABLE IF NOT EXISTS `publication` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `tache`
--

CREATE TABLE IF NOT EXISTS `tache` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datedebut` date NOT NULL,
  `datefin` date NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `etat` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_93872075A76ED395` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=28 ;

--
-- Contenu de la table `tache`
--

INSERT INTO `tache` (`id`, `datedebut`, `datefin`, `description`, `user_id`, `etat`) VALUES
(10, '2017-04-22', '2017-04-23', 'implementer la methode search au niveau du tacheService', 6, 'pas encore réalisée'),
(11, '2017-04-29', '2017-05-21', 'tache a réalisé2', 6, 'pas encore réalisée'),
(12, '2017-04-09', '2017-05-25', 'implemter la méthode AddTache au niveau du tache service', NULL, 'pas encore réalisée'),
(17, '2017-04-05', '2017-04-28', 'Ajuter un Button supprimer au niveau de l''interface ', 6, 'pas encore réalisée'),
(18, '2017-05-18', '2017-04-30', 'création de l''interface graphique d''inscription', NULL, 'pas encore réalisée'),
(19, '2017-04-01', '2017-04-29', 'tester le service  d''inscription', NULL, 'pas encore réalisée'),
(20, '2017-04-09', '2017-04-20', 'implémenter la méthode delete dans evemenetService', 7, 'pas encore réalisée'),
(22, '2017-02-07', '2017-03-01', 'intégration du bundle KnpPagination', 6, 'pas encore réalisée'),
(23, '2017-02-08', '2017-02-28', 'integratin du fos_userBundle', 7, 'pas encore réalisée'),
(24, '2017-04-06', '2017-04-27', 'intégratin de l''api stat', NULL, 'pas encore réalisée'),
(25, '2017-03-08', '2017-04-26', 'intégratin de l''api email', 12, 'pas encore réalisée'),
(26, '2017-03-01', '2017-03-07', 'créaton de la méthode addCon au niveau du CongéService', 12, 'pas encore réalisée'),
(27, '2017-03-16', '2017-03-22', 'Controle sur les intefaces', 12, 'pas encore réalisée');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `classe`
--
ALTER TABLE `classe`
  ADD CONSTRAINT `FK_8F87BF962ED89348` FOREIGN KEY (`conge`) REFERENCES `conge` (`id`);

--
-- Contraintes pour la table `conge`
--
ALTER TABLE `conge`
  ADD CONSTRAINT `FK_2ED893488D93D649` FOREIGN KEY (`user`) REFERENCES `fos_user` (`id`);

--
-- Contraintes pour la table `tache`
--
ALTER TABLE `tache`
  ADD CONSTRAINT `FK_93872075A76ED395` FOREIGN KEY (`user_id`) REFERENCES `fos_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
