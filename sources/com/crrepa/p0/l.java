package com.crrepa.p0;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
/* loaded from: classes9.dex */
public abstract class l {

    /* loaded from: classes9.dex */
    public static class a extends l {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Method f7810a;
        public final /* synthetic */ Object b;

        public a(Method method, Object obj) {
            this.f7810a = method;
            this.b = obj;
        }

        @Override // com.crrepa.p0.l
        public <T> T c(Class<T> cls) throws Exception {
            l.d(cls);
            return (T) this.f7810a.invoke(this.b, cls);
        }
    }

    /* loaded from: classes9.dex */
    public static class b extends l {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Method f7811a;
        public final /* synthetic */ int b;

        public b(Method method, int i) {
            this.f7811a = method;
            this.b = i;
        }

        @Override // com.crrepa.p0.l
        public <T> T c(Class<T> cls) throws Exception {
            l.d(cls);
            return (T) this.f7811a.invoke(null, cls, Integer.valueOf(this.b));
        }
    }

    /* loaded from: classes9.dex */
    public static class c extends l {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Method f7812a;

        public c(Method method) {
            this.f7812a = method;
        }

        @Override // com.crrepa.p0.l
        public <T> T c(Class<T> cls) throws Exception {
            l.d(cls);
            return (T) this.f7812a.invoke(null, cls, Object.class);
        }
    }

    /* loaded from: classes9.dex */
    public static class d extends l {
        @Override // com.crrepa.p0.l
        public <T> T c(Class<T> cls) {
            throw new UnsupportedOperationException("Cannot allocate " + cls);
        }
    }

    public static l a() {
        try {
            Class<?> cls = Class.forName("sun.misc.Unsafe");
            Field declaredField = cls.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            return new a(cls.getMethod("allocateInstance", Class.class), declaredField.get(null));
        } catch (Exception unused) {
            try {
                try {
                    Method declaredMethod = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", Class.class);
                    declaredMethod.setAccessible(true);
                    int intValue = ((Integer) declaredMethod.invoke(null, Object.class)).intValue();
                    Method declaredMethod2 = ObjectStreamClass.class.getDeclaredMethod("newInstance", Class.class, Integer.TYPE);
                    declaredMethod2.setAccessible(true);
                    return new b(declaredMethod2, intValue);
                } catch (Exception unused2) {
                    return new d();
                }
            } catch (Exception unused3) {
                Method declaredMethod3 = ObjectInputStream.class.getDeclaredMethod("newInstance", Class.class, Class.class);
                declaredMethod3.setAccessible(true);
                return new c(declaredMethod3);
            }
        }
    }

    public static void d(Class<?> cls) {
        int modifiers = cls.getModifiers();
        if (Modifier.isInterface(modifiers)) {
            throw new UnsupportedOperationException("Interface can't be instantiated! Interface name: " + cls.getName());
        } else if (Modifier.isAbstract(modifiers)) {
            throw new UnsupportedOperationException("Abstract class can't be instantiated! Class name: " + cls.getName());
        }
    }

    public abstract <T> T c(Class<T> cls) throws Exception;
}
