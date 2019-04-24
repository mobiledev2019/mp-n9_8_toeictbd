package nvduy1997.com.easytoeic.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.adapter.ListTestAdapter;
import nvduy1997.com.easytoeic.model.TestPart5;
import nvduy1997.com.easytoeic.server.APIService;
import nvduy1997.com.easytoeic.server.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListTestFragment extends Fragment  {

    private View view;
    private ListTestAdapter adapter;
    private ListView lv_Test;
    private PassingData ls;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ls = (PassingData) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_test_part5, container, false);
        lv_Test = view.findViewById(R.id.lv_Test);
        getAllTestPart5();
        return view;
    }
    private void getAllTestPart5(){
        DataService dataService = APIService.getService();
        Call<List<TestPart5>> callback = dataService.getTestPart5();
        callback.enqueue(new Callback<List<TestPart5>>() {
            @Override
            public void onResponse(Call<List<TestPart5>> call, Response<List<TestPart5>> response) {
                final ArrayList<TestPart5> arrayTestPart5 = (ArrayList<TestPart5>) response.body();
                adapter = new ListTestAdapter(getContext(),arrayTestPart5);
                lv_Test.setAdapter(adapter);
                lv_Test.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ls.PassData(Integer.parseInt(arrayTestPart5.get(position).getId()));
                    }
                });
            }

            @Override
            public void onFailure(Call<List<TestPart5>> call, Throwable t) {

            }
        });
     }
    public interface PassingData{
        void PassData(int idTest);
    }

}
