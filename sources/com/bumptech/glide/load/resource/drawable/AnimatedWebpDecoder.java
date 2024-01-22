package com.bumptech.glide.load.resource.drawable;

import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.ImageHeaderParserUtils;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.resource.DefaultOnHeaderDecodedListener;
import com.bumptech.glide.util.ByteBufferUtil;
import com.bumptech.glide.util.Util;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;
@RequiresApi(28)
/* loaded from: classes2.dex */
public final class AnimatedWebpDecoder {

    /* renamed from: a  reason: collision with root package name */
    public final List<ImageHeaderParser> f2484a;
    public final ArrayPool b;

    /* loaded from: classes2.dex */
    public static final class a implements Resource<Drawable> {
        public final AnimatedImageDrawable h;

        public a(AnimatedImageDrawable animatedImageDrawable) {
            this.h = animatedImageDrawable;
        }

        @Override // com.bumptech.glide.load.engine.Resource
        @NonNull
        /* renamed from: a */
        public AnimatedImageDrawable get() {
            return this.h;
        }

        @Override // com.bumptech.glide.load.engine.Resource
        @NonNull
        public Class<Drawable> getResourceClass() {
            return Drawable.class;
        }

        @Override // com.bumptech.glide.load.engine.Resource
        public int getSize() {
            return this.h.getIntrinsicWidth() * this.h.getIntrinsicHeight() * Util.getBytesPerPixel(Bitmap.Config.ARGB_8888) * 2;
        }

        @Override // com.bumptech.glide.load.engine.Resource
        public void recycle() {
            this.h.stop();
            this.h.clearAnimationCallbacks();
        }
    }

    /* loaded from: classes2.dex */
    public static final class b implements ResourceDecoder<ByteBuffer, Drawable> {

        /* renamed from: a  reason: collision with root package name */
        public final AnimatedWebpDecoder f2485a;

        public b(AnimatedWebpDecoder animatedWebpDecoder) {
            this.f2485a = animatedWebpDecoder;
        }

        @Override // com.bumptech.glide.load.ResourceDecoder
        /* renamed from: a */
        public Resource<Drawable> decode(@NonNull ByteBuffer byteBuffer, int i, int i2, @NonNull Options options) throws IOException {
            return this.f2485a.a(ImageDecoder.createSource(byteBuffer), i, i2, options);
        }

        @Override // com.bumptech.glide.load.ResourceDecoder
        /* renamed from: b */
        public boolean handles(@NonNull ByteBuffer byteBuffer, @NonNull Options options) throws IOException {
            return this.f2485a.c(byteBuffer);
        }
    }

    /* loaded from: classes2.dex */
    public static final class c implements ResourceDecoder<InputStream, Drawable> {

        /* renamed from: a  reason: collision with root package name */
        public final AnimatedWebpDecoder f2486a;

        public c(AnimatedWebpDecoder animatedWebpDecoder) {
            this.f2486a = animatedWebpDecoder;
        }

        @Override // com.bumptech.glide.load.ResourceDecoder
        /* renamed from: a */
        public Resource<Drawable> decode(@NonNull InputStream inputStream, int i, int i2, @NonNull Options options) throws IOException {
            return this.f2486a.a(ImageDecoder.createSource(ByteBufferUtil.fromStream(inputStream)), i, i2, options);
        }

        @Override // com.bumptech.glide.load.ResourceDecoder
        /* renamed from: b */
        public boolean handles(@NonNull InputStream inputStream, @NonNull Options options) throws IOException {
            return this.f2486a.b(inputStream);
        }
    }

    public AnimatedWebpDecoder(List<ImageHeaderParser> list, ArrayPool arrayPool) {
        this.f2484a = list;
        this.b = arrayPool;
    }

    public static ResourceDecoder<ByteBuffer, Drawable> byteBufferDecoder(List<ImageHeaderParser> list, ArrayPool arrayPool) {
        return new b(new AnimatedWebpDecoder(list, arrayPool));
    }

    public static ResourceDecoder<InputStream, Drawable> streamDecoder(List<ImageHeaderParser> list, ArrayPool arrayPool) {
        return new c(new AnimatedWebpDecoder(list, arrayPool));
    }

    public Resource<Drawable> a(@NonNull ImageDecoder.Source source, int i, int i2, @NonNull Options options) throws IOException {
        Drawable decodeDrawable = ImageDecoder.decodeDrawable(source, new DefaultOnHeaderDecodedListener(i, i2, options));
        if (decodeDrawable instanceof AnimatedImageDrawable) {
            return new a((AnimatedImageDrawable) decodeDrawable);
        }
        throw new IOException("Received unexpected drawable type for animated webp, failing: " + decodeDrawable);
    }

    public boolean b(InputStream inputStream) throws IOException {
        return d(ImageHeaderParserUtils.getType(this.f2484a, inputStream, this.b));
    }

    public boolean c(ByteBuffer byteBuffer) throws IOException {
        return d(ImageHeaderParserUtils.getType(this.f2484a, byteBuffer));
    }

    public final boolean d(ImageHeaderParser.ImageType imageType) {
        return imageType == ImageHeaderParser.ImageType.ANIMATED_WEBP;
    }
}
