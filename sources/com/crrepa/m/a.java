package com.crrepa.m;

import android.bluetooth.BluetoothGattCharacteristic;
import android.text.TextUtils;
import com.crrepa.ble.conn.bean.CRPAlarmClockInfo;
import com.crrepa.ble.conn.bean.CRPBloodOxygenInfo;
import com.crrepa.ble.conn.bean.CRPBloodPressureInfo;
import com.crrepa.ble.conn.bean.CRPDrinkWaterPeriodInfo;
import com.crrepa.ble.conn.bean.CRPFunctionInfo;
import com.crrepa.ble.conn.bean.CRPHandWashingPeriodInfo;
import com.crrepa.ble.conn.bean.CRPHeartRateInfo;
import com.crrepa.ble.conn.bean.CRPHistoryBloodOxygenInfo;
import com.crrepa.ble.conn.bean.CRPHistoryBloodPressureInfo;
import com.crrepa.ble.conn.bean.CRPHistoryHeartRateInfo;
import com.crrepa.ble.conn.bean.CRPHrvInfo;
import com.crrepa.ble.conn.bean.CRPMovementHeartRateInfo;
import com.crrepa.ble.conn.bean.CRPPeriodTimeInfo;
import com.crrepa.ble.conn.bean.CRPPhysiologcalPeriodInfo;
import com.crrepa.ble.conn.bean.CRPSedentaryReminderPeriodInfo;
import com.crrepa.ble.conn.bean.CRPSleepActionInfo;
import com.crrepa.ble.conn.bean.CRPSleepInfo;
import com.crrepa.ble.conn.bean.CRPStepsCategoryInfo;
import com.crrepa.ble.conn.bean.CRPSupportWatchFaceInfo;
import com.crrepa.ble.conn.bean.CRPTempInfo;
import com.crrepa.ble.conn.bean.CRPTrainingInfo;
import com.crrepa.ble.conn.bean.CRPWatchFaceLayoutInfo;
import com.crrepa.ble.conn.callback.CRPBtAddressCallback;
import com.crrepa.ble.conn.callback.CRPContactConfigCallback;
import com.crrepa.ble.conn.callback.CRPContactCountCallback;
import com.crrepa.ble.conn.callback.CRPDeviceAlarmClockCallback;
import com.crrepa.ble.conn.callback.CRPDeviceBondStateCallback;
import com.crrepa.ble.conn.callback.CRPDeviceBreathingLightCallback;
import com.crrepa.ble.conn.callback.CRPDeviceBrightnessCallback;
import com.crrepa.ble.conn.callback.CRPDeviceDfuAddressCallback;
import com.crrepa.ble.conn.callback.CRPDeviceDisplayTimeCallback;
import com.crrepa.ble.conn.callback.CRPDeviceDisplayWatchFaceCallback;
import com.crrepa.ble.conn.callback.CRPDeviceDominantHandCallback;
import com.crrepa.ble.conn.callback.CRPDeviceDrinkWaterPeriodCallback;
import com.crrepa.ble.conn.callback.CRPDeviceFunctionCallback;
import com.crrepa.ble.conn.callback.CRPDeviceGoalStepCallback;
import com.crrepa.ble.conn.callback.CRPDeviceHandWashingPeriodCallback;
import com.crrepa.ble.conn.callback.CRPDeviceLanguageCallback;
import com.crrepa.ble.conn.callback.CRPDeviceMaxHeartRateCallback;
import com.crrepa.ble.conn.callback.CRPDeviceMetricSystemCallback;
import com.crrepa.ble.conn.callback.CRPDeviceOtherMessageCallback;
import com.crrepa.ble.conn.callback.CRPDevicePeriodTimeCallback;
import com.crrepa.ble.conn.callback.CRPDevicePhysiologcalPeriodCallback;
import com.crrepa.ble.conn.callback.CRPDeviceQuickViewCallback;
import com.crrepa.ble.conn.callback.CRPDeviceSedentaryReminderCallback;
import com.crrepa.ble.conn.callback.CRPDeviceSedentaryReminderPeriodCallback;
import com.crrepa.ble.conn.callback.CRPDeviceSupportWatchFaceCallback;
import com.crrepa.ble.conn.callback.CRPDeviceTimeSystemCallback;
import com.crrepa.ble.conn.callback.CRPDeviceTimingMeasureHeartRateCallback;
import com.crrepa.ble.conn.callback.CRPDeviceVersionCallback;
import com.crrepa.ble.conn.callback.CRPDeviceWatchFaceLayoutCallback;
import com.crrepa.ble.conn.callback.CRPDeviceWatchFaceListCallback;
import com.crrepa.ble.conn.callback.CRPPillReminderCallback;
import com.crrepa.ble.conn.callback.CRPTapToWakeCallback;
import com.crrepa.ble.conn.callback.CRPTimingTempStateCallback;
import com.crrepa.ble.conn.listener.CRPA2DPConnectStateListener;
import com.crrepa.ble.conn.listener.CRPBatterySavingChangeListener;
import com.crrepa.ble.conn.listener.CRPBleECGChangeListener;
import com.crrepa.ble.conn.listener.CRPBloodOxygenChangeListener;
import com.crrepa.ble.conn.listener.CRPBloodPressureChangeListener;
import com.crrepa.ble.conn.listener.CRPCallNumberListener;
import com.crrepa.ble.conn.listener.CRPCameraOperationListener;
import com.crrepa.ble.conn.listener.CRPContactListener;
import com.crrepa.ble.conn.listener.CRPFindPhoneListener;
import com.crrepa.ble.conn.listener.CRPHeartRateChangeListener;
import com.crrepa.ble.conn.listener.CRPHrvChangeListener;
import com.crrepa.ble.conn.listener.CRPMovementStateListener;
import com.crrepa.ble.conn.listener.CRPPhoneOperationListener;
import com.crrepa.ble.conn.listener.CRPSleepActionChangeListener;
import com.crrepa.ble.conn.listener.CRPSleepChangeListener;
import com.crrepa.ble.conn.listener.CRPSosChangeListener;
import com.crrepa.ble.conn.listener.CRPStepChangeListener;
import com.crrepa.ble.conn.listener.CRPStepsCategoryChangeListener;
import com.crrepa.ble.conn.listener.CRPTempChangeListener;
import com.crrepa.ble.conn.listener.CRPTrainingChangeListener;
import com.crrepa.ble.conn.listener.CRPWeatherChangeListener;
import com.crrepa.ble.conn.type.CRPEcgMeasureType;
import com.crrepa.ble.conn.type.CRPTempTimeType;
import com.crrepa.ble.trans.tp.CRPTpTransInitiator;
import com.crrepa.ble.trans.ui.CRPUiTransInitiator;
import com.crrepa.f.d0;
import com.crrepa.f.f0;
import com.crrepa.f.j0;
import com.crrepa.f.m0;
import com.crrepa.f.w0;
import com.crrepa.j.h;
import com.crrepa.j.i;
import com.crrepa.j.j;
import com.crrepa.j.k;
import com.crrepa.j.l;
import com.crrepa.j.m;
import com.crrepa.j.o;
import com.crrepa.j.p;
import com.crrepa.j.q;
import com.crrepa.j.r;
import com.crrepa.j.s;
import com.crrepa.j.t;
import com.crrepa.j.u;
import com.crrepa.j.v;
import com.crrepa.j.w;
import com.crrepa.j.x;
import com.crrepa.j.y;
import com.google.android.play.core.integrity.model.IntegrityErrorCode;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class a extends c {
    public CRPBatterySavingChangeListener A;
    public CRPHrvChangeListener B;
    public CRPCallNumberListener C;
    public CRPTrainingChangeListener D;
    public CRPDeviceAlarmClockCallback E;
    public CRPDeviceDominantHandCallback F;
    public CRPDeviceGoalStepCallback G;
    public CRPDeviceLanguageCallback H;
    public CRPDeviceMetricSystemCallback I;
    public CRPDeviceOtherMessageCallback J;
    public CRPDeviceQuickViewCallback K;
    public CRPDeviceSedentaryReminderCallback L;
    public CRPDeviceTimeSystemCallback M;
    public CRPDeviceDisplayWatchFaceCallback N;
    public CRPDeviceVersionCallback O;
    public CRPDeviceFunctionCallback P;
    public CRPDevicePeriodTimeCallback Q;
    public CRPDeviceTimingMeasureHeartRateCallback R;
    public CRPDeviceBreathingLightCallback S;
    public CRPDeviceWatchFaceLayoutCallback T;
    public CRPDeviceSedentaryReminderPeriodCallback U;
    public CRPDeviceSupportWatchFaceCallback V;
    public CRPDeviceDfuAddressCallback W;
    public CRPDevicePhysiologcalPeriodCallback X;
    public CRPDeviceDrinkWaterPeriodCallback Y;
    public CRPDeviceMaxHeartRateCallback Z;
    public CRPTimingTempStateCallback a0;
    public CRPDeviceDisplayTimeCallback b0;
    public CRPDeviceHandWashingPeriodCallback c0;
    public CRPDeviceBrightnessCallback d0;
    public CRPBtAddressCallback e0;
    public CRPContactConfigCallback f0;
    public com.crrepa.k.a g;
    public CRPDeviceBondStateCallback g0;
    public CRPContactCountCallback h0;
    public CRPDeviceWatchFaceListCallback i0;
    public CRPPillReminderCallback j0;
    public CRPStepChangeListener k;
    public CRPTapToWakeCallback k0;
    public CRPSleepChangeListener l;
    public CRPA2DPConnectStateListener l0;
    public CRPHeartRateChangeListener m;
    public CRPBloodPressureChangeListener n;
    public CRPBloodOxygenChangeListener o;
    public CRPPhoneOperationListener p;
    public CRPCameraOperationListener q;
    public CRPWeatherChangeListener r;
    public CRPFindPhoneListener s;
    public CRPBleECGChangeListener t;
    public CRPStepsCategoryChangeListener u;
    public CRPSleepActionChangeListener v;
    public CRPMovementStateListener w;
    public CRPTempChangeListener x;
    public CRPContactListener y;
    public CRPSosChangeListener z;

    /* renamed from: a  reason: collision with root package name */
    public boolean f7752a = true;
    public int b = 0;
    public int c = 0;
    public byte[] d = null;
    public com.crrepa.j.g e = new com.crrepa.j.g();
    public u f = new u();
    public v h = new v();
    public com.crrepa.j.b i = new com.crrepa.j.b();
    public com.crrepa.j.c j = new com.crrepa.j.c();
    public boolean m0 = false;

    public final void A(byte[] bArr) {
        boolean a2 = f0.a(bArr);
        CRPDeviceOtherMessageCallback cRPDeviceOtherMessageCallback = this.J;
        if (cRPDeviceOtherMessageCallback != null) {
            cRPDeviceOtherMessageCallback.onOtherMessage(a2);
        }
    }

    public final void B(byte[] bArr) {
        CRPDevicePhysiologcalPeriodCallback cRPDevicePhysiologcalPeriodCallback;
        CRPPhysiologcalPeriodInfo a2 = l.a(bArr);
        if (a2 == null || (cRPDevicePhysiologcalPeriodCallback = this.X) == null) {
            return;
        }
        cRPDevicePhysiologcalPeriodCallback.onPhysiologcalPeriod(a2);
    }

    public final void C(byte[] bArr) {
        if (this.j0 == null || com.crrepa.i0.e.e(bArr)) {
            return;
        }
        this.j0.onPillReminder(bArr[0], m.b(bArr));
    }

    public final void D(byte[] bArr) {
        if (com.crrepa.i0.e.e(bArr)) {
            return;
        }
        byte b = bArr[0];
        if (b == -18) {
            CRPContactCountCallback cRPContactCountCallback = this.h0;
            if (cRPContactCountCallback != null) {
                cRPContactCountCallback.onContactCount(bArr[1]);
            }
        } else if (b != -2) {
            if (b == 0 && this.f0 != null) {
                this.f0.onContactConfig(o.a(bArr));
            }
        } else {
            CRPContactListener cRPContactListener = this.y;
            if (cRPContactListener != null) {
                byte b2 = bArr[1];
                if (bArr[2] == 0) {
                    cRPContactListener.onSavedSuccess(b2);
                } else {
                    cRPContactListener.onSavedFail(b2);
                }
            }
        }
    }

    public final void E(byte[] bArr) {
        boolean a2 = j0.a(bArr);
        CRPDeviceQuickViewCallback cRPDeviceQuickViewCallback = this.K;
        if (cRPDeviceQuickViewCallback != null) {
            cRPDeviceQuickViewCallback.onQuickView(a2);
        }
    }

    public final void F(byte[] bArr) {
        if (com.crrepa.i0.e.e(bArr) || bArr.length < 2) {
            return;
        }
        int a2 = com.crrepa.i0.e.a(bArr[1]);
        int b = 4 <= bArr.length ? com.crrepa.i0.e.b(bArr[3], bArr[2]) : 1024;
        if (1024 == b) {
            M(a2);
            return;
        }
        CRPHrvChangeListener cRPHrvChangeListener = this.B;
        if (cRPHrvChangeListener != null) {
            cRPHrvChangeListener.onRealRri(b, a2);
        }
    }

    public final void G(byte[] bArr) {
        if (com.crrepa.i0.e.e(bArr)) {
            return;
        }
        byte b = bArr[0];
        if (b == 1) {
            c(bArr);
        } else if (b == 2) {
            e(bArr);
        } else if (b == 3) {
            d(bArr);
        } else if (b != 4) {
        } else {
            b(bArr);
        }
    }

    public final int H(byte b, byte b2) {
        return b == 16 ? b2 : com.crrepa.i0.e.b((byte) (b & 1), b2);
    }

    public final void I(int i, CRPPeriodTimeInfo cRPPeriodTimeInfo) {
        CRPDevicePeriodTimeCallback cRPDevicePeriodTimeCallback = this.Q;
        if (cRPDevicePeriodTimeCallback != null) {
            cRPDevicePeriodTimeCallback.onPeriodTime(i, cRPPeriodTimeInfo);
        }
    }

    public final void J(CRPHeartRateInfo cRPHeartRateInfo) {
        CRPHeartRateChangeListener cRPHeartRateChangeListener = this.m;
        if (cRPHeartRateChangeListener == null || cRPHeartRateInfo == null) {
            return;
        }
        cRPHeartRateChangeListener.on24HourMeasureResult(cRPHeartRateInfo);
    }

    public final void K(byte[] bArr, int i) {
        I(i, k.a(bArr));
    }

    public final void L(byte[] bArr) {
        CRPSedentaryReminderPeriodInfo a2 = q.a(bArr);
        CRPDeviceSedentaryReminderPeriodCallback cRPDeviceSedentaryReminderPeriodCallback = this.U;
        if (cRPDeviceSedentaryReminderPeriodCallback == null || a2 == null) {
            return;
        }
        cRPDeviceSedentaryReminderPeriodCallback.onSedentaryReminderPeriod(a2);
    }

    public final void M(int i) {
        CRPHeartRateChangeListener cRPHeartRateChangeListener = this.m;
        if (cRPHeartRateChangeListener != null) {
            cRPHeartRateChangeListener.onMeasuring(i);
        }
    }

    public final void N(byte[] bArr) {
        boolean a2 = m0.a(bArr);
        CRPDeviceSedentaryReminderCallback cRPDeviceSedentaryReminderCallback = this.L;
        if (cRPDeviceSedentaryReminderCallback != null) {
            cRPDeviceSedentaryReminderCallback.onSedentaryReminder(a2);
        }
    }

    public final void O(byte[] bArr) {
        com.crrepa.o.a.a().c();
        CRPSleepInfo a2 = s.a(bArr, false);
        if (this.l != null) {
            if (this.m0) {
                a2 = p.a(a2);
            }
            this.l.onSleepChange(a2);
        }
    }

    public final void P(byte[] bArr) {
        CRPSleepActionInfo a2 = r.a(bArr);
        CRPSleepActionChangeListener cRPSleepActionChangeListener = this.v;
        if (cRPSleepActionChangeListener == null || a2 == null) {
            return;
        }
        cRPSleepActionChangeListener.onSleepActionChange(a2);
    }

    public final void Q(byte[] bArr) {
        if (this.k != null) {
            this.k.onStepChange(t.a(bArr));
        }
    }

    public final void R(byte[] bArr) {
        CRPStepsCategoryChangeListener cRPStepsCategoryChangeListener;
        CRPStepsCategoryInfo b = this.f.b(bArr);
        if (b == null || (cRPStepsCategoryChangeListener = this.u) == null) {
            return;
        }
        cRPStepsCategoryChangeListener.onStepsCategoryChange(b);
    }

    public final void S(byte[] bArr) {
        CRPSupportWatchFaceInfo a2 = y.a(bArr);
        CRPDeviceSupportWatchFaceCallback cRPDeviceSupportWatchFaceCallback = this.V;
        if (cRPDeviceSupportWatchFaceCallback == null || a2 == null) {
            return;
        }
        cRPDeviceSupportWatchFaceCallback.onSupportWatchFace(a2);
    }

    public final void T(byte[] bArr) {
        float[] d;
        if (this.x == null || (d = this.h.d(bArr)) == null) {
            return;
        }
        List<Float> arrayList = new ArrayList<>();
        int i = 0;
        for (float f : d) {
            arrayList.add(Float.valueOf(f));
        }
        CRPTempTimeType a2 = this.h.a(bArr[0]);
        if (CRPTempTimeType.TODAY == a2) {
            arrayList = this.h.a(arrayList, 30);
        } else {
            i = -1;
        }
        this.x.onContinueTemp(new CRPTempInfo(a2, com.crrepa.i0.g.a(i), arrayList));
    }

    public final void U(byte[] bArr) {
        CRPTapToWakeCallback cRPTapToWakeCallback;
        if (com.crrepa.i0.e.e(bArr) || (cRPTapToWakeCallback = this.k0) == null) {
            return;
        }
        cRPTapToWakeCallback.onWakeState(bArr[0] == 1);
    }

    public final void V(byte[] bArr) {
        if (this.a0 == null || com.crrepa.i0.e.e(bArr)) {
            return;
        }
        this.a0.onTimingState(this.h.e(bArr));
    }

    public final void W(byte[] bArr) {
        if (com.crrepa.i0.e.e(bArr)) {
            return;
        }
        byte b = bArr[0];
        int length = bArr.length - 1;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 1, bArr2, 0, length);
        if (b == 0) {
            g(bArr2);
        } else if (b == 1) {
            f(bArr2);
        } else if (b == 3) {
            V(bArr2);
        } else if (b != 4) {
        } else {
            T(bArr2);
        }
    }

    public final void X(byte[] bArr) {
        CRPA2DPConnectStateListener cRPA2DPConnectStateListener;
        if (com.crrepa.i0.e.e(bArr) || (cRPA2DPConnectStateListener = this.l0) == null) {
            return;
        }
        cRPA2DPConnectStateListener.onConnectState(CRPA2DPConnectStateListener.A2DPConnectState.getInstance(bArr[0]));
    }

    public final void Y(byte[] bArr) {
        int a2 = w0.a(bArr);
        CRPDeviceTimeSystemCallback cRPDeviceTimeSystemCallback = this.M;
        if (cRPDeviceTimeSystemCallback != null) {
            cRPDeviceTimeSystemCallback.onTimeSystem(a2);
        }
    }

    public final void Z(byte[] bArr) {
        CRPHeartRateInfo e = this.e.e(bArr);
        if (this.m == null || e == null) {
            return;
        }
        this.m.onMeasureComplete(this.e.c(bArr), e);
    }

    public final void a(int i) {
        CRPMovementStateListener cRPMovementStateListener = this.w;
        if (cRPMovementStateListener != null) {
            cRPMovementStateListener.onMeasureState(i);
        }
    }

    public synchronized void a(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        byte[] value = bluetoothGattCharacteristic.getValue();
        if (value == null) {
            return;
        }
        String lowerCase = bluetoothGattCharacteristic.getUuid().toString().toLowerCase();
        if (lowerCase.contains(com.crrepa.c.a.e)) {
            Q(value);
        } else if (lowerCase.contains(com.crrepa.c.a.l)) {
            F(value);
        } else {
            if (!lowerCase.contains(com.crrepa.c.a.o) && !lowerCase.contains(com.crrepa.c.a.p)) {
                if (lowerCase.contains(com.crrepa.c.a.h)) {
                    d0(value);
                } else if (q0(value)) {
                    d();
                }
            }
            m(value);
        }
    }

    public void a(CRPBtAddressCallback cRPBtAddressCallback) {
        this.e0 = cRPBtAddressCallback;
    }

    public void a(CRPContactConfigCallback cRPContactConfigCallback) {
        this.f0 = cRPContactConfigCallback;
    }

    public void a(CRPContactCountCallback cRPContactCountCallback) {
        this.h0 = cRPContactCountCallback;
    }

    public void a(CRPDeviceAlarmClockCallback cRPDeviceAlarmClockCallback) {
        this.E = cRPDeviceAlarmClockCallback;
    }

    public void a(CRPDeviceBondStateCallback cRPDeviceBondStateCallback) {
        this.g0 = cRPDeviceBondStateCallback;
    }

    public void a(CRPDeviceBreathingLightCallback cRPDeviceBreathingLightCallback) {
        this.S = cRPDeviceBreathingLightCallback;
    }

    public void a(CRPDeviceBrightnessCallback cRPDeviceBrightnessCallback) {
        this.d0 = cRPDeviceBrightnessCallback;
    }

    public void a(CRPDeviceDfuAddressCallback cRPDeviceDfuAddressCallback) {
        this.W = cRPDeviceDfuAddressCallback;
    }

    public void a(CRPDeviceDisplayTimeCallback cRPDeviceDisplayTimeCallback) {
        this.b0 = cRPDeviceDisplayTimeCallback;
    }

    public void a(CRPDeviceDisplayWatchFaceCallback cRPDeviceDisplayWatchFaceCallback) {
        this.N = cRPDeviceDisplayWatchFaceCallback;
    }

    public void a(CRPDeviceDominantHandCallback cRPDeviceDominantHandCallback) {
        this.F = cRPDeviceDominantHandCallback;
    }

    public void a(CRPDeviceDrinkWaterPeriodCallback cRPDeviceDrinkWaterPeriodCallback) {
        this.Y = cRPDeviceDrinkWaterPeriodCallback;
    }

    public void a(CRPDeviceFunctionCallback cRPDeviceFunctionCallback) {
        this.P = cRPDeviceFunctionCallback;
    }

    public void a(CRPDeviceGoalStepCallback cRPDeviceGoalStepCallback) {
        this.G = cRPDeviceGoalStepCallback;
    }

    public void a(CRPDeviceHandWashingPeriodCallback cRPDeviceHandWashingPeriodCallback) {
        this.c0 = cRPDeviceHandWashingPeriodCallback;
    }

    public void a(CRPDeviceLanguageCallback cRPDeviceLanguageCallback) {
        this.H = cRPDeviceLanguageCallback;
    }

    public void a(CRPDeviceMaxHeartRateCallback cRPDeviceMaxHeartRateCallback) {
        this.Z = cRPDeviceMaxHeartRateCallback;
    }

    public void a(CRPDeviceMetricSystemCallback cRPDeviceMetricSystemCallback) {
        this.I = cRPDeviceMetricSystemCallback;
    }

    public void a(CRPDeviceOtherMessageCallback cRPDeviceOtherMessageCallback) {
        this.J = cRPDeviceOtherMessageCallback;
    }

    public void a(CRPDevicePeriodTimeCallback cRPDevicePeriodTimeCallback) {
        this.Q = cRPDevicePeriodTimeCallback;
    }

    public void a(CRPDevicePhysiologcalPeriodCallback cRPDevicePhysiologcalPeriodCallback) {
        this.X = cRPDevicePhysiologcalPeriodCallback;
    }

    public void a(CRPDeviceQuickViewCallback cRPDeviceQuickViewCallback) {
        this.K = cRPDeviceQuickViewCallback;
    }

    public void a(CRPDeviceSedentaryReminderCallback cRPDeviceSedentaryReminderCallback) {
        this.L = cRPDeviceSedentaryReminderCallback;
    }

    public void a(CRPDeviceSedentaryReminderPeriodCallback cRPDeviceSedentaryReminderPeriodCallback) {
        this.U = cRPDeviceSedentaryReminderPeriodCallback;
    }

    public void a(CRPDeviceSupportWatchFaceCallback cRPDeviceSupportWatchFaceCallback) {
        this.V = cRPDeviceSupportWatchFaceCallback;
    }

    public void a(CRPDeviceTimeSystemCallback cRPDeviceTimeSystemCallback) {
        this.M = cRPDeviceTimeSystemCallback;
    }

    public void a(CRPDeviceTimingMeasureHeartRateCallback cRPDeviceTimingMeasureHeartRateCallback) {
        this.R = cRPDeviceTimingMeasureHeartRateCallback;
    }

    public void a(CRPDeviceVersionCallback cRPDeviceVersionCallback) {
        this.O = cRPDeviceVersionCallback;
    }

    public void a(CRPDeviceWatchFaceLayoutCallback cRPDeviceWatchFaceLayoutCallback) {
        this.T = cRPDeviceWatchFaceLayoutCallback;
    }

    public void a(CRPDeviceWatchFaceListCallback cRPDeviceWatchFaceListCallback) {
        this.i0 = cRPDeviceWatchFaceListCallback;
    }

    public void a(CRPPillReminderCallback cRPPillReminderCallback) {
        this.j0 = cRPPillReminderCallback;
    }

    public void a(CRPTapToWakeCallback cRPTapToWakeCallback) {
        this.k0 = cRPTapToWakeCallback;
    }

    public void a(CRPTimingTempStateCallback cRPTimingTempStateCallback) {
        this.a0 = cRPTimingTempStateCallback;
    }

    public void a(CRPA2DPConnectStateListener cRPA2DPConnectStateListener) {
        this.l0 = cRPA2DPConnectStateListener;
    }

    public void a(CRPBatterySavingChangeListener cRPBatterySavingChangeListener) {
        this.A = cRPBatterySavingChangeListener;
    }

    public void a(CRPBleECGChangeListener cRPBleECGChangeListener, CRPEcgMeasureType cRPEcgMeasureType) {
        this.t = cRPBleECGChangeListener;
        this.g = new com.crrepa.k.b().a(cRPEcgMeasureType);
    }

    public void a(CRPBloodOxygenChangeListener cRPBloodOxygenChangeListener) {
        this.o = cRPBloodOxygenChangeListener;
    }

    public void a(CRPBloodPressureChangeListener cRPBloodPressureChangeListener) {
        this.n = cRPBloodPressureChangeListener;
    }

    public void a(CRPCallNumberListener cRPCallNumberListener) {
        this.C = cRPCallNumberListener;
    }

    public void a(CRPCameraOperationListener cRPCameraOperationListener) {
        this.q = cRPCameraOperationListener;
    }

    public void a(CRPContactListener cRPContactListener) {
        this.y = cRPContactListener;
    }

    public void a(CRPFindPhoneListener cRPFindPhoneListener) {
        this.s = cRPFindPhoneListener;
    }

    public void a(CRPHeartRateChangeListener cRPHeartRateChangeListener) {
        this.m = cRPHeartRateChangeListener;
    }

    public void a(CRPHrvChangeListener cRPHrvChangeListener) {
        this.B = cRPHrvChangeListener;
    }

    public void a(CRPMovementStateListener cRPMovementStateListener) {
        this.w = cRPMovementStateListener;
    }

    public void a(CRPPhoneOperationListener cRPPhoneOperationListener) {
        this.p = cRPPhoneOperationListener;
    }

    public void a(CRPSleepActionChangeListener cRPSleepActionChangeListener) {
        this.v = cRPSleepActionChangeListener;
    }

    public void a(CRPSleepChangeListener cRPSleepChangeListener) {
        this.l = cRPSleepChangeListener;
    }

    public void a(CRPSosChangeListener cRPSosChangeListener) {
        this.z = cRPSosChangeListener;
    }

    public void a(CRPStepChangeListener cRPStepChangeListener) {
        this.k = cRPStepChangeListener;
    }

    public void a(CRPStepsCategoryChangeListener cRPStepsCategoryChangeListener) {
        this.u = cRPStepsCategoryChangeListener;
    }

    public void a(CRPTempChangeListener cRPTempChangeListener) {
        this.x = cRPTempChangeListener;
    }

    public void a(CRPTrainingChangeListener cRPTrainingChangeListener) {
        this.D = cRPTrainingChangeListener;
    }

    public void a(CRPWeatherChangeListener cRPWeatherChangeListener) {
        this.r = cRPWeatherChangeListener;
    }

    public void a(boolean z) {
        this.m0 = z;
    }

    public final void a(byte[] bArr) {
        if (this.s == null) {
            return;
        }
        if (com.crrepa.i0.e.e(bArr) || bArr[0] == 0) {
            this.s.onFindPhone();
        } else {
            this.s.onFindPhoneComplete();
        }
    }

    public final void a0(byte[] bArr) {
        CRPBloodOxygenInfo d;
        if (com.crrepa.i0.e.e(bArr) || this.o == null || (d = this.i.d(bArr)) == null) {
            return;
        }
        this.o.onContinueBloodOxygen(d);
    }

    public final void b(byte[] bArr) {
        CRPDeviceBondStateCallback cRPDeviceBondStateCallback = this.g0;
        if (cRPDeviceBondStateCallback == null || bArr.length < 2) {
            return;
        }
        cRPDeviceBondStateCallback.onBondState(bArr[1]);
    }

    public final void b0(byte[] bArr) {
        List<CRPAlarmClockInfo> a2 = com.crrepa.f.b.a(bArr);
        CRPDeviceAlarmClockCallback cRPDeviceAlarmClockCallback = this.E;
        if (cRPDeviceAlarmClockCallback != null) {
            cRPDeviceAlarmClockCallback.onAlarmClock(a2);
        }
    }

    public final void c() {
        CRPWeatherChangeListener cRPWeatherChangeListener = this.r;
        if (cRPWeatherChangeListener != null) {
            cRPWeatherChangeListener.onUpdateWeather();
        }
    }

    public final void c(byte[] bArr) {
        CRPDrinkWaterPeriodInfo a2 = com.crrepa.j.d.a(bArr);
        CRPDeviceDrinkWaterPeriodCallback cRPDeviceDrinkWaterPeriodCallback = this.Y;
        if (cRPDeviceDrinkWaterPeriodCallback == null || a2 == null) {
            return;
        }
        cRPDeviceDrinkWaterPeriodCallback.onDrinkWaterPeriod(a2);
    }

    public final void c0(byte[] bArr) {
        CRPTrainingChangeListener cRPTrainingChangeListener;
        CRPTrainingInfo c;
        if (com.crrepa.i0.e.e(bArr) || (cRPTrainingChangeListener = this.D) == null) {
            return;
        }
        byte b = bArr[0];
        if (b == 1) {
            cRPTrainingChangeListener.onHistoryTrainingChange(w.a(bArr));
        } else if (b == 3) {
            w.b(bArr);
        } else if (b == 5 && (c = w.c(bArr)) != null) {
            this.D.onTrainingChange(c);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final void d() {
        int i;
        CRPHeartRateInfo i2;
        byte[] bArr = this.d;
        if (bArr == null || bArr.length < 5) {
            return;
        }
        byte b = bArr[4];
        int length = bArr.length - 5;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 5, bArr2, 0, length);
        com.crrepa.i0.c.c("cmd: " + ((int) b));
        if (b == -121) {
            G(bArr2);
        } else if (b == -120) {
            n0(bArr2);
        } else if (b == 89) {
            R(bArr2);
        } else if (b == 90) {
            i(bArr2);
        } else {
            switch (b) {
                case -127:
                    i = 1;
                    break;
                case -126:
                    i = 2;
                    break;
                case -125:
                    L(bArr2);
                    return;
                case -124:
                    S(bArr2);
                    return;
                case -123:
                    B(bArr2);
                    return;
                default:
                    switch (b) {
                        case -116:
                            o0(bArr2);
                            return;
                        case -115:
                            j(bArr2);
                            return;
                        case -114:
                            v0(bArr2);
                            return;
                        case -113:
                            p0(bArr2);
                            return;
                        default:
                            if (b == -95) {
                                e();
                                return;
                            } else if (b == -78) {
                                c0(bArr2);
                                return;
                            } else if (b == 33) {
                                b0(bArr2);
                                return;
                            } else if (b == 116) {
                                i0(bArr2);
                                return;
                            } else {
                                switch (b) {
                                    case 36:
                                        l(bArr2);
                                        return;
                                    case 37:
                                        f0(bArr2);
                                        return;
                                    case 38:
                                        o(bArr2);
                                        return;
                                    case 39:
                                        Y(bArr2);
                                        return;
                                    case 40:
                                        E(bArr2);
                                        return;
                                    case 41:
                                        k(bArr2);
                                        return;
                                    case 42:
                                        v(bArr2);
                                        return;
                                    case 43:
                                        h0(bArr2);
                                        return;
                                    case 44:
                                        A(bArr2);
                                        return;
                                    case 45:
                                        N(bArr2);
                                        return;
                                    case 46:
                                        x0(bArr2);
                                        return;
                                    case 47:
                                        q(bArr2);
                                        return;
                                    default:
                                        switch (b) {
                                            case 98:
                                                a(bArr2);
                                                return;
                                            case 99:
                                                if (4 < length) {
                                                    t(bArr2);
                                                    return;
                                                } else {
                                                    g0(bArr2);
                                                    return;
                                                }
                                            case 100:
                                                c();
                                                return;
                                            case 101:
                                                s0(bArr2);
                                                return;
                                            case 102:
                                                f();
                                                return;
                                            case 103:
                                                x(bArr2);
                                                return;
                                            case 104:
                                                p(bArr2);
                                                return;
                                            case 105:
                                                this.j.a(bArr2, this.n);
                                                return;
                                            default:
                                                switch (b) {
                                                    case 107:
                                                        l0(bArr2);
                                                        return;
                                                    case 108:
                                                        e0(bArr2);
                                                        return;
                                                    case 109:
                                                        y(bArr2);
                                                        return;
                                                    case 110:
                                                        break;
                                                    case 111:
                                                        n(bArr2);
                                                        return;
                                                    default:
                                                        switch (b) {
                                                            case -92:
                                                                j0(bArr2);
                                                                return;
                                                            case -91:
                                                                s(bArr2);
                                                                return;
                                                            case -90:
                                                                m0(bArr2);
                                                                return;
                                                            default:
                                                                switch (b) {
                                                                    case -86:
                                                                        C(bArr2);
                                                                        return;
                                                                    case -85:
                                                                        z(bArr2);
                                                                        return;
                                                                    case -84:
                                                                        U(bArr2);
                                                                        return;
                                                                    default:
                                                                        switch (b) {
                                                                            case IntegrityErrorCode.PLAY_SERVICES_VERSION_OUTDATED /* -15 */:
                                                                                r0(bArr2);
                                                                                return;
                                                                            case IntegrityErrorCode.PLAY_STORE_VERSION_OUTDATED /* -14 */:
                                                                                D(bArr2);
                                                                                return;
                                                                            case IntegrityErrorCode.NONCE_IS_NOT_BASE64 /* -13 */:
                                                                                break;
                                                                            case IntegrityErrorCode.GOOGLE_SERVER_UNAVAILABLE /* -12 */:
                                                                                X(bArr2);
                                                                                return;
                                                                            default:
                                                                                switch (b) {
                                                                                    case 50:
                                                                                        O(bArr2);
                                                                                        return;
                                                                                    case 51:
                                                                                        r(bArr2);
                                                                                        return;
                                                                                    case 52:
                                                                                        Z(bArr2);
                                                                                        return;
                                                                                    case 53:
                                                                                        i2 = this.e.i(bArr2);
                                                                                        break;
                                                                                    case 54:
                                                                                        List<Integer> d = this.e.d(bArr2);
                                                                                        if (d != null) {
                                                                                            J(this.e.a(d));
                                                                                            i2 = this.e.b(d);
                                                                                            break;
                                                                                        } else {
                                                                                            return;
                                                                                        }
                                                                                    case 55:
                                                                                        w(bArr2);
                                                                                        return;
                                                                                    default:
                                                                                        switch (b) {
                                                                                            case 57:
                                                                                                k0(bArr2);
                                                                                                return;
                                                                                            case 58:
                                                                                                P(bArr2);
                                                                                                return;
                                                                                            case 59:
                                                                                                W(bArr2);
                                                                                                return;
                                                                                            case 60:
                                                                                                a0(bArr2);
                                                                                                return;
                                                                                            case 61:
                                                                                                u0(bArr2);
                                                                                                return;
                                                                                            case 62:
                                                                                                t0(bArr2);
                                                                                                return;
                                                                                            case 63:
                                                                                                w0(bArr2);
                                                                                                return;
                                                                                            default:
                                                                                                com.crrepa.i0.c.c("default cmd: " + ((int) b));
                                                                                                return;
                                                                                        }
                                                                                }
                                                                                J(i2);
                                                                                return;
                                                                        }
                                                                }
                                                        }
                                                }
                                                u(bArr2);
                                                return;
                                        }
                                }
                            }
                    }
            }
            K(bArr2, i);
        }
    }

    public final void d(byte[] bArr) {
        CRPHandWashingPeriodInfo a2 = com.crrepa.j.f.a(bArr);
        CRPDeviceHandWashingPeriodCallback cRPDeviceHandWashingPeriodCallback = this.c0;
        if (cRPDeviceHandWashingPeriodCallback == null || a2 == null) {
            return;
        }
        cRPDeviceHandWashingPeriodCallback.onHandWashingPeriod(a2);
    }

    public final void d0(byte[] bArr) {
        if (com.crrepa.i0.e.e(bArr)) {
            return;
        }
        com.crrepa.n.a.a().a(com.crrepa.i0.e.a(bArr[0]));
    }

    public final void e() {
        CRPSosChangeListener cRPSosChangeListener = this.z;
        if (cRPSosChangeListener != null) {
            cRPSosChangeListener.onSos();
        }
    }

    public final void e(byte[] bArr) {
        CRPDeviceMaxHeartRateCallback cRPDeviceMaxHeartRateCallback = this.Z;
        if (cRPDeviceMaxHeartRateCallback == null || bArr.length < 3) {
            return;
        }
        cRPDeviceMaxHeartRateCallback.onHeartRate(bArr[2] & 255, bArr[1] == 1);
    }

    public final void e0(byte[] bArr) {
        com.crrepa.e0.a aVar = new com.crrepa.e0.a(bArr);
        if (CRPUiTransInitiator.getInstance().isStarted()) {
            CRPUiTransInitiator.getInstance().transFileIndex(aVar);
        }
        if (CRPTpTransInitiator.getInstance().isStarted()) {
            CRPTpTransInitiator.getInstance().transFileIndex(aVar);
        }
    }

    public final void f() {
        CRPCameraOperationListener cRPCameraOperationListener = this.q;
        if (cRPCameraOperationListener != null) {
            cRPCameraOperationListener.onTakePhoto();
        }
    }

    public final void f(byte[] bArr) {
        if (this.x == null || com.crrepa.i0.e.e(bArr)) {
            return;
        }
        this.x.onMeasureState(bArr[0] == 1);
    }

    public final void f0(byte[] bArr) {
        CRPFunctionInfo a2 = com.crrepa.j.e.a(bArr);
        CRPDeviceFunctionCallback cRPDeviceFunctionCallback = this.P;
        if (cRPDeviceFunctionCallback != null) {
            cRPDeviceFunctionCallback.onFunctionChenge(a2);
        }
    }

    public final void g(byte[] bArr) {
        if (this.x != null) {
            this.x.onMeasureTemp(this.h.c(bArr));
        }
    }

    public final void g0(byte[] bArr) {
        com.crrepa.e0.b.a().transFileIndex(new com.crrepa.e0.a(bArr));
    }

    public final void h(byte[] bArr) {
        int i = 0;
        if (1 == bArr.length && bArr[0] > 0) {
            i = 1;
        }
        com.crrepa.d.b.a().a(i);
        if (2 == bArr.length) {
            com.crrepa.d.b.a().b(bArr[1]);
        }
    }

    public final void h0(byte[] bArr) {
        int a2 = i.a(bArr);
        int[] b = i.b(bArr);
        CRPDeviceLanguageCallback cRPDeviceLanguageCallback = this.H;
        if (cRPDeviceLanguageCallback != null) {
            cRPDeviceLanguageCallback.onDeviceLanguage(a2, b);
        }
    }

    public final void i(byte[] bArr) {
        if (com.crrepa.i0.e.e(bArr)) {
            return;
        }
        byte b = bArr[0];
        int length = bArr.length - 1;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 1, bArr2, 0, length);
        String str = new String(bArr2);
        if (b == 0) {
            com.crrepa.d.e.a().a(str);
        } else if (b == 1) {
            com.crrepa.d.c.a().a(str);
        } else if (b != 2) {
        } else {
            h(bArr2);
        }
    }

    public final void i0(byte[] bArr) {
        com.crrepa.f0.a.a().transFileIndex(new com.crrepa.e0.a(bArr));
    }

    public final void j(byte[] bArr) {
        CRPDeviceDisplayTimeCallback cRPDeviceDisplayTimeCallback;
        if (com.crrepa.i0.e.e(bArr) || (cRPDeviceDisplayTimeCallback = this.b0) == null) {
            return;
        }
        cRPDeviceDisplayTimeCallback.onDisplayTime(bArr[0]);
    }

    public final void j0(byte[] bArr) {
        if (this.A == null || com.crrepa.i0.e.e(bArr)) {
            return;
        }
        this.A.onBatterSaving(bArr[0] == 1);
    }

    public final void k(byte[] bArr) {
        int b = y.b(bArr);
        CRPDeviceDisplayWatchFaceCallback cRPDeviceDisplayWatchFaceCallback = this.N;
        if (cRPDeviceDisplayWatchFaceCallback != null) {
            cRPDeviceDisplayWatchFaceCallback.onDisplayWatchFace(b);
        }
    }

    public final void k0(byte[] bArr) {
        CRPWatchFaceLayoutInfo a2 = x.a(bArr);
        CRPDeviceWatchFaceLayoutCallback cRPDeviceWatchFaceLayoutCallback = this.T;
        if (cRPDeviceWatchFaceLayoutCallback == null || a2 == null) {
            return;
        }
        cRPDeviceWatchFaceLayoutCallback.onWatchFaceLayoutChange(a2);
    }

    public final void l(byte[] bArr) {
        int a2 = com.crrepa.f.q.a(bArr);
        CRPDeviceDominantHandCallback cRPDeviceDominantHandCallback = this.F;
        if (cRPDeviceDominantHandCallback != null) {
            cRPDeviceDominantHandCallback.onDominantHand(a2);
        }
    }

    public final void l0(byte[] bArr) {
        if (this.o == null || com.crrepa.i0.e.e(bArr)) {
            return;
        }
        this.o.onBloodOxygen(com.crrepa.i0.e.a(bArr[0]));
    }

    public final void m(byte[] bArr) {
        com.crrepa.k.a aVar;
        if (this.t == null || (aVar = this.g) == null) {
            return;
        }
        this.t.onECGChange(aVar.a(bArr));
    }

    public final void m0(byte[] bArr) {
        if (this.i0 == null || com.crrepa.i0.e.e(bArr) || bArr[0] != 1) {
            return;
        }
        this.i0.onWatchFaceList(y.c(bArr));
    }

    public final void n(byte[] bArr) {
        if (this.t == null || this.g == null || com.crrepa.i0.e.e(bArr)) {
            return;
        }
        int c = this.g.c(bArr);
        if (com.crrepa.e.a.a()) {
            if (c == 0) {
                this.t.onCancel();
                return;
            } else if (c == 1) {
                this.t.onTransCpmplete(this.g.b(bArr));
                return;
            } else if (c == 2) {
                this.t.onFail();
                return;
            } else if (c != 3) {
                return;
            }
        }
        this.t.onMeasureComplete();
    }

    public final void n0(byte[] bArr) {
        if (this.S == null || com.crrepa.i0.e.e(bArr)) {
            return;
        }
        this.S.onBreathingLight(bArr[0] == 1);
    }

    public final void o(byte[] bArr) {
        int a2 = com.crrepa.f.w.a(bArr);
        CRPDeviceGoalStepCallback cRPDeviceGoalStepCallback = this.G;
        if (cRPDeviceGoalStepCallback != null) {
            cRPDeviceGoalStepCallback.onGoalStep(a2);
        }
    }

    public final void o0(byte[] bArr) {
        CRPWeatherChangeListener cRPWeatherChangeListener;
        if (com.crrepa.i0.e.e(bArr) || (cRPWeatherChangeListener = this.r) == null) {
            return;
        }
        cRPWeatherChangeListener.onTempUnitChange(bArr[0]);
    }

    public final void p(byte[] bArr) {
        if (2 <= bArr.length) {
            M(this.e.g(bArr));
        }
        if (bArr.length == 1) {
            a(bArr[0]);
        }
    }

    public final void p0(byte[] bArr) {
        CRPDeviceBrightnessCallback cRPDeviceBrightnessCallback;
        if (bArr == null || bArr.length < 2 || (cRPDeviceBrightnessCallback = this.d0) == null) {
            return;
        }
        cRPDeviceBrightnessCallback.onBrightness(bArr[0], bArr[1]);
    }

    public final void q(byte[] bArr) {
        if (this.R != null) {
            this.R.onTimingMeasure(this.e.h(bArr));
        }
    }

    public final boolean q0(byte[] bArr) {
        if (this.f7752a && bArr[0] == -2 && bArr[1] == -22) {
            int H = H(bArr[2], bArr[3]);
            this.b = H;
            this.d = new byte[H];
            this.f7752a = false;
            this.c = 0;
        }
        int i = this.b;
        int i2 = this.c;
        if (bArr.length > i - i2) {
            this.f7752a = true;
        } else if (i2 < i) {
            System.arraycopy(bArr, 0, this.d, i2, bArr.length);
            this.c += bArr.length;
        }
        if (this.c >= this.b) {
            this.f7752a = true;
        }
        com.crrepa.i0.c.c("packetEnded: " + this.f7752a);
        return this.f7752a;
    }

    public final void r(byte[] bArr) {
        CRPStepChangeListener cRPStepChangeListener;
        com.crrepa.o.a.a().c();
        if (com.crrepa.i0.e.e(bArr)) {
            return;
        }
        byte b = bArr[0];
        int length = bArr.length - 1;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 1, bArr2, 0, length);
        if (b <= 2 && (cRPStepChangeListener = this.k) != null) {
            cRPStepChangeListener.onPastStepChange(b, t.a(bArr2));
            return;
        }
        CRPSleepChangeListener cRPSleepChangeListener = this.l;
        if (cRPSleepChangeListener != null) {
            cRPSleepChangeListener.onPastSleepChange(b, s.a(bArr2, true));
        }
    }

    public final void r0(byte[] bArr) {
        if (com.crrepa.i0.e.e(bArr) || this.e0 == null) {
            return;
        }
        this.e0.onAddress(com.crrepa.j.a.a(bArr));
    }

    public final void s(byte[] bArr) {
        CRPHrvInfo a2;
        if (this.B == null || com.crrepa.i0.e.e(bArr)) {
            return;
        }
        byte b = bArr[0];
        if (b == 1) {
            this.B.onMeasureInterval(bArr[1]);
        } else if (b != 2) {
            if (b == 3 && (a2 = h.a(bArr)) != null) {
                this.B.onHrvChange(a2);
            }
        } else {
            byte b2 = bArr[1];
            int b3 = com.crrepa.i0.e.b(bArr[3], bArr[2]);
            com.crrepa.i0.c.a("hrv count: " + b3);
            if (4 >= bArr.length) {
                this.B.onMeasureCount(b2, b3);
                return;
            }
            int length = bArr.length - 4;
            byte[] bArr2 = new byte[length];
            System.arraycopy(bArr, 4, bArr2, 0, length);
            CRPHrvInfo a3 = h.a(bArr2);
            if (a3 != null) {
                this.B.onMeasureResult(b2, b3, a3);
            }
        }
    }

    public final void s0(byte[] bArr) {
        if (this.C != null) {
            String str = new String(bArr, StandardCharsets.UTF_8);
            com.crrepa.i0.c.a("number: " + str);
            this.C.onCallNumber(str);
        }
    }

    public final void t(byte[] bArr) {
        String b = com.crrepa.j.a.b(bArr);
        if (this.W == null || TextUtils.isEmpty(b)) {
            return;
        }
        this.W.onAddress(b);
    }

    public final void t0(byte[] bArr) {
        List<Integer> b;
        if (this.o == null || (b = this.i.b(bArr)) == null) {
            return;
        }
        this.o.onContinueBloodOxygen(this.i.a(b));
        this.o.onContinueBloodOxygen(this.i.b(b));
    }

    public final void u(byte[] bArr) {
        com.crrepa.e0.a aVar = new com.crrepa.e0.a(bArr);
        com.crrepa.a0.a b = com.crrepa.g0.d.b();
        if (b != null) {
            b.a(aVar);
        }
    }

    public final void u0(byte[] bArr) {
        List<CRPBloodPressureInfo.BpBean> b;
        if (this.n == null || (b = this.j.b(bArr)) == null) {
            return;
        }
        this.n.onContinueBloodPressure(this.j.a(b));
        this.n.onContinueBloodPressure(this.j.b(b));
    }

    public final void v(byte[] bArr) {
        int a2 = d0.a(bArr);
        CRPDeviceMetricSystemCallback cRPDeviceMetricSystemCallback = this.I;
        if (cRPDeviceMetricSystemCallback != null) {
            cRPDeviceMetricSystemCallback.onMetricSystem(a2);
        }
    }

    public final void v0(byte[] bArr) {
        CRPTempChangeListener cRPTempChangeListener;
        if (com.crrepa.i0.e.e(bArr)) {
            return;
        }
        if (bArr.length == 1) {
            CRPBloodOxygenChangeListener cRPBloodOxygenChangeListener = this.o;
            if (cRPBloodOxygenChangeListener != null) {
                cRPBloodOxygenChangeListener.onTimingMeasure(bArr[0]);
            }
        } else if (bArr.length == 2) {
            boolean z = bArr[1] == 1;
            byte b = bArr[0];
            if (b == 1) {
                CRPBloodPressureChangeListener cRPBloodPressureChangeListener = this.n;
                if (cRPBloodPressureChangeListener != null) {
                    cRPBloodPressureChangeListener.onContinueState(z);
                }
            } else if (b != 2) {
                if (b == 3 && (cRPTempChangeListener = this.x) != null) {
                    cRPTempChangeListener.onContinueState(z);
                }
            } else {
                CRPBloodOxygenChangeListener cRPBloodOxygenChangeListener2 = this.o;
                if (cRPBloodOxygenChangeListener2 != null) {
                    cRPBloodOxygenChangeListener2.onContinueState(z);
                }
            }
        }
    }

    public final void w(byte[] bArr) {
        List<CRPMovementHeartRateInfo> a2 = j.a(bArr);
        CRPHeartRateChangeListener cRPHeartRateChangeListener = this.m;
        if (cRPHeartRateChangeListener != null) {
            cRPHeartRateChangeListener.onMovementMeasureResult(a2);
        }
    }

    public final void w0(byte[] bArr) {
        List<Float> b;
        if (this.x == null || (b = this.h.b(bArr)) == null) {
            return;
        }
        this.x.onContinueTemp(this.h.a(b));
        this.x.onContinueTemp(this.h.b(b));
    }

    public final void x(byte[] bArr) {
        CRPPhoneOperationListener cRPPhoneOperationListener;
        if (com.crrepa.i0.e.e(bArr) || (cRPPhoneOperationListener = this.p) == null) {
            return;
        }
        cRPPhoneOperationListener.onOperationChange(bArr[0]);
    }

    public final void x0(byte[] bArr) {
        byte a2 = com.crrepa.f.l.a(bArr);
        CRPDeviceVersionCallback cRPDeviceVersionCallback = this.O;
        if (cRPDeviceVersionCallback != null) {
            cRPDeviceVersionCallback.onDeviceVersion(a2);
        }
    }

    public final void y(byte[] bArr) {
        int g = this.e.g(bArr);
        CRPHeartRateChangeListener cRPHeartRateChangeListener = this.m;
        if (cRPHeartRateChangeListener != null) {
            cRPHeartRateChangeListener.onOnceMeasureComplete(g);
        }
    }

    public final void z(byte[] bArr) {
        CRPHeartRateChangeListener cRPHeartRateChangeListener;
        List<CRPHistoryBloodOxygenInfo> c;
        CRPBloodOxygenChangeListener cRPBloodOxygenChangeListener;
        if (com.crrepa.i0.e.e(bArr)) {
            return;
        }
        byte b = bArr[0];
        if (b == 0) {
            List<CRPHistoryHeartRateInfo> f = this.e.f(bArr);
            if (f == null || (cRPHeartRateChangeListener = this.m) == null) {
                return;
            }
            cRPHeartRateChangeListener.onHistoryHeartRate(f);
        } else if (b != 1) {
            if (b != 2 || (c = this.i.c(bArr)) == null || (cRPBloodOxygenChangeListener = this.o) == null) {
                return;
            }
            cRPBloodOxygenChangeListener.onHistoryBloodOxygen(c);
        } else {
            List<CRPHistoryBloodPressureInfo> c2 = this.j.c(bArr);
            if (c2 == null || this.j == null) {
                return;
            }
            this.n.onHistoryBloodPressure(c2);
        }
    }
}
