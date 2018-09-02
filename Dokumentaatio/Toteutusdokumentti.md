# Toteutusdokumentti

## Ohjelman yleisrakenne

Sovellus on toteutettu JavaFX:llä. Siinä on tällä hetkellä 2 näkymää. Ensimmäisessä annetaan käynnistysparametrit ja toisessa näytetään muodostettu kartta. Tavoitteena olisi yhdistää näkymät siten, että parametreja voi muuttaa ja kartan luomisen voi käynnistää uudestaan.


Käynnistysparametreina kysytään kartan leveys ja korkeus karttaruutuina sekä huoneiden lukumäärä. Yksi karttaruutu vastaa käytävän leveyttä. Huoneet ovat 3x3 karttaruutua. Kartalla on minimikoko ja minimi huonelukumäärä. Käynnistysparametrien tarkistukseen pitäisi lisätä myös ainakin huoneiden lukumäärän järkevyystarkastus suhteessa kartan kokoon sekä mahdollisesti vaihtaa huoneiden lukumääränä annettava parametri täyttöasteeksi tms.


Itse kartan luominen tapahtuu seuraavassa järjestyksessä:

1) Luodaan taulukko (leveys x korkeus), johon kartta luodaan
2) Satunnaislukugeneraattorin arpoo huoneiden sijannit
3) Huoneet sijoitetaan satunnaislukugeneraattorin arpomiin paikkoihin
4) Luodaan vieruslistat kullekin huoneelle (huoneen vieruslistalla on kaikki muut huoneet)
5) Ajetaan Primin algoritmi, joka muodostaa pienimmän virittävän puun yhdistäen kaikki huoneet ilman sykliä
6) Ajetaan A* algoritmi, joka etsii lyhyimmän mahdollisen reitin kaikille Primin algoritmin löytämille kaarille
7) Näytetään rakennettu kartta.

### Pakkauskaavio (alustava)

![alt text](https://github.com/majormalfunk/karttaraattori/blob/master/Dokumentaatio/Pakkauskaavio.png "Pakkauskaavio")

## Saavutetut aika- ja tilavaativuudet

Testit ovat vielä kesken

### Prim

Primin algoritmi on toteutettu noudattaen Tietorakenteet ja algoritmit kurssilla (kevät 2018) esitettyä pseudokoodia. Pseudokoodin analyysin perusteella aikavaativuus on O(|E|log|V|), missä |E| on kaarien lukumäärä ja |V| solmujen lukumäärä. Tässä toteutuksessa solmujen lukumäärä |V| annetaan käynnistysparametrina. Kaarien lukumäärä |E| on Primin kannalta (|V|)x(|V|-1) koska kunkin solmun vieruslistalla on kaikki muut huoneet (eli solmut). Tämä siksi, että varsinaista verkkoa ei vielä tässä vaiheessa ole, vaan Primin algoritmi vasta muodostaa sen.

### A*

Myös A* algoritmin toteutus noudattelee Tietorakenteet ja algoritmit kurssilla (kevät 2018) esitettyä pseudokoodia. A*:n pahimman tapauksen aikavaatisuus on O((|E|+|V|)log|V|), kun toteutuksessa on käytetty apuna minimikekoa. A* ajetaan kaikille Primin muodostamille kaarille, joten se ajetaan pahimmassa tapauksessa (|V|-1) kertaa. Tällöin yhteensä vaiheen aikavaativuus on O((|E|+|V|^2)log|V|).

### Kokonaisuus

## Puutteet ja parannusehdotukset

### Lähteet

