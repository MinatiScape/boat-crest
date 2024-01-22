package kotlin.jvm.internal;

import java.util.NoSuchElementException;
import kotlin.collections.CharIterator;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class c extends CharIterator {
    @NotNull
    public final char[] h;
    public int i;

    public c(@NotNull char[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        this.h = array;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.i < this.h.length;
    }

    @Override // kotlin.collections.CharIterator
    public char nextChar() {
        try {
            char[] cArr = this.h;
            int i = this.i;
            this.i = i + 1;
            return cArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.i--;
            throw new NoSuchElementException(e.getMessage());
        }
    }
}
