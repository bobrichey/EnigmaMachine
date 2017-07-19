//*****************************************************************//
// FILE NAME: Rotor.java                                           //
//                                                                 //
// DESCRIPTION: The Rotor object is used to construct an           //
// EnigmaMachine. It contains arrays for encoding chars left to    //
// right and right to left in O(1) time. A Rotor is incremented    // 
// each time a char is encoded and can be set to a given position. //                                 
//*****************************************************************//

public class Rotor {
	private int position;
	static final int MAX = 26;
	private char[] leftToRightWiring = new char[MAX];
	private char[] rightToLeftWiring = new char[MAX];
	
	/**
	 * Creates a Rotor object, initializes arrays for encoding/decoding
    * characters
	 * 
	 * @param s the String used for encoding/decoding characters
	 */
	public Rotor(String s) {
		leftToRightWiring = s.toCharArray();
		
		for (int i = 0; i < MAX; i++) {
			rightToLeftWiring[s.charAt(i) - 'A'] = (char)(i + 'A');
		}
	}
	
	/**
	 * Increments a Rotor's position until it reaches MAX, then returns
    * it to zero
	 * 
	 * @return true if the rotor's position is currently zero,
    *         otherwise false
	 */
	public boolean inc() {
		position = ++position % MAX;
		return position == 0;
	}
	
	/**
	 * Sets a Rotor's position
	 * 
	 * @param n the position a Rotor will be set to
	 */
	public void set(int n) {
		position = n; 
	}
	
	/**
	 * Encodes/decodes a character as it passes left to right through an
    * EnigmaMachine
	 * 
    * @param c the character to be encoded/decoded
	 * @return  the encoded/decoded char
	 */
	public char encodeLR(char c) {
		return leftToRightWiring[(c - 'A' + position) % MAX]; 
	}

	/**
	 * Encodes/decodes a character as it passes right to left through an
    * EnigmaMachine 
	 * 
    * @param c the character to be encoded/decoded
	 * @return  the encoded/decoded char
	 */
	public char encodeRL(char c) {
      c = (char)(rightToLeftWiring[c - 'A'] - position);
      
      if (c < 'A') {
         c += MAX;
      }
      return c;   
	}
}