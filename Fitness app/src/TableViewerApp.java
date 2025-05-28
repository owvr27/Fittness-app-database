import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class TableViewerApp extends JFrame {
    private JTextField tableField;
    private JButton loadButton;
    private JTable dataTable;
    private DefaultTableModel tableModel;

    private final String DB_URL = "jdbc:mysql://localhost:3306/fitnessapp";
    private final String DB_USER = "root";
    private final String DB_PASS = "00111@sis";

    public TableViewerApp() {
        setTitle("FitnessApp - Table Viewer");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Enter table name:"));
        tableField = new JTextField(20);
        topPanel.add(tableField);
        loadButton = new JButton("Load Table");
        topPanel.add(loadButton);

        add(topPanel, BorderLayout.NORTH);


        tableModel = new DefaultTableModel();
        dataTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(dataTable);
        scrollPane.getViewport().setBackground(Color.WHITE);
        add(scrollPane, BorderLayout.CENTER);

        loadButton.addActionListener(  e -> loadTableData());
    }

    private void loadTableData() {
        String tableName = tableField.getText().trim().toLowerCase();
        if (tableName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a table name.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName)) {


            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();

            tableModel.setRowCount(0);
            tableModel.setColumnCount(0);

            for (int i = 1; i <= columnCount; i++) {
                tableModel.addColumn(meta.getColumnName(i));
            }

            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                tableModel.addRow(row);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading table: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TableViewerApp().setVisible(true));
    }
}
