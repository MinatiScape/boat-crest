package io.shipbook.shipbooksdk.Models;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b \u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lio/shipbook/shipbooksdk/Models/BaseEvent;", "Lio/shipbook/shipbooksdk/Models/BaseLog;", "", "type", "<init>", "(Ljava/lang/String;)V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public abstract class BaseEvent extends BaseLog {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseEvent(@NotNull String type) {
        super(type, 0, null, null, 14, null);
        Intrinsics.checkParameterIsNotNull(type, "type");
    }
}
