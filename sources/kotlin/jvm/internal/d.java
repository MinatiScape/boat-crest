package kotlin.jvm.internal;

import java.util.NoSuchElementException;
import kotlin.collections.DoubleIterator;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class d extends DoubleIterator {
    @NotNull
    public final double[] h;
    public int i;

    public d(@NotNull double[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        this.h = array;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.i < this.h.length;
    }

    @Override // kotlin.collections.DoubleIterator
    public double nextDouble() {
        try {
            double[] dArr = this.h;
            int i = this.i;
            this.i = i + 1;
            return dArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.i--;
            throw new NoSuchElementException(e.getMessage());
        }
    }
}
