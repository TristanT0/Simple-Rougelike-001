public class Mängija { //Mängija klass kus saab mängija endale alguse atribuudid millega alustab ta igat mängu
    private int elud = 5;
    private int relv = 2;
    private int varustus = 1;

    public int getElud() {
        return elud;
    }

    public void setElud(int elud) {
        this.elud = elud;
    }

    public int getRelv() {
        return relv;
    }

    public void setRelv(int relv) {
        this.relv = relv;
    }

    public int getVarustus() {
        return varustus;
    }

    public void setVarustus(int varustus) {
        this.varustus = varustus;
    }

    public int rünne(Vastane a) { //ründe meetod millega arvutab programm välja kui palju elusi kaotas vastane arvestades mängija relva tugevust ja vastase varustuse tugevust
        int tulemus = relv-a.getVarustus();
        if(tulemus < 1){
            return 1;
        } else {
            return (relv-a.getVarustus());
        }
    }
}
