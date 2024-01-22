package com.jieli.jl_rcsp.tool;

import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.os.Looper;
import com.jieli.jl_rcsp.interfaces.rcsp.OnRcspEventListener;
import com.jieli.jl_rcsp.model.device.AlarmBean;
import com.jieli.jl_rcsp.model.device.AlarmListInfo;
import com.jieli.jl_rcsp.model.device.BatteryInfo;
import com.jieli.jl_rcsp.model.device.ChannelInfo;
import com.jieli.jl_rcsp.model.device.DefaultAlarmBell;
import com.jieli.jl_rcsp.model.device.DevStorageInfo;
import com.jieli.jl_rcsp.model.device.DynamicLimiterParam;
import com.jieli.jl_rcsp.model.device.EqInfo;
import com.jieli.jl_rcsp.model.device.EqPresetInfo;
import com.jieli.jl_rcsp.model.device.FmStatusInfo;
import com.jieli.jl_rcsp.model.device.ID3MusicInfo;
import com.jieli.jl_rcsp.model.device.LightControlInfo;
import com.jieli.jl_rcsp.model.device.MusicNameInfo;
import com.jieli.jl_rcsp.model.device.MusicStatusInfo;
import com.jieli.jl_rcsp.model.device.PlayModeInfo;
import com.jieli.jl_rcsp.model.device.ReverberationParam;
import com.jieli.jl_rcsp.model.device.VoiceMode;
import com.jieli.jl_rcsp.model.device.VolumeInfo;
import com.jieli.jl_rcsp.model.device.health.HealthData;
import com.jieli.jl_rcsp.model.device.health.HealthSettingInfo;
import com.jieli.jl_rcsp.model.device.health.SportsInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes11.dex */
public class RcspEventListenerManager extends OnRcspEventListener {

    /* renamed from: a  reason: collision with root package name */
    private final List<OnRcspEventListener> f12455a = new ArrayList();
    private final Handler b = new Handler(Looper.getMainLooper());

    /* loaded from: classes11.dex */
    public class OnRcspEventRunnable implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        private final RcspEventListenerImpl f12492a;

        public OnRcspEventRunnable(RcspEventListenerImpl rcspEventListenerImpl) {
            this.f12492a = rcspEventListenerImpl;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (RcspEventListenerManager.this.f12455a.isEmpty() || this.f12492a == null) {
                return;
            }
            Iterator it = new ArrayList(RcspEventListenerManager.this.f12455a).iterator();
            while (it.hasNext()) {
                this.f12492a.onNotify((OnRcspEventListener) it.next());
            }
        }
    }

    /* loaded from: classes11.dex */
    public static abstract class RcspEventListenerImpl {
        private RcspEventListenerImpl() {
        }

        public abstract void onNotify(OnRcspEventListener onRcspEventListener);
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspEventListener
    public void onAlarmDefaultBellListChange(final BluetoothDevice bluetoothDevice, final List<DefaultAlarmBell> list) {
        a(new RcspEventListenerImpl() { // from class: com.jieli.jl_rcsp.tool.RcspEventListenerManager.14
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspEventListenerManager.RcspEventListenerImpl
            public void onNotify(OnRcspEventListener onRcspEventListener) {
                onRcspEventListener.onAlarmDefaultBellListChange(bluetoothDevice, list);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspEventListener
    public void onAlarmListChange(final BluetoothDevice bluetoothDevice, final AlarmListInfo alarmListInfo) {
        a(new RcspEventListenerImpl() { // from class: com.jieli.jl_rcsp.tool.RcspEventListenerManager.13
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspEventListenerManager.RcspEventListenerImpl
            public void onNotify(OnRcspEventListener onRcspEventListener) {
                onRcspEventListener.onAlarmListChange(bluetoothDevice, alarmListInfo);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspEventListener
    public void onAlarmNotify(final BluetoothDevice bluetoothDevice, final AlarmBean alarmBean) {
        a(new RcspEventListenerImpl() { // from class: com.jieli.jl_rcsp.tool.RcspEventListenerManager.15
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspEventListenerManager.RcspEventListenerImpl
            public void onNotify(OnRcspEventListener onRcspEventListener) {
                onRcspEventListener.onAlarmNotify(bluetoothDevice, alarmBean);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspEventListener
    public void onAlarmStop(final BluetoothDevice bluetoothDevice, final AlarmBean alarmBean) {
        a(new RcspEventListenerImpl() { // from class: com.jieli.jl_rcsp.tool.RcspEventListenerManager.16
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspEventListenerManager.RcspEventListenerImpl
            public void onNotify(OnRcspEventListener onRcspEventListener) {
                onRcspEventListener.onAlarmStop(bluetoothDevice, alarmBean);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspEventListener
    public void onAuxStatusChange(final BluetoothDevice bluetoothDevice, final boolean z) {
        a(new RcspEventListenerImpl() { // from class: com.jieli.jl_rcsp.tool.RcspEventListenerManager.10
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspEventListenerManager.RcspEventListenerImpl
            public void onNotify(OnRcspEventListener onRcspEventListener) {
                onRcspEventListener.onAuxStatusChange(bluetoothDevice, z);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspEventListener
    public void onBatteryChange(final BluetoothDevice bluetoothDevice, final BatteryInfo batteryInfo) {
        a(new RcspEventListenerImpl() { // from class: com.jieli.jl_rcsp.tool.RcspEventListenerManager.9
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspEventListenerManager.RcspEventListenerImpl
            public void onNotify(OnRcspEventListener onRcspEventListener) {
                onRcspEventListener.onBatteryChange(bluetoothDevice, batteryInfo);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspEventListener
    public void onCurrentVoiceMode(final BluetoothDevice bluetoothDevice, final VoiceMode voiceMode) {
        a(new RcspEventListenerImpl() { // from class: com.jieli.jl_rcsp.tool.RcspEventListenerManager.30
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspEventListenerManager.RcspEventListenerImpl
            public void onNotify(OnRcspEventListener onRcspEventListener) {
                onRcspEventListener.onCurrentVoiceMode(bluetoothDevice, voiceMode);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspEventListener
    public void onDevStorageInfoChange(final BluetoothDevice bluetoothDevice, final DevStorageInfo devStorageInfo) {
        a(new RcspEventListenerImpl() { // from class: com.jieli.jl_rcsp.tool.RcspEventListenerManager.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspEventListenerManager.RcspEventListenerImpl
            public void onNotify(OnRcspEventListener onRcspEventListener) {
                onRcspEventListener.onDevStorageInfoChange(bluetoothDevice, devStorageInfo);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspEventListener
    public void onDeviceModeChange(final BluetoothDevice bluetoothDevice, final int i) {
        a(new RcspEventListenerImpl() { // from class: com.jieli.jl_rcsp.tool.RcspEventListenerManager.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspEventListenerManager.RcspEventListenerImpl
            public void onNotify(OnRcspEventListener onRcspEventListener) {
                onRcspEventListener.onDeviceModeChange(bluetoothDevice, i);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspEventListener
    public void onDynamicLimiter(final BluetoothDevice bluetoothDevice, final DynamicLimiterParam dynamicLimiterParam) {
        a(new RcspEventListenerImpl() { // from class: com.jieli.jl_rcsp.tool.RcspEventListenerManager.26
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspEventListenerManager.RcspEventListenerImpl
            public void onNotify(OnRcspEventListener onRcspEventListener) {
                onRcspEventListener.onDynamicLimiter(bluetoothDevice, dynamicLimiterParam);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspEventListener
    public void onEqChange(final BluetoothDevice bluetoothDevice, final EqInfo eqInfo) {
        a(new RcspEventListenerImpl() { // from class: com.jieli.jl_rcsp.tool.RcspEventListenerManager.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspEventListenerManager.RcspEventListenerImpl
            public void onNotify(OnRcspEventListener onRcspEventListener) {
                onRcspEventListener.onEqChange(bluetoothDevice, eqInfo);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspEventListener
    public void onEqPresetChange(final BluetoothDevice bluetoothDevice, final EqPresetInfo eqPresetInfo) {
        a(new RcspEventListenerImpl() { // from class: com.jieli.jl_rcsp.tool.RcspEventListenerManager.22
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspEventListenerManager.RcspEventListenerImpl
            public void onNotify(OnRcspEventListener onRcspEventListener) {
                onRcspEventListener.onEqPresetChange(bluetoothDevice, eqPresetInfo);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspEventListener
    public void onExpandFunction(final BluetoothDevice bluetoothDevice, final int i, final byte[] bArr) {
        a(new RcspEventListenerImpl() { // from class: com.jieli.jl_rcsp.tool.RcspEventListenerManager.24
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspEventListenerManager.RcspEventListenerImpl
            public void onNotify(OnRcspEventListener onRcspEventListener) {
                onRcspEventListener.onExpandFunction(bluetoothDevice, i, bArr);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspEventListener
    public void onFileFormatChange(final BluetoothDevice bluetoothDevice, final String str) {
        a(new RcspEventListenerImpl() { // from class: com.jieli.jl_rcsp.tool.RcspEventListenerManager.5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspEventListenerManager.RcspEventListenerImpl
            public void onNotify(OnRcspEventListener onRcspEventListener) {
                onRcspEventListener.onFileFormatChange(bluetoothDevice, str);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspEventListener
    public void onFmChannelsChange(final BluetoothDevice bluetoothDevice, final List<ChannelInfo> list) {
        a(new RcspEventListenerImpl() { // from class: com.jieli.jl_rcsp.tool.RcspEventListenerManager.11
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspEventListenerManager.RcspEventListenerImpl
            public void onNotify(OnRcspEventListener onRcspEventListener) {
                onRcspEventListener.onFmChannelsChange(bluetoothDevice, list);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspEventListener
    public void onFmStatusChange(final BluetoothDevice bluetoothDevice, final FmStatusInfo fmStatusInfo) {
        a(new RcspEventListenerImpl() { // from class: com.jieli.jl_rcsp.tool.RcspEventListenerManager.12
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspEventListenerManager.RcspEventListenerImpl
            public void onNotify(OnRcspEventListener onRcspEventListener) {
                onRcspEventListener.onFmStatusChange(bluetoothDevice, fmStatusInfo);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspEventListener
    public void onFrequencyTx(final BluetoothDevice bluetoothDevice, final float f) {
        a(new RcspEventListenerImpl() { // from class: com.jieli.jl_rcsp.tool.RcspEventListenerManager.17
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspEventListenerManager.RcspEventListenerImpl
            public void onNotify(OnRcspEventListener onRcspEventListener) {
                onRcspEventListener.onFrequencyTx(bluetoothDevice, f);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspEventListener
    public void onHealthDataChange(final BluetoothDevice bluetoothDevice, final HealthData healthData) {
        a(new RcspEventListenerImpl() { // from class: com.jieli.jl_rcsp.tool.RcspEventListenerManager.32
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspEventListenerManager.RcspEventListenerImpl
            public void onNotify(OnRcspEventListener onRcspEventListener) {
                onRcspEventListener.onHealthDataChange(bluetoothDevice, healthData);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspEventListener
    public void onHealthSettingChange(final BluetoothDevice bluetoothDevice, final HealthSettingInfo healthSettingInfo) {
        a(new RcspEventListenerImpl() { // from class: com.jieli.jl_rcsp.tool.RcspEventListenerManager.33
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspEventListenerManager.RcspEventListenerImpl
            public void onNotify(OnRcspEventListener onRcspEventListener) {
                onRcspEventListener.onHealthSettingChange(bluetoothDevice, healthSettingInfo);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspEventListener
    public void onHighAndBassChange(final BluetoothDevice bluetoothDevice, final int i, final int i2) {
        a(new RcspEventListenerImpl() { // from class: com.jieli.jl_rcsp.tool.RcspEventListenerManager.21
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspEventListenerManager.RcspEventListenerImpl
            public void onNotify(OnRcspEventListener onRcspEventListener) {
                onRcspEventListener.onHighAndBassChange(bluetoothDevice, i, i2);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspEventListener
    public void onID3MusicInfo(final BluetoothDevice bluetoothDevice, final ID3MusicInfo iD3MusicInfo) {
        a(new RcspEventListenerImpl() { // from class: com.jieli.jl_rcsp.tool.RcspEventListenerManager.20
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspEventListenerManager.RcspEventListenerImpl
            public void onNotify(OnRcspEventListener onRcspEventListener) {
                onRcspEventListener.onID3MusicInfo(bluetoothDevice, iD3MusicInfo);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspEventListener
    public void onLightControlInfo(final BluetoothDevice bluetoothDevice, final LightControlInfo lightControlInfo) {
        a(new RcspEventListenerImpl() { // from class: com.jieli.jl_rcsp.tool.RcspEventListenerManager.27
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspEventListenerManager.RcspEventListenerImpl
            public void onNotify(OnRcspEventListener onRcspEventListener) {
                onRcspEventListener.onLightControlInfo(bluetoothDevice, lightControlInfo);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspEventListener
    public void onMusicNameChange(final BluetoothDevice bluetoothDevice, final MusicNameInfo musicNameInfo) {
        a(new RcspEventListenerImpl() { // from class: com.jieli.jl_rcsp.tool.RcspEventListenerManager.6
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspEventListenerManager.RcspEventListenerImpl
            public void onNotify(OnRcspEventListener onRcspEventListener) {
                onRcspEventListener.onMusicNameChange(bluetoothDevice, musicNameInfo);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspEventListener
    public void onMusicStatusChange(final BluetoothDevice bluetoothDevice, final MusicStatusInfo musicStatusInfo) {
        a(new RcspEventListenerImpl() { // from class: com.jieli.jl_rcsp.tool.RcspEventListenerManager.7
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspEventListenerManager.RcspEventListenerImpl
            public void onNotify(OnRcspEventListener onRcspEventListener) {
                onRcspEventListener.onMusicStatusChange(bluetoothDevice, musicStatusInfo);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspEventListener
    public void onPeripheralsConnectStatusChange(final BluetoothDevice bluetoothDevice, final boolean z, final String str) {
        a(new RcspEventListenerImpl() { // from class: com.jieli.jl_rcsp.tool.RcspEventListenerManager.19
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspEventListenerManager.RcspEventListenerImpl
            public void onNotify(OnRcspEventListener onRcspEventListener) {
                onRcspEventListener.onPeripheralsConnectStatusChange(bluetoothDevice, z, str);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspEventListener
    public void onPeripheralsModeChange(final BluetoothDevice bluetoothDevice, final int i) {
        a(new RcspEventListenerImpl() { // from class: com.jieli.jl_rcsp.tool.RcspEventListenerManager.18
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspEventListenerManager.RcspEventListenerImpl
            public void onNotify(OnRcspEventListener onRcspEventListener) {
                onRcspEventListener.onPeripheralsModeChange(bluetoothDevice, i);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspEventListener
    public void onPhoneCallStatusChange(final BluetoothDevice bluetoothDevice, final int i) {
        a(new RcspEventListenerImpl() { // from class: com.jieli.jl_rcsp.tool.RcspEventListenerManager.23
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspEventListenerManager.RcspEventListenerImpl
            public void onNotify(OnRcspEventListener onRcspEventListener) {
                onRcspEventListener.onPhoneCallStatusChange(bluetoothDevice, i);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspEventListener
    public void onPlayModeChange(final BluetoothDevice bluetoothDevice, final PlayModeInfo playModeInfo) {
        a(new RcspEventListenerImpl() { // from class: com.jieli.jl_rcsp.tool.RcspEventListenerManager.8
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspEventListenerManager.RcspEventListenerImpl
            public void onNotify(OnRcspEventListener onRcspEventListener) {
                onRcspEventListener.onPlayModeChange(bluetoothDevice, playModeInfo);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspEventListener
    public void onReverberation(final BluetoothDevice bluetoothDevice, final ReverberationParam reverberationParam) {
        a(new RcspEventListenerImpl() { // from class: com.jieli.jl_rcsp.tool.RcspEventListenerManager.25
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspEventListenerManager.RcspEventListenerImpl
            public void onNotify(OnRcspEventListener onRcspEventListener) {
                onRcspEventListener.onReverberation(bluetoothDevice, reverberationParam);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspEventListener
    public void onSensorLogDataChange(final BluetoothDevice bluetoothDevice, final int i, final byte[] bArr) {
        a(new RcspEventListenerImpl() { // from class: com.jieli.jl_rcsp.tool.RcspEventListenerManager.34
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspEventListenerManager.RcspEventListenerImpl
            public void onNotify(OnRcspEventListener onRcspEventListener) {
                onRcspEventListener.onSensorLogDataChange(bluetoothDevice, i, bArr);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspEventListener
    public void onSoundCardEqChange(final BluetoothDevice bluetoothDevice, final EqInfo eqInfo) {
        a(new RcspEventListenerImpl() { // from class: com.jieli.jl_rcsp.tool.RcspEventListenerManager.28
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspEventListenerManager.RcspEventListenerImpl
            public void onNotify(OnRcspEventListener onRcspEventListener) {
                onRcspEventListener.onSoundCardEqChange(bluetoothDevice, eqInfo);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspEventListener
    public void onSoundCardStatusChange(final BluetoothDevice bluetoothDevice, final long j, final byte[] bArr) {
        a(new RcspEventListenerImpl() { // from class: com.jieli.jl_rcsp.tool.RcspEventListenerManager.29
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspEventListenerManager.RcspEventListenerImpl
            public void onNotify(OnRcspEventListener onRcspEventListener) {
                onRcspEventListener.onSoundCardStatusChange(bluetoothDevice, j, bArr);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspEventListener
    public void onSportInfoChange(final BluetoothDevice bluetoothDevice, final SportsInfo sportsInfo) {
        a(new RcspEventListenerImpl() { // from class: com.jieli.jl_rcsp.tool.RcspEventListenerManager.36
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspEventListenerManager.RcspEventListenerImpl
            public void onNotify(OnRcspEventListener onRcspEventListener) {
                onRcspEventListener.onSportInfoChange(bluetoothDevice, sportsInfo);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspEventListener
    public void onSportsState(final BluetoothDevice bluetoothDevice, final int i) {
        a(new RcspEventListenerImpl() { // from class: com.jieli.jl_rcsp.tool.RcspEventListenerManager.35
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspEventListenerManager.RcspEventListenerImpl
            public void onNotify(OnRcspEventListener onRcspEventListener) {
                onRcspEventListener.onSportsState(bluetoothDevice, i);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspEventListener
    public void onVoiceModeList(final BluetoothDevice bluetoothDevice, final List<VoiceMode> list) {
        a(new RcspEventListenerImpl() { // from class: com.jieli.jl_rcsp.tool.RcspEventListenerManager.31
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspEventListenerManager.RcspEventListenerImpl
            public void onNotify(OnRcspEventListener onRcspEventListener) {
                onRcspEventListener.onVoiceModeList(bluetoothDevice, list);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspEventListener
    public void onVolumeChange(final BluetoothDevice bluetoothDevice, final VolumeInfo volumeInfo) {
        a(new RcspEventListenerImpl() { // from class: com.jieli.jl_rcsp.tool.RcspEventListenerManager.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspEventListenerManager.RcspEventListenerImpl
            public void onNotify(OnRcspEventListener onRcspEventListener) {
                onRcspEventListener.onVolumeChange(bluetoothDevice, volumeInfo);
            }
        });
    }

    public void registerRcspEventListener(OnRcspEventListener onRcspEventListener) {
        if (onRcspEventListener == null || this.f12455a.contains(onRcspEventListener)) {
            return;
        }
        this.f12455a.add(onRcspEventListener);
    }

    public void release() {
        this.f12455a.clear();
        this.b.removeCallbacksAndMessages(null);
    }

    public void unregisterRcspEventListener(OnRcspEventListener onRcspEventListener) {
        if (onRcspEventListener != null) {
            this.f12455a.remove(onRcspEventListener);
        }
    }

    private void a(RcspEventListenerImpl rcspEventListenerImpl) {
        if (rcspEventListenerImpl == null) {
            return;
        }
        OnRcspEventRunnable onRcspEventRunnable = new OnRcspEventRunnable(rcspEventListenerImpl);
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            this.b.post(onRcspEventRunnable);
        } else {
            onRcspEventRunnable.run();
        }
    }
}
