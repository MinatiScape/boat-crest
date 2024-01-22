package com.google.android.gms.internal.firebase_ml;
/* loaded from: classes7.dex */
public abstract class b2 extends k1<String> {
    public final CharSequence j;
    public final zzlw k;
    public final boolean l;
    public int m = 0;
    public int n;

    public b2(zzmm zzmmVar, CharSequence charSequence) {
        zzlw zzlwVar;
        int i;
        zzlwVar = zzmmVar.f8787a;
        this.k = zzlwVar;
        this.l = false;
        i = zzmmVar.c;
        this.n = i;
        this.j = charSequence;
    }

    @Override // com.google.android.gms.internal.firebase_ml.k1
    public final /* synthetic */ String a() {
        int c;
        int i = this.m;
        while (true) {
            int i2 = this.m;
            if (i2 != -1) {
                c = c(i2);
                if (c == -1) {
                    c = this.j.length();
                    this.m = -1;
                } else {
                    this.m = d(c);
                }
                int i3 = this.m;
                if (i3 == i) {
                    int i4 = i3 + 1;
                    this.m = i4;
                    if (i4 > this.j.length()) {
                        this.m = -1;
                    }
                } else {
                    while (i < c && this.k.zzb(this.j.charAt(i))) {
                        i++;
                    }
                    while (c > i && this.k.zzb(this.j.charAt(c - 1))) {
                        c--;
                    }
                    if (!this.l || i != c) {
                        break;
                    }
                    i = this.m;
                }
            } else {
                b();
                return null;
            }
        }
        int i5 = this.n;
        if (i5 == 1) {
            c = this.j.length();
            this.m = -1;
            while (c > i && this.k.zzb(this.j.charAt(c - 1))) {
                c--;
            }
        } else {
            this.n = i5 - 1;
        }
        return this.j.subSequence(i, c).toString();
    }

    public abstract int c(int i);

    public abstract int d(int i);
}
