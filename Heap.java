

// Implement your algorithms here
// Methods may be added to this file, but don't remove anything
// Include this file in your final submission

import java.util.ArrayList;
import java.util.Collections;

public class Heap<HeapMemberGeneric extends HeapMember> {
    private ArrayList<HeapMemberGeneric> minHeap;

    public Heap() {
        minHeap = new ArrayList<HeapMemberGeneric>();
    }

    private void heapifyUp(int i)
    {
        int parent;
        if (i == 0) {
            parent = 0;
        }else parent = (i-1)/2;

        if (minHeap.get(parent).getValue() > minHeap.get(i).getValue() && i > 0)
        {
            HeapMemberGeneric swapedHeapMember = minHeap.get(i);
            swapedHeapMember.setIndex(parent);
            minHeap.get(parent).setIndex(i);
            minHeap.set(i,minHeap.get(parent));
            minHeap.set(parent,swapedHeapMember);

            heapifyUp(parent);
        }
    }

    private void heapifyDown(int i)
    {
        int minimum = i;
        int left = 2*i+1;
        int right = 2*i+2;
        int size = minHeap.size();

        if (left < size && minHeap.get(left).getValue() < minHeap.get(minimum).getValue())
            minimum = left;

        if (right < size && minHeap.get(right).getValue() < minHeap.get(minimum).getValue())
            minimum = right;

        if (minimum != i) {
            HeapMemberGeneric swapedHeapMember = minHeap.get(i);
            swapedHeapMember.setIndex(minimum);
            minHeap.get(minimum).setIndex(i);
            minHeap.set(i,minHeap.get(minimum));
            minHeap.set(minimum,swapedHeapMember);

            heapifyDown(minimum);
        }
    }

    /**
     * buildHeap(ArrayList<HeapMember> heap_members)
     * Given an ArrayList of Regions, build a min-heap keyed on each HeapMember's minDist
     * Time Complexity - O(nlog(n)) or O(n)
     *
     * @param heap_members
     */
    public void buildHeap(ArrayList<HeapMemberGeneric> heap_members) {

        for(int i = 0; i < heap_members.size(); i++){
            heap_members.get(i).setIndex(i);
            minHeap.add(heap_members.get(i));
        }
        int firstIndex = (heap_members.size()/2)-1;

        for(int k = firstIndex; k > -1; k--){
            heapifyDown(k);
        }
    }

    /**
     * insertNode(HeapMemberGeneric in)
     * Insert a HeapMemberGeneric into the heap.
     * Time Complexity - O(log(n))
     *
     * @param in - the HeapMemberGeneric to insert.
     */
    public void insertNode(HeapMemberGeneric in) {
        minHeap.add(in);
        in.setIndex(minHeap.size() - 1);
        heapifyUp(minHeap.size() - 1);
    }

    /**
     * findMin()
     * Time Complexity - O(1)
     *
     * @return the minimum element of the heap.
     */
    public HeapMemberGeneric findMin() {
        
        return minHeap.get(0);
    }

    /**
     * extractMin()
     * Time Complexity - O(log(n))
     *
     * @return the minimum element of the heap, AND removes the element from said heap.
     */
    public HeapMemberGeneric extractMin() {
    if(minHeap.isEmpty()){
        return null;
    }
    else{
        HeapMemberGeneric min = minHeap.get(0);
        int lastElementIndex = minHeap.size() - 1;
        Collections.swap(minHeap, 0, lastElementIndex);
        minHeap.remove(lastElementIndex);
        heapifyDown(0);
        return min;
    }
}

    /**
     * delete(HeapMemberGeneric gen)
     * Deletes an element in the min-heap given a member to delete.
     * Sets index to -1 to indicate that the HeapMemberGeneric is no longer in the heap.
     * Time Complexity - O(log(n))
     *
     * @param gen - the member to be deleted in the min-heap.
     */
    public void delete(HeapMemberGeneric gen) {
        int index = gen.getIndex();
        if (index < minHeap.size() && index >= 0) {
            HeapMemberGeneric lastElement = minHeap.get(minHeap.size() - 1);
            minHeap.set(index, lastElement);
            lastElement.setIndex(index);
            minHeap.remove(minHeap.size() - 1);
            gen.setIndex(-1); //deleted
            if (index< minHeap.size()) {
                heapifyUp(index);
                heapifyDown(index);
            }
        }
    }
    

    /**
     * changeKey(HeapMemberGeneric r, int newKey)
     * Changes the attribute value of HeapMemberGeneric s to newKey and updates the heap.
     * Time Complexity - O(log(n))
     *
     * @param r       - the HeapMemberGeneric in the heap that needs to be updated.
     * @param newKey - the new value of HeapMemberGeneric r in the heap
     */
    public void changeKey(HeapMemberGeneric r, int newKey) {
        int index = r.getIndex();
        if (index >= 0 && index < minHeap.size()) {
            minHeap.get(index).setValue(newKey);
            heapifyUp(index);
            heapifyDown(index);
        }
    }

    

    /*
     *  contains(HeapMemberGeneric m)
     *  Checks if the HeapMemberGeneric m is in the heap.
     *  HeapMemberGeneric m, must have been added to the heap previously.
     *  Time Complexity - O(1)
     *
     *  @param m - the HeapMemberGeneric to check if it is in the heap.
     *
     */

    public boolean contains(HeapMemberGeneric m) {
        if(m.getIndex() == -1) return false;
        return true;
    }

    public int getSize() {
        return minHeap.size();
    }

    public String toString() {
        String output = "";
        for (int i = 0; i < minHeap.size(); i++) {
            output += "<index: " + minHeap.get(i).getIndex() + ", id: " + minHeap.get(i).getId() + ", value: " + minHeap.get(i).getValue() + ">\n";
        }
        return output;
    }


    /* Time Complexity - O(1)
     *
     * @return the minimum element of the heap.
     */

    public ArrayList<HeapMemberGeneric> toArrayList() {
        return minHeap;
    }

}
