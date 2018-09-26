package kr.ac.cau.sealife;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;

import java.util.Arrays;

public class SListView extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        String[] values = {
            "해양환경관리공단","람사르 사이트","부산시청","서귀포시청","태안군청","옹진구청","순천시청",
                "무안군청", "서천군청", "고창군청", "보성군청", "부안군청", "진도군청", "낙동강하구에코센터",
                "금강철새조망대", "강화갯벌센터", "신안군", "해양수산부"
        };

        Arrays.sort(values);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, values);

        setListAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
