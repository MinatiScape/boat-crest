package com.realsil.sdk.dfu.model;

import com.realsil.sdk.dfu.image.wrapper.SocImageWrapper;
/* loaded from: classes12.dex */
public class DeviceInfoWrapper {

    /* renamed from: a  reason: collision with root package name */
    public DeviceInfo f13628a;

    public DeviceInfoWrapper(DeviceInfo deviceInfo) {
        this.f13628a = deviceInfo;
    }

    public final int a(int i, int i2) {
        return i;
    }

    public SocImageWrapper getAncImageWrapper() {
        ImageVersionInfo imageVersionInfo;
        SocImageWrapper.Builder builder = new SocImageWrapper.Builder();
        builder.setProtocolType(this.f13628a.protocolType);
        builder.setSpecVersion(this.f13628a.specVersion);
        builder.setIcType(this.f13628a.icType);
        builder.setImageId(10136);
        builder.setBankIndicator(this.f13628a.getActiveBank());
        DeviceInfo deviceInfo = this.f13628a;
        int i = deviceInfo.protocolType;
        if (i == 16) {
            int a2 = a(10, deviceInfo.imageVersionIndicator);
            imageVersionInfo = this.f13628a.getActiveImageVersionInfo(a2);
            builder.setBitNumber(a2);
        } else if (i == 17) {
            if (deviceInfo.specVersion >= 6) {
                imageVersionInfo = deviceInfo.getActiveImageVersionInfoByImageId(10136);
            } else {
                int a3 = a(10, deviceInfo.imageVersionIndicator);
                imageVersionInfo = this.f13628a.getActiveImageVersionInfo(a3);
                builder.setBitNumber(a3);
            }
        } else {
            int i2 = deviceInfo.specVersion;
            if (i2 != 0 && i2 == 1) {
                imageVersionInfo = this.f13628a.getActiveImageVersionInfo(a(10, deviceInfo.imageVersionIndicator));
            } else {
                imageVersionInfo = null;
            }
        }
        if (imageVersionInfo != null) {
            builder.setImageVersion(imageVersionInfo.getVersion());
        }
        return builder.build();
    }

    public SocImageWrapper getAppUiParameterVersion() {
        ImageVersionInfo imageVersionInfo;
        SocImageWrapper.Builder builder = new SocImageWrapper.Builder();
        builder.setProtocolType(this.f13628a.protocolType);
        builder.setSpecVersion(this.f13628a.specVersion);
        builder.setIcType(this.f13628a.icType);
        builder.setImageId(10134);
        builder.setBankIndicator(this.f13628a.getActiveBank());
        DeviceInfo deviceInfo = this.f13628a;
        int i = deviceInfo.protocolType;
        if (i == 16) {
            int a2 = a(9, deviceInfo.imageVersionIndicator);
            imageVersionInfo = this.f13628a.getActiveImageVersionInfo(a2);
            builder.setBitNumber(a2);
        } else if (i == 17) {
            if (deviceInfo.specVersion >= 6) {
                imageVersionInfo = deviceInfo.getActiveImageVersionInfoByImageId(10134);
            } else {
                int a3 = a(9, deviceInfo.imageVersionIndicator);
                imageVersionInfo = this.f13628a.getActiveImageVersionInfo(a3);
                builder.setBitNumber(a3);
            }
        } else {
            int i2 = deviceInfo.specVersion;
            if (i2 != 0 && i2 == 1) {
                imageVersionInfo = this.f13628a.getActiveImageVersionInfo(a(9, deviceInfo.imageVersionIndicator));
            } else {
                imageVersionInfo = null;
            }
        }
        if (imageVersionInfo != null) {
            builder.setImageVersion(imageVersionInfo.getVersion());
        }
        return builder.build();
    }

    public SocImageWrapper getDspAppVersion() {
        ImageVersionInfo imageVersionInfo;
        SocImageWrapper.Builder builder = new SocImageWrapper.Builder();
        builder.setProtocolType(this.f13628a.protocolType);
        builder.setSpecVersion(this.f13628a.specVersion);
        builder.setIcType(this.f13628a.icType);
        builder.setImageId(10133);
        builder.setBankIndicator(this.f13628a.getActiveBank());
        DeviceInfo deviceInfo = this.f13628a;
        int i = deviceInfo.protocolType;
        if (i == 16) {
            int a2 = a(7, deviceInfo.imageVersionIndicator);
            imageVersionInfo = this.f13628a.getActiveImageVersionInfo(a2);
            builder.setBitNumber(a2);
        } else if (i == 17) {
            if (deviceInfo.specVersion >= 6) {
                imageVersionInfo = deviceInfo.getActiveImageVersionInfoByImageId(10133);
            } else {
                int a3 = a(7, deviceInfo.imageVersionIndicator);
                imageVersionInfo = this.f13628a.getActiveImageVersionInfo(a3);
                builder.setBitNumber(a3);
            }
        } else {
            int i2 = deviceInfo.specVersion;
            if (i2 != 0 && i2 == 1) {
                imageVersionInfo = this.f13628a.getActiveImageVersionInfo(a(7, deviceInfo.imageVersionIndicator));
            } else {
                imageVersionInfo = null;
            }
        }
        if (imageVersionInfo != null) {
            builder.setImageVersion(imageVersionInfo.getVersion());
        }
        return builder.build();
    }

    public SocImageWrapper getDspPatchVersion() {
        ImageVersionInfo imageVersionInfo;
        SocImageWrapper.Builder builder = new SocImageWrapper.Builder();
        builder.setProtocolType(this.f13628a.protocolType);
        builder.setSpecVersion(this.f13628a.specVersion);
        builder.setIcType(this.f13628a.icType);
        builder.setImageId(10132);
        builder.setBankIndicator(this.f13628a.getActiveBank());
        DeviceInfo deviceInfo = this.f13628a;
        int i = deviceInfo.protocolType;
        if (i == 16) {
            int a2 = a(6, deviceInfo.imageVersionIndicator);
            imageVersionInfo = this.f13628a.getActiveImageVersionInfo(a2);
            builder.setBitNumber(a2);
        } else if (i == 17) {
            if (deviceInfo.specVersion >= 6) {
                imageVersionInfo = deviceInfo.getActiveImageVersionInfoByImageId(10132);
            } else {
                int a3 = a(6, deviceInfo.imageVersionIndicator);
                imageVersionInfo = this.f13628a.getActiveImageVersionInfo(a3);
                builder.setBitNumber(a3);
            }
        } else {
            int i2 = deviceInfo.specVersion;
            if (i2 != 0 && i2 == 1) {
                imageVersionInfo = this.f13628a.getActiveImageVersionInfo(a(6, deviceInfo.imageVersionIndicator));
            } else {
                imageVersionInfo = null;
            }
        }
        if (imageVersionInfo != null) {
            builder.setImageVersion(imageVersionInfo.getVersion());
        }
        return builder.build();
    }

    public SocImageWrapper getDspUiParameterVersion() {
        ImageVersionInfo imageVersionInfo;
        SocImageWrapper.Builder builder = new SocImageWrapper.Builder();
        builder.setProtocolType(this.f13628a.protocolType);
        builder.setSpecVersion(this.f13628a.specVersion);
        builder.setIcType(this.f13628a.icType);
        builder.setImageId(10135);
        builder.setBankIndicator(this.f13628a.getActiveBank());
        DeviceInfo deviceInfo = this.f13628a;
        int i = deviceInfo.protocolType;
        if (i == 16) {
            int a2 = a(8, deviceInfo.imageVersionIndicator);
            imageVersionInfo = this.f13628a.getActiveImageVersionInfo(a2);
            builder.setBitNumber(a2);
        } else if (i == 17) {
            if (deviceInfo.specVersion >= 6) {
                imageVersionInfo = deviceInfo.getActiveImageVersionInfoByImageId(10135);
            } else {
                int a3 = a(8, deviceInfo.imageVersionIndicator);
                imageVersionInfo = this.f13628a.getActiveImageVersionInfo(a3);
                builder.setBitNumber(a3);
            }
        } else {
            int i2 = deviceInfo.specVersion;
            if (i2 != 0 && i2 == 1) {
                imageVersionInfo = this.f13628a.getActiveImageVersionInfo(a(8, deviceInfo.imageVersionIndicator));
            } else {
                imageVersionInfo = null;
            }
        }
        if (imageVersionInfo != null) {
            builder.setImageVersion(imageVersionInfo.getVersion());
        }
        return builder.build();
    }

    public SocImageWrapper getEx1ImageWrapper() {
        ImageVersionInfo imageVersionInfo;
        SocImageWrapper.Builder builder = new SocImageWrapper.Builder();
        builder.setProtocolType(this.f13628a.protocolType);
        builder.setSpecVersion(this.f13628a.specVersion);
        builder.setIcType(this.f13628a.icType);
        builder.setImageId(10137);
        builder.setBankIndicator(this.f13628a.getActiveBank());
        DeviceInfo deviceInfo = this.f13628a;
        int i = deviceInfo.protocolType;
        if (i == 16) {
            int a2 = a(11, deviceInfo.imageVersionIndicator);
            imageVersionInfo = this.f13628a.getActiveImageVersionInfo(a2);
            builder.setBitNumber(a2);
        } else if (i == 17) {
            if (deviceInfo.specVersion >= 6) {
                imageVersionInfo = deviceInfo.getActiveImageVersionInfoByImageId(10137);
            } else {
                int a3 = a(11, deviceInfo.imageVersionIndicator);
                imageVersionInfo = this.f13628a.getActiveImageVersionInfo(a3);
                builder.setBitNumber(a3);
            }
        } else {
            int i2 = deviceInfo.specVersion;
            if (i2 != 0 && i2 == 1) {
                imageVersionInfo = this.f13628a.getActiveImageVersionInfo(a(11, deviceInfo.imageVersionIndicator));
            } else {
                imageVersionInfo = null;
            }
        }
        if (imageVersionInfo != null) {
            builder.setImageVersion(imageVersionInfo.getVersion());
        }
        return builder.build();
    }

    public SocImageWrapper getEx2ImageWrapper() {
        ImageVersionInfo imageVersionInfo;
        SocImageWrapper.Builder builder = new SocImageWrapper.Builder();
        builder.setProtocolType(this.f13628a.protocolType);
        builder.setSpecVersion(this.f13628a.specVersion);
        builder.setIcType(this.f13628a.icType);
        builder.setImageId(10138);
        builder.setBankIndicator(this.f13628a.getActiveBank());
        DeviceInfo deviceInfo = this.f13628a;
        int i = deviceInfo.protocolType;
        if (i == 16) {
            int a2 = a(12, deviceInfo.imageVersionIndicator);
            imageVersionInfo = this.f13628a.getActiveImageVersionInfo(a2);
            builder.setBitNumber(a2);
        } else if (i == 17) {
            if (deviceInfo.specVersion >= 6) {
                imageVersionInfo = deviceInfo.getActiveImageVersionInfoByImageId(10138);
            } else {
                int a3 = a(12, deviceInfo.imageVersionIndicator);
                imageVersionInfo = this.f13628a.getActiveImageVersionInfo(a3);
                builder.setBitNumber(a3);
            }
        } else {
            int i2 = deviceInfo.specVersion;
            if (i2 != 0 && i2 == 1) {
                imageVersionInfo = this.f13628a.getActiveImageVersionInfo(a(12, deviceInfo.imageVersionIndicator));
            } else {
                imageVersionInfo = null;
            }
        }
        if (imageVersionInfo != null) {
            builder.setImageVersion(imageVersionInfo.getVersion());
        }
        return builder.build();
    }

    public SocImageWrapper getImageWrapper(int i) {
        switch (i) {
            case 10132:
                return getDspPatchVersion();
            case 10133:
                return getDspAppVersion();
            case 10134:
                return getAppUiParameterVersion();
            case 10135:
                return getDspUiParameterVersion();
            case 10136:
                return getAncImageWrapper();
            case 10137:
                return getEx1ImageWrapper();
            case 10138:
                return getEx2ImageWrapper();
            default:
                return null;
        }
    }
}
