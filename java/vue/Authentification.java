package vue;

import dao.ConnectMed;
import model.Medecin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Authentification extends  JFrame{
    private JComboBox ID;
    private JButton ConnexionCabinet;
    private JButton Quitter;
    private JPasswordField PSw;
    private JPanel Connexion;

    public Authentification()  {

        // initialisation de la liste des utilisateur de l'application
        //-----------------------------------------------------------------------------------------------------------------------------------------
        ID.addItem("Admin");
        List<Medecin> Meds = new ArrayList<>();
        ConnectMed Cnp = new ConnectMed();
        Meds = Cnp.liste_medecins();
        for (Medecin m : Meds) {
            ID.addItem(String.valueOf(m.getId()));
        }



        Quitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        ConnexionCabinet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] psw =PSw.getPassword();
                String pass = new String(psw);
                if((ID.getSelectedIndex()==0) && (pass.equals("admin")))
                {
                    javax.swing.JOptionPane.showMessageDialog(null, "Bienvenu Admin");
                    JFrame frame = new JFrame("Cabinet_Smart");
                    frame.setContentPane(new MainForm(0).panel1);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);
                    frame.setLocation(300,200);
                    frame.setPreferredSize(new Dimension(3000, 400));
                    frame.setResizable(true);

                }
                else
                {
                    javax.swing.JOptionPane.showMessageDialog(null, "Mode de passe Admin incorrecte, veuillez le resaisir ");
                }

            }
        });



    }





    // Fonction main
    //-----------------------------------------------------------------------------------------------------------------------------------------------
    public static  void main (String [] args)
    {
        JFrame frame = new JFrame("Gestion Cabinet - Connexion");
        frame.setContentPane(new Authentification().Connexion);
        frame.pack();
        frame.setVisible(true);
        frame.setLocation(500,300);
        frame.setResizable(true);

        Authentification authentification =new Authentification();
    }
    }
