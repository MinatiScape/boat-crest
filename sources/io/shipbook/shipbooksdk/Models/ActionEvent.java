package io.shipbook.shipbooksdk.Models;

import androidx.constraintlayout.core.motion.utils.TypedValues;
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
import org.jose4j.jwk.OctetSequenceJsonWebKey;
import org.json.JSONObject;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001d\b\u0080\b\u0018\u0000 72\u00020\u0001:\u00017BE\u0012\u0006\u0010\u000f\u001a\u00020\u0004\u0012\u0006\u0010\u0010\u001a\u00020\u0004\u0012\u0006\u0010\u0011\u001a\u00020\u0004\u0012\u0006\u0010\u0012\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0013\u001a\u00020\t\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0015\u001a\u00020\r¢\u0006\u0004\b5\u00106J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\t\u0010\u0005\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0006\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0007\u001a\u00020\u0004HÆ\u0003J\t\u0010\b\u001a\u00020\u0004HÆ\u0003J\t\u0010\n\u001a\u00020\tHÆ\u0003J\t\u0010\f\u001a\u00020\u000bHÆ\u0003J\t\u0010\u000e\u001a\u00020\rHÆ\u0003JO\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u000f\u001a\u00020\u00042\b\b\u0002\u0010\u0010\u001a\u00020\u00042\b\b\u0002\u0010\u0011\u001a\u00020\u00042\b\b\u0002\u0010\u0012\u001a\u00020\u00042\b\b\u0002\u0010\u0013\u001a\u00020\t2\b\b\u0002\u0010\u0014\u001a\u00020\u000b2\b\b\u0002\u0010\u0015\u001a\u00020\rHÆ\u0001J\t\u0010\u0017\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0018\u001a\u00020\tHÖ\u0001J\u0013\u0010\u001c\u001a\u00020\u001b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019HÖ\u0003R\u0019\u0010\u000f\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u0019\u0010\u0010\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b!\u0010\u001e\u001a\u0004\b\"\u0010 R\u0019\u0010\u0011\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b#\u0010\u001e\u001a\u0004\b$\u0010 R\u0019\u0010\u0012\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b%\u0010\u001e\u001a\u0004\b&\u0010 R\"\u0010\u0013\u001a\u00020\t8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001c\u0010\u0014\u001a\u00020\u000b8\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b-\u0010.\u001a\u0004\b/\u00100R\u001c\u0010\u0015\u001a\u00020\r8\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b1\u00102\u001a\u0004\b3\u00104¨\u00068"}, d2 = {"Lio/shipbook/shipbooksdk/Models/ActionEvent;", "Lio/shipbook/shipbooksdk/Models/BaseEvent;", "Lorg/json/JSONObject;", "toJson", "", "component1", "component2", "component3", "component4", "", "component5", "Ljava/util/Date;", "component6", "Lio/shipbook/shipbooksdk/Models/BaseLog$ThreadInfo;", "component7", Constants.KEY_ACTION, NotificationCompat.MessagingStyle.Message.KEY_SENDER, "senderTitle", TypedValues.AttributesType.S_TARGET, "orderId", NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP, "threadInfo", Constants.COPY_TYPE, "toString", "hashCode", "", FitnessActivities.OTHER, "", "equals", "f", "Ljava/lang/String;", "getAction", "()Ljava/lang/String;", "g", "getSender", "h", "getSenderTitle", "i", "getTarget", "j", "I", "getOrderId", "()I", "setOrderId", "(I)V", OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME, "Ljava/util/Date;", "getTime", "()Ljava/util/Date;", "l", "Lio/shipbook/shipbooksdk/Models/BaseLog$ThreadInfo;", "getThreadInfo", "()Lio/shipbook/shipbooksdk/Models/BaseLog$ThreadInfo;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/util/Date;Lio/shipbook/shipbooksdk/Models/BaseLog$ThreadInfo;)V", "Companion", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final class ActionEvent extends BaseEvent {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public final String f;
    @NotNull
    public final String g;
    @NotNull
    public final String h;
    @NotNull
    public final String i;
    public int j;
    @NotNull
    public final Date k;
    @NotNull
    public final BaseLog.ThreadInfo l;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\f\u0010\rJ&\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b¨\u0006\u000e"}, d2 = {"Lio/shipbook/shipbooksdk/Models/ActionEvent$Companion;", "", "Lorg/json/JSONObject;", "json", "", "orderId", "Ljava/util/Date;", NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP, "Lio/shipbook/shipbooksdk/Models/BaseLog$ThreadInfo;", "threadInfo", "Lio/shipbook/shipbooksdk/Models/ActionEvent;", "create", "<init>", "()V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final ActionEvent create(@NotNull JSONObject json, int i, @NotNull Date time, @NotNull BaseLog.ThreadInfo threadInfo) {
            Intrinsics.checkParameterIsNotNull(json, "json");
            Intrinsics.checkParameterIsNotNull(time, "time");
            Intrinsics.checkParameterIsNotNull(threadInfo, "threadInfo");
            String action = json.getString(Constants.KEY_ACTION);
            String sender = json.getString(NotificationCompat.MessagingStyle.Message.KEY_SENDER);
            String senderTitle = json.getString("senderTitle");
            String target = json.getString(TypedValues.AttributesType.S_TARGET);
            Intrinsics.checkExpressionValueIsNotNull(action, "action");
            Intrinsics.checkExpressionValueIsNotNull(sender, "sender");
            Intrinsics.checkExpressionValueIsNotNull(senderTitle, "senderTitle");
            Intrinsics.checkExpressionValueIsNotNull(target, "target");
            return new ActionEvent(action, sender, senderTitle, target, i, time, threadInfo);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public /* synthetic */ ActionEvent(String str, String str2, String str3, String str4, int i, Date date, BaseLog.ThreadInfo threadInfo, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, (i2 & 16) != 0 ? 0 : i, (i2 & 32) != 0 ? new Date() : date, (i2 & 64) != 0 ? new BaseLog.ThreadInfo(null, 0L, false, 7, null) : threadInfo);
    }

    @NotNull
    public static /* synthetic */ ActionEvent copy$default(ActionEvent actionEvent, String str, String str2, String str3, String str4, int i, Date date, BaseLog.ThreadInfo threadInfo, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = actionEvent.f;
        }
        if ((i2 & 2) != 0) {
            str2 = actionEvent.g;
        }
        String str5 = str2;
        if ((i2 & 4) != 0) {
            str3 = actionEvent.h;
        }
        String str6 = str3;
        if ((i2 & 8) != 0) {
            str4 = actionEvent.i;
        }
        String str7 = str4;
        if ((i2 & 16) != 0) {
            i = actionEvent.getOrderId();
        }
        int i3 = i;
        if ((i2 & 32) != 0) {
            date = actionEvent.getTime();
        }
        Date date2 = date;
        if ((i2 & 64) != 0) {
            threadInfo = actionEvent.getThreadInfo();
        }
        return actionEvent.copy(str, str5, str6, str7, i3, date2, threadInfo);
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

    @NotNull
    public final String component4() {
        return this.i;
    }

    public final int component5() {
        return getOrderId();
    }

    @NotNull
    public final Date component6() {
        return getTime();
    }

    @NotNull
    public final BaseLog.ThreadInfo component7() {
        return getThreadInfo();
    }

    @NotNull
    public final ActionEvent copy(@NotNull String action, @NotNull String sender, @NotNull String senderTitle, @NotNull String target, int i, @NotNull Date time, @NotNull BaseLog.ThreadInfo threadInfo) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        Intrinsics.checkParameterIsNotNull(sender, "sender");
        Intrinsics.checkParameterIsNotNull(senderTitle, "senderTitle");
        Intrinsics.checkParameterIsNotNull(target, "target");
        Intrinsics.checkParameterIsNotNull(time, "time");
        Intrinsics.checkParameterIsNotNull(threadInfo, "threadInfo");
        return new ActionEvent(action, sender, senderTitle, target, i, time, threadInfo);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof ActionEvent) {
                ActionEvent actionEvent = (ActionEvent) obj;
                if (Intrinsics.areEqual(this.f, actionEvent.f) && Intrinsics.areEqual(this.g, actionEvent.g) && Intrinsics.areEqual(this.h, actionEvent.h) && Intrinsics.areEqual(this.i, actionEvent.i)) {
                    if (!(getOrderId() == actionEvent.getOrderId()) || !Intrinsics.areEqual(getTime(), actionEvent.getTime()) || !Intrinsics.areEqual(getThreadInfo(), actionEvent.getThreadInfo())) {
                    }
                }
            }
            return false;
        }
        return true;
    }

    @NotNull
    public final String getAction() {
        return this.f;
    }

    @Override // io.shipbook.shipbooksdk.Models.BaseLog
    public int getOrderId() {
        return this.j;
    }

    @NotNull
    public final String getSender() {
        return this.g;
    }

    @NotNull
    public final String getSenderTitle() {
        return this.h;
    }

    @NotNull
    public final String getTarget() {
        return this.i;
    }

    @Override // io.shipbook.shipbooksdk.Models.BaseLog
    @NotNull
    public BaseLog.ThreadInfo getThreadInfo() {
        return this.l;
    }

    @Override // io.shipbook.shipbooksdk.Models.BaseLog
    @NotNull
    public Date getTime() {
        return this.k;
    }

    public int hashCode() {
        String str = this.f;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.g;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.h;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.i;
        int hashCode4 = (((hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31) + getOrderId()) * 31;
        Date time = getTime();
        int hashCode5 = (hashCode4 + (time != null ? time.hashCode() : 0)) * 31;
        BaseLog.ThreadInfo threadInfo = getThreadInfo();
        return hashCode5 + (threadInfo != null ? threadInfo.hashCode() : 0);
    }

    @Override // io.shipbook.shipbooksdk.Models.BaseLog
    public void setOrderId(int i) {
        this.j = i;
    }

    @Override // io.shipbook.shipbooksdk.Models.BaseLog, io.shipbook.shipbooksdk.Models.BaseObj
    @NotNull
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        json.put(Constants.KEY_ACTION, this.f);
        json.put(NotificationCompat.MessagingStyle.Message.KEY_SENDER, this.g);
        json.put("senderTitle", this.h);
        json.put(TypedValues.AttributesType.S_TARGET, this.i);
        return json;
    }

    @NotNull
    public String toString() {
        return "ActionEvent(action=" + this.f + ", sender=" + this.g + ", senderTitle=" + this.h + ", target=" + this.i + ", orderId=" + getOrderId() + ", time=" + getTime() + ", threadInfo=" + getThreadInfo() + ")";
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActionEvent(@NotNull String action, @NotNull String sender, @NotNull String senderTitle, @NotNull String target, int i, @NotNull Date time, @NotNull BaseLog.ThreadInfo threadInfo) {
        super("actionEvent");
        Intrinsics.checkParameterIsNotNull(action, "action");
        Intrinsics.checkParameterIsNotNull(sender, "sender");
        Intrinsics.checkParameterIsNotNull(senderTitle, "senderTitle");
        Intrinsics.checkParameterIsNotNull(target, "target");
        Intrinsics.checkParameterIsNotNull(time, "time");
        Intrinsics.checkParameterIsNotNull(threadInfo, "threadInfo");
        this.f = action;
        this.g = sender;
        this.h = senderTitle;
        this.i = target;
        this.j = i;
        this.k = time;
        this.l = threadInfo;
        setOrderId(incrementOrderId(getOrderId()));
    }
}
