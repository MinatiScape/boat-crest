package com.google.android.material.transition;
/* loaded from: classes10.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public static final com.google.android.material.transition.a f10409a = new a();
    public static final com.google.android.material.transition.a b = new C0438b();
    public static final com.google.android.material.transition.a c = new c();
    public static final com.google.android.material.transition.a d = new d();

    /* loaded from: classes10.dex */
    public class a implements com.google.android.material.transition.a {
        @Override // com.google.android.material.transition.a
        public com.google.android.material.transition.c a(float f, float f2, float f3, float f4) {
            return com.google.android.material.transition.c.a(255, j.m(0, 255, f2, f3, f));
        }
    }

    /* renamed from: com.google.android.material.transition.b$b  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public class C0438b implements com.google.android.material.transition.a {
        @Override // com.google.android.material.transition.a
        public com.google.android.material.transition.c a(float f, float f2, float f3, float f4) {
            return com.google.android.material.transition.c.b(j.m(255, 0, f2, f3, f), 255);
        }
    }

    /* loaded from: classes10.dex */
    public class c implements com.google.android.material.transition.a {
        @Override // com.google.android.material.transition.a
        public com.google.android.material.transition.c a(float f, float f2, float f3, float f4) {
            return com.google.android.material.transition.c.b(j.m(255, 0, f2, f3, f), j.m(0, 255, f2, f3, f));
        }
    }

    /* loaded from: classes10.dex */
    public class d implements com.google.android.material.transition.a {
        @Override // com.google.android.material.transition.a
        public com.google.android.material.transition.c a(float f, float f2, float f3, float f4) {
            float f5 = ((f3 - f2) * f4) + f2;
            return com.google.android.material.transition.c.b(j.m(255, 0, f2, f5, f), j.m(0, 255, f5, f3, f));
        }
    }

    public static com.google.android.material.transition.a a(int i, boolean z) {
        if (i == 0) {
            return z ? f10409a : b;
        } else if (i == 1) {
            return z ? b : f10409a;
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
