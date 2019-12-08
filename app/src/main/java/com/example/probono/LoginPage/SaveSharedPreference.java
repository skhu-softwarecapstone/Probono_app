package com.example.probono.LoginPage;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveSharedPreference {

        static final String PREF_USER_NAME = "thebom_saved_username";
        static final String PREF_USER_PASSWORD = "thebom_saved_userpassword";

        static SharedPreferences getSharedPreferences(Context ctx) {
            return PreferenceManager.getDefaultSharedPreferences(ctx);
        }

        // 계정 정보 저장
        public static void setUserName(Context ctx, String userName) {
            SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
            editor.putString(PREF_USER_NAME, userName);
            editor.commit();
        }

        public static void setUserPassword(Context ctx, String userPass) {
            SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
            editor.putString(PREF_USER_PASSWORD, userPass);
            editor.commit();
        }

        // 저장된 정보 가져오기
        public static String getUserName(Context ctx) {
            return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
        }

        public static String getUserPassword(Context ctx) {
            return getSharedPreferences(ctx).getString(PREF_USER_PASSWORD, "");
        }

        // 로그아웃
        public static void clearUserName(Context ctx) {
            SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
            editor.clear();
            editor.commit();
        }


}
