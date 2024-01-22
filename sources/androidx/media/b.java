package androidx.media;

import android.content.Context;
import android.media.session.MediaSessionManager;
import androidx.annotation.RequiresApi;
import androidx.core.util.ObjectsCompat;
import androidx.media.MediaSessionManager;
@RequiresApi(28)
/* loaded from: classes.dex */
public class b extends androidx.media.a {
    public android.media.session.MediaSessionManager d;

    public b(Context context) {
        super(context);
        this.d = (android.media.session.MediaSessionManager) context.getSystemService("media_session");
    }

    @Override // androidx.media.a, androidx.media.c, androidx.media.MediaSessionManager.a
    public boolean a(MediaSessionManager.b bVar) {
        if (bVar instanceof a) {
            return this.d.isTrustedForMediaControl(((a) bVar).f1416a);
        }
        return false;
    }

    /* loaded from: classes.dex */
    public static final class a implements MediaSessionManager.b {

        /* renamed from: a  reason: collision with root package name */
        public final MediaSessionManager.RemoteUserInfo f1416a;

        public a(String str, int i, int i2) {
            this.f1416a = new MediaSessionManager.RemoteUserInfo(str, i, i2);
        }

        @Override // androidx.media.MediaSessionManager.b
        public int a() {
            return this.f1416a.getUid();
        }

        @Override // androidx.media.MediaSessionManager.b
        public int b() {
            return this.f1416a.getPid();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof a) {
                return this.f1416a.equals(((a) obj).f1416a);
            }
            return false;
        }

        @Override // androidx.media.MediaSessionManager.b
        public String getPackageName() {
            return this.f1416a.getPackageName();
        }

        public int hashCode() {
            return ObjectsCompat.hash(this.f1416a);
        }

        public a(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
            this.f1416a = remoteUserInfo;
        }
    }
}
