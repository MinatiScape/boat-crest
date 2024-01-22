package com.google.zxing.oned;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.coveiot.utils.utility.ecg.EcgStyleReportUtil;
import com.goodix.ble.libble.v2.profile.BatteryService;
import com.google.zxing.client.result.ExpandedProductParsedResult;
import com.realsil.sdk.dfu.utils.DfuAdapter;
import com.sifli.ezip.NeuQuant;
import com.touchgui.sdk.TGEventListener;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public final List<int[]> f11828a = new ArrayList();
    public final List<String> b = new ArrayList();

    public final void a(int[] iArr, String str) {
        this.f11828a.add(iArr);
        this.b.add(str);
    }

    public final synchronized void b() {
        if (this.f11828a.isEmpty()) {
            a(new int[]{0, 19}, "US/CA");
            a(new int[]{30, 39}, "US");
            a(new int[]{60, 139}, "US/CA");
            a(new int[]{300, 379}, "FR");
            a(new int[]{380}, "BG");
            a(new int[]{383}, "SI");
            a(new int[]{385}, EcgStyleReportUtil.UserInfoKey.HR);
            a(new int[]{387}, "BA");
            a(new int[]{400, 440}, "DE");
            a(new int[]{450, 459}, "JP");
            a(new int[]{460, 469}, "RU");
            a(new int[]{471}, "TW");
            a(new int[]{474}, "EE");
            a(new int[]{475}, "LV");
            a(new int[]{476}, "AZ");
            a(new int[]{477}, "LT");
            a(new int[]{478}, "UZ");
            a(new int[]{479}, "LK");
            a(new int[]{480}, "PH");
            a(new int[]{481}, "BY");
            a(new int[]{482}, "UA");
            a(new int[]{484}, "MD");
            a(new int[]{485}, "AM");
            a(new int[]{486}, "GE");
            a(new int[]{NeuQuant.prime3}, "KZ");
            a(new int[]{489}, "HK");
            a(new int[]{490, NeuQuant.prime1}, "JP");
            a(new int[]{500, 509}, "GB");
            a(new int[]{520}, "GR");
            a(new int[]{528}, ExpandedProductParsedResult.POUND);
            a(new int[]{529}, "CY");
            a(new int[]{531}, "MK");
            a(new int[]{DfuAdapter.STATE_PREPARE_CONNECTING}, "MT");
            a(new int[]{DfuAdapter.STATE_READ_DEVICE_INFO}, "IE");
            a(new int[]{DfuAdapter.STATE_READ_PROTOCOL_TYPE, 549}, "BE/LU");
            a(new int[]{com.veryfit.multi.nativeprotocol.b.e2}, "PT");
            a(new int[]{569}, "IS");
            a(new int[]{com.veryfit.multi.nativeprotocol.b.k2, 579}, "DK");
            a(new int[]{590}, "PL");
            a(new int[]{594}, "RO");
            a(new int[]{599}, "HU");
            a(new int[]{600, 601}, "ZA");
            a(new int[]{603}, "GH");
            a(new int[]{608}, "BH");
            a(new int[]{609}, "MU");
            a(new int[]{611}, "MA");
            a(new int[]{com.veryfit.multi.nativeprotocol.b.I2}, "DZ");
            a(new int[]{616}, "KE");
            a(new int[]{com.veryfit.multi.nativeprotocol.b.L2}, "CI");
            a(new int[]{619}, "TN");
            a(new int[]{com.veryfit.multi.nativeprotocol.b.N2}, "SY");
            a(new int[]{com.veryfit.multi.nativeprotocol.b.O2}, "EG");
            a(new int[]{com.veryfit.multi.nativeprotocol.b.Q2}, "LY");
            a(new int[]{com.veryfit.multi.nativeprotocol.b.R2}, "JO");
            a(new int[]{com.veryfit.multi.nativeprotocol.b.S2}, "IR");
            a(new int[]{com.veryfit.multi.nativeprotocol.b.T2}, "KW");
            a(new int[]{com.veryfit.multi.nativeprotocol.b.U2}, "SA");
            a(new int[]{com.veryfit.multi.nativeprotocol.b.V2}, "AE");
            a(new int[]{640, 649}, "FI");
            a(new int[]{690, 695}, "CN");
            a(new int[]{TypedValues.TransitionType.TYPE_DURATION, 709}, "NO");
            a(new int[]{729}, "IL");
            a(new int[]{730, 739}, "SE");
            a(new int[]{740}, "GT");
            a(new int[]{741}, "SV");
            a(new int[]{742}, "HN");
            a(new int[]{743}, "NI");
            a(new int[]{744}, "CR");
            a(new int[]{BatteryService.EVT_BATTERY_UPDATE}, "PA");
            a(new int[]{746}, "DO");
            a(new int[]{750}, "MX");
            a(new int[]{754, 755}, "CA");
            a(new int[]{759}, "VE");
            a(new int[]{760, TGEventListener.ALARM_UPDATED}, "CH");
            a(new int[]{TGEventListener.REQUEST_UPDATE_WEATHER}, "CO");
            a(new int[]{773}, "UY");
            a(new int[]{775}, "PE");
            a(new int[]{777}, "BO");
            a(new int[]{779}, "AR");
            a(new int[]{780}, "CL");
            a(new int[]{784}, "PY");
            a(new int[]{785}, "PE");
            a(new int[]{786}, "EC");
            a(new int[]{789, 790}, "BR");
            a(new int[]{800, 839}, "IT");
            a(new int[]{840, 849}, "ES");
            a(new int[]{850}, "CU");
            a(new int[]{858}, "SK");
            a(new int[]{859}, "CZ");
            a(new int[]{860}, "YU");
            a(new int[]{865}, "MN");
            a(new int[]{867}, "KP");
            a(new int[]{868, 869}, "TR");
            a(new int[]{870, 879}, "NL");
            a(new int[]{880}, "KR");
            a(new int[]{885}, "TH");
            a(new int[]{888}, "SG");
            a(new int[]{890}, "IN");
            a(new int[]{893}, "VN");
            a(new int[]{896}, "PK");
            a(new int[]{899}, "ID");
            a(new int[]{TypedValues.Custom.TYPE_INT, 919}, "AT");
            a(new int[]{930, 939}, "AU");
            a(new int[]{940, 949}, "AZ");
            a(new int[]{955}, "MY");
            a(new int[]{958}, "MO");
        }
    }

    public String c(String str) {
        int[] iArr;
        int i;
        b();
        int parseInt = Integer.parseInt(str.substring(0, 3));
        int size = this.f11828a.size();
        for (int i2 = 0; i2 < size && parseInt >= (i = (iArr = this.f11828a.get(i2))[0]); i2++) {
            if (iArr.length != 1) {
                i = iArr[1];
            }
            if (parseInt <= i) {
                return this.b.get(i2);
            }
        }
        return null;
    }
}
