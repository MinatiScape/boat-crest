package com.ido.ble.callback;

import android.os.Handler;
import android.os.Looper;
import com.ido.ble.callback.AppControlDeviceCallBack;
import com.ido.ble.callback.AppExchangeDataCallBack;
import com.ido.ble.callback.AppSendAllPhoneContactsCallBack;
import com.ido.ble.callback.AppSendDataCallBack;
import com.ido.ble.callback.AutoConnectErrorHappenListener;
import com.ido.ble.callback.BindCallBack;
import com.ido.ble.callback.BloodPressureMeasureCallBack;
import com.ido.ble.callback.ConnectCallBack;
import com.ido.ble.callback.DeviceControlAppCallBack;
import com.ido.ble.callback.DeviceExchangeDataCallBack;
import com.ido.ble.callback.DeviceGattCallBack;
import com.ido.ble.callback.DeviceLogCallBack;
import com.ido.ble.callback.DeviceParaChangedCallBack;
import com.ido.ble.callback.DeviceResponseCommonCallBack;
import com.ido.ble.callback.DeviceUpgradeEventListener;
import com.ido.ble.callback.EnterDfuModeCallback;
import com.ido.ble.callback.GetDeviceInfoCallBack;
import com.ido.ble.callback.GetDeviceParaCallBack;
import com.ido.ble.callback.NoticeSportActionToggleCallBack;
import com.ido.ble.callback.OperateCallBack;
import com.ido.ble.callback.OtherProtocolCallBack;
import com.ido.ble.callback.PhoneMsgNoticeCallBack;
import com.ido.ble.callback.QueryStatusCallBack;
import com.ido.ble.callback.RebootCallback;
import com.ido.ble.callback.ScanCallBack;
import com.ido.ble.callback.SetPressCalibrationCallBack;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.callback.SportPlanCallBack;
import com.ido.ble.callback.SyncCallBack;
import com.ido.ble.callback.SyncV3CallBack;
import com.ido.ble.callback.UnbindCallBack;
import com.ido.ble.callback.UserHabitCallBack;
import com.ido.ble.callback.V3AppExchangeDataCallBack;
import com.ido.ble.callback.VoiceCallBack;
import com.ido.ble.callback.c;
import com.ido.ble.callback.e;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public final class b {
    private static b O;

    /* renamed from: a  reason: collision with root package name */
    private List<ScanCallBack.ICallBack> f12143a = new ArrayList();
    private List<ConnectCallBack.ICallBack> b = new ArrayList();
    private List<SettingCallBack.ICallBack> c = new ArrayList();
    private List<BindCallBack.ICallBack> d = new ArrayList();
    private List<UnbindCallBack.ICallBack> e = new ArrayList();
    private List<AppExchangeDataCallBack.ICallBack> f = new ArrayList();
    private List<DeviceExchangeDataCallBack.ICallBack> g = new ArrayList();
    private List<AppSendDataCallBack.ICallBack> h = new ArrayList();
    private List<AppSendAllPhoneContactsCallBack.ICallBack> i = new ArrayList();
    private List<BloodPressureMeasureCallBack.ICallBack> j = new ArrayList();
    private List<AppControlDeviceCallBack.ICallBack> k = new ArrayList();
    private List<DeviceControlAppCallBack.ICallBack> l = new ArrayList();
    private List<GetDeviceInfoCallBack.ICallBack> m = new ArrayList();
    private List<PhoneMsgNoticeCallBack.ICallBack> n = new ArrayList();
    private List<SyncCallBack.IHealthCallBack> o = new ArrayList();
    private List<SyncCallBack.IActivityCallBack> p = new ArrayList();
    private List<SyncCallBack.IConfigCallBack> q = new ArrayList();
    private List<RebootCallback.ICallBack> r = new ArrayList();
    private List<EnterDfuModeCallback.ICallBack> s = new ArrayList();
    private List<QueryStatusCallBack.ICallBack> t = new ArrayList();
    private List<DeviceGattCallBack.ICallBack> u = new ArrayList();
    private List<OtherProtocolCallBack.ICallBack> v = new ArrayList();
    private List<SyncV3CallBack.ICallBack> w = new ArrayList();
    private List<GetDeviceParaCallBack.ICallBack> x = new ArrayList();
    private List<DeviceParaChangedCallBack.ICallBack> y = new ArrayList();
    private List<DeviceResponseCommonCallBack.ICallBack> z = new ArrayList();
    private List<VoiceCallBack.ICallBack> A = new ArrayList();
    private List<DeviceLogCallBack.ICallBack> B = new ArrayList();
    private List<V3AppExchangeDataCallBack.ICallBack> C = new ArrayList();
    private List<AutoConnectErrorHappenListener.IListener> D = new ArrayList();
    private List<DeviceUpgradeEventListener.IListener> E = new ArrayList();
    private List<OperateCallBack.ICallBack> F = new ArrayList();
    private List<SetPressCalibrationCallBack.ICallBack> G = new ArrayList();
    private List<OperateCallBack.IMusicCallBack> H = new ArrayList();
    private List<c.b> I = new ArrayList();
    private List<e.b> J = new ArrayList();
    private List<UserHabitCallBack.ICallBack> K = new ArrayList();
    private List<SportPlanCallBack.ICallBack> L = new ArrayList();
    private List<NoticeSportActionToggleCallBack.ICallBack> M = new ArrayList();
    private Handler N = new Handler(Looper.getMainLooper());

    private b() {
    }

    public static b N() {
        if (O == null) {
            O = new b();
        }
        return O;
    }

    public List<ScanCallBack.ICallBack> A() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f12143a);
        return arrayList;
    }

    public List<SetPressCalibrationCallBack.ICallBack> B() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.G);
        return arrayList;
    }

    public List<SettingCallBack.ICallBack> C() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.c);
        return arrayList;
    }

    public List<SportPlanCallBack.ICallBack> D() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.L);
        return arrayList;
    }

    public List<SyncCallBack.IActivityCallBack> E() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.p);
        return arrayList;
    }

    public List<SyncCallBack.IConfigCallBack> F() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.q);
        return arrayList;
    }

    public List<SyncCallBack.IHealthCallBack> G() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.o);
        return arrayList;
    }

    public List<SyncV3CallBack.ICallBack> H() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.w);
        return arrayList;
    }

    public List<UnbindCallBack.ICallBack> I() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.e);
        return arrayList;
    }

    public List<e.b> J() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.J);
        return arrayList;
    }

    public List<UserHabitCallBack.ICallBack> K() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.K);
        return arrayList;
    }

    public List<V3AppExchangeDataCallBack.ICallBack> L() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.C);
        return arrayList;
    }

    public List<VoiceCallBack.ICallBack> M() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.A);
        return arrayList;
    }

    public List<AppControlDeviceCallBack.ICallBack> a() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.k);
        return arrayList;
    }

    public void a(AppControlDeviceCallBack.ICallBack iCallBack) {
        this.k.add(iCallBack);
    }

    public void a(AppExchangeDataCallBack.ICallBack iCallBack) {
        this.f.add(iCallBack);
    }

    public void a(AppSendAllPhoneContactsCallBack.ICallBack iCallBack) {
        this.i.add(iCallBack);
    }

    public void a(AppSendDataCallBack.ICallBack iCallBack) {
        this.h.add(iCallBack);
    }

    public void a(AutoConnectErrorHappenListener.IListener iListener) {
        this.D.add(iListener);
    }

    public void a(BindCallBack.ICallBack iCallBack) {
        this.d.add(iCallBack);
    }

    public void a(BloodPressureMeasureCallBack.ICallBack iCallBack) {
        this.j.add(iCallBack);
    }

    public void a(ConnectCallBack.ICallBack iCallBack) {
        this.b.add(iCallBack);
    }

    public void a(DeviceControlAppCallBack.ICallBack iCallBack) {
        this.l.add(iCallBack);
    }

    public void a(DeviceExchangeDataCallBack.ICallBack iCallBack) {
        this.g.add(iCallBack);
    }

    public void a(DeviceGattCallBack.ICallBack iCallBack) {
        this.u.add(iCallBack);
    }

    public void a(DeviceLogCallBack.ICallBack iCallBack) {
        this.B.add(iCallBack);
    }

    public void a(DeviceParaChangedCallBack.ICallBack iCallBack) {
        this.y.add(iCallBack);
    }

    public void a(DeviceResponseCommonCallBack.ICallBack iCallBack) {
        this.z.add(iCallBack);
    }

    public void a(DeviceUpgradeEventListener.IListener iListener) {
        this.E.add(iListener);
    }

    public void a(EnterDfuModeCallback.ICallBack iCallBack) {
        this.s.add(iCallBack);
    }

    public void a(GetDeviceInfoCallBack.ICallBack iCallBack) {
        this.m.add(iCallBack);
    }

    public void a(GetDeviceParaCallBack.ICallBack iCallBack) {
        this.x.add(iCallBack);
    }

    public void a(NoticeSportActionToggleCallBack.ICallBack iCallBack) {
        this.M.add(iCallBack);
    }

    public void a(OperateCallBack.ICallBack iCallBack) {
        this.F.add(iCallBack);
    }

    public void a(OperateCallBack.IMusicCallBack iMusicCallBack) {
        this.H.add(iMusicCallBack);
    }

    public void a(OtherProtocolCallBack.ICallBack iCallBack) {
        this.v.add(iCallBack);
    }

    public void a(PhoneMsgNoticeCallBack.ICallBack iCallBack) {
        this.n.add(iCallBack);
    }

    public void a(QueryStatusCallBack.ICallBack iCallBack) {
        this.t.add(iCallBack);
    }

    public void a(RebootCallback.ICallBack iCallBack) {
        this.r.add(iCallBack);
    }

    public void a(ScanCallBack.ICallBack iCallBack) {
        this.f12143a.add(iCallBack);
    }

    public void a(SetPressCalibrationCallBack.ICallBack iCallBack) {
        this.G.add(iCallBack);
    }

    public void a(SettingCallBack.ICallBack iCallBack) {
        this.c.add(iCallBack);
    }

    public void a(SportPlanCallBack.ICallBack iCallBack) {
        this.L.add(iCallBack);
    }

    public void a(SyncCallBack.IActivityCallBack iActivityCallBack) {
        this.p.add(iActivityCallBack);
    }

    public void a(SyncCallBack.IConfigCallBack iConfigCallBack) {
        this.q.add(iConfigCallBack);
    }

    public void a(SyncCallBack.IHealthCallBack iHealthCallBack) {
        this.o.add(iHealthCallBack);
    }

    public void a(SyncV3CallBack.ICallBack iCallBack) {
        this.w.add(iCallBack);
    }

    public void a(UnbindCallBack.ICallBack iCallBack) {
        this.e.add(iCallBack);
    }

    public void a(UserHabitCallBack.ICallBack iCallBack) {
        this.K.add(iCallBack);
    }

    public void a(V3AppExchangeDataCallBack.ICallBack iCallBack) {
        this.C.add(iCallBack);
    }

    public void a(VoiceCallBack.ICallBack iCallBack) {
        this.A.add(iCallBack);
    }

    public void a(c.b bVar) {
        this.I.add(bVar);
    }

    public void a(e.b bVar) {
        this.J.add(bVar);
    }

    public void a(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            this.N.post(runnable);
        }
    }

    public List<AppExchangeDataCallBack.ICallBack> b() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f);
        return arrayList;
    }

    public void b(AppControlDeviceCallBack.ICallBack iCallBack) {
        this.k.remove(iCallBack);
    }

    public void b(AppExchangeDataCallBack.ICallBack iCallBack) {
        this.f.remove(iCallBack);
    }

    public void b(AppSendAllPhoneContactsCallBack.ICallBack iCallBack) {
        this.i.remove(iCallBack);
    }

    public void b(AppSendDataCallBack.ICallBack iCallBack) {
        this.h.remove(iCallBack);
    }

    public void b(AutoConnectErrorHappenListener.IListener iListener) {
        this.D.remove(iListener);
    }

    public void b(BindCallBack.ICallBack iCallBack) {
        this.d.remove(iCallBack);
    }

    public void b(BloodPressureMeasureCallBack.ICallBack iCallBack) {
        this.j.remove(iCallBack);
    }

    public void b(ConnectCallBack.ICallBack iCallBack) {
        this.b.remove(iCallBack);
    }

    public void b(DeviceControlAppCallBack.ICallBack iCallBack) {
        this.l.remove(iCallBack);
    }

    public void b(DeviceExchangeDataCallBack.ICallBack iCallBack) {
        this.g.remove(iCallBack);
    }

    public void b(DeviceGattCallBack.ICallBack iCallBack) {
        this.u.remove(iCallBack);
    }

    public void b(DeviceLogCallBack.ICallBack iCallBack) {
        this.B.remove(iCallBack);
    }

    public void b(DeviceParaChangedCallBack.ICallBack iCallBack) {
        this.y.remove(iCallBack);
    }

    public void b(DeviceResponseCommonCallBack.ICallBack iCallBack) {
        this.z.remove(iCallBack);
    }

    public void b(DeviceUpgradeEventListener.IListener iListener) {
        this.E.remove(iListener);
    }

    public void b(EnterDfuModeCallback.ICallBack iCallBack) {
        this.s.remove(iCallBack);
    }

    public void b(GetDeviceInfoCallBack.ICallBack iCallBack) {
        this.m.remove(iCallBack);
    }

    public void b(GetDeviceParaCallBack.ICallBack iCallBack) {
        this.x.remove(iCallBack);
    }

    public void b(NoticeSportActionToggleCallBack.ICallBack iCallBack) {
        this.M.remove(iCallBack);
    }

    public void b(OperateCallBack.ICallBack iCallBack) {
        this.F.remove(iCallBack);
    }

    public void b(OperateCallBack.IMusicCallBack iMusicCallBack) {
        this.H.remove(iMusicCallBack);
    }

    public void b(OtherProtocolCallBack.ICallBack iCallBack) {
        this.v.remove(iCallBack);
    }

    public void b(PhoneMsgNoticeCallBack.ICallBack iCallBack) {
        this.n.remove(iCallBack);
    }

    public void b(QueryStatusCallBack.ICallBack iCallBack) {
        this.t.remove(iCallBack);
    }

    public void b(RebootCallback.ICallBack iCallBack) {
        this.r.remove(iCallBack);
    }

    public void b(ScanCallBack.ICallBack iCallBack) {
        this.f12143a.remove(iCallBack);
    }

    public void b(SetPressCalibrationCallBack.ICallBack iCallBack) {
        this.G.remove(iCallBack);
    }

    public void b(SettingCallBack.ICallBack iCallBack) {
        this.c.remove(iCallBack);
    }

    public void b(SportPlanCallBack.ICallBack iCallBack) {
        this.L.remove(iCallBack);
    }

    public void b(SyncCallBack.IActivityCallBack iActivityCallBack) {
        this.p.remove(iActivityCallBack);
    }

    public void b(SyncCallBack.IConfigCallBack iConfigCallBack) {
        this.q.remove(iConfigCallBack);
    }

    public void b(SyncCallBack.IHealthCallBack iHealthCallBack) {
        this.o.remove(iHealthCallBack);
    }

    public void b(SyncV3CallBack.ICallBack iCallBack) {
        this.w.remove(iCallBack);
    }

    public void b(UnbindCallBack.ICallBack iCallBack) {
        this.e.remove(iCallBack);
    }

    public void b(UserHabitCallBack.ICallBack iCallBack) {
        this.K.remove(iCallBack);
    }

    public void b(V3AppExchangeDataCallBack.ICallBack iCallBack) {
        this.C.remove(iCallBack);
    }

    public void b(VoiceCallBack.ICallBack iCallBack) {
        this.A.remove(iCallBack);
    }

    public void b(c.b bVar) {
        this.I.remove(bVar);
    }

    public void b(e.b bVar) {
        this.J.remove(bVar);
    }

    public List<AppSendAllPhoneContactsCallBack.ICallBack> c() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.i);
        return arrayList;
    }

    public List<AppSendDataCallBack.ICallBack> d() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.h);
        return arrayList;
    }

    public List<AutoConnectErrorHappenListener.IListener> e() {
        return new ArrayList(this.D);
    }

    public List<BindCallBack.ICallBack> f() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.d);
        return arrayList;
    }

    public List<BloodPressureMeasureCallBack.ICallBack> g() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.j);
        return arrayList;
    }

    public List<ConnectCallBack.ICallBack> h() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.b);
        return arrayList;
    }

    public List<DeviceControlAppCallBack.ICallBack> i() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.l);
        return arrayList;
    }

    public List<DeviceExchangeDataCallBack.ICallBack> j() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.g);
        return arrayList;
    }

    public List<DeviceGattCallBack.ICallBack> k() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.u);
        return arrayList;
    }

    public List<DeviceLogCallBack.ICallBack> l() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.B);
        return arrayList;
    }

    public List<DeviceParaChangedCallBack.ICallBack> m() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.y);
        return arrayList;
    }

    public List<DeviceResponseCommonCallBack.ICallBack> n() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.z);
        return arrayList;
    }

    public List<DeviceUpgradeEventListener.IListener> o() {
        return new ArrayList(this.E);
    }

    public List<c.b> p() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.I);
        return arrayList;
    }

    public List<EnterDfuModeCallback.ICallBack> q() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.s);
        return arrayList;
    }

    public List<GetDeviceInfoCallBack.ICallBack> r() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.m);
        return arrayList;
    }

    public List<GetDeviceParaCallBack.ICallBack> s() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.x);
        return arrayList;
    }

    public List<NoticeSportActionToggleCallBack.ICallBack> t() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.M);
        return arrayList;
    }

    public List<OperateCallBack.ICallBack> u() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.F);
        return arrayList;
    }

    public List<OperateCallBack.IMusicCallBack> v() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.H);
        return arrayList;
    }

    public List<OtherProtocolCallBack.ICallBack> w() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.v);
        return arrayList;
    }

    public List<PhoneMsgNoticeCallBack.ICallBack> x() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.n);
        return arrayList;
    }

    public List<QueryStatusCallBack.ICallBack> y() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.t);
        return arrayList;
    }

    public List<RebootCallback.ICallBack> z() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.r);
        return arrayList;
    }
}
