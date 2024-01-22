package com.blankj.utilcode.util;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public final class ReflectUtils {

    /* renamed from: a  reason: collision with root package name */
    public final Class<?> f2279a;
    public final Object b;

    /* loaded from: classes.dex */
    public static class ReflectException extends RuntimeException {
        private static final long serialVersionUID = 858774075258496016L;

        public ReflectException(String str) {
            super(str);
        }

        public ReflectException(String str, Throwable th) {
            super(str, th);
        }

        public ReflectException(Throwable th) {
            super(th);
        }
    }

    /* loaded from: classes.dex */
    public class a implements Comparator<Constructor<?>> {
        public a() {
        }

        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(Constructor<?> constructor, Constructor<?> constructor2) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            Class<?>[] parameterTypes2 = constructor2.getParameterTypes();
            int length = parameterTypes.length;
            for (int i = 0; i < length; i++) {
                if (!parameterTypes[i].equals(parameterTypes2[i])) {
                    return ReflectUtils.this.u(parameterTypes[i]).isAssignableFrom(ReflectUtils.this.u(parameterTypes2[i])) ? 1 : -1;
                }
            }
            return 0;
        }
    }

    /* loaded from: classes.dex */
    public class b implements Comparator<Method> {
        public b() {
        }

        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(Method method, Method method2) {
            Class<?>[] parameterTypes = method.getParameterTypes();
            Class<?>[] parameterTypes2 = method2.getParameterTypes();
            int length = parameterTypes.length;
            for (int i = 0; i < length; i++) {
                if (!parameterTypes[i].equals(parameterTypes2[i])) {
                    return ReflectUtils.this.u(parameterTypes[i]).isAssignableFrom(ReflectUtils.this.u(parameterTypes2[i])) ? 1 : -1;
                }
            }
            return 0;
        }
    }

    /* loaded from: classes.dex */
    public class c implements InvocationHandler {
        public final /* synthetic */ boolean h;

        public c(boolean z) {
            this.h = z;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) {
            String name = method.getName();
            try {
                return ReflectUtils.reflect(ReflectUtils.this.b).method(name, objArr).get();
            } catch (ReflectException e) {
                if (this.h) {
                    Map map = (Map) ReflectUtils.this.b;
                    int length = objArr == null ? 0 : objArr.length;
                    if (length == 0 && name.startsWith("get")) {
                        return map.get(ReflectUtils.o(name.substring(3)));
                    }
                    if (length == 0 && name.startsWith("is")) {
                        return map.get(ReflectUtils.o(name.substring(2)));
                    }
                    if (length == 1 && name.startsWith("set")) {
                        map.put(ReflectUtils.o(name.substring(3)), objArr[0]);
                        return null;
                    }
                }
                throw e;
            }
        }
    }

    /* loaded from: classes.dex */
    public static class d {
    }

    public ReflectUtils(Class<?> cls) {
        this(cls, cls);
    }

    public static Class<?> f(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new ReflectException(e);
        }
    }

    public static Class<?> g(String str, ClassLoader classLoader) {
        try {
            return Class.forName(str, true, classLoader);
        } catch (ClassNotFoundException e) {
            throw new ReflectException(e);
        }
    }

    public static String o(String str) {
        int length = str.length();
        if (length == 0) {
            return "";
        }
        if (length == 1) {
            return str.toLowerCase();
        }
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    public static ReflectUtils reflect(String str) throws ReflectException {
        return reflect(f(str));
    }

    public final <T extends AccessibleObject> T d(T t) {
        if (t == null) {
            return null;
        }
        if (t instanceof Member) {
            Member member = (Member) t;
            if (Modifier.isPublic(member.getModifiers()) && Modifier.isPublic(member.getDeclaringClass().getModifiers())) {
                return t;
            }
        }
        if (!t.isAccessible()) {
            t.setAccessible(true);
        }
        return t;
    }

    public final Method e(String str, Class<?>[] clsArr) throws NoSuchMethodException {
        Class<?> s = s();
        try {
            return s.getMethod(str, clsArr);
        } catch (NoSuchMethodException unused) {
            do {
                try {
                    return s.getDeclaredMethod(str, clsArr);
                } catch (NoSuchMethodException unused2) {
                    s = s.getSuperclass();
                    if (s == null) {
                        throw new NoSuchMethodException();
                    }
                }
            } while (s == null);
            throw new NoSuchMethodException();
        }
    }

    public boolean equals(Object obj) {
        return (obj instanceof ReflectUtils) && this.b.equals(((ReflectUtils) obj).get());
    }

    public ReflectUtils field(String str) {
        try {
            Field j = j(str);
            return new ReflectUtils(j.getType(), j.get(this.b));
        } catch (IllegalAccessException e) {
            throw new ReflectException(e);
        }
    }

    public <T> T get() {
        return (T) this.b;
    }

    public final Field h(String str) {
        Class<?> s = s();
        try {
            return (Field) d(s.getField(str));
        } catch (NoSuchFieldException e) {
            do {
                try {
                    return (Field) d(s.getDeclaredField(str));
                } catch (NoSuchFieldException unused) {
                    s = s.getSuperclass();
                    if (s != null) {
                        throw new ReflectException(e);
                    }
                }
            } while (s != null);
            throw new ReflectException(e);
        }
    }

    public int hashCode() {
        return this.b.hashCode();
    }

    public final Class<?>[] i(Object... objArr) {
        if (objArr == null) {
            return new Class[0];
        }
        Class<?>[] clsArr = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            Object obj = objArr[i];
            clsArr[i] = obj == null ? d.class : obj.getClass();
        }
        return clsArr;
    }

    public final Field j(String str) throws IllegalAccessException {
        Field h = h(str);
        if ((h.getModifiers() & 16) == 16) {
            try {
                Field declaredField = Field.class.getDeclaredField("modifiers");
                declaredField.setAccessible(true);
                declaredField.setInt(h, h.getModifiers() & (-17));
            } catch (NoSuchFieldException unused) {
                h.setAccessible(true);
            }
        }
        return h;
    }

    public final boolean k(Method method, String str, Class<?>[] clsArr) {
        return method.getName().equals(str) && l(method.getParameterTypes(), clsArr);
    }

    public final boolean l(Class<?>[] clsArr, Class<?>[] clsArr2) {
        if (clsArr.length == clsArr2.length) {
            for (int i = 0; i < clsArr2.length; i++) {
                if (clsArr2[i] != d.class && !u(clsArr[i]).isAssignableFrom(u(clsArr2[i]))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public final ReflectUtils m(Method method, Object obj, Object... objArr) {
        try {
            d(method);
            if (method.getReturnType() == Void.TYPE) {
                method.invoke(obj, objArr);
                return reflect(obj);
            }
            return reflect(method.invoke(obj, objArr));
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    public ReflectUtils method(String str) throws ReflectException {
        return method(str, new Object[0]);
    }

    public final ReflectUtils n(Constructor<?> constructor, Object... objArr) {
        try {
            return new ReflectUtils(constructor.getDeclaringClass(), ((Constructor) d(constructor)).newInstance(objArr));
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    public ReflectUtils newInstance() {
        return newInstance(new Object[0]);
    }

    public final Method p(String str, Class<?>[] clsArr) throws NoSuchMethodException {
        Method[] methods;
        Method[] declaredMethods;
        Class<?> s = s();
        ArrayList arrayList = new ArrayList();
        for (Method method : s.getMethods()) {
            if (k(method, str, clsArr)) {
                arrayList.add(method);
            }
        }
        if (!arrayList.isEmpty()) {
            r(arrayList);
            return arrayList.get(0);
        }
        do {
            for (Method method2 : s.getDeclaredMethods()) {
                if (k(method2, str, clsArr)) {
                    arrayList.add(method2);
                }
            }
            if (!arrayList.isEmpty()) {
                r(arrayList);
                return arrayList.get(0);
            }
            s = s.getSuperclass();
        } while (s != null);
        throw new NoSuchMethodException("No similar method " + str + " with params " + Arrays.toString(clsArr) + " could be found on type " + s() + ".");
    }

    public <P> P proxy(Class<P> cls) {
        return (P) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new c(this.b instanceof Map));
    }

    public final void q(List<Constructor<?>> list) {
        Collections.sort(list, new a());
    }

    public final void r(List<Method> list) {
        Collections.sort(list, new b());
    }

    public final Class<?> s() {
        return this.f2279a;
    }

    public final Object t(Object obj) {
        return obj instanceof ReflectUtils ? ((ReflectUtils) obj).get() : obj;
    }

    public String toString() {
        return this.b.toString();
    }

    public final Class<?> u(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        if (cls.isPrimitive()) {
            if (Boolean.TYPE == cls) {
                return Boolean.class;
            }
            if (Integer.TYPE == cls) {
                return Integer.class;
            }
            if (Long.TYPE == cls) {
                return Long.class;
            }
            if (Short.TYPE == cls) {
                return Short.class;
            }
            if (Byte.TYPE == cls) {
                return Byte.class;
            }
            if (Double.TYPE == cls) {
                return Double.class;
            }
            if (Float.TYPE == cls) {
                return Float.class;
            }
            if (Character.TYPE == cls) {
                return Character.class;
            }
            return Void.TYPE == cls ? Void.class : cls;
        }
        return cls;
    }

    public ReflectUtils(Class<?> cls, Object obj) {
        this.f2279a = cls;
        this.b = obj;
    }

    public static ReflectUtils reflect(String str, ClassLoader classLoader) throws ReflectException {
        return reflect(g(str, classLoader));
    }

    public ReflectUtils method(String str, Object... objArr) throws ReflectException {
        Class<?>[] i = i(objArr);
        try {
            try {
                return m(e(str, i), this.b, objArr);
            } catch (NoSuchMethodException e) {
                throw new ReflectException(e);
            }
        } catch (NoSuchMethodException unused) {
            return m(p(str, i), this.b, objArr);
        }
    }

    public ReflectUtils newInstance(Object... objArr) {
        Constructor<?>[] declaredConstructors;
        Class<?>[] i = i(objArr);
        try {
            return n(s().getDeclaredConstructor(i), objArr);
        } catch (NoSuchMethodException e) {
            ArrayList arrayList = new ArrayList();
            for (Constructor<?> constructor : s().getDeclaredConstructors()) {
                if (l(constructor.getParameterTypes(), i)) {
                    arrayList.add(constructor);
                }
            }
            if (!arrayList.isEmpty()) {
                q(arrayList);
                return n(arrayList.get(0), objArr);
            }
            throw new ReflectException(e);
        }
    }

    public static ReflectUtils reflect(Class<?> cls) throws ReflectException {
        return new ReflectUtils(cls);
    }

    public static ReflectUtils reflect(Object obj) throws ReflectException {
        return new ReflectUtils(obj == null ? Object.class : obj.getClass(), obj);
    }

    public ReflectUtils field(String str, Object obj) {
        try {
            j(str).set(this.b, t(obj));
            return this;
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }
}
