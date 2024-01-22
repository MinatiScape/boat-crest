package kotlin.jvm.internal;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class BooleanSpreadBuilder extends PrimitiveSpreadBuilder<boolean[]> {
    @NotNull
    public final boolean[] d;

    public BooleanSpreadBuilder(int i) {
        super(i);
        this.d = new boolean[i];
    }

    public final void add(boolean z) {
        boolean[] zArr = this.d;
        int position = getPosition();
        setPosition(position + 1);
        zArr[position] = z;
    }

    @NotNull
    public final boolean[] toArray() {
        return toArray(this.d, new boolean[size()]);
    }

    @Override // kotlin.jvm.internal.PrimitiveSpreadBuilder
    public int getSize(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return zArr.length;
    }
}
