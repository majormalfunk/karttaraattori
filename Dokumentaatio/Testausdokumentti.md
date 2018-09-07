## Testausdokumentti

Projektissa tehtiin yksikkötestien lisäksi erilliset suorituskyky testit Primin algoritmille ja A* algoritmille.
Myös oikeellisuutta testattiin yksikkötesteissä.

### Yksikkötestit ja testauskattavuus

Yksikkötesteissä testattiin itsetoteutettuja tietorakenteita (MazeArrayList, MazeHashSet ja MazeMinHeap), itsetoteutettuja algoritmeja (Prim ja A*) sekä toiminnallisuuden oikeellisuutta (tärkeimpinä virittävän puun oikeellisuus ja A* löytämät polut).

Automaattisesti generoidut testikattavuusraporttit:

Kaikki paketit:

()[]

Tietorakenteiden paketti:

()[]

Ohjelmalogiikan paketti:

()[]



Suorituskykyä testataan suhteessa käytettyjen algoritmien tiedettyihin aika- ja tilavaativuuksiin.
Testit voitaneen tehdä samoilla JUnit-testeillä kuin yksikkötestit. Suorituskyky testiin tehdään siten,
että generoidaan isoja testiaineistoja, joilla sitten testaan sovelluksen eri osa-alueiden esim Primin
ja A* -algoritmien suoriutumista ennalta määrätyssä ajassa.

Oikellisuutta testaan esimerkiksi siten, että tutkitaan testialgoritmilla (dfs tai bfs) ovatko kaikki
kartan huoneet saavutettavissa kaikista muista huoneista. Primin algoritmin oikeellisuutta on jo jossain
määrin testattu yksikkötesteillä.


