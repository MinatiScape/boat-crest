package io.shipbook.shipbooksdk.Models;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import io.shipbook.shipbooksdk.Models.BaseLog;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0019\b\u0080\b\u0018\u0000 /2\u00020\u0001:\u0001/B5\u0012\u0006\u0010\r\u001a\u00020\u0004\u0012\u0006\u0010\u000e\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0010\u001a\u00020\t\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u000b¢\u0006\u0004\b-\u0010.J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\t\u0010\u0005\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0006\u001a\u00020\u0004HÆ\u0003J\t\u0010\b\u001a\u00020\u0007HÆ\u0003J\t\u0010\n\u001a\u00020\tHÆ\u0003J\t\u0010\f\u001a\u00020\u000bHÆ\u0003J;\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\r\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u00042\b\b\u0002\u0010\u000f\u001a\u00020\u00072\b\b\u0002\u0010\u0010\u001a\u00020\t2\b\b\u0002\u0010\u0011\u001a\u00020\u000bHÆ\u0001J\t\u0010\u0013\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0007HÖ\u0001J\u0013\u0010\u0018\u001a\u00020\u00172\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015HÖ\u0003R\u0019\u0010\r\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u0019\u0010\u000e\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u001a\u001a\u0004\b\u001e\u0010\u001cR\"\u0010\u000f\u001a\u00020\u00078\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001c\u0010\u0010\u001a\u00020\t8\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(R\u001c\u0010\u0011\u001a\u00020\u000b8\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,¨\u00060"}, d2 = {"Lio/shipbook/shipbooksdk/Models/FragmentEvent;", "Lio/shipbook/shipbooksdk/Models/BaseEvent;", "Lorg/json/JSONObject;", "toJson", "", "component1", "component2", "", "component3", "Ljava/util/Date;", "component4", "Lio/shipbook/shipbooksdk/Models/BaseLog$ThreadInfo;", "component5", AppMeasurementSdk.ConditionalUserProperty.NAME, "event", "orderId", NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP, "threadInfo", Constants.COPY_TYPE, "toString", "hashCode", "", FitnessActivities.OTHER, "", "equals", "f", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "g", "getEvent", "h", "I", "getOrderId", "()I", "setOrderId", "(I)V", "i", "Ljava/util/Date;", "getTime", "()Ljava/util/Date;", "j", "Lio/shipbook/shipbooksdk/Models/BaseLog$ThreadInfo;", "getThreadInfo", "()Lio/shipbook/shipbooksdk/Models/BaseLog$ThreadInfo;", "<init>", "(Ljava/lang/String;Ljava/lang/String;ILjava/util/Date;Lio/shipbook/shipbooksdk/Models/BaseLog$ThreadInfo;)V", "Companion", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final class FragmentEvent extends BaseEvent {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public final String f;
    @NotNull
    public final String g;
    public int h;
    @NotNull
    public final Date i;
    @NotNull
    public final BaseLog.ThreadInfo j;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\f\u0010\rJ&\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b¨\u0006\u000e"}, d2 = {"Lio/shipbook/shipbooksdk/Models/FragmentEvent$Companion;", "", "Lorg/json/JSONObject;", "json", "", "orderId", "Ljava/util/Date;", NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP, "Lio/shipbook/shipbooksdk/Models/BaseLog$ThreadInfo;", "threadInfo", "Lio/shipbook/shipbooksdk/Models/FragmentEvent;", "create", "<init>", "()V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final FragmentEvent create(@NotNull JSONObject json, int i, @NotNull Date time, @NotNull BaseLog.ThreadInfo threadInfo) {
            Intrinsics.checkParameterIsNotNull(json, "json");
            Intrinsics.checkParameterIsNotNull(time, "time");
            Intrinsics.checkParameterIsNotNull(threadInfo, "threadInfo");
            String name = json.getString(AppMeasurementSdk.ConditionalUserProperty.NAME);
            String event = json.getString("event");
            Intrinsics.checkExpressionValueIsNotNull(name, "name");
            Intrinsics.checkExpressionValueIsNotNull(event, "event");
            return new FragmentEvent(name, event, i, time, threadInfo);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public /* synthetic */ FragmentEvent(String str, String str2, int i, Date date, BaseLog.ThreadInfo threadInfo, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i2 & 4) != 0 ? 0 : i, (i2 & 8) != 0 ? new Date() : date, (i2 & 16) != 0 ? new BaseLog.ThreadInfo(null, 0L, false, 7, null) : threadInfo);
    }

    @NotNull
    public static /* synthetic */ FragmentEvent copy$default(FragmentEvent fragmentEvent, String str, String str2, int i, Date date, BaseLog.ThreadInfo threadInfo, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = fragmentEvent.f;
        }
        if ((i2 & 2) != 0) {
            str2 = fragmentEvent.g;
        }
        String str3 = str2;
        if ((i2 & 4) != 0) {
            i = fragmentEvent.getOrderId();
        }
        int i3 = i;
        if ((i2 & 8) != 0) {
            date = fragmentEvent.getTime();
        }
        Date date2 = date;
        if ((i2 & 16) != 0) {
            threadInfo = fragmentEvent.getThreadInfo();
        }
        return fragmentEvent.copy(str, str3, i3, date2, threadInfo);
    }

    @NotNull
    public final String component1() {
        return this.f;
    }

    @NotNull
    public final String component2() {
        return this.g;
    }

    public final int component3() {
        return getOrderId();
    }

    @NotNull
    public final Date component4() {
        return getTime();
    }

    @NotNull
    public final BaseLog.ThreadInfo component5() {
        return getThreadInfo();
    }

    @NotNull
    public final FragmentEvent copy(@NotNull String name, @NotNull String event, int i, @NotNull Date time, @NotNull BaseLog.ThreadInfo threadInfo) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(event, "event");
        Intrinsics.checkParameterIsNotNull(time, "time");
        Intrinsics.checkParameterIsNotNull(threadInfo, "threadInfo");
        return new FragmentEvent(name, event, i, time, threadInfo);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof FragmentEvent) {
                FragmentEvent fragmentEvent = (FragmentEvent) obj;
                if (Intrinsics.areEqual(this.f, fragmentEvent.f) && Intrinsics.areEqual(this.g, fragmentEvent.g)) {
                    if (!(getOrderId() == fragmentEvent.getOrderId()) || !Intrinsics.areEqual(getTime(), fragmentEvent.getTime()) || !Intrinsics.areEqual(getThreadInfo(), fragmentEvent.getThreadInfo())) {
                    }
                }
            }
            return false;
        }
        return true;
    }

    @NotNull
    public final String getEvent() {
        return this.g;
    }

    @NotNull
    public final String getName() {
        return this.f;
    }

    @Override // io.shipbook.shipbooksdk.Models.BaseLog
    public int getOrderId() {
        return this.h;
    }

    @Override // io.shipbook.shipbooksdk.Models.BaseLog
    @NotNull
    public BaseLog.ThreadInfo getThreadInfo() {
        return this.j;
    }

    @Override // io.shipbook.shipbooksdk.Models.BaseLog
    @NotNull
    public Date getTime() {
        return this.i;
    }

    public int hashCode() {
        String str = this.f;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.g;
        int hashCode2 = (((hashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + getOrderId()) * 31;
        Date time = getTime();
        int hashCode3 = (hashCode2 + (time != null ? time.hashCode() : 0)) * 31;
        BaseLog.ThreadInfo threadInfo = getThreadInfo();
        return hashCode3 + (threadInfo != null ? threadInfo.hashCode() : 0);
    }

    @Override // io.shipbook.shipbooksdk.Models.BaseLog
    public void setOrderId(int i) {
        this.h = i;
    }

    @Override // io.shipbook.shipbooksdk.Models.BaseLog, io.shipbook.shipbooksdk.Models.BaseObj
    @NotNull
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        json.put(AppMeasurementSdk.ConditionalUserProperty.NAME, this.f);
        json.put("event", this.g);
        return json;
    }

    @NotNull
    public String toString() {
        return "FragmentEvent(name=" + this.f + ", event=" + this.g + ", orderId=" + getOrderId() + ", time=" + getTime() + ", threadInfo=" + getThreadInfo() + ")";
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FragmentEvent(@NotNull String name, @NotNull String event, int i, @NotNull Date time, @NotNull BaseLog.ThreadInfo threadInfo) {
        super("fragmentEvent");
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(event, "event");
        Intrinsics.checkParameterIsNotNull(time, "time");
        Intrinsics.checkParameterIsNotNull(threadInfo, "threadInfo");
        this.f = name;
        this.g = event;
        this.h = i;
        this.i = time;
        this.j = threadInfo;
        setOrderId(incrementOrderId(getOrderId()));
    }
}
