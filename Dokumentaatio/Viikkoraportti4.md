Viikkoraportti 4

Tällä viikolla sain korjattua virheet Primin algoritmista, joka rakentaa virittävän puun kartan huoneiden välille sekä lisättyä testin,
Joka testaa, että algoritmi rakentaa saman painoisen puun samalle kartalle riippumatta siitä, mistä solmusta aloitetaan.

Sen jälkeen aloitin A* algoritmin toteutuksen. A*-algoritmilla etsitään lyhyin reitti Primin algoritmin tuottamien verkon
kaarien välille. Reitin kuuluu väistää koko kartta-alueen reunojen lisäksi kartalla olevia huoneita jättäen niiden ympärille
yhden ruudukkoyksikön välin. Algoritmin on tarkoitus laskea etäisyydet Manhattan-etäisyyksinä eli rakentaa reitti vain
vaakasuoraan ja pystysuoraan. Reitit vaikuttavat rakentuvan muuten oikein, mutta jossain tilanteissa reitti muodostuukin
kulmittain. Yksikkötesteissä on tästä esimerkki. A*starin löytämä reitti kiertää ainoan esteen, mutta siirtyy kaksi kertaa
kulmittain kiertäessään esteen.

Tässä kuva tilanteesta, joka on luotu parametreilla leveys = 40, korkeus = 40, huoneita = 30:

![alt text](https://github.com/majormalfunk/karttaraattori/blob/master/Dokumentaatio/Testikuva2.png "Kuva testitilanteesta")

Kuvassa on merkittynä taaskin viivoilla Primin tuottama virittävä puu. Käytävät on lisätty eri värillä kuin huoneet. Kuvasta näkee kuinka joissain kohdin tumman harmaa käytävä kulkee kulmittain.

Omien tietorakenteiden suhteen olen aivan alkutekijöissä. Olen luonut tyhjät luokat korvaamaan ArrayListin, PriorityQueuen ja HashSetin
sekä Randomin.

Olen listannut lyhyesti testausdokumenttiin suunnitelmia testauksen kannalta.

Toteutusdokumenttia en ehtinyt aloittamaan tähän viikon 4 dead lineen mennessä.

Aikaa olen kuluneella viikolla käyttänyt noin 12 tuntia.
