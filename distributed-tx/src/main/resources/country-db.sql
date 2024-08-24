CREATE DATABASE world;

DROP TABLE IF EXISTS `country`;
CREATE TABLE `country` (
  `Code` char(3) DEFAULT NULL,
  `Name` char(52) DEFAULT NULL,
  `Region` char(26) DEFAULT NULL,
  `SurfaceArea` decimal(10,2) DEFAULT NULL,
  `Population` int DEFAULT NULL,
  `Capital` int DEFAULT NULL,
  PRIMARY KEY (`Code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Name` char(35) DEFAULT NULL,
  `CountryCode` char(3)  DEFAULT NULL,
  `District` char(20) DEFAULT NULL,
  `Population` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
) ENGINE=InnoDB AUTO_INCREMENT=4091 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;