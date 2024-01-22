package com.coveiot.android.weathersdk.server;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.cli.HelpFormatter;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\b\u0016\u0018\u0000*\n\b\u0000\u0010\u0002 \u0001*\u00020\u0001*\u0006\b\u0001\u0010\u0003 \u00002\u00020\u0001B\u001b\u0012\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b\u000e\u0010\u000fJ\u0015\u0010\u0005\u001a\u00028\u00002\u0006\u0010\u0004\u001a\u00028\u0001¢\u0006\u0004\b\u0005\u0010\u0006R$\u0010\n\u001a\u0010\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00078\b@\bX\u0088\u000e¢\u0006\u0006\n\u0004\b\b\u0010\tR\u0018\u0010\r\u001a\u0004\u0018\u00018\u00008\b@\bX\u0088\u000e¢\u0006\u0006\n\u0004\b\u000b\u0010\f¨\u0006\u0010"}, d2 = {"Lcom/coveiot/android/weathersdk/server/SingletonHolder;", "", ExifInterface.GPS_DIRECTION_TRUE, ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, HelpFormatter.DEFAULT_ARG_NAME, "getInstance", "(Ljava/lang/Object;)Ljava/lang/Object;", "Lkotlin/Function1;", "a", "Lkotlin/jvm/functions/Function1;", "creator", "b", "Ljava/lang/Object;", "instance", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "weather_prodRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes8.dex */
public class SingletonHolder<T, A> {

    /* renamed from: a  reason: collision with root package name */
    public Function1<? super A, ? extends T> f6219a;
    public volatile T b;

    public SingletonHolder(@NotNull Function1<? super A, ? extends T> creator) {
        Intrinsics.checkNotNullParameter(creator, "creator");
        this.f6219a = creator;
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
                Function1<? super A, ? extends T> function1 = this.f6219a;
                Intrinsics.checkNotNull(function1);
                t = function1.invoke(a2);
                this.b = t;
                this.f6219a = null;
            }
        }
        return t;
    }
}
