package com.google.android.gms.vision.text;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.util.SparseArray;
import com.google.android.gms.internal.vision.zzac;
import com.google.android.gms.internal.vision.zzae;
import com.google.android.gms.internal.vision.zzah;
import com.google.android.gms.internal.vision.zzai;
import com.google.android.gms.internal.vision.zzp;
import com.google.android.gms.internal.vision.zzq;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
/* loaded from: classes10.dex */
public final class TextRecognizer extends Detector<TextBlock> {
    public final zzai c;

    /* loaded from: classes10.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Context f10201a;
        public zzah b = new zzah();

        public Builder(Context context) {
            this.f10201a = context;
        }

        public TextRecognizer build() {
            return new TextRecognizer(new zzai(this.f10201a, this.b));
        }
    }

    public TextRecognizer() {
        throw new IllegalStateException("Default constructor called");
    }

    @Override // com.google.android.gms.vision.Detector
    public final SparseArray<TextBlock> detect(Frame frame) {
        byte[] bArr;
        Bitmap decodeByteArray;
        zzae zzaeVar = new zzae(new Rect());
        if (frame != null) {
            zzp zzc = zzp.zzc(frame);
            if (frame.getBitmap() != null) {
                decodeByteArray = frame.getBitmap();
            } else {
                Frame.Metadata metadata = frame.getMetadata();
                ByteBuffer grayscaleImageData = frame.getGrayscaleImageData();
                int format = metadata.getFormat();
                int i = zzc.width;
                int i2 = zzc.height;
                if (grayscaleImageData.hasArray() && grayscaleImageData.arrayOffset() == 0) {
                    bArr = grayscaleImageData.array();
                } else {
                    byte[] bArr2 = new byte[grayscaleImageData.capacity()];
                    grayscaleImageData.get(bArr2);
                    bArr = bArr2;
                }
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                new YuvImage(bArr, format, i, i2, null).compressToJpeg(new Rect(0, 0, i, i2), 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                decodeByteArray = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            }
            Bitmap zzb = zzq.zzb(decodeByteArray, zzc);
            if (!zzaeVar.zzer.isEmpty()) {
                Rect rect = zzaeVar.zzer;
                int width = frame.getMetadata().getWidth();
                int height = frame.getMetadata().getHeight();
                int i3 = zzc.rotation;
                if (i3 == 1) {
                    rect = new Rect(height - rect.bottom, rect.left, height - rect.top, rect.right);
                } else if (i3 == 2) {
                    rect = new Rect(width - rect.right, height - rect.bottom, width - rect.left, height - rect.top);
                } else if (i3 == 3) {
                    rect = new Rect(rect.top, width - rect.right, rect.bottom, width - rect.left);
                }
                zzaeVar.zzer.set(rect);
            }
            zzc.rotation = 0;
            zzac[] zza = this.c.zza(zzb, zzc, zzaeVar);
            SparseArray sparseArray = new SparseArray();
            for (zzac zzacVar : zza) {
                SparseArray sparseArray2 = (SparseArray) sparseArray.get(zzacVar.zzep);
                if (sparseArray2 == null) {
                    sparseArray2 = new SparseArray();
                    sparseArray.append(zzacVar.zzep, sparseArray2);
                }
                sparseArray2.append(zzacVar.zzeq, zzacVar);
            }
            SparseArray<TextBlock> sparseArray3 = new SparseArray<>(sparseArray.size());
            for (int i4 = 0; i4 < sparseArray.size(); i4++) {
                sparseArray3.append(sparseArray.keyAt(i4), new TextBlock((SparseArray) sparseArray.valueAt(i4)));
            }
            return sparseArray3;
        }
        throw new IllegalArgumentException("No frame supplied.");
    }

    @Override // com.google.android.gms.vision.Detector
    public final boolean isOperational() {
        return this.c.isOperational();
    }

    @Override // com.google.android.gms.vision.Detector
    public final void release() {
        super.release();
        this.c.zzo();
    }

    public TextRecognizer(zzai zzaiVar) {
        this.c = zzaiVar;
    }
}
