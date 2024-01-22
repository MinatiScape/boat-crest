package androidx.work.impl.constraints;

import androidx.annotation.NonNull;
/* loaded from: classes.dex */
public class NetworkState {

    /* renamed from: a  reason: collision with root package name */
    public boolean f1811a;
    public boolean b;
    public boolean c;
    public boolean d;

    public NetworkState(boolean z, boolean z2, boolean z3, boolean z4) {
        this.f1811a = z;
        this.b = z2;
        this.c = z3;
        this.d = z4;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof NetworkState) {
            NetworkState networkState = (NetworkState) obj;
            return this.f1811a == networkState.f1811a && this.b == networkState.b && this.c == networkState.c && this.d == networkState.d;
        }
        return false;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [boolean, int] */
    public int hashCode() {
        ?? r0 = this.f1811a;
        int i = r0;
        if (this.b) {
            i = r0 + 16;
        }
        int i2 = i;
        if (this.c) {
            i2 = i + 256;
        }
        return this.d ? i2 + 4096 : i2;
    }

    public boolean isConnected() {
        return this.f1811a;
    }

    public boolean isMetered() {
        return this.c;
    }

    public boolean isNotRoaming() {
        return this.d;
    }

    public boolean isValidated() {
        return this.b;
    }

    @NonNull
    public String toString() {
        return String.format("[ Connected=%b Validated=%b Metered=%b NotRoaming=%b ]", Boolean.valueOf(this.f1811a), Boolean.valueOf(this.b), Boolean.valueOf(this.c), Boolean.valueOf(this.d));
    }
}
