import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;

/**
 * IronPulse Equipment Shop with Search
 * References: Oracle Java RowFilter (https://docs.oracle.com/javase/tutorial/uiswing/components/table.html#sorting)
 */
public class FitnessShop extends JFrame {
    private DefaultTableModel tableModel;
    private JTable equipmentTable;
    private TableRowSorter<DefaultTableModel> rowSorter;
    private ArrayList<Item> items;
    private String currentUser;

    class Item {
        String name;
        String status;
        String borrower;

        Item(String name) {
            this.name = name;
            this.status = "Available";
            this.borrower = "-";
        }
    }

    public FitnessShop(String username) {
        this.currentUser = username;
        setTitle("Sajjad Fitness Shop - Search Mode");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Color bgDark = new Color(26, 26, 26);
        Color accentGreen = new Color(0, 255, 136);

        // 1. Data Initialization
        items = new ArrayList<>();
        items.add(new Item("Dumbbell - 5kg"));
        items.add(new Item("Dumbbell - 10kg"));
        items.add(new Item("Gym Mat (Non-Slip)"));
        items.add(new Item("Abs Roller Pro"));

        // 2. Table and Sorter Setup
        String[] columnNames = {"Instrument", "Status", "Borrowed By"};
        tableModel = new DefaultTableModel(columnNames, 0);
        equipmentTable = new JTable(tableModel);

        // The RowSorter handles the actual filtering logic
        rowSorter = new TableRowSorter<>(tableModel);
        equipmentTable.setRowSorter(rowSorter);

        updateTableData();

        // 3. Search Panel
        JPanel searchPanel = new JPanel(new BorderLayout(5, 5));
        searchPanel.setBackground(bgDark);
        JLabel searchLabel = new JLabel("Search Equipment: ");
        searchLabel.setForeground(Color.WHITE);
        JTextField searchField = new JTextField();

        searchPanel.add(searchLabel, BorderLayout.WEST);
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // 4. SEARCH LOGIC (DocumentListener)
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { filter(); }
            @Override
            public void removeUpdate(DocumentEvent e) { filter(); }
            @Override
            public void changedUpdate(DocumentEvent e) { filter(); }

            private void filter() {
                String text = searchField.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null); // Show all if empty
                } else {
                    // (?i) makes the search case-insensitive
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });

        // 5. Layout Assembly
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(bgDark);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel topContainer = new JPanel(new GridLayout(2, 1));
        topContainer.setBackground(bgDark);
        JLabel welcomeLabel = new JLabel("Welcome, " + username);
        welcomeLabel.setForeground(accentGreen);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 20));

        topContainer.add(welcomeLabel);
        topContainer.add(searchPanel);

        mainPanel.add(topContainer, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(equipmentTable), BorderLayout.CENTER);

        add(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateTableData() {
        tableModel.setRowCount(0);
        for (Item i : items) {
            tableModel.addRow(new Object[]{i.name, i.status, i.borrower});
        }
    }
}