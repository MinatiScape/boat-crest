package com.ido.ble.dfu;
/* loaded from: classes11.dex */
public class BleDFUState {

    /* loaded from: classes11.dex */
    public enum FailReason {
        ENTER_DFU_MODE_FAILED,
        DEVICE_IN_LOW_BATTERY,
        NOT_FIND_TARGET_DEVICE,
        CONFIG_PARAS_ERROR,
        FILE_ERROR,
        PHONE_BLUETOOTH_ERROR,
        DEVICE_NOT_REBOOT,
        OTHER,
        OPERATION_FAILED,
        OPERATION_NOT_PERMITTED
    }

    /* loaded from: classes11.dex */
    public interface IListener {
        void onCanceled();

        void onDeviceInDFUMode();

        void onFailed(FailReason failReason);

        void onPrepare();

        void onProgress(int i);

        void onRetry(int i);

        void onSuccess();

        void onSuccessAndNeedToPromptUser();
    }
}
