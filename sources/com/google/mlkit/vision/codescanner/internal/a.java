package com.google.mlkit.vision.codescanner.internal;

import android.graphics.Point;
import android.graphics.Rect;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_code_scanner.zzon;
import com.google.android.gms.internal.mlkit_code_scanner.zzoo;
import com.google.android.gms.internal.mlkit_code_scanner.zzop;
import com.google.android.gms.internal.mlkit_code_scanner.zzoq;
import com.google.android.gms.internal.mlkit_code_scanner.zzor;
import com.google.android.gms.internal.mlkit_code_scanner.zzos;
import com.google.android.gms.internal.mlkit_code_scanner.zzot;
import com.google.android.gms.internal.mlkit_code_scanner.zzou;
import com.google.android.gms.internal.mlkit_code_scanner.zzov;
import com.google.android.gms.internal.mlkit_code_scanner.zzow;
import com.google.android.gms.internal.mlkit_code_scanner.zzox;
import com.google.android.gms.internal.mlkit_code_scanner.zzoy;
import com.google.android.gms.internal.mlkit_code_scanner.zzoz;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.barcode.common.internal.BarcodeSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes10.dex */
public final class a implements BarcodeSource {

    /* renamed from: a  reason: collision with root package name */
    public final zzoz f11632a;

    public a(zzoz zzozVar) {
        this.f11632a = zzozVar;
    }

    @Nullable
    public static Barcode.CalendarDateTime a(@Nullable zzoo zzooVar) {
        if (zzooVar == null) {
            return null;
        }
        return new Barcode.CalendarDateTime(zzooVar.zzf(), zzooVar.zzd(), zzooVar.zza(), zzooVar.zzb(), zzooVar.zzc(), zzooVar.zze(), zzooVar.zzh(), zzooVar.zzg());
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    @Nullable
    public final Rect getBoundingBox() {
        return null;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    @Nullable
    public final Barcode.CalendarEvent getCalendarEvent() {
        zzop zzc = this.f11632a.zzc();
        if (zzc != null) {
            return new Barcode.CalendarEvent(zzc.zzg(), zzc.zzc(), zzc.zzd(), zzc.zze(), zzc.zzf(), a(zzc.zzb()), a(zzc.zza()));
        }
        return null;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    @Nullable
    public final Barcode.ContactInfo getContactInfo() {
        List arrayList;
        zzoq zzd = this.f11632a.zzd();
        if (zzd != null) {
            zzou zza = zzd.zza();
            Barcode.PersonName personName = zza != null ? new Barcode.PersonName(zza.zzb(), zza.zzf(), zza.zze(), zza.zza(), zza.zzd(), zza.zzc(), zza.zzg()) : null;
            String zzb = zzd.zzb();
            String zzc = zzd.zzc();
            zzov[] zzf = zzd.zzf();
            ArrayList arrayList2 = new ArrayList();
            if (zzf != null) {
                for (zzov zzovVar : zzf) {
                    if (zzovVar != null) {
                        arrayList2.add(new Barcode.Phone(zzovVar.zzb(), zzovVar.zza()));
                    }
                }
            }
            zzos[] zze = zzd.zze();
            ArrayList arrayList3 = new ArrayList();
            if (zze != null) {
                for (zzos zzosVar : zze) {
                    if (zzosVar != null) {
                        arrayList3.add(new Barcode.Email(zzosVar.zza(), zzosVar.zzb(), zzosVar.zzd(), zzosVar.zzc()));
                    }
                }
            }
            if (zzd.zzg() != null) {
                arrayList = Arrays.asList((String[]) Preconditions.checkNotNull(zzd.zzg()));
            } else {
                arrayList = new ArrayList();
            }
            List list = arrayList;
            zzon[] zzd2 = zzd.zzd();
            ArrayList arrayList4 = new ArrayList();
            if (zzd2 != null) {
                for (zzon zzonVar : zzd2) {
                    if (zzonVar != null) {
                        arrayList4.add(new Barcode.Address(zzonVar.zza(), zzonVar.zzb()));
                    }
                }
            }
            return new Barcode.ContactInfo(personName, zzb, zzc, arrayList2, arrayList3, list, arrayList4);
        }
        return null;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    @Nullable
    public final Point[] getCornerPoints() {
        return null;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    @Nullable
    public final String getDisplayValue() {
        return this.f11632a.zzl();
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    @Nullable
    public final Barcode.DriverLicense getDriverLicense() {
        zzor zze = this.f11632a.zze();
        if (zze != null) {
            return new Barcode.DriverLicense(zze.zzf(), zze.zzh(), zze.zzn(), zze.zzl(), zze.zzi(), zze.zzc(), zze.zza(), zze.zzb(), zze.zzd(), zze.zzm(), zze.zzj(), zze.zzg(), zze.zze(), zze.zzk());
        }
        return null;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    @Nullable
    public final Barcode.Email getEmail() {
        zzos zzf = this.f11632a.zzf();
        if (zzf == null) {
            return null;
        }
        return new Barcode.Email(zzf.zza(), zzf.zzb(), zzf.zzd(), zzf.zzc());
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final int getFormat() {
        return this.f11632a.zza();
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    @Nullable
    public final Barcode.GeoPoint getGeoPoint() {
        zzot zzg = this.f11632a.zzg();
        if (zzg != null) {
            return new Barcode.GeoPoint(zzg.zza(), zzg.zzb());
        }
        return null;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    @Nullable
    public final Barcode.Phone getPhone() {
        zzov zzh = this.f11632a.zzh();
        if (zzh != null) {
            return new Barcode.Phone(zzh.zzb(), zzh.zza());
        }
        return null;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    @Nullable
    public final byte[] getRawBytes() {
        return this.f11632a.zzn();
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    @Nullable
    public final String getRawValue() {
        return this.f11632a.zzm();
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    @Nullable
    public final Barcode.Sms getSms() {
        zzow zzi = this.f11632a.zzi();
        if (zzi != null) {
            return new Barcode.Sms(zzi.zza(), zzi.zzb());
        }
        return null;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    @Nullable
    public final Barcode.UrlBookmark getUrl() {
        zzox zzj = this.f11632a.zzj();
        if (zzj != null) {
            return new Barcode.UrlBookmark(zzj.zza(), zzj.zzb());
        }
        return null;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final int getValueType() {
        return this.f11632a.zzb();
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    @Nullable
    public final Barcode.WiFi getWifi() {
        zzoy zzk = this.f11632a.zzk();
        if (zzk != null) {
            return new Barcode.WiFi(zzk.zzc(), zzk.zzb(), zzk.zza());
        }
        return null;
    }
}
