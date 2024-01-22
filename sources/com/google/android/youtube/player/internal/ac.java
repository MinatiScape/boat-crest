package com.google.android.youtube.player.internal;

import android.app.Activity;
import android.content.Context;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.google.android.youtube.player.internal.t;
import com.google.android.youtube.player.internal.w;
/* loaded from: classes10.dex */
public final class ac extends aa {
    @Override // com.google.android.youtube.player.internal.aa
    public final a a(b bVar, YouTubeThumbnailView youTubeThumbnailView) {
        return new p(bVar, youTubeThumbnailView);
    }

    @Override // com.google.android.youtube.player.internal.aa
    public final b a(Context context, String str, t.a aVar, t.b bVar) {
        return new o(context, str, context.getPackageName(), z.d(context), aVar, bVar);
    }

    @Override // com.google.android.youtube.player.internal.aa
    public final d a(Activity activity, b bVar, boolean z) throws w.a {
        return w.a(activity, bVar.a(), z);
    }
}
