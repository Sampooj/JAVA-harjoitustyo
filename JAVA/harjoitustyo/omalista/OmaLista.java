package harjoitustyo.omalista;

import harjoitustyo.apulaiset.Ooperoiva;
import harjoitustyo.dokumentit.Dokumentti;
import java.util.LinkedList;

/**
 *
 * @author Dado
 *
 */
public class OmaLista<E> extends LinkedList<E> implements Ooperoiva<E> {

    @Override
    @SuppressWarnings({"unchecked"})
    public void lisää(E uusi) throws IllegalArgumentException {

        //katsotaan poikkeukset kuntoon
        if (uusi == null) {
            throw new IllegalArgumentException();
        }

        if (!(uusi instanceof Comparable)) {
            throw new IllegalArgumentException();
        }

        //luodaan vertailtava olio
        Comparable vertailtava = (Comparable) uusi;

        if (isEmpty()) {

            add(uusi);
        } else {

            int i = 0;
            boolean t = true;

            //tehdään looppi
            while (t) {

                Comparable nykyinen = (Comparable) get(i);

                //vertaillaan mitä arvoja saadaan CompareTo-metodilla
                if (i == size() - 1 && vertailtava.compareTo(nykyinen) >= 1) {

                    addLast(uusi);
                    t = false;
                }

                if (vertailtava.compareTo(nykyinen) <= -1) {

                    add(i, uusi);
                    t = false;
                    
                    //jos arvot on samat niin pitää vielä tutkia onko samoja arvoja
                    //enemmänkin
                } else if (vertailtava.compareTo(nykyinen) == 0) {
                    int j = i;

                    while (j < size()) {
                        Comparable nykyinenx = (Comparable) get(j);

                        if (vertailtava.compareTo(nykyinenx) <= -1) {

                            add(j, uusi);
                            j++;
                            t = false;
                            break;
                        }

                        if (i == size() - 1 && vertailtava.compareTo(nykyinenx) == 0) {

                            addLast(uusi);
                            j++;
                            t = false;
                            break;
                        }
                        j++;

                    }

                }

                i++;

            }

        }

    }

}
