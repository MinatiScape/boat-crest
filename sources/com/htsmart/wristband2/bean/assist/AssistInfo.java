package com.htsmart.wristband2.bean.assist;

import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public class AssistInfo {
    public int antennaSignalStrength;
    public int bloodPressureAlgorithm;
    public int caloriesAlgorithm;
    public int caloriesOptimizeSportVersion;
    public ChargingInfo chargingInfo;
    public int ecgSwitch;
    public int ecgType;
    public int flashCodeSize;
    public int flashSize;
    public int fontType;
    public int gsensorType;
    public HeartRateInfo heartRateInfo;
    public boolean isCaloriesOptimizeSport;
    public boolean isSleepSupportMultiSegment;
    public boolean isSupportHeartRate;
    public boolean isSupportLowBatteryReminder;
    public boolean isSupportRebindRestoreData;
    public boolean isSupportWeChat;
    public int raiseWristAlgorithm;
    public ScreenInfo screenInfo;
    public int screenTPHotSwap;
    public int sleepAlgorithm;
    public int stepAlgorithm;
    public String stepAlgorithmVersion;
    public int temperatureAlgorithm;
    public int temperatureSwitch;
    public int temperatureType;
    public TPInfo tpInfo;
    public TranslationVersion translationVersion;

    /* loaded from: classes11.dex */
    public static class ChargingInfo {
        public int batteryCurrentPercentage;
        public int batteryCurrentVoltage;
        public int batteryMaxVoltage;
        public int curveType;
        public int logicalVersion;
        public int type;
    }

    /* loaded from: classes11.dex */
    public static class HeartRateInfo {
        public String algorithmVersion;
        public boolean isSupportHotSwap;
        public boolean isSupportInterruptPin;
        public int switchType;
        public int type;
    }

    /* loaded from: classes11.dex */
    public static class ScreenInfo {
        public int defaultBrightScreenTime;
        public int defaultBrightness;
        public int defaultRaiseWristBrightTime;
        public int driveNum;
        public boolean isSupportTE;
        public int rdPinMultiplexed;
        public int type;
    }

    /* loaded from: classes11.dex */
    public static class TPInfo {
        public String firmwareVersion;
        public boolean isCOB;
        public boolean isSupportRD;
        public boolean isSupportTestPage;
        public boolean isSupportTurnOn;
        public boolean isSupportUpgrade;
        public boolean isSupportWakeup;
        public int type;
    }

    /* loaded from: classes11.dex */
    public static class TranslationVersion {
        public String compileTime;
        public int customerNum;
        public int customerTranslationNum;
        public int translationVersion;
    }

    @NonNull
    public String toString() {
        String str;
        String str2;
        StringBuilder sb = new StringBuilder();
        sb.append("0x01 翻译版本信息:\n");
        if (this.translationVersion == null) {
            sb.append("    --");
        } else {
            sb.append("    客户编号:");
            sb.append(this.translationVersion.customerNum);
            sb.append("\n");
            sb.append("    客户翻译编号:");
            sb.append(this.translationVersion.customerTranslationNum);
            sb.append("\n");
            sb.append("    翻译版本号:");
            sb.append(this.translationVersion.translationVersion);
            sb.append("\n");
            sb.append("    编译时间序号:");
            sb.append(this.translationVersion.compileTime);
        }
        sb.append("\n");
        sb.append("\n");
        sb.append("0x02 Flash信息:\n");
        int i = this.flashSize;
        String str3 = "--";
        String str4 = i != 1 ? i != 2 ? i != 3 ? i != 4 ? "--" : "256M" : "128M" : "64M" : "32M";
        int i2 = this.flashCodeSize;
        String str5 = i2 != 1 ? i2 != 2 ? i2 != 3 ? "--" : "1M" : "500KB" : "300KB";
        sb.append("    flash大小:");
        sb.append(str4);
        sb.append("\n");
        sb.append("    代码空间大小:");
        sb.append(str5);
        sb.append("\n");
        sb.append("\n");
        sb.append("0x03 字库信息:\n");
        int i3 = this.fontType;
        String str6 = i3 != 1 ? i3 != 2 ? i3 != 3 ? i3 != 4 ? "--" : "7号字库" : "6号字库" : "5号字库" : "3号字库";
        sb.append("    字库类型:");
        sb.append(str6);
        sb.append("\n");
        sb.append("\n");
        sb.append("0x04 TP信息:\n");
        TPInfo tPInfo = this.tpInfo;
        if (tPInfo == null) {
            sb.append("    --");
        } else {
            int i4 = tPInfo.type;
            String str7 = i4 != 1 ? i4 != 2 ? i4 != 3 ? "--" : "海栎创716" : "海栎创816S" : "新联阳7259E";
            sb.append("    TP型号:");
            sb.append(str7);
            sb.append("\n");
            sb.append("    是否COB:");
            sb.append(this.tpInfo.isCOB ? "Y" : "N");
            sb.append("\n");
            sb.append("    TP是否可以开机:");
            sb.append(this.tpInfo.isSupportTurnOn ? "Y" : "N");
            sb.append("\n");
            sb.append("    TP是否可以唤醒:");
            sb.append(this.tpInfo.isSupportWakeup ? "Y" : "N");
            sb.append("\n");
            sb.append("    TP是否打开升级:");
            sb.append(this.tpInfo.isSupportUpgrade ? "Y" : "N");
            sb.append("\n");
            sb.append("    手环TP测试页面:");
            sb.append(this.tpInfo.isSupportTestPage ? "Y" : "N");
            sb.append("\n");
            sb.append("    TP复位脚复用屏幕RD:");
            sb.append(this.tpInfo.isSupportRD ? "Y" : "N");
            sb.append("\n");
            sb.append("    TP固件版本:");
            sb.append(this.tpInfo.firmwareVersion);
        }
        sb.append("\n");
        sb.append("\n");
        sb.append("0x05 计步算法信息:\n");
        int i5 = this.stepAlgorithm;
        String str8 = i5 != 1 ? i5 != 2 ? "--" : "YC计步算法" : "陈工算法";
        sb.append("    计步算法类型:");
        sb.append(str8);
        sb.append("\n");
        sb.append("    计步算法库版本:");
        sb.append(this.stepAlgorithmVersion);
        sb.append("\n");
        sb.append("\n");
        sb.append("0x06 抬腕算法信息:\n");
        int i6 = this.raiseWristAlgorithm;
        String str9 = i6 != 1 ? i6 != 2 ? "--" : "YC抬腕算法" : "拓步抬腕算法";
        sb.append("    抬腕算法类型:");
        sb.append(str9);
        sb.append("\n");
        sb.append("\n");
        sb.append("0x07 卡路里算法信息:\n");
        int i7 = this.caloriesAlgorithm;
        String str10 = i7 != 1 ? i7 != 2 ? "--" : "YC算法库距离卡路里算法" : "拓步卡路里距离算法";
        sb.append("    卡路里算法类型:");
        sb.append(str10);
        sb.append("\n");
        sb.append("    运动模式优化卡路里:");
        sb.append(this.isCaloriesOptimizeSport ? "Y" : "N");
        sb.append("\n");
        sb.append("    运动模式优化卡路里算法版本:");
        sb.append(this.caloriesOptimizeSportVersion);
        sb.append("\n");
        sb.append("\n");
        sb.append("0x08 睡眠算法信息:\n");
        int i8 = this.sleepAlgorithm;
        String str11 = i8 != 1 ? i8 != 2 ? "--" : "YC睡眠算法" : "拓步睡眠算法";
        sb.append("    睡眠算法类型:");
        sb.append(str11);
        sb.append("\n");
        sb.append("    睡眠支持多段叠加:");
        sb.append(this.isSleepSupportMultiSegment ? "Y" : "N");
        sb.append("\n");
        sb.append("\n");
        sb.append("0x09 心率信息:\n");
        HeartRateInfo heartRateInfo = this.heartRateInfo;
        if (heartRateInfo == null) {
            sb.append("    --");
        } else {
            int i9 = heartRateInfo.type;
            if (i9 == 1) {
                str = "em7028/em7029/em1029";
            } else if (i9 == 33) {
                str = "bd1662";
            } else if (i9 != 49) {
                switch (i9) {
                    case 17:
                        str = "hrs3300/hrs3301";
                        break;
                    case 18:
                        str = "hrs3313";
                        break;
                    case 19:
                        str = "hrs3313lp";
                        break;
                    case 20:
                        str = "hrs3600";
                        break;
                    case 21:
                        str = "hrs3603";
                        break;
                    default:
                        str = "--";
                        break;
                }
            } else {
                str = "vc31";
            }
            sb.append("    心率型号:");
            sb.append(str);
            sb.append("\n");
            sb.append("    心率算法库版本:");
            sb.append(this.heartRateInfo.algorithmVersion);
            sb.append("\n");
            int i10 = this.heartRateInfo.switchType;
            String str12 = i10 != 0 ? i10 != 1 ? i10 != 2 ? "--" : "有且复用RD 脚" : "有" : "无";
            sb.append("    是否有心率开关:");
            sb.append(str12);
            sb.append("\n");
            sb.append("    是否有中断脚:");
            sb.append(this.heartRateInfo.isSupportInterruptPin ? "Y" : "N");
            sb.append("\n");
            sb.append("    心率热插拔:");
            sb.append(this.heartRateInfo.isSupportHotSwap ? "Y" : "N");
        }
        sb.append("\n");
        sb.append("\n");
        sb.append("0x0A gsensor信息:\n");
        int i11 = this.gsensorType;
        String str13 = i11 != 1 ? i11 != 2 ? i11 != 3 ? i11 != 4 ? i11 != 5 ? "--" : "sc7a20" : "bma253" : "lis2dh" : "kx1020" : "stk8321";
        sb.append("    gsensor类型:");
        sb.append(str13);
        sb.append("\n");
        sb.append("\n");
        sb.append("0x0B 充电信息:\n");
        ChargingInfo chargingInfo = this.chargingInfo;
        if (chargingInfo == null) {
            sb.append("    --");
            sb.append("\n");
        } else {
            switch (chargingInfo.type) {
                case 1:
                    str2 = "正常一个充电状态脚";
                    break;
                case 2:
                    str2 = "正常一充电状态脚+5V状态脚";
                    break;
                case 3:
                    str2 = "LTC充电方式";
                    break;
                case 4:
                    str2 = "圣邦微40561两个脚充电方式";
                    break;
                case 5:
                    str2 = "NJY无线充电方式";
                    break;
                case 6:
                    str2 = "XC5015两个管脚充电方式";
                    break;
                default:
                    str2 = "--";
                    break;
            }
            sb.append("    充电类型:");
            sb.append(str2);
            sb.append("\n");
            sb.append("    充电逻辑版本:");
            sb.append(this.chargingInfo.logicalVersion);
            sb.append("\n");
            int i12 = this.chargingInfo.batteryMaxVoltage;
            String str14 = i12 != 0 ? i12 != 1 ? "--" : "4.35V" : "4.2V";
            sb.append("    电池满电电压:");
            sb.append(str14);
            sb.append("\n");
            int i13 = this.chargingInfo.curveType;
            String str15 = i13 != 0 ? i13 != 1 ? "--" : "HSD曲线" : "拓步标准曲线";
            sb.append("    充电曲线:");
            sb.append(str15);
            sb.append("\n");
            sb.append("    电池当前电压:");
            sb.append(this.chargingInfo.batteryCurrentVoltage);
            sb.append("mv\n");
            sb.append("    电池当前电量百分:");
            sb.append(this.chargingInfo.batteryCurrentPercentage);
            sb.append("%\n");
        }
        sb.append("\n");
        sb.append("0x0C 屏幕TP热插拔信息:\n");
        int i14 = this.screenTPHotSwap;
        String str16 = i14 != 0 ? i14 != 1 ? i14 != 2 ? "--" : "充电时热插拔复位;(只有充电时复位屏幕初始化)" : "支持;(每次亮屏都复位屏幕初始化)" : "不支持";
        sb.append("    屏幕TP热插拔:");
        sb.append(str16);
        sb.append("\n");
        sb.append("\n");
        sb.append("0x0D 血压算法信息:\n");
        int i15 = this.bloodPressureAlgorithm;
        String str17 = i15 != 0 ? i15 != 1 ? "--" : "CK18血压算法" : "拓步标准血压算法";
        sb.append("    血压算法类型:");
        sb.append(str17);
        sb.append("\n");
        sb.append("\n");
        sb.append("0x0E 天线信号强度信息:\n");
        int i16 = this.antennaSignalStrength;
        String str18 = i16 != 0 ? i16 != 1 ? i16 != 2 ? "--" : "7.5dB" : "4dB" : "0dB";
        sb.append("    天线信号强度:");
        sb.append(str18);
        sb.append("\n");
        sb.append("\n");
        sb.append("0x0F 屏幕信息:\n");
        if (this.screenInfo == null) {
            sb.append("    --");
        } else {
            sb.append("    屏幕型号:");
            sb.append(this.screenInfo.type);
            sb.append("\n");
            sb.append("    屏幕驱动编号:");
            sb.append(this.screenInfo.driveNum);
            sb.append("\n");
            sb.append("    屏幕是否使能TE信号:");
            sb.append(this.screenInfo.isSupportTE ? "Y" : "N");
            sb.append("\n");
            int i17 = this.screenInfo.rdPinMultiplexed;
            String str19 = i17 != 0 ? i17 != 1 ? i17 != 2 ? "--" : "复用为TP复位脚" : "复用为心率开关脚" : "否";
            sb.append("    并口屏幕RD脚是否复用:");
            sb.append(str19);
            sb.append("\n");
            sb.append("    默认亮度:");
            sb.append(this.screenInfo.defaultBrightness);
            sb.append("\n");
            sb.append("    默认亮屏时间:");
            sb.append(this.screenInfo.defaultBrightScreenTime);
            sb.append("\n");
            sb.append("    默认翻腕亮屏时间:");
            sb.append(this.screenInfo.defaultRaiseWristBrightTime);
        }
        sb.append("\n");
        sb.append("\n");
        sb.append("0x10 体温信息:\n");
        int i18 = this.temperatureType;
        String str20 = i18 != 0 ? i18 != 1 ? i18 != 2 ? "--" : "金开盛模拟ADC体温" : "ct1711" : "无体温";
        sb.append("    体温型号:");
        sb.append(str20);
        sb.append("\n");
        int i19 = this.temperatureAlgorithm;
        String str21 = i19 != 0 ? i19 != 1 ? i19 != 2 ? "--" : "JY体温定制算法" : "公版" : "未知";
        sb.append("    体温算法:");
        sb.append(str21);
        sb.append("\n");
        int i20 = this.temperatureSwitch;
        String str22 = i20 != 0 ? i20 != 1 ? i20 != 2 ? "--" : "跟心率共用一个开关" : "带独立开关" : "否";
        sb.append("    体温硬件开关:");
        sb.append(str22);
        sb.append("\n");
        sb.append("\n");
        sb.append("0x11 心电信息:\n");
        int i21 = this.ecgType;
        String str23 = i21 != 0 ? i21 != 1 ? i21 != 2 ? i21 != 3 ? i21 != 4 ? "--" : "TI4900" : "TI1291" : "J心电" : "技通" : "无心电";
        sb.append("    心电类型:");
        sb.append(str23);
        sb.append("\n");
        int i22 = this.ecgSwitch;
        if (i22 == 0) {
            str3 = "否";
        } else if (i22 == 1) {
            str3 = "带独立开关";
        } else if (i22 == 2) {
            str3 = "跟心率共用一个开关";
        }
        sb.append("    心电硬件开关:");
        sb.append(str3);
        sb.append("\n");
        sb.append("\n");
        sb.append("0x12 微信协议信息:\n");
        sb.append("    微信协议:");
        sb.append(this.isSupportWeChat ? "Y" : "N");
        sb.append("\n");
        sb.append("\n");
        sb.append("0x13 心率带协议信息:\n");
        sb.append("    心率带协议:");
        sb.append(this.isSupportHeartRate ? "Y" : "N");
        sb.append("\n");
        sb.append("\n");
        sb.append("0x14 重绑清数据信息:\n");
        sb.append("    重绑清数据:");
        sb.append(this.isSupportRebindRestoreData ? "N" : "Y");
        sb.append("\n");
        sb.append("\n");
        sb.append("0x15 低电提醒信息:\n");
        sb.append("    低电提醒:");
        sb.append(this.isSupportLowBatteryReminder ? "Y" : "N");
        sb.append("\n");
        sb.append("\n");
        return sb.toString();
    }
}
