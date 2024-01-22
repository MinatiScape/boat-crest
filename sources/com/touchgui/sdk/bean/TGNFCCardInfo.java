package com.touchgui.sdk.bean;

import androidx.annotation.Nullable;
/* loaded from: classes12.dex */
public class TGNFCCardInfo {
    public int amount;
    public String appCode;
    public String cardAid;
    public int cardDefault;
    public String cardName;
    public String cardNo;
    public int cardStatus;
    public int cardType;
    @Nullable
    public byte[] image;

    public int getAmount() {
        return this.amount;
    }

    public String getAppCode() {
        return this.appCode;
    }

    public String getCardAid() {
        return this.cardAid;
    }

    public int getCardDefault() {
        return this.cardDefault;
    }

    public String getCardName() {
        return this.cardName;
    }

    public String getCardNo() {
        return this.cardNo;
    }

    public int getCardStatus() {
        return this.cardStatus;
    }

    public int getCardType() {
        return this.cardType;
    }

    @Nullable
    public byte[] getImage() {
        return this.image;
    }

    public void setAmount(int i) {
        this.amount = i;
    }

    public void setAppCode(String str) {
        this.appCode = str;
    }

    public void setCardAid(String str) {
        this.cardAid = str;
    }

    public void setCardDefault(int i) {
        this.cardDefault = i;
    }

    public void setCardName(String str) {
        this.cardName = str;
    }

    public void setCardNo(String str) {
        this.cardNo = str;
    }

    public void setCardStatus(int i) {
        this.cardStatus = i;
    }

    public void setCardType(int i) {
        this.cardType = i;
    }

    public void setImage(@Nullable byte[] bArr) {
        this.image = bArr;
    }
}
