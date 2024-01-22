package com.google.android.gms.vision.label;

import android.content.Context;
import android.util.SparseArray;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.internal.vision.zzp;
import com.google.android.gms.internal.vision.zzq;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.label.internal.client.ImageLabelerOptions;
import com.google.android.gms.vision.label.internal.client.LabelOptions;
import com.google.android.gms.vision.label.internal.client.zzi;
import java.util.Locale;
@ShowFirstParty
@KeepForSdk
/* loaded from: classes10.dex */
public class ImageLabeler extends Detector<ImageLabel> {
    public static final LabelOptions d = new LabelOptions(-1);
    public final zzi c;

    @ShowFirstParty
    @KeepForSdk
    /* loaded from: classes10.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Context f10197a;
        public ImageLabelerOptions b = new ImageLabelerOptions(ImageLabelerOptions.zza(Locale.getDefault().getLanguage()), -1, 0.5f, 1);

        @KeepForSdk
        public Builder(Context context) {
            this.f10197a = context;
        }

        @KeepForSdk
        public ImageLabeler build() {
            return new ImageLabeler(new zzi(this.f10197a, this.b));
        }

        @KeepForSdk
        public Builder setScoreThreshold(float f) {
            if (f >= 0.0f && f <= 1.0f) {
                this.b.zzdv = f;
                return this;
            }
            throw new IllegalArgumentException("scoreThreshold should be between [0, 1]");
        }
    }

    public ImageLabeler(zzi zziVar) {
        this.c = zziVar;
    }

    @Override // com.google.android.gms.vision.Detector
    @KeepForSdk
    public SparseArray<ImageLabel> detect(Frame frame) {
        return detect(frame, d);
    }

    @Override // com.google.android.gms.vision.Detector
    @KeepForSdk
    public boolean isOperational() {
        return this.c.isOperational();
    }

    @Override // com.google.android.gms.vision.Detector
    @KeepForSdk
    public void release() {
        super.release();
        this.c.zzo();
    }

    @KeepForSdk
    public SparseArray<ImageLabel> detect(Frame frame, LabelOptions labelOptions) {
        if (frame != null) {
            ImageLabel[] zza = this.c.zza(zzq.zzb(frame.getBitmap(), zzp.zzc(frame)), labelOptions);
            SparseArray<ImageLabel> sparseArray = new SparseArray<>(zza.length);
            for (int i = 0; i < zza.length; i++) {
                sparseArray.append(i, zza[i]);
            }
            return sparseArray;
        }
        throw new IllegalArgumentException("No frame supplied.");
    }
}
