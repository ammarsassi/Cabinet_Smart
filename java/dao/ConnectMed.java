package dao;

import model.Medecin;
import model.Specialites;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static model.Specialites.valueOf;

public class ConnectMed {
    public Connection C;
    public Statement stmt;
    public ResultSet res;
    public ConnectMed(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            C= DriverManager.getConnection("jdbc:mysql://localhost:3306/cabinet?UseSSL=false&serverTimezone=UTC","root","root");
                    stmt=C.createStatement();


        }catch (Exception e){
            System.out.println("erreur:Driver introuvable");
            e.printStackTrace();
        }
    }
    public void add_medecin(Medecin med){
        try{
            PreparedStatement statement=C.prepareStatement(
                    "insert into medecins(specialite,nom,prenom,adresse,numtel) values(?,?,?,?,?);");
            statement.setString(1,med.getSpecialite().name());
            statement.setString(2,med.getNom());
            statement.setString(3,med.getPrenom());
            statement.setString(4,med.getAdresse());
            statement.setString(5,med.getNumtel());
            int res = statement.executeUpdate();


        }
        catch (SQLException e){
            System.out.println("erreur d'ajout");
            e.printStackTrace();
        }
    }
    public List liste_medecins(){
        List<Medecin>meds=new ArrayList<>();
      try {
          res=stmt.executeQuery("select * from medecins");
          while(res.next()) {
              Medecin med =new Medecin();
              med.setId((res.getInt(1)));
              med.setSpecialite(valueOf(res.getString(2)));
              med.setNom(res.getString(3));
              med.setPrenom(res.getString(4));
              med.setAdresse(res.getString(5));
              med.setNumtel(res.getString(6));
              meds.add(med);
          }
      }catch (SQLException throwables){
          throwables.printStackTrace();
      }
      return meds;
    }

    public List get_medecin_S(Specialites specialite){
        List<Medecin>meds=new ArrayList<>();
        try {
            res=stmt.executeQuery("select * from medecins where specialite='"+specialite+"'");

            while(res.next()) {
                Medecin med =new Medecin();
                med.setId((res.getInt(1)));
                med.setSpecialite(valueOf(res.getString(2)));
                med.setNom(res.getString(3));
                med.setPrenom(res.getString(4));
                med.setAdresse(res.getString(5));
                med.setNumtel(res.getString(6));
                meds.add(med);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }

            return meds;


    }
    public List get_medecin_N(String nom){
        List<Medecin>meds=new ArrayList<>();
        try {

            res=stmt.executeQuery("select * from medecins where nom='"+nom+"'");

            while(res.next()) {

                Medecin med =new Medecin();
                med.setId((res.getInt(1)));
                med.setSpecialite(valueOf(res.getString(2)));
                med.setNom(res.getString(3));
                med.setPrenom(res.getString(4));
                med.setAdresse(res.getString(5));
                med.setNumtel(res.getString(6));
                meds.add(med);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }

         return meds;


    }
    public List get_medecin_P(String prenom){
        List<Medecin>meds=new ArrayList<>();
        try {

            res=stmt.executeQuery("select * from medecins where nom='"+prenom+"'");

            while(res.next()) {

                Medecin med =new Medecin();
                med.setId((res.getInt(1)));
                med.setSpecialite(valueOf(res.getString(2)));
                med.setNom(res.getString(3));
                med.setPrenom(res.getString(4));
                med.setAdresse(res.getString(5));
                med.setNumtel(res.getString(6));
                meds.add(med);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }

        return meds;


    }

    public List get_medecin_T(String numT){
        List<Medecin>meds=new ArrayList<>();
        try {

            res=stmt.executeQuery("select * from medecins where nom='"+numT+"'");

            while(res.next()) {

                Medecin med =new Medecin();
                med.setId((res.getInt(1)));
                med.setSpecialite(valueOf(res.getString(2)));
                med.setNom(res.getString(3));
                med.setPrenom(res.getString(4));
                med.setAdresse(res.getString(5));
                med.setNumtel(res.getString(6));
                meds.add(med);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }

        return meds;


    }
    public Medecin get_medecin(long id){
        List<Medecin>meds=new ArrayList<>();
        try {
            res=stmt.executeQuery("select * from medecins where id="+id);

            while(res.next()) {
                Medecin med =new Medecin();
                med.setId((res.getInt(1)));
                med.setSpecialite(valueOf(res.getString(2)));
                med.setNom(res.getString(3));
                med.setPrenom(res.getString(4));
                med.setAdresse(res.getString(5));
                med.setNumtel(res.getString(6));
                meds.add(med);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        if(meds.size()>0)
            return meds.get(0);
        else
            return null;
    }

    public boolean delete_medecin(int id) {
        int res=0;
        try {
            PreparedStatement statement =C.prepareStatement("delete from medecins where id=?");
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
    public boolean edit_medecin(long id, Medecin med)
    {
        int res=0;
        try {
            PreparedStatement statement = C.prepareStatement(
                    "update medecins set(specialite,nom,prenom,adresse,numtel) values(?,?,?,?,?) where id=?;");
            statement.setString(1,med.getSpecialite().name());
            statement.setString(2,med.getNom());
            statement.setString(3,med.getPrenom());
            statement.setString(4,med.getAdresse());
            statement.setString(5,med.getNumtel());
            statement.setLong(6,id);
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
