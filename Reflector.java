//*****************************************************************//
// FILE NAME: Reflector.java                                       //
//                                                                 //
// DESCRIPTION: The Reflector object is similar to the Rotor       //
// object. However, it does not rotate and symmetrically encodes   //
// characters.                                                     //
//*****************************************************************//

public class Reflector {
	static final int MAX = Rotor.MAX;
	private char[] leftToRightWiring = new char[MAX];
   
   /**
    * Creates a Reflector object, initializes char array
    * 
    * @param s String used to reflect characters during 
    *          encoding/decoding
    */
	public Reflector(String s) {
		leftToRightWiring = s.toCharArray();
	}
	
   /**
    * Reflects a char via the array initialized in the constructor
    * 
    * @param c the character to be refi fiflected
    * @return  the reflected character
    */
	public char encodeLR(char c) {
		return leftToRightWiring[c - 'A']; 
	}
}