## Yleistä

WETO käyttää aina alkuperäistä _harjoitustyo.apulaiset_-pakkausta. Pakkauksen
tiedostoja ei siksi ole syytä muuttaa ellet ole Mac- tai Linux-käyttäjä (katso
alla).

Projektipohjan juuressa on _.gitignore_-tiedosto, jolla estetään tavukoodin
tallennus versionhallintaan. Lisää siihen tarvittaessa estoja.

## Mac- ja Linux-käyttäjät

Projektipohjan tiedostojen rivinvaihdot ovat Windows-tyylisiä (CR & LF). Muuta
rivinvaihdot tarvittaessa Mac/Linux-muotoon (LF). Tämä onnistuu todennäköisesti
käyttämälläsi editorilla tai IDE:llä. **Älä muuta** _harjoitustyo.apulaiset_
-pakkausta **millään muulla tavoin**.

## Muuta

Harjoitusyössä ohjelmoidaan Java-kielellä tekstikokoelmia käsittelevä olioperustainen Last Oope Task (L.O.T.) -ohjelma. 

Harjoitustyöohjelma lukee kokoelman dokumentit tekstitiedostosta ja säilöö dokumentit linkitetylle listalle. Ohjelma osaa tulostaa kokoelman tietoja eri tavoin, dokumentteja voi lisätä ja poistaa, kokoelmasta voidaan hakea dokumentteja ja kokoelman lajittelu on mahdollista samoin kuin esikäsittely. Esikäsittelyn tavoitteena on tehostaa tiedonhakua poistamalla dokumenteista välimerkkejä ja yleisiä sanoja.
Esikäsittelystä on hyötyä myös siten, että kokoelman kokoa saadaan pienennettyä.
Kaikki muutokset voidaan perua lataamalla kokoelma uudelleen. 

Harjoitustyöohjelma osaa käsitellä kahdentyyppisiä tekstikokoelmia. Kokoelman
dokumentit voivat olla joko uutisia tai vitsejä. Molemman tyyppisissä dokumenteissa on tekstin ohella kaksi metatietoa, joista dokumentin yksilöivä kokonaislukutunniste on yhteinen sekä vitseille että uutisille. Tieto vitsin lajista on ominaista
pelkästään vitsidokumenteille. Uutisen sisältävälle dokumentille ominaista on puolestaan päivämäärä, jona uutinen on julkaistu. Dokumentit ovat aina englanniksi
eikä niissä ole esimerkiksi skandinaavisten kirjainten tapaisia erikoismerkkejä.

Dokumentit on annettu tekstitiedostoissa, joissa yhden dokumentin tiedot ovat aina
yhdellä rivillä. Dokumentin tiedot erotetaan toisistaan merkkijonolla "///".

Ohjelma käynnistetään seuraavalla komennolla:
java Oope2HT jokes_oldies.txt stop_words.txt
kun kokoelman dokumentit halutaan lukea tiedostosta jokes_oldies.txt ja sulkusanat tiedostosta stop_words.txt . Dokumeentit löytyvät esimerkit-kansiosta.

Tiedostoille voidaan määritellä polku. Polun käyttö on suositeltavaa, jotta testitiedostoja ei tulisi vahingossa lisättyä versionhallintaa. Tiedostot voitaisiin lukea esimerkiksi ylihakemistossa olevasta kokoelmat-hakemistosta:
java Oope2HT ..\esimerkit\jokes_oldies.txt ..\esimerkit\stop_words.txt
Mac- tai Linux-järjestelmässä polkuerottimena käytetään jakoviivaa /.
Uudelleenohjausta käytettäessä ajokomento voisi olla esimerkiksi:
java Oope2HT jokes_oldies.txt stop_words.txt < in.txt > out.txt

Jos ohjelmaa kutsuttaessa esimerkiksi kirjoitetaan kokoelmatiedoston nimi väärin:
java Oope2HT jokes-oldies.txt stop_words.txt
niin tulostetaan:
Welcome to L.O.T.
Missing file!
Program terminated
