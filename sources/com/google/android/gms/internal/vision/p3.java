package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
public interface p3 {
    int A() throws IOException;

    long B() throws IOException;

    long C() throws IOException;

    @Deprecated
    <T> T D(Class<T> cls, zzgd zzgdVar) throws IOException;

    void E(List<Integer> list) throws IOException;

    <T> void F(List<T> list, o3<T> o3Var, zzgd zzgdVar) throws IOException;

    void G(List<Integer> list) throws IOException;

    <T> T H(o3<T> o3Var, zzgd zzgdVar) throws IOException;

    @Deprecated
    <T> void I(List<T> list, o3<T> o3Var, zzgd zzgdVar) throws IOException;

    void J(List<Long> list) throws IOException;

    void K(List<Integer> list) throws IOException;

    @Deprecated
    <T> T L(o3<T> o3Var, zzgd zzgdVar) throws IOException;

    String a() throws IOException;

    void b(List<String> list) throws IOException;

    <K, V> void c(Map<K, V> map, y2<K, V> y2Var, zzgd zzgdVar) throws IOException;

    String d() throws IOException;

    void e(List<Boolean> list) throws IOException;

    void f(List<Integer> list) throws IOException;

    zzfh g() throws IOException;

    int getTag();

    void h(List<Long> list) throws IOException;

    int i() throws IOException;

    void j(List<zzfh> list) throws IOException;

    boolean k() throws IOException;

    void l(List<String> list) throws IOException;

    int m() throws IOException;

    int n() throws IOException;

    void o(List<Long> list) throws IOException;

    boolean p() throws IOException;

    long q() throws IOException;

    int r() throws IOException;

    double readDouble() throws IOException;

    float readFloat() throws IOException;

    int s() throws IOException;

    void t(List<Long> list) throws IOException;

    int u() throws IOException;

    void v(List<Long> list) throws IOException;

    <T> T w(Class<T> cls, zzgd zzgdVar) throws IOException;

    void x(List<Integer> list) throws IOException;

    long y() throws IOException;

    long z() throws IOException;

    void zza(List<Double> list) throws IOException;

    void zzb(List<Float> list) throws IOException;

    void zze(List<Integer> list) throws IOException;
}
