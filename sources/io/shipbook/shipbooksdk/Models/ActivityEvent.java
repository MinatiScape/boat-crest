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
import org.jose4j.jwk.OctetSequenceJsonWebKey;
import org.json.JSONObject;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001b\b\u0080\b\u0018\u0000 32\u00020\u0001:\u00013B=\u0012\u0006\u0010\u000e\u001a\u00020\u0004\u0012\u0006\u0010\u000f\u001a\u00020\u0004\u0012\u0006\u0010\u0010\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0011\u001a\u00020\b\u0012\b\b\u0002\u0010\u0012\u001a\u00020\n\u0012\b\b\u0002\u0010\u0013\u001a\u00020\f¢\u0006\u0004\b1\u00102J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\t\u0010\u0005\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0006\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0007\u001a\u00020\u0004HÆ\u0003J\t\u0010\t\u001a\u00020\bHÆ\u0003J\t\u0010\u000b\u001a\u00020\nHÆ\u0003J\t\u0010\r\u001a\u00020\fHÆ\u0003JE\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u000e\u001a\u00020\u00042\b\b\u0002\u0010\u000f\u001a\u00020\u00042\b\b\u0002\u0010\u0010\u001a\u00020\u00042\b\b\u0002\u0010\u0011\u001a\u00020\b2\b\b\u0002\u0010\u0012\u001a\u00020\n2\b\b\u0002\u0010\u0013\u001a\u00020\fHÆ\u0001J\t\u0010\u0015\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0016\u001a\u00020\bHÖ\u0001J\u0013\u0010\u001a\u001a\u00020\u00192\b\u0010\u0018\u001a\u0004\u0018\u00010\u0017HÖ\u0003R\u0019\u0010\u000e\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0019\u0010\u000f\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\u001f\u0010\u001c\u001a\u0004\b \u0010\u001eR\u0019\u0010\u0010\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b!\u0010\u001c\u001a\u0004\b\"\u0010\u001eR\"\u0010\u0011\u001a\u00020\b8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001c\u0010\u0012\u001a\u00020\n8\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,R\u001c\u0010\u0013\u001a\u00020\f8\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b-\u0010.\u001a\u0004\b/\u00100¨\u00064"}, d2 = {"Lio/shipbook/shipbooksdk/Models/ActivityEvent;", "Lio/shipbook/shipbooksdk/Models/BaseEvent;", "Lorg/json/JSONObject;", "toJson", "", "component1", "component2", "component3", "", "component4", "Ljava/util/Date;", "component5", "Lio/shipbook/shipbooksdk/Models/BaseLog$ThreadInfo;", "component6", AppMeasurementSdk.ConditionalUserProperty.NAME, "event", "title", "orderId", NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP, "threadInfo", Constants.COPY_TYPE, "toString", "hashCode", "", FitnessActivities.OTHER, "", "equals", "f", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "g", "getEvent", "h", "getTitle", "i", "I", "getOrderId", "()I", "setOrderId", "(I)V", "j", "Ljava/util/Date;", "getTime", "()Ljava/util/Date;", OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME, "Lio/shipbook/shipbooksdk/Models/BaseLog$ThreadInfo;", "getThreadInfo", "()Lio/shipbook/shipbooksdk/Models/BaseLog$ThreadInfo;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/util/Date;Lio/shipbook/shipbooksdk/Models/BaseLog$ThreadInfo;)V", "Companion", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final class ActivityEvent extends BaseEvent {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public final String f;
    @NotNull
    public final String g;
    @NotNull
    public final String h;
    public int i;
    @NotNull
    public final Date j;
    @NotNull
    public final BaseLog.ThreadInfo k;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\f\u0010\rJ&\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b¨\u0006\u000e"}, d2 = {"Lio/shipbook/shipbooksdk/Models/ActivityEvent$Companion;", "", "Lorg/json/JSONObject;", "json", "", "orderId", "Ljava/util/Date;", NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP, "Lio/shipbook/shipbooksdk/Models/BaseLog$ThreadInfo;", "threadInfo", "Lio/shipbook/shipbooksdk/Models/ActivityEvent;", "create", "<init>", "()V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final ActivityEvent create(@NotNull JSONObject json, int i, @NotNull Date time, @NotNull BaseLog.ThreadInfo threadInfo) {
            Intrinsics.checkParameterIsNotNull(json, "json");
            Intrinsics.checkParameterIsNotNull(time, "time");
            Intrinsics.checkParameterIsNotNull(threadInfo, "threadInfo");
            String name = json.getString(AppMeasurementSdk.ConditionalUserProperty.NAME);
            String event = json.getString("event");
            String title = json.getString("title");
            Intrinsics.checkExpressionValueIsNotNull(name, "name");
            Intrinsics.checkExpressionValueIsNotNull(event, "event");
            Intrinsics.checkExpressionValueIsNotNull(title, "title");
            return new ActivityEvent(name, event, title, i, time, threadInfo);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public /* synthetic */ ActivityEvent(String str, String str2, String str3, int i, Date date, BaseLog.ThreadInfo threadInfo, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i2 & 8) != 0 ? 0 : i, (i2 & 16) != 0 ? new Date() : date, (i2 & 32) != 0 ? new BaseLog.ThreadInfo(null, 0L, false, 7, null) : threadInfo);
    }

    @NotNull
    public static /* synthetic */ ActivityEvent copy$default(ActivityEvent activityEvent, String str, String str2, String str3, int i, Date date, BaseLog.ThreadInfo threadInfo, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = activityEvent.f;
        }
        if ((i2 & 2) != 0) {
            str2 = activityEvent.g;
        }
        String str4 = str2;
        if ((i2 & 4) != 0) {
            str3 = activityEvent.h;
        }
        String str5 = str3;
        if ((i2 & 8) != 0) {
            i = activityEvent.getOrderId();
        }
        int i3 = i;
        if ((i2 & 16) != 0) {
            date = activityEvent.getTime();
        }
        Date date2 = date;
        if ((i2 & 32) != 0) {
            threadInfo = activityEvent.getThreadInfo();
        }
        return activityEvent.copy(str, str4, str5, i3, date2, threadInfo);
    }

    @NotNull
    public final String component1() {
        return this.f;
    }

    @NotNull
    public final String component2() {
        return this.g;
    }

    @NotNull
    public final String component3() {
        return this.h;
    }

    public final int component4() {
        return getOrderId();
    }

    @NotNull
    public final Date component5() {
        return getTime();
    }

    @NotNull
    public final BaseLog.ThreadInfo component6() {
        return getThreadInfo();
    }

    @NotNull
    public final ActivityEvent copy(@NotNull String name, @NotNull String event, @NotNull String title, int i, @NotNull Date time, @NotNull BaseLog.ThreadInfo threadInfo) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(event, "event");
        Intrinsics.checkParameterIsNotNull(title, "title");
        Intrinsics.checkParameterIsNotNull(time, "time");
        Intrinsics.checkParameterIsNotNull(threadInfo, "threadInfo");
        return new ActivityEvent(name, event, title, i, time, threadInfo);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof ActivityEvent) {
                ActivityEvent activityEvent = (ActivityEvent) obj;
                if (Intrinsics.areEqual(this.f, activityEvent.f) && Intrinsics.areEqual(this.g, activityEvent.g) && Intrinsics.areEqual(this.h, activityEvent.h)) {
                    if (!(getOrderId() == activityEvent.getOrderId()) || !Intrinsics.areEqual(getTime(), activityEvent.getTime()) || !Intrinsics.areEqual(getThreadInfo(), activityEvent.getThreadInfo())) {
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
        return this.i;
    }

    @Override // io.shipbook.shipbooksdk.Models.BaseLog
    @NotNull
    public BaseLog.ThreadInfo getThreadInfo() {
        return this.k;
    }

    @Override // io.shipbook.shipbooksdk.Models.BaseLog
    @NotNull
    public Date getTime() {
        return this.j;
    }

    @NotNull
    public final String getTitle() {
        return this.h;
    }

    public int hashCode() {
        String str = this.f;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.g;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.h;
        int hashCode3 = (((hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31) + getOrderId()) * 31;
        Date time = getTime();
        int hashCode4 = (hashCode3 + (time != null ? time.hashCode() : 0)) * 31;
        BaseLog.ThreadInfo threadInfo = getThreadInfo();
        return hashCode4 + (threadInfo != null ? threadInfo.hashCode() : 0);
    }

    @Override // io.shipbook.shipbooksdk.Models.BaseLog
    public void setOrderId(int i) {
        this.i = i;
    }

    @Override // io.shipbook.shipbooksdk.Models.BaseLog, io.shipbook.shipbooksdk.Models.BaseObj
    @NotNull
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        json.put(AppMeasurementSdk.ConditionalUserProperty.NAME, this.f);
        json.put("event", this.g);
        json.put("title", this.h);
        return json;
    }

    @NotNull
    public String toString() {
        return "ActivityEvent(name=" + this.f + ", event=" + this.g + ", title=" + this.h + ", orderId=" + getOrderId() + ", time=" + getTime() + ", threadInfo=" + getThreadInfo() + ")";
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActivityEvent(@NotNull String name, @NotNull String event, @NotNull String title, int i, @NotNull Date time, @NotNull BaseLog.ThreadInfo threadInfo) {
        super("activityEvent");
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(event, "event");
        Intrinsics.checkParameterIsNotNull(title, "title");
        Intrinsics.checkParameterIsNotNull(time, "time");
        Intrinsics.checkParameterIsNotNull(threadInfo, "threadInfo");
        this.f = name;
        this.g = event;
        this.h = title;
        this.i = i;
        this.j = time;
        this.k = threadInfo;
        setOrderId(incrementOrderId(getOrderId()));
    }
}
