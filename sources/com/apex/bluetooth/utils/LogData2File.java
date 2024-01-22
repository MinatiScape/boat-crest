package com.apex.bluetooth.utils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.alibaba.fastjson.JSON;
import com.apex.bluetooth.model.EABleBloodOxygen;
import com.apex.bluetooth.model.EABleDailyData;
import com.apex.bluetooth.model.EABleGpsData;
import com.apex.bluetooth.model.EABleHeartData;
import com.apex.bluetooth.model.EABleMultiData;
import com.apex.bluetooth.model.EABlePaceData;
import com.apex.bluetooth.model.EABlePressureData;
import com.apex.bluetooth.model.EABleRestingRateData;
import com.apex.bluetooth.model.EABleSleepData;
import com.apex.bluetooth.model.EABleStepFrequencyData;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.jstyle.blesdk1860.constant.BleConst;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;
/* loaded from: classes.dex */
public class LogData2File {
    public static final String TAG = "LogData2File";
    public static LogData2File logData2File;
    public volatile boolean isSaveLog;
    public volatile boolean isSaveOriginalData;
    public ConcurrentLinkedQueue<String> logCache;
    public Context mContext;
    public Timer mTimer;

    public LogData2File() {
        if (this.mTimer == null) {
            Timer timer = new Timer();
            this.mTimer = timer;
            timer.schedule(new TimerTask() { // from class: com.apex.bluetooth.utils.LogData2File.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    try {
                        LogData2File.this.saveLog2File();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, 0L, 5L);
        }
    }

    public static LogData2File getInstance() {
        if (logData2File == null) {
            synchronized (LogData2File.class) {
                if (logData2File == null) {
                    logData2File = new LogData2File();
                }
            }
        }
        return logData2File;
    }

    private File getSaveFile(int i) {
        String str;
        String str2 = "spo2.txt";
        if (Build.VERSION.SDK_INT >= 24) {
            if ("mounted".equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageEmulated()) {
                if (i == 1) {
                    str = this.mContext.getExternalCacheDir().getAbsolutePath() + "/daily_data";
                    str2 = "daily.txt";
                } else if (i == 2) {
                    str = this.mContext.getExternalCacheDir().getAbsolutePath() + "/sleep_data";
                    str2 = "sleep.txt";
                } else if (i == 3) {
                    str = this.mContext.getExternalCacheDir().getAbsolutePath() + "/heart_data";
                    str2 = "heart.txt";
                } else if (i == 4) {
                    str = this.mContext.getExternalCacheDir().getAbsolutePath() + "/gps_data";
                    str2 = "gps.txt";
                } else if (i == 5) {
                    str = this.mContext.getExternalCacheDir().getAbsolutePath() + "/multi_data";
                    str2 = "multi.txt";
                } else if (i == 6) {
                    str = this.mContext.getExternalCacheDir().getAbsolutePath() + "/spo2_data";
                } else if (i == 7) {
                    str = this.mContext.getExternalCacheDir().getAbsolutePath() + "/stress_data";
                    str2 = "stress.txt";
                } else if (i == 8) {
                    str = this.mContext.getExternalCacheDir().getAbsolutePath() + "/freq_data";
                    str2 = "freq.txt";
                } else if (i == 9) {
                    str = this.mContext.getExternalCacheDir().getAbsolutePath() + "/pace_data";
                    str2 = "pace.txt";
                } else if (i == 10) {
                    str = this.mContext.getExternalCacheDir().getAbsolutePath() + "/resting_data";
                    str2 = "resting.txt";
                } else {
                    if (i == 11) {
                        str = this.mContext.getExternalCacheDir().getAbsolutePath() + "/log_data";
                        str2 = "log.txt";
                    }
                    str = null;
                    str2 = null;
                }
            } else if (i == 1) {
                str = this.mContext.getCacheDir().getAbsolutePath() + "/daily_data";
                str2 = "daily.txt";
            } else if (i == 2) {
                str = this.mContext.getCacheDir().getAbsolutePath() + "/sleep_data";
                str2 = "sleep.txt";
            } else if (i == 3) {
                str = this.mContext.getCacheDir().getAbsolutePath() + "/heart_data";
                str2 = "heart.txt";
            } else if (i == 4) {
                str = this.mContext.getCacheDir().getAbsolutePath() + "/gps_data";
                str2 = "gps.txt";
            } else if (i == 5) {
                str = this.mContext.getCacheDir().getAbsolutePath() + "/multi_data";
                str2 = "multi.txt";
            } else if (i == 6) {
                str = this.mContext.getCacheDir().getAbsolutePath() + "/spo2_data";
            } else if (i == 7) {
                str = this.mContext.getCacheDir().getAbsolutePath() + "/stress_data";
                str2 = "stress.txt";
            } else if (i == 8) {
                str = this.mContext.getCacheDir().getAbsolutePath() + "/freq_data";
                str2 = "freq.txt";
            } else if (i == 9) {
                str = this.mContext.getCacheDir().getAbsolutePath() + "/pace_data";
                str2 = "pace.txt";
            } else if (i == 10) {
                str = this.mContext.getCacheDir().getAbsolutePath() + "/resting_data";
                str2 = "resting.txt";
            } else {
                if (i == 11) {
                    str = this.mContext.getCacheDir().getAbsolutePath() + "/log_data";
                    str2 = "log.txt";
                }
                str = null;
                str2 = null;
            }
        } else if (("mounted".equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageEmulated()) && ContextCompat.checkSelfPermission(this.mContext, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            if (i == 1) {
                str = Environment.getExternalStorageDirectory().getAbsolutePath() + "/daily_data";
                str2 = "daily.txt";
            } else if (i == 2) {
                str = Environment.getExternalStorageDirectory().getAbsolutePath() + "/sleep_data";
                str2 = "sleep.txt";
            } else if (i == 3) {
                str = Environment.getExternalStorageDirectory().getAbsolutePath() + "/heart_data";
                str2 = "heart.txt";
            } else if (i == 4) {
                str = Environment.getExternalStorageDirectory().getAbsolutePath() + "/gps_data";
                str2 = "gps.txt";
            } else if (i == 5) {
                str = Environment.getExternalStorageDirectory().getAbsolutePath() + "/multi_data";
                str2 = "multi.txt";
            } else if (i == 6) {
                str = Environment.getExternalStorageDirectory().getAbsolutePath() + "/spo2_data";
            } else if (i == 7) {
                str = Environment.getExternalStorageDirectory().getAbsolutePath() + "/stress_data";
                str2 = "stress.txt";
            } else if (i == 8) {
                str = Environment.getExternalStorageDirectory().getAbsolutePath() + "/freq_data";
                str2 = "freq.txt";
            } else if (i == 9) {
                str = Environment.getExternalStorageDirectory().getAbsolutePath() + "/pace_data";
                str2 = "pace.txt";
            } else if (i == 10) {
                str = Environment.getExternalStorageDirectory().getAbsolutePath() + "/resting_data";
                str2 = "resting.txt";
            } else {
                if (i == 11) {
                    str = Environment.getExternalStorageDirectory().getAbsolutePath() + "/log_data";
                    str2 = "log.txt";
                }
                str = null;
                str2 = null;
            }
        } else if (i == 1) {
            str = Environment.getDataDirectory().getAbsolutePath() + "/daily_data";
            str2 = "daily.txt";
        } else if (i == 2) {
            str = Environment.getDataDirectory().getAbsolutePath() + "/sleep_data";
            str2 = "sleep.txt";
        } else if (i == 3) {
            str = Environment.getDataDirectory().getAbsolutePath() + "/heart_data";
            str2 = "heart.txt";
        } else if (i == 4) {
            str = Environment.getDataDirectory().getAbsolutePath() + "/gps_data";
            str2 = "gps.txt";
        } else if (i == 5) {
            str = Environment.getDataDirectory().getAbsolutePath() + "/multi_data";
            str2 = "multi.txt";
        } else if (i == 6) {
            str = Environment.getDataDirectory().getAbsolutePath() + "/spo2_data";
        } else if (i == 7) {
            str = Environment.getDataDirectory().getAbsolutePath() + "/stress_data";
            str2 = "stress.txt";
        } else if (i == 8) {
            str = Environment.getDataDirectory().getAbsolutePath() + "/freq_data";
            str2 = "freq.txt";
        } else if (i == 9) {
            str = Environment.getDataDirectory().getAbsolutePath() + "/pace_data";
            str2 = "pace.txt";
        } else if (i == 10) {
            str = Environment.getDataDirectory().getAbsolutePath() + "/resting_data";
            str2 = "resting.txt";
        } else {
            if (i == 11) {
                str = Environment.getDataDirectory().getAbsolutePath() + "/log_data";
                str2 = "log.txt";
            }
            str = null;
            str2 = null;
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        } else if (!file.isDirectory()) {
            file.mkdirs();
        }
        File file2 = new File(file, str2);
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return file2;
    }

    private String getSaveTime() {
        Object valueOf;
        Object valueOf2;
        Object valueOf3;
        Object valueOf4;
        Object valueOf5;
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(1);
        int i2 = calendar.get(2) + 1;
        int i3 = calendar.get(5);
        int i4 = calendar.get(11);
        int i5 = calendar.get(12);
        int i6 = calendar.get(13);
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        sb.append("--");
        if (i2 < 10) {
            valueOf = BleConst.GetDeviceTime + i2;
        } else {
            valueOf = Integer.valueOf(i2);
        }
        sb.append(valueOf);
        sb.append("--");
        if (i3 < 10) {
            valueOf2 = BleConst.GetDeviceTime + i3;
        } else {
            valueOf2 = Integer.valueOf(i3);
        }
        sb.append(valueOf2);
        sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
        if (i4 < 10) {
            valueOf3 = BleConst.GetDeviceTime + i4;
        } else {
            valueOf3 = Integer.valueOf(i4);
        }
        sb.append(valueOf3);
        sb.append(":");
        if (i5 < 10) {
            valueOf4 = BleConst.GetDeviceTime + i5;
        } else {
            valueOf4 = Integer.valueOf(i5);
        }
        sb.append(valueOf4);
        sb.append(":");
        if (i6 < 10) {
            valueOf5 = BleConst.GetDeviceTime + i6;
        } else {
            valueOf5 = Integer.valueOf(i6);
        }
        sb.append(valueOf5);
        return sb.toString();
    }

    private void saveData2File(File file, String str) {
        if (file == null || !file.exists() || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(str + "\r\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveLog2File() {
        File saveFile;
        ConcurrentLinkedQueue<String> concurrentLinkedQueue = this.logCache;
        if (concurrentLinkedQueue == null || concurrentLinkedQueue.isEmpty()) {
            return;
        }
        String poll = this.logCache.poll();
        if (TextUtils.isEmpty(poll) || (saveFile = getSaveFile(11)) == null || !saveFile.exists()) {
            return;
        }
        saveData2File(saveFile, poll);
    }

    public void init(@NonNull Context context) {
        if (context != null) {
            this.mContext = context.getApplicationContext();
        }
        if (this.logCache == null) {
            this.logCache = new ConcurrentLinkedQueue<>();
        }
    }

    public void saveLogData(String str) {
        try {
            if (!TextUtils.isEmpty(str) && this.isSaveLog) {
                String str2 = getSaveTime() + "\r\n" + str;
                if (this.logCache == null) {
                    this.logCache = new ConcurrentLinkedQueue<>();
                }
                try {
                    this.logCache.add(str2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void saveOriginalDailyData(List<EABleDailyData> list) {
        File saveFile;
        if (list != null) {
            try {
                if (!list.isEmpty() && this.isSaveOriginalData && (saveFile = getSaveFile(1)) != null && saveFile.exists()) {
                    String jSONString = JSON.toJSONString(list);
                    String saveTime = getSaveTime();
                    saveData2File(saveFile, saveTime + "\r\n" + jSONString);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void saveOriginalGpsData(List<EABleGpsData> list) {
        File saveFile;
        if (list != null) {
            try {
                if (!list.isEmpty() && this.isSaveOriginalData && (saveFile = getSaveFile(4)) != null && saveFile.exists()) {
                    String jSONString = JSON.toJSONString(list);
                    String saveTime = getSaveTime();
                    saveData2File(saveFile, saveTime + "\r\n" + jSONString);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void saveOriginalHeartData(List<EABleHeartData> list) {
        File saveFile;
        if (list != null) {
            try {
                if (!list.isEmpty() && this.isSaveOriginalData && (saveFile = getSaveFile(3)) != null && saveFile.exists()) {
                    String jSONString = JSON.toJSONString(list);
                    String saveTime = getSaveTime();
                    saveData2File(saveFile, saveTime + "\r\n" + jSONString);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void saveOriginalMultiData(List<EABleMultiData> list) {
        File saveFile;
        if (list != null) {
            try {
                if (!list.isEmpty() && this.isSaveOriginalData && (saveFile = getSaveFile(5)) != null && saveFile.exists()) {
                    String jSONString = JSON.toJSONString(list);
                    String saveTime = getSaveTime();
                    saveData2File(saveFile, saveTime + "\r\n" + jSONString);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void saveOriginalPaceData(List<EABlePaceData> list) {
        File saveFile;
        if (list != null) {
            try {
                if (!list.isEmpty() && this.isSaveOriginalData && (saveFile = getSaveFile(9)) != null && saveFile.exists()) {
                    String jSONString = JSON.toJSONString(list);
                    String saveTime = getSaveTime();
                    saveData2File(saveFile, saveTime + "\r\n" + jSONString);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void saveOriginalRestingHeartData(List<EABleRestingRateData> list) {
        File saveFile;
        if (list != null) {
            try {
                if (!list.isEmpty() && this.isSaveOriginalData && (saveFile = getSaveFile(10)) != null && saveFile.exists()) {
                    String jSONString = JSON.toJSONString(list);
                    String saveTime = getSaveTime();
                    saveData2File(saveFile, saveTime + "\r\n" + jSONString);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void saveOriginalSleepData(List<EABleSleepData> list) {
        File saveFile;
        if (list != null) {
            try {
                if (!list.isEmpty() && this.isSaveOriginalData && (saveFile = getSaveFile(2)) != null && saveFile.exists()) {
                    String jSONString = JSON.toJSONString(list);
                    String saveTime = getSaveTime();
                    saveData2File(saveFile, saveTime + "\r\n" + jSONString);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void saveOriginalSpo2Data(List<EABleBloodOxygen> list) {
        File saveFile;
        if (list != null) {
            try {
                if (!list.isEmpty() && this.isSaveOriginalData && (saveFile = getSaveFile(6)) != null && saveFile.exists()) {
                    String jSONString = JSON.toJSONString(list);
                    String saveTime = getSaveTime();
                    saveData2File(saveFile, saveTime + "\r\n" + jSONString);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void saveOriginalStepFreqData(List<EABleStepFrequencyData> list) {
        File saveFile;
        if (list != null) {
            try {
                if (!list.isEmpty() && this.isSaveOriginalData && (saveFile = getSaveFile(8)) != null && saveFile.exists()) {
                    String jSONString = JSON.toJSONString(list);
                    String saveTime = getSaveTime();
                    saveData2File(saveFile, saveTime + "\r\n" + jSONString);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void saveOriginalStressData(List<EABlePressureData> list) {
        File saveFile;
        if (list != null) {
            try {
                if (!list.isEmpty() && this.isSaveOriginalData && (saveFile = getSaveFile(7)) != null && saveFile.exists()) {
                    String jSONString = JSON.toJSONString(list);
                    String saveTime = getSaveTime();
                    saveData2File(saveFile, saveTime + "\r\n" + jSONString);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setSaveLog(boolean z) {
        this.isSaveLog = z;
    }

    public void setSaveOriginalData(boolean z) {
        this.isSaveOriginalData = z;
    }
}
