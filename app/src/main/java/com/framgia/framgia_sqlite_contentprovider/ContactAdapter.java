package com.framgia.framgia_sqlite_contentprovider;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nttungg on 2/6/18.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<UserModel> userModels;

    public ContactAdapter(Context context, ArrayList<UserModel> userModels) {
        this.context = context;
        this.userModels = userModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        View itemview = layoutInflater.inflate(R.layout.item_rv_contact,parent,false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(userModels.get(position));
    }

    @Override
    public int getItemCount() {
        return userModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView userName;
        private TextView phone;
        public ViewHolder(View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
        }

        public void bindData(UserModel userModel){
            userName.setText(userModel.getName());
            phone.setText(userModel.getPhonenumber());
        }
    }
}
