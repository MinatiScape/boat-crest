package com.google.android.gms.common.api.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
@KeepForSdk
/* loaded from: classes6.dex */
public class RegistrationMethods<A extends Api.AnyClient, L> {
    @NonNull
    @KeepForSdk
    public final RegisterListenerMethod<A, L> register;
    @NonNull
    public final UnregisterListenerMethod zaa;
    @NonNull
    public final Runnable zab;

    @KeepForSdk
    /* loaded from: classes6.dex */
    public static class Builder<A extends Api.AnyClient, L> {

        /* renamed from: a  reason: collision with root package name */
        public RemoteCall f8262a;
        public RemoteCall b;
        public ListenerHolder d;
        public Feature[] e;
        public int g;
        public Runnable c = zacj.zaa;
        public boolean f = true;

        public Builder() {
        }

        public /* synthetic */ Builder(zacm zacmVar) {
        }

        @NonNull
        @KeepForSdk
        public RegistrationMethods<A, L> build() {
            Preconditions.checkArgument(this.f8262a != null, "Must set register function");
            Preconditions.checkArgument(this.b != null, "Must set unregister function");
            Preconditions.checkArgument(this.d != null, "Must set holder");
            return new RegistrationMethods<>(new j0(this, this.d, this.e, this.f, this.g), new k0(this, (ListenerHolder.ListenerKey) Preconditions.checkNotNull(this.d.getListenerKey(), "Key must not be null")), this.c, null);
        }

        @NonNull
        @CanIgnoreReturnValue
        @KeepForSdk
        public Builder<A, L> onConnectionSuspended(@NonNull Runnable runnable) {
            this.c = runnable;
            return this;
        }

        @NonNull
        @CanIgnoreReturnValue
        @KeepForSdk
        public Builder<A, L> register(@NonNull RemoteCall<A, TaskCompletionSource<Void>> remoteCall) {
            this.f8262a = remoteCall;
            return this;
        }

        @NonNull
        @CanIgnoreReturnValue
        @KeepForSdk
        public Builder<A, L> setAutoResolveMissingFeatures(boolean z) {
            this.f = z;
            return this;
        }

        @NonNull
        @CanIgnoreReturnValue
        @KeepForSdk
        public Builder<A, L> setFeatures(@NonNull Feature... featureArr) {
            this.e = featureArr;
            return this;
        }

        @NonNull
        @CanIgnoreReturnValue
        @KeepForSdk
        public Builder<A, L> setMethodKey(int i) {
            this.g = i;
            return this;
        }

        @NonNull
        @CanIgnoreReturnValue
        @KeepForSdk
        public Builder<A, L> unregister(@NonNull RemoteCall<A, TaskCompletionSource<Boolean>> remoteCall) {
            this.b = remoteCall;
            return this;
        }

        @NonNull
        @CanIgnoreReturnValue
        @KeepForSdk
        public Builder<A, L> withHolder(@NonNull ListenerHolder<L> listenerHolder) {
            this.d = listenerHolder;
            return this;
        }
    }

    public /* synthetic */ RegistrationMethods(RegisterListenerMethod registerListenerMethod, UnregisterListenerMethod unregisterListenerMethod, Runnable runnable, zacn zacnVar) {
        this.register = registerListenerMethod;
        this.zaa = unregisterListenerMethod;
        this.zab = runnable;
    }

    @NonNull
    @KeepForSdk
    public static <A extends Api.AnyClient, L> Builder<A, L> builder() {
        return new Builder<>(null);
    }
}
