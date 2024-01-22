package com.google.android.gms.internal.mlkit_vision_barcode;
/* loaded from: classes9.dex */
public final class p8 extends zzto {

    /* renamed from: a  reason: collision with root package name */
    public String f9477a;
    public boolean b;
    public int c;
    public byte d;

    public final zzto a(String str) {
        this.f9477a = str;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzto
    public final zzto zza(boolean z) {
        this.b = true;
        this.d = (byte) (1 | this.d);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzto
    public final zzto zzb(int i) {
        this.c = 1;
        this.d = (byte) (this.d | 2);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzto
    public final zztp zzd() {
        String str;
        if (this.d == 3 && (str = this.f9477a) != null) {
            return new q8(str, this.b, this.c, null);
        }
        StringBuilder sb = new StringBuilder();
        if (this.f9477a == null) {
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
