package kotlin.jvm.internal;

import java.util.NoSuchElementException;
import kotlin.collections.LongIterator;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class h extends LongIterator {
    @NotNull
    public final long[] h;
    public int i;

    public h(@NotNull long[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        this.h = array;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.i < this.h.length;
    }

    @Override // kotlin.collections.LongIterator
    public long nextLong() {
        try {
            long[] jArr = this.h;
            int i = this.i;
            this.i = i + 1;
            return jArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.i--;
            throw new NoSuchElementException(e.getMessage());
        }
    }
}
