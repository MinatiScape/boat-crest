package com.coveiot.sdk.ble.api.request;

import androidx.annotation.NonNull;
import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.api.model.AppNotificationType;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class StopMessageNotificationReq extends BaseRequest {
    public AppNotificationType appNotificationType;

    /* renamed from: com.coveiot.sdk.ble.api.request.StopMessageNotificationReq$1  reason: invalid class name */
    /* loaded from: classes9.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f7537a;

        static {
            int[] iArr = new int[AppNotificationType.values().length];
            f7537a = iArr;
            try {
                iArr[AppNotificationType.CALL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7537a[AppNotificationType.CALENDAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7537a[AppNotificationType.SMS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f7537a[AppNotificationType.EMAIL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f7537a[AppNotificationType.WHATSAPP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f7537a[AppNotificationType.WECHAT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f7537a[AppNotificationType.FACEBOOK.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f7537a[AppNotificationType.INSTAGRAM.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f7537a[AppNotificationType.TWITTER.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f7537a[AppNotificationType.MESSENGER.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f7537a[AppNotificationType.QQ.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f7537a[AppNotificationType.QZONE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f7537a[AppNotificationType.SNAPCHAT.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f7537a[AppNotificationType.SKYPE.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f7537a[AppNotificationType.LINE.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
        }
    }

    /* loaded from: classes9.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Object f7538a;
        public AppNotificationType appNotificationType;
        public String message;

        public Builder(AppNotificationType appNotificationType) {
            this.appNotificationType = appNotificationType;
        }

        public StopMessageNotificationReq build() {
            StopMessageNotificationReq stopMessageNotificationReq = new StopMessageNotificationReq(this.f7538a, null);
            stopMessageNotificationReq.appNotificationType = this.appNotificationType;
            return stopMessageNotificationReq;
        }
    }

    public /* synthetic */ StopMessageNotificationReq(Object obj, AnonymousClass1 anonymousClass1) {
        this(obj);
    }

    @NonNull
    public final List<CommandBytes> a() {
        int i;
        ArrayList arrayList = new ArrayList();
        CommandBytes commandBytes = new CommandBytes();
        switch (AnonymousClass1.f7537a[this.appNotificationType.ordinal()]) {
            case 1:
            default:
                i = 1;
                break;
            case 2:
                i = 2;
                break;
            case 3:
                i = 3;
                break;
            case 4:
                i = 4;
                break;
            case 5:
                i = 5;
                break;
            case 6:
                i = 6;
                break;
            case 7:
                i = 7;
                break;
            case 8:
                i = 8;
                break;
            case 9:
                i = 9;
                break;
            case 10:
                i = 10;
                break;
            case 11:
                i = 11;
                break;
            case 12:
                i = 12;
                break;
            case 13:
                i = 13;
                break;
            case 14:
                i = 14;
                break;
            case 15:
                i = 15;
                break;
        }
        byte[] bArr = BleUUID.STOP_MESSAGE_NOTIFICATION;
        byte[] bArr2 = {(byte) i};
        byte[] bArr3 = new byte[1];
        System.arraycopy(bArr2, 0, bArr3, 0, 1);
        byte[] bArr4 = new byte[bArr.length + 1];
        System.arraycopy(bArr, 0, bArr4, 0, bArr.length);
        System.arraycopy(bArr3, 0, bArr4, bArr.length, 1);
        commandBytes.setCommandData(bArr4);
        arrayList.add(commandBytes);
        return arrayList;
    }

    public AppNotificationType getAppNotificationType() {
        return this.appNotificationType;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        return a();
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.STOP_MESSAGE_NOTIFICATION;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattCharacteristicToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattServiceToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isPriority() {
        return true;
    }

    public StopMessageNotificationReq(Object obj) {
        super(obj);
    }
}
