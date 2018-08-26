package com.example.student.animals;

import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView list;
    private AnimalsHelper helper;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.list);

        helper = new AnimalsHelper(this);
        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,
                null,
                new String[]{AnimalsTable.COLUMN_ID,
                          AnimalsTable.COLUMN_ANIMAL},
                new int[] {
                        android.R.id.text1,
                        android.R.id.text2
                },0
                );
        list.setAdapter(adapter);
        updateCursor();
        list.setEmptyView(findViewById(R.id.notfound));

        list.setOnItemClickListener(this);






    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return  super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.search:
                handleSearch(item);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private String query = "";

    private void handleSearch(MenuItem item) {
        SearchView searchView = (SearchView) item.getActionView();
        item.expandActionView();
        searchView.setQuery(query,false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(!TextUtils.isEmpty(newText)){
                    selection = "   animal like  ?";
                    selectionArgs = new String[] { "%" + newText + "%"   };
                    query = newText;
                }
                else{
                    selection = null;
                    selectionArgs = null;
                }

                updateCursor();
                return true;
            }
        });

    }

    private String selection = "";
    private String [] selectionArgs = null;

    void updateCursor(){
        Cursor cursor = helper.getReadableDatabase().query(
                AnimalsTable.TABLE_ANIMALS,
                null,selection,selectionArgs,null,null,null
        );
        adapter.swapCursor(cursor);


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Cursor c = adapter.getCursor();
        c.moveToPosition(i);
        int col = c.getColumnIndex(AnimalsTable.COLUMN_ID);
        String id = c.getString(col);
        helper.getWritableDatabase().delete(AnimalsTable.TABLE_ANIMALS,
                "_id =   ?",
                new String[] {id}
                );
        updateCursor();
    }
}
