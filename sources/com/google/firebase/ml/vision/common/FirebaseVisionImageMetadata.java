package com.google.firebase.ml.vision.common;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes10.dex */
public class FirebaseVisionImageMetadata {
    public static final int IMAGE_FORMAT_NV21 = 17;
    public static final int IMAGE_FORMAT_YV12 = 842094169;
    public static final int ROTATION_0 = 0;
    public static final int ROTATION_180 = 2;
    public static final int ROTATION_270 = 3;
    public static final int ROTATION_90 = 1;

    /* renamed from: a  reason: collision with root package name */
    public final int f11440a;
    public final int b;
    @Rotation
    public final int c;
    @ImageFormat
    public final int d;

    /* loaded from: classes10.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public int f11441a;
        public int b;
        @Rotation
        public int c;
        @ImageFormat
        public int d;

        @NonNull
        public FirebaseVisionImageMetadata build() {
            return new FirebaseVisionImageMetadata(this.f11441a, this.b, this.c, this.d);
        }

        @NonNull
        public Builder setFormat(@ImageFormat int i) {
            Preconditions.checkArgument(i == 842094169 || i == 17);
            this.d = i;
            return this;
        }

        @NonNull
        public Builder setHeight(int i) {
            Preconditions.checkArgument(i > 0, "Image buffer height should be positive.");
            this.b = i;
            return this;
        }

        @NonNull
        public Builder setRotation(@Rotation int i) {
            boolean z = true;
            if (i != 0 && i != 1 && i != 2 && i != 3) {
                z = false;
            }
            Preconditions.checkArgument(z);
            this.c = i;
            return this;
        }

        @NonNull
        public Builder setWidth(int i) {
            Preconditions.checkArgument(i > 0, "Image buffer width should be positive.");
            this.f11441a = i;
            return this;
        }
    }

    @Retention(RetentionPolicy.CLASS)
    /* loaded from: classes10.dex */
    public @interface ImageFormat {
    }

    @Retention(RetentionPolicy.CLASS)
    /* loaded from: classes10.dex */
    public @interface Rotation {
    }

    public FirebaseVisionImageMetadata(@NonNull FirebaseVisionImageMetadata firebaseVisionImageMetadata) {
        this.f11440a = firebaseVisionImageMetadata.getWidth();
        this.b = firebaseVisionImageMetadata.getHeight();
        this.d = firebaseVisionImageMetadata.getFormat();
        this.c = firebaseVisionImageMetadata.getRotation();
    }

    @ImageFormat
    public int getFormat() {
        return this.d;
    }

    public int getHeight() {
        return this.b;
    }

    @Rotation
    public int getRotation() {
        return this.c;
    }

    public int getWidth() {
        return this.f11440a;
    }

    public FirebaseVisionImageMetadata(int i, int i2, @Rotation int i3, @ImageFormat int i4) {
        this.f11440a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
    }
}
