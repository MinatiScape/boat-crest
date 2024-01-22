package kotlin.jvm.internal;

import java.util.NoSuchElementException;
import kotlin.collections.BooleanIterator;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class a extends BooleanIterator {
    @NotNull
    public final boolean[] h;
    public int i;

    public a(@NotNull boolean[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        this.h = array;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.i < this.h.length;
    }

    @Override // kotlin.collections.BooleanIterator
    public boolean nextBoolean() {
        try {
            boolean[] zArr = this.h;
            int i = this.i;
            this.i = i + 1;
            return zArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.i--;
            throw new NoSuchElementException(e.getMessage());
        }
    }
}
