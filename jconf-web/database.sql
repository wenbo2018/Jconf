
#Config table slq
CREATE TABLE `Config` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `Key` varchar(100) COLLATE utf8_persian_ci NOT NULL,
  `Value`  VARCHAR(100) NOT NULL,
  `Env` int(10) COLLATE utf8_persian_ci NOT NULL,
  `ProjectId` int(10) NOT NULL,
  `Env` INT(10) COLLATE utf8_persian_ci NOT NULL,
  `UserName` VARCHAR(20) NOT NULL,
  `UserEmail` varchar(20) COLLATE utf8_persian_ci NOT NULL,
  `DateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=174535 DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

