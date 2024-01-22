package com.coveiot.android.tappy.model;

import java.io.Serializable;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class ProductDetails implements Serializable {
    private boolean allowMultipleTokens;
    private long brandId;
    private long chipsetId;
    @Nullable
    private String chipsetName;
    @Nullable
    private String description;
    @Nullable
    private String imageUrl;
    @Nullable
    private String name;
    private long productId;
    @Nullable
    private String sKU;

    public final boolean getAllowMultipleTokens() {
        return this.allowMultipleTokens;
    }

    public final long getBrandId() {
        return this.brandId;
    }

    public final long getChipsetId() {
        return this.chipsetId;
    }

    @Nullable
    public final String getChipsetName() {
        return this.chipsetName;
    }

    @Nullable
    public final String getDescription() {
        return this.description;
    }

    @Nullable
    public final String getImageUrl() {
        return this.imageUrl;
    }

    @Nullable
    public final String getName() {
        return this.name;
    }

    public final long getProductId() {
        return this.productId;
    }

    @Nullable
    public final String getSKU() {
        return this.sKU;
    }

    public final void setAllowMultipleTokens(boolean z) {
        this.allowMultipleTokens = z;
    }

    public final void setBrandId(long j) {
        this.brandId = j;
    }

    public final void setChipsetId(long j) {
        this.chipsetId = j;
    }

    public final void setChipsetName(@Nullable String str) {
        this.chipsetName = str;
    }

    public final void setDescription(@Nullable String str) {
        this.description = str;
    }

    public final void setImageUrl(@Nullable String str) {
        this.imageUrl = str;
    }

    public final void setName(@Nullable String str) {
        this.name = str;
    }

    public final void setProductId(long j) {
        this.productId = j;
    }

    public final void setSKU(@Nullable String str) {
        this.sKU = str;
    }
}
