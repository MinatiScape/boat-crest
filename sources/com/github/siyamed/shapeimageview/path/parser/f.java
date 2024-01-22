package com.github.siyamed.shapeimageview.path.parser;

import android.graphics.Path;
import android.graphics.RectF;
import android.util.Log;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
/* loaded from: classes9.dex */
public class f {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7975a = SvgToPath.n;

    public static Path a(String str) {
        char c;
        int i;
        char c2;
        float f;
        Path path;
        RectF rectF;
        float f2;
        float f3;
        String str2 = str;
        int length = str.length();
        e eVar = new e(str2);
        eVar.h();
        Path path2 = new Path();
        RectF rectF2 = new RectF();
        float f4 = 0.0f;
        char c3 = 'x';
        float f5 = 0.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        float f8 = 0.0f;
        float f9 = 0.0f;
        float f10 = 0.0f;
        while (true) {
            int i2 = eVar.c;
            if (i2 >= length) {
                return path2;
            }
            char charAt = str2.charAt(i2);
            if (Character.isDigit(charAt) || charAt == '.' || charAt == '-') {
                if (c3 == 'M') {
                    c3 = Matrix.MATRIX_TYPE_RANDOM_LT;
                } else if (c3 == 'm') {
                    c = 'l';
                }
                c = c3;
            } else {
                eVar.a();
                c = charAt;
            }
            boolean z = true;
            path2.computeBounds(rectF2, true);
            switch (c) {
                case 'A':
                case 'a':
                    float c4 = eVar.c();
                    float c5 = eVar.c();
                    float c6 = eVar.c();
                    int c7 = (int) eVar.c();
                    int c8 = (int) eVar.c();
                    float c9 = eVar.c();
                    float c10 = eVar.c();
                    if (c == 'a') {
                        c9 += f5;
                        c10 += f6;
                    }
                    i = length;
                    float f11 = c10;
                    float f12 = c9;
                    c2 = c;
                    f = 0.0f;
                    path = path2;
                    rectF = rectF2;
                    b(path2, f5, f6, f12, f11, c4, c5, c6, c7 == 1, c8 == 1);
                    f6 = f11;
                    z = false;
                    f5 = f12;
                    break;
                case 'C':
                case 'c':
                    float c11 = eVar.c();
                    float c12 = eVar.c();
                    float c13 = eVar.c();
                    float c14 = eVar.c();
                    float c15 = eVar.c();
                    float c16 = eVar.c();
                    if (c == 'c') {
                        c11 += f5;
                        c13 += f5;
                        c15 += f5;
                        c12 += f6;
                        c14 += f6;
                        c16 += f6;
                    }
                    f9 = c13;
                    f10 = c14;
                    float f13 = c16;
                    float f14 = c15;
                    path2.cubicTo(c11, c12, f9, f10, f14, f13);
                    i = length;
                    f5 = f14;
                    f6 = f13;
                    c2 = c;
                    path = path2;
                    rectF = rectF2;
                    f = 0.0f;
                    break;
                case 'H':
                case 'h':
                    float c17 = eVar.c();
                    if (c == 'h') {
                        path2.rLineTo(c17, f4);
                        f5 += c17;
                        i = length;
                        f = f4;
                        c2 = c;
                        path = path2;
                        rectF = rectF2;
                        z = false;
                        break;
                    } else {
                        path2.lineTo(c17, f6);
                        i = length;
                        f5 = c17;
                        f = f4;
                        c2 = c;
                        path = path2;
                        rectF = rectF2;
                        z = false;
                    }
                case 'L':
                case 'l':
                    float c18 = eVar.c();
                    float c19 = eVar.c();
                    if (c == 'l') {
                        path2.rLineTo(c18, c19);
                        f5 += c18;
                        f6 += c19;
                        i = length;
                        f = f4;
                        c2 = c;
                        path = path2;
                        rectF = rectF2;
                        z = false;
                        break;
                    } else {
                        path2.lineTo(c18, c19);
                        i = length;
                        f5 = c18;
                        f6 = c19;
                        f = f4;
                        c2 = c;
                        path = path2;
                        rectF = rectF2;
                        z = false;
                    }
                case 'M':
                case 'm':
                    float c20 = eVar.c();
                    float c21 = eVar.c();
                    if (c == 'm') {
                        path2.rMoveTo(c20, c21);
                        f5 += c20;
                        f6 += c21;
                    } else {
                        path2.moveTo(c20, c21);
                        f5 = c20;
                        f6 = c21;
                    }
                    i = length;
                    f7 = f5;
                    f8 = f6;
                    f = f4;
                    c2 = c;
                    path = path2;
                    rectF = rectF2;
                    z = false;
                    break;
                case 'Q':
                case 'q':
                    float c22 = eVar.c();
                    float c23 = eVar.c();
                    float c24 = eVar.c();
                    float c25 = eVar.c();
                    if (c == 'q') {
                        c24 += f5;
                        c25 += f6;
                        c22 += f5;
                        c23 += f6;
                    }
                    f9 = c22;
                    f10 = c23;
                    f2 = c24;
                    f3 = c25;
                    path2.cubicTo(f5, f6, f9, f10, f2, f3);
                    i = length;
                    f5 = f2;
                    f = f4;
                    c2 = c;
                    path = path2;
                    rectF = rectF2;
                    f6 = f3;
                    break;
                case 'S':
                case 's':
                    float c26 = eVar.c();
                    float c27 = eVar.c();
                    float c28 = eVar.c();
                    float c29 = eVar.c();
                    if (c == 's') {
                        c26 += f5;
                        c28 += f5;
                        c27 += f6;
                        c29 += f6;
                    }
                    float f15 = c26;
                    float f16 = c27;
                    float f17 = c28;
                    float f18 = c29;
                    path2.cubicTo((f5 * 2.0f) - f9, (f6 * 2.0f) - f10, f15, f16, f17, f18);
                    i = length;
                    f9 = f15;
                    f = f4;
                    c2 = c;
                    path = path2;
                    rectF = rectF2;
                    f10 = f16;
                    f5 = f17;
                    f6 = f18;
                    break;
                case 'T':
                case 't':
                    float c30 = eVar.c();
                    float c31 = eVar.c();
                    if (c == 't') {
                        c30 += f5;
                        c31 += f6;
                    }
                    f2 = c30;
                    f3 = c31;
                    f9 = (f5 * 2.0f) - f9;
                    f10 = (2.0f * f6) - f10;
                    path2.cubicTo(f5, f6, f9, f10, f2, f3);
                    i = length;
                    f5 = f2;
                    f = f4;
                    c2 = c;
                    path = path2;
                    rectF = rectF2;
                    f6 = f3;
                    break;
                case 'V':
                case 'v':
                    float c32 = eVar.c();
                    if (c == 'v') {
                        path2.rLineTo(f4, c32);
                        f6 += c32;
                        i = length;
                        f = f4;
                        c2 = c;
                        path = path2;
                        rectF = rectF2;
                        z = false;
                        break;
                    } else {
                        path2.lineTo(f5, c32);
                        i = length;
                        f6 = c32;
                        f = f4;
                        c2 = c;
                        path = path2;
                        rectF = rectF2;
                        z = false;
                    }
                case 'Z':
                case 'z':
                    path2.close();
                    i = length;
                    f = f4;
                    c2 = c;
                    path = path2;
                    rectF = rectF2;
                    f5 = f7;
                    f6 = f8;
                    z = false;
                    break;
                default:
                    i = length;
                    f = f4;
                    c2 = c;
                    path = path2;
                    rectF = rectF2;
                    Log.w(f7975a, "Invalid path command: " + c2);
                    eVar.a();
                    z = false;
                    break;
            }
            if (!z) {
                f9 = f5;
                f10 = f6;
            }
            eVar.h();
            str2 = str;
            c3 = c2;
            length = i;
            f4 = f;
            path2 = path;
            rectF2 = rectF;
        }
    }

    public static void b(Path path, double d, double d2, double d3, double d4, double d5, double d6, double d7, boolean z, boolean z2) {
        double d8;
        double d9 = (d - d3) / 2.0d;
        double d10 = (d2 - d4) / 2.0d;
        double radians = Math.toRadians(d7 % 360.0d);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        double d11 = (cos * d9) + (sin * d10);
        double d12 = ((-sin) * d9) + (d10 * cos);
        double abs = Math.abs(d5);
        double abs2 = Math.abs(d6);
        double d13 = abs * abs;
        double d14 = abs2 * abs2;
        double d15 = d11 * d11;
        double d16 = d12 * d12;
        double d17 = (d15 / d13) + (d16 / d14);
        if (d17 > 1.0d) {
            abs *= Math.sqrt(d17);
            abs2 *= Math.sqrt(d17);
            d13 = abs * abs;
            d14 = abs2 * abs2;
        }
        double d18 = z == z2 ? -1.0d : 1.0d;
        double d19 = d13 * d14;
        double d20 = d13 * d16;
        double d21 = d14 * d15;
        double d22 = ((d19 - d20) - d21) / (d20 + d21);
        if (d22 < 0.0d) {
            d22 = 0.0d;
        }
        double sqrt = d18 * Math.sqrt(d22);
        double d23 = ((abs * d12) / abs2) * sqrt;
        double d24 = sqrt * (-((abs2 * d11) / abs));
        double d25 = ((d + d3) / 2.0d) + ((cos * d23) - (sin * d24));
        double d26 = ((d2 + d4) / 2.0d) + (sin * d23) + (cos * d24);
        double d27 = (d11 - d23) / abs;
        double d28 = (d12 - d24) / abs2;
        double d29 = ((-d11) - d23) / abs;
        double d30 = ((-d12) - d24) / abs2;
        double d31 = (d27 * d27) + (d28 * d28);
        double degrees = Math.toDegrees((d28 < 0.0d ? -1.0d : 1.0d) * Math.acos(d27 / Math.sqrt(d31)));
        double degrees2 = Math.toDegrees(((d27 * d30) - (d28 * d29) < 0.0d ? -1.0d : 1.0d) * Math.acos(((d27 * d29) + (d28 * d30)) / Math.sqrt(d31 * ((d29 * d29) + (d30 * d30)))));
        if (z2 || degrees2 <= 0.0d) {
            d8 = 360.0d;
            if (z2 && degrees2 < 0.0d) {
                degrees2 += 360.0d;
            }
        } else {
            d8 = 360.0d;
            degrees2 -= 360.0d;
        }
        path.addArc(new RectF((float) (d25 - abs), (float) (d26 - abs2), (float) (d25 + abs), (float) (d26 + abs2)), (float) (degrees % d8), (float) (degrees2 % d8));
    }
}
