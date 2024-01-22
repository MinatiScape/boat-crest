package com.google.firebase.ml.vision.common;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.Image;
import android.net.Uri;
import android.os.SystemClock;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzsc;
import com.google.android.gms.internal.firebase_ml.zzsd;
import com.google.android.gms.vision.Frame;
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata;
import com.realsil.sdk.dfu.DfuException;
import java.io.IOException;
import java.nio.ByteBuffer;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes10.dex */
public class FirebaseVisionImage {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public volatile Bitmap f11439a;
    @Nullable
    public volatile ByteBuffer b;
    @Nullable
    public volatile FirebaseVisionImageMetadata c;
    @Nullable
    public volatile Frame d;
    @Nullable
    public volatile byte[] e;
    public final long f;

    static {
        zzsc.zzqp();
    }

    public FirebaseVisionImage(@NonNull ByteBuffer byteBuffer, @NonNull FirebaseVisionImageMetadata firebaseVisionImageMetadata) {
        this.f = SystemClock.elapsedRealtime();
        this.b = (ByteBuffer) Preconditions.checkNotNull(byteBuffer);
        this.c = (FirebaseVisionImageMetadata) Preconditions.checkNotNull(firebaseVisionImageMetadata);
    }

    public static Bitmap a(Bitmap bitmap, @FirebaseVisionImageMetadata.Rotation int i) {
        int i2;
        if (i == 0) {
            i2 = 0;
        } else if (i == 1) {
            i2 = 90;
        } else if (i == 2) {
            i2 = 180;
        } else if (i != 3) {
            StringBuilder sb = new StringBuilder(29);
            sb.append("Invalid rotation: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        } else {
            i2 = DfuException.ERROR_READ_DEVICE_INFO_ERROR;
        }
        if (i2 == 0) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.postRotate(i2);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    @NonNull
    public static FirebaseVisionImage fromBitmap(@NonNull Bitmap bitmap) {
        return new FirebaseVisionImage(bitmap);
    }

    @NonNull
    public static FirebaseVisionImage fromByteArray(@NonNull byte[] bArr, @NonNull FirebaseVisionImageMetadata firebaseVisionImageMetadata) {
        return new FirebaseVisionImage(bArr, firebaseVisionImageMetadata);
    }

    @NonNull
    public static FirebaseVisionImage fromByteBuffer(@NonNull ByteBuffer byteBuffer, @NonNull FirebaseVisionImageMetadata firebaseVisionImageMetadata) {
        return new FirebaseVisionImage(byteBuffer, firebaseVisionImageMetadata);
    }

    @NonNull
    public static FirebaseVisionImage fromFilePath(@NonNull Context context, @NonNull Uri uri) throws IOException {
        Preconditions.checkNotNull(context, "Please provide a valid Context");
        Preconditions.checkNotNull(uri, "Please provide a valid imageUri");
        zzsd.zzqq();
        return new FirebaseVisionImage(zzsd.zza(context.getContentResolver(), uri));
    }

    @NonNull
    @RequiresApi(19)
    @TargetApi(19)
    public static FirebaseVisionImage fromMediaImage(@NonNull Image image, @FirebaseVisionImageMetadata.Rotation int i) {
        Preconditions.checkNotNull(image, "Please provide a valid image");
        Preconditions.checkArgument(image.getFormat() == 256 || image.getFormat() == 35, "Only JPEG and YUV_420_888 are supported now");
        Image.Plane[] planes = image.getPlanes();
        if (image.getFormat() == 256) {
            if (planes != null && planes.length == 1) {
                ByteBuffer buffer = planes[0].getBuffer();
                int remaining = buffer.remaining();
                byte[] bArr = new byte[remaining];
                buffer.get(bArr);
                if (i == 0) {
                    return new FirebaseVisionImage(bArr);
                }
                return new FirebaseVisionImage(a(BitmapFactory.decodeByteArray(bArr, 0, remaining), i));
            }
            throw new IllegalArgumentException("Unexpected image format, JPEG should have exactly 1 image plane");
        }
        return new FirebaseVisionImage(zzsc.zza(planes, image.getWidth(), image.getHeight()), new FirebaseVisionImageMetadata.Builder().setFormat(17).setWidth(image.getWidth()).setHeight(image.getHeight()).setRotation(i).build());
    }

    public final byte[] b(boolean z) {
        if (this.e != null) {
            return this.e;
        }
        synchronized (this) {
            if (this.e != null) {
                return this.e;
            } else if (this.b != null && (!z || this.c.getRotation() == 0)) {
                byte[] zza = zzsc.zza(this.b);
                int format = this.c.getFormat();
                if (format != 17) {
                    if (format == 842094169) {
                        zza = zzsc.zzf(zza);
                    } else {
                        throw new IllegalStateException("Must be one of: IMAGE_FORMAT_NV21, IMAGE_FORMAT_YV12");
                    }
                }
                byte[] zza2 = zzsc.zza(zza, this.c.getWidth(), this.c.getHeight());
                if (this.c.getRotation() == 0) {
                    this.e = zza2;
                }
                return zza2;
            } else {
                byte[] zza3 = zzsc.zza(c());
                this.e = zza3;
                return zza3;
            }
        }
    }

    public final Bitmap c() {
        if (this.f11439a != null) {
            return this.f11439a;
        }
        synchronized (this) {
            if (this.f11439a == null) {
                byte[] b = b(false);
                Bitmap decodeByteArray = BitmapFactory.decodeByteArray(b, 0, b.length);
                if (this.c != null) {
                    decodeByteArray = a(decodeByteArray, this.c.getRotation());
                }
                this.f11439a = decodeByteArray;
            }
        }
        return this.f11439a;
    }

    @NonNull
    public Bitmap getBitmap() {
        return c();
    }

    public final synchronized Frame zza(boolean z, boolean z2) {
        int i = 0;
        Preconditions.checkArgument((z && z2) ? false : true, "Can't restrict to bitmap-only and NV21 byte buffer-only");
        if (this.d == null) {
            Frame.Builder builder = new Frame.Builder();
            if (this.b != null && !z) {
                int i2 = 842094169;
                if (z2 && this.c.getFormat() != 17) {
                    if (this.c.getFormat() == 842094169) {
                        this.b = ByteBuffer.wrap(zzsc.zzf(zzsc.zza(this.b)));
                        this.c = new FirebaseVisionImageMetadata.Builder().setFormat(17).setWidth(this.c.getWidth()).setHeight(this.c.getHeight()).setRotation(this.c.getRotation()).build();
                    } else {
                        throw new IllegalStateException("Must be one of: IMAGE_FORMAT_NV21, IMAGE_FORMAT_YV12");
                    }
                }
                ByteBuffer byteBuffer = this.b;
                int width = this.c.getWidth();
                int height = this.c.getHeight();
                int format = this.c.getFormat();
                if (format == 17) {
                    i2 = 17;
                } else if (format != 842094169) {
                    i2 = 0;
                }
                builder.setImageData(byteBuffer, width, height, i2);
                int rotation = this.c.getRotation();
                if (rotation != 0) {
                    if (rotation == 1) {
                        i = 1;
                    } else if (rotation == 2) {
                        i = 2;
                    } else if (rotation != 3) {
                        StringBuilder sb = new StringBuilder(29);
                        sb.append("Invalid rotation: ");
                        sb.append(rotation);
                        throw new IllegalArgumentException(sb.toString());
                    } else {
                        i = 3;
                    }
                }
                builder.setRotation(i);
            } else {
                builder.setBitmap(c());
            }
            builder.setTimestampMillis(this.f);
            this.d = builder.build();
        }
        return this.d;
    }

    public final Pair<byte[], Float> zze(int i, int i2) {
        int width;
        int height;
        byte[] b;
        if (this.c != null) {
            boolean z = this.c.getRotation() == 1 || this.c.getRotation() == 3;
            FirebaseVisionImageMetadata firebaseVisionImageMetadata = this.c;
            width = z ? firebaseVisionImageMetadata.getHeight() : firebaseVisionImageMetadata.getWidth();
            height = z ? this.c.getWidth() : this.c.getHeight();
        } else {
            width = c().getWidth();
            height = c().getHeight();
        }
        float min = Math.min(i / width, i2 / height);
        if (min < 1.0f) {
            Bitmap c = c();
            Matrix matrix = new Matrix();
            matrix.postScale(min, min);
            b = zzsc.zza(Bitmap.createBitmap(c, 0, 0, this.f11439a.getWidth(), this.f11439a.getHeight(), matrix, true));
        } else {
            b = b(true);
            min = 1.0f;
        }
        return Pair.create(b, Float.valueOf(min));
    }

    public final void zzqn() {
        if (this.b != null) {
            ByteBuffer byteBuffer = this.b;
            ByteBuffer allocate = ByteBuffer.allocate(byteBuffer.capacity());
            byteBuffer.rewind();
            allocate.put(byteBuffer);
            byteBuffer.rewind();
            allocate.flip();
            this.b = allocate;
        }
    }

    public FirebaseVisionImage(@NonNull byte[] bArr, @NonNull FirebaseVisionImageMetadata firebaseVisionImageMetadata) {
        this(ByteBuffer.wrap((byte[]) Preconditions.checkNotNull(bArr)), firebaseVisionImageMetadata);
    }

    public FirebaseVisionImage(@NonNull Bitmap bitmap) {
        this.f = SystemClock.elapsedRealtime();
        this.f11439a = (Bitmap) Preconditions.checkNotNull(bitmap);
    }

    public FirebaseVisionImage(byte[] bArr) {
        this.f = SystemClock.elapsedRealtime();
        this.e = (byte[]) Preconditions.checkNotNull(bArr);
    }
}
