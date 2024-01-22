package com.google.mlkit.vision.common.internal;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.Image;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.vision.common.InputImage;
import java.nio.ByteBuffer;
@KeepForSdk
/* loaded from: classes10.dex */
public class ImageUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final GmsLogger f11639a = new GmsLogger("MLKitImageUtils", "");
    public static final ImageUtils b = new ImageUtils();

    @NonNull
    @KeepForSdk
    public static ImageUtils getInstance() {
        return b;
    }

    @NonNull
    @KeepForSdk
    public IObjectWrapper getImageDataWrapper(@NonNull InputImage inputImage) throws MlKitException {
        int format = inputImage.getFormat();
        if (format != -1) {
            if (format != 17) {
                if (format == 35) {
                    return ObjectWrapper.wrap(inputImage.getMediaImage());
                }
                if (format != 842094169) {
                    int format2 = inputImage.getFormat();
                    throw new MlKitException("Unsupported image format: " + format2, 3);
                }
            }
            return ObjectWrapper.wrap((ByteBuffer) Preconditions.checkNotNull(inputImage.getByteBuffer()));
        }
        return ObjectWrapper.wrap((Bitmap) Preconditions.checkNotNull(inputImage.getBitmapInternal()));
    }

    @KeepForSdk
    public int getMobileVisionImageFormat(@NonNull InputImage inputImage) {
        return inputImage.getFormat();
    }

    @KeepForSdk
    public int getMobileVisionImageSize(@NonNull InputImage inputImage) {
        if (inputImage.getFormat() == -1) {
            return ((Bitmap) Preconditions.checkNotNull(inputImage.getBitmapInternal())).getAllocationByteCount();
        }
        if (inputImage.getFormat() != 17 && inputImage.getFormat() != 842094169) {
            if (inputImage.getFormat() != 35) {
                return 0;
            }
            return (((Image.Plane[]) Preconditions.checkNotNull(inputImage.getPlanes()))[0].getBuffer().limit() * 3) / 2;
        }
        return ((ByteBuffer) Preconditions.checkNotNull(inputImage.getByteBuffer())).limit();
    }

    @Nullable
    @KeepForSdk
    public Matrix getUprightRotationMatrix(int i, int i2, int i3) {
        if (i3 == 0) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.postTranslate((-i) / 2.0f, (-i2) / 2.0f);
        matrix.postRotate(i3 * 90);
        int i4 = i3 % 2;
        int i5 = i4 != 0 ? i2 : i;
        if (i4 == 0) {
            i = i2;
        }
        matrix.postTranslate(i5 / 2.0f, i / 2.0f);
        return matrix;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0069 A[Catch: FileNotFoundException -> 0x00cc, TryCatch #5 {FileNotFoundException -> 0x00cc, blocks: (B:3:0x0002, B:5:0x0008, B:7:0x0016, B:33:0x006f, B:34:0x0084, B:45:0x00b5, B:47:0x00bf, B:36:0x0089, B:37:0x008d, B:38:0x0094, B:39:0x0098, B:40:0x009f, B:41:0x00a3, B:43:0x00aa, B:32:0x0069, B:29:0x0057, B:49:0x00c4, B:50:0x00cb), top: B:59:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0089 A[Catch: FileNotFoundException -> 0x00cc, TryCatch #5 {FileNotFoundException -> 0x00cc, blocks: (B:3:0x0002, B:5:0x0008, B:7:0x0016, B:33:0x006f, B:34:0x0084, B:45:0x00b5, B:47:0x00bf, B:36:0x0089, B:37:0x008d, B:38:0x0094, B:39:0x0098, B:40:0x009f, B:41:0x00a3, B:43:0x00aa, B:32:0x0069, B:29:0x0057, B:49:0x00c4, B:50:0x00cb), top: B:59:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x008d A[Catch: FileNotFoundException -> 0x00cc, TryCatch #5 {FileNotFoundException -> 0x00cc, blocks: (B:3:0x0002, B:5:0x0008, B:7:0x0016, B:33:0x006f, B:34:0x0084, B:45:0x00b5, B:47:0x00bf, B:36:0x0089, B:37:0x008d, B:38:0x0094, B:39:0x0098, B:40:0x009f, B:41:0x00a3, B:43:0x00aa, B:32:0x0069, B:29:0x0057, B:49:0x00c4, B:50:0x00cb), top: B:59:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0094 A[Catch: FileNotFoundException -> 0x00cc, TryCatch #5 {FileNotFoundException -> 0x00cc, blocks: (B:3:0x0002, B:5:0x0008, B:7:0x0016, B:33:0x006f, B:34:0x0084, B:45:0x00b5, B:47:0x00bf, B:36:0x0089, B:37:0x008d, B:38:0x0094, B:39:0x0098, B:40:0x009f, B:41:0x00a3, B:43:0x00aa, B:32:0x0069, B:29:0x0057, B:49:0x00c4, B:50:0x00cb), top: B:59:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0098 A[Catch: FileNotFoundException -> 0x00cc, TryCatch #5 {FileNotFoundException -> 0x00cc, blocks: (B:3:0x0002, B:5:0x0008, B:7:0x0016, B:33:0x006f, B:34:0x0084, B:45:0x00b5, B:47:0x00bf, B:36:0x0089, B:37:0x008d, B:38:0x0094, B:39:0x0098, B:40:0x009f, B:41:0x00a3, B:43:0x00aa, B:32:0x0069, B:29:0x0057, B:49:0x00c4, B:50:0x00cb), top: B:59:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x009f A[Catch: FileNotFoundException -> 0x00cc, TryCatch #5 {FileNotFoundException -> 0x00cc, blocks: (B:3:0x0002, B:5:0x0008, B:7:0x0016, B:33:0x006f, B:34:0x0084, B:45:0x00b5, B:47:0x00bf, B:36:0x0089, B:37:0x008d, B:38:0x0094, B:39:0x0098, B:40:0x009f, B:41:0x00a3, B:43:0x00aa, B:32:0x0069, B:29:0x0057, B:49:0x00c4, B:50:0x00cb), top: B:59:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00a3 A[Catch: FileNotFoundException -> 0x00cc, TryCatch #5 {FileNotFoundException -> 0x00cc, blocks: (B:3:0x0002, B:5:0x0008, B:7:0x0016, B:33:0x006f, B:34:0x0084, B:45:0x00b5, B:47:0x00bf, B:36:0x0089, B:37:0x008d, B:38:0x0094, B:39:0x0098, B:40:0x009f, B:41:0x00a3, B:43:0x00aa, B:32:0x0069, B:29:0x0057, B:49:0x00c4, B:50:0x00cb), top: B:59:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00aa A[Catch: FileNotFoundException -> 0x00cc, TryCatch #5 {FileNotFoundException -> 0x00cc, blocks: (B:3:0x0002, B:5:0x0008, B:7:0x0016, B:33:0x006f, B:34:0x0084, B:45:0x00b5, B:47:0x00bf, B:36:0x0089, B:37:0x008d, B:38:0x0094, B:39:0x0098, B:40:0x009f, B:41:0x00a3, B:43:0x00aa, B:32:0x0069, B:29:0x0057, B:49:0x00c4, B:50:0x00cb), top: B:59:0x0002 }] */
    @androidx.annotation.NonNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.graphics.Bitmap zza(@androidx.annotation.NonNull android.content.ContentResolver r11, @androidx.annotation.NonNull android.net.Uri r12) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 240
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.vision.common.internal.ImageUtils.zza(android.content.ContentResolver, android.net.Uri):android.graphics.Bitmap");
    }
}
