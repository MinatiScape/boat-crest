package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.c;
import java.io.IOException;
/* loaded from: classes10.dex */
public interface n0<T> {
    int a(T t);

    boolean b(T t, T t2);

    void c(T t, T t2);

    void d(T t);

    boolean e(T t);

    int f(T t);

    void g(T t, byte[] bArr, int i, int i2, c.b bVar) throws IOException;

    void h(T t, l0 l0Var, ExtensionRegistryLite extensionRegistryLite) throws IOException;

    void i(T t, Writer writer) throws IOException;

    T newInstance();
}
