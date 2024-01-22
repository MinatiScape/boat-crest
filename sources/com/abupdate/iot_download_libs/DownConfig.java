package com.abupdate.iot_download_libs;

import javax.net.ssl.SSLSocketFactory;
/* loaded from: classes.dex */
public class DownConfig {
    public static int CONNECT_TIMEOUT = 30000;
    public static int READ_TIMEOUT = 30000;
    public static int RETRY_INTERVAL_TIME = 1000;
    public static int RETRY_TIMES_MAX = 1;
    public static int SEGMENT_DOWNLOAD_RETRY_TIME = 5;
    public static SSLSocketFactory SSL = null;
    public static long THREAD_BLOCK_CELL_MAX = 20971520;
    public static long THREAD_BLOCK_CELL_MIN = 1048576;
    public static boolean sSegmentDownload = true;
}
