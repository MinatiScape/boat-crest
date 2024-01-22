package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
/* loaded from: classes10.dex */
public abstract class TypeParameter<T> extends b<T> {

    /* renamed from: a  reason: collision with root package name */
    public final TypeVariable<?> f10729a;

    public TypeParameter() {
        Type capture = capture();
        Preconditions.checkArgument(capture instanceof TypeVariable, "%s should be a type variable.", capture);
        this.f10729a = (TypeVariable) capture;
    }

    public final boolean equals(@NullableDecl Object obj) {
        if (obj instanceof TypeParameter) {
            return this.f10729a.equals(((TypeParameter) obj).f10729a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f10729a.hashCode();
    }

    public String toString() {
        return this.f10729a.toString();
    }
}
