package kotlin.ranges;

import java.util.NoSuchElementException;
import kotlin.collections.CharIterator;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes12.dex */
public final class CharProgressionIterator extends CharIterator {
    public final int h;
    public final int i;
    public boolean j;
    public int k;

    public CharProgressionIterator(char c, char c2, int i) {
        this.h = i;
        this.i = c2;
        boolean z = true;
        if (i <= 0 ? Intrinsics.compare((int) c, (int) c2) < 0 : Intrinsics.compare((int) c, (int) c2) > 0) {
            z = false;
        }
        this.j = z;
        this.k = z ? c : c2;
    }

    public final int getStep() {
        return this.h;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.j;
    }

    @Override // kotlin.collections.CharIterator
    public char nextChar() {
        int i = this.k;
        if (i == this.i) {
            if (this.j) {
                this.j = false;
            } else {
                throw new NoSuchElementException();
            }
        } else {
            this.k = this.h + i;
        }
        return (char) i;
    }
}
