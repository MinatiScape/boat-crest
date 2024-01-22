package com.google.android.youtube.player.internal;

import android.app.Activity;
import android.content.Context;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.google.android.youtube.player.internal.t;
import com.google.android.youtube.player.internal.w;
/* loaded from: classes10.dex */
public abstract class aa {

    /* renamed from: a  reason: collision with root package name */
    public static final aa f10490a = b();

    public static aa a() {
        return f10490a;
    }

    public static aa b() {
        try {
            try {
                return (aa) Class.forName("com.google.android.youtube.api.locallylinked.LocallyLinkedFactory").asSubclass(aa.class).newInstance();
            } catch (IllegalAccessException e) {
                throw new IllegalStateException(e);
            } catch (InstantiationException e2) {
                throw new IllegalStateException(e2);
            }
        } catch (ClassNotFoundException unused) {
            return new ac();
        }
    }

    public abstract a a(b bVar, YouTubeThumbnailView youTubeThumbnailView);

    public abstract b a(Context context, String str, t.a aVar, t.b bVar);

    public abstract d a(Activity activity, b bVar, boolean z) throws w.a;
}
