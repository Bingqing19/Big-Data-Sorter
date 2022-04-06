package evaluator.arith;

import language.Operand;
//import language.Operator;
import parser.IllegalPostfixExpressionException;
import parser.PostfixParser.Type;
import parser.Token;
import parser.arith.ArithPostfixParser;
import stack.LinkedStack;
import stack.StackInterface;
import evaluator.PostfixEvaluator;

/** An {@link ArithPostfixEvaluator} is a postfix evaluator over simple arithmetic expressions. */
public class ArithPostfixEvaluator implements PostfixEvaluator<Integer> {

  private final StackInterface<Operand<Integer>> stack;

  /** Constructs an {@link ArithPostfixEvaluator} */
  public ArithPostfixEvaluator() { 
       
    // Initialize to your LinkedStack
       stack = new LinkedStack<>(); 
       
    
       
  }


  /** {@inheritDoc} */
  @Override
  public Integer evaluate(String expr) throws IllegalPostfixExpressionException {
    ArithPostfixParser parser = new ArithPostfixParser(expr);
    for (Token<Integer> token : parser) {
      Type type = token.getType();
      switch (type) {
        case OPERAND:
          // TODO What do we do when we see an operand?
          stack.push(token.getOperand());//?????????????????????
            
          break;
        case OPERATOR:
          // What do we do when we see an operator?
        
          if(stack.isEmpty()){
            throw new IllegalStateException("");
          }
         
          
          if(stack.isEmpty()){
            throw new IllegalStateException("");
          }
          //stack.pop();
            
          if(stack.size()>1){
           token.getOperator().setOperand(1, stack.pop());
           token.getOperator().setOperand(0, stack.pop());
          }
           else{
            token.getOperator().setOperand(0, stack.pop());
           }
           stack.push(token.getOperator().performOperation());  
            
           


             
         
                  
          break;
        default:
          throw new IllegalStateException("Parser returned an invalid Type: " + type);
      }
    }
    // TODO What do we return?
    return stack.top().getValue(); //??????????????????
  }
}

