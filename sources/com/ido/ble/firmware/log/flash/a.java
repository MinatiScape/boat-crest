package com.ido.ble.firmware.log.flash;

import com.ido.ble.callback.DeviceResponseCommonCallBack;
import com.ido.ble.common.k;
import com.ido.ble.firmware.log.b;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.handler.u;
import com.ido.ble.protocol.model.FlashLogParam;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.polidea.rxandroidble2.ClientComponent;
/* loaded from: classes11.dex */
public class a {
    private static final String g = "COLLECT_FLASH_LOG";
    private static a h;
    private ICollectFlashLogListener b;
    private ICollectFlashLogListener c;
    private com.ido.ble.firmware.log.b d;

    /* renamed from: a  reason: collision with root package name */
    private boolean f12278a = false;
    private int e = 1;
    private DeviceResponseCommonCallBack.ICallBack f = new C0603a();

    /* renamed from: com.ido.ble.firmware.log.flash.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class C0603a implements DeviceResponseCommonCallBack.ICallBack {
        public C0603a() {
        }

        @Override // com.ido.ble.callback.DeviceResponseCommonCallBack.ICallBack
        public void onResponse(int i, String str) {
            if (i == 5512 || i == 5513) {
                a.this.a();
            }
        }
    }

    /* loaded from: classes11.dex */
    public class b implements b.InterfaceC0602b {
        public b() {
        }

        @Override // com.ido.ble.firmware.log.b.InterfaceC0602b
        public void a() {
            a.this.d();
        }
    }

    /* loaded from: classes11.dex */
    public class c implements b.InterfaceC0602b {
        public c() {
        }

        @Override // com.ido.ble.firmware.log.b.InterfaceC0602b
        public void a() {
            a.this.d();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        ICollectFlashLogListener iCollectFlashLogListener;
        ICollectFlashLogListener iCollectFlashLogListener2;
        LogTool.d(g, DeviceKey.KFinishFlag);
        c();
        int i = this.e;
        if (i == 1 && (iCollectFlashLogListener2 = this.b) != null) {
            iCollectFlashLogListener2.onFinish();
            this.b = null;
        } else if (i != 2 || (iCollectFlashLogListener = this.c) == null) {
        } else {
            iCollectFlashLogListener.onFinish();
            this.c = null;
        }
    }

    public static a b() {
        if (h == null) {
            h = new a();
        }
        return h;
    }

    private void c() {
        LogTool.d(g, "release");
        this.f12278a = false;
        this.d.a();
        com.ido.ble.callback.b.N().b(this.f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        ICollectFlashLogListener iCollectFlashLogListener;
        ICollectFlashLogListener iCollectFlashLogListener2;
        LogTool.b(g, ClientComponent.NamedSchedulers.TIMEOUT);
        c();
        int i = this.e;
        if (i == 1 && (iCollectFlashLogListener2 = this.b) != null) {
            iCollectFlashLogListener2.onFinish();
            this.b = null;
        } else if (i != 2 || (iCollectFlashLogListener = this.c) == null) {
        } else {
            iCollectFlashLogListener.onFinish();
            this.c = null;
        }
    }

    public void a(int i, String str, int i2, ICollectFlashLogListener iCollectFlashLogListener) {
        if (this.f12278a) {
            LogTool.b(g, "in doing state...");
            return;
        }
        this.e = 1;
        this.b = iCollectFlashLogListener;
        this.f12278a = true;
        LogTool.d(g, "[start] type=" + i + ",filePath=" + str + ",timeoutSecond=" + i2 + ",listener=" + iCollectFlashLogListener);
        com.ido.ble.firmware.log.b bVar = new com.ido.ble.firmware.log.b();
        this.d = bVar;
        bVar.a(new b(), i2);
        this.b.onStart();
        com.ido.ble.callback.b.N().a(this.f);
        FlashLogParam flashLogParam = new FlashLogParam();
        flashLogParam.filePath = str;
        flashLogParam.type = i;
        u.b(k.a(flashLogParam).getBytes(), (int) com.veryfit.multi.nativeprotocol.b.e4);
    }

    public void b(int i, String str, int i2, ICollectFlashLogListener iCollectFlashLogListener) {
        if (this.f12278a) {
            LogTool.b(g, "SecondChip in doing state...");
            return;
        }
        this.e = 2;
        this.c = iCollectFlashLogListener;
        this.f12278a = true;
        LogTool.d(g, "SecondChip [start] type=" + i + ",filePath=" + str + ",timeoutSecond=" + i2 + ",listener=" + iCollectFlashLogListener);
        com.ido.ble.firmware.log.b bVar = new com.ido.ble.firmware.log.b();
        this.d = bVar;
        bVar.a(new c(), i2);
        this.c.onStart();
        com.ido.ble.callback.b.N().a(this.f);
        FlashLogParam flashLogParam = new FlashLogParam();
        flashLogParam.filePath = str;
        flashLogParam.type = i;
        u.b(k.a(flashLogParam).getBytes(), (int) com.veryfit.multi.nativeprotocol.b.l4);
    }
}
