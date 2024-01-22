package com.github.siyamed.shapeimageview.path.parser;

import android.graphics.Matrix;
import android.util.Log;
/* loaded from: classes9.dex */
public class g {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7976a = SvgToPath.class.getSimpleName();

    public static Matrix a(String str) {
        int i;
        Matrix matrix = new Matrix();
        while (true) {
            b(str, matrix);
            int indexOf = str.indexOf(")");
            if (indexOf <= 0 || str.length() <= (i = indexOf + 1)) {
                break;
            }
            str = str.substring(i).replaceFirst("[\\s,]*", "");
        }
        return matrix;
    }

    public static void b(String str, Matrix matrix) {
        float f;
        c b;
        c b2;
        if (str.startsWith("matrix(")) {
            c b3 = c.b(str.substring(7));
            if (b3.f7973a.size() == 6) {
                Matrix matrix2 = new Matrix();
                matrix2.setValues(new float[]{b3.f7973a.get(0).floatValue(), b3.f7973a.get(2).floatValue(), b3.f7973a.get(4).floatValue(), b3.f7973a.get(1).floatValue(), b3.f7973a.get(3).floatValue(), b3.f7973a.get(5).floatValue(), 0.0f, 0.0f, 1.0f});
                matrix.preConcat(matrix2);
            }
        } else if (str.startsWith("translate(")) {
            c b4 = c.b(str.substring(10));
            if (b4.f7973a.size() > 0) {
                matrix.preTranslate(b4.f7973a.get(0).floatValue(), b4.f7973a.size() > 1 ? b4.f7973a.get(1).floatValue() : 0.0f);
            }
        } else if (str.startsWith("scale(")) {
            c b5 = c.b(str.substring(6));
            if (b5.f7973a.size() > 0) {
                float floatValue = b5.f7973a.get(0).floatValue();
                matrix.preScale(floatValue, b5.f7973a.size() > 1 ? b5.f7973a.get(1).floatValue() : floatValue);
            }
        } else if (str.startsWith("skewX(")) {
            if (c.b(str.substring(6)).f7973a.size() > 0) {
                matrix.preSkew((float) Math.tan(b2.f7973a.get(0).floatValue()), 0.0f);
            }
        } else if (str.startsWith("skewY(")) {
            if (c.b(str.substring(6)).f7973a.size() > 0) {
                matrix.preSkew(0.0f, (float) Math.tan(b.f7973a.get(0).floatValue()));
            }
        } else if (str.startsWith("rotate(")) {
            c b6 = c.b(str.substring(7));
            if (b6.f7973a.size() > 0) {
                float floatValue2 = b6.f7973a.get(0).floatValue();
                if (b6.f7973a.size() > 2) {
                    r4 = b6.f7973a.get(1).floatValue();
                    f = b6.f7973a.get(2).floatValue();
                } else {
                    f = 0.0f;
                }
                matrix.preTranslate(r4, f);
                matrix.preRotate(floatValue2);
                matrix.preTranslate(-r4, -f);
            }
        } else {
            Log.w(f7976a, "Invalid transform (" + str + ")");
        }
    }
}
