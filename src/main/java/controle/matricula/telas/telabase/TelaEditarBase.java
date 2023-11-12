package controle.matricula.telas.telabase;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public abstract class TelaEditarBase extends JFrame {

    protected JFrame frameEditar;

    protected void criarPainel(String... campos) {
        if (campos.length % 2 != 0) {
            throw new IllegalArgumentException("A quantidade de rótulos e campos deve ser par.");
        }

        var panel = new JPanel(new GridLayout(campos.length / 2 + 1, 2));

        for (int i = 0; i < campos.length; i += 2) {
            var label = new JLabel(campos[i] + ":");
            panel.add(label);
            if (campos[i].equalsIgnoreCase("UF")) {
                var ufComboBox = new JComboBox<>(getUFOptions());
                ufComboBox.setSelectedItem(campos[i + 1]);
                panel.add(ufComboBox);
            } else {
                var textField = new JTextField(20);
                textField.setText(campos[i + 1]);
                panel.add(textField);
            }
        }

        panel.add(new JButton("Cancelar"));
        panel.add(new JButton("Salvar"));

        frameEditar = new JFrame();
        frameEditar.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frameEditar.add(panel);
        frameEditar.pack();
        frameEditar.setLocationRelativeTo(null);
        frameEditar.setVisible(true);
    }

    private String[] getUFOptions() {
        return new String[]{"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB",
                "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};
    }

    protected abstract void configurarTitulo();

}
