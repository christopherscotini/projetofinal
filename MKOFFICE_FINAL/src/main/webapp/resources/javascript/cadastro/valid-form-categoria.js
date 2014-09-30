msgsOcampo = 'O campo';
msgObrigatorio = 'é obrigatório';
pulaLinha = '\n';

function validarFormCategoria(form) {
	var descCategoria = document.getElementById(form.name + ":txtCat");
	var cboCategoria = document.getElementById(form.name
			+ ":cboSecao_input");

	if (isEmpty(descCategoria)) {
		msg += msgsOcampo + " Desc. Categoria " + msgObrigatorio + pulaLinha;
	}
	if (isEmpty(cboCategoria.value)) {
		msg += msgsOcampo + " Seção " + msgObrigatorio + pulaLinha;
	}

	if (msg != '') {
		alert(msg);
		return false;
	} else {
		return true;
	}
}
