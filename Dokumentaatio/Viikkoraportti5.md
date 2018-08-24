## Viikkoraportti 5

Tällä viikolla sain Primin ja A*:n toimimaan mielestäni oikeellisesti. Tässä kuva kartasta parametreilla: Leveys = 60, Korkeus = 60, huoneita = 100:

![alt text](https://github.com/majormalfunk/karttaraattori/blob/master/Dokumentaatio/Testikuva3.png "Kuva testitilanteesta")

Primin ja A*:n lisäksi toteutin MazeArrayListin eli oman versioni ArrayLististä sekä laadin sille testit.

Koodikatselmoinnissa sain hyödyllistä palautetta Graniganilta ja tein hänen ehdottamansa korjaukset ja muutokset soveltuvin osin. Osan pidemmistä metodeista jätin kuitenkin vielä ainakin osittain ennalleen, koska oman heap-toteutukseni käyttöönottaminen javan PriorityQueuen tilalle tulee hieman muuttamaan näitä kohtia lyhyemmiksi.

Lisätessäni Astarille testejä huomasin, että kun nyt Astar ajetaan yhdessä metodissa kaikille Primin luomille kaarille, niin testaamisen ja mahdollisen kartan rakentamisen visualisoinnin kannalta runAstar()-metodi kannattaa purkaa metodiin, joka käy kaaret läpi ja kutsuu toista metodia, joka käynnistää Astarin. Lisäksi polku kannattaa purkaa listaksi, jonka metodi palautta. Sillä tavoin voitaisiin testata yksittäisiä polkuja ja toisaalta se mahdollistaisi polun rakentamisen visualisoinnin, jos projektissa riittä aikaa siihen.

Tällä viikolla käytin aikaa 20 tuntia.
