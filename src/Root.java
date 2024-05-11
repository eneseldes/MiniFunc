public class Root extends Expression{
    Expression radicand;
    Expression index;
    Root(Expression radicand, Expression index){
        this.radicand=radicand;
        this.index=index;
    }

    @Override
    Object getValue() {
       return execute().getValue();
    }

    @Override
    Expression execute() {
        if(radicand.getValue() instanceof Number){            
            DoubleLiteral radicandDouble= new DoubleLiteral(((Number)radicand.getValue()).doubleValue());
             if(index.getValue() instanceof Number){
                  DoubleLiteral indexDouble= new DoubleLiteral((1.0/((Number)index.getValue()).doubleValue()));
                 return new Power(radicandDouble, indexDouble);
             }
             else{
                 //hata fırlat
             }
        }
        return null;//hata fırlat
    }
    
}
