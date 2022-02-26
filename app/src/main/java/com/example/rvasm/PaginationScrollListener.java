package com.example.rvasm;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class PaginationScrollListener extends RecyclerView.OnScrollListener {
    private final LinearLayoutManager linearLayoutManager;

    public PaginationScrollListener(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = linearLayoutManager.getChildCount();
        int totalItemCount = linearLayoutManager.getItemCount();
        int firstVisibleItemPosistion = linearLayoutManager.findFirstCompletelyVisibleItemPosition();

        if (loading() || last() ){
            return;
        }
        if (firstVisibleItemPosistion>=0 && (visibleItemCount + firstVisibleItemPosistion) >= totalItemCount){
            loadMoreItem();
        }
    }

    public abstract void loadMoreItem();
    public abstract boolean loading();
    public abstract boolean last();



}
