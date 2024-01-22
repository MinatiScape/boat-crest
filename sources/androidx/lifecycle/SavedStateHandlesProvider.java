package androidx.lifecycle;

import android.os.Bundle;
import androidx.savedstate.SavedStateRegistry;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class SavedStateHandlesProvider implements SavedStateRegistry.SavedStateProvider {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final SavedStateRegistry f1362a;
    public boolean b;
    @Nullable
    public Bundle c;
    @NotNull
    public final Lazy d;

    /* loaded from: classes.dex */
    public static final class a extends Lambda implements Function0<SavedStateHandlesVM> {
        public final /* synthetic */ ViewModelStoreOwner $viewModelStoreOwner;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(ViewModelStoreOwner viewModelStoreOwner) {
            super(0);
            this.$viewModelStoreOwner = viewModelStoreOwner;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final SavedStateHandlesVM invoke() {
            return SavedStateHandleSupport.getSavedStateHandlesVM(this.$viewModelStoreOwner);
        }
    }

    public SavedStateHandlesProvider(@NotNull SavedStateRegistry savedStateRegistry, @NotNull ViewModelStoreOwner viewModelStoreOwner) {
        Intrinsics.checkNotNullParameter(savedStateRegistry, "savedStateRegistry");
        Intrinsics.checkNotNullParameter(viewModelStoreOwner, "viewModelStoreOwner");
        this.f1362a = savedStateRegistry;
        this.d = LazyKt__LazyJVMKt.lazy(new a(viewModelStoreOwner));
    }

    public final SavedStateHandlesVM a() {
        return (SavedStateHandlesVM) this.d.getValue();
    }

    @Nullable
    public final Bundle consumeRestoredStateForKey(@NotNull String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        performRestore();
        Bundle bundle = this.c;
        Bundle bundle2 = bundle != null ? bundle.getBundle(key) : null;
        Bundle bundle3 = this.c;
        if (bundle3 != null) {
            bundle3.remove(key);
        }
        Bundle bundle4 = this.c;
        boolean z = true;
        if (bundle4 == null || !bundle4.isEmpty()) {
            z = false;
        }
        if (z) {
            this.c = null;
        }
        return bundle2;
    }

    public final void performRestore() {
        if (this.b) {
            return;
        }
        this.c = this.f1362a.consumeRestoredStateForKey("androidx.lifecycle.internal.SavedStateHandlesProvider");
        this.b = true;
        a();
    }

    @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
    @NotNull
    public Bundle saveState() {
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.c;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
        for (Map.Entry<String, SavedStateHandle> entry : a().getHandles().entrySet()) {
            String key = entry.getKey();
            Bundle saveState = entry.getValue().savedStateProvider().saveState();
            if (!Intrinsics.areEqual(saveState, Bundle.EMPTY)) {
                bundle.putBundle(key, saveState);
            }
        }
        this.b = false;
        return bundle;
    }
}
