package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import java.lang.ref.WeakReference;
/* loaded from: classes.dex */
public class s extends p {
    public final WeakReference<Context> b;

    public s(@NonNull Context context, @NonNull Resources resources) {
        super(resources);
        this.b = new WeakReference<>(context);
    }

    @Override // androidx.appcompat.widget.p, android.content.res.Resources
    public Drawable getDrawable(int i) throws Resources.NotFoundException {
        Drawable a2 = a(i);
        Context context = this.b.get();
        if (a2 != null && context != null) {
            ResourceManagerInternal.get().s(context, i, a2);
        }
        return a2;
    }
}
