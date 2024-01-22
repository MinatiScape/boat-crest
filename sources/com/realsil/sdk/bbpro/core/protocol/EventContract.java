package com.realsil.sdk.bbpro.core.protocol;

import com.realsil.sdk.bbpro.core.transportlayer.EventPacket;
import com.realsil.sdk.core.utility.DataConverter;
/* loaded from: classes12.dex */
public class EventContract {
    public static final short EVENT_ACK = 0;
    public static final short EVENT_INFO_REQ = 17;
    public static final int EVENT_LE_ADDR = 260;
    public static final int EVENT_REPORT_CFG_SETTINGS = 24;
    public static final int EVENT_REPORT_NAME = 24;
    public static final int EVENT_REPORT_PROMPT_LANGUAGE = 23;
    public static final int EVENT_REPORT_STATUS = 25;

    /* loaded from: classes12.dex */
    public static class ReportName extends EventPacket {
        public int b;
        public byte[] name;
        public byte type;

        public static ReportName builder(byte[] bArr) {
            ReportName reportName = new ReportName();
            if (reportName.parse(bArr)) {
                return reportName;
            }
            return null;
        }

        @Override // com.realsil.sdk.bbpro.core.transportlayer.EventPacket
        public boolean parse(byte[] bArr) {
            if (super.parse(bArr)) {
                int i = this.paramsLen;
                if (i > 0) {
                    this.type = this.mEventParams[0];
                }
                if (i > 1) {
                    this.b = this.mEventParams[1] & 255;
                }
                int i2 = this.b;
                if (i >= i2 + 2) {
                    byte[] bArr2 = new byte[i2];
                    this.name = bArr2;
                    System.arraycopy(this.mEventParams, 2, bArr2, 0, i2);
                }
                return true;
            }
            return false;
        }

        @Override // com.realsil.sdk.bbpro.core.transportlayer.EventPacket
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("type=" + ((int) this.type));
            sb.append(",name=" + DataConverter.bytes2HexWithSeparate(this.name));
            return sb.toString();
        }
    }

    /* loaded from: classes12.dex */
    public static class ReportPromptLan extends EventPacket {
        public byte b;
        public byte c;

        public static ReportPromptLan builder(byte[] bArr) {
            ReportPromptLan reportPromptLan = new ReportPromptLan();
            if (reportPromptLan.parse(bArr)) {
                return reportPromptLan;
            }
            return null;
        }

        public byte getCurrentLanuage() {
            return this.b;
        }

        public byte getSupportedLanguage() {
            return this.c;
        }

        @Override // com.realsil.sdk.bbpro.core.transportlayer.EventPacket
        public boolean parse(byte[] bArr) {
            if (super.parse(bArr)) {
                int i = this.paramsLen;
                if (i > 0) {
                    this.b = this.mEventParams[0];
                }
                if (i > 1) {
                    this.c = this.mEventParams[1];
                }
                return true;
            }
            return false;
        }

        @Override // com.realsil.sdk.bbpro.core.transportlayer.EventPacket
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("currentLanuage=" + ((int) this.b));
            sb.append(",supportedLanguage=" + ((int) this.c));
            return sb.toString();
        }
    }
}
