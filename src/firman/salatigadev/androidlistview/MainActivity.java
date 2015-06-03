package firman.salatigadev.androidlistview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	
	Map<String, Integer> mapIndex;
    ListView negaraList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		String[] negara = getResources().getStringArray(R.array.daftar_negara);
		 
        Arrays.asList(negara);
 
        negaraList = (ListView) findViewById(R.id.list_negara);
        negaraList.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, negara));
 
        getIndexList(negara);
 
        displayIndex();        
	}
	
	private void getIndexList(String[] negara) {
        mapIndex = new LinkedHashMap<String, Integer>();
        for (int i = 0; i < negara.length; i++) {
            String ngr = negara[i];
            String index = ngr.substring(0, 1);
 
            if (mapIndex.get(index) == null)
                mapIndex.put(index, i);
        }
    }
 
    private void displayIndex() {
        LinearLayout indexLayout = (LinearLayout) findViewById(R.id.side_index);
 
        TextView textView;
        List<String> indexList = new ArrayList<String>(mapIndex.keySet());
        for (String index : indexList) {
            textView = (TextView) getLayoutInflater().inflate(
                    R.layout.side_index_item, null);
            textView.setText(index);
            textView.setOnClickListener(this);
            indexLayout.addView(textView);
        }
    }
 
    public void onClick(View view) {
        TextView selectedIndex = (TextView) view;
        negaraList.setSelection(mapIndex.get(selectedIndex.getText()));
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
