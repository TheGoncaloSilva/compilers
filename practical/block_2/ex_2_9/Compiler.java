import java.util.HashMap;

@SuppressWarnings("CheckReturnValue")
public class Compiler extends RationalCalculatorBaseVisitor<Fraction> {

   // Hashmap is created uppon class initialization
   private HashMap<String, Fraction> history = new HashMap<String, Fraction>();

   public HashMap<String, Fraction> getHistory(){ return history; }

   @Override public Fraction visitProgram(RationalCalculatorParser.ProgramContext ctx) {
      return visitChildren(ctx);
}

   @Override public Fraction visitStat(RationalCalculatorParser.StatContext ctx) {
      if(ctx.print() != null){
         visit(ctx.print());
      }else if(ctx.assignment() != null)
         visit(ctx.assignment());
      return null;
   }

   @Override public Fraction visitAssignment(RationalCalculatorParser.AssignmentContext ctx) {
      String variable = ctx.ID().getText();
      Fraction res = visit(ctx.expr());
      if(res != null){
         history.put(variable, res);
      }
      return res;
   }

   @Override public Fraction visitPrint(RationalCalculatorParser.PrintContext ctx) {
      Fraction res = visit(ctx.expr());
      if(res != null)
         System.out.println("Result: " + res.toString());
      return null;
   }

   @Override public Fraction visitExprFrac(RationalCalculatorParser.ExprFracContext ctx) {
      Integer numerator = Integer.parseInt(ctx.Integer(0).getText());
      Integer denominator = Integer.parseInt(ctx.Integer(1).getText());
      return new Fraction(numerator, denominator);
   }

   @Override public Fraction visitExprAddSub(RationalCalculatorParser.ExprAddSubContext ctx) {
      Fraction frac1 = visit(ctx.expr(0));
      Fraction frac2 = visit(ctx.expr(1));


      switch (ctx.op.getText()) {
         case "+":
            return Fraction.add(frac1, frac2);

         case "-":
            return Fraction.sub(frac1, frac2);

         default:
            System.err.println("Invalid Operator");
            return null;
      }
   }

   @Override public Fraction visitExprParent(RationalCalculatorParser.ExprParentContext ctx) {
      return visit(ctx.expr());
   }

   @Override public Fraction visitExprUnary(RationalCalculatorParser.ExprUnaryContext ctx) {
      Fraction frac = visit(ctx.expr());

      switch (ctx.op.getText()) {
         case "-":
            frac.setNumerator(frac.getNumerator()*-1);
            return frac;

         case ":":
            return frac;

         default:
            System.err.println("Invalid Operator");
            return null;
      }
   }

   @Override public Fraction visitExprPower(RationalCalculatorParser.ExprPowerContext ctx) {
      Fraction frac = visit(ctx.expr());
      int power = Integer.parseInt(ctx.Integer().getText());;
      return Fraction.power(frac, power); 
   }

   @Override public Fraction visitExprMultDiv(RationalCalculatorParser.ExprMultDivContext ctx) {
      Fraction frac1 = visit(ctx.expr(0));
      Fraction frac2 = visit(ctx.expr(1));

      switch (ctx.op.getText()) {
         case "*":
            return Fraction.mul(frac1, frac2);

         case ":":
            return Fraction.mul(frac1, frac2);

         default:
            System.err.println("Invalid Operator");
            return null;
      }
   }

   @Override public Fraction visitExprInteger(RationalCalculatorParser.ExprIntegerContext ctx) {
      return new Fraction(Integer.parseInt(ctx.Integer().getText()));
   }

   @Override public Fraction visitExprReduce(RationalCalculatorParser.ExprReduceContext ctx) {
      Fraction frac = visit(ctx.expr());
      frac.reduce();
      return frac;
   }

   @Override public Fraction visitExprID(RationalCalculatorParser.ExprIDContext ctx) {
      if(history.containsKey(ctx.ID().getText()))
         return history.get(ctx.ID().getText());
      else{
         System.out.println("Error: Undefined variable");
         return null;
      }

      
   }
}
