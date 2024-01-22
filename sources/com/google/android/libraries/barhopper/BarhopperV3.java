package com.google.android.libraries.barhopper;

import android.graphics.Bitmap;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdo;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo;
import com.google.barhopper.deeplearning.BarhopperV3Options;
import com.google.photos.vision.barhopper.BarhopperProto$BarhopperResponse;
import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Objects;
/* loaded from: classes10.dex */
public class BarhopperV3 implements Closeable {
    public static final String i = BarhopperV3.class.getSimpleName();
    public long h;

    public BarhopperV3() {
        System.loadLibrary("barhopper_v3");
    }

    public static BarhopperProto$BarhopperResponse a(byte[] bArr) {
        Objects.requireNonNull(bArr);
        try {
            return BarhopperProto$BarhopperResponse.zzb(bArr, zzdo.zza());
        } catch (zzeo e) {
            throw new IllegalStateException("Received unexpected BarhopperResponse buffer: {0}", e);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        long j = this.h;
        if (j != 0) {
            closeNative(j);
            this.h = 0L;
        }
    }

    public final native void closeNative(long j);

    public void create() {
        if (this.h != 0) {
            Log.w(i, "Native context already exists.");
            return;
        }
        long createNative = createNative();
        this.h = createNative;
        if (createNative == 0) {
            throw new IllegalStateException("Failed to create native context.");
        }
    }

    public final native long createNative();

    public final native long createNativeWithClientOptions(byte[] bArr);

    @NonNull
    public BarhopperProto$BarhopperResponse recognize(int i2, int i3, int i4, @NonNull ByteBuffer byteBuffer, @NonNull RecognitionOptions recognitionOptions) {
        long j = this.h;
        if (j != 0) {
            return a(recognizeStridedBufferNative(j, i2, i3, i4, byteBuffer, recognitionOptions));
        }
        throw new IllegalStateException("Native context does not exist.");
    }

    public final native byte[] recognizeBitmapNative(long j, Bitmap bitmap, RecognitionOptions recognitionOptions);

    public final native byte[] recognizeBufferNative(long j, int i2, int i3, ByteBuffer byteBuffer, RecognitionOptions recognitionOptions);

    public final native byte[] recognizeNative(long j, int i2, int i3, byte[] bArr, RecognitionOptions recognitionOptions);

    public final native byte[] recognizeStridedBufferNative(long j, int i2, int i3, int i4, ByteBuffer byteBuffer, RecognitionOptions recognitionOptions);

    public final native byte[] recognizeStridedNative(long j, int i2, int i3, int i4, byte[] bArr, RecognitionOptions recognitionOptions);

    public void create(@NonNull BarhopperV3Options barhopperV3Options) {
        if (this.h == 0) {
            try {
                int zzE = barhopperV3Options.zzE();
                byte[] bArr = new byte[zzE];
                zzdj zzA = zzdj.zzA(bArr, 0, zzE);
                barhopperV3Options.zzaa(zzA);
                zzA.zzB();
                long createNativeWithClientOptions = createNativeWithClientOptions(bArr);
                this.h = createNativeWithClientOptions;
                if (createNativeWithClientOptions == 0) {
                    throw new IllegalArgumentException("Failed to create native context with client options.");
                }
                return;
            } catch (IOException e) {
                String name = barhopperV3Options.getClass().getName();
                throw new RuntimeException("Serializing " + name + " to a byte array threw an IOException (should never happen).", e);
            }
        }
        Log.w(i, "Native context already exists.");
    }

    @NonNull
    public BarhopperProto$BarhopperResponse recognize(int i2, int i3, int i4, @NonNull byte[] bArr, @NonNull RecognitionOptions recognitionOptions) {
        long j = this.h;
        if (j != 0) {
            return a(recognizeStridedNative(j, i2, i3, i4, bArr, recognitionOptions));
        }
        throw new IllegalStateException("Native context does not exist.");
    }

    @NonNull
    public BarhopperProto$BarhopperResponse recognize(int i2, int i3, @NonNull ByteBuffer byteBuffer, @NonNull RecognitionOptions recognitionOptions) {
        long j = this.h;
        if (j != 0) {
            return a(recognizeBufferNative(j, i2, i3, byteBuffer, recognitionOptions));
        }
        throw new IllegalStateException("Native context does not exist.");
    }

    @NonNull
    public BarhopperProto$BarhopperResponse recognize(int i2, int i3, @NonNull byte[] bArr, @NonNull RecognitionOptions recognitionOptions) {
        long j = this.h;
        if (j != 0) {
            return a(recognizeNative(j, i2, i3, bArr, recognitionOptions));
        }
        throw new IllegalStateException("Native context does not exist.");
    }

    @NonNull
    public BarhopperProto$BarhopperResponse recognize(@NonNull Bitmap bitmap, @NonNull RecognitionOptions recognitionOptions) {
        if (this.h != 0) {
            if (bitmap.getConfig() != Bitmap.Config.ARGB_8888) {
                Log.d(i, "Input bitmap config is not ARGB_8888. Converting it to ARGB_8888 from ".concat(String.valueOf(bitmap.getConfig())));
                bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, bitmap.isMutable());
            }
            return a(recognizeBitmapNative(this.h, bitmap, recognitionOptions));
        }
        throw new IllegalStateException("Native context does not exist.");
    }
}
