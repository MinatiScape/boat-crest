package com.google.android.gms.tagmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Collections;
@VisibleForTesting
/* loaded from: classes10.dex */
public final class zzdw implements zzcd {
    public static final String zza = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL,'%s' INTEGER NOT NULL);", "gtm_hits", "hit_id", "hit_time", "hit_url", "hit_first_send_time");
    public final zzdv zzb;
    public volatile zzbk zzc;
    public final Context zzd;
    public final String zze;
    public long zzf;
    public final Clock zzg;
    public final int zzh;
    public final zzez zzi;

    public zzdw(zzez zzezVar, Context context, byte[] bArr) {
        Context applicationContext = context.getApplicationContext();
        this.zzd = applicationContext;
        this.zze = "gtm_urls.db";
        this.zzi = zzezVar;
        this.zzg = DefaultClock.getInstance();
        this.zzb = new zzdv(this, applicationContext, "gtm_urls.db");
        this.zzc = new zzfj(applicationContext, new zzdu(this));
        this.zzf = 0L;
        this.zzh = 2000;
    }

    public static /* bridge */ /* synthetic */ void zzi(zzdw zzdwVar, long j, long j2) {
        SQLiteDatabase zzk = zzdwVar.zzk("Error opening database for getNumStoredHits.");
        if (zzk == null) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("hit_first_send_time", Long.valueOf(j2));
        try {
            zzk.update("gtm_hits", contentValues, "hit_id=?", new String[]{String.valueOf(j)});
        } catch (SQLiteException unused) {
            StringBuilder sb = new StringBuilder(69);
            sb.append("Error setting HIT_FIRST_DISPATCH_TIME for hitId: ");
            sb.append(j);
            zzdh.zzc(sb.toString());
            zzdwVar.zzl(j);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x01d4  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:144:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x010d A[Catch: all -> 0x00e9, TryCatch #5 {all -> 0x00e9, blocks: (B:19:0x009c, B:22:0x00a3, B:24:0x00b0, B:26:0x00db, B:25:0x00bf, B:43:0x00fd, B:45:0x010d, B:47:0x0117, B:48:0x0124, B:50:0x012a, B:55:0x013e, B:46:0x0112), top: B:122:0x0079 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0112 A[Catch: all -> 0x00e9, TryCatch #5 {all -> 0x00e9, blocks: (B:19:0x009c, B:22:0x00a3, B:24:0x00b0, B:26:0x00db, B:25:0x00bf, B:43:0x00fd, B:45:0x010d, B:47:0x0117, B:48:0x0124, B:50:0x012a, B:55:0x013e, B:46:0x0112), top: B:122:0x0079 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x012a A[Catch: all -> 0x00e9, TryCatch #5 {all -> 0x00e9, blocks: (B:19:0x009c, B:22:0x00a3, B:24:0x00b0, B:26:0x00db, B:25:0x00bf, B:43:0x00fd, B:45:0x010d, B:47:0x0117, B:48:0x0124, B:50:0x012a, B:55:0x013e, B:46:0x0112), top: B:122:0x0079 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x017a A[Catch: all -> 0x01e8, TryCatch #4 {all -> 0x01e8, blocks: (B:76:0x016a, B:78:0x017a, B:80:0x0184, B:79:0x017f), top: B:120:0x016a }] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x017f A[Catch: all -> 0x01e8, TryCatch #4 {all -> 0x01e8, blocks: (B:76:0x016a, B:78:0x017a, B:80:0x0184, B:79:0x017f), top: B:120:0x016a }] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01a0  */
    @Override // com.google.android.gms.tagmanager.zzcd
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zza() {
        /*
            Method dump skipped, instructions count: 497
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzdw.zza():void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ba, code lost:
        if (r2 == null) goto L14;
     */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00f0  */
    @Override // com.google.android.gms.tagmanager.zzcd
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzb(long r19, java.lang.String r21) {
        /*
            Method dump skipped, instructions count: 298
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzdw.zzb(long, java.lang.String):void");
    }

    public final int zzc() {
        SQLiteDatabase zzk = zzk("Error opening database for getNumStoredHits.");
        if (zzk != null) {
            Cursor cursor = null;
            try {
                try {
                    cursor = zzk.rawQuery("SELECT COUNT(*) from gtm_hits", null);
                    int i = cursor.moveToFirst() ? (int) cursor.getLong(0) : 0;
                    cursor.close();
                    return i;
                } catch (SQLiteException unused) {
                    zzdh.zzc("Error getting numStoredHits");
                    if (cursor == null) {
                        return 0;
                    }
                    cursor.close();
                    return 0;
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }
        return 0;
    }

    public final void zzj(String[] strArr) {
        int length;
        SQLiteDatabase zzk;
        if (strArr == null || (length = strArr.length) == 0 || (zzk = zzk("Error opening database for deleteHits.")) == null) {
            return;
        }
        boolean z = true;
        try {
            zzk.delete("gtm_hits", String.format("HIT_ID in (%s)", TextUtils.join(Constants.SEPARATOR_COMMA, Collections.nCopies(length, "?"))), strArr);
            zzez zzezVar = this.zzi;
            if (zzc() != 0) {
                z = false;
            }
            zzezVar.zza(z);
        } catch (SQLiteException unused) {
            zzdh.zzc("Error deleting hits");
        }
    }

    public final SQLiteDatabase zzk(String str) {
        try {
            return this.zzb.getWritableDatabase();
        } catch (SQLiteException unused) {
            zzdh.zzc(str);
            return null;
        }
    }

    public final void zzl(long j) {
        zzj(new String[]{String.valueOf(j)});
    }
}
