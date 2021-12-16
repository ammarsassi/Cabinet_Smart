package metier;

import dao.ConnectPat;
import model.Patient;

import java.util.ArrayList;
import java.util.List;

public class GestionPersonne implements Gestion
{
    private ConnectPat agent;
    private  List<Patient>patients;


public GestionPersonne(){
    this.agent=new ConnectPat();
    this.patients=new ArrayList<>();
}
    @Override
    public void add_entite(Object entite) {
        this.agent.add_patient((Patient)entite);
    }

    @Override
    public List consulte() {
        return this.agent.liste_patient();
    }

    @Override
    public Object get_entite(Object id) {
        return this.agent.get_patient((long)id);
    }

    @Override
    public void delete_entite(Object id) {

            this.agent.delete_patient((int)id);
    }

    @Override
    public void edit_entite(Object id, Object value) {
        this.patients=agent.liste_patient();
        for(Patient p:patients){
            if(p.getId()==(int)id){
                this.agent.delete_patient((int)id);
                this.agent.add_patient((Patient) value);
        }
    }
    }
}
