package blackjack;

//@author Alex

public class Kortstokk<Kort> {
    private Element<Kort> første = null;
    private int antall = 0;
    
    public void leggTil(Kort kort, Kort verdi){
        if(første == null){
            første = new Element<>(kort, verdi);
            antall++;
        } else{
            Element<Kort> temp = første;
            Element<Kort> nytt = new Element<>(kort, verdi, temp);
            første = nytt;
            antall++;
        }
    }
    
    public Kort trekkKort(){
        if(første != null){
            Element<Kort> temp = første;
            første = første.neste;
            antall--;
            return temp.hentKort();
        } else{
            return null;   
        }
    }
    
    public Kort seFørsteKort(){
        if(første == null){
            return null;
        } else{
            return første.hentKort();
        }
    }
    
    public Kort seFørsteVerdi(){
        if(første == null){
            return null;
        } else{
            return første.hentVerdi();
        }
    }
    
    public int antallKort(){
        return antall;
    }
    
    public void tømKort(){
        antall = 0;
        første = null;
    }
    
    public Object[][] tilArray(){
        if(første != null){
            Object[][] liste = new Object[2][antall];
            Element<Kort> element = første;
            int lengde = 0;
            while(element != null){
                liste[0][lengde] = element.hentKort();
                liste[1][lengde] = element.hentVerdi();
                element = element.neste;
                lengde++;
            }
            return liste;
        } else{
            return null;
        }
    }
    
    //Kort klasse
    private class Element<Kort>{
        public Kort kort, verdi;
        public Element<Kort> neste;
        
        public Element(Kort kort, Kort verdi){
            this.kort = kort;
            this.verdi = verdi;
            this.neste = null;
        }
        
        public Element(Kort kort, Kort verdi, Element<Kort> neste){
            this.kort = kort;
            this.verdi = verdi;
            this.neste = neste;
        }
        
        public Kort hentKort(){
            return kort;
        }
        
        public Kort hentVerdi(){
            return verdi;
        }
        
        @Override
        public String toString(){
            return kort.toString();
        }
    }
}
