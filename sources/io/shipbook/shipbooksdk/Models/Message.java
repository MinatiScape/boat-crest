package io.shipbook.shipbooksdk.Models;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.material.color.c;
import io.shipbook.shipbooksdk.Models.BaseLog;
import io.shipbook.shipbooksdk.ShipBook;
import io.shipbook.shipbooksdk.Util.ListStackTraceElementExtKt;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.m;
import org.eclipse.paho.android.service.MqttServiceConstants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwx.HeaderParameterNames;
import org.json.JSONArray;
import org.json.JSONObject;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b>\b\u0080\b\u0018\u0000 j2\u00020\u0001:\u0002jkB\u009b\u0001\u0012\u0006\u0010\u001b\u001a\u00020\u0004\u0012\u0006\u0010\u001c\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0006\u0012\u0010\b\u0002\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u0014\u0012\b\b\u0002\u0010%\u001a\u00020\u0010\u0012\b\b\u0002\u0010&\u001a\u00020\u0017\u0012\b\b\u0002\u0010'\u001a\u00020\u0019¢\u0006\u0004\bh\u0010iJ\b\u0010\u0003\u001a\u00020\u0002H\u0016J\t\u0010\u0005\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0007\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0011\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tHÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\fHÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u0010HÆ\u0003¢\u0006\u0004\b\u0011\u0010\u0012J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0014HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0010HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0017HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0019HÆ\u0003J¨\u0001\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u001b\u001a\u00020\u00042\b\b\u0002\u0010\u001c\u001a\u00020\u00062\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u00142\b\b\u0002\u0010%\u001a\u00020\u00102\b\b\u0002\u0010&\u001a\u00020\u00172\b\b\u0002\u0010'\u001a\u00020\u0019HÆ\u0001¢\u0006\u0004\b(\u0010)J\t\u0010*\u001a\u00020\u0006HÖ\u0001J\t\u0010+\u001a\u00020\u0010HÖ\u0001J\u0013\u0010/\u001a\u00020.2\b\u0010-\u001a\u0004\u0018\u00010,HÖ\u0003R\u0019\u0010\u001b\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b0\u00101\u001a\u0004\b2\u00103R\u0019\u0010\u001c\u001a\u00020\u00068\u0006@\u0006¢\u0006\f\n\u0004\b4\u00105\u001a\u0004\b6\u00107R$\u0010\u001d\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b8\u00105\u001a\u0004\b9\u00107\"\u0004\b:\u0010;R*\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b<\u0010=\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\u001b\u0010\u001f\u001a\u0004\u0018\u00010\f8\u0006@\u0006¢\u0006\f\n\u0004\bB\u0010C\u001a\u0004\bD\u0010ER$\u0010 \u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bF\u00105\u001a\u0004\bG\u00107\"\u0004\bH\u0010;R$\u0010!\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bI\u00105\u001a\u0004\bJ\u00107\"\u0004\bK\u0010;R$\u0010\"\u001a\u0004\u0018\u00010\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bL\u0010M\u001a\u0004\bN\u0010\u0012\"\u0004\bO\u0010PR$\u0010#\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bQ\u00105\u001a\u0004\bR\u00107\"\u0004\bS\u0010;R$\u0010$\u001a\u0004\u0018\u00010\u00148\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bT\u0010U\u001a\u0004\bV\u0010W\"\u0004\bX\u0010YR\"\u0010%\u001a\u00020\u00108\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\bZ\u0010[\u001a\u0004\b\\\u0010]\"\u0004\b^\u0010_R\u001c\u0010&\u001a\u00020\u00178\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b`\u0010a\u001a\u0004\bb\u0010cR\u001c\u0010'\u001a\u00020\u00198\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\bd\u0010e\u001a\u0004\bf\u0010g¨\u0006l"}, d2 = {"Lio/shipbook/shipbooksdk/Models/Message;", "Lio/shipbook/shipbooksdk/Models/BaseLog;", "Lorg/json/JSONObject;", "toJson", "Lio/shipbook/shipbooksdk/Models/Severity;", "component1", "", "component2", "component3", "", "Lio/shipbook/shipbooksdk/Models/StackTraceElement;", "component4", "", "component5", "component6", "component7", "", "component8", "()Ljava/lang/Integer;", "component9", "Lio/shipbook/shipbooksdk/Models/Message$MessageException;", "component10", "component11", "Ljava/util/Date;", "component12", "Lio/shipbook/shipbooksdk/Models/BaseLog$ThreadInfo;", "component13", "severity", Constants.KEY_MESSAGE, HeaderParameterNames.AUTHENTICATION_TAG, "stackTrace", "throwable", "function", "fileName", "lineNumber", "className", MqttServiceConstants.TRACE_EXCEPTION, "orderId", NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP, "threadInfo", Constants.COPY_TYPE, "(Lio/shipbook/shipbooksdk/Models/Severity;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lio/shipbook/shipbooksdk/Models/Message$MessageException;ILjava/util/Date;Lio/shipbook/shipbooksdk/Models/BaseLog$ThreadInfo;)Lio/shipbook/shipbooksdk/Models/Message;", "toString", "hashCode", "", FitnessActivities.OTHER, "", "equals", "f", "Lio/shipbook/shipbooksdk/Models/Severity;", "getSeverity", "()Lio/shipbook/shipbooksdk/Models/Severity;", "g", "Ljava/lang/String;", "getMessage", "()Ljava/lang/String;", "h", "getTag", "setTag", "(Ljava/lang/String;)V", "i", "Ljava/util/List;", "getStackTrace", "()Ljava/util/List;", "setStackTrace", "(Ljava/util/List;)V", "j", "Ljava/lang/Throwable;", "getThrowable", "()Ljava/lang/Throwable;", OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME, "getFunction", "setFunction", "l", "getFileName", "setFileName", "m", "Ljava/lang/Integer;", "getLineNumber", "setLineNumber", "(Ljava/lang/Integer;)V", "n", "getClassName", "setClassName", "o", "Lio/shipbook/shipbooksdk/Models/Message$MessageException;", "getException", "()Lio/shipbook/shipbooksdk/Models/Message$MessageException;", "setException", "(Lio/shipbook/shipbooksdk/Models/Message$MessageException;)V", RsaJsonWebKey.FIRST_PRIME_FACTOR_MEMBER_NAME, "I", "getOrderId", "()I", "setOrderId", "(I)V", RsaJsonWebKey.SECOND_PRIME_FACTOR_MEMBER_NAME, "Ljava/util/Date;", "getTime", "()Ljava/util/Date;", RsaJsonWebKey.PRIME_FACTOR_OTHER_MEMBER_NAME, "Lio/shipbook/shipbooksdk/Models/BaseLog$ThreadInfo;", "getThreadInfo", "()Lio/shipbook/shipbooksdk/Models/BaseLog$ThreadInfo;", "<init>", "(Lio/shipbook/shipbooksdk/Models/Severity;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lio/shipbook/shipbooksdk/Models/Message$MessageException;ILjava/util/Date;Lio/shipbook/shipbooksdk/Models/BaseLog$ThreadInfo;)V", "Companion", "MessageException", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final class Message extends BaseLog {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String s;
    @NotNull
    public static final List<String> t;
    @NotNull
    public final Severity f;
    @NotNull
    public final String g;
    @Nullable
    public String h;
    @Nullable
    public List<StackTraceElement> i;
    @Nullable
    public final Throwable j;
    @Nullable
    public String k;
    @Nullable
    public String l;
    @Nullable
    public Integer m;
    @Nullable
    public String n;
    @Nullable
    public MessageException o;
    public int p;
    @NotNull
    public final Date q;
    @NotNull
    public final BaseLog.ThreadInfo r;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ&\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bJ\u000e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\fR\u0019\u0010\u0010\u001a\u00020\f8\u0006@\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\f0\u00148\u0006@\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001b"}, d2 = {"Lio/shipbook/shipbooksdk/Models/Message$Companion;", "", "Lorg/json/JSONObject;", "json", "", "orderId", "Ljava/util/Date;", NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP, "Lio/shipbook/shipbooksdk/Models/BaseLog$ThreadInfo;", "threadInfo", "Lio/shipbook/shipbooksdk/Models/Message;", "create", "", AppMeasurementSdk.ConditionalUserProperty.NAME, "", "addIgnoreClass", "shipbookClassname", "Ljava/lang/String;", "getShipbookClassname", "()Ljava/lang/String;", "", "ignoreClasses", "Ljava/util/List;", "getIgnoreClasses", "()Ljava/util/List;", "<init>", "()V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        public final void addIgnoreClass(@NotNull String name) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            getIgnoreClasses().add(name);
        }

        @NotNull
        public final Message create(@NotNull JSONObject json, int i, @NotNull Date time, @NotNull BaseLog.ThreadInfo threadInfo) {
            MessageException messageException;
            Intrinsics.checkParameterIsNotNull(json, "json");
            Intrinsics.checkParameterIsNotNull(time, "time");
            Intrinsics.checkParameterIsNotNull(threadInfo, "threadInfo");
            String string = json.getString(HeaderParameterNames.AUTHENTICATION_TAG);
            String string2 = json.getString("severity");
            Intrinsics.checkExpressionValueIsNotNull(string2, "json.getString(\"severity\")");
            Severity valueOf = Severity.valueOf(string2);
            String message = json.getString(Constants.KEY_MESSAGE);
            JSONArray optJSONArray = json.optJSONArray("stackTrace");
            List<StackTraceElement> listStackTraceElement = optJSONArray != null ? ListStackTraceElementExtKt.toListStackTraceElement(optJSONArray) : null;
            if (json.has(MqttServiceConstants.TRACE_EXCEPTION)) {
                MessageException.Companion companion = MessageException.Companion;
                JSONObject optJSONObject = json.optJSONObject(MqttServiceConstants.TRACE_EXCEPTION);
                Intrinsics.checkExpressionValueIsNotNull(optJSONObject, "json.optJSONObject(\"exception\")");
                messageException = companion.create(optJSONObject);
            } else {
                messageException = null;
            }
            String optString = json.optString("function");
            String optString2 = json.optString("fileName");
            int optInt = json.optInt("lineNumber");
            String optString3 = json.optString("className");
            Intrinsics.checkExpressionValueIsNotNull(message, "message");
            return new Message(valueOf, message, string, listStackTraceElement, null, optString, optString2, Integer.valueOf(optInt), optString3, messageException, i, time, threadInfo);
        }

        @NotNull
        public final List<String> getIgnoreClasses() {
            return Message.t;
        }

        @NotNull
        public final String getShipbookClassname() {
            return Message.s;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\b\u0086\b\u0018\u0000 !2\u00020\u0001:\u0001!B)\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\u001f\u0010 J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\u000b\u0010\u0005\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u0006\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J1\u0010\r\u001a\u00020\u00002\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00042\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0001J\t\u0010\u000e\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0010\u001a\u00020\u000fHÖ\u0001J\u0013\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011HÖ\u0003R\u001b\u0010\n\u001a\u0004\u0018\u00010\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001b\u0010\u000b\u001a\u0004\u0018\u00010\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u0016\u001a\u0004\b\u001a\u0010\u0018R\u001f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006@\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e¨\u0006\""}, d2 = {"Lio/shipbook/shipbooksdk/Models/Message$MessageException;", "Lio/shipbook/shipbooksdk/Models/BaseObj;", "Lorg/json/JSONObject;", "toJson", "", "component1", "component2", "", "Lio/shipbook/shipbooksdk/Models/StackTraceElement;", "component3", AppMeasurementSdk.ConditionalUserProperty.NAME, "reason", "stackTrace", Constants.COPY_TYPE, "toString", "", "hashCode", "", FitnessActivities.OTHER, "", "equals", "a", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "b", "getReason", c.f10260a, "Ljava/util/List;", "getStackTrace", "()Ljava/util/List;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "Companion", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes12.dex */
    public static final class MessageException implements BaseObj {
        public static final Companion Companion = new Companion(null);
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public final String f14034a;
        @Nullable
        public final String b;
        @NotNull
        public final List<StackTraceElement> c;

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\b"}, d2 = {"Lio/shipbook/shipbooksdk/Models/Message$MessageException$Companion;", "", "Lorg/json/JSONObject;", "json", "Lio/shipbook/shipbooksdk/Models/Message$MessageException;", "create", "<init>", "()V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
        /* loaded from: classes12.dex */
        public static final class Companion {
            public Companion() {
            }

            @Nullable
            public final MessageException create(@NotNull JSONObject json) {
                Intrinsics.checkParameterIsNotNull(json, "json");
                String optString = json.optString(AppMeasurementSdk.ConditionalUserProperty.NAME);
                String optString2 = json.optString("reason");
                JSONArray jSONArray = json.getJSONArray("stackTrace");
                Intrinsics.checkExpressionValueIsNotNull(jSONArray, "json.getJSONArray(\"stackTrace\")");
                return new MessageException(optString, optString2, ListStackTraceElementExtKt.toListStackTraceElement(jSONArray));
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        public MessageException(@Nullable String str, @Nullable String str2, @NotNull List<StackTraceElement> stackTrace) {
            Intrinsics.checkParameterIsNotNull(stackTrace, "stackTrace");
            this.f14034a = str;
            this.b = str2;
            this.c = stackTrace;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @NotNull
        public static /* synthetic */ MessageException copy$default(MessageException messageException, String str, String str2, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                str = messageException.f14034a;
            }
            if ((i & 2) != 0) {
                str2 = messageException.b;
            }
            if ((i & 4) != 0) {
                list = messageException.c;
            }
            return messageException.copy(str, str2, list);
        }

        @Nullable
        public final String component1() {
            return this.f14034a;
        }

        @Nullable
        public final String component2() {
            return this.b;
        }

        @NotNull
        public final List<StackTraceElement> component3() {
            return this.c;
        }

        @NotNull
        public final MessageException copy(@Nullable String str, @Nullable String str2, @NotNull List<StackTraceElement> stackTrace) {
            Intrinsics.checkParameterIsNotNull(stackTrace, "stackTrace");
            return new MessageException(str, str2, stackTrace);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (obj instanceof MessageException) {
                    MessageException messageException = (MessageException) obj;
                    return Intrinsics.areEqual(this.f14034a, messageException.f14034a) && Intrinsics.areEqual(this.b, messageException.b) && Intrinsics.areEqual(this.c, messageException.c);
                }
                return false;
            }
            return true;
        }

        @Nullable
        public final String getName() {
            return this.f14034a;
        }

        @Nullable
        public final String getReason() {
            return this.b;
        }

        @NotNull
        public final List<StackTraceElement> getStackTrace() {
            return this.c;
        }

        public int hashCode() {
            String str = this.f14034a;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.b;
            int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            List<StackTraceElement> list = this.c;
            return hashCode2 + (list != null ? list.hashCode() : 0);
        }

        @Override // io.shipbook.shipbooksdk.Models.BaseObj
        @NotNull
        public JSONObject toJson() {
            JSONObject jSONObject = new JSONObject();
            jSONObject.putOpt(AppMeasurementSdk.ConditionalUserProperty.NAME, this.f14034a);
            jSONObject.putOpt("reason", this.b);
            jSONObject.put("stackTrace", ListStackTraceElementExtKt.toJson(this.c));
            return jSONObject;
        }

        @NotNull
        public String toString() {
            return "MessageException(name=" + this.f14034a + ", reason=" + this.b + ", stackTrace=" + this.c + ")";
        }
    }

    static {
        String name = ShipBook.class.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "ShipBook::class.java.name");
        String substringBeforeLast$default = StringsKt__StringsKt.substringBeforeLast$default(name, ".", (String) null, 2, (Object) null);
        s = substringBeforeLast$default;
        t = CollectionsKt__CollectionsKt.mutableListOf(substringBeforeLast$default);
    }

    public /* synthetic */ Message(Severity severity, String str, String str2, List list, Throwable th, String str3, String str4, Integer num, String str5, MessageException messageException, int i, Date date, BaseLog.ThreadInfo threadInfo, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(severity, str, (i2 & 4) != 0 ? null : str2, (i2 & 8) != 0 ? null : list, (i2 & 16) != 0 ? null : th, (i2 & 32) != 0 ? null : str3, (i2 & 64) != 0 ? null : str4, (i2 & 128) != 0 ? null : num, (i2 & 256) != 0 ? null : str5, (i2 & 512) != 0 ? null : messageException, (i2 & 1024) != 0 ? 0 : i, (i2 & 2048) != 0 ? new Date() : date, (i2 & 4096) != 0 ? new BaseLog.ThreadInfo(null, 0L, false, 7, null) : threadInfo);
    }

    @NotNull
    public final Severity component1() {
        return this.f;
    }

    @Nullable
    public final MessageException component10() {
        return this.o;
    }

    public final int component11() {
        return getOrderId();
    }

    @NotNull
    public final Date component12() {
        return getTime();
    }

    @NotNull
    public final BaseLog.ThreadInfo component13() {
        return getThreadInfo();
    }

    @NotNull
    public final String component2() {
        return this.g;
    }

    @Nullable
    public final String component3() {
        return this.h;
    }

    @Nullable
    public final List<StackTraceElement> component4() {
        return this.i;
    }

    @Nullable
    public final Throwable component5() {
        return this.j;
    }

    @Nullable
    public final String component6() {
        return this.k;
    }

    @Nullable
    public final String component7() {
        return this.l;
    }

    @Nullable
    public final Integer component8() {
        return this.m;
    }

    @Nullable
    public final String component9() {
        return this.n;
    }

    @NotNull
    public final Message copy(@NotNull Severity severity, @NotNull String message, @Nullable String str, @Nullable List<StackTraceElement> list, @Nullable Throwable th, @Nullable String str2, @Nullable String str3, @Nullable Integer num, @Nullable String str4, @Nullable MessageException messageException, int i, @NotNull Date time, @NotNull BaseLog.ThreadInfo threadInfo) {
        Intrinsics.checkParameterIsNotNull(severity, "severity");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Intrinsics.checkParameterIsNotNull(time, "time");
        Intrinsics.checkParameterIsNotNull(threadInfo, "threadInfo");
        return new Message(severity, message, str, list, th, str2, str3, num, str4, messageException, i, time, threadInfo);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof Message) {
                Message message = (Message) obj;
                if (Intrinsics.areEqual(this.f, message.f) && Intrinsics.areEqual(this.g, message.g) && Intrinsics.areEqual(this.h, message.h) && Intrinsics.areEqual(this.i, message.i) && Intrinsics.areEqual(this.j, message.j) && Intrinsics.areEqual(this.k, message.k) && Intrinsics.areEqual(this.l, message.l) && Intrinsics.areEqual(this.m, message.m) && Intrinsics.areEqual(this.n, message.n) && Intrinsics.areEqual(this.o, message.o)) {
                    if (!(getOrderId() == message.getOrderId()) || !Intrinsics.areEqual(getTime(), message.getTime()) || !Intrinsics.areEqual(getThreadInfo(), message.getThreadInfo())) {
                    }
                }
            }
            return false;
        }
        return true;
    }

    @Nullable
    public final String getClassName() {
        return this.n;
    }

    @Nullable
    public final MessageException getException() {
        return this.o;
    }

    @Nullable
    public final String getFileName() {
        return this.l;
    }

    @Nullable
    public final String getFunction() {
        return this.k;
    }

    @Nullable
    public final Integer getLineNumber() {
        return this.m;
    }

    @NotNull
    public final String getMessage() {
        return this.g;
    }

    @Override // io.shipbook.shipbooksdk.Models.BaseLog
    public int getOrderId() {
        return this.p;
    }

    @NotNull
    public final Severity getSeverity() {
        return this.f;
    }

    @Nullable
    public final List<StackTraceElement> getStackTrace() {
        return this.i;
    }

    @Nullable
    public final String getTag() {
        return this.h;
    }

    @Override // io.shipbook.shipbooksdk.Models.BaseLog
    @NotNull
    public BaseLog.ThreadInfo getThreadInfo() {
        return this.r;
    }

    @Nullable
    public final Throwable getThrowable() {
        return this.j;
    }

    @Override // io.shipbook.shipbooksdk.Models.BaseLog
    @NotNull
    public Date getTime() {
        return this.q;
    }

    public int hashCode() {
        Severity severity = this.f;
        int hashCode = (severity != null ? severity.hashCode() : 0) * 31;
        String str = this.g;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.h;
        int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        List<StackTraceElement> list = this.i;
        int hashCode4 = (hashCode3 + (list != null ? list.hashCode() : 0)) * 31;
        Throwable th = this.j;
        int hashCode5 = (hashCode4 + (th != null ? th.hashCode() : 0)) * 31;
        String str3 = this.k;
        int hashCode6 = (hashCode5 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.l;
        int hashCode7 = (hashCode6 + (str4 != null ? str4.hashCode() : 0)) * 31;
        Integer num = this.m;
        int hashCode8 = (hashCode7 + (num != null ? num.hashCode() : 0)) * 31;
        String str5 = this.n;
        int hashCode9 = (hashCode8 + (str5 != null ? str5.hashCode() : 0)) * 31;
        MessageException messageException = this.o;
        int hashCode10 = (((hashCode9 + (messageException != null ? messageException.hashCode() : 0)) * 31) + getOrderId()) * 31;
        Date time = getTime();
        int hashCode11 = (hashCode10 + (time != null ? time.hashCode() : 0)) * 31;
        BaseLog.ThreadInfo threadInfo = getThreadInfo();
        return hashCode11 + (threadInfo != null ? threadInfo.hashCode() : 0);
    }

    public final void setClassName(@Nullable String str) {
        this.n = str;
    }

    public final void setException(@Nullable MessageException messageException) {
        this.o = messageException;
    }

    public final void setFileName(@Nullable String str) {
        this.l = str;
    }

    public final void setFunction(@Nullable String str) {
        this.k = str;
    }

    public final void setLineNumber(@Nullable Integer num) {
        this.m = num;
    }

    @Override // io.shipbook.shipbooksdk.Models.BaseLog
    public void setOrderId(int i) {
        this.p = i;
    }

    public final void setStackTrace(@Nullable List<StackTraceElement> list) {
        this.i = list;
    }

    public final void setTag(@Nullable String str) {
        this.h = str;
    }

    @Override // io.shipbook.shipbooksdk.Models.BaseLog, io.shipbook.shipbooksdk.Models.BaseObj
    @NotNull
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        json.put(HeaderParameterNames.AUTHENTICATION_TAG, this.h);
        json.put("severity", this.f);
        json.put(Constants.KEY_MESSAGE, this.g);
        MessageException messageException = this.o;
        json.putOpt(MqttServiceConstants.TRACE_EXCEPTION, messageException != null ? messageException.toJson() : null);
        List<StackTraceElement> list = this.i;
        json.putOpt("stackTrace", list != null ? ListStackTraceElementExtKt.toJson(list) : null);
        json.put("function", this.k);
        json.put("fileName", this.l);
        json.put("lineNumber", this.m);
        json.put("className", this.n);
        return json;
    }

    @NotNull
    public String toString() {
        return "Message(severity=" + this.f + ", message=" + this.g + ", tag=" + this.h + ", stackTrace=" + this.i + ", throwable=" + this.j + ", function=" + this.k + ", fileName=" + this.l + ", lineNumber=" + this.m + ", className=" + this.n + ", exception=" + this.o + ", orderId=" + getOrderId() + ", time=" + getTime() + ", threadInfo=" + getThreadInfo() + ")";
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Message(@NotNull Severity severity, @NotNull String message, @Nullable String str, @Nullable List<StackTraceElement> list, @Nullable Throwable th, @Nullable String str2, @Nullable String str3, @Nullable Integer num, @Nullable String str4, @Nullable MessageException messageException, int i, @NotNull Date time, @NotNull BaseLog.ThreadInfo threadInfo) {
        super(Constants.KEY_MESSAGE, 0, null, null, 14, null);
        java.lang.StackTraceElement trace;
        Object obj;
        Intrinsics.checkParameterIsNotNull(severity, "severity");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Intrinsics.checkParameterIsNotNull(time, "time");
        Intrinsics.checkParameterIsNotNull(threadInfo, "threadInfo");
        this.f = severity;
        this.g = message;
        this.h = str;
        this.i = list;
        this.j = th;
        this.k = str2;
        this.l = str3;
        this.m = num;
        this.n = str4;
        this.o = messageException;
        this.p = i;
        this.q = time;
        this.r = threadInfo;
        setOrderId(incrementOrderId(getOrderId()));
        if (this.l == null) {
            java.lang.StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkExpressionValueIsNotNull(stackTrace, "Throwable().stackTrace");
            int length = stackTrace.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    trace = null;
                    break;
                }
                trace = stackTrace[i2];
                Iterator<T> it = t.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    }
                    obj = it.next();
                    Intrinsics.checkExpressionValueIsNotNull(trace, "trace");
                    String className = trace.getClassName();
                    Intrinsics.checkExpressionValueIsNotNull(className, "trace.className");
                    if (m.startsWith$default(className, (String) obj, false, 2, null)) {
                        break;
                    }
                }
                if (obj == null) {
                    break;
                }
                i2++;
            }
            this.k = trace != null ? trace.getMethodName() : null;
            this.l = trace != null ? trace.getFileName() : null;
            this.m = trace != null ? Integer.valueOf(trace.getLineNumber()) : null;
            this.n = trace != null ? trace.getClassName() : null;
        }
        if (this.h == null) {
            String str5 = this.n;
            this.h = str5 != null ? StringsKt__StringsKt.substringAfterLast$default(str5, '.', (String) null, 2, (Object) null) : null;
        }
        Throwable th2 = this.j;
        if (th2 != null) {
            java.lang.StackTraceElement[] stackTrace2 = th2.getStackTrace();
            Intrinsics.checkExpressionValueIsNotNull(stackTrace2, "throwable.stackTrace");
            this.o = new MessageException(this.j.getClass().getName(), this.j.getMessage(), ListStackTraceElementExtKt.toInternal(stackTrace2));
        }
    }
}
