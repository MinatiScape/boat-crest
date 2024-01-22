package com.google.mlkit.vision.text.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.CommonConvertUtils;
import com.google.mlkit.vision.common.internal.ImageConvertUtils;
import com.google.mlkit.vision.text.Text;
/* loaded from: classes10.dex */
public final class c implements e {

    /* renamed from: a  reason: collision with root package name */
    public final Context f11645a;
    public final com.google.android.gms.internal.mlkit_vision_text_common.zzp b = new com.google.android.gms.internal.mlkit_vision_text_common.zzp(null);
    public boolean c;
    @Nullable
    public com.google.android.gms.internal.mlkit_vision_text_common.zzh d;

    public c(Context context) {
        this.f11645a = context;
    }

    @Override // com.google.mlkit.vision.text.internal.e
    public final Text a(InputImage inputImage) throws MlKitException {
        Bitmap convertToUpRightBitmap;
        int i;
        if (this.d == null) {
            zzb();
        }
        if (this.d != null) {
            if (inputImage.getFormat() == -1) {
                convertToUpRightBitmap = inputImage.getBitmapInternal();
                i = CommonConvertUtils.convertToMVRotation(inputImage.getRotationDegrees());
            } else {
                convertToUpRightBitmap = ImageConvertUtils.getInstance().convertToUpRightBitmap(inputImage);
                i = 0;
            }
            try {
                return d.a(((com.google.android.gms.internal.mlkit_vision_text_common.zzh) Preconditions.checkNotNull(this.d)).zze(ObjectWrapper.wrap(convertToUpRightBitmap), new com.google.android.gms.internal.mlkit_vision_text_common.zzd(inputImage.getWidth(), inputImage.getHeight(), 0, 0L, i)), inputImage.getCoordinatesMatrix());
            } catch (RemoteException e) {
                throw new MlKitException("Failed to run legacy text recognizer.", 13, e);
            }
        }
        throw new MlKitException("Waiting for the text recognition module to be downloaded. Please wait.", 14);
    }

    @Override // com.google.mlkit.vision.text.internal.e
    public final void zzb() throws MlKitException {
        if (this.d == null) {
            try {
                com.google.android.gms.internal.mlkit_vision_text_common.zzh zzd = com.google.android.gms.internal.mlkit_vision_text_common.zzj.zza(DynamiteModule.load(this.f11645a, DynamiteModule.PREFER_REMOTE, OptionalModuleUtils.DEPRECATED_DYNAMITE_MODULE_ID).instantiate("com.google.android.gms.vision.text.ChimeraNativeTextRecognizerCreator")).zzd(ObjectWrapper.wrap(this.f11645a), this.b);
                this.d = zzd;
                if (zzd != null || this.c) {
                    return;
                }
                Log.d("LegacyTextDelegate", "Request OCR optional module download.");
                OptionalModuleUtils.requestDownload(this.f11645a, OptionalModuleUtils.OCR);
                this.c = true;
            } catch (RemoteException e) {
                throw new MlKitException("Failed to create legacy text recognizer.", 13, e);
            } catch (DynamiteModule.LoadingException e2) {
                throw new MlKitException("Failed to load deprecated vision dynamite module.", 13, e2);
            }
        }
    }

    @Override // com.google.mlkit.vision.text.internal.e
    public final void zzc() {
        com.google.android.gms.internal.mlkit_vision_text_common.zzh zzhVar = this.d;
        if (zzhVar != null) {
            try {
                zzhVar.zzd();
            } catch (RemoteException e) {
                Log.e("LegacyTextDelegate", "Failed to release legacy text recognizer.", e);
            }
            this.d = null;
        }
    }
}
