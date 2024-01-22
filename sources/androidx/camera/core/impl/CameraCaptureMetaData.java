package androidx.camera.core.impl;
/* loaded from: classes.dex */
public final class CameraCaptureMetaData {

    /* loaded from: classes.dex */
    public enum AeState {
        UNKNOWN,
        INACTIVE,
        SEARCHING,
        FLASH_REQUIRED,
        CONVERGED,
        LOCKED
    }

    /* loaded from: classes.dex */
    public enum AfMode {
        UNKNOWN,
        OFF,
        ON_MANUAL_AUTO,
        ON_CONTINUOUS_AUTO
    }

    /* loaded from: classes.dex */
    public enum AfState {
        UNKNOWN,
        INACTIVE,
        SCANNING,
        FOCUSED,
        LOCKED_FOCUSED,
        LOCKED_NOT_FOCUSED
    }

    /* loaded from: classes.dex */
    public enum AwbState {
        UNKNOWN,
        INACTIVE,
        METERING,
        CONVERGED,
        LOCKED
    }

    /* loaded from: classes.dex */
    public enum FlashState {
        UNKNOWN,
        NONE,
        READY,
        FIRED
    }
}
