package com.coveiot.utils.utility;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
/* loaded from: classes9.dex */
public class GlideUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7625a = "GlideUtils";

    /* loaded from: classes9.dex */
    public static class a extends BitmapImageViewTarget {
        public final /* synthetic */ Context p;
        public final /* synthetic */ ImageView q;
        public final /* synthetic */ ImageLodeListener r;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(ImageView imageView, Context context, ImageView imageView2, ImageLodeListener imageLodeListener) {
            super(imageView);
            this.p = context;
            this.q = imageView2;
            this.r = imageLodeListener;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.bumptech.glide.request.target.BitmapImageViewTarget, com.bumptech.glide.request.target.ImageViewTarget
        public void setResource(Bitmap bitmap) {
            RoundedBitmapDrawable create = RoundedBitmapDrawableFactory.create(this.p.getResources(), bitmap);
            create.setCircular(true);
            this.q.setImageDrawable(create);
            this.r.onImageLoaded();
        }
    }

    public static GlideUrl a(String str) {
        GlideUrl glideUrl = new GlideUrl(str, new LazyHeaders.Builder().addHeader("x-clove-api", "101025").build());
        String str2 = f7625a;
        LogHelper.d(str2, "Glide Url : " + glideUrl.toStringUrl());
        return glideUrl;
    }

    public static void loadCircularImage(Context context, String str, ImageView imageView, ImageLodeListener imageLodeListener) {
        Glide.with(context).asBitmap().m21load(str).centerCrop().into((RequestBuilder) new a(imageView, context, imageView, imageLodeListener));
    }

    public static void loadImage(Context context, ImageView imageView, String str) {
        try {
            Glide.with(context.getApplicationContext()).m29load((Object) a(str)).fitCenter().skipMemoryCache(true).into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            String str2 = f7625a;
            LogHelper.d(str2, "Exception when Gliding...." + str);
        }
    }

    public static void loadImageNoCache(Context context, ImageView imageView, String str) {
        try {
            Glide.with(context.getApplicationContext()).m29load((Object) a(str)).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
        } catch (Exception unused) {
            String str2 = f7625a;
            LogHelper.d(str2, "Exception when Gliding...." + str);
        }
    }

    public static void loadImageWithSignature(Context context, ImageView imageView, String str, String str2) {
        try {
            Glide.with(context.getApplicationContext()).m29load((Object) a(str)).skipMemoryCache(true).into(imageView);
        } catch (Exception unused) {
            String str3 = f7625a;
            LogHelper.d(str3, "Exception when Gliding...." + str);
        }
    }

    public static void loadScaledImage(Context context, String str, SimpleTarget<Bitmap> simpleTarget) {
        try {
            Glide.with(context.getApplicationContext()).asBitmap().m20load((Object) a(str)).override(100, 100).skipMemoryCache(true).into((RequestBuilder) simpleTarget);
        } catch (Exception unused) {
            String str2 = f7625a;
            LogHelper.d(str2, "Exception when Gliding...." + str);
        }
    }

    public static void loadImageWithSignature(Context context, ImageView imageView, String str, RequestListener requestListener, String str2) {
        try {
            Glide.with(context.getApplicationContext()).m29load((Object) a(str)).skipMemoryCache(true).listener(requestListener).into(imageView);
        } catch (Exception unused) {
            String str3 = f7625a;
            LogHelper.d(str3, "Exception when Gliding...." + str);
        }
    }

    public static void loadImageNoCache(Context context, String str, SimpleTarget<Bitmap> simpleTarget) {
        try {
            Glide.with(context.getApplicationContext()).asBitmap().m20load((Object) a(str)).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into((RequestBuilder) simpleTarget);
        } catch (Exception unused) {
            String str2 = f7625a;
            LogHelper.d(str2, "Exception when Gliding...." + str);
        }
    }

    public static void loadImage(Context context, ImageView imageView, String str, RequestListener requestListener) {
        try {
            Glide.with(context.getApplicationContext()).m29load((Object) a(str)).fitCenter().skipMemoryCache(true).listener(requestListener).into(imageView);
        } catch (Exception unused) {
            String str2 = f7625a;
            LogHelper.d(str2, "Exception when Gliding...." + str);
        }
    }

    public static void loadImageWithSignature(Context context, SimpleTarget<Bitmap> simpleTarget, String str, String str2) {
        try {
            Glide.with(context.getApplicationContext()).asBitmap().m20load((Object) a(str)).skipMemoryCache(true).into((RequestBuilder) simpleTarget);
        } catch (Exception unused) {
            String str3 = f7625a;
            LogHelper.d(str3, "Exception when Gliding...." + str);
        }
    }

    public static void loadImageNoCache(Context context, ImageView imageView, String str, RequestListener requestListener) {
        try {
            Glide.with(context.getApplicationContext()).m29load((Object) a(str)).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).listener(requestListener).into(imageView);
        } catch (Exception unused) {
            String str2 = f7625a;
            LogHelper.d(str2, "Exception when Gliding...." + str);
        }
    }

    public static void loadImage(Context context, String str, SimpleTarget<Bitmap> simpleTarget) {
        try {
            Glide.with(context.getApplicationContext()).asBitmap().m20load((Object) a(str)).skipMemoryCache(true).into((RequestBuilder) simpleTarget);
        } catch (Exception unused) {
            String str2 = f7625a;
            LogHelper.d(str2, "Exception when Gliding...." + str);
        }
    }
}
