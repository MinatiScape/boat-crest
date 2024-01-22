package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleReadable;
import com.bestmafen.baseble.data.BleWritable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.s;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class BleNotificationSettings extends BleWritable {
    @NotNull
    public static final String BAND = "bandapp";
    @NotNull
    public static final String BETWEEN = "between";
    @NotNull
    public static final String CALL = "tel";
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String EMAIL = "mailto";
    @NotNull
    public static final String FACEBOOK_MESSENGER = "fbauth2";
    @NotNull
    public static final String GMAIL = "googlegmail";
    @NotNull
    public static final String INSTAGRAM = "instagram";
    public static final int ITEM_LENGTH = 4;
    @NotNull
    public static final String KAKAO_TALK = "kakaolink";
    @NotNull
    public static final String LINE = "line";
    @NotNull
    public static final String LINKED_IN = "linkedin";
    @NotNull
    public static final String MIRROR_PHONE = "mirror_phone";
    @NotNull
    public static final String NAVERCAFE = "navercafe";
    @NotNull
    public static final String NETFLIX = "nflx";
    @NotNull
    public static final String QQ = "mqq";
    @NotNull
    public static final String SINA_WEIBO = "sinaweibo";
    @NotNull
    public static final String SKYPE = "skype";
    @NotNull
    public static final String SMS = "sms";
    @NotNull
    public static final String TELEGRAM = "telegram";
    @NotNull
    public static final String TWITTER = "twitter";
    @NotNull
    public static final String WE_CHAT = "wechat";
    @NotNull
    public static final String WHATS_APP = "whatsapp";
    @NotNull
    public static final String YOUTUBE = "youtube";
    @NotNull
    private final Map<String, Integer> BIT_MASKS;
    private int mNotificationBits;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleNotificationSettings() {
        Map<String, Integer> mapOf = s.mapOf(TuplesKt.to(MIRROR_PHONE, 1), TuplesKt.to(CALL, 2), TuplesKt.to(SMS, 4), TuplesKt.to(EMAIL, 8), TuplesKt.to(SKYPE, 16), TuplesKt.to(FACEBOOK_MESSENGER, 32), TuplesKt.to(WHATS_APP, 64), TuplesKt.to("line", 128), TuplesKt.to(INSTAGRAM, 256), TuplesKt.to(KAKAO_TALK, 512), TuplesKt.to(GMAIL, 1024), TuplesKt.to(TWITTER, 2048), TuplesKt.to(LINKED_IN, 4096), TuplesKt.to(SINA_WEIBO, 8192), TuplesKt.to(QQ, 16384), TuplesKt.to(WE_CHAT, 32768), TuplesKt.to(BAND, 65536), TuplesKt.to(TELEGRAM, 131072), TuplesKt.to(BETWEEN, 262144), TuplesKt.to(NAVERCAFE, 524288), TuplesKt.to(YOUTUBE, 1048576), TuplesKt.to(NETFLIX, 2097152));
        this.BIT_MASKS = mapOf;
        for (String str : mapOf.keySet()) {
            if (!Intrinsics.areEqual(str, CALL)) {
                enable(str);
            }
        }
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        this.mNotificationBits = BleReadable.readInt32$default(this, null, 1, null);
    }

    public final void disable(@NotNull String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        Integer num = this.BIT_MASKS.get(id);
        if (num != null) {
            int intValue = num.intValue();
            this.mNotificationBits = (~intValue) & this.mNotificationBits;
        }
    }

    public final void enable(@NotNull String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        Integer num = this.BIT_MASKS.get(id);
        if (num != null) {
            this.mNotificationBits = num.intValue() | this.mNotificationBits;
        }
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public void encode() {
        super.encode();
        BleWritable.writeInt32$default(this, this.mNotificationBits, null, 2, null);
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        return 4;
    }

    public final int getMNotificationBits() {
        return this.mNotificationBits;
    }

    public final boolean isEnabled(@NotNull String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        Integer num = this.BIT_MASKS.get(id);
        return num != null && (num.intValue() & this.mNotificationBits) > 0;
    }

    public final void setMNotificationBits(int i) {
        this.mNotificationBits = i;
    }

    @NotNull
    public String toString() {
        return "BleNotificationSettings(mNotificationBits=" + Integer.toBinaryString(this.mNotificationBits) + HexStringBuilder.COMMENT_END_CHAR;
    }

    public final void toggle(@NotNull String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        Integer num = this.BIT_MASKS.get(id);
        if (num != null) {
            this.mNotificationBits = num.intValue() ^ this.mNotificationBits;
        }
    }
}
