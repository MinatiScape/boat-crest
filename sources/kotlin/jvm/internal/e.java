package kotlin.jvm.internal;

import java.util.NoSuchElementException;
import kotlin.collections.FloatIterator;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class e extends FloatIterator {
    @NotNull
    public final float[] h;
    public int i;

    public e(@NotNull float[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        this.h = array;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.i < this.h.length;
    }

    @Override // kotlin.collections.FloatIterator
    public float nextFloat() {
        try {
            float[] fArr = this.h;
            int i = this.i;
            this.i = i + 1;
            return fArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.i--;
            throw new NoSuchElementException(e.getMessage());
        }
    }
}
