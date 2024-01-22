package com.mappls.sdk.maps.offline;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.maps.LibraryLoader;
import com.mappls.sdk.maps.Mappls;
import com.mappls.sdk.maps.net.ConnectivityReceiver;
import com.mappls.sdk.maps.storage.FileSource;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes11.dex */
public class OfflineRegion {
    public static final int STATE_ACTIVE = 1;
    public static final int STATE_INACTIVE = 0;
    public FileSource b;
    public long c;
    public boolean d;
    public OfflineRegionDefinition e;
    public byte[] f;
    @Keep
    private long nativePtr;
    public final Handler g = new Handler(Looper.getMainLooper());
    public int h = 0;
    public boolean i = false;

    /* renamed from: a  reason: collision with root package name */
    public final Context f12807a = Mappls.getApplicationContext();

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface DownloadState {
    }

    @Keep
    /* loaded from: classes11.dex */
    public interface OfflineRegionDeleteCallback {
        void onDelete();

        void onError(String str);
    }

    @Keep
    /* loaded from: classes11.dex */
    public interface OfflineRegionInvalidateCallback {
        void onError(String str);

        void onInvalidate();
    }

    @Keep
    /* loaded from: classes11.dex */
    public interface OfflineRegionObserver {
        void mapplsTileCountLimitExceeded(long j);

        void onError(OfflineRegionError offlineRegionError);

        void onStatusChanged(OfflineRegionStatus offlineRegionStatus);
    }

    @Keep
    /* loaded from: classes11.dex */
    public interface OfflineRegionStatusCallback {
        void onError(String str);

        void onStatus(OfflineRegionStatus offlineRegionStatus);
    }

    @Keep
    /* loaded from: classes11.dex */
    public interface OfflineRegionUpdateMetadataCallback {
        void onError(String str);

        void onUpdate(byte[] bArr);
    }

    /* loaded from: classes11.dex */
    public class a implements OfflineRegionObserver {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OfflineRegionObserver f12808a;

        /* renamed from: com.mappls.sdk.maps.offline.OfflineRegion$a$a  reason: collision with other inner class name */
        /* loaded from: classes11.dex */
        public class RunnableC0633a implements Runnable {
            public final /* synthetic */ OfflineRegionStatus h;

            public RunnableC0633a(OfflineRegionStatus offlineRegionStatus) {
                this.h = offlineRegionStatus;
            }

            @Override // java.lang.Runnable
            public void run() {
                OfflineRegionObserver offlineRegionObserver = a.this.f12808a;
                if (offlineRegionObserver != null) {
                    offlineRegionObserver.onStatusChanged(this.h);
                }
            }
        }

        /* loaded from: classes11.dex */
        public class b implements Runnable {
            public final /* synthetic */ OfflineRegionError h;

            public b(OfflineRegionError offlineRegionError) {
                this.h = offlineRegionError;
            }

            @Override // java.lang.Runnable
            public void run() {
                OfflineRegionObserver offlineRegionObserver = a.this.f12808a;
                if (offlineRegionObserver != null) {
                    offlineRegionObserver.onError(this.h);
                }
            }
        }

        /* loaded from: classes11.dex */
        public class c implements Runnable {
            public final /* synthetic */ long h;

            public c(long j) {
                this.h = j;
            }

            @Override // java.lang.Runnable
            public void run() {
                OfflineRegionObserver offlineRegionObserver = a.this.f12808a;
                if (offlineRegionObserver != null) {
                    offlineRegionObserver.mapplsTileCountLimitExceeded(this.h);
                }
            }
        }

        public a(OfflineRegionObserver offlineRegionObserver) {
            this.f12808a = offlineRegionObserver;
        }

        @Override // com.mappls.sdk.maps.offline.OfflineRegion.OfflineRegionObserver
        public void mapplsTileCountLimitExceeded(long j) {
            if (OfflineRegion.this.f()) {
                OfflineRegion.this.g.post(new c(j));
            }
        }

        @Override // com.mappls.sdk.maps.offline.OfflineRegion.OfflineRegionObserver
        public void onError(OfflineRegionError offlineRegionError) {
            if (OfflineRegion.this.f()) {
                OfflineRegion.this.g.post(new b(offlineRegionError));
            }
        }

        @Override // com.mappls.sdk.maps.offline.OfflineRegion.OfflineRegionObserver
        public void onStatusChanged(OfflineRegionStatus offlineRegionStatus) {
            if (OfflineRegion.this.f()) {
                OfflineRegion.this.g.post(new RunnableC0633a(offlineRegionStatus));
            }
        }
    }

    /* loaded from: classes11.dex */
    public class b implements OfflineRegionStatusCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OfflineRegionStatusCallback f12809a;

        /* loaded from: classes11.dex */
        public class a implements Runnable {
            public final /* synthetic */ OfflineRegionStatus h;

            public a(OfflineRegionStatus offlineRegionStatus) {
                this.h = offlineRegionStatus;
            }

            @Override // java.lang.Runnable
            public void run() {
                OfflineRegion.this.b.deactivate();
                b.this.f12809a.onStatus(this.h);
            }
        }

        /* renamed from: com.mappls.sdk.maps.offline.OfflineRegion$b$b  reason: collision with other inner class name */
        /* loaded from: classes11.dex */
        public class RunnableC0634b implements Runnable {
            public final /* synthetic */ String h;

            public RunnableC0634b(String str) {
                this.h = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                OfflineRegion.this.b.deactivate();
                b.this.f12809a.onError(this.h);
            }
        }

        public b(OfflineRegionStatusCallback offlineRegionStatusCallback) {
            this.f12809a = offlineRegionStatusCallback;
        }

        @Override // com.mappls.sdk.maps.offline.OfflineRegion.OfflineRegionStatusCallback
        public void onError(String str) {
            OfflineRegion.this.g.post(new RunnableC0634b(str));
        }

        @Override // com.mappls.sdk.maps.offline.OfflineRegion.OfflineRegionStatusCallback
        public void onStatus(OfflineRegionStatus offlineRegionStatus) {
            OfflineRegion.this.g.post(new a(offlineRegionStatus));
        }
    }

    /* loaded from: classes11.dex */
    public class c implements OfflineRegionDeleteCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OfflineRegionDeleteCallback f12810a;

        /* loaded from: classes11.dex */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                OfflineRegion.this.b.deactivate();
                c.this.f12810a.onDelete();
                OfflineRegion.this.finalize();
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
                OfflineRegion.this.d = false;
                OfflineRegion.this.b.deactivate();
                c.this.f12810a.onError(this.h);
            }
        }

        public c(OfflineRegionDeleteCallback offlineRegionDeleteCallback) {
            this.f12810a = offlineRegionDeleteCallback;
        }

        @Override // com.mappls.sdk.maps.offline.OfflineRegion.OfflineRegionDeleteCallback
        public void onDelete() {
            OfflineRegion.this.g.post(new a());
        }

        @Override // com.mappls.sdk.maps.offline.OfflineRegion.OfflineRegionDeleteCallback
        public void onError(String str) {
            OfflineRegion.this.g.post(new b(str));
        }
    }

    /* loaded from: classes11.dex */
    public class d implements OfflineRegionInvalidateCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OfflineRegionInvalidateCallback f12811a;

        /* loaded from: classes11.dex */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                OfflineRegion.this.b.deactivate();
                OfflineRegionInvalidateCallback offlineRegionInvalidateCallback = d.this.f12811a;
                if (offlineRegionInvalidateCallback != null) {
                    offlineRegionInvalidateCallback.onInvalidate();
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
                OfflineRegion.this.b.deactivate();
                OfflineRegionInvalidateCallback offlineRegionInvalidateCallback = d.this.f12811a;
                if (offlineRegionInvalidateCallback != null) {
                    offlineRegionInvalidateCallback.onError(this.h);
                }
            }
        }

        public d(OfflineRegionInvalidateCallback offlineRegionInvalidateCallback) {
            this.f12811a = offlineRegionInvalidateCallback;
        }

        @Override // com.mappls.sdk.maps.offline.OfflineRegion.OfflineRegionInvalidateCallback
        public void onError(@NonNull String str) {
            OfflineRegion.this.g.post(new b(str));
        }

        @Override // com.mappls.sdk.maps.offline.OfflineRegion.OfflineRegionInvalidateCallback
        public void onInvalidate() {
            OfflineRegion.this.g.post(new a());
        }
    }

    /* loaded from: classes11.dex */
    public class e implements OfflineRegionUpdateMetadataCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OfflineRegionUpdateMetadataCallback f12812a;

        /* loaded from: classes11.dex */
        public class a implements Runnable {
            public final /* synthetic */ byte[] h;

            public a(byte[] bArr) {
                this.h = bArr;
            }

            @Override // java.lang.Runnable
            public void run() {
                OfflineRegion.this.f = this.h;
                e.this.f12812a.onUpdate(this.h);
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
                e.this.f12812a.onError(this.h);
            }
        }

        public e(OfflineRegionUpdateMetadataCallback offlineRegionUpdateMetadataCallback) {
            this.f12812a = offlineRegionUpdateMetadataCallback;
        }

        @Override // com.mappls.sdk.maps.offline.OfflineRegion.OfflineRegionUpdateMetadataCallback
        public void onError(String str) {
            OfflineRegion.this.g.post(new b(str));
        }

        @Override // com.mappls.sdk.maps.offline.OfflineRegion.OfflineRegionUpdateMetadataCallback
        public void onUpdate(byte[] bArr) {
            OfflineRegion.this.g.post(new a(bArr));
        }
    }

    static {
        LibraryLoader.load();
    }

    @Keep
    private OfflineRegion(long j, FileSource fileSource, long j2, OfflineRegionDefinition offlineRegionDefinition, byte[] bArr) {
        this.b = fileSource;
        this.c = j2;
        this.e = offlineRegionDefinition;
        this.f = bArr;
        initialize(j, fileSource);
    }

    @Keep
    private native void deleteOfflineRegion(OfflineRegionDeleteCallback offlineRegionDeleteCallback);

    @Keep
    private native void getOfflineRegionStatus(OfflineRegionStatusCallback offlineRegionStatusCallback);

    @Keep
    private native void initialize(long j, FileSource fileSource);

    @Keep
    private native void invalidateOfflineRegion(OfflineRegionInvalidateCallback offlineRegionInvalidateCallback);

    @Keep
    private native void setOfflineRegionDownloadState(int i);

    @Keep
    private native void setOfflineRegionObserver(OfflineRegionObserver offlineRegionObserver);

    @Keep
    private native void updateOfflineRegionMetadata(byte[] bArr, OfflineRegionUpdateMetadataCallback offlineRegionUpdateMetadataCallback);

    public void delete(@NonNull OfflineRegionDeleteCallback offlineRegionDeleteCallback) {
        if (this.d) {
            return;
        }
        this.d = true;
        this.b.activate();
        deleteOfflineRegion(new c(offlineRegionDeleteCallback));
    }

    public final boolean f() {
        if (this.h == 1) {
            return true;
        }
        return isDeliveringInactiveMessages();
    }

    @Keep
    public native void finalize();

    public OfflineRegionDefinition getDefinition() {
        return this.e;
    }

    public long getID() {
        return this.c;
    }

    public byte[] getMetadata() {
        return this.f;
    }

    public void getStatus(@NonNull OfflineRegionStatusCallback offlineRegionStatusCallback) {
        this.b.activate();
        getOfflineRegionStatus(new b(offlineRegionStatusCallback));
    }

    public void invalidate(@Nullable OfflineRegionInvalidateCallback offlineRegionInvalidateCallback) {
        this.b.activate();
        invalidateOfflineRegion(new d(offlineRegionInvalidateCallback));
    }

    public boolean isDeliveringInactiveMessages() {
        return this.i;
    }

    public void setDeliverInactiveMessages(boolean z) {
        this.i = z;
    }

    public void setDownloadState(int i) {
        if (this.h == i) {
            return;
        }
        if (i == 1) {
            ConnectivityReceiver.instance(this.f12807a).activate();
            this.b.activate();
        } else {
            this.b.deactivate();
            ConnectivityReceiver.instance(this.f12807a).deactivate();
        }
        this.h = i;
        setOfflineRegionDownloadState(i);
    }

    public void setObserver(@Nullable OfflineRegionObserver offlineRegionObserver) {
        setOfflineRegionObserver(new a(offlineRegionObserver));
    }

    public void updateMetadata(@NonNull byte[] bArr, @NonNull OfflineRegionUpdateMetadataCallback offlineRegionUpdateMetadataCallback) {
        updateOfflineRegionMetadata(bArr, new e(offlineRegionUpdateMetadataCallback));
    }
}
