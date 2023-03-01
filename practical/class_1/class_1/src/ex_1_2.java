/* Cool website for regex: https://regex101.com/
 * Anottations:
 *  - Double support is not implemented consistently in regex
 *  - No Tree of operations, which means that cascade operations are limited
 */
import java.util.Scanner;
import java.util.HashMap;

public class ex_1_2 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("NOTE: Don't forget to use ',' instead of '.'!");
        var assoc = new HashMap<String, Double>();

        while(sc.hasNextLine()){
            String line = sc.nextLine();
            
            // Variable instant calculation, ex: n + 5
            if(line.matches("[a-z]\\s*[-+*/]\\s*\\d+")){
                var elements = line.split(" ");
                if(elements.length != 3){
                    System.err.println("Prompt not in correct format");
                    continue;
                }
                if(!assoc.containsKey(elements[0])){
                    System.err.printf("The variable %s is null\n", elements[0]);
                    continue;
                }
                double result = calculate(assoc.get(elements[0]), elements[1], Double.parseDouble(elements[2]));
                System.out.printf("-> %3.2f\n", result);

            // Variable instant calculation, 4 * n
            }else if(line.matches("\\d+\\s*[-+*/]\\s*[a-z]")){
                var elements = line.split(" ");
                if(elements.length != 3){
                    System.err.println("Prompt not in correct format");
                    continue;
                }
                if(!assoc.containsKey(elements[2])){
                    System.err.printf("The variable %s is null\n", elements[2]);
                    continue;
                }
                double result = calculate(Double.parseDouble(elements[0]), elements[1], assoc.get(elements[2]));
                System.out.printf("-> %3.2f\n", result);
                
            // Basic calculation, ex: 1,2 + 1,2
            }else if(line.matches("\\d*,\\d*\\s*[-+*/]\\s*\\d*,\\d*")){
                var elements = line.split(" ");
                if(elements.length != 3){
                    System.err.println("Prompt not in correct format");
                    continue;
                }
                calculate(Double.parseDouble(elements[0]), elements[1], Double.parseDouble(elements[2]));

            // Simple Attribution, ex: n = 1
            }else if(line.matches("[a-z]\\s*=\\s*\\d+")){
                var elements = line.split(" ");
                if(elements.length != 3){
                    System.err.println("Prompt not in correct format");
                    continue;
                }
                assoc.put(elements[0], Double.parseDouble(elements[2]));

            // Complex Attribution, ex: n = n + 1
            }else if(line.matches("[a-z]\\s*=\\s*[a-z]\\s*[-+*/]\\s*\\d+")){
                var elements = line.split(" ");
                if(elements.length != 5){
                    System.err.println("Prompt not in correct format");
                    continue;
                }
                if(!assoc.containsKey(elements[2])){
                    System.err.printf("The variable %s is null\n", elements[2]);
                    continue;
                }
                double value = calculate(assoc.get(elements[2]), elements[3], Double.parseDouble(elements[4]));
                assoc.put(elements[0], value);

            // Complex Attribution, ex: n = 1 + n
            }else if(line.matches("[a-z]\\s*=\\s*\\d+\\s*[-+*/]\\s*[a-z]")){
                var elements = line.split(" ");
                if(elements.length != 5){
                    System.err.println("Prompt not in correct format");
                    continue;
                }
                if(!assoc.containsKey(elements[4])){
                    System.err.printf("The variable %s is null\n", elements[2]);
                    continue;
                }
                double value = calculate(Double.parseDouble(elements[2]), elements[3], assoc.get(elements[4]));
                assoc.put(elements[0], value);

            // Check value
            }else if(line.matches("[a-z]")){
                if(!assoc.containsKey(line)){
                    System.err.printf("The variable %s is null\n", line);
                    continue;
                }
                System.out.printf("-> %3.2f\n", assoc.get(line));
            }else{
                System.out.println("Unrecognized Operation");
            }
            
        }
        sc.close();
    }

    public static double calculate(double val1, String operand, double val2){
        double result;
        switch(operand){
            case "+": result = val1 + val2; break;
            case "-": result = val1 - val2; break;
            case "*": result = val1 * val2; break;
            case "/": result = val1 / val2; break;
            default: result = 0.0; break;
        }
        return result;
    }
}
