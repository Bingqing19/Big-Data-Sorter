package language.arith;


import language.Operand;

/** The {@code NegateOperator} is an operator that performs negation on a single integer */

public class NegateOperator extends UnaryOperator<Integer> {
  // You will notice that this class extends BinaryOperator.
  // That is not correct as negate is a unary operator.  You should first
  // write an abstract class called UnaryOperator, paralleling
  // BinaryOperator, that abstracts out all the bits common
  // across UnaryOperators.
  @Override
  public Operand<Integer> performOperation() {
    if (op0 == null)
      throw new IllegalStateException("Could not perform operation prior to operands being set.");
    Integer result = -1 * op0.getValue(); 
    //?????Note that for this evaluator, unary negation is designated by the “!” operator.
    return new Operand<Integer>(result);
  }

  public String toString() {
    return "!";
  }
}
