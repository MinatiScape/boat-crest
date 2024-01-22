package com.google.android.gms.vision;

import android.graphics.Bitmap;
import android.graphics.Color;
import java.nio.ByteBuffer;
/* loaded from: classes10.dex */
public class Frame {
    public static final int ROTATION_0 = 0;
    public static final int ROTATION_180 = 2;
    public static final int ROTATION_270 = 3;
    public static final int ROTATION_90 = 1;

    /* renamed from: a  reason: collision with root package name */
    public Metadata f10182a;
    public ByteBuffer b;
    public Bitmap c;

    /* loaded from: classes10.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Frame f10183a = new Frame();

        public Frame build() {
            if (this.f10183a.b == null && this.f10183a.c == null) {
                throw new IllegalStateException("Missing image data.  Call either setBitmap or setImageData to specify the image");
            }
            return this.f10183a;
        }

        public Builder setBitmap(Bitmap bitmap) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            this.f10183a.c = bitmap;
            Metadata metadata = this.f10183a.getMetadata();
            metadata.f10184a = width;
            metadata.b = height;
            return this;
        }

        public Builder setId(int i) {
            this.f10183a.getMetadata().c = i;
            return this;
        }

        public Builder setImageData(ByteBuffer byteBuffer, int i, int i2, int i3) {
            if (byteBuffer != null) {
                if (byteBuffer.capacity() >= i * i2) {
                    if (i3 == 16 || i3 == 17 || i3 == 842094169) {
                        this.f10183a.b = byteBuffer;
                        Metadata metadata = this.f10183a.getMetadata();
                        metadata.f10184a = i;
                        metadata.b = i2;
                        metadata.f = i3;
                        return this;
                    }
                    StringBuilder sb = new StringBuilder(37);
                    sb.append("Unsupported image format: ");
                    sb.append(i3);
                    throw new IllegalArgumentException(sb.toString());
                }
                throw new IllegalArgumentException("Invalid image data size.");
            }
            throw new IllegalArgumentException("Null image data supplied.");
        }

        public Builder setRotation(int i) {
            this.f10183a.getMetadata().e = i;
            return this;
        }

        public Builder setTimestampMillis(long j) {
            this.f10183a.getMetadata().d = j;
            return this;
        }
    }

    public Frame() {
        this.f10182a = new Metadata();
        this.b = null;
        this.c = null;
    }

    public Bitmap getBitmap() {
        return this.c;
    }

    public ByteBuffer getGrayscaleImageData() {
        Bitmap bitmap = this.c;
        if (bitmap != null) {
            int width = bitmap.getWidth();
            int height = this.c.getHeight();
            int i = width * height;
            int[] iArr = new int[i];
            this.c.getPixels(iArr, 0, width, 0, 0, width, height);
            byte[] bArr = new byte[i];
            for (int i2 = 0; i2 < i; i2++) {
                bArr[i2] = (byte) ((Color.red(iArr[i2]) * 0.299f) + (Color.green(iArr[i2]) * 0.587f) + (Color.blue(iArr[i2]) * 0.114f));
            }
            return ByteBuffer.wrap(bArr);
        }
        return this.b;
    }

    public Metadata getMetadata() {
        return this.f10182a;
    }

    /* loaded from: classes10.dex */
    public static class Metadata {

        /* renamed from: a  reason: collision with root package name */
        public int f10184a;
        public int b;
        public int c;
        public long d;
        public int e;
        public int f = -1;

        public Metadata() {
        }

        public int getFormat() {
            return this.f;
        }

        public int getHeight() {
            return this.b;
        }

        public int getId() {
            return this.c;
        }

        public int getRotation() {
            return this.e;
        }

        public long getTimestampMillis() {
            return this.d;
        }

        public int getWidth() {
            return this.f10184a;
        }

        public final void zzd() {
            if (this.e % 2 != 0) {
                int i = this.f10184a;
                this.f10184a = this.b;
                this.b = i;
            }
            this.e = 0;
        }

        public Metadata(Metadata metadata) {
            this.f10184a = metadata.getWidth();
            this.b = metadata.getHeight();
            this.c = metadata.getId();
            this.d = metadata.getTimestampMillis();
            this.e = metadata.getRotation();
        }
    }
}
