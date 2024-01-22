package androidx.transition;

import android.view.View;
import android.view.WindowId;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
@RequiresApi(18)
/* loaded from: classes.dex */
public class j0 implements k0 {

    /* renamed from: a  reason: collision with root package name */
    public final WindowId f1715a;

    public j0(@NonNull View view) {
        this.f1715a = view.getWindowId();
    }

    public boolean equals(Object obj) {
        return (obj instanceof j0) && ((j0) obj).f1715a.equals(this.f1715a);
    }

    public int hashCode() {
        return this.f1715a.hashCode();
    }
}
