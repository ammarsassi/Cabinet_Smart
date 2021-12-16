package dao;

import model.Medecin;
import model.Patient;
import model.Personne;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static model.Specialites.valueOf;

public class ConnectPat {
    public Connection C;
    public Statement stmt;
    public ResultSet res;

    public ConnectPat() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            C = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabinet?UseSSL=false&serverTimezone=UTC", "root", "root");
            stmt = C.createStatement();


        } catch (Exception e) {
            System.out.println("erreur:Driver introuvable");
            e.printStackTrace();
        }
    }

    public void add_patient(Patient p) {
        try {
            PreparedStatement statement = C.prepareStatement(
                    "insert into patients(nom,prenom,adresse,numtel) values(?,?,?,?);");
            statement.setString(1, p.getNom());
            statement.setString(2, p.getPrenom());
            statement.setString(3, p.getAdresse());
            statement.setString(4, p.getNumtel());
            int res = statement.executeUpdate();


        } catch (SQLException e) {
            System.out.println("erreur d'ajout");
            e.printStackTrace();
        }
    }

    public List liste_patient() {
        List<Patient> patients = new ArrayList<>();
        try {
            res = stmt.executeQuery("select * from patients");
            while (res.next()) {
                Patient p = new Patient();
                p.setId((res.getInt(1)));
                p.setNom(res.getString(2));
                p.setPrenom(res.getString(3));
                p.setAdresse(res.getString(4));
                p.setNumtel(res.getString(5));
                patients.add(p);
            }
        } catch (SQLException e) {
            System.out.println("erreur");
            e.printStackTrace();
        }
        return patients;
    }

    public List get_patient_N(String nom){
        List<Patient> patients=new ArrayList<>();
        try {

            res=stmt.executeQuery("select * from patients where nom='"+nom+"'");

            while(res.next()) {

                Patient patient =new Patient();
                patient.setId((res.getInt(1)));
                patient.setNom(res.getString(2));
                patient.setPrenom(res.getString(3));
                patient.setAdresse(res.getString(4));
                patient.setNumtel(res.getString(5));
                patients.add(patient);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }

        return patients;


    }
    public List get_patient_P(String prenom){
        List<Patient> patients=new ArrayList<>();
        try {

            res=stmt.executeQuery("select * from patients where nom='"+prenom+"'");

            while(res.next()) {

                Patient patient =new Patient();
                patient.setId((res.getInt(1)));
                patient.setNom(res.getString(2));
                patient.setPrenom(res.getString(3));
                patient.setAdresse(res.getString(4));
                patient.setNumtel(res.getString(5));
                patients.add(patient);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }

        return patients;


    }

    public List get_patient_T(String numT){
        List<Patient> patients=new ArrayList<>();
        try {

            res=stmt.executeQuery("select * from patients where nom='"+numT+"'");

            while(res.next()) {

                Patient patient =new Patient();
                patient.setId((res.getInt(1)));
                patient.setNom(res.getString(2));
                patient.setPrenom(res.getString(3));
                patient.setAdresse(res.getString(4));
                patient.setNumtel(res.getString(5));
                patients.add(patient);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }

        return patients;


    }
    public Patient get_patient(long id)
    {
        List<Patient> patients = new ArrayList<>();
        try {
            res = stmt.executeQuery("select * from patients where id="+ id);
            while (res.next()) {
                Patient p = new Patient();
                p.setId((res.getInt(1)));
                p.setNom(res.getString(2));
                p.setPrenom(res.getString(3));
                p.setAdresse(res.getString(4));
                p.setNumtel(res.getString(5));
                patients.add(p);
            }
        } catch (SQLException e) {
            System.out.println("erreur");
            e.printStackTrace();
        }
        if (patients.size()>0)
            return patients.get(0);
        else
            return null;
    }

    public boolean delete_patient(long id) {
        int res=0;
        try {
            PreparedStatement statement =C.prepareStatement("delete from patients where id=?");
            statement.setLong(1,id);
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
    public boolean edit_patient(long id, Patient p)
    {
        int res=0;
        try {
            PreparedStatement statement = C.prepareStatement(
                    "update patients set(nom,prenom,adresse,numtel) values(?,?,?,?) where id=?;");
            statement.setString(1, p.getNom());
            statement.setString(2, p.getPrenom());
            statement.setString(3, p.getAdresse());
            statement.setString(4, p.getNumtel());
            statement.setLong(5, id);
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
