# Molar Mass Calculator
 
Personal project created to calculate and print the molar mass of a compound.

Run the main function of MolarMassCalculator with the compound to calculate the mass as a string, written in chemical formula notation (without subscripts). Accepts parentheses, as long as there are no nested parentheses and number of sub-compounds immediately follows.

Example use in the command line:
>> javac MolarMassCalculator.java
>> java MolarMassCalculator "H2O"

18.015 g/mol

>> java MolarMassCalculator "NaCl"   

58.44 g/mol

>> java MolarMassCalculator "C12H22O11"

342.297 g/mol

>> java MolarMassCalculator "(CH3)3CO" 

73.115 g/mol
