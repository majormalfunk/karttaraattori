Testausdokumentti

Projektin testuksessa on tarkoitus yksikkötestien lisäksi testata suorituskykyä ja oikellisuutta.

Suorituskykyä testataan suhteessa käytettyjen algoritmien tiedettyihin aika- ja tilavaativuuksiin.
Testit voitaneen tehdä samoilla JUnit-testeillä kuin yksikkötestit. Suorituskyky testiin tehdään siten,
että generoidaan isoja testiaineistoja, joilla sitten testaan sovelluksen eri osa-alueiden esim Primin
ja A* -algoritmien suoriutumista ennalta määrätyssä ajassa.

Oikellisuutta testaan esimerkiksi siten, että tutkitaan testialgoritmilla (dfs tai bfs) ovatko kaikki
kartan huoneet saavutettavissa kaikista muista huoneista. Primin algoritmin oikeellisuutta on jo jossain
määrin testattu yksikkötesteillä.


