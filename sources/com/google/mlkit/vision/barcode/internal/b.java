package com.google.mlkit.vision.barcode.internal;

import android.content.Context;
import android.media.Image;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.mlkit.dynamite.barcode.ModuleDescriptor;
import com.google.android.gms.internal.mlkit_vision_barcode.zzcv;
import com.google.android.gms.internal.mlkit_vision_barcode.zzpj;
import com.google.android.gms.internal.mlkit_vision_barcode.zztx;
import com.google.android.gms.internal.mlkit_vision_barcode.zzvj;
import com.google.android.gms.internal.mlkit_vision_barcode.zzvl;
import com.google.android.gms.internal.mlkit_vision_barcode.zzvt;
import com.google.android.gms.internal.mlkit_vision_barcode.zzvv;
import com.google.android.gms.internal.mlkit_vision_barcode.zzvw;
import com.google.android.gms.internal.mlkit_vision_barcode.zzwc;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.CommonConvertUtils;
import com.google.mlkit.vision.common.internal.ImageUtils;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes10.dex */
public final class b implements a {
    public static final zzcv h = zzcv.zzh(OptionalModuleUtils.BARCODE_MODULE_ID, OptionalModuleUtils.TFLITE_DYNAMITE_MODULE_ID);

    /* renamed from: a  reason: collision with root package name */
    public boolean f11624a;
    public boolean b;
    public boolean c;
    public final Context d;
    public final BarcodeScannerOptions e;
    public final zztx f;
    @Nullable
    public zzvt g;

    public b(Context context, BarcodeScannerOptions barcodeScannerOptions, zztx zztxVar) {
        this.d = context;
        this.e = barcodeScannerOptions;
        this.f = zztxVar;
    }

    public static boolean b(Context context) {
        return DynamiteModule.getLocalVersion(context, ModuleDescriptor.MODULE_ID) > 0;
    }

    @Override // com.google.mlkit.vision.barcode.internal.a
    @WorkerThread
    public final List a(InputImage inputImage) throws MlKitException {
        if (this.g == null) {
            zzc();
        }
        zzvt zzvtVar = (zzvt) Preconditions.checkNotNull(this.g);
        if (!this.f11624a) {
            try {
                zzvtVar.zze();
                this.f11624a = true;
            } catch (RemoteException e) {
                throw new MlKitException("Failed to init barcode scanner.", 13, e);
            }
        }
        int width = inputImage.getWidth();
        if (inputImage.getFormat() == 35) {
            width = ((Image.Plane[]) Preconditions.checkNotNull(inputImage.getPlanes()))[0].getRowStride();
        }
        try {
            List<zzvj> zzd = zzvtVar.zzd(ImageUtils.getInstance().getImageDataWrapper(inputImage), new zzwc(inputImage.getFormat(), width, inputImage.getHeight(), CommonConvertUtils.convertToMVRotation(inputImage.getRotationDegrees()), SystemClock.elapsedRealtime()));
            ArrayList arrayList = new ArrayList();
            for (zzvj zzvjVar : zzd) {
                arrayList.add(new Barcode(new zzm(zzvjVar), inputImage.getCoordinatesMatrix()));
            }
            return arrayList;
        } catch (RemoteException e2) {
            throw new MlKitException("Failed to run barcode scanner.", 13, e2);
        }
    }

    @VisibleForTesting
    public final zzvt c(DynamiteModule.VersionPolicy versionPolicy, String str, String str2) throws DynamiteModule.LoadingException, RemoteException {
        zzvw zza = zzvv.zza(DynamiteModule.load(this.d, versionPolicy, str).instantiate(str2));
        IObjectWrapper wrap = ObjectWrapper.wrap(this.d);
        int zza2 = this.e.zza();
        boolean z = true;
        if (!this.e.zzd() && this.e.zzb() == null) {
            z = false;
        }
        return zza.zzd(wrap, new zzvl(zza2, z));
    }

    @Override // com.google.mlkit.vision.barcode.internal.a
    @WorkerThread
    public final void zzb() {
        zzvt zzvtVar = this.g;
        if (zzvtVar != null) {
            try {
                zzvtVar.zzf();
            } catch (RemoteException e) {
                Log.e("DecoupledBarcodeScanner", "Failed to release barcode scanner.", e);
            }
            this.g = null;
            this.f11624a = false;
        }
    }

    @Override // com.google.mlkit.vision.barcode.internal.a
    @WorkerThread
    public final boolean zzc() throws MlKitException {
        if (this.g != null) {
            return this.b;
        }
        if (b(this.d)) {
            this.b = true;
            try {
                this.g = c(DynamiteModule.PREFER_LOCAL, ModuleDescriptor.MODULE_ID, "com.google.mlkit.vision.barcode.bundled.internal.ThickBarcodeScannerCreator");
            } catch (RemoteException e) {
                throw new MlKitException("Failed to create thick barcode scanner.", 13, e);
            } catch (DynamiteModule.LoadingException e2) {
                throw new MlKitException("Failed to load the bundled barcode module.", 13, e2);
            }
        } else {
            this.b = false;
            if (!OptionalModuleUtils.areAllRequiredModulesAvailable(this.d, h)) {
                if (!this.c) {
                    OptionalModuleUtils.requestDownload(this.d, zzcv.zzh(OptionalModuleUtils.BARCODE, OptionalModuleUtils.TFLITE_DYNAMITE));
                    this.c = true;
                }
                zzb.a(this.f, zzpj.OPTIONAL_MODULE_NOT_AVAILABLE);
                throw new MlKitException("Waiting for the barcode module to be downloaded. Please wait.", 14);
            }
            try {
                this.g = c(DynamiteModule.PREFER_REMOTE, OptionalModuleUtils.BARCODE_MODULE_ID, "com.google.android.gms.vision.barcode.mlkit.BarcodeScannerCreator");
            } catch (RemoteException | DynamiteModule.LoadingException e3) {
                zzb.a(this.f, zzpj.OPTIONAL_MODULE_INIT_ERROR);
                throw new MlKitException("Failed to create thin barcode scanner.", 13, e3);
            }
        }
        zzb.a(this.f, zzpj.NO_ERROR);
        return this.b;
    }
}
