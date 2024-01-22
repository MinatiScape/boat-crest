package com.coveiot.android.activitymodes;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public class SingletonHolder<T, A> {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Function1<? super A, ? extends T> f2704a;
    @Nullable
    public volatile T b;

    public SingletonHolder(@NotNull Function1<? super A, ? extends T> creator) {
        Intrinsics.checkNotNullParameter(creator, "creator");
        this.f2704a = creator;
    }

    public final void clearInstance() {
        this.b = null;
    }

    @NotNull
    public final T getInstance(A a2) {
        T t;
        T t2 = this.b;
        if (t2 != null) {
            return t2;
        }
        synchronized (this) {
            t = this.b;
            if (t == null) {
                Function1<? super A, ? extends T> function1 = this.f2704a;
                Intrinsics.checkNotNull(function1);
                t = function1.invoke(a2);
                this.b = t;
                this.f2704a = null;
            }
        }
        return t;
    }
}