package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class DeviceExchangeDataStopAppReplyData implements Serializable {
    public static final int CODE_FAILED_APP_NOT_IN_SOPRT_MODE = 1;
    public static final int CODE_SUCCESS = 0;
    private static final long serialVersionUID = 1;
    public int distance;
    public int ret_code;
}
