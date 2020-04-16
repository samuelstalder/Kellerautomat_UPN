public class Keller {

    private int pointer = 0;
    private char[] kellerArray = new char[] {'$', '0', '0', '0', '0', '0', '0', '0', '0', '0'};

    public void push(char secondSymbol, char firstSymbol) {
        kellerArray[++pointer] = firstSymbol;
        kellerArray[++pointer] = secondSymbol;
    }

    public void push(char firstSymbol) {
        if (firstSymbol != Automat.EPSILON) {
            kellerArray[pointer++] = firstSymbol;
        }
    }

    public char pop() {
        char res = kellerArray[pointer];
        kellerArray[pointer] = '0';
        pointer --;
        return res;
    }


    public void printKeller() {
        for (int i = 0; i < kellerArray.length; i++) {
            System.out.print(kellerArray[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < pointer; i++) {
            System.out.print("  ");
        }
        System.out.print("i");
        System.out.println();
    }
}
