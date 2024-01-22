package com.google.android.material.transition.platform;

import androidx.annotation.RequiresApi;
@RequiresApi(21)
/* loaded from: classes10.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public static final com.google.android.material.transition.platform.a f10431a = new a();
    public static final com.google.android.material.transition.platform.a b = new C0439b();
    public static final com.google.android.material.transition.platform.a c = new c();
    public static final com.google.android.material.transition.platform.a d = new d();

    /* loaded from: classes10.dex */
    public class a implements com.google.android.material.transition.platform.a {
        @Override // com.google.android.material.transition.platform.a
        public com.google.android.material.transition.platform.c a(float f, float f2, float f3, float f4) {
            return com.google.android.material.transition.platform.c.a(255, j.n(0, 255, f2, f3, f));
        }
    }

    /* renamed from: com.google.android.material.transition.platform.b$b  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public class C0439b implements com.google.android.material.transition.platform.a {
        @Override // com.google.android.material.transition.platform.a
        public com.google.android.material.transition.platform.c a(float f, float f2, float f3, float f4) {
            return com.google.android.material.transition.platform.c.b(j.n(255, 0, f2, f3, f), 255);
        }
    }

    /* loaded from: classes10.dex */
    public class c implements com.google.android.material.transition.platform.a {
        @Override // com.google.android.material.transition.platform.a
        public com.google.android.material.transition.platform.c a(float f, float f2, float f3, float f4) {
            return com.google.android.material.transition.platform.c.b(j.n(255, 0, f2, f3, f), j.n(0, 255, f2, f3, f));
        }
    }

    /* loaded from: classes10.dex */
    public class d implements com.google.android.material.transition.platform.a {
        @Override // com.google.android.material.transition.platform.a
        public com.google.android.material.transition.platform.c a(float f, float f2, float f3, float f4) {
            float f5 = ((f3 - f2) * f4) + f2;
            return com.google.android.material.transition.platform.c.b(j.n(255, 0, f2, f5, f), j.n(0, 255, f5, f3, f));
        }
    }

    public static com.google.android.material.transition.platform.a a(int i, boolean z) {
        if (i == 0) {
            return z ? f10431a : b;
        } else if (i == 1) {
            return z ? b : f10431a;
        } else if (i != 2) {
            if (i == 3) {
                return d;
            }
            throw new IllegalArgumentException("Invalid fade mode: " + i);
        } else {
            return c;
        }
    }
}
