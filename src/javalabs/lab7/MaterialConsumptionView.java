package javalabs.lab7;

import javalabs.lab7.Model.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
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
                "Название материала", "Цена,$", "Категория",
                "Дата", "Стоимость,$",
                "Фамилия мастера", "Имя мастера", "Должность", "Телефон мастера",
                "Фамилия клиента", "Имя клиента", "Телефон клиента"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Запрещает редактирование всех ячеек
            }
        };

        table = new JTable(tableModel);
        table.setRowHeight(30);
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);

        table.getTableHeader().setBackground(new Color(60, 63, 65));
        table.getTableHeader().setForeground(Color.BLACK);
        table.getTableHeader().setFont(table.getTableHeader().getFont().deriveFont(Font.BOLD));

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (isSelected) {
                    c.setBackground(table.getSelectionBackground());
                    c.setForeground(table.getSelectionForeground());
                } else {
                    c.setBackground(row % 2 == 0 ? new Color(245, 245, 245) : Color.WHITE);
                    c.setForeground(Color.BLACK);
                }
                setHorizontalAlignment(LEFT);
                return c;
            }
        });



        JScrollPane scrollPane = new JScrollPane(table);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        addButton = createStyledButton("Добавить", new Color(76, 175, 80), new Color(56, 142, 60));
        updateButton = createStyledButton("Изменить", new Color(33, 150, 243), new Color(25, 118, 210));
        deleteButton = createStyledButton("Удалить", new Color(244, 67, 54), new Color(211, 47, 47));
        saveButton = createStyledButton("Сохранить", new Color(255, 193, 7), new Color(255, 160, 0));
        loadButton = createStyledButton("Загрузить", new Color(121, 85, 72), new Color(93, 64, 55));

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JButton createStyledButton(String text, Color bgColor, Color hoverColor) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);
        button.setBackground(bgColor);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setUI(new javax.swing.plaf.basic.BasicButtonUI());

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverColor);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });

        return button;
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
                        i+1,
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

    JPanel materialPanel = new JPanel(new GridLayout(0, 2, 5, 5));
    materialPanel.setBorder(BorderFactory.createTitledBorder("Материал"));
    materialPanel.add(new JLabel("Количество:"));
    materialPanel.add(countField);
    materialPanel.add(new JLabel("Название материала:"));
    materialPanel.add(nameField);
    materialPanel.add(new JLabel("Цена ($):"));
    materialPanel.add(costField);
    materialPanel.add(new JLabel("Категория:"));
    materialPanel.add(categoryBox);

    JTextField priceField = new JTextField(defaultValue != null ? String.valueOf(defaultValue.getRecord().getPrice()) : "");
    JTextField dateField = new JTextField(defaultValue != null ? String.valueOf(defaultValue.getRecord().getDate()) : "");

    JPanel recordPanel = new JPanel(new GridLayout(0, 2, 5, 5));
    recordPanel.setBorder(BorderFactory.createTitledBorder("Запись"));
    recordPanel.add(new JLabel("Стоимость ($):"));
    recordPanel.add(priceField);
    recordPanel.add(new JLabel("Дата (yyyy-MM-dd):"));
    recordPanel.add(dateField);

    JTextField lNameMasterField = new JTextField(defaultValue != null ? defaultValue.getRecord().getMaster().getLastName() : "");
    JTextField fNameMasterField = new JTextField(defaultValue != null ? defaultValue.getRecord().getMaster().getFirstName() : "");
    JTextField postField = new JTextField(defaultValue != null ? String.valueOf(defaultValue.getRecord().getMaster().getPost()) : "");
    JTextField numberPhoneMasterField = new JTextField(defaultValue != null ? String.valueOf(defaultValue.getRecord().getMaster().getNumberPhone()) : "");

    JPanel masterPanel = new JPanel(new GridLayout(0, 2, 5, 5));
    masterPanel.setBorder(BorderFactory.createTitledBorder("Мастер"));
    masterPanel.add(new JLabel("Фамилия:"));
    masterPanel.add(lNameMasterField);
    masterPanel.add(new JLabel("Имя:"));
    masterPanel.add(fNameMasterField);
    masterPanel.add(new JLabel("Должность:"));
    masterPanel.add(postField);
    masterPanel.add(new JLabel("Телефон:"));
    masterPanel.add(numberPhoneMasterField);

    JTextField lNameClientField = new JTextField(defaultValue != null ? defaultValue.getRecord().getClient().getLastName() : "");
    JTextField fNameClientField = new JTextField(defaultValue != null ? String.valueOf(defaultValue.getRecord().getClient().getFirstName()) : "");
    JTextField numberPhoneClientField = new JTextField(defaultValue != null ? String.valueOf(defaultValue.getRecord().getClient().getNumberPhone()) : "");

    JPanel clientPanel = new JPanel(new GridLayout(0, 2, 5, 5));
    clientPanel.setBorder(BorderFactory.createTitledBorder("Клиент"));
    clientPanel.add(new JLabel("Фамилия:"));
    clientPanel.add(lNameClientField);
    clientPanel.add(new JLabel("Имя:"));
    clientPanel.add(fNameClientField);
    clientPanel.add(new JLabel("Телефон:"));
    clientPanel.add(numberPhoneClientField);

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.add(materialPanel);
    panel.add(recordPanel);
    panel.add(masterPanel);
    panel.add(clientPanel);

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
                    showError("Стоимость записи должна быть положительной.");
                    continue;
                }
                item.getRecord().setPrice(Double.parseDouble(priceText));

                String dateText = dateField.getText().trim();
                if (dateText.isEmpty()) {
                    showError("Дата не может быть пустой.");
                    continue;
                }
                try {
                    LocalDate date = LocalDate.parse(dateText, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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

                if (postField.getText().trim().isEmpty()) {
                    showError("Должность не может быть пустой.");
                    continue;
                }
                item.getRecord().getMaster().setPost(postField.getText().trim());
                if (numberPhoneMasterField.getText().trim().isEmpty()) {
                    showError("Телефон мастера не может быть пустым.");
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
                    showError("Телефон клиента не может быть пустым.");
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

