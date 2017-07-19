//*****************************************************************//
// FILE NAME: English.java                                         //
//                                                                 //
// DESCRIPTION: Organizes methods used for detecting if a          //
// decryption in FindRotorSettings.java is likely to be English.   //
// Methods are provided for counting letters in a decryption and   //
// for counting how many letters do not fit the English signature. //
//*****************************************************************//

public class English {
   private static int totalNumberOfLetters;
   private static final int MAX = Rotor.MAX;
   private static double[] engLetterFreq = {8.1, 1.6, 3.2, 3.6, 12.3, 2.3, 
   	1.6, 5.1, 7.2, 0.1, 0.5, 4.0, 2.2, 7.2, 7.9, 2.3, 0.2, 6.0, 6.6, 9.6,
   	3.1, 0.9, 2.0, 0.2, 1.9, 0.1};
	
   private static int[] engLetterDeviation = {10, 50, 30, 30, 10, 30, 50, 
   	20, 15, 100, 80, 30, 30, 20, 20, 30, 100, 30, 20, 15, 30, 60, 40, 100,
   	40, 100};
	
   private static int[] letterCount = new int[MAX];
	
   /**
    * Counts the total number of uppercase letters in a String, as
    * well as the number of occurrences of each letter, storing them
    * in an array
    *
    * @param s String whose letters are to be counted
    */
   public static void countAllLetters(String s) {
      totalNumberOfLetters = 0;

      for (int i = 0; i < MAX; i++) {
         letterCount[i] = 0;
      }
      
      for (int i = 0; i < s.length(); i++) {
         if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
            letterCount[s.charAt(i) - 'A']++;
            totalNumberOfLetters++;
         }
      }
   }
   
   /**
    * Examines the characters held in the fields of the English class
    * and determines how closely they match the signature of English
    *
    * @param mult determines how far the found frequency of a given 
    *             letter may be from its English signature before
    *             being counted as an error
    * @return     the number of alphabetic characters that deviate
    *             from English
    */
   public static int getErrorCount(double mult) {
      int errorCount = 0;
            
      for (int i = 0; i < MAX; i++) {
         double allowableDeviation = (mult * 
            engLetterDeviation[i] * 0.01) * engLetterFreq[i];
            
         double lowerBound = engLetterFreq[i] - allowableDeviation;
         double upperBound = engLetterFreq[i] + allowableDeviation;    
         double frequency = ((double)(letterCount[i]) / 
            totalNumberOfLetters) * 100;
         
         if (frequency < lowerBound || frequency > upperBound) {
            errorCount++;
         }
      }
      return errorCount;
   }
}