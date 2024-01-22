package com.coveiot.android.bleabstract.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class UserProfileDetails {

    /* renamed from: a  reason: collision with root package name */
    public final int f3452a;
    public final double b;
    public final int c;
    public final int d;
    public boolean e;

    public UserProfileDetails(int i, double d, int i2, int i3) {
        this.f3452a = i;
        this.b = d;
        this.c = i2;
        this.d = i3;
    }

    public static /* synthetic */ UserProfileDetails copy$default(UserProfileDetails userProfileDetails, int i, double d, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = userProfileDetails.f3452a;
        }
        if ((i4 & 2) != 0) {
            d = userProfileDetails.b;
        }
        double d2 = d;
        if ((i4 & 4) != 0) {
            i2 = userProfileDetails.c;
        }
        int i5 = i2;
        if ((i4 & 8) != 0) {
            i3 = userProfileDetails.d;
        }
        return userProfileDetails.copy(i, d2, i5, i3);
    }

    public final int component1() {
        return this.f3452a;
    }

    public final double component2() {
        return this.b;
    }

    public final int component3() {
        return this.c;
    }

    public final int component4() {
        return this.d;
    }

    @NotNull
    public final UserProfileDetails copy(int i, double d, int i2, int i3) {
        return new UserProfileDetails(i, d, i2, i3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof UserProfileDetails) {
            UserProfileDetails userProfileDetails = (UserProfileDetails) obj;
            return this.f3452a == userProfileDetails.f3452a && Double.compare(this.b, userProfileDetails.b) == 0 && this.c == userProfileDetails.c && this.d == userProfileDetails.d;
        }
        return false;
    }

    public final int getAge() {
        return this.c;
    }

    public final int getGender() {
        return this.d;
    }

    public final int getHeight() {
        return this.f3452a;
    }

    public final double getWeight() {
        return this.b;
    }

    public int hashCode() {
        return (((((Integer.hashCode(this.f3452a) * 31) + Double.hashCode(this.b)) * 31) + Integer.hashCode(this.c)) * 31) + Integer.hashCode(this.d);
    }

    public final boolean isExistingUser() {
        return this.e;
    }

    public final void setExistingUser(boolean z) {
        this.e = z;
    }

    @NotNull
    public String toString() {
        return "UserProfileDetails(height=" + this.f3452a + ", weight=" + this.b + ", age=" + this.c + ", gender=" + this.d + HexStringBuilder.COMMENT_END_CHAR;
    }
}
