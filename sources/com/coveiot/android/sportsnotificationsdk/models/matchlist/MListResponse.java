package com.coveiot.android.sportsnotificationsdk.models.matchlist;

import com.google.android.material.color.c;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u001b\u0010\u001cR*\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR$\u0010\u0012\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u001a\u001a\u0004\u0018\u00010\u00138\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006\u001d"}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/models/matchlist/MListResponse;", "", "", "Lcom/coveiot/android/sportsnotificationsdk/models/matchlist/Item;", "a", "Ljava/util/List;", "getItems", "()Ljava/util/List;", "setItems", "(Ljava/util/List;)V", FirebaseAnalytics.Param.ITEMS, "", "b", "Ljava/lang/String;", "getTotalItems", "()Ljava/lang/String;", "setTotalItems", "(Ljava/lang/String;)V", "totalItems", "", c.f10260a, "Ljava/lang/Integer;", "getTotalPages", "()Ljava/lang/Integer;", "setTotalPages", "(Ljava/lang/Integer;)V", "totalPages", "<init>", "()V", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public final class MListResponse {
    @SerializedName(FirebaseAnalytics.Param.ITEMS)
    @Expose
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private List<Item> f5944a;
    @SerializedName("total_items")
    @Expose
    @Nullable
    private String b;
    @SerializedName("total_pages")
    @Expose
    @Nullable
    private Integer c;

    @Nullable
    public final List<Item> getItems() {
        return this.f5944a;
    }

    @Nullable
    public final String getTotalItems() {
        return this.b;
    }

    @Nullable
    public final Integer getTotalPages() {
        return this.c;
    }

    public final void setItems(@Nullable List<Item> list) {
        this.f5944a = list;
    }

    public final void setTotalItems(@Nullable String str) {
        this.b = str;
    }

    public final void setTotalPages(@Nullable Integer num) {
        this.c = num;
    }
}
