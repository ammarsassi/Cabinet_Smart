package model;



public class Fiche
{
    public Fiche() {
    }
    private int id;
    private int medecin;
    private int patient;
    private String avis;
    private String liste_medc;

    public int getId(){ return id;}

    public void setId(int id)
    {
        this.id=id;
    }
    public int getMedecin() {
        return medecin;
    }

    public void setMedecin(int medecin) {
        this.medecin = medecin;
    }

    public int getPatient() {
        return patient;
    }

    public void setPatient(int patient) {
        this.patient = patient;
    }

    public String getAvis() {
        return avis;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }

    public String getListe_medc() {
        return liste_medc;
    }

    public void setListe_medc(String liste_medc) {
        this.liste_medc = liste_medc;
    }

    @Override
    public String toString() {
        return "Fiche{" +
                "identifiant="+ id +
                "medecin=" + medecin +
                ", patient=" + patient +
                ", avis='" + avis + '\'' +
                ", liste_medc='" + liste_medc + '\'' +
                '}';
    }

}
