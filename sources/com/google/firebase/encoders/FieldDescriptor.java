package com.google.firebase.encoders;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes10.dex */
public final class FieldDescriptor {

    /* renamed from: a  reason: collision with root package name */
    public final String f11268a;
    public final Map<Class<?>, Object> b;

    /* loaded from: classes10.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final String f11269a;
        public Map<Class<?>, Object> b = null;

        public Builder(String str) {
            this.f11269a = str;
        }

        @NonNull
        public FieldDescriptor build() {
            Map unmodifiableMap;
            String str = this.f11269a;
            if (this.b == null) {
                unmodifiableMap = Collections.emptyMap();
            } else {
                unmodifiableMap = Collections.unmodifiableMap(new HashMap(this.b));
            }
            return new FieldDescriptor(str, unmodifiableMap);
        }

        @NonNull
        public <T extends Annotation> Builder withProperty(@NonNull T t) {
            if (this.b == null) {
                this.b = new HashMap();
            }
            this.b.put(t.annotationType(), t);
            return this;
        }
    }

    @NonNull
    public static Builder builder(@NonNull String str) {
        return new Builder(str);
    }

    @NonNull
    public static FieldDescriptor of(@NonNull String str) {
        return new FieldDescriptor(str, Collections.emptyMap());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FieldDescriptor) {
            FieldDescriptor fieldDescriptor = (FieldDescriptor) obj;
            return this.f11268a.equals(fieldDescriptor.f11268a) && this.b.equals(fieldDescriptor.b);
        }
        return false;
    }

    @NonNull
    public String getName() {
        return this.f11268a;
    }

    @Nullable
    public <T extends Annotation> T getProperty(@NonNull Class<T> cls) {
        return (T) this.b.get(cls);
    }

    public int hashCode() {
        return (this.f11268a.hashCode() * 31) + this.b.hashCode();
    }

    @NonNull
    public String toString() {
        return "FieldDescriptor{name=" + this.f11268a + ", properties=" + this.b.values() + "}";
    }

    public FieldDescriptor(String str, Map<Class<?>, Object> map) {
        this.f11268a = str;
        this.b = map;
    }
}
