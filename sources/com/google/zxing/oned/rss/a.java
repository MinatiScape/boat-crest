package com.google.zxing.oned.rss;
/* loaded from: classes11.dex */
public final class a extends DataCharacter {
    public final FinderPattern c;
    public int d;

    public a(int i, int i2, FinderPattern finderPattern) {
        super(i, i2);
        this.c = finderPattern;
    }

    public int a() {
        return this.d;
    }

    public FinderPattern b() {
        return this.c;
    }

    public void c() {
        this.d++;
    }
}
