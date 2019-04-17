package com.example.r30_a.weatheractivity.Utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.r30_a.weatheractivity.Interface.onSwipeListener;

public class ItemTouchHelperCalback extends ItemTouchHelper.Callback {

    onSwipeListener onSwipeListener;

    public ItemTouchHelperCalback(onSwipeListener onSwipeListener) {
        this.onSwipeListener = onSwipeListener;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        //設定滑動動作的flag
        int dragFlags = 0;
        int swipeFlags = 0;
        if(recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN ;
            swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;;
        }
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        //滑動後啟動listener做刪除卡片的動作
        onSwipeListener.onItemDelete(viewHolder.getAdapterPosition());
    }
}
