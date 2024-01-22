package com.google.android.gms.vision.label.internal.client;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.internal.vision.zzn;
import com.google.android.gms.internal.vision.zzr;
import com.google.android.gms.vision.label.ImageLabel;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
/* loaded from: classes10.dex */
public final class zzi extends zzn<INativeImageLabeler> {
    public ImageLabelerOptions k;

    public zzi(Context context, ImageLabelerOptions imageLabelerOptions) {
        super(context, "LabelerNativeHandle", OptionalModuleUtils.ICA);
        this.k = imageLabelerOptions;
        zzp();
    }

    public final ImageLabel[] zza(Bitmap bitmap, LabelOptions labelOptions) {
        if (isOperational()) {
            try {
                zzf[] zza = zzp().zza(ObjectWrapper.wrap(bitmap), labelOptions);
                ImageLabel[] imageLabelArr = new ImageLabel[zza.length];
                for (int i = 0; i != zza.length; i++) {
                    imageLabelArr[i] = new ImageLabel(zza[i].zzdo, zza[i].label, zza[i].zzdp);
                }
                return imageLabelArr;
            } catch (RemoteException e) {
                Log.e("LabelerNativeHandle", "Error calling native image labeler", e);
                return new ImageLabel[0];
            }
        }
        return new ImageLabel[0];
    }

    @Override // com.google.android.gms.internal.vision.zzn
    public final void zzn() throws RemoteException {
        zzp().zzq();
    }

    @Override // com.google.android.gms.internal.vision.zzn
    public final /* synthetic */ INativeImageLabeler zza(DynamiteModule dynamiteModule, Context context) throws RemoteException, DynamiteModule.LoadingException {
        zza asInterface;
        if (zzr.zza(context, "com.google.android.gms.vision.dynamite.ica")) {
            asInterface = zzd.asInterface(dynamiteModule.instantiate("com.google.android.gms.vision.label.NativeImageLabelerCreator"));
        } else {
            asInterface = zzd.asInterface(dynamiteModule.instantiate("com.google.android.gms.vision.label.ChimeraNativeImageLabelerCreator"));
        }
        if (asInterface == null) {
            return null;
        }
        return asInterface.newImageLabeler(ObjectWrapper.wrap(context), this.k);
    }
}
