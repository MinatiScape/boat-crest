package com.bumptech.glide.load.engine.prefill;

import android.graphics.Bitmap;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.engine.prefill.PreFillType;
import com.bumptech.glide.util.Util;
import java.util.HashMap;
/* loaded from: classes2.dex */
public final class BitmapPreFiller {

    /* renamed from: a  reason: collision with root package name */
    public final MemoryCache f2391a;
    public final BitmapPool b;
    public final DecodeFormat c;
    public a d;

    public BitmapPreFiller(MemoryCache memoryCache, BitmapPool bitmapPool, DecodeFormat decodeFormat) {
        this.f2391a = memoryCache;
        this.b = bitmapPool;
        this.c = decodeFormat;
    }

    public static int b(PreFillType preFillType) {
        return Util.getBitmapByteSize(preFillType.d(), preFillType.b(), preFillType.a());
    }

    @VisibleForTesting
    public b a(PreFillType... preFillTypeArr) {
        long maxSize = (this.f2391a.getMaxSize() - this.f2391a.getCurrentSize()) + this.b.getMaxSize();
        int i = 0;
        for (PreFillType preFillType : preFillTypeArr) {
            i += preFillType.c();
        }
        float f = ((float) maxSize) / i;
        HashMap hashMap = new HashMap();
        for (PreFillType preFillType2 : preFillTypeArr) {
            hashMap.put(preFillType2, Integer.valueOf(Math.round(preFillType2.c() * f) / b(preFillType2)));
        }
        return new b(hashMap);
    }

    public void preFill(PreFillType.Builder... builderArr) {
        Bitmap.Config config;
        a aVar = this.d;
        if (aVar != null) {
            aVar.b();
        }
        PreFillType[] preFillTypeArr = new PreFillType[builderArr.length];
        for (int i = 0; i < builderArr.length; i++) {
            PreFillType.Builder builder = builderArr[i];
            if (builder.b() == null) {
                if (this.c == DecodeFormat.PREFER_ARGB_8888) {
                    config = Bitmap.Config.ARGB_8888;
                } else {
                    config = Bitmap.Config.RGB_565;
                }
                builder.setConfig(config);
            }
            preFillTypeArr[i] = builder.a();
        }
        a aVar2 = new a(this.b, this.f2391a, a(preFillTypeArr));
        this.d = aVar2;
        Util.postOnUiThread(aVar2);
    }
}
