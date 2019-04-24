package nvduy1997.com.easytoeic.test;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;


import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.model.Question;

public class GirdViewAdapter extends BaseAdapter {
    ArrayList<Question> list;
    LayoutInflater inflater;

    public GirdViewAdapter(ArrayList<Question> list, Context context) {
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Question question = list.get(position);
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_gird_view,null);
            viewHolder.tvAnswer = convertView.findViewById(R.id.tvAnswer);
            viewHolder.tvNum = convertView.findViewById(R.id.tvNumAnsewer);
            convertView.setTag(viewHolder);

        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvNum.setText(question.getId());
        Log.e("getView", "getView: " + question.getTraloi() );
        viewHolder.tvAnswer.setText(question.getTraloi());
        return convertView;
    }
    private static class ViewHolder{
        TextView tvNum,tvAnswer;
    }
}
