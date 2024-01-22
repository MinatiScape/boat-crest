package com.coveiot.android.bleabstract.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ContactData {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f3420a;
    @NotNull
    public final String b;

    public ContactData(@NotNull String name, @NotNull String mobileNumber) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(mobileNumber, "mobileNumber");
        this.f3420a = name;
        this.b = mobileNumber;
    }

    public static /* synthetic */ ContactData copy$default(ContactData contactData, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = contactData.f3420a;
        }
        if ((i & 2) != 0) {
            str2 = contactData.b;
        }
        return contactData.copy(str, str2);
    }

    @NotNull
    public final String component1() {
        return this.f3420a;
    }

    @NotNull
    public final String component2() {
        return this.b;
    }

    @NotNull
    public final ContactData copy(@NotNull String name, @NotNull String mobileNumber) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(mobileNumber, "mobileNumber");
        return new ContactData(name, mobileNumber);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ContactData) {
            ContactData contactData = (ContactData) obj;
            return Intrinsics.areEqual(this.f3420a, contactData.f3420a) && Intrinsics.areEqual(this.b, contactData.b);
        }
        return false;
    }

    @NotNull
    public final String getMobileNumber() {
        return this.b;
    }

    @NotNull
    public final String getName() {
        return this.f3420a;
    }

    public int hashCode() {
        return (this.f3420a.hashCode() * 31) + this.b.hashCode();
    }

    @NotNull
    public String toString() {
        return "ContactData(name=" + this.f3420a + ", mobileNumber=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
