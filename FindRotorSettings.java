//*****************************************************************//
// FILE NAME: FindRotorSettings.java                               //
//                                                                 //
// DESCRIPTION: Sequentially decodes part of a text file (line 45) // 
// using all settings on an EnigmaMachine. Likely matches are      //
// printed for the user. Three tunable constants (lines 50-52) are //  
// provided to narrow the displayed matches.                       //
//*****************************************************************//

import java.io.File;
import java.util.Scanner;

public class FindRotorSettings {
   public static void main(String[] args) throws Exception {
      Rotor r1 = new Rotor("QWERTYUIOPLKJHGFDSAZXCVBNM");
      Rotor r2 = new Rotor("ZAQWSXCDERFVBGTYHNMJUIKLOP");
      Rotor r3 = new Rotor("PLOKMIJNUHBYGVTFCRDXESZWAQ");
      Reflector rf = new Reflector("NPKMSLZTWQCFDAVBJYEHXOIURG");
   	
      EnigmaMachine em = new EnigmaMachine(r1, r2, r3, rf);
      
      for (int i = 0; i < Rotor.MAX; i++) {
         for (int j = 0; j < Rotor.MAX; j++) {
            for (int k = 0; k < Rotor.MAX; k++) {
               checkRotorSettings(em, i, j, k);
            }
         }
      }
   }
   
   /**
    * Partially decrypts a document using a given EnigmaMachine's
    * rotor settings, storing the result in a String and testing
    * if it is likely to be English.
    * 
    * @param em the EnigmaMachine to be used for decryption 
    * @param a  the setting of the EnigmaMachine's first rotor 
    * @param b  the setting of the EnigmaMachine's second rotor 
    * @param c  the setting of the EnigmaMachine's third rotor 
    */
   public static void checkRotorSettings(
      EnigmaMachine em, int a, int b, int c) throws Exception {
      
      em.setRotors(a, b, c);
      
      String filename = "encrypted.text";
      File file = new File(filename);     
      Scanner input = new Scanner(file);
      
      // TUNABLE CONSTANTS
      final int NUMBER_OF_LINES = 3;
      final double MULTIPLIER = 3.0;
      final int ERRORS_ALLOWED = 5;
      
      String s = "";
   
      for (int i = 0; i < NUMBER_OF_LINES; i++) {
         s += em.encodeLine(input.nextLine()) + "\n";
      }
      input.close();
      
      English.countAllLetters(s);
   
      if (English.getErrorCount(MULTIPLIER) < ERRORS_ALLOWED) {
         System.out.printf("%d, %d, %d %s", a, b, c, s);
      }
   }
}