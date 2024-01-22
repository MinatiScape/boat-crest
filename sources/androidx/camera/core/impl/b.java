package androidx.camera.core.impl;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.impl.Config;
import java.util.Objects;
/* loaded from: classes.dex */
public final class b<T> extends Config.Option<T> {

    /* renamed from: a  reason: collision with root package name */
    public final String f721a;
    public final Class<T> b;
    public final Object c;

    public b(String str, Class<T> cls, @Nullable Object obj) {
        Objects.requireNonNull(str, "Null id");
        this.f721a = str;
        Objects.requireNonNull(cls, "Null valueClass");
        this.b = cls;
        this.c = obj;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Config.Option) {
            Config.Option option = (Config.Option) obj;
            if (this.f721a.equals(option.getId()) && this.b.equals(option.getValueClass())) {
                Object obj2 = this.c;
                if (obj2 == null) {
                    if (option.getToken() == null) {
                        return true;
                    }
                } else if (obj2.equals(option.getToken())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override // androidx.camera.core.impl.Config.Option
    @NonNull
    public String getId() {
        return this.f721a;
    }

    @Override // androidx.camera.core.impl.Config.Option
    @Nullable
    public Object getToken() {
        return this.c;
    }

    @Override // androidx.camera.core.impl.Config.Option
    @NonNull
    public Class<T> getValueClass() {
        return this.b;
    }

    public int hashCode() {
        int hashCode = (((this.f721a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003;
        Object obj = this.c;
        return hashCode ^ (obj == null ? 0 : obj.hashCode());
    }

    public String toString() {
        return "Option{id=" + this.f721a + ", valueClass=" + this.b + ", token=" + this.c + "}";
    }
}
