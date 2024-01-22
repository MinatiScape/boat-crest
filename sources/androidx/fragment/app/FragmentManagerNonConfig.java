package androidx.fragment.app;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelStore;
import java.util.Collection;
import java.util.Map;
@Deprecated
/* loaded from: classes.dex */
public class FragmentManagerNonConfig {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Collection<Fragment> f1316a;
    @Nullable
    public final Map<String, FragmentManagerNonConfig> b;
    @Nullable
    public final Map<String, ViewModelStore> c;

    public FragmentManagerNonConfig(@Nullable Collection<Fragment> collection, @Nullable Map<String, FragmentManagerNonConfig> map, @Nullable Map<String, ViewModelStore> map2) {
        this.f1316a = collection;
        this.b = map;
        this.c = map2;
    }

    @Nullable
    public Map<String, FragmentManagerNonConfig> a() {
        return this.b;
    }

    @Nullable
    public Collection<Fragment> b() {
        return this.f1316a;
    }

    @Nullable
    public Map<String, ViewModelStore> c() {
        return this.c;
    }
}
