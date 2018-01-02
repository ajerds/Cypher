/**
 * encodes and decodes a message given a key and an initial letter for the cipher table
 */

public class Prog010Cipher {
    // INSTANCE VARIABLES
    char [ ] keyList;// an array of the key
    char [ ][ ] cipherTable = new char[26][26];// a two dimensional array of the cipher table

    // METHODS
    /**
     * encodes the message using a provided key and a cipher table
     * param message
     * return
     */
    String encode( String message ) {
        String result;
        message = message.toUpperCase();
        char [ ] messageArray = new char[ message.length() ];
        char [ ] resultArray = new char[ messageArray.length ];
        int n;
        int k = 0;

        // converts the message in to an array
        for ( n = 0; n < message.length(); n++ ) {
            messageArray[ n ] = message.charAt( n );
        }
        // encodes the message character by character
        for ( int i = 0; i < messageArray.length; i++ ) {

			//reuses the key list once it runs out
        	if ( i%(keyList.length) == 0 ) {
                k = 0;
            }
            int h = ((int)( keyList[ k ] ) - 65 );
            int j = ((int)( messageArray[ i ] ) - 65 );

            // inserts a space if no letter in found

            if ( j < 0) {
				resultArray[i] = ' ';
            }
            else {
                resultArray[ i ] = cipherTable[ h ][ j ];
            }
			k++;
        }
        //converts the result into a string
        result = new String( resultArray );

        return result;
    }

    /**
     * decodes the message
     * param message
     * return
     */
    String decode( String message ) {
        String result;
        char [ ] messageArray = new char[ message.length( ) ];
        char [ ] resultArray = new char[ message.length( ) ];
        int h = 0;

        //result array is filled with space characters
        for ( int k = 0; k < resultArray.length; k++ ) {
            resultArray[k] = ' ';
        }
        // converts message into an array
        for (int n = 0; n < message.length(); n++) {
            messageArray[n] = message.charAt(n);
        }
        // decodes each letter in the array
        for (int i = 0; i < messageArray.length; i++) {
            for (int j = 0; j < cipherTable[0].length; j++) {

				//reuses the key list once it runs out
                if (i%(keyList.length) == 0) {
                    h = 0;
                }

                //decodes
                if (messageArray[i] == cipherTable[(int)(keyList[h]) - 65][j]) {
                    resultArray[i] = (char) (j + 65);
                    break;
                }
            }
			h++;
        }
        result = new String(resultArray);
        return result;
    }

    // CONSTRUCTOR
    /**
     * Cunstructs the class Prog2Cipher
     * param code
     * param key
     */
    public Prog010Cipher( char code, String key ) {

    	int k = 0;

        char[] oldKeyList = new char[key.length()];

        // converts the key in to an array and stores it in data field keyList
        for (int n = 0; n < key.length(); n++) {
			if ((int)key.charAt(n) != 32 ) {
				oldKeyList[k] = key.charAt(n);
				k++;
			}
		}
		if ( k != oldKeyList.length) {
        	keyList = new char[k];
			for (int j = 0; j < k; j++) {
				keyList[j] = oldKeyList[j];
			}
		}
		else {
			keyList = new char[key.length()];
        	for (int h = 0; h < oldKeyList.length; h++) {
        		keyList[h] = oldKeyList[h];
			}
		}

        // creates the cipher table
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {

                cipherTable[i][j] = code;

                if (code == 'Z') {
                    code = 'A';
                }
                else {
                    code = ( char )((( int ) code) + 1);
                }
            }
            if (code == 'Z') {
                code = 'A';
            }
            else {
				code = (char) (((int) code) + 1);

			}
        }
    }
    // MAIN (TEST) Method
    /**
     * * tests the class by giving it H as the code, and BABBAGE as the key, provides the message Happy Birthday and expects
     * * param args
     */
    public static void main(String[ ]args ) {
        // Testing only works if using VM argument -ea
        Prog010Cipher self = new Prog010Cipher( 'H', "BABBAGE" );

        assert "PHXXF MQYBPKNJ".equals(self.encode( "HAPPY BIRTHDAY" ) );
		assert "HAPPY BIRTHDAY".equals(self.decode( "PHXXF MQYBPKNJ" ) );

		Prog010Cipher self1 = new Prog010Cipher( 'H', "SECRET" );

		assert "ZECYNK LC OAVY".equals(self1.encode( "ATTACK AT DAWN" ) );
		assert "ATTACK AT DAWN".equals(self1.decode( "ZECYNK LC OAVY" ) );

		Prog010Cipher self2 = new Prog010Cipher( 'W', "SECRET" );

		assert "OTRNCZ AR DPKN".equals(self2.encode( "ATTACK AT DAWN" ) );
		assert "ATTACK AT DAWN".equals(self2.decode( "OTRNCZ AR DPKN" ) );

		Prog010Cipher self3 = new Prog010Cipher( 'W', "NOW IS THE TIME" );
		assert "JDLEQZ AI LAFX".equals(self3.encode( "ATTACK AT DAWN" ) );
		assert "ATTACK AT DAWN".equals(self3.decode( "JDLEQZ AI LAFX" ) );

		Prog010Cipher self4 = new Prog010Cipher( 'W', "NOW IS THE TIME" );
		assert "JDLEQZ AI LAFX".equals(self4.encode( "ATTACK AT DAWN" ) );
		assert "ATTACK AT DAWN".equals(self4.decode( "JDLEQZ AI LAFX" ) );

		Prog010Cipher self5 = new Prog010Cipher( 'W', "NOW IS THE TIME" );
		assert "JDLEQZ AI LAFX".equals(self5.encode( "ATTACK AT DAWN" ) );
		assert "ATTACK AT DAWN".equals(self5.decode( "JDLEQZ AI LAFX" ) );

		Prog010Cipher self6 = new Prog010Cipher( 'W', "NOW IS THE TIME" );
		assert "JDLEQZ AI LAFX".equals(self6.encode( "ATTACK AT DAWN" ) );
		assert "ATTACK AT DAWN".equals(self6.decode( "JDLEQZ AI LAFX" ) );




    }


}