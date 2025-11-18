package Hotel.Management.System;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;



public class ManagerInfo extends JFrame {
    ManagerInfo(){

        JPanel panel = new JPanel();
        panel.setBounds(0,0,1950,1090);
        panel.setBackground(new Color(3,45,48));
        panel.setLayout(null);
        add(panel);

        JTable table = new JTable();
        table.setBounds(10,34,980,450);
        table.setForeground(Color.WHITE);
        table.setBackground(new Color(3,45,48));
        panel.add(table);

        try {
            confi c = new confi();
            String q = "select * from Employee where job = 'Manager'";
            ResultSet resultSet = c.statement.executeQuery(q);

            table.setModel((TableModel) resultSet);

        }catch (Exception e ){
            e.printStackTrace();
        }

        JButton back = new JButton("BACK");
        back.setBounds(350,500,120,30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        panel.add(back);


        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });


        // COLUMN LABELS
        String[] labels = {"Name","Age","Gender","Job","Salary","Phone","Gmail"};
        int[] positions = {41,159,273,416,536,656,786};

        for(int i=0; i<labels.length; i++){
            JLabel label = new JLabel(labels[i]);
            label.setBounds(positions[i],11,50,19);
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Tahoma", Font.BOLD,14));
            panel.add(label);
        }

        setUndecorated(true);
        setLayout(null);
        setLocation(430,50);
        setSize(950,600);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ManagerInfo();
    }
}
