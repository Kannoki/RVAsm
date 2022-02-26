package com.example.rvasm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Adaptor itemAdaptor;
    private ArrayList<Item> itemArrayList;

    private ProgressBar progressBar;

    private boolean loading;
    private boolean last;
    private int currentPage = 1;
    private final int totalPage = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         progressBar = findViewById(R.id.pb);
         RecyclerView recyclerView = findViewById(R.id.rv);
         itemAdaptor = new Adaptor();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(itemAdaptor);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        recyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            public void loadMoreItem() {
                loading = true;
                progressBar.setVisibility(View.VISIBLE);
                currentPage += 1;
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

        for(int i = 1; i<=10; i++){
            itemArrayList.add(new Item("Item name ", "This is item description"));

        }
        return itemArrayList;
    }
    @SuppressLint("NotifyDataSetChanged")
    private void loadNextPage(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayList<Item> list = getListItem();
                itemArrayList.addAll(list);
                itemAdaptor.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
                loading = false;

                    if (currentPage == totalPage){
                        last = true;
                    }
            }
        }, 2000);
    }
}
