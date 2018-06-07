package blackjack;

//@author Alex

import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JOptionPane;

public class Kontroll {
    Kortstokk kortstokk = new Kortstokk();
    public Object[][] liste = new Object[2][52];
    public int antall = 0;
    public int sumspiller = 0;
    public int sumdealer = 0;
    public int essspiller = 0;
    public int essdealer = 0;
    public int kortspiller = 0;
    public int kortdealer = 0;
    public int penger = 0;
    public int pengerbrukt = 0;
    public int runderspilt = 0;
    public int essigjen = 0;
    public int bildekortigjen = 0;
    public boolean spillerdouble = false;
    public boolean spillersplit = false;
    public Object dealerkort = null;
    public int dealerkortverdi = 0;
    public Object splitkort = null;
    public int splitverdi = 0;
    
    public void lagListe(){
        stokkKort();
        kortstokk.tømKort();
        for(int i=0; i<antall; i++){
            kortstokk.leggTil(liste[0][i], liste[1][i]);
        }
    }
    
    public Object[][] hentKortstokk(){
        return kortstokk.tilArray();
    }
    
    public int hentAntallKort(){
        return kortstokk.antallKort();
    }
    
    public Object nesteKort(){
        if(seNesteVerdi() == 11){
            essigjen--;
        } else if(seNesteVerdi() == 10){
            bildekortigjen--;
        }
        return kortstokk.trekkKort();
    }
    
    public Object seNesteKort(){
        return kortstokk.seFørsteKort();
    }
    
    public void sjekkKortstokk(){
        if(kortstokk.seFørsteKort() == null){
            JOptionPane.showMessageDialog(null, "Kortstokken er tom\nHenter ny kortstokk");
            leggTilKort();
        }
    }
    
    public int seNesteVerdi(){
        return (Integer)kortstokk.seFørsteVerdi();
    }
    
    public void dealerTotal(int verdi){
        sumdealer += verdi;
    }
    
    public void spillerTotal(int verdi){
        sumspiller += verdi;
    }
    
    public void spillerSplitTotal(int verdi){
        splitverdi += verdi;
    }
    
    public void spillerEss(){
        essspiller += 1;
    }
    
    public void dealerEss(){
        essdealer += 1;
    }
    
    public void spillerKort(){
        kortspiller += 1;
    }
    
    public void dealerKort(){
        kortdealer += 1;
    }
    
    public void leggTilKort(){
        antall = 0;
        for(int i=0; i<4; i++){
            String type;
            switch (i) {
                case 1:
                    type = "Spar ";
                    break;
                case 2:
                    type = "Kløver ";
                    break;
                case 3:
                    type = "Hjerter ";
                    break;
                default:
                    type = "Ruter ";
                    break;
            }
            for(int j=1; j<=13; j++){
                switch (j){
                    case 1:
                        liste[0][antall] = type+"ess";
                        liste[1][antall] = 11;
                        break;
                    case 11:
                        liste[0][antall] = type+"knekt";
                        liste[1][antall] = 10;
                        break;
                    case 12:
                        liste[0][antall] = type+"dronning";
                        liste[1][antall] = 10;
                        break;
                    case 13:
                        liste[0][antall] = type+"konge";
                        liste[1][antall] = 10;
                        break;
                    default:
                        liste[0][antall] = type+j;
                        liste[1][antall] = j;
                        break;
                }
                antall++;
            }
        }
        essigjen = 4;
        bildekortigjen = 16;
        lagListe();
    }
    
    public void stokkKort(){
        //Collections.shuffle(Arrays.asList(dec));
        for(int i=0; i<52; i++){
            int rnd = ThreadLocalRandom.current().nextInt(0, 52);
            for(int j=0; j<2; j++){
                Object x = liste[j][i];
                Object y = liste[j][rnd];
                liste[j][i] = y;
                liste[j][rnd] = x;
            }
        }
    }

}
