package rx.internal.util.unsafe;

import rx.internal.util.SuppressAnimalSniffer;
@SuppressAnimalSniffer
/* loaded from: classes13.dex */
public abstract class w<E> extends t<E> {
    public static final long P_INDEX_OFFSET = UnsafeAccess.addressOf(w.class, "producerIndex");
    public long producerIndex;
    public long producerLookAhead;

    public w(int i) {
        super(i);
    }
}
