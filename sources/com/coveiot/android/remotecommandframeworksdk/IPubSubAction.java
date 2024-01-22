package com.coveiot.android.remotecommandframeworksdk;

import android.content.Context;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import com.coveiot.android.remotecommandframeworksdk.utils.MQTTConnectionParams;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import org.bouncycastle.cms.CMSAttributeTableGenerator;
import org.eclipse.paho.android.service.MqttServiceConstants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\n\bf\u0018\u00002\u00020\u0001J)\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H&¢\u0006\u0004\b\t\u0010\nJ\u0019\u0010\u000b\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H&¢\u0006\u0004\b\u000b\u0010\fJ1\u0010\u0012\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\r2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H&¢\u0006\u0004\b\u0012\u0010\u0013J!\u0010\u0014\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H&¢\u0006\u0004\b\u0014\u0010\u0015J/\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\rH&¢\u0006\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Lcom/coveiot/android/remotecommandframeworksdk/IPubSubAction;", "", "Landroid/content/Context;", "context", "Lcom/coveiot/android/remotecommandframeworksdk/utils/MQTTConnectionParams;", "params", "Lcom/coveiot/android/remotecommandframeworksdk/listener/SuccessListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "", MqttServiceConstants.CONNECT_ACTION, "(Landroid/content/Context;Lcom/coveiot/android/remotecommandframeworksdk/utils/MQTTConnectionParams;Lcom/coveiot/android/remotecommandframeworksdk/listener/SuccessListener;)V", MqttServiceConstants.DISCONNECT_ACTION, "(Lcom/coveiot/android/remotecommandframeworksdk/listener/SuccessListener;)V", "", "topic", "", MqttServiceConstants.QOS, CMSAttributeTableGenerator.CONTENT_TYPE, MqttServiceConstants.SUBSCRIBE_ACTION, "(Ljava/lang/String;ILjava/lang/String;Lcom/coveiot/android/remotecommandframeworksdk/listener/SuccessListener;)V", MqttServiceConstants.UNSUBSCRIBE_ACTION, "(Ljava/lang/String;Lcom/coveiot/android/remotecommandframeworksdk/listener/SuccessListener;)V", Constants.KEY_MESSAGE, "publish", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V", "remotecommandframeworksdk_prodRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public interface IPubSubAction {
    void connect(@NotNull Context context, @NotNull MQTTConnectionParams mQTTConnectionParams, @Nullable SuccessListener successListener);

    void disconnect(@Nullable SuccessListener successListener);

    void publish(@NotNull String str, @NotNull String str2, int i, @NotNull String str3);

    void subscribe(@NotNull String str, int i, @NotNull String str2, @Nullable SuccessListener successListener);

    void unsubscribe(@NotNull String str, @Nullable SuccessListener successListener);
}
