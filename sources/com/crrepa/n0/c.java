package com.crrepa.n0;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
/* loaded from: classes9.dex */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    public final Field f7773a;

    public c(Field field) {
        com.crrepa.p0.a.a(field);
        this.f7773a = field;
    }

    public <T extends Annotation> T a(Class<T> cls) {
        return (T) this.f7773a.getAnnotation(cls);
    }

    public Collection<Annotation> a() {
        return Arrays.asList(this.f7773a.getAnnotations());
    }

    public boolean a(int i) {
        return (i & this.f7773a.getModifiers()) != 0;
    }

    public Class<?> b() {
        return this.f7773a.getType();
    }

    public Type c() {
        return this.f7773a.getGenericType();
    }

    public Class<?> d() {
        return this.f7773a.getDeclaringClass();
    }

    public String e() {
        return this.f7773a.getName();
    }
}
