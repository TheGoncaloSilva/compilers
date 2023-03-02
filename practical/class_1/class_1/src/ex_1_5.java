import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Vector;

public class ex_1_5 {
    public static void main(String[] args){
        String NUMBER_FILE_NAME = "./bloco1/numbers.txt";
        var sc = new Scanner(System.in);
        var assoc = read_number_names(NUMBER_FILE_NAME);
        var numbers = new Vector<Integer>();

        while(sc.hasNext()){
            String temp = sc.next();
            
            processWord(temp, assoc, numbers);
        }

        int tempValue = 0;
        int finalResult = 0;
        for(Integer number: numbers){
            // Contar os valores por ordem e multiplicar até o valor seguinte ser maior que o atual
            // e quando for menor, somar
        }


        sc.close();
    }

    public static void processWord(String line, HashMap<String, Integer> assoc, Vector<Integer> numbers){
        if(assoc.containsKey(line))
                numbers.add(assoc.get(line));
        else if(line.contains("-")){
            String[] lineArray = line.split("-");
            for(String word: lineArray){
                processWord(word, assoc, numbers);
            }
        }
    }

    public static HashMap<String, Integer> read_number_names(String fileName) {
        var assoc = new HashMap<String, Integer>();
        try{
            File numbersFile = new File(fileName);
            Scanner fileReader = new Scanner(numbersFile);
            
            while(fileReader.hasNextLine()){
                String temp = fileReader.nextLine();
                if(!temp.matches("\\d+\\s*-\\s*.+"))
                    continue;
                String[] line = temp.split(" ");
                assoc.put(line[2], Integer.parseInt(line[0]));
            }
            fileReader.close();

        }catch(FileNotFoundException ex){
            System.err.println("Provided File path not valid");
            ex.printStackTrace();
            System.exit(1);
        }

        return assoc;
    }
}
