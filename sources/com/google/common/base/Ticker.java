package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
@GwtCompatible
/* loaded from: classes10.dex */
public abstract class Ticker {

    /* renamed from: a  reason: collision with root package name */
    public static final Ticker f10532a = new a();

    /* loaded from: classes10.dex */
    public class a extends Ticker {
        @Override // com.google.common.base.Ticker
        public long read() {
            return l.j();
        }
    }

    public static Ticker systemTicker() {
        return f10532a;
    }

    public abstract long read();
}
