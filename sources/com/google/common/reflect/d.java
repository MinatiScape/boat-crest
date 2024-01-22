package com.google.common.reflect;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.UnmodifiableIterator;
import java.io.Serializable;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.security.AccessControlException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.text.Typography;
import okhttp3.HttpUrl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes10.dex */
public final class d {

    /* renamed from: a  reason: collision with root package name */
    public static final Function<Type, String> f10737a = new a();
    public static final Joiner b = Joiner.on(", ").useForNull("null");

    /* loaded from: classes10.dex */
    public class a implements Function<Type, String> {
        @Override // com.google.common.base.Function
        /* renamed from: a */
        public String apply(Type type) {
            return e.CURRENT.typeName(type);
        }
    }

    /* loaded from: classes10.dex */
    public class b extends com.google.common.reflect.c {
        public final /* synthetic */ AtomicReference b;

        public b(AtomicReference atomicReference) {
            this.b = atomicReference;
        }

        @Override // com.google.common.reflect.c
        public void b(Class<?> cls) {
            this.b.set(cls.getComponentType());
        }

        @Override // com.google.common.reflect.c
        public void c(GenericArrayType genericArrayType) {
            this.b.set(genericArrayType.getGenericComponentType());
        }

        @Override // com.google.common.reflect.c
        public void e(TypeVariable<?> typeVariable) {
            this.b.set(d.q(typeVariable.getBounds()));
        }

        @Override // com.google.common.reflect.c
        public void f(WildcardType wildcardType) {
            this.b.set(d.q(wildcardType.getUpperBounds()));
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* loaded from: classes10.dex */
    public static abstract class c {
        public static final c OWNED_BY_ENCLOSING_CLASS = new a("OWNED_BY_ENCLOSING_CLASS", 0);
        public static final c LOCAL_CLASS_HAS_NO_OWNER = new C0516c("LOCAL_CLASS_HAS_NO_OWNER", 1);
        private static final /* synthetic */ c[] $VALUES = $values();
        public static final c JVM_BEHAVIOR = detectJvmBehavior();

        /* loaded from: classes10.dex */
        public enum a extends c {
            public a(String str, int i) {
                super(str, i, null);
            }

            @Override // com.google.common.reflect.d.c
            @NullableDecl
            public Class<?> getOwnerType(Class<?> cls) {
                return cls.getEnclosingClass();
            }
        }

        /* loaded from: classes10.dex */
        public class b<T> {
        }

        /* renamed from: com.google.common.reflect.d$c$c  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public enum C0516c extends c {
            public C0516c(String str, int i) {
                super(str, i, null);
            }

            @Override // com.google.common.reflect.d.c
            @NullableDecl
            public Class<?> getOwnerType(Class<?> cls) {
                if (cls.isLocalClass()) {
                    return null;
                }
                return cls.getEnclosingClass();
            }
        }

        /* renamed from: com.google.common.reflect.d$c$d  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public class C0517d extends b<String> {
        }

        private static /* synthetic */ c[] $values() {
            return new c[]{OWNED_BY_ENCLOSING_CLASS, LOCAL_CLASS_HAS_NO_OWNER};
        }

        private c(String str, int i) {
        }

        private static c detectJvmBehavior() {
            c[] values;
            new C0517d();
            ParameterizedType parameterizedType = (ParameterizedType) C0517d.class.getGenericSuperclass();
            for (c cVar : values()) {
                if (cVar.getOwnerType(b.class) == parameterizedType.getOwnerType()) {
                    return cVar;
                }
            }
            throw new AssertionError();
        }

        public static c valueOf(String str) {
            return (c) Enum.valueOf(c.class, str);
        }

        public static c[] values() {
            return (c[]) $VALUES.clone();
        }

        @NullableDecl
        public abstract Class<?> getOwnerType(Class<?> cls);

        public /* synthetic */ c(String str, int i, a aVar) {
            this(str, i);
        }
    }

    /* renamed from: com.google.common.reflect.d$d  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public static final class C0518d implements GenericArrayType, Serializable {
        private static final long serialVersionUID = 0;
        private final Type componentType;

        public C0518d(Type type) {
            this.componentType = e.CURRENT.usedInGenericType(type);
        }

        public boolean equals(Object obj) {
            if (obj instanceof GenericArrayType) {
                return Objects.equal(getGenericComponentType(), ((GenericArrayType) obj).getGenericComponentType());
            }
            return false;
        }

        @Override // java.lang.reflect.GenericArrayType
        public Type getGenericComponentType() {
            return this.componentType;
        }

        public int hashCode() {
            return this.componentType.hashCode();
        }

        public String toString() {
            return String.valueOf(d.t(this.componentType)).concat(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* loaded from: classes10.dex */
    public static abstract class e {
        private static final /* synthetic */ e[] $VALUES;
        public static final e CURRENT;
        public static final e JAVA6;
        public static final e JAVA7;
        public static final e JAVA8;
        public static final e JAVA9;

        /* loaded from: classes10.dex */
        public enum a extends e {
            public a(String str, int i) {
                super(str, i, null);
            }

            @Override // com.google.common.reflect.d.e
            public Type usedInGenericType(Type type) {
                Preconditions.checkNotNull(type);
                if (type instanceof Class) {
                    Class cls = (Class) type;
                    return cls.isArray() ? new C0518d(cls.getComponentType()) : type;
                }
                return type;
            }

            @Override // com.google.common.reflect.d.e
            public GenericArrayType newArrayType(Type type) {
                return new C0518d(type);
            }
        }

        /* loaded from: classes10.dex */
        public enum b extends e {
            public b(String str, int i) {
                super(str, i, null);
            }

            @Override // com.google.common.reflect.d.e
            public Type newArrayType(Type type) {
                if (type instanceof Class) {
                    return d.i((Class) type);
                }
                return new C0518d(type);
            }

            @Override // com.google.common.reflect.d.e
            public Type usedInGenericType(Type type) {
                return (Type) Preconditions.checkNotNull(type);
            }
        }

        /* loaded from: classes10.dex */
        public enum c extends e {
            public c(String str, int i) {
                super(str, i, null);
            }

            @Override // com.google.common.reflect.d.e
            public Type newArrayType(Type type) {
                return e.JAVA7.newArrayType(type);
            }

            @Override // com.google.common.reflect.d.e
            public String typeName(Type type) {
                try {
                    return (String) Type.class.getMethod("getTypeName", new Class[0]).invoke(type, new Object[0]);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (NoSuchMethodException unused) {
                    throw new AssertionError("Type.getTypeName should be available in Java 8");
                } catch (InvocationTargetException e2) {
                    throw new RuntimeException(e2);
                }
            }

            @Override // com.google.common.reflect.d.e
            public Type usedInGenericType(Type type) {
                return e.JAVA7.usedInGenericType(type);
            }
        }

        /* renamed from: com.google.common.reflect.d$e$d  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public enum C0519d extends e {
            public C0519d(String str, int i) {
                super(str, i, null);
            }

            @Override // com.google.common.reflect.d.e
            public boolean jdkTypeDuplicatesOwnerName() {
                return false;
            }

            @Override // com.google.common.reflect.d.e
            public Type newArrayType(Type type) {
                return e.JAVA8.newArrayType(type);
            }

            @Override // com.google.common.reflect.d.e
            public String typeName(Type type) {
                return e.JAVA8.typeName(type);
            }

            @Override // com.google.common.reflect.d.e
            public Type usedInGenericType(Type type) {
                return e.JAVA8.usedInGenericType(type);
            }
        }

        /* renamed from: com.google.common.reflect.d$e$e  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public class C0520e extends com.google.common.reflect.b<Map.Entry<String, int[][]>> {
        }

        /* loaded from: classes10.dex */
        public class f extends com.google.common.reflect.b<int[]> {
        }

        private static /* synthetic */ e[] $values() {
            return new e[]{JAVA6, JAVA7, JAVA8, JAVA9};
        }

        static {
            a aVar = new a("JAVA6", 0);
            JAVA6 = aVar;
            b bVar = new b("JAVA7", 1);
            JAVA7 = bVar;
            c cVar = new c("JAVA8", 2);
            JAVA8 = cVar;
            C0519d c0519d = new C0519d("JAVA9", 3);
            JAVA9 = c0519d;
            $VALUES = $values();
            if (AnnotatedElement.class.isAssignableFrom(TypeVariable.class)) {
                if (new C0520e().capture().toString().contains("java.util.Map.java.util.Map")) {
                    CURRENT = cVar;
                } else {
                    CURRENT = c0519d;
                }
            } else if (new f().capture() instanceof Class) {
                CURRENT = bVar;
            } else {
                CURRENT = aVar;
            }
        }

        private e(String str, int i) {
        }

        public static e valueOf(String str) {
            return (e) Enum.valueOf(e.class, str);
        }

        public static e[] values() {
            return (e[]) $VALUES.clone();
        }

        public boolean jdkTypeDuplicatesOwnerName() {
            return true;
        }

        public abstract Type newArrayType(Type type);

        public String typeName(Type type) {
            return d.t(type);
        }

        public final ImmutableList<Type> usedInGenericType(Type[] typeArr) {
            ImmutableList.Builder builder = ImmutableList.builder();
            for (Type type : typeArr) {
                builder.add((ImmutableList.Builder) usedInGenericType(type));
            }
            return builder.build();
        }

        public abstract Type usedInGenericType(Type type);

        public /* synthetic */ e(String str, int i, a aVar) {
            this(str, i);
        }
    }

    /* loaded from: classes10.dex */
    public static final class f<X> {

        /* renamed from: a  reason: collision with root package name */
        public static final boolean f10738a = !f.class.getTypeParameters()[0].equals(d.l(f.class, "X", new Type[0]));
    }

    /* loaded from: classes10.dex */
    public static final class g implements ParameterizedType, Serializable {
        private static final long serialVersionUID = 0;
        private final ImmutableList<Type> argumentsList;
        @NullableDecl
        private final Type ownerType;
        private final Class<?> rawType;

        public g(@NullableDecl Type type, Class<?> cls, Type[] typeArr) {
            Preconditions.checkNotNull(cls);
            Preconditions.checkArgument(typeArr.length == cls.getTypeParameters().length);
            d.g(typeArr, "type parameter");
            this.ownerType = type;
            this.rawType = cls;
            this.argumentsList = e.CURRENT.usedInGenericType(typeArr);
        }

        public boolean equals(Object obj) {
            if (obj instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) obj;
                return getRawType().equals(parameterizedType.getRawType()) && Objects.equal(getOwnerType(), parameterizedType.getOwnerType()) && Arrays.equals(getActualTypeArguments(), parameterizedType.getActualTypeArguments());
            }
            return false;
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type[] getActualTypeArguments() {
            return d.s(this.argumentsList);
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type getOwnerType() {
            return this.ownerType;
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type getRawType() {
            return this.rawType;
        }

        public int hashCode() {
            Type type = this.ownerType;
            return ((type == null ? 0 : type.hashCode()) ^ this.argumentsList.hashCode()) ^ this.rawType.hashCode();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (this.ownerType != null) {
                e eVar = e.CURRENT;
                if (eVar.jdkTypeDuplicatesOwnerName()) {
                    sb.append(eVar.typeName(this.ownerType));
                    sb.append('.');
                }
            }
            sb.append(this.rawType.getName());
            sb.append(Typography.less);
            sb.append(d.b.join(Iterables.transform(this.argumentsList, d.f10737a)));
            sb.append(Typography.greater);
            return sb.toString();
        }
    }

    /* loaded from: classes10.dex */
    public static final class h<D extends GenericDeclaration> {

        /* renamed from: a  reason: collision with root package name */
        public final D f10739a;
        public final String b;
        public final ImmutableList<Type> c;

        public h(D d, String str, Type[] typeArr) {
            d.g(typeArr, "bound for type variable");
            this.f10739a = (D) Preconditions.checkNotNull(d);
            this.b = (String) Preconditions.checkNotNull(str);
            this.c = ImmutableList.copyOf(typeArr);
        }

        public D a() {
            return this.f10739a;
        }

        public String b() {
            return this.b;
        }

        public boolean equals(Object obj) {
            if (f.f10738a) {
                if (obj != null && Proxy.isProxyClass(obj.getClass()) && (Proxy.getInvocationHandler(obj) instanceof i)) {
                    h hVar = ((i) Proxy.getInvocationHandler(obj)).h;
                    return this.b.equals(hVar.b()) && this.f10739a.equals(hVar.a()) && this.c.equals(hVar.c);
                }
                return false;
            } else if (obj instanceof TypeVariable) {
                TypeVariable typeVariable = (TypeVariable) obj;
                return this.b.equals(typeVariable.getName()) && this.f10739a.equals(typeVariable.getGenericDeclaration());
            } else {
                return false;
            }
        }

        public int hashCode() {
            return this.f10739a.hashCode() ^ this.b.hashCode();
        }

        public String toString() {
            return this.b;
        }
    }

    /* loaded from: classes10.dex */
    public static final class i implements InvocationHandler {
        public static final ImmutableMap<String, Method> i;
        public final h<?> h;

        static {
            Method[] methods;
            ImmutableMap.Builder builder = ImmutableMap.builder();
            for (Method method : h.class.getMethods()) {
                if (method.getDeclaringClass().equals(h.class)) {
                    try {
                        method.setAccessible(true);
                    } catch (AccessControlException unused) {
                    }
                    builder.put(method.getName(), method);
                }
            }
            i = builder.build();
        }

        public i(h<?> hVar) {
            this.h = hVar;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            String name = method.getName();
            Method method2 = i.get(name);
            if (method2 != null) {
                try {
                    return method2.invoke(this.h, objArr);
                } catch (InvocationTargetException e) {
                    throw e.getCause();
                }
            }
            throw new UnsupportedOperationException(name);
        }
    }

    /* loaded from: classes10.dex */
    public static final class j implements WildcardType, Serializable {
        private static final long serialVersionUID = 0;
        private final ImmutableList<Type> lowerBounds;
        private final ImmutableList<Type> upperBounds;

        public j(Type[] typeArr, Type[] typeArr2) {
            d.g(typeArr, "lower bound for wildcard");
            d.g(typeArr2, "upper bound for wildcard");
            e eVar = e.CURRENT;
            this.lowerBounds = eVar.usedInGenericType(typeArr);
            this.upperBounds = eVar.usedInGenericType(typeArr2);
        }

        public boolean equals(Object obj) {
            if (obj instanceof WildcardType) {
                WildcardType wildcardType = (WildcardType) obj;
                return this.lowerBounds.equals(Arrays.asList(wildcardType.getLowerBounds())) && this.upperBounds.equals(Arrays.asList(wildcardType.getUpperBounds()));
            }
            return false;
        }

        @Override // java.lang.reflect.WildcardType
        public Type[] getLowerBounds() {
            return d.s(this.lowerBounds);
        }

        @Override // java.lang.reflect.WildcardType
        public Type[] getUpperBounds() {
            return d.s(this.upperBounds);
        }

        public int hashCode() {
            return this.lowerBounds.hashCode() ^ this.upperBounds.hashCode();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("?");
            UnmodifiableIterator<Type> it = this.lowerBounds.iterator();
            while (it.hasNext()) {
                sb.append(" super ");
                sb.append(e.CURRENT.typeName(it.next()));
            }
            for (Type type : d.h(this.upperBounds)) {
                sb.append(" extends ");
                sb.append(e.CURRENT.typeName(type));
            }
            return sb.toString();
        }
    }

    public static void g(Type[] typeArr, String str) {
        Class cls;
        for (Type type : typeArr) {
            if (type instanceof Class) {
                Preconditions.checkArgument(!cls.isPrimitive(), "Primitive type '%s' used as %s", (Class) type, str);
            }
        }
    }

    public static Iterable<Type> h(Iterable<Type> iterable) {
        return Iterables.filter(iterable, Predicates.not(Predicates.equalTo(Object.class)));
    }

    public static Class<?> i(Class<?> cls) {
        return Array.newInstance(cls, 0).getClass();
    }

    @NullableDecl
    public static Type j(Type type) {
        Preconditions.checkNotNull(type);
        AtomicReference atomicReference = new AtomicReference();
        new b(atomicReference).a(type);
        return (Type) atomicReference.get();
    }

    public static Type k(Type type) {
        if (type instanceof WildcardType) {
            WildcardType wildcardType = (WildcardType) type;
            Type[] lowerBounds = wildcardType.getLowerBounds();
            Preconditions.checkArgument(lowerBounds.length <= 1, "Wildcard cannot have more than one lower bounds.");
            if (lowerBounds.length == 1) {
                return r(k(lowerBounds[0]));
            }
            Type[] upperBounds = wildcardType.getUpperBounds();
            Preconditions.checkArgument(upperBounds.length == 1, "Wildcard should have only one upper bound.");
            return p(k(upperBounds[0]));
        }
        return e.CURRENT.newArrayType(type);
    }

    public static <D extends GenericDeclaration> TypeVariable<D> l(D d, String str, Type... typeArr) {
        if (typeArr.length == 0) {
            typeArr = new Type[]{Object.class};
        }
        return o(d, str, typeArr);
    }

    public static ParameterizedType m(Class<?> cls, Type... typeArr) {
        return new g(c.JVM_BEHAVIOR.getOwnerType(cls), cls, typeArr);
    }

    public static ParameterizedType n(@NullableDecl Type type, Class<?> cls, Type... typeArr) {
        if (type == null) {
            return m(cls, typeArr);
        }
        Preconditions.checkNotNull(typeArr);
        Preconditions.checkArgument(cls.getEnclosingClass() != null, "Owner type for unenclosed %s", cls);
        return new g(type, cls, typeArr);
    }

    public static <D extends GenericDeclaration> TypeVariable<D> o(D d, String str, Type[] typeArr) {
        return (TypeVariable) Reflection.newProxy(TypeVariable.class, new i(new h(d, str, typeArr)));
    }

    @VisibleForTesting
    public static WildcardType p(Type type) {
        return new j(new Type[0], new Type[]{type});
    }

    @NullableDecl
    public static Type q(Type[] typeArr) {
        for (Type type : typeArr) {
            Type j2 = j(type);
            if (j2 != null) {
                if (j2 instanceof Class) {
                    Class cls = (Class) j2;
                    if (cls.isPrimitive()) {
                        return cls;
                    }
                }
                return p(j2);
            }
        }
        return null;
    }

    @VisibleForTesting
    public static WildcardType r(Type type) {
        return new j(new Type[]{type}, new Type[]{Object.class});
    }

    public static Type[] s(Collection<Type> collection) {
        return (Type[]) collection.toArray(new Type[0]);
    }

    public static String t(Type type) {
        return type instanceof Class ? ((Class) type).getName() : type.toString();
    }
}
