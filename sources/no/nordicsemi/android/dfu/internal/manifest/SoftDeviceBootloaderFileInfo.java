package no.nordicsemi.android.dfu.internal.manifest;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes12.dex */
public class SoftDeviceBootloaderFileInfo extends FileInfo {
    @SerializedName("bl_size")
    private int bootloaderSize;
    @SerializedName("sd_size")
    private int softdeviceSize;

    public int getBootloaderSize() {
        return this.bootloaderSize;
    }

    public int getSoftdeviceSize() {
        return this.softdeviceSize;
    }
}
