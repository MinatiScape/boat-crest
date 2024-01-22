package com.google.android.gms.tagmanager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.internal.gtm.zzfz;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
/* loaded from: classes10.dex */
public final class zzbe implements zzax {
    public static final String zza = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' STRING NOT NULL, '%s' BLOB NOT NULL, '%s' INTEGER NOT NULL);", "datalayer", "ID", Constants.KEY_KEY, "value", "expires");
    public final Executor zzb;
    public final Context zzc;
    public final zzbc zzd;
    public final Clock zze;

    public zzbe(Context context) {
        Clock defaultClock = DefaultClock.getInstance();
        ExecutorService zza2 = zzfz.zza().zza(2);
        this.zzc = context;
        this.zze = defaultClock;
        this.zzb = zza2;
        this.zzd = new zzbc(this, context, "google_tagmanager.db");
    }

    public static /* bridge */ /* synthetic */ List zzf(zzbe zzbeVar) {
        ObjectInputStream objectInputStream;
        try {
            zzbeVar.zzk(zzbeVar.zze.currentTimeMillis());
            SQLiteDatabase zzi = zzbeVar.zzi("Error opening database for loadSerialized.");
            ArrayList<zzbd> arrayList = new ArrayList();
            if (zzi != null) {
                Cursor query = zzi.query("datalayer", new String[]{Constants.KEY_KEY, "value"}, null, null, null, null, "ID", null);
                while (query.moveToNext()) {
                    arrayList.add(new zzbd(query.getString(0), query.getBlob(1)));
                }
                query.close();
            }
            ArrayList arrayList2 = new ArrayList();
            for (zzbd zzbdVar : arrayList) {
                String str = zzbdVar.zza;
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(zzbdVar.zzb);
                ObjectInputStream objectInputStream2 = null;
                r2 = null;
                r2 = null;
                r2 = null;
                Object obj = null;
                try {
                    objectInputStream = new ObjectInputStream(byteArrayInputStream);
                } catch (IOException unused) {
                    objectInputStream = null;
                } catch (ClassNotFoundException unused2) {
                    objectInputStream = null;
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    obj = objectInputStream.readObject();
                    try {
                        objectInputStream.close();
                    } catch (IOException unused3) {
                    }
                } catch (IOException unused4) {
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    byteArrayInputStream.close();
                    arrayList2.add(new zzau(str, obj));
                } catch (ClassNotFoundException unused5) {
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    byteArrayInputStream.close();
                    arrayList2.add(new zzau(str, obj));
                } catch (Throwable th2) {
                    th = th2;
                    objectInputStream2 = objectInputStream;
                    if (objectInputStream2 != null) {
                        try {
                            objectInputStream2.close();
                        } catch (IOException unused6) {
                            throw th;
                        }
                    }
                    byteArrayInputStream.close();
                    throw th;
                }
                byteArrayInputStream.close();
                arrayList2.add(new zzau(str, obj));
            }
            return arrayList2;
        } finally {
            zzbeVar.zzj();
        }
    }

    public static /* bridge */ /* synthetic */ void zzg(zzbe zzbeVar, String str) {
        SQLiteDatabase zzi = zzbeVar.zzi("Error opening database for clearKeysWithPrefix.");
        if (zzi == null) {
            return;
        }
        try {
            int delete = zzi.delete("datalayer", "key = ? OR key LIKE ?", new String[]{str, String.valueOf(str).concat(".%")});
            StringBuilder sb = new StringBuilder(25);
            sb.append("Cleared ");
            sb.append(delete);
            sb.append(" items");
            zzdh.zzb.zzd(sb.toString());
        } catch (SQLiteException e) {
            String valueOf = String.valueOf(e);
            StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 44 + valueOf.length());
            sb2.append("Error deleting entries with key prefix: ");
            sb2.append(str);
            sb2.append(" (");
            sb2.append(valueOf);
            sb2.append(").");
            zzdh.zzc(sb2.toString());
        } finally {
            zzbeVar.zzj();
        }
    }

    @Override // com.google.android.gms.tagmanager.zzax
    public final void zza(String str) {
        this.zzb.execute(new zzbb(this, str));
    }

    @Override // com.google.android.gms.tagmanager.zzax
    public final void zzb(zzaw zzawVar) {
        this.zzb.execute(new zzba(this, zzawVar));
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0042, code lost:
        if (r6 == null) goto L11;
     */
    @Override // com.google.android.gms.tagmanager.zzax
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzc(java.util.List<com.google.android.gms.tagmanager.zzau> r8, long r9) {
        /*
            r7 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Iterator r8 = r8.iterator()
        L9:
            boolean r1 = r8.hasNext()
            if (r1 == 0) goto L4c
            java.lang.Object r1 = r8.next()
            com.google.android.gms.tagmanager.zzau r1 = (com.google.android.gms.tagmanager.zzau) r1
            com.google.android.gms.tagmanager.zzbd r2 = new com.google.android.gms.tagmanager.zzbd
            java.lang.String r3 = r1.zza
            java.lang.Object r1 = r1.zzb
            java.io.ByteArrayOutputStream r4 = new java.io.ByteArrayOutputStream
            r4.<init>()
            r5 = 0
            java.io.ObjectOutputStream r6 = new java.io.ObjectOutputStream     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L41
            r6.<init>(r4)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L41
            r6.writeObject(r1)     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L42
            byte[] r5 = r4.toByteArray()     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L42
        L2d:
            r6.close()     // Catch: java.io.IOException -> L45
        L30:
            r4.close()     // Catch: java.io.IOException -> L45
            goto L45
        L34:
            r8 = move-exception
            r5 = r6
            goto L38
        L37:
            r8 = move-exception
        L38:
            if (r5 == 0) goto L3d
            r5.close()     // Catch: java.io.IOException -> L40
        L3d:
            r4.close()     // Catch: java.io.IOException -> L40
        L40:
            throw r8
        L41:
            r6 = r5
        L42:
            if (r6 == 0) goto L30
            goto L2d
        L45:
            r2.<init>(r3, r5)
            r0.add(r2)
            goto L9
        L4c:
            java.util.concurrent.Executor r8 = r7.zzb
            com.google.android.gms.tagmanager.zzaz r1 = new com.google.android.gms.tagmanager.zzaz
            r1.<init>(r7, r0, r9)
            r8.execute(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzbe.zzc(java.util.List, long):void");
    }

    public final SQLiteDatabase zzi(String str) {
        try {
            return this.zzd.getWritableDatabase();
        } catch (SQLiteException unused) {
            zzdh.zzc(str);
            return null;
        }
    }

    public final void zzj() {
        try {
            this.zzd.close();
        } catch (SQLiteException unused) {
        }
    }

    public final void zzk(long j) {
        SQLiteDatabase zzi = zzi("Error opening database for deleteOlderThan.");
        if (zzi == null) {
            return;
        }
        try {
            int delete = zzi.delete("datalayer", "expires <= ?", new String[]{Long.toString(j)});
            StringBuilder sb = new StringBuilder(33);
            sb.append("Deleted ");
            sb.append(delete);
            sb.append(" expired items");
            zzdh.zzb.zzd(sb.toString());
        } catch (SQLiteException unused) {
            zzdh.zzc("Error deleting old entries.");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:55:0x00c0, code lost:
        if (r8 != null) goto L46;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0047 A[Catch: all -> 0x018b, TRY_LEAVE, TryCatch #9 {, blocks: (B:85:0x017e, B:4:0x0003, B:22:0x0042, B:24:0x0047, B:57:0x00c3, B:59:0x00ee, B:62:0x00f2, B:64:0x00fa, B:65:0x0115, B:67:0x011b, B:69:0x012b, B:71:0x0135, B:70:0x0130, B:37:0x0092, B:75:0x013d, B:76:0x0140, B:77:0x0141, B:80:0x014c, B:81:0x0150, B:83:0x0156, B:14:0x002f, B:21:0x003e, B:91:0x0187, B:92:0x018a), top: B:113:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00b3 A[Catch: all -> 0x0139, TryCatch #11 {all -> 0x0139, blocks: (B:33:0x007b, B:35:0x0081, B:50:0x00a3, B:52:0x00b3, B:54:0x00bd, B:53:0x00b8), top: B:107:0x007b }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00b8 A[Catch: all -> 0x0139, TryCatch #11 {all -> 0x0139, blocks: (B:33:0x007b, B:35:0x0081, B:50:0x00a3, B:52:0x00b3, B:54:0x00bd, B:53:0x00b8), top: B:107:0x007b }] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x013d A[Catch: all -> 0x018b, TryCatch #9 {, blocks: (B:85:0x017e, B:4:0x0003, B:22:0x0042, B:24:0x0047, B:57:0x00c3, B:59:0x00ee, B:62:0x00f2, B:64:0x00fa, B:65:0x0115, B:67:0x011b, B:69:0x012b, B:71:0x0135, B:70:0x0130, B:37:0x0092, B:75:0x013d, B:76:0x0140, B:77:0x0141, B:80:0x014c, B:81:0x0150, B:83:0x0156, B:14:0x002f, B:21:0x003e, B:91:0x0187, B:92:0x018a), top: B:113:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x014c A[Catch: all -> 0x018b, TryCatch #9 {, blocks: (B:85:0x017e, B:4:0x0003, B:22:0x0042, B:24:0x0047, B:57:0x00c3, B:59:0x00ee, B:62:0x00f2, B:64:0x00fa, B:65:0x0115, B:67:0x011b, B:69:0x012b, B:71:0x0135, B:70:0x0130, B:37:0x0092, B:75:0x013d, B:76:0x0140, B:77:0x0141, B:80:0x014c, B:81:0x0150, B:83:0x0156, B:14:0x002f, B:21:0x003e, B:91:0x0187, B:92:0x018a), top: B:113:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0187 A[Catch: all -> 0x018b, TRY_ENTER, TryCatch #9 {, blocks: (B:85:0x017e, B:4:0x0003, B:22:0x0042, B:24:0x0047, B:57:0x00c3, B:59:0x00ee, B:62:0x00f2, B:64:0x00fa, B:65:0x0115, B:67:0x011b, B:69:0x012b, B:71:0x0135, B:70:0x0130, B:37:0x0092, B:75:0x013d, B:76:0x0140, B:77:0x0141, B:80:0x014c, B:81:0x0150, B:83:0x0156, B:14:0x002f, B:21:0x003e, B:91:0x0187, B:92:0x018a), top: B:113:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized void zzl(java.util.List<com.google.android.gms.tagmanager.zzbd> r18, long r19) {
        /*
            Method dump skipped, instructions count: 403
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzbe.zzl(java.util.List, long):void");
    }
}
