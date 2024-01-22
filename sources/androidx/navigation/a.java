package androidx.navigation;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;
/* loaded from: classes.dex */
public class a extends ViewModel {
    public static final ViewModelProvider.Factory b = new C0157a();

    /* renamed from: a  reason: collision with root package name */
    public final HashMap<UUID, ViewModelStore> f1456a = new HashMap<>();

    /* renamed from: androidx.navigation.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0157a implements ViewModelProvider.Factory {
        @Override // androidx.lifecycle.ViewModelProvider.Factory
        @NonNull
        public <T extends ViewModel> T create(@NonNull Class<T> cls) {
            return new a();
        }
    }

    @NonNull
    public static a b(ViewModelStore viewModelStore) {
        return (a) new ViewModelProvider(viewModelStore, b).get(a.class);
    }

    public void a(@NonNull UUID uuid) {
        ViewModelStore remove = this.f1456a.remove(uuid);
        if (remove != null) {
            remove.clear();
        }
    }

    @NonNull
    public ViewModelStore c(@NonNull UUID uuid) {
        ViewModelStore viewModelStore = this.f1456a.get(uuid);
        if (viewModelStore == null) {
            ViewModelStore viewModelStore2 = new ViewModelStore();
            this.f1456a.put(uuid, viewModelStore2);
            return viewModelStore2;
        }
        return viewModelStore;
    }

    @Override // androidx.lifecycle.ViewModel
    public void onCleared() {
        for (ViewModelStore viewModelStore : this.f1456a.values()) {
            viewModelStore.clear();
        }
        this.f1456a.clear();
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder("NavControllerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} ViewModelStores (");
        Iterator<UUID> it = this.f1456a.keySet().iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(HexStringBuilder.COMMENT_END_CHAR);
        return sb.toString();
    }
}
