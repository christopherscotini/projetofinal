/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50612
Source Host           : localhost:3306
Source Database       : mkoffice_old_final

Target Server Type    : MYSQL
Target Server Version : 50612
File Encoding         : 65001

Date: 2014-11-27 17:30:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_agenda
-- ----------------------------
DROP TABLE IF EXISTS `tb_agenda`;
CREATE TABLE `tb_agenda` (
  `ID_AGENDA` bigint(20) NOT NULL AUTO_INCREMENT,
  `DT_FIM` datetime DEFAULT NULL,
  `DT_INICIO` datetime DEFAULT NULL,
  `DS_AGENDA` varchar(255) DEFAULT NULL,
  `FL_ALLDAY` tinyint(1) DEFAULT NULL,
  `TB_CLIENTE_FK` bigint(20) DEFAULT NULL,
  `TB_TIPO_AGENDA_FK` bigint(20) DEFAULT NULL,
  `TB_USUARIO_FK` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID_AGENDA`),
  KEY `FK82E60AFDA438050A` (`TB_CLIENTE_FK`),
  KEY `FK82E60AFDB9C1A942` (`TB_USUARIO_FK`),
  KEY `FK82E60AFD422A9B35` (`TB_TIPO_AGENDA_FK`),
  CONSTRAINT `FK82E60AFD422A9B35` FOREIGN KEY (`TB_TIPO_AGENDA_FK`) REFERENCES `tb_tipo_agenda` (`ID_AGENDA`),
  CONSTRAINT `FK82E60AFDA438050A` FOREIGN KEY (`TB_CLIENTE_FK`) REFERENCES `tb_cliente` (`ID_CLIENTE`),
  CONSTRAINT `FK82E60AFDB9C1A942` FOREIGN KEY (`TB_USUARIO_FK`) REFERENCES `tb_user` (`ID_USER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_agenda
-- ----------------------------

-- ----------------------------
-- Table structure for tb_catalogo
-- ----------------------------
DROP TABLE IF EXISTS `tb_catalogo`;
CREATE TABLE `tb_catalogo` (
  `ID_CATALOGO` bigint(20) NOT NULL AUTO_INCREMENT,
  `FL_ATIVO` tinyint(1) DEFAULT NULL,
  `CD_PRODUTO` bigint(20) DEFAULT NULL,
  `NM_PRODUTO` varchar(255) DEFAULT NULL,
  `NU_PONTOS` int(11) DEFAULT NULL,
  `VL_PRODUTO` decimal(19,2) DEFAULT NULL,
  `CATEGORIA_CATALOGO_FK` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID_CATALOGO`)
) ENGINE=InnoDB AUTO_INCREMENT=340 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tb_catalogo
-- ----------------------------
INSERT INTO `tb_catalogo` VALUES ('1', '1', '10059507', 'Base Líquida TimeWise® acabamento Matte Ivory 5    ', '27', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('2', '1', '10059508', 'Base Líquida TimeWise® acabamento Matte Ivory 6    ', '27', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('3', '1', '10059509', 'Base Líquida TimeWise® acabamento Matte Ivory 7    ', '27', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('4', '1', '10059510', 'Base Líquida TimeWise® acabamento Matte Beige 1    ', '27', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('5', '1', '10038758', 'Base Líquida TimeWise® acabamento Matte Beige 2    ', '27', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('6', '1', '10059511', 'Base Líquida TimeWise® acabamento Matte Beige 2    ', '27', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('7', '1', '10059512', 'Base Líquida TimeWise® acabamento Matte Beige 3    ', '27', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('8', '1', '10059513', 'Base Líquida TimeWise® acabamento Matte Beige 4    ', '27', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('9', '1', '10059514', 'Base Líquida TimeWise® acabamento Matte Beige 5    ', '27', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('10', '1', '10059515', 'Base Líquida TimeWise® acabamento Matte Beige 6    ', '27', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('11', '1', '10038765', 'Base Líquida TimeWise® acabamento Matte Bronze 1    ', '27', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('12', '1', '10059519', 'Base Líquida TimeWise® acabamento Matte Bronze 1', '27', '57.00', '1');
INSERT INTO `tb_catalogo` VALUES ('13', '1', '10059520', 'Base Líquida TimeWise® acabamento Matte Bronze 2    ', '27', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('14', '1', '10059522', 'Base Líquida TimeWise® acabamento Matte Bronze 4    ', '27', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('15', '1', '10059523', 'Base Líquida TimeWise® acabamento Matte Bronze 5    ', '27', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('16', '1', '10059525', 'Base Líquida TimeWise® acabamento Matte Bronze 7    ', '27', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('17', '1', '10038701', 'Base Líquida TimeWise® acabamento Luminoso Ivory 5    ', '27', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('18', '1', '10059978', 'Base Líquida TimeWise® acabamento Luminoso Ivory 5    ', '27', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('19', '1', '10038702', 'Base Líquida TimeWise® acabamento Luminoso Ivory 6    ', '27', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('20', '1', '10059979', 'Base Líquida TimeWise® acabamento Luminoso Ivory 6    ', '27', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('21', '1', '10038703', 'Base Líquida TimeWise® acabamento Luminoso Ivory 7    ', '27', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('22', '1', '10059980', 'Base Líquida TimeWise® acabamento Luminoso Ivory 7    ', '27', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('23', '1', '10059527', 'Base Líquida TimeWise® acabamento Luminoso Beige 1    ', '27', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('24', '1', '10059528', 'Base Líquida TimeWise® acabamento Luminoso Beige 2    ', '27', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('25', '1', '10038706', 'Base Líquida TimeWise® acabamento Luminoso Beige 3    ', '27', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('26', '1', '10059529', 'Base Líquida TimeWise® acabamento Luminoso Beige 3    ', '27', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('27', '1', '10059530', 'Base Líquida TimeWise® acabamento Luminoso Beige 4    ', '27', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('28', '1', '10038709', 'Base Líquida TimeWise® acabamento Luminoso Beige 5    ', '27', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('29', '1', '10059531', 'Base Líquida TimeWise® acabamento Luminoso Beige 5    ', '27', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('30', '1', '10038710', 'Base Líquida TimeWise® acabamento Luminoso Beige 6    ', '27', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('31', '1', '10059532', 'Base Líquida TimeWise® acabamento Luminoso Beige 6    ', '27', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('32', '1', '10059535', 'Base Líquida TimeWise® acabamento Luminoso Bronze 1    ', '27', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('33', '1', '10059536', 'Base Líquida TimeWise® acabamento Luminoso Bronze 2    ', '27', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('34', '1', '10059538', 'Base Líquida TimeWise® acabamento Luminoso Bronze 4    ', '27', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('35', '1', '10038718', 'Base Líquida TimeWise® acabamento Luminoso Bronze 5    ', '27', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('36', '1', '10059539', 'Base Líquida TimeWise® acabamento Luminoso Bronze 5    ', '27', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('37', '1', '10059541', 'Base Líquida TimeWise® acabamento Luminoso Bronze 7    ', '27', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('38', '1', '10041998', 'Base de Cobertura Média Ivory 200    ', '20', '46.00', '1');
INSERT INTO `tb_catalogo` VALUES ('39', '1', '10042001', 'Base de Cobertura Média Ivory 204    ', '20', '46.00', '1');
INSERT INTO `tb_catalogo` VALUES ('40', '1', '10042002', 'Base de Cobertura Média Beige 300    ', '20', '46.00', '1');
INSERT INTO `tb_catalogo` VALUES ('41', '1', '10042003', 'Base de Cobertura Média Beige 302    ', '20', '46.00', '1');
INSERT INTO `tb_catalogo` VALUES ('42', '1', '10042004', 'Base de Cobertura Média Beige 304    ', '20', '46.00', '1');
INSERT INTO `tb_catalogo` VALUES ('43', '1', '10042006', 'Base de Cobertura Média Beige 400    ', '20', '46.00', '1');
INSERT INTO `tb_catalogo` VALUES ('44', '1', '10042007', 'Base de Cobertura Média Beige 402    ', '20', '46.00', '1');
INSERT INTO `tb_catalogo` VALUES ('45', '1', '10042009', 'Base de Cobertura Média Bronze 500    ', '20', '46.00', '1');
INSERT INTO `tb_catalogo` VALUES ('46', '1', '10040984', 'Base em Pó Mineral Ivory 1    ', '23', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('47', '1', '10040985', 'Base em Pó Mineral Ivory 2    ', '23', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('48', '1', '10040987', 'Base em Pó Mineral Beige 1    ', '23', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('49', '1', '10040989', 'Base em Pó Mineral Beige 2    ', '23', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('50', '1', '10040990', 'Base em Pó Mineral Bronze 1    ', '23', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('51', '1', '10040991', 'Base em Pó Mineral Bronze 2    ', '23', '54.00', '1');
INSERT INTO `tb_catalogo` VALUES ('52', '1', '10031512', 'Hidratante Tonalizante com FPS 20 Ivory 2    ', '23', '47.00', '1');
INSERT INTO `tb_catalogo` VALUES ('53', '1', '10031513', 'Hidratante Tonalizante com FPS 20 Beige 1    ', '23', '47.00', '1');
INSERT INTO `tb_catalogo` VALUES ('54', '1', '10031514', 'Hidratante Tonalizante com FPS 20 Beige 2    ', '23', '47.00', '1');
INSERT INTO `tb_catalogo` VALUES ('55', '1', '10031515', 'Hidratante Tonalizante com FPS 20 Bronze 1    ', '23', '47.00', '1');
INSERT INTO `tb_catalogo` VALUES ('56', '1', '10105900', 'Espuma de Limpeza Facial 3    ', '19', '47.00', '8');
INSERT INTO `tb_catalogo` VALUES ('57', '1', '10106600', 'Creme Hidratante Enriquecido 1    ', '26', '56.00', '8');
INSERT INTO `tb_catalogo` VALUES ('58', '1', '10106800', 'Fluido Hidratante para Controle da Oleosidade 3    ', '26', '56.00', '8');
INSERT INTO `tb_catalogo` VALUES ('59', '1', '10053074', 'Espuma de Limpeza Volu-Firm™ TimeWise Repair™    ', '42', '69.00', '11');
INSERT INTO `tb_catalogo` VALUES ('60', '1', '10048848', 'Sérum Lifting Volu-Firm™ TimeWise Repair™    ', '74', '132.00', '11');
INSERT INTO `tb_catalogo` VALUES ('61', '1', '10058796', 'Creme Diurno com FPS 30 Volu-Firm™ TimeWise Repair™    ', '57', '105.00', '11');
INSERT INTO `tb_catalogo` VALUES ('62', '1', '10057096', 'Creme Noturno Volu-Firm™ TimeWise Repair™    ', '57', '105.00', '11');
INSERT INTO `tb_catalogo` VALUES ('63', '1', '10047376', 'Creme para a Área dos OlhosVolu-Firm™ TimeWise Repair™   ', '69', '99.00', '11');
INSERT INTO `tb_catalogo` VALUES ('64', '1', '10072912', 'Kit Creme Diurno e Noturno Volu-Firm TimeWise Repair    ', '114', '189.00', '11');
INSERT INTO `tb_catalogo` VALUES ('65', '1', '10073276', 'Sistema Volu-Firm TimeWise Repair    ', '299', '489.00', '11');
INSERT INTO `tb_catalogo` VALUES ('66', '1', '10059570', 'Corretivo Ivory 1    ', '20', '32.00', '12');
INSERT INTO `tb_catalogo` VALUES ('67', '1', '10023467', 'Corretivo Ivory 1    ', '20', '32.00', '12');
INSERT INTO `tb_catalogo` VALUES ('68', '1', '10059571', 'Corretivo Ivory 2    ', '20', '32.00', '12');
INSERT INTO `tb_catalogo` VALUES ('69', '1', '10023468', 'Corretivo Ivory 2    ', '20', '32.00', '12');
INSERT INTO `tb_catalogo` VALUES ('70', '1', '10059572', 'Corretivo Beige 1    ', '20', '32.00', '12');
INSERT INTO `tb_catalogo` VALUES ('71', '1', '10023469', 'Corretivo Beige 1    ', '20', '32.00', '12');
INSERT INTO `tb_catalogo` VALUES ('72', '1', '10059573', 'Corretivo Beige 2    ', '20', '32.00', '12');
INSERT INTO `tb_catalogo` VALUES ('73', '1', '10023470', 'Corretivo Beige 2    ', '20', '32.00', '12');
INSERT INTO `tb_catalogo` VALUES ('74', '1', '10059574', 'Corretivo Bronze 1    ', '20', '32.00', '12');
INSERT INTO `tb_catalogo` VALUES ('75', '1', '10023471', 'Corretivo Bronze 1    ', '20', '32.00', '12');
INSERT INTO `tb_catalogo` VALUES ('76', '1', '10059575', 'Corretivo Bronze 2    ', '20', '32.00', '12');
INSERT INTO `tb_catalogo` VALUES ('77', '1', '10023472', 'Corretivo Bronze 2    ', '20', '32.00', '12');
INSERT INTO `tb_catalogo` VALUES ('78', '1', '10059576', 'Corretivo Yellow    ', '20', '32.00', '12');
INSERT INTO `tb_catalogo` VALUES ('79', '1', '10027243', 'Corretivo Yellow    ', '20', '32.00', '12');
INSERT INTO `tb_catalogo` VALUES ('80', '1', '10015136', 'Pó Mineral Compacto Ivory 2    ', '21', '39.00', '12');
INSERT INTO `tb_catalogo` VALUES ('81', '1', '10015137', 'Pó Mineral Compacto Beige 1    ', '21', '39.00', '12');
INSERT INTO `tb_catalogo` VALUES ('82', '1', '10015138', 'Pó Mineral Compacto Beige 2    ', '21', '39.00', '12');
INSERT INTO `tb_catalogo` VALUES ('83', '1', '10015139', 'Pó Mineral Compacto Bronze 1    ', '21', '39.00', '12');
INSERT INTO `tb_catalogo` VALUES ('84', '1', '10015140', 'Pó Mineral Compacto Bronze 2    ', '21', '39.00', '12');
INSERT INTO `tb_catalogo` VALUES ('85', '1', '10019029', 'Iluminador Facial Tom 2    ', '18', '42.00', '12');
INSERT INTO `tb_catalogo` VALUES ('86', '1', '10019031', 'Iluminador Facial Tom 3    ', '18', '42.00', '12');
INSERT INTO `tb_catalogo` VALUES ('87', '1', '10013008', 'Sombra Mineral Amber Blaze    ', '12', '21.00', '12');
INSERT INTO `tb_catalogo` VALUES ('88', '1', '10030111', 'Sombra Mineral Azure    ', '12', '21.00', '12');
INSERT INTO `tb_catalogo` VALUES ('89', '1', '10028589', 'Sombra Mineral Black Pearl    ', '12', '21.00', '12');
INSERT INTO `tb_catalogo` VALUES ('90', '1', '10013050', 'Sombra Mineral Blue Metal    ', '12', '21.00', '12');
INSERT INTO `tb_catalogo` VALUES ('91', '1', '10013035', 'Sombra Mineral Chocolate Kiss    ', '12', '21.00', '12');
INSERT INTO `tb_catalogo` VALUES ('92', '1', '10013038', 'Sombra Mineral Cinnabar    ', '12', '21.00', '12');
INSERT INTO `tb_catalogo` VALUES ('93', '1', '10046687', 'Sombra Mineral Classic Navy    ', '12', '21.00', '12');
INSERT INTO `tb_catalogo` VALUES ('94', '1', '10013026', 'Sombra Mineral Coal    ', '12', '21.00', '12');
INSERT INTO `tb_catalogo` VALUES ('95', '1', '10013044', 'Sombra Mineral Crystalline    ', '12', '21.00', '12');
INSERT INTO `tb_catalogo` VALUES ('96', '1', '10013056', 'Sombra Mineral Denim Frost    ', '12', '21.00', '12');
INSERT INTO `tb_catalogo` VALUES ('97', '1', '10013053', 'Sombra Mineral Dusty Lilac    ', '12', '21.00', '12');
INSERT INTO `tb_catalogo` VALUES ('98', '1', '10030110', 'Sombra Mineral Emerald    ', '12', '21.00', '12');
INSERT INTO `tb_catalogo` VALUES ('99', '1', '10013047', 'Sombra Mineral Espresso    ', '12', '21.00', '12');
INSERT INTO `tb_catalogo` VALUES ('100', '1', '10046684', 'Sombra Mineral Gold Coast    ', '12', '21.00', '12');
INSERT INTO `tb_catalogo` VALUES ('101', '1', '10013080', 'Sombra Mineral Granite    ', '12', '21.00', '12');
INSERT INTO `tb_catalogo` VALUES ('102', '1', '10046680', 'Sombra Mineral Jade    ', '12', '21.00', '12');
INSERT INTO `tb_catalogo` VALUES ('103', '1', '10013032', 'Sombra Mineral Honey Spice    ', '12', '21.00', '12');
INSERT INTO `tb_catalogo` VALUES ('104', '1', '10013095', 'Sombra Mineral Iris    ', '12', '21.00', '12');
INSERT INTO `tb_catalogo` VALUES ('105', '1', '10013062', 'Sombra Mineral Ivy Garden    ', '12', '21.00', '12');
INSERT INTO `tb_catalogo` VALUES ('106', '1', '10030107', 'Sombra Mineral Lavander Fog    ', '12', '21.00', '12');
INSERT INTO `tb_catalogo` VALUES ('107', '1', '10013005', 'Sombra Mineral Lemongrass    ', '12', '21.00', '12');
INSERT INTO `tb_catalogo` VALUES ('108', '1', '10028588', 'Sombra Mineral Midnight Star    ', '12', '21.00', '12');
INSERT INTO `tb_catalogo` VALUES ('109', '1', '10030106', 'Sombra Mineral Peacock Blue    ', '12', '21.00', '12');
INSERT INTO `tb_catalogo` VALUES ('110', '1', '10013074', 'Sombra Mineral Precious Pink    ', '12', '21.00', '12');
INSERT INTO `tb_catalogo` VALUES ('111', '1', '10046681', 'Sombra Mineral Truffle    ', '12', '21.00', '12');
INSERT INTO `tb_catalogo` VALUES ('112', '1', '10026297', 'Sombra Mineral Silver Satin    ', '12', '21.00', '12');
INSERT INTO `tb_catalogo` VALUES ('113', '1', '10046683', 'Sombra Mineral Sterling    ', '12', '21.00', '12');
INSERT INTO `tb_catalogo` VALUES ('114', '1', '10013101', 'Sombra Mineral Sweet Plum    ', '12', '21.00', '12');
INSERT INTO `tb_catalogo` VALUES ('115', '1', '10026292', 'Sombra Mineral White Lilly    ', '12', '21.00', '12');
INSERT INTO `tb_catalogo` VALUES ('116', '1', '10017587', 'Delineador Líquido para os Olhos Preto    ', '23', '35.00', '12');
INSERT INTO `tb_catalogo` VALUES ('117', '1', '10051607', 'Lápis Retrátil para os Olhos - Black    ', '18', '33.00', '12');
INSERT INTO `tb_catalogo` VALUES ('118', '1', '10051608', 'Lápis Retrátil para os Olhos - Deep Brown    ', '18', '33.00', '12');
INSERT INTO `tb_catalogo` VALUES ('119', '1', '10051610', 'Lápis Retrátil para os Olhos - Dark Denim    ', '18', '33.00', '12');
INSERT INTO `tb_catalogo` VALUES ('120', '1', '10051613', 'Lápis Retrátil para os Olhos - Rich Jade    ', '18', '33.00', '12');
INSERT INTO `tb_catalogo` VALUES ('121', '1', '10051609', 'Lápis Retrátil para os Olhos - Steely    ', '18', '33.00', '12');
INSERT INTO `tb_catalogo` VALUES ('122', '1', '10051611', 'Lápis Retrátil para os Olhos - Violet Ink    ', '18', '33.00', '12');
INSERT INTO `tb_catalogo` VALUES ('123', '1', '10027859', 'Gel para Sobrancelhas    ', '14', '27.00', '12');
INSERT INTO `tb_catalogo` VALUES ('124', '1', '10027861', 'Kit para Sobrancelhas    ', '8', '15.00', '12');
INSERT INTO `tb_catalogo` VALUES ('125', '1', '10034730', 'Lápis Delineador para Sobrancelhas Classic Blonde    ', '18', '30.00', '12');
INSERT INTO `tb_catalogo` VALUES ('126', '1', '10034731', 'Lápis Delineador para Sobrancelhas Brunette    ', '18', '30.00', '12');
INSERT INTO `tb_catalogo` VALUES ('127', '1', '10054563', 'Máscara Alongadora para Cílios Lash Love Preta    ', '25', '51.00', '12');
INSERT INTO `tb_catalogo` VALUES ('128', '1', '10035651', 'Ultimate Máscara para Cílios Preta    ', '25', '51.00', '12');
INSERT INTO `tb_catalogo` VALUES ('129', '1', '10049293', 'Máscara para Cílios Lash Love Preta    ', '25', '51.00', '12');
INSERT INTO `tb_catalogo` VALUES ('130', '1', '10049289', 'Máscara para Cílios Lash Love à Prova D\'água Preta    ', '25', '51.00', '12');
INSERT INTO `tb_catalogo` VALUES ('131', '1', '10046176', 'Máscara Incolor Primer para Cílios    ', '19', '37.00', '12');
INSERT INTO `tb_catalogo` VALUES ('132', '1', '10046188', 'Sérum Fortalecedor de Cílios e Sobrancelhas    ', '48', '89.00', '12');
INSERT INTO `tb_catalogo` VALUES ('133', '1', '10042768', 'Demaquilante para Área dos Olhos    ', '26', '51.00', '12');
INSERT INTO `tb_catalogo` VALUES ('134', '1', '10039851', 'Primer Facial Fixador de Maquiagem    ', '29', '56.00', '12');
INSERT INTO `tb_catalogo` VALUES ('135', '1', '10016960', 'Eye Primer Fixador de Sombra    ', '28', '42.00', '12');
INSERT INTO `tb_catalogo` VALUES ('136', '1', '10012983', 'Blush Mineral Cherry Blosson    ', '18', '32.00', '12');
INSERT INTO `tb_catalogo` VALUES ('137', '1', '10012977', 'Blush Mineral Cinnamon Stick    ', '18', '32.00', '12');
INSERT INTO `tb_catalogo` VALUES ('138', '1', '10026286', 'Blush Mineral Citrus Bloom    ', '18', '32.00', '12');
INSERT INTO `tb_catalogo` VALUES ('139', '1', '10012965', 'Blush Mineral Golden Copper    ', '18', '32.00', '12');
INSERT INTO `tb_catalogo` VALUES ('140', '1', '10012974', 'Blush Mineral Shy Blush    ', '18', '32.00', '12');
INSERT INTO `tb_catalogo` VALUES ('141', '1', '10012950', 'Blush Mineral Sparkling Cider    ', '18', '32.00', '12');
INSERT INTO `tb_catalogo` VALUES ('142', '1', '10012980', 'Blush Mineral Strawberry Cream    ', '18', '32.00', '12');
INSERT INTO `tb_catalogo` VALUES ('143', '1', '10016243', 'Blush Mineral Sunny Spice    ', '18', '32.00', '12');
INSERT INTO `tb_catalogo` VALUES ('144', '1', '10031780', 'Blush em Creme Cranberry    ', '19', '35.00', '12');
INSERT INTO `tb_catalogo` VALUES ('145', '1', '10031782', 'Blush em Creme Sheer Bliss    ', '19', '35.00', '12');
INSERT INTO `tb_catalogo` VALUES ('146', '1', '10016165', 'Pó Mineral Bronzeador Sandstone (matte)    ', '19', '35.00', '12');
INSERT INTO `tb_catalogo` VALUES ('147', '1', '10016610', 'Pó Mineral Bronzeador Desert Sun (com brilho)    ', '19', '35.00', '12');
INSERT INTO `tb_catalogo` VALUES ('148', '1', '10016167', 'Pó Mineral Bronzeador Bronze Diva (matte)    ', '19', '35.00', '12');
INSERT INTO `tb_catalogo` VALUES ('149', '1', '10016611', 'Pó Mineral Bronzeador Canyon Gold (com brilho)    ', '19', '35.00', '12');
INSERT INTO `tb_catalogo` VALUES ('150', '1', '10016615', 'Pó Mineral Iluminador Pink Stardust    ', '19', '35.00', '12');
INSERT INTO `tb_catalogo` VALUES ('151', '1', '10051589', 'Lápis Retrátil para os Lábios Capuccino    ', '18', '31.00', '12');
INSERT INTO `tb_catalogo` VALUES ('152', '1', '10051595', 'Lápis Retrátil para os Lábios Clear    ', '18', '31.00', '12');
INSERT INTO `tb_catalogo` VALUES ('153', '1', '10051587', 'Lápis Retrátil para os Lábios Neutral    ', '18', '31.00', '12');
INSERT INTO `tb_catalogo` VALUES ('154', '1', '10051592', 'Lápis Retrátil para os Lábios Soft Blush    ', '18', '31.00', '12');
INSERT INTO `tb_catalogo` VALUES ('155', '1', '10054822', 'Batom True Dimensions Color me Coral    ', '26', '47.00', '12');
INSERT INTO `tb_catalogo` VALUES ('156', '1', '10054828', 'Batom True Dimensions Firecracker    ', '26', '47.00', '12');
INSERT INTO `tb_catalogo` VALUES ('157', '1', '10054823', 'Batom True Dimensions First Blush    ', '26', '47.00', '12');
INSERT INTO `tb_catalogo` VALUES ('158', '1', '10054825', 'Batom True Dimensions Natural Beaute    ', '26', '47.00', '12');
INSERT INTO `tb_catalogo` VALUES ('159', '1', '10054820', 'Batom True Dimensions Pink Cheríe    ', '26', '47.00', '12');
INSERT INTO `tb_catalogo` VALUES ('160', '1', '10054830', 'Batom True Dimensions Rosette    ', '26', '47.00', '12');
INSERT INTO `tb_catalogo` VALUES ('161', '1', '10054829', 'Batom True Dimensions Sizzling Red    ', '26', '47.00', '12');
INSERT INTO `tb_catalogo` VALUES ('162', '1', '10054821', 'Batom True Dimensions Wild about Pink    ', '26', '47.00', '12');
INSERT INTO `tb_catalogo` VALUES ('163', '1', '10022936', 'Batom Cremoso Amber Suede', '19', '37.00', '12');
INSERT INTO `tb_catalogo` VALUES ('164', '1', '10022942', 'Batom Cremoso Apple Berry', '19', '37.00', '12');
INSERT INTO `tb_catalogo` VALUES ('165', '1', '10022944', 'Batom Cremoso Berry Kiss    ', '19', '35.00', '12');
INSERT INTO `tb_catalogo` VALUES ('166', '1', '10022958', 'Batom Cremoso Dusty Rose    ', '19', '35.00', '12');
INSERT INTO `tb_catalogo` VALUES ('167', '1', '10022952', 'Batom Cremoso Frosted Rose    ', '19', '35.00', '12');
INSERT INTO `tb_catalogo` VALUES ('168', '1', '10040717', 'Batom Cremoso Fuchsia', '19', '37.00', '12');
INSERT INTO `tb_catalogo` VALUES ('169', '1', '10022937', 'Batom Cremoso Gingerbread    ', '19', '35.00', '12');
INSERT INTO `tb_catalogo` VALUES ('170', '1', '10022961', 'Batom Cremoso Hibiscus    ', '19', '35.00', '12');
INSERT INTO `tb_catalogo` VALUES ('171', '1', '10029478', 'Batom Cremoso Icy Peach', '19', '37.00', '12');
INSERT INTO `tb_catalogo` VALUES ('172', '1', '10040707', 'Batom Cremoso Midnight Red    ', '19', '35.00', '12');
INSERT INTO `tb_catalogo` VALUES ('173', '1', '10022957', 'Batom Cremoso Pink Melon    ', '19', '35.00', '12');
INSERT INTO `tb_catalogo` VALUES ('174', '1', '10029472', 'Batom Cremoso Pink Passion    ', '19', '35.00', '12');
INSERT INTO `tb_catalogo` VALUES ('175', '1', '10022960', 'Batom Cremoso Pink Satin', '19', '37.00', '12');
INSERT INTO `tb_catalogo` VALUES ('176', '1', '10022935', 'Batom Cremoso Raisinberry    ', '19', '35.00', '12');
INSERT INTO `tb_catalogo` VALUES ('177', '1', '10022965', 'Batom Cremoso Red', '19', '37.00', '12');
INSERT INTO `tb_catalogo` VALUES ('178', '1', '10022945', 'Batom Cremoso Sheer Blush    ', '19', '35.00', '12');
INSERT INTO `tb_catalogo` VALUES ('179', '1', '10022954', 'Batom Cremoso Shell', '19', '37.00', '12');
INSERT INTO `tb_catalogo` VALUES ('180', '1', '10040712', 'Batom Cremoso Sunny Citrus    ', '19', '35.00', '12');
INSERT INTO `tb_catalogo` VALUES ('181', '1', '10022963', 'Batom Cremoso Sweet Nectar    ', '19', '35.00', '12');
INSERT INTO `tb_catalogo` VALUES ('182', '1', '10022955', 'Batom Cremoso Tanned', '19', '37.00', '12');
INSERT INTO `tb_catalogo` VALUES ('183', '1', '10022953', 'Batom Cremoso Toffee    ', '19', '35.00', '12');
INSERT INTO `tb_catalogo` VALUES ('184', '1', '10040710', 'Batom Cremoso Whisper    ', '19', '35.00', '12');
INSERT INTO `tb_catalogo` VALUES ('185', '1', '10060356', 'Batom Cremoso Really Red', '19', '37.00', '12');
INSERT INTO `tb_catalogo` VALUES ('186', '1', '10051647', 'Batom Líquido Guava Punch    ', '19', '35.00', '12');
INSERT INTO `tb_catalogo` VALUES ('187', '1', '10051645', 'Batom Líquido Grapefruit    ', '19', '35.00', '12');
INSERT INTO `tb_catalogo` VALUES ('188', '1', '10030422', 'Batom Líquido Malted    ', '19', '35.00', '12');
INSERT INTO `tb_catalogo` VALUES ('189', '1', '10030424', 'Batom Líquido Raspberry Ice    ', '19', '35.00', '12');
INSERT INTO `tb_catalogo` VALUES ('190', '1', '10030425', 'Batom Líquido Sherbet    ', '19', '35.00', '12');
INSERT INTO `tb_catalogo` VALUES ('191', '1', '10052397', 'Brilho para os Lábios NouriShine Plus Au Naturel    ', '19', '33.00', '12');
INSERT INTO `tb_catalogo` VALUES ('192', '1', '10052405', 'Brilho para os Lábios NouriShine Plus Beach Bronze    ', '19', '33.00', '12');
INSERT INTO `tb_catalogo` VALUES ('193', '1', '10052394', 'Brilho para os Lábios NouriShine Plus Berry Tart    ', '19', '33.00', '12');
INSERT INTO `tb_catalogo` VALUES ('194', '1', '10052393', 'Brilho para os Lábios NouriShine Plus Icicle    ', '19', '33.00', '12');
INSERT INTO `tb_catalogo` VALUES ('195', '1', '10052398', 'Brilho para os Lábios NouriShine Plus Mango Tango    ', '19', '33.00', '12');
INSERT INTO `tb_catalogo` VALUES ('196', '1', '10057773', 'Brilho para os Lábios NouriShine Plus Pink Diamonds    ', '19', '33.00', '12');
INSERT INTO `tb_catalogo` VALUES ('197', '1', '10052401', 'Brilho para os Lábios NouriShine Plus Pink Luster    ', '19', '33.00', '12');
INSERT INTO `tb_catalogo` VALUES ('198', '1', '10053128', 'Brilho para os Lábios NouriShine Plus Rock`n Red    ', '19', '33.00', '12');
INSERT INTO `tb_catalogo` VALUES ('199', '1', '10053129', 'Brilho para os Lábios NouriShine Plus Shock Tart    ', '19', '33.00', '12');
INSERT INTO `tb_catalogo` VALUES ('200', '1', '10053132', 'Brilho para os Lábios NouriShine Plus Sun Blossoms    ', '19', '33.00', '12');
INSERT INTO `tb_catalogo` VALUES ('201', '1', '10040752', 'Mini Estojo Compacto Mary Kay® (vazio)    ', '33', '52.00', '12');
INSERT INTO `tb_catalogo` VALUES ('202', '1', '10017362', 'Estojo Compacto Mary Kay® (vazio)    ', '41', '61.00', '12');
INSERT INTO `tb_catalogo` VALUES ('203', '1', '10018587', 'Estojo Compacto Pro Mary Kay® (vazio)    ', '71', '110.00', '12');
INSERT INTO `tb_catalogo` VALUES ('204', '1', '10054040', 'Pincel para Base Líquida Mary Kay®    ', '19', '36.00', '12');
INSERT INTO `tb_catalogo` VALUES ('205', '1', '10054039', 'Pincel para Base em Pó Mineral Mary Kay®    ', '15', '29.00', '12');
INSERT INTO `tb_catalogo` VALUES ('206', '1', '10053678', 'Pincel para Pó Facial Mary Kay®    ', '19', '36.00', '12');
INSERT INTO `tb_catalogo` VALUES ('207', '1', '10053679', 'Pincel para Blush Mary Kay®    ', '17', '32.00', '12');
INSERT INTO `tb_catalogo` VALUES ('208', '1', '10053680', 'Pincel para o Côncavo dos Olhos Mary Kay®    ', '9', '18.00', '12');
INSERT INTO `tb_catalogo` VALUES ('209', '1', '10053682', 'Pincel Duo Sobrancelha e Delineador Mary Kay®    ', '9', '18.00', '12');
INSERT INTO `tb_catalogo` VALUES ('210', '1', '10053675', 'Pincel para Esfumar Mary Kay®    ', '9', '18.00', '12');
INSERT INTO `tb_catalogo` VALUES ('211', '1', '10054041', 'Pincel para Corretivo e Sombra em Creme Mary Kay®    ', '9', '18.00', '12');
INSERT INTO `tb_catalogo` VALUES ('212', '1', '10015263', 'Pincel Compacto para Blush    ', '7', '9.00', '12');
INSERT INTO `tb_catalogo` VALUES ('213', '1', '10015264', 'Aplicadores para Sombra (2 unidades)    ', '7', '9.00', '12');
INSERT INTO `tb_catalogo` VALUES ('214', '1', '10015261', 'Esponjas Cosméticas (2 unidades)    ', '4', '11.00', '12');
INSERT INTO `tb_catalogo` VALUES ('215', '1', '10015262', 'Pincel para Pó Compacto    ', '5', '13.00', '12');
INSERT INTO `tb_catalogo` VALUES ('216', '1', '10015265', 'Pincel para Lábios (retrátil)    ', '3', '10.00', '12');
INSERT INTO `tb_catalogo` VALUES ('217', '1', '10014209', 'Apontador Duplo    ', '7', '15.00', '12');
INSERT INTO `tb_catalogo` VALUES ('218', '1', '2236951', 'Pink Sale - Brilho Labial Duo Mix - Warm Coral    ', '16', '28.00', '13');
INSERT INTO `tb_catalogo` VALUES ('219', '1', '2237051', 'Pink Sale - Brilho Labial Duo Mix - Redwood    ', '16', '28.00', '13');
INSERT INTO `tb_catalogo` VALUES ('220', '1', '2237251', 'Pink Sale - Blush Cremoso Natural - Pink Pearl    ', '16', '28.00', '13');
INSERT INTO `tb_catalogo` VALUES ('221', '1', '2237451', 'Pink Sale - Esmalte para Unhas - Crushed Pearl    ', '9', '16.00', '13');
INSERT INTO `tb_catalogo` VALUES ('222', '1', '2237551', 'Pink Sale - Esmalte para Unhas - Lava    ', '9', '16.00', '13');
INSERT INTO `tb_catalogo` VALUES ('223', '1', '10021628', 'Kit Introdutório Sistema de Cuidados da Pele com Tendência à Acne    ', '65', '95.00', '14');
INSERT INTO `tb_catalogo` VALUES ('224', '1', '10029381', 'Sistema de Cuidados da Pele com Tendência à Acne    ', '111', '163.00', '14');
INSERT INTO `tb_catalogo` VALUES ('225', '1', '10020374', 'Gel de Limpeza para Pele com Tendência à Acne    ', '34', '50.00', '14');
INSERT INTO `tb_catalogo` VALUES ('226', '1', '10020377', 'Loção para Pele com Tendência à Acne    ', '46', '68.00', '14');
INSERT INTO `tb_catalogo` VALUES ('227', '1', '10020379', 'Creme Secativo para Pele com Tendência à Acne    ', '31', '45.00', '14');
INSERT INTO `tb_catalogo` VALUES ('228', '1', '10023376', 'Spray Corporal para Pele com Tendência à Acne    ', '28', '55.00', '14');
INSERT INTO `tb_catalogo` VALUES ('229', '1', '10001600', 'Gel de Limpeza para o Rosto,147ml    ', '24', '43.00', '15');
INSERT INTO `tb_catalogo` VALUES ('230', '1', '10060182', 'Pó Solto Translúcido    ', '21', '49.00', '16');
INSERT INTO `tb_catalogo` VALUES ('231', '1', '10062144', 'Trio de Sombras Efeito Brilho - Earth Bound    ', '15', '29.00', '16');
INSERT INTO `tb_catalogo` VALUES ('232', '1', '10062145', 'Trio de Sombras Efeito Brilho - Ocean View    ', '15', '29.00', '16');
INSERT INTO `tb_catalogo` VALUES ('233', '1', '10062143', 'Trio de Sombras Efeito Brilho - On the Horizon    ', '15', '29.00', '16');
INSERT INTO `tb_catalogo` VALUES ('234', '1', '10062146', 'Trio de Sombras Efeito Brilho - Tuxedo    ', '15', '29.00', '16');
INSERT INTO `tb_catalogo` VALUES ('235', '1', '10067805', 'Lápis Sombra - Gold Mine    ', '11', '20.00', '16');
INSERT INTO `tb_catalogo` VALUES ('236', '1', '10067803', 'Lápis Sombra - Green Tea    ', '11', '20.00', '16');
INSERT INTO `tb_catalogo` VALUES ('237', '1', '10067801', 'Lápis Sombra - In the Navy    ', '11', '20.00', '16');
INSERT INTO `tb_catalogo` VALUES ('238', '1', '10067804', 'Lápis Sombra - Purple Smoke    ', '11', '20.00', '16');
INSERT INTO `tb_catalogo` VALUES ('239', '1', '10062089', 'Gloss Labial Jelly - Berry Me    ', '9', '18.00', '16');
INSERT INTO `tb_catalogo` VALUES ('240', '1', '10062088', 'Gloss Labial Jelly - Crushed Plum    ', '9', '18.00', '16');
INSERT INTO `tb_catalogo` VALUES ('241', '1', '10062087', 'Gloss Labial Jelly - Hot Tamale    ', '9', '18.00', '16');
INSERT INTO `tb_catalogo` VALUES ('242', '1', '10062086', 'Gloss Labial Jelly - Teddy Bare    ', '9', '18.00', '16');
INSERT INTO `tb_catalogo` VALUES ('243', '1', '10067790', 'Lápis Batom - Candied Apple    ', '11', '20.00', '16');
INSERT INTO `tb_catalogo` VALUES ('244', '1', '10067788', 'Lápis Batom - Perfect Pink    ', '11', '20.00', '16');
INSERT INTO `tb_catalogo` VALUES ('245', '1', '10067783', 'Lápis Batom - Toasted    ', '11', '20.00', '16');
INSERT INTO `tb_catalogo` VALUES ('246', '1', '10067789', 'Lápis Batom - Violet Love    ', '11', '20.00', '16');
INSERT INTO `tb_catalogo` VALUES ('247', '1', '10053681', 'Pincel para Sombra Mary Kay®    ', '9', '18.00', '16');
INSERT INTO `tb_catalogo` VALUES ('248', '1', '10054132', 'Coleção de Pincéis Mary Kay®', '63', '129.00', '16');
INSERT INTO `tb_catalogo` VALUES ('249', '1', '10039418', 'Creme Esfoliante Satin Body™    ', '20', '35.00', '3');
INSERT INTO `tb_catalogo` VALUES ('250', '1', '10039409', 'Gel para o Corpo 2 em 1 Satin Body™    ', '20', '35.00', '3');
INSERT INTO `tb_catalogo` VALUES ('251', '1', '10039413', 'Loção Hidratante Satin Body™    ', '20', '35.00', '3');
INSERT INTO `tb_catalogo` VALUES ('252', '1', '10033897', 'Kit Mãos de Seda Pêssego    ', '67', '132.00', '3');
INSERT INTO `tb_catalogo` VALUES ('253', '1', '10031832', 'Creme para as Mãos Satin Hands Livre de Fragrância    ', '19', '32.00', '3');
INSERT INTO `tb_catalogo` VALUES ('254', '1', '10042836', 'Creme para as Mãos Satin Hands Pêssego    ', '19', '32.00', '3');
INSERT INTO `tb_catalogo` VALUES ('255', '1', '10032362', 'Kit Mãos de Seda Livre de Fragrância    ', '67', '132.00', '3');
INSERT INTO `tb_catalogo` VALUES ('256', '1', '10014096', 'Loção Energizante para os Pés e Pernas Mint Bliss    ', '19', '42.00', '3');
INSERT INTO `tb_catalogo` VALUES ('257', '1', '10029739', 'Gel Suavizante para a Área dos Olhos    ', '29', '53.00', '4');
INSERT INTO `tb_catalogo` VALUES ('258', '1', '10998000', 'Kit Lábios de Seda (Bálsamo + Máscara)    ', '50', '69.00', '4');
INSERT INTO `tb_catalogo` VALUES ('259', '1', '10042792', 'Bálsamo para os Lábios Satin Lips®    ', '25', '39.00', '4');
INSERT INTO `tb_catalogo` VALUES ('260', '1', '10042796', 'Máscara Para os Lábios Satin Lips®    ', '25', '39.00', '4');
INSERT INTO `tb_catalogo` VALUES ('261', '1', '10031573', 'Loção Antibrilho e Oleosidade    ', '29', '45.00', '4');
INSERT INTO `tb_catalogo` VALUES ('262', '1', '10041758', 'Lenços de Papel Antibrilho da Pele, 75 Lenços    ', '17', '28.00', '4');
INSERT INTO `tb_catalogo` VALUES ('263', '1', '10045655', 'Hidratante Noturno Extra Emoliente    ', '30', '47.00', '4');
INSERT INTO `tb_catalogo` VALUES ('264', '1', '10031551', 'Gel Hidratante sem Óleo (Pele Normal/ Oleosa)    ', '51', '77.00', '4');
INSERT INTO `tb_catalogo` VALUES ('265', '1', '10060794', 'Spray Fixador de Maquiagem Mary Kay®    ', '31', '59.00', '17');
INSERT INTO `tb_catalogo` VALUES ('266', '1', '10025868', 'Sombra em Creme Mary Kay® Beach Blonde    ', '20', '35.00', '17');
INSERT INTO `tb_catalogo` VALUES ('267', '1', '10025875', 'Sombra em Creme Mary Kay® Pale Blush    ', '20', '35.00', '17');
INSERT INTO `tb_catalogo` VALUES ('268', '1', '10025874', 'Sombra em Creme Mary Kay® Apricot Twist    ', '20', '35.00', '17');
INSERT INTO `tb_catalogo` VALUES ('269', '1', '10025876', 'Sombra em Creme Mary Kay® Glacier Gray    ', '20', '35.00', '17');
INSERT INTO `tb_catalogo` VALUES ('270', '1', '10054125', 'Mini Estojo Compacto Edição Especial de Aniversário    ', '33', '52.00', '17');
INSERT INTO `tb_catalogo` VALUES ('271', '1', '10056218', 'Batom Líquido Ultra Brilho Hollywood Mystique - Radiant Red    ', '21', '38.00', '17');
INSERT INTO `tb_catalogo` VALUES ('272', '1', '10056219', 'Batom Líquido Ultra Brilho Hollywood Mystique - Bold Fuchsia   ', '21', '38.00', '17');
INSERT INTO `tb_catalogo` VALUES ('273', '1', '10056220', 'Batom Líquido Ultra Brilho Hollywood Mystique - Poised Pink    ', '21', '38.00', '17');
INSERT INTO `tb_catalogo` VALUES ('274', '1', '10056222', 'Delineador em Gel Hollywood Mystique - Tempting Teal    ', '26', '47.00', '17');
INSERT INTO `tb_catalogo` VALUES ('275', '1', '10055778', 'Dance To Life    ', '75', '149.00', '17');
INSERT INTO `tb_catalogo` VALUES ('276', '1', '10192900', 'Belara®    ', '41', '88.00', '5');
INSERT INTO `tb_catalogo` VALUES ('277', '1', '10012512', 'Bella Belara®    ', '44', '88.00', '5');
INSERT INTO `tb_catalogo` VALUES ('278', '1', '10397300', 'Elige®    ', '59', '110.00', '5');
INSERT INTO `tb_catalogo` VALUES ('279', '1', '10166700', 'Journey®    ', '48', '99.00', '5');
INSERT INTO `tb_catalogo` VALUES ('280', '1', '10051949', 'Pink Diamonds    ', '59', '99.00', '5');
INSERT INTO `tb_catalogo` VALUES ('281', '1', '10037726', 'Thinking of You    ', '52', '89.00', '5');
INSERT INTO `tb_catalogo` VALUES ('282', '1', '10168700', 'Velocity    ', '52', '95.00', '5');
INSERT INTO `tb_catalogo` VALUES ('283', '1', '10033691', 'Forever Orchid    ', '32', '63.00', '5');
INSERT INTO `tb_catalogo` VALUES ('284', '1', '10020029', 'Simply Cotton    ', '32', '63.00', '5');
INSERT INTO `tb_catalogo` VALUES ('285', '1', '10020031', 'Exotic Passionfruit    ', '32', '63.00', '5');
INSERT INTO `tb_catalogo` VALUES ('286', '1', '10020028', 'Warm Amber    ', '32', '63.00', '5');
INSERT INTO `tb_catalogo` VALUES ('287', '1', '10048515', 'True Original Cologne Spray    ', '60', '115.00', '6');
INSERT INTO `tb_catalogo` VALUES ('288', '1', '10012494', 'MK High Intensity Cologne Spray    ', '60', '115.00', '6');
INSERT INTO `tb_catalogo` VALUES ('289', '1', '10028783', 'Domain® Cologne    ', '45', '85.00', '6');
INSERT INTO `tb_catalogo` VALUES ('290', '1', '10501700', 'Velocity® for Him    ', '52', '95.00', '6');
INSERT INTO `tb_catalogo` VALUES ('291', '1', '10049513', 'Creme de Limpeza Botanical Effects Pele Normal    ', '17', '29.00', '7');
INSERT INTO `tb_catalogo` VALUES ('292', '1', '10049516', 'Gel de Limpeza Botanical Effects Pele Oleosa    ', '17', '29.00', '7');
INSERT INTO `tb_catalogo` VALUES ('293', '1', '10050141', 'Máscara Facial Botanical Effects Pele Normal    ', '28', '49.00', '7');
INSERT INTO `tb_catalogo` VALUES ('294', '1', '10050144', 'Máscara Facial Botanical Effects Pele Oleosa    ', '28', '49.00', '7');
INSERT INTO `tb_catalogo` VALUES ('295', '1', '10049807', 'Tônico Refrescante Botanical Effects Pele Normal    ', '17', '29.00', '7');
INSERT INTO `tb_catalogo` VALUES ('296', '1', '10049810', 'Tônico Refrescante Botanical Effects Pele Oleosa    ', '17', '29.00', '7');
INSERT INTO `tb_catalogo` VALUES ('297', '1', '10049591', 'Hidratante Botanical Effects Pele Normal    ', '17', '29.00', '7');
INSERT INTO `tb_catalogo` VALUES ('298', '1', '10049594', 'Hidratante Botanical Effects Pele Oleosa    ', '17', '29.00', '7');
INSERT INTO `tb_catalogo` VALUES ('299', '1', '10060198', 'Creme Intensivo para a Área dos Olhos MK Men®    ', '25', '45.00', '9');
INSERT INTO `tb_catalogo` VALUES ('300', '1', '10020104', 'Fragrancia Corporal em Spray MKMen®    ', '18', '41.00', '9');
INSERT INTO `tb_catalogo` VALUES ('301', '1', '10055844', 'Sabonete Facial MKMen®    ', '21', '38.00', '9');
INSERT INTO `tb_catalogo` VALUES ('302', '1', '10010336', 'Espuma de Barbear MKMen®    ', '19', '37.00', '9');
INSERT INTO `tb_catalogo` VALUES ('303', '1', '10010338', 'Gel Refrescante Pós Barba MKMen®    ', '18', '33.00', '9');
INSERT INTO `tb_catalogo` VALUES ('304', '1', '10010332', 'Loção Hidratante com Protetor Solar FPS 25 MKMen®    ', '30', '55.00', '9');
INSERT INTO `tb_catalogo` VALUES ('305', '1', '10049423', 'Loção Corporal Autobronzeadora    ', '24', '44.00', '10');
INSERT INTO `tb_catalogo` VALUES ('306', '1', '10042829', 'Protetor Solar com FPS 30', '28', '55.00', '10');
INSERT INTO `tb_catalogo` VALUES ('307', '1', '10050165', 'Gel Restaurador Pós-Sol    ', '20', '39.00', '10');
INSERT INTO `tb_catalogo` VALUES ('308', '1', '10049430', 'Protetor Solar para os Lábios com FPS 15    ', '13', '23.00', '10');
INSERT INTO `tb_catalogo` VALUES ('309', '1', '10042751', 'Creme de Limpeza 3 em 1 TimeWise® (Normal/Seca)    ', '37', '58.00', '2');
INSERT INTO `tb_catalogo` VALUES ('310', '1', '10042761', 'Creme de Limpeza 3 em 1 TimeWise® (Mista/Oleosa)    ', '37', '58.00', '2');
INSERT INTO `tb_catalogo` VALUES ('311', '1', '10027914', 'Sabonete 3 em 1 de Limpeza Facial TimeWise®    ', '37', '56.00', '2');
INSERT INTO `tb_catalogo` VALUES ('312', '1', '10042747', 'Hidratante Redutor de Linhas de Expressão TimeWise® (Normal/Seca)    ', '39', '62.00', '2');
INSERT INTO `tb_catalogo` VALUES ('313', '1', '10042743', 'Hidratante Redutor de Linhas de Expressão TimeWise® (Mista/Oleosa)    ', '39', '62.00', '2');
INSERT INTO `tb_catalogo` VALUES ('314', '1', '10042814', 'Hidratante Redutor de Linhas de Expressão com FPS 15 TimeWise®    ', '39', '65.00', '2');
INSERT INTO `tb_catalogo` VALUES ('315', '1', '10563500', 'Kit Solução Diurna e Noturna TimeWise®    ', '94', '153.00', '2');
INSERT INTO `tb_catalogo` VALUES ('316', '1', '10042754', 'Solução Diurna com FPS 25 TimeWise®    ', '47', '85.00', '2');
INSERT INTO `tb_catalogo` VALUES ('317', '1', '10042760', 'Solução Noturna TimeWise®    ', '47', '85.00', '2');
INSERT INTO `tb_catalogo` VALUES ('318', '1', '10026901', 'Sistema Anti-idade de Cuidados com a Pele TimeWise® (Normal/Seca)    ', '170', '273.00', '2');
INSERT INTO `tb_catalogo` VALUES ('319', '1', '10026902', 'Sistema Anti-idade de Cuidados com a Pele TimeWise® (Mista/Oleosa)    ', '170', '273.00', '2');
INSERT INTO `tb_catalogo` VALUES ('320', '1', '10054641', 'Sérum Facial Renovador TimeWise®    ', '64', '110.00', '2');
INSERT INTO `tb_catalogo` VALUES ('321', '1', '10046752', 'Complexo Facial Noturno TimeWise® (Normal/Seca)    ', '47', '85.00', '2');
INSERT INTO `tb_catalogo` VALUES ('322', '1', '10046753', 'Complexo Facial Noturno TimeWise® (Mista/Oleosa)    ', '47', '85.00', '2');
INSERT INTO `tb_catalogo` VALUES ('323', '1', '10004300', 'Kit Microdermoabrasão TimeWise® (Passo 1: Refinar e Passo 2: Restaurar)    ', '98', '162.00', '2');
INSERT INTO `tb_catalogo` VALUES ('324', '1', '10029733', 'Microdermoabrasão TimeWise® Passo 1: Refinar    ', '51', '92.00', '2');
INSERT INTO `tb_catalogo` VALUES ('325', '1', '10061256', 'Microdermoabrasão TimeWise® Passo 1: Refinar    ', '51', '92.00', '2');
INSERT INTO `tb_catalogo` VALUES ('326', '1', '10061257', 'Microdermoabrasão TimeWise® Passo 2: Restaurar    ', '47', '88.00', '2');
INSERT INTO `tb_catalogo` VALUES ('327', '1', '10029735', 'Microdermoabrasão TimeWise® Passo 2: Restaurar    ', '47', '88.00', '2');
INSERT INTO `tb_catalogo` VALUES ('328', '1', '10039964', 'Máscara Hidratante Renovadora em Gel TimeWise®    ', '35', '59.00', '2');
INSERT INTO `tb_catalogo` VALUES ('329', '1', '10029730', 'Loção Even Complexion TimeWise®    ', '55', '96.00', '2');
INSERT INTO `tb_catalogo` VALUES ('330', '1', '10031174', 'Máscara Even Complexion TimeWise®    ', '37', '67.00', '2');
INSERT INTO `tb_catalogo` VALUES ('331', '1', '10031340', 'Creme Redutor de Linhas de Expressão Targeted-Action® TimeWise®    ', '52', '79.00', '2');
INSERT INTO `tb_catalogo` VALUES ('332', '1', '10035083', 'Loção Corporal Firmadora Targeted-Action® TimeWise®    ', '65', '97.00', '2');
INSERT INTO `tb_catalogo` VALUES ('333', '1', '10032946', 'Creme para Mãos e Colo com FPS 15 TimeWise®    ', '35', '65.00', '2');
INSERT INTO `tb_catalogo` VALUES ('334', '1', '10029743', 'Complexo Anti-idade para os Lábios TimeWise®    ', '39', '66.00', '2');
INSERT INTO `tb_catalogo` VALUES ('335', '1', '10042799', 'Creme Firmador para Área dos Olhos TimeWise®    ', '52', '72.00', '2');
INSERT INTO `tb_catalogo` VALUES ('336', '1', '10029728', 'Creme Anti-idade para Contorno dos Olhos TimeWise®    ', '52', '70.00', '2');
INSERT INTO `tb_catalogo` VALUES ('337', '1', '10029737', 'Revitalizante para Área dos Olhos Targeted-Action® TimeWise®    ', '52', '79.00', '2');
INSERT INTO `tb_catalogo` VALUES ('338', '1', '10077348', 'Pó Mineral Compacto Beige 2', '21', '39.00', '15');
INSERT INTO `tb_catalogo` VALUES ('339', '1', '10078024', 'Lápis Retrátil para os Olhos Black', '18', '34.00', '15');

-- ----------------------------
-- Table structure for tb_categoria
-- ----------------------------
DROP TABLE IF EXISTS `tb_categoria`;
CREATE TABLE `tb_categoria` (
  `ID_CATEGORIA` int(11) NOT NULL AUTO_INCREMENT,
  `NM_CATEGORIA` varchar(255) DEFAULT NULL,
  `SECAO_CATEGORIA_FK` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_CATEGORIA`),
  KEY `FK93EE448240045250` (`SECAO_CATEGORIA_FK`),
  CONSTRAINT `FK93EE448240045250` FOREIGN KEY (`SECAO_CATEGORIA_FK`) REFERENCES `tb_secao` (`ID_SECAO`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tb_categoria
-- ----------------------------
INSERT INTO `tb_categoria` VALUES ('1', 'Bases', '1');
INSERT INTO `tb_categoria` VALUES ('2', 'Cuidados com a Pele TimeWise®', '1');
INSERT INTO `tb_categoria` VALUES ('3', 'Cuidados com o Corpo', '1');
INSERT INTO `tb_categoria` VALUES ('4', 'Cuidados Personalizados', '1');
INSERT INTO `tb_categoria` VALUES ('5', 'Fragrâncias Femininas', '1');
INSERT INTO `tb_categoria` VALUES ('6', 'Fragrâncias Masculinas', '1');
INSERT INTO `tb_categoria` VALUES ('7', 'Linha Botanical Effects', '1');
INSERT INTO `tb_categoria` VALUES ('8', 'Linha Clássica de Cuidados com a Pele', '1');
INSERT INTO `tb_categoria` VALUES ('9', 'Linha Masculina MK Men', '1');
INSERT INTO `tb_categoria` VALUES ('10', 'Linha Solar', '1');
INSERT INTO `tb_categoria` VALUES ('11', 'Linha TimeWise Repair™', '1');
INSERT INTO `tb_categoria` VALUES ('12', 'Maquiagem', '1');
INSERT INTO `tb_categoria` VALUES ('13', 'Pink Sale', '1');
INSERT INTO `tb_categoria` VALUES ('14', 'Sistema de Cuidados da Pele com Tendência à Acne', '1');
INSERT INTO `tb_categoria` VALUES ('15', 'Velocity®', '1');
INSERT INTO `tb_categoria` VALUES ('16', 'Lançamentos Linha Regular', '1');
INSERT INTO `tb_categoria` VALUES ('17', 'Edição Limitada / Especial', '1');
INSERT INTO `tb_categoria` VALUES ('18', 'Acessórios para sessões', '2');
INSERT INTO `tb_categoria` VALUES ('19', 'Edição Limitada / Especial', '2');
INSERT INTO `tb_categoria` VALUES ('20', 'Materiais Impressos', '2');
INSERT INTO `tb_categoria` VALUES ('21', 'Amostras', '2');
INSERT INTO `tb_categoria` VALUES ('22', 'Diversos', '2');
INSERT INTO `tb_categoria` VALUES ('23', 'Lançamentos', '2');
INSERT INTO `tb_categoria` VALUES ('24', 'Promoções', '2');

-- ----------------------------
-- Table structure for tb_cliente
-- ----------------------------
DROP TABLE IF EXISTS `tb_cliente`;
CREATE TABLE `tb_cliente` (
  `ID_CLIENTE` bigint(20) NOT NULL AUTO_INCREMENT,
  `DT_NASCIMENTO` datetime DEFAULT NULL,
  `DS_EMAIL` varchar(255) DEFAULT NULL,
  `DS_BAIRRO` varchar(255) DEFAULT NULL,
  `DS_CEP` varchar(255) DEFAULT NULL,
  `DS_CIDADE` varchar(255) DEFAULT NULL,
  `DS_COMPLEMENTO` varchar(255) DEFAULT NULL,
  `DS_ESTADO` varchar(255) DEFAULT NULL,
  `DS_LOGRADOURO` varchar(255) DEFAULT NULL,
  `DS_NUMERO` varchar(255) DEFAULT NULL,
  `DS_TP_LOGRADOURO` varchar(255) DEFAULT NULL,
  `DS_NOME` varchar(255) DEFAULT NULL,
  `NU_CELULAR` bigint(20) DEFAULT NULL,
  `NU_TELEFONE` bigint(20) DEFAULT NULL,
  `DS_SEXO` varchar(255) DEFAULT NULL,
  `DT_INIC_ACOM` datetime DEFAULT NULL,
  `DT_PRIM_VENDA` datetime DEFAULT NULL,
  `DT_ULTI_VENDA` datetime DEFAULT NULL,
  `DS_INFO_ADIC` varchar(255) DEFAULT NULL,
  `TB_USUARIO_FK` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID_CLIENTE`),
  KEY `FK4C647509B9C1A942` (`TB_USUARIO_FK`),
  CONSTRAINT `FK4C647509B9C1A942` FOREIGN KEY (`TB_USUARIO_FK`) REFERENCES `tb_user` (`ID_USER`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tb_cliente
-- ----------------------------
INSERT INTO `tb_cliente` VALUES ('1', '2013-10-29 00:00:00', 'gomessilvae@gmail.com', 'Jd. Belval', '06422-100', 'Barueri', 'Apto 42 - Bloco C', 'SP', 'Estrada da nações', '395', null, 'Elaine Gomes da Silva ', '1143820180', '1172758962', 'Feminino', null, null, null, 'Pele mista\r\nBase beige 6 / bronze 1', '2');
INSERT INTO `tb_cliente` VALUES ('2', '2013-11-08 00:00:00', 'ritanegro@gmail.com', 'Vila São Francisco', '05351-035', 'São Paulo', 'Apto 53 - Perissá', 'SP', 'RUA DR HELIO FIDELIS', '26', null, 'Rita de Cassia Negro da Silva', '11996172320', '1137842780', 'Feminino', null, null, null, 'Pele mista\r\nBase bronze 2', '2');
INSERT INTO `tb_cliente` VALUES ('3', '2013-07-05 00:00:00', 'amanda.fercoelho@hotmail.com', 'Jaguaribe', '06050-300', 'Osasco', 'Apto 91B', 'SP', 'Av. José Julio', '541', null, 'Amanda C. F. Pissarra', '11994422869', '1136542734', 'Feminino', null, null, null, 'Pele norma\r\nBase Ivory 5', '2');
INSERT INTO `tb_cliente` VALUES ('4', '2013-05-04 00:00:00', 'caroline.silva73@yahoo.com', 'Km 18', '06192-040', 'Osasco', '', 'SP', 'Rua Guilherme de Jesus', '08', null, 'Caroline Silva', '11972177209', '1136952767', 'Feminino', '2013-08-24 00:00:00', null, null, 'Pele normal\r\nBase beige 2', '2');
INSERT INTO `tb_cliente` VALUES ('5', '2013-04-03 00:00:00', 'evelynalves@hotmail.com', 'Metalúrgicos', '06150-210', 'Osasco', '', 'SP', 'Rua Antonio Coutinho Neto', '09', null, 'Evelyn Carolina Alves', '11987066542', '1128013293', 'Feminino', '2013-08-24 00:00:00', null, null, 'Pele normal\r\nBase beige 2', '2');
INSERT INTO `tb_cliente` VALUES ('6', '2013-06-25 00:00:00', 'jullymacedo@gmail.com', 'Piratininga', '06233-030', 'Osasco', 'Bloco 26 ap 32', 'SP', 'Rua Paula Rodrigues', '175', null, 'Juliana Macedo de Noronha', '11983951581', '1135998426', 'Feminino', '2013-08-24 00:00:00', null, null, 'Pele Oleose\r\nBase beige 3', '2');
INSERT INTO `tb_cliente` VALUES ('7', '2013-06-08 00:00:00', 'tgbonatelli@hotmail.com', 'Sumarezinho', '05440-000', 'São Paulo', 'Ap 123c', 'SP', 'Rua Paulistânia', '46', null, 'Tarsila Grigolin Bonatelli', '11989968118', '1133845878', 'Feminino', null, null, null, 'Base beige 3\r\nPela mista\r\nUsa a linha Botanical Effects - Firmador para os olhos Timewise - Microdermoabrasão Timewise\r\nGosta de perfumes cítricos', '2');
INSERT INTO `tb_cliente` VALUES ('8', '2013-08-02 00:00:00', 'camillacoscia@gmail.com', 'Parque São Domingos', '05126-040', 'São Paulo', 'Apto 45A', 'SP', 'Rua João Veloso de Oliveira0', '165', null, 'Camilla Coscia Neves Rebello', '11954467572', null, 'Feminino', null, null, null, 'Base beige 5\r\nPele normal', '2');
INSERT INTO `tb_cliente` VALUES ('9', '2013-12-27 00:00:00', 'revianna8@hotmail.com', 'Brasilândia', '02844-060', 'São Paulo', ' Bl 29 - apto 71', 'SP', 'Rua Tiro ao Pombo', '402', null, 'Renata Cunha Vianna', '11996379658', '1123731897', 'Feminino', null, null, null, 'Base Beige 2\r\nPele normal', '2');
INSERT INTO `tb_cliente` VALUES ('10', '2013-07-19 00:00:00', 'sonia3923@hotmail.com', 'Jd. do Tiro', '32681-240', 'São Paulo', 'Bloco 8 - Apto 124', 'SP', 'Rua Rui de Moraes Apocalipse', '326', null, 'Sonia Cunha', '11970576979', '1139233957', 'Feminino', null, null, null, 'Base beige 5\r\nPele normal', '2');
INSERT INTO `tb_cliente` VALUES ('11', '2013-03-11 00:00:00', 'ediana.vieira@hotmail.com', 'Presidente Altino', '06260-180', 'Osasco', '', 'SP', 'R Rev. João e pereira', '459', null, 'Ediana Vieira santos', '11997156463', null, 'Feminino', null, null, null, 'Base Beige 8\r\nPele mista', '2');

-- ----------------------------
-- Table structure for tb_configuracao_sistema
-- ----------------------------
DROP TABLE IF EXISTS `tb_configuracao_sistema`;
CREATE TABLE `tb_configuracao_sistema` (
  `ID_CONFIGURACAO_SISTEMA` bigint(20) NOT NULL AUTO_INCREMENT,
  `DS_IMG_FUNDO_TELA_LOGIN` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_CONFIGURACAO_SISTEMA`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tb_configuracao_sistema
-- ----------------------------
INSERT INTO `tb_configuracao_sistema` VALUES ('1', 'ferramentas_suas ferramentas_fundos de tela_ Entusiasmo.jpg');

-- ----------------------------
-- Table structure for tb_consultora
-- ----------------------------
DROP TABLE IF EXISTS `tb_consultora`;
CREATE TABLE `tb_consultora` (
  `ID_CONSULTORA` bigint(20) NOT NULL,
  `DT_NASCIMENTO` datetime DEFAULT NULL,
  `DS_EMAIL` varchar(255) DEFAULT NULL,
  `DS_BAIRRO` varchar(255) DEFAULT NULL,
  `DS_CEP` varchar(255) DEFAULT NULL,
  `DS_CIDADE` varchar(255) DEFAULT NULL,
  `DS_COMPLEMENTO` varchar(255) DEFAULT NULL,
  `DS_ESTADO` varchar(255) DEFAULT NULL,
  `DS_LOGRADOURO` varchar(255) DEFAULT NULL,
  `DS_NUMERO` varchar(255) DEFAULT NULL,
  `DS_TP_LOGRADOURO` varchar(255) DEFAULT NULL,
  `DS_NOME` varchar(255) DEFAULT NULL,
  `NU_CELULAR` bigint(20) DEFAULT NULL,
  `NU_TELEFONE` bigint(20) DEFAULT NULL,
  `DS_SEXO` varchar(255) DEFAULT NULL,
  `DT_DESATIVACAO` datetime DEFAULT NULL,
  `DT_INICIACAO` datetime DEFAULT NULL,
  `DS_INF_ADICIONAL` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `TB_USUARIO_FK` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID_CONSULTORA`),
  KEY `FK92A321E3B9C1A942` (`TB_USUARIO_FK`),
  CONSTRAINT `FK92A321E3B9C1A942` FOREIGN KEY (`TB_USUARIO_FK`) REFERENCES `tb_user` (`ID_USER`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tb_consultora
-- ----------------------------
INSERT INTO `tb_consultora` VALUES ('727188', '2013-07-26 00:00:00', 'babi.lp@gmail.com', 'BATEL', '16011-602', 'CURITIBA', 'Sala 1601/1602', 'PR', 'AVENIDA SETE DE SETEMBRO', '4923', null, 'BARBARA MARIA VASCONCELOS DA SILVA', '4188964096', '4130851837', 'Feminino', null, '2013-07-19 00:00:00', 'Estrela Diamante (3° semestre)', '1', '2');
INSERT INTO `tb_consultora` VALUES ('734735', '2013-12-13 00:00:00', 'fjuntolli@yahoo.com.br', 'VILA SOFIA', '04685-001', 'SÃO PAULO', '', 'SP', 'AVENIDA NOSSA SENHORA SABARA', '527', null, 'FERNANDA RODRIGUES JUNTOLLI', '11000000000', '1126133160', 'Feminino', null, '2013-07-30 00:00:00', 'Consultora Senior', '1', '2');
INSERT INTO `tb_consultora` VALUES ('737202', '2013-07-26 00:00:00', 'danielecamargo83@gmail.com', 'PQ SANTA TEREZA', '06341-210', 'CARAPICUIBA', '', 'SP', 'RUA KALIL FILHO', '355', null, 'DANIELE APARECIDA DE CAMARGO', '11979925743', null, 'Feminino', null, '2013-08-01 00:00:00', '', '1', '2');
INSERT INTO `tb_consultora` VALUES ('799618', '2013-11-20 00:00:00', 'anayoshiwara1@gmail.com', 'PARQUE ESMERALDA', '06447-510', 'BARUERI', '', 'SP', 'RUA QUARTZO', '35', null, 'ANA PAULA PIRES YOSHIWARA', '11974464989', '1142012286', 'Feminino', null, '2013-10-02 00:00:00', 'Consultora Senior', '1', '2');
INSERT INTO `tb_consultora` VALUES ('842398', '2013-10-14 00:00:00', 'caroline.amorim86@terra.com.br', 'TUCURUVI', '02303-131', 'SÃO PAULO', '', 'SP', 'RUA DOUTOR NATALINO RIGHETO', '392', null, 'CAROLINE AMORIM DE SIMAS', '11987347843', '1129965720', 'Feminino', null, '2013-11-18 00:00:00', '', '1', '2');

-- ----------------------------
-- Table structure for tb_estoque
-- ----------------------------
DROP TABLE IF EXISTS `tb_estoque`;
CREATE TABLE `tb_estoque` (
  `ID_ESTOQUE` bigint(20) NOT NULL AUTO_INCREMENT,
  `FL_DISPONIVEL` tinyint(1) DEFAULT NULL,
  `DT_MOVIMENTACAO` datetime DEFAULT NULL,
  `NU_PERC_DESCONTO` varchar(255) DEFAULT NULL,
  `NU_QTDE_PRODUTO_ESTOQUE_ATUAL` int(11) DEFAULT NULL,
  `NU_QTDE_PRODUTO_MOVIMENTADO` int(11) DEFAULT NULL,
  `NU_SOMA_PONTOS_MOVIMENTADOS` int(11) DEFAULT NULL,
  `VL_VLR_TOTAL_MOVIMENTADO` decimal(19,2) DEFAULT NULL,
  `TB_CATALOGO_TB_ESTOQUE_FK` bigint(20) NOT NULL,
  `TB_PEDIDO_TB_ESTOQUE_FK` bigint(20) DEFAULT NULL,
  `TB_VENDA_TB_ESTOQUE_FK` bigint(20) DEFAULT NULL,
  `TB_FLUXO_ESTOQUE_ESTOQUE_FK` bigint(20) DEFAULT NULL,
  `TB_USUARIO_FK` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID_ESTOQUE`),
  KEY `FKC2C271879659F3F` (`TB_FLUXO_ESTOQUE_ESTOQUE_FK`),
  KEY `FKC2C27187A0FD2157` (`TB_PEDIDO_TB_ESTOQUE_FK`),
  KEY `FKC2C27187B9C1A942` (`TB_USUARIO_FK`),
  KEY `FKC2C2718773171621` (`TB_CATALOGO_TB_ESTOQUE_FK`),
  KEY `FKC2C2718728027728` (`TB_VENDA_TB_ESTOQUE_FK`),
  CONSTRAINT `FKC2C2718728027728` FOREIGN KEY (`TB_VENDA_TB_ESTOQUE_FK`) REFERENCES `tb_venda` (`ID_VENDA`),
  CONSTRAINT `FKC2C2718773171621` FOREIGN KEY (`TB_CATALOGO_TB_ESTOQUE_FK`) REFERENCES `tb_catalogo` (`ID_CATALOGO`),
  CONSTRAINT `FKC2C271879659F3F` FOREIGN KEY (`TB_FLUXO_ESTOQUE_ESTOQUE_FK`) REFERENCES `tb_fluxo_estoque` (`ID_FLUXO_ESTOQUE`),
  CONSTRAINT `FKC2C27187A0FD2157` FOREIGN KEY (`TB_PEDIDO_TB_ESTOQUE_FK`) REFERENCES `tb_pedido` (`ID_PEDIDO`),
  CONSTRAINT `FKC2C27187B9C1A942` FOREIGN KEY (`TB_USUARIO_FK`) REFERENCES `tb_user` (`ID_USER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_estoque
-- ----------------------------

-- ----------------------------
-- Table structure for tb_fluxo_estoque
-- ----------------------------
DROP TABLE IF EXISTS `tb_fluxo_estoque`;
CREATE TABLE `tb_fluxo_estoque` (
  `ID_FLUXO_ESTOQUE` bigint(20) NOT NULL AUTO_INCREMENT,
  `DS_FLUXO_ESTOQUE` varchar(255) DEFAULT NULL,
  `FL_FLUXO_SAIDA` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ID_FLUXO_ESTOQUE`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_fluxo_estoque
-- ----------------------------
INSERT INTO `tb_fluxo_estoque` VALUES ('1', 'ENTRADA - DEVOLUÇÃO DE EMPRÉSTIMO', '0');
INSERT INTO `tb_fluxo_estoque` VALUES ('2', 'ENTRADA - PEDIDO', '0');
INSERT INTO `tb_fluxo_estoque` VALUES ('4', 'ENTRADA - MANUAL', '0');
INSERT INTO `tb_fluxo_estoque` VALUES ('5', 'SAIDA - VENDA', '1');
INSERT INTO `tb_fluxo_estoque` VALUES ('6', 'SAIDA - SESSÃO/DEMONSTRAÇÃO', '1');
INSERT INTO `tb_fluxo_estoque` VALUES ('7', 'SAIDA - MANUAL', '1');
INSERT INTO `tb_fluxo_estoque` VALUES ('8', 'SAIDA - BRINDE', '1');
INSERT INTO `tb_fluxo_estoque` VALUES ('9', 'SAIDA - EMPRÉSTIMO', '1');

-- ----------------------------
-- Table structure for tb_forma_pgto
-- ----------------------------
DROP TABLE IF EXISTS `tb_forma_pgto`;
CREATE TABLE `tb_forma_pgto` (
  `ID_FORMA_PGTO` bigint(20) NOT NULL AUTO_INCREMENT,
  `DS_FORMA_PGTO` varchar(255) DEFAULT NULL,
  `NU_PARCELAS` int(11) DEFAULT NULL,
  `FL_POSSUI_PARCELAS` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ID_FORMA_PGTO`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_forma_pgto
-- ----------------------------
INSERT INTO `tb_forma_pgto` VALUES ('1', 'DINHEIRO', '1', '0');
INSERT INTO `tb_forma_pgto` VALUES ('2', 'CARTÃO DE DÉBITO', '1', '0');
INSERT INTO `tb_forma_pgto` VALUES ('3', 'BOLETO', '1', '0');
INSERT INTO `tb_forma_pgto` VALUES ('4', 'CARTÃO DE CRÉDITO', '4', '1');

-- ----------------------------
-- Table structure for tb_parametro_dashboard
-- ----------------------------
DROP TABLE IF EXISTS `tb_parametro_dashboard`;
CREATE TABLE `tb_parametro_dashboard` (
  `ID_PARAMETRO` bigint(20) NOT NULL AUTO_INCREMENT,
  `VL_LUCRO_DESEJADO` decimal(19,2) DEFAULT NULL,
  `VL_META_FATURAMENTO` decimal(19,2) DEFAULT NULL,
  `TB_USUARIO_FK` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID_PARAMETRO`),
  KEY `FK463D037DB9C1A942` (`TB_USUARIO_FK`),
  CONSTRAINT `FK463D037DB9C1A942` FOREIGN KEY (`TB_USUARIO_FK`) REFERENCES `tb_user` (`ID_USER`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_parametro_dashboard
-- ----------------------------
INSERT INTO `tb_parametro_dashboard` VALUES ('1', '1000.00', '1000.00', '1');
INSERT INTO `tb_parametro_dashboard` VALUES ('2', '1000.00', '1000.00', '2');

-- ----------------------------
-- Table structure for tb_parcela
-- ----------------------------
DROP TABLE IF EXISTS `tb_parcela`;
CREATE TABLE `tb_parcela` (
  `ID_PARCELA` bigint(20) NOT NULL AUTO_INCREMENT,
  `DS_PARCELA` varchar(255) DEFAULT NULL,
  `DT_PAGAMENTO` datetime DEFAULT NULL,
  `DT_VENCIMENTO` datetime DEFAULT NULL,
  `NU_PARCELA` int(11) DEFAULT NULL,
  `VL_VLR_PAGO` decimal(19,2) DEFAULT NULL,
  `VL_VLR_PARCELA` decimal(19,2) DEFAULT NULL,
  `TB_PEDIDO_PARCELA_FK` bigint(20) DEFAULT NULL,
  `TB_SITUACAO_PAGTO_PARCELA_FK` bigint(20) DEFAULT NULL,
  `TB_VENDA_PARCELA_FK` bigint(20) DEFAULT NULL,
  `TB_USUARIO_FK` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID_PARCELA`),
  KEY `FKE9CDF08730BE4718` (`TB_SITUACAO_PAGTO_PARCELA_FK`),
  KEY `FKE9CDF087B9C1A942` (`TB_USUARIO_FK`),
  KEY `FKE9CDF08770564BD8` (`TB_PEDIDO_PARCELA_FK`),
  KEY `FKE9CDF087B6B191F` (`TB_VENDA_PARCELA_FK`),
  CONSTRAINT `FKE9CDF087B6B191F` FOREIGN KEY (`TB_VENDA_PARCELA_FK`) REFERENCES `tb_venda` (`ID_VENDA`),
  CONSTRAINT `FKE9CDF08730BE4718` FOREIGN KEY (`TB_SITUACAO_PAGTO_PARCELA_FK`) REFERENCES `tb_situacao_pagto` (`ID_SITUACAO_PAGTO`),
  CONSTRAINT `FKE9CDF08770564BD8` FOREIGN KEY (`TB_PEDIDO_PARCELA_FK`) REFERENCES `tb_pedido` (`ID_PEDIDO`),
  CONSTRAINT `FKE9CDF087B9C1A942` FOREIGN KEY (`TB_USUARIO_FK`) REFERENCES `tb_user` (`ID_USER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_parcela
-- ----------------------------

-- ----------------------------
-- Table structure for tb_pedido
-- ----------------------------
DROP TABLE IF EXISTS `tb_pedido`;
CREATE TABLE `tb_pedido` (
  `ID_PEDIDO` bigint(20) NOT NULL,
  `DT_PEDIDO` datetime DEFAULT NULL,
  `VL_LUCRO_TOTAL` decimal(19,2) DEFAULT NULL,
  `PC_PERC_DESCONTO` varchar(255) DEFAULT NULL,
  `NU_TOTAL_PONTOS_PEDIDO` int(11) DEFAULT NULL,
  `VL_BONUS_UTILIZADO` decimal(19,2) DEFAULT NULL,
  `VL_FRETE` decimal(19,2) DEFAULT NULL,
  `VL_VLR_TOTAL_REVENDA` decimal(19,2) DEFAULT NULL,
  `VL_VLR_TOTAL_PAGO` decimal(19,2) DEFAULT NULL,
  `VL_VLR_TOTAL_COM_DESCONTO` decimal(19,2) DEFAULT NULL,
  `TB_FORMA_PAGTO_PEDIDO_FK` bigint(20) DEFAULT NULL,
  `TB_USUARIO_FK` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID_PEDIDO`),
  UNIQUE KEY `ID_PEDIDO` (`ID_PEDIDO`),
  KEY `FK9C620636B9C1A942` (`TB_USUARIO_FK`),
  KEY `FK9C620636959CA7F7` (`TB_FORMA_PAGTO_PEDIDO_FK`),
  CONSTRAINT `FK9C620636959CA7F7` FOREIGN KEY (`TB_FORMA_PAGTO_PEDIDO_FK`) REFERENCES `tb_forma_pgto` (`ID_FORMA_PGTO`),
  CONSTRAINT `FK9C620636B9C1A942` FOREIGN KEY (`TB_USUARIO_FK`) REFERENCES `tb_user` (`ID_USER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_pedido
-- ----------------------------

-- ----------------------------
-- Table structure for tb_pedido_join_tb_catalogo
-- ----------------------------
DROP TABLE IF EXISTS `tb_pedido_join_tb_catalogo`;
CREATE TABLE `tb_pedido_join_tb_catalogo` (
  `ID_JOIN` bigint(20) NOT NULL AUTO_INCREMENT,
  `DT_COMPRA` datetime DEFAULT NULL,
  `NU_QTDE_COMPRADA` int(11) DEFAULT NULL,
  `NU_SOMA_PONTOS_COMPRADOS` int(11) DEFAULT NULL,
  `VL_VLR_TOTAL_COMPRADO` decimal(19,2) DEFAULT NULL,
  `ID_CATALOGO_FK` bigint(20) DEFAULT NULL,
  `ID_PEDIDO_FK` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID_JOIN`),
  KEY `FK2FC2297BC23AFF1E` (`ID_PEDIDO_FK`),
  KEY `FK2FC2297BD01FCCE6` (`ID_CATALOGO_FK`),
  CONSTRAINT `FK2FC2297BD01FCCE6` FOREIGN KEY (`ID_CATALOGO_FK`) REFERENCES `tb_catalogo` (`ID_CATALOGO`),
  CONSTRAINT `FK2FC2297BC23AFF1E` FOREIGN KEY (`ID_PEDIDO_FK`) REFERENCES `tb_pedido` (`ID_PEDIDO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_pedido_join_tb_catalogo
-- ----------------------------

-- ----------------------------
-- Table structure for tb_permissao
-- ----------------------------
DROP TABLE IF EXISTS `tb_permissao`;
CREATE TABLE `tb_permissao` (
  `ID_PERMISSAO` bigint(20) NOT NULL AUTO_INCREMENT,
  `DS_PERMISSAO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_PERMISSAO`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_permissao
-- ----------------------------
INSERT INTO `tb_permissao` VALUES ('1', 'ADMINISTRADOR');
INSERT INTO `tb_permissao` VALUES ('2', 'USUARIO_COMUM');
INSERT INTO `tb_permissao` VALUES ('3', 'USUARIO_PREMIUM');

-- ----------------------------
-- Table structure for tb_secao
-- ----------------------------
DROP TABLE IF EXISTS `tb_secao`;
CREATE TABLE `tb_secao` (
  `ID_SECAO` int(11) NOT NULL AUTO_INCREMENT,
  `NM_SECAO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_SECAO`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tb_secao
-- ----------------------------
INSERT INTO `tb_secao` VALUES ('1', 'Seção 1');
INSERT INTO `tb_secao` VALUES ('2', 'Seção 2');

-- ----------------------------
-- Table structure for tb_situacao_pagto
-- ----------------------------
DROP TABLE IF EXISTS `tb_situacao_pagto`;
CREATE TABLE `tb_situacao_pagto` (
  `ID_SITUACAO_PAGTO` bigint(20) NOT NULL AUTO_INCREMENT,
  `DS_SITUACAO_PAGTO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_SITUACAO_PAGTO`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_situacao_pagto
-- ----------------------------
INSERT INTO `tb_situacao_pagto` VALUES ('1', 'Pago');
INSERT INTO `tb_situacao_pagto` VALUES ('2', 'Pendente de Pagamento');

-- ----------------------------
-- Table structure for tb_tipo_agenda
-- ----------------------------
DROP TABLE IF EXISTS `tb_tipo_agenda`;
CREATE TABLE `tb_tipo_agenda` (
  `ID_AGENDA` bigint(20) NOT NULL AUTO_INCREMENT,
  `FL_CONFIG_PADRAO` tinyint(1) DEFAULT NULL,
  `DS_TIPO_AGENDA` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_AGENDA`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tb_tipo_agenda
-- ----------------------------
INSERT INTO `tb_tipo_agenda` VALUES ('1', '1', 'Agenda de Acompanhamento');
INSERT INTO `tb_tipo_agenda` VALUES ('2', '1', 'Agenda Pessoal');
INSERT INTO `tb_tipo_agenda` VALUES ('3', '1', 'Agenda Profissional');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `ID_USER` bigint(20) NOT NULL AUTO_INCREMENT,
  `DS_PASSWORD` varchar(255) NOT NULL,
  `DS_USERNAME` varchar(25) NOT NULL,
  `TB_PERMISSAO_USER_FK` bigint(20) NOT NULL,
  `NU_CPF` bigint(20) NOT NULL,
  `DT_NASCIMENTO` datetime DEFAULT NULL,
  `DS_EMAIL` varchar(255) DEFAULT NULL,
  `DS_BAIRRO` varchar(255) DEFAULT NULL,
  `DS_CEP` varchar(255) DEFAULT NULL,
  `DS_CIDADE` varchar(255) DEFAULT NULL,
  `DS_COMPLEMENTO` varchar(255) DEFAULT NULL,
  `DS_ESTADO` varchar(255) DEFAULT NULL,
  `DS_LOGRADOURO` varchar(255) DEFAULT NULL,
  `DS_NUMERO` varchar(255) DEFAULT NULL,
  `DS_TP_LOGRADOURO` varchar(255) DEFAULT NULL,
  `DS_NOME` varchar(255) DEFAULT NULL,
  `NU_CELULAR` bigint(20) DEFAULT NULL,
  `NU_TELEFONE` bigint(20) DEFAULT NULL,
  `DS_SEXO` varchar(255) DEFAULT NULL,
  `DS_AVATAR` varchar(255) NOT NULL,
  PRIMARY KEY (`ID_USER`),
  UNIQUE KEY `DS_USERNAME` (`DS_USERNAME`),
  KEY `FKD18DE6FCBD380130` (`TB_PERMISSAO_USER_FK`),
  CONSTRAINT `FKD18DE6FCBD380130` FOREIGN KEY (`TB_PERMISSAO_USER_FK`) REFERENCES `tb_permissao` (`ID_PERMISSAO`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', '202CB962AC59075B964B07152D234B70', 'admin', '1', '41236557859', '1992-06-19 18:38:55', 'christopherscotini@mkoffice.com.br', 'Jd Tupanci', '06414-025', 'Barueri', 'BL C AP 55', 'SP', 'Rua Werner GOldBerg', '179', null, 'CHristopher Scotini Rozário', '11980138781', '1141683898', 'M', 'resources/images/avatar/man_business_avatar.png');
INSERT INTO `tb_user` VALUES ('2', '202CB962AC59075B964B07152D234B70', 'joice', '2', '35105930819', '1988-02-03 18:40:30', 'joice_as@mkoffice.com.br', 'Vila Izabel', '06180-200', 'Osasco', null, 'SP', 'Rua Nilsa Barbosa Barreto', '16', null, 'Joice Santana', '11997253039', '1141683898', 'F', 'resources/images/avatar/woman_business_avatar.png');

-- ----------------------------
-- Table structure for tb_venda
-- ----------------------------
DROP TABLE IF EXISTS `tb_venda`;
CREATE TABLE `tb_venda` (
  `ID_VENDA` bigint(20) NOT NULL AUTO_INCREMENT,
  `DT_VENDA` datetime NOT NULL,
  `NU_QTDE_TOTAL_PONTOS_VENDIDOS` int(11) NOT NULL,
  `VL_VLR_DESCONTO_VENDA` decimal(19,2) DEFAULT NULL,
  `VL_VLR_LUCRO` decimal(19,2) NOT NULL,
  `VL_VLR_VENDA` decimal(19,2) NOT NULL,
  `TB_CLIENTE_TB_VENDA_FK` bigint(20) NOT NULL,
  `TB_FORMA_PAGTO_VENDA_FK` bigint(20) DEFAULT NULL,
  `TB_USUARIO_FK` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID_VENDA`),
  KEY `FK6036D32BCFA52D92` (`TB_CLIENTE_TB_VENDA_FK`),
  KEY `FK6036D32BB9C1A942` (`TB_USUARIO_FK`),
  KEY `FK6036D32B6D3F8462` (`TB_FORMA_PAGTO_VENDA_FK`),
  CONSTRAINT `FK6036D32B6D3F8462` FOREIGN KEY (`TB_FORMA_PAGTO_VENDA_FK`) REFERENCES `tb_forma_pgto` (`ID_FORMA_PGTO`),
  CONSTRAINT `FK6036D32BB9C1A942` FOREIGN KEY (`TB_USUARIO_FK`) REFERENCES `tb_user` (`ID_USER`),
  CONSTRAINT `FK6036D32BCFA52D92` FOREIGN KEY (`TB_CLIENTE_TB_VENDA_FK`) REFERENCES `tb_cliente` (`ID_CLIENTE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_venda
-- ----------------------------

-- ----------------------------
-- Table structure for tb_venda_join_tb_catalogo
-- ----------------------------
DROP TABLE IF EXISTS `tb_venda_join_tb_catalogo`;
CREATE TABLE `tb_venda_join_tb_catalogo` (
  `ID_JOIN` bigint(20) NOT NULL AUTO_INCREMENT,
  `DT_VENDA` datetime DEFAULT NULL,
  `NU_QTDE_VENDIDA` int(11) DEFAULT NULL,
  `NU_SOMA_PONTOS_VENDIDOS` int(11) DEFAULT NULL,
  `VL_VLR_TOTAL_VENDIDO` decimal(19,2) DEFAULT NULL,
  `ID_CATALOGO_FK` bigint(20) DEFAULT NULL,
  `ID_VENDA_FK` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID_JOIN`),
  KEY `FK6E3C4526D01FCCE6` (`ID_CATALOGO_FK`),
  KEY `FK6E3C452668EDB0EB` (`ID_VENDA_FK`),
  CONSTRAINT `FK6E3C452668EDB0EB` FOREIGN KEY (`ID_VENDA_FK`) REFERENCES `tb_venda` (`ID_VENDA`),
  CONSTRAINT `FK6E3C4526D01FCCE6` FOREIGN KEY (`ID_CATALOGO_FK`) REFERENCES `tb_catalogo` (`ID_CATALOGO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_venda_join_tb_catalogo
-- ----------------------------
