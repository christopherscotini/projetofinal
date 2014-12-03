select
         * 
     from
         TB_PARCELA parcelas0_ 
     left outer join
         TB_PEDIDO pedidoenti1_ 
             on parcelas0_.TB_PEDIDO_PARCELA_FK=pedidoenti1_.ID_PEDIDO 
     left outer join
         TB_FORMA_PGTO formapagam2_ 
             on pedidoenti1_.TB_FORMA_PAGTO_PEDIDO_FK=formapagam2_.ID_FORMA_PGTO 
     left outer join
         TB_USER userentity3_ 
             on pedidoenti1_.TB_USUARIO_FK=userentity3_.ID_USER 
     left outer join
         TB_PERMISSAO permissaoe4_ 
             on userentity3_.TB_PERMISSAO_USER_FK=permissaoe4_.ID_PERMISSAO 
     left outer join
         TB_SITUACAO_PAGTO situacaoen5_ 
             on parcelas0_.TB_SITUACAO_PAGTO_PARCELA_FK=situacaoen5_.ID_SITUACAO_PAGTO 
     left outer join
         TB_USER userentity6_ 
             on parcelas0_.TB_USUARIO_FK=userentity6_.ID_USER 
     where
         parcelas0_.TB_VENDA_PARCELA_FK=?