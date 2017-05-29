
#Config table slq
CREATE TABLE `Config` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `Key` varchar(100) COLLATE utf8_persian_ci NOT NULL,
  `Value`  VARCHAR(100) NOT NULL,
  `Env` int(10) COLLATE utf8_persian_ci NOT NULL,
  `ProjectId` int(10) NOT NULL,
  `UserName` VARCHAR(20) NOT NULL,
  `UserEmail` varchar(20) COLLATE utf8_persian_ci NOT NULL,
  `DateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=174535 DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;


#Config table slq
CREATE TABLE `User` (
  `Id` int(20) NOT NULL AUTO_INCREMENT,
  `UserId` int(20)  NOT NULL,
  `UserName`  VARCHAR(20) NOT NULL,
  `PassWord` VARCHAR(200)  NOT NULL,
  `ProjectId` int(10) NOT NULL,
  `Email` VARCHAR(50) NOT NULL,
  `Token` varchar(200)  NOT NULL,
  `AddTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`),
  KEY `IX_UserId` (`UserId`),
  KEY `IX_Token` (`Token`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;



