package com.google.android.gms.internal.mlkit_vision_common;
/* loaded from: classes8.dex */
public final class x4 extends zzmd {

    /* renamed from: a  reason: collision with root package name */
    public String f9770a;
    public boolean b;
    public int c;
    public byte d;

    public final zzmd a(String str) {
        this.f9770a = "vision-common";
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzmd
    public final zzmd zza(boolean z) {
        this.b = true;
        this.d = (byte) (1 | this.d);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzmd
    public final zzmd zzb(int i) {
        this.c = 1;
        this.d = (byte) (this.d | 2);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzmd
    public final zzme zzd() {
        String str;
        if (this.d == 3 && (str = this.f9770a) != null) {
            return new y4(str, this.b, this.c, null);
        }
        StringBuilder sb = new StringBuilder();
        if (this.f9770a == null) {
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
