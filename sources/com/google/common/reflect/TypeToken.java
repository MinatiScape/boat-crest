package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ForwardingSet;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.primitives.Primitives;
import com.google.common.reflect.Invokable;
import com.google.common.reflect.TypeResolver;
import com.google.common.reflect.d;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
/* loaded from: classes10.dex */
public abstract class TypeToken<T> extends com.google.common.reflect.b<T> implements Serializable {
    private static final long serialVersionUID = 3637540370352322684L;
    @NullableDecl
    private transient TypeResolver covariantTypeResolver;
    @NullableDecl
    private transient TypeResolver invariantTypeResolver;
    private final Type runtimeType;

    /* loaded from: classes10.dex */
    public class a extends Invokable.b<T> {
        public a(Method method) {
            super(method);
        }

        @Override // com.google.common.reflect.Invokable.b, com.google.common.reflect.Invokable
        public Type[] a() {
            return TypeToken.this.getCovariantTypeResolver().k(super.a());
        }

        @Override // com.google.common.reflect.Invokable.b, com.google.common.reflect.Invokable
        public Type[] b() {
            return TypeToken.this.getInvariantTypeResolver().k(super.b());
        }

        @Override // com.google.common.reflect.Invokable.b, com.google.common.reflect.Invokable
        public Type c() {
            return TypeToken.this.getCovariantTypeResolver().resolveType(super.c());
        }

        @Override // com.google.common.reflect.Invokable, com.google.common.reflect.a
        public TypeToken<T> getOwnerType() {
            return TypeToken.this;
        }

        @Override // com.google.common.reflect.Invokable, com.google.common.reflect.a
        public String toString() {
            String valueOf = String.valueOf(getOwnerType());
            String invokable = super.toString();
            StringBuilder sb = new StringBuilder(valueOf.length() + 1 + String.valueOf(invokable).length());
            sb.append(valueOf);
            sb.append(".");
            sb.append(invokable);
            return sb.toString();
        }
    }

    /* loaded from: classes10.dex */
    public class b extends Invokable.a<T> {
        public b(Constructor constructor) {
            super(constructor);
        }

        @Override // com.google.common.reflect.Invokable.a, com.google.common.reflect.Invokable
        public Type[] a() {
            return TypeToken.this.getCovariantTypeResolver().k(super.a());
        }

        @Override // com.google.common.reflect.Invokable.a, com.google.common.reflect.Invokable
        public Type[] b() {
            return TypeToken.this.getInvariantTypeResolver().k(super.b());
        }

        @Override // com.google.common.reflect.Invokable.a, com.google.common.reflect.Invokable
        public Type c() {
            return TypeToken.this.getCovariantTypeResolver().resolveType(super.c());
        }

        @Override // com.google.common.reflect.Invokable, com.google.common.reflect.a
        public TypeToken<T> getOwnerType() {
            return TypeToken.this;
        }

        @Override // com.google.common.reflect.Invokable, com.google.common.reflect.a
        public String toString() {
            String valueOf = String.valueOf(getOwnerType());
            String join = Joiner.on(", ").join(b());
            StringBuilder sb = new StringBuilder(valueOf.length() + 2 + String.valueOf(join).length());
            sb.append(valueOf);
            sb.append("(");
            sb.append(join);
            sb.append(")");
            return sb.toString();
        }
    }

    /* loaded from: classes10.dex */
    public class c extends com.google.common.reflect.c {
        public c() {
        }

        @Override // com.google.common.reflect.c
        public void c(GenericArrayType genericArrayType) {
            a(genericArrayType.getGenericComponentType());
        }

        @Override // com.google.common.reflect.c
        public void d(ParameterizedType parameterizedType) {
            a(parameterizedType.getActualTypeArguments());
            a(parameterizedType.getOwnerType());
        }

        @Override // com.google.common.reflect.c
        public void e(TypeVariable<?> typeVariable) {
            String valueOf = String.valueOf(TypeToken.this.runtimeType);
            StringBuilder sb = new StringBuilder(valueOf.length() + 58);
            sb.append(valueOf);
            sb.append("contains a type variable and is not safe for the operation");
            throw new IllegalArgumentException(sb.toString());
        }

        @Override // com.google.common.reflect.c
        public void f(WildcardType wildcardType) {
            a(wildcardType.getLowerBounds());
            a(wildcardType.getUpperBounds());
        }
    }

    /* loaded from: classes10.dex */
    public class d extends com.google.common.reflect.c {
        public final /* synthetic */ ImmutableSet.Builder b;

        public d(TypeToken typeToken, ImmutableSet.Builder builder) {
            this.b = builder;
        }

        @Override // com.google.common.reflect.c
        public void b(Class<?> cls) {
            this.b.add((ImmutableSet.Builder) cls);
        }

        @Override // com.google.common.reflect.c
        public void c(GenericArrayType genericArrayType) {
            this.b.add((ImmutableSet.Builder) com.google.common.reflect.d.i(TypeToken.of(genericArrayType.getGenericComponentType()).getRawType()));
        }

        @Override // com.google.common.reflect.c
        public void d(ParameterizedType parameterizedType) {
            this.b.add((ImmutableSet.Builder) ((Class) parameterizedType.getRawType()));
        }

        @Override // com.google.common.reflect.c
        public void e(TypeVariable<?> typeVariable) {
            a(typeVariable.getBounds());
        }

        @Override // com.google.common.reflect.c
        public void f(WildcardType wildcardType) {
            a(wildcardType.getUpperBounds());
        }
    }

    /* loaded from: classes10.dex */
    public static class e {

        /* renamed from: a  reason: collision with root package name */
        public final Type[] f10734a;
        public final boolean b;

        public e(Type[] typeArr, boolean z) {
            this.f10734a = typeArr;
            this.b = z;
        }

        public boolean a(Type type) {
            for (Type type2 : this.f10734a) {
                boolean isSubtypeOf = TypeToken.of(type2).isSubtypeOf(type);
                boolean z = this.b;
                if (isSubtypeOf == z) {
                    return z;
                }
            }
            return !this.b;
        }

        public boolean b(Type type) {
            TypeToken<?> of = TypeToken.of(type);
            for (Type type2 : this.f10734a) {
                boolean isSubtypeOf = of.isSubtypeOf(type2);
                boolean z = this.b;
                if (isSubtypeOf == z) {
                    return z;
                }
            }
            return !this.b;
        }
    }

    /* loaded from: classes10.dex */
    public final class f extends TypeToken<T>.TypeSet {
        private static final long serialVersionUID = 0;
        @NullableDecl
        private transient ImmutableSet<TypeToken<? super T>> classes;

        private f() {
            super();
        }

        private Object readResolve() {
            return TypeToken.this.getTypes().classes();
        }

        @Override // com.google.common.reflect.TypeToken.TypeSet
        public TypeToken<T>.TypeSet classes() {
            return this;
        }

        @Override // com.google.common.reflect.TypeToken.TypeSet
        public TypeToken<T>.TypeSet interfaces() {
            throw new UnsupportedOperationException("classes().interfaces() not supported.");
        }

        @Override // com.google.common.reflect.TypeToken.TypeSet
        public Set<Class<? super T>> rawTypes() {
            return ImmutableSet.copyOf((Collection) i.b.a().c(TypeToken.this.getRawTypes()));
        }

        public /* synthetic */ f(TypeToken typeToken, a aVar) {
            this();
        }

        @Override // com.google.common.reflect.TypeToken.TypeSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Set<TypeToken<? super T>> delegate() {
            ImmutableSet<TypeToken<? super T>> immutableSet = this.classes;
            if (immutableSet == null) {
                ImmutableSet<TypeToken<? super T>> set = FluentIterable.from(i.f10735a.a().d(TypeToken.this)).filter(j.IGNORE_TYPE_VARIABLE_OR_WILDCARD).toSet();
                this.classes = set;
                return set;
            }
            return immutableSet;
        }
    }

    /* loaded from: classes10.dex */
    public static final class h<T> extends TypeToken<T> {
        private static final long serialVersionUID = 0;

        public h(Type type) {
            super(type, null);
        }
    }

    /* loaded from: classes10.dex */
    public static abstract class i<K> {

        /* renamed from: a  reason: collision with root package name */
        public static final i<TypeToken<?>> f10735a = new a();
        public static final i<Class<?>> b = new b();

        /* loaded from: classes10.dex */
        public class a extends i<TypeToken<?>> {
            public a() {
                super(null);
            }

            @Override // com.google.common.reflect.TypeToken.i
            /* renamed from: i */
            public Iterable<? extends TypeToken<?>> e(TypeToken<?> typeToken) {
                return typeToken.getGenericInterfaces();
            }

            @Override // com.google.common.reflect.TypeToken.i
            /* renamed from: j */
            public Class<?> f(TypeToken<?> typeToken) {
                return typeToken.getRawType();
            }

            @Override // com.google.common.reflect.TypeToken.i
            @NullableDecl
            /* renamed from: k */
            public TypeToken<?> g(TypeToken<?> typeToken) {
                return typeToken.getGenericSuperclass();
            }
        }

        /* loaded from: classes10.dex */
        public class b extends i<Class<?>> {
            public b() {
                super(null);
            }

            @Override // com.google.common.reflect.TypeToken.i
            /* renamed from: i */
            public Iterable<? extends Class<?>> e(Class<?> cls) {
                return Arrays.asList(cls.getInterfaces());
            }

            @Override // com.google.common.reflect.TypeToken.i
            /* renamed from: j */
            public Class<?> f(Class<?> cls) {
                return cls;
            }

            @Override // com.google.common.reflect.TypeToken.i
            @NullableDecl
            /* renamed from: k */
            public Class<?> g(Class<?> cls) {
                return cls.getSuperclass();
            }
        }

        /* loaded from: classes10.dex */
        public class c extends e<K> {
            public c(i iVar, i iVar2) {
                super(iVar2);
            }

            @Override // com.google.common.reflect.TypeToken.i
            public ImmutableList<K> c(Iterable<? extends K> iterable) {
                ImmutableList.Builder builder = ImmutableList.builder();
                for (K k : iterable) {
                    if (!f(k).isInterface()) {
                        builder.add((ImmutableList.Builder) k);
                    }
                }
                return super.c(builder.build());
            }

            @Override // com.google.common.reflect.TypeToken.i
            public Iterable<? extends K> e(K k) {
                return ImmutableSet.of();
            }
        }

        /* loaded from: classes10.dex */
        public class d extends Ordering<K> {
            public final /* synthetic */ Comparator h;
            public final /* synthetic */ Map i;

            public d(Comparator comparator, Map map) {
                this.h = comparator;
                this.i = map;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.Ordering, java.util.Comparator
            public int compare(K k, K k2) {
                return this.h.compare(this.i.get(k), this.i.get(k2));
            }
        }

        /* loaded from: classes10.dex */
        public static class e<K> extends i<K> {
            public final i<K> c;

            public e(i<K> iVar) {
                super(null);
                this.c = iVar;
            }

            @Override // com.google.common.reflect.TypeToken.i
            public Class<?> f(K k) {
                return this.c.f(k);
            }

            @Override // com.google.common.reflect.TypeToken.i
            public K g(K k) {
                return this.c.g(k);
            }
        }

        public i() {
        }

        public static <K, V> ImmutableList<K> h(Map<K, V> map, Comparator<? super V> comparator) {
            return (ImmutableList<K>) new d(comparator, map).immutableSortedCopy(map.keySet());
        }

        public final i<K> a() {
            return new c(this, this);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @CanIgnoreReturnValue
        public final int b(K k, Map<? super K, Integer> map) {
            Integer num = map.get(k);
            if (num != null) {
                return num.intValue();
            }
            boolean isInterface = f(k).isInterface();
            int i = isInterface;
            for (K k2 : e(k)) {
                i = Math.max(i, b(k2, map));
            }
            K g = g(k);
            int i2 = i;
            if (g != null) {
                i2 = Math.max(i, b(g, map));
            }
            int i3 = i2 + 1;
            map.put(k, Integer.valueOf(i3));
            return i3;
        }

        public ImmutableList<K> c(Iterable<? extends K> iterable) {
            HashMap newHashMap = Maps.newHashMap();
            for (K k : iterable) {
                b(k, newHashMap);
            }
            return h(newHashMap, Ordering.natural().reverse());
        }

        public final ImmutableList<K> d(K k) {
            return c(ImmutableList.of(k));
        }

        public abstract Iterable<? extends K> e(K k);

        public abstract Class<?> f(K k);

        @NullableDecl
        public abstract K g(K k);

        public /* synthetic */ i(a aVar) {
            this();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* loaded from: classes10.dex */
    public static abstract class j implements Predicate<TypeToken<?>> {
        public static final j IGNORE_TYPE_VARIABLE_OR_WILDCARD = new a("IGNORE_TYPE_VARIABLE_OR_WILDCARD", 0);
        public static final j INTERFACE_ONLY = new b("INTERFACE_ONLY", 1);
        private static final /* synthetic */ j[] $VALUES = $values();

        /* loaded from: classes10.dex */
        public enum a extends j {
            public a(String str, int i) {
                super(str, i, null);
            }

            @Override // com.google.common.base.Predicate
            public boolean apply(TypeToken<?> typeToken) {
                return ((((TypeToken) typeToken).runtimeType instanceof TypeVariable) || (((TypeToken) typeToken).runtimeType instanceof WildcardType)) ? false : true;
            }
        }

        /* loaded from: classes10.dex */
        public enum b extends j {
            public b(String str, int i) {
                super(str, i, null);
            }

            @Override // com.google.common.base.Predicate
            public boolean apply(TypeToken<?> typeToken) {
                return typeToken.getRawType().isInterface();
            }
        }

        private static /* synthetic */ j[] $values() {
            return new j[]{IGNORE_TYPE_VARIABLE_OR_WILDCARD, INTERFACE_ONLY};
        }

        private j(String str, int i) {
        }

        public static j valueOf(String str) {
            return (j) Enum.valueOf(j.class, str);
        }

        public static j[] values() {
            return (j[]) $VALUES.clone();
        }

        public /* synthetic */ j(String str, int i, a aVar) {
            this(str, i);
        }
    }

    public /* synthetic */ TypeToken(Type type, a aVar) {
        this(type);
    }

    private static e any(Type[] typeArr) {
        return new e(typeArr, true);
    }

    @NullableDecl
    private TypeToken<? super T> boundAsSuperclass(Type type) {
        TypeToken<? super T> typeToken = (TypeToken<? super T>) of(type);
        if (typeToken.getRawType().isInterface()) {
            return null;
        }
        return typeToken;
    }

    private ImmutableList<TypeToken<? super T>> boundsAsInterfaces(Type[] typeArr) {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (Type type : typeArr) {
            TypeToken<?> of = of(type);
            if (of.getRawType().isInterface()) {
                builder.add((ImmutableList.Builder) of);
            }
        }
        return builder.build();
    }

    private static Type canonicalizeTypeArg(TypeVariable<?> typeVariable, Type type) {
        if (type instanceof WildcardType) {
            return canonicalizeWildcardType(typeVariable, (WildcardType) type);
        }
        return canonicalizeWildcardsInType(type);
    }

    private static WildcardType canonicalizeWildcardType(TypeVariable<?> typeVariable, WildcardType wildcardType) {
        Type[] upperBounds;
        Type[] bounds = typeVariable.getBounds();
        ArrayList arrayList = new ArrayList();
        for (Type type : wildcardType.getUpperBounds()) {
            if (!any(bounds).a(type)) {
                arrayList.add(canonicalizeWildcardsInType(type));
            }
        }
        return new d.j(wildcardType.getLowerBounds(), (Type[]) arrayList.toArray(new Type[0]));
    }

    private static ParameterizedType canonicalizeWildcardsInParameterizedType(ParameterizedType parameterizedType) {
        Class cls = (Class) parameterizedType.getRawType();
        TypeVariable<Class<T>>[] typeParameters = cls.getTypeParameters();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        for (int i2 = 0; i2 < actualTypeArguments.length; i2++) {
            actualTypeArguments[i2] = canonicalizeTypeArg(typeParameters[i2], actualTypeArguments[i2]);
        }
        return com.google.common.reflect.d.n(parameterizedType.getOwnerType(), cls, actualTypeArguments);
    }

    private static Type canonicalizeWildcardsInType(Type type) {
        if (type instanceof ParameterizedType) {
            return canonicalizeWildcardsInParameterizedType((ParameterizedType) type);
        }
        return type instanceof GenericArrayType ? com.google.common.reflect.d.k(canonicalizeWildcardsInType(((GenericArrayType) type).getGenericComponentType())) : type;
    }

    private static e every(Type[] typeArr) {
        return new e(typeArr, false);
    }

    private TypeToken<? extends T> getArraySubtype(Class<?> cls) {
        return (TypeToken<? extends T>) of(newArrayClassOrGenericArrayType(getComponentType().getSubtype(cls.getComponentType()).runtimeType));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private TypeToken<? super T> getArraySupertype(Class<? super T> cls) {
        return (TypeToken<? super T>) of(newArrayClassOrGenericArrayType(((TypeToken) Preconditions.checkNotNull(getComponentType(), "%s isn't a super type of %s", cls, this)).getSupertype(cls.getComponentType()).runtimeType));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public TypeResolver getCovariantTypeResolver() {
        TypeResolver typeResolver = this.covariantTypeResolver;
        if (typeResolver == null) {
            TypeResolver d2 = TypeResolver.d(this.runtimeType);
            this.covariantTypeResolver = d2;
            return d2;
        }
        return typeResolver;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public TypeResolver getInvariantTypeResolver() {
        TypeResolver typeResolver = this.invariantTypeResolver;
        if (typeResolver == null) {
            TypeResolver f2 = TypeResolver.f(this.runtimeType);
            this.invariantTypeResolver = f2;
            return f2;
        }
        return typeResolver;
    }

    @NullableDecl
    private Type getOwnerTypeIfPresent() {
        Type type = this.runtimeType;
        if (type instanceof ParameterizedType) {
            return ((ParameterizedType) type).getOwnerType();
        }
        if (type instanceof Class) {
            return ((Class) type).getEnclosingClass();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ImmutableSet<Class<? super T>> getRawTypes() {
        ImmutableSet.Builder builder = ImmutableSet.builder();
        new d(this, builder).a(this.runtimeType);
        return builder.build();
    }

    private TypeToken<? extends T> getSubtypeFromLowerBounds(Class<?> cls, Type[] typeArr) {
        if (typeArr.length > 0) {
            return (TypeToken<? extends T>) of(typeArr[0]).getSubtype(cls);
        }
        String valueOf = String.valueOf(cls);
        String valueOf2 = String.valueOf(this);
        StringBuilder sb = new StringBuilder(valueOf.length() + 21 + valueOf2.length());
        sb.append(valueOf);
        sb.append(" isn't a subclass of ");
        sb.append(valueOf2);
        throw new IllegalArgumentException(sb.toString());
    }

    private TypeToken<? super T> getSupertypeFromUpperBounds(Class<? super T> cls, Type[] typeArr) {
        for (Type type : typeArr) {
            TypeToken<?> of = of(type);
            if (of.isSubtypeOf(cls)) {
                return (TypeToken<? super T>) of.getSupertype(cls);
            }
        }
        String valueOf = String.valueOf(cls);
        String valueOf2 = String.valueOf(this);
        StringBuilder sb = new StringBuilder(valueOf.length() + 23 + valueOf2.length());
        sb.append(valueOf);
        sb.append(" isn't a super type of ");
        sb.append(valueOf2);
        throw new IllegalArgumentException(sb.toString());
    }

    private boolean is(Type type, TypeVariable<?> typeVariable) {
        if (this.runtimeType.equals(type)) {
            return true;
        }
        if (type instanceof WildcardType) {
            WildcardType canonicalizeWildcardType = canonicalizeWildcardType(typeVariable, (WildcardType) type);
            return every(canonicalizeWildcardType.getUpperBounds()).b(this.runtimeType) && every(canonicalizeWildcardType.getLowerBounds()).a(this.runtimeType);
        }
        return canonicalizeWildcardsInType(this.runtimeType).equals(canonicalizeWildcardsInType(type));
    }

    private boolean isOwnedBySubtypeOf(Type type) {
        Iterator<TypeToken<? super T>> it = getTypes().iterator();
        while (it.hasNext()) {
            Type ownerTypeIfPresent = it.next().getOwnerTypeIfPresent();
            if (ownerTypeIfPresent != null && of(ownerTypeIfPresent).isSubtypeOf(type)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSubtypeOfArrayType(GenericArrayType genericArrayType) {
        Type type = this.runtimeType;
        if (type instanceof Class) {
            Class cls = (Class) type;
            if (cls.isArray()) {
                return of((Class) cls.getComponentType()).isSubtypeOf(genericArrayType.getGenericComponentType());
            }
            return false;
        } else if (type instanceof GenericArrayType) {
            return of(((GenericArrayType) type).getGenericComponentType()).isSubtypeOf(genericArrayType.getGenericComponentType());
        } else {
            return false;
        }
    }

    private boolean isSubtypeOfParameterizedType(ParameterizedType parameterizedType) {
        Class<? super Object> rawType = of(parameterizedType).getRawType();
        if (someRawTypeIsSubclassOf(rawType)) {
            TypeVariable<Class<? super Object>>[] typeParameters = rawType.getTypeParameters();
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            for (int i2 = 0; i2 < typeParameters.length; i2++) {
                if (!of(getCovariantTypeResolver().resolveType(typeParameters[i2])).is(actualTypeArguments[i2], typeParameters[i2])) {
                    return false;
                }
            }
            return Modifier.isStatic(((Class) parameterizedType.getRawType()).getModifiers()) || parameterizedType.getOwnerType() == null || isOwnedBySubtypeOf(parameterizedType.getOwnerType());
        }
        return false;
    }

    private boolean isSupertypeOfArray(GenericArrayType genericArrayType) {
        Type type = this.runtimeType;
        if (type instanceof Class) {
            Class cls = (Class) type;
            if (!cls.isArray()) {
                return cls.isAssignableFrom(Object[].class);
            }
            return of(genericArrayType.getGenericComponentType()).isSubtypeOf(cls.getComponentType());
        } else if (type instanceof GenericArrayType) {
            return of(genericArrayType.getGenericComponentType()).isSubtypeOf(((GenericArrayType) this.runtimeType).getGenericComponentType());
        } else {
            return false;
        }
    }

    private boolean isWrapper() {
        return Primitives.allWrapperTypes().contains(this.runtimeType);
    }

    private static Type newArrayClassOrGenericArrayType(Type type) {
        return d.e.JAVA7.newArrayType(type);
    }

    public static <T> TypeToken<T> of(Class<T> cls) {
        return new h(cls);
    }

    private TypeToken<?> resolveSupertype(Type type) {
        TypeToken<?> of = of(getCovariantTypeResolver().resolveType(type));
        of.covariantTypeResolver = this.covariantTypeResolver;
        of.invariantTypeResolver = this.invariantTypeResolver;
        return of;
    }

    private Type resolveTypeArgsForSubclass(Class<?> cls) {
        if (!(this.runtimeType instanceof Class) || (cls.getTypeParameters().length != 0 && getRawType().getTypeParameters().length == 0)) {
            TypeToken genericType = toGenericType(cls);
            return new TypeResolver().where(genericType.getSupertype(getRawType()).runtimeType, this.runtimeType).resolveType(genericType.runtimeType);
        }
        return cls;
    }

    private boolean someRawTypeIsSubclassOf(Class<?> cls) {
        UnmodifiableIterator<Class<? super T>> it = getRawTypes().iterator();
        while (it.hasNext()) {
            if (cls.isAssignableFrom(it.next())) {
                return true;
            }
        }
        return false;
    }

    @VisibleForTesting
    public static <T> TypeToken<? extends T> toGenericType(Class<T> cls) {
        if (cls.isArray()) {
            return (TypeToken<? extends T>) of(com.google.common.reflect.d.k(toGenericType(cls.getComponentType()).runtimeType));
        }
        TypeVariable<Class<T>>[] typeParameters = cls.getTypeParameters();
        Type type = (!cls.isMemberClass() || Modifier.isStatic(cls.getModifiers())) ? null : toGenericType(cls.getEnclosingClass()).runtimeType;
        if (typeParameters.length <= 0 && (type == null || type == cls.getEnclosingClass())) {
            return of((Class) cls);
        }
        return (TypeToken<? extends T>) of(com.google.common.reflect.d.n(type, cls, typeParameters));
    }

    public final Invokable<T, T> constructor(Constructor<?> constructor) {
        Preconditions.checkArgument(constructor.getDeclaringClass() == getRawType(), "%s not declared by %s", constructor, getRawType());
        return new b(constructor);
    }

    public boolean equals(@NullableDecl Object obj) {
        if (obj instanceof TypeToken) {
            return this.runtimeType.equals(((TypeToken) obj).runtimeType);
        }
        return false;
    }

    @NullableDecl
    public final TypeToken<?> getComponentType() {
        Type j2 = com.google.common.reflect.d.j(this.runtimeType);
        if (j2 == null) {
            return null;
        }
        return of(j2);
    }

    public final ImmutableList<TypeToken<? super T>> getGenericInterfaces() {
        Type type = this.runtimeType;
        if (type instanceof TypeVariable) {
            return boundsAsInterfaces(((TypeVariable) type).getBounds());
        }
        if (type instanceof WildcardType) {
            return boundsAsInterfaces(((WildcardType) type).getUpperBounds());
        }
        ImmutableList.Builder builder = ImmutableList.builder();
        for (Type type2 : getRawType().getGenericInterfaces()) {
            builder.add((ImmutableList.Builder) resolveSupertype(type2));
        }
        return builder.build();
    }

    @NullableDecl
    public final TypeToken<? super T> getGenericSuperclass() {
        Type type = this.runtimeType;
        if (type instanceof TypeVariable) {
            return boundAsSuperclass(((TypeVariable) type).getBounds()[0]);
        }
        if (type instanceof WildcardType) {
            return boundAsSuperclass(((WildcardType) type).getUpperBounds()[0]);
        }
        Type genericSuperclass = getRawType().getGenericSuperclass();
        if (genericSuperclass == null) {
            return null;
        }
        return (TypeToken<? super T>) resolveSupertype(genericSuperclass);
    }

    public final Class<? super T> getRawType() {
        return getRawTypes().iterator().next();
    }

    public final TypeToken<? extends T> getSubtype(Class<?> cls) {
        Preconditions.checkArgument(!(this.runtimeType instanceof TypeVariable), "Cannot get subtype of type variable <%s>", this);
        Type type = this.runtimeType;
        if (type instanceof WildcardType) {
            return getSubtypeFromLowerBounds(cls, ((WildcardType) type).getLowerBounds());
        }
        if (isArray()) {
            return getArraySubtype(cls);
        }
        Preconditions.checkArgument(getRawType().isAssignableFrom(cls), "%s isn't a subclass of %s", cls, this);
        TypeToken<? extends T> typeToken = (TypeToken<? extends T>) of(resolveTypeArgsForSubclass(cls));
        Preconditions.checkArgument(typeToken.isSubtypeOf((TypeToken<?>) this), "%s does not appear to be a subtype of %s", typeToken, this);
        return typeToken;
    }

    public final TypeToken<? super T> getSupertype(Class<? super T> cls) {
        Preconditions.checkArgument(someRawTypeIsSubclassOf(cls), "%s is not a super class of %s", cls, this);
        Type type = this.runtimeType;
        if (type instanceof TypeVariable) {
            return getSupertypeFromUpperBounds(cls, ((TypeVariable) type).getBounds());
        }
        if (type instanceof WildcardType) {
            return getSupertypeFromUpperBounds(cls, ((WildcardType) type).getUpperBounds());
        }
        if (cls.isArray()) {
            return getArraySupertype(cls);
        }
        return (TypeToken<? super T>) resolveSupertype(toGenericType(cls).runtimeType);
    }

    public final Type getType() {
        return this.runtimeType;
    }

    public final TypeToken<T>.TypeSet getTypes() {
        return new TypeSet();
    }

    public int hashCode() {
        return this.runtimeType.hashCode();
    }

    public final boolean isArray() {
        return getComponentType() != null;
    }

    public final boolean isPrimitive() {
        Type type = this.runtimeType;
        return (type instanceof Class) && ((Class) type).isPrimitive();
    }

    public final boolean isSubtypeOf(TypeToken<?> typeToken) {
        return isSubtypeOf(typeToken.getType());
    }

    public final boolean isSupertypeOf(TypeToken<?> typeToken) {
        return typeToken.isSubtypeOf(getType());
    }

    public final Invokable<T, Object> method(Method method) {
        Preconditions.checkArgument(someRawTypeIsSubclassOf(method.getDeclaringClass()), "%s not declared by %s", method, this);
        return new a(method);
    }

    @CanIgnoreReturnValue
    public final TypeToken<T> rejectTypeVariables() {
        new c().a(this.runtimeType);
        return this;
    }

    public final TypeToken<?> resolveType(Type type) {
        Preconditions.checkNotNull(type);
        return of(getInvariantTypeResolver().resolveType(type));
    }

    public String toString() {
        return com.google.common.reflect.d.t(this.runtimeType);
    }

    public final TypeToken<T> unwrap() {
        return isWrapper() ? of(Primitives.unwrap((Class) this.runtimeType)) : this;
    }

    public final <X> TypeToken<T> where(TypeParameter<X> typeParameter, TypeToken<X> typeToken) {
        return new h(new TypeResolver().m(ImmutableMap.of(new TypeResolver.d(typeParameter.f10729a), typeToken.runtimeType)).resolveType(this.runtimeType));
    }

    public final TypeToken<T> wrap() {
        return isPrimitive() ? of(Primitives.wrap((Class) this.runtimeType)) : this;
    }

    public Object writeReplace() {
        return of(new TypeResolver().resolveType(this.runtimeType));
    }

    /* loaded from: classes10.dex */
    public class TypeSet extends ForwardingSet<TypeToken<? super T>> implements Serializable {
        private static final long serialVersionUID = 0;
        @NullableDecl
        private transient ImmutableSet<TypeToken<? super T>> types;

        public TypeSet() {
        }

        public TypeToken<T>.TypeSet classes() {
            return new f(TypeToken.this, null);
        }

        public TypeToken<T>.TypeSet interfaces() {
            return new g(this);
        }

        public Set<Class<? super T>> rawTypes() {
            return ImmutableSet.copyOf((Collection) i.b.c(TypeToken.this.getRawTypes()));
        }

        @Override // com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Set<TypeToken<? super T>> delegate() {
            ImmutableSet<TypeToken<? super T>> immutableSet = this.types;
            if (immutableSet == null) {
                ImmutableSet<TypeToken<? super T>> set = FluentIterable.from(i.f10735a.d(TypeToken.this)).filter(j.IGNORE_TYPE_VARIABLE_OR_WILDCARD).toSet();
                this.types = set;
                return set;
            }
            return immutableSet;
        }
    }

    /* loaded from: classes10.dex */
    public final class g extends TypeToken<T>.TypeSet {
        private static final long serialVersionUID = 0;
        private final transient TypeToken<T>.TypeSet allTypes;
        @NullableDecl
        private transient ImmutableSet<TypeToken<? super T>> interfaces;

        /* loaded from: classes10.dex */
        public class a implements Predicate<Class<?>> {
            public a(g gVar) {
            }

            @Override // com.google.common.base.Predicate
            /* renamed from: a */
            public boolean apply(Class<?> cls) {
                return cls.isInterface();
            }
        }

        public g(TypeToken<T>.TypeSet typeSet) {
            super();
            this.allTypes = typeSet;
        }

        private Object readResolve() {
            return TypeToken.this.getTypes().interfaces();
        }

        @Override // com.google.common.reflect.TypeToken.TypeSet
        public TypeToken<T>.TypeSet classes() {
            throw new UnsupportedOperationException("interfaces().classes() not supported.");
        }

        @Override // com.google.common.reflect.TypeToken.TypeSet
        public TypeToken<T>.TypeSet interfaces() {
            return this;
        }

        @Override // com.google.common.reflect.TypeToken.TypeSet
        public Set<Class<? super T>> rawTypes() {
            return FluentIterable.from(i.b.c(TypeToken.this.getRawTypes())).filter(new a(this)).toSet();
        }

        @Override // com.google.common.reflect.TypeToken.TypeSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Set<TypeToken<? super T>> delegate() {
            ImmutableSet<TypeToken<? super T>> immutableSet = this.interfaces;
            if (immutableSet == null) {
                ImmutableSet<TypeToken<? super T>> set = FluentIterable.from(this.allTypes).filter(j.INTERFACE_ONLY).toSet();
                this.interfaces = set;
                return set;
            }
            return immutableSet;
        }
    }

    public TypeToken() {
        Type capture = capture();
        this.runtimeType = capture;
        Preconditions.checkState(!(capture instanceof TypeVariable), "Cannot construct a TypeToken for a type variable.\nYou probably meant to call new TypeToken<%s>(getClass()) that can resolve the type variable for you.\nIf you do need to create a TypeToken of a type variable, please use TypeToken.of() instead.", capture);
    }

    public static TypeToken<?> of(Type type) {
        return new h(type);
    }

    public final boolean isSubtypeOf(Type type) {
        Preconditions.checkNotNull(type);
        if (type instanceof WildcardType) {
            return any(((WildcardType) type).getLowerBounds()).b(this.runtimeType);
        }
        Type type2 = this.runtimeType;
        if (type2 instanceof WildcardType) {
            return any(((WildcardType) type2).getUpperBounds()).a(type);
        }
        if (type2 instanceof TypeVariable) {
            return type2.equals(type) || any(((TypeVariable) this.runtimeType).getBounds()).a(type);
        } else if (type2 instanceof GenericArrayType) {
            return of(type).isSupertypeOfArray((GenericArrayType) this.runtimeType);
        } else {
            if (type instanceof Class) {
                return someRawTypeIsSubclassOf((Class) type);
            }
            if (type instanceof ParameterizedType) {
                return isSubtypeOfParameterizedType((ParameterizedType) type);
            }
            if (type instanceof GenericArrayType) {
                return isSubtypeOfArrayType((GenericArrayType) type);
            }
            return false;
        }
    }

    public final boolean isSupertypeOf(Type type) {
        return of(type).isSubtypeOf(getType());
    }

    public TypeToken(Class<?> cls) {
        Type capture = super.capture();
        if (capture instanceof Class) {
            this.runtimeType = capture;
        } else {
            this.runtimeType = TypeResolver.d(cls).resolveType(capture);
        }
    }

    public final <X> TypeToken<T> where(TypeParameter<X> typeParameter, Class<X> cls) {
        return where(typeParameter, of((Class) cls));
    }

    private TypeToken(Type type) {
        this.runtimeType = (Type) Preconditions.checkNotNull(type);
    }
}
