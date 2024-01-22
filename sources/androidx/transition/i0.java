package androidx.transition;

import android.os.IBinder;
/* loaded from: classes.dex */
public class i0 implements k0 {

    /* renamed from: a  reason: collision with root package name */
    public final IBinder f1713a;

    public i0(IBinder iBinder) {
        this.f1713a = iBinder;
    }

    public boolean equals(Object obj) {
        return (obj instanceof i0) && ((i0) obj).f1713a.equals(this.f1713a);
    }

    public int hashCode() {
        return this.f1713a.hashCode();
    }
}
