package javalabs.lab7;

import javax.swing.*;

//--add-opens java.base/java.time=ALL-UNNAMED
public class mainForm {

    private JPanel panel1;

    public static void main(String[] args) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            SwingUtilities.invokeLater(() -> {
                MaterialConsumptionModel model = new MaterialConsumptionModel();
                MaterialConsumptionView view = new MaterialConsumptionView();
                new CollectionController(model, view);
                view.setVisible(true);
            });
        }
    }