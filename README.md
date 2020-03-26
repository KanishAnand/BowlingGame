# Refactoring
1. In NewPatronView file I replaced same code by a function named 'MakeField' and used it everywhere `nickField = MakeField("Nick Name",patronPanel);`
2. Also `private JLabel nickLabel, fullLabel, emailLabel;` these variables were not used anywhere so I removed them. Initially they were declared globally.
3. In NewPatronView file I replaced same code and made general function to add button 'MakeButton'
4. Same way generalised function for making a panel 'MakePanel' is also made.
5. Generalised function to set window position on the screen
6. Added comments along with wherever they were missing.
7. Same code to setup buttons, windows, setting their positions, making panels was written many times so I made new file  `ViewComponents` and write all 
these functions in single file and inherited other classes from this class (here we need to tell advantages of inheritence).Here we have also done abstraction : explain in report.
8. Made View Components class `abstract` because we don't want to make any direct object of that class.
9. New file made for calculating score because in file `Lane.java` initially there were alot of functions and it was also doing tasks which it was not meant to 
do like there should have been some other file to calculate score instead of doing same in lane Class , so for that I made class `CalculateScore` and also in this new 
class methods are public and not static so that they can only be accessed by objects and not directly. This thing is called `Cohesion`(mention its uses in report). By making new file we made it more cohesive.
10. In file `Bowler.java` I improved a conditional expression : if was used three times which could be easily replaced by else...if so that it is not checked 
unnecessarily.
11. In `PinSetterView.java` same code was repeated 10 times to setup all 10 pins and also to set up 4 rows , So I made a generalised function which setup rows as
well as add 10 pins in them 
12. if replaced by if....else if in `EndGamePrompt.java` for selection of yes/no button.
13. In file `EndGamePrompt.java` I removed repeated code and also removed unused variables.
14. In file `EndGameReport.java` also inheritence is made from Viecomponents to use functions and also if...else thing was done with buttons.
15. Removed unused variables from `ScoreReport.java` and also merged code lines like to put `\n` 3 times three line of code was written which could be merged in 1 line.
16. All code was already within classes so `Encapsulation` was followe.