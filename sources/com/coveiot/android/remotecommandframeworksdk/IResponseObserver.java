package com.coveiot.android.remotecommandframeworksdk;

import com.coveiot.android.remotecommandframeworksdk.utils.Constants;
import kotlin.Metadata;
import org.bouncycastle.cms.CMSAttributeTableGenerator;
import org.eclipse.paho.android.service.MqttServiceConstants;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J1\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0002H&¢\u0006\u0004\b\t\u0010\nJ1\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0002H&¢\u0006\u0004\b\u000b\u0010\nJ1\u0010\f\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0002H&¢\u0006\u0004\b\f\u0010\n¨\u0006\r"}, d2 = {"Lcom/coveiot/android/remotecommandframeworksdk/IResponseObserver;", "Lcom/coveiot/android/remotecommandframeworksdk/IObserver;", "", "msg", "topic", "", MqttServiceConstants.QOS, CMSAttributeTableGenerator.CONTENT_TYPE, "", "onSendResponseAck", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V", "onSendResponse", "onResetRetainFlag", "remotecommandframeworksdk_prodRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public interface IResponseObserver extends IObserver {

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes6.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ void onResetRetainFlag$default(IResponseObserver iResponseObserver, String str, String str2, int i, String str3, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onResetRetainFlag");
            }
            if ((i2 & 8) != 0) {
                str3 = Constants.CONTENT_TYPE_CBOR.getValue();
            }
            iResponseObserver.onResetRetainFlag(str, str2, i, str3);
        }

        public static /* synthetic */ void onSendResponse$default(IResponseObserver iResponseObserver, String str, String str2, int i, String str3, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onSendResponse");
            }
            if ((i2 & 8) != 0) {
                str3 = Constants.CONTENT_TYPE_CBOR.getValue();
            }
            iResponseObserver.onSendResponse(str, str2, i, str3);
        }

        public static /* synthetic */ void onSendResponseAck$default(IResponseObserver iResponseObserver, String str, String str2, int i, String str3, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onSendResponseAck");
            }
            if ((i2 & 8) != 0) {
                str3 = Constants.CONTENT_TYPE_CBOR.getValue();
            }
            iResponseObserver.onSendResponseAck(str, str2, i, str3);
        }
    }

    void onResetRetainFlag(@NotNull String str, @NotNull String str2, int i, @NotNull String str3);

    void onSendResponse(@NotNull String str, @NotNull String str2, int i, @NotNull String str3);

    void onSendResponseAck(@NotNull String str, @NotNull String str2, int i, @NotNull String str3);
}
