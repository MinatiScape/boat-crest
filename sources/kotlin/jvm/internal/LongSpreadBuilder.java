package kotlin.jvm.internal;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class LongSpreadBuilder extends PrimitiveSpreadBuilder<long[]> {
    @NotNull
    public final long[] d;

    public LongSpreadBuilder(int i) {
        super(i);
        this.d = new long[i];
    }

    public final void add(long j) {
        long[] jArr = this.d;
        int position = getPosition();
        setPosition(position + 1);
        jArr[position] = j;
    }

    @NotNull
    public final long[] toArray() {
        return toArray(this.d, new long[size()]);
    }

    @Override // kotlin.jvm.internal.PrimitiveSpreadBuilder
    public int getSize(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return jArr.length;
    }
}
