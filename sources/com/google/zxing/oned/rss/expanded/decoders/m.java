package com.google.zxing.oned.rss.expanded.decoders;
/* loaded from: classes11.dex */
public final class m extends p {
    public final char b;

    public m(int i, char c) {
        super(i);
        this.b = c;
    }

    public char b() {
        return this.b;
    }

    public boolean c() {
        return this.b == '$';
    }
}
