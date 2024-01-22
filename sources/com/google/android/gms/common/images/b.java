package com.google.android.gms.common.images;

import android.content.Context;
import android.net.Uri;
import android.os.SystemClock;
import com.google.android.gms.common.images.ImageManager;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.internal.base.zam;
import java.util.HashSet;
import java.util.Map;
/* loaded from: classes6.dex */
public final class b implements Runnable {
    public final zag h;
    public final /* synthetic */ ImageManager i;

    public b(ImageManager imageManager, zag zagVar) {
        this.i = imageManager;
        this.h = zagVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Map map;
        zam zamVar;
        Map map2;
        Map map3;
        Object obj;
        HashSet hashSet;
        HashSet hashSet2;
        Map map4;
        Map map5;
        Map map6;
        zam zamVar2;
        Map map7;
        Asserts.checkMainThread("LoadImageRunnable must be executed on the main thread");
        map = this.i.e;
        ImageManager.ImageReceiver imageReceiver = (ImageManager.ImageReceiver) map.get(this.h);
        if (imageReceiver != null) {
            map7 = this.i.e;
            map7.remove(this.h);
            imageReceiver.c(this.h);
        }
        zag zagVar = this.h;
        d dVar = zagVar.f8322a;
        Uri uri = dVar.f8321a;
        if (uri != null) {
            map2 = this.i.g;
            Long l = (Long) map2.get(uri);
            if (l != null) {
                if (SystemClock.elapsedRealtime() - l.longValue() < 3600000) {
                    zag zagVar2 = this.h;
                    ImageManager imageManager = this.i;
                    Context context = imageManager.f8319a;
                    zamVar2 = imageManager.d;
                    zagVar2.a(context, zamVar2, true);
                    return;
                }
                map6 = this.i.g;
                map6.remove(dVar.f8321a);
            }
            this.h.zaa(null, false, true, false);
            map3 = this.i.f;
            ImageManager.ImageReceiver imageReceiver2 = (ImageManager.ImageReceiver) map3.get(dVar.f8321a);
            if (imageReceiver2 == null) {
                imageReceiver2 = new ImageManager.ImageReceiver(dVar.f8321a);
                map5 = this.i.f;
                map5.put(dVar.f8321a, imageReceiver2);
            }
            imageReceiver2.b(this.h);
            zag zagVar3 = this.h;
            if (!(zagVar3 instanceof zaf)) {
                map4 = this.i.e;
                map4.put(zagVar3, imageReceiver2);
            }
            obj = ImageManager.h;
            synchronized (obj) {
                hashSet = ImageManager.i;
                if (!hashSet.contains(dVar.f8321a)) {
                    hashSet2 = ImageManager.i;
                    hashSet2.add(dVar.f8321a);
                    imageReceiver2.d();
                }
            }
            return;
        }
        ImageManager imageManager2 = this.i;
        Context context2 = imageManager2.f8319a;
        zamVar = imageManager2.d;
        zagVar.a(context2, zamVar, true);
    }
}
