package kotlin.ranges;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.SinceKotlin;
import kotlin.ULong;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;
@SinceKotlin(version = "1.3")
/* loaded from: classes12.dex */
public final class j implements Iterator<ULong>, KMappedMarker {
    public final long h;
    public boolean i;
    public final long j;
    public long k;

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001b, code lost:
        r1 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0011, code lost:
        if (r0 <= 0) goto L6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0018, code lost:
        if (r0 >= 0) goto L6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public j(long r4, long r6, long r8) {
        /*
            r3 = this;
            r3.<init>()
            r3.h = r6
            r0 = 0
            int r0 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            r1 = 1
            r2 = 0
            if (r0 <= 0) goto L14
            int r0 = kotlin.n.a(r4, r6)
            if (r0 > 0) goto L1b
            goto L1c
        L14:
            int r0 = kotlin.n.a(r4, r6)
            if (r0 < 0) goto L1b
            goto L1c
        L1b:
            r1 = r2
        L1c:
            r3.i = r1
            long r8 = kotlin.ULong.m182constructorimpl(r8)
            r3.j = r8
            boolean r8 = r3.i
            if (r8 == 0) goto L29
            goto L2a
        L29:
            r4 = r6
        L2a:
            r3.k = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.ranges.j.<init>(long, long, long):void");
    }

    public /* synthetic */ j(long j, long j2, long j3, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3);
    }

    public long a() {
        long j = this.k;
        if (j == this.h) {
            if (this.i) {
                this.i = false;
            } else {
                throw new NoSuchElementException();
            }
        } else {
            this.k = ULong.m182constructorimpl(this.j + j);
        }
        return j;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.i;
    }

    @Override // java.util.Iterator
    public /* bridge */ /* synthetic */ ULong next() {
        return ULong.m181boximpl(a());
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
