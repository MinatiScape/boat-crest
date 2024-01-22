package com.google.mlkit.vision.text.internal;

import android.content.Context;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.internal.mlkit_vision_text_common.zzks;
import com.google.android.gms.internal.mlkit_vision_text_common.zzog;
import com.google.android.gms.internal.mlkit_vision_text_common.zzou;
import com.google.android.gms.internal.mlkit_vision_text_common.zzow;
import com.google.android.gms.internal.mlkit_vision_text_common.zzoy;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.CommonConvertUtils;
import com.google.mlkit.vision.common.internal.ImageUtils;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognizerOptionsInterface;
/* loaded from: classes10.dex */
public final class b implements e {

    /* renamed from: a  reason: collision with root package name */
    public final Context f11644a;
    public final TextRecognizerOptionsInterface b;
    public boolean c;
    public boolean d;
    public final zzog e;
    @Nullable
    public zzow f;

    public b(Context context, TextRecognizerOptionsInterface textRecognizerOptionsInterface, zzog zzogVar) {
        this.f11644a = context;
        this.b = textRecognizerOptionsInterface;
        this.e = zzogVar;
    }

    @Override // com.google.mlkit.vision.text.internal.e
    @WorkerThread
    public final Text a(InputImage inputImage) throws MlKitException {
        if (this.f == null) {
            zzb();
        }
        zzow zzowVar = (zzow) Preconditions.checkNotNull(this.f);
        if (!this.c) {
            try {
                zzowVar.zze();
                this.c = true;
            } catch (RemoteException e) {
                throw new MlKitException("Failed to init text recognizer ".concat(String.valueOf(this.b.getLoggingLibraryName())), 13, e);
            }
        }
        try {
            return new Text(zzowVar.zzd(ImageUtils.getInstance().getImageDataWrapper(inputImage), new zzou(inputImage.getFormat(), inputImage.getWidth(), inputImage.getHeight(), CommonConvertUtils.convertToMVRotation(inputImage.getRotationDegrees()), SystemClock.elapsedRealtime())), inputImage.getCoordinatesMatrix());
        } catch (RemoteException e2) {
            throw new MlKitException("Failed to run text recognizer ".concat(String.valueOf(this.b.getLoggingLibraryName())), 13, e2);
        }
    }

    @Override // com.google.mlkit.vision.text.internal.e
    @WorkerThread
    public final void zzb() throws MlKitException {
        DynamiteModule.VersionPolicy versionPolicy;
        if (this.f == null) {
            try {
                Context context = this.f11644a;
                if (this.b.getIsThickClient()) {
                    versionPolicy = DynamiteModule.PREFER_LOCAL;
                } else {
                    versionPolicy = DynamiteModule.PREFER_REMOTE;
                }
                this.f = zzoy.zza(DynamiteModule.load(context, versionPolicy, this.b.getModuleId()).instantiate(this.b.getCreatorClass())).zzd(ObjectWrapper.wrap(this.f11644a));
                LoggingUtils.b(this.e, this.b.getIsThickClient(), zzks.NO_ERROR);
            } catch (RemoteException e) {
                LoggingUtils.b(this.e, this.b.getIsThickClient(), zzks.OPTIONAL_MODULE_INIT_ERROR);
                throw new MlKitException("Failed to create text recognizer ".concat(String.valueOf(this.b.getLoggingLibraryName())), 13, e);
            } catch (DynamiteModule.LoadingException e2) {
                LoggingUtils.b(this.e, this.b.getIsThickClient(), zzks.OPTIONAL_MODULE_NOT_AVAILABLE);
                if (!this.b.getIsThickClient()) {
                    if (!this.d) {
                        OptionalModuleUtils.requestDownload(this.f11644a, OptionalModuleUtils.OCR);
                        this.d = true;
                    }
                    throw new MlKitException("Waiting for the text optional module to be downloaded. Please wait.", 14);
                }
                throw new MlKitException(String.format("Failed to load text module %s. %s", this.b.getLoggingLibraryName(), e2.getMessage()), 13, e2);
            }
        }
    }

    @Override // com.google.mlkit.vision.text.internal.e
    @WorkerThread
    public final void zzc() {
        zzow zzowVar = this.f;
        if (zzowVar != null) {
            try {
                zzowVar.zzf();
            } catch (RemoteException e) {
                Log.e("DecoupledTextDelegate", "Failed to release text recognizer ".concat(String.valueOf(this.b.getLoggingLibraryName())), e);
            }
            this.f = null;
        }
        this.c = false;
    }
}
