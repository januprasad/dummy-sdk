package in.hatio.dummysdk;

import android.content.Context;
import android.provider.Settings;

/**
 * Created by jenuprasad on 13/04/17.
 */

public class DeviceParams {
    private String mobileNumber = "91" + getMobileNumber();
    //    private String mobileNumber = "918606434912";
    private String deviceId;
    private String appId = "billdesk";
    private String clientId, clientSecret;
    private boolean isTrusted;


    protected DeviceParams(Context c) {
    }

    public static String getDeviceId() {
        String deviceId = Settings.Secure.getString(
                BillDeskUPI.getContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return deviceId;
    }


    public String getAppId() {
        return appId;
    }


    public String getToken() {
        return BillDeskUPI.getContext().get("token", null);
    }

    public String getLk() {
        return BillDeskUPI.getContext().get("lk", "");
    }


    public void setLk(String body) {
        BillDeskUPI.getContext().set("lk", body);
    }

    public static String getOs() {
        return "android";
    }


    public void setClient(String clientId) {
        BillDeskUPI.getContext().set("clientId", clientId);
    }

    public void setSecret(String clientSecret) {
        BillDeskUPI.getContext().set("clientSecret", clientSecret);
    }

    public String getClient() {
        return BillDeskUPI.getContext().get("clientId", null);
    }

    public String getSecret() {
        return BillDeskUPI.getContext().get("clientSecret", null);
    }




    public boolean isMobileVerified() {
        return BillDeskUPI.getContext().get("mobile-numebr-verifeid", false);
    }

    public void setMobileVerified(boolean mobileVerified) {
        BillDeskUPI.getContext().set("mobile-numebr-verifeid", mobileVerified);
    }

    public boolean isTrusted() {
        return BillDeskUPI.getContext().get("merchant", false);
    }

    public void setTrusted(boolean trusted) {
        isTrusted = trusted;
        BillDeskUPI.getContext().set("merchant", trusted);
    }

    public String getMobileNumber() {
        return BillDeskUPI.getContext().get("mobile-number", null);
    }

    public void setMobileNumber(String mobileNumber) {
        BillDeskUPI.getContext().set("mobile-number", mobileNumber);
    }
}
