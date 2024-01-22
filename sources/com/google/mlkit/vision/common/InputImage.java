package com.google.mlkit.vision.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.Image;
import android.net.Uri;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_vision_common.zzms;
import com.google.android.gms.internal.mlkit_vision_common.zzmu;
import com.google.mlkit.common.sdkinternal.MLTaskInput;
import com.google.mlkit.vision.common.internal.ImageConvertUtils;
import com.google.mlkit.vision.common.internal.ImageUtils;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes10.dex */
public class InputImage implements MLTaskInput {
    @KeepForSdk
    public static final int IMAGE_FORMAT_BITMAP = -1;
    public static final int IMAGE_FORMAT_NV21 = 17;
    public static final int IMAGE_FORMAT_YUV_420_888 = 35;
    public static final int IMAGE_FORMAT_YV12 = 842094169;
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public volatile Bitmap f11633a;
    @Nullable
    public volatile ByteBuffer b;
    @Nullable
    public volatile b c;
    public final int d;
    public final int e;
    public final int f;
    @ImageFormat
    public final int g;
    @Nullable
    public final Matrix h;

    @Retention(RetentionPolicy.CLASS)
    /* loaded from: classes10.dex */
    public @interface ImageFormat {
    }

    public InputImage(@NonNull Bitmap bitmap, int i) {
        this.f11633a = (Bitmap) Preconditions.checkNotNull(bitmap);
        this.d = bitmap.getWidth();
        this.e = bitmap.getHeight();
        a(i);
        this.f = i;
        this.g = -1;
        this.h = null;
    }

    public static int a(int i) {
        boolean z = true;
        if (i != 0 && i != 90 && i != 180) {
            if (i == 270) {
                i = 270;
            } else {
                z = false;
            }
        }
        Preconditions.checkArgument(z, "Invalid rotation. Only 0, 90, 180, 270 are supported currently.");
        return i;
    }

    public static InputImage b(@NonNull Image image, int i, @Nullable Matrix matrix) {
        InputImage inputImage;
        int limit;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Preconditions.checkNotNull(image, "Please provide a valid image");
        a(i);
        boolean z = true;
        if (image.getFormat() != 256 && image.getFormat() != 35) {
            z = false;
        }
        Preconditions.checkArgument(z, "Only JPEG and YUV_420_888 are supported now");
        Image.Plane[] planes = image.getPlanes();
        if (image.getFormat() == 256) {
            limit = image.getPlanes()[0].getBuffer().limit();
            inputImage = new InputImage(ImageConvertUtils.getInstance().convertJpegToUpRightBitmap(image, i), 0);
        } else {
            for (Image.Plane plane : planes) {
                if (plane.getBuffer() != null) {
                    plane.getBuffer().rewind();
                }
            }
            inputImage = new InputImage(image, image.getWidth(), image.getHeight(), i, matrix);
            limit = (image.getPlanes()[0].getBuffer().limit() * 3) / 2;
        }
        int i2 = limit;
        InputImage inputImage2 = inputImage;
        c(image.getFormat(), 5, elapsedRealtime, image.getHeight(), image.getWidth(), i2, i);
        return inputImage2;
    }

    public static void c(int i, int i2, long j, int i3, int i4, int i5, int i6) {
        zzmu.zza(zzms.zzb("vision-common"), i, i2, j, i3, i4, i5, i6);
    }

    @NonNull
    public static InputImage fromBitmap(@NonNull Bitmap bitmap, int i) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        InputImage inputImage = new InputImage(bitmap, i);
        c(-1, 1, elapsedRealtime, bitmap.getHeight(), bitmap.getWidth(), bitmap.getAllocationByteCount(), i);
        return inputImage;
    }

    @NonNull
    public static InputImage fromByteArray(@NonNull byte[] bArr, int i, int i2, int i3, @ImageFormat int i4) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        InputImage inputImage = new InputImage(ByteBuffer.wrap((byte[]) Preconditions.checkNotNull(bArr)), i, i2, i3, i4);
        c(i4, 2, elapsedRealtime, i2, i, bArr.length, i3);
        return inputImage;
    }

    @NonNull
    public static InputImage fromByteBuffer(@NonNull ByteBuffer byteBuffer, int i, int i2, int i3, @ImageFormat int i4) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        InputImage inputImage = new InputImage(byteBuffer, i, i2, i3, i4);
        c(i4, 3, elapsedRealtime, i2, i, byteBuffer.limit(), i3);
        return inputImage;
    }

    @NonNull
    public static InputImage fromFilePath(@NonNull Context context, @NonNull Uri uri) throws IOException {
        Preconditions.checkNotNull(context, "Please provide a valid Context");
        Preconditions.checkNotNull(uri, "Please provide a valid imageUri");
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Bitmap zza = ImageUtils.getInstance().zza(context.getContentResolver(), uri);
        InputImage inputImage = new InputImage(zza, 0);
        c(-1, 4, elapsedRealtime, zza.getHeight(), zza.getWidth(), zza.getAllocationByteCount(), 0);
        return inputImage;
    }

    @NonNull
    public static InputImage fromMediaImage(@NonNull Image image, int i) {
        return b(image, i, null);
    }

    @Nullable
    @KeepForSdk
    public Bitmap getBitmapInternal() {
        return this.f11633a;
    }

    @Nullable
    @KeepForSdk
    public ByteBuffer getByteBuffer() {
        return this.b;
    }

    @Nullable
    @KeepForSdk
    public Matrix getCoordinatesMatrix() {
        return this.h;
    }

    @KeepForSdk
    @ImageFormat
    public int getFormat() {
        return this.g;
    }

    @KeepForSdk
    public int getHeight() {
        return this.e;
    }

    @Nullable
    @KeepForSdk
    public Image getMediaImage() {
        if (this.c == null) {
            return null;
        }
        return this.c.a();
    }

    @Nullable
    @KeepForSdk
    public Image.Plane[] getPlanes() {
        if (this.c == null) {
            return null;
        }
        return this.c.b();
    }

    @KeepForSdk
    public int getRotationDegrees() {
        return this.f;
    }

    @KeepForSdk
    public int getWidth() {
        return this.d;
    }

    @NonNull
    @KeepForSdk
    public static InputImage fromMediaImage(@NonNull Image image, int i, @NonNull Matrix matrix) {
        Preconditions.checkArgument(image.getFormat() == 35, "Only YUV_420_888 is supported now");
        return b(image, i, matrix);
    }

    public InputImage(@NonNull Image image, int i, int i2, int i3, @Nullable Matrix matrix) {
        Preconditions.checkNotNull(image);
        this.c = new b(image);
        this.d = i;
        this.e = i2;
        a(i3);
        this.f = i3;
        this.g = 35;
        this.h = matrix;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public InputImage(@androidx.annotation.NonNull java.nio.ByteBuffer r5, int r6, int r7, int r8, @com.google.mlkit.vision.common.InputImage.ImageFormat int r9) {
        /*
            r4 = this;
            r4.<init>()
            r0 = 0
            r1 = 17
            r2 = 1
            r3 = 842094169(0x32315659, float:1.0322389E-8)
            if (r9 == r3) goto L12
            if (r9 != r1) goto L10
            r9 = r1
            goto L12
        L10:
            r1 = r0
            goto L13
        L12:
            r1 = r2
        L13:
            com.google.android.gms.common.internal.Preconditions.checkArgument(r1)
            java.lang.Object r1 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r5)
            java.nio.ByteBuffer r1 = (java.nio.ByteBuffer) r1
            r4.b = r1
            int r1 = r5.limit()
            int r3 = r6 * r7
            if (r1 <= r3) goto L27
            r0 = r2
        L27:
            java.lang.String r1 = "Image dimension, ByteBuffer size and format don't match. Please check if the ByteBuffer is in the decalred format."
            com.google.android.gms.common.internal.Preconditions.checkArgument(r0, r1)
            r5.rewind()
            r4.d = r6
            r4.e = r7
            a(r8)
            r4.f = r8
            r4.g = r9
            r5 = 0
            r4.h = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.vision.common.InputImage.<init>(java.nio.ByteBuffer, int, int, int, int):void");
    }
}
