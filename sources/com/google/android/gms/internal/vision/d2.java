package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzgs;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
public final class d2 extends b2<zzgs.a> {
    @Override // com.google.android.gms.internal.vision.b2
    public final int a(Map.Entry<?, ?> entry) {
        return ((zzgs.a) entry.getKey()).i;
    }

    @Override // com.google.android.gms.internal.vision.b2
    public final Object b(zzgd zzgdVar, zzic zzicVar, int i) {
        return zzgdVar.zza(zzicVar, i);
    }

    @Override // com.google.android.gms.internal.vision.b2
    public final <UT, UB> UB c(p3 p3Var, Object obj, zzgd zzgdVar, e2<zzgs.a> e2Var, UB ub, g4<UT, UB> g4Var) throws IOException {
        Object f;
        ArrayList arrayList;
        zzgs.zzg zzgVar = (zzgs.zzg) obj;
        zzgs.a aVar = zzgVar.d;
        int i = aVar.i;
        if (aVar.k && aVar.l) {
            switch (c2.f9966a[aVar.j.ordinal()]) {
                case 1:
                    arrayList = new ArrayList();
                    p3Var.zza(arrayList);
                    break;
                case 2:
                    arrayList = new ArrayList();
                    p3Var.zzb(arrayList);
                    break;
                case 3:
                    arrayList = new ArrayList();
                    p3Var.t(arrayList);
                    break;
                case 4:
                    arrayList = new ArrayList();
                    p3Var.o(arrayList);
                    break;
                case 5:
                    arrayList = new ArrayList();
                    p3Var.zze(arrayList);
                    break;
                case 6:
                    arrayList = new ArrayList();
                    p3Var.h(arrayList);
                    break;
                case 7:
                    arrayList = new ArrayList();
                    p3Var.f(arrayList);
                    break;
                case 8:
                    arrayList = new ArrayList();
                    p3Var.e(arrayList);
                    break;
                case 9:
                    arrayList = new ArrayList();
                    p3Var.G(arrayList);
                    break;
                case 10:
                    arrayList = new ArrayList();
                    p3Var.K(arrayList);
                    break;
                case 11:
                    arrayList = new ArrayList();
                    p3Var.J(arrayList);
                    break;
                case 12:
                    arrayList = new ArrayList();
                    p3Var.x(arrayList);
                    break;
                case 13:
                    arrayList = new ArrayList();
                    p3Var.v(arrayList);
                    break;
                case 14:
                    arrayList = new ArrayList();
                    p3Var.E(arrayList);
                    ub = (UB) q3.b(i, arrayList, zzgVar.d.h, ub, g4Var);
                    break;
                default:
                    String valueOf = String.valueOf(zzgVar.d.j);
                    StringBuilder sb = new StringBuilder(valueOf.length() + 23);
                    sb.append("Type cannot be packed: ");
                    sb.append(valueOf);
                    throw new IllegalStateException(sb.toString());
            }
            e2Var.h(zzgVar.d, arrayList);
        } else {
            Object obj2 = null;
            zzka zzkaVar = aVar.j;
            if (zzkaVar == zzka.zzacf) {
                int u = p3Var.u();
                if (zzgVar.d.h.zzh(u) == null) {
                    return (UB) q3.a(i, u, ub, g4Var);
                }
                obj2 = Integer.valueOf(u);
            } else {
                switch (c2.f9966a[zzkaVar.ordinal()]) {
                    case 1:
                        obj2 = Double.valueOf(p3Var.readDouble());
                        break;
                    case 2:
                        obj2 = Float.valueOf(p3Var.readFloat());
                        break;
                    case 3:
                        obj2 = Long.valueOf(p3Var.B());
                        break;
                    case 4:
                        obj2 = Long.valueOf(p3Var.z());
                        break;
                    case 5:
                        obj2 = Integer.valueOf(p3Var.u());
                        break;
                    case 6:
                        obj2 = Long.valueOf(p3Var.y());
                        break;
                    case 7:
                        obj2 = Integer.valueOf(p3Var.i());
                        break;
                    case 8:
                        obj2 = Boolean.valueOf(p3Var.k());
                        break;
                    case 9:
                        obj2 = Integer.valueOf(p3Var.r());
                        break;
                    case 10:
                        obj2 = Integer.valueOf(p3Var.n());
                        break;
                    case 11:
                        obj2 = Long.valueOf(p3Var.q());
                        break;
                    case 12:
                        obj2 = Integer.valueOf(p3Var.A());
                        break;
                    case 13:
                        obj2 = Long.valueOf(p3Var.C());
                        break;
                    case 14:
                        throw new IllegalStateException("Shouldn't reach here.");
                    case 15:
                        obj2 = p3Var.g();
                        break;
                    case 16:
                        obj2 = p3Var.a();
                        break;
                    case 17:
                        obj2 = p3Var.D(zzgVar.c.getClass(), zzgdVar);
                        break;
                    case 18:
                        obj2 = p3Var.w(zzgVar.c.getClass(), zzgdVar);
                        break;
                }
            }
            zzgs.a aVar2 = zzgVar.d;
            if (aVar2.k) {
                e2Var.k(aVar2, obj2);
            } else {
                int i2 = c2.f9966a[aVar2.j.ordinal()];
                if ((i2 == 17 || i2 == 18) && (f = e2Var.f(zzgVar.d)) != null) {
                    obj2 = zzgt.d(f, obj2);
                }
                e2Var.h(zzgVar.d, obj2);
            }
        }
        return ub;
    }

    @Override // com.google.android.gms.internal.vision.b2
    public final void d(zzfh zzfhVar, Object obj, zzgd zzgdVar, e2<zzgs.a> e2Var) throws IOException {
        byte[] bArr;
        zzgs.zzg zzgVar = (zzgs.zzg) obj;
        zzic zzgb = zzgVar.c.zzgj().zzgb();
        int size = zzfhVar.size();
        if (size == 0) {
            bArr = zzgt.zzxi;
        } else {
            byte[] bArr2 = new byte[size];
            zzfhVar.zza(bArr2, 0, 0, size);
            bArr = bArr2;
        }
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        if (wrap.hasArray()) {
            k1 k1Var = new k1(wrap, true);
            k3.b().c(zzgb).i(zzgb, k1Var, zzgdVar);
            e2Var.h(zzgVar.d, zzgb);
            if (k1Var.m() != Integer.MAX_VALUE) {
                throw zzhc.zzgq();
            }
            return;
        }
        throw new IllegalArgumentException("Direct buffers not yet supported");
    }

    @Override // com.google.android.gms.internal.vision.b2
    public final void e(p3 p3Var, Object obj, zzgd zzgdVar, e2<zzgs.a> e2Var) throws IOException {
        zzgs.zzg zzgVar = (zzgs.zzg) obj;
        e2Var.h(zzgVar.d, p3Var.w(zzgVar.c.getClass(), zzgdVar));
    }

    @Override // com.google.android.gms.internal.vision.b2
    public final void f(x4 x4Var, Map.Entry<?, ?> entry) throws IOException {
        zzgs.a aVar = (zzgs.a) entry.getKey();
        if (aVar.k) {
            switch (c2.f9966a[aVar.j.ordinal()]) {
                case 1:
                    q3.f(aVar.i, (List) entry.getValue(), x4Var, aVar.l);
                    return;
                case 2:
                    q3.l(aVar.i, (List) entry.getValue(), x4Var, aVar.l);
                    return;
                case 3:
                    q3.p(aVar.i, (List) entry.getValue(), x4Var, aVar.l);
                    return;
                case 4:
                    q3.s(aVar.i, (List) entry.getValue(), x4Var, aVar.l);
                    return;
                case 5:
                    q3.y(aVar.i, (List) entry.getValue(), x4Var, aVar.l);
                    return;
                case 6:
                    q3.v(aVar.i, (List) entry.getValue(), x4Var, aVar.l);
                    return;
                case 7:
                    q3.G(aVar.i, (List) entry.getValue(), x4Var, aVar.l);
                    return;
                case 8:
                    q3.K(aVar.i, (List) entry.getValue(), x4Var, aVar.l);
                    return;
                case 9:
                    q3.E(aVar.i, (List) entry.getValue(), x4Var, aVar.l);
                    return;
                case 10:
                    q3.H(aVar.i, (List) entry.getValue(), x4Var, aVar.l);
                    return;
                case 11:
                    q3.w(aVar.i, (List) entry.getValue(), x4Var, aVar.l);
                    return;
                case 12:
                    q3.F(aVar.i, (List) entry.getValue(), x4Var, aVar.l);
                    return;
                case 13:
                    q3.t(aVar.i, (List) entry.getValue(), x4Var, aVar.l);
                    return;
                case 14:
                    q3.y(aVar.i, (List) entry.getValue(), x4Var, aVar.l);
                    return;
                case 15:
                    q3.j(aVar.i, (List) entry.getValue(), x4Var);
                    return;
                case 16:
                    q3.d(aVar.i, (List) entry.getValue(), x4Var);
                    return;
                case 17:
                    List list = (List) entry.getValue();
                    if (list == null || list.isEmpty()) {
                        return;
                    }
                    q3.k(aVar.i, (List) entry.getValue(), x4Var, k3.b().a(list.get(0).getClass()));
                    return;
                case 18:
                    List list2 = (List) entry.getValue();
                    if (list2 == null || list2.isEmpty()) {
                        return;
                    }
                    q3.e(aVar.i, (List) entry.getValue(), x4Var, k3.b().a(list2.get(0).getClass()));
                    return;
                default:
                    return;
            }
        }
        switch (c2.f9966a[aVar.j.ordinal()]) {
            case 1:
                x4Var.zza(aVar.i, ((Double) entry.getValue()).doubleValue());
                return;
            case 2:
                x4Var.zza(aVar.i, ((Float) entry.getValue()).floatValue());
                return;
            case 3:
                x4Var.b(aVar.i, ((Long) entry.getValue()).longValue());
                return;
            case 4:
                x4Var.zza(aVar.i, ((Long) entry.getValue()).longValue());
                return;
            case 5:
                x4Var.e(aVar.i, ((Integer) entry.getValue()).intValue());
                return;
            case 6:
                x4Var.zzc(aVar.i, ((Long) entry.getValue()).longValue());
                return;
            case 7:
                x4Var.d(aVar.i, ((Integer) entry.getValue()).intValue());
                return;
            case 8:
                x4Var.zza(aVar.i, ((Boolean) entry.getValue()).booleanValue());
                return;
            case 9:
                x4Var.c(aVar.i, ((Integer) entry.getValue()).intValue());
                return;
            case 10:
                x4Var.g(aVar.i, ((Integer) entry.getValue()).intValue());
                return;
            case 11:
                x4Var.a(aVar.i, ((Long) entry.getValue()).longValue());
                return;
            case 12:
                x4Var.zzi(aVar.i, ((Integer) entry.getValue()).intValue());
                return;
            case 13:
                x4Var.zzb(aVar.i, ((Long) entry.getValue()).longValue());
                return;
            case 14:
                x4Var.e(aVar.i, ((Integer) entry.getValue()).intValue());
                return;
            case 15:
                x4Var.o(aVar.i, (zzfh) entry.getValue());
                return;
            case 16:
                x4Var.zza(aVar.i, (String) entry.getValue());
                return;
            case 17:
                x4Var.n(aVar.i, entry.getValue(), k3.b().a(entry.getValue().getClass()));
                return;
            case 18:
                x4Var.f(aVar.i, entry.getValue(), k3.b().a(entry.getValue().getClass()));
                return;
            default:
                return;
        }
    }

    @Override // com.google.android.gms.internal.vision.b2
    public final boolean g(zzic zzicVar) {
        return zzicVar instanceof zzgs.zze;
    }

    @Override // com.google.android.gms.internal.vision.b2
    public final e2<zzgs.a> h(Object obj) {
        return ((zzgs.zze) obj).zzwq;
    }

    @Override // com.google.android.gms.internal.vision.b2
    public final e2<zzgs.a> i(Object obj) {
        return ((zzgs.zze) obj).i();
    }

    @Override // com.google.android.gms.internal.vision.b2
    public final void j(Object obj) {
        h(obj).p();
    }
}
