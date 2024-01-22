package com.coveiot.android.bleabstract.services;

import android.os.Handler;
import com.coveiot.android.bleabstract.R;
import com.coveiot.android.bleabstract.response.LiveFirmwareUploadPercentage;
import com.coveiot.android.crpsdk.CRPResponseListener;
import com.coveiot.android.crpsdk.api.CRPBaseReq;
import com.coveiot.android.crpsdk.api.CRPBaseRes;
import com.coveiot.android.crpsdk.api.CRPFirmwareUpgradeReq;
import com.coveiot.android.crpsdk.error.CRPError;
import com.coveiot.android.crpsdk.error.CRPErrorType;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import com.crrepa.ble.conn.listener.CRPBleFirmwareUpgradeListener;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class CRPBaseBleService$crpFirmwareUpgradeListener$1 implements CRPBleFirmwareUpgradeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ CRPBaseBleService f3702a;

    public CRPBaseBleService$crpFirmwareUpgradeListener$1(CRPBaseBleService cRPBaseBleService) {
        this.f3702a = cRPBaseBleService;
    }

    public static final void a(CRPBaseBleService this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getKhCurrentCommand() == null || !(this$0.getKhCurrentCommand() instanceof CRPFirmwareUpgradeReq)) {
            return;
        }
        CRPBaseRes cRPBaseRes = new CRPBaseRes();
        CRPBaseReq khCurrentCommand = this$0.getKhCurrentCommand();
        Intrinsics.checkNotNull(khCurrentCommand);
        cRPBaseRes.setBaseReq(khCurrentCommand);
        LiveFirmwareUploadPercentage liveFirmwareUploadPercentage = new LiveFirmwareUploadPercentage();
        liveFirmwareUploadPercentage.setPercentage(i);
        cRPBaseRes.setObj(liveFirmwareUploadPercentage);
        CRPBaseReq khCurrentCommand2 = this$0.getKhCurrentCommand();
        Intrinsics.checkNotNull(khCurrentCommand2);
        CRPResponseListener responseListener = khCurrentCommand2.getResponseListener();
        Intrinsics.checkNotNull(responseListener);
        responseListener.onResponse(cRPBaseRes);
    }

    public static final void b(CRPBaseBleService this$0) {
        String str;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        str = this$0.m;
        LogHelper.d(str, "crpFirmwareUpgradeListener->onUpgradeCompleted->khCurrentCommand " + this$0.getKhCurrentCommand(), ModuleNames.BLEABSTRACT.getModuleName());
        if (this$0.getKhCurrentCommand() == null || !(this$0.getKhCurrentCommand() instanceof CRPFirmwareUpgradeReq)) {
            return;
        }
        CRPBaseRes cRPBaseRes = new CRPBaseRes();
        CRPBaseReq khCurrentCommand = this$0.getKhCurrentCommand();
        Intrinsics.checkNotNull(khCurrentCommand);
        cRPBaseRes.setBaseReq(khCurrentCommand);
        CRPBaseReq khCurrentCommand2 = this$0.getKhCurrentCommand();
        Intrinsics.checkNotNull(khCurrentCommand2);
        CRPResponseListener responseListener = khCurrentCommand2.getResponseListener();
        Intrinsics.checkNotNull(responseListener);
        responseListener.onResponse(cRPBaseRes);
        this$0.setKhCurrentCommand(null);
    }

    @Override // com.crrepa.ble.conn.listener.CRPBleFirmwareUpgradeListener
    public void onError(final int i, @Nullable final String str) {
        Handler handler;
        handler = this.f3702a.c;
        final CRPBaseBleService cRPBaseBleService = this.f3702a;
        handler.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.f0
            @Override // java.lang.Runnable
            public final void run() {
                CRPBaseBleService$crpFirmwareUpgradeListener$1.a(CRPBaseBleService.this, i, str);
            }
        });
    }

    @Override // com.crrepa.ble.conn.listener.CRPBleFirmwareUpgradeListener
    public void onFirmwareDownloadComplete() {
        String str;
        str = this.f3702a.m;
        LogHelper.d(str, "crpFirmwareUpgradeListener->onFirmwareDownloadComplete", ModuleNames.BLEABSTRACT.getModuleName());
    }

    @Override // com.crrepa.ble.conn.listener.CRPBleFirmwareUpgradeListener
    public void onFirmwareDownloadStarting() {
        String str;
        str = this.f3702a.m;
        LogHelper.d(str, "crpFirmwareUpgradeListener->onFirmwareDownloadStarting", ModuleNames.BLEABSTRACT.getModuleName());
    }

    @Override // com.crrepa.ble.conn.listener.CRPBleFirmwareUpgradeListener
    public void onUpgradeAborted() {
        Handler handler;
        handler = this.f3702a.c;
        final CRPBaseBleService cRPBaseBleService = this.f3702a;
        handler.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.c0
            @Override // java.lang.Runnable
            public final void run() {
                CRPBaseBleService$crpFirmwareUpgradeListener$1.a(CRPBaseBleService.this);
            }
        });
    }

    @Override // com.crrepa.ble.conn.listener.CRPBleFirmwareUpgradeListener
    public void onUpgradeCompleted() {
        Handler handler;
        handler = this.f3702a.c;
        final CRPBaseBleService cRPBaseBleService = this.f3702a;
        handler.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.d0
            @Override // java.lang.Runnable
            public final void run() {
                CRPBaseBleService$crpFirmwareUpgradeListener$1.b(CRPBaseBleService.this);
            }
        });
    }

    @Override // com.crrepa.ble.conn.listener.CRPBleFirmwareUpgradeListener
    public void onUpgradeProgressChanged(final int i, float f) {
        Handler handler;
        handler = this.f3702a.c;
        final CRPBaseBleService cRPBaseBleService = this.f3702a;
        handler.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.e0
            @Override // java.lang.Runnable
            public final void run() {
                CRPBaseBleService$crpFirmwareUpgradeListener$1.a(CRPBaseBleService.this, i);
            }
        });
    }

    @Override // com.crrepa.ble.conn.listener.CRPBleFirmwareUpgradeListener
    public void onUpgradeProgressStarting() {
        String str;
        str = this.f3702a.m;
        LogHelper.d(str, "crpFirmwareUpgradeListener->onUpgradeProgressStarting", ModuleNames.BLEABSTRACT.getModuleName());
    }

    public static final void a(CRPBaseBleService this$0) {
        String str;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        str = this$0.m;
        LogHelper.d(str, "crpFirmwareUpgradeListener->onUpgradeAborted->khCurrentCommand " + this$0.getKhCurrentCommand(), ModuleNames.BLEABSTRACT.getModuleName());
        if (this$0.getKhCurrentCommand() == null || !(this$0.getKhCurrentCommand() instanceof CRPFirmwareUpgradeReq)) {
            return;
        }
        CRPBaseReq khCurrentCommand = this$0.getKhCurrentCommand();
        Intrinsics.checkNotNull(khCurrentCommand);
        CRPResponseListener responseListener = khCurrentCommand.getResponseListener();
        Intrinsics.checkNotNull(responseListener);
        responseListener.onFailure(new CRPError(CRPErrorType.COMMAND_REQUEST_ERROR, this$0.getString(R.string.fw_upgrade_aborted)));
        this$0.setKhCurrentCommand(null);
    }

    public static final void a(CRPBaseBleService this$0, int i, String str) {
        String str2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        str2 = this$0.m;
        LogHelper.e(str2, "crpFirmwareUpgradeListener->onError-> p0->" + i + " p1->" + str + " , khCurrentCommand " + this$0.getKhCurrentCommand(), ModuleNames.BLEABSTRACT.getModuleName());
        if (this$0.getKhCurrentCommand() == null || !(this$0.getKhCurrentCommand() instanceof CRPFirmwareUpgradeReq)) {
            return;
        }
        CRPBaseReq khCurrentCommand = this$0.getKhCurrentCommand();
        Intrinsics.checkNotNull(khCurrentCommand);
        CRPResponseListener responseListener = khCurrentCommand.getResponseListener();
        Intrinsics.checkNotNull(responseListener);
        CRPErrorType cRPErrorType = CRPErrorType.COMMAND_REQUEST_ERROR;
        responseListener.onFailure(new CRPError(cRPErrorType, i + ' ' + str));
        this$0.setKhCurrentCommand(null);
    }
}
