package com.google.firebase.installations;

import android.text.TextUtils;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.inject.Provider;
import com.google.firebase.installations.FirebaseInstallationsException;
import com.google.firebase.installations.internal.FidListener;
import com.google.firebase.installations.internal.FidListenerHandle;
import com.google.firebase.installations.local.IidStore;
import com.google.firebase.installations.local.PersistedInstallation;
import com.google.firebase.installations.local.PersistedInstallationEntry;
import com.google.firebase.installations.remote.FirebaseInstallationServiceClient;
import com.google.firebase.installations.remote.InstallationResponse;
import com.google.firebase.installations.remote.TokenResult;
import com.google.firebase.platforminfo.UserAgentPublisher;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes10.dex */
public class FirebaseInstallations implements FirebaseInstallationsApi {
    public static final Object m = new Object();
    public static final ThreadFactory n = new a();

    /* renamed from: a  reason: collision with root package name */
    public final FirebaseApp f11304a;
    public final FirebaseInstallationServiceClient b;
    public final PersistedInstallation c;
    public final Utils d;
    public final IidStore e;
    public final RandomFidGenerator f;
    public final Object g;
    public final ExecutorService h;
    public final ExecutorService i;
    @GuardedBy("this")
    public String j;
    @GuardedBy("FirebaseInstallations.this")
    public Set<FidListener> k;
    @GuardedBy("lock")
    public final List<j> l;

    /* loaded from: classes10.dex */
    public class a implements ThreadFactory {
        public final AtomicInteger h = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, String.format("firebase-installations-executor-%d", Integer.valueOf(this.h.getAndIncrement())));
        }
    }

    /* loaded from: classes10.dex */
    public class b implements FidListenerHandle {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ FidListener f11305a;

        public b(FidListener fidListener) {
            this.f11305a = fidListener;
        }

        @Override // com.google.firebase.installations.internal.FidListenerHandle
        public void unregister() {
            synchronized (FirebaseInstallations.this) {
                FirebaseInstallations.this.k.remove(this.f11305a);
            }
        }
    }

    /* loaded from: classes10.dex */
    public static /* synthetic */ class c {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f11306a;
        public static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[TokenResult.ResponseCode.values().length];
            b = iArr;
            try {
                iArr[TokenResult.ResponseCode.OK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[TokenResult.ResponseCode.BAD_CONFIG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[TokenResult.ResponseCode.AUTH_ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[InstallationResponse.ResponseCode.values().length];
            f11306a = iArr2;
            try {
                iArr2[InstallationResponse.ResponseCode.OK.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f11306a[InstallationResponse.ResponseCode.BAD_CONFIG.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public FirebaseInstallations(FirebaseApp firebaseApp, @NonNull Provider<UserAgentPublisher> provider, @NonNull Provider<HeartBeatInfo> provider2) {
        this(new ThreadPoolExecutor(0, 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue(), n), firebaseApp, new FirebaseInstallationServiceClient(firebaseApp.getApplicationContext(), provider, provider2), new PersistedInstallation(firebaseApp), Utils.getInstance(), new IidStore(firebaseApp), new RandomFidGenerator());
    }

    @NonNull
    public static FirebaseInstallations getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void u() {
        v(false);
    }

    public final void A(PersistedInstallationEntry persistedInstallationEntry) {
        synchronized (this.g) {
            Iterator<j> it = this.l.iterator();
            while (it.hasNext()) {
                if (it.next().b(persistedInstallationEntry)) {
                    it.remove();
                }
            }
        }
    }

    public final synchronized void B(String str) {
        this.j = str;
    }

    public final synchronized void C(PersistedInstallationEntry persistedInstallationEntry, PersistedInstallationEntry persistedInstallationEntry2) {
        if (this.k.size() != 0 && !persistedInstallationEntry.getFirebaseInstallationId().equals(persistedInstallationEntry2.getFirebaseInstallationId())) {
            for (FidListener fidListener : this.k) {
                fidListener.onFidChanged(persistedInstallationEntry2.getFirebaseInstallationId());
            }
        }
    }

    @Override // com.google.firebase.installations.FirebaseInstallationsApi
    @NonNull
    public Task<Void> delete() {
        return Tasks.call(this.h, new Callable() { // from class: com.google.firebase.installations.f
            @Override // java.util.concurrent.Callable
            public final Object call() {
                Void i;
                i = FirebaseInstallations.this.i();
                return i;
            }
        });
    }

    public final Task<InstallationTokenResult> f() {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        h(new h(this.d, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    public final Task<String> g() {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        h(new i(taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    @Override // com.google.firebase.installations.FirebaseInstallationsApi
    @NonNull
    public Task<String> getId() {
        w();
        String o = o();
        if (o != null) {
            return Tasks.forResult(o);
        }
        Task<String> g = g();
        this.h.execute(new Runnable() { // from class: com.google.firebase.installations.c
            @Override // java.lang.Runnable
            public final void run() {
                FirebaseInstallations.this.u();
            }
        });
        return g;
    }

    @Override // com.google.firebase.installations.FirebaseInstallationsApi
    @NonNull
    public Task<InstallationTokenResult> getToken(final boolean z) {
        w();
        Task<InstallationTokenResult> f = f();
        this.h.execute(new Runnable() { // from class: com.google.firebase.installations.e
            @Override // java.lang.Runnable
            public final void run() {
                FirebaseInstallations.this.v(z);
            }
        });
        return f;
    }

    public final void h(j jVar) {
        synchronized (this.g) {
            this.l.add(jVar);
        }
    }

    public final Void i() throws FirebaseInstallationsException {
        B(null);
        PersistedInstallationEntry p = p();
        if (p.isRegistered()) {
            this.b.deleteFirebaseInstallation(m(), p.getFirebaseInstallationId(), r(), p.getRefreshToken());
        }
        s(p.withNoGeneratedFid());
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x004a  */
    /* renamed from: j */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void t(boolean r3) {
        /*
            r2 = this;
            com.google.firebase.installations.local.PersistedInstallationEntry r0 = r2.p()
            boolean r1 = r0.isErrored()     // Catch: com.google.firebase.installations.FirebaseInstallationsException -> L5f
            if (r1 != 0) goto L22
            boolean r1 = r0.isUnregistered()     // Catch: com.google.firebase.installations.FirebaseInstallationsException -> L5f
            if (r1 == 0) goto L11
            goto L22
        L11:
            if (r3 != 0) goto L1d
            com.google.firebase.installations.Utils r3 = r2.d     // Catch: com.google.firebase.installations.FirebaseInstallationsException -> L5f
            boolean r3 = r3.isAuthTokenExpired(r0)     // Catch: com.google.firebase.installations.FirebaseInstallationsException -> L5f
            if (r3 == 0) goto L1c
            goto L1d
        L1c:
            return
        L1d:
            com.google.firebase.installations.local.PersistedInstallationEntry r3 = r2.l(r0)     // Catch: com.google.firebase.installations.FirebaseInstallationsException -> L5f
            goto L26
        L22:
            com.google.firebase.installations.local.PersistedInstallationEntry r3 = r2.y(r0)     // Catch: com.google.firebase.installations.FirebaseInstallationsException -> L5f
        L26:
            r2.s(r3)
            r2.C(r0, r3)
            boolean r0 = r3.isRegistered()
            if (r0 == 0) goto L39
            java.lang.String r0 = r3.getFirebaseInstallationId()
            r2.B(r0)
        L39:
            boolean r0 = r3.isErrored()
            if (r0 == 0) goto L4a
            com.google.firebase.installations.FirebaseInstallationsException r3 = new com.google.firebase.installations.FirebaseInstallationsException
            com.google.firebase.installations.FirebaseInstallationsException$Status r0 = com.google.firebase.installations.FirebaseInstallationsException.Status.BAD_CONFIG
            r3.<init>(r0)
            r2.z(r3)
            goto L5e
        L4a:
            boolean r0 = r3.isNotGenerated()
            if (r0 == 0) goto L5b
            java.io.IOException r3 = new java.io.IOException
            java.lang.String r0 = "Installation ID could not be validated with the Firebase servers (maybe it was deleted). Firebase Installations will need to create a new Installation ID and auth token. Please retry your last request."
            r3.<init>(r0)
            r2.z(r3)
            goto L5e
        L5b:
            r2.A(r3)
        L5e:
            return
        L5f:
            r3 = move-exception
            r2.z(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.installations.FirebaseInstallations.t(boolean):void");
    }

    /* renamed from: k */
    public final void v(final boolean z) {
        PersistedInstallationEntry q = q();
        if (z) {
            q = q.withClearedAuthToken();
        }
        A(q);
        this.i.execute(new Runnable() { // from class: com.google.firebase.installations.d
            @Override // java.lang.Runnable
            public final void run() {
                FirebaseInstallations.this.t(z);
            }
        });
    }

    public final PersistedInstallationEntry l(@NonNull PersistedInstallationEntry persistedInstallationEntry) throws FirebaseInstallationsException {
        TokenResult generateAuthToken = this.b.generateAuthToken(m(), persistedInstallationEntry.getFirebaseInstallationId(), r(), persistedInstallationEntry.getRefreshToken());
        int i = c.b[generateAuthToken.getResponseCode().ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    B(null);
                    return persistedInstallationEntry.withNoGeneratedFid();
                }
                throw new FirebaseInstallationsException("Firebase Installations Service is unavailable. Please try again later.", FirebaseInstallationsException.Status.UNAVAILABLE);
            }
            return persistedInstallationEntry.withFisError("BAD CONFIG");
        }
        return persistedInstallationEntry.withAuthToken(generateAuthToken.getToken(), generateAuthToken.getTokenExpirationTimestamp(), this.d.currentTimeInSecs());
    }

    @Nullable
    public String m() {
        return this.f11304a.getOptions().getApiKey();
    }

    @VisibleForTesting
    public String n() {
        return this.f11304a.getOptions().getApplicationId();
    }

    public final synchronized String o() {
        return this.j;
    }

    public final PersistedInstallationEntry p() {
        PersistedInstallationEntry readPersistedInstallationEntryValue;
        synchronized (m) {
            com.google.firebase.installations.b a2 = com.google.firebase.installations.b.a(this.f11304a.getApplicationContext(), "generatefid.lock");
            readPersistedInstallationEntryValue = this.c.readPersistedInstallationEntryValue();
            if (a2 != null) {
                a2.b();
            }
        }
        return readPersistedInstallationEntryValue;
    }

    public final PersistedInstallationEntry q() {
        PersistedInstallationEntry readPersistedInstallationEntryValue;
        synchronized (m) {
            com.google.firebase.installations.b a2 = com.google.firebase.installations.b.a(this.f11304a.getApplicationContext(), "generatefid.lock");
            readPersistedInstallationEntryValue = this.c.readPersistedInstallationEntryValue();
            if (readPersistedInstallationEntryValue.isNotGenerated()) {
                readPersistedInstallationEntryValue = this.c.insertOrUpdatePersistedInstallationEntry(readPersistedInstallationEntryValue.withUnregisteredFid(x(readPersistedInstallationEntryValue)));
            }
            if (a2 != null) {
                a2.b();
            }
        }
        return readPersistedInstallationEntryValue;
    }

    @Nullable
    public String r() {
        return this.f11304a.getOptions().getProjectId();
    }

    @Override // com.google.firebase.installations.FirebaseInstallationsApi
    @NonNull
    public synchronized FidListenerHandle registerFidListener(@NonNull FidListener fidListener) {
        this.k.add(fidListener);
        return new b(fidListener);
    }

    public final void s(PersistedInstallationEntry persistedInstallationEntry) {
        synchronized (m) {
            com.google.firebase.installations.b a2 = com.google.firebase.installations.b.a(this.f11304a.getApplicationContext(), "generatefid.lock");
            this.c.insertOrUpdatePersistedInstallationEntry(persistedInstallationEntry);
            if (a2 != null) {
                a2.b();
            }
        }
    }

    public final void w() {
        Preconditions.checkNotEmpty(n(), "Please set your Application ID. A valid Firebase App ID is required to communicate with Firebase server APIs: It identifies your application with Firebase.Please refer to https://firebase.google.com/support/privacy/init-options.");
        Preconditions.checkNotEmpty(r(), "Please set your Project ID. A valid Firebase Project ID is required to communicate with Firebase server APIs: It identifies your application with Firebase.Please refer to https://firebase.google.com/support/privacy/init-options.");
        Preconditions.checkNotEmpty(m(), "Please set a valid API key. A Firebase API key is required to communicate with Firebase server APIs: It authenticates your project with Google.Please refer to https://firebase.google.com/support/privacy/init-options.");
        Preconditions.checkArgument(Utils.b(n()), "Please set your Application ID. A valid Firebase App ID is required to communicate with Firebase server APIs: It identifies your application with Firebase.Please refer to https://firebase.google.com/support/privacy/init-options.");
        Preconditions.checkArgument(Utils.a(m()), "Please set a valid API key. A Firebase API key is required to communicate with Firebase server APIs: It authenticates your project with Google.Please refer to https://firebase.google.com/support/privacy/init-options.");
    }

    public final String x(PersistedInstallationEntry persistedInstallationEntry) {
        if ((!this.f11304a.getName().equals("CHIME_ANDROID_SDK") && !this.f11304a.isDefaultApp()) || !persistedInstallationEntry.shouldAttemptMigration()) {
            return this.f.createRandomFid();
        }
        String readIid = this.e.readIid();
        return TextUtils.isEmpty(readIid) ? this.f.createRandomFid() : readIid;
    }

    public final PersistedInstallationEntry y(PersistedInstallationEntry persistedInstallationEntry) throws FirebaseInstallationsException {
        InstallationResponse createFirebaseInstallation = this.b.createFirebaseInstallation(m(), persistedInstallationEntry.getFirebaseInstallationId(), r(), n(), (persistedInstallationEntry.getFirebaseInstallationId() == null || persistedInstallationEntry.getFirebaseInstallationId().length() != 11) ? null : this.e.readToken());
        int i = c.f11306a[createFirebaseInstallation.getResponseCode().ordinal()];
        if (i != 1) {
            if (i == 2) {
                return persistedInstallationEntry.withFisError("BAD CONFIG");
            }
            throw new FirebaseInstallationsException("Firebase Installations Service is unavailable. Please try again later.", FirebaseInstallationsException.Status.UNAVAILABLE);
        }
        return persistedInstallationEntry.withRegisteredFid(createFirebaseInstallation.getFid(), createFirebaseInstallation.getRefreshToken(), this.d.currentTimeInSecs(), createFirebaseInstallation.getAuthToken().getToken(), createFirebaseInstallation.getAuthToken().getTokenExpirationTimestamp());
    }

    public final void z(Exception exc) {
        synchronized (this.g) {
            Iterator<j> it = this.l.iterator();
            while (it.hasNext()) {
                if (it.next().a(exc)) {
                    it.remove();
                }
            }
        }
    }

    @NonNull
    public static FirebaseInstallations getInstance(@NonNull FirebaseApp firebaseApp) {
        Preconditions.checkArgument(firebaseApp != null, "Null is not a valid value of FirebaseApp.");
        return (FirebaseInstallations) firebaseApp.get(FirebaseInstallationsApi.class);
    }

    public FirebaseInstallations(ExecutorService executorService, FirebaseApp firebaseApp, FirebaseInstallationServiceClient firebaseInstallationServiceClient, PersistedInstallation persistedInstallation, Utils utils, IidStore iidStore, RandomFidGenerator randomFidGenerator) {
        this.g = new Object();
        this.k = new HashSet();
        this.l = new ArrayList();
        this.f11304a = firebaseApp;
        this.b = firebaseInstallationServiceClient;
        this.c = persistedInstallation;
        this.d = utils;
        this.e = iidStore;
        this.f = randomFidGenerator;
        this.h = executorService;
        this.i = new ThreadPoolExecutor(0, 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue(), n);
    }
}
