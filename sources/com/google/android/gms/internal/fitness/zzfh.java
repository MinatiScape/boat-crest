package com.google.android.gms.internal.fitness;

import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes8.dex */
public abstract class zzfh<E> extends zzfd<E> implements Set<E> {
    @NullableDecl
    private transient zzfc<E> zztl;

    /*  JADX ERROR: JadxOverflowException in pass: LoopRegionVisitor
        jadx.core.utils.exceptions.JadxOverflowException: LoopRegionVisitor.assignOnlyInLoop endless recursion
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:56)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:30)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:18)
        */
    public static <E> com.google.android.gms.internal.fitness.zzfh<E> zza(E r11, E r12, E r13, E r14, E r15) {
        /*
            r0 = 5
            java.lang.Object[] r1 = new java.lang.Object[r0]
            r2 = 0
            r1[r2] = r11
            r11 = 1
            r1[r11] = r12
            r12 = 2
            r1[r12] = r13
            r12 = 3
            r1[r12] = r14
            r13 = 4
            r1[r13] = r15
        L12:
            if (r0 == 0) goto L7a
            if (r0 == r11) goto L72
            int r13 = zzh(r0)
            java.lang.Object[] r6 = new java.lang.Object[r13]
            int r7 = r13 + (-1)
            r14 = r2
            r5 = r14
            r8 = r5
        L21:
            if (r14 >= r0) goto L4c
            r15 = r1[r14]
            java.lang.Object r15 = com.google.android.gms.internal.fitness.zzfj.a(r15, r14)
            int r3 = r15.hashCode()
            int r4 = com.google.android.gms.internal.fitness.n1.a(r3)
        L31:
            r9 = r4 & r7
            r10 = r6[r9]
            if (r10 != 0) goto L40
            int r4 = r8 + 1
            r1[r8] = r15
            r6[r9] = r15
            int r5 = r5 + r3
            r8 = r4
            goto L49
        L40:
            boolean r9 = r10.equals(r15)
            if (r9 != 0) goto L49
            int r4 = r4 + 1
            goto L31
        L49:
            int r14 = r14 + 1
            goto L21
        L4c:
            r14 = 0
            java.util.Arrays.fill(r1, r8, r0, r14)
            if (r8 != r11) goto L5a
            r11 = r1[r2]
            com.google.android.gms.internal.fitness.u1 r12 = new com.google.android.gms.internal.fitness.u1
            r12.<init>(r11, r5)
            return r12
        L5a:
            int r14 = zzh(r8)
            int r13 = r13 / 2
            if (r14 >= r13) goto L64
            r0 = r8
            goto L12
        L64:
            if (r8 >= r12) goto L6a
            java.lang.Object[] r1 = java.util.Arrays.copyOf(r1, r8)
        L6a:
            r4 = r1
            com.google.android.gms.internal.fitness.t1 r11 = new com.google.android.gms.internal.fitness.t1
            r3 = r11
            r3.<init>(r4, r5, r6, r7, r8)
            return r11
        L72:
            r11 = r1[r2]
            com.google.android.gms.internal.fitness.u1 r12 = new com.google.android.gms.internal.fitness.u1
            r12.<init>(r11)
            return r12
        L7a:
            com.google.android.gms.internal.fitness.t1<java.lang.Object> r11 = com.google.android.gms.internal.fitness.t1.zzto
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.fitness.zzfh.zza(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object):com.google.android.gms.internal.fitness.zzfh");
    }

    private static int zzh(int i) {
        int max = Math.max(i, 2);
        if (max >= 751619276) {
            if (max < 1073741824) {
                return 1073741824;
            }
            throw new IllegalArgumentException("collection too large");
        }
        int highestOneBit = Integer.highestOneBit(max - 1) << 1;
        while (highestOneBit * 0.7d < max) {
            highestOneBit <<= 1;
        }
        return highestOneBit;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzfh) && zzak() && ((zzfh) obj).zzak() && hashCode() != obj.hashCode()) {
            return false;
        }
        return zzfk.a(this, obj);
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        Iterator<E> it = iterator();
        int i = 0;
        while (it.hasNext()) {
            E next = it.next();
            i = ~(~(i + (next != null ? next.hashCode() : 0)));
        }
        return i;
    }

    @Override // com.google.android.gms.internal.fitness.zzfd, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public /* synthetic */ Iterator iterator() {
        return iterator();
    }

    @Override // com.google.android.gms.internal.fitness.zzfd
    public final zzfc<E> zzaf() {
        zzfc<E> zzfcVar = this.zztl;
        if (zzfcVar == null) {
            zzfc<E> zzal = zzal();
            this.zztl = zzal;
            return zzal;
        }
        return zzfcVar;
    }

    public boolean zzak() {
        return false;
    }

    public zzfc<E> zzal() {
        return zzfc.zza(toArray());
    }
}
