-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: db:3306
-- Tiempo de generación: 29-10-2023 a las 22:40:11
-- Versión del servidor: 5.7.44
-- Versión de PHP: 8.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `takecare`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla medicamento
--

CREATE TABLE medicamento (
  codigo int(11) NOT NULL,
  nombre varchar(30) NOT NULL,
  cantidadamaxima int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `medicamento` (`codigo`, `nombre`, `cantidadamaxima`) VALUES
(1, 'Paracetamol', 10),
(2, 'Omeprazol', 8),
(3, 'Salbutamol', 10),
(4, 'Aspirina', 10),
(5, 'Metformina', 10),
(6, 'Naproxeno', 10),
(7, 'Captopril', 10),
(8, 'Loratadina', 10),
(9, 'Ibuprofeno', 10),
(10, 'Ambroxol', 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla paciente
--

CREATE TABLE paciente (
  codigo int(11) NOT NULL,
  nombre varchar(30) NOT NULL,
  fechaNacimiento date NOT NULL,
  contacto varchar(15) NOT NULL,
  codigoubicacion int(11) NOT NULL,
  password varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla toma
--

CREATE TABLE toma (
  codigo int(11) NOT NULL,
  fecha date NOT NULL,
  dosis int(11) NOT NULL,
  horario int(11) NOT NULL,
  tomada tinyint(1) NOT NULL,
  codigopaciente int(11) NOT NULL,
  codigomedicamento int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla ubicacion
--

CREATE TABLE ubicacion(
  codigo int(11) NOT NULL,
  nombre varchar(30) NOT NULL,
  direccion varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla medicamento
--
ALTER TABLE medicamento
  ADD PRIMARY KEY (codigo);

--
-- Indices de la tabla paciente
--
ALTER TABLE paciente
  ADD PRIMARY KEY (codigo),
  ADD KEY fk_ubicacion (codigoubicacion);

--
-- Indices de la tabla toma
--
ALTER TABLE toma
  ADD PRIMARY KEY (codigo),
  ADD KEY fk_paciente (codigopaciente),
  ADD KEY fk_medicamento (codigomedicamento);

--
-- Indices de la tabla ubicacion
--
ALTER TABLE ubicacion
  ADD PRIMARY KEY (codigo);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla medicamento
--
ALTER TABLE `medicamento`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla paciente
--
ALTER TABLE paciente
  MODIFY codigo int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla toma
--
ALTER TABLE toma
  MODIFY codigo int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla ubicacion
--
ALTER TABLE ubicacion
  MODIFY codigo int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla paciente
--
ALTER TABLE paciente
  ADD CONSTRAINT fk_ubicacion FOREIGN KEY (codigoubicacion) REFERENCES ubicacion (codigo);

--
-- Filtros para la tabla toma
--
ALTER TABLE toma
  ADD CONSTRAINT fk_medicamento FOREIGN KEY (codigomedicamento) REFERENCES medicamento (codigo),
  ADD CONSTRAINT fk_paciente FOREIGN KEY (codigopaciente) REFERENCES paciente (codigo);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
