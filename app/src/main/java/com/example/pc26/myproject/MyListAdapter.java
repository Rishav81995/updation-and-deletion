package com.example.pc26.myproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {
    private List<UserDetailsposo> listdata;
    Context context;
    RecyclerView recyclerView;
    RecyclerActivity recyclerActivity;
    //Listdata listdata;
    UserDetailsposo userDetailsposo;


    public MyListAdapter(List<UserDetailsposo> listdata, Context context,RecyclerActivity recyclerActivity) {
        this.listdata = listdata;
        this.context = context;
        this.recyclerActivity = recyclerActivity;
        Log.e("rry", " rishav" + listdata.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_list_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(listItem,context,recyclerActivity,listdata);

        return viewHolder;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public List<UserDetailsposo> getListdata() {
        return listdata;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserDetailsposo userde = listdata.get(position);
        holder.name.setText("" + userde.getName());
        holder.phone_no.setText("" + userde.getPhone_no());
        holder.add.setText("" + userde.getAddress());
        holder.gender.setText("" + userde.getGender());
        holder.course.setText("" + userde.getCourse());

        Log.e("rry", " rishav11" + listdata.size() + " " + position);


    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        public TextView add;
        Intent i;
        int position;
        public TextView phone_no;
        public TextView name;
        public TextView course;
        public List<UserDetailsposo> listdata;
        public TextView gender;
        public Context context;
        LongClickListener onLongClickListener;
        public  RecyclerActivity recyclerActivity;

        public ViewHolder(View itemView, Context context, RecyclerActivity recyclerActivity, List<UserDetailsposo> listdata)  {
            super(itemView);
            this.add = (TextView) itemView.findViewById(R.id.address);
            this.phone_no = (TextView) itemView.findViewById(R.id.phone_no);
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.course = (TextView) itemView.findViewById(R.id.course);
            this.gender = (TextView) itemView.findViewById(R.id.gender);
             this.context = context;
             this.recyclerActivity=recyclerActivity;
             this.listdata=listdata;


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("FFF", "matrid");

                }
            });
            itemView.setOnCreateContextMenuListener(this);

        }

         public void setLongClickListner(LongClickListener lc) {
            this.onLongClickListener = lc;
        }

        public boolean onLongClick(View view) {
           // this.onLongClickListener.onItemLongClick(getLayoutPosition());
            return false;
        }


        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
//             MenuInflater inflater =co.getMenuInflater();
//             inflater.inflate(R.menu.menu1, menu);
//             menu.setHeaderTitle("Select The Action");
            contextMenu.setHeaderTitle("Select the Action");

            ViewHolder holder = (ViewHolder) recyclerActivity.recyclerView.getChildViewHolder(view);
             position = holder.getAdapterPosition();
             i = new Intent(recyclerActivity,UpdateActivity.class);
             // for adding permission to open app in other phones which have low sdk..
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }


            MenuItem Update = contextMenu.add(Menu.NONE, 1, 1, "Update");
            MenuItem Delete = contextMenu.add(Menu.NONE, 2, 2, "Delete");
            Delete.setOnMenuItemClickListener(onEditMenu);
            Update.setOnMenuItemClickListener(onEditMenu);
        }


        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
                        .getMenuInfo();

                if (item.getTitle().equals("Update"))
                {
                    UserDetailsposo userDetailsposo =listdata.get(position);
                    i.putExtra("kk",userDetailsposo);
                   context.startActivity(i);
                }
                if(item.getTitle().equals("Delete")) {
                    //listdata.remove(listdata.getItem(info.position));

                    UserDetailsposo userDetailsposo = listdata.get(position);
                    ((mylistener)recyclerActivity).delete();
                    FirebaseAuth mFirebaseAuth;
                    FirebaseDatabase database ;
                   // DatabaseReference ref;
                    mFirebaseAuth = FirebaseAuth.getInstance();
                    database = FirebaseDatabase.getInstance();
                   // ref = database.getReference("users");
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users").child(userDetailsposo.getKey());
                    ref.removeValue();
                    Toast.makeText(recyclerActivity,"Data Deleted",Toast.LENGTH_SHORT).show();
                   // ref.removeValue();;

                }

                           return true;
            }
        };


    }

}
