package controle.matricula.telas.impl;

import controle.matricula.telas.telabase.TelaEditarBase;

public class TelaEditarPessoa extends TelaEditarBase {

    public void editarTela() {
        String[] labelsAndFields = {"Nome Completo", "", "CPF", "", "Endereço", "", "UF", "", "email", "", "Telefone", ""};
        criarPainel(labelsAndFields);
    }

    @Override
    protected void configurarTitulo() {
        frameEditar.setTitle("Editar Pessoa");
    }
}
