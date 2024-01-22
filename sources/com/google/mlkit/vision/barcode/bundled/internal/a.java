package com.google.mlkit.vision.barcode.bundled.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.media.Image;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzao;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzap;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzaq;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzar;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzas;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzat;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzau;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzav;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzaw;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzax;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzay;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzaz;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzba;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbc;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbk;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbu;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcb;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcd;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzci;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb;
import com.google.android.libraries.barhopper.BarhopperV3;
import com.google.android.libraries.barhopper.RecognitionOptions;
import com.google.barhopper.deeplearning.BarhopperV3Options;
import com.google.barhopper.deeplearning.zze;
import com.google.barhopper.deeplearning.zzf;
import com.google.barhopper.deeplearning.zzh;
import com.google.barhopper.deeplearning.zzi;
import com.google.barhopper.deeplearning.zzk;
import com.google.barhopper.deeplearning.zzm;
import com.google.mlkit.vision.common.internal.ImageUtils;
import com.google.photos.vision.barhopper.BarhopperProto$BarhopperResponse;
import com.google.photos.vision.barhopper.zzac;
import com.google.photos.vision.barhopper.zzad;
import com.google.photos.vision.barhopper.zzae;
import com.google.photos.vision.barhopper.zzag;
import com.google.photos.vision.barhopper.zzaj;
import com.google.photos.vision.barhopper.zzb;
import com.google.photos.vision.barhopper.zzc;
import com.google.photos.vision.barhopper.zzn;
import com.google.photos.vision.barhopper.zzp;
import com.google.photos.vision.barhopper.zzr;
import com.google.photos.vision.barhopper.zzt;
import com.google.photos.vision.barhopper.zzy;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes10.dex */
public final class a extends zzbk {
    public static final int[] d = {5, 7, 7, 7, 5, 5};
    public static final double[][] e = {new double[]{0.075d, 1.0d}, new double[]{0.1d, 1.0d}, new double[]{0.125d, 1.0d}, new double[]{0.2d, 2.0d}, new double[]{0.2d, 0.5d}, new double[]{0.15d, 1.0d}, new double[]{0.2d, 1.0d}, new double[]{0.25d, 1.0d}, new double[]{0.35d, 2.0d}, new double[]{0.35d, 0.5d}, new double[]{0.35d, 3.0d}, new double[]{0.35d, 0.3333d}, new double[]{0.3d, 1.0d}, new double[]{0.4d, 1.0d}, new double[]{0.5d, 1.0d}, new double[]{0.5d, 2.0d}, new double[]{0.5d, 0.5d}, new double[]{0.5d, 3.0d}, new double[]{0.5d, 0.3333d}, new double[]{0.6d, 1.0d}, new double[]{0.8d, 1.0d}, new double[]{1.0d, 1.0d}, new double[]{0.65d, 2.0d}, new double[]{0.65d, 0.5d}, new double[]{0.65d, 3.0d}, new double[]{0.65d, 0.3333d}, new double[]{1.0d, 1.0d}, new double[]{0.8d, 2.0d}, new double[]{0.8d, 0.5d}, new double[]{0.8d, 3.0d}, new double[]{0.8d, 0.3333d}, new double[]{1.0d, 1.0d}, new double[]{0.95d, 2.0d}, new double[]{0.95d, 0.5d}, new double[]{0.95d, 3.0d}, new double[]{0.95d, 0.3333d}};

    /* renamed from: a  reason: collision with root package name */
    public final Context f11610a;
    public final RecognitionOptions b;
    @Nullable
    public BarhopperV3 c;

    public a(Context context, zzbc zzbcVar) {
        RecognitionOptions recognitionOptions = new RecognitionOptions();
        this.b = recognitionOptions;
        this.f11610a = context;
        recognitionOptions.setBarcodeFormats(zzbcVar.zza());
        recognitionOptions.setOutputUnrecognizedBarcodes(zzbcVar.zzb());
    }

    @Nullable
    public static zzap a(@Nullable zzn zznVar, @Nullable String str, String str2) {
        if (zznVar == null || str == null) {
            return null;
        }
        Matcher matcher = Pattern.compile(str2).matcher(str);
        return new zzap(zznVar.zzf(), zznVar.zzd(), zznVar.zza(), zznVar.zzb(), zznVar.zzc(), zznVar.zze(), zznVar.zzj(), matcher.find() ? matcher.group(1) : null);
    }

    public final BarhopperProto$BarhopperResponse b(ByteBuffer byteBuffer, zzbu zzbuVar) {
        BarhopperV3 barhopperV3 = (BarhopperV3) Preconditions.checkNotNull(this.c);
        if (((ByteBuffer) Preconditions.checkNotNull(byteBuffer)).isDirect()) {
            return barhopperV3.recognize(zzbuVar.zzd(), zzbuVar.zza(), byteBuffer, this.b);
        }
        if (byteBuffer.hasArray() && byteBuffer.arrayOffset() == 0) {
            return barhopperV3.recognize(zzbuVar.zzd(), zzbuVar.zza(), byteBuffer.array(), this.b);
        }
        byte[] bArr = new byte[byteBuffer.remaining()];
        byteBuffer.get(bArr);
        return barhopperV3.recognize(zzbuVar.zzd(), zzbuVar.zza(), bArr, this.b);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbl
    public final List zzb(IObjectWrapper iObjectWrapper, zzbu zzbuVar) {
        BarhopperProto$BarhopperResponse recognize;
        zzat zzatVar;
        zzaw zzawVar;
        zzax zzaxVar;
        zzaz zzazVar;
        zzay zzayVar;
        zzau zzauVar;
        zzaq zzaqVar;
        int i;
        zzar zzarVar;
        zzas zzasVar;
        int i2;
        Point[] pointArr;
        int i3;
        int i4;
        zzaw[] zzawVarArr;
        zzat[] zzatVarArr;
        zzao[] zzaoVarArr;
        int zzb = zzbuVar.zzb();
        int i5 = -1;
        int i6 = 0;
        if (zzb != -1) {
            if (zzb != 17) {
                if (zzb == 35) {
                    recognize = b(((Image) Preconditions.checkNotNull((Image) ObjectWrapper.unwrap(iObjectWrapper))).getPlanes()[0].getBuffer(), zzbuVar);
                } else if (zzb != 842094169) {
                    throw new IllegalArgumentException("Unsupported image format: " + zzbuVar.zzb());
                }
            }
            recognize = b((ByteBuffer) ObjectWrapper.unwrap(iObjectWrapper), zzbuVar);
        } else {
            recognize = ((BarhopperV3) Preconditions.checkNotNull(this.c)).recognize((Bitmap) ObjectWrapper.unwrap(iObjectWrapper), this.b);
        }
        ArrayList arrayList = new ArrayList();
        Matrix uprightRotationMatrix = ImageUtils.getInstance().getUprightRotationMatrix(zzbuVar.zzd(), zzbuVar.zza(), zzbuVar.zzc());
        for (zzc zzcVar : recognize.zzc()) {
            if (zzcVar.zza() > 0 && uprightRotationMatrix != null) {
                float[] fArr = new float[8];
                List zzo = zzcVar.zzo();
                int zza = zzcVar.zza();
                for (int i7 = i6; i7 < zza; i7++) {
                    int i8 = i7 + i7;
                    fArr[i8] = ((zzae) zzo.get(i7)).zza();
                    fArr[i8 + 1] = ((zzae) zzo.get(i7)).zzb();
                }
                uprightRotationMatrix.mapPoints(fArr);
                int zzc = zzbuVar.zzc();
                for (int i9 = i6; i9 < zza; i9++) {
                    zzb zzbVar = (zzb) zzcVar.zzG();
                    int i10 = i9 + i9;
                    zzad zzc2 = zzae.zzc();
                    zzc2.zza((int) fArr[i10]);
                    zzc2.zzb((int) fArr[i10 + 1]);
                    zzbVar.zza((i9 + zzc) % zza, (zzae) zzc2.zzj());
                    zzcVar = (zzc) zzbVar.zzj();
                }
            }
            if (zzcVar.zzt()) {
                zzy zzh = zzcVar.zzh();
                zzatVar = new zzat(zzh.zzf() + i5, zzh.zzc(), zzh.zze(), zzh.zzd());
            } else {
                zzatVar = null;
            }
            if (zzcVar.zzv()) {
                zzci zzb2 = zzcVar.zzb();
                zzawVar = new zzaw(zzb2.zzd() + i5, zzb2.zzc());
            } else {
                zzawVar = null;
            }
            if (zzcVar.zzw()) {
                zzag zzj = zzcVar.zzj();
                zzaxVar = new zzax(zzj.zzc(), zzj.zzd());
            } else {
                zzaxVar = null;
            }
            if (zzcVar.zzy()) {
                com.google.photos.vision.barhopper.zzao zzl = zzcVar.zzl();
                zzazVar = new zzaz(zzl.zzd(), zzl.zzc(), zzl.zze() + i5);
            } else {
                zzazVar = null;
            }
            if (zzcVar.zzx()) {
                zzaj zzk = zzcVar.zzk();
                zzayVar = new zzay(zzk.zzc(), zzk.zzd());
            } else {
                zzayVar = null;
            }
            if (zzcVar.zzu()) {
                zzac zzi = zzcVar.zzi();
                zzauVar = new zzau(zzi.zza(), zzi.zzb());
            } else {
                zzauVar = null;
            }
            if (zzcVar.zzq()) {
                zzp zzd = zzcVar.zzd();
                zzaqVar = new zzaq(zzd.zzj(), zzd.zze(), zzd.zzf(), zzd.zzh(), zzd.zzi(), a(zzd.zzb(), zzcVar.zzm().zzn() ? zzcVar.zzm().zzu() : null, "DTSTART:([0-9TZ]*)"), a(zzd.zza(), zzcVar.zzm().zzn() ? zzcVar.zzm().zzu() : null, "DTEND:([0-9TZ]*)"));
            } else {
                zzaqVar = null;
            }
            if (zzcVar.zzr()) {
                zzr zze = zzcVar.zze();
                zzcd zza2 = zze.zza();
                zzav zzavVar = zza2 != null ? new zzav(zza2.zzd(), zza2.zzi(), zza2.zzh(), zza2.zzc(), zza2.zzf(), zza2.zze(), zza2.zzj()) : null;
                String zzd2 = zze.zzd();
                String zze2 = zze.zze();
                List zzi2 = zze.zzi();
                if (zzi2.isEmpty()) {
                    zzawVarArr = null;
                } else {
                    zzaw[] zzawVarArr2 = new zzaw[zzi2.size()];
                    for (int i11 = 0; i11 < zzi2.size(); i11++) {
                        zzawVarArr2[i11] = new zzaw(((zzci) zzi2.get(i11)).zzd() + i5, ((zzci) zzi2.get(i11)).zzc());
                    }
                    zzawVarArr = zzawVarArr2;
                }
                List zzh2 = zze.zzh();
                if (zzh2.isEmpty()) {
                    zzatVarArr = null;
                } else {
                    zzat[] zzatVarArr2 = new zzat[zzh2.size()];
                    int i12 = 0;
                    while (i12 < zzh2.size()) {
                        zzatVarArr2[i12] = new zzat(((zzy) zzh2.get(i12)).zzf() + i5, ((zzy) zzh2.get(i12)).zzc(), ((zzy) zzh2.get(i12)).zze(), ((zzy) zzh2.get(i12)).zzd());
                        i12++;
                        i5 = -1;
                    }
                    zzatVarArr = zzatVarArr2;
                }
                String[] strArr = (String[]) zze.zzj().toArray(new String[0]);
                List zzf = zze.zzf();
                if (zzf.isEmpty()) {
                    i = 0;
                    zzaoVarArr = null;
                } else {
                    zzao[] zzaoVarArr2 = new zzao[zzf.size()];
                    for (int i13 = 0; i13 < zzf.size(); i13++) {
                        zzaoVarArr2[i13] = new zzao(((zzcb) zzf.get(i13)).zzc() - 1, (String[]) ((zzcb) zzf.get(i13)).zzb().toArray(new String[0]));
                    }
                    i = 0;
                    zzaoVarArr = zzaoVarArr2;
                }
                zzarVar = new zzar(zzavVar, zzd2, zze2, zzawVarArr, zzatVarArr, strArr, zzaoVarArr);
            } else {
                i = 0;
                zzarVar = null;
            }
            if (zzcVar.zzs()) {
                zzt zzf2 = zzcVar.zzf();
                zzasVar = new zzas(zzf2.zzi(), zzf2.zzk(), zzf2.zzq(), zzf2.zzo(), zzf2.zzl(), zzf2.zze(), zzf2.zzc(), zzf2.zzd(), zzf2.zzf(), zzf2.zzp(), zzf2.zzm(), zzf2.zzj(), zzf2.zzh(), zzf2.zzn());
            } else {
                zzasVar = null;
            }
            switch (zzcVar.zzz() - 1) {
                case 0:
                    i2 = i;
                    break;
                case 1:
                    i2 = 1;
                    break;
                case 2:
                    i2 = 2;
                    break;
                case 3:
                    i2 = 4;
                    break;
                case 4:
                    i2 = 8;
                    break;
                case 5:
                    i2 = 16;
                    break;
                case 6:
                    i2 = 32;
                    break;
                case 7:
                    i2 = 64;
                    break;
                case 8:
                    i2 = 128;
                    break;
                case 9:
                    i2 = 256;
                    break;
                case 10:
                    i2 = 512;
                    break;
                case 11:
                    i2 = 1024;
                    break;
                case 12:
                    i2 = 2048;
                    break;
                case 13:
                    i2 = 4096;
                    break;
                default:
                    i2 = -1;
                    break;
            }
            String zzn = zzcVar.zzn();
            String zzu = zzcVar.zzm().zzn() ? zzcVar.zzm().zzu() : null;
            byte[] zzx = zzcVar.zzm().zzx();
            List zzo2 = zzcVar.zzo();
            if (zzo2.isEmpty()) {
                pointArr = null;
            } else {
                Point[] pointArr2 = new Point[zzo2.size()];
                for (int i14 = i; i14 < zzo2.size(); i14++) {
                    pointArr2[i14] = new Point(((zzae) zzo2.get(i14)).zza(), ((zzae) zzo2.get(i14)).zzb());
                }
                pointArr = pointArr2;
            }
            switch (zzcVar.zzA() - 1) {
                case 1:
                    i3 = 1;
                    continue;
                    arrayList.add(new zzba(i2, zzn, zzu, zzx, pointArr, i3, zzatVar, zzawVar, zzaxVar, zzazVar, zzayVar, zzauVar, zzaqVar, zzarVar, zzasVar));
                    i5 = -1;
                    i6 = i;
                case 2:
                    i3 = 2;
                    continue;
                    arrayList.add(new zzba(i2, zzn, zzu, zzx, pointArr, i3, zzatVar, zzawVar, zzaxVar, zzazVar, zzayVar, zzauVar, zzaqVar, zzarVar, zzasVar));
                    i5 = -1;
                    i6 = i;
                case 3:
                    i4 = 3;
                    break;
                case 4:
                    i3 = 4;
                    continue;
                    arrayList.add(new zzba(i2, zzn, zzu, zzx, pointArr, i3, zzatVar, zzawVar, zzaxVar, zzazVar, zzayVar, zzauVar, zzaqVar, zzarVar, zzasVar));
                    i5 = -1;
                    i6 = i;
                case 5:
                    i4 = 5;
                    break;
                case 6:
                    i4 = 6;
                    break;
                case 7:
                    i4 = 7;
                    break;
                case 8:
                    i3 = 8;
                    continue;
                    arrayList.add(new zzba(i2, zzn, zzu, zzx, pointArr, i3, zzatVar, zzawVar, zzaxVar, zzazVar, zzayVar, zzauVar, zzaqVar, zzarVar, zzasVar));
                    i5 = -1;
                    i6 = i;
                case 9:
                    i4 = 9;
                    break;
                case 10:
                    i4 = 10;
                    break;
                case 11:
                    i4 = 11;
                    break;
                case 12:
                    i4 = 12;
                    break;
                default:
                    i3 = i;
                    continue;
                    arrayList.add(new zzba(i2, zzn, zzu, zzx, pointArr, i3, zzatVar, zzawVar, zzaxVar, zzazVar, zzayVar, zzauVar, zzaqVar, zzarVar, zzasVar));
                    i5 = -1;
                    i6 = i;
            }
            i3 = i4;
            arrayList.add(new zzba(i2, zzn, zzu, zzx, pointArr, i3, zzatVar, zzawVar, zzaxVar, zzazVar, zzayVar, zzauVar, zzaqVar, zzarVar, zzasVar));
            i5 = -1;
            i6 = i;
        }
        return arrayList;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbl
    public final void zzc() {
        if (this.c != null) {
            return;
        }
        this.c = new BarhopperV3();
        zzh zza = zzi.zza();
        zze zza2 = zzf.zza();
        int i = 16;
        int i2 = 0;
        for (int i3 = 0; i3 < 6; i3++) {
            com.google.barhopper.deeplearning.zzb zza3 = com.google.barhopper.deeplearning.zzc.zza();
            zza3.zzc(i);
            zza3.zzd(i);
            for (int i4 = 0; i4 < d[i3]; i4++) {
                double[] dArr = e[i2];
                float sqrt = (float) Math.sqrt(dArr[1]);
                float f = (float) (dArr[0] * 320.0d);
                zza3.zza(f / sqrt);
                zza3.zzb(f * sqrt);
                i2++;
            }
            i += i;
            zza2.zza(zza3);
        }
        zza.zza(zza2);
        try {
            InputStream open = this.f11610a.getAssets().open("mlkit_barcode_models/barcode_ssd_mobilenet_v1_dmp25_quant.tflite");
            InputStream open2 = this.f11610a.getAssets().open("mlkit_barcode_models/oned_auto_regressor_mobile.tflite");
            try {
                InputStream open3 = this.f11610a.getAssets().open("mlkit_barcode_models/oned_feature_extractor_mobile.tflite");
                zzk zza4 = BarhopperV3Options.zza();
                zza.zzb(zzdb.zzs(open));
                zza4.zza(zza);
                zzm zza5 = com.google.barhopper.deeplearning.zzn.zza();
                zza5.zza(zzdb.zzs(open2));
                zza5.zzb(zzdb.zzs(open3));
                zza4.zzb(zza5);
                ((BarhopperV3) Preconditions.checkNotNull(this.c)).create(zza4.zzj());
                if (open3 != null) {
                    open3.close();
                }
                if (open2 != null) {
                    open2.close();
                }
                if (open != null) {
                    open.close();
                }
            } catch (Throwable th) {
                if (open2 != null) {
                    try {
                        open2.close();
                    } catch (Throwable th2) {
                        Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                    }
                }
                throw th;
            }
        } catch (IOException e2) {
            throw new IllegalStateException("Failed to open Barcode models", e2);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbl
    public final void zzd() {
        BarhopperV3 barhopperV3 = this.c;
        if (barhopperV3 != null) {
            barhopperV3.close();
            this.c = null;
        }
    }
}
