package com.coveiot.android.tappy.utils;
/* loaded from: classes7.dex */
public class LocalAPDUCommands {
    public static int PAYMENTNETWORK_MASTERCARD = 2;
    public static int PAYMENTNETWORK_VISA = 1;

    public static String getCPLCDataApdu() {
        return "80CA9F7F00";
    }

    public static String getDataApdu() {
        return "80CA7F2101AF";
    }

    public static String initUpdateApdu() {
        return "8050000008000000000000000000";
    }

    public static String selectCASDApdu() {
        return "00A404000CA00000015153504341534400";
    }

    public static String selectIsdApdu() {
        return "00A4040008A000000018434D00";
    }

    public static String selectPaymentAIDAPDU(int i) {
        return i == PAYMENTNETWORK_MASTERCARD ? "00A4040007A0000000041010" : i == PAYMENTNETWORK_VISA ? "00A4040007A0000000031010" : "";
    }
}
