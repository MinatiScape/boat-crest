package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.FieldSet;
import com.google.crypto.tink.shaded.protobuf.FieldSet.FieldDescriptorLite;
import java.io.IOException;
import java.util.Map;
/* loaded from: classes10.dex */
public abstract class k<T extends FieldSet.FieldDescriptorLite<T>> {
    public abstract int a(Map.Entry<?, ?> entry);

    public abstract Object b(ExtensionRegistryLite extensionRegistryLite, MessageLite messageLite, int i);

    public abstract FieldSet<T> c(Object obj);

    public abstract FieldSet<T> d(Object obj);

    public abstract boolean e(MessageLite messageLite);

    public abstract void f(Object obj);

    public abstract <UT, UB> UB g(l0 l0Var, Object obj, ExtensionRegistryLite extensionRegistryLite, FieldSet<T> fieldSet, UB ub, s0<UT, UB> s0Var) throws IOException;

    public abstract void h(l0 l0Var, Object obj, ExtensionRegistryLite extensionRegistryLite, FieldSet<T> fieldSet) throws IOException;

    public abstract void i(ByteString byteString, Object obj, ExtensionRegistryLite extensionRegistryLite, FieldSet<T> fieldSet) throws IOException;

    public abstract void j(Writer writer, Map.Entry<?, ?> entry) throws IOException;
}
