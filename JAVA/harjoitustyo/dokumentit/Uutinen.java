
package harjoitustyo.dokumentit;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Uutinen extends Dokumentti  {
    //attribuutit
    private LocalDate päivämäärä;
    
    //rakentaja
    public Uutinen(int luku, LocalDate paiva, String txt){
        super(luku, txt);
        päivämäärä(paiva);
        
        
        
        
        
        
    } public void päivämäärä(LocalDate pv) throws IllegalArgumentException {
        if(pv == null) {
            throw new IllegalArgumentException();
        }
        
        
        this.päivämäärä = pv;
    }
    
    public LocalDate päivämäärä() {
      
        //DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd.MM.uuuu");
        //String string = this.päivämäärä.format(pattern);
        
    
        
       return this.päivämäärä;
    }
    
    @Override
    public String toString() {
        
        
        return super.tunniste()+"///"+this.päivämäärä.format(DateTimeFormatter.
                ofPattern("d.M.uuuu"))+"///"+super.teksti();
    }

    @Override
    public int compareTo(Dokumentti o) {
      
        return super.compareTo(o);
    }
    
    
}
