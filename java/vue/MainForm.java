package vue;

import dao.*;
import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.*;

public class MainForm {

    public JPanel panel1;
    private JTabbedPane Gestion_cabinet;
    private JComboBox Specialite_medecin;
    private JComboBox Choisir_medecin;
    private JButton Confirmer_passwrd;
    private JButton afficher_rdvs;
    private JButton Annuler_passwrd;
    private JTextField Nom_medecin;
    private JTextField Prenom_medecin;
    private JTextField Adresse_medecin;
    private JTextField Telephone_medecin;
    private JTable Liste_medecins;
    private JButton Ajouter_medecin;
    private JButton Modifier_medecin;
    private JButton Chercher_medecin;
    private JButton Supprimer_medecin;
    private JButton Rafraichir_table_medecin;
    private JButton Effacer_champs_medecin;
    private JComboBox Recherche_medecin;
    private JTable Liste_patients;
    private JTextField Nom_patient;
    private JTextField Prenom_patient;
    private JTextField Adresse_patient;
    private JTextField Telephone_patient;
    private JButton Effacer_champs_patient;
    private JButton Rafraichir_table_patient;
    private JButton Ajouter_patient;
    private JButton Modifier_patient;
    private JButton Chercher_patient;
    private JButton Supprimer_patient;
    private JComboBox Recherche_patient;
    private JTextArea Avis;
    private JTextArea Liste_medec;
    private JTable Liste_fiches;
    private JTextField Id_patient;
    private JButton Effacer_champs_fiche;
    private JButton Rafraichir_table_fiche;
    private JButton Ajouter_fiche;
    private JButton Modifier_fiche;
    private JButton Chercher_fiche;
    private JButton Supprimer_fiche;
    private JComboBox Recherche_fiche;
    private JPanel Configuration;
    private JPanel Gestion_medecins;
    private JPanel Gestion_fiches;
    private JPanel Gestion_rdvs;
    private JPanel Gestion_patients;
    private JComboBox Jour;
    private JComboBox Mois;
    private JTextField Id_p;
    private JTextField Annee;
    private JComboBox Heure;
    private JTable Liste_rdvs;
    private JButton Effacer_champs_rdv;
    private JButton Rafraichir_table_rdv;
    private JButton Ajouter_rdv;
    private JButton Modifier_rdv;
    private JButton Chercher_rdv;
    private JButton Supprimer_rdv;
    private JComboBox Recherche_rdv;
    private JPasswordField passwordM;


    ConnectPat cnP = new ConnectPat();
    List<Patient> patients = new ArrayList<>();
    Patient patient_selected;
    int num_lp;

    ConnectMed cnM = new ConnectMed();
    List<Medecin> medecins = new ArrayList<>();
    Medecin medecin_selected;
    int num_lm;

    ConnectFiche cnF = new ConnectFiche();
    List<Fiche> fiches = new ArrayList<>();
    Fiche fiche_selected;
    int num_lf;

    ConnectRdv cnR = new ConnectRdv();
    List<Rdv> rdvs = new ArrayList<>();
    Rdv rdv_selected;
    int num_lr;


    public MainForm(int i) {

        if(i==0)
        {
            this.Configuration.setEnabled(true);
            this.Gestion_medecins.setEnabled(true);
            this.Gestion_patients.setEnabled(false);
            this.Gestion_fiches.setEnabled(false);
            this.Gestion_rdvs.setEnabled(false);
        }
        else if (i==1)
        {
            this.Configuration.setEnabled(false);
            this.Gestion_medecins.setEnabled(false);
            this.Gestion_patients.setEnabled(true);
            this.Gestion_fiches.setEnabled(true);
            this.Gestion_rdvs.setEnabled(true);
        }
        // Initialisation des ComboBox : listes déroulante et chargement des tables Medecin, Patient, Fiche, Rdv
        Specialite_medecin.addItem("Spec1");
        Specialite_medecin.addItem("Spec2");
        Specialite_medecin.addItem("Spec3");

        Recherche_medecin.addItem("Recherche par Id");
        Recherche_medecin.addItem("Recherche par Spécialité");
        Recherche_medecin.addItem("Recherche par Nom");
        Recherche_medecin.addItem("Recherche par Prénom");
        Recherche_medecin.addItem("Recherche par NumTel");

        Recherche_patient.addItem("Recherche par Id");
        Recherche_patient.addItem("Recherche par Nom");
        Recherche_patient.addItem("Recherche par Prénom");
        Recherche_patient.addItem("Recherche par NumTel");

        Recherche_fiche.addItem("Recherche par Id");
        Recherche_fiche.addItem("Recherche par Nom patient");
        Recherche_fiche.addItem("Recherche par Prénom patient");
        Recherche_fiche.addItem("Recherche par NumTel patient");

        Recherche_rdv.addItem("Recherche par Id");
        Recherche_rdv.addItem("Recherche par Jour");
        Recherche_rdv.addItem("Recherche par Mois");
        Recherche_rdv.addItem("Recherche par année");

        for (int j = 1; j <= 31; j++) {
            Jour.addItem(String.valueOf(j));
        }

        Mois.addItem("Janvier");
        Mois.addItem("Fevrier");
        Mois.addItem("Mars");
        Mois.addItem("April");
        Mois.addItem("Mai");
        Mois.addItem("Juin");
        Mois.addItem("Juillet");
        Mois.addItem("Août");
        Mois.addItem("Septembre");
        Mois.addItem("Octobre");
        Mois.addItem("Novembre");
        Mois.addItem("Décembre");

        Heure.addItem("08:00");
        Heure.addItem("09:00");
        Heure.addItem("10:00");
        Heure.addItem("11:00");
        Heure.addItem("12:00");
        Heure.addItem("13:00");
        Heure.addItem("14:00");
        Heure.addItem("15:00");
        Heure.addItem("16:00");
        Heure.addItem("17:00");
        Heure.addItem("18:00");
        Heure.addItem("19:00");
        Heure.addItem("20:00");


        List<Medecin> Meds = new ArrayList<>();
        ConnectMed Cnm = new ConnectMed();
        Meds = Cnm.liste_medecins();
        for (Medecin m : Meds) {
            Choisir_medecin.addItem(String.valueOf(m.getId()));
        }

        charger_table_medecin();
        charger_table_patient();
        charger_table_fiche();
        charger_table_rdv();



        // Code source correspondant au boutton Ajouter (Medecin, Patient, Fiche, Rdv)
        //----------------------------------------------------------------------------------------------------------------------------------------------
        Ajouter_medecin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                Medecin m = new Medecin();
                m.setSpecialite(Specialites.valueOf((String) Specialite_medecin.getSelectedItem()));

                m.setNom(Nom_medecin.getText().trim());
                m.setPrenom(Prenom_medecin.getText().trim());
                m.setAdresse(Adresse_medecin.getText().trim());
                m.setNumtel(Telephone_medecin.getText().trim());

                if ((Nom_medecin.getText().length() == 0) || (Prenom_medecin.getText().length() == 0) || (Adresse_medecin.getText().length() == 0) || Telephone_medecin.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Certaines informations sont manquantes, veuillez remplir toutes les cases.");
                } else {
                    cnM.add_medecin(m);
                    JOptionPane.showMessageDialog(null, "L'ajout du medecin c'est bien exécuté");
                    charger_table_medecin();
                    supprimer_infos_medecin();
                }
            }
        });

        Ajouter_patient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                Patient p = new Patient();

                p.setNom(Nom_patient.getText().trim());
                p.setPrenom(Prenom_patient.getText().trim());
                p.setAdresse(Adresse_patient.getText().trim());
                p.setNumtel(Telephone_patient.getText().trim());

                if ((Nom_patient.getText().length() == 0) || (Prenom_patient.getText().length() == 0) || (Adresse_patient.getText().length() == 0) || Telephone_patient.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Certaines informations sont manquantes, veuillez remplir toutes les cases.");
                } else {
                    cnP.add_patient(p);
                    JOptionPane.showMessageDialog(null, "L'ajout du patient c'est bien exécuté");
                    charger_table_patient();
                    supprimer_infos_patient();
                }
            }
        });
        Ajouter_fiche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                Fiche f = new Fiche();

                f.setPatient(Integer.parseInt(Id_patient.getText().trim()));
                f.setMedecin(9); // à remplacer avec le vrai id du médecin connecté sur l'application
                f.setAvis(Avis.getText().trim());
                f.setListe_medc(Liste_medec.getText().trim());

                if ((Id_patient.getText().length() == 0) || (Avis.getText().length() == 0) || (Liste_medec.getText().length() == 0)) {
                    JOptionPane.showMessageDialog(null, "Certaines informations sont manquantes, veuillez remplir toutes les cases.");
                } else {
                    cnF.add_fiche(f);
                    JOptionPane.showMessageDialog(null, "L'ajout de la fiche patient c'est bien exécuté");
                    charger_table_fiche();
                    supprimer_infos_fiche();
                }
            }
        });

        Ajouter_rdv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                Rdv r = new Rdv();

                r.setPatient(Integer.parseInt(Id_p.getText().trim()));
                r.setMedecin(9); // à remplacer avec le vrai id du médecin connecté sur l'application


                String j = (String) Jour.getSelectedItem();
                String m = String.valueOf(Mois.getSelectedIndex() + 1);
                String a = Annee.getText();
                String h = String.valueOf(Heure.getSelectedIndex() + 8);
                String jmah = j + '-' + m + '-' + a + '-' + h;
                r.setJour(jmah);

                if ((Annee.getText().length() == 0) || (Id_p.getText().length() == 0)) {
                    JOptionPane.showMessageDialog(null, "Certaines informations sont manquantes, veuillez remplir toutes les cases.");
                } else {
                    cnR.add_Rdv(r);
                    JOptionPane.showMessageDialog(null, "L'ajout des données du Rdv patient c'est bien exécuté");
                    charger_table_rdv();
                    supprimer_infos_rdv();
                }
            }
        });

        // Code source cooresspondant au boutton Supprimer (Medecin, Patient, Fiche, Rdv)
        //------------------------------------------------------------------------------------------------------------------------------------------
        Supprimer_medecin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cnM.delete_medecin((int) medecin_selected.getId()) == true) {
                    javax.swing.JOptionPane.showMessageDialog(null, "La suppression du medecin c'est bien exécutée");
                    charger_table_medecin();
                    supprimer_infos_medecin();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(null, "La suppression du medecin a échoué");
                }
            }
        });

        Supprimer_patient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cnP.delete_patient((int) patient_selected.getId()) == true) {
                    javax.swing.JOptionPane.showMessageDialog(null, "La suppression du patient c'est bien exécutée");
                    charger_table_patient();
                    supprimer_infos_patient();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(null, "La suppression du patient a échoué");
                }
            }
        });

        Supprimer_fiche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cnF.delete_fiche(fiche_selected.getId()) == true) {
                    javax.swing.JOptionPane.showMessageDialog(null, "La suppression de la fiche patient c'est bien exécutée");
                    charger_table_fiche();
                    supprimer_infos_fiche();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(null, "La suppression de la fiche patient a échoué");
                }
            }
        });

        Supprimer_rdv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                if (cnR.delete_rdv(rdv_selected.getPatient(), 9) == true) {
                    javax.swing.JOptionPane.showMessageDialog(null, "La suppression du Rdv patient c'est bien exécutée");
                    charger_table_rdv();
                    supprimer_infos_rdv();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(null, "La suppression du Rdv patient a échoué");
                }
            }
        });

        // Code source correspondant au boutton  Modifier (Medecin, Patient, Fiche, Rdv)
        //-------------------------------------------------------------------------------------------------------------------------------------------
        Modifier_medecin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Medecin m = new Medecin();

                m.setSpecialite(Specialites.valueOf((String) Specialite_medecin.getSelectedItem()));
                m.setNom(Nom_medecin.getText().trim());
                m.setPrenom(Prenom_medecin.getText().trim());
                m.setAdresse(Adresse_medecin.getText().trim());
                m.setNumtel(Telephone_medecin.getText().trim());

                if (cnM.edit_medecin(medecin_selected.getId(), m) == true) {
                    javax.swing.JOptionPane.showMessageDialog(null, "La modification des données du medecin c'est bien exécutée");
                    charger_table_medecin();
                    supprimer_infos_medecin();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(null, "La modification des données du medecin a échoué");
                }
            }
        });

        Modifier_patient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Patient p = new Patient();

                p.setNom(Nom_patient.getText().trim());
                p.setPrenom(Prenom_patient.getText().trim());
                p.setAdresse(Adresse_patient.getText().trim());
                p.setNumtel(Telephone_patient.getText().trim());

                if (cnP.edit_patient(patient_selected.getId(), p) == true) {
                    javax.swing.JOptionPane.showMessageDialog(null, "La modification  des données du patient c'est bien exécutée");
                    charger_table_patient();
                    supprimer_infos_patient();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(null, "La modification des données du patient a échoué");
                }
            }
        });

        Modifier_fiche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Fiche f = new Fiche();
                Patient p;
                p = cnP.get_patient(fiche_selected.getPatient());

                f.setPatient((int) p.getId());
                f.setMedecin(9); // à remplacer cette valeur avec le vrai id du médecin connecté sur l'aplication
                f.setAvis(Avis.getText().trim());
                f.setListe_medc(Liste_medec.getText().trim());

                if (cnF.edit_fiche(fiche_selected.getId(), f) == true) {
                    javax.swing.JOptionPane.showMessageDialog(null, "La modification  de la fiche du patient c'est bien exécutée");
                    charger_table_fiche();
                    supprimer_infos_fiche();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(null, "La modification de la fiche du patient a échoué");
                }
            }
        });

        Modifier_rdv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Rdv r = new Rdv();

                r.setPatient(rdv_selected.getPatient());
                r.setMedecin(9); // à remplacer cette valeur avec le vrai id du médecin connecté sur l'aplication

                String j = (String) Jour.getSelectedItem();
                String m = String.valueOf(Mois.getSelectedIndex() + 1);
                String a = Annee.getText();
                String h = String.valueOf(Heure.getSelectedIndex() + 1);
                String jmah = j + '-' + m + '-' + a + '-' + h;
                r.setJour(jmah);

                if (cnR.edit_rdv(fiche_selected.getPatient(), fiche_selected.getMedecin(), r) == true) {
                    javax.swing.JOptionPane.showMessageDialog(null, "La modification  du Rdv du patient c'est bien exécutée");
                    charger_table_rdv();
                    supprimer_infos_rdv();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(null, "La modification du Rdv du patient a échoué");
                }
            }
        });

        // Code source correspondant au bouton Recherche avec options de recherche recherche par Id, Spécialité, Nom, Prénom, NuTel
        //--------------------------------------------------------------------------------------------------------------------------------------------
        Chercher_medecin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (Recherche_medecin.getSelectedIndex()) {
                    case 0: {
                        int x = Integer.parseInt(JOptionPane.showInputDialog("Donner Id du medecin recherché"));
                        Medecin m = cnM.get_medecin(x);

                        String[] title = {"Id", "Specialité", "Nom", "Prénom", "Adresse", "NumTel"};
                        DefaultTableModel model = new DefaultTableModel(null, title);
                        Object[] row = new Object[6];
                        row[0] = m.getId();
                        row[1] = m.getSpecialite();
                        row[2] = m.getNom();
                        row[3] = m.getPrenom();
                        row[4] = m.getAdresse();
                        row[5] = m.getNumtel();
                        model.addRow(row);

                        Liste_medecins.setModel(model);
                    }
                    break;

                    case 1: {
                        List<Medecin> meds;

                        String s = JOptionPane.showInputDialog("Donner Spécialité du medecin recherché");
                        meds = cnM.get_medecin_S(Specialites.valueOf(s));
                        String[] title = {"Id", "Specialité", "Nom", "Prénom", "Adresse", "NumTel"};
                        DefaultTableModel model = new DefaultTableModel(null, title);
                        Object[] row = new Object[6];


                        for (Medecin m : meds) {
                            row[0] = m.getId();
                            row[1] = m.getSpecialite();
                            row[2] = m.getNom();
                            row[3] = m.getPrenom();
                            row[4] = m.getAdresse();
                            row[5] = m.getNumtel();
                            model.addRow(row);

                            Liste_medecins.setModel(model);
                        }
                    }
                    break;

                    case 2: {
                        String n = JOptionPane.showInputDialog("Donner Nom du medecin recherché");
                        String[] title = {"Id", "Specialité", "Nom", "Prénom", "Adresse", "NumTel"};
                        DefaultTableModel model = new DefaultTableModel(null, title);
                        Object[] row = new Object[6];
                        List<Medecin> meds;
                        meds = cnM.get_medecin_N(n);

                        for (Medecin m : meds) {
                            row[0] = m.getId();
                            row[1] = m.getSpecialite();
                            row[2] = m.getNom();
                            row[3] = m.getPrenom();
                            row[4] = m.getAdresse();
                            row[5] = m.getNumtel();
                            model.addRow(row);

                            Liste_medecins.setModel(model);
                        }
                    }
                    break;

                    case 3: {
                        String p = JOptionPane.showInputDialog("Donner Prénom du medecin recherché");
                        String[] title = {"Id", "Specialité", "Nom", "Prénom", "Adresse", "NumTel"};
                        DefaultTableModel model = new DefaultTableModel(null, title);
                        Object[] row = new Object[6];
                        List<Medecin> meds;
                        meds = cnM.get_medecin_P(p);

                        for (Medecin m : meds) {
                            row[0] = m.getId();
                            row[1] = m.getSpecialite();
                            row[2] = m.getNom();
                            row[3] = m.getPrenom();
                            row[4] = m.getAdresse();
                            row[5] = m.getNumtel();
                            model.addRow(row);

                            Liste_medecins.setModel(model);
                        }
                    }
                    break;
                    case 4: {
                        String t = JOptionPane.showInputDialog("Donner Numéro Téléphone du medecin recherché");
                        String[] title = {"Id", "Specialité", "Nom", "Prénom", "Adresse", "NumTel"};
                        DefaultTableModel model = new DefaultTableModel(null, title);
                        Object[] row = new Object[6];
                        List<Medecin> meds;
                        meds = cnM.get_medecin_T(t);

                        for (Medecin m : meds) {
                            row[0] = m.getId();
                            row[1] = m.getSpecialite();
                            row[2] = m.getNom();
                            row[3] = m.getPrenom();
                            row[4] = m.getAdresse();
                            row[5] = m.getNumtel();
                            model.addRow(row);

                            Liste_medecins.setModel(model);
                        }
                    }
                    break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + (int) Recherche_medecin.getSelectedItem());
                }
            }
        });

        Chercher_patient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (Recherche_patient.getSelectedIndex()) {
                    case 0: {
                        int x = Integer.parseInt(JOptionPane.showInputDialog("Donner Id du patient recherché"));
                        Patient p = cnP.get_patient(x);

                        String[] title = {"Id", "Nom", "Prénom", "Adresse", "NumTel"};
                        DefaultTableModel model = new DefaultTableModel(null, title);
                        Object[] row = new Object[5];
                        row[0] = p.getId();
                        row[1] = p.getNom();
                        row[2] = p.getPrenom();
                        row[3] = p.getAdresse();
                        row[4] = p.getNumtel();
                        model.addRow(row);

                        Liste_patients.setModel(model);
                    }
                    break;

                    case 1: {
                        String n = JOptionPane.showInputDialog("Donner Nom du patient recherché");
                        String[] title = {"Id", "Nom", "Prénom", "Adresse", "NumTel"};
                        DefaultTableModel model = new DefaultTableModel(null, title);
                        Object[] row = new Object[5];
                        List<Patient> patients;
                        patients = cnP.get_patient_N(n);

                        for (Patient p : patients) {
                            row[0] = p.getId();
                            row[1] = p.getNom();
                            row[2] = p.getPrenom();
                            row[3] = p.getAdresse();
                            row[4] = p.getNumtel();
                            model.addRow(row);

                            Liste_patients.setModel(model);
                        }
                    }
                    break;

                    case 2: {
                        String pr = JOptionPane.showInputDialog("Donner Prénom du patient recherché");
                        String[] title = {"Id", "Nom", "Prénom", "Adresse", "NumTel"};
                        DefaultTableModel model = new DefaultTableModel(null, title);
                        Object[] row = new Object[5];
                        List<Patient> patients;
                        patients = cnP.get_patient_P(pr);

                        for (Patient p : patients) {
                            row[0] = p.getId();
                            row[1] = p.getNom();
                            row[2] = p.getPrenom();
                            row[3] = p.getAdresse();
                            row[4] = p.getNumtel();
                            model.addRow(row);

                            Liste_patients.setModel(model);
                        }
                    }
                    break;
                    case 3: {
                        String t = JOptionPane.showInputDialog("Donner Numéro Téléphone du patient recherché");
                        String[] title = {"Id", "Nom", "Prénom", "Adresse", "NumTel"};
                        DefaultTableModel model = new DefaultTableModel(null, title);
                        Object[] row = new Object[5];
                        List<Patient> patients;
                        patients = cnP.get_patient_T(t);

                        for (Patient p : patients) {
                            row[0] = p.getId();
                            row[1] = p.getNom();
                            row[2] = p.getPrenom();
                            row[3] = p.getAdresse();
                            row[4] = p.getNumtel();
                            model.addRow(row);

                            Liste_patients.setModel(model);
                        }
                    }
                    break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + (int) Recherche_medecin.getSelectedItem());
                }
            }
        });

        Chercher_fiche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (Recherche_fiche.getSelectedIndex()) {
                    case 0: {
                        int x = Integer.parseInt(JOptionPane.showInputDialog("Donner Id fiche recherchée"));
                        Fiche f = cnF.get_fiche(x);
                        Patient p = cnP.get_patient(f.getPatient());

                        String[] title = {"Id", "Nom patient", "Prénom patient", "Avis", "Liste médicaments"};
                        DefaultTableModel model = new DefaultTableModel(null, title);
                        Object[] row = new Object[5];
                        row[0] = f.getId();
                        row[1] = p.getNom();
                        row[2] = p.getPrenom();
                        row[3] = f.getAvis();
                        row[4] = f.getListe_medc();
                        model.addRow(row);

                        Liste_fiches.setModel(model);
                    }
                    break;

                    case 1: {
                        String n = JOptionPane.showInputDialog("Donner Nom du patient recherché");
                        String[] title = {"Id", "Nom patient", "Prénom patient", "Avis", "Liste médicaments"};
                        DefaultTableModel model = new DefaultTableModel(null, title);
                        Object[] row = new Object[5];
                        List<Fiche> fiches;
                        fiches = cnF.get_fiche_N(n);

                        for (Fiche f : fiches) {
                            Patient p = cnP.get_patient(f.getPatient());
                            row[0] = f.getId();
                            row[1] = p.getNom();
                            row[2] = p.getPrenom();
                            row[3] = f.getAvis();
                            row[4] = f.getListe_medc();
                            model.addRow(row);

                            Liste_fiches.setModel(model);
                        }
                    }
                    break;

                    case 2: {
                        String pr = JOptionPane.showInputDialog("Donner Prénom du patient recherché");
                        String[] title = {"Id", "Nom patient", "Prénom patient", "Avis", "Liste médicaments"};
                        DefaultTableModel model = new DefaultTableModel(null, title);
                        Object[] row = new Object[5];
                        List<Fiche> fiches;
                        fiches = cnF.get_fiche_P(pr);

                        for (Fiche f : fiches) {
                            Patient p = cnP.get_patient(f.getPatient());
                            row[0] = f.getId();
                            row[1] = p.getNom();
                            row[2] = p.getPrenom();
                            row[3] = f.getAvis();
                            row[4] = f.getListe_medc();
                            model.addRow(row);

                            Liste_fiches.setModel(model);
                        }
                    }
                    break;
                    case 3: {
                        String t = JOptionPane.showInputDialog("Donner Numéro Téléphone du patient recherché");
                        String[] title = {"Id", "Nom patient", "Prénom patient", "Avis", "Liste médicaments"};
                        DefaultTableModel model = new DefaultTableModel(null, title);
                        Object[] row = new Object[5];
                        List<Fiche> fiches;
                        fiches = cnF.get_fiche_T(t);

                        for (Fiche f : fiches) {
                            Patient p = cnP.get_patient(f.getPatient());
                            row[0] = f.getId();
                            row[1] = p.getNom();
                            row[2] = p.getPrenom();
                            row[3] = f.getAvis();
                            row[4] = f.getListe_medc();
                            model.addRow(row);

                            Liste_fiches.setModel(model);
                        }
                    }
                    break;

                    default:
                        throw new IllegalStateException("Unexpected value: " + (int) Recherche_medecin.getSelectedItem());

                }

            }

        });

        Chercher_rdv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                switch (Recherche_rdv.getSelectedIndex()) {

                    case 0: {
                        int x = Integer.parseInt(JOptionPane.showInputDialog("Donner Id patient recherché"));
                        Rdv r = cnR.get_rdv(x,9);
                        Patient p = cnP.get_patient(r.getPatient());

                        String[] title = {"Nom patient", "Prénom patient", "Date et Heure prochain(s) Rdv", "Num téléphone"};
                        DefaultTableModel model = new DefaultTableModel(null, title);
                        Object[] row = new Object[4];
                        row[0] = p.getNom();
                        row[1] = p.getPrenom();
                        row[2] = r.getJour();
                        row[3] = p.getNumtel();
                        model.addRow(row);
                        Liste_rdvs.setModel(model);
                    }
                    break;

                    case 1: {
                        String n = JOptionPane.showInputDialog("Donner le jour recherché du mois courant");
                        String[] title = {"Id", "Nom patient", "Prénom patient", "Avis", "Liste médicaments"};
                        DefaultTableModel model = new DefaultTableModel(null, title);
                        Object[] row = new Object[5];
                        List<Fiche> fiches;
                        fiches = cnF.get_fiche_N(n);

                        for (Fiche f : fiches) {
                            Patient p = cnP.get_patient(f.getPatient());
                            row[0] = f.getId();
                            row[1] = p.getNom();
                            row[2] = p.getPrenom();
                            row[3] = f.getAvis();
                            row[4] = f.getListe_medc();
                            model.addRow(row);

                            Liste_fiches.setModel(model);
                        }
                    }
                    break;

                    case 2: {
                        String pr = JOptionPane.showInputDialog("Donner Prénom du patient recherché");
                        String[] title = {"Id", "Nom patient", "Prénom patient", "Avis", "Liste médicaments"};
                        DefaultTableModel model = new DefaultTableModel(null, title);
                        Object[] row = new Object[5];
                        List<Fiche> fiches;
                        fiches = cnF.get_fiche_P(pr);

                        for (Fiche f : fiches) {
                            Patient p = cnP.get_patient(f.getPatient());
                            row[0] = f.getId();
                            row[1] = p.getNom();
                            row[2] = p.getPrenom();
                            row[3] = f.getAvis();
                            row[4] = f.getListe_medc();
                            model.addRow(row);

                            Liste_fiches.setModel(model);
                        }
                    }
                    break;
                    case 3: {
                        String t = JOptionPane.showInputDialog("Donner Numéro Téléphone du patient recherché");
                        String[] title = {"Id", "Nom patient", "Prénom patient", "Avis", "Liste médicaments"};
                        DefaultTableModel model = new DefaultTableModel(null, title);
                        Object[] row = new Object[5];
                        List<Fiche> fiches;
                        fiches = cnF.get_fiche_T(t);

                        for (Fiche f : fiches) {
                            Patient p = cnP.get_patient(f.getPatient());
                            row[0] = f.getId();
                            row[1] = p.getNom();
                            row[2] = p.getPrenom();
                            row[3] = f.getAvis();
                            row[4] = f.getListe_medc();
                            model.addRow(row);

                            Liste_fiches.setModel(model);
                        }
                    }
                    break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + (int) Recherche_medecin.getSelectedItem());

                }
                 */
            }

        });

        //Code source du boutton Rafraichir table (Medecin, Patient, Fiche, Rdv)
        //---------------------------------------------------------------------------------------------------------------------------------------
        Rafraichir_table_medecin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                charger_table_medecin();
            }
        });

        Rafraichir_table_patient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                charger_table_patient();
            }
        });

        Rafraichir_table_fiche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                charger_table_fiche();
            }
        });

        Rafraichir_table_rdv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                charger_table_rdv();
            }
        });

        //Code source du boutton Effacer Infos(Medecin, Patient, Fiche, Rdv)
        //-------------------------------------------------------------------------------------------------------------------------------------------
        Effacer_champs_medecin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                supprimer_infos_medecin();
            }
        });

        Effacer_champs_patient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                supprimer_infos_patient();
            }
        });
        Effacer_champs_fiche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                supprimer_infos_fiche();
            }
        });

        Effacer_champs_rdv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                supprimer_infos_rdv();
            }
        });

        //Code source de l'événement correspondant à la sélection d'un enregistrement dans les tables Medecin, Patient, Fiche, Rdv
        //-------------------------------------------------------------------------------------------------------------------------------------------
        Liste_medecins.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                num_lm = Liste_medecins.getSelectedRow();
                handle_medecin(num_lm);
            }
        });

        Liste_patients.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                num_lp = Liste_patients.getSelectedRow();
                handle_patient(num_lp);
            }
        });

        Liste_fiches.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                num_lf = Liste_fiches.getSelectedRow();
                handle_fiche(num_lf);
            }
        });

        Liste_rdvs.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                num_lr = Liste_rdvs.getSelectedRow();
                handle_rdv(num_lr);
            }
        });

        // Code source de la page configuration "attribution des mots de passes pour les médecins qui vont utiliser cette application
        //----------------------------------------------------------------------------------------------------------------------------------------------
        Confirmer_passwrd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (passwordM.getPassword().length==0) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Le champs Password est vide, veuillez ajouter un mot de passe ");
                } else {
                    int idM = Integer.parseInt((String) Choisir_medecin.getSelectedItem());
                    char[] psw =passwordM.getPassword();
                    String pass = new String(psw);
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection C = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabinet?UseSSL=false&serverTimezone=UTC", "root", "root");
                        Statement stmt = C.createStatement();


                        PreparedStatement statement = C.prepareStatement(
                                "insert into passwords(id_medecin, password) values(?,?);");
                        statement.setInt(1, idM);
                        statement.setString(2, pass);

                        int res = statement.executeUpdate();
                        javax.swing.JOptionPane.showMessageDialog(null, "Mode de passe enregistré avec succès ");
                        passwordM.setText("");
                        Choisir_medecin.setSelectedIndex(0);


                    } catch (Exception ex) {
                        System.out.println("erreur:Driver introuvable");
                        ex.printStackTrace();
                    }


                }
            }
        });

        Annuler_passwrd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Choisir_medecin.setSelectedIndex(0);
                passwordM.setText("");
            }
        });

    }
            // Fonctions utiles aux traitements des événements correspondants aux bouttons et autres événements
            //---------------------------------------------------------------------------------------------------------------------------------------------
            public void charger_table_medecin() {
                String[] title = {"Id", "Specialité", "Nom", "Prénom", "Adresse", "NumTel"};
                DefaultTableModel model = new DefaultTableModel(null, title);
                Object[] row = new Object[6];
                medecins = cnM.liste_medecins();

                for (Medecin m : medecins) {
                    row[0] = m.getId();
                    row[1] = m.getSpecialite();
                    row[2] = m.getNom();
                    row[3] = m.getPrenom();
                    row[4] = m.getAdresse();
                    row[5] = m.getNumtel();
                    model.addRow(row);
                    Liste_medecins.setModel(model);
                }
            }

            public void charger_table_patient() {
                String[] title = {"Id", "Nom", "Prénom", "Adresse", "NumTel"};
                DefaultTableModel model = new DefaultTableModel(null, title);
                Object[] row = new Object[5];
                patients = cnP.liste_patient();

                for (Patient p : patients) {
                    row[0] = p.getId();
                    row[1] = p.getNom();
                    row[2] = p.getPrenom();
                    row[3] = p.getAdresse();
                    row[4] = p.getNumtel();
                    model.addRow(row);
                    Liste_patients.setModel(model);
                }
            }

            public void charger_table_fiche() {
                String[] title = {"Id", "Nom patient", "Prénom patient", "Avis", "Liste médicaments"};
                DefaultTableModel model = new DefaultTableModel(null, title);
                Object[] row = new Object[5];
                fiches = cnF.liste_fiche();

                for (Fiche f : fiches) {
                    Patient p;
                    p = cnP.get_patient(f.getPatient());

                    row[0] = f.getId();
                    row[1] = p.getNom();
                    row[2] = p.getPrenom();
                    row[3] = f.getAvis();
                    row[4] = f.getListe_medc();
                    model.addRow(row);
                    Liste_fiches.setModel(model);
                }
            }

            public void charger_table_rdv() {
                String[] title = {"Nom patient", "Prénom patient", "Date et Heure prochain(s) Rdv", "Num téléphone"};
                DefaultTableModel model = new DefaultTableModel(null, title);
                Object[] row = new Object[5];
                rdvs = cnR.liste_rdv();

                for (Rdv r : rdvs) {
                    Patient p;
                    p = cnP.get_patient(r.getPatient());

                    row[0] = p.getNom();
                    row[1] = p.getPrenom();
                    row[2] = r.getJour();
                    row[3] = p.getNumtel();
                    model.addRow(row);
                    Liste_rdvs.setModel(model);
                }
            }

            //----------------------------------------------------------------------------------------------------------------------------------------
            public void supprimer_infos_medecin() {
                Nom_medecin.setText("");
                Prenom_medecin.setText("");
                Adresse_medecin.setText("");
                Telephone_medecin.setText("");
                Specialite_medecin.setSelectedIndex(0);
            }

            public void supprimer_infos_patient() {
                Nom_patient.setText("");
                Prenom_patient.setText("");
                Adresse_patient.setText("");
                Telephone_patient.setText("");
            }

            public void supprimer_infos_fiche() {
                Id_patient.setText("");
                Avis.setText("");
                Liste_medec.setText("");
            }

            public void supprimer_infos_rdv() {
                Id_p.setText("");
                Jour.setSelectedIndex(0);
                Mois.setSelectedIndex(0);
                Annee.setText("");
                Heure.setSelectedIndex(0);
            }

            //-----------------------------------------------------------------------------------------------------------------------------------------
            public void handle_medecin(int pos) {
                Medecin m = medecins.get(pos);
                medecin_selected = m;
                int x = 0;
                if (m.getSpecialite() == Specialites.valueOf("Spec1"))
                    x = 0;
                else if (m.getSpecialite() == Specialites.valueOf("Spec2"))
                    x = 1;
                else if (m.getSpecialite() == Specialites.valueOf("Spec3"))
                    x = 2;

                Specialite_medecin.setSelectedIndex(x);
                Nom_medecin.setText(m.getNom());
                Prenom_medecin.setText(m.getPrenom());
                Adresse_medecin.setText(m.getAdresse());
                Telephone_medecin.setText(m.getNumtel());
            }

            public void handle_patient(int pos) {
                Patient p = patients.get(pos);
                patient_selected = p;

                Nom_patient.setText(p.getNom());
                Prenom_patient.setText(p.getPrenom());
                Adresse_patient.setText(p.getAdresse());
                Telephone_patient.setText(p.getNumtel());
            }

            public void handle_fiche(int pos) {
                Fiche f = fiches.get(pos);
                fiche_selected = f;

                Id_patient.setText(String.valueOf(f.getPatient()));
                Avis.setText(f.getAvis());
                Liste_medec.setText(f.getListe_medc());
            }

            public void handle_rdv(int pos) {
                Rdv r = rdvs.get(pos);
                rdv_selected = r;

                Id_p.setText(String.valueOf(r.getPatient()));
                String date = r.getJour();
                String[] jmah = date.split("-", 4);

                Jour.setSelectedIndex(Integer.parseInt(jmah[0]) - 1);
                Mois.setSelectedIndex(Integer.parseInt(jmah[1]) - 1);
                Annee.setText(jmah[2]);
                Heure.setSelectedIndex(Integer.parseInt(jmah[3]) - 1);

            }

    }


