package controle.matricula.telas.impl;

import javax.swing.*;

import static java.awt.EventQueue.invokeLater;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import static javax.swing.UIManager.getInstalledLookAndFeels;
import static javax.swing.UIManager.setLookAndFeel;

public class TelaFaturamento extends JFrame {

    private static TelaFaturamento telaMenuInstance;


    public static void telaFaturamento() {

        if (telaMenuInstance == null) {
            try {
                for (UIManager.LookAndFeelInfo info : getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (Exception ex) {
                getLogger(TelaFaturamento.class.getName()).log(SEVERE, null, ex);
            }

            telaMenuInstance = new TelaFaturamento();
            invokeLater(() -> telaMenuInstance.setVisible(true));
        } else {
            telaMenuInstance.setExtendedState(NORMAL);
            telaMenuInstance.setVisible(true);
            telaMenuInstance.toFront();
        }
    }
}
