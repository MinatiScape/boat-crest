package com.ido.ble.icon.transfer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.ido.ble.callback.DeviceResponseCommonCallBack;
import com.ido.ble.common.k;
import com.ido.ble.file.transfer.FileTransferConfig;
import com.ido.ble.file.transfer.IFileTransferListener;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.handler.u;
import com.ido.ble.protocol.model.IconCompressConfig;
import com.ido.ble.protocol.model.IconPara;
import java.io.File;
/* loaded from: classes11.dex */
public class a {
    private static final String g = "IconTransferManager";
    private static final String h = "msg";
    private static final String i = "sport";
    private static final String j = "sports";
    private static a k;

    /* renamed from: a  reason: collision with root package name */
    private String f12302a;
    private IconTranConfig b;
    private IIconTransferListener c;
    private boolean d = false;
    private Handler e = new Handler(Looper.getMainLooper());
    private DeviceResponseCommonCallBack.ICallBack f = new b();

    /* renamed from: com.ido.ble.icon.transfer.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class RunnableC0605a implements Runnable {
        public RunnableC0605a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            LogTool.b(a.g, "time out.");
            a.this.c();
        }
    }

    /* loaded from: classes11.dex */
    public class b implements DeviceResponseCommonCallBack.ICallBack {
        public b() {
        }

        @Override // com.ido.ble.callback.DeviceResponseCommonCallBack.ICallBack
        public void onResponse(int i, String str) {
            if (i == 334) {
                a.this.a(str);
            }
        }
    }

    /* loaded from: classes11.dex */
    public class c implements IFileTransferListener {
        public c() {
        }

        @Override // com.ido.ble.file.transfer.IFileTransferListener
        public void onFailed(String str) {
            LogTool.d(a.g, "[beginTransfer] " + str);
            a.this.c();
        }

        @Override // com.ido.ble.file.transfer.IFileTransferListener
        public void onProgress(int i) {
            if (a.this.c != null) {
                a.this.c.onProgress(a.this.b, i);
            }
        }

        @Override // com.ido.ble.file.transfer.IFileTransferListener
        public void onStart() {
        }

        @Override // com.ido.ble.file.transfer.IFileTransferListener
        public void onSuccess() {
            a.this.j();
        }
    }

    private void a(int i2) {
        String str;
        StringBuilder sb;
        String str2;
        IconCompressConfig iconCompressConfig = new IconCompressConfig();
        String str3 = this.f12302a;
        iconCompressConfig.fileName = str3;
        iconCompressConfig.format = i2;
        if (this.b.type == 4) {
            Bitmap decodeFile = BitmapFactory.decodeFile(str3);
            if (decodeFile == null || decodeFile.getWidth() == 0 || decodeFile.getHeight() == 0) {
                iconCompressConfig.pic_num = 20;
                sb = new StringBuilder();
                str2 = "[compressIconFile] wallPaper==null  pic_num:";
            } else {
                iconCompressConfig.pic_num = decodeFile.getHeight() / decodeFile.getWidth();
                sb = new StringBuilder();
                str2 = "[compressIconFile]   pic_num:";
            }
            sb.append(str2);
            sb.append(iconCompressConfig.pic_num);
            LogTool.d(g, sb.toString());
        } else {
            iconCompressConfig.pic_num = 1;
        }
        int i3 = this.b.type;
        if (i3 == 1) {
            str = "msg";
        } else if (i3 != 3 && i3 != 5 && i3 != 2) {
            if (i3 == 4 || i3 == 6) {
                str = j;
            }
            LogTool.d(g, "[compressIconFile json] " + k.a(iconCompressConfig));
            u.b(k.a(iconCompressConfig));
            b();
        } else {
            str = i;
        }
        iconCompressConfig.endName = str;
        LogTool.d(g, "[compressIconFile json] " + k.a(iconCompressConfig));
        u.b(k.a(iconCompressConfig));
        b();
    }

    private void a(int i2, int i3, int i4) {
        IIconTransferListener iIconTransferListener = this.c;
        if (iIconTransferListener == null) {
            LogTool.b(g, "[getIconFile] listener is null");
            c();
            return;
        }
        this.f12302a = iIconTransferListener.onHandlePicFile(this.b, i2, i3);
        LogTool.d(g, "[getIconFile] iconPath is  " + this.f12302a);
        a(i4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        com.ido.ble.callback.b.N().b(this.f);
        i();
        if (TextUtils.isEmpty(str)) {
            LogTool.b(g, "[returnIconPara] return icon para json is null");
            c();
            return;
        }
        LogTool.d(g, "[returnIconPara] return icon para " + str);
        IconPara.Response response = (IconPara.Response) k.c(str, IconPara.Response.class);
        if (response != null) {
            a(response.icon_width, response.icon_height, response.format);
            return;
        }
        LogTool.b(g, "[returnIconPara] return icon para json is null");
        c();
    }

    private void b() {
        StringBuilder sb;
        LogTool.d(g, "[beginTransfer]");
        FileTransferConfig fileTransferConfig = new FileTransferConfig();
        int i2 = this.b.type;
        if (i2 == 1) {
            fileTransferConfig.filePath = this.f12302a + ".temp.msg";
            fileTransferConfig.firmwareSpecName = "1.msg";
        } else {
            String str = "1.sport";
            String str2 = i;
            if (i2 == 3) {
                sb = new StringBuilder();
            } else if (i2 == 5) {
                sb = new StringBuilder();
            } else if (i2 == 2) {
                sb = new StringBuilder();
            } else {
                str = "1.sports";
                str2 = j;
                if (i2 == 4) {
                    sb = new StringBuilder();
                } else if (i2 == 6) {
                    sb = new StringBuilder();
                }
            }
            sb.append(this.f12302a);
            sb.append(".temp.");
            sb.append(str2);
            fileTransferConfig.filePath = sb.toString();
            fileTransferConfig.firmwareSpecName = str;
        }
        if (!new File(fileTransferConfig.filePath).exists()) {
            LogTool.d(g, "[beginTransfer], remove ‘temp’");
            fileTransferConfig.filePath = fileTransferConfig.filePath.replace(".temp.", ".");
        }
        fileTransferConfig.PRN = 10;
        fileTransferConfig.zipType = 2;
        fileTransferConfig.dataType = 2;
        fileTransferConfig.maxRetryTimes = this.b.maxRetryTimes;
        fileTransferConfig.isNeedChangeSpeedMode = true;
        fileTransferConfig.iFileTransferListener = new c();
        com.ido.ble.file.transfer.b.g().b(fileTransferConfig);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogTool.b(g, "failed. ");
        IIconTransferListener iIconTransferListener = this.c;
        if (iIconTransferListener != null) {
            iIconTransferListener.onFailed(this.b);
        }
        g();
    }

    private Handler d() {
        if (this.e == null) {
            this.e = new Handler(Looper.getMainLooper());
        }
        return this.e;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0035, code lost:
        if (r2 == 6) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void e() {
        /*
            r6 = this;
            com.ido.ble.callback.b r0 = com.ido.ble.callback.b.N()
            com.ido.ble.callback.DeviceResponseCommonCallBack$ICallBack r1 = r6.f
            r0.a(r1)
            com.ido.ble.protocol.model.IconPara$Get r0 = new com.ido.ble.protocol.model.IconPara$Get
            r0.<init>()
            com.ido.ble.icon.transfer.IconTranConfig r1 = r6.b
            int r2 = r1.type
            r3 = 1
            if (r2 != r3) goto L1a
            int r1 = r1.index
            r0.evt_type = r1
            goto L38
        L1a:
            r4 = 2
            r5 = 3
            if (r2 != r5) goto L25
        L1e:
            r0.type = r4
        L20:
            int r1 = r1.index
            r0.sport_type = r1
            goto L38
        L25:
            if (r2 != r4) goto L2a
        L27:
            r0.type = r3
            goto L20
        L2a:
            r3 = 4
            if (r2 != r3) goto L30
            r0.type = r5
            goto L20
        L30:
            r4 = 5
            if (r2 != r4) goto L34
            goto L27
        L34:
            r3 = 6
            if (r2 != r3) goto L38
            goto L1e
        L38:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "[getIconPara] start get icon para "
            r1.append(r2)
            java.lang.String r2 = r0.toString()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "IconTransferManager"
            com.ido.ble.logs.LogTool.d(r2, r1)
            java.lang.String r0 = com.ido.ble.common.k.a(r0)
            r1 = 334(0x14e, float:4.68E-43)
            com.ido.ble.i.a.a.a(r1, r0)
            r6.h()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.ble.icon.transfer.a.e():void");
    }

    public static a f() {
        if (k == null) {
            k = new a();
        }
        return k;
    }

    private void g() {
        com.ido.ble.callback.b.N().b(this.f);
        this.b = null;
        this.c = null;
        this.d = false;
        this.f12302a = "";
        Handler handler = this.e;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.e = null;
        }
    }

    private void h() {
        d().postDelayed(new RunnableC0605a(), 20000L);
    }

    private void i() {
        Handler handler = this.e;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        LogTool.d(g, "success.");
        IIconTransferListener iIconTransferListener = this.c;
        if (iIconTransferListener != null) {
            iIconTransferListener.onSuccess(this.b);
        }
        g();
    }

    public void a() {
        LogTool.b(g, "stopByUser. ");
        com.ido.ble.file.transfer.b.g().a();
        g();
    }

    public void a(IconTranConfig iconTranConfig, IIconTransferListener iIconTransferListener) {
        if (this.d) {
            LogTool.b(g, "[start] is doing, it`s config is " + this.b);
            IIconTransferListener iIconTransferListener2 = this.c;
            if (iIconTransferListener2 != null) {
                iIconTransferListener2.onBusy(this.b);
                return;
            }
            return;
        }
        this.b = iconTranConfig;
        this.c = iIconTransferListener;
        LogTool.d(g, "[start] " + iconTranConfig.toString());
        this.d = true;
        IIconTransferListener iIconTransferListener3 = this.c;
        if (iIconTransferListener3 != null) {
            iIconTransferListener3.onStart(iconTranConfig);
        }
        e();
    }
}
