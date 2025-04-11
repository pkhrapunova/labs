package javalabs.lab7;

import javalabs.lab7.Model.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class MaterialConsumptionView extends JFrame {
    private final JTable table;
    private final DefaultTableModel tableModel;
    private final JButton addButton, updateButton, deleteButton, saveButton, loadButton;

    public MaterialConsumptionView() {
        setTitle("Расход материала");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("src/javalabs/lab7/women.png");
        setIconImage(icon.getImage());



        String[] columns = {
                "№", "Количество",
                "Название материала", "Цена", "Категория",
                "Дата", "Стоимость",
                "Фамилия мастера", "Имя мастера", "Должность", "Телефон мастера",
                "Фамилия клиента", "Имя клиента", "Телефон клиента"
        };

        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);



        JScrollPane scrollPane = new JScrollPane(table);

        // Панель с кнопками
        JPanel buttonPanel = new JPanel(new FlowLayout());
        addButton = new JButton("Добавить");
        updateButton = new JButton("Изменить");
        deleteButton = new JButton("Удалить");
        saveButton = new JButton("Сохранить");
        loadButton = new JButton("Загрузить");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

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
        JOptionPane.showMessageDialog(this, error, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }

    public void updateTable(MaterialConsumption[] items) {
        tableModel.setRowCount(0);
        if (items != null) {
            for (int i = 0; i < items.length; i++) {
                MaterialConsumption item = items[i];
                tableModel.addRow(new Object[]{
                        i,
                        item.getCount(),
                        item.getMaterial().getName(),
                        item.getMaterial().getCost(),
                        item.getMaterial().getCategories(),

                        item.getRecord().getDate(),
                        item.getRecord().getPrice(),

                        item.getRecord().getMaster().getLastName(),
                        item.getRecord().getMaster().getFirstName(),
                        item.getRecord().getMaster().getPost(),
                        item.getRecord().getMaster().getNumberPhone(),

                        item.getRecord().getClient().getLastName(),
                        item.getRecord().getClient().getFirstName(),
                        item.getRecord().getClient().getNumberPhone()
                });
            }
        }
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

    public MaterialConsumption showInputDialog(String title, MaterialConsumption defaultValue) {
        JTextField countField = new JTextField(defaultValue != null ? String.valueOf(defaultValue.getCount()) : "");
        JTextField nameField = new JTextField(defaultValue != null ? defaultValue.getMaterial().getName() : "");
        JTextField costField = new JTextField(defaultValue != null ? String.valueOf(defaultValue.getMaterial().getCost()) : "");

        JComboBox<Categories> categoryBox = new JComboBox<>(Categories.values());
        if (defaultValue != null) {
            categoryBox.setSelectedItem(defaultValue.getMaterial().getCategories());
        }
        JTextField dateField = new JTextField(defaultValue != null ? String.valueOf(defaultValue.getRecord().getDate()) : "");
        JTextField priceField = new JTextField(defaultValue != null ? String.valueOf(defaultValue.getRecord().getPrice()) : "");
        JTextField lNameMasterField = new JTextField(defaultValue != null ? defaultValue.getRecord().getMaster().getLastName() : "");
        JTextField fNameMasterField = new JTextField(defaultValue != null ? defaultValue.getRecord().getMaster().getFirstName() : "");
        JTextField postField = new JTextField(defaultValue != null ? String.valueOf(defaultValue.getRecord().getMaster().getPost()) : "");
        JTextField numberPhoneMasterField = new JTextField(defaultValue != null ? String.valueOf(defaultValue.getRecord().getMaster().getNumberPhone()) : "");
        JTextField lNameClientField = new JTextField(defaultValue != null ? defaultValue.getRecord().getClient().getLastName() : "");
        JTextField fNameClientField = new JTextField(defaultValue != null ? String.valueOf(defaultValue.getRecord().getClient().getFirstName()) : "");
        JTextField numberPhoneClientField = new JTextField(defaultValue != null ? String.valueOf(defaultValue.getRecord().getClient().getNumberPhone()) : "");


        // Создаем панель для ввода данных
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Количество:"));
        panel.add(countField);
        panel.add(new JLabel("Название материала:"));
        panel.add(nameField);
        panel.add(new JLabel("Цена($):"));
        panel.add(costField);
        panel.add(new JLabel("Категория:"));
        panel.add(categoryBox);
        panel.add(new JLabel("Стоимость($):"));
        panel.add(priceField);
        panel.add(new JLabel("Дата (yyyy-MM-dd):"));
        panel.add(dateField);
        panel.add(new JLabel("Фамилия мастера:"));
        panel.add(lNameMasterField);
        panel.add(new JLabel("Имя мастера:"));
        panel.add(fNameMasterField);
        panel.add(new JLabel("Должность мастера:"));
        panel.add(postField);
        panel.add(new JLabel("Номер телефона мастера:"));
        panel.add(numberPhoneMasterField);
        panel.add(new JLabel("Фамилия клиента:"));
        panel.add(lNameClientField);
        panel.add(new JLabel("Имя клиента:"));
        panel.add(fNameClientField);
        panel.add(new JLabel("Номер телефона клиента:"));
        panel.add(numberPhoneClientField);


        while (true) {
            int result = JOptionPane.showConfirmDialog(this, panel, title,
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                MaterialConsumption item = new MaterialConsumption();

                try {
                    String countText = countField.getText().trim();
                    if (countText.isEmpty() || Double.parseDouble(countText) < 0) {
                        showError("Количество должно быть положительным числом.");
                        continue;
                    }
                    item.setCount(Double.parseDouble(countText));

                    String name = nameField.getText().trim();
                    if (name.isEmpty()) {
                        showError("Название материала не может быть пустым.");
                        continue;
                    }
                    item.getMaterial().setName(name);

                    String costText = costField.getText().trim();
                    if (costText.isEmpty() || Double.parseDouble(costText) < 0) {
                        showError("Цена материала должна быть положительным числом.");
                        continue;
                    }
                    item.getMaterial().setCost(Double.parseDouble(costText));

                    item.getMaterial().setCategories((Categories) categoryBox.getSelectedItem());

                    String priceText = priceField.getText().trim();
                    if (priceText.isEmpty() || Double.parseDouble(priceText) < 0) {
                        showError("Стоимость записи должна быть положительным числом.");
                        continue;
                    }
                    item.getRecord().setPrice(Double.parseDouble(priceText));


                    String dateText = dateField.getText().trim();
                    if (dateText.isEmpty()) {
                        showError("Дата не может быть пустой.");
                        continue;
                    }

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    try {
                        LocalDate date = LocalDate.parse(dateText, formatter);
                        item.getRecord().setDate(date);
                    } catch (DateTimeParseException e) {
                        showError("Неверный формат даты. Используйте yyyy-MM-dd.");
                        continue;
                    }

                    if (lNameMasterField.getText().trim().isEmpty()) {
                        showError("Фамилия мастера не может быть пустой.");
                        continue;
                    }
                    item.getRecord().getMaster().setLastName(lNameMasterField.getText().trim());

                    if (fNameMasterField.getText().trim().isEmpty()) {
                        showError("Имя мастера не может быть пустым.");
                        continue;
                    }
                    item.getRecord().getMaster().setFirstName(fNameMasterField.getText().trim());

                    if (numberPhoneMasterField.getText().trim().isEmpty()) {
                        showError("Номер телефона мастера не может быть пустым.");
                        continue;
                    }
                    item.getRecord().getMaster().setNumberPhone(numberPhoneMasterField.getText().trim());

                    if (lNameClientField.getText().trim().isEmpty()) {
                        showError("Фамилия клиента не может быть пустой.");
                        continue;
                    }
                    item.getRecord().getClient().setLastName(lNameClientField.getText().trim());

                    if (fNameClientField.getText().trim().isEmpty()) {
                        showError("Имя клиента не может быть пустым.");
                        continue;
                    }
                    item.getRecord().getClient().setFirstName(fNameClientField.getText().trim());

                    if (numberPhoneClientField.getText().trim().isEmpty()) {
                        showError("Номер телефона клиента не может быть пустым.");
                        continue;
                    }
                    item.getRecord().getClient().setNumberPhone(numberPhoneClientField.getText().trim());

                    return item;
                } catch (NumberFormatException e) {
                    showError("Некорректный ввод числовых значений.");
                }
            } else {
                return null;
            }
        }
    }
}
