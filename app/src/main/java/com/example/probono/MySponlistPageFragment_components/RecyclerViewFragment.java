package com.example.probono.MySponlistPageFragment_components;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.probono.DAO.NetworkTask.MySponNetworkTaskHandler;
import com.example.probono.DAO.NetworkTask.NetworkTask;
import com.example.probono.DAO.NetworkTask.SponNetworkTaskHandler;
import com.example.probono.DTO.Movie;
import com.example.probono.MainActivity;
import com.example.probono.R;

import java.util.ArrayList;

public class RecyclerViewFragment extends Fragment {

    // 받아올 image Resource id
    private String url;
    private RecyclerView recyclerView;
    private MainActivity activity;
    private String userid;



    // url 초기화
    public RecyclerViewFragment(String url) {this.url = url;}

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (MainActivity) getActivity();
        //        //메소드가 호출될 때, 프래그먼트가 액티비티 위에 올라와있다.
        //getActivity Method로 참조가능


    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_sponlist_recyclerviewpager, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.my_sponlist_recyclerView);

        System.out.println(url);
        MySponNetworkTaskHandler networkTaskHandler = new MySponNetworkTaskHandler(url, null, recyclerView, view);
        NetworkTask networkTask = new NetworkTask(networkTaskHandler);
        networkTask.execute();

        recyclerView.setLayoutManager(new LinearLayoutManager(activity.getApplicationContext()));
        return view;
    }



    // data call
//
//        public class NetworkTask extends AsyncTask<Void, Void, String> {
//
//            private String url;
//            private ContentValues values;
//            private MovieJsonParsing movieJsonParsing;
//
//            public NetworkTask(String url, ContentValues values) {
//                this.url = url;
//                this.values = values;
//
//            }
//
//            @Override
//            protected String doInBackground(Void... params) {
//
//                String result; //요청 결과를 저장할 변수
//                RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
//                result = requestHttpURLConnection.request(url, values); //해당 URL로부터 결과물을 얻어온다.
//                System.out.println(url);
//                return result;
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(
//                        s);
//                //doInBackground()로 부터 리턴된 값이 onPostExcute()의 매개변수로 넘어오므로  s를 출력한다.
//                System.out.println(s);
//
//                movieJsonParsing = new MovieJsonParsing(s);
//                System.out.println(s);
//                System.out.println(url);
//
//                movieJsonParsing.jsonParsing(movieList);
//                //RecyclerView에 Adpater 지정
//                RecyclerView_SimpleTextAdapter adapter = new RecyclerView_SimpleTextAdapter(movieList, activity);
//                recyclerView.setAdapter(adapter);
//            }
//    }
}