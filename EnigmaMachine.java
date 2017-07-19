//*****************************************************************//
// FILE NAME: EnigmaMachine.java                                   //
//                                                                 //
// DESCRIPTION: A simulated World War II Enigma Machine. It's      //
// composed of three Rotors and a Reflector. It allows users to    //
// encode/decode text and set the rotors to a given position.      //                                            //
//*****************************************************************//

public class EnigmaMachine {
   private Rotor r1;
   private Rotor r2;
   private Rotor r3;
   private Reflector r;
	
	/**
	 * Creates an EnigmaMachine object with three Rotors and a Reflector
	 * 
	 * @param r1 the first Rotor in the EnigmaMachine
	 * @param r2 the second Rotor in the EnigmaMachine
	 * @param r3 the third Rotor in the EnigmaMachine
	 * @param r  the Reflector in the EnigmaMachine
	 */
   public EnigmaMachine(Rotor r1, Rotor r2, Rotor r3, Reflector r) {
      this.r1 = r1;
      this.r2 = r2;
      this.r3 = r3;
      this.r = r;
   } 
	
	/**
	 * Encodes/decodes a character using the EnigmaMachine's Rotors and 
    * Reflector, then increments the Rotors
	 * 
	 * @param c the character to be encoded/decoded
	 * @return  the encoded/decoded character
	 */
   private char encodeChar(char c) {
      c = r1.encodeLR(c);
      c = r2.encodeLR(c);
      c = r3.encodeLR(c);
      c = r.encodeLR(c);
      c = r3.encodeRL(c);
      c = r2.encodeRL(c);
      c = r1.encodeRL(c);
      incrementRotors();
      return c;
   }
	
	/**
	 * Encodes/decodes a line of text, ignores non-uppercase, 
    * non-alphabetic characters
	 * 
	 * @param s the String to be encoded/decoded
	 * @return  the encoded/decoded string
	 */
   public String encodeLine(String s) {
      String encodedLine = "";
   	
      for (int i = 0; i < s.length(); i++) {
         char c = s.charAt(i);

         if (c >= 'A' && c <= 'Z') {
            c = encodeChar(c);
         }
         encodedLine += c;
      }
      return encodedLine;
   }
	
	/**
	 * Sets the positions of an EnigmaMachine's Rotors
	 * 
	 * @param a the position that Rotor 1 will be set to
	 * @param b the position that Rotor 2 will be set to
	 * @param c the position that Rotor 3 will be set to		
	 */
   public void setRotors(int a, int b, int c) {
      r1.set(a);
      r2.set(b);
      r3.set(c);
   }
	
	/**
	 * Increments an EnigmaMachine's Rotors, a Rotor only increments if
    * the position of the Rotor before it has returned zero
	 */
   private void incrementRotors() {
      if (r1.inc()) {
         if (r2.inc()) {
            r3.inc();
         }
      }
   }
}