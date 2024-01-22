package com.google.firebase.messaging.reporting;

import androidx.annotation.NonNull;
import com.google.android.gms.internal.firebase_messaging.zzx;
import com.google.android.gms.internal.firebase_messaging.zzz;
/* loaded from: classes10.dex */
public final class MessagingClientEvent {
    public static final MessagingClientEvent p = new Builder().build();

    /* renamed from: a  reason: collision with root package name */
    public final long f11358a;
    public final String b;
    public final String c;
    public final MessageType d;
    public final SDKPlatform e;
    public final String f;
    public final String g;
    public final int h;
    public final int i;
    public final String j;
    public final long k;
    public final Event l;
    public final String m;
    public final long n;
    public final String o;

    /* loaded from: classes10.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public long f11359a = 0;
        public String b = "";
        public String c = "";
        public MessageType d = MessageType.UNKNOWN;
        public SDKPlatform e = SDKPlatform.UNKNOWN_OS;
        public String f = "";
        public String g = "";
        public int h = 0;
        public int i = 0;
        public String j = "";
        public long k = 0;
        public Event l = Event.UNKNOWN_EVENT;
        public String m = "";
        public long n = 0;
        public String o = "";

        @NonNull
        public MessagingClientEvent build() {
            return new MessagingClientEvent(this.f11359a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o);
        }

        @NonNull
        public Builder setAnalyticsLabel(@NonNull String str) {
            this.m = str;
            return this;
        }

        @NonNull
        public Builder setBulkId(long j) {
            this.k = j;
            return this;
        }

        @NonNull
        public Builder setCampaignId(long j) {
            this.n = j;
            return this;
        }

        @NonNull
        public Builder setCollapseKey(@NonNull String str) {
            this.g = str;
            return this;
        }

        @NonNull
        public Builder setComposerLabel(@NonNull String str) {
            this.o = str;
            return this;
        }

        @NonNull
        public Builder setEvent(@NonNull Event event) {
            this.l = event;
            return this;
        }

        @NonNull
        public Builder setInstanceId(@NonNull String str) {
            this.c = str;
            return this;
        }

        @NonNull
        public Builder setMessageId(@NonNull String str) {
            this.b = str;
            return this;
        }

        @NonNull
        public Builder setMessageType(@NonNull MessageType messageType) {
            this.d = messageType;
            return this;
        }

        @NonNull
        public Builder setPackageName(@NonNull String str) {
            this.f = str;
            return this;
        }

        @NonNull
        public Builder setPriority(int i) {
            this.h = i;
            return this;
        }

        @NonNull
        public Builder setProjectNumber(long j) {
            this.f11359a = j;
            return this;
        }

        @NonNull
        public Builder setSdkPlatform(@NonNull SDKPlatform sDKPlatform) {
            this.e = sDKPlatform;
            return this;
        }

        @NonNull
        public Builder setTopic(@NonNull String str) {
            this.j = str;
            return this;
        }

        @NonNull
        public Builder setTtl(int i) {
            this.i = i;
            return this;
        }
    }

    /* loaded from: classes10.dex */
    public enum Event implements zzx {
        UNKNOWN_EVENT(0),
        MESSAGE_DELIVERED(1),
        MESSAGE_OPEN(2);
        
        private final int number_;

        Event(int i) {
            this.number_ = i;
        }

        @Override // com.google.android.gms.internal.firebase_messaging.zzx
        public int getNumber() {
            return this.number_;
        }
    }

    /* loaded from: classes10.dex */
    public enum MessageType implements zzx {
        UNKNOWN(0),
        DATA_MESSAGE(1),
        TOPIC(2),
        DISPLAY_NOTIFICATION(3);
        
        private final int number_;

        MessageType(int i) {
            this.number_ = i;
        }

        @Override // com.google.android.gms.internal.firebase_messaging.zzx
        public int getNumber() {
            return this.number_;
        }
    }

    /* loaded from: classes10.dex */
    public enum SDKPlatform implements zzx {
        UNKNOWN_OS(0),
        ANDROID(1),
        IOS(2),
        WEB(3);
        
        private final int number_;

        SDKPlatform(int i) {
            this.number_ = i;
        }

        @Override // com.google.android.gms.internal.firebase_messaging.zzx
        public int getNumber() {
            return this.number_;
        }
    }

    public MessagingClientEvent(long j, String str, String str2, MessageType messageType, SDKPlatform sDKPlatform, String str3, String str4, int i, int i2, String str5, long j2, Event event, String str6, long j3, String str7) {
        this.f11358a = j;
        this.b = str;
        this.c = str2;
        this.d = messageType;
        this.e = sDKPlatform;
        this.f = str3;
        this.g = str4;
        this.h = i;
        this.i = i2;
        this.j = str5;
        this.k = j2;
        this.l = event;
        this.m = str6;
        this.n = j3;
        this.o = str7;
    }

    @NonNull
    public static MessagingClientEvent getDefaultInstance() {
        return p;
    }

    @NonNull
    public static Builder newBuilder() {
        return new Builder();
    }

    @NonNull
    @zzz(zza = 13)
    public String getAnalyticsLabel() {
        return this.m;
    }

    @zzz(zza = 11)
    public long getBulkId() {
        return this.k;
    }

    @zzz(zza = 14)
    public long getCampaignId() {
        return this.n;
    }

    @NonNull
    @zzz(zza = 7)
    public String getCollapseKey() {
        return this.g;
    }

    @NonNull
    @zzz(zza = 15)
    public String getComposerLabel() {
        return this.o;
    }

    @NonNull
    @zzz(zza = 12)
    public Event getEvent() {
        return this.l;
    }

    @NonNull
    @zzz(zza = 3)
    public String getInstanceId() {
        return this.c;
    }

    @NonNull
    @zzz(zza = 2)
    public String getMessageId() {
        return this.b;
    }

    @NonNull
    @zzz(zza = 4)
    public MessageType getMessageType() {
        return this.d;
    }

    @NonNull
    @zzz(zza = 6)
    public String getPackageName() {
        return this.f;
    }

    @zzz(zza = 8)
    public int getPriority() {
        return this.h;
    }

    @zzz(zza = 1)
    public long getProjectNumber() {
        return this.f11358a;
    }

    @NonNull
    @zzz(zza = 5)
    public SDKPlatform getSdkPlatform() {
        return this.e;
    }

    @NonNull
    @zzz(zza = 10)
    public String getTopic() {
        return this.j;
    }

    @zzz(zza = 9)
    public int getTtl() {
        return this.i;
    }
}
