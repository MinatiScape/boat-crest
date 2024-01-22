package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.gtm.zzrl;
import com.google.android.gms.internal.gtm.zzto;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
/* loaded from: classes10.dex */
public final class zzel implements Runnable {
    public final /* synthetic */ zzrl zza;
    public final /* synthetic */ zzem zzb;

    public zzel(zzem zzemVar, zzrl zzrlVar) {
        this.zzb = zzemVar;
        this.zza = zzrlVar;
    }

    /* JADX WARN: Not initialized variable reg: 3, insn: 0x0069: INVOKE  (r3 I:java.io.FileOutputStream) type: VIRTUAL call: java.io.FileOutputStream.close():void, block:B:16:0x0069 */
    /* JADX WARN: Not initialized variable reg: 3, insn: 0x0071: INVOKE  (r3 I:java.io.FileOutputStream) type: VIRTUAL call: java.io.FileOutputStream.close():void, block:B:20:0x0071 */
    @Override // java.lang.Runnable
    public final void run() {
        FileOutputStream close;
        FileOutputStream close2;
        zzem zzemVar = this.zzb;
        zzrl zzrlVar = this.zza;
        File zze = zzemVar.zze();
        try {
            try {
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(zze);
                    try {
                        byte[] bArr = new byte[zzrlVar.zzX()];
                        zzto zzF = zzto.zzF(bArr);
                        zzrlVar.zzaq(zzF);
                        zzF.zzG();
                        fileOutputStream.write(bArr);
                        try {
                            fileOutputStream.close();
                        } catch (IOException unused) {
                            zzdh.zzc("error closing stream for writing resource to disk");
                        }
                    } catch (IOException e) {
                        String name = zzrlVar.getClass().getName();
                        StringBuilder sb = new StringBuilder(name.length() + 72);
                        sb.append("Serializing ");
                        sb.append(name);
                        sb.append(" to a ");
                        sb.append("byte array");
                        sb.append(" threw an IOException (should never happen).");
                        throw new RuntimeException(sb.toString(), e);
                    }
                } catch (IOException unused2) {
                    zzdh.zzc("Error writing resource to disk. Removing resource from disk.");
                    zze.delete();
                    try {
                        close2.close();
                    } catch (IOException unused3) {
                        zzdh.zzc("error closing stream for writing resource to disk");
                    }
                }
            } catch (FileNotFoundException unused4) {
                zzdh.zza("Error opening resource file for writing");
            }
        } catch (Throwable th) {
            try {
                close.close();
            } catch (IOException unused5) {
                zzdh.zzc("error closing stream for writing resource to disk");
            }
            throw th;
        }
    }
}
