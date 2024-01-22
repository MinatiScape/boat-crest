package com.google.zxing.datamatrix.encoder;

import com.google.zxing.Dimension;
import java.nio.charset.StandardCharsets;
/* loaded from: classes11.dex */
public final class g {

    /* renamed from: a  reason: collision with root package name */
    public final String f11811a;
    public SymbolShapeHint b;
    public Dimension c;
    public Dimension d;
    public final StringBuilder e;
    public int f;
    public int g;
    public SymbolInfo h;
    public int i;

    public g(String str) {
        byte[] bytes = str.getBytes(StandardCharsets.ISO_8859_1);
        StringBuilder sb = new StringBuilder(bytes.length);
        int length = bytes.length;
        for (int i = 0; i < length; i++) {
            char c = (char) (bytes[i] & 255);
            if (c == '?' && str.charAt(i) != '?') {
                throw new IllegalArgumentException("Message contains characters outside ISO-8859-1 encoding.");
            }
            sb.append(c);
        }
        this.f11811a = sb.toString();
        this.b = SymbolShapeHint.FORCE_NONE;
        this.e = new StringBuilder(str.length());
        this.g = -1;
    }

    public int a() {
        return this.e.length();
    }

    public StringBuilder b() {
        return this.e;
    }

    public char c() {
        return this.f11811a.charAt(this.f);
    }

    public String d() {
        return this.f11811a;
    }

    public int e() {
        return this.g;
    }

    public int f() {
        return h() - this.f;
    }

    public SymbolInfo g() {
        return this.h;
    }

    public final int h() {
        return this.f11811a.length() - this.i;
    }

    public boolean i() {
        return this.f < h();
    }

    public void j() {
        this.g = -1;
    }

    public void k() {
        this.h = null;
    }

    public void l(Dimension dimension, Dimension dimension2) {
        this.c = dimension;
        this.d = dimension2;
    }

    public void m(int i) {
        this.i = i;
    }

    public void n(SymbolShapeHint symbolShapeHint) {
        this.b = symbolShapeHint;
    }

    public void o(int i) {
        this.g = i;
    }

    public void p() {
        q(a());
    }

    public void q(int i) {
        SymbolInfo symbolInfo = this.h;
        if (symbolInfo == null || i > symbolInfo.getDataCapacity()) {
            this.h = SymbolInfo.lookup(i, this.b, this.c, this.d, true);
        }
    }

    public void r(char c) {
        this.e.append(c);
    }

    public void s(String str) {
        this.e.append(str);
    }
}
