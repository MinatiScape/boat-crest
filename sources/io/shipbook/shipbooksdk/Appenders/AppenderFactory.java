package io.shipbook.shipbooksdk.Appenders;

import com.clevertap.android.sdk.Constants;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\n\u0010\u000bJ4\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u001a\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0005j\u0004\u0018\u0001`\u0006¨\u0006\f"}, d2 = {"Lio/shipbook/shipbooksdk/Appenders/AppenderFactory;", "", "", "type", AppMeasurementSdk.ConditionalUserProperty.NAME, "", "Lio/shipbook/shipbooksdk/Appenders/Config;", Constants.KEY_CONFIG, "Lio/shipbook/shipbooksdk/Appenders/BaseAppender;", "create", "<init>", "()V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final class AppenderFactory {
    public static final AppenderFactory INSTANCE = new AppenderFactory();

    @Nullable
    public final BaseAppender create(@NotNull String type, @NotNull String name, @Nullable Map<String, String> map) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(name, "name");
        int hashCode = type.hashCode();
        if (hashCode != -878823522) {
            if (hashCode != 1148532931) {
                if (hashCode != 1909151885 || !type.equals("SBCloudAppender")) {
                    return null;
                }
            } else if (!type.equals("SLCloudAppender")) {
                return null;
            }
            return new SBCloudAppender(name, map);
        } else if (type.equals("ConsoleAppender")) {
            return new ConsoleAppender(name, map);
        } else {
            return null;
        }
    }
}
