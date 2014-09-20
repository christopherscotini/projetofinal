/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50612
Source Host           : localhost:3306
Source Database       : mkoffice_final

Target Server Type    : MYSQL
Target Server Version : 50612
File Encoding         : 65001

Date: 2014-09-10 17:52:15
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
  `FL_ALLDAY` bit(1) DEFAULT NULL,
  `TB_CLIENTE_FK` bigint(20) DEFAULT NULL,
  `TB_TIPO_AGENDA_FK` bigint(20) DEFAULT NULL,
  `TB_USUARIO_FK` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID_AGENDA`),
  KEY `FK_qks7f16b9m6hj2s39wuxrv583` (`TB_CLIENTE_FK`),
  KEY `FK_g8v19jt3hd6c7034wlfare66v` (`TB_TIPO_AGENDA_FK`),
  KEY `FK_7qsqxpxblinxwx9bag9pkw76y` (`TB_USUARIO_FK`),
  CONSTRAINT `FK_7qsqxpxblinxwx9bag9pkw76y` FOREIGN KEY (`TB_USUARIO_FK`) REFERENCES `tb_user` (`ID_USER`),
  CONSTRAINT `FK_g8v19jt3hd6c7034wlfare66v` FOREIGN KEY (`TB_TIPO_AGENDA_FK`) REFERENCES `tb_compromisso` (`ID_AGENDA`),
  CONSTRAINT `FK_qks7f16b9m6hj2s39wuxrv583` FOREIGN KEY (`TB_CLIENTE_FK`) REFERENCES `tb_cliente` (`ID_CLIENTE`)
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
  `FL_ATIVO` bit(1) DEFAULT NULL,
  `CD_PRODUTO` bigint(20) DEFAULT NULL,
  `NM_PRODUTO` varchar(255) DEFAULT NULL,
  `NU_PONTOS` int(11) DEFAULT NULL,
  `VL_PRODUTO` decimal(19,2) DEFAULT NULL,
  `CATEGORIA_CATALOGO_FK` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID_CATALOGO`),
  KEY `FK_jw9dwhvm3kp2a2nt2mjage23k` (`CATEGORIA_CATALOGO_FK`),
  CONSTRAINT `FK_jw9dwhvm3kp2a2nt2mjage23k` FOREIGN KEY (`CATEGORIA_CATALOGO_FK`) REFERENCES `tb_categoria` (`ID_CATEGORIA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_catalogo
-- ----------------------------

-- ----------------------------
-- Table structure for tb_categoria
-- ----------------------------
DROP TABLE IF EXISTS `tb_categoria`;
CREATE TABLE `tb_categoria` (
  `ID_CATEGORIA` bigint(20) NOT NULL AUTO_INCREMENT,
  `NM_CATEGORIA` varchar(255) DEFAULT NULL,
  `SECAO_CATEGORIA_FK` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_CATEGORIA`),
  KEY `FK_a7c1orchhi67l5otfl7u8epo8` (`SECAO_CATEGORIA_FK`),
  CONSTRAINT `FK_a7c1orchhi67l5otfl7u8epo8` FOREIGN KEY (`SECAO_CATEGORIA_FK`) REFERENCES `tb_secao` (`ID_SECAO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_categoria
-- ----------------------------

-- ----------------------------
-- Table structure for tb_cliente
-- ----------------------------
DROP TABLE IF EXISTS `tb_cliente`;
CREATE TABLE `tb_cliente` (
  `ID_CLIENTE` bigint(20) NOT NULL AUTO_INCREMENT,
  `DT_INIC_ACOM` datetime DEFAULT NULL,
  `DT_PRIM_VENDA` datetime DEFAULT NULL,
  `DT_ULTI_VENDA` datetime DEFAULT NULL,
  `DS_INFO_ADIC` varchar(255) DEFAULT NULL,
  `TB_PESSOA_FK` bigint(20) NOT NULL,
  `TB_USUARIO_FK` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID_CLIENTE`),
  UNIQUE KEY `UK_1v3s2ghvins1i1jvjwv58gbnx` (`TB_PESSOA_FK`),
  KEY `FK_gnv7ljx41djrkkn1a2mo31xna` (`TB_USUARIO_FK`),
  CONSTRAINT `FK_1v3s2ghvins1i1jvjwv58gbnx` FOREIGN KEY (`TB_PESSOA_FK`) REFERENCES `tb_pessoa` (`ID_PESSOA`),
  CONSTRAINT `FK_gnv7ljx41djrkkn1a2mo31xna` FOREIGN KEY (`TB_USUARIO_FK`) REFERENCES `tb_user` (`ID_USER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_cliente
-- ----------------------------

-- ----------------------------
-- Table structure for tb_compromisso
-- ----------------------------
DROP TABLE IF EXISTS `tb_compromisso`;
CREATE TABLE `tb_compromisso` (
  `ID_AGENDA` bigint(20) NOT NULL AUTO_INCREMENT,
  `FL_CONFIG_PADRAO` bit(1) DEFAULT NULL,
  `DS_TIPO_AGENDA` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_AGENDA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_compromisso
-- ----------------------------

-- ----------------------------
-- Table structure for tb_configuracao_sistema
-- ----------------------------
DROP TABLE IF EXISTS `tb_configuracao_sistema`;
CREATE TABLE `tb_configuracao_sistema` (
  `ID_CONFIGURACAO_SISTEMA` bigint(20) NOT NULL AUTO_INCREMENT,
  `DS_IMG_FUNDO_TELA_LOGIN` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_CONFIGURACAO_SISTEMA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_configuracao_sistema
-- ----------------------------

-- ----------------------------
-- Table structure for tb_consultora
-- ----------------------------
DROP TABLE IF EXISTS `tb_consultora`;
CREATE TABLE `tb_consultora` (
  `ID_CONSULTORA` bigint(20) NOT NULL AUTO_INCREMENT,
  `DT_DESATIVACAO` datetime DEFAULT NULL,
  `DT_INICIACAO` datetime DEFAULT NULL,
  `DS_INF_ADICIONAL` varchar(255) DEFAULT NULL,
  `DS_STATUS` int(11) DEFAULT NULL,
  `TB_PESSOA_FK` bigint(20) NOT NULL,
  `TB_USUARIO_FK` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID_CONSULTORA`),
  UNIQUE KEY `UK_pdibees605nru2clcd6rwecgy` (`TB_PESSOA_FK`),
  KEY `FK_3i9t4guq8spbw2mfwx5xa49b0` (`TB_USUARIO_FK`),
  CONSTRAINT `FK_3i9t4guq8spbw2mfwx5xa49b0` FOREIGN KEY (`TB_USUARIO_FK`) REFERENCES `tb_user` (`ID_USER`),
  CONSTRAINT `FK_pdibees605nru2clcd6rwecgy` FOREIGN KEY (`TB_PESSOA_FK`) REFERENCES `tb_pessoa` (`ID_PESSOA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_consultora
-- ----------------------------

-- ----------------------------
-- Table structure for tb_endereco
-- ----------------------------
DROP TABLE IF EXISTS `tb_endereco`;
CREATE TABLE `tb_endereco` (
  `ID_ENDERECO` bigint(20) NOT NULL AUTO_INCREMENT,
  `DS_BAIRRO` varchar(255) DEFAULT NULL,
  `DS_CEP` varchar(255) DEFAULT NULL,
  `DS_CIDADE` varchar(255) DEFAULT NULL,
  `DS_COMPLEMENTO` varchar(255) DEFAULT NULL,
  `DS_ESTADO` varchar(255) DEFAULT NULL,
  `DS_LOGRADOURO` varchar(255) DEFAULT NULL,
  `DS_NUMERO` varchar(255) DEFAULT NULL,
  `DS_TP_LOGRADOURO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_ENDERECO`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_endereco
-- ----------------------------
INSERT INTO `tb_endereco` VALUES ('1', 'Jd Tupanci', '06414-025', 'Barueri', 'BL C AP 55', 'SP', 'Rua Werner GOldBerg', '179', 'Rua');

-- ----------------------------
-- Table structure for tb_forma_pgto
-- ----------------------------
DROP TABLE IF EXISTS `tb_forma_pgto`;
CREATE TABLE `tb_forma_pgto` (
  `ID_FORMA_PGTO` bigint(20) NOT NULL AUTO_INCREMENT,
  `DS_FORMA_PGTO` varchar(255) DEFAULT NULL,
  `NU_PARCELAS` int(11) DEFAULT NULL,
  `FL_POSSUI_PARCELAS` bit(1) DEFAULT NULL,
  PRIMARY KEY (`ID_FORMA_PGTO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_forma_pgto
-- ----------------------------

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
  KEY `FK_a30x1rkbnyyvohswv0g9ak6gm` (`TB_PEDIDO_PARCELA_FK`),
  KEY `FK_fuvssa3gwshco7ah16sqq0fx1` (`TB_SITUACAO_PAGTO_PARCELA_FK`),
  KEY `FK_ljhrnos69r5s1ldg3k58ybyr1` (`TB_VENDA_PARCELA_FK`),
  KEY `FK_qiyr57qv38na5f8kqp4yuyq57` (`TB_USUARIO_FK`),
  CONSTRAINT `FK_a30x1rkbnyyvohswv0g9ak6gm` FOREIGN KEY (`TB_PEDIDO_PARCELA_FK`) REFERENCES `tb_pedido` (`ID_PEDIDO`),
  CONSTRAINT `FK_fuvssa3gwshco7ah16sqq0fx1` FOREIGN KEY (`TB_SITUACAO_PAGTO_PARCELA_FK`) REFERENCES `tb_situacao_pagto` (`ID_SITUACAO_PAGTO`),
  CONSTRAINT `FK_ljhrnos69r5s1ldg3k58ybyr1` FOREIGN KEY (`TB_VENDA_PARCELA_FK`) REFERENCES `tb_venda` (`ID_VENDA`),
  CONSTRAINT `FK_qiyr57qv38na5f8kqp4yuyq57` FOREIGN KEY (`TB_USUARIO_FK`) REFERENCES `tb_user` (`ID_USER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_parcela
-- ----------------------------

-- ----------------------------
-- Table structure for tb_pedido
-- ----------------------------
DROP TABLE IF EXISTS `tb_pedido`;
CREATE TABLE `tb_pedido` (
  `ID_PEDIDO` bigint(20) NOT NULL AUTO_INCREMENT,
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
  KEY `FK_tcchamuso8fwu6vua93ao4twi` (`TB_FORMA_PAGTO_PEDIDO_FK`),
  KEY `FK_luq7pdn3e094uvhn6q5byy2xr` (`TB_USUARIO_FK`),
  CONSTRAINT `FK_luq7pdn3e094uvhn6q5byy2xr` FOREIGN KEY (`TB_USUARIO_FK`) REFERENCES `tb_user` (`ID_USER`),
  CONSTRAINT `FK_tcchamuso8fwu6vua93ao4twi` FOREIGN KEY (`TB_FORMA_PAGTO_PEDIDO_FK`) REFERENCES `tb_forma_pgto` (`ID_FORMA_PGTO`)
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
  KEY `FK_266d8n1isj0b4fc7wople7gpo` (`ID_CATALOGO_FK`),
  KEY `FK_3j3ma80mg04dmt80nm192c9yd` (`ID_PEDIDO_FK`),
  CONSTRAINT `FK_266d8n1isj0b4fc7wople7gpo` FOREIGN KEY (`ID_CATALOGO_FK`) REFERENCES `tb_catalogo` (`ID_CATALOGO`),
  CONSTRAINT `FK_3j3ma80mg04dmt80nm192c9yd` FOREIGN KEY (`ID_PEDIDO_FK`) REFERENCES `tb_pedido` (`ID_PEDIDO`)
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
-- Table structure for tb_pessoa
-- ----------------------------
DROP TABLE IF EXISTS `tb_pessoa`;
CREATE TABLE `tb_pessoa` (
  `ID_PESSOA` bigint(20) NOT NULL AUTO_INCREMENT,
  `DT_NASCIMENTO` datetime DEFAULT NULL,
  `DS_EMAIL` varchar(255) DEFAULT NULL,
  `DS_NOME` varchar(255) DEFAULT NULL,
  `NU_CELULAR` bigint(20) DEFAULT NULL,
  `NU_TELEFONE` bigint(20) DEFAULT NULL,
  `DS_SEXO` varchar(255) DEFAULT NULL,
  `TB_ENDERECO_PESSOA_FK` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID_PESSOA`),
  KEY `FK_cki4s69atdoy30befii20cfna` (`TB_ENDERECO_PESSOA_FK`),
  CONSTRAINT `FK_cki4s69atdoy30befii20cfna` FOREIGN KEY (`TB_ENDERECO_PESSOA_FK`) REFERENCES `tb_endereco` (`ID_ENDERECO`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_pessoa
-- ----------------------------
INSERT INTO `tb_pessoa` VALUES ('1', '1992-06-19 17:44:22', 'christopherscotini@mkoffice.com.br', 'Christopher Scotini Rozário', '11980138781', '1141683898', 'M', '1');

-- ----------------------------
-- Table structure for tb_produto
-- ----------------------------
DROP TABLE IF EXISTS `tb_produto`;
CREATE TABLE `tb_produto` (
  `ID_ESTOQUE` bigint(20) NOT NULL AUTO_INCREMENT,
  `FL_DISPONIVEL` bit(1) DEFAULT NULL,
  `DT_MOVIMENTACAO` datetime DEFAULT NULL,
  `NU_PERC_DESCONTO` varchar(255) DEFAULT NULL,
  `NU_QTDE_PRODUTO_ESTOQUE_ATUAL` int(11) DEFAULT NULL,
  `NU_QTDE_PRODUTO_MOVIMENTADO` int(11) DEFAULT NULL,
  `NU_SOMA_PONTOS_MOVIMENTADOS` int(11) DEFAULT NULL,
  `VL_VLR_TOTAL_MOVIMENTADO` decimal(19,2) DEFAULT NULL,
  `TB_CATALOGO_TB_ESTOQUE_FK` bigint(20) NOT NULL,
  `TB_PEDIDO_TB_ESTOQUE_FK` bigint(20) DEFAULT NULL,
  `TB_VENDA_TB_ESTOQUE_FK` bigint(20) DEFAULT NULL,
  `TB_USUARIO_FK` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID_ESTOQUE`),
  KEY `FK_p4i9h34jpptu7joihsn686tgx` (`TB_CATALOGO_TB_ESTOQUE_FK`),
  KEY `FK_qrexn7yfkt8uxj06gpxgeju0b` (`TB_PEDIDO_TB_ESTOQUE_FK`),
  KEY `FK_6obh5q1mc2p0rlf5at6uyw923` (`TB_VENDA_TB_ESTOQUE_FK`),
  KEY `FK_306p9pmxeqonw8kjjgu5x0byy` (`TB_USUARIO_FK`),
  CONSTRAINT `FK_306p9pmxeqonw8kjjgu5x0byy` FOREIGN KEY (`TB_USUARIO_FK`) REFERENCES `tb_user` (`ID_USER`),
  CONSTRAINT `FK_6obh5q1mc2p0rlf5at6uyw923` FOREIGN KEY (`TB_VENDA_TB_ESTOQUE_FK`) REFERENCES `tb_venda` (`ID_VENDA`),
  CONSTRAINT `FK_p4i9h34jpptu7joihsn686tgx` FOREIGN KEY (`TB_CATALOGO_TB_ESTOQUE_FK`) REFERENCES `tb_catalogo` (`ID_CATALOGO`),
  CONSTRAINT `FK_qrexn7yfkt8uxj06gpxgeju0b` FOREIGN KEY (`TB_PEDIDO_TB_ESTOQUE_FK`) REFERENCES `tb_pedido` (`ID_PEDIDO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_produto
-- ----------------------------

-- ----------------------------
-- Table structure for tb_secao
-- ----------------------------
DROP TABLE IF EXISTS `tb_secao`;
CREATE TABLE `tb_secao` (
  `ID_SECAO` int(11) NOT NULL AUTO_INCREMENT,
  `NM_SECAO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_SECAO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_secao
-- ----------------------------

-- ----------------------------
-- Table structure for tb_situacao_pagto
-- ----------------------------
DROP TABLE IF EXISTS `tb_situacao_pagto`;
CREATE TABLE `tb_situacao_pagto` (
  `ID_SITUACAO_PAGTO` bigint(20) NOT NULL AUTO_INCREMENT,
  `DS_SITUACAO_PAGTO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_SITUACAO_PAGTO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_situacao_pagto
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `ID_USER` bigint(20) NOT NULL AUTO_INCREMENT,
  `NU_CPF` bigint(20) NOT NULL,
  `DS_PASSWORD` varchar(255) NOT NULL,
  `DS_USERNAME` varchar(25) NOT NULL,
  `TB_PESSOA_USER_FK` bigint(20) NOT NULL,
  `TB_PERMISSAO_USER_FK` bigint(20) NOT NULL,
  PRIMARY KEY (`ID_USER`),
  UNIQUE KEY `UK_k5o2glhpkwjko5ivkxdq41a65` (`DS_USERNAME`),
  KEY `FK_fdj8mir08hedxe1nrjxvs0w6p` (`TB_PESSOA_USER_FK`),
  KEY `FK_5ys92wqoyrk89ifd98py9on8h` (`TB_PERMISSAO_USER_FK`),
  CONSTRAINT `FK_5ys92wqoyrk89ifd98py9on8h` FOREIGN KEY (`TB_PERMISSAO_USER_FK`) REFERENCES `tb_permissao` (`ID_PERMISSAO`),
  CONSTRAINT `FK_fdj8mir08hedxe1nrjxvs0w6p` FOREIGN KEY (`TB_PESSOA_USER_FK`) REFERENCES `tb_pessoa` (`ID_PESSOA`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', '41236557859', '202CB962AC59075B964B07152D234B70', 'admin', '1', '1');

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
  KEY `FK_ah300h770826w4b8gdhvm6xy4` (`TB_CLIENTE_TB_VENDA_FK`),
  KEY `FK_l9q8erib4eg9a604qn0jk4x20` (`TB_FORMA_PAGTO_VENDA_FK`),
  KEY `FK_qbi1hbms80c2c10w45nudqd8l` (`TB_USUARIO_FK`),
  CONSTRAINT `FK_ah300h770826w4b8gdhvm6xy4` FOREIGN KEY (`TB_CLIENTE_TB_VENDA_FK`) REFERENCES `tb_cliente` (`ID_CLIENTE`),
  CONSTRAINT `FK_l9q8erib4eg9a604qn0jk4x20` FOREIGN KEY (`TB_FORMA_PAGTO_VENDA_FK`) REFERENCES `tb_forma_pgto` (`ID_FORMA_PGTO`),
  CONSTRAINT `FK_qbi1hbms80c2c10w45nudqd8l` FOREIGN KEY (`TB_USUARIO_FK`) REFERENCES `tb_user` (`ID_USER`)
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
  KEY `FK_q62eafekhsh0s1rauo253wuul` (`ID_CATALOGO_FK`),
  KEY `FK_swikoijjlsdg984oa6fycybe` (`ID_VENDA_FK`),
  CONSTRAINT `FK_q62eafekhsh0s1rauo253wuul` FOREIGN KEY (`ID_CATALOGO_FK`) REFERENCES `tb_catalogo` (`ID_CATALOGO`),
  CONSTRAINT `FK_swikoijjlsdg984oa6fycybe` FOREIGN KEY (`ID_VENDA_FK`) REFERENCES `tb_venda` (`ID_VENDA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_venda_join_tb_catalogo
-- ----------------------------
