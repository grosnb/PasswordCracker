package dataType;

public class Triplet<T1, T2, T3> {
    private T1 a;
    private T2 b;
    private T3 c;

    public Triplet(T1 a, T2 b, T3 c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public T1 getLeft() {
        return a;
    }

    public T2 getMid() {
        return b;
    }

    public T3 getRight() {
        return c;
    }
}
