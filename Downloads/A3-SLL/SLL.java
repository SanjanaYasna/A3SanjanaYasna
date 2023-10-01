import org.w3c.dom.Node;
//since nothing is mentioned about error exception handling (like removing from an empty list), certain test cases are of course not accoutned for.
//tests were passed anyway, so I suppose that's just a given for this assignment
public class SLL<T> implements Phase1SLL<T>, Phase2SLL<T>, Phase3SLL<T> {
    NodeSL<T> head;
    NodeSL<T> tail;
    int size = 0;

    public SLL(SLL<T> linkedList){
        //empty list
        if (linkedList.isEmpty()){
            this.head = null;
            this.tail = null;
            this.size = linkedList.size;
        }
        //otherwise 1+ elements
        else{
            NodeSL<T> start = linkedList.getHead();
            while (start.getNext() != null){
                this.addLast(start.getData());
                start = start.getNext();
            }
            if (linkedList.size == 1){
                this.addLast(start.getData());
            }
            this.head = linkedList.getHead();
            this.tail = linkedList.getTail();
            this.size = linkedList.size;
    }

    }

    public SLL() {
        this.head = null;
        this.tail = null;
    }


    public NodeSL<T> getHead() {
        return head;
    }


    public NodeSL<T> getTail() {
       return tail;
    }


    public boolean isEmpty() {
        return (head == null && tail == null);
    }

  
    public void addFirst(T v) {
        if (isEmpty()){ //edge case if empty and tail null, piont it to null tail
            NodeSL<T> frontNode = new NodeSL<T>(v, null);
            head = frontNode;
            tail = frontNode;
        }
        else{
            NodeSL<T> frontNode = new NodeSL<T>(v, head);
            head = frontNode;
        }
        size++;
    }
    
    public String toString(){
        if (isEmpty()) return "[]";
        String res = "[";
        NodeSL<T> item = head;
        //to avoid null pointer exception in case of just one element
       /*  if (item.getNext() == null){
            return res += item.getData() + "]";
        }*/
        while(item.getNext() != null){
            res += item.getData() + ", ";
            item = item.getNext();
        }
        res += item.getData();
        res += "]";
        return res;
    }

    public void addLast(T v){
        if(isEmpty()){
            NodeSL<T> endNode = new NodeSL<T>(v, null);
            tail = endNode;
            head = endNode; //not so sure abt this one
        }
        else{
            NodeSL<T> node = head;
            NodeSL<T> endNode = new NodeSL<T>(v, null);
            while (node.getNext() != null){
                node = node.getNext();
            }
            node.setNext(endNode);
            tail = endNode;
    }
        size++;
        }
        

    

    /** 
     *  Inserts the given item after the specified node
     *  @param here node to insert after
     *  @param v item to insert 
     */
    public void addAfter(NodeSL<T> here, T v){
        NodeSL<T> node = head;
        if (here == tail) {
            NodeSL<T> newNode = new NodeSL<T>(v, null);
            here.setNext(newNode);
            tail = newNode;
        }
        else{
            while (node.getNext() != null){
                if (node == here){
                    NodeSL<T> newNode = new NodeSL<T>(v, here.getNext());
                    here.setNext(newNode);
                    }
                node = node.getNext();
            }   
        } 
        size++;   
    }

    /** 
     *  Removes the given item from the head of the list
     *  @return v item removed
     */
    //since exception handling isn't mentioned, I won't account for the edge case where it's empty
    public T removeFirst(){
        NodeSL<T> originalHead = this.head;
        this.head = head.getNext();
        size--;
        return originalHead.getData();
    }

    /** 
     *  Removes the given item from the tail of the list
     *  @return item removed
     */
    public T removeLast(){
        if (head == tail){
            T onlyNode = tail.getData();
            head = null;
            tail = null;
            size--;
            return onlyNode;
        }
        else{
            NodeSL<T> node = head;
            while (node.getNext() != tail){
                    node = node.getNext();
                }
            T returnLast = tail.getData();
            tail = node;
            node.setNext(null);
            size--;
            return returnLast;
        }
    }

    /** 
     *  Removes the node after the given position
     *  @param here marks position to remove after
     *  @return item removed
     */
    //size -- remember
    public T removeAfter(NodeSL<T> here){
        NodeSL<T> node = head;
        //if you're removing when there's only one element:
        if (here == null){
            T returnVal = head.getData();
            tail = null;
            head = null;
            size --;
            return returnVal;
        }
        T returnValue = here.getNext().getData();
        while (node.getNext() != null){
            if (node == here){
                //if hyou're removing the last element
                if (here.getNext() == tail){
                    here.setNext(null);
                    tail = here;
                }
                //normal add after
                else here.setNext(here.getNext().getNext());
                }
            else node = node.getNext();
        }  
        size--;  
        return returnValue;
    }

    /**
     *  Returns a count of the number of elements in the list
     *  @return current number of nodes
     */
    public int size(){
        return size;
    }

    /** 
     *  Makes a copy of elements from the original list
     *  @param here  starting point of copy
     *  @param n  number of items to copy
     *  @return the copied list
     */
    public SLL<T> subseqByCopy(NodeSL<T> here, int n){
        return null;
    }

    /**
     *  Places copy of the provided list into this after the specified node.
     *  @param list  the list to splice in a copy of
     *  @param afterHere  marks the position in this where the new list should go
     */
    public void spliceByCopy(SLL<T> list, NodeSL<T> afterHere){

    }

    /** 
     *  Extracts a subsequence of nodes and returns them as a new list
     *  @param afterHere  marks the node just before the extraction
     *  @param toHere  marks the node where the extraction ends
     *  @return  the new list
     */
    public SLL<T> subseqByTransfer(NodeSL<T> afterHere, NodeSL<T> toHere){
        return null;
    }

    /** 
     *  Takes the provided list and inserts its elements into this
     *  after the specified node.  The inserted list ends up empty.
     *  @param list  the list to splice in.  Becomes empty after the call
     *  @param afterHere  Marks the place where the new elements are inserted
     */
    public void spliceByTransfer(SLL<T> list, NodeSL<T> afterHere){
       
    }
 



        
    }

