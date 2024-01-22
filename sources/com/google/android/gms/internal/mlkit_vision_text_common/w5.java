package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.Objects;
/* loaded from: classes6.dex */
public final class w5 extends zznw {

    /* renamed from: a  reason: collision with root package name */
    public String f9911a;
    public boolean b;
    public int c;
    public byte d;

    public final zznw a(String str) {
        Objects.requireNonNull(str, "Null libraryName");
        this.f9911a = str;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zznw
    public final zznw zza(boolean z) {
        this.b = true;
        this.d = (byte) (1 | this.d);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zznw
    public final zznw zzb(int i) {
        this.c = 1;
        this.d = (byte) (this.d | 2);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zznw
    public final zznx zzd() {
        String str;
        if (this.d == 3 && (str = this.f9911a) != null) {
            return new x5(str, this.b, this.c, null);
        }
        StringBuilder sb = new StringBuilder();
        if (this.f9911a == null) {
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
