package kotlin.jvm.internal;

import java.util.NoSuchElementException;
import kotlin.collections.ShortIterator;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class i extends ShortIterator {
    @NotNull
    public final short[] h;
    public int i;

    public i(@NotNull short[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        this.h = array;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.i < this.h.length;
    }

    @Override // kotlin.collections.ShortIterator
    public short nextShort() {
        try {
            short[] sArr = this.h;
            int i = this.i;
            this.i = i + 1;
            return sArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.i--;
            throw new NoSuchElementException(e.getMessage());
        }
    }
}
