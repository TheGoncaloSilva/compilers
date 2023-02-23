import java.util.Scanner;
import java.util.HashMap;

public class ex_1_2 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("NOTE: Don't forget to use ',' instead of '.'!");
        var exp = new HashMap<String, Double>();

        while(true){
            System.out.println("-> ");
            String line = sc.nextLine();
            
            // Regular attribution, ex: n = 10, n + 5
            if(line.matches("/[a-z]\\s*=\\s*\\d+/gm")){

            // Basic calculation, ex: 1,2 + 1,2
            }else if(line.matches("\\d*,\\d*\\s*[-+*/]\\s*\\d*,\\d*/gm")){

            }
                
        	if(sc.hasNextDouble()){
                if(state == 1){
                    value1 = sc.nextDouble();
                    state++;
                }else if(state == 3){
                    value2 = sc.nextDouble();
                    calculate(value1, operand, value2);
                    // reset
                    state = 1;
                }else{
                    System.err.println("The input is not in correct format");
                    break;
                }
            }else{
                if(state == 2){
                    state++;
                    operand = sc.next();
                    if(!operand.matches("[=-+*/]")){
                        System.err.println("Unknow operand");
                        break;
                    }
                    
                }else{
                    System.err.println("The provided input is not in correct format");
                    break;
                }
            }
        }
        sc.close();
    }

    public static void calculate(double val1, String operand, double val2){
        double result;
        switch(operand){
            case "+": result = val1 + val2; break;
            case "-": result = val1 - val2; break;
            case "*": result = val1 * val2; break;
            case "/": result = val1 / val2; break;
            case "=": 
            default: result = 0.0; break;
        }

        if(operand != "=")
            System.out.printf("Result: %3.2f\n", result);
    }
}
