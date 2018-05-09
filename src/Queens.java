import java.util.ArrayList;
import java.util.HashMap;

public class Queens {

    // main @ 130. sor

    static ArrayList<String> perm = new ArrayList<>();
    private ArrayList<String> good = new ArrayList<>();
    private ArrayList<String> unique = new ArrayList<>();


    public static void perm1(String s) {
        perm1("", s);
    }
    private static void perm1(String prefix, String s) {
        int n = s.length();
        if (n == 0) {
            Queens.perm.add(prefix);
        }
        else {
            for (int i = 0; i < n; i++)
                perm1(prefix + s.charAt(i), s.substring(0, i) + s.substring(i+1, n));
        }
    }

    public void goodCheck (String num) {
        int counter = 0;
        for (int i = 0; i < num.length()-1; i++) {
            int firstNumber = num.charAt(i);
            for (int j = i+1; j < num.length(); j++) {
                int secondNumber = num.charAt(j);
                if (firstNumber + j - i == secondNumber || firstNumber - j + i == secondNumber) {
                    counter++;
                }
            }
        }
        if (counter == 0) good.add(num);
    }

    public String rotate90 (String num) {
        StringBuffer sb = new StringBuffer("");
        int max = num.length();
        for (int i = 1; i <= max; i++) {
            sb.append(max-num.indexOf(Integer.toString(i).charAt(0)));
        }
        return sb.toString();
    }

    public String rotate180 (String num) {
        StringBuffer sb = new StringBuffer("");
        int max = num.length();
        for (int i = max-1; i >= 0; i--) {
            sb.append(max - Character.getNumericValue(num.charAt(i)) + 1);
        }
        return sb.toString();
    }

    public String rotate270 (String num) {
        return rotate90(rotate180(num));
    }

    public String mirror (String num) {
        StringBuffer sb = new StringBuffer("");
        int max = num.length();
        for (int i = max - 1; i >= 0; i--) {
            sb.append(Character.getNumericValue(num.charAt(i)));
        }
        return sb.toString();
    }

    public void uniqueCheck () {
        for (int i = 0; i < good.size(); i++) {
            if (!unique.contains(rotate90(good.get(i))) &&
                    !unique.contains(rotate180(good.get(i))) &&
                    !unique.contains(rotate270(good.get(i))) &&
                    !unique.contains(mirror(good.get(i))) &&
                    !unique.contains(mirror(rotate90(good.get(i)))) &&
                    !unique.contains(mirror(rotate180(good.get(i)))) &&
                    !unique.contains(mirror(rotate270(good.get(i))))) {
                unique.add(good.get(i));
            }
        }
    }

    public char [][] boardGen (String str) {
        char [][] matrix = new char[str.length()][str.length()];
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < str.length(); j++) {
                matrix[i][j] = '.';
            }
        }

        return matrix;
    }

    public char[][] changeChar (char[][] matrix, String num) {
        HashMap<Integer, Integer> positions = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            positions.put(i, Character.getNumericValue(num.charAt(i)));
        }
        positions.forEach((k,v) -> {
            matrix[k][v-1] = 'X';
        });
        return matrix;

    }

    public void print (char[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            StringBuffer row = new StringBuffer("");
            for (int j = 0; j < matrix[i].length; j++) {
                row.append(matrix[i][j] + "  ");
            }
            System.out.println(row.toString());
        }
    }

    public void printAll (String str) {
        System.out.println("n = " + str.length() + "\n");
        for (int i = 0; i < unique.size(); i++) {
            System.out.println(i+1 + ". variation:");
            print(changeChar(boardGen(str), unique.get(i)));
            System.out.println("______________________\n");

        }
        System.out.println("Total: " + unique.size() + " unique variations printed.");
    }

    public static void main(String[] args) {
        String boardSize = "123456789"; // modositsd a szamsort mas oldalhosszu tabla megadasahoz - majd egyszer meg az is lehet, hogy szamot fog kerni...
        Queens q = new Queens();
        perm1(boardSize);
        for (int i = 0; i < q.perm.size(); i++) { // ezt majd beviszem a goodCheck metodusba, de most mar aludni akarok...
            q.goodCheck(q.perm.get(i));
        }
        q.uniqueCheck();
        q.printAll(boardSize);


/*
        System.out.println(q.perm.size());
        System.out.println(q.good);
        System.out.println(q.good.size());
        System.out.println(q.unique);
        System.out.println(q.unique.size());

*/




    }
}

