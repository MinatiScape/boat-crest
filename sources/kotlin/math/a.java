package kotlin.math;

import kotlin.jvm.JvmField;
/* loaded from: classes12.dex */
public final class a {
    @JvmField

    /* renamed from: a  reason: collision with root package name */
    public static final double f14084a;
    @JvmField
    public static final double b;
    @JvmField
    public static final double c;
    @JvmField
    public static final double d;
    @JvmField
    public static final double e;
    @JvmField
    public static final double f;

    static {
        new a();
        f14084a = Math.log(2.0d);
        double ulp = Math.ulp(1.0d);
        b = ulp;
        double sqrt = Math.sqrt(ulp);
        c = sqrt;
        double sqrt2 = Math.sqrt(sqrt);
        d = sqrt2;
        double d2 = 1;
        e = d2 / sqrt;
        f = d2 / sqrt2;
    }
}
