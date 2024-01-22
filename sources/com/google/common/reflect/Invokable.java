package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
/* loaded from: classes10.dex */
public abstract class Invokable<T, R> extends com.google.common.reflect.a implements GenericDeclaration {

    /* loaded from: classes10.dex */
    public static class a<T> extends Invokable<T, T> {
        public final Constructor<?> j;

        public a(Constructor<?> constructor) {
            super(constructor);
            this.j = constructor;
        }

        @Override // com.google.common.reflect.Invokable
        public Type[] a() {
            return this.j.getGenericExceptionTypes();
        }

        @Override // com.google.common.reflect.Invokable
        public Type[] b() {
            Type[] genericParameterTypes = this.j.getGenericParameterTypes();
            if (genericParameterTypes.length <= 0 || !f()) {
                return genericParameterTypes;
            }
            Class<?>[] parameterTypes = this.j.getParameterTypes();
            return (genericParameterTypes.length == parameterTypes.length && parameterTypes[0] == getDeclaringClass().getEnclosingClass()) ? (Type[]) Arrays.copyOfRange(genericParameterTypes, 1, genericParameterTypes.length) : genericParameterTypes;
        }

        @Override // com.google.common.reflect.Invokable
        public Type c() {
            Class<? super T> declaringClass = getDeclaringClass();
            TypeVariable<Class<? super T>>[] typeParameters = declaringClass.getTypeParameters();
            return typeParameters.length > 0 ? d.m(declaringClass, typeParameters) : declaringClass;
        }

        @Override // com.google.common.reflect.Invokable
        public final Annotation[][] d() {
            return this.j.getParameterAnnotations();
        }

        @Override // com.google.common.reflect.Invokable
        public final Object e(@NullableDecl Object obj, Object[] objArr) throws InvocationTargetException, IllegalAccessException {
            try {
                return this.j.newInstance(objArr);
            } catch (InstantiationException e) {
                String valueOf = String.valueOf(this.j);
                StringBuilder sb = new StringBuilder(valueOf.length() + 8);
                sb.append(valueOf);
                sb.append(" failed.");
                throw new RuntimeException(sb.toString(), e);
            }
        }

        public final boolean f() {
            Class<?> declaringClass = this.j.getDeclaringClass();
            if (declaringClass.getEnclosingConstructor() != null) {
                return true;
            }
            Method enclosingMethod = declaringClass.getEnclosingMethod();
            if (enclosingMethod != null) {
                return !Modifier.isStatic(enclosingMethod.getModifiers());
            }
            return (declaringClass.getEnclosingClass() == null || Modifier.isStatic(declaringClass.getModifiers())) ? false : true;
        }

        @Override // java.lang.reflect.GenericDeclaration
        public final TypeVariable<?>[] getTypeParameters() {
            TypeVariable<Class<? super T>>[] typeParameters = getDeclaringClass().getTypeParameters();
            TypeVariable<Constructor<?>>[] typeParameters2 = this.j.getTypeParameters();
            TypeVariable<?>[] typeVariableArr = new TypeVariable[typeParameters.length + typeParameters2.length];
            System.arraycopy(typeParameters, 0, typeVariableArr, 0, typeParameters.length);
            System.arraycopy(typeParameters2, 0, typeVariableArr, typeParameters.length, typeParameters2.length);
            return typeVariableArr;
        }

        @Override // com.google.common.reflect.Invokable
        public final boolean isOverridable() {
            return false;
        }

        @Override // com.google.common.reflect.Invokable
        public final boolean isVarArgs() {
            return this.j.isVarArgs();
        }
    }

    /* loaded from: classes10.dex */
    public static class b<T> extends Invokable<T, Object> {
        public final Method j;

        public b(Method method) {
            super(method);
            this.j = method;
        }

        @Override // com.google.common.reflect.Invokable
        public Type[] a() {
            return this.j.getGenericExceptionTypes();
        }

        @Override // com.google.common.reflect.Invokable
        public Type[] b() {
            return this.j.getGenericParameterTypes();
        }

        @Override // com.google.common.reflect.Invokable
        public Type c() {
            return this.j.getGenericReturnType();
        }

        @Override // com.google.common.reflect.Invokable
        public final Annotation[][] d() {
            return this.j.getParameterAnnotations();
        }

        @Override // com.google.common.reflect.Invokable
        public final Object e(@NullableDecl Object obj, Object[] objArr) throws InvocationTargetException, IllegalAccessException {
            return this.j.invoke(obj, objArr);
        }

        @Override // java.lang.reflect.GenericDeclaration
        public final TypeVariable<?>[] getTypeParameters() {
            return this.j.getTypeParameters();
        }

        @Override // com.google.common.reflect.Invokable
        public final boolean isOverridable() {
            return (isFinal() || isPrivate() || isStatic() || Modifier.isFinal(getDeclaringClass().getModifiers())) ? false : true;
        }

        @Override // com.google.common.reflect.Invokable
        public final boolean isVarArgs() {
            return this.j.isVarArgs();
        }
    }

    public <M extends AccessibleObject & Member> Invokable(M m) {
        super(m);
    }

    public static Invokable<?, Object> from(Method method) {
        return new b(method);
    }

    public abstract Type[] a();

    public abstract Type[] b();

    public abstract Type c();

    public abstract Annotation[][] d();

    public abstract Object e(@NullableDecl Object obj, Object[] objArr) throws InvocationTargetException, IllegalAccessException;

    @Override // com.google.common.reflect.a
    public /* bridge */ /* synthetic */ boolean equals(@NullableDecl Object obj) {
        return super.equals(obj);
    }

    @Override // com.google.common.reflect.a, java.lang.reflect.Member
    public final Class<? super T> getDeclaringClass() {
        return (Class<? super T>) super.getDeclaringClass();
    }

    public final ImmutableList<TypeToken<? extends Throwable>> getExceptionTypes() {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (Type type : a()) {
            builder.add((ImmutableList.Builder) TypeToken.of(type));
        }
        return builder.build();
    }

    @Override // com.google.common.reflect.a
    public TypeToken<T> getOwnerType() {
        return TypeToken.of((Class) getDeclaringClass());
    }

    public final ImmutableList<Parameter> getParameters() {
        Type[] b2 = b();
        Annotation[][] d = d();
        ImmutableList.Builder builder = ImmutableList.builder();
        for (int i = 0; i < b2.length; i++) {
            builder.add((ImmutableList.Builder) new Parameter(this, i, TypeToken.of(b2[i]), d[i]));
        }
        return builder.build();
    }

    public final TypeToken<? extends R> getReturnType() {
        return (TypeToken<? extends R>) TypeToken.of(c());
    }

    @Override // com.google.common.reflect.a
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @CanIgnoreReturnValue
    public final R invoke(@NullableDecl T t, Object... objArr) throws InvocationTargetException, IllegalAccessException {
        return (R) e(t, (Object[]) Preconditions.checkNotNull(objArr));
    }

    public abstract boolean isOverridable();

    public abstract boolean isVarArgs();

    public final <R1 extends R> Invokable<T, R1> returning(Class<R1> cls) {
        return returning(TypeToken.of((Class) cls));
    }

    @Override // com.google.common.reflect.a
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public static <T> Invokable<T, T> from(Constructor<T> constructor) {
        return new a(constructor);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <R1 extends R> Invokable<T, R1> returning(TypeToken<R1> typeToken) {
        if (typeToken.isSupertypeOf(getReturnType())) {
            return this;
        }
        String valueOf = String.valueOf(getReturnType());
        String valueOf2 = String.valueOf(typeToken);
        StringBuilder sb = new StringBuilder(valueOf.length() + 35 + valueOf2.length());
        sb.append("Invokable is known to return ");
        sb.append(valueOf);
        sb.append(", not ");
        sb.append(valueOf2);
        throw new IllegalArgumentException(sb.toString());
    }
}
