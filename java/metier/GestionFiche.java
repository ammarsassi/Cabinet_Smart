package metier;

import dao.ConnectFiche;
import model.Fiche;


import java.util.ArrayList;
import java.util.List;

public class GestionFiche implements Gestion{

    private ConnectFiche agent;
    private  List<Fiche>fiches;

        public GestionFiche(){
        this.agent=new ConnectFiche();
        this.fiches=new ArrayList<>();
    }
    @Override
    public void add_entite(Object entite) {
        this.agent.add_fiche((Fiche)entite);

    }

    @Override
    public List consulte() {
        return this.agent.liste_fiche();
    }

    @Override
    public Object get_entite(Object id) {
        return this.agent.get_fiche((int)id);
    }

    @Override
    public void delete_entite(Object id) {
        this.agent.delete_fiche((int)id);
    }

    @Override
    public void edit_entite(Object id, Object value) {
        this.fiches=agent.liste_fiche();
        for(Fiche  f :fiches)
            if(f.getId()==(int)id){
                this.agent.delete_fiche((int)id);
                this.agent.add_fiche((Fiche) value);
            }
    }


}
