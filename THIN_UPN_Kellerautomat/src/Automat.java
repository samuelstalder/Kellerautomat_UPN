import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Automat {

    enum State {
        q0, q1, q2, q3, q4
    }

    public static char EPSILON = '0';
    State currentState = State.q0;

    /**
     * Method checks if word is part of a alphabet
     * @param word
     * @return boolean
     * true: if the last state is q4
     * false: if the last state is q0, q1, q2, q3 or stack-exception
     */
    public boolean check(String word) {
        currentState = State.q0;
        word += "00";
        Keller keller = new Keller();
        keller.printKeller();
        try {
            for (int i = 0; i < word.length(); i++) {
                char inputSymbol = word.charAt(i);
                char kellerSymbol = keller.pop();
                switch(currentState) {
                    case q0:
                        // D,$/D$
                        if (inputSymbol == 'D' && kellerSymbol == '$') {
                            keller.push('D', '$');
                            currentState = State.q1;
                        }
                        break;
                    case q1:
                        // D,D/DD
                        if (inputSymbol == 'D' && kellerSymbol == 'D') {
                            keller.push('D', 'D');
                            currentState = State.q2;
                        } // O,D/e
                        else if (inputSymbol == 'O' && kellerSymbol == 'D') {
                            keller.push(EPSILON);
                            currentState = State.q1;
                        } // e,D/e
                        else if (inputSymbol == EPSILON && kellerSymbol == 'D') {
                            keller.push(EPSILON);
                            currentState = State.q3;
                        }
                        break;
                    case q2:
                        // O,D/e
                        if (inputSymbol == 'O' && kellerSymbol == 'D') {
                            keller.push(EPSILON);
                            currentState = State.q1;
                        } // D,D/DD
                        else if (inputSymbol == 'D' && kellerSymbol == 'D') {
                            keller.push('D', 'D');
                            currentState = State.q2;
                        }
                        break;
                    case q3:
                        // e,$/e
                        if (inputSymbol == EPSILON && kellerSymbol == '$') {
                            keller.push(EPSILON);
                            currentState = State.q4;
                        }
                        break;
                }
                keller.printKeller();
            }
        } catch (Exception e) {
            return false;
        }
        //returns true, wenn der aktuelle Zustand ein gültiger Endzustand ist
        return (currentState == State.q4);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Automat UPNAutomat = new Automat();
        boolean thinIsFun = true;
        while(thinIsFun) {
            String word = reader.readLine();
            System.out.println((UPNAutomat.check(word) ? "bingooo": "böööp"));
        }
    }
}


