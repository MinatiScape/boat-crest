package com.google.mlkit.vision.barcode.internal;

import android.content.Context;
import android.media.Image;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.internal.mlkit_vision_barcode.zzah;
import com.google.android.gms.internal.mlkit_vision_barcode.zzaj;
import com.google.android.gms.internal.mlkit_vision_barcode.zzal;
import com.google.android.gms.internal.mlkit_vision_barcode.zzan;
import com.google.android.gms.internal.mlkit_vision_barcode.zzpj;
import com.google.android.gms.internal.mlkit_vision_barcode.zztx;
import com.google.android.gms.internal.mlkit_vision_barcode.zzu;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.CommonConvertUtils;
import com.google.mlkit.vision.common.internal.ImageConvertUtils;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes10.dex */
public final class c implements a {

    /* renamed from: a  reason: collision with root package name */
    public boolean f11625a;
    public final Context b;
    public final zzah c;
    public final zztx d;
    @Nullable
    public zzaj e;

    public c(Context context, BarcodeScannerOptions barcodeScannerOptions, zztx zztxVar) {
        zzah zzahVar = new zzah();
        this.c = zzahVar;
        this.b = context;
        zzahVar.zza = barcodeScannerOptions.zza();
        this.d = zztxVar;
    }

    @Override // com.google.mlkit.vision.barcode.internal.a
    @WorkerThread
    public final List a(InputImage inputImage) throws MlKitException {
        zzu[] zzf;
        if (this.e == null) {
            zzc();
        }
        zzaj zzajVar = this.e;
        if (zzajVar != null) {
            zzaj zzajVar2 = (zzaj) Preconditions.checkNotNull(zzajVar);
            zzan zzanVar = new zzan(inputImage.getWidth(), inputImage.getHeight(), 0, 0L, CommonConvertUtils.convertToMVRotation(inputImage.getRotationDegrees()));
            try {
                int format = inputImage.getFormat();
                if (format == -1) {
                    zzf = zzajVar2.zzf(ObjectWrapper.wrap(inputImage.getBitmapInternal()), zzanVar);
                } else if (format == 17) {
                    zzf = zzajVar2.zze(ObjectWrapper.wrap(inputImage.getByteBuffer()), zzanVar);
                } else if (format == 35) {
                    Image.Plane[] planeArr = (Image.Plane[]) Preconditions.checkNotNull(inputImage.getPlanes());
                    zzanVar.zza = planeArr[0].getRowStride();
                    zzf = zzajVar2.zze(ObjectWrapper.wrap(planeArr[0].getBuffer()), zzanVar);
                } else if (format == 842094169) {
                    zzf = zzajVar2.zze(ObjectWrapper.wrap(ImageConvertUtils.getInstance().convertToNv21Buffer(inputImage, false)), zzanVar);
                } else {
                    throw new MlKitException("Unsupported image format: " + inputImage.getFormat(), 3);
                }
                ArrayList arrayList = new ArrayList();
                for (zzu zzuVar : zzf) {
                    arrayList.add(new Barcode(new zzo(zzuVar), inputImage.getCoordinatesMatrix()));
                }
                return arrayList;
            } catch (RemoteException e) {
                throw new MlKitException("Failed to detect with legacy barcode detector", 13, e);
            }
        }
        throw new MlKitException("Error initializing the legacy barcode scanner.", 14);
    }

    @Override // com.google.mlkit.vision.barcode.internal.a
    @WorkerThread
    public final void zzb() {
        zzaj zzajVar = this.e;
        if (zzajVar != null) {
            try {
                zzajVar.zzd();
            } catch (RemoteException e) {
                Log.e("LegacyBarcodeScanner", "Failed to release legacy barcode detector.", e);
            }
            this.e = null;
        }
    }

    @Override // com.google.mlkit.vision.barcode.internal.a
    @WorkerThread
    public final boolean zzc() throws MlKitException {
        if (this.e != null) {
            return false;
        }
        try {
            zzaj zzd = zzal.zza(DynamiteModule.load(this.b, DynamiteModule.PREFER_REMOTE, OptionalModuleUtils.DEPRECATED_DYNAMITE_MODULE_ID).instantiate("com.google.android.gms.vision.barcode.ChimeraNativeBarcodeDetectorCreator")).zzd(ObjectWrapper.wrap(this.b), this.c);
            this.e = zzd;
            if (zzd == null && !this.f11625a) {
                Log.d("LegacyBarcodeScanner", "Request optional module download.");
                OptionalModuleUtils.requestDownload(this.b, OptionalModuleUtils.BARCODE);
                this.f11625a = true;
                zzb.a(this.d, zzpj.OPTIONAL_MODULE_NOT_AVAILABLE);
                throw new MlKitException("Waiting for the barcode module to be downloaded. Please wait.", 14);
            }
            zzb.a(this.d, zzpj.NO_ERROR);
            return false;
        } catch (RemoteException e) {
            throw new MlKitException("Failed to create legacy barcode detector.", 13, e);
        } catch (DynamiteModule.LoadingException e2) {
            throw new MlKitException("Failed to load deprecated vision dynamite module.", 13, e2);
        }
    }
}
