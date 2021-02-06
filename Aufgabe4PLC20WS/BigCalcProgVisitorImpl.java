import java.math.BigDecimal;
import java.math.RoundingMode;

import java.util.HashMap;
import java.util.Map;

public class BigCalcProgVisitorImpl extends BigCalcProgBaseVisitor<BigDecimal> {

    Map<String, BigDecimal> memory = new HashMap<String, BigDecimal>();
    BigDecimal Result = BigDecimal.valueOf(0);

    @Override
    public BigDecimal visitProgram(BigCalcProgParser.ProgramContext ctx) {
        visitChildren(ctx);
        return  Result;
    }

    @Override
    public BigDecimal visitExpressionStatement(BigCalcProgParser.ExpressionStatementContext ctx) {
        return   visitChildren(ctx);
    }


    @Override
    public BigDecimal visitAddSub(BigCalcProgParser.AddSubContext ctx) {
        final BigDecimal left = visit(ctx.expression(0));
        final BigDecimal right = visit(ctx.expression(1));
        if (ctx.op.getText().equals("+")) {
            Result = left.add(right);
            return  Result;
        } else {
            Result = left.subtract(right);
            return  Result;
        }
    }

    @Override
    public BigDecimal visitMulDiv(BigCalcProgParser.MulDivContext ctx) {
        final BigDecimal left = visit(ctx.expression(0));
        final BigDecimal right = visit(ctx.expression(1));
        if (ctx.op.getText().equals("*")) {
            Result = left.multiply(right);
            return  Result;
        } else {
            Result = left.divide(right,10,RoundingMode.HALF_UP);
            return  Result;
        }
    }

    @Override
    public BigDecimal visitNum(BigCalcProgParser.NumContext ctx) {
        return new BigDecimal(ctx.Number().getText());
    }
    

}
