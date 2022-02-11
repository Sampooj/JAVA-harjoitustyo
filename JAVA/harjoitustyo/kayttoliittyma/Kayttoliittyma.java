package harjoitustyo.kayttoliittyma;

import harjoitustyo.dokumentit.Dokumentti;
import harjoitustyo.dokumentit.Uutinen;
import harjoitustyo.dokumentit.Vitsi;
import harjoitustyo.kokoelma.Kokoelma;
import harjoitustyo.omalista.OmaLista;
import java.io.File;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.TreeMap;
import jdk.nashorn.internal.ir.ContinueNode;

/**
 *
 *
 */
public class Kayttoliittyma {

    //vakiot
    final String LOPETA = "quit";
    final String KAIUTA = "echo";
    final String TULOSTUS = "print";
    final String LISÄÄ = "add";
    final String POISTA = "remove";
    final String SIIVOA = "polish";
    final String ETSI = "find";

    //attribuutit
    private Kokoelma kokoelma;

    public Kayttoliittyma(String[] args) {
        System.out.println("Welcome to L.O.T.");

        boolean paaSilmukka = true;

        //tarkistettaan onko parametreja kaksi. Jos ei niin virhe.
        if (args.length == 2) {
            this.kokoelma = new Kokoelma(args[0], args[1]);

            if (!this.kokoelma.tiedostoOk() || !this.kokoelma.sanatOk()) {
                System.out.println("Missing file!");
                paaSilmukka = false;
            }
        } else {
            System.out.println("Wrong number of command-line arguments!");
            paaSilmukka = false;

        }

        Scanner lukija = new Scanner(System.in);

        //kaiutus-toiminnon tarkistavat attribuutit
        boolean kaiutusPaalla = false;
        int onkoParillinen = 1;

        while (paaSilmukka) {

            System.out.println("Please, enter a command:");

            String sana = lukija.nextLine();

            //luodaan syötteestä talukko
            String[] sanaTaulukko = sana.split(" ");

            try {

                //vertaillaan komennon ensimmäista osaa
                switch (sanaTaulukko[0]) {

                    case LOPETA:
                        if (kaiutusPaalla) {
                            System.out.println(sana);
                        }
                        paaSilmukka = false;
                        break;

                    case KAIUTA:
                        //jos jaollinen kahdella niin kaiutus pois päältä
                        //jos ei jaollinen niin kytketään päälle
                        if (onkoParillinen % 2 == 0) {
                            onkoParillinen++;
                            kaiutusPaalla = false;
                            System.out.println(sana);
                            break;
                        } else {
                            kaiutusPaalla = true;
                            onkoParillinen++;
                            System.out.println(sana);
                            break;
                        }

                    case TULOSTUS:
                        if (kaiutusPaalla) {
                            System.out.println(sana);
                        }

                        //jos komento 'print', haetaan kaikki dokumentit
                        if (sanaTaulukko.length == 1) {

                            for (int i = 0; i < this.kokoelma.dokumentit().size(); i++) {
                                System.out.println(this.kokoelma.dokumentit().get(i).toString());
                            }
                            break;

                            //jos komennossa myös toinen osa niin
                            //muutetaan se luvuksi ja haetaan kyseinen dokumentti
                        } else if (sanaTaulukko.length == 2) {

                            int tunnus = Integer.parseInt(sanaTaulukko[1]);

                            //jos dokumenttia ei löydy niin tulostetaan "Error!"
                            if (this.kokoelma.hae(tunnus) == null) {
                                System.out.println("Error!");
                                break;
                            } //muussa tapauksessa tulosteaan dokumentti
                            else {
                                System.out.println(this.kokoelma.hae(tunnus).toString());
                                break;
                            }

                        } //jos osia enemmän kuin kaksi niin tulostetaan "Error!
                        else {
                            System.out.println("Error!");
                            break;
                        }

                    case LISÄÄ:
                        break;

                    case POISTA:
                        break;

                    case SIIVOA:
                        if (kaiutusPaalla) {
                            System.out.println(sana);
                        }
                        this.kokoelma.siivoaTeksti(sanaTaulukko[1]);
                        break;

                    case ETSI:
                        String etsittavatSanat = "";
                        for (int i = 1; i < sanaTaulukko.length; i++) {
                            etsittavatSanat = etsittavatSanat + sanaTaulukko[i] + " ";
                        }
                        this.kokoelma.etsiSanat(etsittavatSanat);
                        break;
                       

                    default:
                        if (kaiutusPaalla) {
                            System.out.println(sana);
                        }
                        System.out.println("Error!");
                }

            } catch (Exception e) {
                System.out.println("Error!");
            }

        }

        System.out.println("Program terminated.");

    }

}
