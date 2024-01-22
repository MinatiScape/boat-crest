package kotlin.jvm.internal;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class CharSpreadBuilder extends PrimitiveSpreadBuilder<char[]> {
    @NotNull
    public final char[] d;

    public CharSpreadBuilder(int i) {
        super(i);
        this.d = new char[i];
    }

    public final void add(char c) {
        char[] cArr = this.d;
        int position = getPosition();
        setPosition(position + 1);
        cArr[position] = c;
    }

    @NotNull
    public final char[] toArray() {
        return toArray(this.d, new char[size()]);
    }

    @Override // kotlin.jvm.internal.PrimitiveSpreadBuilder
    public int getSize(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return cArr.length;
    }
}
