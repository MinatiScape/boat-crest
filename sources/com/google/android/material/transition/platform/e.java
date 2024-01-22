package com.google.android.material.transition.platform;

import android.graphics.RectF;
import androidx.annotation.RequiresApi;
@RequiresApi(21)
/* loaded from: classes10.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    public static final d f10433a = new a();
    public static final d b = new b();

    /* loaded from: classes10.dex */
    public class a implements d {
        @Override // com.google.android.material.transition.platform.d
        public f a(float f, float f2, float f3, float f4, float f5, float f6, float f7) {
            float m = j.m(f4, f6, f2, f3, f, true);
            float f8 = m / f4;
            float f9 = m / f6;
            return new f(f8, f9, m, f5 * f8, m, f7 * f9);
        }

        @Override // com.google.android.material.transition.platform.d
        public boolean b(f fVar) {
            return fVar.d > fVar.f;
        }

        @Override // com.google.android.material.transition.platform.d
        public void c(RectF rectF, float f, f fVar) {
            rectF.bottom -= Math.abs(fVar.f - fVar.d) * f;
        }
    }

    /* loaded from: classes10.dex */
    public class b implements d {
        @Override // com.google.android.material.transition.platform.d
        public f a(float f, float f2, float f3, float f4, float f5, float f6, float f7) {
            float m = j.m(f5, f7, f2, f3, f, true);
            float f8 = m / f5;
            float f9 = m / f7;
            return new f(f8, f9, f4 * f8, m, f6 * f9, m);
        }

        @Override // com.google.android.material.transition.platform.d
        public boolean b(f fVar) {
            return fVar.c > fVar.e;
        }

        @Override // com.google.android.material.transition.platform.d
        public void c(RectF rectF, float f, f fVar) {
            float abs = (Math.abs(fVar.e - fVar.c) / 2.0f) * f;
            rectF.left += abs;
            rectF.right -= abs;
        }
    }

    public static d a(int i, boolean z, RectF rectF, RectF rectF2) {
        if (i == 0) {
            return b(z, rectF, rectF2) ? f10433a : b;
        } else if (i != 1) {
            if (i == 2) {
                return b;
            }
            throw new IllegalArgumentException("Invalid fit mode: " + i);
        } else {
            return f10433a;
        }
    }

    public static boolean b(boolean z, RectF rectF, RectF rectF2) {
        float width = rectF.width();
        float height = rectF.height();
        float width2 = rectF2.width();
        float height2 = rectF2.height();
        float f = (height2 * width) / width2;
        float f2 = (width2 * height) / width;
        if (z) {
            if (f >= height) {
                return true;
            }
        } else if (f2 >= height2) {
            return true;
        }
        return false;
    }
}
