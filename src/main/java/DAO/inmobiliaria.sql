-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: db
-- Tiempo de generación: 04-09-2024 a las 15:33:05
-- Versión del servidor: 8.4.0
-- Versión de PHP: 8.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `inmobiliaria`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `admins`
--

CREATE TABLE `admins` (
  `userName` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `admins`
--

INSERT INTO `admins` (`userName`) VALUES
('AdrianCB27');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gestores`
--

CREATE TABLE `gestores` (
  `bloqueado` tinyint(1) DEFAULT '0',
  `userName` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `gestores`
--

INSERT INTO `gestores` (`bloqueado`, `userName`) VALUES
(0, 'AdrianCB28');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inversion`
--

CREATE TABLE `inversion` (
  `codigo` int NOT NULL,
  `fechaInicio` varchar(40) DEFAULT NULL,
  `ultimaActualizacion` varchar(40) DEFAULT NULL,
  `cantidadParticipada` double NOT NULL,
  `codigoProyecto` int DEFAULT NULL,
  `inversorUserName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `inversion`
--

INSERT INTO `inversion` (`codigo`, `fechaInicio`, `ultimaActualizacion`, `cantidadParticipada`, `codigoProyecto`, `inversorUserName`) VALUES
(1, '2024-09-03', '2024-09-03', 40, 3, 'AdrianCB28'),
(2, '2024-09-03', '2024-09-03', 22.59, 1, 'AdrianCB28'),
(3, '2024-09-04', '2024-09-04', 12, 2, 'AdrianCB28');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inversores`
--

CREATE TABLE `inversores` (
  `saldo` double DEFAULT NULL,
  `bloqueado` tinyint(1) DEFAULT '0',
  `userName` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `inversores`
--

INSERT INTO `inversores` (`saldo`, `bloqueado`, `userName`) VALUES
(0, 0, 'Metalero65');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proyectos`
--

CREATE TABLE `proyectos` (
  `codigo` int NOT NULL,
  `nombre` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `descripcion` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `tipo` enum('Alquiler','Plusvalía','Préstamo') DEFAULT NULL,
  `fechaInicio` date NOT NULL,
  `fechaFin` date NOT NULL,
  `cantidadNecesaria` double NOT NULL,
  `cantidadFinanciada` double DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `proyectos`
--

INSERT INTO `proyectos` (`codigo`, `nombre`, `descripcion`, `tipo`, `fechaInicio`, `fechaFin`, `cantidadNecesaria`, `cantidadFinanciada`) VALUES
(1, 'Villa rosaa', 'Erase una vez', 'Alquiler', '2004-03-05', '2004-03-04', 35000, 22.59),
(2, 'Villa celulosa', 'La villa más morada', 'Plusvalía', '2004-03-04', '2024-03-04', 50000, 12),
(3, 'Villa azul', 'La villa más bonita', 'Préstamo', '2003-06-26', '2024-06-26', 60000, 40),
(7, 'Prueba 1', 'Una prueba 1', 'Alquiler', '2034-03-12', '2035-03-23', 125000, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `userName` varchar(250) NOT NULL,
  `nombre` varchar(250) DEFAULT NULL,
  `userPassword` varchar(250) DEFAULT NULL,
  `email` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`userName`, `nombre`, `userPassword`, `email`) VALUES
('AdrianCB27', 'Adrinoan', '2dd88844519d4f5be16869a6bc430ef5db6e0aea', 'contrerasbuenoadrian@gmail.com'),
('AdrianCB28', 'Alex el favores', 'e86892504d64c851a78199c8a8ffa13aff24e855', 'alexElFavores@gmail.com'),
('Metalero65', 'Alejandro Ordoñez', 'ea145ee55109a5ca8957220654a0d245d6b4d88f', 'alejandro.ordonez.2308@fernando3martos.com');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `admins`
--
ALTER TABLE `admins`
  ADD KEY `userName` (`userName`);

--
-- Indices de la tabla `gestores`
--
ALTER TABLE `gestores`
  ADD KEY `userName` (`userName`);

--
-- Indices de la tabla `inversion`
--
ALTER TABLE `inversion`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `codigoProyecto` (`codigoProyecto`),
  ADD KEY `fk_inversorUserName` (`inversorUserName`);

--
-- Indices de la tabla `inversores`
--
ALTER TABLE `inversores`
  ADD KEY `userName` (`userName`);

--
-- Indices de la tabla `proyectos`
--
ALTER TABLE `proyectos`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`userName`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `inversion`
--
ALTER TABLE `inversion`
  MODIFY `codigo` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `proyectos`
--
ALTER TABLE `proyectos`
  MODIFY `codigo` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `admins`
--
ALTER TABLE `admins`
  ADD CONSTRAINT `admins_ibfk_1` FOREIGN KEY (`userName`) REFERENCES `usuarios` (`userName`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `gestores`
--
ALTER TABLE `gestores`
  ADD CONSTRAINT `gestores_ibfk_1` FOREIGN KEY (`userName`) REFERENCES `usuarios` (`userName`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `inversion`
--
ALTER TABLE `inversion`
  ADD CONSTRAINT `fk_inversorUserName` FOREIGN KEY (`inversorUserName`) REFERENCES `usuarios` (`userName`),
  ADD CONSTRAINT `inversion_ibfk_1` FOREIGN KEY (`codigoProyecto`) REFERENCES `proyectos` (`codigo`);

--
-- Filtros para la tabla `inversores`
--
ALTER TABLE `inversores`
  ADD CONSTRAINT `inversores_ibfk_1` FOREIGN KEY (`userName`) REFERENCES `usuarios` (`userName`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
