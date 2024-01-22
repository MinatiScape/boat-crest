package androidx.media;

import android.content.Context;
import android.media.session.MediaSessionManager;
import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.media.b;
import androidx.media.c;
/* loaded from: classes.dex */
public final class MediaSessionManager {
    public static final boolean b = Log.isLoggable("MediaSessionManager", 3);
    public static final Object c = new Object();
    public static volatile MediaSessionManager d;

    /* renamed from: a  reason: collision with root package name */
    public a f1410a;

    /* loaded from: classes.dex */
    public interface a {
        boolean a(b bVar);
    }

    /* loaded from: classes.dex */
    public interface b {
        int a();

        int b();

        String getPackageName();
    }

    public MediaSessionManager(Context context) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 28) {
            this.f1410a = new androidx.media.b(context);
        } else if (i >= 21) {
            this.f1410a = new androidx.media.a(context);
        } else {
            this.f1410a = new c(context);
        }
    }

    @NonNull
    public static MediaSessionManager getSessionManager(@NonNull Context context) {
        MediaSessionManager mediaSessionManager = d;
        if (mediaSessionManager == null) {
            synchronized (c) {
                mediaSessionManager = d;
                if (mediaSessionManager == null) {
                    d = new MediaSessionManager(context.getApplicationContext());
                    mediaSessionManager = d;
                }
            }
        }
        return mediaSessionManager;
    }

    public boolean isTrustedForMediaControl(@NonNull RemoteUserInfo remoteUserInfo) {
        if (remoteUserInfo != null) {
            return this.f1410a.a(remoteUserInfo.f1411a);
        }
        throw new IllegalArgumentException("userInfo should not be null");
    }

    /* loaded from: classes.dex */
    public static final class RemoteUserInfo {
        public static final String LEGACY_CONTROLLER = "android.media.session.MediaController";

        /* renamed from: a  reason: collision with root package name */
        public b f1411a;

        public RemoteUserInfo(@NonNull String str, int i, int i2) {
            if (Build.VERSION.SDK_INT >= 28) {
                this.f1411a = new b.a(str, i, i2);
            } else {
                this.f1411a = new c.a(str, i, i2);
            }
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof RemoteUserInfo) {
                return this.f1411a.equals(((RemoteUserInfo) obj).f1411a);
            }
            return false;
        }

        @NonNull
        public String getPackageName() {
            return this.f1411a.getPackageName();
        }

        public int getPid() {
            return this.f1411a.b();
        }

        public int getUid() {
            return this.f1411a.a();
        }

        public int hashCode() {
            return this.f1411a.hashCode();
        }

        @RequiresApi(28)
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteUserInfo(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
            this.f1411a = new b.a(remoteUserInfo);
        }
    }
}
