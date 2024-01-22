package com.google.zxing.client.result;

import com.goodix.ble.libcomx.util.HexStringBuilder;
/* loaded from: classes11.dex */
public final class GeoParsedResult extends ParsedResult {
    public final double b;
    public final double c;
    public final double d;
    public final String e;

    public GeoParsedResult(double d, double d2, double d3, String str) {
        super(ParsedResultType.GEO);
        this.b = d;
        this.c = d2;
        this.d = d3;
        this.e = str;
    }

    public double getAltitude() {
        return this.d;
    }

    @Override // com.google.zxing.client.result.ParsedResult
    public String getDisplayResult() {
        StringBuilder sb = new StringBuilder(20);
        sb.append(this.b);
        sb.append(", ");
        sb.append(this.c);
        if (this.d > 0.0d) {
            sb.append(", ");
            sb.append(this.d);
            sb.append('m');
        }
        if (this.e != null) {
            sb.append(" (");
            sb.append(this.e);
            sb.append(HexStringBuilder.COMMENT_END_CHAR);
        }
        return sb.toString();
    }

    public String getGeoURI() {
        StringBuilder sb = new StringBuilder();
        sb.append("geo:");
        sb.append(this.b);
        sb.append(',');
        sb.append(this.c);
        if (this.d > 0.0d) {
            sb.append(',');
            sb.append(this.d);
        }
        if (this.e != null) {
            sb.append(org.apache.commons.codec.net.a.SEP);
            sb.append(this.e);
        }
        return sb.toString();
    }

    public double getLatitude() {
        return this.b;
    }

    public double getLongitude() {
        return this.c;
    }

    public String getQuery() {
        return this.e;
    }
}
