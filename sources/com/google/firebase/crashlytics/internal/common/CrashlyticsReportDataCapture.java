package com.google.firebase.crashlytics.internal.common;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.google.firebase.crashlytics.BuildConfig;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.crashlytics.internal.model.ImmutableList;
import com.google.firebase.crashlytics.internal.stacktrace.StackTraceTrimmingStrategy;
import com.google.firebase.crashlytics.internal.stacktrace.TrimmedThrowableData;
import com.jstyle.blesdk1860.constant.BleConst;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes10.dex */
public class CrashlyticsReportDataCapture {
    public static final Map<String, Integer> e;
    public static final String f;

    /* renamed from: a  reason: collision with root package name */
    public final Context f11135a;
    public final IdManager b;
    public final AppData c;
    public final StackTraceTrimmingStrategy d;

    static {
        HashMap hashMap = new HashMap();
        e = hashMap;
        hashMap.put("armeabi", 5);
        hashMap.put("armeabi-v7a", 6);
        hashMap.put("arm64-v8a", 9);
        hashMap.put("x86", 0);
        hashMap.put("x86_64", 1);
        f = String.format(Locale.US, "Crashlytics Android SDK/%s", BuildConfig.VERSION_NAME);
    }

    public CrashlyticsReportDataCapture(Context context, IdManager idManager, AppData appData, StackTraceTrimmingStrategy stackTraceTrimmingStrategy) {
        this.f11135a = context;
        this.b = idManager;
        this.c = appData;
        this.d = stackTraceTrimmingStrategy;
    }

    public static int b() {
        Integer num;
        String str = Build.CPU_ABI;
        if (TextUtils.isEmpty(str) || (num = e.get(str.toLowerCase(Locale.US))) == null) {
            return 7;
        }
        return num.intValue();
    }

    public final CrashlyticsReport.Builder a() {
        return CrashlyticsReport.builder().setSdkVersion(BuildConfig.VERSION_NAME).setGmpAppId(this.c.googleAppId).setInstallationUuid(this.b.getCrashlyticsInstallId()).setBuildVersion(this.c.versionCode).setDisplayVersion(this.c.versionName).setPlatform(4);
    }

    public final CrashlyticsReport.Session.Event.Application.Execution.BinaryImage c() {
        return CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.builder().setBaseAddress(0L).setSize(0L).setName(this.c.packageName).setUuid(this.c.buildId).build();
    }

    public CrashlyticsReport.Session.Event captureAnrEventData(CrashlyticsReport.ApplicationExitInfo applicationExitInfo) {
        int i = this.f11135a.getResources().getConfiguration().orientation;
        return CrashlyticsReport.Session.Event.builder().setType("anr").setTimestamp(applicationExitInfo.getTimestamp()).setApp(e(i, applicationExitInfo)).setDevice(g(i)).build();
    }

    public CrashlyticsReport.Session.Event captureEventData(Throwable th, Thread thread, String str, long j, int i, int i2, boolean z) {
        int i3 = this.f11135a.getResources().getConfiguration().orientation;
        return CrashlyticsReport.Session.Event.builder().setType(str).setTimestamp(j).setApp(f(i3, new TrimmedThrowableData(th, this.d), thread, i, i2, z)).setDevice(g(i3)).build();
    }

    public CrashlyticsReport captureReportData(String str, long j) {
        return a().setSession(o(str, j)).build();
    }

    public final ImmutableList<CrashlyticsReport.Session.Event.Application.Execution.BinaryImage> d() {
        return ImmutableList.from(c());
    }

    public final CrashlyticsReport.Session.Event.Application e(int i, CrashlyticsReport.ApplicationExitInfo applicationExitInfo) {
        return CrashlyticsReport.Session.Event.Application.builder().setBackground(Boolean.valueOf(applicationExitInfo.getImportance() != 100)).setUiOrientation(i).setExecution(j(applicationExitInfo)).build();
    }

    public final CrashlyticsReport.Session.Event.Application f(int i, TrimmedThrowableData trimmedThrowableData, Thread thread, int i2, int i3, boolean z) {
        Boolean bool;
        ActivityManager.RunningAppProcessInfo appProcessInfo = CommonUtils.getAppProcessInfo(this.c.packageName, this.f11135a);
        if (appProcessInfo != null) {
            bool = Boolean.valueOf(appProcessInfo.importance != 100);
        } else {
            bool = null;
        }
        return CrashlyticsReport.Session.Event.Application.builder().setBackground(bool).setUiOrientation(i).setExecution(k(trimmedThrowableData, thread, i2, i3, z)).build();
    }

    public final CrashlyticsReport.Session.Event.Device g(int i) {
        b a2 = b.a(this.f11135a);
        Float b = a2.b();
        Double valueOf = b != null ? Double.valueOf(b.doubleValue()) : null;
        int c = a2.c();
        boolean proximitySensorEnabled = CommonUtils.getProximitySensorEnabled(this.f11135a);
        return CrashlyticsReport.Session.Event.Device.builder().setBatteryLevel(valueOf).setBatteryVelocity(c).setProximityOn(proximitySensorEnabled).setOrientation(i).setRamUsed(CommonUtils.getTotalRamInBytes() - CommonUtils.calculateFreeRamInBytes(this.f11135a)).setDiskUsed(CommonUtils.calculateUsedDiskSpaceInBytes(Environment.getDataDirectory().getPath())).build();
    }

    public final CrashlyticsReport.Session.Event.Application.Execution.Exception h(TrimmedThrowableData trimmedThrowableData, int i, int i2) {
        return i(trimmedThrowableData, i, i2, 0);
    }

    public final CrashlyticsReport.Session.Event.Application.Execution.Exception i(TrimmedThrowableData trimmedThrowableData, int i, int i2, int i3) {
        String str = trimmedThrowableData.className;
        String str2 = trimmedThrowableData.localizedMessage;
        StackTraceElement[] stackTraceElementArr = trimmedThrowableData.stacktrace;
        int i4 = 0;
        if (stackTraceElementArr == null) {
            stackTraceElementArr = new StackTraceElement[0];
        }
        TrimmedThrowableData trimmedThrowableData2 = trimmedThrowableData.cause;
        if (i3 >= i2) {
            TrimmedThrowableData trimmedThrowableData3 = trimmedThrowableData2;
            while (trimmedThrowableData3 != null) {
                trimmedThrowableData3 = trimmedThrowableData3.cause;
                i4++;
            }
        }
        CrashlyticsReport.Session.Event.Application.Execution.Exception.Builder overflowCount = CrashlyticsReport.Session.Event.Application.Execution.Exception.builder().setType(str).setReason(str2).setFrames(ImmutableList.from(m(stackTraceElementArr, i))).setOverflowCount(i4);
        if (trimmedThrowableData2 != null && i4 == 0) {
            overflowCount.setCausedBy(i(trimmedThrowableData2, i, i2, i3 + 1));
        }
        return overflowCount.build();
    }

    public final CrashlyticsReport.Session.Event.Application.Execution j(CrashlyticsReport.ApplicationExitInfo applicationExitInfo) {
        return CrashlyticsReport.Session.Event.Application.Execution.builder().setAppExitInfo(applicationExitInfo).setSignal(r()).setBinaries(d()).build();
    }

    public final CrashlyticsReport.Session.Event.Application.Execution k(TrimmedThrowableData trimmedThrowableData, Thread thread, int i, int i2, boolean z) {
        return CrashlyticsReport.Session.Event.Application.Execution.builder().setThreads(u(trimmedThrowableData, thread, i, z)).setException(h(trimmedThrowableData, i, i2)).setSignal(r()).setBinaries(d()).build();
    }

    public final CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame l(StackTraceElement stackTraceElement, CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.Builder builder) {
        long j = 0;
        long max = stackTraceElement.isNativeMethod() ? Math.max(stackTraceElement.getLineNumber(), 0L) : 0L;
        String str = stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName();
        String fileName = stackTraceElement.getFileName();
        if (!stackTraceElement.isNativeMethod() && stackTraceElement.getLineNumber() > 0) {
            j = stackTraceElement.getLineNumber();
        }
        return builder.setPc(max).setSymbol(str).setFile(fileName).setOffset(j).build();
    }

    public final ImmutableList<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> m(StackTraceElement[] stackTraceElementArr, int i) {
        ArrayList arrayList = new ArrayList();
        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
            arrayList.add(l(stackTraceElement, CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.builder().setImportance(i)));
        }
        return ImmutableList.from(arrayList);
    }

    public final CrashlyticsReport.Session.Application n() {
        CrashlyticsReport.Session.Application.Builder installationUuid = CrashlyticsReport.Session.Application.builder().setIdentifier(this.b.getAppIdentifier()).setVersion(this.c.versionCode).setDisplayVersion(this.c.versionName).setInstallationUuid(this.b.getCrashlyticsInstallId());
        String unityVersion = this.c.unityVersionProvider.getUnityVersion();
        if (unityVersion != null) {
            installationUuid.setDevelopmentPlatform(CrashlyticsReport.DEVELOPMENT_PLATFORM_UNITY).setDevelopmentPlatformVersion(unityVersion);
        }
        return installationUuid.build();
    }

    public final CrashlyticsReport.Session o(String str, long j) {
        return CrashlyticsReport.Session.builder().setStartedAt(j).setIdentifier(str).setGenerator(f).setApp(n()).setOs(q()).setDevice(p()).setGeneratorType(3).build();
    }

    public final CrashlyticsReport.Session.Device p() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        int b = b();
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        long totalRamInBytes = CommonUtils.getTotalRamInBytes();
        long blockCount = statFs.getBlockCount() * statFs.getBlockSize();
        boolean isEmulator = CommonUtils.isEmulator(this.f11135a);
        int deviceState = CommonUtils.getDeviceState(this.f11135a);
        String str = Build.MANUFACTURER;
        return CrashlyticsReport.Session.Device.builder().setArch(b).setModel(Build.MODEL).setCores(availableProcessors).setRam(totalRamInBytes).setDiskSpace(blockCount).setSimulator(isEmulator).setState(deviceState).setManufacturer(str).setModelClass(Build.PRODUCT).build();
    }

    public final CrashlyticsReport.Session.OperatingSystem q() {
        return CrashlyticsReport.Session.OperatingSystem.builder().setPlatform(3).setVersion(Build.VERSION.RELEASE).setBuildVersion(Build.VERSION.CODENAME).setJailbroken(CommonUtils.isRooted(this.f11135a)).build();
    }

    public final CrashlyticsReport.Session.Event.Application.Execution.Signal r() {
        return CrashlyticsReport.Session.Event.Application.Execution.Signal.builder().setName(BleConst.GetDeviceTime).setCode(BleConst.GetDeviceTime).setAddress(0L).build();
    }

    public final CrashlyticsReport.Session.Event.Application.Execution.Thread s(Thread thread, StackTraceElement[] stackTraceElementArr) {
        return t(thread, stackTraceElementArr, 0);
    }

    public final CrashlyticsReport.Session.Event.Application.Execution.Thread t(Thread thread, StackTraceElement[] stackTraceElementArr, int i) {
        return CrashlyticsReport.Session.Event.Application.Execution.Thread.builder().setName(thread.getName()).setImportance(i).setFrames(ImmutableList.from(m(stackTraceElementArr, i))).build();
    }

    public final ImmutableList<CrashlyticsReport.Session.Event.Application.Execution.Thread> u(TrimmedThrowableData trimmedThrowableData, Thread thread, int i, boolean z) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(t(thread, trimmedThrowableData.stacktrace, i));
        if (z) {
            for (Map.Entry<Thread, StackTraceElement[]> entry : Thread.getAllStackTraces().entrySet()) {
                Thread key = entry.getKey();
                if (!key.equals(thread)) {
                    arrayList.add(s(key, this.d.getTrimmedStackTrace(entry.getValue())));
                }
            }
        }
        return ImmutableList.from(arrayList);
    }
}
