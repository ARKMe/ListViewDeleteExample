package bello.andrea.listviewdeleteexample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MioAdapter adapter = new MioAdapter();
        final String[] array = getResources().getStringArray(R.array.mio_array);
        adapter.reset(array);

        ListView listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(adapter);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.reset(array);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public class MioAdapter extends BaseAdapter {

        ArrayList<String> array;

        public MioAdapter() {
            this.array = new ArrayList<>();
        }

        @Override
        public int getCount() {
            return array.size();
        }

        @Override
        public Object getItem(int position) {
            return array.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.element_layout, null);
            }

            String valore = array.get(position);
            ((TextView)convertView.findViewById(R.id.testo)).setText(valore);

            convertView.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    array.remove(position);
                    notifyDataSetChanged();
                }
            });

            return convertView;
        }

        public void reset(String[] array) {
            this.array = new ArrayList<>(Arrays.asList(array));
        }
    }
}
