@SuppressWarnings("CheckReturnValue")
public class Interpreter extends CalculatorBaseVisitor<Double> {

   @Override public Double visitProgram(CalculatorParser.ProgramContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Double visitStat(CalculatorParser.StatContext ctx) {
      if(ctx.expr() != null){
         Double res = visit(ctx.expr());
         System.out.printf("Result: %3.2f \n", res);
      }
      return null;
   }

   @Override public Double visitExprAddSub(CalculatorParser.ExprAddSubContext ctx) {
      Double res = null;
      Double o1 = visit(ctx.expr(0));
      Double o2 = visit(ctx.expr(1));

      switch (ctx.op.getText()) {
         case "+":
            res = o1 + o2;
            break;

         case "-":
            res = o1 - o2;
            break;
      
         default:
            System.err.println("Opeator invalid");
            break;
      }

      return res;
   }

   @Override public Double visitExprParent(CalculatorParser.ExprParentContext ctx) {
      return visit(ctx.expr());
   }

   @Override public Double visitExprInteger(CalculatorParser.ExprIntegerContext ctx) {
      return Double.parseDouble(ctx.Integer().getText());
   }

   @Override public Double visitExprMultDivMod(CalculatorParser.ExprMultDivModContext ctx) {
      Double res = null;
      Double o1 = visit(ctx.expr(0));
      Double o2 = visit(ctx.expr(1));

      switch (ctx.op.getText()) {
         case "*":
            res = o1 * o2;
            break;

         case "/":
            res = o1 / o2;
            break;

         case "%":
            res = o1 % o2;
            break;
      
         default:
            System.err.println("Opeator invalid");
            break;
      }

      return res;
   }
}
