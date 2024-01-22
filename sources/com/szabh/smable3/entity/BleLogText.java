package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleReadable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleLogText extends BleReadable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 64;
    @NotNull
    private String mContent;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleLogText() {
        this(null, 1, null);
    }

    public /* synthetic */ BleLogText(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str);
    }

    public static /* synthetic */ BleLogText copy$default(BleLogText bleLogText, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = bleLogText.mContent;
        }
        return bleLogText.copy(str);
    }

    @NotNull
    public final String component1() {
        return this.mContent;
    }

    @NotNull
    public final BleLogText copy(@NotNull String mContent) {
        Intrinsics.checkNotNullParameter(mContent, "mContent");
        return new BleLogText(mContent);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        String str;
        super.decode();
        if (getMBytes() != null) {
            byte[] mBytes = getMBytes();
            Intrinsics.checkNotNull(mBytes);
            if (!(mBytes.length == 0)) {
                byte[] mBytes2 = getMBytes();
                Intrinsics.checkNotNull(mBytes2);
                int indexOf = ArraysKt___ArraysKt.indexOf(mBytes2, (byte) 0);
                if (indexOf == -1) {
                    byte[] mBytes3 = getMBytes();
                    Intrinsics.checkNotNull(mBytes3);
                    Charset UTF_8 = StandardCharsets.UTF_8;
                    Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
                    str = new String(mBytes3, UTF_8);
                } else {
                    byte[] mBytes4 = getMBytes();
                    Intrinsics.checkNotNull(mBytes4);
                    Charset UTF_82 = StandardCharsets.UTF_8;
                    Intrinsics.checkNotNullExpressionValue(UTF_82, "UTF_8");
                    str = new String(mBytes4, 0, indexOf, UTF_82);
                }
                this.mContent = str;
            }
        }
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof BleLogText) && Intrinsics.areEqual(this.mContent, ((BleLogText) obj).mContent);
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
        return "BleLogText(mContent=" + this.mContent + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleLogText(@NotNull String mContent) {
        Intrinsics.checkNotNullParameter(mContent, "mContent");
        this.mContent = mContent;
    }
}
