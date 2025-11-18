package Hotel.Management.System;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

public class Department extends JFrame {

    DefaultTableModel model;
    JTable table;
    JTextField tfDept, tfBudget;

    Department() {

        // Panel Setup
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 700, 500);
        panel.setBackground(new Color(3, 45, 48));
        panel.setLayout(null);
        add(panel);

        // Header
        JLabel header = new JLabel("Department Management");
        header.setBounds(180, 10, 400, 30);
        header.setFont(new Font("Serif", Font.BOLD, 24));
        header.setForeground(Color.WHITE);
        panel.add(header);

        // Input Fields
        JLabel lblDept = new JLabel("Department Name:");
        lblDept.setBounds(50, 420, 140, 25);
        lblDept.setForeground(Color.WHITE);
        panel.add(lblDept);

        tfDept = new JTextField();
        tfDept.setBounds(200, 420, 150, 25);
        panel.add(tfDept);

        JLabel lblBudget = new JLabel("Budget:");
        lblBudget.setBounds(380, 420, 80, 25);
        lblBudget.setForeground(Color.WHITE);
        panel.add(lblBudget);

        tfBudget = new JTextField();
        tfBudget.setBounds(450, 420, 100, 25);
        panel.add(tfBudget);

        // Table Setup
        model = new DefaultTableModel();
        table = new JTable(model);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setForeground(Color.WHITE);
        table.setBackground(new Color(4, 45, 48));
        table.setGridColor(Color.LIGHT_GRAY);

        // Table Header
        JTableHeader th = table.getTableHeader();
        th.setFont(new Font("Arial", Font.BOLD, 15));
        th.setBackground(new Color(0, 120, 140));
        th.setForeground(Color.WHITE);

        // Center Alignment
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, center);

        // Scroll Pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 50, 670, 350);
        scrollPane.getViewport().setBackground(new Color(4, 45, 48));
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        panel.add(scrollPane);

        // Buttons
        JButton addBtn = new JButton("ADD");
        addBtn.setBounds(50, 460, 100, 30);
        addBtn.setBackground(new Color(0, 33, 200));
        addBtn.setForeground(Color.WHITE);
        addBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(addBtn);

        JButton updateBtn = new JButton("UPDATE");
        updateBtn.setBounds(180, 460, 120, 30);
        updateBtn.setBackground(new Color(0, 33, 200));
        updateBtn.setForeground(Color.WHITE);
        updateBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(updateBtn);

        JButton deleteBtn = new JButton("DELETE");
        deleteBtn.setBounds(330, 460, 120, 30);
        deleteBtn.setBackground(new Color(0, 33, 200));
        deleteBtn.setForeground(Color.WHITE);
        deleteBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(deleteBtn);

        JButton backBtn = new JButton("BACK");
        backBtn.setBounds(500, 460, 120, 30);
        backBtn.setBackground(new Color(0, 33, 200));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(backBtn);

        // Load data initially
        loadData();

        // ADD Button Action
        addBtn.addActionListener(e -> {
            String dept = tfDept.getText().trim();
            String budget = tfBudget.getText().trim();
            if (dept.isEmpty() || budget.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter all fields!");
                return;
            }
            try {
                confi c = new confi();
                String q = "INSERT INTO department (department, budget) VALUES (?, ?)";
                PreparedStatement ps = c.connection.prepareStatement(q);
                ps.setString(1, dept);
                ps.setString(2, budget);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Department added successfully!");
                loadData();
                tfDept.setText("");
                tfBudget.setText("");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // UPDATE Button Action
        updateBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Please select a department to update!");
                return;
            }
            String id = table.getValueAt(row, 0).toString(); // assuming first column is ID
            String dept = tfDept.getText().trim();
            String budget = tfBudget.getText().trim();
            if (dept.isEmpty() || budget.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter all fields!");
                return;
            }
            try {
                confi c = new confi();
                String q = "UPDATE department SET department=?, budget=? WHERE id=?";
                PreparedStatement ps = c.connection.prepareStatement(q);
                ps.setString(1, dept);
                ps.setString(2, budget);
                ps.setString(3, id);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Department updated successfully!");
                loadData();
                tfDept.setText("");
                tfBudget.setText("");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // DELETE Button Action
        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Please select a department to delete!");
                return;
            }
            String id = table.getValueAt(row, 0).toString(); // assuming first column is ID
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?");
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    confi c = new confi();
                    String q = "DELETE FROM department WHERE id=?";
                    PreparedStatement ps = c.connection.prepareStatement(q);
                    ps.setString(1, id);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Department deleted successfully!");
                    loadData();
                    tfDept.setText("");
                    tfBudget.setText("");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Table row click to load data into text fields
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                tfDept.setText(table.getValueAt(row, 1).toString()); // department name
                tfBudget.setText(table.getValueAt(row, 2).toString()); // budget
            }
        });

        // Back Button
        backBtn.addActionListener(e -> {
            setVisible(false);
            new Reception();
        });

        // Frame Settings
        setUndecorated(false);
        setLayout(null);
        setLocation(550, 150);
        setSize(700, 520);
        setVisible(true);
    }

    // Load table data from DB
    void loadData() {
        try {
            confi c = new confi();
            String q = "SELECT * FROM department";
            ResultSet rs = c.statement.executeQuery(q);

            ResultSetMetaData meta = rs.getMetaData();
            int columns = meta.getColumnCount();

            // Column Names
            Vector<String> colNames = new Vector<>();
            for (int i = 1; i <= columns; i++) {
                colNames.add(meta.getColumnName(i));
            }

            // Data Rows
            Vector<Vector<Object>> data = new Vector<>();
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= columns; i++) {
                    row.add(rs.getObject(i));
                }
                data.add(row);
            }

            model.setDataVector(data, colNames);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Department();
    }
}
