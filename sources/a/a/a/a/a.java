package a.a.a.a;

import com.coveiot.android.crpsdk.BleConnectHelper;
import com.coveiot.utils.utility.LogHelper;
import io.reactivex.rxjava3.functions.Consumer;
/* loaded from: classes.dex */
public class a implements Consumer<Long> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ BleConnectHelper f6a;

    public a(BleConnectHelper bleConnectHelper) {
        this.f6a = bleConnectHelper;
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public void accept(Long l) throws Throwable {
        LogHelper.d("BleConnectHelper", "reconnection2: " + this.f6a.h);
        BleConnectHelper bleConnectHelper = this.f6a;
        if (bleConnectHelper.h) {
            bleConnectHelper.establishConnection(bleConnectHelper.f4101a);
        }
    }
}
