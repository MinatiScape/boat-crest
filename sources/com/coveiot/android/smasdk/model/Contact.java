package com.coveiot.android.smasdk.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class Contact {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f5767a;
    @NotNull
    public String b;

    public Contact(@NotNull String name, @NotNull String phone) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(phone, "phone");
        this.f5767a = name;
        this.b = phone;
    }

    public static /* synthetic */ Contact copy$default(Contact contact, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = contact.f5767a;
        }
        if ((i & 2) != 0) {
            str2 = contact.b;
        }
        return contact.copy(str, str2);
    }

    @NotNull
    public final String component1() {
        return this.f5767a;
    }

    @NotNull
    public final String component2() {
        return this.b;
    }

    @NotNull
    public final Contact copy(@NotNull String name, @NotNull String phone) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(phone, "phone");
        return new Contact(name, phone);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Contact) {
            Contact contact = (Contact) obj;
            return Intrinsics.areEqual(this.f5767a, contact.f5767a) && Intrinsics.areEqual(this.b, contact.b);
        }
        return false;
    }

    @NotNull
    public final String getName() {
        return this.f5767a;
    }

    @NotNull
    public final String getPhone() {
        return this.b;
    }

    public int hashCode() {
        return (this.f5767a.hashCode() * 31) + this.b.hashCode();
    }

    public final void setName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f5767a = str;
    }

    public final void setPhone(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.b = str;
    }

    @NotNull
    public String toString() {
        return "Contact(name=" + this.f5767a + ", phone=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
