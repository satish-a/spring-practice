DROP TABLE IF EXISTS userBalance;
DROP TABLE IF EXISTS userPayments;
DROP TABLE IF EXISTS salesOrderTaxes;
DROP TABLE IF EXISTS salesOrderCharges;
DROP TABLE IF EXISTS salesOrderItems;
DROP TABLE IF EXISTS salesOrder;
DROP TABLE IF EXISTS purchaseOrderTaxes;
DROP TABLE IF EXISTS purchaseOrderCharges;
DROP TABLE IF EXISTS purchaseOrderItems;
DROP TABLE IF EXISTS purchaseOrder;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS productGroup;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS user;

CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `accountType` varchar(255) DEFAULT NULL,
  `billingAddress` varchar(255) DEFAULT NULL,
  `mobilePhone` varchar(255) DEFAULT NULL,
  `officePhone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `createadBy` bigint(20) DEFAULT NULL,
  `modifiedBy` bigint(20) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `lastLoggedIn` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` int(11) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3580769128426C` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `productGroup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `groupName` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `createadBy` bigint(20) DEFAULT NULL,
  `modifiedBy` bigint(20) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `groupName` (`groupName`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `productName` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `productGroup` bigint(20) DEFAULT NULL,
  `price` bigint(20) DEFAULT NULL,
  `createadBy` bigint(20) DEFAULT NULL,
  `modifiedBy` bigint(20) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `productname` (`productName`),
  KEY `FKPRODGROUP` (`productGroup`),
  CONSTRAINT `FKPRODGROUP` FOREIGN KEY (`productGroup`) REFERENCES `productGroup` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `purchaseOrder` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orderId` varchar(255) NOT NULL,
  `accountId` bigint(20) NOT NULL,
  `orderDate` datetime NOT NULL,
  `referenceNumber` varchar(255) DEFAULT NULL,
  `dueDate` datetime DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `discount` bigint(20) DEFAULT NULL,
  `orderType` varchar(255) DEFAULT NULL,
  `total` bigint(20) DEFAULT 0,
  `createdBy` bigint(20) DEFAULT NULL,
  `modifiedBy` bigint(20) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `purchaseOrderId` (`orderId`),
  CONSTRAINT `FKPOACCID` FOREIGN KEY (`accountId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `purchaseOrderItems` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orderId` bigint(20) NOT NULL,
  `productId` bigint(20) NOT NULL,
  `quantity` bigint(20) NOT NULL,
  `unitPrice` bigint(20) NOT NULL,
  `discount` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `purchaseOrderItemsKey` (`orderId`, `productId`),
  CONSTRAINT `FKPOIORDRID` FOREIGN KEY (`orderId`) REFERENCES `purchaseOrder` (`id`),
  CONSTRAINT `FKPOIPRODID` FOREIGN KEY (`productId`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `purchaseOrderCharges` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orderId` bigint(20) NOT NULL,
  `chargeName` varchar(255) NOT NULL,
  `amount` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `purchaseOrderChargesKey` (`orderId`, `chargeName`),
  CONSTRAINT `FKPOCORDRID` FOREIGN KEY (`orderId`) REFERENCES `purchaseOrder` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `purchaseOrderTaxes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orderId` bigint(20) NOT NULL,
  `taxName` varchar(255) NOT NULL,
  `amount` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `purchaseOrderChargesKey` (`orderId`, `taxName`),
  CONSTRAINT `FKPOTORDRID` FOREIGN KEY (`orderId`) REFERENCES `purchaseOrder` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `salesOrder` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orderId` varchar(255) NOT NULL,
  `accountId` bigint(20) NOT NULL,
  `orderDate` datetime NOT NULL,
  `referenceNumber` varchar(255) DEFAULT NULL,
  `dueDate` datetime DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `discount` bigint(20) DEFAULT 0,
  `orderType` varchar(255) DEFAULT NULL,
  `total` bigint(20) DEFAULT 0,
  `createdBy` bigint(20) DEFAULT NULL,
  `modifiedBy` bigint(20) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `salesOrderId` (`orderId`),
  CONSTRAINT `FKSOACCID` FOREIGN KEY (`accountId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `salesOrderItems` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orderId` bigint(20) NOT NULL,
  `productId` bigint(20) NOT NULL,
  `quantity` bigint(20) NOT NULL,
  `unitPrice` bigint(20) NOT NULL,
  `discount` bigint(20) DEFAULT 0,
  `total` bigint(20) DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `salesOrderItemsKey` (`orderId`, `productId`),
  CONSTRAINT `FKSOIORDRID` FOREIGN KEY (`orderId`) REFERENCES `salesOrder` (`id`),
  CONSTRAINT `FKSOIPRODID` FOREIGN KEY (`productId`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `salesOrderCharges` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orderId` bigint(20) NOT NULL,
  `chargeName` varchar(255) NOT NULL,
  `amount` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `salesOrderChargesKey` (`orderId`, `chargeName`),
  CONSTRAINT `FKSOCORDRID` FOREIGN KEY (`orderId`) REFERENCES `salesOrder` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `salesOrderTaxes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orderId` bigint(20) NOT NULL,
  `taxName` varchar(255) NOT NULL,
  `amount` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `salesOrderChargesKey` (`orderId`, `taxName`),
  CONSTRAINT `FKSOTORDRID` FOREIGN KEY (`orderId`) REFERENCES `salesOrder` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `userPayments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `accountId` bigint(20) NOT NULL,
  `amount` bigint(20) NOT NULL,
  `paymentDate` datetime DEFAULT NULL,
  `notes` varchar(255) NOT NULL,
  `paymentType` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKUPSACCID` FOREIGN KEY (`accountId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `userBalance` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `accountId` bigint(20) NOT NULL,
  `amount` bigint(20) NOT NULL,
  `createdBy` bigint(20) DEFAULT NULL,
  `modifiedBy` bigint(20) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `notes` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKUBACCID` FOREIGN KEY (`accountId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;
