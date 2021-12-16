package metier;

import dao.ConnectRdv;

import model.Rdv;

import java.util.ArrayList;
import java.util.List;

public class GestionRdv implements Gestion {
    private ConnectRdv agent;
    private List<Rdv> rdvs;

    public GestionRdv() {
        this.agent = new ConnectRdv();
        this.rdvs = new ArrayList<>();

    }

    @Override
    public void add_entite(Object entite) {
        this.agent.add_Rdv((Rdv) entite);
    }

    @Override
    public List consulte() {
        return this.agent.liste_rdv();
    }

    @Override
    public Object get_entite(Object id) {

        return null;
    }

    public Object get_entite(Object id_p, Object id_m) {
        return this.agent.get_rdv((int) id_p, (int) id_m);
    }

    @Override
    public void delete_entite(Object id) {

    }

    public void delete_entite(Object id_p, Object id_m) {
        this.agent.get_rdv((int) id_p, (int) id_m);
    }

    @Override
    public void edit_entite(Object id, Object value) {

    }

    public void edit_entite(Object id_p, Object id_m, Object value) {
        this.rdvs = agent.liste_rdv();
        for (Rdv r : rdvs) {
            if ((r.getPatient() == (int) id_p) && ((r.getMedecin() == (int) id_m))) {
                this.agent.delete_rdv((int) id_p, (int) id_m);
                this.agent.add_Rdv((Rdv) value);
            }

        }
    }
}