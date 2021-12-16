package metier;

import dao.ConnectMed;
import model.Medecin;


import java.util.ArrayList;
import java.util.List;

public class GestionMedecin implements Gestion{
    private ConnectMed agent;
    private  List<Medecin>medecins;

    public GestionMedecin(){
        this.agent=new ConnectMed();
        this.medecins=new ArrayList<>();
    }
    @Override
    public void add_entite(Object entite) {
        this.agent.add_medecin((Medecin)entite);

    }

    @Override
    public List consulte() {


        return this.agent.liste_medecins();
    }

    @Override
    public Object get_entite(Object id)
    {
       return this.agent.get_medecin((int)id);
        }

    @Override
    public void delete_entite(Object id) {

            this.agent.delete_medecin((int)id);

    }

    @Override
    public void edit_entite(Object id, Object value)
    {
        this.medecins=agent.liste_medecins();
        for(Medecin  m :medecins)
            if(m.getId()==(long)id){
                this.agent.delete_medecin((int)id);
                this.agent.add_medecin((Medecin) value);
            }
        }


}
