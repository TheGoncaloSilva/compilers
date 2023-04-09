/*
 * Compile: javac ex_1_6.java
 * Usage: java ex_1_6 <translation_file> ... <input_files> ...
 * Example: java ex_1_6 ./bloco1/dic1.txt ./bloco1/texto1.txt
 */
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Vector;
import java.io.File;
import java.io.FileNotFoundException;

public class ex_1_6 {
	public static void main(String[] args){

        if(args.length < 2){
            System.err.println("At least two arguments should be given");
            System.exit(1);
        }

		var translator = read_translation_file(args[0]);

        for(int i = 1; i < args.length; i++){
            System.out.printf("--- File %d ---\n", i);
            var wordVector = new Vector<String>();
            wordVector = read_file(args[i], wordVector);

            wordVector = make_translation_recursive(wordVector, translator);

            for(String word: wordVector){
                System.out.print(word);
                if(!word.equals("\n"))
                    System.out.print(" ");
            }

            System.out.println();
        }
	}

    public static Vector<String> make_translation_recursive(Vector<String> wordVector, HashMap<String, String> dictionary){

        for(int i = 0; i < wordVector.size(); i++){
            if(dictionary.containsKey(wordVector.get(i))){
                String[] translatorWords = (dictionary.get(wordVector.get(i))).split(" ");

                wordVector.remove(i);
                int aux = 0;
                for(String word: translatorWords){
                    wordVector.add(i+aux, word);
                    aux++;
                }
                wordVector = make_translation_recursive(wordVector, dictionary);
                break;
            }
        }

        return wordVector;
    }

    public static Vector<String> make_translation(Vector<String> wordVector, HashMap<String, String> dictionary){
        for(int i = 0; i < wordVector.size(); i++){
            if(dictionary.containsKey(wordVector.get(i))){
                wordVector.set(i, dictionary.get(wordVector.get(i)));
            }
        }

        return wordVector;
    }

    public static Vector<String> read_file(String fileName, Vector<String> wordVector){
        try{
            File wordFile = new File(fileName);
            Scanner fileReader = new Scanner(wordFile);
            
            while(fileReader.hasNext()){
                String line = fileReader.nextLine();

                String[] words = line.split(" ");
                
                for(String word: words)
                    wordVector.add(word);

                wordVector.add("\n");                
            }
            fileReader.close();

        }catch(FileNotFoundException ex){
            System.err.println("Provided Word File path not valid");
            ex.printStackTrace();
            System.exit(1);
        }


        return wordVector;
    }

	public static HashMap<String, String> read_translation_file(String fileName){
		var translator = new HashMap<String,String>();
		
		try{
            File wordFile = new File(fileName);
            Scanner fileReader = new Scanner(wordFile);
            
            while(fileReader.hasNextLine()){
                String line = fileReader.nextLine();
                
                String regex = "^(\\w+|\\S+)\\s+(.*)$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(line);

                if (matcher.matches())
                    translator.put(matcher.group(1), matcher.group(2));
                else{
                    System.err.println("Invalid File Format");
                    System.exit(1);
                }
            }
            fileReader.close();

        }catch(FileNotFoundException ex){
            System.err.println("Provided Translator File path not valid");
            ex.printStackTrace();
            System.exit(1);
        }

		return translator;
	}
}
