package rx.internal.util.unsafe;

import rx.internal.util.SuppressAnimalSniffer;
@SuppressAnimalSniffer
/* loaded from: classes13.dex */
public abstract class s<E> extends u<E> {
    public static final long C_INDEX_OFFSET = UnsafeAccess.addressOf(s.class, "consumerIndex");
    public long consumerIndex;

    public s(int i) {
        super(i);
    }
}
