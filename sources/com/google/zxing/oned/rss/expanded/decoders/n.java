package com.google.zxing.oned.rss.expanded.decoders;
/* loaded from: classes11.dex */
public final class n extends p {
    public final String b;
    public final int c;
    public final boolean d;

    public n(int i, String str) {
        super(i);
        this.b = str;
        this.d = false;
        this.c = 0;
    }

    public String b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public boolean d() {
        return this.d;
    }

    public n(int i, String str, int i2) {
        super(i);
        this.d = true;
        this.c = i2;
        this.b = str;
    }
}
