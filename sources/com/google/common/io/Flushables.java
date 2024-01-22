package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import java.io.Flushable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
@Beta
@GwtIncompatible
/* loaded from: classes10.dex */
public final class Flushables {

    /* renamed from: a  reason: collision with root package name */
    public static final Logger f10683a = Logger.getLogger(Flushables.class.getName());

    public static void flush(Flushable flushable, boolean z) throws IOException {
        try {
            flushable.flush();
        } catch (IOException e) {
            if (z) {
                f10683a.log(Level.WARNING, "IOException thrown while flushing Flushable.", (Throwable) e);
                return;
            }
            throw e;
        }
    }

    public static void flushQuietly(Flushable flushable) {
        try {
            flush(flushable, true);
        } catch (IOException e) {
            f10683a.log(Level.SEVERE, "IOException should not have been thrown.", (Throwable) e);
        }
    }
}
