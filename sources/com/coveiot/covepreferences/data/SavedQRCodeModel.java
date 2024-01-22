package com.coveiot.covepreferences.data;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class SavedQRCodeModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f7039a;
    @NotNull
    public final ArrayList<QrCodeData> b;

    public SavedQRCodeModel(@NotNull String macAddress, @NotNull ArrayList<QrCodeData> qrCodeList) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        Intrinsics.checkNotNullParameter(qrCodeList, "qrCodeList");
        this.f7039a = macAddress;
        this.b = qrCodeList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SavedQRCodeModel copy$default(SavedQRCodeModel savedQRCodeModel, String str, ArrayList arrayList, int i, Object obj) {
        if ((i & 1) != 0) {
            str = savedQRCodeModel.f7039a;
        }
        if ((i & 2) != 0) {
            arrayList = savedQRCodeModel.b;
        }
        return savedQRCodeModel.copy(str, arrayList);
    }

    @NotNull
    public final String component1() {
        return this.f7039a;
    }

    @NotNull
    public final ArrayList<QrCodeData> component2() {
        return this.b;
    }

    @NotNull
    public final SavedQRCodeModel copy(@NotNull String macAddress, @NotNull ArrayList<QrCodeData> qrCodeList) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        Intrinsics.checkNotNullParameter(qrCodeList, "qrCodeList");
        return new SavedQRCodeModel(macAddress, qrCodeList);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SavedQRCodeModel) {
            SavedQRCodeModel savedQRCodeModel = (SavedQRCodeModel) obj;
            return Intrinsics.areEqual(this.f7039a, savedQRCodeModel.f7039a) && Intrinsics.areEqual(this.b, savedQRCodeModel.b);
        }
        return false;
    }

    @NotNull
    public final String getMacAddress() {
        return this.f7039a;
    }

    @NotNull
    public final ArrayList<QrCodeData> getQrCodeList() {
        return this.b;
    }

    public int hashCode() {
        return (this.f7039a.hashCode() * 31) + this.b.hashCode();
    }

    @NotNull
    public String toString() {
        return "SavedQRCodeModel(macAddress=" + this.f7039a + ", qrCodeList=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
