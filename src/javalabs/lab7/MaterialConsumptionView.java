package javalabs.lab7;

import javalabs.lab7.Model.MaterialConsumption;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class MaterialConsumptionView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton addButton, updateButton, deleteButton, saveButton, loadButton;

    public MaterialConsumptionView() {
        setTitle("Material Consumption Manager");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Создаем модель таблицы
        String[] columns = {"Index", "Количество", "Материал", "Запись"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Создаем панель с кнопками
        JPanel buttonPanel = new JPanel(new FlowLayout());
        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);

        // Добавляем компоненты на форму
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Методы для работы с контроллером
    public void setAddButtonListener(java.awt.event.ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void setUpdateButtonListener(java.awt.event.ActionListener listener) {
        updateButton.addActionListener(listener);
    }

    public void setDeleteButtonListener(java.awt.event.ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    public void setSaveButtonListener(java.awt.event.ActionListener listener) {
        saveButton.addActionListener(listener);
    }

    public void setLoadButtonListener(java.awt.event.ActionListener listener) {
        loadButton.addActionListener(listener);
    }

    public int getSelectedIndex() {
        return table.getSelectedRow();
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void showError(String error) {
        JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void updateTable(MaterialConsumption[] items) {
        tableModel.setRowCount(0);
        if (items != null) {
            for (int i = 0; i < items.length; i++) {
                MaterialConsumption item = items[i];
                tableModel.addRow(new Object[]{
                        i,
                        item.getCount(),
                        item.getMaterial(),
                        item.getRecord(),
                });
            }
        }
    }

    public MaterialConsumption showInputDialog(String title, MaterialConsumption defaultValue) {
        JTextField nameField = new JTextField(defaultValue != null ? String.valueOf(defaultValue.getCount()) : "");
        JTextField quantityField = new JTextField(defaultValue != null ? String.valueOf(defaultValue.getMaterial()) : "");
        JTextField unitField = new JTextField(defaultValue != null ? String.valueOf(defaultValue.getRecord()) : "");

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Количество:"));
        panel.add(nameField);
        panel.add(new JLabel("Материал:"));
        panel.add(quantityField);
        panel.add(new JLabel("Запись:"));
        panel.add(unitField);

        int result = JOptionPane.showConfirmDialog(this, panel, title,
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            MaterialConsumption item = new MaterialConsumption();
            item.setCount(Integer.parseInt(nameField.getText()));
            //item.setMaterial(quantityField.getText());
            //item.setUnit(unitField.getText());
            return item;
        }
        return null;
    }

    public String showFileChooser(String title) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle(title);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        }
        return null;
    }
}