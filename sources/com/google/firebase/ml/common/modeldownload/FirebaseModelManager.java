package com.google.firebase.ml.common.modeldownload;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.inject.Provider;
import com.google.firebase.ml.common.FirebaseMLException;
import com.google.firebase.ml.common.internal.modeldownload.RemoteModelManagerInterface;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/* loaded from: classes10.dex */
public class FirebaseModelManager {

    /* renamed from: a  reason: collision with root package name */
    public final Map<Class<? extends FirebaseRemoteModel>, Provider<? extends RemoteModelManagerInterface<? extends FirebaseRemoteModel>>> f11405a = new HashMap();

    @KeepForSdk
    /* loaded from: classes10.dex */
    public static class RemoteModelManagerRegistration {

        /* renamed from: a  reason: collision with root package name */
        public final Class<? extends FirebaseRemoteModel> f11406a;
        public final Provider<? extends RemoteModelManagerInterface<? extends FirebaseRemoteModel>> b;

        public <TRemote extends FirebaseRemoteModel> RemoteModelManagerRegistration(Class<TRemote> cls, Provider<? extends RemoteModelManagerInterface<TRemote>> provider) {
            this.f11406a = cls;
            this.b = provider;
        }

        public final Class<? extends FirebaseRemoteModel> a() {
            return this.f11406a;
        }

        public final Provider<? extends RemoteModelManagerInterface<? extends FirebaseRemoteModel>> b() {
            return this.b;
        }
    }

    @KeepForSdk
    public FirebaseModelManager(Set<RemoteModelManagerRegistration> set) {
        for (RemoteModelManagerRegistration remoteModelManagerRegistration : set) {
            this.f11405a.put(remoteModelManagerRegistration.a(), remoteModelManagerRegistration.b());
        }
    }

    @NonNull
    public static synchronized FirebaseModelManager getInstance() {
        FirebaseModelManager firebaseModelManager;
        synchronized (FirebaseModelManager.class) {
            firebaseModelManager = getInstance(FirebaseApp.getInstance());
        }
        return firebaseModelManager;
    }

    public final RemoteModelManagerInterface<FirebaseRemoteModel> a(Class<? extends FirebaseRemoteModel> cls) {
        return (RemoteModelManagerInterface) this.f11405a.get(cls).get();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NonNull
    public Task<Void> deleteDownloadedModel(@NonNull FirebaseRemoteModel firebaseRemoteModel) {
        Preconditions.checkNotNull(firebaseRemoteModel, "FirebaseRemoteModel cannot be null");
        return a(firebaseRemoteModel.getClass()).deleteDownloadedModel(firebaseRemoteModel);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NonNull
    public Task<Void> download(@NonNull FirebaseRemoteModel firebaseRemoteModel, @NonNull FirebaseModelDownloadConditions firebaseModelDownloadConditions) {
        Preconditions.checkNotNull(firebaseRemoteModel, "FirebaseRemoteModel cannot be null");
        Preconditions.checkNotNull(firebaseModelDownloadConditions, "FirebaseModelDownloadConditions cannot be null");
        if (this.f11405a.containsKey(firebaseRemoteModel.getClass())) {
            return a(firebaseRemoteModel.getClass()).download(firebaseRemoteModel, firebaseModelDownloadConditions);
        }
        return Tasks.forException(new FirebaseMLException("Feature model doesn't have a corresponding modelmanager registered.", 13));
    }

    @NonNull
    public <T extends FirebaseRemoteModel> Task<Set<T>> getDownloadedModels(@NonNull Class<T> cls) {
        return (Task<Set<T>>) this.f11405a.get(cls).get().getDownloadedModels();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NonNull
    public Task<File> getLatestModelFile(@NonNull FirebaseRemoteModel firebaseRemoteModel) {
        Preconditions.checkNotNull(firebaseRemoteModel, "FirebaseRemoteModel cannot be null");
        if (this.f11405a.containsKey(firebaseRemoteModel.getClass())) {
            return a(firebaseRemoteModel.getClass()).getLatestModelFile(firebaseRemoteModel);
        }
        return Tasks.forException(new FirebaseMLException("Feature model doesn't have a corresponding modelmanager registered.", 13));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NonNull
    public Task<Boolean> isModelDownloaded(@NonNull FirebaseRemoteModel firebaseRemoteModel) {
        Preconditions.checkNotNull(firebaseRemoteModel, "FirebaseRemoteModel cannot be null");
        return a(firebaseRemoteModel.getClass()).isModelDownloaded(firebaseRemoteModel);
    }

    @NonNull
    public static synchronized FirebaseModelManager getInstance(@NonNull FirebaseApp firebaseApp) {
        FirebaseModelManager firebaseModelManager;
        synchronized (FirebaseModelManager.class) {
            Preconditions.checkNotNull(firebaseApp, "Please provide a valid FirebaseApp");
            firebaseModelManager = (FirebaseModelManager) firebaseApp.get(FirebaseModelManager.class);
        }
        return firebaseModelManager;
    }
}
