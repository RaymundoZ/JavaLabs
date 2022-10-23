package com.raymundo.lab1;

public class Container<T> {

    private static final int INIT_SIZE = 10;
    private Object[] array;
    private int index;
    private int size;

    public Container() {
        this.array = new Object[INIT_SIZE];
        this.index = 0;
        this.size = INIT_SIZE;
    }

    public Container(int size) {
        this.array = new Object[size];
        this.index = 0;
        this.size = size;
    }

    public void add(T object) {
        if (isArrayFull())
            expand();
        array[index++] = object;
    }

    public void add(T object, int index) {
        if (isArrayFull())
            expand();
        shiftUp(index);
        array[index] = object;
    }

    public void remove(int index) {
        shiftDown(index);
    }

    public void remove(T object) {
        for (int i = 0; i < size; i++)
            if (object.equals(array[i]))
                remove(i);
    }

    public T get(int index) {
        return (T) array[index];
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        String str = "[";
        for (Object element : array)
            if (element != null)
                str = str.concat(element.toString()).concat(", ");
        return str.substring(0, str.length() - 2).concat("]");
    }

    private boolean isArrayFull() {
        return index >= size;
    }

    private void expand(int amount) {
        size += amount;
        Object[] newArray = new Object[size];
        copy(array, newArray);
        array = newArray;
    }

    private void expand() {
        expand(10);
    }

    private void copy(Object[] a, Object[] b) {
        if (b.length >= a.length) {
            for (int i = 0; i < a.length; i++)
                b[i] = a[i];
        }
    }

    private void shiftUp(int index) {
        Object[] arr = new Object[size];
        int counter = 0;
        for (int i = index; i < size; i++)
            arr[counter++] = array[i];
        counter = 0;
        expand(1);
        for (int i = index + 1; i < size; i++)
            array[i] = arr[counter++];
        array[index] = null;
        this.index++;
    }

    private void shiftDown(int index) {
        Object[] arr = new Object[size];
        int counter = 0;
        for (int i = index + 1; i < size; i++)
            arr[counter++] = array[i];
        counter = 0;
        for (int i = index; i < size; i++)
            array[i] = arr[counter++];
        array[size - 1] = null;
        this.index--;
    }

}
