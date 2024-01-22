package com.google.protobuf;

import com.google.protobuf.c;
import java.io.IOException;
/* loaded from: classes11.dex */
public interface s0<T> {
    int a(T t);

    boolean b(T t, T t2);

    void c(T t, T t2);

    void d(T t);

    boolean e(T t);

    int f(T t);

    void g(T t, Writer writer) throws IOException;

    void h(T t, q0 q0Var, ExtensionRegistryLite extensionRegistryLite) throws IOException;

    void i(T t, byte[] bArr, int i, int i2, c.b bVar) throws IOException;

    T newInstance();
}
