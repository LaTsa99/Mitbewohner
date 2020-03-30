#How to set up your IntelliJ
Írok pár sort arról, hogyan és mit állítsatok be, hogy lefusson a Main.main() :) 
1. `Ellenőrizzétek, hogy a legfrisebb **master** ág legyen meg.` \
    (ez a _reshaped folders_ commit, amikor ezt a fájlt írom)
    
2. `Töröljétek ki a Mitbewohner\out, Mitbewohner\src, Mitbewohner\.idea mappákat!`\ *Tomi megjegyzése: a .idea mappát újra legenerálja,
                                                                                     anélkül nem fordul -> csak egyszer törölni (?)
    (ebből nem biztos, hogy mind ott van, a lényeg, hogy ne maradjon ott semelyik:) 

    **Érdemes a fenti lépéseknél még sima fájlkezelőt és git basht használni, és csak 
    ezután nyissátok meg az IntelliJ-t.**  

3.  `Ha szükséges, állítsátok be az SDK-t.`\
    (Nálam fel szokott ugrani egy kék sáv, amin keresztül ez azonnal megy.
    Ha nem ugrik fel, az 5-ös pont szerint nyissátok meg a Module Settings-et, 
    és a lenti megjegyzésben írt füleknél állítsátok be az SDK verziót. 
    Értelemszerűen mindenhol ugyanazt.)
    
4. `Jelöljétek meg a Code\Szkeleton\src mappát  Source mappának.`\
    3.1 Jobb klikk a src mappára a bal felső Project ablakban.\
    3.2 Mark Directory as -> Sources Root
    
    >Ha jól csináltátok, kék lett a mappa színe.

5. `Compiler output beállítása`\
    5.1 Project ablakban jobb klikk a Mitbewohnerre\
   5.2 Open Module Settings\
   (OR Bal klikk a Mitbewohnerre és **F4**)\
   5.3 Project fül -> Project compiler output\
   Itt adjátok meg a *Code\Szkeleton\out* mappát. Nagy valószínűséggel ez 
   még nem létezik nálatok, a mappa létrehozását akár a felugró fájlkiválasztós 
   ablakból is meg tudjátok tenni.\
   ![New folder guide](https://i.imgur.com/TtFA4PX.png)
   
   >Ha jól csináltátok, az out mappa narancssárga színű lett.
   
---------------------------
Ha ezek után nem tudjátok futtatni a main-t, nézzétek meg, hogy a Module Settings-en 
belül mindenhol ugyanazt az SDK verziót látjátok (Project, Modules, SDKs fülek relevánsak).
Egy Rebuild Project is csodákra képes. Ha még mindig nem jó a cucc, indítsátok újra az IntelliJ-t. 
Ha ez se működött, lehet kutakodni Stackoverflown. :P 

Sok sikert mindenkinek! 

Domi

---------------------------
Ide betolakszom, mivel lehet nem csak nekem nem hajlandó lefordulni a projekt semmilyen Language Levellel.
**"Error:java: invalid source release: 12"** hibát kapok, a szám 9-13 között tetszőleges, a beállításaimtól függően
(Stackoverflow, újraindítás, ... eddig nem segített).
Ha valakinek működik, kérem írja le, neki mi van a Project Settins/Project/Project language level-be beállítva.
Ha valakinek szintén nem működik, az is jelezheti.

Tomi


   
   
