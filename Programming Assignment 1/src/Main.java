import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    Boolean[] variables;
    int[][] clauseArray;

    public Main() {
    }

    public static void main(String[] args) throws FileNotFoundException {
        Main main = new Main();
        Scanner scanner = new Scanner(System.in);
        main.runAlgorithm(scanner);
    }

    private void runAlgorithm(Scanner scanner) {
        int testNumber = 0;
        while (scanner.hasNextLine()) {
            testNumber++;
            // Ignore test line since we use a variable to keep the test count
            scanner.nextLine();
            int[] numbers = parseInput(scanner.nextLine());
            variables = new Boolean[numbers[0]];
            clauseArray = new int[numbers[1]][];
            for (int i = 0; i < numbers[1]; i++) {
                clauseArray[i] = parseInput(scanner.nextLine());
            }
            boolean[] values = findSatisfiability(1, numbers[0], new boolean[numbers[1]]);
            print(testNumber, numbers[0], numbers[1], values);
        }
    }

    private boolean[] findSatisfiability(int cnt, int varcnt, boolean[] array) {
        //Set the first variable to true and the progress up to cnt
        array[cnt - 1] = true;
        if (cnt != varcnt) {
            array = findSatisfiability(cnt + 1, varcnt, array);
        }
        if (checkClauses(array)) {
            return array;
        } else {
            if (array == null) {
                return null;
            }
            array[cnt - 1] = false;
            if (cnt != varcnt) {
                array = findSatisfiability(cnt + 1, varcnt, array);
            } else if (checkClauses(array)) {
                return array;
            }
        }
        return array;
    }

    private boolean checkClauses(boolean[] variables) {
        if (variables == null) {
            return false;
        } else {
            boolean exit = true;
            for (int[] clause : clauseArray) {
                boolean or = false;
                for (int literal : clause) {
                    int loc = Math.abs(literal);
                    boolean value = variables[loc - 1];
                    if (literal < 0) {
                        or = or || !value;
                    } else {
                        or = or || value;
                    }
                }
                exit = exit && or;
            }
            return exit;
        }
    }

    private int[] parseInput(String input) throws NumberFormatException {
        int[] output;
        String[] splitInput = input.split(" ");
        output = new int[splitInput.length];
        for (int i = 0; i < splitInput.length; i++) {
            output[i] = Integer.parseInt(splitInput[i]);
        }
        return output;
    }

    private void print(int testNum, int variables, int clauses, boolean[] booleans) {
        System.out.printf("Test %d: %d Variable(s) %d Clause(s)\n", testNum, variables, clauses);
        if (!checkClauses(booleans)) {
            System.out.print("Unsatisfiable\n");
        } else {
            System.out.print("Satisfiable\n");
            StringBuilder builder = new StringBuilder();
            for (Boolean bool : booleans) {
                builder.append(bool + " ");
            }
            System.out.println(builder.toString());
        }
    }
}
