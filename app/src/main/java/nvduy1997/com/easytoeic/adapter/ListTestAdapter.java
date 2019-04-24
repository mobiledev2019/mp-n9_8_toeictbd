package nvduy1997.com.easytoeic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.model.TestPart5;

public class ListTestAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<TestPart5> arrayTest;

    public ListTestAdapter(Context context, ArrayList<TestPart5> arrayTest) {
        this.context = context;
        this.arrayTest = arrayTest;
    }

    @Override
    public int getCount() {
        return arrayTest.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayTest.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        TextView textViewTest;
        ImageView imageViewTest;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_recycler_view,null);
            viewHolder.imageViewTest = convertView.findViewById(R.id.imgIcon);
            viewHolder.textViewTest = convertView.findViewById(R.id.textViewTest);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        TestPart5 test = arrayTest.get(position);
        viewHolder.textViewTest.setText(test.getTen());


        return convertView;
    }

//
//    public ListTestAdapter(Context context, ArrayList<Test> tests) {
//        this.arrayTest = tests;
//        inflater = LayoutInflater.from(context);
//    }
//
//
//    public void setOnClickItemView(OnClickItemView onClickItemView) {
//        this.onClickItemView = onClickItemView;
//    }
//
//    @NonNull
//    @Override
//    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View view = inflater.inflate(R.layout.item_recycler_view, viewGroup, false);
//        return new viewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull viewHolder viewHolder, final int i) {
//        final Test test = arrayTest.get(i);
//        viewHolder.textViewTest.setText(test.getTen());
//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onClickItemView.onClickItemView(test);
//
//            }
//        });
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return arrayTest.size();
//    }
//
//
//    class viewHolder extends RecyclerView.ViewHolder {
//        TextView textViewTest;
//        ImageView imageViewTest;
//
//        @SuppressLint("CutPasteId")
//        public viewHolder(@NonNull View itemView) {
//            super(itemView);
//            textViewTest = itemView.findViewById(R.id.textViewTest);
//            imageViewTest = itemView.findViewById(R.id.imgIcon);
//        }
//    }
//
//    public interface OnClickItemView {
//        void onClickItemView(Test test);
//    }

}
