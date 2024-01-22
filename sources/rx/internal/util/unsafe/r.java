package rx.internal.util.unsafe;
/* loaded from: classes13.dex */
public abstract class r<E> extends ConcurrentCircularArrayQueue<E> {
    public static final Integer j = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096);
    public final int lookAheadStep;

    public r(int i) {
        super(i);
        this.lookAheadStep = Math.min(i / 4, j.intValue());
    }
}
