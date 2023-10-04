//import org.junit.Assert;

/** copntains methods for testing SLL */
public class TestSLL implements Phase1SLL{

  @Override
  public NodeSL getHead() {
    
    throw new UnsupportedOperationException("Unimplemented method 'getHead'");
  }

  @Override
  public NodeSL getTail() {
    
    throw new UnsupportedOperationException("Unimplemented method 'getTail'");
  }

  @Override
  public boolean isEmpty() {
    
    throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");
  }

  @Override
  public void addFirst(Object v) {
    
    throw new UnsupportedOperationException("Unimplemented method 'addFirst'");
  }
  // /** checks the form of a SLL for errors */
  // public static <T> void checkSLL(SLL<T> list) {
  //   // TODO
  // }

  // /**
  //  *  Checks the form and contents of a SLL for errors
  //  *  @param list to check
  //  *  @param values array showing expected contents of list
  //  */
  // public static <T> void verifySLL(SLL<T> list, T[] values) {
  //   NodeSL<T> here = list.getHead();
  //   for (int i = 0; i < values.length-1; i++) {
  //     Assert.assertEquals(values[i],here.getData());
  //     here = here.getNext();
  //   }
  //   Assert.assertEquals(list.getTail(),here);
  //   if (values.length>0) {
  //     Assert.assertEquals(values[values.length-1],here.getData());
  //     Assert.assertEquals(null,here.getNext());
  //   } else {
  //     Assert.assertEquals(null,list.getHead());
  //   }
  // }
}