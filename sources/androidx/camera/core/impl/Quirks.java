package androidx.camera.core.impl;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
public class Quirks {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final List<Quirk> f711a;

    public Quirks(@NonNull List<Quirk> list) {
        this.f711a = new ArrayList(list);
    }

    public boolean contains(@NonNull Class<? extends Quirk> cls) {
        for (Quirk quirk : this.f711a) {
            if (cls.isAssignableFrom(quirk.getClass())) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public <T extends Quirk> T get(@NonNull Class<T> cls) {
        Iterator<Quirk> it = this.f711a.iterator();
        while (it.hasNext()) {
            T t = (T) it.next();
            if (t.getClass() == cls) {
                return t;
            }
        }
        return null;
    }
}
