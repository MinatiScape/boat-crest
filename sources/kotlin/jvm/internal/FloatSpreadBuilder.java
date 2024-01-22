package kotlin.jvm.internal;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class FloatSpreadBuilder extends PrimitiveSpreadBuilder<float[]> {
    @NotNull
    public final float[] d;

    public FloatSpreadBuilder(int i) {
        super(i);
        this.d = new float[i];
    }

    public final void add(float f) {
        float[] fArr = this.d;
        int position = getPosition();
        setPosition(position + 1);
        fArr[position] = f;
    }

    @NotNull
    public final float[] toArray() {
        return toArray(this.d, new float[size()]);
    }

    @Override // kotlin.jvm.internal.PrimitiveSpreadBuilder
    public int getSize(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return fArr.length;
    }
}
