package com.google.android.datatransport.runtime.backends;

import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.datatransport.runtime.time.Clock;
import java.util.Objects;
/* loaded from: classes6.dex */
public final class c extends CreationContext {

    /* renamed from: a  reason: collision with root package name */
    public final Context f8090a;
    public final Clock b;
    public final Clock c;
    public final String d;

    public c(Context context, Clock clock, Clock clock2, String str) {
        Objects.requireNonNull(context, "Null applicationContext");
        this.f8090a = context;
        Objects.requireNonNull(clock, "Null wallClock");
        this.b = clock;
        Objects.requireNonNull(clock2, "Null monotonicClock");
        this.c = clock2;
        Objects.requireNonNull(str, "Null backendName");
        this.d = str;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CreationContext) {
            CreationContext creationContext = (CreationContext) obj;
            return this.f8090a.equals(creationContext.getApplicationContext()) && this.b.equals(creationContext.getWallClock()) && this.c.equals(creationContext.getMonotonicClock()) && this.d.equals(creationContext.getBackendName());
        }
        return false;
    }

    @Override // com.google.android.datatransport.runtime.backends.CreationContext
    public Context getApplicationContext() {
        return this.f8090a;
    }

    @Override // com.google.android.datatransport.runtime.backends.CreationContext
    @NonNull
    public String getBackendName() {
        return this.d;
    }

    @Override // com.google.android.datatransport.runtime.backends.CreationContext
    public Clock getMonotonicClock() {
        return this.c;
    }

    @Override // com.google.android.datatransport.runtime.backends.CreationContext
    public Clock getWallClock() {
        return this.b;
    }

    public int hashCode() {
        return ((((((this.f8090a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d.hashCode();
    }

    public String toString() {
        return "CreationContext{applicationContext=" + this.f8090a + ", wallClock=" + this.b + ", monotonicClock=" + this.c + ", backendName=" + this.d + "}";
    }
}
