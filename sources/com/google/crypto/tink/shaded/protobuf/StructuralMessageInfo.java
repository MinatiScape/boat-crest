package com.google.crypto.tink.shaded.protobuf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes10.dex */
public final class StructuralMessageInfo implements x {

    /* renamed from: a  reason: collision with root package name */
    public final ProtoSyntax f10963a;
    public final boolean b;
    public final int[] c;
    public final FieldInfo[] d;
    public final MessageLite e;

    public StructuralMessageInfo(ProtoSyntax protoSyntax, boolean z, int[] iArr, FieldInfo[] fieldInfoArr, Object obj) {
        this.f10963a = protoSyntax;
        this.b = z;
        this.c = iArr;
        this.d = fieldInfoArr;
        this.e = (MessageLite) Internal.b(obj, "defaultInstance");
    }

    @Override // com.google.crypto.tink.shaded.protobuf.x
    public boolean a() {
        return this.b;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.x
    public MessageLite b() {
        return this.e;
    }

    public int[] c() {
        return this.c;
    }

    public FieldInfo[] d() {
        return this.d;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.x
    public ProtoSyntax getSyntax() {
        return this.f10963a;
    }

    /* loaded from: classes10.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final List<FieldInfo> f10964a;
        public ProtoSyntax b;
        public boolean c;
        public boolean d;
        public int[] e;
        public Object f;

        public Builder() {
            this.e = null;
            this.f10964a = new ArrayList();
        }

        public StructuralMessageInfo build() {
            if (!this.c) {
                if (this.b != null) {
                    this.c = true;
                    Collections.sort(this.f10964a);
                    return new StructuralMessageInfo(this.b, this.d, this.e, (FieldInfo[]) this.f10964a.toArray(new FieldInfo[0]), this.f);
                }
                throw new IllegalStateException("Must specify a proto syntax");
            }
            throw new IllegalStateException("Builder can only build once");
        }

        public void withCheckInitialized(int[] iArr) {
            this.e = iArr;
        }

        public void withDefaultInstance(Object obj) {
            this.f = obj;
        }

        public void withField(FieldInfo fieldInfo) {
            if (!this.c) {
                this.f10964a.add(fieldInfo);
                return;
            }
            throw new IllegalStateException("Builder can only build once");
        }

        public void withMessageSetWireFormat(boolean z) {
            this.d = z;
        }

        public void withSyntax(ProtoSyntax protoSyntax) {
            this.b = (ProtoSyntax) Internal.b(protoSyntax, "syntax");
        }

        public Builder(int i) {
            this.e = null;
            this.f10964a = new ArrayList(i);
        }
    }
}
