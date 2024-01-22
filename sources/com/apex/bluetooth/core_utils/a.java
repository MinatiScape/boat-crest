package com.apex.bluetooth.core_utils;

import a.b;
import a.e;
import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import com.apex.bluetooth.callback.DataReportCallback;
import com.apex.bluetooth.data_parse.b;
import com.apex.bluetooth.data_parse.d;
import com.apex.bluetooth.enumeration.CheckType;
import com.apex.bluetooth.enumeration.EABleSportStatus;
import com.apex.bluetooth.model.EABleMtu;
import com.apex.bluetooth.model.EABleMusicControl;
import com.apex.bluetooth.model.EABleOtaInfo;
import com.apex.bluetooth.model.EABleQueryMusic;
import com.apex.bluetooth.model.EABleReportMonitorData;
import com.apex.bluetooth.model.EABleReportSportData;
import com.apex.bluetooth.model.EABleSocialResponse;
import com.apex.bluetooth.model.EABleSwitch;
import com.apex.bluetooth.model.EABleTimelyData;
import com.apex.bluetooth.utils.LogData2File;
import com.apex.bluetooth.utils.LogUtils;
import java.util.List;
import java.util.Objects;
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public final String f2206a = a.class.getSimpleName();
    public com.apex.bluetooth.data_parse.a b = new com.apex.bluetooth.data_parse.a();
    public b c = new b();
    public d d = new d();

    /* JADX WARN: Removed duplicated region for block: B:553:0x0766  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(@androidx.annotation.NonNull byte[] r18, com.apex.bluetooth.core.m.c r19) {
        /*
            Method dump skipped, instructions count: 2823
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apex.bluetooth.core_utils.a.a(byte[], com.apex.bluetooth.core.m.c):void");
    }

    public void a(@NonNull byte[] bArr, com.apex.bluetooth.core.m.a aVar) {
        if (this.c == null) {
            this.c = new b();
        }
        Objects.requireNonNull(this.c);
        if (bArr == null || aVar == null) {
            return;
        }
        int i = ((bArr[4] & 255) << 8) | (bArr[3] & 255);
        int length = bArr.length - 6;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 5, bArr2, 0, length);
        if (i != 2001) {
            if (i == 2002) {
                EABleQueryMusic eABleQueryMusic = new EABleQueryMusic();
                int i2 = b.h.d.parseFrom(bArr2).f199a;
                if (i2 == 0) {
                    eABleQueryMusic.setE_app(EABleQueryMusic.PlayerType.default_type);
                } else if (i2 == 1) {
                    eABleQueryMusic.setE_app(EABleQueryMusic.PlayerType.apple_music);
                } else if (i2 == 2) {
                    eABleQueryMusic.setE_app(EABleQueryMusic.PlayerType.deeze);
                } else {
                    eABleQueryMusic.setE_app(EABleQueryMusic.PlayerType.spotify);
                }
                LogUtils.i(aVar.b, "queryMusic");
                DataReportCallback dataReportCallback = aVar.f2192a;
                if (dataReportCallback != null) {
                    dataReportCallback.queryMusic(eABleQueryMusic);
                    return;
                }
                return;
            } else if (i == 2004) {
                b.g parseFrom = b.g.f.parseFrom(bArr2);
                EABleMusicControl eABleMusicControl = new EABleMusicControl();
                int i3 = parseFrom.f196a;
                if (i3 == 0) {
                    eABleMusicControl.setE_ops(EABleMusicControl.MusicControl.play_start);
                } else if (i3 == 1) {
                    eABleMusicControl.setE_ops(EABleMusicControl.MusicControl.play_stop);
                } else if (i3 == 2) {
                    eABleMusicControl.setE_ops(EABleMusicControl.MusicControl.previous_song);
                } else if (i3 == 3) {
                    eABleMusicControl.setE_ops(EABleMusicControl.MusicControl.next_song);
                } else if (i3 == 4) {
                    eABleMusicControl.setE_ops(EABleMusicControl.MusicControl.volume_up);
                } else {
                    eABleMusicControl.setE_ops(EABleMusicControl.MusicControl.volume_reduction);
                }
                eABleMusicControl.setElapsedtime(parseFrom.c);
                eABleMusicControl.setVolume(parseFrom.b);
                LogUtils.i(aVar.b, "musicControl");
                DataReportCallback dataReportCallback2 = aVar.f2192a;
                if (dataReportCallback2 != null) {
                    dataReportCallback2.musicControl(eABleMusicControl);
                    return;
                }
                return;
            } else if (i == 2005) {
                b.c parseFrom2 = b.c.e.parseFrom(bArr2);
                EABleSocialResponse eABleSocialResponse = new EABleSocialResponse();
                eABleSocialResponse.setContent(parseFrom2.b());
                eABleSocialResponse.setId(parseFrom2.f187a);
                LogUtils.i(aVar.b, "socialResponse");
                DataReportCallback dataReportCallback3 = aVar.f2192a;
                if (dataReportCallback3 != null) {
                    dataReportCallback3.socialResponse(null);
                    return;
                }
                return;
            } else if (i == 2006) {
                EABleMtu eABleMtu = new EABleMtu();
                eABleMtu.setMtu_value(b.f.d.parseFrom(bArr2).f194a);
                LogUtils.i(aVar.b, "responseMtu");
                DataReportCallback dataReportCallback4 = aVar.f2192a;
                if (dataReportCallback4 != null) {
                    dataReportCallback4.mtu(eABleMtu);
                    return;
                }
                return;
            } else if (i == 2007) {
                b.i parseFrom3 = b.i.k.parseFrom(bArr2);
                EABleTimelyData eABleTimelyData = new EABleTimelyData();
                eABleTimelyData.setDuration(parseFrom3.d);
                eABleTimelyData.setDistance(parseFrom3.c);
                eABleTimelyData.setBlood_oxygen(parseFrom3.g);
                eABleTimelyData.setCalorie(parseFrom3.b);
                eABleTimelyData.setHr(parseFrom3.e);
                eABleTimelyData.setPressure(parseFrom3.f);
                eABleTimelyData.setSteps(parseFrom3.f202a);
                eABleTimelyData.setBat_lev(parseFrom3.h);
                LogUtils.i(aVar.b, "timelyData");
                DataReportCallback dataReportCallback5 = aVar.f2192a;
                if (dataReportCallback5 != null) {
                    dataReportCallback5.timelyData(eABleTimelyData);
                    return;
                }
                return;
            } else if (i == 2008) {
                b.e parseFrom4 = b.e.l.parseFrom(bArr2);
                EABleReportSportData eABleReportSportData = new EABleReportSportData();
                eABleReportSportData.setCalorie(parseFrom4.b);
                eABleReportSportData.setHr(parseFrom4.c);
                eABleReportSportData.setSteps(parseFrom4.f192a);
                eABleReportSportData.setTimestamp(parseFrom4.d);
                eABleReportSportData.setCount(parseFrom4.f);
                eABleReportSportData.setAltitude(parseFrom4.g);
                eABleReportSportData.setDistance(parseFrom4.e);
                eABleReportSportData.setPace(parseFrom4.h);
                eABleReportSportData.setStride_frequency(parseFrom4.i);
                LogUtils.i(aVar.b, "appSportData");
                DataReportCallback dataReportCallback6 = aVar.f2192a;
                if (dataReportCallback6 != null) {
                    dataReportCallback6.appSportData(eABleReportSportData);
                    return;
                }
                return;
            } else if (i == 2009) {
                b.d parseFrom5 = b.d.f.parseFrom(bArr2);
                EABleReportMonitorData eABleReportMonitorData = new EABleReportMonitorData();
                if (b.d.c.a(parseFrom5.f189a) == null) {
                    b.d.c cVar = b.d.c.UNRECOGNIZED;
                }
                int i4 = parseFrom5.f189a;
                if (i4 == 0) {
                    eABleReportMonitorData.setCheckType(CheckType.default_type);
                } else if (i4 == 1) {
                    eABleReportMonitorData.setCheckType(CheckType.hr);
                } else if (i4 == 2) {
                    eABleReportMonitorData.setCheckType(CheckType.stress);
                } else if (i4 == 3) {
                    eABleReportMonitorData.setCheckType(CheckType.blood_oxygen);
                } else if (i4 == 4) {
                    eABleReportMonitorData.setCheckType(CheckType.breathe);
                }
                eABleReportMonitorData.setMonitorStatus(parseFrom5.c);
                eABleReportMonitorData.setMonitorData(parseFrom5.b);
                LogUtils.i(aVar.b, "monitorData");
                DataReportCallback dataReportCallback7 = aVar.f2192a;
                if (dataReportCallback7 != null) {
                    dataReportCallback7.reportMonitorData(eABleReportMonitorData);
                    return;
                }
                return;
            } else {
                return;
            }
        }
        b.j parseFrom6 = b.j.e.parseFrom(bArr2);
        parseFrom6.c();
        if (parseFrom6.c() == b.j.c.search_phone) {
            LogUtils.i(aVar.b, "searchPhone");
            DataReportCallback dataReportCallback8 = aVar.f2192a;
            if (dataReportCallback8 != null) {
                dataReportCallback8.searchPhone();
            }
        } else if (parseFrom6.c() == b.j.c.stop_search_phone) {
            LogUtils.i(aVar.b, "stopSearchPhone");
            DataReportCallback dataReportCallback9 = aVar.f2192a;
            if (dataReportCallback9 != null) {
                dataReportCallback9.stopSearchPhone();
            }
        } else if (parseFrom6.c() == b.j.c.connect_the_camera) {
            LogUtils.i(aVar.b, "connectCamera");
            DataReportCallback dataReportCallback10 = aVar.f2192a;
            if (dataReportCallback10 != null) {
                dataReportCallback10.connectCamera();
            }
        } else if (parseFrom6.c() == b.j.c.start_taking_pictures) {
            LogUtils.i(aVar.b, "takePhoto");
            DataReportCallback dataReportCallback11 = aVar.f2192a;
            if (dataReportCallback11 != null) {
                dataReportCallback11.takePhoto();
            }
        } else if (parseFrom6.c() == b.j.c.stop_taking_pictures) {
            LogUtils.i(aVar.b, "endTakePhoto");
            DataReportCallback dataReportCallback12 = aVar.f2192a;
            if (dataReportCallback12 != null) {
                dataReportCallback12.endTakePhoto();
            }
        } else if (parseFrom6.c() == b.j.c.request_the_latest_weather) {
            LogUtils.i(aVar.b, "updateWeather");
            DataReportCallback dataReportCallback13 = aVar.f2192a;
            if (dataReportCallback13 != null) {
                dataReportCallback13.updateWeather();
            }
        } else if (parseFrom6.c() == b.j.c.request_the_agps) {
            LogUtils.i(aVar.b, "updateAgps");
            DataReportCallback dataReportCallback14 = aVar.f2192a;
            if (dataReportCallback14 != null) {
                dataReportCallback14.updateAgps();
            }
        } else if (parseFrom6.c() == b.j.c.request_the_menstrual_cycle) {
            LogUtils.i(aVar.b, "circadian");
            DataReportCallback dataReportCallback15 = aVar.f2192a;
            if (dataReportCallback15 != null) {
                dataReportCallback15.circadian();
            }
        } else if (parseFrom6.c() == b.j.c.big_8803data_update_finish) {
            LogUtils.i(aVar.b, "transmissionComplete");
            LogData2File.getInstance().saveLogData("transmissionComplete");
            DataReportCallback dataReportCallback16 = aVar.f2192a;
            if (dataReportCallback16 != null) {
                dataReportCallback16.transmissionComplete();
            }
        } else if (parseFrom6.c() == b.j.c.stop_search_watch) {
            LogUtils.i(aVar.b, "stopSearchWatch");
            DataReportCallback dataReportCallback17 = aVar.f2192a;
            if (dataReportCallback17 != null) {
                dataReportCallback17.stopSearchWatch();
            }
        } else if (parseFrom6.c() == b.j.c.request_bt_one_key_connect) {
            LogUtils.i(aVar.b, "startBTConnect");
            if (aVar.c) {
                return;
            }
            Log.e(aVar.b, "开始BT连接");
            com.apex.bluetooth.broadcast.a aVar2 = aVar.d;
            if (aVar2 != null) {
                Context context = aVar.e;
                Thread thread = aVar2.e;
                if (thread != null) {
                    thread.interrupt();
                    aVar2.e = null;
                }
                com.apex.bluetooth.broadcast.d dVar = new com.apex.bluetooth.broadcast.d(aVar2, context);
                aVar2.e = dVar;
                dVar.start();
            }
        } else if (parseFrom6.c() == b.j.c.app_sport_pause) {
            LogUtils.i(aVar.b, "pauseAppSport");
            DataReportCallback dataReportCallback18 = aVar.f2192a;
            if (dataReportCallback18 != null) {
                dataReportCallback18.appSportStatus(EABleSportStatus.pause);
            }
        } else if (parseFrom6.c() == b.j.c.app_sport_continue) {
            LogUtils.i(aVar.b, "processedAppSport");
            DataReportCallback dataReportCallback19 = aVar.f2192a;
            if (dataReportCallback19 != null) {
                dataReportCallback19.appSportStatus(EABleSportStatus.processed);
            }
        } else if (parseFrom6.c() == b.j.c.app_sport_end) {
            LogUtils.i(aVar.b, "endAppSport");
            DataReportCallback dataReportCallback20 = aVar.f2192a;
            if (dataReportCallback20 != null) {
                dataReportCallback20.appSportStatus(EABleSportStatus.close);
            }
        } else if (parseFrom6.c() == b.j.c.incoming_call_accept) {
            LogUtils.i(aVar.b, "answerIncoming");
            DataReportCallback dataReportCallback21 = aVar.f2192a;
            if (dataReportCallback21 != null) {
                dataReportCallback21.answerIncoming();
            }
        } else if (parseFrom6.c() == b.j.c.incoming_call_reject) {
            LogUtils.i(aVar.b, "hangUpIncoming");
            DataReportCallback dataReportCallback22 = aVar.f2192a;
            if (dataReportCallback22 != null) {
                dataReportCallback22.hangUpIncoming();
            }
        } else if (parseFrom6.c() == b.j.c.not_disturb_close) {
            EABleSwitch eABleSwitch = EABleSwitch.off;
            LogUtils.i(aVar.b, "disturbStatus");
            DataReportCallback dataReportCallback23 = aVar.f2192a;
            if (dataReportCallback23 != null) {
                dataReportCallback23.disturbStatus(eABleSwitch);
            }
        } else if (parseFrom6.c() == b.j.c.not_disturb_open) {
            EABleSwitch eABleSwitch2 = EABleSwitch.on;
            LogUtils.i(aVar.b, "disturbStatus");
            DataReportCallback dataReportCallback24 = aVar.f2192a;
            if (dataReportCallback24 != null) {
                dataReportCallback24.disturbStatus(eABleSwitch2);
            }
        } else if (parseFrom6.c() == b.j.c.gestures_open) {
            EABleSwitch eABleSwitch3 = EABleSwitch.on;
            LogUtils.i(aVar.b, "brightScreenStatus");
            DataReportCallback dataReportCallback25 = aVar.f2192a;
            if (dataReportCallback25 != null) {
                dataReportCallback25.brightScreenStatus(eABleSwitch3);
            }
        } else if (parseFrom6.c() == b.j.c.gestures_close) {
            EABleSwitch eABleSwitch4 = EABleSwitch.off;
            LogUtils.i(aVar.b, "brightScreenStatus");
            DataReportCallback dataReportCallback26 = aVar.f2192a;
            if (dataReportCallback26 != null) {
                dataReportCallback26.brightScreenStatus(eABleSwitch4);
            }
        }
    }

    public void a(@NonNull byte[] bArr, com.apex.bluetooth.listener.b bVar) {
        if (this.d == null) {
            this.d = new d();
        }
        d dVar = this.d;
        Objects.requireNonNull(dVar);
        if (bArr == null) {
            return;
        }
        int i = ((bArr[4] & 255) << 8) | (bArr[3] & 255);
        int length = bArr.length - 6;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 5, bArr2, 0, length);
        if (i == 9000) {
            e.b parseFrom = e.b.f.parseFrom(bArr2);
            EABleOtaInfo eABleOtaInfo = new EABleOtaInfo();
            eABleOtaInfo.setRequest_id(parseFrom.f290a);
            eABleOtaInfo.setReceive_bytes(parseFrom.c);
            e.b.c a2 = e.b.c.a(parseFrom.b);
            if (a2 == null) {
                a2 = e.b.c.UNRECOGNIZED;
            }
            if (a2 == e.b.c.accept) {
                eABleOtaInfo.setOtaStatus(EABleOtaInfo.OtaStatus.accept);
            } else if (a2 == e.b.c.reject) {
                LogUtils.i(dVar.f2214a, "OTA时,其他原因拒绝");
                LogData2File.getInstance().saveLogData("OTA时,其他原因拒绝");
                eABleOtaInfo.setOtaStatus(EABleOtaInfo.OtaStatus.reject);
            } else if (a2 == e.b.c.reject_version_error) {
                LogUtils.i(dVar.f2214a, "OTA时,低版本被拒绝");
                LogData2File.getInstance().saveLogData("OTA时,低版本被拒绝");
                eABleOtaInfo.setOtaStatus(EABleOtaInfo.OtaStatus.reject_version_error);
            } else if (a2 == e.b.c.proceed) {
                eABleOtaInfo.setOtaStatus(EABleOtaInfo.OtaStatus.proceed);
            } else if (a2 == e.b.c.crc_error) {
                eABleOtaInfo.setOtaStatus(EABleOtaInfo.OtaStatus.crc_error);
            } else {
                eABleOtaInfo.setOtaStatus(EABleOtaInfo.OtaStatus.complete);
            }
            if (bVar != null) {
                bVar.a(eABleOtaInfo);
            }
        }
    }

    public boolean a(byte[] bArr) {
        if (bArr != null) {
            if (bArr.length < 4) {
                return false;
            }
            int i = bArr[0] & 255;
            int i2 = bArr[bArr.length - 1] & 255;
            int i3 = ((bArr[2] & 255) << 8) | (bArr[1] & 255);
            if (i == 234 && i2 == 239 && i3 == bArr.length - 4) {
                return true;
            }
            LogUtils.i(this.f2206a, "Incomplete data");
        } else {
            LogUtils.i(this.f2206a, "Data does not exist");
        }
        return false;
    }

    public final void a(byte[] bArr, int i, List<byte[]> list) {
        if (i < bArr.length && (bArr[i] & 255) == 234 && i <= bArr.length - 2) {
            int i2 = ((bArr[i + 2] & 255) << 8) | (bArr[i + 1] & 255);
            int i3 = i + i2 + 4;
            if (i3 <= bArr.length && (bArr[i3 - 1] & 255) == 239) {
                int i4 = i2 + 4;
                byte[] bArr2 = new byte[i4];
                System.arraycopy(bArr, i, bArr2, 0, i4);
                list.add(bArr2);
                a(bArr, i + i4, list);
            }
        }
    }
}
