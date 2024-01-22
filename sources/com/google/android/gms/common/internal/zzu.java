package com.google.android.gms.common.internal;

import android.net.Uri;
/* loaded from: classes6.dex */
public final class zzu {

    /* renamed from: a  reason: collision with root package name */
    public static final Uri f8347a;

    static {
        Uri parse = Uri.parse("https://plus.google.com/");
        f8347a = parse;
        parse.buildUpon().appendPath("circles").appendPath("find").build();
    }
}
