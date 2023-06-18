import java.util.HashMap;
import java.util.Scanner;

@SuppressWarnings("CheckReturnValue")
public class interpreter extends StrLangBaseVisitor<String> {

   private HashMap<String, String> vars = new HashMap<String, String>();

   public HashMap<String, String> getVars() { return vars; }

   @Override public String visitProgram(StrLangParser.ProgramContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitStat(StrLangParser.StatContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitPrint(StrLangParser.PrintContext ctx) {
      String res = visit(ctx.expr());
      if(res != null){
         System.out.println(res);
      }
      return null;
   }
   
   @Override public String visitRegularAttr(StrLangParser.RegularAttrContext ctx) {
      String var = ctx.ID().getText();
      String value = visit(ctx.expr());
      vars.put(var, value);
      
      return null;
   }

   @Override public String visitInputAttr(StrLangParser.InputAttrContext ctx) {
      Scanner sc = new Scanner(System.in);
      String prompt = ctx.String().getText();
      if(prompt.startsWith("\"") && prompt.endsWith("\""))
         prompt = prompt.substring(1, prompt.length() - 1); // remove ""
      String var = ctx.ID().getText();
      System.out.print(prompt);
      String res = sc.nextLine();

      vars.put(var, res);

      sc.close();

      return null;
   }

   @Override public String visitExprID(StrLangParser.ExprIDContext ctx) {
      String var = ctx.ID().getText();
      if(vars.containsKey(var)){
         return vars.get(var);
      }else{
         throw new RuntimeException("Variable not found");
      }
   }

   @Override public String visitExprStr(StrLangParser.ExprStrContext ctx) {
      String value = ctx.String().getText();
      if(value.startsWith("\"") && value.endsWith("\""))
         value = value.substring(1, value.length() - 1); // remove ""
      
      return value;
   }
}
