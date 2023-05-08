import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Kirst { //Kirstu klass mis tekitab Kirstu ruumi kus mängija saab valida relva või varustuse mis on tugevam kui tema endine aga tugevus on valitud suvaliselt
    private Mängija atribuudid;
    private List<Integer> kirst;
    public Kirst(Mängija atribuudid){
        this.atribuudid = atribuudid;
        kirst = new ArrayList<>();
    }

    public List<Integer> getKirst() {
        return kirst;
    }

    public void kirstust(){
        Random random = new Random();
        kirst.add(atribuudid.getRelv()+random.nextInt(3)+2);
        kirst.add(atribuudid.getRelv()+random.nextInt(3)+2);
    }
}
