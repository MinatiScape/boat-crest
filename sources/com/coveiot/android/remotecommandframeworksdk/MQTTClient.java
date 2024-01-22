package com.coveiot.android.remotecommandframeworksdk;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import com.coveiot.android.remotecommandframeworksdk.utils.MQTTConnectionParams;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.material.color.c;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.cms.CMSAttributeTableGenerator;
import org.eclipse.paho.android.service.MqttServiceConstants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002:\u0001QB\u000f\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\bO\u0010PJ\u000f\u0010\u0004\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u0004\u0010\u0005J)\u0010\r\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0019\u0010\u000f\u001a\u00020\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J1\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00112\b\u0010\u0016\u001a\u0004\u0018\u00010\nH\u0016¢\u0006\u0004\b\u0017\u0010\u0018J!\u0010\u0019\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00112\b\u0010\u0016\u001a\u0004\u0018\u00010\nH\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ/\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ/\u0010\u001f\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u001f\u0010\u001dJ/\u0010 \u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0011H\u0016¢\u0006\u0004\b \u0010\u001dJ/\u0010!\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0011H\u0016¢\u0006\u0004\b!\u0010\u001dJ\r\u0010\"\u001a\u00020\f¢\u0006\u0004\b\"\u0010#J\u0015\u0010&\u001a\u00020\f2\u0006\u0010%\u001a\u00020$¢\u0006\u0004\b&\u0010'J\u0015\u0010(\u001a\u00020\f2\u0006\u0010%\u001a\u00020$¢\u0006\u0004\b(\u0010'J%\u0010*\u001a\u00020\f2\u0006\u0010)\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0011¢\u0006\u0004\b*\u0010+J\u0015\u0010,\u001a\u00020\f2\u0006\u0010)\u001a\u00020\u0011¢\u0006\u0004\b,\u0010-J\r\u0010.\u001a\u00020\f¢\u0006\u0004\b.\u0010#J\u001f\u00102\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001100\u0012\u0004\u0012\u0002010/¢\u0006\u0004\b2\u00103J\u0017\u00106\u001a\u0004\u0018\u0001052\u0006\u00104\u001a\u00020\u0011¢\u0006\u0004\b6\u00107R\u001c\u0010<\u001a\u00020\u00118\u0016@\u0016X\u0096D¢\u0006\f\n\u0004\b8\u00109\u001a\u0004\b:\u0010;R\"\u0010B\u001a\b\u0012\u0004\u0012\u00020$0=8\u0004@\u0004X\u0084\u0004¢\u0006\f\n\u0004\b>\u0010?\u001a\u0004\b@\u0010AR\"\u0010J\u001a\u00020C8\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\bD\u0010E\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR\u0019\u0010\u0007\u001a\u00020\u00068\u0006@\u0006¢\u0006\f\n\u0004\bK\u0010L\u001a\u0004\bM\u0010N¨\u0006R"}, d2 = {"Lcom/coveiot/android/remotecommandframeworksdk/MQTTClient;", "Lcom/coveiot/android/remotecommandframeworksdk/IPubSubAction;", "Lcom/coveiot/android/remotecommandframeworksdk/IResponseObserver;", "", "isConnected", "()Z", "Landroid/content/Context;", "context", "Lcom/coveiot/android/remotecommandframeworksdk/utils/MQTTConnectionParams;", "params", "Lcom/coveiot/android/remotecommandframeworksdk/listener/SuccessListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "", MqttServiceConstants.CONNECT_ACTION, "(Landroid/content/Context;Lcom/coveiot/android/remotecommandframeworksdk/utils/MQTTConnectionParams;Lcom/coveiot/android/remotecommandframeworksdk/listener/SuccessListener;)V", MqttServiceConstants.DISCONNECT_ACTION, "(Lcom/coveiot/android/remotecommandframeworksdk/listener/SuccessListener;)V", "", "topic", "", MqttServiceConstants.QOS, CMSAttributeTableGenerator.CONTENT_TYPE, "successListener", MqttServiceConstants.SUBSCRIBE_ACTION, "(Ljava/lang/String;ILjava/lang/String;Lcom/coveiot/android/remotecommandframeworksdk/listener/SuccessListener;)V", MqttServiceConstants.UNSUBSCRIBE_ACTION, "(Ljava/lang/String;Lcom/coveiot/android/remotecommandframeworksdk/listener/SuccessListener;)V", Constants.KEY_MESSAGE, "publish", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V", "msg", "onSendResponseAck", "onSendResponse", "onResetRetainFlag", "syncCompleted", "()V", "Lcom/coveiot/android/remotecommandframeworksdk/IObserver;", "observer", "addObserver", "(Lcom/coveiot/android/remotecommandframeworksdk/IObserver;)V", "removeObserver", AppMeasurementSdk.ConditionalUserProperty.NAME, "addTopic", "(Ljava/lang/String;ILjava/lang/String;)V", "removeTopic", "(Ljava/lang/String;)V", "clearAllTopic", "Lkotlin/Pair;", "", "", "getAllSubscription", "()Lkotlin/Pair;", "topicName", "Lcom/coveiot/android/remotecommandframeworksdk/MQTTClient$Topic;", "getSubscriptionBy", "(Ljava/lang/String;)Lcom/coveiot/android/remotecommandframeworksdk/MQTTClient$Topic;", "h", "Ljava/lang/String;", "getTAG", "()Ljava/lang/String;", "TAG", "", "i", "Ljava/util/List;", "getObservers", "()Ljava/util/List;", "observers", "Landroid/os/Handler;", OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME, "Landroid/os/Handler;", "getMHandler", "()Landroid/os/Handler;", "setMHandler", "(Landroid/os/Handler;)V", "mHandler", "m", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "Topic", "remotecommandframeworksdk_prodRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public class MQTTClient implements IPubSubAction, IResponseObserver {
    @NotNull
    public final String h;
    @NotNull
    public final List<IObserver> i;
    public HandlerThread j;
    @NotNull
    public Handler k;
    public List<Topic> l;
    @NotNull
    public final Context m;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0012\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0002¢\u0006\u0004\b!\u0010\"J\u0010\u0010\u0003\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0006\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\b\u0010\u0004J.\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u000e\u0010\u0004J\u0010\u0010\u000f\u001a\u00020\u0005HÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0007J\u001a\u0010\u0012\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0012\u0010\u0013R\"\u0010\t\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0004\"\u0004\b\u0017\u0010\u0018R\"\u0010\n\u001a\u00020\u00058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u0007\"\u0004\b\u001c\u0010\u001dR\"\u0010\u000b\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\u0015\u001a\u0004\b\u001f\u0010\u0004\"\u0004\b \u0010\u0018¨\u0006#"}, d2 = {"Lcom/coveiot/android/remotecommandframeworksdk/MQTTClient$Topic;", "", "", "component1", "()Ljava/lang/String;", "", "component2", "()I", "component3", AppMeasurementSdk.ConditionalUserProperty.NAME, MqttServiceConstants.QOS, CMSAttributeTableGenerator.CONTENT_TYPE, Constants.COPY_TYPE, "(Ljava/lang/String;ILjava/lang/String;)Lcom/coveiot/android/remotecommandframeworksdk/MQTTClient$Topic;", "toString", "hashCode", FitnessActivities.OTHER, "", "equals", "(Ljava/lang/Object;)Z", "a", "Ljava/lang/String;", "getName", "setName", "(Ljava/lang/String;)V", "b", "I", "getQos", "setQos", "(I)V", c.f10260a, "getContentType", "setContentType", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "remotecommandframeworksdk_prodRelease"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes6.dex */
    public static final class Topic {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public String f5650a;
        public int b;
        @NotNull
        public String c;

        public Topic(@NotNull String name, int i, @NotNull String contentType) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(contentType, "contentType");
            this.f5650a = name;
            this.b = i;
            this.c = contentType;
        }

        public /* synthetic */ Topic(String str, int i, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, i, (i2 & 4) != 0 ? com.coveiot.android.remotecommandframeworksdk.utils.Constants.CONTENT_TYPE_CBOR.getValue() : str2);
        }

        public static /* synthetic */ Topic copy$default(Topic topic, String str, int i, String str2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = topic.f5650a;
            }
            if ((i2 & 2) != 0) {
                i = topic.b;
            }
            if ((i2 & 4) != 0) {
                str2 = topic.c;
            }
            return topic.copy(str, i, str2);
        }

        @NotNull
        public final String component1() {
            return this.f5650a;
        }

        public final int component2() {
            return this.b;
        }

        @NotNull
        public final String component3() {
            return this.c;
        }

        @NotNull
        public final Topic copy(@NotNull String name, int i, @NotNull String contentType) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(contentType, "contentType");
            return new Topic(name, i, contentType);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (obj instanceof Topic) {
                    Topic topic = (Topic) obj;
                    return Intrinsics.areEqual(this.f5650a, topic.f5650a) && this.b == topic.b && Intrinsics.areEqual(this.c, topic.c);
                }
                return false;
            }
            return true;
        }

        @NotNull
        public final String getContentType() {
            return this.c;
        }

        @NotNull
        public final String getName() {
            return this.f5650a;
        }

        public final int getQos() {
            return this.b;
        }

        public int hashCode() {
            String str = this.f5650a;
            int hashCode = (((str != null ? str.hashCode() : 0) * 31) + Integer.hashCode(this.b)) * 31;
            String str2 = this.c;
            return hashCode + (str2 != null ? str2.hashCode() : 0);
        }

        public final void setContentType(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.c = str;
        }

        public final void setName(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.f5650a = str;
        }

        public final void setQos(int i) {
            this.b = i;
        }

        @NotNull
        public String toString() {
            return "Topic(name=" + this.f5650a + ", qos=" + this.b + ", contentType=" + this.c + ")";
        }
    }

    public MQTTClient(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.m = context;
        this.h = "MQTTClient";
        this.i = new ArrayList();
        this.j = new HandlerThread("MQTTClient-Thread");
        this.l = new ArrayList();
        this.j.start();
        this.k = new Handler(this.j.getLooper());
    }

    public final void addObserver(@NotNull IObserver observer) {
        Intrinsics.checkNotNullParameter(observer, "observer");
        this.i.add(observer);
    }

    public final void addTopic(@NotNull String name, int i, @NotNull String contentType) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        List<Topic> list = this.l;
        boolean z = true;
        if (!(list == null || list.isEmpty())) {
            for (Topic topic : this.l) {
                if (Intrinsics.areEqual(topic.getName(), name)) {
                    topic.setQos(i);
                    topic.setContentType(contentType);
                    break;
                }
            }
        }
        z = false;
        if (z) {
            return;
        }
        this.l.add(new Topic(name, i, contentType));
    }

    public final void clearAllTopic() {
        List<Topic> list = this.l;
        if (list != null) {
            list.clear();
        }
    }

    @Override // com.coveiot.android.remotecommandframeworksdk.IPubSubAction
    public void connect(@NotNull Context context, @NotNull MQTTConnectionParams params, @Nullable SuccessListener successListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
    }

    @Override // com.coveiot.android.remotecommandframeworksdk.IPubSubAction
    public void disconnect(@Nullable SuccessListener successListener) {
    }

    @NotNull
    public final Pair<String[], int[]> getAllSubscription() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        List<Topic> list = this.l;
        if (!(list == null || list.isEmpty())) {
            for (Topic topic : this.l) {
                arrayList.add(topic.getName());
                arrayList2.add(Integer.valueOf(topic.getQos()));
            }
        }
        Object[] array = arrayList.toArray(new String[0]);
        Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T>");
        return new Pair<>(array, CollectionsKt___CollectionsKt.toIntArray(arrayList2));
    }

    @NotNull
    public final Context getContext() {
        return this.m;
    }

    @NotNull
    public final Handler getMHandler() {
        return this.k;
    }

    @NotNull
    public final List<IObserver> getObservers() {
        return this.i;
    }

    @Nullable
    public final Topic getSubscriptionBy(@NotNull String topicName) {
        Intrinsics.checkNotNullParameter(topicName, "topicName");
        List<Topic> list = this.l;
        if (!(list == null || list.isEmpty())) {
            for (Topic topic : this.l) {
                if (Intrinsics.areEqual(topic.getName(), topicName)) {
                    return topic;
                }
            }
        }
        return null;
    }

    @NotNull
    public String getTAG() {
        return this.h;
    }

    public boolean isConnected() {
        return false;
    }

    @Override // com.coveiot.android.remotecommandframeworksdk.IResponseObserver
    public void onResetRetainFlag(@NotNull String msg, @NotNull String topic, int i, @NotNull String contentType) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        Intrinsics.checkNotNullParameter(topic, "topic");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
    }

    @Override // com.coveiot.android.remotecommandframeworksdk.IResponseObserver
    public void onSendResponse(@NotNull String msg, @NotNull String topic, int i, @NotNull String contentType) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        Intrinsics.checkNotNullParameter(topic, "topic");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
    }

    @Override // com.coveiot.android.remotecommandframeworksdk.IResponseObserver
    public void onSendResponseAck(@NotNull String msg, @NotNull String topic, int i, @NotNull String contentType) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        Intrinsics.checkNotNullParameter(topic, "topic");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
    }

    @Override // com.coveiot.android.remotecommandframeworksdk.IPubSubAction
    public void publish(@NotNull String message, @NotNull String topic, int i, @NotNull String contentType) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(topic, "topic");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
    }

    public final void removeObserver(@NotNull IObserver observer) {
        Intrinsics.checkNotNullParameter(observer, "observer");
        this.i.remove(observer);
    }

    public final void removeTopic(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        List<Topic> list = this.l;
        int i = 0;
        int i2 = -1;
        if (!(list == null || list.isEmpty())) {
            for (Object obj : this.l) {
                int i3 = i + 1;
                if (i < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                }
                if (Intrinsics.areEqual(((Topic) obj).getName(), name)) {
                    i2 = i;
                }
                i = i3;
            }
        }
        if (i2 >= 0) {
            this.l.remove(i2);
        }
    }

    public final void setMHandler(@NotNull Handler handler) {
        Intrinsics.checkNotNullParameter(handler, "<set-?>");
        this.k = handler;
    }

    @Override // com.coveiot.android.remotecommandframeworksdk.IPubSubAction
    public void subscribe(@NotNull String topic, int i, @NotNull String contentType, @Nullable SuccessListener successListener) {
        Intrinsics.checkNotNullParameter(topic, "topic");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
    }

    public final void syncCompleted() {
        for (IObserver iObserver : this.i) {
            if (iObserver instanceof ISyncStatusObserver) {
                ((ISyncStatusObserver) iObserver).onSyncCompleted();
            }
        }
    }

    @Override // com.coveiot.android.remotecommandframeworksdk.IPubSubAction
    public void unsubscribe(@NotNull String topic, @Nullable SuccessListener successListener) {
        Intrinsics.checkNotNullParameter(topic, "topic");
    }
}
