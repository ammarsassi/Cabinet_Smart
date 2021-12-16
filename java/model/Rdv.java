package model;



public class Rdv {


    private int medecin;
    private int patient;
    private String jour;

    public Rdv() {
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

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    @Override
    public String toString() {
        return "Rdv{" +
                ", medecin=" + medecin +
                ", patient=" + patient +
                ", date='" + jour + '\'' +
                '}';
    }


}
