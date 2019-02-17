// Vigenere_Cipher
// Last Modified: 3/5/16

public class VigenereCipher {
    // data fields
    public static final int ENCRYPT = 0, DECRYPT = 1;
    private String text = null, keyWord = null;
    private int mode = ENCRYPT;

    // Cipher Table
    private static final int sizeOfAlpha = 26;
    private static final char[][] codeTable = new char[sizeOfAlpha][sizeOfAlpha];
    private static final char[] alphabet = new char[sizeOfAlpha];


    // Constructors
    public VigenereCipher(int mode) {
        this.mode = ((mode == 1) ? DECRYPT : ENCRYPT);
        fillAlpha();
        fillTableCode();
    }

    // Getters
    public String getText() {
        return text;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public int getMode() { return mode; }

    // Setters
    public void setText(String text) {
        this.text = text;
    }

    public void setMode(int mode) {
        this.mode = ((mode == 1) ? DECRYPT : ENCRYPT);
    }

    public void setKeyWord(String keyWord) {
        keyWord = keyWord.toUpperCase();
        //String text = (mode == DECRYPT ? cipherText : plainText);

        int multiple = text.length()/keyWord.length();
        int leftOver = text.length()%keyWord.length();
        for (int index = 0; index < multiple - 1; index++)
            keyWord += keyWord;
        keyWord += keyWord.substring(0, leftOver);
        this.keyWord = keyWord;
    }

    ////////////////////////////////ENCRYPT////////////////////////////////////
    public String getFinalText() {
        String finalText = "";
        int intText, intKey;
        char ch;

        try {
            if (mode == ENCRYPT) {
                for (int index = 0; index < text.length(); index++)
                {
                    if (Character.isUpperCase(text.charAt(index))) {
                        intText = findIndexOfLetter(text.charAt(index));
                        intKey = findIndexOfLetter(keyWord.charAt(index));
                        ch = codeTable[intText][intKey];
                        finalText += ch;
                    }
                    else if (Character.isLowerCase(text.charAt(index))){
                        intText = findIndexOfLetter(Character.toUpperCase(text.charAt(index)));
                        intKey = findIndexOfLetter(keyWord.charAt(index));
                         ch = codeTable[intText][intKey];
                        finalText += Character.toLowerCase(ch);
                    }
                    else {
                        finalText += text.charAt(index);
                    }
                }
            }
            else if (mode == DECRYPT) {
                for (int index = 0; index < text.length(); index++)
                {
                    if (Character.isUpperCase(text.charAt(index))) {
                        intKey = findIndexOfLetter(keyWord.charAt(index));
                        for (int index1 = 0; index1 < sizeOfAlpha; index1++)
                            if (codeTable[intKey][index1] == text.charAt(index))
                                finalText += alphabet[index1];
                    }
                    else if (Character.isLowerCase(text.charAt(index))){
                        intKey = findIndexOfLetter(keyWord.charAt(index));
                        for (int index1 = 0; index1 < sizeOfAlpha; index1++)
                            if (codeTable[intKey][index1] == Character.toUpperCase(text.charAt(index)))
                                finalText += Character.toLowerCase(alphabet[index1]);
                    }
                    else {
                        finalText += text.charAt(index);
                    }
                }
            }
        }
        catch (Exception e) {
            finalText = "Process failed..";
        }

        return finalText;
    }

    ////////////////////////////////TABLE//////////////////////////////////////
    private void fillAlpha() {
        for (int index = 0; index < sizeOfAlpha; index++)
            alphabet[index] = (char)('A' + index);
    }

    private void fillTableCode() {
        for (int index = 0; index < sizeOfAlpha; index++) {
            int counter = index;
            for (int index1 = 0; index1 < sizeOfAlpha; index1++) {
                codeTable[index][index1] = alphabet[counter++];
                if (counter > 25)
                    counter = 0;
            }
        }
    }

    private int findIndexOfLetter(char ch) {
        int index = -1;
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i] == ch)
                index = i;
        }
        return index;
    }

    public void printTableCode() {
        for (int index = 0; index < sizeOfAlpha; index++) {
            for (int index1 = 0; index1 < sizeOfAlpha; index1++) {
                System.out.print(codeTable[index][index1] + " ");
            }
            System.out.println("");
        }
    }
}
