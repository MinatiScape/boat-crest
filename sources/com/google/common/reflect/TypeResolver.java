package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.reflect.d;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.text.Typography;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
/* loaded from: classes10.dex */
public final class TypeResolver {

    /* renamed from: a  reason: collision with root package name */
    public final c f10730a;

    /* loaded from: classes10.dex */
    public class a extends com.google.common.reflect.c {
        public final /* synthetic */ Map b;
        public final /* synthetic */ Type c;

        public a(Map map, Type type) {
            this.b = map;
            this.c = type;
        }

        @Override // com.google.common.reflect.c
        public void b(Class<?> cls) {
            if (this.c instanceof WildcardType) {
                return;
            }
            String valueOf = String.valueOf(cls);
            String valueOf2 = String.valueOf(this.c);
            StringBuilder sb = new StringBuilder(valueOf.length() + 25 + valueOf2.length());
            sb.append("No type mapping from ");
            sb.append(valueOf);
            sb.append(" to ");
            sb.append(valueOf2);
            throw new IllegalArgumentException(sb.toString());
        }

        @Override // com.google.common.reflect.c
        public void c(GenericArrayType genericArrayType) {
            Type type = this.c;
            if (type instanceof WildcardType) {
                return;
            }
            Type j = com.google.common.reflect.d.j(type);
            Preconditions.checkArgument(j != null, "%s is not an array type.", this.c);
            TypeResolver.g(this.b, genericArrayType.getGenericComponentType(), j);
        }

        @Override // com.google.common.reflect.c
        public void d(ParameterizedType parameterizedType) {
            Type type = this.c;
            if (type instanceof WildcardType) {
                return;
            }
            ParameterizedType parameterizedType2 = (ParameterizedType) TypeResolver.e(ParameterizedType.class, type);
            if (parameterizedType.getOwnerType() != null && parameterizedType2.getOwnerType() != null) {
                TypeResolver.g(this.b, parameterizedType.getOwnerType(), parameterizedType2.getOwnerType());
            }
            Preconditions.checkArgument(parameterizedType.getRawType().equals(parameterizedType2.getRawType()), "Inconsistent raw type: %s vs. %s", parameterizedType, this.c);
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            Type[] actualTypeArguments2 = parameterizedType2.getActualTypeArguments();
            Preconditions.checkArgument(actualTypeArguments.length == actualTypeArguments2.length, "%s not compatible with %s", parameterizedType, parameterizedType2);
            for (int i = 0; i < actualTypeArguments.length; i++) {
                TypeResolver.g(this.b, actualTypeArguments[i], actualTypeArguments2[i]);
            }
        }

        @Override // com.google.common.reflect.c
        public void e(TypeVariable<?> typeVariable) {
            this.b.put(new d(typeVariable), this.c);
        }

        @Override // com.google.common.reflect.c
        public void f(WildcardType wildcardType) {
            Type type = this.c;
            if (type instanceof WildcardType) {
                WildcardType wildcardType2 = (WildcardType) type;
                Type[] upperBounds = wildcardType.getUpperBounds();
                Type[] upperBounds2 = wildcardType2.getUpperBounds();
                Type[] lowerBounds = wildcardType.getLowerBounds();
                Type[] lowerBounds2 = wildcardType2.getLowerBounds();
                Preconditions.checkArgument(upperBounds.length == upperBounds2.length && lowerBounds.length == lowerBounds2.length, "Incompatible type: %s vs. %s", wildcardType, this.c);
                for (int i = 0; i < upperBounds.length; i++) {
                    TypeResolver.g(this.b, upperBounds[i], upperBounds2[i]);
                }
                for (int i2 = 0; i2 < lowerBounds.length; i2++) {
                    TypeResolver.g(this.b, lowerBounds[i2], lowerBounds2[i2]);
                }
            }
        }
    }

    /* loaded from: classes10.dex */
    public static final class b extends com.google.common.reflect.c {
        public final Map<d, Type> b = Maps.newHashMap();

        public static ImmutableMap<d, Type> g(Type type) {
            Preconditions.checkNotNull(type);
            b bVar = new b();
            bVar.a(type);
            return ImmutableMap.copyOf((Map) bVar.b);
        }

        @Override // com.google.common.reflect.c
        public void b(Class<?> cls) {
            a(cls.getGenericSuperclass());
            a(cls.getGenericInterfaces());
        }

        @Override // com.google.common.reflect.c
        public void d(ParameterizedType parameterizedType) {
            Class cls = (Class) parameterizedType.getRawType();
            TypeVariable[] typeParameters = cls.getTypeParameters();
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            Preconditions.checkState(typeParameters.length == actualTypeArguments.length);
            for (int i = 0; i < typeParameters.length; i++) {
                h(new d(typeParameters[i]), actualTypeArguments[i]);
            }
            a(cls);
            a(parameterizedType.getOwnerType());
        }

        @Override // com.google.common.reflect.c
        public void e(TypeVariable<?> typeVariable) {
            a(typeVariable.getBounds());
        }

        @Override // com.google.common.reflect.c
        public void f(WildcardType wildcardType) {
            a(wildcardType.getUpperBounds());
        }

        public final void h(d dVar, Type type) {
            if (this.b.containsKey(dVar)) {
                return;
            }
            Type type2 = type;
            while (type2 != null) {
                if (dVar.a(type2)) {
                    while (type != null) {
                        type = this.b.remove(d.c(type));
                    }
                    return;
                }
                type2 = this.b.get(d.c(type2));
            }
            this.b.put(dVar, type);
        }
    }

    /* loaded from: classes10.dex */
    public static final class d {

        /* renamed from: a  reason: collision with root package name */
        public final TypeVariable<?> f10732a;

        public d(TypeVariable<?> typeVariable) {
            this.f10732a = (TypeVariable) Preconditions.checkNotNull(typeVariable);
        }

        public static d c(Type type) {
            if (type instanceof TypeVariable) {
                return new d((TypeVariable) type);
            }
            return null;
        }

        public boolean a(Type type) {
            if (type instanceof TypeVariable) {
                return b((TypeVariable) type);
            }
            return false;
        }

        public final boolean b(TypeVariable<?> typeVariable) {
            return this.f10732a.getGenericDeclaration().equals(typeVariable.getGenericDeclaration()) && this.f10732a.getName().equals(typeVariable.getName());
        }

        public boolean equals(Object obj) {
            if (obj instanceof d) {
                return b(((d) obj).f10732a);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hashCode(this.f10732a.getGenericDeclaration(), this.f10732a.getName());
        }

        public String toString() {
            return this.f10732a.toString();
        }
    }

    /* loaded from: classes10.dex */
    public static class e {
        public static final e b = new e();

        /* renamed from: a  reason: collision with root package name */
        public final AtomicInteger f10733a;

        /* loaded from: classes10.dex */
        public class a extends e {
            public final /* synthetic */ TypeVariable c;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(e eVar, AtomicInteger atomicInteger, TypeVariable typeVariable) {
                super(atomicInteger, null);
                this.c = typeVariable;
            }

            @Override // com.google.common.reflect.TypeResolver.e
            public TypeVariable<?> b(Type[] typeArr) {
                LinkedHashSet linkedHashSet = new LinkedHashSet(Arrays.asList(typeArr));
                linkedHashSet.addAll(Arrays.asList(this.c.getBounds()));
                if (linkedHashSet.size() > 1) {
                    linkedHashSet.remove(Object.class);
                }
                return super.b((Type[]) linkedHashSet.toArray(new Type[0]));
            }
        }

        public /* synthetic */ e(AtomicInteger atomicInteger, a aVar) {
            this(atomicInteger);
        }

        public final Type a(Type type) {
            Preconditions.checkNotNull(type);
            if ((type instanceof Class) || (type instanceof TypeVariable)) {
                return type;
            }
            if (type instanceof GenericArrayType) {
                return com.google.common.reflect.d.k(e().a(((GenericArrayType) type).getGenericComponentType()));
            }
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Class cls = (Class) parameterizedType.getRawType();
                TypeVariable<?>[] typeParameters = cls.getTypeParameters();
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                for (int i = 0; i < actualTypeArguments.length; i++) {
                    actualTypeArguments[i] = d(typeParameters[i]).a(actualTypeArguments[i]);
                }
                return com.google.common.reflect.d.n(e().c(parameterizedType.getOwnerType()), cls, actualTypeArguments);
            } else if (type instanceof WildcardType) {
                WildcardType wildcardType = (WildcardType) type;
                return wildcardType.getLowerBounds().length == 0 ? b(wildcardType.getUpperBounds()) : type;
            } else {
                throw new AssertionError("must have been one of the known types");
            }
        }

        public TypeVariable<?> b(Type[] typeArr) {
            int incrementAndGet = this.f10733a.incrementAndGet();
            String join = Joiner.on((char) Typography.amp).join(typeArr);
            StringBuilder sb = new StringBuilder(String.valueOf(join).length() + 33);
            sb.append("capture#");
            sb.append(incrementAndGet);
            sb.append("-of ? extends ");
            sb.append(join);
            return com.google.common.reflect.d.l(e.class, sb.toString(), typeArr);
        }

        public final Type c(@NullableDecl Type type) {
            if (type == null) {
                return null;
            }
            return a(type);
        }

        public final e d(TypeVariable<?> typeVariable) {
            return new a(this, this.f10733a, typeVariable);
        }

        public final e e() {
            return new e(this.f10733a);
        }

        public e() {
            this(new AtomicInteger());
        }

        public e(AtomicInteger atomicInteger) {
            this.f10733a = atomicInteger;
        }
    }

    public /* synthetic */ TypeResolver(c cVar, a aVar) {
        this(cVar);
    }

    public static TypeResolver d(Type type) {
        return new TypeResolver().m(b.g(type));
    }

    public static <T> T e(Class<T> cls, Object obj) {
        try {
            return cls.cast(obj);
        } catch (ClassCastException unused) {
            String valueOf = String.valueOf(obj);
            String simpleName = cls.getSimpleName();
            StringBuilder sb = new StringBuilder(valueOf.length() + 10 + simpleName.length());
            sb.append(valueOf);
            sb.append(" is not a ");
            sb.append(simpleName);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public static TypeResolver f(Type type) {
        return new TypeResolver().m(b.g(e.b.a(type)));
    }

    public static void g(Map<d, Type> map, Type type, Type type2) {
        if (type.equals(type2)) {
            return;
        }
        new a(map, type2).a(type);
    }

    public final Type h(GenericArrayType genericArrayType) {
        return com.google.common.reflect.d.k(resolveType(genericArrayType.getGenericComponentType()));
    }

    public final ParameterizedType i(ParameterizedType parameterizedType) {
        Type ownerType = parameterizedType.getOwnerType();
        return com.google.common.reflect.d.n(ownerType == null ? null : resolveType(ownerType), (Class) resolveType(parameterizedType.getRawType()), j(parameterizedType.getActualTypeArguments()));
    }

    public final Type[] j(Type[] typeArr) {
        Type[] typeArr2 = new Type[typeArr.length];
        for (int i = 0; i < typeArr.length; i++) {
            typeArr2[i] = resolveType(typeArr[i]);
        }
        return typeArr2;
    }

    public Type[] k(Type[] typeArr) {
        for (int i = 0; i < typeArr.length; i++) {
            typeArr[i] = resolveType(typeArr[i]);
        }
        return typeArr;
    }

    public final WildcardType l(WildcardType wildcardType) {
        return new d.j(j(wildcardType.getLowerBounds()), j(wildcardType.getUpperBounds()));
    }

    public TypeResolver m(Map<d, ? extends Type> map) {
        return new TypeResolver(this.f10730a.c(map));
    }

    public Type resolveType(Type type) {
        Preconditions.checkNotNull(type);
        if (type instanceof TypeVariable) {
            return this.f10730a.a((TypeVariable) type);
        }
        if (type instanceof ParameterizedType) {
            return i((ParameterizedType) type);
        }
        if (type instanceof GenericArrayType) {
            return h((GenericArrayType) type);
        }
        return type instanceof WildcardType ? l((WildcardType) type) : type;
    }

    public TypeResolver where(Type type, Type type2) {
        HashMap newHashMap = Maps.newHashMap();
        g(newHashMap, (Type) Preconditions.checkNotNull(type), (Type) Preconditions.checkNotNull(type2));
        return m(newHashMap);
    }

    /* loaded from: classes10.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public final ImmutableMap<d, Type> f10731a;

        /* loaded from: classes10.dex */
        public class a extends c {
            public final /* synthetic */ TypeVariable b;
            public final /* synthetic */ c c;

            public a(c cVar, TypeVariable typeVariable, c cVar2) {
                this.b = typeVariable;
                this.c = cVar2;
            }

            @Override // com.google.common.reflect.TypeResolver.c
            public Type b(TypeVariable<?> typeVariable, c cVar) {
                return typeVariable.getGenericDeclaration().equals(this.b.getGenericDeclaration()) ? typeVariable : this.c.b(typeVariable, cVar);
            }
        }

        public c() {
            this.f10731a = ImmutableMap.of();
        }

        public final Type a(TypeVariable<?> typeVariable) {
            return b(typeVariable, new a(this, typeVariable, this));
        }

        /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.reflect.GenericDeclaration] */
        public Type b(TypeVariable<?> typeVariable, c cVar) {
            Type type = this.f10731a.get(new d(typeVariable));
            if (type == null) {
                Type[] bounds = typeVariable.getBounds();
                if (bounds.length == 0) {
                    return typeVariable;
                }
                Type[] j = new TypeResolver(cVar, null).j(bounds);
                return (d.f.f10738a && Arrays.equals(bounds, j)) ? typeVariable : com.google.common.reflect.d.l(typeVariable.getGenericDeclaration(), typeVariable.getName(), j);
            }
            return new TypeResolver(cVar, null).resolveType(type);
        }

        public final c c(Map<d, ? extends Type> map) {
            ImmutableMap.Builder builder = ImmutableMap.builder();
            builder.putAll(this.f10731a);
            for (Map.Entry<d, ? extends Type> entry : map.entrySet()) {
                d key = entry.getKey();
                Type value = entry.getValue();
                Preconditions.checkArgument(!key.a(value), "Type variable %s bound to itself", key);
                builder.put(key, value);
            }
            return new c(builder.build());
        }

        public c(ImmutableMap<d, Type> immutableMap) {
            this.f10731a = immutableMap;
        }
    }

    public TypeResolver() {
        this.f10730a = new c();
    }

    public TypeResolver(c cVar) {
        this.f10730a = cVar;
    }
}
