package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.FormatException;
/* loaded from: classes11.dex */
public final class o extends p {
    public final int b;
    public final int c;

    public o(int i, int i2, int i3) throws FormatException {
        super(i);
        if (i2 >= 0 && i2 <= 10 && i3 >= 0 && i3 <= 10) {
            this.b = i2;
            this.c = i3;
            return;
        }
        throw FormatException.getFormatInstance();
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public boolean d() {
        return this.b == 10;
    }

    public boolean e() {
        return this.c == 10;
    }
}
