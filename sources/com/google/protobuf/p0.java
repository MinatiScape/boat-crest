package com.google.protobuf;
/* loaded from: classes11.dex */
public final class p0 implements a0 {

    /* renamed from: a  reason: collision with root package name */
    public final MessageLite f11749a;
    public final String b;
    public final Object[] c;
    public final int d;

    public p0(MessageLite messageLite, String str, Object[] objArr) {
        String str2;
        Throwable e;
        char charAt;
        this.f11749a = messageLite;
        this.b = str;
        this.c = objArr;
        int i = 1;
        try {
            charAt = str.charAt(0);
        } catch (StringIndexOutOfBoundsException unused) {
            char[] charArray = str.toCharArray();
            String str3 = new String(charArray);
            try {
                try {
                    charAt = str3.charAt(0);
                    str = str3;
                } catch (StringIndexOutOfBoundsException unused2) {
                    char[] cArr = new char[str3.length()];
                    str3.getChars(0, str3.length(), cArr, 0);
                    str2 = new String(cArr);
                    try {
                        charAt = str2.charAt(0);
                        str = str2;
                    } catch (ArrayIndexOutOfBoundsException e2) {
                        e = e2;
                        throw new IllegalStateException(String.format("Failed parsing '%s' with charArray.length of %d", str2, Integer.valueOf(charArray.length)), e);
                    } catch (StringIndexOutOfBoundsException e3) {
                        e = e3;
                        throw new IllegalStateException(String.format("Failed parsing '%s' with charArray.length of %d", str2, Integer.valueOf(charArray.length)), e);
                    }
                }
            } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e4) {
                str2 = str3;
                e = e4;
                throw new IllegalStateException(String.format("Failed parsing '%s' with charArray.length of %d", str2, Integer.valueOf(charArray.length)), e);
            }
        }
        if (charAt < 55296) {
            this.d = charAt;
            return;
        }
        int i2 = charAt & 8191;
        int i3 = 13;
        while (true) {
            int i4 = i + 1;
            char charAt2 = str.charAt(i);
            if (charAt2 < 55296) {
                this.d = (charAt2 << i3) | i2;
                return;
            }
            i2 |= (charAt2 & 8191) << i3;
            i3 += 13;
            i = i4;
        }
    }

    @Override // com.google.protobuf.a0
    public boolean a() {
        return (this.d & 2) == 2;
    }

    @Override // com.google.protobuf.a0
    public MessageLite b() {
        return this.f11749a;
    }

    public Object[] c() {
        return this.c;
    }

    public String d() {
        return this.b;
    }

    @Override // com.google.protobuf.a0
    public ProtoSyntax getSyntax() {
        return (this.d & 1) == 1 ? ProtoSyntax.PROTO2 : ProtoSyntax.PROTO3;
    }
}
