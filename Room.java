package Hotel.Management.System;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.ResultSet;

public class Room extends JFrame {

    Room() {

        // Panel Setup
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1000, 600);
        panel.setBackground(new Color(12, 40, 51));
        panel.setLayout(null);
        add(panel);

        // Header
        JLabel header = new JLabel("Room Information");
        header.setBounds(330, 20, 500, 40);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Serif", Font.BOLD, 32));
        panel.add(header);

        // Table Model
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);

        model.addColumn("Name");
        model.addColumn("Age");
        model.addColumn("Gender");
        model.addColumn("Job");
        model.addColumn("Badget");
        model.addColumn("Phone");
        model.addColumn("Email");

        table.setRowHeight(28);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setForeground(Color.WHITE);
        table.setBackground(new Color(25, 70, 85));
        table.setGridColor(Color.LIGHT_GRAY);

        // Table Header
        JTableHeader th = table.getTableHeader();
        th.setFont(new Font("Arial", Font.BOLD, 15));
        th.setBackground(new Color(0, 120, 140));
        th.setForeground(Color.WHITE);
        th.setOpaque(true);

        // Center Text Alignment
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, center);

        // Scroll Panel
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 100, 960, 380);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        panel.add(scrollPane);

        // Database Load
        try {
            confi c = new confi();
            String q = "SELECT * FROM employee WHERE job='Manager'";
            ResultSet rs = c.statement.executeQuery(q);

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("name"),
                        rs.getString("age"),
                        rs.getString("gender"),
                        rs.getString("job"),
                        rs.getString("Badget"),
                        rs.getString("phone"),
                        rs.getString("gmail"),

                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // ADD Button (Correct Position)
        JButton add = new JButton("ADD");
        add.setBounds(800, 520, 150, 40);
        add.setBackground(new Color(0, 33, 200));
        add.setForeground(Color.WHITE);
        add.setFont(new Font("Tahoma", Font.BOLD, 16));
        add.setFocusPainted(false);
        add.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        panel.add(add);

        // BACK Button
        JButton back = new JButton("BACK");
        back.setBounds(30, 520, 150, 40);
        back.setBackground(new Color(0, 33, 200));
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Tahoma", Font.BOLD, 16));
        back.setFocusPainted(false);
        back.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        panel.add(back);

        // Back Button Action
        back.addActionListener(e -> setVisible(false));

        // Frame Settings
        setUndecorated(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(1000, 600);
        setLocation(420, 120);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Room();
    }
}
