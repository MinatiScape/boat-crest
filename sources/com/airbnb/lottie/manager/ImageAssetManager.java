package com.airbnb.lottie.manager;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import androidx.annotation.Nullable;
import com.airbnb.lottie.ImageAssetDelegate;
import com.airbnb.lottie.LottieImageAsset;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.Utils;
import java.io.IOException;
import java.util.Map;
/* loaded from: classes.dex */
public class ImageAssetManager {
    public static final Object e = new Object();
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Context f2022a;
    public final String b;
    @Nullable
    public ImageAssetDelegate c;
    public final Map<String, LottieImageAsset> d;

    public ImageAssetManager(Drawable.Callback callback, String str, ImageAssetDelegate imageAssetDelegate, Map<String, LottieImageAsset> map) {
        if (!TextUtils.isEmpty(str) && str.charAt(str.length() - 1) != '/') {
            this.b = str + '/';
        } else {
            this.b = str;
        }
        this.d = map;
        setDelegate(imageAssetDelegate);
        if (!(callback instanceof View)) {
            this.f2022a = null;
        } else {
            this.f2022a = ((View) callback).getContext().getApplicationContext();
        }
    }

    public final Bitmap a(String str, @Nullable Bitmap bitmap) {
        synchronized (e) {
            this.d.get(str).setBitmap(bitmap);
        }
        return bitmap;
    }

    @Nullable
    public Bitmap bitmapForId(String str) {
        LottieImageAsset lottieImageAsset = this.d.get(str);
        if (lottieImageAsset == null) {
            return null;
        }
        Bitmap bitmap = lottieImageAsset.getBitmap();
        if (bitmap != null) {
            return bitmap;
        }
        ImageAssetDelegate imageAssetDelegate = this.c;
        if (imageAssetDelegate != null) {
            Bitmap fetchBitmap = imageAssetDelegate.fetchBitmap(lottieImageAsset);
            if (fetchBitmap != null) {
                a(str, fetchBitmap);
            }
            return fetchBitmap;
        }
        Context context = this.f2022a;
        if (context == null) {
            return null;
        }
        String fileName = lottieImageAsset.getFileName();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = true;
        options.inDensity = 160;
        if (fileName.startsWith("data:") && fileName.indexOf("base64,") > 0) {
            try {
                byte[] decode = Base64.decode(fileName.substring(fileName.indexOf(44) + 1), 0);
                return a(str, BitmapFactory.decodeByteArray(decode, 0, decode.length, options));
            } catch (IllegalArgumentException e2) {
                Logger.warning("data URL did not have correct base64 format.", e2);
                return null;
            }
        }
        try {
            if (!TextUtils.isEmpty(this.b)) {
                AssetManager assets = context.getAssets();
                try {
                    Bitmap decodeStream = BitmapFactory.decodeStream(assets.open(this.b + fileName), null, options);
                    if (decodeStream == null) {
                        Logger.warning("Decoded image `" + str + "` is null.");
                        return null;
                    }
                    return a(str, Utils.resizeBitmapIfNeeded(decodeStream, lottieImageAsset.getWidth(), lottieImageAsset.getHeight()));
                } catch (IllegalArgumentException e3) {
                    Logger.warning("Unable to decode image `" + str + "`.", e3);
                    return null;
                }
            }
            throw new IllegalStateException("You must set an images folder before loading an image. Set it with LottieComposition#setImagesFolder or LottieDrawable#setImagesFolder");
        } catch (IOException e4) {
            Logger.warning("Unable to open asset.", e4);
            return null;
        }
    }

    @Nullable
    public LottieImageAsset getImageAssetById(String str) {
        return this.d.get(str);
    }

    public boolean hasSameContext(Context context) {
        return (context == null && this.f2022a == null) || this.f2022a.equals(context);
    }

    public void setDelegate(@Nullable ImageAssetDelegate imageAssetDelegate) {
        this.c = imageAssetDelegate;
    }

    @Nullable
    public Bitmap updateBitmap(String str, @Nullable Bitmap bitmap) {
        if (bitmap == null) {
            LottieImageAsset lottieImageAsset = this.d.get(str);
            Bitmap bitmap2 = lottieImageAsset.getBitmap();
            lottieImageAsset.setBitmap(null);
            return bitmap2;
        }
        Bitmap bitmap3 = this.d.get(str).getBitmap();
        a(str, bitmap);
        return bitmap3;
    }
}
