package com.coveiot.sdk.ble.api.request;

import androidx.annotation.NonNull;
import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.api.model.AppNotificationType;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes9.dex */
public class MessageContentReq extends BaseRequest {
    public AppNotificationType appNotificationType;
    public String message;

    /* renamed from: com.coveiot.sdk.ble.api.request.MessageContentReq$1  reason: invalid class name */
    /* loaded from: classes9.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f7504a;

        static {
            int[] iArr = new int[AppNotificationType.values().length];
            f7504a = iArr;
            try {
                iArr[AppNotificationType.CALL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7504a[AppNotificationType.CALENDAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7504a[AppNotificationType.SMS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f7504a[AppNotificationType.EMAIL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f7504a[AppNotificationType.WHATSAPP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f7504a[AppNotificationType.WECHAT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f7504a[AppNotificationType.FACEBOOK.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f7504a[AppNotificationType.INSTAGRAM.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f7504a[AppNotificationType.TWITTER.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f7504a[AppNotificationType.MESSENGER.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f7504a[AppNotificationType.QQ.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f7504a[AppNotificationType.QZONE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f7504a[AppNotificationType.SNAPCHAT.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f7504a[AppNotificationType.SKYPE.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f7504a[AppNotificationType.TELEGRAM.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f7504a[AppNotificationType.LINKEDIN.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f7504a[AppNotificationType.CUSTOM_EVENT.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f7504a[AppNotificationType.OTHER_APPS.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                f7504a[AppNotificationType.GMAIL.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                f7504a[AppNotificationType.KAKAO_TALK.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                f7504a[AppNotificationType.LINE.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                f7504a[AppNotificationType.YOUTUBE.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                f7504a[AppNotificationType.NEWS.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
        }
    }

    /* loaded from: classes9.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Object f7505a;
        public AppNotificationType appNotificationType;
        public String message;

        public Builder(AppNotificationType appNotificationType, String str) {
            this.appNotificationType = appNotificationType;
            this.message = str;
        }

        public MessageContentReq build() {
            MessageContentReq messageContentReq = new MessageContentReq(this.f7505a, null);
            messageContentReq.appNotificationType = this.appNotificationType;
            messageContentReq.message = this.message;
            return messageContentReq;
        }
    }

    public /* synthetic */ MessageContentReq(Object obj, AnonymousClass1 anonymousClass1) {
        this(obj);
    }

    @NonNull
    public final List<CommandBytes> a() {
        int i;
        String str;
        byte[] bytes;
        int length;
        int i2;
        ArrayList arrayList = new ArrayList();
        CommandBytes commandBytes = new CommandBytes();
        int i3 = 13;
        int i4 = 4;
        switch (AnonymousClass1.f7504a[this.appNotificationType.ordinal()]) {
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
        String str2 = this.message;
        byte b = 0;
        if (str2.length() > 15) {
            if (str2.length() > 59) {
                str2 = str2.substring(0, 58);
            }
            int length2 = str2.length() + 1 + 4;
            double ceil = Math.ceil(((length2 + 4) + 15) / 16);
            int length3 = (length2 * (-125) * str2.length()) + 2;
            byte b2 = (byte) length3;
            byte b3 = (byte) (length3 >> 8);
            byte b4 = (byte) length2;
            byte b5 = (byte) (length2 >> 8);
            String str3 = str2;
            int i5 = 0;
            int i6 = 0;
            while (i5 < ceil) {
                CommandBytes commandBytes2 = new CommandBytes();
                if (i5 == 0) {
                    byte[] bArr = new byte[i3];
                    bArr[b] = Byte.MAX_VALUE;
                    bArr[1] = b2;
                    bArr[2] = (byte) i5;
                    bArr[3] = b;
                    bArr[i4] = (byte) ceil;
                    bArr[5] = b;
                    bArr[6] = b2;
                    bArr[7] = b3;
                    bArr[8] = 2;
                    bArr[9] = -125;
                    bArr[10] = b4;
                    bArr[11] = b5;
                    bArr[12] = (byte) i;
                    str = str3;
                    byte[] bytes2 = str.substring(b, 7).getBytes();
                    i6 += bytes2.length;
                    byte[] bArr2 = new byte[bytes2.length + 13];
                    System.arraycopy(bArr, 0, bArr2, 0, 13);
                    System.arraycopy(bytes2, 0, bArr2, 13, bytes2.length);
                    commandBytes2.setCommandData(bArr2);
                    arrayList.add(commandBytes2);
                    i2 = 4;
                } else {
                    byte b6 = b;
                    int i7 = i4;
                    int i8 = i6;
                    str = str3;
                    byte[] bArr3 = new byte[i7];
                    bArr3[b6] = Byte.MAX_VALUE;
                    bArr3[1] = b2;
                    bArr3[2] = (byte) i5;
                    bArr3[3] = b6;
                    if (str.length() - i8 > 16) {
                        bytes = str.substring(i8, i8 + 16).getBytes();
                        length = bytes.length;
                    } else {
                        bytes = str.substring(i8, (str.length() - i8) + i8).getBytes();
                        length = bytes.length;
                    }
                    i6 = i8 + length;
                    i2 = 4;
                    byte[] bArr4 = new byte[bytes.length + 4];
                    System.arraycopy(bArr3, 0, bArr4, 0, 4);
                    System.arraycopy(bytes, 0, bArr4, 4, bytes.length);
                    commandBytes2.setCommandData(bArr4);
                    arrayList.add(commandBytes2);
                }
                i5++;
                str3 = str;
                b = 0;
                i4 = i2;
                i3 = 13;
            }
        } else {
            byte[] bArr5 = BleUUID.SEND_MESSAGE_CONTENT;
            byte[] bArr6 = {(byte) (bArr5.length + 3 + str2.length()), 0, (byte) i};
            byte[] bytes3 = str2.getBytes();
            int length4 = bytes3.length + 3;
            byte[] bArr7 = new byte[length4];
            System.arraycopy(bArr6, 0, bArr7, 0, 3);
            System.arraycopy(bytes3, 0, bArr7, 3, bytes3.length);
            byte[] bArr8 = new byte[bArr5.length + length4];
            System.arraycopy(bArr5, 0, bArr8, 0, bArr5.length);
            System.arraycopy(bArr7, 0, bArr8, bArr5.length, length4);
            commandBytes.setCommandData(bArr8);
            arrayList.add(commandBytes);
        }
        Collections.reverse(arrayList);
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

    public MessageContentReq(Object obj) {
        super(obj);
    }
}
