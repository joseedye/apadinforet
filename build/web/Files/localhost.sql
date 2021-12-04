-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 29-11-2021 a las 04:13:22
-- Versión del servidor: 8.0.17
-- Versión de PHP: 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `curso`
--
CREATE DATABASE IF NOT EXISTS `curso` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `curso`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(40) COLLATE utf8_bin NOT NULL,
  `apellido` varchar(40) COLLATE utf8_bin NOT NULL,
  `email` varchar(255) COLLATE utf8_bin NOT NULL,
  `telefono` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `nombre`, `apellido`, `email`, `telefono`, `password`) VALUES
(1, 'jose', 'rozo', 'a@ufps.edu.co', '3122663067', '$argon2id$v=19$m=1024,t=1,p=1$UzJN6CWA3wVULjWsNeHwjQ$Cz3LTpqSrpMz2sHdrq0LgMJeBfYxIEzJVr8SHKQWB6I'),
(4, 'maria', 'rozo', 'a@ufps.edu.co', '3122663067', '$argon2id$v=19$m=1024,t=1,p=1$UzJN6CWA3wVULjWsNeHwjQ$Cz3LTpqSrpMz2sHdrq0LgMJeBfYxIEzJVr8SHKQWB6I'),
(5, 'jose', 'eduardo rozo', 'das@ff', NULL, '$argon2id$v=19$m=1024,t=1,p=1$UzJN6CWA3wVULjWsNeHwjQ$Cz3LTpqSrpMz2sHdrq0LgMJeBfYxIEzJVr8SHKQWB6I'),
(6, 'yeny', 'villaba', 'yeny@yeny.com', NULL, '$argon2id$v=19$m=1024,t=1,p=1$UzJN6CWA3wVULjWsNeHwjQ$Cz3LTpqSrpMz2sHdrq0LgMJeBfYxIEzJVr8SHKQWB6I'),
(7, 'yeny', 'torres', 'email@hjh', NULL, '$argon2id$v=19$m=1024,t=1,p=1$UzJN6CWA3wVULjWsNeHwjQ$Cz3LTpqSrpMz2sHdrq0LgMJeBfYxIEzJVr8SHKQWB6I');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
