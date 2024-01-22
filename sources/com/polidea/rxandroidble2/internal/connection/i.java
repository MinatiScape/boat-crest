package com.polidea.rxandroidble2.internal.connection;

import androidx.annotation.RestrictTo;
import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble2.RxBleConnection;
@ConnectionScope
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes12.dex */
public class i implements PayloadSizeLimitProvider {

    /* renamed from: a  reason: collision with root package name */
    public final RxBleConnection f13434a;
    public final int b;

    @Inject
    public i(RxBleConnection rxBleConnection, @Named("GATT_WRITE_MTU_OVERHEAD") int i) {
        this.f13434a = rxBleConnection;
        this.b = i;
    }

    @Override // com.polidea.rxandroidble2.internal.connection.PayloadSizeLimitProvider
    public int getPayloadSizeLimit() {
        return this.f13434a.getMtu() - this.b;
    }
}
