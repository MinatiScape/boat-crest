package com.google.android.gms.internal.mlkit_code_scanner;
/* loaded from: classes8.dex */
public final class t5 extends zzns {

    /* renamed from: a  reason: collision with root package name */
    public String f9100a;
    public boolean b;
    public int c;
    public byte d;

    public final zzns a(String str) {
        this.f9100a = "play-services-code-scanner";
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zzns
    public final zzns zza(boolean z) {
        this.b = true;
        this.d = (byte) (1 | this.d);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zzns
    public final zzns zzb(int i) {
        this.c = 1;
        this.d = (byte) (this.d | 2);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zzns
    public final zznt zzd() {
        String str;
        if (this.d == 3 && (str = this.f9100a) != null) {
            return new u5(str, this.b, this.c, null);
        }
        StringBuilder sb = new StringBuilder();
        if (this.f9100a == null) {
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
