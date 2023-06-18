import java.util.HashMap;
import java.util.Scanner;

@SuppressWarnings("CheckReturnValue")
public class interpreter extends StrLangBaseVisitor<String> {

   private HashMap<String, String> vars = new HashMap<String, String>();

   private Scanner sc = new Scanner(System.in);

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
      String prompt = ctx.String().getText();
      if(prompt.startsWith("\"") && prompt.endsWith("\""))
         prompt = prompt.substring(1, prompt.length() - 1); // remove ""
      String var = ctx.ID().getText();
      System.out.print(prompt);
      String res = sc.nextLine();

      vars.put(var, res);

      return null;
   }

   @Override public String visitExprAddSum(StrLangParser.ExprAddSumContext ctx) {
      String op1 = visit(ctx.expr(0));
      String op2 = visit(ctx.expr(1));
      String res = null;
      switch(ctx.op.getText()){
         case "+":
            res = op1 + op2;
            break;
         case "-":
            res = op1.replace(op2, "");
            break;
         default:
            throw new RuntimeException("Unknown operator");
      }

      return res;
   }

   @Override public String visitExprTrim(StrLangParser.ExprTrimContext ctx) {
      String res = visit(ctx.expr());
      res = res.trim();
      return res;
   }

   @Override public String visitExprOpen(StrLangParser.ExprOpenContext ctx) {
      return visit(ctx.expr());
   }

   @Override public String visitExprSubs(StrLangParser.ExprSubsContext ctx) {
      String text = visit(ctx.expr(0));
      String substring = visit(ctx.expr(1));
      String replacing = visit(ctx.expr(2));
      String res = text.replace(substring, replacing);
      return res;
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
