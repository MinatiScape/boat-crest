package com.ido.ble.e.c;

import android.os.Handler;
import android.os.Looper;
import com.google.gson.Gson;
import com.ido.ble.callback.DeviceResponseCommonCallBack;
import com.ido.ble.common.c;
import com.ido.ble.common.k;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.handler.u;
import com.ido.ble.protocol.model.Mp3ToPcmPara;
import com.ido.ble.protocol.model.VoiceFileTranConfigPara;
import com.ido.ble.protocol.model.VoiceFileTranStartPara;
import com.veryfit.multi.nativeprotocol.Protocol;
import com.veryfit.multi.nativeprotocol.b;
import java.io.File;
import sox.ProtocolSox;
/* loaded from: classes11.dex */
public class a {
    private static final String c = "AlexaVoicePlayManager";
    private static a d;

    /* renamed from: a  reason: collision with root package name */
    private String f12216a;
    private Handler b = new Handler(Looper.getMainLooper());

    /* renamed from: com.ido.ble.e.c.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class C0596a implements DeviceResponseCommonCallBack.ICallBack {
        public C0596a() {
        }

        @Override // com.ido.ble.callback.DeviceResponseCommonCallBack.ICallBack
        public void onResponse(int i, String str) {
            if (i == 7633) {
                a.this.c(str);
            } else if (i == 7634) {
            } else {
                if (i == 7635) {
                    a.this.i();
                } else if (i == 7636) {
                    a.this.b();
                }
            }
        }
    }

    private void a(int i) {
        VoiceFileTranConfigPara voiceFileTranConfigPara = new VoiceFileTranConfigPara();
        voiceFileTranConfigPara.prn = 10;
        voiceFileTranConfigPara.voice_type = 1;
        voiceFileTranConfigPara.sbc_init_enum = 1;
        voiceFileTranConfigPara.opus_init_enum = 1;
        voiceFileTranConfigPara.file_len = (i / 240) * 57;
        LogTool.d(c, "[setTranPara] " + voiceFileTranConfigPara.toString());
        u.b(c.b(k.a(voiceFileTranConfigPara)), (int) b.l5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LogTool.d(c, "abnormal end");
    }

    private boolean b(String str) {
        File file = new File(str);
        if (!file.exists()) {
            LogTool.d(c, "[mp3ToPcm] file not exists:" + str);
            d();
            return false;
        }
        this.f12216a = str.replace(file.getName(), "") + "tempVoice.pcm";
        Mp3ToPcmPara mp3ToPcmPara = new Mp3ToPcmPara();
        mp3ToPcmPara.mp3Path = str;
        mp3ToPcmPara.pcmPath = this.f12216a;
        LogTool.d(c, "[mp3ToPcm] " + mp3ToPcmPara.toString());
        u.b(c.b(k.a(mp3ToPcmPara)), (int) b.i4);
        return true;
    }

    private void c() {
        Protocol.getInstance().testSetTimeInterval(1, 10);
        VoiceFileTranStartPara voiceFileTranStartPara = new VoiceFileTranStartPara();
        voiceFileTranStartPara.fileName = this.f12216a;
        LogTool.d(c, "[beginTran] " + k.a(voiceFileTranStartPara));
        u.b(c.b(k.a(voiceFileTranStartPara)), (int) b.m5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        LogTool.d(c, "[returnSetTranPara] " + str);
        c();
    }

    private void d() {
        LogTool.b(c, "failed.");
    }

    private Handler e() {
        if (this.b == null) {
            this.b = new Handler(Looper.getMainLooper());
        }
        return this.b;
    }

    public static a f() {
        if (d == null) {
            a aVar = new a();
            d = aVar;
            aVar.h();
        }
        return d;
    }

    private long g() {
        String str;
        this.f12216a = ProtocolSox.a().a(this.f12216a);
        LogTool.d(c, "[pcmFileTo16HZ] " + this.f12216a);
        File file = new File(this.f12216a);
        if (!file.exists()) {
            str = "targetPcmFile is not exists";
        } else if (file.length() > 0) {
            return file.length();
        } else {
            str = "targetPcmFile length is 0";
        }
        LogTool.b(c, str);
        d();
        return -1L;
    }

    private void h() {
        com.ido.ble.callback.b.N().a(new C0596a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        LogTool.d(c, "success.");
    }

    public void a() {
        LogTool.d(c, "[stop] ");
        u.b(c.b(new Gson().toJson("")), (int) b.n5);
    }

    public void a(String str) {
        LogTool.d(c, "[play] mp3FilePath is " + str);
        if (b(str)) {
            long g = g();
            if (g <= 0) {
                return;
            }
            a((int) g);
        }
    }
}
