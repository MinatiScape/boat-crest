package com.coveiot.android.leonardo.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class MyWatchFeatureModel {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public MyWatchFeatureType f4856a;
    public boolean b;

    public MyWatchFeatureModel() {
        this(null, false, 3, null);
    }

    public MyWatchFeatureModel(@Nullable MyWatchFeatureType myWatchFeatureType, boolean z) {
        this.f4856a = myWatchFeatureType;
        this.b = z;
    }

    public static /* synthetic */ MyWatchFeatureModel copy$default(MyWatchFeatureModel myWatchFeatureModel, MyWatchFeatureType myWatchFeatureType, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            myWatchFeatureType = myWatchFeatureModel.f4856a;
        }
        if ((i & 2) != 0) {
            z = myWatchFeatureModel.b;
        }
        return myWatchFeatureModel.copy(myWatchFeatureType, z);
    }

    @Nullable
    public final MyWatchFeatureType component1() {
        return this.f4856a;
    }

    public final boolean component2() {
        return this.b;
    }

    @NotNull
    public final MyWatchFeatureModel copy(@Nullable MyWatchFeatureType myWatchFeatureType, boolean z) {
        return new MyWatchFeatureModel(myWatchFeatureType, z);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof MyWatchFeatureModel) {
            MyWatchFeatureModel myWatchFeatureModel = (MyWatchFeatureModel) obj;
            return this.f4856a == myWatchFeatureModel.f4856a && this.b == myWatchFeatureModel.b;
        }
        return false;
    }

    @Nullable
    public final MyWatchFeatureType getMyWatchFeatureType() {
        return this.f4856a;
    }

    public final boolean getShow() {
        return this.b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        MyWatchFeatureType myWatchFeatureType = this.f4856a;
        int hashCode = (myWatchFeatureType == null ? 0 : myWatchFeatureType.hashCode()) * 31;
        boolean z = this.b;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode + i;
    }

    public final void setMyWatchFeatureType(@Nullable MyWatchFeatureType myWatchFeatureType) {
        this.f4856a = myWatchFeatureType;
    }

    public final void setShow(boolean z) {
        this.b = z;
    }

    @NotNull
    public String toString() {
        return "MyWatchFeatureModel(myWatchFeatureType=" + this.f4856a + ", show=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ MyWatchFeatureModel(MyWatchFeatureType myWatchFeatureType, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : myWatchFeatureType, (i & 2) != 0 ? true : z);
    }
}
