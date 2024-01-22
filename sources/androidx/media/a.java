package androidx.media;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.media.MediaSessionManager;
@RequiresApi(21)
/* loaded from: classes.dex */
public class a extends c {
    public a(Context context) {
        super(context);
        this.f1417a = context;
    }

    @Override // androidx.media.c, androidx.media.MediaSessionManager.a
    public boolean a(@NonNull MediaSessionManager.b bVar) {
        return e(bVar) || super.a(bVar);
    }

    public final boolean e(@NonNull MediaSessionManager.b bVar) {
        return b().checkPermission("android.permission.MEDIA_CONTENT_CONTROL", bVar.b(), bVar.a()) == 0;
    }
}
