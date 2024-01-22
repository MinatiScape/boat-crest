package com.google.protobuf;

import com.google.protobuf.MapEntryLite;
import java.io.IOException;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public interface q0 {
    int A() throws IOException;

    void B(List<Float> list) throws IOException;

    boolean C() throws IOException;

    int D() throws IOException;

    void E(List<ByteString> list) throws IOException;

    void F(List<Double> list) throws IOException;

    long G() throws IOException;

    String H() throws IOException;

    @Deprecated
    <T> void I(List<T> list, s0<T> s0Var, ExtensionRegistryLite extensionRegistryLite) throws IOException;

    <T> void J(List<T> list, s0<T> s0Var, ExtensionRegistryLite extensionRegistryLite) throws IOException;

    @Deprecated
    <T> T K(Class<T> cls, ExtensionRegistryLite extensionRegistryLite) throws IOException;

    <K, V> void L(Map<K, V> map, MapEntryLite.b<K, V> bVar, ExtensionRegistryLite extensionRegistryLite) throws IOException;

    <T> T M(Class<T> cls, ExtensionRegistryLite extensionRegistryLite) throws IOException;

    @Deprecated
    <T> T N(s0<T> s0Var, ExtensionRegistryLite extensionRegistryLite) throws IOException;

    <T> T O(s0<T> s0Var, ExtensionRegistryLite extensionRegistryLite) throws IOException;

    boolean P();

    String a() throws IOException;

    void b(List<String> list) throws IOException;

    long c() throws IOException;

    void d(List<Integer> list) throws IOException;

    void e(List<Long> list) throws IOException;

    boolean f() throws IOException;

    long g() throws IOException;

    int getTag();

    void h(List<Long> list) throws IOException;

    int i() throws IOException;

    void j(List<Long> list) throws IOException;

    void k(List<Integer> list) throws IOException;

    int l() throws IOException;

    int m() throws IOException;

    void n(List<Boolean> list) throws IOException;

    void o(List<String> list) throws IOException;

    ByteString p() throws IOException;

    int q() throws IOException;

    void r(List<Long> list) throws IOException;

    double readDouble() throws IOException;

    float readFloat() throws IOException;

    void s(List<Integer> list) throws IOException;

    long t() throws IOException;

    void u(List<Integer> list) throws IOException;

    int v() throws IOException;

    void w(List<Long> list) throws IOException;

    void x(List<Integer> list) throws IOException;

    void y(List<Integer> list) throws IOException;

    long z() throws IOException;
}
