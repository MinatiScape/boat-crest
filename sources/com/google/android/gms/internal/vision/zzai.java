package com.google.android.gms.internal.vision;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
/* loaded from: classes10.dex */
public final class zzai extends zzn<zzy> {
    public final zzah k;

    public zzai(Context context, zzah zzahVar) {
        super(context, "TextNativeHandle", OptionalModuleUtils.OCR);
        this.k = zzahVar;
        zzp();
    }

    public final zzac[] zza(Bitmap bitmap, zzp zzpVar, zzae zzaeVar) {
        if (isOperational()) {
            try {
                return zzp().zza(ObjectWrapper.wrap(bitmap), zzpVar, zzaeVar);
            } catch (RemoteException e) {
                Log.e("TextNativeHandle", "Error calling native text recognizer", e);
                return new zzac[0];
            }
        }
        return new zzac[0];
    }

    @Override // com.google.android.gms.internal.vision.zzn
    public final void zzn() throws RemoteException {
        zzp().zzq();
    }

    @Override // com.google.android.gms.internal.vision.zzn
    public final /* synthetic */ zzy zza(DynamiteModule dynamiteModule, Context context) throws RemoteException, DynamiteModule.LoadingException {
        zzaa zzzVar;
        IBinder instantiate = dynamiteModule.instantiate("com.google.android.gms.vision.text.ChimeraNativeTextRecognizerCreator");
        if (instantiate == null) {
            zzzVar = null;
        } else {
            IInterface queryLocalInterface = instantiate.queryLocalInterface("com.google.android.gms.vision.text.internal.client.INativeTextRecognizerCreator");
            if (queryLocalInterface instanceof zzaa) {
                zzzVar = (zzaa) queryLocalInterface;
            } else {
                zzzVar = new zzz(instantiate);
            }
        }
        if (zzzVar == null) {
            return null;
        }
        return zzzVar.zza(ObjectWrapper.wrap(context), this.k);
    }
}
