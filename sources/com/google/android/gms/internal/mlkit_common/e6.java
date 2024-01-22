package com.google.android.gms.internal.mlkit_common;
/* loaded from: classes8.dex */
public final class e6 extends zzps {

    /* renamed from: a  reason: collision with root package name */
    public String f9189a;
    public boolean b;
    public int c;
    public byte d;

    public final zzps a(String str) {
        this.f9189a = "common";
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzps
    public final zzps zza(boolean z) {
        this.b = true;
        this.d = (byte) (1 | this.d);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzps
    public final zzps zzb(int i) {
        this.c = 1;
        this.d = (byte) (this.d | 2);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzps
    public final zzpt zzd() {
        String str;
        if (this.d == 3 && (str = this.f9189a) != null) {
            return new f6(str, this.b, this.c, null);
        }
        StringBuilder sb = new StringBuilder();
        if (this.f9189a == null) {
            sb.append(" libraryName");
        }
        if ((this.d & 1) == 0) {
            sb.append(" enableFirelog");
        }
        if ((this.d & 2) == 0) {
            sb.append(" firelogEventType");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }
}
