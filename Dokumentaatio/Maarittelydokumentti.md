# Määrittelydokumentti

## Taustaa

Toteutan karttageneraattorin noudattaen artikkelissa **Algorithms For Procedural Dungeon Generation** (*Nathan Hilliard*, *John Salis* ja *Hala ELAarg*) kuvattua Span*-algoritmia. Artikkelissa on verrattu kirjoittajien kehittämien Span*-algoritmin ja Growth-algoritmin ominaisuuksia, lopputulosta ja suorituskykyä. Artikkeli löytyy mm. ACM:n sähköisestä kirjastosta julkaisussa *October 2017 Journal of Computing Sciences in Colleges: Volume 33 Issue 1, October 2017*.

Valitsin näistä kahdesta Span* algoritmin julkaisun johtopäätösten perusteella, koska tekijöiden havaintojen perusteella Span* ei tee umpikujia päinvastoin kuin Growth-algoritmi. Lisäksi Span* vaikuttaisi luovan visuaalisesti viimeistellymmän näköisiä karttoja.

Vaihtoehtoja karttoja ja luolastoja luoville algoritmeille löytyy useita Internetistä esimerkiksi hakutermeillä *procedural dungeon generation*, *random map generator* tai jollain näiden termien yhdistelmillä.

## Käytettävät algoritmit ja tietorakenteet

Span* -algoritmissa hyödynnetään *Primin* algoritmia toisiaan lähellä olevien huoneiden löytämiseen ja *A\**-algoritmia mahdollisimman lyhyiden käytävien piirtämiseen näiden välille. Lisäksi algoritmiin sisältyy alustustoimenpiteet annettujen parametrien perusteella.

*Primin* algoritmin kuvaus löytyy mm. Wikipediasta: [Prim's algorithm](https://en.wikipedia.org/wiki/Prim%27s_algorithm)
Primin algoritmin aikavaativuus on O(|E|log|V|).

*A\**-algoritmin kuvaus löytyy mm. Wikipediasta: [A* search algorithm](https://en.wikipedia.org/wiki/A*_search_algorithm)
A*-alogritmin aikavaativuus on O((|E|+|V|)log|V|), jos toteutuksessa käytetään minimikekoa vielä käsittelemättömien solmujen tallettamiseen.

Toteutan Primin algoritmin ja A\* algoritmin seuraten Helsingin Yliopiston Tietorakenteet ja algoritmit -kurssin kevään 2018 materiaalissa olevia näiden algoritmien pseudokoodeja. Näissä Primin algoritmi hyödyntää tietorakenteina virittävää puuta, aputaulukoita sekä minimikekoa. A* algoritmissä käytetään verkkoa, aputaulukoita ja minimikekoa.

## Syötteet 

Ohjelmalle annetaan syötteenä ainakin luotavan kartan koon (pituus \* leveys) sekä karttaan tehtävien huoneiden sijoitteluun liittyviä arvoja parametreina.

## Käyttöliittymä

Toteutan ohjelman JavaFX:llä, joten siinä on graafinen käyttöliittymä. Koko kartta ei näy kerralla ruudulla, mikäli kartan koko on suurempi kuin sovellusikkunan koko. Tällöin käyttäjä pystyy liikuttamaan karttaa tietokoneen hiirellä tai näppäimistöllä.


## Pakkauskaavio (alustava)

![alt text](https://github.com/majormalfunk/karttaraattori/blob/master/Dokumentaatio/Pakkauskaavio.png "Pakkauskaavio")
