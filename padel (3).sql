-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-11-2023 a las 11:06:06
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `padel`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administrador`
--

CREATE TABLE `administrador` (
  `nombre_usuario` varchar(25) DEFAULT NULL,
  `contrasena` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `administrador`
--

INSERT INTO `administrador` (`nombre_usuario`, `contrasena`) VALUES
('admin', 'admin');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado_pistas`
--

CREATE TABLE `estado_pistas` (
  `id_estado` int(11) NOT NULL,
  `id_pista` int(1) DEFAULT NULL,
  `estado_pista` varchar(20) DEFAULT NULL,
  `fecha_inicio` timestamp(6) NULL DEFAULT NULL,
  `fecha_fin` timestamp(6) NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `estado_pistas`
--

INSERT INTO `estado_pistas` (`id_estado`, `id_pista`, `estado_pista`, `fecha_inicio`, `fecha_fin`) VALUES
(94, 3, 'Mantenimiento', '2023-11-17 08:00:00.000000', '2023-11-17 15:00:00.000000'),
(95, 2, 'Ocupada', '2023-11-18 19:00:00.000000', '2023-11-19 08:00:00.000000'),
(96, 4, 'Mantenimiento', '2023-11-18 08:00:00.000000', '2023-11-18 20:00:00.000000'),
(97, 2, 'Mantenimiento', '2023-11-18 08:00:00.000000', '2023-11-18 20:00:00.000000'),
(98, 2, 'Mantenimiento', '2023-11-18 13:00:00.000000', '2023-11-22 17:00:00.000000');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pistas`
--

CREATE TABLE `pistas` (
  `id_pista` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pistas`
--

INSERT INTO `pistas` (`id_pista`) VALUES
(1),
(2),
(3),
(4),
(5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservas`
--

CREATE TABLE `reservas` (
  `id_reserva` int(11) NOT NULL,
  `email_usuario` varchar(100) DEFAULT NULL,
  `id_pista` int(11) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `hora` varchar(5) NOT NULL,
  `activa` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `reservas`
--

INSERT INTO `reservas` (`id_reserva`, `email_usuario`, `id_pista`, `fecha`, `hora`, `activa`) VALUES
(131, 'alamisalma949@gmail.com', 3, '2023-11-17', '09:00', 0),
(132, 'alamisalma949@gmail.com', 3, '2023-11-17', '10:00', 0),
(133, 'salma.alami@inslapineda.cat', 4, '2023-11-17', '14:00', 0),
(134, 'salma.alami@inslapineda.cat', 2, '2023-11-19', '16:00', 0),
(135, 'alamisalma949@gmail.com', 5, '2023-11-19', '17:00', 0),
(136, 'alamisalma949@gmail.com', 1, '2023-11-19', '12:00', 0),
(137, 'alamisalma949@gmail.com', 1, '2023-11-19', '18:00', 1),
(138, 'alamisalma949@gmail.com', 3, '2023-11-19', '15:00', 0),
(139, 'alamisalma949@gmail.com', 5, '2023-11-17', '11:00', 1),
(140, 'alamisalma949@gmail.com', 1, '2023-11-17', '18:00', 0),
(141, 'alamisalma949@gmail.com', 4, '2023-11-18', '10:00', 0),
(142, 'alamisalma949@gmail.com', 4, '2023-11-18', '11:00', 0),
(143, 'alamisalma949@gmail.com', 4, '2023-11-18', '12:00', 0),
(144, 'alamisalma949@gmail.com', 4, '2023-11-18', '13:00', 0),
(145, 'alamisalma949@gmail.com', 4, '2023-11-18', '14:00', 0),
(146, 'alamisalma949@gmail.com', 4, '2023-11-18', '15:00', 0),
(147, 'alamisalma949@gmail.com', 2, '2023-11-18', '09:00', 0),
(148, 'alamisalma949@gmail.com', 2, '2023-11-18', '11:00', 0),
(149, 'alamisalma949@gmail.com', 2, '2023-11-18', '10:00', 0),
(150, 'alamisalma949@gmail.com', 2, '2023-11-18', '12:00', 0),
(151, 'alamisalma949@gmail.com', 2, '2023-11-18', '13:00', 0),
(152, 'alamisalma949@gmail.com', 3, '2023-11-18', '11:00', 0),
(153, 'alamisalma949@gmail.com', 5, '2023-11-18', '10:00', 1),
(154, 'alamisalma949@gmail.com', 1, '2023-11-17', '15:00', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservasseleccionadas`
--

CREATE TABLE `reservasseleccionadas` (
  `emailUsuario` varchar(50) NOT NULL,
  `idReserva` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `reservasseleccionadas`
--

INSERT INTO `reservasseleccionadas` (`emailUsuario`, `idReserva`) VALUES
('salma.alami@inslapineda.cat', 136),
('salma.alami@inslapineda.cat', 135),
('salma.alami@inslapineda.cat', 136),
('isalas@xtec.cat', 136),
('salma.alami@inslapineda.cat', 152);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `email` varchar(100) NOT NULL,
  `nombre` varchar(20) DEFAULT NULL,
  `apellido` varchar(20) DEFAULT NULL,
  `contraseña` varchar(20) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `dni` varchar(9) DEFAULT NULL,
  `foto` varchar(200) DEFAULT NULL,
  `socio` tinyint(1) DEFAULT NULL,
  `activado` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`email`, `nombre`, `apellido`, `contraseña`, `fecha_nacimiento`, `telefono`, `dni`, `foto`, `socio`, `activado`) VALUES
('alamisalma949@gmail.com', 'salmaaa', 'Alami', 'salma', '2003-01-11', '555555555', '74185296X', 'th (1).jpeg', 1, 1),
('alex2002bdn@gmail.com', 'Alex', 'Cedeño', 'alex', '2003-10-10', '111111', '14778998X', 'th.jpeg', 1, 1),
('brichafadwa1@gmail.com', 'Fadwa', 'Bricha', 'fadwa', '1983-10-19', '111111111', '147741F', 'th (1).jpeg', 1, 1),
('gebeal2002@gmail.com', 'Gerard', 'Bejarano', 'gerard', '2003-10-09', '7777777', '14785236G', 'th.jpeg', 1, 1),
('isalas@xtec.cat', 'Inma', 'Salas', 'inma', '1975-10-09', '644789512', '12233484O', 'th (1).jpeg', 1, 1),
('salma.alami@inslapineda.cat', 'Alumna', 'Alumna', 'salma', '2003-11-01', '646390007', '15963278S', 'th (1).jpeg', 1, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `estado_pistas`
--
ALTER TABLE `estado_pistas`
  ADD PRIMARY KEY (`id_estado`),
  ADD UNIQUE KEY `id_estado` (`id_estado`);

--
-- Indices de la tabla `pistas`
--
ALTER TABLE `pistas`
  ADD PRIMARY KEY (`id_pista`);

--
-- Indices de la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD PRIMARY KEY (`id_reserva`),
  ADD KEY `id_pista` (`id_pista`),
  ADD KEY `reservas_ibfk_1` (`email_usuario`);

--
-- Indices de la tabla `reservasseleccionadas`
--
ALTER TABLE `reservasseleccionadas`
  ADD KEY `idPista` (`idReserva`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `estado_pistas`
--
ALTER TABLE `estado_pistas`
  MODIFY `id_estado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=99;

--
-- AUTO_INCREMENT de la tabla `pistas`
--
ALTER TABLE `pistas`
  MODIFY `id_pista` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `reservas`
--
ALTER TABLE `reservas`
  MODIFY `id_reserva` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=155;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD CONSTRAINT `reservas_ibfk_1` FOREIGN KEY (`email_usuario`) REFERENCES `usuarios` (`email`),
  ADD CONSTRAINT `reservas_ibfk_2` FOREIGN KEY (`id_pista`) REFERENCES `pistas` (`id_pista`);

--
-- Filtros para la tabla `reservasseleccionadas`
--
ALTER TABLE `reservasseleccionadas`
  ADD CONSTRAINT `reservasseleccionadas_ibfk_1` FOREIGN KEY (`idReserva`) REFERENCES `reservas` (`id_reserva`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
