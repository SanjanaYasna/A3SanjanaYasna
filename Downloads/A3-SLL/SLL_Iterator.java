/** Keeps track of position in a linked list */
public class SLL_Iterator<T> implements Phase5SLL_Iterator<T> {
    /**
     * Creates a new iterator on the given list.
     * Default position is leftmost
     * 
     * @param list the list to iterate on
     */
    NodeSL<T> currNode;
    NodeSL<T> prevNode;
    NodeSL<T> head;
    NodeSL<T> tail;
    SLL<T> list;
    public SLL_Iterator(SLL<T> list) {
        tail = list.tail;
        head = list.head;
        this.list = list;
        currNode = new NodeSL<T>(null, list.head);
        prevNode = new NodeSL<T>(null, currNode);
    }

    /**
     * Tests whether there are any more
     * 
     * @return T/F is it safe to call next()?
     */
    public boolean hasNext() {
        if (currNode.getNext() != null) return true;
        return false;
    }

    /**
     * Returns next or throws an exception
     * and advances the iterator
     * 
     * @return the next element
     */
    public T next() {
        if (hasNext()){
            T node = currNode.getNext().getData();
            prevNode = currNode;
            currNode = currNode.getNext();
            return node;
        }
        else throw new NullPointerException();
        /*if (hasNext()){
            T node = currNode.getData();
            prevNode = currNode;
            currNode = currNode.getNext();
            return node;
        }
        else throw new NullPointerException();*/
    }

    /**
     * Sets the data for the element just passed
     * 
     * @param data value to set
     */
    public void set(T data) {
        if (list.isEmpty()) throw new MissingElementException();
        currNode.setData(data);
    }

    /**
     * Returns the data for the element just passed
     * 
     * @return data value in the element just passed
     */
    public T get() {
        return currNode.getData();
    }

    /**
     * Inserts a node with the specified data
     * Cannot be called twice in a row without intervening next()
     * 
     * @param data the value to insert
     */
    public void add(T data) {
        if (currNode.getNext() == list.head){
            list.addFirst(data);
            currNode = list.head;
            prevNode.setNext(currNode);
        }

        else {
            list.addAfter(currNode, data);
            this.next();
            /*NodeSL<T> newNode = new NodeSL<T>(data, null);
            currNode = this.tail;
            prevNode.setNext(currNode);*/
        }     
        }
    

    /**
     * Removes the node just passed
     * Cannot be called twice in a row without intervening next()
     */
    public void remove() {
        if (currNode.getNext() == list.head || list.isEmpty()){throw new MissingElementException(); }
        list.removeFirst();
        this.next();
        System.out.println("cur" + currNode.getData());
        System.out.println("prev" + prevNode.getData());
        System.out.println(list.toString());

    }
}