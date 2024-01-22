package io.shipbook.shipbooksdk.Models;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.material.color.c;
import io.shipbook.shipbooksdk.Util.DateHelper;
import io.shipbook.shipbooksdk.Util.DateHelperKt;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.paho.android.service.MqttServiceConstants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\b \u0018\u0000 !2\u00020\u0001:\u0002!\"B-\u0012\u0006\u0010\f\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u0019¢\u0006\u0004\b\u001f\u0010 J\u000e\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002J\b\u0010\u0006\u001a\u00020\u0005H\u0016R\u0019\u0010\f\u001a\u00020\u00078\u0006@\u0006¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\"\u0010\u0003\u001a\u00020\u00028\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0018\u001a\u00020\u00138\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u001c\u0010\u001e\u001a\u00020\u00198\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d¨\u0006#"}, d2 = {"Lio/shipbook/shipbooksdk/Models/BaseLog;", "Lio/shipbook/shipbooksdk/Models/BaseObj;", "", "orderId", "incrementOrderId", "Lorg/json/JSONObject;", "toJson", "", "a", "Ljava/lang/String;", "getType", "()Ljava/lang/String;", "type", "b", "I", "getOrderId", "()I", "setOrderId", "(I)V", "Ljava/util/Date;", c.f10260a, "Ljava/util/Date;", "getTime", "()Ljava/util/Date;", NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP, "Lio/shipbook/shipbooksdk/Models/BaseLog$ThreadInfo;", "d", "Lio/shipbook/shipbooksdk/Models/BaseLog$ThreadInfo;", "getThreadInfo", "()Lio/shipbook/shipbooksdk/Models/BaseLog$ThreadInfo;", "threadInfo", "<init>", "(Ljava/lang/String;ILjava/util/Date;Lio/shipbook/shipbooksdk/Models/BaseLog$ThreadInfo;)V", "Companion", "ThreadInfo", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public abstract class BaseLog implements BaseObj {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static AtomicInteger e = new AtomicInteger(0);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f14026a;
    public int b;
    @NotNull
    public final Date c;
    @NotNull
    public final ThreadInfo d;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002R\"\u0010\u0007\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lio/shipbook/shipbooksdk/Models/BaseLog$Companion;", "", "Lorg/json/JSONObject;", "json", "Lio/shipbook/shipbooksdk/Models/BaseLog;", "create", "Ljava/util/concurrent/atomic/AtomicInteger;", "count", "Ljava/util/concurrent/atomic/AtomicInteger;", "getCount", "()Ljava/util/concurrent/atomic/AtomicInteger;", "setCount", "(Ljava/util/concurrent/atomic/AtomicInteger;)V", "<init>", "()V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final BaseLog create(@NotNull JSONObject json) {
            Intrinsics.checkParameterIsNotNull(json, "json");
            String string = json.getString("type");
            int i = json.getInt("orderId");
            DateHelper dateHelper = DateHelper.INSTANCE;
            String string2 = json.getString(NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP);
            Intrinsics.checkExpressionValueIsNotNull(string2, "json.getString(\"time\")");
            Date createDateStandard = dateHelper.createDateStandard(string2);
            if (createDateStandard == null) {
                Intrinsics.throwNpe();
            }
            ThreadInfo.Companion companion = ThreadInfo.Companion;
            JSONObject jSONObject = json.getJSONObject("threadInfo");
            Intrinsics.checkExpressionValueIsNotNull(jSONObject, "json.getJSONObject(\"threadInfo\")");
            ThreadInfo create = companion.create(jSONObject);
            if (string != null) {
                switch (string.hashCode()) {
                    case -1060101941:
                        if (string.equals("activityEvent")) {
                            return ActivityEvent.Companion.create(json, i, createDateStandard, create);
                        }
                        break;
                    case -882798038:
                        if (string.equals("fragmentEvent")) {
                            return FragmentEvent.Companion.create(json, i, createDateStandard, create);
                        }
                        break;
                    case -67033714:
                        if (string.equals("screenEvent")) {
                            return ScreenEvent.Companion.create(json, i, createDateStandard, create);
                        }
                        break;
                    case -6440840:
                        if (string.equals("configEvent")) {
                            return ConfigEvent.Companion.create(json, i, createDateStandard, create);
                        }
                        break;
                    case 954925063:
                        if (string.equals(Constants.KEY_MESSAGE)) {
                            return Message.Companion.create(json, i, createDateStandard, create);
                        }
                        break;
                    case 1481625679:
                        if (string.equals(MqttServiceConstants.TRACE_EXCEPTION)) {
                            return Exception.Companion.create(json, i, createDateStandard, create);
                        }
                        break;
                    case 1559787012:
                        if (string.equals("actionEvent")) {
                            return ActionEvent.Companion.create(json, i, createDateStandard, create);
                        }
                        break;
                }
            }
            throw new Error("There doesn't exist this type");
        }

        @NotNull
        public final AtomicInteger getCount() {
            return BaseLog.e;
        }

        public final void setCount(@NotNull AtomicInteger atomicInteger) {
            Intrinsics.checkParameterIsNotNull(atomicInteger, "<set-?>");
            BaseLog.e = atomicInteger;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0017\b\u0086\b\u0018\u0000 '2\u00020\u0001:\u0001'B%\u0012\b\b\u0002\u0010\n\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0006\u0012\b\b\u0002\u0010\f\u001a\u00020\b¢\u0006\u0004\b%\u0010&J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\t\u0010\u0005\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0007\u001a\u00020\u0006HÆ\u0003J\t\u0010\t\u001a\u00020\bHÆ\u0003J'\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\bHÆ\u0001J\t\u0010\u000e\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0010\u001a\u00020\u000fHÖ\u0001J\u0013\u0010\u0013\u001a\u00020\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011HÖ\u0003R\"\u0010\n\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\"\u0010\u000b\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\"\u0010\f\u001a\u00020\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b \u0010!\u001a\u0004\b\f\u0010\"\"\u0004\b#\u0010$¨\u0006("}, d2 = {"Lio/shipbook/shipbooksdk/Models/BaseLog$ThreadInfo;", "Lio/shipbook/shipbooksdk/Models/BaseObj;", "Lorg/json/JSONObject;", "toJson", "", "component1", "", "component2", "", "component3", "threadName", "threadId", "isMain", Constants.COPY_TYPE, "toString", "", "hashCode", "", FitnessActivities.OTHER, "equals", "a", "Ljava/lang/String;", "getThreadName", "()Ljava/lang/String;", "setThreadName", "(Ljava/lang/String;)V", "b", "J", "getThreadId", "()J", "setThreadId", "(J)V", c.f10260a, "Z", "()Z", "setMain", "(Z)V", "<init>", "(Ljava/lang/String;JZ)V", "Companion", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes12.dex */
    public static final class ThreadInfo implements BaseObj {
        public static final Companion Companion = new Companion(null);
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public String f14027a;
        public long b;
        public boolean c;

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\b"}, d2 = {"Lio/shipbook/shipbooksdk/Models/BaseLog$ThreadInfo$Companion;", "", "Lorg/json/JSONObject;", "json", "Lio/shipbook/shipbooksdk/Models/BaseLog$ThreadInfo;", "create", "<init>", "()V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
        /* loaded from: classes12.dex */
        public static final class Companion {
            public Companion() {
            }

            @NotNull
            public final ThreadInfo create(@NotNull JSONObject json) {
                Intrinsics.checkParameterIsNotNull(json, "json");
                String threadName = json.getString("threadName");
                long j = json.getLong("threadId");
                boolean z = json.getBoolean("isMain");
                Intrinsics.checkExpressionValueIsNotNull(threadName, "threadName");
                return new ThreadInfo(threadName, j, z);
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        public ThreadInfo() {
            this(null, 0L, false, 7, null);
        }

        public ThreadInfo(@NotNull String threadName, long j, boolean z) {
            Intrinsics.checkParameterIsNotNull(threadName, "threadName");
            this.f14027a = threadName;
            this.b = j;
            this.c = z;
        }

        @NotNull
        public static /* synthetic */ ThreadInfo copy$default(ThreadInfo threadInfo, String str, long j, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                str = threadInfo.f14027a;
            }
            if ((i & 2) != 0) {
                j = threadInfo.b;
            }
            if ((i & 4) != 0) {
                z = threadInfo.c;
            }
            return threadInfo.copy(str, j, z);
        }

        @NotNull
        public final String component1() {
            return this.f14027a;
        }

        public final long component2() {
            return this.b;
        }

        public final boolean component3() {
            return this.c;
        }

        @NotNull
        public final ThreadInfo copy(@NotNull String threadName, long j, boolean z) {
            Intrinsics.checkParameterIsNotNull(threadName, "threadName");
            return new ThreadInfo(threadName, j, z);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (obj instanceof ThreadInfo) {
                    ThreadInfo threadInfo = (ThreadInfo) obj;
                    if (Intrinsics.areEqual(this.f14027a, threadInfo.f14027a)) {
                        if (this.b == threadInfo.b) {
                            if (this.c == threadInfo.c) {
                            }
                        }
                    }
                }
                return false;
            }
            return true;
        }

        public final long getThreadId() {
            return this.b;
        }

        @NotNull
        public final String getThreadName() {
            return this.f14027a;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            String str = this.f14027a;
            int hashCode = str != null ? str.hashCode() : 0;
            long j = this.b;
            int i = ((hashCode * 31) + ((int) (j ^ (j >>> 32)))) * 31;
            boolean z = this.c;
            int i2 = z;
            if (z != 0) {
                i2 = 1;
            }
            return i + i2;
        }

        public final boolean isMain() {
            return this.c;
        }

        public final void setMain(boolean z) {
            this.c = z;
        }

        public final void setThreadId(long j) {
            this.b = j;
        }

        public final void setThreadName(@NotNull String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.f14027a = str;
        }

        @Override // io.shipbook.shipbooksdk.Models.BaseObj
        @NotNull
        public JSONObject toJson() {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("threadName", this.f14027a);
            jSONObject.put("threadId", this.b);
            jSONObject.put("isMain", this.c);
            return jSONObject;
        }

        @NotNull
        public String toString() {
            return "ThreadInfo(threadName=" + this.f14027a + ", threadId=" + this.b + ", isMain=" + this.c + ")";
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public /* synthetic */ ThreadInfo(java.lang.String r2, long r3, boolean r5, int r6, kotlin.jvm.internal.DefaultConstructorMarker r7) {
            /*
                r1 = this;
                r7 = r6 & 1
                java.lang.String r0 = "Thread.currentThread()"
                if (r7 == 0) goto L16
                java.lang.Thread r2 = java.lang.Thread.currentThread()
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r0)
                java.lang.String r2 = r2.getName()
                java.lang.String r7 = "Thread.currentThread().name"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r7)
            L16:
                r7 = r6 & 2
                if (r7 == 0) goto L25
                java.lang.Thread r3 = java.lang.Thread.currentThread()
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r0)
                long r3 = r3.getId()
            L25:
                r6 = r6 & 4
                if (r6 == 0) goto L35
                android.os.Looper r5 = android.os.Looper.myLooper()
                android.os.Looper r6 = android.os.Looper.getMainLooper()
                boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r6)
            L35:
                r1.<init>(r2, r3, r5)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.shipbook.shipbooksdk.Models.BaseLog.ThreadInfo.<init>(java.lang.String, long, boolean, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
        }
    }

    public BaseLog(@NotNull String type, int i, @NotNull Date time, @NotNull ThreadInfo threadInfo) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(time, "time");
        Intrinsics.checkParameterIsNotNull(threadInfo, "threadInfo");
        this.f14026a = type;
        this.b = i;
        this.c = time;
        this.d = threadInfo;
    }

    public int getOrderId() {
        return this.b;
    }

    @NotNull
    public ThreadInfo getThreadInfo() {
        return this.d;
    }

    @NotNull
    public Date getTime() {
        return this.c;
    }

    @NotNull
    public final String getType() {
        return this.f14026a;
    }

    public final int incrementOrderId(int i) {
        return i == 0 ? e.incrementAndGet() : i;
    }

    public void setOrderId(int i) {
        this.b = i;
    }

    @Override // io.shipbook.shipbooksdk.Models.BaseObj
    @NotNull
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", this.f14026a);
        jSONObject.put("orderId", getOrderId());
        jSONObject.put(NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP, DateHelperKt.toStandardString(getTime()));
        jSONObject.put("threadInfo", getThreadInfo().toJson());
        return jSONObject;
    }

    public /* synthetic */ BaseLog(String str, int i, Date date, ThreadInfo threadInfo, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? 0 : i, (i2 & 4) != 0 ? new Date() : date, (i2 & 8) != 0 ? new ThreadInfo(null, 0L, false, 7, null) : threadInfo);
    }
}
