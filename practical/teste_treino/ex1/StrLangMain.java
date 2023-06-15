import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class StrLangMain {
   public static void main(String[] args) {
      try {
         /*Scanner sc = new Scanner(System.in);
         String lineText = null;
         int numLine = 1;
         if (sc.hasNextLine())
            lineText = sc.nextLine();*/
         String inputFileName = null;

         for (int i = 0; i < args.length; i++) {
            inputFileName = args[i];
         }

         if(inputFileName == null) {
            System.err.println("No input file specified");
            System.exit(1);
         }

         File inputFile = null;
         Scanner sc = null;
         try {
            inputFile = new File(inputFileName);
            sc = new Scanner(inputFile);

         } catch (Exception e) {
            System.err.println("Input file not found or can't be read.");
            System.exit(1);
         }

         String lineText = null;
         int numLine = 1;
         if (sc.hasNextLine())
            lineText = sc.nextLine();

         StrLangParser parser = new StrLangParser(null);
         // replace error listener:
         //parser.removeErrorListeners(); // remove ConsoleErrorListener
         //parser.addErrorListener(new ErrorHandlingListener());
         interpreter visitor0 = new interpreter();
         while(lineText != null) {
            // create a CharStream that reads from standard input:
            CharStream input = CharStreams.fromString(lineText + "\n");
            // create a lexer that feeds off of input CharStream:
            StrLangLexer lexer = new StrLangLexer(input);
            lexer.setLine(numLine);
            lexer.setCharPositionInLine(0);
            // create a buffer of tokens pulled from the lexer:
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            // create a parser that feeds off the tokens buffer:
            parser.setInputStream(tokens);
            // begin parsing at program rule:
            ParseTree tree = parser.program();
            if (parser.getNumberOfSyntaxErrors() == 0) {
               // print LISP-style tree:
               // System.out.println(tree.toStringTree(parser));
               visitor0.visit(tree);
            }
            if (sc.hasNextLine())
               lineText = sc.nextLine();
            else
               lineText = null;
            numLine++;
         }

         sc.close();
      }
      catch(RecognitionException e) {
         e.printStackTrace();
         System.exit(1);
      }
   }
}
