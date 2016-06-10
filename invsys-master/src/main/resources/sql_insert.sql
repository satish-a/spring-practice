-- Staff
INSERT INTO `sainv`.`user`
(`firstName`, `lastName`, `password`, `username`, `accountType`, `billingAddress`, `mobilePhone`, `officePhone`,`email`, `description`, `notes`, `createadBy`, `modifiedBy`, `createdDate`, `modifiedDate`, `lastLoggedIn`) VALUES
('Administrator', 'Local', '21232f297a57a5a743894a0e4a801fc3', 'admin', 'Staff', 'India', '9898989898', '4040404040', 'local.admin@local.com', 'Local Administrator', 'Local Administrator', 1, 1, sysdate(), sysdate(), sysdate() ),
('Administrator1', 'Local', '21232f297a57a5a743894a0e4a801fc3', 'admin1', 'Staff', 'India', '9898989898', '4040404040', 'local.admin1@local.com', 'Local Administrator1', 'Local Administrator1', 1, 1, sysdate(), sysdate(), sysdate() ),
('Administrator2', 'Local', '21232f297a57a5a743894a0e4a801fc3', 'admin2', 'Staff', 'India', '9898989898', '4040404040', 'local.admin2@local.com', 'Local Administrator2', 'Local Administrator2', 1, 1, sysdate(), sysdate(), sysdate() ),
('Administrator3', 'Local', '21232f297a57a5a743894a0e4a801fc3', 'admin3', 'Staff', 'India', '9898989898', '4040404040', 'local.admin3@local.com', 'Local Administrator3', 'Local Administrator3', 1, 1, sysdate(), sysdate(), sysdate() ),
('Administrator4', 'Local', '21232f297a57a5a743894a0e4a801fc3', 'admin4', 'Staff', 'India', '9898989898', '4040404040', 'local.admin4@local.com', 'Local Administrator4', 'Local Administrator4', 1, 1, sysdate(), sysdate(), sysdate() );;
-- Customer
INSERT INTO `sainv`.`user`
(`firstName`, `lastName`, `password`, `username`, `accountType`, `billingAddress`, `mobilePhone`, `officePhone`,`email`, `description`, `notes`, `createadBy`, `modifiedBy`, `createdDate`, `modifiedDate`, `lastLoggedIn`) VALUES
('Customer1', 'Local', '21232f297a57a5a743894a0e4a801fc3', 'customer1', 'Customer', 'India', '9898989898', '4040404040', 'local.customer1@local.com', 'Local Customer1', 'Local Customer1', 1, 1, sysdate(), sysdate(), sysdate() ),
('Customer2', 'Local', '21232f297a57a5a743894a0e4a801fc3', 'customer2', 'Customer', 'India', '9898989898', '4040404040', 'local.customer2@local.com', 'Local Customer2', 'Local Customer2', 1, 1, sysdate(), sysdate(), sysdate() ),
('Customer3', 'Local', '21232f297a57a5a743894a0e4a801fc3', 'customer3', 'Customer', 'India', '9898989898', '4040404040', 'local.customer3@local.com', 'Local Customer3', 'Local Customer3', 1, 1, sysdate(), sysdate(), sysdate() ),
('Customer4', 'Local', '21232f297a57a5a743894a0e4a801fc3', 'customer4', 'Customer', 'India', '9898989898', '4040404040', 'local.customer4@local.com', 'Local Customer4', 'Local Customer4', 1, 1, sysdate(), sysdate(), sysdate() ),
('Customer5', 'Local', '21232f297a57a5a743894a0e4a801fc3', 'customer5', 'Customer', 'India', '9898989898', '4040404040', 'local.customer5@local.com', 'Local Customer5', 'Local Customer5', 1, 1, sysdate(), sysdate(), sysdate() );
-- Vendor
INSERT INTO `sainv`.`user`
(`firstName`, `lastName`, `password`, `username`, `accountType`, `billingAddress`, `mobilePhone`, `officePhone`,`email`, `description`, `notes`, `createadBy`, `modifiedBy`, `createdDate`, `modifiedDate`, `lastLoggedIn`) VALUES
('Vendor1', 'Local', '21232f297a57a5a743894a0e4a801fc3', 'vendor1', 'Vendor', 'India', '9898989898', '4040404040', 'local.customer1@local.com', 'Local Vendor1', 'Local Vendor1', 1, 1, sysdate(), sysdate(), sysdate() ),
('Vendor2', 'Local', '21232f297a57a5a743894a0e4a801fc3', 'vendor2', 'Vendor', 'India', '9898989898', '4040404040', 'local.customer2@local.com', 'Local Vendor2', 'Local Vendor2', 1, 1, sysdate(), sysdate(), sysdate() ),
('Vendor3', 'Local', '21232f297a57a5a743894a0e4a801fc3', 'vendor3', 'Vendor', 'India', '9898989898', '4040404040', 'local.customer3@local.com', 'Local Vendor3', 'Local Vendor3', 1, 1, sysdate(), sysdate(), sysdate() ),
('Vendor4', 'Local', '21232f297a57a5a743894a0e4a801fc3', 'vendor4', 'Vendor', 'India', '9898989898', '4040404040', 'local.customer4@local.com', 'Local Vendor4', 'Local Vendor4', 1, 1, sysdate(), sysdate(), sysdate() ),
('Vendor5', 'Local', '21232f297a57a5a743894a0e4a801fc3', 'vendor5', 'Vendor', 'India', '9898989898', '4040404040', 'local.customer5@local.com', 'Local Vendor5', 'Local Vendor5', 1, 1, sysdate(), sysdate(), sysdate() );
-- Product Group 
INSERT INTO `sainv`.`productGroup`
(`groupName`,`description`,`createadBy`,`modifiedBy`,`createdDate`,`modifiedDate`) VALUES
('ProductGroup1', 'Product Group One', 1, 1, sysdate(), sysdate()),
('ProductGroup2', 'Product Group Two', 1, 1, sysdate(), sysdate()),
('ProductGroup3', 'Product Group Three', 1, 1, sysdate(), sysdate()),
('ProductGroup4', 'Product Group Four', 1, 1, sysdate(), sysdate()),
('ProductGroup5', 'Product Group Five', 1, 1, sysdate(), sysdate());
-- Product
INSERT INTO `sainv`.`product`
(`productName`, `description`, `productGroup`, `price`, `createadBy`, `modifiedBy`, `createdDate`, `modifiedDate`) VALUES
('Product1Group1', 'Group One Product One', 1, 100, 1, 1, sysdate(), sysdate()),
('Product2Group1', 'Group One Product Two', 1, 125, 1, 1, sysdate(), sysdate()),
('Product3Group1', 'Group One Product Three', 1, 150, 1, 1, sysdate(), sysdate()),
('Product4Group1', 'Group One Product Four', 1, 175, 1, 1, sysdate(), sysdate()),
('Product5Group1', 'Group One Product Five', 1, 200, 1, 1, sysdate(), sysdate()),
('Product1Group2', 'Group Two Product One', 2, 100, 1, 1, sysdate(), sysdate()),
('Product2Group2', 'Group Two Product Two', 2, 125, 1, 1, sysdate(), sysdate()),
('Product3Group2', 'Group Two Product Three', 2, 150, 1, 1, sysdate(), sysdate()),
('Product4Group2', 'Group Two Product Four', 2, 175, 1, 1, sysdate(), sysdate()),
('Product5Group2', 'Group Two Product Five', 2, 200, 1, 1, sysdate(), sysdate()),
('Product1Group3', 'Group Three Product One', 3, 100, 1, 1, sysdate(), sysdate()),
('Product2Group3', 'Group Three Product Two', 3, 125, 1, 1, sysdate(), sysdate()),
('Product3Group3', 'Group Three Product Three', 3, 150, 1, 1, sysdate(), sysdate()),
('Product4Group3', 'Group Three Product Four', 3, 175, 1, 1, sysdate(), sysdate()),
('Product5Group3', 'Group Three Product Five', 3, 200, 1, 1, sysdate(), sysdate()),
('Product1Group4', 'Group Four Product One', 4, 100, 1, 1, sysdate(), sysdate()),
('Product2Group4', 'Group Four Product Two', 4, 125, 1, 1, sysdate(), sysdate()),
('Product3Group4', 'Group Four Product Three', 4, 150, 1, 1, sysdate(), sysdate()),
('Product4Group4', 'Group Four Product Four', 4, 175, 1, 1, sysdate(), sysdate()),
('Product5Group4', 'Group Four Product Five', 4, 200, 1, 1, sysdate(), sysdate()),
('Product1Group5', 'Group Five Product One', 5, 100, 1, 1, sysdate(), sysdate()),
('Product2Group5', 'Group Five Product Two', 5, 125, 1, 1, sysdate(), sysdate()),
('Product3Group5', 'Group Five Product Three', 5, 150, 1, 1, sysdate(), sysdate()),
('Product4Group5', 'Group Five Product Four', 5, 175, 1, 1, sysdate(), sysdate()),
('Product5Group5', 'Group Five Product Five', 5, 200, 1, 1, sysdate(), sysdate());
-- Purchase Order
INSERT INTO `sainv`.`purchaseOrder`
(`orderId`, `accountId`, `orderDate`, `referenceNumber`, `dueDate`, `comments`, `discount`, `orderType`, `createdBy`, `modifiedBy`, `createdDate`, `modifiedDate`, `total`) VALUES
('PurchaseOrder1', 1, DATE_SUB(CURDATE(),INTERVAL 0 DAY), 'OrderOne', sysdate()+5, 'First Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 5350),
('PurchaseOrder2', 2, DATE_SUB(CURDATE(),INTERVAL 0 DAY), 'OrderTwo', sysdate()+5, 'Second Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 5350),
('PurchaseOrder3', 3, DATE_SUB(CURDATE(),INTERVAL 0 DAY), 'OrderThree', sysdate()+5, 'Third Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 5350),
('PurchaseOrder4', 1, DATE_SUB(CURDATE(),INTERVAL 1 DAY), 'OrderFour', sysdate()+5, 'Fourth Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 8350),
('PurchaseOrder5', 2, DATE_SUB(CURDATE(),INTERVAL 1 DAY), 'OrderFive', sysdate()+5, 'Fifth Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 7350),
('PurchaseOrder6', 3, DATE_SUB(CURDATE(),INTERVAL 1 DAY), 'OrderSix', sysdate()+5, 'Sixth Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 5350),
('PurchaseOrder7', 1, DATE_SUB(CURDATE(),INTERVAL 2 DAY), 'OrderSeven', sysdate()+5, 'Seventh Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 5350),
('PurchaseOrder8', 2, DATE_SUB(CURDATE(),INTERVAL 2 DAY), 'OrderEight', sysdate()+5, 'Eight Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 6350),
('PurchaseOrder9', 3, DATE_SUB(CURDATE(),INTERVAL 2 DAY), 'OrderNine', sysdate()+5, 'Nineth Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 5350),
('PurchaseOrder10', 1, DATE_SUB(CURDATE(),INTERVAL 3 DAY), 'OrderTen', sysdate()+5, 'Tenth Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 5350),
('PurchaseOrder11', 1, DATE_SUB(CURDATE(),INTERVAL 3 DAY), 'OrderOne', sysdate()+5, 'First Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 1350),
('PurchaseOrder12', 2, DATE_SUB(CURDATE(),INTERVAL 3 DAY), 'OrderTwo', sysdate()+5, 'Second Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 5350),
('PurchaseOrder13', 3, DATE_SUB(CURDATE(),INTERVAL 3 DAY), 'OrderThree', sysdate()+5, 'Third Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 5350),
('PurchaseOrder14', 1, DATE_SUB(CURDATE(),INTERVAL 4 DAY), 'OrderFour', sysdate()+5, 'Fourth Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 2350),
('PurchaseOrder15', 2, DATE_SUB(CURDATE(),INTERVAL 4 DAY), 'OrderFive', sysdate()+5, 'Fifth Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 5350),
('PurchaseOrder16', 3, DATE_SUB(CURDATE(),INTERVAL 4 DAY), 'OrderSix', sysdate()+5, 'Sixth Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 5350),
('PurchaseOrder17', 1, DATE_SUB(CURDATE(),INTERVAL 5 DAY), 'OrderSeven', sysdate()+5, 'Seventh Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 6750),
('PurchaseOrder18', 2, DATE_SUB(CURDATE(),INTERVAL 5 DAY), 'OrderEight', sysdate()+5, 'Eight Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 3350),
('PurchaseOrder19', 3, DATE_SUB(CURDATE(),INTERVAL 5 DAY), 'OrderNine', sysdate()+5, 'Nineth Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 9150),
('PurchaseOrder20', 1, DATE_SUB(CURDATE(),INTERVAL 6 DAY), 'OrderTen', sysdate()+5, 'Tenth Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 4550),
('PurchaseOrder21', 1, DATE_SUB(CURDATE(),INTERVAL 1 MONTH), 'OrderOne', sysdate()+5, 'First Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 9850),
('PurchaseOrder22', 2, DATE_SUB(CURDATE(),INTERVAL 1 MONTH), 'OrderTwo', sysdate()+5, 'Second Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 7850),
('PurchaseOrder23', 3, DATE_SUB(CURDATE(),INTERVAL 1 MONTH), 'OrderThree', sysdate()+5, 'Third Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 6750),
('PurchaseOrder24', 1, DATE_SUB(CURDATE(),INTERVAL 2 MONTH), 'OrderFour', sysdate()+5, 'Fourth Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 5350),
('PurchaseOrder25', 2, DATE_SUB(CURDATE(),INTERVAL 2 MONTH), 'OrderFive', sysdate()+5, 'Fifth Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 6350),
('PurchaseOrder26', 3, DATE_SUB(CURDATE(),INTERVAL 2 MONTH), 'OrderSix', sysdate()+5, 'Sixth Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 7350),
('PurchaseOrder27', 1, DATE_SUB(CURDATE(),INTERVAL 3 MONTH), 'OrderSeven', sysdate()+5, 'Seventh Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 8350),
('PurchaseOrder28', 2, DATE_SUB(CURDATE(),INTERVAL 3 MONTH), 'OrderEight', sysdate()+5, 'Eight Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 9350),
('PurchaseOrder29', 3, DATE_SUB(CURDATE(),INTERVAL 3 MONTH), 'OrderNine', sysdate()+5, 'Nineth Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 7350),
('PurchaseOrder30', 1, DATE_SUB(CURDATE(),INTERVAL 4 MONTH), 'OrderTen', sysdate()+5, 'Tenth Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 6350),
('PurchaseOrder31', 1, DATE_SUB(CURDATE(),INTERVAL 1 YEAR), 'OrderOne', sysdate()+5, 'First Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 4350),
('PurchaseOrder32', 2, DATE_SUB(CURDATE(),INTERVAL 1 YEAR), 'OrderTwo', sysdate()+5, 'Second Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 3350),
('PurchaseOrder33', 3, DATE_SUB(CURDATE(),INTERVAL 1 YEAR), 'OrderThree', sysdate()+5, 'Third Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 1350),
('PurchaseOrder34', 1, DATE_SUB(CURDATE(),INTERVAL 2 YEAR), 'OrderFour', sysdate()+5, 'Fourth Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 9350),
('PurchaseOrder35', 2, DATE_SUB(CURDATE(),INTERVAL 2 YEAR), 'OrderFive', sysdate()+5, 'Fifth Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 8350),
('PurchaseOrder36', 3, DATE_SUB(CURDATE(),INTERVAL 2 YEAR), 'OrderSix', sysdate()+5, 'Sixth Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 7350),
('PurchaseOrder37', 1, DATE_SUB(CURDATE(),INTERVAL 3 YEAR), 'OrderSeven', sysdate()+5, 'Seventh Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 6350),
('PurchaseOrder38', 2, DATE_SUB(CURDATE(),INTERVAL 3 YEAR), 'OrderEight', sysdate()+5, 'Eight Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 4350),
('PurchaseOrder39', 3, DATE_SUB(CURDATE(),INTERVAL 3 YEAR), 'OrderNine', sysdate()+5, 'Nineth Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 3350),
('PurchaseOrder40', 1, DATE_SUB(CURDATE(),INTERVAL 4 YEAR), 'OrderTen', sysdate()+5, 'Tenth Order', 10, 'Purchase', 1, 1, sysdate(), sysdate(), 2350);
-- Purchase Order Items
INSERT INTO `sainv`.`purchaseOrderItems`
(`orderId`, `productId`, `quantity`, `unitPrice`, `discount`) VALUES
(1, 1, 10, 10, 10), (1, 2, 20, 15, 10), (1, 3, 30, 20, 10), (1, 4, 40, 25, 10), (1, 5, 45, 30, 10), (1, 6, 50, 40, 10),
(2, 1, 10, 10, 10), (2, 2, 20, 15, 10), (2, 3, 30, 20, 10), (2, 4, 40, 25, 10), (2, 5, 45, 30, 10), (2, 6, 50, 40, 10),
(3, 1, 10, 10, 10), (3, 2, 20, 15, 10), (3, 3, 30, 20, 10), (3, 4, 40, 25, 10), (3, 5, 45, 30, 10), (3, 6, 50, 40, 10),
(4, 1, 10, 10, 10), (4, 2, 20, 15, 10), (4, 3, 30, 20, 10), (4, 4, 40, 25, 10), (4, 5, 45, 30, 10), (4, 6, 50, 40, 10),
(5, 1, 10, 10, 10), (5, 2, 20, 15, 10), (5, 3, 30, 20, 10), (5, 4, 40, 25, 10), (5, 5, 45, 30, 10), (5, 6, 50, 40, 10),
(6, 1, 10, 10, 10), (6, 2, 20, 15, 10), (6, 3, 30, 20, 10), (6, 4, 40, 25, 10), (6, 5, 45, 30, 10), (6, 6, 50, 40, 10),
(7, 1, 10, 10, 10), (7, 2, 20, 15, 10), (7, 3, 30, 20, 10), (7, 4, 40, 25, 10), (7, 5, 45, 30, 10), (7, 6, 50, 40, 10),
(8, 1, 10, 10, 10), (8, 2, 20, 15, 10), (8, 3, 30, 20, 10), (8, 4, 40, 25, 10), (8, 5, 45, 30, 10), (8, 6, 50, 40, 10),
(9, 1, 10, 10, 10), (9, 2, 20, 15, 10), (9, 3, 30, 20, 10), (9, 4, 40, 25, 10), (9, 5, 45, 30, 10), (9, 6, 50, 40, 10),
(10, 1, 10, 10, 10), (10, 2, 20, 15, 10), (10, 3, 30, 20, 10), (10, 4, 40, 25, 10), (10, 5, 45, 30, 10), (10, 6, 50, 40, 10);
-- Sales Order
INSERT INTO `sainv`.`salesOrder`
(`orderId`, `accountId`, `orderDate`, `referenceNumber`, `dueDate`, `comments`, `discount`, `orderType`, `createdBy`, `modifiedBy`, `createdDate`, `modifiedDate`, `total`) VALUES
('SaleOrder1', 1, DATE_SUB(CURDATE(),INTERVAL 0 DAY), 'OrderOne', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'First Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder2', 2, DATE_SUB(CURDATE(),INTERVAL 0 DAY), 'OrderTwo', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'Second Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder3', 3, DATE_SUB(CURDATE(),INTERVAL 0 DAY), 'OrderThree', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'Third Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder4', 1, DATE_SUB(CURDATE(),INTERVAL 1 DAY), 'OrderFour', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'Fourth Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder5', 2, DATE_SUB(CURDATE(),INTERVAL 1 DAY), 'OrderFive', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'Fifth Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder6', 3, DATE_SUB(CURDATE(),INTERVAL 1 DAY), 'OrderSix', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'Sixth Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder7', 1, DATE_SUB(CURDATE(),INTERVAL 2 DAY), 'OrderSeven', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'Seventh Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder8', 2, DATE_SUB(CURDATE(),INTERVAL 2 DAY), 'OrderEight', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'Eight Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder9', 3, DATE_SUB(CURDATE(),INTERVAL 2 DAY), 'OrderNine', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'Nineth Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder10', 1, DATE_SUB(CURDATE(),INTERVAL 3 DAY), 'OrderTen', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'Tenth Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder11', 1, DATE_SUB(CURDATE(),INTERVAL 3 DAY), 'OrderOne', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'First Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder12', 2, DATE_SUB(CURDATE(),INTERVAL 3 DAY), 'OrderTwo', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'Second Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder13', 3, DATE_SUB(CURDATE(),INTERVAL 3 DAY), 'OrderThree', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'Third Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder14', 1, DATE_SUB(CURDATE(),INTERVAL 4 DAY), 'OrderFour', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'Fourth Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder15', 2, DATE_SUB(CURDATE(),INTERVAL 4 DAY), 'OrderFive', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'Fifth Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder16', 3, DATE_SUB(CURDATE(),INTERVAL 4 DAY), 'OrderSix', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'Sixth Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder17', 1, DATE_SUB(CURDATE(),INTERVAL 5 DAY), 'OrderSeven', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'Seventh Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder18', 2, DATE_SUB(CURDATE(),INTERVAL 5 DAY), 'OrderEight', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'Eight Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder19', 3, DATE_SUB(CURDATE(),INTERVAL 5 DAY), 'OrderNine', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'Nineth Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder20', 1, DATE_SUB(CURDATE(),INTERVAL 6 DAY), 'OrderTen', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'Tenth Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder21', 1, DATE_SUB(CURDATE(),INTERVAL 1 MONTH), 'OrderOne', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'First Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder22', 2, DATE_SUB(CURDATE(),INTERVAL 1 MONTH), 'OrderTwo', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'Second Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder23', 3, DATE_SUB(CURDATE(),INTERVAL 1 MONTH), 'OrderThree', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'Third Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder24', 1, DATE_SUB(CURDATE(),INTERVAL 2 MONTH), 'OrderFour', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'Fourth Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder25', 2, DATE_SUB(CURDATE(),INTERVAL 2 MONTH), 'OrderFive', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'Fifth Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder26', 3, DATE_SUB(CURDATE(),INTERVAL 2 MONTH), 'OrderSix', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'Sixth Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder27', 1, DATE_SUB(CURDATE(),INTERVAL 3 MONTH), 'OrderSeven', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'Seventh Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder28', 2, DATE_SUB(CURDATE(),INTERVAL 3 MONTH), 'OrderEight', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'Eight Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder29', 3, DATE_SUB(CURDATE(),INTERVAL 3 MONTH), 'OrderNine', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'Nineth Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder30', 1, DATE_SUB(CURDATE(),INTERVAL 4 MONTH), 'OrderTen', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'Tenth Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder31', 1, DATE_SUB(CURDATE(),INTERVAL 4 MONTH), 'OrderOne', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'First Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder32', 2, DATE_SUB(CURDATE(),INTERVAL 4 MONTH), 'OrderTwo', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'Second Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder33', 3, DATE_SUB(CURDATE(),INTERVAL 4 MONTH), 'OrderThree', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'Third Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder34', 1, DATE_SUB(CURDATE(),INTERVAL 1 YEAR), 'OrderFour', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'Fourth Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder35', 2, DATE_SUB(CURDATE(),INTERVAL 1 YEAR), 'OrderFive', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'Fifth Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder36', 3, DATE_SUB(CURDATE(),INTERVAL 1 YEAR), 'OrderSix', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'Sixth Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder37', 1, DATE_SUB(CURDATE(),INTERVAL 2 YEAR), 'OrderSeven', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'Seventh Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder38', 2, DATE_SUB(CURDATE(),INTERVAL 2 YEAR), 'OrderEight', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'Eight Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder39', 3, DATE_SUB(CURDATE(),INTERVAL 2 YEAR), 'OrderNine', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'Nineth Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350),
('SaleOrder40', 1, DATE_SUB(CURDATE(),INTERVAL 3 YEAR), 'OrderTen', DATE_ADD(CURDATE(),INTERVAL 5 DAY), 'Tenth Order', 10, 'Sales', 1, 1, sysdate(), sysdate(), 5350);
-- Sales Order Items
INSERT INTO `sainv`.`salesOrderItems`
(`orderId`, `productId`, `quantity`, `unitPrice`, `discount`, `total`) VALUES
(1, 1, 10, 10, 10, 100), (1, 2, 20, 15, 10, 300), (1, 3, 30, 20, 10, 600), (1, 4, 40, 25, 10, 1000), (1, 5, 45, 30, 10, 1350), (1, 6, 50, 40, 10, 2000),
(2, 1, 10, 10, 10, 100), (2, 2, 20, 15, 10, 300), (2, 3, 30, 20, 10, 600), (2, 4, 40, 25, 10, 1000), (2, 5, 45, 30, 10, 1350), (2, 6, 50, 40, 10, 2000),
(3, 1, 10, 10, 10, 100), (3, 2, 20, 15, 10, 300), (3, 3, 30, 20, 10, 600), (3, 4, 40, 25, 10, 1000), (3, 5, 45, 30, 10, 1350), (3, 6, 50, 40, 10, 2000),
(4, 1, 10, 10, 10, 100), (4, 2, 20, 15, 10, 300), (4, 3, 30, 20, 10, 600), (4, 4, 40, 25, 10, 1000), (4, 5, 45, 30, 10, 1350), (4, 6, 50, 40, 10, 2000),
(5, 1, 10, 10, 10, 100), (5, 2, 20, 15, 10, 300), (5, 3, 30, 20, 10, 600), (5, 4, 40, 25, 10, 1000), (5, 5, 45, 30, 10, 1350), (5, 6, 50, 40, 10, 2000),
(6, 1, 10, 10, 10, 100), (6, 2, 20, 15, 10, 300), (6, 3, 30, 20, 10, 600), (6, 4, 40, 25, 10, 1000), (6, 5, 45, 30, 10, 1350), (6, 6, 50, 40, 10, 2000),
(7, 1, 10, 10, 10, 100), (7, 2, 20, 15, 10, 300), (7, 3, 30, 20, 10, 600), (7, 4, 40, 25, 10, 1000), (7, 5, 45, 30, 10, 1350), (7, 6, 50, 40, 10, 2000),
(8, 1, 10, 10, 10, 100), (8, 2, 20, 15, 10, 300), (8, 3, 30, 20, 10, 600), (8, 4, 40, 25, 10, 1000), (8, 5, 45, 30, 10, 1350), (8, 6, 50, 40, 10, 2000),
(9, 1, 10, 10, 10, 100), (9, 2, 20, 15, 10, 300), (9, 3, 30, 20, 10, 600), (9, 4, 40, 25, 10, 1000), (9, 5, 45, 30, 10, 1350), (9, 6, 50, 40, 10, 2000),
(10, 1, 10, 10, 10, 100), (10, 2, 20, 15, 10, 300), (10, 3, 30, 20, 10, 600), (10, 4, 40, 25, 10, 1000), (10, 5, 45, 30, 10, 1350), (10, 6, 50, 40, 10, 2000);
-- User Balance
INSERT INTO `sainv`.`userBalance`
(`accountId`,`amount`,`createdBy`,`modifiedBy`,`createdDate`,`modifiedDate`, `notes`) VALUES
(1, 1000, 1, 1, sysdate(), sysdate(), 'Balance'),
(2, 2000, 1, 1, sysdate(), sysdate(), 'Balance'),
(3, 3000, 1, 1, sysdate(), sysdate(), 'Balance'),
(4, 4000, 1, 1, sysdate(), sysdate(), 'Balance'),
(5, 5000, 1, 1, sysdate(), sysdate(), 'Balance'),
(6, 6000, 1, 1, sysdate(), sysdate(), 'Balance'),
(7, 7000, 1, 1, sysdate(), sysdate(), 'Balance'),
(8, 8000, 1, 1, sysdate(), sysdate(), 'Balance'),
(9, 9000, 1, 1, sysdate(), sysdate(), 'Balance'),
(10, 10000, 1, 1, sysdate(), sysdate(), 'Balance'),
(11, 11000, 1, 1, sysdate(), sysdate(), 'Balance'),
(12, 12000, 1, 1, sysdate(), sysdate(), 'Balance'),
(13, 13000, 1, 1, sysdate(), sysdate(), 'Balance'),
(14, 14000, 1, 1, sysdate(), sysdate(), 'Balance'),
(15, 15000, 1, 1, sysdate(), sysdate(), 'Balance');
