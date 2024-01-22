package com.google.zxing.qrcode.encoder;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import com.google.zxing.qrcode.decoder.Version;
/* loaded from: classes11.dex */
public final class QRCode {
    public static final int NUM_MASK_PATTERNS = 8;

    /* renamed from: a  reason: collision with root package name */
    public Mode f11886a;
    public ErrorCorrectionLevel b;
    public Version c;
    public int d = -1;
    public ByteMatrix e;

    public static boolean isValidMaskPattern(int i) {
        return i >= 0 && i < 8;
    }

    public ErrorCorrectionLevel getECLevel() {
        return this.b;
    }

    public int getMaskPattern() {
        return this.d;
    }

    public ByteMatrix getMatrix() {
        return this.e;
    }

    public Mode getMode() {
        return this.f11886a;
    }

    public Version getVersion() {
        return this.c;
    }

    public void setECLevel(ErrorCorrectionLevel errorCorrectionLevel) {
        this.b = errorCorrectionLevel;
    }

    public void setMaskPattern(int i) {
        this.d = i;
    }

    public void setMatrix(ByteMatrix byteMatrix) {
        this.e = byteMatrix;
    }

    public void setMode(Mode mode) {
        this.f11886a = mode;
    }

    public void setVersion(Version version) {
        this.c = version;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(200);
        sb.append("<<\n");
        sb.append(" mode: ");
        sb.append(this.f11886a);
        sb.append("\n ecLevel: ");
        sb.append(this.b);
        sb.append("\n version: ");
        sb.append(this.c);
        sb.append("\n maskPattern: ");
        sb.append(this.d);
        if (this.e == null) {
            sb.append("\n matrix: null\n");
        } else {
            sb.append("\n matrix:\n");
            sb.append(this.e);
        }
        sb.append(">>\n");
        return sb.toString();
    }
}
