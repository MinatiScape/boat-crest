package kotlin.jvm.internal;

import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public abstract class PrimitiveSpreadBuilder<T> {

    /* renamed from: a  reason: collision with root package name */
    public final int f14081a;
    public int b;
    @NotNull
    public final T[] c;

    public PrimitiveSpreadBuilder(int i) {
        this.f14081a = i;
        this.c = (T[]) new Object[i];
    }

    public final void addSpread(@NotNull T spreadArgument) {
        Intrinsics.checkNotNullParameter(spreadArgument, "spreadArgument");
        T[] tArr = this.c;
        int i = this.b;
        this.b = i + 1;
        tArr[i] = spreadArgument;
    }

    public final int getPosition() {
        return this.b;
    }

    public abstract int getSize(@NotNull T t);

    public final void setPosition(int i) {
        this.b = i;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [kotlin.collections.IntIterator, java.util.Iterator] */
    public final int size() {
        int i = 0;
        ?? it = new IntRange(0, this.f14081a - 1).iterator();
        while (it.hasNext()) {
            T t = this.c[it.nextInt()];
            i += t != null ? getSize(t) : 1;
        }
        return i;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [kotlin.collections.IntIterator, java.util.Iterator] */
    @NotNull
    public final T toArray(@NotNull T values, @NotNull T result) {
        Intrinsics.checkNotNullParameter(values, "values");
        Intrinsics.checkNotNullParameter(result, "result");
        ?? it = new IntRange(0, this.f14081a - 1).iterator();
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            int nextInt = it.nextInt();
            T t = this.c[nextInt];
            if (t != null) {
                if (i < nextInt) {
                    int i3 = nextInt - i;
                    System.arraycopy(values, i, result, i2, i3);
                    i2 += i3;
                }
                int size = getSize(t);
                System.arraycopy(t, 0, result, i2, size);
                i2 += size;
                i = nextInt + 1;
            }
        }
        int i4 = this.f14081a;
        if (i < i4) {
            System.arraycopy(values, i, result, i2, i4 - i);
        }
        return result;
    }
}
