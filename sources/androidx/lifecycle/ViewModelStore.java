package androidx.lifecycle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
public class ViewModelStore {

    /* renamed from: a  reason: collision with root package name */
    public final HashMap<String, ViewModel> f1374a = new HashMap<>();

    public final ViewModel a(String str) {
        return this.f1374a.get(str);
    }

    public Set<String> b() {
        return new HashSet(this.f1374a.keySet());
    }

    public final void c(String str, ViewModel viewModel) {
        ViewModel put = this.f1374a.put(str, viewModel);
        if (put != null) {
            put.onCleared();
        }
    }

    public final void clear() {
        for (ViewModel viewModel : this.f1374a.values()) {
            viewModel.clear();
        }
        this.f1374a.clear();
    }
}
