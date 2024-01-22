package androidx.fragment.app;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes.dex */
public final class g extends ViewModel {
    public static final ViewModelProvider.Factory h = new a();
    public final boolean d;

    /* renamed from: a  reason: collision with root package name */
    public final HashMap<String, Fragment> f1329a = new HashMap<>();
    public final HashMap<String, g> b = new HashMap<>();
    public final HashMap<String, ViewModelStore> c = new HashMap<>();
    public boolean e = false;
    public boolean f = false;
    public boolean g = false;

    /* loaded from: classes.dex */
    public class a implements ViewModelProvider.Factory {
        @Override // androidx.lifecycle.ViewModelProvider.Factory
        @NonNull
        public <T extends ViewModel> T create(@NonNull Class<T> cls) {
            return new g(true);
        }
    }

    public g(boolean z) {
        this.d = z;
    }

    @NonNull
    public static g e(ViewModelStore viewModelStore) {
        return (g) new ViewModelProvider(viewModelStore, h).get(g.class);
    }

    public void a(@NonNull Fragment fragment) {
        if (this.g) {
            if (FragmentManager.x0(2)) {
                Log.v("FragmentManager", "Ignoring addRetainedFragment as the state is already saved");
            }
        } else if (this.f1329a.containsKey(fragment.mWho)) {
        } else {
            this.f1329a.put(fragment.mWho, fragment);
            if (FragmentManager.x0(2)) {
                Log.v("FragmentManager", "Updating retained Fragments: Added " + fragment);
            }
        }
    }

    public void b(@NonNull Fragment fragment) {
        if (FragmentManager.x0(3)) {
            Log.d("FragmentManager", "Clearing non-config state for " + fragment);
        }
        g gVar = this.b.get(fragment.mWho);
        if (gVar != null) {
            gVar.onCleared();
            this.b.remove(fragment.mWho);
        }
        ViewModelStore viewModelStore = this.c.get(fragment.mWho);
        if (viewModelStore != null) {
            viewModelStore.clear();
            this.c.remove(fragment.mWho);
        }
    }

    @Nullable
    public Fragment c(String str) {
        return this.f1329a.get(str);
    }

    @NonNull
    public g d(@NonNull Fragment fragment) {
        g gVar = this.b.get(fragment.mWho);
        if (gVar == null) {
            g gVar2 = new g(this.d);
            this.b.put(fragment.mWho, gVar2);
            return gVar2;
        }
        return gVar;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || g.class != obj.getClass()) {
            return false;
        }
        g gVar = (g) obj;
        return this.f1329a.equals(gVar.f1329a) && this.b.equals(gVar.b) && this.c.equals(gVar.c);
    }

    @NonNull
    public Collection<Fragment> h() {
        return new ArrayList(this.f1329a.values());
    }

    public int hashCode() {
        return (((this.f1329a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
    }

    @Nullable
    @Deprecated
    public FragmentManagerNonConfig i() {
        if (this.f1329a.isEmpty() && this.b.isEmpty() && this.c.isEmpty()) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, g> entry : this.b.entrySet()) {
            FragmentManagerNonConfig i = entry.getValue().i();
            if (i != null) {
                hashMap.put(entry.getKey(), i);
            }
        }
        this.f = true;
        if (this.f1329a.isEmpty() && hashMap.isEmpty() && this.c.isEmpty()) {
            return null;
        }
        return new FragmentManagerNonConfig(new ArrayList(this.f1329a.values()), hashMap, new HashMap(this.c));
    }

    @NonNull
    public ViewModelStore j(@NonNull Fragment fragment) {
        ViewModelStore viewModelStore = this.c.get(fragment.mWho);
        if (viewModelStore == null) {
            ViewModelStore viewModelStore2 = new ViewModelStore();
            this.c.put(fragment.mWho, viewModelStore2);
            return viewModelStore2;
        }
        return viewModelStore;
    }

    public boolean k() {
        return this.e;
    }

    public void l(@NonNull Fragment fragment) {
        if (this.g) {
            if (FragmentManager.x0(2)) {
                Log.v("FragmentManager", "Ignoring removeRetainedFragment as the state is already saved");
                return;
            }
            return;
        }
        if ((this.f1329a.remove(fragment.mWho) != null) && FragmentManager.x0(2)) {
            Log.v("FragmentManager", "Updating retained Fragments: Removed " + fragment);
        }
    }

    @Deprecated
    public void m(@Nullable FragmentManagerNonConfig fragmentManagerNonConfig) {
        this.f1329a.clear();
        this.b.clear();
        this.c.clear();
        if (fragmentManagerNonConfig != null) {
            Collection<Fragment> b = fragmentManagerNonConfig.b();
            if (b != null) {
                for (Fragment fragment : b) {
                    if (fragment != null) {
                        this.f1329a.put(fragment.mWho, fragment);
                    }
                }
            }
            Map<String, FragmentManagerNonConfig> a2 = fragmentManagerNonConfig.a();
            if (a2 != null) {
                for (Map.Entry<String, FragmentManagerNonConfig> entry : a2.entrySet()) {
                    g gVar = new g(this.d);
                    gVar.m(entry.getValue());
                    this.b.put(entry.getKey(), gVar);
                }
            }
            Map<String, ViewModelStore> c = fragmentManagerNonConfig.c();
            if (c != null) {
                this.c.putAll(c);
            }
        }
        this.f = false;
    }

    public void n(boolean z) {
        this.g = z;
    }

    public boolean o(@NonNull Fragment fragment) {
        if (this.f1329a.containsKey(fragment.mWho)) {
            if (this.d) {
                return this.e;
            }
            return !this.f;
        }
        return true;
    }

    @Override // androidx.lifecycle.ViewModel
    public void onCleared() {
        if (FragmentManager.x0(3)) {
            Log.d("FragmentManager", "onCleared called for " + this);
        }
        this.e = true;
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder("FragmentManagerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} Fragments (");
        Iterator<Fragment> it = this.f1329a.values().iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") Child Non Config (");
        Iterator<String> it2 = this.b.keySet().iterator();
        while (it2.hasNext()) {
            sb.append(it2.next());
            if (it2.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") ViewModelStores (");
        Iterator<String> it3 = this.c.keySet().iterator();
        while (it3.hasNext()) {
            sb.append(it3.next());
            if (it3.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(HexStringBuilder.COMMENT_END_CHAR);
        return sb.toString();
    }
}
