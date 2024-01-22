package com.mappls.sdk.maps.offline;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import com.mappls.sdk.maps.LibraryLoader;
import com.mappls.sdk.maps.R;
import com.mappls.sdk.maps.geometry.LatLngBounds;
import com.mappls.sdk.maps.net.ConnectivityReceiver;
import com.mappls.sdk.maps.storage.FileSource;
import com.mappls.sdk.maps.utils.FileUtils;
import java.io.File;
import java.io.IOException;
@UiThread
/* loaded from: classes11.dex */
public class OfflineManager {
    @SuppressLint({"StaticFieldLeak"})
    public static OfflineManager d;

    /* renamed from: a  reason: collision with root package name */
    public final FileSource f12798a;
    public final Handler b = new Handler(Looper.getMainLooper());
    public Context c;
    @Keep
    private long nativePtr;

    @Keep
    /* loaded from: classes11.dex */
    public interface CreateOfflineRegionCallback {
        void onCreate(OfflineRegion offlineRegion);

        void onError(String str);
    }

    @Keep
    /* loaded from: classes11.dex */
    public interface FileSourceCallback {
        void onError(@NonNull String str);

        void onSuccess();
    }

    @Keep
    /* loaded from: classes11.dex */
    public interface ListOfflineRegionsCallback {
        void onError(String str);

        void onList(OfflineRegion[] offlineRegionArr);
    }

    @Keep
    /* loaded from: classes11.dex */
    public interface MergeOfflineRegionsCallback {
        void onError(String str);

        void onMerge(OfflineRegion[] offlineRegionArr);
    }

    /* loaded from: classes11.dex */
    public class a implements ListOfflineRegionsCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ ListOfflineRegionsCallback f12799a;

        /* renamed from: com.mappls.sdk.maps.offline.OfflineManager$a$a  reason: collision with other inner class name */
        /* loaded from: classes11.dex */
        public class RunnableC0631a implements Runnable {
            public final /* synthetic */ OfflineRegion[] h;

            public RunnableC0631a(OfflineRegion[] offlineRegionArr) {
                this.h = offlineRegionArr;
            }

            @Override // java.lang.Runnable
            public void run() {
                OfflineManager.this.f12798a.deactivate();
                a.this.f12799a.onList(this.h);
            }
        }

        /* loaded from: classes11.dex */
        public class b implements Runnable {
            public final /* synthetic */ String h;

            public b(String str) {
                this.h = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                OfflineManager.this.f12798a.deactivate();
                a.this.f12799a.onError(this.h);
            }
        }

        public a(ListOfflineRegionsCallback listOfflineRegionsCallback) {
            this.f12799a = listOfflineRegionsCallback;
        }

        @Override // com.mappls.sdk.maps.offline.OfflineManager.ListOfflineRegionsCallback
        public void onError(String str) {
            OfflineManager.this.b.post(new b(str));
        }

        @Override // com.mappls.sdk.maps.offline.OfflineManager.ListOfflineRegionsCallback
        public void onList(OfflineRegion[] offlineRegionArr) {
            OfflineManager.this.b.post(new RunnableC0631a(offlineRegionArr));
        }
    }

    /* loaded from: classes11.dex */
    public class b implements Runnable {
        public final /* synthetic */ File h;
        public final /* synthetic */ MergeOfflineRegionsCallback i;

        /* loaded from: classes11.dex */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                b bVar = b.this;
                OfflineManager.this.i(bVar.h, bVar.i, false);
            }
        }

        /* renamed from: com.mappls.sdk.maps.offline.OfflineManager$b$b  reason: collision with other inner class name */
        /* loaded from: classes11.dex */
        public class RunnableC0632b implements Runnable {
            public final /* synthetic */ File h;

            public RunnableC0632b(File file) {
                this.h = file;
            }

            @Override // java.lang.Runnable
            public void run() {
                b bVar = b.this;
                OfflineManager.this.i(this.h, bVar.i, true);
            }
        }

        /* loaded from: classes11.dex */
        public class c implements Runnable {
            public final /* synthetic */ String h;

            public c(String str) {
                this.h = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.i.onError(this.h);
            }
        }

        public b(File file, MergeOfflineRegionsCallback mergeOfflineRegionsCallback) {
            this.h = file;
            this.i = mergeOfflineRegionsCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            String str = null;
            if (this.h.canWrite()) {
                OfflineManager.this.b.post(new a());
            } else if (this.h.canRead()) {
                File file = new File(FileSource.getInternalCachePath(OfflineManager.this.c), this.h.getName());
                try {
                    OfflineManager.f(this.h, file);
                    OfflineManager.this.b.post(new RunnableC0632b(file));
                } catch (IOException e) {
                    e.printStackTrace();
                    str = e.getMessage();
                }
            } else {
                str = "Secondary database needs to be located in a readable path.";
            }
            if (str != null) {
                OfflineManager.this.b.post(new c(str));
            }
        }
    }

    /* loaded from: classes11.dex */
    public class c implements FileSourceCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ FileSourceCallback f12800a;

        /* loaded from: classes11.dex */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                OfflineManager.this.f12798a.deactivate();
                FileSourceCallback fileSourceCallback = c.this.f12800a;
                if (fileSourceCallback != null) {
                    fileSourceCallback.onSuccess();
                }
            }
        }

        /* loaded from: classes11.dex */
        public class b implements Runnable {
            public final /* synthetic */ String h;

            public b(String str) {
                this.h = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                OfflineManager.this.f12798a.deactivate();
                FileSourceCallback fileSourceCallback = c.this.f12800a;
                if (fileSourceCallback != null) {
                    fileSourceCallback.onError(this.h);
                }
            }
        }

        public c(FileSourceCallback fileSourceCallback) {
            this.f12800a = fileSourceCallback;
        }

        @Override // com.mappls.sdk.maps.offline.OfflineManager.FileSourceCallback
        public void onError(@NonNull String str) {
            OfflineManager.this.b.post(new b(str));
        }

        @Override // com.mappls.sdk.maps.offline.OfflineManager.FileSourceCallback
        public void onSuccess() {
            OfflineManager.this.b.post(new a());
        }
    }

    /* loaded from: classes11.dex */
    public class d implements FileSourceCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ FileSourceCallback f12801a;

        /* loaded from: classes11.dex */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                OfflineManager.this.f12798a.deactivate();
                FileSourceCallback fileSourceCallback = d.this.f12801a;
                if (fileSourceCallback != null) {
                    fileSourceCallback.onSuccess();
                }
            }
        }

        /* loaded from: classes11.dex */
        public class b implements Runnable {
            public final /* synthetic */ String h;

            public b(String str) {
                this.h = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                OfflineManager.this.f12798a.deactivate();
                FileSourceCallback fileSourceCallback = d.this.f12801a;
                if (fileSourceCallback != null) {
                    fileSourceCallback.onError(this.h);
                }
            }
        }

        public d(FileSourceCallback fileSourceCallback) {
            this.f12801a = fileSourceCallback;
        }

        @Override // com.mappls.sdk.maps.offline.OfflineManager.FileSourceCallback
        public void onError(@NonNull String str) {
            OfflineManager.this.b.post(new b(str));
        }

        @Override // com.mappls.sdk.maps.offline.OfflineManager.FileSourceCallback
        public void onSuccess() {
            OfflineManager.this.b.post(new a());
        }
    }

    /* loaded from: classes11.dex */
    public class e implements FileSourceCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ FileSourceCallback f12802a;

        /* loaded from: classes11.dex */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                OfflineManager.this.f12798a.deactivate();
                FileSourceCallback fileSourceCallback = e.this.f12802a;
                if (fileSourceCallback != null) {
                    fileSourceCallback.onSuccess();
                }
            }
        }

        /* loaded from: classes11.dex */
        public class b implements Runnable {
            public final /* synthetic */ String h;

            public b(String str) {
                this.h = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                OfflineManager.this.f12798a.deactivate();
                FileSourceCallback fileSourceCallback = e.this.f12802a;
                if (fileSourceCallback != null) {
                    fileSourceCallback.onError(this.h);
                }
            }
        }

        public e(FileSourceCallback fileSourceCallback) {
            this.f12802a = fileSourceCallback;
        }

        @Override // com.mappls.sdk.maps.offline.OfflineManager.FileSourceCallback
        public void onError(@NonNull String str) {
            OfflineManager.this.b.post(new b(str));
        }

        @Override // com.mappls.sdk.maps.offline.OfflineManager.FileSourceCallback
        public void onSuccess() {
            OfflineManager.this.b.post(new a());
        }
    }

    /* loaded from: classes11.dex */
    public class f implements FileSourceCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ FileSourceCallback f12803a;

        /* loaded from: classes11.dex */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                OfflineManager.this.f12798a.deactivate();
                FileSourceCallback fileSourceCallback = f.this.f12803a;
                if (fileSourceCallback != null) {
                    fileSourceCallback.onSuccess();
                }
            }
        }

        /* loaded from: classes11.dex */
        public class b implements Runnable {
            public final /* synthetic */ String h;

            public b(String str) {
                this.h = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                OfflineManager.this.f12798a.deactivate();
                FileSourceCallback fileSourceCallback = f.this.f12803a;
                if (fileSourceCallback != null) {
                    fileSourceCallback.onError(this.h);
                }
            }
        }

        public f(FileSourceCallback fileSourceCallback) {
            this.f12803a = fileSourceCallback;
        }

        @Override // com.mappls.sdk.maps.offline.OfflineManager.FileSourceCallback
        public void onError(@NonNull String str) {
            OfflineManager.this.b.post(new b(str));
        }

        @Override // com.mappls.sdk.maps.offline.OfflineManager.FileSourceCallback
        public void onSuccess() {
            OfflineManager.this.b.post(new a());
        }
    }

    /* loaded from: classes11.dex */
    public class g implements FileSourceCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ FileSourceCallback f12804a;

        /* loaded from: classes11.dex */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                OfflineManager.this.f12798a.deactivate();
                FileSourceCallback fileSourceCallback = g.this.f12804a;
                if (fileSourceCallback != null) {
                    fileSourceCallback.onSuccess();
                }
            }
        }

        /* loaded from: classes11.dex */
        public class b implements Runnable {
            public final /* synthetic */ String h;

            public b(String str) {
                this.h = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                OfflineManager.this.f12798a.deactivate();
                FileSourceCallback fileSourceCallback = g.this.f12804a;
                if (fileSourceCallback != null) {
                    fileSourceCallback.onError(this.h);
                }
            }
        }

        public g(FileSourceCallback fileSourceCallback) {
            this.f12804a = fileSourceCallback;
        }

        @Override // com.mappls.sdk.maps.offline.OfflineManager.FileSourceCallback
        public void onError(@NonNull String str) {
            OfflineManager.this.f12798a.activate();
            OfflineManager.this.b.post(new b(str));
        }

        @Override // com.mappls.sdk.maps.offline.OfflineManager.FileSourceCallback
        public void onSuccess() {
            OfflineManager.this.b.post(new a());
        }
    }

    /* loaded from: classes11.dex */
    public class h implements MergeOfflineRegionsCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ boolean f12805a;
        public final /* synthetic */ File b;
        public final /* synthetic */ MergeOfflineRegionsCallback c;

        /* loaded from: classes11.dex */
        public class a implements Runnable {
            public final /* synthetic */ OfflineRegion[] h;

            public a(OfflineRegion[] offlineRegionArr) {
                this.h = offlineRegionArr;
            }

            @Override // java.lang.Runnable
            public void run() {
                OfflineManager.this.f12798a.deactivate();
                h.this.c.onMerge(this.h);
            }
        }

        /* loaded from: classes11.dex */
        public class b implements Runnable {
            public final /* synthetic */ String h;

            public b(String str) {
                this.h = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                OfflineManager.this.f12798a.deactivate();
                h.this.c.onError(this.h);
            }
        }

        public h(boolean z, File file, MergeOfflineRegionsCallback mergeOfflineRegionsCallback) {
            this.f12805a = z;
            this.b = file;
            this.c = mergeOfflineRegionsCallback;
        }

        @Override // com.mappls.sdk.maps.offline.OfflineManager.MergeOfflineRegionsCallback
        public void onError(String str) {
            if (this.f12805a) {
                this.b.delete();
            }
            OfflineManager.this.b.post(new b(str));
        }

        @Override // com.mappls.sdk.maps.offline.OfflineManager.MergeOfflineRegionsCallback
        public void onMerge(OfflineRegion[] offlineRegionArr) {
            if (this.f12805a) {
                this.b.delete();
            }
            OfflineManager.this.b.post(new a(offlineRegionArr));
        }
    }

    /* loaded from: classes11.dex */
    public class i implements CreateOfflineRegionCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CreateOfflineRegionCallback f12806a;

        /* loaded from: classes11.dex */
        public class a implements Runnable {
            public final /* synthetic */ OfflineRegion h;

            public a(OfflineRegion offlineRegion) {
                this.h = offlineRegion;
            }

            @Override // java.lang.Runnable
            public void run() {
                ConnectivityReceiver.instance(OfflineManager.this.c).deactivate();
                FileSource.getInstance(OfflineManager.this.c).deactivate();
                i.this.f12806a.onCreate(this.h);
            }
        }

        /* loaded from: classes11.dex */
        public class b implements Runnable {
            public final /* synthetic */ String h;

            public b(String str) {
                this.h = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                ConnectivityReceiver.instance(OfflineManager.this.c).deactivate();
                FileSource.getInstance(OfflineManager.this.c).deactivate();
                i.this.f12806a.onError(this.h);
            }
        }

        public i(CreateOfflineRegionCallback createOfflineRegionCallback) {
            this.f12806a = createOfflineRegionCallback;
        }

        @Override // com.mappls.sdk.maps.offline.OfflineManager.CreateOfflineRegionCallback
        public void onCreate(OfflineRegion offlineRegion) {
            OfflineManager.this.b.post(new a(offlineRegion));
        }

        @Override // com.mappls.sdk.maps.offline.OfflineManager.CreateOfflineRegionCallback
        public void onError(String str) {
            OfflineManager.this.b.post(new b(str));
        }
    }

    static {
        LibraryLoader.load();
    }

    public OfflineManager(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.c = applicationContext;
        FileSource fileSource = FileSource.getInstance(applicationContext);
        this.f12798a = fileSource;
        initialize(fileSource);
        g(this.c);
    }

    @Keep
    private native void createOfflineRegion(FileSource fileSource, OfflineRegionDefinition offlineRegionDefinition, byte[] bArr, CreateOfflineRegionCallback createOfflineRegionCallback);

    /* JADX WARN: Removed duplicated region for block: B:26:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0067  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void f(@androidx.annotation.NonNull java.io.File r9, java.io.File r10) throws java.io.IOException {
        /*
            boolean r0 = r10.exists()
            if (r0 != 0) goto L15
            boolean r0 = r10.createNewFile()
            if (r0 == 0) goto Ld
            goto L15
        Ld:
            java.io.IOException r9 = new java.io.IOException
            java.lang.String r10 = "Unable to copy database file for merge."
            r9.<init>(r10)
            throw r9
        L15:
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L47
            r1.<init>(r9)     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L47
            java.nio.channels.FileChannel r9 = r1.getChannel()     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L47
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L3a java.io.IOException -> L3f
            r1.<init>(r10)     // Catch: java.lang.Throwable -> L3a java.io.IOException -> L3f
            java.nio.channels.FileChannel r0 = r1.getChannel()     // Catch: java.lang.Throwable -> L3a java.io.IOException -> L3f
            r4 = 0
            long r6 = r9.size()     // Catch: java.lang.Throwable -> L3a java.io.IOException -> L3f
            r2 = r0
            r3 = r9
            r2.transferFrom(r3, r4, r6)     // Catch: java.lang.Throwable -> L3a java.io.IOException -> L3f
            r9.close()
            r0.close()
            return
        L3a:
            r10 = move-exception
            r8 = r0
            r0 = r9
            r9 = r8
            goto L60
        L3f:
            r10 = move-exception
            r8 = r0
            r0 = r9
            r9 = r8
            goto L49
        L44:
            r10 = move-exception
            r9 = r0
            goto L60
        L47:
            r10 = move-exception
            r9 = r0
        L49:
            java.io.IOException r1 = new java.io.IOException     // Catch: java.lang.Throwable -> L5f
            java.lang.String r2 = "Unable to copy database file for merge. %s"
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L5f
            r4 = 0
            java.lang.String r10 = r10.getMessage()     // Catch: java.lang.Throwable -> L5f
            r3[r4] = r10     // Catch: java.lang.Throwable -> L5f
            java.lang.String r10 = java.lang.String.format(r2, r3)     // Catch: java.lang.Throwable -> L5f
            r1.<init>(r10)     // Catch: java.lang.Throwable -> L5f
            throw r1     // Catch: java.lang.Throwable -> L5f
        L5f:
            r10 = move-exception
        L60:
            if (r0 == 0) goto L65
            r0.close()
        L65:
            if (r9 == 0) goto L6a
            r9.close()
        L6a:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.sdk.maps.offline.OfflineManager.f(java.io.File, java.io.File):void");
    }

    public static synchronized OfflineManager getInstance(@NonNull Context context) {
        OfflineManager offlineManager;
        synchronized (OfflineManager.class) {
            if (d == null) {
                d = new OfflineManager(context);
            }
            offlineManager = d;
        }
        return offlineManager;
    }

    @Keep
    private native void initialize(FileSource fileSource);

    @Keep
    private native void listOfflineRegions(FileSource fileSource, ListOfflineRegionsCallback listOfflineRegionsCallback);

    @Keep
    private native void mergeOfflineRegions(FileSource fileSource, String str, MergeOfflineRegionsCallback mergeOfflineRegionsCallback);

    @Keep
    private native void nativeClearAmbientCache(@Nullable FileSourceCallback fileSourceCallback);

    @Keep
    private native void nativeInvalidateAmbientCache(@Nullable FileSourceCallback fileSourceCallback);

    @Keep
    private native void nativePackDatabase(@Nullable FileSourceCallback fileSourceCallback);

    @Keep
    private native void nativeResetDatabase(@Nullable FileSourceCallback fileSourceCallback);

    @Keep
    private native void nativeSetMaximumAmbientCacheSize(long j, @Nullable FileSourceCallback fileSourceCallback);

    public void clearAmbientCache(@Nullable FileSourceCallback fileSourceCallback) {
        this.f12798a.activate();
        nativeClearAmbientCache(new f(fileSourceCallback));
    }

    public void createOfflineRegion(@NonNull OfflineRegionDefinition offlineRegionDefinition, @NonNull byte[] bArr, @NonNull CreateOfflineRegionCallback createOfflineRegionCallback) {
        if (!h(offlineRegionDefinition)) {
            createOfflineRegionCallback.onError(String.format(this.c.getString(R.string.mappls_maps_offline_error_region_definition_invalid), offlineRegionDefinition.getBounds()));
            return;
        }
        ConnectivityReceiver.instance(this.c).activate();
        FileSource.getInstance(this.c).activate();
        createOfflineRegion(this.f12798a, offlineRegionDefinition, bArr, new i(createOfflineRegionCallback));
    }

    @Keep
    public native void finalize() throws Throwable;

    public final void g(Context context) {
        FileUtils.deleteFile(FileSource.getInternalCachePath(context) + File.separator + "mbgl-cache.db");
    }

    public final boolean h(OfflineRegionDefinition offlineRegionDefinition) {
        return LatLngBounds.world().contains(offlineRegionDefinition.getBounds());
    }

    public final void i(@NonNull File file, @NonNull MergeOfflineRegionsCallback mergeOfflineRegionsCallback, boolean z) {
        this.f12798a.activate();
        mergeOfflineRegions(this.f12798a, file.getAbsolutePath(), new h(z, file, mergeOfflineRegionsCallback));
    }

    public void invalidateAmbientCache(@Nullable FileSourceCallback fileSourceCallback) {
        this.f12798a.activate();
        nativeInvalidateAmbientCache(new e(fileSourceCallback));
    }

    public void listOfflineRegions(@NonNull ListOfflineRegionsCallback listOfflineRegionsCallback) {
        this.f12798a.activate();
        listOfflineRegions(this.f12798a, new a(listOfflineRegionsCallback));
    }

    public void mergeOfflineRegions(@NonNull String str, @NonNull MergeOfflineRegionsCallback mergeOfflineRegionsCallback) {
        new Thread(new b(new File(str), mergeOfflineRegionsCallback)).start();
    }

    public void packDatabase(@Nullable FileSourceCallback fileSourceCallback) {
        this.f12798a.activate();
        nativePackDatabase(new d(fileSourceCallback));
    }

    @Keep
    public native void putResourceWithUrl(String str, byte[] bArr, long j, long j2, String str2, boolean z);

    public void resetDatabase(@Nullable FileSourceCallback fileSourceCallback) {
        this.f12798a.activate();
        nativeResetDatabase(new c(fileSourceCallback));
    }

    @Keep
    public native void runPackDatabaseAutomatically(boolean z);

    public void setMaximumAmbientCacheSize(long j, @Nullable FileSourceCallback fileSourceCallback) {
        this.f12798a.activate();
        nativeSetMaximumAmbientCacheSize(j, new g(fileSourceCallback));
    }

    @Keep
    public native void setOfflineMapplsTileCountLimit(long j);
}
