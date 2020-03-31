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
16. All code was already within classes so `Encapsulation` was followed.
17. Inherited `LaneStatusView` from `ViewComponents` and also corrected unecessary if conditions from file .
18. Deprecated method of win.hide and win.show was used I replaced it by setVisible(true/false)
19. Replaced C type array declaration in various files with java type array declaration for good coding styles(write its uses from net).
20. Remove unnecessary public modifier in interfaces : https://stackoverflow.com/questions/17011374/are-public-and-public-final-redundant-for-interface-fields
21. Removed unnecessary imports.
22. `LaneEventInterface` was not linked with any files it was useless , So I linked it with `LaneEvent` file.
23. Replaced for loop with enhanced for loop write advantages in report : https://stackoverflow.com/questions/3328672/what-are-the-advantages-of-enhanced-for-loop-and-iterator-in-java
24. Removed redundant casting to various fields advantages : Unnecessary casting expressions make the code harder to read and understand.
25. Used `final` keyword wherever  it was possible : https://stackoverflow.com/questions/137868/using-the-final-modifier-whenever-applicable-in-java
26. Replaced manual array copy in file `PinsetterEvent` with copy function so as to prevent from bugs.
27. Remove unnecessary creation of temporary objects when converting from primitive types to Strings.         
    For example:    new Integer(3).toString()  will be reported, and can be automatically converted to:  Integer.toString(3)                                                        
28. Replaced redundant class fields that can be replaced with local variables. If all local usages of a field are preceded by assignments to that field, the field can be removed 
    and its usages replaced with local variables.              
29. There were many empty catch statements in which nothing was printed I corrected them.
30. Vector's object was iterated without  asserting that it was not null. It could cause error if vector would be null. So I added an assert statement.(`ScoreReport` file))
`assert v != null;
 Iterator scoreIt = v.iterator();`                                       
31. Deleted empty if or else statements.   
32. Replaced `catch (FileNotFoundException e) {
              			System.err.println("Error..." + e);
              		} catch (IOException e) {
              			System.err.println("Error..." + e);
              		}` with ` catch (IOException e) {
                             			System.err.println("Error..." + e);
                             		}`                         
33. Reports any if statements with then and else branches which are both assignment expressions or both return statements. The same semantics can be expressed more compactly, and arguably more clearly, with a conditional expression. Example:
     `if (foo == null) {
       bar = null;
     } else {
       bar = foo.get();
     }`
   may be expressed as:
    ` bar = foo == null ? null : foo.get();`
34. Inverted negated if statements which increases clarity.
35. At many places while throwing exception IOException, FileNotFoundException both  were written but second is already included in IOException so replaced it.
36. Removed cyclic dependency in interface made (write its advantages).
37. In order to improve performance I replaced string concatenation using '+' in loops by function append.
For that I replaced string by String builder. This has better performance because with + it always copies full string.
38. Reports any attempt to instantiate a new Long, Integer, Short or Byte object from a primitive long, integer, short or byte argument. It may be more efficient to use the static method valueOf() here (introduced in Java 5), which will cache objects for values between -128 and 127 inclusive.
Replaced `(new Integer(((int[]) le.getScore()` with `(Integer.valueOf(((int[]) le.getScore()
39. The variable was initialized a value but there was no need as it was assigned afterwards.
` 
 
 
 		

