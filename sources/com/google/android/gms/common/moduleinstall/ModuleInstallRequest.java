package com.google.android.gms.common.moduleinstall;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.OptionalModuleApi;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
/* loaded from: classes6.dex */
public final class ModuleInstallRequest {

    /* renamed from: a  reason: collision with root package name */
    public final List f8352a;
    @Nullable
    public final InstallStatusListener b;
    @Nullable
    public final Executor c;
    public final boolean d;

    /* loaded from: classes6.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final List f8353a = new ArrayList();
        public boolean b = true;
        @Nullable
        public InstallStatusListener c;
        @Nullable
        public Executor d;

        @NonNull
        @CanIgnoreReturnValue
        public Builder addApi(@NonNull OptionalModuleApi optionalModuleApi) {
            this.f8353a.add(optionalModuleApi);
            return this;
        }

        @NonNull
        public ModuleInstallRequest build() {
            return new ModuleInstallRequest(this.f8353a, this.c, this.d, this.b, null);
        }

        @NonNull
        public Builder setListener(@NonNull InstallStatusListener installStatusListener) {
            return setListener(installStatusListener, null);
        }

        @NonNull
        @CanIgnoreReturnValue
        public Builder setListener(@NonNull InstallStatusListener installStatusListener, @Nullable Executor executor) {
            this.c = installStatusListener;
            this.d = executor;
            return this;
        }
    }

    public /* synthetic */ ModuleInstallRequest(List list, InstallStatusListener installStatusListener, Executor executor, boolean z, zac zacVar) {
        Preconditions.checkNotNull(list, "APIs must not be null.");
        Preconditions.checkArgument(!list.isEmpty(), "APIs must not be empty.");
        if (executor != null) {
            Preconditions.checkNotNull(installStatusListener, "Listener must not be null when listener executor is set.");
        }
        this.f8352a = list;
        this.b = installStatusListener;
        this.c = executor;
        this.d = z;
    }

    @NonNull
    public static Builder newBuilder() {
        return new Builder();
    }

    @NonNull
    public List<OptionalModuleApi> getApis() {
        return this.f8352a;
    }

    @Nullable
    public InstallStatusListener getListener() {
        return this.b;
    }

    @Nullable
    public Executor getListenerExecutor() {
        return this.c;
    }

    @ShowFirstParty
    public final boolean zaa() {
        return this.d;
    }
}
