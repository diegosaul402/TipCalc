package com.diegosaul402.tipcalc.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.diegosaul402.tipcalc.R;
import com.diegosaul402.tipcalc.entity.TipRecord;
import com.diegosaul402.tipcalc.utils.TipUtils;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by diego on 13/10/16.
 */

public class TipAdapter extends RecyclerView.Adapter<TipAdapter.ViewHolder>{

    private Context context;
    private List<TipRecord> dataset;
    private OnItemClickListener onItemClickListener;

    public TipAdapter(Context context, List<TipRecord> dataset, OnItemClickListener onItemClickListener){
        this.context = context;
        this.dataset = dataset;
        this.onItemClickListener = onItemClickListener;
    }

    public TipAdapter(Context context, OnItemClickListener onItemClickListener){
        this.context = context;
        this.dataset = new ArrayList<TipRecord>();
        this.onItemClickListener = onItemClickListener;
    }

    public  void init(){
        dataset = new Select().from(TipRecord.class).queryList();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TipRecord element = dataset.get(position);

        String strTip = String.format(context.getString(R.string.global_message_tip), TipUtils.getTip(element));
        holder.txtContent.setText(strTip);
        holder.setOnItemClickListener(element, onItemClickListener);
    }


    public void add(TipRecord record){
        //dataset.add(0, record);
        record.save();
        dataset = new Select().from(TipRecord.class).queryList();
        notifyDataSetChanged();
    }

    public void clear(){
        dataset.clear();
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.txtContent)
        TextView txtContent;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setOnItemClickListener(final TipRecord element, final OnItemClickListener onItemClickListener) {
          itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  onItemClickListener.onItemClick(element);
              }
          });
        }
    }
}
