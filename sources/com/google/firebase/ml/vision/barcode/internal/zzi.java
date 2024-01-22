package com.google.firebase.ml.vision.barcode.internal;

import android.os.IInterface;
import android.os.RemoteException;
/* loaded from: classes10.dex */
public interface zzi extends IInterface {
    IBarcodeDetector newBarcodeDetector(BarcodeDetectorOptionsParcel barcodeDetectorOptionsParcel) throws RemoteException;
}
