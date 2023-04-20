import java.util.ArrayList;

@SuppressWarnings("CheckReturnValue")
public class Semantica extends RationalCalculatorBaseVisitor<String> {

   // Hashmap is created uppon class initialization
   private ArrayList<String, Fraction> history = new HashMap<String, Fraction>();

   public HashMap<String, Fraction> getHistory(){ return history; }


   @Override public String visitProgram(RationalCalculatorParser.ProgramContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitStat(RationalCalculatorParser.StatContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitAssignment(RationalCalculatorParser.AssignmentContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitPrint(RationalCalculatorParser.PrintContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitExprFrac(RationalCalculatorParser.ExprFracContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitExprAddSub(RationalCalculatorParser.ExprAddSubContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitExprParent(RationalCalculatorParser.ExprParentContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitExprUnary(RationalCalculatorParser.ExprUnaryContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitExprMultDiv(RationalCalculatorParser.ExprMultDivContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitExprInteger(RationalCalculatorParser.ExprIntegerContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitExprPower(RationalCalculatorParser.ExprPowerContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitExprID(RationalCalculatorParser.ExprIDContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitExprReduce(RationalCalculatorParser.ExprReduceContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }
}
