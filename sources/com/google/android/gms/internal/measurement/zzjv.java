package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzjv;
import com.google.android.gms.internal.measurement.zzjz;
import java.io.IOException;
/* loaded from: classes8.dex */
public class zzjv<MessageType extends zzjz<MessageType, BuilderType>, BuilderType extends zzjv<MessageType, BuilderType>> extends zzig<MessageType, BuilderType> {
    public final MessageType h;
    public MessageType zza;
    public boolean zzb = false;

    public zzjv(MessageType messagetype) {
        this.h = messagetype;
        this.zza = (MessageType) messagetype.zzl(4, null, null);
    }

    public static final void a(MessageType messagetype, MessageType messagetype2) {
        u3.a().b(messagetype.getClass()).zzg(messagetype, messagetype2);
    }

    public final MessageType zzaA() {
        MessageType zzaC = zzaC();
        boolean z = true;
        byte byteValue = ((Byte) zzaC.zzl(1, null, null)).byteValue();
        if (byteValue != 1) {
            if (byteValue == 0) {
                z = false;
            } else {
                boolean b = u3.a().b(zzaC.getClass()).b(zzaC);
                zzaC.zzl(2, true != b ? null : zzaC, null);
                z = b;
            }
        }
        if (z) {
            return zzaC;
        }
        throw new zzmh(zzaC);
    }

    @Override // com.google.android.gms.internal.measurement.zzlf
    /* renamed from: zzaB */
    public MessageType zzaC() {
        if (this.zzb) {
            return this.zza;
        }
        MessageType messagetype = this.zza;
        u3.a().b(messagetype.getClass()).zzf(messagetype);
        this.zzb = true;
        return this.zza;
    }

    public void zzaE() {
        MessageType messagetype = (MessageType) this.zza.zzl(4, null, null);
        a(messagetype, this.zza);
        this.zza = messagetype;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.measurement.zzig
    public final /* bridge */ /* synthetic */ zzig zzar(zzih zzihVar) {
        zzay((zzjz) zzihVar);
        return this;
    }

    @Override // com.google.android.gms.internal.measurement.zzig
    public final /* bridge */ /* synthetic */ zzig zzas(byte[] bArr, int i, int i2) throws zzkj {
        zzaz(bArr, 0, i2, zzjl.zza());
        return this;
    }

    @Override // com.google.android.gms.internal.measurement.zzig
    public final /* bridge */ /* synthetic */ zzig zzat(byte[] bArr, int i, int i2, zzjl zzjlVar) throws zzkj {
        zzaz(bArr, 0, i2, zzjlVar);
        return this;
    }

    @Override // com.google.android.gms.internal.measurement.zzig
    /* renamed from: zzax */
    public final BuilderType zzaq() {
        BuilderType buildertype = (BuilderType) this.h.zzl(5, null, null);
        buildertype.zzay(zzaC());
        return buildertype;
    }

    public final BuilderType zzay(MessageType messagetype) {
        if (this.zzb) {
            zzaE();
            this.zzb = false;
        }
        a(this.zza, messagetype);
        return this;
    }

    public final BuilderType zzaz(byte[] bArr, int i, int i2, zzjl zzjlVar) throws zzkj {
        if (this.zzb) {
            zzaE();
            this.zzb = false;
        }
        try {
            u3.a().b(this.zza.getClass()).a(this.zza, bArr, 0, i2, new g2(zzjlVar));
            return this;
        } catch (zzkj e) {
            throw e;
        } catch (IOException e2) {
            throw new RuntimeException("Reading from byte array should not throw IOException.", e2);
        } catch (IndexOutOfBoundsException unused) {
            throw zzkj.zzf();
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlh
    public final /* bridge */ /* synthetic */ zzlg zzbL() {
        return this.h;
    }
}
