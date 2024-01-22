package io.shipbook.shipbooksdk.Util;

import io.shipbook.shipbooksdk.InnerLog;
import java.text.SimpleDateFormat;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\b"}, d2 = {"Lio/shipbook/shipbooksdk/Util/DateHelper;", "", "", "string", "Ljava/util/Date;", "createDateStandard", "<init>", "()V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final class DateHelper {
    public static final DateHelper INSTANCE = new DateHelper();

    @Nullable
    public final Date createDateStandard(@NotNull String string) {
        String TAG;
        SimpleDateFormat simpleDateFormat;
        Intrinsics.checkParameterIsNotNull(string, "string");
        try {
            simpleDateFormat = DateHelperKt.b;
            return simpleDateFormat.parse(string);
        } catch (Exception e) {
            InnerLog innerLog = InnerLog.INSTANCE;
            TAG = DateHelperKt.f14044a;
            Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
            innerLog.e(TAG, "error in the parse", e);
            return null;
        }
    }
}
