@SuppressWarnings("CheckReturnValue")
public class Interpretor extends PreffixCalculatorBaseVisitor<Double> {

   @Override public Double visitProgram(PreffixCalculatorParser.ProgramContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Double visitStat(PreffixCalculatorParser.StatContext ctx) {
      if(ctx.expr() != null){
         Double res = visit(ctx.expr());
         System.out.printf("Result: %3.2f \n", res);
      }
      
      return null;
   }

   @Override public Double visitExprSuffix(PreffixCalculatorParser.ExprSuffixContext ctx) {
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

         case "*":
            res = o1 * o2;
            break;

         case "/":
            res = o1 / o2;
            break;
      
         default:
            System.err.println("Opeator invalid");
            break;
      }

      return res;

   }

   @Override public Double visitExprNumber(PreffixCalculatorParser.ExprNumberContext ctx) {
      return Double.parseDouble(ctx.Number().getText());
   }
}
