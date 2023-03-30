import java.util.HashMap;

@SuppressWarnings("CheckReturnValue")
public class Interpreter extends CalculatorBaseVisitor<Double> {

   private HashMap<String, Double> history= new HashMap<String, Double>();;

   public HashMap<String, Double> getHistory(){ return history; }

   @Override public Double visitProgram(CalculatorParser.ProgramContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Double visitStat(CalculatorParser.StatContext ctx) {
      if(ctx.expr() != null){
         Double res = visit(ctx.expr());
         if(res != null)
            System.out.printf("Result: %3.2f \n", res);
      }else if(ctx.assignment() != null)
         visit(ctx.assignment());
      return null;
   }

   @Override public Double visitAssignment(CalculatorParser.AssignmentContext ctx) {
      String variable = ctx.ID().getText();
      Double res = visit(ctx.expr());
      if(res != null){
         history.put(variable, res);
      }
      return res;
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
            System.err.println("Invalid Operator");
            break;
      }

      return res;
   }

   @Override public Double visitExprParent(CalculatorParser.ExprParentContext ctx) {
      return visit(ctx.expr());
   }

   @Override public Double visitExprUnary(CalculatorParser.ExprUnaryContext ctx) {
      return ctx.op.getText().equals("-") ? -visit(ctx.expr()) : visit(ctx.expr());
   }

   @Override public Double visitExprInteger(CalculatorParser.ExprIntegerContext ctx) {
      return Double.parseDouble(ctx.Integer().getText());
   }

   @Override public Double visitExprID(CalculatorParser.ExprIDContext ctx) {
      if(history.containsKey(ctx.ID().getText()))
         System.out.println(ctx.ID().getText() + ": " + history.get(ctx.ID().getText()));
      else
         System.out.println("Error: Undefined variable");

      return null;
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
