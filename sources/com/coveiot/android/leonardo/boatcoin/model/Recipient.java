package com.coveiot.android.leonardo.boatcoin.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class Recipient {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public String f4655a;
    @Nullable
    public String b;
    @Nullable
    public RecipientData c;

    public Recipient() {
        this(null, null, null, 7, null);
    }

    public Recipient(@Nullable String str, @Nullable String str2, @Nullable RecipientData recipientData) {
        this.f4655a = str;
        this.b = str2;
        this.c = recipientData;
    }

    public static /* synthetic */ Recipient copy$default(Recipient recipient, String str, String str2, RecipientData recipientData, int i, Object obj) {
        if ((i & 1) != 0) {
            str = recipient.f4655a;
        }
        if ((i & 2) != 0) {
            str2 = recipient.b;
        }
        if ((i & 4) != 0) {
            recipientData = recipient.c;
        }
        return recipient.copy(str, str2, recipientData);
    }

    @Nullable
    public final String component1() {
        return this.f4655a;
    }

    @Nullable
    public final String component2() {
        return this.b;
    }

    @Nullable
    public final RecipientData component3() {
        return this.c;
    }

    @NotNull
    public final Recipient copy(@Nullable String str, @Nullable String str2, @Nullable RecipientData recipientData) {
        return new Recipient(str, str2, recipientData);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Recipient) {
            Recipient recipient = (Recipient) obj;
            return Intrinsics.areEqual(this.f4655a, recipient.f4655a) && Intrinsics.areEqual(this.b, recipient.b) && Intrinsics.areEqual(this.c, recipient.c);
        }
        return false;
    }

    @Nullable
    public final RecipientData getData() {
        return this.c;
    }

    @Nullable
    public final String getMessage() {
        return this.b;
    }

    @Nullable
    public final String getStatus() {
        return this.f4655a;
    }

    public int hashCode() {
        String str = this.f4655a;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.b;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        RecipientData recipientData = this.c;
        return hashCode2 + (recipientData != null ? recipientData.hashCode() : 0);
    }

    public final void setData(@Nullable RecipientData recipientData) {
        this.c = recipientData;
    }

    public final void setMessage(@Nullable String str) {
        this.b = str;
    }

    public final void setStatus(@Nullable String str) {
        this.f4655a = str;
    }

    @NotNull
    public String toString() {
        return "Recipient(status=" + this.f4655a + ", message=" + this.b + ", data=" + this.c + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ Recipient(String str, String str2, RecipientData recipientData, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : recipientData);
    }
}
