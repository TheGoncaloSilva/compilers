import java.util.Scanner;
import java.util.HashMap;

@SuppressWarnings("CheckReturnValue")
public class interpreter extends FracLangBaseVisitor<Fraction> {

   private HashMap<String, Fraction> vars = new HashMap<String, Fraction>();

   private Scanner sc = new Scanner(System.in);

   @Override public Fraction visitProgram(FracLangParser.ProgramContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Fraction visitStat(FracLangParser.StatContext ctx) {
      Fraction res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public Fraction visitAssign(FracLangParser.AssignContext ctx) {
      String varName = ctx.ID().getText();
      Fraction value = visit(ctx.expr());
      vars.put(varName, value);
      return null;
   }

   @Override public Fraction visitDisplay(FracLangParser.DisplayContext ctx) {
      Fraction value = visit(ctx.expr());
      if(value != null)
         System.out.println(value.toString());
      return null;
   }

   @Override public Fraction visitExprRead(FracLangParser.ExprReadContext ctx) {
      Fraction res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public Fraction visitExprAddSub(FracLangParser.ExprAddSubContext ctx) {
      Fraction frac1 = visit(ctx.expr(0));
      String op = ctx.op.getText();
      Fraction frac2 = visit(ctx.expr(1));
      
      Fraction res = null;
      switch(op){
         case "+": res = Fraction.sum(frac1, frac2); break;
         case "-": res = Fraction.sub(frac1, frac2); break;
      }
      return res;
   }

   @Override public Fraction visitExprReduce(FracLangParser.ExprReduceContext ctx) {
      Fraction frac = visit(ctx.expr());
      return Fraction.reduce(frac);
   }

   @Override public Fraction visitExprParentesis(FracLangParser.ExprParentesisContext ctx) {
      return visit(ctx.expr());
   }

   @Override public Fraction visitExprId(FracLangParser.ExprIdContext ctx) {
      String varName = ctx.ID().getText();
      if(vars.containsKey(varName))
         return vars.get(varName);
      else
         throw new RuntimeException("The variable isn't defined");
   }

   @Override public Fraction visitExprUnary(FracLangParser.ExprUnaryContext ctx) {
      String signal = ctx.op.getText();
      Fraction frac = visit(ctx.expr());

      switch(signal){
         case "+": frac.setDen(frac.getDen()*1); // nothing
            break;
         case "-": frac.setDen(frac.getDen()*(-1));
            break;
      }
      return frac;
   }

   @Override public Fraction visitExprFrac(FracLangParser.ExprFracContext ctx) {
      return visit(ctx.fraction());
   }

   @Override public Fraction visitExprMulDiv(FracLangParser.ExprMulDivContext ctx) {
      Fraction frac1 = visit(ctx.expr(0));
      String op = ctx.op.getText();
      Fraction frac2 = visit(ctx.expr(1));
      
      Fraction res = null;
      switch(op){
         case "*": res = Fraction.mull(frac1, frac2); break;
         case ":": res = Fraction.div(frac1, frac2); break;
      }
      return res;
   }

   @Override public Fraction visitRegularFrac(FracLangParser.RegularFracContext ctx) {
      int num = Integer.parseInt(ctx.NUMBER(0).getText());
      int den = Integer.parseInt(ctx.NUMBER(1).getText());
      return new Fraction(num, den);
   }

   @Override public Fraction visitSingleFrac(FracLangParser.SingleFracContext ctx) {
      int num = Integer.parseInt(ctx.NUMBER().getText());
      return new Fraction(num);
   }
}
