package io.shipbook.shipbooksdk.Models;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.fitness.FitnessActivities;
import io.shipbook.shipbooksdk.Models.BaseLog;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0017\b\u0080\b\u0018\u0000 ,2\u00020\u0001:\u0001,B-\u0012\u0006\u0010\f\u001a\u00020\u0004\u0012\b\b\u0002\u0010\r\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u000e\u001a\u00020\b\u0012\b\b\u0002\u0010\u000f\u001a\u00020\n¢\u0006\u0004\b*\u0010+J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\t\u0010\u0005\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0007\u001a\u00020\u0006HÆ\u0003J\t\u0010\t\u001a\u00020\bHÆ\u0003J\t\u0010\u000b\u001a\u00020\nHÆ\u0003J1\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u00062\b\b\u0002\u0010\u000e\u001a\u00020\b2\b\b\u0002\u0010\u000f\u001a\u00020\nHÆ\u0001J\t\u0010\u0012\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0006HÖ\u0001J\u0013\u0010\u0017\u001a\u00020\u00162\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014HÖ\u0003R\u0019\u0010\f\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\"\u0010\r\u001a\u00020\u00068\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001c\u0010\u000e\u001a\u00020\b8\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%R\u001c\u0010\u000f\u001a\u00020\n8\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)¨\u0006-"}, d2 = {"Lio/shipbook/shipbooksdk/Models/ConfigEvent;", "Lio/shipbook/shipbooksdk/Models/BaseEvent;", "Lorg/json/JSONObject;", "toJson", "Lio/shipbook/shipbooksdk/Models/Orientation;", "component1", "", "component2", "Ljava/util/Date;", "component3", "Lio/shipbook/shipbooksdk/Models/BaseLog$ThreadInfo;", "component4", Constants.KEY_ORIENTATION, "orderId", NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP, "threadInfo", Constants.COPY_TYPE, "", "toString", "hashCode", "", FitnessActivities.OTHER, "", "equals", "f", "Lio/shipbook/shipbooksdk/Models/Orientation;", "getOrientation", "()Lio/shipbook/shipbooksdk/Models/Orientation;", "g", "I", "getOrderId", "()I", "setOrderId", "(I)V", "h", "Ljava/util/Date;", "getTime", "()Ljava/util/Date;", "i", "Lio/shipbook/shipbooksdk/Models/BaseLog$ThreadInfo;", "getThreadInfo", "()Lio/shipbook/shipbooksdk/Models/BaseLog$ThreadInfo;", "<init>", "(Lio/shipbook/shipbooksdk/Models/Orientation;ILjava/util/Date;Lio/shipbook/shipbooksdk/Models/BaseLog$ThreadInfo;)V", "Companion", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final class ConfigEvent extends BaseEvent {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public final Orientation f;
    public int g;
    @NotNull
    public final Date h;
    @NotNull
    public final BaseLog.ThreadInfo i;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\f\u0010\rJ&\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b¨\u0006\u000e"}, d2 = {"Lio/shipbook/shipbooksdk/Models/ConfigEvent$Companion;", "", "Lorg/json/JSONObject;", "json", "", "orderId", "Ljava/util/Date;", NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP, "Lio/shipbook/shipbooksdk/Models/BaseLog$ThreadInfo;", "threadInfo", "Lio/shipbook/shipbooksdk/Models/ConfigEvent;", "create", "<init>", "()V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final ConfigEvent create(@NotNull JSONObject json, int i, @NotNull Date time, @NotNull BaseLog.ThreadInfo threadInfo) {
            Intrinsics.checkParameterIsNotNull(json, "json");
            Intrinsics.checkParameterIsNotNull(time, "time");
            Intrinsics.checkParameterIsNotNull(threadInfo, "threadInfo");
            String string = json.getString(Constants.KEY_ORIENTATION);
            Intrinsics.checkExpressionValueIsNotNull(string, "json.getString(\"orientation\")");
            return new ConfigEvent(Orientation.valueOf(string), i, time, threadInfo);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public /* synthetic */ ConfigEvent(Orientation orientation, int i, Date date, BaseLog.ThreadInfo threadInfo, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(orientation, (i2 & 2) != 0 ? 0 : i, (i2 & 4) != 0 ? new Date() : date, (i2 & 8) != 0 ? new BaseLog.ThreadInfo(null, 0L, false, 7, null) : threadInfo);
    }

    @NotNull
    public static /* synthetic */ ConfigEvent copy$default(ConfigEvent configEvent, Orientation orientation, int i, Date date, BaseLog.ThreadInfo threadInfo, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            orientation = configEvent.f;
        }
        if ((i2 & 2) != 0) {
            i = configEvent.getOrderId();
        }
        if ((i2 & 4) != 0) {
            date = configEvent.getTime();
        }
        if ((i2 & 8) != 0) {
            threadInfo = configEvent.getThreadInfo();
        }
        return configEvent.copy(orientation, i, date, threadInfo);
    }

    @NotNull
    public final Orientation component1() {
        return this.f;
    }

    public final int component2() {
        return getOrderId();
    }

    @NotNull
    public final Date component3() {
        return getTime();
    }

    @NotNull
    public final BaseLog.ThreadInfo component4() {
        return getThreadInfo();
    }

    @NotNull
    public final ConfigEvent copy(@NotNull Orientation orientation, int i, @NotNull Date time, @NotNull BaseLog.ThreadInfo threadInfo) {
        Intrinsics.checkParameterIsNotNull(orientation, "orientation");
        Intrinsics.checkParameterIsNotNull(time, "time");
        Intrinsics.checkParameterIsNotNull(threadInfo, "threadInfo");
        return new ConfigEvent(orientation, i, time, threadInfo);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof ConfigEvent) {
                ConfigEvent configEvent = (ConfigEvent) obj;
                if (Intrinsics.areEqual(this.f, configEvent.f)) {
                    if (!(getOrderId() == configEvent.getOrderId()) || !Intrinsics.areEqual(getTime(), configEvent.getTime()) || !Intrinsics.areEqual(getThreadInfo(), configEvent.getThreadInfo())) {
                    }
                }
            }
            return false;
        }
        return true;
    }

    @Override // io.shipbook.shipbooksdk.Models.BaseLog
    public int getOrderId() {
        return this.g;
    }

    @NotNull
    public final Orientation getOrientation() {
        return this.f;
    }

    @Override // io.shipbook.shipbooksdk.Models.BaseLog
    @NotNull
    public BaseLog.ThreadInfo getThreadInfo() {
        return this.i;
    }

    @Override // io.shipbook.shipbooksdk.Models.BaseLog
    @NotNull
    public Date getTime() {
        return this.h;
    }

    public int hashCode() {
        Orientation orientation = this.f;
        int hashCode = (((orientation != null ? orientation.hashCode() : 0) * 31) + getOrderId()) * 31;
        Date time = getTime();
        int hashCode2 = (hashCode + (time != null ? time.hashCode() : 0)) * 31;
        BaseLog.ThreadInfo threadInfo = getThreadInfo();
        return hashCode2 + (threadInfo != null ? threadInfo.hashCode() : 0);
    }

    @Override // io.shipbook.shipbooksdk.Models.BaseLog
    public void setOrderId(int i) {
        this.g = i;
    }

    @Override // io.shipbook.shipbooksdk.Models.BaseLog, io.shipbook.shipbooksdk.Models.BaseObj
    @NotNull
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        json.put(Constants.KEY_ORIENTATION, this.f);
        return json;
    }

    @NotNull
    public String toString() {
        return "ConfigEvent(orientation=" + this.f + ", orderId=" + getOrderId() + ", time=" + getTime() + ", threadInfo=" + getThreadInfo() + ")";
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConfigEvent(@NotNull Orientation orientation, int i, @NotNull Date time, @NotNull BaseLog.ThreadInfo threadInfo) {
        super("configEvent");
        Intrinsics.checkParameterIsNotNull(orientation, "orientation");
        Intrinsics.checkParameterIsNotNull(time, "time");
        Intrinsics.checkParameterIsNotNull(threadInfo, "threadInfo");
        this.f = orientation;
        this.g = i;
        this.h = time;
        this.i = threadInfo;
        setOrderId(incrementOrderId(getOrderId()));
    }
}
