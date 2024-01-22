package com.coveiot.coveaccess.tappy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class SProduct implements Serializable {
    @SerializedName("AllowMultipleTokens")
    @Expose
    private boolean allowMultipleTokens;
    @SerializedName("BrandID")
    @Expose
    private long brandID;
    @SerializedName("ChipsetID")
    @Expose
    private long chipsetID;
    @SerializedName("ChipsetName")
    @Expose
    private String chipsetName;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("ImageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("ProductID")
    @Expose
    private long productID;
    @SerializedName("SKU")
    @Expose
    private String sKU;

    public long getBrandID() {
        return this.brandID;
    }

    public long getChipsetID() {
        return this.chipsetID;
    }

    public String getChipsetName() {
        return this.chipsetName;
    }

    public String getDescription() {
        return this.description;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public String getName() {
        return this.name;
    }

    public long getProductID() {
        return this.productID;
    }

    public String getsKU() {
        return this.sKU;
    }

    public boolean isAllowMultipleTokens() {
        return this.allowMultipleTokens;
    }

    public void setAllowMultipleTokens(boolean z) {
        this.allowMultipleTokens = z;
    }

    public void setBrandID(long j) {
        this.brandID = j;
    }

    public void setChipsetID(long j) {
        this.chipsetID = j;
    }

    public void setChipsetName(String str) {
        this.chipsetName = str;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setImageUrl(String str) {
        this.imageUrl = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setProductID(long j) {
        this.productID = j;
    }

    public void setsKU(String str) {
        this.sKU = str;
    }
}
