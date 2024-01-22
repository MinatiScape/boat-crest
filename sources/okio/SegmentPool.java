package okio;

import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class SegmentPool {
    @NotNull
    public static final SegmentPool INSTANCE = new SegmentPool();

    /* renamed from: a  reason: collision with root package name */
    public static final int f14316a = 65536;
    @NotNull
    public static final Segment b = new Segment(new byte[0], 0, 0, false, false);
    public static final int c;
    @NotNull
    public static final AtomicReference<Segment>[] d;

    static {
        int highestOneBit = Integer.highestOneBit((Runtime.getRuntime().availableProcessors() * 2) - 1);
        c = highestOneBit;
        AtomicReference<Segment>[] atomicReferenceArr = new AtomicReference[highestOneBit];
        for (int i = 0; i < highestOneBit; i++) {
            atomicReferenceArr[i] = new AtomicReference<>();
        }
        d = atomicReferenceArr;
    }

    @JvmStatic
    public static final void recycle(@NotNull Segment segment) {
        AtomicReference<Segment> a2;
        Segment segment2;
        Intrinsics.checkNotNullParameter(segment, "segment");
        if (segment.next == null && segment.prev == null) {
            if (segment.shared || (segment2 = (a2 = INSTANCE.a()).get()) == b) {
                return;
            }
            int i = segment2 != null ? segment2.limit : 0;
            if (i >= f14316a) {
                return;
            }
            segment.next = segment2;
            segment.pos = 0;
            segment.limit = i + 8192;
            if (a2.compareAndSet(segment2, segment)) {
                return;
            }
            segment.next = null;
            return;
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    @JvmStatic
    @NotNull
    public static final Segment take() {
        AtomicReference<Segment> a2 = INSTANCE.a();
        Segment segment = b;
        Segment andSet = a2.getAndSet(segment);
        if (andSet == segment) {
            return new Segment();
        }
        if (andSet == null) {
            a2.set(null);
            return new Segment();
        }
        a2.set(andSet.next);
        andSet.next = null;
        andSet.limit = 0;
        return andSet;
    }

    public final AtomicReference<Segment> a() {
        return d[(int) (Thread.currentThread().getId() & (c - 1))];
    }

    public final int getByteCount() {
        Segment segment = a().get();
        if (segment == null) {
            return 0;
        }
        return segment.limit;
    }

    public final int getMAX_SIZE() {
        return f14316a;
    }
}
