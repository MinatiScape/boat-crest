package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.BiConsumer;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
@KeepForSdk
/* loaded from: classes6.dex */
public abstract class TaskApiCall<A extends Api.AnyClient, ResultT> {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Feature[] f8264a;
    public final boolean b;
    public final int c;

    @KeepForSdk
    /* loaded from: classes6.dex */
    public static class Builder<A extends Api.AnyClient, ResultT> {

        /* renamed from: a  reason: collision with root package name */
        public RemoteCall f8265a;
        public Feature[] c;
        public boolean b = true;
        public int d = 0;

        public Builder() {
        }

        public /* synthetic */ Builder(zacw zacwVar) {
        }

        @NonNull
        @KeepForSdk
        public TaskApiCall<A, ResultT> build() {
            Preconditions.checkArgument(this.f8265a != null, "execute parameter required");
            return new n0(this, this.c, this.b, this.d);
        }

        @NonNull
        @Deprecated
        @CanIgnoreReturnValue
        @KeepForSdk
        public Builder<A, ResultT> execute(@NonNull final BiConsumer<A, TaskCompletionSource<ResultT>> biConsumer) {
            this.f8265a = new RemoteCall() { // from class: com.google.android.gms.common.api.internal.zacu
                @Override // com.google.android.gms.common.api.internal.RemoteCall
                public final void accept(Object obj, Object obj2) {
                    BiConsumer.this.accept((Api.AnyClient) obj, (TaskCompletionSource) obj2);
                }
            };
            return this;
        }

        @NonNull
        @CanIgnoreReturnValue
        @KeepForSdk
        public Builder<A, ResultT> run(@NonNull RemoteCall<A, TaskCompletionSource<ResultT>> remoteCall) {
            this.f8265a = remoteCall;
            return this;
        }

        @NonNull
        @CanIgnoreReturnValue
        @KeepForSdk
        public Builder<A, ResultT> setAutoResolveMissingFeatures(boolean z) {
            this.b = z;
            return this;
        }

        @NonNull
        @CanIgnoreReturnValue
        @KeepForSdk
        public Builder<A, ResultT> setFeatures(@NonNull Feature... featureArr) {
            this.c = featureArr;
            return this;
        }

        @NonNull
        @CanIgnoreReturnValue
        @KeepForSdk
        public Builder<A, ResultT> setMethodKey(int i) {
            this.d = i;
            return this;
        }
    }

    @KeepForSdk
    @Deprecated
    public TaskApiCall() {
        this.f8264a = null;
        this.b = false;
        this.c = 0;
    }

    @KeepForSdk
    public TaskApiCall(@Nullable Feature[] featureArr, boolean z, int i) {
        this.f8264a = featureArr;
        boolean z2 = false;
        if (featureArr != null && z) {
            z2 = true;
        }
        this.b = z2;
        this.c = i;
    }

    @NonNull
    @KeepForSdk
    public static <A extends Api.AnyClient, ResultT> Builder<A, ResultT> builder() {
        return new Builder<>(null);
    }

    @KeepForSdk
    public abstract void doExecute(@NonNull A a2, @NonNull TaskCompletionSource<ResultT> taskCompletionSource) throws RemoteException;

    @KeepForSdk
    public boolean shouldAutoResolveMissingFeatures() {
        return this.b;
    }

    public final int zaa() {
        return this.c;
    }

    @Nullable
    public final Feature[] zab() {
        return this.f8264a;
    }
}
