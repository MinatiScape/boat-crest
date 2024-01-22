package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import java.io.IOException;
import java.nio.ByteBuffer;
@RequiresApi(api = 28)
/* loaded from: classes2.dex */
public final class ByteBufferBitmapImageDecoderResourceDecoder implements ResourceDecoder<ByteBuffer, Bitmap> {

    /* renamed from: a  reason: collision with root package name */
    public final BitmapImageDecoderResourceDecoder f2450a = new BitmapImageDecoderResourceDecoder();

    @Override // com.bumptech.glide.load.ResourceDecoder
    public boolean handles(@NonNull ByteBuffer byteBuffer, @NonNull Options options) throws IOException {
        return true;
    }

    @Override // com.bumptech.glide.load.ResourceDecoder
    public Resource<Bitmap> decode(@NonNull ByteBuffer byteBuffer, int i, int i2, @NonNull Options options) throws IOException {
        return this.f2450a.decode(ImageDecoder.createSource(byteBuffer), i, i2, options);
    }
}
