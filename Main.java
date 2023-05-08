import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static List<Object> generaator(){ //Generaator teeb mängimiseks kaardi ehk see teeb valmis järjendi kus on suvaliste atribuutidega vastased
        List<Object> mäng = new ArrayList<Object>();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            if(i<4){ //Enne kirstu ruumi mis toimub neljandas ruumis on nõrgemad vastased kui pärast kirstu sest kirstust saab tugevama relva või varustuse
                int elud = random.nextInt(3)+2;
                int relv = random.nextInt(2)+1;
                int varustus = random.nextInt(2)+1;
                mäng.add(new Vastane(elud,relv,varustus));
            } else {
                int elud = random.nextInt(4)+2;
                int relv = random.nextInt(3)+2;
                int varustus = random.nextInt(3)+2;
                mäng.add(new Vastane(elud,relv,varustus));
            }
        }
        return mäng;
    }
    public static int lugeja(int a) { //Kuna mängija sisendit on väga palju vaja siis oli mõtekas teha selle jaoks meetod
        StringBuilder alg = new StringBuilder("Sisestada 1");
        for (int i = 2; i < a+1; i++) {
            alg.append(" või ").append(i);
        }
        while (true){//Kuna võimalik on vajutada valet nuppu mis võib programmi kokku jooksutada siis meetod kasutab try catch et kontrollida kas tegu on int tüüpi sisendiga ja ka kas sisend on määratud piirides
            try{
                System.out.println(alg);
                Scanner sc = new Scanner(System.in);
                int i = sc.nextInt();
                if(i >=1 && i <= a){
                    return i;
                }
            } catch (Exception InputMismatchException){
            }
        }
    }
    public static boolean kaklus(Vastane vastane, Mängija mängija) throws InterruptedException { //See meetod on mängu peamine tegevus kus mängiva võitleb vastasega
        while(vastane.getElud() >= 0 || mängija.getElud() > 0){ //Võitlus kestab kuni mängijal saavad elud otsa või vastasel saavad elud otsa(Kui mängijal saavad elud otsa siis mäng on läbi)
            Random random = new Random();
            if(random.nextInt(2)+1 == 1){ //Võitlemine toimub suvaliselt ja 50/50 on võimalus kas rüjndab mängija või vastane
                int number = mängija.rünne(vastane);//Vastanse ja Mängija fukntsioonid on identsed ja iga kord kasutab meetod vastava klassi meetodit ründamiseks ja kontrollib ega elud ei oli nullini jõudnud
                vastane.setElud(vastane.getElud()-number);
                if(vastane.getElud() <= 0){
                    System.out.println("Ründasid vastast ja ta sai surma");
                    mängija.setElud(mängija.getElud()+2);
                    return false;
                }
                System.out.println("Ründasid vatast ja ta kaotas "+ number+" elu");
                System.out.println("Vastasel on| "+vastane.getElud() + " elu"+ " | "+vastane.getRelv() + " tugevusega relv"+ " | "+vastane.getVarustus() + " tugevusega varustus");
                System.out.println();
                Thread.sleep(2000);//Thread.sleep kasutasin igal pool kus oleks vaja natuke aega, et aru saada mis mängus toimus
            } else {
                int number = vastane.rünne(mängija);
                mängija.setElud(mängija.getElud()-number);
                if(mängija.getElud() <= 0){
                    System.out.println("Vastane ründas sind ja sa said surma");
                    return true;
                }
                System.out.println("Vastane ründas sind ja sa kaotasid "+ number+" elu");
                System.out.println("Sinul on| "+mängija.getElud() + " elu"+ " | "+mängija.getRelv() + " tugevusega relv"+ " | "+mängija.getVarustus() + " tugevusega varustus");
                System.out.println();
                Thread.sleep(2000);
            }
        }
        return true; //meetod tagastab true kui mängija sai surma ja false kui vastane sai surma
    }
    public static void mäng() throws InterruptedException {// Mängu peamine funktsioon mis eeldab, et see käib kuni kõik vastased on surma saanud.
        List<Object> kaart = generaator();
        Mängija mängija = new Mängija();
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < kaart.size(); i++) {
            if(i==3){ //Neljandas ruumil tekitab meetod Kirstu ruumi kus mängija saab valida suvaliselt genereeritud atribuutidega aga tugevama relva või varustuse endale
                Kirst kirst = new Kirst(mängija);
                kirst.kirstust();
                System.out.println("Oled Kirstu ruumis. Sinu elud on taastatud 5 peale ja saad valida kas");
                System.out.println("1. Relva tugevusega: "+kirst.getKirst().get(0)+" | Sinu praegune on: "+mängija.getRelv());
                System.out.println("2. Varustus tugevusega: "+kirst.getKirst().get(1)+" | Sinu praegune on: "+mängija.getVarustus());
                System.out.println("Saad valida ühe neist");
                if(lugeja(2) == 1){
                    System.out.println("Valisid relva");
                    mängija.setRelv(kirst.getKirst().get(0));
                } else {
                    System.out.println("Valisid varustuse");
                    mängija.setVarustus(kirst.getKirst().get(1));
                }
                System.out.println("Sinul on| "+mängija.getElud() + " elu"+ " | "+mängija.getRelv() + "tugevusega relv"+ " | "+mängija.getVarustus() + "tugevusega varustus");
                Thread.sleep(2000);
            }
            System.out.println("Alustasid võitlust");
            Vastane suvaline = (Vastane) kaart.get(i);
            if(kaklus(suvaline,mängija)){//Kui kaklus tagastab true siis mängu pea loop lõpetab töö
                break;
            }
            System.out.println();
            System.out.println("Võitsid kakluse, et minna edasi");
            lugeja(2);

        }
        System.out.println();
        System.out.println("Lõpetasid mängu selliste atribuutidega| "+mängija.getElud() + " elu"+ " | "+mängija.getRelv() + "tugevusega relv"+ " | "+mängija.getVarustus() + "tugevusega varustus");
        System.out.println("Mäng on läbi kui oled valmis minema tagasi peamenüüse siis");
        lugeja(2);
        System.out.println();
    }
    public static void õpetus(){//Lihtsalt meetod mis väljastab mängijale seletuse mängus toimuva kohta
        System.out.println("See on õpetus mis seletab mängu mehhanisme");
        System.out.println("1.See on rougelite tüüpi mäng kus iga uus mäng on erinev eelnevast ja surres tuleb mängu uuesti alustada. Mäng koosneb 7 ruumist kus 6 nendest on vastased ja 1 kirstu ruum");
        System.out.println("2.Elud on peamine resurss ja kui need kukuvad nullini mängijal siis on mäng läbi ja kui vastasel siis liigub edasi järgmise vastaseni");
        System.out.println("3.Relv on tööriist mille pealt arvutatakse kui palju elusi kaotab mängija või vastane");
        System.out.println("4. Varustus on rüü mis kaitseb mängijat või vastast rünnakute eest. Näiteks kui mängija relv on tugevusega 3 ja vastase rüü on tugevusega 1 siis koatab vastane 2 elu");
        System.out.println("5. NB! Mängijal või vastasel ei ole võimalik kaotad vähem kui 1 elu");
        System.out.println("6. Kirstu ruumis saab mängija valida endale parema relva või varustuse. See ruum on alati 4 ruum mängus");
        System.out.println();
        System.out.println("Õpetusest lahkumiseks");
        lugeja(2);
    }

    public static void main(String[] args) throws Exception { //Programmi pea meetod mis kuvab mängijale peamenüü kus ta saab valida mida edasi tahab teha. Siia tullakse tagasi peale igat mängu või õpetuse lugemist
        while (true) {
            System.out.println("   Peamenüü   ");
            System.out.println("1. Alusta uut mängu");
            System.out.println("2. Õpetus");
            System.out.println("3. Välju mängust");
            switch (lugeja(3)) { //Küsib mängijalt Tema valiku ja alustab sellest olenevalt tegevuse
                case 1 -> mäng();
                case 2 -> õpetus();
                case 3 -> { //Kui mängida enam ei tahta siis sulgeme programmi
                    System.out.println("Täname mängimast!");
                    Thread.sleep(1500);
                    System.exit(0);
                }
            }
        }
    }
}