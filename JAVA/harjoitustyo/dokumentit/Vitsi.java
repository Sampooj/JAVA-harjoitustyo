
package harjoitustyo.dokumentit;


public class Vitsi extends Dokumentti{
    //attribuutit
    private String laji;
    
    //rakentaja
    public Vitsi(int luku,String s, String txt) throws IllegalArgumentException {
        super(luku, txt);
        laji(s);
    }
    
    public void laji(String s) throws IllegalArgumentException {
        
        if(s == null || s.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        
        this.laji = s;
        
    }
    
    public String laji(){
        
        return this.laji;
        
    }@Override
 
    public String toString() {
        
        return super.tunniste()+"///"+this.laji+"///"+ super.teksti();
    }

    @Override
    public int compareTo(Dokumentti o) {
        return super.compareTo(o);
    }
}
