package com.crrepa.s0;

import com.crrepa.p0.b;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes9.dex */
public class a<T> {

    /* renamed from: a  reason: collision with root package name */
    public final Class<? super T> f7839a;
    public final Type b;
    public final int c;

    public a() {
        Type g = g(getClass());
        this.b = g;
        this.f7839a = (Class<? super T>) b.e(g);
        this.c = g.hashCode();
    }

    public a(Type type) {
        Type b = b.b((Type) com.crrepa.p0.a.a(type));
        this.b = b;
        this.f7839a = (Class<? super T>) b.e(b);
        this.c = b.hashCode();
    }

    public static <T> a<T> a(Class<T> cls) {
        return new a<>(cls);
    }

    public static a<?> a(Type type) {
        return new a<>(type);
    }

    public static a<?> a(Type type, Type... typeArr) {
        return new a<>(b.a((Type) null, type, typeArr));
    }

    public static a<?> b(Type type) {
        return new a<>(b.a(type));
    }

    public static AssertionError b(Type type, Class<?>... clsArr) {
        StringBuilder sb = new StringBuilder("Unexpected type. Expected one of: ");
        for (Class<?> cls : clsArr) {
            sb.append(cls.getName());
            sb.append(", ");
        }
        sb.append("but got: ");
        sb.append(type.getClass().getName());
        sb.append(", for type token: ");
        sb.append(type.toString());
        sb.append('.');
        return new AssertionError(sb.toString());
    }

    public static boolean c(ParameterizedType parameterizedType, ParameterizedType parameterizedType2, Map<String, Type> map) {
        if (parameterizedType.getRawType().equals(parameterizedType2.getRawType())) {
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            Type[] actualTypeArguments2 = parameterizedType2.getActualTypeArguments();
            for (int i = 0; i < actualTypeArguments.length; i++) {
                if (!f(actualTypeArguments[i], actualTypeArguments2[i], map)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.reflect.Type] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.Class] */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.lang.reflect.Type] */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.lang.reflect.Type] */
    /* JADX WARN: Type inference failed for: r1v9 */
    public static boolean d(Type type, GenericArrayType genericArrayType) {
        Type genericComponentType = genericArrayType.getGenericComponentType();
        if (genericComponentType instanceof ParameterizedType) {
            if (type instanceof GenericArrayType) {
                type = ((GenericArrayType) type).getGenericComponentType();
            } else if (type instanceof Class) {
                type = (Class) type;
                while (type.isArray()) {
                    type = type.getComponentType();
                }
            }
            return e(type, (ParameterizedType) genericComponentType, new HashMap());
        }
        return true;
    }

    public static boolean e(Type type, ParameterizedType parameterizedType, Map<String, Type> map) {
        if (type == null) {
            return false;
        }
        if (parameterizedType.equals(type)) {
            return true;
        }
        Class<?> e = b.e(type);
        ParameterizedType parameterizedType2 = type instanceof ParameterizedType ? (ParameterizedType) type : null;
        if (parameterizedType2 != null) {
            Type[] actualTypeArguments = parameterizedType2.getActualTypeArguments();
            TypeVariable<Class<?>>[] typeParameters = e.getTypeParameters();
            for (int i = 0; i < actualTypeArguments.length; i++) {
                Type type2 = actualTypeArguments[i];
                TypeVariable<Class<?>> typeVariable = typeParameters[i];
                while (type2 instanceof TypeVariable) {
                    type2 = map.get(((TypeVariable) type2).getName());
                }
                map.put(typeVariable.getName(), type2);
            }
            if (c(parameterizedType2, parameterizedType, map)) {
                return true;
            }
        }
        for (Type type3 : e.getGenericInterfaces()) {
            if (e(type3, parameterizedType, new HashMap(map))) {
                return true;
            }
        }
        return e(e.getGenericSuperclass(), parameterizedType, new HashMap(map));
    }

    public static boolean f(Type type, Type type2, Map<String, Type> map) {
        return type2.equals(type) || ((type instanceof TypeVariable) && type2.equals(map.get(((TypeVariable) type).getName())));
    }

    public static Type g(Class<?> cls) {
        Type genericSuperclass = cls.getGenericSuperclass();
        if (genericSuperclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        return b.b(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
    }

    public final Class<? super T> a() {
        return this.f7839a;
    }

    @Deprecated
    public boolean a(a<?> aVar) {
        return c(aVar.b());
    }

    public final Type b() {
        return this.b;
    }

    @Deprecated
    public boolean c(Class<?> cls) {
        return c((Type) cls);
    }

    @Deprecated
    public boolean c(Type type) {
        if (type == null) {
            return false;
        }
        if (this.b.equals(type)) {
            return true;
        }
        Type type2 = this.b;
        if (type2 instanceof Class) {
            return this.f7839a.isAssignableFrom(b.e(type));
        }
        if (type2 instanceof ParameterizedType) {
            return e(type, (ParameterizedType) type2, new HashMap());
        }
        if (type2 instanceof GenericArrayType) {
            return this.f7839a.isAssignableFrom(b.e(type)) && d(type, (GenericArrayType) this.b);
        }
        throw b(type2, Class.class, ParameterizedType.class, GenericArrayType.class);
    }

    public final boolean equals(Object obj) {
        return (obj instanceof a) && b.a(this.b, ((a) obj).b);
    }

    public final int hashCode() {
        return this.c;
    }

    public final String toString() {
        return b.h(this.b);
    }
}
