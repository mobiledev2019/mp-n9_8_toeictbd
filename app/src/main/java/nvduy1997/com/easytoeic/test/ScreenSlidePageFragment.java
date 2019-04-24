package nvduy1997.com.easytoeic.test;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.model.Question;
import nvduy1997.com.easytoeic.server.APIService;
import nvduy1997.com.easytoeic.server.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScreenSlidePageFragment extends Fragment {
    private static final String ARGUMENT_ID = "ARGUMENT_ID";
    private int mId;
    private int numberPage;
    private TextView tvNum, tvQuestion;
    private RadioButton radA, radB, radC, radD;
    private RadioGroup radGroup;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        numberPage = bundle.getInt("OK");
        Log.e("TAG", "onCreate: " + numberPage);
    }

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_screen_slide_page, container, false);

        tvNum = view.findViewById(R.id.tvNum);
        tvQuestion = view.findViewById(R.id.tvQuestion);
        radA = view.findViewById(R.id.radioA);
        radB = view.findViewById(R.id.radioB);
        radC = view.findViewById(R.id.radioC);
        radD = view.findViewById(R.id.radioD);
        radGroup = view.findViewById(R.id.radioGroup);


        if (getArguments() != null) {
            mId = getArguments().getInt(ARGUMENT_ID);
        }


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        DataService dataService = APIService.getService();
        Call<List<Question>> callback = dataService.getQuestionTest(String.valueOf(mId));
        callback.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                final ArrayList<Question> array = (ArrayList<Question>) response.body();

                tvNum.setText("Câu " + (numberPage + 1));
                tvQuestion.setText(array.get(numberPage).getTen());
                radA.setText(array.get(numberPage).getA());
                radB.setText(array.get(numberPage).getB());
                radC.setText(array.get(numberPage).getC());
                radD.setText(array.get(numberPage).getD());


                radGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @SuppressLint("ResourceType")
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
//                        Toast.makeText(getActivity(),"Day la dap an "+ checkedId,Toast.LENGTH_LONG).show();
                        array.get(numberPage).choiceID = checkedId;
                        array.get(numberPage).setTraloi(getChoiceID(checkedId));
                        Log.e("onCheckedChanged", "onCheckedChanged: " + array.get(numberPage).getTraloi() );
                    }
                });

            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                Log.d("onFailure", "onFailure: " + t.toString());
            }
        });

    }

    private String getChoiceID(int id) {
        if (id == R.id.radioA) {
            return "A";
        } else if (id == R.id.radioB) {
            return "B";
        } else if (id == R.id.radioC) {
            return "C";
        } else if (id == R.id.radioD) {
            return "D";
        } else return "X";
    }

    //    private void getAllQuestionTest(String id) {
//
//        DataService dataService = APIService.getService();
//        Call<List<Question>> callback = dataService.getQuestionTest(id);
//        callback.enqueue(new Callback<List<Question>>() {
//            @Override
//            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
//                array = (ArrayList<Question>) response.body();
//               // ArrayList<Question> array = (ArrayList<Question>) response.body();
//                Log.e("onResponse", "onResponse: " + array.size() );
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Question>> call, Throwable t) {
//                Log.d("onFailure", "onFailure: " + t.toString());
//            }
//        });
//
//    }
    public static ScreenSlidePageFragment create(int numberPage, int id) {
        ScreenSlidePageFragment sc = new ScreenSlidePageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("OK", numberPage);
        bundle.putInt(ARGUMENT_ID, id);
        sc.setArguments(bundle);
        return sc;
    }

}
