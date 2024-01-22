package kotlin.ranges;

import java.util.NoSuchElementException;
import kotlin.collections.IntIterator;
/* loaded from: classes12.dex */
public final class IntProgressionIterator extends IntIterator {
    public final int h;
    public final int i;
    public boolean j;
    public int k;

    public IntProgressionIterator(int i, int i2, int i3) {
        this.h = i3;
        this.i = i2;
        boolean z = true;
        if (i3 <= 0 ? i < i2 : i > i2) {
            z = false;
        }
        this.j = z;
        this.k = z ? i : i2;
    }

    public final int getStep() {
        return this.h;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.j;
    }

    @Override // kotlin.collections.IntIterator
    public int nextInt() {
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
        return i;
    }
}
