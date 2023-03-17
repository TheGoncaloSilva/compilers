@SuppressWarnings("CheckReturnValue")
public class Interpretor extends PreffixCalculatorBaseVisitor<Double> {

   @Override public Double visitProgram(PreffixCalculatorParser.ProgramContext ctx) {
      Double res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public Double visitStat(PreffixCalculatorParser.StatContext ctx) {
      Double res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public Double visitExprSuffix(PreffixCalculatorParser.ExprSuffixContext ctx) {
      Double res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public Double visitExprNumber(PreffixCalculatorParser.ExprNumberContext ctx) {
      Double res = null;
      return visitChildren(ctx);
      //return res;
   }
}
