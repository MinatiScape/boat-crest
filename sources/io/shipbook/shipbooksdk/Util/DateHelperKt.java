package io.shipbook.shipbooksdk.Util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\f\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0000¨\u0006\u0003"}, d2 = {"Ljava/util/Date;", "", "toStandardString", "shipbooksdk_release"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final class DateHelperKt {

    /* renamed from: a  reason: collision with root package name */
    public static final String f14044a = DateHelper.class.getSimpleName();
    public static final SimpleDateFormat b = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US);

    @NotNull
    public static final String toStandardString(@NotNull Date receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        String format = b.format(receiver$0);
        Intrinsics.checkExpressionValueIsNotNull(format, "sDateFormat.format(this)");
        return format;
    }
}
