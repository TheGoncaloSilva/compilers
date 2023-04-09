import java.util.Stack;
import java.util.Scanner;

public class ex_1_3 {
    public static void main(String[] args){
        var sc = new Scanner(System.in);
        
        var numbers = new Stack<Double>();

        while(sc.hasNext()){
            if(sc.hasNextDouble()){
                numbers.push(sc.nextDouble());
            }else{
                String operand = sc.next();
                if(!operand.matches("[-+*/]")){
                    System.err.println("Unrecognized operation");
                   continue; 
                }
                if(numbers.size() < 2){
                    System.err.println("Not enough numbers to operate on");
                    continue;
                }
                numbers.push(calculate(numbers.pop(), operand, numbers.pop()));
            }
            System.out.println("Stack: " + numbers.toString());
        }
        if(numbers.size() > 1)
            System.err.println("The Stack should finish with only one number");

        sc.close();
    }

    // Invert the processing order
    public static double calculate(double val2, String operand, double val1){
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
