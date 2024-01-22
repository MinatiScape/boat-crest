package com.coveiot.android.bleabstract.request;

import com.coveiot.sdk.ble.model.QRCodeData;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class QRTrayCodeRequest extends BleBaseRequest {
    @NotNull
    public final ArrayList<QRCodeData> f;

    /* loaded from: classes2.dex */
    public static final class Builder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public ArrayList<QRCodeData> f3508a = new ArrayList<>();

        @NotNull
        public final QRTrayCodeRequest build() {
            return new QRTrayCodeRequest(this.f3508a);
        }

        @NotNull
        public final ArrayList<QRCodeData> getImageId() {
            return this.f3508a;
        }

        public final void setImageId(@NotNull ArrayList<QRCodeData> arrayList) {
            Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
            this.f3508a = arrayList;
        }

        @NotNull
        public final Builder setQRTrayCodeRequest(@NotNull ArrayList<QRCodeData> id) {
            Intrinsics.checkNotNullParameter(id, "id");
            this.f3508a = id;
            return this;
        }
    }

    public QRTrayCodeRequest(@NotNull ArrayList<QRCodeData> qrCodeData) {
        Intrinsics.checkNotNullParameter(qrCodeData, "qrCodeData");
        this.f = qrCodeData;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ QRTrayCodeRequest copy$default(QRTrayCodeRequest qRTrayCodeRequest, ArrayList arrayList, int i, Object obj) {
        if ((i & 1) != 0) {
            arrayList = qRTrayCodeRequest.f;
        }
        return qRTrayCodeRequest.copy(arrayList);
    }

    @NotNull
    public final ArrayList<QRCodeData> component1() {
        return this.f;
    }

    @NotNull
    public final QRTrayCodeRequest copy(@NotNull ArrayList<QRCodeData> qrCodeData) {
        Intrinsics.checkNotNullParameter(qrCodeData, "qrCodeData");
        return new QRTrayCodeRequest(qrCodeData);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof QRTrayCodeRequest) && Intrinsics.areEqual(this.f, ((QRTrayCodeRequest) obj).f);
    }

    @NotNull
    public final ArrayList<QRCodeData> getQrCodeData() {
        return this.f;
    }

    public int hashCode() {
        return this.f.hashCode();
    }

    @NotNull
    public String toString() {
        return "QRTrayCodeRequest(qrCodeData=" + this.f + HexStringBuilder.COMMENT_END_CHAR;
    }
}
