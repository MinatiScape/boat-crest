package com.htsmart.wristband2.dfu;

import android.text.TextUtils;
import com.htsmart.wristband2.WristbandApplication;
import com.htsmart.wristband2.WristbandManager;
import com.htsmart.wristband2.bean.WristbandVersion;
import com.htsmart.wristband2.dfu.j;
import com.htsmart.wristband2.utils.BytesUtil;
import com.polidea.rxandroidble2.exceptions.BleDisconnectedException;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.concurrent.TimeUnit;
/* loaded from: classes11.dex */
public class d implements j {
    public j.b e;
    public WristbandManager f = WristbandApplication.getWristbandManager();
    public String g;
    public String h;
    public int i;
    public boolean j;
    public Disposable k;

    /* loaded from: classes11.dex */
    public class a implements Consumer<Integer> {
        public a() {
        }

        @Override // io.reactivex.functions.Consumer
        /* renamed from: a */
        public void accept(Integer num) throws Exception {
            d dVar;
            int i;
            if (num.intValue() == 0) {
                d.this.e();
                return;
            }
            if (num.intValue() == 1) {
                dVar = d.this;
                i = 2;
            } else {
                dVar = d.this;
                i = Integer.MAX_VALUE;
            }
            dVar.b(i);
        }
    }

    /* loaded from: classes11.dex */
    public class b implements Consumer<Throwable> {
        public b() {
        }

        @Override // io.reactivex.functions.Consumer
        /* renamed from: a */
        public void accept(Throwable th) throws Exception {
            d dVar;
            int i;
            if (th instanceof BleDisconnectedException) {
                dVar = d.this;
                i = 3;
            } else {
                dVar = d.this;
                i = 1;
            }
            dVar.b(i);
        }
    }

    @Override // com.htsmart.wristband2.dfu.j
    public void a() {
        this.j = true;
        this.e = null;
        Disposable disposable = this.k;
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        this.k.dispose();
    }

    @Override // com.htsmart.wristband2.dfu.j
    public void a(j.b bVar) {
        this.e = bVar;
    }

    @Override // com.htsmart.wristband2.dfu.j
    public void a(boolean z) {
        String str;
        this.j = false;
        com.htsmart.wristband2.utils.a b2 = com.htsmart.wristband2.utils.a.b();
        if (b2 != null) {
            this.g = b2.c();
            this.h = b2.a();
            str = b2.d();
        } else {
            str = null;
        }
        if (TextUtils.isEmpty(str) || str.length() < 76) {
            b(0);
            return;
        }
        byte[] hexStr2Bytes = BytesUtil.hexStr2Bytes(WristbandVersion.get_version_hardware(str));
        if (hexStr2Bytes == null || hexStr2Bytes.length != 4) {
            b(0);
            return;
        }
        byte b3 = hexStr2Bytes[2];
        boolean z2 = (b3 & 1) > 0;
        if ((b3 & 128) > 0) {
            this.i = 3;
        } else if (!z2) {
            b(4);
            return;
        } else {
            this.i = 2;
        }
        if (this.f.isConnected()) {
            this.k = this.f.requestEnterOTA().delay(1L, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new a(), new b());
        } else {
            e();
        }
    }

    public final void b(int i) {
        if (this.j) {
            return;
        }
        this.j = true;
        j.b bVar = this.e;
        if (bVar != null) {
            bVar.a(i);
        }
    }

    @Override // com.htsmart.wristband2.dfu.j
    public void cancel() {
        this.j = true;
        Disposable disposable = this.k;
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        this.k.dispose();
    }

    public final void e() {
        if (this.j) {
            return;
        }
        this.j = true;
        this.f.close();
        j.b bVar = this.e;
        if (bVar != null) {
            bVar.a(this.g, this.h, this.i);
        }
    }
}
