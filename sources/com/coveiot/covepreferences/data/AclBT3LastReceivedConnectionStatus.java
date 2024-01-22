package com.coveiot.covepreferences.data;

import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class AclBT3LastReceivedConnectionStatus implements Serializable {
    @Nullable
    private ConnectionStatus connectionStatus;
    @NotNull
    private String macAddress;

    public AclBT3LastReceivedConnectionStatus(@NotNull String macAddress, @Nullable ConnectionStatus connectionStatus) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.macAddress = macAddress;
        this.connectionStatus = connectionStatus;
    }

    public static /* synthetic */ AclBT3LastReceivedConnectionStatus copy$default(AclBT3LastReceivedConnectionStatus aclBT3LastReceivedConnectionStatus, String str, ConnectionStatus connectionStatus, int i, Object obj) {
        if ((i & 1) != 0) {
            str = aclBT3LastReceivedConnectionStatus.macAddress;
        }
        if ((i & 2) != 0) {
            connectionStatus = aclBT3LastReceivedConnectionStatus.connectionStatus;
        }
        return aclBT3LastReceivedConnectionStatus.copy(str, connectionStatus);
    }

    @NotNull
    public final String component1() {
        return this.macAddress;
    }

    @Nullable
    public final ConnectionStatus component2() {
        return this.connectionStatus;
    }

    @NotNull
    public final AclBT3LastReceivedConnectionStatus copy(@NotNull String macAddress, @Nullable ConnectionStatus connectionStatus) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return new AclBT3LastReceivedConnectionStatus(macAddress, connectionStatus);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AclBT3LastReceivedConnectionStatus) {
            AclBT3LastReceivedConnectionStatus aclBT3LastReceivedConnectionStatus = (AclBT3LastReceivedConnectionStatus) obj;
            return Intrinsics.areEqual(this.macAddress, aclBT3LastReceivedConnectionStatus.macAddress) && this.connectionStatus == aclBT3LastReceivedConnectionStatus.connectionStatus;
        }
        return false;
    }

    @Nullable
    public final ConnectionStatus getConnectionStatus() {
        return this.connectionStatus;
    }

    @NotNull
    public final String getMacAddress() {
        return this.macAddress;
    }

    public int hashCode() {
        int hashCode = this.macAddress.hashCode() * 31;
        ConnectionStatus connectionStatus = this.connectionStatus;
        return hashCode + (connectionStatus == null ? 0 : connectionStatus.hashCode());
    }

    public final void setConnectionStatus(@Nullable ConnectionStatus connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    public final void setMacAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.macAddress = str;
    }

    @NotNull
    public String toString() {
        return "AclBT3LastReceivedConnectionStatus(macAddress=" + this.macAddress + ", connectionStatus=" + this.connectionStatus + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ AclBT3LastReceivedConnectionStatus(String str, ConnectionStatus connectionStatus, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : connectionStatus);
    }
}
