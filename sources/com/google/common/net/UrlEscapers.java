package com.google.common.net;

import com.google.common.annotations.GwtCompatible;
import com.google.common.escape.Escaper;
@GwtCompatible
/* loaded from: classes10.dex */
public final class UrlEscapers {

    /* renamed from: a  reason: collision with root package name */
    public static final Escaper f10715a = new PercentEscaper("-_.*", true);
    public static final Escaper b = new PercentEscaper("-._~!$'()*,;&=@:+", false);
    public static final Escaper c = new PercentEscaper("-._~!$'()*,;&=@:+/?", false);

    public static Escaper urlFormParameterEscaper() {
        return f10715a;
    }

    public static Escaper urlFragmentEscaper() {
        return c;
    }

    public static Escaper urlPathSegmentEscaper() {
        return b;
    }
}
