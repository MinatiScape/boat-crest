package com.coveiot.android.remotecommandframeworksdk;

import android.content.Context;
import com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient;
import com.coveiot.android.remotecommandframeworksdk.utils.MQTTClientType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/coveiot/android/remotecommandframeworksdk/MQTTClientFactory;", "", "Lcom/coveiot/android/remotecommandframeworksdk/utils/MQTTClientType;", "mqttClientType", "Landroid/content/Context;", "context", "Lcom/coveiot/android/remotecommandframeworksdk/MQTTClient;", "getMQTTClient", "(Lcom/coveiot/android/remotecommandframeworksdk/utils/MQTTClientType;Landroid/content/Context;)Lcom/coveiot/android/remotecommandframeworksdk/MQTTClient;", "<init>", "()V", "remotecommandframeworksdk_prodRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class MQTTClientFactory {
    public static final MQTTClientFactory INSTANCE = new MQTTClientFactory();

    @Nullable
    public final MQTTClient getMQTTClient(@NotNull MQTTClientType mqttClientType, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(mqttClientType, "mqttClientType");
        Intrinsics.checkNotNullParameter(context, "context");
        if (Intrinsics.areEqual(mqttClientType.getType(), MQTTClientType.ECLIPSE_PAHO_CLIENT.getType())) {
            return PahoMQTTClient.Companion.getInstance(context);
        }
        return null;
    }
}
