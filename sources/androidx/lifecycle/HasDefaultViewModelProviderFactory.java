package androidx.lifecycle;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;
/* loaded from: classes.dex */
public interface HasDefaultViewModelProviderFactory {
    @NonNull
    default CreationExtras getDefaultViewModelCreationExtras() {
        return CreationExtras.Empty.INSTANCE;
    }

    @NonNull
    ViewModelProvider.Factory getDefaultViewModelProviderFactory();
}
