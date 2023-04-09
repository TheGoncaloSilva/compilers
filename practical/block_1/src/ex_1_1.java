import java.util.Scanner;

public class ex_1_1{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("NOTE: Don't forget to use ',' instead of '.'!");
	    double value1 = 0.0, value2 = 0.0;
        char operand = ' ';
        int state = 1;

        while(sc.hasNext()){
            if(state == 1)
                System.out.print("-> ");
                
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
                    String _temp = sc.next();
                    operand = _temp.toCharArray()[0];
                    if(operand != '+' && operand != '-' && operand != '*' && operand != '/'){
                        System.err.println("Unknow operand");
                        break;
                    }
                    state++;
                }else{
                    System.err.println("The provided input is not in correct format");
                    break;
                }
            }
        }
        sc.close();
    }

    public static void calculate(double val1, char operand, double val2){
        double result;
        switch(operand){
            case '+': result = val1 + val2; break;
            case '-': result = val1 - val2; break;
            case '*': result = val1 * val2; break;
            case '/': result = val1 / val2; break;
            default: result = 0.0; break;
        }

        System.out.printf("Result: %3.2f\n", result);
    }
}
