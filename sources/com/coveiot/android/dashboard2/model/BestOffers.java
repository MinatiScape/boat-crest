package com.coveiot.android.dashboard2.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class BestOffers {
    @NotNull
    private final String imageUrl;
    @NotNull
    private final String redirectionType;
    @NotNull
    private final String redirectionUrl;

    public BestOffers(@NotNull String imageUrl, @NotNull String redirectionUrl, @NotNull String redirectionType) {
        Intrinsics.checkNotNullParameter(imageUrl, "imageUrl");
        Intrinsics.checkNotNullParameter(redirectionUrl, "redirectionUrl");
        Intrinsics.checkNotNullParameter(redirectionType, "redirectionType");
        this.imageUrl = imageUrl;
        this.redirectionUrl = redirectionUrl;
        this.redirectionType = redirectionType;
    }

    public static /* synthetic */ BestOffers copy$default(BestOffers bestOffers, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = bestOffers.imageUrl;
        }
        if ((i & 2) != 0) {
            str2 = bestOffers.redirectionUrl;
        }
        if ((i & 4) != 0) {
            str3 = bestOffers.redirectionType;
        }
        return bestOffers.copy(str, str2, str3);
    }

    @NotNull
    public final String component1() {
        return this.imageUrl;
    }

    @NotNull
    public final String component2() {
        return this.redirectionUrl;
    }

    @NotNull
    public final String component3() {
        return this.redirectionType;
    }

    @NotNull
    public final BestOffers copy(@NotNull String imageUrl, @NotNull String redirectionUrl, @NotNull String redirectionType) {
        Intrinsics.checkNotNullParameter(imageUrl, "imageUrl");
        Intrinsics.checkNotNullParameter(redirectionUrl, "redirectionUrl");
        Intrinsics.checkNotNullParameter(redirectionType, "redirectionType");
        return new BestOffers(imageUrl, redirectionUrl, redirectionType);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BestOffers) {
            BestOffers bestOffers = (BestOffers) obj;
            return Intrinsics.areEqual(this.imageUrl, bestOffers.imageUrl) && Intrinsics.areEqual(this.redirectionUrl, bestOffers.redirectionUrl) && Intrinsics.areEqual(this.redirectionType, bestOffers.redirectionType);
        }
        return false;
    }

    @NotNull
    public final String getImageUrl() {
        return this.imageUrl;
    }

    @NotNull
    public final String getRedirectionType() {
        return this.redirectionType;
    }

    @NotNull
    public final String getRedirectionUrl() {
        return this.redirectionUrl;
    }

    public int hashCode() {
        return (((this.imageUrl.hashCode() * 31) + this.redirectionUrl.hashCode()) * 31) + this.redirectionType.hashCode();
    }

    @NotNull
    public String toString() {
        return "BestOffers(imageUrl=" + this.imageUrl + ", redirectionUrl=" + this.redirectionUrl + ", redirectionType=" + this.redirectionType + HexStringBuilder.COMMENT_END_CHAR;
    }
}
