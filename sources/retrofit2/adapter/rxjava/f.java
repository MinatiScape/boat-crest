package retrofit2.adapter.rxjava;

import java.lang.reflect.Type;
import javax.annotation.Nullable;
import retrofit2.CallAdapter;
import rx.Scheduler;
/* loaded from: classes13.dex */
public final class f<R> implements CallAdapter<R, Object> {

    /* renamed from: a  reason: collision with root package name */
    public final Type f15595a;
    @Nullable
    public final Scheduler b;
    public final boolean c;
    public final boolean d;
    public final boolean e;
    public final boolean f;
    public final boolean g;

    public f(Type type, @Nullable Scheduler scheduler, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        this.f15595a = type;
        this.b = scheduler;
        this.c = z;
        this.d = z2;
        this.e = z3;
        this.f = z4;
        this.g = z5;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0039  */
    @Override // retrofit2.CallAdapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object adapt(retrofit2.Call<R> r2) {
        /*
            r1 = this;
            boolean r0 = r1.c
            if (r0 == 0) goto La
            retrofit2.adapter.rxjava.c r0 = new retrofit2.adapter.rxjava.c
            r0.<init>(r2)
            goto Lf
        La:
            retrofit2.adapter.rxjava.d r0 = new retrofit2.adapter.rxjava.d
            r0.<init>(r2)
        Lf:
            boolean r2 = r1.d
            if (r2 == 0) goto L1a
            retrofit2.adapter.rxjava.e r2 = new retrofit2.adapter.rxjava.e
            r2.<init>(r0)
        L18:
            r0 = r2
            goto L24
        L1a:
            boolean r2 = r1.e
            if (r2 == 0) goto L24
            retrofit2.adapter.rxjava.a r2 = new retrofit2.adapter.rxjava.a
            r2.<init>(r0)
            goto L18
        L24:
            rx.Observable r2 = rx.Observable.create(r0)
            rx.Scheduler r0 = r1.b
            if (r0 == 0) goto L30
            rx.Observable r2 = r2.subscribeOn(r0)
        L30:
            boolean r0 = r1.f
            if (r0 == 0) goto L39
            rx.Single r2 = r2.toSingle()
            return r2
        L39:
            boolean r0 = r1.g
            if (r0 == 0) goto L41
            rx.Completable r2 = r2.toCompletable()
        L41:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: retrofit2.adapter.rxjava.f.adapt(retrofit2.Call):java.lang.Object");
    }

    @Override // retrofit2.CallAdapter
    public Type responseType() {
        return this.f15595a;
    }
}
