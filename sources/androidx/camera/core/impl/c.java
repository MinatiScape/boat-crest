package androidx.camera.core.impl;

import androidx.annotation.NonNull;
import java.util.Objects;
/* loaded from: classes.dex */
public final class c extends DeviceProperties {

    /* renamed from: a  reason: collision with root package name */
    public final String f722a;
    public final String b;
    public final int c;

    public c(String str, String str2, int i) {
        Objects.requireNonNull(str, "Null manufacturer");
        this.f722a = str;
        Objects.requireNonNull(str2, "Null model");
        this.b = str2;
        this.c = i;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DeviceProperties) {
            DeviceProperties deviceProperties = (DeviceProperties) obj;
            return this.f722a.equals(deviceProperties.manufacturer()) && this.b.equals(deviceProperties.model()) && this.c == deviceProperties.sdkVersion();
        }
        return false;
    }

    public int hashCode() {
        return ((((this.f722a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c;
    }

    @Override // androidx.camera.core.impl.DeviceProperties
    @NonNull
    public String manufacturer() {
        return this.f722a;
    }

    @Override // androidx.camera.core.impl.DeviceProperties
    @NonNull
    public String model() {
        return this.b;
    }

    @Override // androidx.camera.core.impl.DeviceProperties
    public int sdkVersion() {
        return this.c;
    }

    public String toString() {
        return "DeviceProperties{manufacturer=" + this.f722a + ", model=" + this.b + ", sdkVersion=" + this.c + "}";
    }
}
