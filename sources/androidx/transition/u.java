package androidx.transition;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
@RequiresApi(18)
/* loaded from: classes.dex */
public class u implements v {

    /* renamed from: a  reason: collision with root package name */
    public final ViewGroupOverlay f1722a;

    public u(@NonNull ViewGroup viewGroup) {
        this.f1722a = viewGroup.getOverlay();
    }

    @Override // androidx.transition.v
    public void a(@NonNull View view) {
        this.f1722a.add(view);
    }

    @Override // androidx.transition.a0
    public void add(@NonNull Drawable drawable) {
        this.f1722a.add(drawable);
    }

    @Override // androidx.transition.v
    public void b(@NonNull View view) {
        this.f1722a.remove(view);
    }

    @Override // androidx.transition.a0
    public void remove(@NonNull Drawable drawable) {
        this.f1722a.remove(drawable);
    }
}
