package com.google.android.odml.image;
/* loaded from: classes10.dex */
public final class b extends h {

    /* renamed from: a  reason: collision with root package name */
    public Integer f10443a;
    public Integer b;

    @Override // com.google.android.odml.image.h
    public final h a(int i) {
        this.f10443a = Integer.valueOf(i);
        return this;
    }

    @Override // com.google.android.odml.image.h
    public final h b(int i) {
        this.b = Integer.valueOf(i);
        return this;
    }

    @Override // com.google.android.odml.image.h
    public final ImageProperties c() {
        Integer num = this.f10443a;
        if (num != null && this.b != null) {
            return new c(num.intValue(), this.b.intValue(), null);
        }
        StringBuilder sb = new StringBuilder();
        if (this.f10443a == null) {
            sb.append(" imageFormat");
        }
        if (this.b == null) {
            sb.append(" storageType");
        }
        String valueOf = String.valueOf(sb);
        StringBuilder sb2 = new StringBuilder(valueOf.length() + 28);
        sb2.append("Missing required properties:");
        sb2.append(valueOf);
        throw new IllegalStateException(sb2.toString());
    }
}
