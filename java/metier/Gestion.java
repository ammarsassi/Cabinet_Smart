package metier;

import java.util.List;

public interface Gestion {
    public void add_entite(Object entite);
    public List consulte();
    public Object get_entite(Object id);
    public void delete_entite(Object id);
    public void edit_entite(Object id,Object value);

}
