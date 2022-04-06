package stack;

//import javax.xml.crypto.Data; //??????????????

/**
 * A {@link LinkedStack} is a stack that is implemented using a Linked List structure to allow for
 * unbounded size.
 *
 * @param <T> the elements stored in the stack
 */

public class LinkedStack<T> implements StackInterface<T> {
  
  //private LinkedStack<T> newData = new LinkedStack<T>(); 
  LLNode<T> top;
  //??????????????

// public LinkedStack() //????????????
// {
//     data = null; 
// }

  /** {@inheritDoc} */
  @Override
  //Removes the top most element on this stack. For convenience, the top most element is returned
   
  public T pop() throws StackUnderflowException {
   
    
    if (isEmpty()) {
       throw new StackUnderflowException("Pop attempted on an empty stack.");
    }
    //stack.StackUnderflowException> but was<java.lang.NullPointerException>???????????
    
      T tempe = top.getData();
      top= top.getNext();
      
      
      return tempe;
      }
    
    
  

  /** {@inheritDoc} */
  @Override
  public T top() throws StackUnderflowException {
    
    if (isEmpty()){
       throw new StackUnderflowException("Top attempted on an empty stack.");
    }
    else{
    
       return top.getData();
    }
 
    }

  /** {@inheritDoc} */
  @Override
  public boolean isEmpty() {
   
    return top == null;
    
  }

  /** {@inheritDoc} */
  @Override
  public int size() {
    
    int size = 0;
      // counting the number of nodes
      for (LLNode<T> temp = top; temp != null; temp = temp.getNext()) {
         size++;
      }
    
      return size;
  }

  /** {@inheritDoc} */
  @Override
  public void push(T elem) {
    
    LLNode<T> newNode = new LLNode<T>(elem);
    newNode.setNext(top);
    top = newNode;
  }
}
