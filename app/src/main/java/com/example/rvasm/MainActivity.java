package com.example.rvasm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Adaptor itemAdaptor;
    private ArrayList<Item> itemArrayList;

    private boolean loading;
    private boolean last;
    private int currentPage = 1;
    private final int totalPage = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
         progressBar = findViewById(R.id.pb);
         recyclerView = findViewById(R.id.rv);



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(itemAdaptor);

        recyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            public void loadMoreItem() {
                loading = true;
                progressBar.setVisibility(View.VISIBLE);
                currentPage ++;
                loadNextPage();


            }

            @Override
            public boolean loading() {
                return loading;
            }

            @Override
            public boolean last() {
                return last;
            }
        });
        setFirstItem();
    }
    private void setFirstItem(){
        itemArrayList = getListItem();
        itemAdaptor.setItemData(itemArrayList);

    }
    private ArrayList<Item> getListItem(){
        Toast.makeText(this, "Load in page" + " " + currentPage, Toast.LENGTH_SHORT).show();
        ArrayList<Item> itemArrayList = new ArrayList<>();

        for(int i = 1; i<=9; i++){
            itemArrayList.add(new Item("Item name ", "This is item description"));

        }

        return itemArrayList;
    }
    private void loadNextPage(){
        Handler handler = new Handler();
        handler.postDelayed(() -> {

            ArrayList<Item> itemList = getListItem();
            itemArrayList.addAll(itemList);


            loading = false;
            progressBar.setVisibility(View.GONE);
            if (currentPage == totalPage){
                last = true;
            }
            itemAdaptor.notifyDataSetChanged();
        }, 2000);
    }
}
