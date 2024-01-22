package com.airbnb.lottie.model;

import androidx.annotation.CheckResult;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes.dex */
public class KeyPath {
    public static final KeyPath COMPOSITION = new KeyPath("COMPOSITION");

    /* renamed from: a  reason: collision with root package name */
    public final List<String> f2026a;
    @Nullable
    public KeyPathElement b;

    public KeyPath(String... strArr) {
        this.f2026a = Arrays.asList(strArr);
    }

    public final boolean a() {
        List<String> list = this.f2026a;
        return list.get(list.size() - 1).equals("**");
    }

    @CheckResult
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public KeyPath addKey(String str) {
        KeyPath keyPath = new KeyPath(this);
        keyPath.f2026a.add(str);
        return keyPath;
    }

    public final boolean b(String str) {
        return "__container".equals(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        KeyPath keyPath = (KeyPath) obj;
        if (this.f2026a.equals(keyPath.f2026a)) {
            KeyPathElement keyPathElement = this.b;
            KeyPathElement keyPathElement2 = keyPath.b;
            return keyPathElement != null ? keyPathElement.equals(keyPathElement2) : keyPathElement2 == null;
        }
        return false;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public boolean fullyResolvesTo(String str, int i) {
        if (i >= this.f2026a.size()) {
            return false;
        }
        boolean z = i == this.f2026a.size() - 1;
        String str2 = this.f2026a.get(i);
        if (!str2.equals("**")) {
            return (z || (i == this.f2026a.size() + (-2) && a())) && (str2.equals(str) || str2.equals(org.slf4j.Marker.ANY_MARKER));
        }
        if (!z && this.f2026a.get(i + 1).equals(str)) {
            return i == this.f2026a.size() + (-2) || (i == this.f2026a.size() + (-3) && a());
        } else if (z) {
            return true;
        } else {
            int i2 = i + 1;
            if (i2 < this.f2026a.size() - 1) {
                return false;
            }
            return this.f2026a.get(i2).equals(str);
        }
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public KeyPathElement getResolvedElement() {
        return this.b;
    }

    public int hashCode() {
        int hashCode = this.f2026a.hashCode() * 31;
        KeyPathElement keyPathElement = this.b;
        return hashCode + (keyPathElement != null ? keyPathElement.hashCode() : 0);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public int incrementDepthBy(String str, int i) {
        if (b(str)) {
            return 0;
        }
        if (this.f2026a.get(i).equals("**")) {
            return (i != this.f2026a.size() - 1 && this.f2026a.get(i + 1).equals(str)) ? 2 : 0;
        }
        return 1;
    }

    public String keysToString() {
        return this.f2026a.toString();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public boolean matches(String str, int i) {
        if (b(str)) {
            return true;
        }
        if (i >= this.f2026a.size()) {
            return false;
        }
        return this.f2026a.get(i).equals(str) || this.f2026a.get(i).equals("**") || this.f2026a.get(i).equals(org.slf4j.Marker.ANY_MARKER);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public boolean propagateToChildren(String str, int i) {
        return "__container".equals(str) || i < this.f2026a.size() - 1 || this.f2026a.get(i).equals("**");
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public KeyPath resolve(KeyPathElement keyPathElement) {
        KeyPath keyPath = new KeyPath(this);
        keyPath.b = keyPathElement;
        return keyPath;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("KeyPath{keys=");
        sb.append(this.f2026a);
        sb.append(",resolved=");
        sb.append(this.b != null);
        sb.append('}');
        return sb.toString();
    }

    public KeyPath(KeyPath keyPath) {
        this.f2026a = new ArrayList(keyPath.f2026a);
        this.b = keyPath.b;
    }
}
