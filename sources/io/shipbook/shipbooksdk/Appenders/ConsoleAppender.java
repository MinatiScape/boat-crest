package io.shipbook.shipbooksdk.Appenders;

import android.util.Log;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import io.shipbook.shipbooksdk.Models.BaseLog;
import io.shipbook.shipbooksdk.Models.Message;
import io.shipbook.shipbooksdk.Models.Severity;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u001a\u0010\u0005\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002j\u0004\u0018\u0001`\u0004¢\u0006\u0004\b\f\u0010\rJ$\u0010\u0007\u001a\u00020\u00062\u001a\u0010\u0005\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002j\u0004\u0018\u0001`\u0004H\u0016J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0016¨\u0006\u000e"}, d2 = {"Lio/shipbook/shipbooksdk/Appenders/ConsoleAppender;", "Lio/shipbook/shipbooksdk/Appenders/BaseAppender;", "", "", "Lio/shipbook/shipbooksdk/Appenders/Config;", Constants.KEY_CONFIG, "", "update", "Lio/shipbook/shipbooksdk/Models/BaseLog;", "log", "push", AppMeasurementSdk.ConditionalUserProperty.NAME, "<init>", "(Ljava/lang/String;Ljava/util/Map;)V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final class ConsoleAppender extends BaseAppender {

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes12.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[Severity.values().length];
            $EnumSwitchMapping$0 = iArr;
            Severity severity = Severity.Error;
            iArr[severity.ordinal()] = 1;
            Severity severity2 = Severity.Warning;
            iArr[severity2.ordinal()] = 2;
            Severity severity3 = Severity.Info;
            iArr[severity3.ordinal()] = 3;
            Severity severity4 = Severity.Debug;
            iArr[severity4.ordinal()] = 4;
            Severity severity5 = Severity.Verbose;
            iArr[severity5.ordinal()] = 5;
            Severity severity6 = Severity.Off;
            iArr[severity6.ordinal()] = 6;
            int[] iArr2 = new int[Severity.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[severity.ordinal()] = 1;
            iArr2[severity2.ordinal()] = 2;
            iArr2[severity3.ordinal()] = 3;
            iArr2[severity4.ordinal()] = 4;
            iArr2[severity5.ordinal()] = 5;
            iArr2[severity6.ordinal()] = 6;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConsoleAppender(@NotNull String name, @Nullable Map<String, String> map) {
        super(name, map);
        Intrinsics.checkParameterIsNotNull(name, "name");
    }

    @Override // io.shipbook.shipbooksdk.Appenders.BaseAppender
    public void push(@NotNull BaseLog log) {
        Intrinsics.checkParameterIsNotNull(log, "log");
        if (log instanceof Message) {
            Message message = (Message) log;
            if (message.getThrowable() == null) {
                int i = WhenMappings.$EnumSwitchMapping$0[message.getSeverity().ordinal()];
                if (i == 1) {
                    Log.e(message.getTag(), message.getMessage());
                    return;
                } else if (i == 2) {
                    Log.w(message.getTag(), message.getMessage());
                    return;
                } else if (i == 3) {
                    Log.i(message.getTag(), message.getMessage());
                    return;
                } else if (i == 4) {
                    Log.d(message.getTag(), message.getMessage());
                    return;
                } else if (i != 5) {
                    return;
                } else {
                    Log.v(message.getTag(), message.getMessage());
                    return;
                }
            }
            int i2 = WhenMappings.$EnumSwitchMapping$1[message.getSeverity().ordinal()];
            if (i2 == 1) {
                Log.e(message.getTag(), message.getMessage(), message.getThrowable());
            } else if (i2 == 2) {
                Log.w(message.getTag(), message.getMessage(), message.getThrowable());
            } else if (i2 == 3) {
                Log.i(message.getTag(), message.getMessage(), message.getThrowable());
            } else if (i2 == 4) {
                Log.d(message.getTag(), message.getMessage(), message.getThrowable());
            } else if (i2 != 5) {
            } else {
                Log.v(message.getTag(), message.getMessage(), message.getThrowable());
            }
        }
    }

    @Override // io.shipbook.shipbooksdk.Appenders.BaseAppender
    public void update(@Nullable Map<String, String> map) {
    }
}
