package com.apex.bluetooth.callback;

import com.apex.bluetooth.model.EABleContact;
import java.util.List;
/* loaded from: classes.dex */
public interface ReadBookListCallback extends EABleCallback {
    void bookList(List<EABleContact> list);
}
