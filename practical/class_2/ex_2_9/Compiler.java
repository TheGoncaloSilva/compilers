import java.util.HashMap;

@SuppressWarnings("CheckReturnValue")
public class Compiler extends RationalCalculatorBaseVisitor<Double> {

   // Hashmpa is created uppon class initialization
   private HashMap<String, Double> history = new HashMap<String, Double>();

   public HashMap<String, Double> getHistory(){ return history; }

   @Override public Double visitProgram(RationalCalculatorParser.ProgramContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Double visitStat(RationalCalculatorParser.StatContext ctx) {
      if(ctx.print() != null){
         visit(ctx.print());
      }else if(ctx.assignment() != null)
         visit(ctx.assignment());
      return null;
   }

   @Override public Double visitAssignment(RationalCalculatorParser.AssignmentContext ctx) {
      String variable = ctx.ID().getText();
      Double res = visit(ctx.expr());
      if(res != null){
         history.put(variable, res);
      }
      return res;
   }

   @Override public Double visitPrint(RationalCalculatorParser.PrintContext ctx) {
      /*// Check if expr is variable
      if (ctx.ID() != null){
         Double res = visit(ctx.expr());
         if(res != null)
            System.out.printf("Value: %3.2f \n", res);
      } else {
         Double res = visit(ctx.expr());
         if(res != null)
            System.out.printf("Result: %3.2f \n", res);
         return null;
      }*/
      Double res = visit(ctx.expr());
      System.out.println("Result: %3.2f \n", res);
   }

   @Override public Double visitExprFrac(RationalCalculatorParser.ExprFracContext ctx) {
      // Because the visitor type is Double, there's no need to have a Converter
      Double numerator = visit(ctx.expr(0));
      Double denominator = visit(ctx.expr(1));
      return numerator / denominator;
   }

   @Override public Double visitExprAddSub(RationalCalculatorParser.ExprAddSubContext ctx) {
      Double res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public Double visitExprParent(RationalCalculatorParser.ExprParentContext ctx) {
      return visit(ctx.expr());
   }

   @Override public Double visitExprUnary(RationalCalculatorParser.ExprUnaryContext ctx) {
      Double res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public Double visitExprInteger(RationalCalculatorParser.ExprIntegerContext ctx) {
      return Double.parseDouble(ctx.Integer().getText());
   }

   @Override public Double visitExprMultDiv(RationalCalculatorParser.ExprMultDivContext ctx) {
      Double res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public Double visitExprReduce(RationalCalculatorParser.ExprReduceContext ctx) {
      Double res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public Double visitExprID(RationalCalculatorParser.ExprIDContext ctx) {
      if(history.containsKey(ctx.ID().getText()))
         System.out.println(ctx.ID().getText() + ": " + history.get(ctx.ID().getText()));
      else
         System.out.println("Error: Undefined variable");

      return null;
   }

}
