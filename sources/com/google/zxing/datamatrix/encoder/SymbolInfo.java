package com.google.zxing.datamatrix.encoder;

import com.google.zxing.Dimension;
import com.realsil.sdk.dfu.DfuException;
/* loaded from: classes11.dex */
public class SymbolInfo {
    public static SymbolInfo[] g = {new SymbolInfo(false, 3, 5, 8, 8, 1), new SymbolInfo(false, 5, 7, 10, 10, 1), new SymbolInfo(true, 5, 7, 16, 6, 1), new SymbolInfo(false, 8, 10, 12, 12, 1), new SymbolInfo(true, 10, 11, 14, 6, 2), new SymbolInfo(false, 12, 12, 14, 14, 1), new SymbolInfo(true, 16, 14, 24, 10, 1), new SymbolInfo(false, 18, 14, 16, 16, 1), new SymbolInfo(false, 22, 18, 18, 18, 1), new SymbolInfo(true, 22, 18, 16, 10, 2), new SymbolInfo(false, 30, 20, 20, 20, 1), new SymbolInfo(true, 32, 24, 16, 14, 2), new SymbolInfo(false, 36, 24, 22, 22, 1), new SymbolInfo(false, 44, 28, 24, 24, 1), new SymbolInfo(true, 49, 28, 22, 14, 2), new SymbolInfo(false, 62, 36, 14, 14, 4), new SymbolInfo(false, 86, 42, 16, 16, 4), new SymbolInfo(false, 114, 48, 18, 18, 4), new SymbolInfo(false, 144, 56, 20, 20, 4), new SymbolInfo(false, 174, 68, 22, 22, 4), new SymbolInfo(false, 204, 84, 24, 24, 4, 102, 42), new SymbolInfo(false, DfuException.ERROR_ENTER_OTA_MODE_FAILED, 112, 14, 14, 16, 140, 56), new SymbolInfo(false, 368, 144, 16, 16, 16, 92, 36), new SymbolInfo(false, 456, 192, 18, 18, 16, 114, 48), new SymbolInfo(false, com.veryfit.multi.nativeprotocol.b.q2, 224, 20, 20, 16, 144, 56), new SymbolInfo(false, 696, 272, 22, 22, 16, 174, 68), new SymbolInfo(false, 816, com.veryfit.multi.nativeprotocol.b.q1, 24, 24, 16, 136, 56), new SymbolInfo(false, 1050, com.veryfit.multi.nativeprotocol.b.B1, 18, 18, 36, 175, 68), new SymbolInfo(false, 1304, 496, 20, 20, 36, 163, 62), new d()};

    /* renamed from: a  reason: collision with root package name */
    public final boolean f11810a;
    public final int b;
    public final int c;
    public final int d;
    public final int e;
    public final int f;
    public final int matrixHeight;
    public final int matrixWidth;

    public SymbolInfo(boolean z, int i, int i2, int i3, int i4, int i5) {
        this(z, i, i2, i3, i4, i5, i, i2);
    }

    public static SymbolInfo c(int i, SymbolShapeHint symbolShapeHint, boolean z) {
        return lookup(i, symbolShapeHint, null, null, z);
    }

    public static SymbolInfo lookup(int i) {
        return c(i, SymbolShapeHint.FORCE_NONE, true);
    }

    public static void overrideSymbolSet(SymbolInfo[] symbolInfoArr) {
        g = symbolInfoArr;
    }

    public final int a() {
        int i = this.d;
        int i2 = 1;
        if (i != 1) {
            i2 = 2;
            if (i != 2 && i != 4) {
                if (i != 16) {
                    if (i == 36) {
                        return 6;
                    }
                    throw new IllegalStateException("Cannot handle this number of data regions");
                }
                return 4;
            }
        }
        return i2;
    }

    public final int b() {
        int i = this.d;
        if (i == 1 || i == 2) {
            return 1;
        }
        if (i != 4) {
            if (i != 16) {
                if (i == 36) {
                    return 6;
                }
                throw new IllegalStateException("Cannot handle this number of data regions");
            }
            return 4;
        }
        return 2;
    }

    public int getCodewordCount() {
        return this.b + this.c;
    }

    public final int getDataCapacity() {
        return this.b;
    }

    public int getDataLengthForInterleavedBlock(int i) {
        return this.e;
    }

    public final int getErrorCodewords() {
        return this.c;
    }

    public final int getErrorLengthForInterleavedBlock(int i) {
        return this.f;
    }

    public int getInterleavedBlockCount() {
        return this.b / this.e;
    }

    public final int getSymbolDataHeight() {
        return b() * this.matrixHeight;
    }

    public final int getSymbolDataWidth() {
        return a() * this.matrixWidth;
    }

    public final int getSymbolHeight() {
        return getSymbolDataHeight() + (b() << 1);
    }

    public final int getSymbolWidth() {
        return getSymbolDataWidth() + (a() << 1);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f11810a ? "Rectangular Symbol:" : "Square Symbol:");
        sb.append(" data region ");
        sb.append(this.matrixWidth);
        sb.append('x');
        sb.append(this.matrixHeight);
        sb.append(", symbol size ");
        sb.append(getSymbolWidth());
        sb.append('x');
        sb.append(getSymbolHeight());
        sb.append(", symbol data size ");
        sb.append(getSymbolDataWidth());
        sb.append('x');
        sb.append(getSymbolDataHeight());
        sb.append(", codewords ");
        sb.append(this.b);
        sb.append('+');
        sb.append(this.c);
        return sb.toString();
    }

    public SymbolInfo(boolean z, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.f11810a = z;
        this.b = i;
        this.c = i2;
        this.matrixWidth = i3;
        this.matrixHeight = i4;
        this.d = i5;
        this.e = i6;
        this.f = i7;
    }

    public static SymbolInfo lookup(int i, SymbolShapeHint symbolShapeHint) {
        return c(i, symbolShapeHint, true);
    }

    public static SymbolInfo lookup(int i, boolean z, boolean z2) {
        return c(i, z ? SymbolShapeHint.FORCE_NONE : SymbolShapeHint.FORCE_SQUARE, z2);
    }

    public static SymbolInfo lookup(int i, SymbolShapeHint symbolShapeHint, Dimension dimension, Dimension dimension2, boolean z) {
        SymbolInfo[] symbolInfoArr;
        for (SymbolInfo symbolInfo : g) {
            if (!(symbolShapeHint == SymbolShapeHint.FORCE_SQUARE && symbolInfo.f11810a) && ((symbolShapeHint != SymbolShapeHint.FORCE_RECTANGLE || symbolInfo.f11810a) && ((dimension == null || (symbolInfo.getSymbolWidth() >= dimension.getWidth() && symbolInfo.getSymbolHeight() >= dimension.getHeight())) && ((dimension2 == null || (symbolInfo.getSymbolWidth() <= dimension2.getWidth() && symbolInfo.getSymbolHeight() <= dimension2.getHeight())) && i <= symbolInfo.b)))) {
                return symbolInfo;
            }
        }
        if (z) {
            throw new IllegalArgumentException("Can't find a symbol arrangement that matches the message. Data codewords: ".concat(String.valueOf(i)));
        }
        return null;
    }
}
