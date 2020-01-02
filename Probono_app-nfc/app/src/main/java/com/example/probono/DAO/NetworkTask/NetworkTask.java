package com.example.probono.DAO.NetworkTask;

import android.os.AsyncTask;

public class NetworkTask extends AsyncTask<Void, Void, String> { //Params, Progress, Results

    NetworkCallee networkCallee;

    public NetworkTask(NetworkCallee networkCallee) {
        this.networkCallee = networkCallee;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String result = networkCallee.doinBackground();
        return result;
    }

    @Override
    protected void onPostExecute(String s) { //받아올 결과
        super.onPostExecute(s);
        networkCallee.onPostExecute(s);
    }


    public interface NetworkCallee {
        public String doinBackground();
        public void onPostExecute(String s);
    }


}
