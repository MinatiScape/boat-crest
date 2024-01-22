package com.google.android.gms.internal.firebase_ml;

import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes7.dex */
public final class w1 {

    /* loaded from: classes7.dex */
    public static final class a {
        public a() {
        }
    }

    static {
        Logger.getLogger(w1.class.getName());
        new a();
    }

    public static boolean a(@NullableDecl String str) {
        return str == null || str.isEmpty();
    }

    public static String b(@NullableDecl String str) {
        return str == null ? "" : str;
    }
}
