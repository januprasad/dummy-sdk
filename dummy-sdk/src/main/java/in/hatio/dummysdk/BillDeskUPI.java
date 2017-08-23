package in.hatio.dummysdk;

import android.app.Application;


/**
 * Created by jenuprasad on 11/04/17.
 */

public class BillDeskUPI extends Application {


    private static BillDeskUPI context;
    private static String SHARED_PREFS = "billdesk_upi_app";

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

    }

    public static BillDeskUPI getContext() {
        return context;
    }



    public String get(String key, String defaultValue) {
        return getSharedPreferences(SHARED_PREFS, MODE_PRIVATE).getString(key, defaultValue);
    }

    public void set(String key, String value) {
        getSharedPreferences(SHARED_PREFS, MODE_PRIVATE).
                edit().
                putString(key, value).
                commit();
    }

    public boolean get(String key, boolean defaultValue) {
        return getSharedPreferences(SHARED_PREFS, MODE_PRIVATE).getBoolean(key, false);
    }

    public void set(String key, boolean value) {
        getSharedPreferences(SHARED_PREFS, MODE_PRIVATE).
                edit().
                putBoolean(key, value).
                commit();
    }


    public void setCustomerState(boolean b) {
        set("customer-state", b);
    }

    public boolean getCustomerState() {
        return get("customer-state", false);
    }
}
