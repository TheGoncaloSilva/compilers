import java.util.Scanner;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;

public class ex_1_6 {
	public static void main(String[] args){
		var translator = read_translation_file("./bloco1/dic1.txt");

		System.out.println(translator.toString());
	}

	public static HashMap<String, String> read_translation_file(String filename){
		var translator = new HashMap<String,String>();
		
		try{
            File wordFile = new File(filename);
            Scanner fileReader = new Scanner(wordFile);
            
            while(fileReader.hasNextLine()){
                String temp = fileReader.nextLine();
                if(!temp.matches("^\\w*\\s*\\w*.+"))
                    continue;
                String[] line = temp.split("^\\w*\\s*");
                translator.put(line[0], line[1]);
            }
            fileReader.close();

        }catch(FileNotFoundException ex){
            System.err.println("Provided File path not valid");
            ex.printStackTrace();
            System.exit(1);
        }

		return translator;
	}
}
