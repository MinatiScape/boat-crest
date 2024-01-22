package kotlin.jvm.internal;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class IntSpreadBuilder extends PrimitiveSpreadBuilder<int[]> {
    @NotNull
    public final int[] d;

    public IntSpreadBuilder(int i) {
        super(i);
        this.d = new int[i];
    }

    public final void add(int i) {
        int[] iArr = this.d;
        int position = getPosition();
        setPosition(position + 1);
        iArr[position] = i;
    }

    @NotNull
    public final int[] toArray() {
        return toArray(this.d, new int[size()]);
    }

    @Override // kotlin.jvm.internal.PrimitiveSpreadBuilder
    public int getSize(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return iArr.length;
    }
}
