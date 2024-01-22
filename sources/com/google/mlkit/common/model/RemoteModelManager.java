package com.google.mlkit.common.model;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.inject.Provider;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.model.RemoteModelManagerInterface;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/* loaded from: classes10.dex */
public class RemoteModelManager {

    /* renamed from: a  reason: collision with root package name */
    public final Map f11580a = new HashMap();

    @KeepForSdk
    /* loaded from: classes10.dex */
    public static class RemoteModelManagerRegistration {

        /* renamed from: a  reason: collision with root package name */
        public final Class f11581a;
        public final Provider b;

        @KeepForSdk
        public <RemoteT extends RemoteModel> RemoteModelManagerRegistration(@NonNull Class<RemoteT> cls, @NonNull Provider<? extends RemoteModelManagerInterface<RemoteT>> provider) {
            this.f11581a = cls;
            this.b = provider;
        }

        public final Provider a() {
            return this.b;
        }

        public final Class b() {
            return this.f11581a;
        }
    }

    @KeepForSdk
    public RemoteModelManager(@NonNull Set<RemoteModelManagerRegistration> set) {
        for (RemoteModelManagerRegistration remoteModelManagerRegistration : set) {
            this.f11580a.put(remoteModelManagerRegistration.b(), remoteModelManagerRegistration.a());
        }
    }

    @NonNull
    public static synchronized RemoteModelManager getInstance() {
        RemoteModelManager remoteModelManager;
        synchronized (RemoteModelManager.class) {
            remoteModelManager = (RemoteModelManager) MlKitContext.getInstance().get(RemoteModelManager.class);
        }
        return remoteModelManager;
    }

    public final RemoteModelManagerInterface a(Class cls) {
        return (RemoteModelManagerInterface) ((Provider) Preconditions.checkNotNull((Provider) this.f11580a.get(cls))).get();
    }

    @NonNull
    public Task<Void> deleteDownloadedModel(@NonNull RemoteModel remoteModel) {
        Preconditions.checkNotNull(remoteModel, "RemoteModel cannot be null");
        return a(remoteModel.getClass()).deleteDownloadedModel(remoteModel);
    }

    @NonNull
    public Task<Void> download(@NonNull RemoteModel remoteModel, @NonNull DownloadConditions downloadConditions) {
        Preconditions.checkNotNull(remoteModel, "RemoteModel cannot be null");
        Preconditions.checkNotNull(downloadConditions, "DownloadConditions cannot be null");
        if (this.f11580a.containsKey(remoteModel.getClass())) {
            return a(remoteModel.getClass()).download(remoteModel, downloadConditions);
        }
        String simpleName = remoteModel.getClass().getSimpleName();
        return Tasks.forException(new MlKitException("Feature model '" + simpleName + "' doesn't have a corresponding modelmanager registered.", 13));
    }

    @NonNull
    public <T extends RemoteModel> Task<Set<T>> getDownloadedModels(@NonNull Class<T> cls) {
        return ((RemoteModelManagerInterface) ((Provider) Preconditions.checkNotNull((Provider) this.f11580a.get(cls))).get()).getDownloadedModels();
    }

    @NonNull
    public Task<Boolean> isModelDownloaded(@NonNull RemoteModel remoteModel) {
        Preconditions.checkNotNull(remoteModel, "RemoteModel cannot be null");
        return a(remoteModel.getClass()).isModelDownloaded(remoteModel);
    }
}
