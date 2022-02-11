package harjoitustyo.kokoelma;

import harjoitustyo.apulaiset.Kokoava;
import harjoitustyo.dokumentit.Dokumentti;
import harjoitustyo.dokumentit.Uutinen;
import harjoitustyo.dokumentit.Vitsi;
import harjoitustyo.omalista.OmaLista;
import java.io.File;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import jdk.nashorn.internal.runtime.JSType;

/**
 * Kokoelma-luokka. Harjoitustyö, Olio-ohjelmoinnin perusteet 2, kevät 2020.
 * <p>
 * @author Sampo Ojala (sampo.ojala@tuni.fi), Informaatioteknologian ja
 * viestinnän tiedekunta, Tampereen yliopisto.
 */
public class Kokoelma extends Object implements Kokoava<Dokumentti> {

    //attribuutit
    private OmaLista<Dokumentti> dokumentit;
    private String tiedostoPolku;
    private String sulkuSanatPolku;
    private LinkedList<String> sulkuSanaLista;

    private boolean tiedostoOk;
    private boolean sanatOk;

    //rakentaja
    public Kokoelma(String tiedosto, String poistettavatSanat) {
        this.tiedostoOk = true;
        this.sanatOk = true;

        Scanner lukija = new Scanner(System.in);
        this.sulkuSanaLista = new LinkedList<String>();

        this.dokumentit = new OmaLista<>();
        this.tiedostoPolku = tiedosto;
        this.sulkuSanatPolku = poistettavatSanat;

        lataaTiedosto(this.tiedostoPolku);
        lataaSulkusanat(this.sulkuSanatPolku);

    }

    //metodi lataa tiedoston auki
    public void lataaTiedosto(String t) {

        Scanner tiedostonLukija = null;

        try {
            File file = new File(t);

            tiedostonLukija = new Scanner(file);
            boolean num = true;

            //käydään tiedosto läpi rivi riviltä
            while (tiedostonLukija.hasNextLine()) {
                String sana = tiedostonLukija.nextLine();

                //splitataan dokumentti taulukoksi
                String[] split = sana.split("///");
                int tunnus = Integer.parseInt(split[0]);

                //jos tiedosto alkanut "news" niin luodaan Uutinen olio
                if (t.startsWith("news")) {

                    //päivämäärä muistiin
                    String date = split[1];
                    //luodaan LocalDate-olio ja annetaan parametriksi Uutiselle
                    LocalDate localDate = null;
                    DateTimeFormatter formatter = null;
                    formatter = DateTimeFormatter.ofPattern("d.M.uuuu");
                    localDate = LocalDate.parse(date, formatter);

                    Uutinen uutinen = new Uutinen(tunnus, localDate, split[2]);
                    this.dokumentit.lisää(uutinen);

                } //jos tiedosto alkanut "jokes" niin luoda Vitsi-olio
                else if (t.startsWith("jokes")) {

                    Vitsi vitsi = new Vitsi(tunnus, split[1], split[2]);
                    this.dokumentit.lisää(vitsi);
                }

            }

            tiedostonLukija.close();

        } catch (Exception e) {

            this.tiedostoOk = false;

        }

    }

    public void lataaSulkusanat(String poistettavatSanat) {

        Scanner tiedostonLukija = null;

        try {
            File file = new File(this.sulkuSanatPolku);
            tiedostonLukija = new Scanner(file);
            this.sanatOk = sanatOk();

            while (tiedostonLukija.hasNextLine()) {
                this.sulkuSanaLista.add(tiedostonLukija.nextLine());

            }

            tiedostonLukija.close();

        } catch (Exception e) {
            this.sanatOk = false;

        }

    }

    /*metodi käy läpi kokoelman jokaisen dokumentin ja siivoaa
    siitä halutut erikoismerkit ja tiedostosta luetut sulkusanat
     */
    public void siivoaTeksti(String poistettavatMerkit) {

        for (int i = 0; i < this.sulkuSanaLista.size(); i++) {

            for (int j = 0; j < this.dokumentit.size(); j++) {
                this.dokumentit.get(j).siivoa(this.sulkuSanaLista, poistettavatMerkit);
            }

        }

    }

    public boolean tiedostoOk() {

        return this.tiedostoOk;
    }

    public boolean sanatOk() {

        return this.sanatOk;
    }

    public void etsiSanat(String s) {

        s.trim();

        String[] taulukko = s.split(" ");
        LinkedList<String> lista = new LinkedList<String>();

        for (int i = 0; i < taulukko.length; i++) {

            lista.add(taulukko[i]);

        }

        for (int i = 0; i < this.dokumentit.size(); i++) {

            if (this.dokumentit.get(i).sanatTäsmäävät(lista)) {

                System.out.println(this.dokumentit.get(i).tunniste());
            }

        }

    }

    public OmaLista<Dokumentti> dokumentit() {

        return dokumentit;
    }

    //metodi lisää uuden dokumentit listalle ja järjestää sen
    @Override
    public void lisää(Dokumentti uusi) throws IllegalArgumentException {

        for (int i = 0; i < dokumentit.size(); i++) {

            if (uusi.tunniste() == dokumentit.get(i).tunniste()) {
                throw new IllegalArgumentException();
            }

        }

        dokumentit.lisää(uusi);

    }

    //metodi palauttaa sen dokumentin mikä vastaa tunnistetta
    //jos tunnistetta ei löydy niin palautetaan null
    @Override
    public Dokumentti hae(int tunniste) {

        for (int i = 0; i < dokumentit.size(); i++) {
            if (tunniste == dokumentit.get(i).tunniste()) {
                return dokumentit.get(i);
            }

        }

        return null;

    }
}
