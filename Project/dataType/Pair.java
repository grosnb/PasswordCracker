package dataType;

public class Pair<T1, T2> {
    private T1 a;
    private T2 b;

    public Pair(T1 a, T2 b) {
        this.a = a;
        this.b = b;
    }

    public T1 getLeft() {
        return a;
    }

    public T2 getRight() {
        return b;
    }
}
