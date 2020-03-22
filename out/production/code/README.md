# Refactoring
1. In NewPatronView file I replaced same code by a function named 'MakeField' and used it everywhere `nickField = MakeField("Nick Name",patronPanel);`
2. Also `private JLabel nickLabel, fullLabel, emailLabel;` these variables were not used anywhere so I removed them. Initially they were declared globally.
3. In NewPatronView file I replaced same code and made general function to add button 'MakeButton'
4. Same way generalised function for making a panel 'MakePanel' is also made.