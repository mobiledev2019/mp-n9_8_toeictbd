package nvduy1997.com.easytoeic.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.adapter.QuestionTestAdapter;
import nvduy1997.com.easytoeic.model.Question;
import nvduy1997.com.easytoeic.server.APIService;
import nvduy1997.com.easytoeic.server.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionTestFragment extends Fragment {


    public QuestionTestAdapter adapter;
    private RecyclerView recyclerViewQuestion;
    private View view;
    private static int idtest;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            idtest = bundle.getInt("IDTEST");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_screen_slide, container, false);
        recyclerViewQuestion = view.findViewById(R.id.RecyclerViewQuestion);
        getAllQuestionTest(String.valueOf(idtest));
//        Log.d("onCreateView", "onCreateView: " + idtest);
        //  getALL();
        return view;

    }

    private void getAllQuestionTest(String id) {
        DataService dataService = APIService.getService();
        Call<List<Question>> callback = dataService.getQuestionTest(id);
        callback.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                ArrayList<Question> arrayQuestion = (ArrayList<Question>) response.body();

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewQuestion.setLayoutManager(linearLayoutManager);
                adapter = new QuestionTestAdapter(getContext(), arrayQuestion);
                recyclerViewQuestion.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                Log.d("onFailure", "onFailure: " + t.toString());
            }
        });
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


}
