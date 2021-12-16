package temp;


import dao.ConnectPat;
import model.Patient;

import java.util.ArrayList;
import java.util.List;


public class Test {

    public static void main (String [] args)
    {
        Patient p = new Patient();
        System.out.println("Ok");
        List<Patient> listeP=new ArrayList<>();

        ConnectPat cnP = new ConnectPat();

        for(int i =0 ; i<5; i++) {
            p.setNom("fnbfjhdqzskdvqihdvzlqiydvfezuiy");
            p.setPrenom("gksgghdvdgyfvezyif");
            p.setAdresse("ffdsffgdsgrsg");
            p.setNumtel("284ez849z56666");
            cnP.add_patient(p);
        }

        listeP = cnP.liste_patient();
        for(Patient pf : listeP)
        {
            System.out.println(pf.toString());
        }
    }
}
