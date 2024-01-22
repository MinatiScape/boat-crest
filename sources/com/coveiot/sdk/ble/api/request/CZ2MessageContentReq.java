package com.coveiot.sdk.ble.api.request;

import androidx.annotation.NonNull;
import com.coveiot.sdk.ble.api.MultiPacketRequestGenerator;
import com.coveiot.sdk.ble.api.model.AppNotificationType;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class CZ2MessageContentReq extends BaseRequest {
    public AppNotificationType appNotificationType;
    public String message;
    public String title;

    /* renamed from: com.coveiot.sdk.ble.api.request.CZ2MessageContentReq$1  reason: invalid class name */
    /* loaded from: classes9.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f7476a;

        static {
            int[] iArr = new int[AppNotificationType.values().length];
            f7476a = iArr;
            try {
                iArr[AppNotificationType.CALL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7476a[AppNotificationType.CALENDAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7476a[AppNotificationType.SMS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f7476a[AppNotificationType.EMAIL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f7476a[AppNotificationType.WHATSAPP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f7476a[AppNotificationType.WECHAT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f7476a[AppNotificationType.FACEBOOK.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f7476a[AppNotificationType.INSTAGRAM.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f7476a[AppNotificationType.TWITTER.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f7476a[AppNotificationType.MESSENGER.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f7476a[AppNotificationType.QQ.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f7476a[AppNotificationType.QZONE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f7476a[AppNotificationType.SNAPCHAT.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f7476a[AppNotificationType.SKYPE.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f7476a[AppNotificationType.TELEGRAM.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f7476a[AppNotificationType.LINKEDIN.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f7476a[AppNotificationType.CUSTOM_EVENT.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f7476a[AppNotificationType.OTHER_APPS.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                f7476a[AppNotificationType.GMAIL.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                f7476a[AppNotificationType.KAKAO_TALK.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                f7476a[AppNotificationType.LINE.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                f7476a[AppNotificationType.YOUTUBE.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                f7476a[AppNotificationType.NEWS.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
        }
    }

    /* loaded from: classes9.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Object f7477a;
        public AppNotificationType appNotificationType;
        public String message;
        public String title;

        public Builder(AppNotificationType appNotificationType, String str, String str2) {
            this.appNotificationType = appNotificationType;
            this.message = str2;
            this.title = str;
        }

        public CZ2MessageContentReq build() {
            CZ2MessageContentReq cZ2MessageContentReq = new CZ2MessageContentReq(this.f7477a, null);
            cZ2MessageContentReq.appNotificationType = this.appNotificationType;
            cZ2MessageContentReq.message = this.message;
            cZ2MessageContentReq.title = this.title;
            return cZ2MessageContentReq;
        }
    }

    public /* synthetic */ CZ2MessageContentReq(Object obj, AnonymousClass1 anonymousClass1) {
        this(obj);
    }

    @NonNull
    public final List<CommandBytes> a() {
        int i;
        new ArrayList();
        new CommandBytes();
        switch (AnonymousClass1.f7476a[this.appNotificationType.ordinal()]) {
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
            case 16:
                i = 16;
                break;
            case 17:
                i = 17;
                break;
            case 18:
                i = 18;
                break;
            case 19:
                i = 19;
                break;
            case 20:
                i = 20;
                break;
            case 21:
                i = 21;
                break;
            case 22:
                i = 22;
                break;
            case 23:
                i = 23;
                break;
        }
        int length = this.title.getBytes(StandardCharsets.UTF_8).length + this.message.getBytes(StandardCharsets.UTF_8).length + 1;
        byte[] bArr = new byte[length];
        int i2 = 0;
        for (int i3 = 0; i3 < this.title.getBytes(StandardCharsets.UTF_8).length; i3++) {
            bArr[i2] = this.title.getBytes()[i3];
            i2++;
        }
        bArr[i2] = -1;
        int i4 = i2 + 1;
        for (int i5 = 0; i5 < this.message.getBytes(StandardCharsets.UTF_8).length; i5++) {
            bArr[i4] = this.message.getBytes()[i5];
            i4++;
        }
        if (length + 13 > 146) {
            return MultiPacketRequestGenerator.generateRequest((byte) 2, (byte) -125, bArr, new byte[]{(byte) i}, false, false);
        }
        return MultiPacketRequestGenerator.generateSinglePacketRequest((byte) 2, (byte) -125, bArr, new byte[]{(byte) i}, false);
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
        return CommandNames.SEND_MESSAGE_CONTENT;
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
        return this.message.length() > 15;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isPriority() {
        return true;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean shouldSendAllPacketsAtOnce() {
        return true;
    }

    public CZ2MessageContentReq(Object obj) {
        super(obj);
    }
}
