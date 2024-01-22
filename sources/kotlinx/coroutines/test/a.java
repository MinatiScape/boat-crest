package kotlinx.coroutines.test;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.ThreadSafeHeap;
import kotlinx.coroutines.internal.ThreadSafeHeapNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class a implements Comparable<a>, Runnable, ThreadSafeHeapNode {
    @NotNull
    public final Runnable h;
    public final long i;
    @JvmField
    public final long j;
    @Nullable
    public ThreadSafeHeap<?> k;
    public int l;

    public a(@NotNull Runnable runnable, long j, long j2) {
        this.h = runnable;
        this.i = j;
        this.j = j2;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(@NotNull a aVar) {
        long j = this.j;
        long j2 = aVar.j;
        if (j == j2) {
            return Intrinsics.compare(this.i, aVar.i);
        }
        return Intrinsics.compare(j, j2);
    }

    @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
    @Nullable
    public ThreadSafeHeap<?> getHeap() {
        return this.k;
    }

    @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
    public int getIndex() {
        return this.l;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.h.run();
    }

    @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
    public void setHeap(@Nullable ThreadSafeHeap<?> threadSafeHeap) {
        this.k = threadSafeHeap;
    }

    @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
    public void setIndex(int i) {
        this.l = i;
    }

    @NotNull
    public String toString() {
        return "TimedRunnable(time=" + this.j + ", run=" + this.h + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ a(Runnable runnable, long j, long j2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(runnable, (i & 2) != 0 ? 0L : j, (i & 4) != 0 ? 0L : j2);
    }
}
