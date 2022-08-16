
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class uijava {
    private JPanel main1;
    private JLabel UI_JAVA;
    private JButton SAVETODATABASEButton;
    private JTextField txtname;
    private JTextField txtcc;
    private JTextField txtsn;
    private JTextField txtre;
    private JTextField txtrew;

    public static void main(String[] args) {
        JFrame frame = new JFrame("uijava");
        frame.setContentPane(new uijava().main1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    Connection con;
    PreparedStatement pst;
    public uijava() {
        Connect();

        SAVETODATABASEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date,name,cc,sn;
                Double re,rew,sum;

                Date dates = new Date();
                java.sql.Date sqldate = new java.sql.Date(dates.getTime());

                name = txtname.getText();
                cc = txtcc.getText();
                sn = txtsn.getText();
                re= Double.parseDouble(txtre.getText());
                rew=Double.parseDouble(txtrew.getText());

                sum=re+rew;

                try {
                    //Class.forName(com.mysql.cj.jdbc.Driver");
                    String url = "jdbc:mysql://localhost:3306/you3";
                    String username = "root";
                    String password = "Am@zon9860";
                    Connection con = DriverManager.getConnection(url, username, password);
                    String q = "insert into n(Date,Name,Credit_Card,Site_Name,Reward_1,Reward_2,Sum)values(?,?,?,?,?,?,?)";
                    PreparedStatement pst = con.prepareStatement(q);
                    //pst = con.prepareStatement("insert into n(Date,Name,Credit_Card,Site_Name,Reward_1,Reward_2,Sum)values(?,?,?,?,?,?,?)");

                    pst.setDate(1,sqldate);
                    pst.setString(2, name);
                    pst.setString(3, cc);
                    pst.setString(4, sn);
                    pst.setDouble(5,re);
                    pst.setDouble(6,rew);
                    pst.setDouble(7,sum);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Record Addedddddd!!!!");

                    txtname.setText("");
                    txtcc.setText("");
                    txtsn.setText("");
                    txtre.setText("");
                    txtrew.setText("");
                    txtname.requestFocus();
                }

                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }
            }
        });
    }
    public void Connect()
    {
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //String url = "jdbc:mysql://localhost:3306/you3";
            //String username = "root";
            //String password = "Am@zon9860";
            //Connection con = DriverManager.getConnection(url,username,password);
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/you3", "root","Am@zon9860");
            System.out.println("Success");
        }

        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

    }
}
