package dao;

import model.Medecin;
import model.Rdv;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static model.Specialites.valueOf;


public class ConnectRdv
{
    public Connection C;
    public Statement stmt;
    public ResultSet res;
    public ConnectRdv()
    {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            C= DriverManager.getConnection("jdbc:mysql://localhost:3306/cabinet?UseSSL=false&serverTimezone=UTC","root","root");

            stmt=C.createStatement();


        }catch (Exception e){
            System.out.println("erreur:Driver introuvable");
            e.printStackTrace();
        }
    }

    public void add_Rdv(Rdv rdv){
        try{
            PreparedStatement statement=C.prepareStatement(
                    "insert into rdvs(id_patient,id_medecin,jour) values(?,?,?);");

            statement.setInt(1,rdv.getPatient());
            statement.setInt(2,rdv.getMedecin());
            statement.setString(3, rdv.getJour());

            int res = statement.executeUpdate();


        }
        catch (SQLException e){
            System.out.println("erreur d'jaout");
            e.printStackTrace();
        }
    }

    public List liste_rdv(){
        List<Rdv>rdvs=new ArrayList<>();
        try {
            res=stmt.executeQuery("select * from rdvs");
            while(res.next()) {
                Rdv rdv =new Rdv();

                rdv.setPatient(res.getInt(1));
                rdv.setMedecin(res.getInt(2));
                rdv.setJour(res.getString(3));

                rdvs.add(rdv);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return rdvs;
    }

    public Rdv get_rdv(int id_p, int id_m){
        List<Rdv>rdvs=new ArrayList<>();
        try {
            res=stmt.executeQuery("select * from rdvs where id_patient="+id_p+"and id_medecin="+id_m);
            while(res.next()) {
                Rdv rdv =new Rdv();
                rdv.setPatient(res.getInt(1));
                rdv.setMedecin(res.getInt(2));
                rdv.setJour(res.getString(3));
                rdvs.add(rdv);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        if(rdvs.size()>0)
            return rdvs.get(0);
        else
            return null;
    }

    public boolean delete_rdv(int id_p, int id_m) {
        int res=0;
        try {
            PreparedStatement statement =C.prepareStatement("delete from rdvs where id_patient=? and id_medecin=?");
            statement.setInt(1,id_p);
            statement.setInt(2,id_m);
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
    public boolean edit_rdv(int id_p, int id_m, Rdv rdv)
    {
        int res=0;
        try {
            PreparedStatement statement = C.prepareStatement(
                    "update rdvs set(jour,patient,medecin) values(?,?,?) where id_patient="+id_p+"and id_medecin="+id_m);

            statement.setString(2,String.valueOf(rdv.getPatient()));
            statement.setString(3,String.valueOf(rdv.getMedecin()));
            statement.setString(1,rdv.getJour());
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
