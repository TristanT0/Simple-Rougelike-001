public class Vastane {//Vastase klass mille abil programm genereerib suvalise tugevusega vastased mängijaga võitlemiseks
    private int elud;
    private final int relv;
    private final int varustus;
    public Vastane (int elud, int relv,int varustus){
        this.elud = elud;
        this.relv = relv;
        this.varustus = varustus;
    }

    public void setElud(int elud) {
        this.elud = elud;
    }

    public int getElud() {
        return elud;
    }

    public int getVarustus() {
        return varustus;
    }

    public int getRelv() {
        return relv;
    }

    public int rünne(Mängija a) { //ründe meetod millega arvutab programm välja kui palju elusi kaotas mängija arvestades vastase relva tugevust ja mängija varustuse tugevust
        int tulemus = relv-a.getVarustus();
        if(tulemus < 1){
            return 1;
        } else {
            return (relv-a.getVarustus());
        }
    }
}
