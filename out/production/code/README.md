# Refactoring
1. In NewPatronView file I replaced same code by a function named 'MakeField' and used it everywhere `nickField = MakeField("Nick Name",patronPanel);`
2. Also `private JLabel nickLabel, fullLabel, emailLabel;` these variables were not used anywhere so I removed them. Initially they were declared globally.
3. In NewPatronView file I replaced same code and made general function to add button 'MakeButton'
4. Same way generalised function for making a panel 'MakePanel' is also made.
5. Generalised function to set window position on the screen
6. Added comments along with wherever they were missing.
7. Same code to setup buttons, windows, setting their positions, making panels was written many times so I made new file  `ViewComponents` and write all 
these functions in single file and inherited other classes from this class (here we need to tell advantages of inheritence).
8. Made View Components class `abstract` because we don't want to make any direct object of that class.
9. New file made for calculating score because in file `Lane.java` initially there were alot of functions and it was also doing tasks which it was not meant to 
do like there should have been some other file to calculate score instead of doing same in lane Class , so for that I made class `CalculateScore` and also in this new 
class methods are public and not static so that they can only be accessed by objects and not directly.
10. In file `Bowler.java` I improved a conditional expression : if was used three times which could be easily replaced by else...if so that it is not checked 
unnecessarily.
 