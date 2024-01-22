package com.google.android.gms.vision.face.internal.client;

import android.content.Context;
import android.graphics.PointF;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.internal.vision.zzbe;
import com.google.android.gms.internal.vision.zzn;
import com.google.android.gms.internal.vision.zzp;
import com.google.android.gms.internal.vision.zzr;
import com.google.android.gms.vision.face.Contour;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.Landmark;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import java.nio.ByteBuffer;
/* loaded from: classes10.dex */
public final class zzb extends zzn<zzh> {
    public final zzf k;

    public zzb(Context context, zzf zzfVar) {
        super(context, "FaceNativeHandle", OptionalModuleUtils.FACE);
        zzbe.init(context);
        this.k = zzfVar;
        zzp();
    }

    @Override // com.google.android.gms.internal.vision.zzn
    public final /* synthetic */ zzh zza(DynamiteModule dynamiteModule, Context context) throws RemoteException, DynamiteModule.LoadingException {
        zzi asInterface;
        if (zzr.zza(context, "com.google.android.gms.vision.dynamite.face")) {
            asInterface = zzl.asInterface(dynamiteModule.instantiate("com.google.android.gms.vision.face.NativeFaceDetectorV2Creator"));
        } else {
            asInterface = zzl.asInterface(dynamiteModule.instantiate("com.google.android.gms.vision.face.ChimeraNativeFaceDetectorCreator"));
        }
        if (asInterface == null) {
            return null;
        }
        return asInterface.newFaceDetector(ObjectWrapper.wrap(context), this.k);
    }

    public final Face[] zzb(ByteBuffer byteBuffer, zzp zzpVar) {
        Landmark[] landmarkArr;
        FaceParcel[] faceParcelArr;
        Face[] faceArr;
        Contour[] contourArr;
        int i = 0;
        if (isOperational()) {
            try {
                FaceParcel[] zzc = zzp().zzc(ObjectWrapper.wrap(byteBuffer), zzpVar);
                Face[] faceArr2 = new Face[zzc.length];
                int i2 = 0;
                while (i2 < zzc.length) {
                    FaceParcel faceParcel = zzc[i2];
                    int i3 = faceParcel.id;
                    PointF pointF = new PointF(faceParcel.centerX, faceParcel.centerY);
                    float f = faceParcel.width;
                    float f2 = faceParcel.height;
                    float f3 = faceParcel.zzda;
                    float f4 = faceParcel.zzdb;
                    float f5 = faceParcel.zzdc;
                    LandmarkParcel[] landmarkParcelArr = faceParcel.zzdd;
                    if (landmarkParcelArr == null) {
                        faceParcelArr = zzc;
                        faceArr = faceArr2;
                        landmarkArr = new Landmark[i];
                    } else {
                        landmarkArr = new Landmark[landmarkParcelArr.length];
                        int i4 = i;
                        while (i4 < landmarkParcelArr.length) {
                            LandmarkParcel landmarkParcel = landmarkParcelArr[i4];
                            landmarkArr[i4] = new Landmark(new PointF(landmarkParcel.x, landmarkParcel.y), landmarkParcel.type);
                            i4++;
                            zzc = zzc;
                            faceArr2 = faceArr2;
                            landmarkParcelArr = landmarkParcelArr;
                        }
                        faceParcelArr = zzc;
                        faceArr = faceArr2;
                    }
                    zza[] zzaVarArr = faceParcel.zzde;
                    if (zzaVarArr == null) {
                        contourArr = new Contour[0];
                    } else {
                        Contour[] contourArr2 = new Contour[zzaVarArr.length];
                        for (int i5 = 0; i5 < zzaVarArr.length; i5++) {
                            zza zzaVar = zzaVarArr[i5];
                            contourArr2[i5] = new Contour(zzaVar.zzcy, zzaVar.type);
                        }
                        contourArr = contourArr2;
                    }
                    faceArr[i2] = new Face(i3, pointF, f, f2, f3, f4, f5, landmarkArr, contourArr, faceParcel.zzcf, faceParcel.zzcg, faceParcel.zzch, faceParcel.zzci);
                    i2++;
                    zzc = faceParcelArr;
                    faceArr2 = faceArr;
                    i = 0;
                }
                return faceArr2;
            } catch (RemoteException e) {
                Log.e("FaceNativeHandle", "Could not call native face detector", e);
                return new Face[0];
            }
        }
        return new Face[0];
    }

    public final boolean zzd(int i) {
        if (isOperational()) {
            try {
                return zzp().zzd(i);
            } catch (RemoteException e) {
                Log.e("FaceNativeHandle", "Could not call native face detector", e);
                return false;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.vision.zzn
    public final void zzn() throws RemoteException {
        zzp().zzm();
    }
}
