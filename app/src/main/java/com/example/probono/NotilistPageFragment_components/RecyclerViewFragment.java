package com.example.probono.NotilistPageFragment_components;

        import android.content.ContentValues;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.fragment.app.Fragment;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import com.example.probono.DAO.JsonParsing.JsonParser;
        import com.example.probono.DAO.JsonParsing.NotiListJsonParsing;
        import com.example.probono.DAO.NetworkTask.JsonNetworkTask;
        import com.example.probono.DTO.Notice;
        import com.example.probono.DAO.NetworkTask.NetworkTask;
        import com.example.probono.R;

        import org.json.JSONObject;

        import java.util.ArrayList;

public class RecyclerViewFragment extends Fragment {


    private String url;
    private RecyclerView recyclerView;


    View view;
    // url 초기화

    public RecyclerViewFragment(String url) {this.url = url;}


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.notilist_page_fragment_recyclerviewpager, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.noti_recyclerView);

        new Task(url, null, new NotiListJsonParsing()).execute();


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    class Task extends JsonNetworkTask<ArrayList<Notice>>{

        public Task(String url, JSONObject param, JsonParser<ArrayList<Notice>> parser) {
            super(url, param, parser);
        }

        @Override
        public void postTask(ArrayList<Notice> result) {
            RecyclerView_SimpleTextAdapter adapter = new RecyclerView_SimpleTextAdapter(result, view);
            recyclerView.setAdapter(adapter);
        }
    }


}