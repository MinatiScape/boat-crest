package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Arrays;
import java.util.Objects;
import javax.annotation.CheckForNull;
/* loaded from: classes9.dex */
public final class zzaw {

    /* renamed from: a  reason: collision with root package name */
    public final String f9546a;
    public final d b;
    public d c;

    public /* synthetic */ zzaw(String str, zzav zzavVar) {
        d dVar = new d(null);
        this.b = dVar;
        this.c = dVar;
        Objects.requireNonNull(str);
        this.f9546a = str;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append(this.f9546a);
        sb.append('{');
        d dVar = this.b.b;
        String str = "";
        while (dVar != null) {
            Object obj = dVar.f9381a;
            sb.append(str);
            if (obj != null && obj.getClass().isArray()) {
                String deepToString = Arrays.deepToString(new Object[]{obj});
                sb.append((CharSequence) deepToString, 1, deepToString.length() - 1);
            } else {
                sb.append(obj);
            }
            dVar = dVar.b;
            str = ", ";
        }
        sb.append('}');
        return sb.toString();
    }

    public final zzaw zza(@CheckForNull Object obj) {
        d dVar = new d(null);
        this.c.b = dVar;
        this.c = dVar;
        dVar.f9381a = obj;
        return this;
    }
}
