## Viikkoraportti 6

Tällä viikolla tein A*:n testejä sekä omat toteutukseni minimikeosta ja hajautuslistasta. Muut opiskelut rajoittivat käytettävissä olevaa aikaani.
Olisin halunnut saada edistettyä dokumentaatiota sekä suorituskyky- että oikeellisuustestejä, mutta valitettavasti en ehtinyt etenemään niissä.
Onneksi tulevalla viikolla on vähemmän muita opintoja, niin ehdin työstämään paremmin niitä sekä laatimaan loputkin yksikkötestit.


Ensi viikolla on myös demot, joten yritän vähän viilata myös käyttöliittymää demoamisen kannalta mielenkiintoisemman oloiseksi.
Tarkoitus olisi lisätä käyttöliittymään vähintään nappi, jolla käynnistää uusi kartta, mutta mielummin myös haluttaessa vähän
animaatiota kartan syntymiseen. Alkuperäisen tavoitteen mukaan aikomuksena oli mahdollistaa jonkinlainen kartan skrollaaminen,
mutta harjoitustyön kannalta lienee mielekkäämpää jonkinlainen animaatio, joka havainnollistaisi käytettyjen algoritmien toimintaa.


Tässä jälleen kuva kartasta parametreilla: Leveys = 60, Korkeus = 60, huoneita = 100. Nyt siinä näkyy vähän keskikohdasta alavasemmalle
mielenkiintoisesti kuinka lähelle toisiaan osuvat huoneet voivat pakottaa A*:n kiertämään isohkonkin lenkin muiden huoneiden ympäri.
Toteutuksessanihan oli yhtenä ehtona, että A*:n tulee kiertää muut huoneet siten, että väliin jää vähintään yksi blokki.

![alt text](https://github.com/majormalfunk/karttaraattori/blob/master/Dokumentaatio/Testikuva4.png "Kuva testitilanteesta")

Aikaa olen käyttänyt tällä viikolla 15 tuntia.
