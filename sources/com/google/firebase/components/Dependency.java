package com.google.firebase.components;

import com.google.android.gms.fido.fido2.api.common.DevicePublicKeyStringDef;
/* loaded from: classes10.dex */
public final class Dependency {

    /* renamed from: a  reason: collision with root package name */
    public final Class<?> f11095a;
    public final int b;
    public final int c;

    public Dependency(Class<?> cls, int i, int i2) {
        this.f11095a = (Class) Preconditions.checkNotNull(cls, "Null dependency anInterface.");
        this.b = i;
        this.c = i2;
    }

    public static String a(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i == 2) {
                    return "deferred";
                }
                throw new AssertionError("Unsupported injection: " + i);
            }
            return "provider";
        }
        return DevicePublicKeyStringDef.DIRECT;
    }

    public static Dependency deferred(Class<?> cls) {
        return new Dependency(cls, 0, 2);
    }

    @Deprecated
    public static Dependency optional(Class<?> cls) {
        return new Dependency(cls, 0, 0);
    }

    public static Dependency optionalProvider(Class<?> cls) {
        return new Dependency(cls, 0, 1);
    }

    public static Dependency required(Class<?> cls) {
        return new Dependency(cls, 1, 0);
    }

    public static Dependency requiredProvider(Class<?> cls) {
        return new Dependency(cls, 1, 1);
    }

    public static Dependency setOf(Class<?> cls) {
        return new Dependency(cls, 2, 0);
    }

    public static Dependency setOfProvider(Class<?> cls) {
        return new Dependency(cls, 2, 1);
    }

    public boolean equals(Object obj) {
        if (obj instanceof Dependency) {
            Dependency dependency = (Dependency) obj;
            return this.f11095a == dependency.f11095a && this.b == dependency.b && this.c == dependency.c;
        }
        return false;
    }

    public Class<?> getInterface() {
        return this.f11095a;
    }

    public int hashCode() {
        return ((((this.f11095a.hashCode() ^ 1000003) * 1000003) ^ this.b) * 1000003) ^ this.c;
    }

    public boolean isDeferred() {
        return this.c == 2;
    }

    public boolean isDirectInjection() {
        return this.c == 0;
    }

    public boolean isRequired() {
        return this.b == 1;
    }

    public boolean isSet() {
        return this.b == 2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Dependency{anInterface=");
        sb.append(this.f11095a);
        sb.append(", type=");
        int i = this.b;
        sb.append(i == 1 ? "required" : i == 0 ? "optional" : "set");
        sb.append(", injection=");
        sb.append(a(this.c));
        sb.append("}");
        return sb.toString();
    }
}
