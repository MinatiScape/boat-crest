package com.google.protobuf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes11.dex */
public final class StructuralMessageInfo implements a0 {

    /* renamed from: a  reason: collision with root package name */
    public final ProtoSyntax f11708a;
    public final boolean b;
    public final int[] c;
    public final FieldInfo[] d;
    public final MessageLite e;

    public StructuralMessageInfo(ProtoSyntax protoSyntax, boolean z, int[] iArr, FieldInfo[] fieldInfoArr, Object obj) {
        this.f11708a = protoSyntax;
        this.b = z;
        this.c = iArr;
        this.d = fieldInfoArr;
        this.e = (MessageLite) Internal.checkNotNull(obj, "defaultInstance");
    }

    public static Builder e(int i) {
        return new Builder(i);
    }

    @Override // com.google.protobuf.a0
    public boolean a() {
        return this.b;
    }

    @Override // com.google.protobuf.a0
    public MessageLite b() {
        return this.e;
    }

    public int[] c() {
        return this.c;
    }

    public FieldInfo[] d() {
        return this.d;
    }

    @Override // com.google.protobuf.a0
    public ProtoSyntax getSyntax() {
        return this.f11708a;
    }

    /* loaded from: classes11.dex */
    public static final class Builder {
        private int[] checkInitialized;
        private Object defaultInstance;
        private final List<FieldInfo> fields;
        private boolean messageSetWireFormat;
        private ProtoSyntax syntax;
        private boolean wasBuilt;

        public Builder() {
            this.checkInitialized = null;
            this.fields = new ArrayList();
        }

        public StructuralMessageInfo build() {
            if (!this.wasBuilt) {
                if (this.syntax != null) {
                    this.wasBuilt = true;
                    Collections.sort(this.fields);
                    return new StructuralMessageInfo(this.syntax, this.messageSetWireFormat, this.checkInitialized, (FieldInfo[]) this.fields.toArray(new FieldInfo[0]), this.defaultInstance);
                }
                throw new IllegalStateException("Must specify a proto syntax");
            }
            throw new IllegalStateException("Builder can only build once");
        }

        public void withCheckInitialized(int[] iArr) {
            this.checkInitialized = iArr;
        }

        public void withDefaultInstance(Object obj) {
            this.defaultInstance = obj;
        }

        public void withField(FieldInfo fieldInfo) {
            if (!this.wasBuilt) {
                this.fields.add(fieldInfo);
                return;
            }
            throw new IllegalStateException("Builder can only build once");
        }

        public void withMessageSetWireFormat(boolean z) {
            this.messageSetWireFormat = z;
        }

        public void withSyntax(ProtoSyntax protoSyntax) {
            this.syntax = (ProtoSyntax) Internal.checkNotNull(protoSyntax, "syntax");
        }

        public Builder(int i) {
            this.checkInitialized = null;
            this.fields = new ArrayList(i);
        }
    }
}
