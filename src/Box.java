public class Box<T> {

    private T content;

    public void put(T item) {
        if (content != null) {
            throw new IllegalStateException("Box is not empty");
        }
        content = item;
    }

    public T get() {
        T temp = content;
        content = null;
        return temp;
    }

    public boolean isEmpty() {
        return content == null;
    }
}
