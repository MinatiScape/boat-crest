package com.google.crypto.tink.shaded.protobuf;
/* loaded from: classes10.dex */
public final class k0 implements x {

    /* renamed from: a  reason: collision with root package name */
    public final MessageLite f10984a;
    public final String b;
    public final Object[] c;
    public final int d;

    public k0(MessageLite messageLite, String str, Object[] objArr) {
        this.f10984a = messageLite;
        this.b = str;
        this.c = objArr;
        char charAt = str.charAt(0);
        if (charAt < 55296) {
            this.d = charAt;
            return;
        }
        int i = charAt & 8191;
        int i2 = 13;
        int i3 = 1;
        while (true) {
            int i4 = i3 + 1;
            char charAt2 = str.charAt(i3);
            if (charAt2 < 55296) {
                this.d = i | (charAt2 << i2);
                return;
            }
            i |= (charAt2 & 8191) << i2;
            i2 += 13;
            i3 = i4;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.x
    public boolean a() {
        return (this.d & 2) == 2;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.x
    public MessageLite b() {
        return this.f10984a;
    }

    public Object[] c() {
        return this.c;
    }

    public String d() {
        return this.b;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.x
    public ProtoSyntax getSyntax() {
        return (this.d & 1) == 1 ? ProtoSyntax.PROTO2 : ProtoSyntax.PROTO3;
    }
}
