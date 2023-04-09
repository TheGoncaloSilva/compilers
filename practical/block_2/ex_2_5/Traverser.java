import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import java.util.HashMap;

import javax.management.RuntimeErrorException;

@SuppressWarnings("CheckReturnValue")

public class Traverser extends TranslatorBaseListener {

   private HashMap<String, Integer> dictionary;

   public HashMap<String, Integer> getDictionary(){ return dictionary; }

   @Override public void enterDictionary(TranslatorParser.DictionaryContext ctx) {
      dictionary = new HashMap<String, Integer>();
   }

   @Override public void exitExpr(TranslatorParser.ExprContext ctx) {
      int number = Integer.parseInt(ctx.Number().getText());
      String meaning = ctx.Meaning().getText();

      if(dictionary.containsKey(meaning)){
         throw new RuntimeErrorException(null, "Translation already defined");
      }else{
         dictionary.put(meaning, number);
      }
   }
}
