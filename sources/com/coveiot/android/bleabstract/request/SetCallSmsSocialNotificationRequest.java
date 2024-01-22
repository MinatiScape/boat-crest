package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.BleCommand;
/* loaded from: classes2.dex */
public class SetCallSmsSocialNotificationRequest extends BleBaseRequest {
    public boolean A;
    public boolean B;
    public boolean C;
    public boolean D;
    public boolean E;
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

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f3522a;
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
        public boolean y;
        public boolean z;

        public SetCallSmsSocialNotificationRequest build() {
            SetCallSmsSocialNotificationRequest setCallSmsSocialNotificationRequest = new SetCallSmsSocialNotificationRequest();
            setCallSmsSocialNotificationRequest.k = this.b;
            setCallSmsSocialNotificationRequest.j = this.f3522a;
            setCallSmsSocialNotificationRequest.m = this.d;
            setCallSmsSocialNotificationRequest.p = this.g;
            setCallSmsSocialNotificationRequest.q = this.h;
            setCallSmsSocialNotificationRequest.s = this.j;
            setCallSmsSocialNotificationRequest.t = this.k;
            setCallSmsSocialNotificationRequest.u = this.l;
            setCallSmsSocialNotificationRequest.v = this.m;
            setCallSmsSocialNotificationRequest.w = this.n;
            setCallSmsSocialNotificationRequest.l = this.c;
            setCallSmsSocialNotificationRequest.r = this.i;
            setCallSmsSocialNotificationRequest.o = this.f;
            setCallSmsSocialNotificationRequest.n = this.e;
            setCallSmsSocialNotificationRequest.x = this.o;
            setCallSmsSocialNotificationRequest.y = this.p;
            setCallSmsSocialNotificationRequest.z = this.q;
            setCallSmsSocialNotificationRequest.A = this.r;
            setCallSmsSocialNotificationRequest.f = this.s;
            setCallSmsSocialNotificationRequest.g = this.t;
            setCallSmsSocialNotificationRequest.h = this.u;
            setCallSmsSocialNotificationRequest.i = this.v;
            setCallSmsSocialNotificationRequest.B = this.w;
            setCallSmsSocialNotificationRequest.C = this.x;
            setCallSmsSocialNotificationRequest.D = this.y;
            setCallSmsSocialNotificationRequest.E = this.z;
            return setCallSmsSocialNotificationRequest;
        }

        public Builder setAppAlerts(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15) {
            this.b = z2;
            this.f3522a = z;
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
            return this;
        }

        public Builder setCalendarEnabled(boolean z) {
            this.b = z;
            return this;
        }

        public Builder setCallEnabled(boolean z) {
            this.f3522a = z;
            return this;
        }

        public Builder setCustomEventEnabled(boolean z) {
            this.w = z;
            return this;
        }

        public Builder setEmailAppEnabled(boolean z) {
            this.d = z;
            return this;
        }

        public Builder setEmailEnabled(boolean z) {
            this.d = z;
            return this;
        }

        public Builder setFacebookEnabled(boolean z) {
            this.g = z;
            return this;
        }

        public Builder setGmailEnabled(boolean z) {
            this.x = z;
            return this;
        }

        public Builder setInstagramEnabled(boolean z) {
            this.h = z;
            return this;
        }

        public Builder setKaKaoTalkEnabled(boolean z) {
            this.y = z;
            return this;
        }

        public Builder setLineEnabled(boolean z) {
            this.o = z;
            return this;
        }

        public Builder setLinkedInEnabled(boolean z) {
            this.q = z;
            return this;
        }

        public Builder setMessengerEnabled(boolean z) {
            this.j = z;
            return this;
        }

        public Builder setNewsEnabled(boolean z) {
            this.z = z;
            return this;
        }

        public Builder setOtherAppEnabled(boolean z) {
            this.r = z;
            return this;
        }

        public Builder setOutLookEnabled(boolean z) {
            this.t = z;
            return this;
        }

        public Builder setQQEnabled(boolean z) {
            this.k = z;
            return this;
        }

        public Builder setQzoneEnabled(boolean z) {
            this.l = z;
            return this;
        }

        public Builder setSMSAppEnabled(boolean z) {
            this.c = z;
            return this;
        }

        public Builder setSkypeEnabled(boolean z) {
            this.n = z;
            return this;
        }

        public Builder setSmsEnabled(boolean z) {
            this.c = z;
            return this;
        }

        public Builder setSnapchatEnabled(boolean z) {
            this.m = z;
            return this;
        }

        public Builder setTelegramEnabled(boolean z) {
            this.p = z;
            return this;
        }

        public Builder setTwitterEnabled(boolean z) {
            this.i = z;
            return this;
        }

        public Builder setWeChatEnabled(boolean z) {
            this.f = z;
            return this;
        }

        public Builder setWechatEnabled(boolean z) {
            this.f = z;
            return this;
        }

        public Builder setWhatsAppBusinessEnabled(boolean z) {
            this.s = z;
            return this;
        }

        public Builder setWhatsAppEnabled(boolean z) {
            this.e = z;
            return this;
        }

        public Builder setYahooEmailEnabled(boolean z) {
            this.u = z;
            return this;
        }

        public Builder setYoutubeEnabled(boolean z) {
            this.v = z;
            return this;
        }
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    public BleCommand getBleCommand() {
        return BleCommand.SET_MESSAGE_ALERT_SWITCH;
    }

    public boolean isCalendarEnabled() {
        return this.k;
    }

    public boolean isCallEnabled() {
        return this.j;
    }

    public boolean isCustomEventEnabled() {
        return this.B;
    }

    public boolean isEmailEnabled() {
        return this.m;
    }

    public boolean isFacebookEnabled() {
        return this.p;
    }

    public boolean isGmailEnabled() {
        return this.C;
    }

    public boolean isInstagramEnabled() {
        return this.q;
    }

    public boolean isKaKaoTalkEnabled() {
        return this.D;
    }

    public boolean isLineEnabled() {
        return this.x;
    }

    public boolean isLinkedInEnabled() {
        return this.z;
    }

    public boolean isMessengerEnabled() {
        return this.s;
    }

    public boolean isNewsEnabled() {
        return this.E;
    }

    public boolean isOtherAppEnabled() {
        return this.A;
    }

    public boolean isOutLookEnabled() {
        return this.g;
    }

    public boolean isQQEnabled() {
        return this.t;
    }

    public boolean isQzoneEnabled() {
        return this.u;
    }

    public boolean isSkypeEnabled() {
        return this.w;
    }

    public boolean isSmsEnabled() {
        return this.l;
    }

    public boolean isSnapchatEnabled() {
        return this.v;
    }

    public boolean isTelegramEnabled() {
        return this.y;
    }

    public boolean isTwitterEnabled() {
        return this.r;
    }

    public boolean isWeChatEnabled() {
        return this.o;
    }

    public boolean isWhatsAppBusinessEnabled() {
        return this.f;
    }

    public boolean isWhatsAppEnabled() {
        return this.n;
    }

    public boolean isYahooEmailEnabled() {
        return this.h;
    }

    public boolean isYoutubeEnabled() {
        return this.i;
    }
}
