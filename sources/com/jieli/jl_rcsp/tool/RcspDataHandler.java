package com.jieli.jl_rcsp.tool;

import android.bluetooth.BluetoothDevice;
import android.graphics.Color;
import com.jieli.jl_rcsp.impl.RcspOpImpl;
import com.jieli.jl_rcsp.model.device.AttrBean;
import com.jieli.jl_rcsp.model.device.BatteryInfo;
import com.jieli.jl_rcsp.model.device.ChannelInfo;
import com.jieli.jl_rcsp.model.device.DevStorageInfo;
import com.jieli.jl_rcsp.model.device.DeviceInfo;
import com.jieli.jl_rcsp.model.device.DynamicLimiterParam;
import com.jieli.jl_rcsp.model.device.EqInfo;
import com.jieli.jl_rcsp.model.device.EqPresetInfo;
import com.jieli.jl_rcsp.model.device.ExpandFunction;
import com.jieli.jl_rcsp.model.device.FmStatusInfo;
import com.jieli.jl_rcsp.model.device.ID3MusicInfo;
import com.jieli.jl_rcsp.model.device.LightControlInfo;
import com.jieli.jl_rcsp.model.device.MusicNameInfo;
import com.jieli.jl_rcsp.model.device.MusicStatusInfo;
import com.jieli.jl_rcsp.model.device.PlayModeInfo;
import com.jieli.jl_rcsp.model.device.ReverberationParam;
import com.jieli.jl_rcsp.model.device.VoiceMode;
import com.jieli.jl_rcsp.model.device.VolumeInfo;
import com.jieli.jl_rcsp.model.device.health.AutomaticPressureDetection;
import com.jieli.jl_rcsp.model.device.health.BloodOxygenMeasurementAlert;
import com.jieli.jl_rcsp.model.device.health.DisconnectReminder;
import com.jieli.jl_rcsp.model.device.health.EmergencyContact;
import com.jieli.jl_rcsp.model.device.health.ExerciseHeartRateReminder;
import com.jieli.jl_rcsp.model.device.health.FallDetection;
import com.jieli.jl_rcsp.model.device.health.HealthSettingInfo;
import com.jieli.jl_rcsp.model.device.health.HeartRateMeasure;
import com.jieli.jl_rcsp.model.device.health.LiftWristDetection;
import com.jieli.jl_rcsp.model.device.health.SedentaryReminder;
import com.jieli.jl_rcsp.model.device.health.SensorInfo;
import com.jieli.jl_rcsp.model.device.health.SleepDetection;
import com.jieli.jl_rcsp.model.device.health.UserInfo;
import com.jieli.jl_rcsp.util.CHexConver;
import com.jieli.jl_rcsp.util.JL_Log;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes11.dex */
public class RcspDataHandler {
    private static final String c = "rcsp_handler";
    private static final int d = 64;

    /* renamed from: a  reason: collision with root package name */
    private final RcspOpImpl f12454a;
    private final RcspEventListenerManager b;

    public RcspDataHandler(RcspOpImpl rcspOpImpl, RcspEventListenerManager rcspEventListenerManager) {
        this.f12454a = rcspOpImpl;
        this.b = rcspEventListenerManager;
    }

    private void a(BluetoothDevice bluetoothDevice, List<AttrBean> list) {
        DeviceInfo deviceInfo;
        if (list == null || (deviceInfo = this.f12454a.getDeviceInfo(bluetoothDevice)) == null) {
            return;
        }
        for (AttrBean attrBean : list) {
            byte[] attrData = attrBean.getAttrData();
            if (attrData != null && attrData.length != 0 && attrBean.getType() == 0) {
                boolean z = CHexConver.byteToInt(attrData[0]) == 1;
                JL_Log.i(c, "onAuxStatusChange >> " + z);
                deviceInfo.setAuxPlay(z);
                this.b.onAuxStatusChange(bluetoothDevice, z);
            }
        }
    }

    private void b(BluetoothDevice bluetoothDevice, List<AttrBean> list) {
        DeviceInfo deviceInfo;
        String str;
        String str2;
        String str3;
        String str4;
        if (list == null || (deviceInfo = this.f12454a.getDeviceInfo(bluetoothDevice)) == null) {
            return;
        }
        ID3MusicInfo iD3MusicInfo = deviceInfo.getiD3MusicInfo();
        if (iD3MusicInfo == null) {
            iD3MusicInfo = new ID3MusicInfo();
        }
        ID3MusicInfo iD3MusicInfo2 = iD3MusicInfo;
        boolean z = false;
        for (AttrBean attrBean : list) {
            byte[] attrData = attrBean.getAttrData();
            if (attrData != null && attrData.length != 0) {
                switch (attrBean.getType()) {
                    case 0:
                        try {
                            str4 = new String(attrData, 0, attrData.length, StandardCharsets.UTF_8);
                        } catch (Exception e) {
                            e.printStackTrace();
                            str4 = null;
                        }
                        JL_Log.i(c, "-parseBtData- id3 title: [" + CHexConver.byte2HexStr(attrData, attrData.length) + "], " + str4);
                        if (str4 != null && attrData.length == 64) {
                            str4 = str4.substring(0, str4.length() - 1) + "...";
                        }
                        if (!CHexConver.byte2HexStr(attrData, attrData.length).equals("00")) {
                            iD3MusicInfo2.setTitle(str4);
                        } else {
                            iD3MusicInfo2.setTitle(null);
                        }
                        z = true;
                        break;
                    case 1:
                        try {
                            str3 = new String(attrData, 0, attrData.length, StandardCharsets.UTF_8);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            str3 = null;
                        }
                        JL_Log.i(c, "-parseBtData- id3 artist: [" + CHexConver.byte2HexStr(attrData, attrData.length) + "], " + str3);
                        if (str3 != null && attrData.length == 64) {
                            str3 = str3.substring(0, str3.length() - 1) + "...";
                        }
                        if (!CHexConver.byte2HexStr(attrData, attrData.length).equals("00")) {
                            iD3MusicInfo2.setArtist(str3);
                        } else {
                            iD3MusicInfo2.setArtist(null);
                        }
                        z = true;
                        break;
                    case 2:
                        try {
                            str = new String(attrData, 0, attrData.length, StandardCharsets.UTF_8);
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            str = null;
                        }
                        JL_Log.i(c, "-parseBtData- id3 album: [" + CHexConver.byte2HexStr(attrData, attrData.length) + "], " + str);
                        if (str != null && attrData.length == 64) {
                            str = str.substring(0, str.length() - 1) + "...";
                        }
                        if (!CHexConver.byte2HexStr(attrData, attrData.length).equals("00")) {
                            iD3MusicInfo2.setAlbum(str);
                        } else {
                            iD3MusicInfo2.setAlbum(null);
                        }
                        z = true;
                        break;
                    case 3:
                        int byteToInt = CHexConver.byteToInt(attrData[0]);
                        JL_Log.i(c, "-parseBtData- id3 number: " + byteToInt);
                        iD3MusicInfo2.setNumber(byteToInt);
                        z = true;
                        break;
                    case 4:
                        int bytesToInt = attrData.length >= 2 ? CHexConver.bytesToInt(attrData, 0, 2) : 0;
                        JL_Log.i(c, "-parseBtData- id3 allNum: " + bytesToInt);
                        iD3MusicInfo2.setTotal(bytesToInt);
                        z = true;
                        break;
                    case 5:
                        try {
                            str2 = new String(attrData, 0, attrData.length, StandardCharsets.UTF_8);
                        } catch (Exception e4) {
                            e4.printStackTrace();
                            str2 = null;
                        }
                        JL_Log.i(c, "-parseBtData- id3 genre: " + str2);
                        iD3MusicInfo2.setGenre(str2);
                        z = true;
                        break;
                    case 6:
                        JL_Log.i(c, "-parseBtData- id3 data: [" + CHexConver.byte2HexStr(attrData, attrData.length) + "]");
                        int bytesToInt2 = attrData.length >= 2 ? CHexConver.bytesToInt(attrData, 0, 2) : 0;
                        JL_Log.i(c, "-parseBtData- id3 allTime: " + bytesToInt2);
                        iD3MusicInfo2.setTotalTime(bytesToInt2);
                        z = true;
                        break;
                    case 7:
                        boolean z2 = (attrData[0] & 1) == 1;
                        JL_Log.d(c, "-parseBtData- id3 isPlay: " + z2);
                        iD3MusicInfo2.setPlayStatus(z2);
                        z = true;
                        break;
                    case 8:
                        int bytesToInt3 = attrData.length >= 4 ? CHexConver.bytesToInt(attrData, 0, 4) / 1000 : 0;
                        JL_Log.d(c, "-parseBtData- id3 currentTime: " + bytesToInt3);
                        iD3MusicInfo2.setCurrentTime(bytesToInt3);
                        z = true;
                        break;
                }
            }
        }
        if (z) {
            deviceInfo.setiD3MusicInfo(iD3MusicInfo2);
            this.b.onID3MusicInfo(bluetoothDevice, iD3MusicInfo2);
        }
    }

    private void c(BluetoothDevice bluetoothDevice, List<AttrBean> list) {
        DeviceInfo deviceInfo;
        if (list == null || (deviceInfo = this.f12454a.getDeviceInfo(bluetoothDevice)) == null) {
            return;
        }
        for (AttrBean attrBean : list) {
            byte[] attrData = attrBean.getAttrData();
            if (attrData != null && attrData.length != 0) {
                byte type = attrBean.getType();
                int i = 1;
                if (type == 0) {
                    FmStatusInfo fmStatusInfo = new FmStatusInfo(CHexConver.byteToInt(attrData[0]) == 1, attrData.length > 1 ? CHexConver.byteToInt(attrData[1]) : 0, attrData.length > 3 ? CHexConver.bytesToInt(attrData[2], attrData[3]) / 10.0f : 0.0f, attrData.length > 4 ? CHexConver.byteToInt(attrData[4]) : 0);
                    deviceInfo.setFmStatusInfo(fmStatusInfo);
                    this.b.onFmStatusChange(bluetoothDevice, fmStatusInfo);
                } else if (type == 1) {
                    ArrayList arrayList = new ArrayList();
                    int length = attrData.length;
                    while (length - i >= 3) {
                        int i2 = i + 1;
                        i = i2 + 2;
                        arrayList.add(new ChannelInfo(CHexConver.byteToInt(attrData[i]), CHexConver.bytesToInt(attrData[i2], attrData[i2 + 1]) / 10.0f));
                    }
                    deviceInfo.setChannelInfos(arrayList);
                    this.b.onFmChannelsChange(bluetoothDevice, arrayList);
                }
            }
        }
    }

    private void d(BluetoothDevice bluetoothDevice, List<AttrBean> list) {
        DeviceInfo deviceInfo;
        int i;
        int i2;
        if (list == null || (deviceInfo = this.f12454a.getDeviceInfo(bluetoothDevice)) == null) {
            return;
        }
        for (AttrBean attrBean : list) {
            byte[] attrData = attrBean.getAttrData();
            if (attrData != null && attrData.length != 0) {
                byte type = attrBean.getType();
                if (type == 0) {
                    boolean z = (attrData[0] & 1) == 1;
                    if (attrData.length > 4) {
                        byte[] bArr = new byte[4];
                        System.arraycopy(attrData, 1, bArr, 0, 4);
                        int bytesToInt = CHexConver.bytesToInt(bArr) * 1000;
                        if (attrData.length > 8) {
                            System.arraycopy(attrData, 5, bArr, 0, 4);
                            i2 = CHexConver.bytesToInt(bArr) * 1000;
                            i = attrData.length > 9 ? CHexConver.byteToInt(attrData[9]) : 0;
                        } else {
                            i = 0;
                            i2 = 0;
                        }
                        r6 = bytesToInt;
                    } else {
                        i = 0;
                        i2 = 0;
                    }
                    MusicStatusInfo musicStatusInfo = new MusicStatusInfo(z, r6, i2, i);
                    deviceInfo.setMusicStatusInfo(musicStatusInfo);
                    deviceInfo.setCurrentDevIndex(CHexConver.intToByte(musicStatusInfo.getCurrentDev()));
                    this.b.onMusicStatusChange(bluetoothDevice, musicStatusInfo);
                } else if (type != 1) {
                    if (type == 2) {
                        int byteToInt = CHexConver.byteToInt(attrData[0]);
                        JL_Log.d(c, "-parseMusicData- music play mode : " + byteToInt);
                        PlayModeInfo playModeInfo = new PlayModeInfo(byteToInt);
                        deviceInfo.setPlayModeInfo(playModeInfo);
                        this.b.onPlayModeChange(bluetoothDevice, playModeInfo);
                    }
                } else if (attrData.length > 3) {
                    byte[] bArr2 = new byte[4];
                    System.arraycopy(attrData, 0, bArr2, 0, 4);
                    int bytesToInt2 = CHexConver.bytesToInt(bArr2);
                    String str = null;
                    if (attrData.length > 4) {
                        boolean z2 = (attrData[4] & 255) == 1;
                        if (attrData.length > 5) {
                            try {
                                str = new String(attrData, 5, attrData.length - 5, z2 ? "gbk" : "utf-16le");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    MusicNameInfo musicNameInfo = new MusicNameInfo(bytesToInt2, str);
                    deviceInfo.setMusicNameInfo(musicNameInfo);
                    deviceInfo.setCluster(musicNameInfo.getCluster());
                    this.b.onMusicNameChange(bluetoothDevice, musicNameInfo);
                }
            }
        }
    }

    private void e(BluetoothDevice bluetoothDevice, List<AttrBean> list) {
        DeviceInfo deviceInfo = this.f12454a.getDeviceInfo(bluetoothDevice);
        if (deviceInfo == null) {
            return;
        }
        for (AttrBean attrBean : list) {
            byte[] attrData = attrBean.getAttrData();
            if (attrData != null) {
                if (attrData.length != 0) {
                    int i = 1;
                    int i2 = 0;
                    switch (attrBean.getType()) {
                        case 0:
                            this.b.onBatteryChange(bluetoothDevice, new BatteryInfo(CHexConver.byteToInt(attrData[0])));
                            break;
                        case 1:
                            int maxVol = deviceInfo.getMaxVol();
                            boolean isSupportVolumeSync = deviceInfo.isSupportVolumeSync();
                            int byteToInt = CHexConver.byteToInt(attrData[0]);
                            deviceInfo.setVolume(byteToInt);
                            this.b.onVolumeChange(bluetoothDevice, new VolumeInfo(maxVol, byteToInt, isSupportVolumeSync));
                            break;
                        case 2:
                            DevStorageInfo devStorageInfo = new DevStorageInfo();
                            devStorageInfo.parseData(attrData);
                            deviceInfo.setDevStorageInfo(devStorageInfo);
                            this.b.onDevStorageInfoChange(bluetoothDevice, devStorageInfo);
                            break;
                        case 4:
                            boolean z = (attrData[0] & 128) == 128;
                            EqInfo eqInfo = new EqInfo();
                            eqInfo.setMode(CHexConver.byteToInt(attrData[0]) & 127);
                            eqInfo.setDynamic(z);
                            if (z) {
                                int i3 = attrData[1];
                                byte[] bArr = new byte[i3];
                                System.arraycopy(attrData, 2, bArr, 0, i3);
                                eqInfo.setValue(bArr);
                                EqPresetInfo a2 = a(list);
                                if (a2 == null) {
                                    a2 = deviceInfo.getEqPresetInfo();
                                }
                                if (a2 != null) {
                                    eqInfo.setFreqs(a2.getFreqs());
                                }
                                JL_Log.d(c, "-parsePublicData- eq data freq-->" + a2);
                            } else {
                                byte[] bArr2 = new byte[0];
                                if (attrData.length > 10) {
                                    bArr2 = new byte[10];
                                    System.arraycopy(attrData, 1, bArr2, 0, 10);
                                }
                                eqInfo.setValue(bArr2);
                            }
                            deviceInfo.setEqInfo(eqInfo);
                            this.b.onEqChange(bluetoothDevice, eqInfo);
                            break;
                        case 5:
                            String str = null;
                            try {
                                str = new String(attrData);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            deviceInfo.setPlayFileFormat(str);
                            this.b.onFileFormatChange(bluetoothDevice, str);
                            break;
                        case 6:
                            int byteToInt2 = CHexConver.byteToInt(attrData[0]);
                            deviceInfo.setCurFunction(attrData[0]);
                            JL_Log.i(c, "-parsePublicData- onDeviceModeChange >> " + byteToInt2);
                            this.b.onDeviceModeChange(bluetoothDevice, byteToInt2);
                            break;
                        case 7:
                            JL_Log.i(c, "-parsePublicData- SYS_INFO_ATTR_LIGHT");
                            int byteToInt3 = CHexConver.byteToInt(attrData[0]);
                            int byteToInt4 = CHexConver.byteToInt(attrData[1]);
                            int byteToInt5 = CHexConver.byteToInt(attrData[2]);
                            int byteToInt6 = CHexConver.byteToInt(attrData[3]);
                            int byteToInt7 = CHexConver.byteToInt(attrData[4]);
                            int byteToInt8 = CHexConver.byteToInt(attrData[5]);
                            int byteToInt9 = CHexConver.byteToInt(attrData[6]);
                            LightControlInfo luminance = new LightControlInfo().setSwitchState(byteToInt3 & 3).setLightMode((byteToInt3 & 12) >>> 2).setColor(Color.rgb(byteToInt4, byteToInt5, byteToInt6)).setTwinkleMode(byteToInt7).setTwinkleFreq(byteToInt8).setSceneMode(byteToInt9).setHue(CHexConver.bytesToInt(attrData[7], attrData[8])).setSaturation(CHexConver.byteToInt(attrData[9])).setLuminance(CHexConver.byteToInt(attrData[10]));
                            deviceInfo.setLightControlInfo(luminance);
                            this.b.onLightControlInfo(bluetoothDevice, luminance);
                            break;
                        case 8:
                            if (attrData.length >= 2 && attrData.length < 4) {
                                byte[] bArr3 = new byte[2];
                                System.arraycopy(attrData, 0, bArr3, 0, 2);
                                i2 = CHexConver.bytesToInt(bArr3[0], bArr3[1]);
                            } else if (attrData.length >= 4) {
                                byte[] bArr4 = new byte[4];
                                System.arraycopy(attrData, 0, bArr4, 0, 4);
                                i2 = CHexConver.bytesToInt(bArr4);
                            }
                            float f = i2 / 10.0f;
                            deviceInfo.setFrequency(f);
                            this.b.onFrequencyTx(bluetoothDevice, f);
                            break;
                        case 9:
                            this.b.onPeripheralsModeChange(bluetoothDevice, CHexConver.byteToInt(attrData[0]));
                            break;
                        case 10:
                            byte[] bArr5 = new byte[0];
                            boolean z2 = attrData[0] == 1;
                            if (z2 && attrData.length > 6) {
                                bArr5 = new byte[6];
                                System.arraycopy(attrData, 1, bArr5, 0, 6);
                            }
                            this.b.onPeripheralsConnectStatusChange(bluetoothDevice, z2, CHexConver.hexDataCovetToAddress(bArr5));
                            break;
                        case 11:
                            if (attrData.length >= 8) {
                                this.b.onHighAndBassChange(bluetoothDevice, CHexConver.bytesToInt(attrData, 0, 4), CHexConver.bytesToInt(attrData, 4, 4));
                                break;
                            } else {
                                break;
                            }
                        case 12:
                            JL_Log.d(c, "-parsePublicData- eq preset data-->" + CHexConver.byte2HexStr(attrData, attrData.length));
                            EqPresetInfo a3 = a(attrBean);
                            deviceInfo.setEqPresetInfo(a3);
                            this.b.onEqPresetChange(bluetoothDevice, a3);
                            break;
                        case 13:
                            if (attrData.length >= 9) {
                                byte[] bArr6 = new byte[9];
                                System.arraycopy(attrData, 0, bArr6, 0, 9);
                                VoiceMode parse = VoiceMode.parse(bArr6);
                                deviceInfo.setCurrentVoiceMode(parse);
                                this.b.onCurrentVoiceMode(bluetoothDevice, parse);
                                break;
                            } else {
                                break;
                            }
                        case 14:
                            int i4 = attrData[0];
                            if (i4 > 0 && attrData.length >= (i4 * 9) + 1) {
                                byte[] bArr7 = new byte[9];
                                ArrayList arrayList = new ArrayList();
                                for (int i5 = 0; i5 < i4; i5++) {
                                    System.arraycopy(attrData, i, bArr7, 0, 9);
                                    i += 9;
                                    arrayList.add(VoiceMode.parse(bArr7));
                                }
                                deviceInfo.setVoiceModeList(arrayList);
                                this.b.onVoiceModeList(bluetoothDevice, arrayList);
                                break;
                            }
                            break;
                        case 15:
                            JL_Log.e(c, "-parsePublicData- phone status change ：" + CHexConver.byte2HexStr(attrData, attrData.length));
                            int byteToInt10 = CHexConver.byteToInt(attrData[0]);
                            deviceInfo.setPhoneStatus(byteToInt10);
                            this.b.onPhoneCallStatusChange(bluetoothDevice, byteToInt10);
                            break;
                        case 16:
                            int length = attrData.length;
                            byte[] bArr8 = new byte[length];
                            System.arraycopy(attrData, 0, bArr8, 0, length);
                            a(bluetoothDevice, bArr8);
                            break;
                        case 17:
                            EqInfo eqInfo2 = new EqInfo();
                            eqInfo2.setDynamic(true);
                            eqInfo2.setMode(0);
                            int i6 = attrData[0];
                            int[] iArr = new int[i6];
                            while (i2 < i6) {
                                iArr[i2] = CHexConver.bytesToInt(attrData, i, 2);
                                i2++;
                                i += 2;
                            }
                            eqInfo2.setFreqs(iArr);
                            deviceInfo.setSoundCardEqInfo(eqInfo2);
                            break;
                        case 18:
                            EqInfo soundCardEqInfo = deviceInfo.getSoundCardEqInfo();
                            if (soundCardEqInfo == null) {
                                break;
                            } else {
                                int i7 = attrData[0];
                                byte[] bArr9 = new byte[i7];
                                System.arraycopy(attrData, 1, bArr9, 0, i7);
                                soundCardEqInfo.setValue(bArr9);
                                deviceInfo.setSoundCardEqInfo(soundCardEqInfo);
                                this.b.onSoundCardEqChange(bluetoothDevice, soundCardEqInfo);
                                break;
                            }
                        case 19:
                            JL_Log.d(c, "-parsePublicData- 声卡功能状态变化：" + CHexConver.byte2HexStr(attrData, attrData.length));
                            if (attrData.length >= 8) {
                                long bytesToLong = CHexConver.bytesToLong(attrData, 0, 8);
                                JL_Log.d(c, "-parsePublicData- 声卡功能状态变化 ： mask  ->  " + CHexConver.byte2HexStr(attrData, 8));
                                byte[] bArr10 = new byte[0];
                                if (attrData.length > 8) {
                                    int length2 = attrData.length - 8;
                                    byte[] bArr11 = new byte[length2];
                                    System.arraycopy(attrData, 8, bArr11, 0, length2);
                                    JL_Log.d(c, "-parsePublicData- 声卡功能状态变化 ： values  ->  " + CHexConver.byte2HexStr(bArr11, length2));
                                    bArr10 = bArr11;
                                }
                                this.b.onSoundCardStatusChange(bluetoothDevice, bytesToLong, bArr10);
                                break;
                            } else {
                                break;
                            }
                    }
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01a3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void f(android.bluetooth.BluetoothDevice r23, java.util.List<com.jieli.jl_rcsp.model.device.AttrBean> r24) {
        /*
            Method dump skipped, instructions count: 611
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jieli.jl_rcsp.tool.RcspDataHandler.f(android.bluetooth.BluetoothDevice, java.util.List):void");
    }

    public void parseAttrMessage(BluetoothDevice bluetoothDevice, byte b, List<AttrBean> list) {
        if (bluetoothDevice == null || list == null) {
            return;
        }
        if (b == -1) {
            e(bluetoothDevice, list);
        } else if (b == 0) {
            b(bluetoothDevice, list);
        } else if (b == 1) {
            d(bluetoothDevice, list);
        } else if (b == 2) {
            f(bluetoothDevice, list);
        } else if (b == 3) {
            a(bluetoothDevice, list);
        } else if (b != 4) {
        } else {
            c(bluetoothDevice, list);
        }
    }

    public void parseHealthSetting(BluetoothDevice bluetoothDevice, List<AttrBean> list) {
        DeviceInfo deviceInfo = this.f12454a.getDeviceInfo();
        if (deviceInfo == null) {
            return;
        }
        HealthSettingInfo healthSettingInfo = deviceInfo.getHealthSettingInfo();
        if (healthSettingInfo == null) {
            healthSettingInfo = new HealthSettingInfo();
            deviceInfo.setHealthSettingInfo(healthSettingInfo);
        }
        for (AttrBean attrBean : list) {
            byte[] attrData = attrBean.getAttrData();
            healthSettingInfo.setFuncFlag(attrBean.getType());
            switch (attrBean.getType()) {
                case 1:
                    healthSettingInfo.setSensorInfo(new SensorInfo(CHexConver.bytesToInt(attrData, 0, 2)));
                    break;
                case 2:
                    healthSettingInfo.setSedentaryReminder(new SedentaryReminder(attrData));
                    break;
                case 3:
                    healthSettingInfo.setHeartRateMeasure(new HeartRateMeasure(attrData));
                    break;
                case 4:
                    healthSettingInfo.setExerciseHeartRateReminder(new ExerciseHeartRateReminder(attrData));
                    break;
                case 5:
                    healthSettingInfo.setAutomaticPressureDetection(new AutomaticPressureDetection(attrData));
                    break;
                case 6:
                    healthSettingInfo.setSleepDetection(new SleepDetection(attrData));
                    break;
                case 7:
                    healthSettingInfo.setFallDetection(new FallDetection(attrData));
                    break;
                case 8:
                    healthSettingInfo.setLiftWristDetection(new LiftWristDetection(attrData));
                    break;
                case 9:
                    healthSettingInfo.setUserInfo(new UserInfo(attrData));
                    break;
                case 10:
                    healthSettingInfo.setDisconnectReminder(new DisconnectReminder(attrData));
                    break;
                case 11:
                    healthSettingInfo.setBloodOxygenMeasurementAlert(new BloodOxygenMeasurementAlert(attrData));
                    break;
                case 12:
                    healthSettingInfo.setEmergencyContact(new EmergencyContact(attrData));
                    break;
            }
            this.b.onHealthSettingChange(bluetoothDevice, healthSettingInfo);
        }
    }

    private void a(BluetoothDevice bluetoothDevice, byte[] bArr) {
        JL_Log.d(c, "parseFixedLenData-->" + CHexConver.byte2HexStr(bArr));
        DeviceInfo deviceInfo = this.f12454a.getDeviceInfo(bluetoothDevice);
        if (deviceInfo == null) {
            return;
        }
        ExpandFunction expandFunction = deviceInfo.getExpandFunction();
        int i = 4;
        if (bArr.length >= 4) {
            int bytesToInt = CHexConver.bytesToInt(bArr, 0, 4);
            if (expandFunction == null) {
                expandFunction = new ExpandFunction();
                expandFunction.setMask(bytesToInt);
            } else {
                expandFunction.setMask(expandFunction.getMask() | bytesToInt);
            }
            if ((bytesToInt & 1) == 1 && bArr.length >= 9) {
                byte[] bArr2 = new byte[5];
                System.arraycopy(bArr, 4, bArr2, 0, 5);
                expandFunction.putData(0, bArr2);
                this.b.onExpandFunction(bluetoothDevice, 0, bArr2);
                ReverberationParam parseData = ReverberationParam.parseData(bArr2);
                if (parseData != null) {
                    this.b.onReverberation(bluetoothDevice, parseData);
                }
                i = 9;
            }
            if ((bytesToInt & 2) == 2 && bArr.length >= i + 2) {
                byte[] bArr3 = new byte[2];
                System.arraycopy(bArr, i, bArr3, 0, 2);
                expandFunction.putData(1, bArr3);
                this.b.onExpandFunction(bluetoothDevice, 1, bArr3);
                DynamicLimiterParam parseData2 = DynamicLimiterParam.parseData(bArr3);
                if (parseData2 != null) {
                    this.b.onDynamicLimiter(bluetoothDevice, parseData2);
                }
            }
            deviceInfo.setExpandFunction(expandFunction);
        }
    }

    private EqPresetInfo a(List<AttrBean> list) {
        AttrBean attrBean;
        Iterator<AttrBean> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                attrBean = null;
                break;
            }
            attrBean = it.next();
            if (attrBean.getType() == 12) {
                break;
            }
        }
        if (attrBean == null) {
            return null;
        }
        return a(attrBean);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private EqPresetInfo a(AttrBean attrBean) {
        EqPresetInfo eqPresetInfo = new EqPresetInfo();
        byte[] attrData = attrBean.getAttrData();
        ArrayList arrayList = new ArrayList();
        int i = attrData[0];
        int[] iArr = new int[i];
        int i2 = 1;
        for (int i3 = 0; i3 < i; i3++) {
            iArr[i3] = ((attrData[i2] & 255) << 8) | (attrData[i2 + 1] & 255);
            i2 += 2;
        }
        for (int i4 = 0; i4 < 7; i4++) {
            byte[] bArr = new byte[i];
            System.arraycopy(attrData, i2 + 1, bArr, 0, i);
            EqInfo eqInfo = new EqInfo((byte) (attrData[i2] & 127), bArr, iArr);
            eqInfo.setDynamic((attrData[i2] & 128) == 128);
            arrayList.add(eqInfo);
            i2 += i + 1;
        }
        eqPresetInfo.setNumber(i);
        eqPresetInfo.setFreqs(iArr);
        eqPresetInfo.setEqInfos(arrayList);
        JL_Log.d(c, "-parseEqPresetInfo- eq--->" + eqPresetInfo);
        return eqPresetInfo;
    }

    private int b(List<AttrBean> list) {
        for (AttrBean attrBean : list) {
            if (attrBean.getType() == 4) {
                return attrBean.getAttrData()[0] & 7;
            }
        }
        return 0;
    }
}
