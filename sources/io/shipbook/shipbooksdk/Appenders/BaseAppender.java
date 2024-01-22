package io.shipbook.shipbooksdk.Appenders;

import com.clevertap.android.sdk.Constants;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import io.shipbook.shipbooksdk.Models.BaseLog;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\b \u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u001a\u0010\u0005\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002j\u0004\u0018\u0001`\u0004¢\u0006\u0004\b\u0014\u0010\u0015J$\u0010\u0007\u001a\u00020\u00062\u001a\u0010\u0005\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002j\u0004\u0018\u0001`\u0004H&J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH&R\u0019\u0010\u000f\u001a\u00020\u00038\u0006@\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR-\u0010\u0005\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002j\u0004\u0018\u0001`\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lio/shipbook/shipbooksdk/Appenders/BaseAppender;", "", "", "", "Lio/shipbook/shipbooksdk/Appenders/Config;", Constants.KEY_CONFIG, "", "update", "Lio/shipbook/shipbooksdk/Models/BaseLog;", "log", "push", "a", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", AppMeasurementSdk.ConditionalUserProperty.NAME, "b", "Ljava/util/Map;", "getConfig", "()Ljava/util/Map;", "<init>", "(Ljava/lang/String;Ljava/util/Map;)V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public abstract class BaseAppender {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f14010a;
    @Nullable
    public final Map<String, String> b;

    public BaseAppender(@NotNull String name, @Nullable Map<String, String> map) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.f14010a = name;
        this.b = map;
    }

    @Nullable
    public final Map<String, String> getConfig() {
        return this.b;
    }

    @NotNull
    public final String getName() {
        return this.f14010a;
    }

    public abstract void push(@NotNull BaseLog baseLog);

    public abstract void update(@Nullable Map<String, String> map);
}
