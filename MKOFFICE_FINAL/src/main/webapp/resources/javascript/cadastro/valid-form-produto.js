msgsOcampo = 'O campo';
msgObrigatorio = 'é obrigatório';
pulaLinha = '\n';

function validarFormProdutoCatalogo(form) {
	var codProduto = document.getElementById(form.name + ":txtCodDialog");
	var descProduto = document.getElementById(form.name + ":txtProduto");
	var cboCategoria = document.getElementById(form.name
			+ ":txtCatDialog_input");
	var preco = document.getElementById(form.name + ":txtPrecoDialog");
	var pontos = document.getElementById(form.name + ":txtPontosDialog");

	var msg = '';

	if (isEmpty(codProduto.value)) {
		msg += msgsOcampo + " Cód Produto " + msgObrigatorio + pulaLinha;
	}
	if (isEmpty(descProduto.value)) {
		msg += msgsOcampo + " Desc. Produto " + msgObrigatorio + pulaLinha;
	}
	if (isEmpty(cboCategoria.value)) {
		msg += msgsOcampo + " Categoria " + msgObrigatorio + pulaLinha;
	}
	if (isEmpty(preco.value)) {
		msg += msgsOcampo + " Preço " + msgObrigatorio + pulaLinha;
	}
	if (isEmpty(pontos.value)) {
		msg += msgsOcampo + " Pontos " + msgObrigatorio + pulaLinha;
	}

	if (msg != '') {
		alert(msg);
		return false;
	} else {
		return true;
	}
}

function validarFormAtualizarEstoque(form) {
	var fluxo = document.getElementById(form.name
			+ ":txtTipoFluxoDialogEstoque_input");
	var qtde = document.getElementById(form.name
			+ ":txtQtdeTotalDialogEstoque_input");
	var msg = '';

	if (isEmpty(fluxo.value)) {
		msg += msgsOcampo + " TIPO DE FLUXO " + msgObrigatorio + pulaLinha;
	} else {
		if (fluxo.value <= 0) {
			msg += msgsOcampo + " TIPO DE FLUXO " + msgObrigatorio + pulaLinha
					+ pulaLinha;
		}
		if (isEmpty(qtde.value)) {
			msg += msgsOcampo + " QUANTIDADE " + msgObrigatorio + pulaLinha;
		} else {
			if (qtde.value < 0) {
				msg += msgsOcampo
						+ " QUANTIDADE deve ser maior ou igual a ZERO "
						+ pulaLinha;
			}
		}

		if (msg != '') {
			alert(msg);
			return false;
		} else {
			return true;
		}
	}
}