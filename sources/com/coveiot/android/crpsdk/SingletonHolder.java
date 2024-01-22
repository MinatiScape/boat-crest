package com.coveiot.android.crpsdk;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.cli.HelpFormatter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u0001*\u0006\b\u0001\u0010\u0002 \u00002\u00020\u0003B\u001b\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u0005\u001a\u00028\u00002\u0006\u0010\u0004\u001a\u00028\u0001¢\u0006\u0004\b\u0005\u0010\u0006R\"\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00000\u00078\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\tR\u0018\u0010\n\u001a\u0004\u0018\u00018\u00008\b@\bX\u0088\u000e¢\u0006\u0006\n\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcom/coveiot/android/crpsdk/SingletonHolder;", ExifInterface.GPS_DIRECTION_TRUE, ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "", HelpFormatter.DEFAULT_ARG_NAME, "getInstance", "(Ljava/lang/Object;)Ljava/lang/Object;", "Lkotlin/Function1;", "constructor", "Lkotlin/jvm/functions/Function1;", "instance", "Ljava/lang/Object;", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "crpSdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public class SingletonHolder<T, A> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Function1<A, T> f4111a;
    @Nullable
    public volatile T b;

    /* JADX WARN: Multi-variable type inference failed */
    public SingletonHolder(@NotNull Function1<? super A, ? extends T> constructor) {
        Intrinsics.checkNotNullParameter(constructor, "constructor");
        this.f4111a = constructor;
    }

    public final T getInstance(A a2) {
        T t;
        if (this.b != null) {
            T t2 = this.b;
            Intrinsics.checkNotNull(t2);
            return t2;
        }
        synchronized (this) {
            if (this.b == null) {
                this.b = this.f4111a.invoke(a2);
            }
            t = this.b;
            Intrinsics.checkNotNull(t);
        }
        return t;
    }
}
