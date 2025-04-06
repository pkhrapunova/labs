package javalabs.lab7;

import javalabs.lab7.Model.MaterialConsumption;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class CollectionController {
    private final MaterialConsumptionModel model;
    private final MaterialConsumptionView view;

    public CollectionController(MaterialConsumptionModel model, MaterialConsumptionView view) {
        this.model = model;
        this.view = view;

        // Настраиваем обработчики событий
        view.setAddButtonListener(new AddButtonListener());
        view.setUpdateButtonListener(new UpdateButtonListener());
        view.setDeleteButtonListener(new DeleteButtonListener());
        view.setSaveButtonListener(new SaveButtonListener());
        view.setLoadButtonListener(new LoadButtonListener());

        // Обновляем таблицу при запуске
        updateView();
    }

    private void updateView() {
        try {
            view.updateTable(model.getAll());
        } catch (CustomException e) {
            view.showError(e.getMessage());
        }
    }

    class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MaterialConsumption item = view.showInputDialog("Add New Material Consumption", new MaterialConsumption());
            if (item != null) {
                model.add(item);
                updateView();
            }
        }
    }

    class UpdateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = view.getSelectedIndex();
//            if (selectedIndex >= 0) {
//                try {
//                    MaterialConsumption item = model.get(selectedIndex);
//                    MaterialConsumption updatedItem = view.showInputDialog("Update Material Consumption", item);
//                    if (updatedItem != null) {
//                        model.update(selectedIndex, updatedItem);
//                        updateView();
//                    }
//                } catch (CustomException ex) {
//                    view.showError(ex.getMessage());
//                }
//            } else {
//                view.showMessage("Please select an item to update.");
//            }
        }
    }

    class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = view.getSelectedIndex();
            if (selectedIndex >= 0) {
                try {
                    model.delete(selectedIndex);
                    updateView();
                } catch (CustomException ex) {
                    view.showError(ex.getMessage());
                }
            } else {
                view.showMessage("Please select an item to delete.");
            }
        }
    }

    class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String filePath = view.showFileChooser("Save to file");
                if (filePath != null) {
                    model.saveToFile(filePath);
                    view.showMessage("Data saved successfully.");
                }
            } catch (Exception ex) {
                view.showError("Error saving file: " + ex.getMessage());
            }
        }
    }

    class LoadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String filePath = view.showFileChooser("Load from file");
                if (filePath != null) {
                    model.loadFromFile(filePath);
                    updateView();
                    view.showMessage("Data loaded successfully.");
                }
            } catch (Exception ex) {
                view.showError("Error loading file: " + ex.getMessage());
            }
        }
    }

}
