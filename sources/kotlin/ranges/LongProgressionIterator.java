package kotlin.ranges;

import java.util.NoSuchElementException;
import kotlin.collections.LongIterator;
/* loaded from: classes12.dex */
public final class LongProgressionIterator extends LongIterator {
    public final long h;
    public final long i;
    public boolean j;
    public long k;

    public LongProgressionIterator(long j, long j2, long j3) {
        this.h = j3;
        this.i = j2;
        boolean z = true;
        if (j3 <= 0 ? j < j2 : j > j2) {
            z = false;
        }
        this.j = z;
        this.k = z ? j : j2;
    }

    public final long getStep() {
        return this.h;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.j;
    }

    @Override // kotlin.collections.LongIterator
    public long nextLong() {
        long j = this.k;
        if (j == this.i) {
            if (this.j) {
                this.j = false;
            } else {
                throw new NoSuchElementException();
            }
        } else {
            this.k = this.h + j;
        }
        return j;
    }
}
