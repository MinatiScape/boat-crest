package com.coveiot.android.leonardo.more.models;

import com.coveiot.android.leonardo.model.ShowHideTypeModel;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ShowHideListModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public ArrayList<ShowHideTypeModel> f5136a;
    @NotNull
    public ArrayList<ShowHideTypeModel> b;

    public ShowHideListModel(@NotNull ArrayList<ShowHideTypeModel> showList, @NotNull ArrayList<ShowHideTypeModel> hideList) {
        Intrinsics.checkNotNullParameter(showList, "showList");
        Intrinsics.checkNotNullParameter(hideList, "hideList");
        this.f5136a = showList;
        this.b = hideList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ShowHideListModel copy$default(ShowHideListModel showHideListModel, ArrayList arrayList, ArrayList arrayList2, int i, Object obj) {
        if ((i & 1) != 0) {
            arrayList = showHideListModel.f5136a;
        }
        if ((i & 2) != 0) {
            arrayList2 = showHideListModel.b;
        }
        return showHideListModel.copy(arrayList, arrayList2);
    }

    @NotNull
    public final ArrayList<ShowHideTypeModel> component1() {
        return this.f5136a;
    }

    @NotNull
    public final ArrayList<ShowHideTypeModel> component2() {
        return this.b;
    }

    @NotNull
    public final ShowHideListModel copy(@NotNull ArrayList<ShowHideTypeModel> showList, @NotNull ArrayList<ShowHideTypeModel> hideList) {
        Intrinsics.checkNotNullParameter(showList, "showList");
        Intrinsics.checkNotNullParameter(hideList, "hideList");
        return new ShowHideListModel(showList, hideList);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ShowHideListModel) {
            ShowHideListModel showHideListModel = (ShowHideListModel) obj;
            return Intrinsics.areEqual(this.f5136a, showHideListModel.f5136a) && Intrinsics.areEqual(this.b, showHideListModel.b);
        }
        return false;
    }

    @NotNull
    public final ArrayList<ShowHideTypeModel> getHideList() {
        return this.b;
    }

    @NotNull
    public final ArrayList<ShowHideTypeModel> getShowList() {
        return this.f5136a;
    }

    public int hashCode() {
        return (this.f5136a.hashCode() * 31) + this.b.hashCode();
    }

    public final void setHideList(@NotNull ArrayList<ShowHideTypeModel> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.b = arrayList;
    }

    public final void setShowList(@NotNull ArrayList<ShowHideTypeModel> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.f5136a = arrayList;
    }

    @NotNull
    public String toString() {
        return "ShowHideListModel(showList=" + this.f5136a + ", hideList=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
