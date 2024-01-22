package kotlin.ranges;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.SinceKotlin;
import kotlin.UInt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;
@SinceKotlin(version = "1.3")
/* loaded from: classes12.dex */
public final class i implements Iterator<UInt>, KMappedMarker {
    public final int h;
    public boolean i;
    public final int j;
    public int k;

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0017, code lost:
        r0 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x000d, code lost:
        if (r2 <= 0) goto L6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0014, code lost:
        if (r2 >= 0) goto L6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public i(int r4, int r5, int r6) {
        /*
            r3 = this;
            r3.<init>()
            r3.h = r5
            r0 = 1
            r1 = 0
            if (r6 <= 0) goto L10
            int r2 = kotlin.collections.d0.a(r4, r5)
            if (r2 > 0) goto L17
            goto L18
        L10:
            int r2 = kotlin.collections.d0.a(r4, r5)
            if (r2 < 0) goto L17
            goto L18
        L17:
            r0 = r1
        L18:
            r3.i = r0
            int r6 = kotlin.UInt.m158constructorimpl(r6)
            r3.j = r6
            boolean r6 = r3.i
            if (r6 == 0) goto L25
            goto L26
        L25:
            r4 = r5
        L26:
            r3.k = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.ranges.i.<init>(int, int, int):void");
    }

    public /* synthetic */ i(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, i3);
    }

    public int a() {
        int i = this.k;
        if (i == this.h) {
            if (this.i) {
                this.i = false;
            } else {
                throw new NoSuchElementException();
            }
        } else {
            this.k = UInt.m158constructorimpl(this.j + i);
        }
        return i;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.i;
    }

    @Override // java.util.Iterator
    public /* bridge */ /* synthetic */ UInt next() {
        return UInt.m157boximpl(a());
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
