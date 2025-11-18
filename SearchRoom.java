package Hotel.Management.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SearchRoom extends JFrame implements ActionListener {

    JCheckBox checkBox;
    Choice choice;
    JTable table;
    JButton add, back;

    SearchRoom() {

        JPanel panel = new JPanel();
        panel.setBackground(new Color(3,45,48));
        panel.setBounds(5,5,690,490);
        panel.setLayout(null);
        add(panel);

        JLabel searchForRoom = new JLabel("Search For Room");
        searchForRoom.setBounds(250,11,186,31);
        searchForRoom.setForeground(Color.WHITE);
        searchForRoom.setFont(new Font("Tahoma", Font.BOLD,20));
        panel.add(searchForRoom);

        JLabel rbt = new JLabel("Room Bed Type:");
        rbt.setBounds(50,73,120,20);
        rbt.setForeground(Color.WHITE);
        rbt.setFont(new Font("Tahoma", Font.BOLD,14));
        panel.add(rbt);

        checkBox = new JCheckBox("Only Display Available");
        checkBox.setBounds(400,69,205,23);
        checkBox.setForeground(Color.WHITE);
        checkBox.setBackground(new Color(3,45,48));
        panel.add(checkBox);

        choice = new Choice();
        choice.add("Single Bed");
        choice.add("Double Bed");
        choice.setBounds(170,70,120,20);
        panel.add(choice);


        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 187, 690, 150);
        panel.add(scrollPane);

        add = new JButton("Search");
        add.setBounds(200,400,120,30);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add.addActionListener(this);
        panel.add(add);

        back = new JButton("Back");
        back.setBounds(380,400,120,30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        panel.add(back);

        setUndecorated(true);
        setLayout(null);
        setLocation(500,200);
        setSize(700,500);
        setVisible(true);

        loadTable("SELECT * FROM room");
    }

    private void loadTable(String query) {
        try {
            confi c = new confi();
            ResultSet rs = c.s.executeQuery(query);

            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();
            String[] columnNames = new String[columnCount];
            for(int i=0; i<columnCount; i++) {
                columnNames[i] = meta.getColumnName(i+1);
            }

            rs.last();
            int rowCount = rs.getRow();
            rs.beforeFirst();
            String[][] data = new String[rowCount][columnCount];
            int r = 0;
            while(rs.next()) {
                for(int i=0; i<columnCount; i++) {
                    data[r][i] = rs.getString(i+1);
                }
                r++;
            }

            table.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == add) {
            String query;
            if(checkBox.isSelected()){
                query = "SELECT * FROM room WHERE availability='Available' AND bed_type='" + choice.getSelectedItem() + "'";
            } else {
                query = "SELECT * FROM room WHERE bed_type='" + choice.getSelectedItem() + "'";
            }
            loadTable(query);
        } else if(e.getSource() == back){
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new SearchRoom();
    }
}
