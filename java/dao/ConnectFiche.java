package dao;

import model.Fiche;
import model.Patient;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectFiche {
    public Connection C;
    public Statement stmt;
    public ResultSet res;


    public ConnectFiche() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            C = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabinet?UseSSL=false&serverTimezone=UTC", "root", "root");
            stmt = C.createStatement();


        } catch (Exception e) {
            System.out.println("erreur:Driver introuvable");
            e.printStackTrace();
        }
    }

    public void add_fiche(Fiche fiche) {
        try {
            PreparedStatement statement = C.prepareStatement(
                    "insert into fiches(id,id_patient,id_medecin,avis,medec) values(?,?,?,?,?);");
            statement.setInt(1, fiche.getId());
            statement.setInt(2, fiche.getPatient());
            statement.setInt(3, fiche.getMedecin());
            statement.setString(4, fiche.getAvis());
            statement.setString(5, fiche.getListe_medc());

            int res = statement.executeUpdate();


        } catch (SQLException e) {
            System.out.println("erreur d'aout");
            e.printStackTrace();
        }
    }

    public List liste_fiche() {
        List<Fiche> fiches = new ArrayList<>();
        try {
            res = stmt.executeQuery("select * from fiches");
            while (res.next()) {
                Fiche fiche = new Fiche();
                fiche.setId((res.getInt(1)));
                fiche.setPatient((res.getInt(2)));
                fiche.setMedecin(res.getInt(3));
                fiche.setAvis(res.getString(4));
                fiche.setListe_medc(res.getString(5));

                fiches.add(fiche);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return fiches;
    }

    public List get_fiche_N(String nomP) {
        List<Fiche> fiches = new ArrayList<>();
        ConnectPat cnP = new ConnectPat();
        List<Patient> patients;

        patients = cnP.get_patient_N(nomP);

        for (Patient p : patients)
        {

            try {
                res = stmt.executeQuery("select * from fiches where nom='"+p.getNom()+"'");
                while (res.next()) {
                    Fiche fiche = new Fiche();
                    fiche.setId((res.getInt(1)));
                    fiche.setPatient((res.getInt(2)));
                    fiche.setMedecin(res.getInt(3));
                    fiche.setAvis(res.getString(4));
                    fiche.setListe_medc(res.getString(5));

                    fiches.add(fiche);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return fiches;
    }
    public List get_fiche_P(String prenomP) {
        List<Fiche> fiches = new ArrayList<>();
        ConnectPat cnP = new ConnectPat();
        List<Patient> patients;

        patients = cnP.get_patient_P(prenomP);

        for (Patient p : patients)
        {

            try {
                res = stmt.executeQuery("select * from fiches where nom='"+p.getPrenom()+"'");
                while (res.next()) {
                    Fiche fiche = new Fiche();
                    fiche.setId((res.getInt(1)));
                    fiche.setPatient((res.getInt(2)));
                    fiche.setMedecin(res.getInt(3));
                    fiche.setAvis(res.getString(4));
                    fiche.setListe_medc(res.getString(5));

                    fiches.add(fiche);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return fiches;
    }
    public List get_fiche_T(String numtP) {
        List<Fiche> fiches = new ArrayList<>();
        ConnectPat cnP = new ConnectPat();
        List<Patient> patients;

        patients = cnP.get_patient_P(numtP);

        for (Patient p : patients)
        {

            try {
                res = stmt.executeQuery("select * from fiches where nom='"+p.getNumtel()+"'");
                while (res.next()) {
                    Fiche fiche = new Fiche();
                    fiche.setId((res.getInt(1)));
                    fiche.setPatient((res.getInt(2)));
                    fiche.setMedecin(res.getInt(3));
                    fiche.setAvis(res.getString(4));
                    fiche.setListe_medc(res.getString(5));

                    fiches.add(fiche);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return fiches;
    }


    public Fiche get_fiche(int id){
        List<Fiche> fiches=new ArrayList<>();
        try {
            res=stmt.executeQuery("select * from fiches where id_patient="+id+");");
            while(res.next()) {
                Fiche fiche =new Fiche();
                fiche.setId((res.getInt(1)));
                fiche.setPatient((res.getInt(2)));
                fiche.setMedecin(res.getInt(3));
                fiche.setAvis(res.getString(4));
                fiche.setListe_medc(res.getString(5));

                fiches.add(fiche);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        if(fiches.size()>0)
            return fiches.get(0);
        else
            return null;
    }

    public boolean delete_fiche(int id) {
        int res=0;
        try {
            PreparedStatement statement =C.prepareStatement("delete from fiches where id=?");
            statement.setInt(1,id);
            res =statement.executeUpdate();




        } catch (SQLException e) {
            System.out.println("erreur");
            e.printStackTrace();

        }
        if(res>0)
            return true;
        else
            return false;


    }
    public boolean edit_fiche(int id, Fiche fiche)
    {
        int res=0;
        try {
            PreparedStatement statement = C.prepareStatement(
                    "update fiches set(id, id_patient,id_medecin, avis, medec) values(?,?,?,?,?) where id=?;");
            statement.setInt(1,fiche.getId());
            statement.setInt(2,fiche.getPatient());
            statement.setInt(3,fiche.getMedecin());
            statement.setString(4,fiche.getAvis());
            statement.setString(5,fiche.getListe_medc());
            statement.setString(6,String.valueOf(id));
            res = statement.executeUpdate();


        } catch (SQLException e) {
            System.out.println("erreur");
            e.printStackTrace();
        }
        if(res>0)
            return true;
        else
            return false;

    }

}
