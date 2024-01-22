package com.google.android.gms.vision.face;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.internal.vision.zzp;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.internal.client.zzb;
import com.google.android.gms.vision.face.internal.client.zzf;
import com.google.android.gms.vision.zzc;
import java.nio.ByteBuffer;
import java.util.HashSet;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes10.dex */
public final class FaceDetector extends Detector<Face> {
    public static final int ACCURATE_MODE = 1;
    public static final int ALL_CLASSIFICATIONS = 1;
    public static final int ALL_LANDMARKS = 1;
    public static final int CONTOUR_LANDMARKS = 2;
    public static final int FAST_MODE = 0;
    public static final int NO_CLASSIFICATIONS = 0;
    public static final int NO_LANDMARKS = 0;
    public static final int SELFIE_MODE = 2;
    public final zzc c;
    @GuardedBy("lock")
    public final zzb d;
    public final Object e;
    @GuardedBy("lock")
    public boolean f;

    /* loaded from: classes10.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final Context f10193a;
        public int b = 0;
        public boolean c = false;
        public int d = 0;
        public boolean e = true;
        public int f = 0;
        public float g = -1.0f;

        public Builder(Context context) {
            this.f10193a = context;
        }

        public FaceDetector build() {
            zzf zzfVar = new zzf();
            zzfVar.mode = this.f;
            zzfVar.landmarkType = this.b;
            zzfVar.zzcp = this.d;
            zzfVar.zzco = this.c;
            zzfVar.trackingEnabled = this.e;
            zzfVar.proportionalMinFaceSize = this.g;
            if (FaceDetector.a(zzfVar)) {
                return new FaceDetector(new zzb(this.f10193a, zzfVar));
            }
            throw new IllegalArgumentException("Invalid build options");
        }

        public Builder setClassificationType(int i) {
            if (i != 0 && i != 1) {
                StringBuilder sb = new StringBuilder(40);
                sb.append("Invalid classification type: ");
                sb.append(i);
                throw new IllegalArgumentException(sb.toString());
            }
            this.d = i;
            return this;
        }

        public Builder setLandmarkType(int i) {
            if (i != 0 && i != 1 && i != 2) {
                StringBuilder sb = new StringBuilder(34);
                sb.append("Invalid landmark type: ");
                sb.append(i);
                throw new IllegalArgumentException(sb.toString());
            }
            this.b = i;
            return this;
        }

        public Builder setMinFaceSize(float f) {
            if (f >= 0.0f && f <= 1.0f) {
                this.g = f;
                return this;
            }
            StringBuilder sb = new StringBuilder(47);
            sb.append("Invalid proportional face size: ");
            sb.append(f);
            throw new IllegalArgumentException(sb.toString());
        }

        public Builder setMode(int i) {
            if (i != 0 && i != 1 && i != 2) {
                StringBuilder sb = new StringBuilder(25);
                sb.append("Invalid mode: ");
                sb.append(i);
                throw new IllegalArgumentException(sb.toString());
            }
            this.f = i;
            return this;
        }

        public Builder setProminentFaceOnly(boolean z) {
            this.c = z;
            return this;
        }

        public Builder setTrackingEnabled(boolean z) {
            this.e = z;
            return this;
        }
    }

    public FaceDetector() {
        this.c = new zzc();
        this.e = new Object();
        this.f = true;
        throw new IllegalStateException("Default constructor called");
    }

    public static boolean a(zzf zzfVar) {
        boolean z;
        if (zzfVar.mode == 2 || zzfVar.landmarkType != 2) {
            z = true;
        } else {
            Log.e("FaceDetector", "Contour is not supported for non-SELFIE mode.");
            z = false;
        }
        if (zzfVar.landmarkType == 2 && zzfVar.zzcp == 1) {
            Log.e("FaceDetector", "Classification is not supported with contour.");
            return false;
        }
        return z;
    }

    @Override // com.google.android.gms.vision.Detector
    public final SparseArray<Face> detect(Frame frame) {
        ByteBuffer grayscaleImageData;
        Face[] zzb;
        if (frame != null) {
            if (frame.getBitmap() != null) {
                Bitmap bitmap = frame.getBitmap();
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                int i = width * height;
                grayscaleImageData = ByteBuffer.allocateDirect(((((width + 1) / 2) * ((height + 1) / 2)) << 1) + i);
                int i2 = i;
                for (int i3 = 0; i3 < i; i3++) {
                    int i4 = i3 % width;
                    int i5 = i3 / width;
                    int pixel = bitmap.getPixel(i4, i5);
                    float red = Color.red(pixel);
                    float green = Color.green(pixel);
                    float blue = Color.blue(pixel);
                    grayscaleImageData.put(i3, (byte) ((0.299f * red) + (0.587f * green) + (0.114f * blue)));
                    if (i5 % 2 == 0 && i4 % 2 == 0) {
                        int i6 = i2 + 1;
                        grayscaleImageData.put(i2, (byte) (((-0.169f) * red) + ((-0.331f) * green) + (blue * 0.5f) + 128.0f));
                        i2 = i6 + 1;
                        grayscaleImageData.put(i6, (byte) ((red * 0.5f) + (green * (-0.419f)) + (blue * (-0.081f)) + 128.0f));
                    }
                }
            } else {
                grayscaleImageData = frame.getGrayscaleImageData();
            }
            synchronized (this.e) {
                if (this.f) {
                    zzb = this.d.zzb(grayscaleImageData, zzp.zzc(frame));
                } else {
                    throw new RuntimeException("Cannot use detector after release()");
                }
            }
            HashSet hashSet = new HashSet();
            SparseArray<Face> sparseArray = new SparseArray<>(zzb.length);
            int i7 = 0;
            for (Face face : zzb) {
                int id = face.getId();
                i7 = Math.max(i7, id);
                if (hashSet.contains(Integer.valueOf(id))) {
                    id = i7 + 1;
                    i7 = id;
                }
                hashSet.add(Integer.valueOf(id));
                sparseArray.append(this.c.zzb(id), face);
            }
            return sparseArray;
        }
        throw new IllegalArgumentException("No frame supplied.");
    }

    public final void finalize() throws Throwable {
        try {
            synchronized (this.e) {
                if (this.f) {
                    Log.w("FaceDetector", "FaceDetector was not released with FaceDetector.release()");
                    release();
                }
            }
        } finally {
            super.finalize();
        }
    }

    @Override // com.google.android.gms.vision.Detector
    public final boolean isOperational() {
        return this.d.isOperational();
    }

    @Override // com.google.android.gms.vision.Detector
    public final void release() {
        super.release();
        synchronized (this.e) {
            if (this.f) {
                this.d.zzo();
                this.f = false;
            }
        }
    }

    @Override // com.google.android.gms.vision.Detector
    public final boolean setFocus(int i) {
        boolean zzd;
        int zzc = this.c.zzc(i);
        synchronized (this.e) {
            if (this.f) {
                zzd = this.d.zzd(zzc);
            } else {
                throw new RuntimeException("Cannot use detector after release()");
            }
        }
        return zzd;
    }

    public FaceDetector(zzb zzbVar) {
        this.c = new zzc();
        this.e = new Object();
        this.f = true;
        this.d = zzbVar;
    }
}
