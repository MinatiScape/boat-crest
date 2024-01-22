package kotlin.text;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class DelimitedRangesSequence$iterator$1 implements Iterator<IntRange>, KMappedMarker {
    public int h = -1;
    public int i;
    public int j;
    @Nullable
    public IntRange k;
    public int l;
    public final /* synthetic */ DelimitedRangesSequence m;

    public DelimitedRangesSequence$iterator$1(DelimitedRangesSequence delimitedRangesSequence) {
        int i;
        CharSequence charSequence;
        this.m = delimitedRangesSequence;
        i = delimitedRangesSequence.b;
        charSequence = delimitedRangesSequence.f14120a;
        int coerceIn = kotlin.ranges.h.coerceIn(i, 0, charSequence.length());
        this.i = coerceIn;
        this.j = coerceIn;
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0021, code lost:
        if (r0 < r4) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a() {
        /*
            r6 = this;
            int r0 = r6.j
            r1 = 0
            if (r0 >= 0) goto Lc
            r6.h = r1
            r0 = 0
            r6.k = r0
            goto L9e
        Lc:
            kotlin.text.DelimitedRangesSequence r0 = r6.m
            int r0 = kotlin.text.DelimitedRangesSequence.c(r0)
            r2 = -1
            r3 = 1
            if (r0 <= 0) goto L23
            int r0 = r6.l
            int r0 = r0 + r3
            r6.l = r0
            kotlin.text.DelimitedRangesSequence r4 = r6.m
            int r4 = kotlin.text.DelimitedRangesSequence.c(r4)
            if (r0 >= r4) goto L31
        L23:
            int r0 = r6.j
            kotlin.text.DelimitedRangesSequence r4 = r6.m
            java.lang.CharSequence r4 = kotlin.text.DelimitedRangesSequence.b(r4)
            int r4 = r4.length()
            if (r0 <= r4) goto L47
        L31:
            kotlin.ranges.IntRange r0 = new kotlin.ranges.IntRange
            int r1 = r6.i
            kotlin.text.DelimitedRangesSequence r4 = r6.m
            java.lang.CharSequence r4 = kotlin.text.DelimitedRangesSequence.b(r4)
            int r4 = kotlin.text.StringsKt__StringsKt.getLastIndex(r4)
            r0.<init>(r1, r4)
            r6.k = r0
            r6.j = r2
            goto L9c
        L47:
            kotlin.text.DelimitedRangesSequence r0 = r6.m
            kotlin.jvm.functions.Function2 r0 = kotlin.text.DelimitedRangesSequence.a(r0)
            kotlin.text.DelimitedRangesSequence r4 = r6.m
            java.lang.CharSequence r4 = kotlin.text.DelimitedRangesSequence.b(r4)
            int r5 = r6.j
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.Object r0 = r0.invoke(r4, r5)
            kotlin.Pair r0 = (kotlin.Pair) r0
            if (r0 != 0) goto L77
            kotlin.ranges.IntRange r0 = new kotlin.ranges.IntRange
            int r1 = r6.i
            kotlin.text.DelimitedRangesSequence r4 = r6.m
            java.lang.CharSequence r4 = kotlin.text.DelimitedRangesSequence.b(r4)
            int r4 = kotlin.text.StringsKt__StringsKt.getLastIndex(r4)
            r0.<init>(r1, r4)
            r6.k = r0
            r6.j = r2
            goto L9c
        L77:
            java.lang.Object r2 = r0.component1()
            java.lang.Number r2 = (java.lang.Number) r2
            int r2 = r2.intValue()
            java.lang.Object r0 = r0.component2()
            java.lang.Number r0 = (java.lang.Number) r0
            int r0 = r0.intValue()
            int r4 = r6.i
            kotlin.ranges.IntRange r4 = kotlin.ranges.h.until(r4, r2)
            r6.k = r4
            int r2 = r2 + r0
            r6.i = r2
            if (r0 != 0) goto L99
            r1 = r3
        L99:
            int r2 = r2 + r1
            r6.j = r2
        L9c:
            r6.h = r3
        L9e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.DelimitedRangesSequence$iterator$1.a():void");
    }

    public final int getCounter() {
        return this.l;
    }

    public final int getCurrentStartIndex() {
        return this.i;
    }

    @Nullable
    public final IntRange getNextItem() {
        return this.k;
    }

    public final int getNextSearchIndex() {
        return this.j;
    }

    public final int getNextState() {
        return this.h;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.h == -1) {
            a();
        }
        return this.h == 1;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final void setCounter(int i) {
        this.l = i;
    }

    public final void setCurrentStartIndex(int i) {
        this.i = i;
    }

    public final void setNextItem(@Nullable IntRange intRange) {
        this.k = intRange;
    }

    public final void setNextSearchIndex(int i) {
        this.j = i;
    }

    public final void setNextState(int i) {
        this.h = i;
    }

    @Override // java.util.Iterator
    @NotNull
    public IntRange next() {
        if (this.h == -1) {
            a();
        }
        if (this.h != 0) {
            IntRange intRange = this.k;
            Intrinsics.checkNotNull(intRange, "null cannot be cast to non-null type kotlin.ranges.IntRange");
            this.k = null;
            this.h = -1;
            return intRange;
        }
        throw new NoSuchElementException();
    }
}
