package com.coveiot.android.tappy.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class SupportedBank implements Serializable {
    @NotNull
    private String bankID;
    private int bankIcon;
    @NotNull
    private String bankName;

    public SupportedBank(int i, @NotNull String bankName, @NotNull String bankID) {
        Intrinsics.checkNotNullParameter(bankName, "bankName");
        Intrinsics.checkNotNullParameter(bankID, "bankID");
        this.bankIcon = i;
        this.bankName = bankName;
        this.bankID = bankID;
    }

    public static /* synthetic */ SupportedBank copy$default(SupportedBank supportedBank, int i, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = supportedBank.bankIcon;
        }
        if ((i2 & 2) != 0) {
            str = supportedBank.bankName;
        }
        if ((i2 & 4) != 0) {
            str2 = supportedBank.bankID;
        }
        return supportedBank.copy(i, str, str2);
    }

    public final int component1() {
        return this.bankIcon;
    }

    @NotNull
    public final String component2() {
        return this.bankName;
    }

    @NotNull
    public final String component3() {
        return this.bankID;
    }

    @NotNull
    public final SupportedBank copy(int i, @NotNull String bankName, @NotNull String bankID) {
        Intrinsics.checkNotNullParameter(bankName, "bankName");
        Intrinsics.checkNotNullParameter(bankID, "bankID");
        return new SupportedBank(i, bankName, bankID);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SupportedBank) {
            SupportedBank supportedBank = (SupportedBank) obj;
            return this.bankIcon == supportedBank.bankIcon && Intrinsics.areEqual(this.bankName, supportedBank.bankName) && Intrinsics.areEqual(this.bankID, supportedBank.bankID);
        }
        return false;
    }

    @NotNull
    public final String getBankID() {
        return this.bankID;
    }

    public final int getBankIcon() {
        return this.bankIcon;
    }

    @NotNull
    public final String getBankName() {
        return this.bankName;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.bankIcon) * 31) + this.bankName.hashCode()) * 31) + this.bankID.hashCode();
    }

    public final void setBankID(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.bankID = str;
    }

    public final void setBankIcon(int i) {
        this.bankIcon = i;
    }

    public final void setBankName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.bankName = str;
    }

    @NotNull
    public String toString() {
        return "SupportedBank(bankIcon=" + this.bankIcon + ", bankName=" + this.bankName + ", bankID=" + this.bankID + HexStringBuilder.COMMENT_END_CHAR;
    }
}
