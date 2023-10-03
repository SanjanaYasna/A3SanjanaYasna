import org.w3c.dom.Node;

import java.util.EmptyStackException;
import java.util.Stack;
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
    public T removeFirst(){
        if (isEmpty()){
            throw new MissingElementException();
        }
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
        if (isEmpty()){
            throw new MissingElementException();
        }
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
        if (isEmpty()){
            throw new MissingElementException();
        }
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
     *  Returns a count of the number of elemenFts in the list
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
        SLL<T> seqCopyList = new SLL<T>();
        seqCopyList.head = null;
        seqCopyList.tail = null;
        seqCopyList.size = n;
        if (seqCopyList.size ==0 || this.size == 0) return seqCopyList;
        NodeSL<T> node = head;
        while (node.getNext() != null){
            if (node == here){
                int i = n;
                while(node.getNext() != null && i >0){
                    seqCopyList.addLast(node.getData());
                    i--;
                    node = node.getNext();
                }
                if (i == 1){ //if you just have eto copy the tail and that's it
                    seqCopyList.addLast(tail.getData());
                }
        }
            else node = node.getNext();
    }
        return seqCopyList;
    }

    /**
     *  Places copy of the provided list into this after the specified node.
     *  @param list  the list to splice in a copy of
     *  @param afterHere  marks the position in this where the new list should go
     */
    public void spliceByCopy(SLL<T> list, NodeSL<T> afterHere){
        //can insert into itself
        if (afterHere == list.getHead()){
            throw new SelfInsertException();
        }
        NodeSL<T> node = head;
        //if you're adding to end
        if (afterHere == null){
            NodeSL<T> listNode = list.head;
            Stack<NodeSL<T>> stack = new Stack<NodeSL<T>>();
            for (int i = list.size; i > 0; i--){
                stack.push(listNode);
                listNode = listNode.getNext();
            }
            while (!stack.isEmpty()){
                this.addFirst(stack.pop().getData());
            }
        }
        else if (afterHere == tail){
            NodeSL<T> listNode = list.head;
            while (listNode.getNext() != null){
                this.addLast(listNode.getData());
                listNode = listNode.getNext();
            }
            this.addLast(list.tail.getData());
        }
        else {
            for (int i = this.size; i > 0; i --){
                if (node == afterHere){
                    NodeSL<T> listNode = list.head;
                    for (int j = list.size; j > 0; j--){
                        this.addAfter(node, listNode.getData());
                        node = node.getNext();
                        listNode = listNode.getNext();
                    }
                }
                else node = node.getNext();
            }
        }
        this.size = this.size + list.size;

    }

    /** 
     *  Extracts a subsequence of nodes and returns them as a new list
     *  @param afterHere  marks the node just before the extraction
     *  @param toHere  marks the node where the extraction ends
     *  @return  the new list
     */

    //exception for afterhere = tail, cuz then u can't rlly transfer..
    public SLL<T> subseqByTransfer(NodeSL<T> afterHere, NodeSL<T> toHere){
        SLL<T> extraction = new SLL<T>();
        if (afterHere == null) {
            afterHere = this.head;
            extraction.head = this.head;
            //if tohere is tail, throw exception for can't splice itself and basically become an empty list
            //(I assume that's probaby not the intention of this method?)
            if (toHere == tail){
                throw new EmptyStackException();
            }
            this.head = toHere.getNext();
            extraction.tail = toHere;
            toHere.setNext(null);
            return extraction;
        }
        else {
        extraction.head = afterHere.getNext();
        if (toHere == this.tail) afterHere.setNext(null);
        else afterHere.setNext(toHere.getNext());
        extraction.tail = toHere; 
        toHere.setNext(null);
        return extraction; 
        }
    }

    /** 
     *  Takes the provided list and inserts its elements into this
     *  after the specified node.  The inserted list ends up empty.
     *  @param list  the list to splice in.  Becomes empty after the call
     *  @param afterHere  Marks the place where the new elements are inserted
     */
    public void spliceByTransfer(SLL<T> list, NodeSL<T> afterHere){
        //can insert into itself
        if (afterHere == list.getHead()){
            throw new SelfInsertException();
        }
        if (afterHere ==null){
            list.tail.setNext(this.head);
            this.head = list.head;
            list.head = null;
            list.tail = null;
        }
        else{
            NodeSL<T> endAttachment = afterHere.getNext();
            afterHere.setNext(list.head);
            list.tail.setNext(endAttachment);
            list.head = null;
            list.tail = null;
        }
    }
 



        
    }

