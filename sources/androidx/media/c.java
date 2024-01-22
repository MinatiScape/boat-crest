package androidx.media;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.util.ObjectsCompat;
import androidx.media.MediaSessionManager;
/* loaded from: classes.dex */
public class c implements MediaSessionManager.a {
    public static final boolean c = MediaSessionManager.b;

    /* renamed from: a  reason: collision with root package name */
    public Context f1417a;
    public ContentResolver b;

    /* loaded from: classes.dex */
    public static class a implements MediaSessionManager.b {

        /* renamed from: a  reason: collision with root package name */
        public String f1418a;
        public int b;
        public int c;

        public a(String str, int i, int i2) {
            this.f1418a = str;
            this.b = i;
            this.c = i2;
        }

        @Override // androidx.media.MediaSessionManager.b
        public int a() {
            return this.c;
        }

        @Override // androidx.media.MediaSessionManager.b
        public int b() {
            return this.b;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof a) {
                a aVar = (a) obj;
                return TextUtils.equals(this.f1418a, aVar.f1418a) && this.b == aVar.b && this.c == aVar.c;
            }
            return false;
        }

        @Override // androidx.media.MediaSessionManager.b
        public String getPackageName() {
            return this.f1418a;
        }

        public int hashCode() {
            return ObjectsCompat.hash(this.f1418a, Integer.valueOf(this.b), Integer.valueOf(this.c));
        }
    }

    public c(Context context) {
        this.f1417a = context;
        this.b = context.getContentResolver();
    }

    @Override // androidx.media.MediaSessionManager.a
    public boolean a(@NonNull MediaSessionManager.b bVar) {
        try {
            if (this.f1417a.getPackageManager().getApplicationInfo(bVar.getPackageName(), 0).uid == bVar.a()) {
                return d(bVar, "android.permission.STATUS_BAR_SERVICE") || d(bVar, "android.permission.MEDIA_CONTENT_CONTROL") || bVar.a() == 1000 || c(bVar);
            }
            if (c) {
                Log.d("MediaSessionManager", "Package name " + bVar.getPackageName() + " doesn't match with the uid " + bVar.a());
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            if (c) {
                Log.d("MediaSessionManager", "Package " + bVar.getPackageName() + " doesn't exist");
            }
            return false;
        }
    }

    public Context b() {
        return this.f1417a;
    }

    public boolean c(@NonNull MediaSessionManager.b bVar) {
        String string = Settings.Secure.getString(this.b, "enabled_notification_listeners");
        if (string != null) {
            for (String str : string.split(":")) {
                ComponentName unflattenFromString = ComponentName.unflattenFromString(str);
                if (unflattenFromString != null && unflattenFromString.getPackageName().equals(bVar.getPackageName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean d(MediaSessionManager.b bVar, String str) {
        return bVar.b() < 0 ? this.f1417a.getPackageManager().checkPermission(str, bVar.getPackageName()) == 0 : this.f1417a.checkPermission(str, bVar.b(), bVar.a()) == 0;
    }
}
