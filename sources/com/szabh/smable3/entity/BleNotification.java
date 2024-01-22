package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleWritable;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleNotification extends BleWritable {
    @NotNull
    public static final String ANDROID_EMAIL = "com.android.email";
    @NotNull
    public static final String ANDROID_INCALLUI = "com.android.incallui";
    @NotNull
    public static final String ANDROID_MMS = "com.android.mms";
    @NotNull
    public static final String ANDROID_MMS_SERVICE = "com.android.mms.service";
    @NotNull
    public static final String ANDROID_TELECOM = "com.android.server.telecom";
    @NotNull
    public static final String BAND = "com.nhn.android.band";
    @NotNull
    public static final String BETWEEN = "kr.co.vcnc.android.couple";
    public static final int CATEGORY_INCOMING_CALL = 1;
    public static final int CATEGORY_MESSAGE = 127;
    public static final int CONTENT_MAX_LENGTH = 250;
    @NotNull
    public static final String DISCORD = "com.discord";
    @NotNull
    public static final String FACEBOOK = "com.facebook.katana";
    @NotNull
    public static final String FACEBOOK_LITE = "com.facebook.lite";
    @NotNull
    public static final String FACEBOOK_MESSENGER = "com.facebook.orca";
    @NotNull
    public static final String FACEBOOK_MESSENGER_LITE = "com.facebook.mlite";
    @NotNull
    public static final String GOOGLE_DUO = "com.google.android.apps.tachyon";
    @NotNull
    public static final String GOOGLE_MMS = "com.google.android.apps.messaging";
    @NotNull
    public static final String GOOGLE_TELECOM = "com.google.android.dialer";
    @NotNull
    public static final String HUAWEI_SYSTEM_MANAGER = "com.huawei.systemmanager";
    @NotNull
    public static final String INSTAGRAM = "com.instagram.android";
    @NotNull
    public static final String KAKAO_TALK = "com.kakao.talk";
    @NotNull
    public static final String LINE = "jp.naver.line.android";
    @NotNull
    public static final String LINKED_IN = "com.linkedin.android";
    @NotNull
    public static final String NAVERCAFE = "com.nhn.android.navercafe";
    @NotNull
    public static final String NETFLIX = "com.netflix.mediaclient";
    @NotNull
    public static final String ONE_PLUS_MMS = "com.oneplus.mms";
    @NotNull
    public static final String ONE_PLUS_TELECOM = "com.oneplus.dialer";
    public static final int PACKAGE_LENGTH = 32;
    @NotNull
    public static final String PACKAGE_MISSED_CALL = "com.android.mobilephone";
    @NotNull
    public static final String PACKAGE_SMS = "com.android.mms";
    public static final int PHONE_LENGTH = 32;
    @NotNull
    public static final String QQ = "com.tencent.mobileqq";
    @NotNull
    public static final String SAMSUNG_INCALLUI = "com.samsung.android.incallui";
    @NotNull
    public static final String SAMSUNG_MMS = "com.samsung.android.messaging";
    @NotNull
    public static final String SAMSUNG_TELECOM = "com.samsung.android.dialer";
    @NotNull
    public static final String SINA_WEIBO = "com.sina.weibo";
    @NotNull
    public static final String SKYPE = "com.skype.raider";
    @NotNull
    public static final String SNAPCHAT = "com.snapchat.android";
    @NotNull
    public static final String TELEGRAM = "org.telegram.messenger";
    public static final int TITLE_LENGTH = 32;
    @NotNull
    public static final String TWITTER = "com.twitter.android";
    @NotNull
    public static final String VIBER = "com.viber.voip";
    @NotNull
    public static final String WE_CHAT = "com.tencent.mm";
    @NotNull
    public static final String WHATS_APP = "com.whatsapp";
    @NotNull
    public static final String YAHOO_MAIL = "com.yahoo.mobile.client.android.mail";
    @NotNull
    public static final String YOUTUBE = "com.google.android.youtube";
    private int mCategory;
    @NotNull
    private String mContent;
    @NotNull
    private String mPackage;
    private long mTime;
    @NotNull
    private String mTitle;
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String GMAIL = "com.google.android.gm";
    @NotNull
    public static final String OUT_LOOK = "com.microsoft.office.outlook";
    @NotNull
    public static final String OUT_LOOK2 = "park.outlook.sign.in.client";
    @NotNull
    private static final List<String> sEmailPackages = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{GMAIL, OUT_LOOK, OUT_LOOK2});

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean isEmail(@NotNull String packageName) {
            Intrinsics.checkNotNullParameter(packageName, "packageName");
            return StringsKt__StringsKt.contains$default((CharSequence) packageName, (CharSequence) "mail", false, 2, (Object) null) || BleNotification.sEmailPackages.contains(packageName);
        }
    }

    public BleNotification() {
        this(0, 0L, null, null, null, 31, null);
    }

    public /* synthetic */ BleNotification(int i, long j, String str, String str2, String str3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? 0L : j, (i2 & 4) != 0 ? "" : str, (i2 & 8) != 0 ? "" : str2, (i2 & 16) != 0 ? "" : str3);
    }

    public static /* synthetic */ BleNotification copy$default(BleNotification bleNotification, int i, long j, String str, String str2, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = bleNotification.mCategory;
        }
        if ((i2 & 2) != 0) {
            j = bleNotification.mTime;
        }
        long j2 = j;
        if ((i2 & 4) != 0) {
            str = bleNotification.mPackage;
        }
        String str4 = str;
        if ((i2 & 8) != 0) {
            str2 = bleNotification.mTitle;
        }
        String str5 = str2;
        if ((i2 & 16) != 0) {
            str3 = bleNotification.mContent;
        }
        return bleNotification.copy(i, j2, str4, str5, str3);
    }

    public final int component1() {
        return this.mCategory;
    }

    public final long component2() {
        return this.mTime;
    }

    @NotNull
    public final String component3() {
        return this.mPackage;
    }

    @NotNull
    public final String component4() {
        return this.mTitle;
    }

    @NotNull
    public final String component5() {
        return this.mContent;
    }

    @NotNull
    public final BleNotification copy(int i, long j, @NotNull String mPackage, @NotNull String mTitle, @NotNull String mContent) {
        Intrinsics.checkNotNullParameter(mPackage, "mPackage");
        Intrinsics.checkNotNullParameter(mTitle, "mTitle");
        Intrinsics.checkNotNullParameter(mContent, "mContent");
        return new BleNotification(i, j, mPackage, mTitle, mContent);
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public void encode() {
        super.encode();
        writeInt8(this.mCategory);
        writeObject(BleTime.Companion.ofLocal(this.mTime));
        BleWritable.writeStringWithFix$default(this, this.mPackage, 32, null, 4, null);
        BleWritable.writeStringWithFix$default(this, this.mTitle, 32, null, 4, null);
        BleWritable.writeStringWithLimit$default(this, this.mContent, 250, null, true, 4, null);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleNotification) {
            BleNotification bleNotification = (BleNotification) obj;
            return this.mCategory == bleNotification.mCategory && this.mTime == bleNotification.mTime && Intrinsics.areEqual(this.mPackage, bleNotification.mPackage) && Intrinsics.areEqual(this.mTitle, bleNotification.mTitle) && Intrinsics.areEqual(this.mContent, bleNotification.mContent);
        }
        return false;
    }

    public final int getMCategory() {
        return this.mCategory;
    }

    @NotNull
    public final String getMContent() {
        return this.mContent;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        byte[] bytes = this.mContent.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        return Math.min(bytes.length, 250) + 71;
    }

    @NotNull
    public final String getMPackage() {
        return this.mPackage;
    }

    public final long getMTime() {
        return this.mTime;
    }

    @NotNull
    public final String getMTitle() {
        return this.mTitle;
    }

    public int hashCode() {
        return (((((((Integer.hashCode(this.mCategory) * 31) + Long.hashCode(this.mTime)) * 31) + this.mPackage.hashCode()) * 31) + this.mTitle.hashCode()) * 31) + this.mContent.hashCode();
    }

    public final void setMCategory(int i) {
        this.mCategory = i;
    }

    public final void setMContent(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mContent = str;
    }

    public final void setMPackage(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mPackage = str;
    }

    public final void setMTime(long j) {
        this.mTime = j;
    }

    public final void setMTitle(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mTitle = str;
    }

    @NotNull
    public String toString() {
        return "BleNotification(mCategory=" + this.mCategory + ", mTime=" + this.mTime + ", mPackage='" + this.mPackage + "', mTitle='" + this.mTitle + "', mContent='" + this.mContent + "')";
    }

    public BleNotification(int i, long j, @NotNull String mPackage, @NotNull String mTitle, @NotNull String mContent) {
        Intrinsics.checkNotNullParameter(mPackage, "mPackage");
        Intrinsics.checkNotNullParameter(mTitle, "mTitle");
        Intrinsics.checkNotNullParameter(mContent, "mContent");
        this.mCategory = i;
        this.mTime = j;
        this.mPackage = mPackage;
        this.mTitle = mTitle;
        this.mContent = mContent;
    }
}
