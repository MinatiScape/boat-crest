package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleReadable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleRealtimeLog extends BleReadable {
    @NotNull
    private String mContent;

    public BleRealtimeLog() {
        this(null, 1, null);
    }

    public BleRealtimeLog(@NotNull String mContent) {
        Intrinsics.checkNotNullParameter(mContent, "mContent");
        this.mContent = mContent;
    }

    public static /* synthetic */ BleRealtimeLog copy$default(BleRealtimeLog bleRealtimeLog, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = bleRealtimeLog.mContent;
        }
        return bleRealtimeLog.copy(str);
    }

    @NotNull
    public final String component1() {
        return this.mContent;
    }

    @NotNull
    public final BleRealtimeLog copy(@NotNull String mContent) {
        Intrinsics.checkNotNullParameter(mContent, "mContent");
        return new BleRealtimeLog(mContent);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        String str;
        super.decode();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS    ", Locale.getDefault());
        if (getMBytes() != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(simpleDateFormat.format(new Date()));
            byte[] mBytes = getMBytes();
            Intrinsics.checkNotNull(mBytes);
            Charset UTF_8 = StandardCharsets.UTF_8;
            Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
            sb.append(new String(mBytes, UTF_8));
            str = sb.toString();
        } else {
            str = simpleDateFormat.format(new Date()) + "null";
        }
        this.mContent = str;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof BleRealtimeLog) && Intrinsics.areEqual(this.mContent, ((BleRealtimeLog) obj).mContent);
    }

    @NotNull
    public final String getMContent() {
        return this.mContent;
    }

    public int hashCode() {
        return this.mContent.hashCode();
    }

    public final void setMContent(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mContent = str;
    }

    @NotNull
    public String toString() {
        return "BleRealtimeLog(mContent=" + this.mContent + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ BleRealtimeLog(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str);
    }
}
