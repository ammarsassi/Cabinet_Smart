package model;



public class Medecin extends Personne {
    private Specialites specialite;

    public Medecin() {
        super();
    }

    public Specialites getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Specialites specialite) {
        this.specialite = specialite;
    }



    @Override
    public String toString()
    {
        return "Medecin{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", numtel='" + numtel + '\'' +
                "specialite=" + specialite +
                '}';


    }


}
