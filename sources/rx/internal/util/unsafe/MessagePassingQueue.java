package rx.internal.util.unsafe;
/* loaded from: classes13.dex */
public interface MessagePassingQueue<M> {
    boolean isEmpty();

    boolean offer(M m);

    M peek();

    M poll();

    int size();
}
