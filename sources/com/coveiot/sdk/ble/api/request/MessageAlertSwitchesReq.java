package com.coveiot.sdk.ble.api.request;

import androidx.annotation.NonNull;
import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.utils.CommandNames;
import com.coveiot.sdk.ble.utils.DevicePlatformEnum;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class MessageAlertSwitchesReq extends BaseRequest {
    public boolean A;
    public boolean B;
    public boolean C;
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public boolean j;
    public boolean k;
    public boolean l;
    public boolean m;
    public boolean n;
    public boolean o;
    public boolean p;
    public boolean q;
    public boolean r;
    public boolean s;
    public boolean t;
    public boolean u;
    public boolean v;
    public boolean w;
    public boolean x;
    public boolean y;
    public boolean z;

    /* loaded from: classes9.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f7503a;
        public boolean b;
        public boolean c;
        public boolean d;
        public boolean e;
        public boolean f;
        public boolean g;
        public boolean h;
        public boolean i;
        public boolean j;
        public boolean k;
        public boolean l;
        public boolean m;
        public boolean n;
        public boolean o;
        public boolean p;
        public boolean q;
        public boolean r;
        public boolean s;
        public boolean t;
        public boolean u;
        public boolean v;
        public boolean w;
        public boolean x;
        public Object y;

        public MessageAlertSwitchesReq build() {
            MessageAlertSwitchesReq messageAlertSwitchesReq = new MessageAlertSwitchesReq(this.y);
            messageAlertSwitchesReq.g = this.b;
            messageAlertSwitchesReq.f = this.f7503a;
            messageAlertSwitchesReq.i = this.d;
            messageAlertSwitchesReq.l = this.g;
            messageAlertSwitchesReq.m = this.h;
            messageAlertSwitchesReq.o = this.j;
            messageAlertSwitchesReq.p = this.k;
            messageAlertSwitchesReq.q = this.l;
            messageAlertSwitchesReq.r = this.m;
            messageAlertSwitchesReq.s = this.n;
            messageAlertSwitchesReq.h = this.c;
            messageAlertSwitchesReq.n = this.i;
            messageAlertSwitchesReq.k = this.f;
            messageAlertSwitchesReq.j = this.e;
            messageAlertSwitchesReq.t = this.o;
            messageAlertSwitchesReq.u = this.p;
            messageAlertSwitchesReq.v = this.q;
            messageAlertSwitchesReq.w = this.r;
            messageAlertSwitchesReq.x = this.s;
            messageAlertSwitchesReq.y = this.t;
            messageAlertSwitchesReq.z = this.u;
            messageAlertSwitchesReq.A = this.v;
            messageAlertSwitchesReq.B = this.w;
            messageAlertSwitchesReq.C = this.x;
            return messageAlertSwitchesReq;
        }

        public Builder doesSupportExtendedAppNotification(boolean z) {
            this.x = z;
            return this;
        }

        public Builder setAppAlerts(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15, boolean z16, boolean z17) {
            this.b = z2;
            this.f7503a = z;
            this.d = z4;
            this.g = z7;
            this.h = z8;
            this.j = z10;
            this.k = z11;
            this.l = z12;
            this.m = z13;
            this.n = z14;
            this.c = z3;
            this.i = z9;
            this.f = z6;
            this.e = z5;
            this.o = z15;
            this.p = z16;
            this.q = z17;
            return this;
        }

        public Builder setCommonAppEnabled(boolean z) {
            this.s = z;
            return this;
        }

        public Builder setCustomEventEnabled(boolean z) {
            this.r = z;
            return this;
        }

        public Builder setGmailEnabled(boolean z) {
            this.t = z;
            return this;
        }

        public Builder setKaKaoTalkEnabled(boolean z) {
            this.u = z;
            return this;
        }

        public Builder setNewsEnabled(boolean z) {
            this.w = z;
            return this;
        }

        public Builder setYoutubeEnabled(boolean z) {
            this.v = z;
            return this;
        }
    }

    public MessageAlertSwitchesReq(Object obj) {
        super(obj);
    }

    @NonNull
    public final List<CommandBytes> a() {
        ArrayList arrayList = new ArrayList();
        byte b = isCallEnabled() ? (byte) 1 : (byte) 0;
        if (isCalendarEnabled()) {
            b = (byte) (b + 2);
        }
        if (isSmsEnabled()) {
            b = (byte) (b + 4);
        }
        if (isEmailEnabled()) {
            b = (byte) (b + 8);
        }
        if (isWhatsAppEnabled()) {
            b = (byte) (b + 16);
        }
        if (isWeChatEnabled()) {
            b = (byte) (b + 32);
        }
        if (isFacebookEnabled()) {
            b = (byte) (b + 64);
        }
        if (isInstagramEnabled()) {
            b = (byte) (b + 128);
        }
        byte b2 = isTwitterEnabled() ? (byte) 1 : (byte) 0;
        if (isMessengerEnabled()) {
            b2 = (byte) (b2 + 2);
        }
        if (isQQEnabled()) {
            b2 = (byte) (b2 + 4);
        }
        if (isQzoneEnabled()) {
            b2 = (byte) (b2 + 8);
        }
        if (isSnapchatEnabled()) {
            b2 = (byte) (b2 + 16);
        }
        if (isSkypeEnabled()) {
            b2 = (byte) (b2 + 32);
        }
        if (isTelegramEnabled()) {
            b2 = (byte) (b2 + 64);
        }
        if (isLinkedInEnabled()) {
            b2 = (byte) (b2 + 128);
        }
        byte b3 = this.w ? (byte) 1 : (byte) 0;
        if (this.x) {
            b3 = (byte) (b3 + 2);
        }
        if (this.y) {
            b3 = (byte) (b3 + 4);
        }
        if (this.z) {
            b3 = (byte) (b3 + 8);
        }
        if (this.t) {
            b3 = (byte) (b3 + 16);
        }
        if (this.A) {
            b3 = (byte) (b3 + 32);
        }
        if (this.B) {
            b3 = (byte) (b3 + 64);
        }
        byte[] bArr = BleUUID.SET_MESSAGE_ALERT_SWITCHES;
        byte[] bArr2 = {b, b2};
        if (this.d == DevicePlatformEnum.Apollo || this.C) {
            bArr2 = new byte[]{b, b2, b3, 0};
            bArr = BleUUID.SET_MESSAGE_ALERT_SWITCHES_EXTENDED_NOTIFY;
        }
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        CommandBytes commandBytes = new CommandBytes();
        commandBytes.setCommandData(bArr3);
        arrayList.add(commandBytes);
        return arrayList;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        return a();
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.MESSAGE_ALERT_SWITCHES;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattCharacteristicToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattServiceToRead() {
        return null;
    }

    public boolean isCalendarEnabled() {
        return this.g;
    }

    public boolean isCallEnabled() {
        return this.f;
    }

    public boolean isEmailEnabled() {
        return this.i;
    }

    public boolean isFacebookEnabled() {
        return this.l;
    }

    public boolean isInstagramEnabled() {
        return this.m;
    }

    public boolean isLineEnabled() {
        return this.t;
    }

    public boolean isLinkedInEnabled() {
        return this.v;
    }

    public boolean isMessengerEnabled() {
        return this.o;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isPriority() {
        return true;
    }

    public boolean isQQEnabled() {
        return this.p;
    }

    public boolean isQzoneEnabled() {
        return this.q;
    }

    public boolean isSkypeEnabled() {
        return this.s;
    }

    public boolean isSmsEnabled() {
        return this.h;
    }

    public boolean isSnapchatEnabled() {
        return this.r;
    }

    public boolean isTelegramEnabled() {
        return this.u;
    }

    public boolean isTwitterEnabled() {
        return this.n;
    }

    public boolean isWeChatEnabled() {
        return this.k;
    }

    public boolean isWhatsAppEnabled() {
        return this.j;
    }
}
