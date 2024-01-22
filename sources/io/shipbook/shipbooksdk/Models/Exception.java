package io.shipbook.shipbooksdk.Models;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import io.shipbook.shipbooksdk.Models.BaseLog;
import io.shipbook.shipbooksdk.Util.ListStackTraceElementExtKt;
import java.util.Date;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.paho.android.service.MqttServiceConstants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
import org.json.JSONArray;
import org.json.JSONObject;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001d\b\u0080\b\u0018\u0000 72\u00020\u0001:\u00017BG\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004\u0012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\b\u0002\u0010\u0013\u001a\u00020\n\u0012\b\b\u0002\u0010\u0014\u001a\u00020\f\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u000e¢\u0006\u0004\b5\u00106J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\u000b\u0010\u0005\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u0006\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\t\u0010\u000b\u001a\u00020\nHÆ\u0003J\t\u0010\r\u001a\u00020\fHÆ\u0003J\t\u0010\u000f\u001a\u00020\u000eHÆ\u0003JO\u0010\u0016\u001a\u00020\u00002\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00042\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\u0013\u001a\u00020\n2\b\b\u0002\u0010\u0014\u001a\u00020\f2\b\b\u0002\u0010\u0015\u001a\u00020\u000eHÆ\u0001J\t\u0010\u0017\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0018\u001a\u00020\nHÖ\u0001J\u0013\u0010\u001c\u001a\u00020\u001b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019HÖ\u0003R\u001b\u0010\u0010\u001a\u0004\u0018\u00010\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u001b\u0010\u0011\u001a\u0004\u0018\u00010\u00048\u0006@\u0006¢\u0006\f\n\u0004\b!\u0010\u001e\u001a\u0004\b\"\u0010 R\u001f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006@\u0006¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&R\"\u0010\u0013\u001a\u00020\n8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001c\u0010\u0014\u001a\u00020\f8\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b-\u0010.\u001a\u0004\b/\u00100R\u001c\u0010\u0015\u001a\u00020\u000e8\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b1\u00102\u001a\u0004\b3\u00104¨\u00068"}, d2 = {"Lio/shipbook/shipbooksdk/Models/Exception;", "Lio/shipbook/shipbooksdk/Models/BaseLog;", "Lorg/json/JSONObject;", "toJson", "", "component1", "component2", "", "Lio/shipbook/shipbooksdk/Models/StackTraceElement;", "component3", "", "component4", "Ljava/util/Date;", "component5", "Lio/shipbook/shipbooksdk/Models/BaseLog$ThreadInfo;", "component6", AppMeasurementSdk.ConditionalUserProperty.NAME, "reason", "stackTrace", "orderId", NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP, "threadInfo", Constants.COPY_TYPE, "toString", "hashCode", "", FitnessActivities.OTHER, "", "equals", "f", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "g", "getReason", "h", "Ljava/util/List;", "getStackTrace", "()Ljava/util/List;", "i", "I", "getOrderId", "()I", "setOrderId", "(I)V", "j", "Ljava/util/Date;", "getTime", "()Ljava/util/Date;", OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME, "Lio/shipbook/shipbooksdk/Models/BaseLog$ThreadInfo;", "getThreadInfo", "()Lio/shipbook/shipbooksdk/Models/BaseLog$ThreadInfo;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;ILjava/util/Date;Lio/shipbook/shipbooksdk/Models/BaseLog$ThreadInfo;)V", "Companion", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final class Exception extends BaseLog {
    public static final Companion Companion = new Companion(null);
    @Nullable
    public final String f;
    @Nullable
    public final String g;
    @NotNull
    public final List<StackTraceElement> h;
    public int i;
    @NotNull
    public final Date j;
    @NotNull
    public final BaseLog.ThreadInfo k;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\f\u0010\rJ&\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b¨\u0006\u000e"}, d2 = {"Lio/shipbook/shipbooksdk/Models/Exception$Companion;", "", "Lorg/json/JSONObject;", "json", "", "orderId", "Ljava/util/Date;", NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP, "Lio/shipbook/shipbooksdk/Models/BaseLog$ThreadInfo;", "threadInfo", "Lio/shipbook/shipbooksdk/Models/Exception;", "create", "<init>", "()V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final Exception create(@NotNull JSONObject json, int i, @NotNull Date time, @NotNull BaseLog.ThreadInfo threadInfo) {
            Intrinsics.checkParameterIsNotNull(json, "json");
            Intrinsics.checkParameterIsNotNull(time, "time");
            Intrinsics.checkParameterIsNotNull(threadInfo, "threadInfo");
            String optString = json.optString(AppMeasurementSdk.ConditionalUserProperty.NAME);
            String optString2 = json.optString("reason");
            JSONArray jSONArray = json.getJSONArray("stackTrace");
            Intrinsics.checkExpressionValueIsNotNull(jSONArray, "json.getJSONArray(\"stackTrace\")");
            return new Exception(optString, optString2, ListStackTraceElementExtKt.toListStackTraceElement(jSONArray), i, time, threadInfo);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public /* synthetic */ Exception(String str, String str2, List list, int i, Date date, BaseLog.ThreadInfo threadInfo, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, list, (i2 & 8) != 0 ? 0 : i, (i2 & 16) != 0 ? new Date() : date, (i2 & 32) != 0 ? new BaseLog.ThreadInfo(null, 0L, false, 7, null) : threadInfo);
    }

    @NotNull
    public static /* synthetic */ Exception copy$default(Exception exception, String str, String str2, List list, int i, Date date, BaseLog.ThreadInfo threadInfo, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = exception.f;
        }
        if ((i2 & 2) != 0) {
            str2 = exception.g;
        }
        String str3 = str2;
        List<StackTraceElement> list2 = list;
        if ((i2 & 4) != 0) {
            list2 = exception.h;
        }
        List list3 = list2;
        if ((i2 & 8) != 0) {
            i = exception.getOrderId();
        }
        int i3 = i;
        if ((i2 & 16) != 0) {
            date = exception.getTime();
        }
        Date date2 = date;
        if ((i2 & 32) != 0) {
            threadInfo = exception.getThreadInfo();
        }
        return exception.copy(str, str3, list3, i3, date2, threadInfo);
    }

    @Nullable
    public final String component1() {
        return this.f;
    }

    @Nullable
    public final String component2() {
        return this.g;
    }

    @NotNull
    public final List<StackTraceElement> component3() {
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
    public final Exception copy(@Nullable String str, @Nullable String str2, @NotNull List<StackTraceElement> stackTrace, int i, @NotNull Date time, @NotNull BaseLog.ThreadInfo threadInfo) {
        Intrinsics.checkParameterIsNotNull(stackTrace, "stackTrace");
        Intrinsics.checkParameterIsNotNull(time, "time");
        Intrinsics.checkParameterIsNotNull(threadInfo, "threadInfo");
        return new Exception(str, str2, stackTrace, i, time, threadInfo);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof Exception) {
                Exception exception = (Exception) obj;
                if (Intrinsics.areEqual(this.f, exception.f) && Intrinsics.areEqual(this.g, exception.g) && Intrinsics.areEqual(this.h, exception.h)) {
                    if (!(getOrderId() == exception.getOrderId()) || !Intrinsics.areEqual(getTime(), exception.getTime()) || !Intrinsics.areEqual(getThreadInfo(), exception.getThreadInfo())) {
                    }
                }
            }
            return false;
        }
        return true;
    }

    @Nullable
    public final String getName() {
        return this.f;
    }

    @Override // io.shipbook.shipbooksdk.Models.BaseLog
    public int getOrderId() {
        return this.i;
    }

    @Nullable
    public final String getReason() {
        return this.g;
    }

    @NotNull
    public final List<StackTraceElement> getStackTrace() {
        return this.h;
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

    public int hashCode() {
        String str = this.f;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.g;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        List<StackTraceElement> list = this.h;
        int hashCode3 = (((hashCode2 + (list != null ? list.hashCode() : 0)) * 31) + getOrderId()) * 31;
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
        json.putOpt(AppMeasurementSdk.ConditionalUserProperty.NAME, this.f);
        json.putOpt("reason", this.g);
        json.put("stackTrace", ListStackTraceElementExtKt.toJson(this.h));
        return json;
    }

    @NotNull
    public String toString() {
        return "Exception(name=" + this.f + ", reason=" + this.g + ", stackTrace=" + this.h + ", orderId=" + getOrderId() + ", time=" + getTime() + ", threadInfo=" + getThreadInfo() + ")";
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Exception(@Nullable String str, @Nullable String str2, @NotNull List<StackTraceElement> stackTrace, int i, @NotNull Date time, @NotNull BaseLog.ThreadInfo threadInfo) {
        super(MqttServiceConstants.TRACE_EXCEPTION, 0, null, null, 14, null);
        Intrinsics.checkParameterIsNotNull(stackTrace, "stackTrace");
        Intrinsics.checkParameterIsNotNull(time, "time");
        Intrinsics.checkParameterIsNotNull(threadInfo, "threadInfo");
        this.f = str;
        this.g = str2;
        this.h = stackTrace;
        this.i = i;
        this.j = time;
        this.k = threadInfo;
        setOrderId(incrementOrderId(getOrderId()));
    }
}
