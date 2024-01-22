package com.google.mlkit.vision.text.internal;

import android.graphics.Matrix;
import android.graphics.Point;
import android.util.SparseArray;
import androidx.annotation.Nullable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.mlkit_vision_text_common.zzab;
import com.google.android.gms.internal.mlkit_vision_text_common.zzbj;
import com.google.android.gms.internal.mlkit_vision_text_common.zzbm;
import com.google.android.gms.internal.mlkit_vision_text_common.zzbw;
import com.google.android.gms.internal.mlkit_vision_text_common.zzcr;
import com.google.android.gms.internal.mlkit_vision_text_common.zzr;
import com.google.android.gms.internal.mlkit_vision_text_common.zzu;
import com.google.android.gms.internal.mlkit_vision_text_common.zzv;
import com.google.mlkit.vision.text.Text;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes10.dex */
public final class d {
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    public static final zzv f11646a = zzv.zza("\n");
    public static final Comparator b = new Comparator() { // from class: com.google.mlkit.vision.text.internal.zzh
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return ((Integer) ((Map.Entry) obj).getValue()).compareTo((Integer) ((Map.Entry) obj2).getValue());
        }
    };

    public static Text a(com.google.android.gms.internal.mlkit_vision_text_common.zzl[] zzlVarArr, @Nullable final Matrix matrix) {
        SparseArray sparseArray = new SparseArray();
        int i = 0;
        for (com.google.android.gms.internal.mlkit_vision_text_common.zzl zzlVar : zzlVarArr) {
            SparseArray sparseArray2 = (SparseArray) sparseArray.get(zzlVar.zzj);
            if (sparseArray2 == null) {
                sparseArray2 = new SparseArray();
                sparseArray.append(zzlVar.zzj, sparseArray2);
            }
            sparseArray2.append(zzlVar.zzk, zzlVar);
        }
        zzbj zzg = zzbm.zzg();
        int i2 = 0;
        while (i2 < sparseArray.size()) {
            SparseArray sparseArray3 = (SparseArray) sparseArray.valueAt(i2);
            zzbj zzg2 = zzbm.zzg();
            for (int i3 = i; i3 < sparseArray3.size(); i3++) {
                zzg2.zzb((com.google.android.gms.internal.mlkit_vision_text_common.zzl) sparseArray3.valueAt(i3));
            }
            zzbm zzc = zzg2.zzc();
            List zza = zzbw.zza(zzc, new zzu() { // from class: com.google.mlkit.vision.text.internal.zzd
                @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzu
                public final Object zza(Object obj) {
                    final Matrix matrix2 = matrix;
                    com.google.android.gms.internal.mlkit_vision_text_common.zzl zzlVar2 = (com.google.android.gms.internal.mlkit_vision_text_common.zzl) obj;
                    List b2 = a.b(zzlVar2.zzb);
                    return new Text.Line(zzab.zzb(zzlVar2.zze) ? "" : zzlVar2.zze, a.a(b2), b2, zzab.zzb(zzlVar2.zzg) ? "und" : zzlVar2.zzg, matrix2, zzbw.zza(Arrays.asList(zzlVar2.zza), new zzu() { // from class: com.google.mlkit.vision.text.internal.zzg
                        @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzu
                        public final Object zza(Object obj2) {
                            Matrix matrix3 = matrix2;
                            zzr zzrVar = (zzr) obj2;
                            List b3 = a.b(zzrVar.zzb);
                            return new Text.Element(zzab.zzb(zzrVar.zzd) ? "" : zzrVar.zzd, a.a(b3), b3, zzab.zzb(zzrVar.zzf) ? "und" : zzrVar.zzf, matrix3, zzrVar.zze, zzrVar.zzb.zze, zzbm.zzj());
                        }
                    }), zzlVar2.zzf, zzlVar2.zzb.zze);
                }
            });
            com.google.android.gms.internal.mlkit_vision_text_common.zzf zzfVar = ((com.google.android.gms.internal.mlkit_vision_text_common.zzl) zzc.get(i)).zzb;
            zzcr listIterator = zzc.listIterator(i);
            int i4 = Integer.MIN_VALUE;
            int i5 = Integer.MAX_VALUE;
            int i6 = Integer.MAX_VALUE;
            int i7 = Integer.MIN_VALUE;
            while (listIterator.hasNext()) {
                com.google.android.gms.internal.mlkit_vision_text_common.zzf zzfVar2 = ((com.google.android.gms.internal.mlkit_vision_text_common.zzl) listIterator.next()).zzb;
                int i8 = zzfVar.zza;
                int i9 = zzfVar.zzb;
                zzcr zzcrVar = listIterator;
                double sin = Math.sin(Math.toRadians(zzfVar.zze));
                SparseArray sparseArray4 = sparseArray;
                int i10 = i2;
                double cos = Math.cos(Math.toRadians(zzfVar.zze));
                zzbj zzbjVar = zzg;
                List list = zza;
                Point point = new Point(zzfVar2.zza, zzfVar2.zzb);
                point.offset(-i8, -i9);
                int i11 = (int) ((r13[0].x * cos) + (r13[0].y * sin));
                int i12 = (int) (((-r13[0].x) * sin) + (r13[0].y * cos));
                r13[0].x = i11;
                r13[0].y = i12;
                Point[] pointArr = {point, new Point(zzfVar2.zzc + i11, i12), new Point(zzfVar2.zzc + i11, zzfVar2.zzd + i12), new Point(i11, i12 + zzfVar2.zzd)};
                for (int i13 = 0; i13 < 4; i13++) {
                    Point point2 = pointArr[i13];
                    i5 = Math.min(i5, point2.x);
                    i4 = Math.max(i4, point2.x);
                    i6 = Math.min(i6, point2.y);
                    i7 = Math.max(i7, point2.y);
                }
                listIterator = zzcrVar;
                sparseArray = sparseArray4;
                i2 = i10;
                zzg = zzbjVar;
                zza = list;
            }
            zzbj zzbjVar2 = zzg;
            SparseArray sparseArray5 = sparseArray;
            int i14 = i2;
            List list2 = zza;
            int i15 = zzfVar.zza;
            int i16 = zzfVar.zzb;
            double sin2 = Math.sin(Math.toRadians(zzfVar.zze));
            double cos2 = Math.cos(Math.toRadians(zzfVar.zze));
            Point[] pointArr2 = {new Point(i5, i6), new Point(i4, i6), new Point(i4, i7), new Point(i5, i7)};
            int i17 = 0;
            for (int i18 = 4; i17 < i18; i18 = 4) {
                int i19 = pointArr2[i17].x;
                int i20 = pointArr2[i17].y;
                int i21 = pointArr2[i17].x;
                int i22 = pointArr2[i17].y;
                pointArr2[i17].x = (int) ((i19 * cos2) - (i20 * sin2));
                pointArr2[i17].y = (int) ((i21 * sin2) + (i22 * cos2));
                pointArr2[i17].offset(i15, i16);
                i17++;
            }
            List asList = Arrays.asList(pointArr2);
            zzbjVar2.zzb(new Text.TextBlock(f11646a.zzb(zzbw.zza(list2, new zzu() { // from class: com.google.mlkit.vision.text.internal.zze
                @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzu
                public final Object zza(Object obj) {
                    return ((Text.Line) obj).getText();
                }
            })), a.a(asList), asList, b(list2), matrix, list2));
            i2 = i14 + 1;
            zzg = zzbjVar2;
            sparseArray = sparseArray5;
            i = 0;
        }
        zzbm zzc2 = zzg.zzc();
        return new Text(f11646a.zzb(zzbw.zza(zzc2, new zzu() { // from class: com.google.mlkit.vision.text.internal.zzf
            @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzu
            public final Object zza(Object obj) {
                return ((Text.TextBlock) obj).getText();
            }
        })), zzc2);
    }

    public static String b(List list) {
        HashMap hashMap = new HashMap();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String recognizedLanguage = ((Text.Line) it.next()).getRecognizedLanguage();
            hashMap.put(recognizedLanguage, Integer.valueOf((hashMap.containsKey(recognizedLanguage) ? ((Integer) hashMap.get(recognizedLanguage)).intValue() : 0) + 1));
        }
        Set entrySet = hashMap.entrySet();
        if (entrySet.isEmpty()) {
            return "und";
        }
        String str = (String) ((Map.Entry) Collections.max(entrySet, b)).getKey();
        return zzab.zzb(str) ? "und" : str;
    }
}
