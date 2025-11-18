package Hotel.Management.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class UpdateRoom extends JFrame {

    UpdateRoom() {

        JPanel panel = new JPanel();
        panel.setBounds(5,5,940,490);
        panel.setBackground(new Color(3,45,48));
        panel.setLayout(null);
        add(panel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/update.png"));
        Image image = imageIcon.getImage().getScaledInstance(300,300,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(500,60,300,300);
        panel.add(label);

        JLabel label1  = new JLabel("Update Room Status");
        label1.setBounds(124,11,300,25);
        label1.setFont(new Font("Tahoma", Font.BOLD, 20));
        label1.setForeground(Color.WHITE);
        panel.add(label1);

        JLabel label2  = new JLabel("Customer ID :");
        label2.setBounds(25,88,120,14);
        label2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label2.setForeground(Color.WHITE);
        panel.add(label2);

        Choice customerChoice = new Choice();
        customerChoice.setBounds(248,85,140,20);
        panel.add(customerChoice);

        try {
            confi con = new confi();
            ResultSet rs = con.s.executeQuery("SELECT * FROM customer");
            while (rs.next()) {
                customerChoice.add(rs.getString("number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel label3  = new JLabel("Room Number:");
        label3.setBounds(25,129,120,14);
        label3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label3.setForeground(Color.WHITE);
        panel.add(label3);

        JTextField roomField = new JTextField();
        roomField.setBounds(248,129,140,20);
        panel.add(roomField);

        JLabel label4  = new JLabel("Availability:");
        label4.setBounds(25,174,120,14);
        label4.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label4.setForeground(Color.WHITE);
        panel.add(label4);

        JTextField availabilityField = new JTextField();
        availabilityField.setBounds(248,174,140,20);
        panel.add(availabilityField);

        JLabel label5  = new JLabel("Clean Status:");
        label5.setBounds(25,216,120,14);
        label5.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label5.setForeground(Color.WHITE);
        panel.add(label5);

        JTextField cleanField = new JTextField();
        cleanField.setBounds(248,216,140,20);
        panel.add(cleanField);

        JButton updateBtn = new JButton("Update");
        updateBtn.setBounds(120,315,89,23);
        updateBtn.setBackground(Color.BLACK);
        updateBtn.setForeground(Color.WHITE);
        panel.add(updateBtn);

        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    confi con = new confi();
                    String room = roomField.getText();
                    String clean = cleanField.getText();

                    String query = "UPDATE room SET cleaning_status = '"+clean+"' WHERE roomnumber = '"+room+"'";
                    con.s.executeUpdate(query);

                    JOptionPane.showMessageDialog(null, "Updated Successfully");
                    setVisible(false);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(220,315,89,23);
        backBtn.setBackground(Color.BLACK);
        backBtn.setForeground(Color.WHITE);
        panel.add(backBtn);

        backBtn.addActionListener(e -> setVisible(false));

        JButton checkBtn = new JButton("Check");
        checkBtn.setBounds(60,355,89,23);
        checkBtn.setBackground(Color.BLACK);
        checkBtn.setForeground(Color.WHITE);
        panel.add(checkBtn);

        checkBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String id = customerChoice.getSelectedItem();
                String query = "SELECT * FROM customer WHERE number='" + id + "'";

                try {
                    confi con = new confi();
                    ResultSet rs = con.s.executeQuery(query);

                    if (rs.next()) {
                        roomField.setText(rs.getString("room"));
                    }

                    ResultSet rs2 = con.s.executeQuery("SELECT * FROM room WHERE roomnumber='" + roomField.getText() + "'");

                    if (rs2.next()) {
                        availabilityField.setText(rs2.getString("availability"));
                        cleanField.setText(rs2.getString("cleaning_status"));
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        setLayout(null);
        setSize(950,450);
        setLocation(400,200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new UpdateRoom();
    }
}

