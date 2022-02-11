package harjoitustyo.dokumentit;
import harjoitustyo.apulaiset.Tietoinen;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;


public abstract class Dokumentti implements Comparable<Dokumentti>, Tietoinen<Dokumentti> {

    //attribuutit
    private int tunniste;
    private String teksti;

    //rakentaja
    public Dokumentti(int luku, String txt) throws IllegalArgumentException {

        if (luku < 1 || txt == null || txt.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.tunniste = luku;
        this.teksti = txt;

    }

    //setterit
    public void tunniste(int luku) throws IllegalArgumentException {
        if (luku < 1) {
            throw new IllegalArgumentException();
        }

        this.tunniste = luku;
    }

    public void teksti(String txt) throws IllegalArgumentException {
        if (txt.trim().isEmpty() || txt == null) {
            throw new IllegalArgumentException();
        }

        this.teksti = txt;
    }

    //getterit
    public int tunniste() {

        return this.tunniste;
    }

    public String teksti() {

        return this.teksti;
    }

    @Override
    public String toString() {

        return this.tunniste + "///" + this.teksti;

    }

    @Override
    public boolean equals(Object o) {

        try {

            Dokumentti toinenDokumentti = (Dokumentti) o;

            return tunniste == toinenDokumentti.tunniste();
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public int compareTo(Dokumentti o) {
        if (tunniste > o.tunniste) {
            return 1;
        }

        if (tunniste == o.tunniste) {
            return 0;
        } else {
            return -1;
        }

    }

    @Override
    public boolean sanatTäsmäävät(LinkedList<String> hakusanat) throws IllegalArgumentException {

        if (hakusanat == null || hakusanat.isEmpty()) {
            throw new IllegalArgumentException();
        }

        //jaetaan string osiin
        String str[] = this.teksti.split(" ");
        LinkedList<String> lista = new LinkedList<String>();

        //lisätään sanat listalle
        for (int i = 0; i < str.length; i++) {

            lista.add(str[i]);
        }

        //käydään hakusanoja läpi
        for (int i = 0; i < hakusanat.size(); i++) {

            for (int j = 0; j < lista.size(); j++) {

                if (hakusanat.get(i).equals(lista.get(j))) {

                    if (i == hakusanat.size() - 1) {
                        return true;
                    }

                    break;
                }
                if (!hakusanat.get(i).equals(lista.get(j)) && j == lista.size() - 1) {

                    return false;
                }

            }

        }

        return false;
    }

    @Override
    public void siivoa(LinkedList<String> sulkusanat, String välimerkit) throws IllegalArgumentException {
        
        if(sulkusanat == null || sulkusanat.isEmpty() || välimerkit == null || välimerkit.equals("")) {
            throw new IllegalArgumentException();
        }

        //poistettaville välimerkeille taulukko
        String[] merkit = välimerkit.split("");
        

        //tekstille taulukko
        String[] sanat = this.teksti.split(" ");

        for (int i = 0; i < sanat.length; i++) {

            for (String string : merkit) {

                boolean numeric = true;

                if (sanat[i].contains(string)) {

                    //katsotaan onko sana numereeninen
                    if (numeric = sanat[i].contains(string)) {
                        sanat[i] = sanat[i].replace(string, "");
                    } else {

                        sanat[i] = sanat[i].replace(string, " ");

                    }

                }
            }

        }

        this.teksti = "";

        for (String string : sanat) {
            this.teksti += string + " ";

        }

        

        this.teksti = this.teksti.toLowerCase();

        this.teksti = this.teksti.trim().replaceAll(" +", " ");

        String str[] = this.teksti.split(" ");

        ArrayList<String> lista = new ArrayList<String>();
        ArrayList<String> listaTeksti = new ArrayList<String>();

        for (int i = 0; i < sulkusanat.size(); i++) {

            lista.add(sulkusanat.get(i));

        }

        for (int i = 0; i < str.length; i++) {

            listaTeksti.add(str[i]);
        }

        for (int i = 0; i < lista.size(); i++) {
            for (int j = 0; j < listaTeksti.size(); j++) {
                if (lista.get(i).equals(listaTeksti.get(j))) {

                    listaTeksti.remove(listaTeksti.get(j));
                }
            }
        }
        this.teksti = "";

        for (int i = 0; i < listaTeksti.size(); i++) {
            if (i == listaTeksti.size() - 1) {
                this.teksti = this.teksti + listaTeksti.get(i);
            } else {
                this.teksti = this.teksti + listaTeksti.get(i) + " ";
            }
        }
       
        
        
    }
}
