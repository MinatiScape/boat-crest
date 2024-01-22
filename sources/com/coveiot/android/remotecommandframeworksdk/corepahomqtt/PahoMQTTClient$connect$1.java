package com.coveiot.android.remotecommandframeworksdk.corepahomqtt;

import android.content.Context;
import com.coveiot.android.remotecommandframeworksdk.PreferenceManager;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import com.coveiot.android.remotecommandframeworksdk.utils.MQTTConnectionParams;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.device.rcf.mqtt.DeviceMqttInfoManager;
import com.coveiot.coveaccess.device.rcf.mqtt.model.GetIotMqttCredInfoResponse;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.utils.utility.LogHelper;
import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"", "run", "()V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class PahoMQTTClient$connect$1 implements Runnable {
    public final /* synthetic */ MQTTConnectionParams $connectionParams;
    public final /* synthetic */ Context $context;
    public final /* synthetic */ SuccessListener $listener;
    public final /* synthetic */ PahoMQTTClient this$0;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001J\u0019\u0010\u0006\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\b\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0016¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"com/coveiot/android/remotecommandframeworksdk/corepahomqtt/PahoMQTTClient$connect$1$1", "Lcom/coveiot/coveaccess/CoveApiListener;", "Lcom/coveiot/coveaccess/device/rcf/mqtt/model/GetIotMqttCredInfoResponse;", "Lcom/coveiot/coveaccess/model/CoveApiErrorModel;", "p0", "", "onSuccess", "(Lcom/coveiot/coveaccess/device/rcf/mqtt/model/GetIotMqttCredInfoResponse;)V", "onError", "(Lcom/coveiot/coveaccess/model/CoveApiErrorModel;)V", "remotecommandframeworksdk_prodRelease"}, k = 1, mv = {1, 4, 0})
    /* renamed from: com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient$connect$1$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    public static final class AnonymousClass1 implements CoveApiListener<GetIotMqttCredInfoResponse, CoveApiErrorModel> {
        public AnonymousClass1() {
        }

        @Override // com.coveiot.coveaccess.CoveApiListener
        public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
            LogHelper.e(PahoMQTTClient$connect$1.this.this$0.getTAG(), "Certificates download failed.");
            SuccessListener successListener = PahoMQTTClient$connect$1.this.$listener;
            if (successListener != null) {
                successListener.onFailure("Certificates download failed.");
            }
        }

        @Override // com.coveiot.coveaccess.CoveApiListener
        public void onSuccess(@Nullable GetIotMqttCredInfoResponse getIotMqttCredInfoResponse) {
            if (getIotMqttCredInfoResponse == null) {
                LogHelper.e(PahoMQTTClient$connect$1.this.this$0.getTAG(), "No required certificates found.");
                SuccessListener successListener = PahoMQTTClient$connect$1.this.$listener;
                if (successListener != null) {
                    successListener.onFailure("No required certificates found.");
                    return;
                }
                return;
            }
            PreferenceManager.Companion companion = PreferenceManager.Companion;
            String str = getIotMqttCredInfoResponse.caCertPem;
            Intrinsics.checkNotNullExpressionValue(str, "p0.caCertPem");
            companion.getInstance(PahoMQTTClient$connect$1.this.$context).saveCa(str);
            String str2 = getIotMqttCredInfoResponse.certPem;
            Intrinsics.checkNotNullExpressionValue(str2, "p0.certPem");
            companion.getInstance(PahoMQTTClient$connect$1.this.$context).saveCrt(str2);
            String str3 = getIotMqttCredInfoResponse.pvtKeyPem;
            Intrinsics.checkNotNullExpressionValue(str3, "p0.pvtKeyPem");
            companion.getInstance(PahoMQTTClient$connect$1.this.$context).saveKy(str3);
            MQTTConnectionParams mQTTConnectionParams = PahoMQTTClient$connect$1.this.$connectionParams;
            String str4 = getIotMqttCredInfoResponse.caCertPem;
            Intrinsics.checkNotNullExpressionValue(str4, "p0?.caCertPem");
            Charset charset = Charsets.UTF_8;
            Objects.requireNonNull(str4, "null cannot be cast to non-null type java.lang.String");
            byte[] bytes = str4.getBytes(charset);
            Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
            mQTTConnectionParams.setCaFileInputStream(new ByteArrayInputStream(bytes));
            MQTTConnectionParams mQTTConnectionParams2 = PahoMQTTClient$connect$1.this.$connectionParams;
            String str5 = getIotMqttCredInfoResponse.certPem;
            Intrinsics.checkNotNullExpressionValue(str5, "p0?.certPem");
            Objects.requireNonNull(str5, "null cannot be cast to non-null type java.lang.String");
            byte[] bytes2 = str5.getBytes(charset);
            Intrinsics.checkNotNullExpressionValue(bytes2, "(this as java.lang.String).getBytes(charset)");
            mQTTConnectionParams2.setCertFileInputStream(new ByteArrayInputStream(bytes2));
            MQTTConnectionParams mQTTConnectionParams3 = PahoMQTTClient$connect$1.this.$connectionParams;
            String str6 = getIotMqttCredInfoResponse.pvtKeyPem;
            Intrinsics.checkNotNullExpressionValue(str6, "p0?.pvtKeyPem");
            Objects.requireNonNull(str6, "null cannot be cast to non-null type java.lang.String");
            byte[] bytes3 = str6.getBytes(charset);
            Intrinsics.checkNotNullExpressionValue(bytes3, "(this as java.lang.String).getBytes(charset)");
            mQTTConnectionParams3.setKeyFileInputStream(new ByteArrayInputStream(bytes3));
            PahoMQTTClient$connect$1 pahoMQTTClient$connect$1 = PahoMQTTClient$connect$1.this;
            PahoMQTTClient pahoMQTTClient = pahoMQTTClient$connect$1.this$0;
            Context context = pahoMQTTClient$connect$1.$context;
            MQTTConnectionParams mQTTConnectionParams4 = pahoMQTTClient$connect$1.$connectionParams;
            Intrinsics.checkNotNull(mQTTConnectionParams4);
            pahoMQTTClient.a(context, mQTTConnectionParams4, new SuccessListener() { // from class: com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient$connect$1$1$onSuccess$1
                @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                public void onFailure(@Nullable String str7) {
                    SuccessListener successListener2 = PahoMQTTClient$connect$1.this.$listener;
                    if (successListener2 != null) {
                        successListener2.onFailure(str7);
                    }
                }

                @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                public void onSuccess() {
                    SuccessListener successListener2 = PahoMQTTClient$connect$1.this.$listener;
                    if (successListener2 != null) {
                        successListener2.onSuccess();
                    }
                }
            });
        }
    }

    public PahoMQTTClient$connect$1(PahoMQTTClient pahoMQTTClient, Context context, MQTTConnectionParams mQTTConnectionParams, SuccessListener successListener) {
        this.this$0 = pahoMQTTClient;
        this.$context = context;
        this.$connectionParams = mQTTConnectionParams;
        this.$listener = successListener;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            if (this.this$0.isConnected()) {
                SuccessListener successListener = this.$listener;
                if (successListener != null) {
                    successListener.onSuccess();
                }
                this.this$0.subscribe(this.$connectionParams.getTopicToSubscribe(), this.$connectionParams.getReqQos(), this.$connectionParams.getReqContentType(), null);
                return;
            }
            PreferenceManager.Companion companion = PreferenceManager.Companion;
            if (companion.getInstance(this.$context).getCrt() != null && companion.getInstance(this.$context).getCa() != null && companion.getInstance(this.$context).getKy() != null) {
                MQTTConnectionParams mQTTConnectionParams = this.$connectionParams;
                String ca = companion.getInstance(this.$context).getCa();
                Intrinsics.checkNotNull(ca);
                Charset charset = Charsets.UTF_8;
                if (ca == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                byte[] bytes = ca.getBytes(charset);
                Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
                mQTTConnectionParams.setCaFileInputStream(new ByteArrayInputStream(bytes));
                MQTTConnectionParams mQTTConnectionParams2 = this.$connectionParams;
                String crt = companion.getInstance(this.$context).getCrt();
                Intrinsics.checkNotNull(crt);
                if (crt == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                byte[] bytes2 = crt.getBytes(charset);
                Intrinsics.checkNotNullExpressionValue(bytes2, "(this as java.lang.String).getBytes(charset)");
                mQTTConnectionParams2.setCertFileInputStream(new ByteArrayInputStream(bytes2));
                MQTTConnectionParams mQTTConnectionParams3 = this.$connectionParams;
                String ky = companion.getInstance(this.$context).getKy();
                Intrinsics.checkNotNull(ky);
                if (ky == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                byte[] bytes3 = ky.getBytes(charset);
                Intrinsics.checkNotNullExpressionValue(bytes3, "(this as java.lang.String).getBytes(charset)");
                mQTTConnectionParams3.setKeyFileInputStream(new ByteArrayInputStream(bytes3));
                PahoMQTTClient pahoMQTTClient = this.this$0;
                Context context = this.$context;
                MQTTConnectionParams mQTTConnectionParams4 = this.$connectionParams;
                Intrinsics.checkNotNull(mQTTConnectionParams4);
                pahoMQTTClient.a(context, mQTTConnectionParams4, new SuccessListener() { // from class: com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient$connect$1.2
                    @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                    public void onFailure(@Nullable String str) {
                        SuccessListener successListener2 = PahoMQTTClient$connect$1.this.$listener;
                        if (successListener2 != null) {
                            successListener2.onFailure(str);
                        }
                    }

                    @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                    public void onSuccess() {
                        SuccessListener successListener2 = PahoMQTTClient$connect$1.this.$listener;
                        if (successListener2 != null) {
                            successListener2.onSuccess();
                        }
                    }
                });
                return;
            }
            LogHelper.d(this.this$0.getTAG(), "Crt download triggered.");
            com.coveiot.coveaccess.prefs.PreferenceManager preferenceManager = com.coveiot.coveaccess.prefs.PreferenceManager.getInstance();
            Intrinsics.checkNotNullExpressionValue(preferenceManager, "com.coveiot.coveaccess.p…enceManager.getInstance()");
            DeviceMqttInfoManager.getIotMqttCredInfo(preferenceManager.getUserDeviceID(), new AnonymousClass1());
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
}
