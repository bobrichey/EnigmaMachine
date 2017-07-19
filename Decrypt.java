//*****************************************************************//
// FILE NAME: Decrypt.java                                         //
//                                                                 //
// DESCRIPTION: Decrypts a file using a specific EnigmaMachine's   //
// rotor settings. The rotor settings are entered on line 22 and   //
// the encrypted file is entered on line 25.                       //
//*****************************************************************//

import java.io.File;
import java.util.Scanner;

public class Decrypt {
   public static void main(String[] args) throws Exception {
      Rotor r1 = new Rotor("QWERTYUIOPLKJHGFDSAZXCVBNM");
      Rotor r2 = new Rotor("ZAQWSXCDERFVBGTYHNMJUIKLOP");
      Rotor r3 = new Rotor("PLOKMIJNUHBYGVTFCRDXESZWAQ");
      Reflector rf = new Reflector("NPKMSLZTWQCFDAVBJYEHXOIURG");
   	
      EnigmaMachine em = new EnigmaMachine(r1, r2, r3, rf);
      
      // rotor settings discovered using FindRotorSettings.java 
      em.setRotors(18, 19, 14);
     
      String filename = "encrypted.text";
      File file = new File(filename);
      Scanner input = new Scanner(file);
   	
      while (input.hasNext()) {
         System.out.println(em.encodeLine(input.nextLine()));
      }
      input.close();
   }
}