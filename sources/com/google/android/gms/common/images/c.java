package com.google.android.gms.common.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.SystemClock;
import androidx.annotation.Nullable;
import com.google.android.gms.common.images.ImageManager;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.internal.base.zam;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
/* loaded from: classes6.dex */
public final class c implements Runnable {
    public final Uri h;
    @Nullable
    public final Bitmap i;
    public final CountDownLatch j;
    public final /* synthetic */ ImageManager k;

    public c(ImageManager imageManager, @Nullable Uri uri, Bitmap bitmap, boolean z, CountDownLatch countDownLatch) {
        this.k = imageManager;
        this.h = uri;
        this.i = bitmap;
        this.j = countDownLatch;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Map map;
        Object obj;
        HashSet hashSet;
        ArrayList arrayList;
        Map map2;
        zam zamVar;
        Map map3;
        Asserts.checkMainThread("OnBitmapLoadedRunnable must be executed in the main thread");
        Bitmap bitmap = this.i;
        map = this.k.f;
        ImageManager.ImageReceiver imageReceiver = (ImageManager.ImageReceiver) map.remove(this.h);
        if (imageReceiver != null) {
            arrayList = imageReceiver.i;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                zag zagVar = (zag) arrayList.get(i);
                Bitmap bitmap2 = this.i;
                if (bitmap2 != null && bitmap != null) {
                    zagVar.b(this.k.f8319a, bitmap2, false);
                } else {
                    map2 = this.k.g;
                    map2.put(this.h, Long.valueOf(SystemClock.elapsedRealtime()));
                    ImageManager imageManager = this.k;
                    Context context = imageManager.f8319a;
                    zamVar = imageManager.d;
                    zagVar.a(context, zamVar, false);
                }
                if (!(zagVar instanceof zaf)) {
                    map3 = this.k.e;
                    map3.remove(zagVar);
                }
            }
        }
        this.j.countDown();
        obj = ImageManager.h;
        synchronized (obj) {
            hashSet = ImageManager.i;
            hashSet.remove(this.h);
        }
    }
}
