package Hotel.Management.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateCheck extends JFrame {

    public UpdateCheck() {

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 940, 490);
        panel.setBackground(new Color(3, 45, 48));
        panel.setLayout(null);
        add(panel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/updated.png"));
        Image image = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        JLabel label = new JLabel(new ImageIcon(image));
        label.setBounds(500, 60, 300, 300);
        panel.add(label);

        JLabel label1 = new JLabel("Check-In Details");
        label1.setBounds(124, 11, 222, 25);
        label1.setFont(new Font("Tahoma", Font.BOLD, 20));
        label1.setForeground(Color.WHITE);
        panel.add(label1);

        JLabel label2 = new JLabel("ID :");
        label2.setBounds(25, 88, 46, 14);
        label2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label2.setForeground(Color.WHITE);
        panel.add(label2);

        Choice idChoice = new Choice();
        idChoice.setBounds(248, 85, 140, 20);
        panel.add(idChoice);

        try {
            confi C = new confi();
            ResultSet rs = C.statement.executeQuery("SELECT number FROM customer");
            while (rs.next()) {
                idChoice.add(rs.getString("number"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel label3 = new JLabel("Room Number :");
        label3.setBounds(25, 129, 120, 14);
        label3.setForeground(Color.WHITE);
        panel.add(label3);

        JTextField textRoom = new JTextField();
        textRoom.setBounds(248, 129, 140, 20);
        panel.add(textRoom);

        JLabel label4 = new JLabel("Name :");
        label4.setBounds(25, 174, 97, 14);
        label4.setForeground(Color.WHITE);
        panel.add(label4);

        JTextField textName = new JTextField();
        textName.setBounds(248, 174, 140, 20);
        panel.add(textName);

        JLabel label5 = new JLabel("Checked-in :");
        label5.setBounds(25, 216, 100, 14);
        label5.setForeground(Color.WHITE);
        panel.add(label5);

        JTextField textCheckIn = new JTextField();
        textCheckIn.setBounds(248, 216, 140, 20);
        panel.add(textCheckIn);

        JLabel label6 = new JLabel("Amount Paid (Rs) :");
        label6.setBounds(25, 261, 150, 14);
        label6.setForeground(Color.WHITE);
        panel.add(label6);

        JTextField textPaid = new JTextField();
        textPaid.setBounds(248, 261, 140, 20);
        panel.add(textPaid);

        JLabel label7 = new JLabel("Pending Amount (Rs) :");
        label7.setBounds(25, 302, 150, 14);
        label7.setForeground(Color.WHITE);
        panel.add(label7);

        JTextField textPending = new JTextField();
        textPending.setBounds(248, 302, 140, 20);
        panel.add(textPending);

        JButton update = new JButton("Update");
        update.setBounds(56, 378, 89, 23);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        panel.add(update);

        update.addActionListener(e -> {
            try {
                confi C = new confi();

                String id = idChoice.getSelectedItem();
                String room = textRoom.getText();
                String name = textName.getText();
                String check = textCheckIn.getText();
                String paid = textPaid.getText();

                String query = "UPDATE customer SET room='" + room + "', name='" + name +
                        "', checkintime='" + check + "', deposit='" + paid + "' WHERE number='" + id + "'";

                C.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Updated Successfully!");
                setVisible(false);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        JButton back = new JButton("Back");
        back.setBounds(168, 378, 89, 23);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        panel.add(back);

        back.addActionListener(e -> setVisible(false));

        JButton check = new JButton("Check");
        check.setBounds(281, 378, 89, 23);
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        panel.add(check);

        check.addActionListener(e -> {
            try {
                String id = idChoice.getSelectedItem();

                confi C = new confi();

                ResultSet rs = C.statement.executeQuery("SELECT * FROM customer WHERE number='" + id + "'");
                if (rs.next()) {
                    textRoom.setText(rs.getString("room"));
                    textName.setText(rs.getString("name"));
                    textCheckIn.setText(rs.getString("checkintime"));
                    textPaid.setText(rs.getString("deposit"));
                }

                ResultSet rs2 = C.statement.executeQuery(
                        "SELECT price FROM room WHERE roomnumber='" + textRoom.getText() + "'");

                if (rs2.next()) {
                    int price = Integer.parseInt(rs2.getString("price"));
                    int paid = Integer.parseInt(textPaid.getText());
                    textPending.setText("" + (price - paid));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        setLayout(null);
        setSize(950, 500);
        setLocation(400, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new UpdateRoom();
    }
}