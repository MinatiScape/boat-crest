package androidx.transition;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOverlay;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
@RequiresApi(18)
/* loaded from: classes.dex */
public class z implements a0 {

    /* renamed from: a  reason: collision with root package name */
    public final ViewOverlay f1726a;

    public z(@NonNull View view) {
        this.f1726a = view.getOverlay();
    }

    @Override // androidx.transition.a0
    public void add(@NonNull Drawable drawable) {
        this.f1726a.add(drawable);
    }

    @Override // androidx.transition.a0
    public void remove(@NonNull Drawable drawable) {
        this.f1726a.remove(drawable);
    }
}
