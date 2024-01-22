package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
@Beta
@GwtCompatible
/* loaded from: classes10.dex */
public final class Runnables {

    /* renamed from: a  reason: collision with root package name */
    public static final Runnable f10796a = new a();

    /* loaded from: classes10.dex */
    public class a implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
        }
    }

    public static Runnable doNothing() {
        return f10796a;
    }
}
