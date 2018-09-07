# Testausdokumentti

Projektissa tehtiin yksikkötestien lisäksi erilliset suorituskyky testit Primin algoritmille ja A* algoritmille.
Myös oikeellisuutta testattiin yksikkötesteissä.

## Yksikkötestit ja testauskattavuus

Yksikkötesteissä testattiin itsetoteutettuja tietorakenteita (MazeArrayList, MazeHashSet ja MazeMinHeap), itsetoteutettuja algoritmeja (Prim ja A*) sekä toiminnallisuuden oikeellisuutta (tärkeimpinä virittävän puun oikeellisuus ja A* löytämät polut).

Automaattisesti generoidut testikattavuusraporttit:

Kaikki paketit:

![alt text](https://github.com/majormalfunk/karttaraattori/blob/master/Dokumentaatio/TestikattavuusKarttaraattori.png "Testikattavuus: kaikki paketit")

Tietorakenteiden paketti:

![alt text](https://github.com/majormalfunk/karttaraattori/blob/master/Dokumentaatio/TestikattavuusMazeomaticStructures.png "Testikattavuus: tietorakenteiden paketit")

Ohjelmalogiikan paketti:

![alt text](https://github.com/majormalfunk/karttaraattori/blob/master/Dokumentaatio/TestikattavuusMazeomaticLogic.png "Testikattavuus: ohjelmalogiikan paketit")

## Suorituskykytestit

Suorituskykytestejä tehtiin erikseen Primin algoritmille ja A* algoritmille. Koko ohjelmalle ei tehty erikseen suorituskykytestejä.

Kummankin algoritmin suorituskykytestissä ajettiin kullakin parametrikokoonpanolla (solmut, kaaret) 10 ajokertaa ja laskettiin suoritusajoista keskiarvo. Saatuja suoritusaikoja verrattiin kyseisen algoritmin odotettuun O-arvoon. Alla olevissa kuvaajissa odotettu arvo on suhteutettu toteutuneeseen siten, että pienimmillä solmu- ja kaarimäärillä se on sama kuin toteutuneiden ajoaikojen keskiarvo pienimmillä solmu- ja kaarimäärillä. Näin ollen kuvaajat lähtevät samasta pisteestä ja ovat samalla asteikolla.

### Primin algoritmin suorituskyky

Arvo *V* kuvaa solmujen määrää eli Primin osalta kartalle arvottujen huoneiden lukumäärää. Arvo *E* kuvaa kaarien määrää eli tässä tapauksessa kaarta kaikkiin muihin solmuihin. Pahimman tapauksen aikavaativuus Primille on *O(|E|log|V|)*.

![alt text](https://github.com/majormalfunk/karttaraattori/blob/master/Dokumentaatio/SuorituskykyPrimArvot.png "Prim suorituskykytestit")
![alt text](https://github.com/majormalfunk/karttaraattori/blob/master/Dokumentaatio/SuorituskykyPrimKuvaajat.png "Prim suorituskykytestit")

Primin kuvaajasta nähdään, että pienimmillä solmu- ja kaarimäärillä itsetoteutettu Primin algoritmi suoriutuu paremmin kuin pahimman tapauksen aikavaativuus, mutta n. 1600 solmun ja n. 2500000 kaaren kohdalla suorituskyky alkaa heikkenemään nopeammin kuin pahimman tapauksen O-arvo. Tämän projektin kontekstissa eli tietokonepelin karttageneraattorina tulos voisi olla hyväksyttävä, jos ajatellaan, että harvoin generoitaisiin valmiiksi karttoja, joissa olisi tätä enemmän huoneita. Luultavimmin sitä isommissa pelikentissä generointi tapahtuisi lennossa vähän kerrallaan.

### A* algoritmin suorituskyky

Arvo *V* kuvaa kaikkia kartan ruutuja eli A* osalta kaikkia mahdollisia sijainteja kartalla. Arvo *E* kuvaa kaaria jokaisesta solmusta *V* viereisiin solmuihin. Koska kartalle voidaan luoda käytäviä vain vaaka tai pystysuuntaan, niin kaikilla solmuilla on *4* naapuria pois lukien reunapaikoilla reunan yli menevät suunnat. Pahimman tapauksen aikavaativuus A*:lle on *O((|E|+|V|)log|V|)*

![alt text](https://github.com/majormalfunk/karttaraattori/blob/master/Dokumentaatio/SuorituskykyAStarArvot.png "A* suorituskykytestit")
![alt text](https://github.com/majormalfunk/karttaraattori/blob/master/Dokumentaatio/SuorituskykyAStarKuvaajat.png "A* suorituskykytestit")

A* kuvaajasta nähdään merkittävä ero pahimman tapauksen aikavaativuuden ja itsetoteutetun välillä itsetoteutetun hyväksi. Suorituskykytesti on tehty siten, että jokaisella ajokerralla reitti oli diagonaalinen ilman esteitä, joten pahin tapaus ei luonnollisestikaan pääse tapahtumaan.


