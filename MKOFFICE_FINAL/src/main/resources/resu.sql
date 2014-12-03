       select * from TB_PARCELA parcela1 
       left outer join
           TB_PEDIDO pedido1 on parcela1.TB_PEDIDO_PARCELA_FK=pedido1.ID_PEDIDO 
       left outer join
           TB_FORMA_PGTO formapagam2 on pedido1.TB_FORMA_PAGTO_PEDIDO_FK=formapagam2.ID_FORMA_PGTO 
       left outer join 
       	   TB_USER userentity3 on pedido1.TB_USUARIO_FK=userentity3.ID_USER 
       left outer join
           TB_PERMISSAO permissaoe4 on userentity3.TB_PERMISSAO_USER_FK=permissaoe4.ID_PERMISSAO
       left outer join
           TB_SITUACAO_PAGTO situacaoen5 on TB_PARCELA.TB_SITUACAO_PAGTO_PARCELA_FK=situacaoen5.ID_SITUACAO_PAGTO 
       left outer join
           TB_USER userentity6 on parcela1.TB_USUARIO_FK=userentity6.ID_USER 
       where parcela1.TB_VENDA_PARCELA_FK=?


	   select * from TB_VENDA venda1
       left outer join
           TB_FORMA_PGTO formapagam1 on venda1.TB_FORMA_PAGTO_VENDA_FK=formapagam1.ID_FORMA_PGTO 
       left outer join
           TB_USER userentity2 on venda1.TB_USUARIO_FK=userentity2.ID_USER 
       left outer join
           TB_PERMISSAO permissaoe3 on userentity2.TB_PERMISSAO_USER_FK=permissaoe3.ID_PERMISSAO 
       where venda1.TB_CLIENTE_TB_VENDA_FK=?
           
       select vendaentit0_.TB_CLIENTE_TB_VENDA_FK as col_0_0_,
         sum(case when month(vendaentit0_.DT_VENDA)=1 then vendaentit0_.VL_VLR_VENDA else 0 end) as col_1_0_,
         sum(case when month(vendaentit0_.DT_VENDA)=2 then vendaentit0_.VL_VLR_VENDA else 0 end) as col_2_0_,
         sum(case when month(vendaentit0_.DT_VENDA)=3 then vendaentit0_.VL_VLR_VENDA else 0 end) as col_3_0_,
         sum(case when month(vendaentit0_.DT_VENDA)=4 then vendaentit0_.VL_VLR_VENDA else 0 end) as col_4_0_,
         sum(case when month(vendaentit0_.DT_VENDA)=5 then vendaentit0_.VL_VLR_VENDA else 0 end) as col_5_0_,
         sum(case when month(vendaentit0_.DT_VENDA)=6 then vendaentit0_.VL_VLR_VENDA else 0 end) as col_6_0_,
         sum(case when month(vendaentit0_.DT_VENDA)=7 then vendaentit0_.VL_VLR_VENDA else 0 end) as col_7_0_,
         sum(case when month(vendaentit0_.DT_VENDA)=8 then vendaentit0_.VL_VLR_VENDA else 0 end) as col_8_0_,
         sum(case when month(vendaentit0_.DT_VENDA)=9 then vendaentit0_.VL_VLR_VENDA else 0 end) as col_9_0_,
         sum(case when month(vendaentit0_.DT_VENDA)=10 then vendaentit0_.VL_VLR_VENDA else 0 end) as col_10_0_,
         sum(case when month(vendaentit0_.DT_VENDA)=11 then vendaentit0_.VL_VLR_VENDA else 0 end) as col_11_0_,
         sum(case when month(vendaentit0_.DT_VENDA)=12 then vendaentit0_.VL_VLR_VENDA else 0 end) as col_12_0_,
         tb_cliente.* 
     from
         TB_VENDA vendaentit0_ 
     inner join
         TB_CLIENTE clienteent1_ on vendaentit0_.TB_CLIENTE_TB_VENDA_FK=tb_cliente.ID_CLIENTE 
     where(vendaentit0_.DT_VENDA between '2014/01/01 00:00:00' and '2014/12/31 23:59:59') and vendaentit0_.TB_USUARIO_FK=? 
     group by vendaentit0_.TB_CLIENTE_TB_VENDA_FK 
     having sum(vendaentit0_.VL_VLR_VENDA)>=? 
     order by vendaentit0_.VL_VLR_VENDA desc
         
         
     select * from TB_VENDA listavenda0_ 
     left outer join
         TB_FORMA_PGTO formapagam1_ on listavenda0_.TB_FORMA_PAGTO_VENDA_FK=formapagam1_.ID_FORMA_PGTO 
     left outer join TB_USER userentity2_  
             on listavenda0_.TB_USUARIO_FK=userentity2_.ID_USER 
     left outer join
         TB_PERMISSAO permissaoe3_ 
             on userentity2_.TB_PERMISSAO_USER_FK=permissaoe3_.ID_PERMISSAO 
     where
         listavenda0_.TB_CLIENTE_TB_VENDA_FK=?
           
     
   SELECT c.*,
   	  COALESCE(e.FL_DISPONIVEL,0),
      COALESCE(SUM(e.NU_QTDE_PRODUTO_ESTOQUE_ATUAL),0) 
  from
      tb_catalogo AS c 
  LEFT JOIN
      (SELECT
              est.TB_CATALOGO_TB_ESTOQUE_FK,
              est.FL_DISPONIVEL,
              est.NU_QTDE_PRODUTO_ESTOQUE_ATUAL 
          FROM
              tb_estoque AS est 
          WHERE
              est.FL_DISPONIVEL = 1
      ) AS e 
          ON c.ID_CATALOGO = e.TB_CATALOGO_TB_ESTOQUE_FK  
  GROUP BY
      c.ID_CATALOGO 
  ORDER BY
      c.NM_PRODUTO ASC
          
      
     select
         sum(parcelasen0_.VL_VLR_PAGO) as col_0_0_,
         count(parcelasen0_.ID_PARCELA) as col_1_0_ 
     from
         TB_PARCELA parcelasen0_ 
     where (parcelasen0_.DT_PAGAMENTO between '2014/01/01 00:00:00' and '2014/12/31 23:59:59') 
         and (parcelasen0_.TB_PEDIDO_PARCELA_FK is not null) 
         and parcelasen0_.TB_USUARIO_FK=? limit ?

    select sum(pedidoprod2_.NU_QTDE_COMPRADA) as col_0_0_ 
     from TB_PARCELA parcelasen0_ 
     inner join
         TB_PEDIDO pedidoenti1_ on parcelasen0_.TB_PEDIDO_PARCELA_FK=pedidoenti1_.ID_PEDIDO 
     inner join
         TB_PEDIDO_JOIN_TB_CATALOGO pedidoprod2_ on pedidoenti1_.ID_PEDIDO=pedidoprod2_.ID_PEDIDO_FK 
     where(parcelasen0_.DT_PAGAMENTO between '2014/01/01 00:00:00' and '2014/12/31 23:59:59') 
         and (parcelasen0_.TB_PEDIDO_PARCELA_FK is not null) 
         and parcelasen0_.TB_USUARIO_FK=? limit ?

