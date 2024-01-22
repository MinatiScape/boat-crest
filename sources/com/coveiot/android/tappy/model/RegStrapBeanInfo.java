package com.coveiot.android.tappy.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class RegStrapBeanInfo implements Serializable {
    @Nullable
    private Long deviceId;
    @Nullable
    private Long endUserID;
    @Nullable
    private Long endUserProductRegistrationID;
    @Nullable
    private String friendlyName;
    @Nullable
    private String imageUrl;
    private boolean isCardAdded;
    private boolean isSelected;
    @Nullable
    private String productName;
    @Nullable
    private String productSerialNumber;
    @Nullable
    private RegCardBeanInfo regCardBeanInfo;
    @Nullable
    private String sku;

    public RegStrapBeanInfo() {
        this(null, null, null, null, null, null, null, null, false, false, null, 2047, null);
    }

    public RegStrapBeanInfo(@Nullable Long l, @Nullable Long l2, @Nullable Long l3, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, boolean z, boolean z2, @Nullable RegCardBeanInfo regCardBeanInfo) {
        this.endUserProductRegistrationID = l;
        this.endUserID = l2;
        this.deviceId = l3;
        this.friendlyName = str;
        this.productSerialNumber = str2;
        this.productName = str3;
        this.sku = str4;
        this.imageUrl = str5;
        this.isCardAdded = z;
        this.isSelected = z2;
        this.regCardBeanInfo = regCardBeanInfo;
    }

    @Nullable
    public final Long component1() {
        return this.endUserProductRegistrationID;
    }

    public final boolean component10() {
        return this.isSelected;
    }

    @Nullable
    public final RegCardBeanInfo component11() {
        return this.regCardBeanInfo;
    }

    @Nullable
    public final Long component2() {
        return this.endUserID;
    }

    @Nullable
    public final Long component3() {
        return this.deviceId;
    }

    @Nullable
    public final String component4() {
        return this.friendlyName;
    }

    @Nullable
    public final String component5() {
        return this.productSerialNumber;
    }

    @Nullable
    public final String component6() {
        return this.productName;
    }

    @Nullable
    public final String component7() {
        return this.sku;
    }

    @Nullable
    public final String component8() {
        return this.imageUrl;
    }

    public final boolean component9() {
        return this.isCardAdded;
    }

    @NotNull
    public final RegStrapBeanInfo copy(@Nullable Long l, @Nullable Long l2, @Nullable Long l3, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, boolean z, boolean z2, @Nullable RegCardBeanInfo regCardBeanInfo) {
        return new RegStrapBeanInfo(l, l2, l3, str, str2, str3, str4, str5, z, z2, regCardBeanInfo);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RegStrapBeanInfo) {
            RegStrapBeanInfo regStrapBeanInfo = (RegStrapBeanInfo) obj;
            return Intrinsics.areEqual(this.endUserProductRegistrationID, regStrapBeanInfo.endUserProductRegistrationID) && Intrinsics.areEqual(this.endUserID, regStrapBeanInfo.endUserID) && Intrinsics.areEqual(this.deviceId, regStrapBeanInfo.deviceId) && Intrinsics.areEqual(this.friendlyName, regStrapBeanInfo.friendlyName) && Intrinsics.areEqual(this.productSerialNumber, regStrapBeanInfo.productSerialNumber) && Intrinsics.areEqual(this.productName, regStrapBeanInfo.productName) && Intrinsics.areEqual(this.sku, regStrapBeanInfo.sku) && Intrinsics.areEqual(this.imageUrl, regStrapBeanInfo.imageUrl) && this.isCardAdded == regStrapBeanInfo.isCardAdded && this.isSelected == regStrapBeanInfo.isSelected && Intrinsics.areEqual(this.regCardBeanInfo, regStrapBeanInfo.regCardBeanInfo);
        }
        return false;
    }

    @Nullable
    public final Long getDeviceId() {
        return this.deviceId;
    }

    @Nullable
    public final Long getEndUserID() {
        return this.endUserID;
    }

    @Nullable
    public final Long getEndUserProductRegistrationID() {
        return this.endUserProductRegistrationID;
    }

    @Nullable
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    @Nullable
    public final String getImageUrl() {
        return this.imageUrl;
    }

    @Nullable
    public final String getProductName() {
        return this.productName;
    }

    @Nullable
    public final String getProductSerialNumber() {
        return this.productSerialNumber;
    }

    @Nullable
    public final RegCardBeanInfo getRegCardBeanInfo() {
        return this.regCardBeanInfo;
    }

    @Nullable
    public final String getSku() {
        return this.sku;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        Long l = this.endUserProductRegistrationID;
        int hashCode = (l == null ? 0 : l.hashCode()) * 31;
        Long l2 = this.endUserID;
        int hashCode2 = (hashCode + (l2 == null ? 0 : l2.hashCode())) * 31;
        Long l3 = this.deviceId;
        int hashCode3 = (hashCode2 + (l3 == null ? 0 : l3.hashCode())) * 31;
        String str = this.friendlyName;
        int hashCode4 = (hashCode3 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.productSerialNumber;
        int hashCode5 = (hashCode4 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.productName;
        int hashCode6 = (hashCode5 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.sku;
        int hashCode7 = (hashCode6 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.imageUrl;
        int hashCode8 = (hashCode7 + (str5 == null ? 0 : str5.hashCode())) * 31;
        boolean z = this.isCardAdded;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode8 + i) * 31;
        boolean z2 = this.isSelected;
        int i3 = (i2 + (z2 ? 1 : z2 ? 1 : 0)) * 31;
        RegCardBeanInfo regCardBeanInfo = this.regCardBeanInfo;
        return i3 + (regCardBeanInfo != null ? regCardBeanInfo.hashCode() : 0);
    }

    public final boolean isCardAdded() {
        return this.isCardAdded;
    }

    public final boolean isSelected() {
        return this.isSelected;
    }

    public final void setCardAdded(boolean z) {
        this.isCardAdded = z;
    }

    public final void setDeviceId(@Nullable Long l) {
        this.deviceId = l;
    }

    public final void setEndUserID(@Nullable Long l) {
        this.endUserID = l;
    }

    public final void setEndUserProductRegistrationID(@Nullable Long l) {
        this.endUserProductRegistrationID = l;
    }

    public final void setFriendlyName(@Nullable String str) {
        this.friendlyName = str;
    }

    public final void setImageUrl(@Nullable String str) {
        this.imageUrl = str;
    }

    public final void setProductName(@Nullable String str) {
        this.productName = str;
    }

    public final void setProductSerialNumber(@Nullable String str) {
        this.productSerialNumber = str;
    }

    public final void setRegCardBeanInfo(@Nullable RegCardBeanInfo regCardBeanInfo) {
        this.regCardBeanInfo = regCardBeanInfo;
    }

    public final void setSelected(boolean z) {
        this.isSelected = z;
    }

    public final void setSku(@Nullable String str) {
        this.sku = str;
    }

    @NotNull
    public String toString() {
        return "RegStrapBeanInfo(endUserProductRegistrationID=" + this.endUserProductRegistrationID + ", endUserID=" + this.endUserID + ", deviceId=" + this.deviceId + ", friendlyName=" + this.friendlyName + ", productSerialNumber=" + this.productSerialNumber + ", productName=" + this.productName + ", sku=" + this.sku + ", imageUrl=" + this.imageUrl + ", isCardAdded=" + this.isCardAdded + ", isSelected=" + this.isSelected + ", regCardBeanInfo=" + this.regCardBeanInfo + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ RegStrapBeanInfo(Long l, Long l2, Long l3, String str, String str2, String str3, String str4, String str5, boolean z, boolean z2, RegCardBeanInfo regCardBeanInfo, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : l, (i & 2) != 0 ? null : l2, (i & 4) != 0 ? null : l3, (i & 8) != 0 ? null : str, (i & 16) != 0 ? null : str2, (i & 32) != 0 ? null : str3, (i & 64) != 0 ? null : str4, (i & 128) != 0 ? null : str5, (i & 256) != 0 ? false : z, (i & 512) == 0 ? z2 : false, (i & 1024) == 0 ? regCardBeanInfo : null);
    }
}
