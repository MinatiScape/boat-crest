package kotlin.jvm.internal;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class ByteSpreadBuilder extends PrimitiveSpreadBuilder<byte[]> {
    @NotNull
    public final byte[] d;

    public ByteSpreadBuilder(int i) {
        super(i);
        this.d = new byte[i];
    }

    public final void add(byte b) {
        byte[] bArr = this.d;
        int position = getPosition();
        setPosition(position + 1);
        bArr[position] = b;
    }

    @NotNull
    public final byte[] toArray() {
        return toArray(this.d, new byte[size()]);
    }

    @Override // kotlin.jvm.internal.PrimitiveSpreadBuilder
    public int getSize(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return bArr.length;
    }
}
