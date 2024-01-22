package kotlin.jvm.internal;

import java.util.NoSuchElementException;
import kotlin.collections.IntIterator;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class f extends IntIterator {
    @NotNull
    public final int[] h;
    public int i;

    public f(@NotNull int[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        this.h = array;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.i < this.h.length;
    }

    @Override // kotlin.collections.IntIterator
    public int nextInt() {
        try {
            int[] iArr = this.h;
            int i = this.i;
            this.i = i + 1;
            return iArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.i--;
            throw new NoSuchElementException(e.getMessage());
        }
    }
}
