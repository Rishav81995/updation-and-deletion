package com.example.pc26.myproject;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity implements mylistener {
    FirebaseAuth mFirebaseAuth;
    FirebaseDatabase database;
    DatabaseReference ref;
    RecyclerView recyclerView;
    List<UserDetailsposo> userList;
    ArrayAdapter<String> adapter;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        mFirebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        b1 = (Button)findViewById(R.id.delete);
        userList = new ArrayList<>();
        ref = database.getReference("users");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userList.clear();
                Log.e("ttt", "this is me" + dataSnapshot.getValue());
                if (dataSnapshot.getChildren() != null)

                    for (DataSnapshot kk : dataSnapshot.getChildren()) {

                        UserDetailsposo userDetailsposo = kk.getValue(UserDetailsposo.class);
                       userDetailsposo.setKey(kk.getKey());
                        Log.e("ttt", "this is data " + userDetailsposo.getPhone_no() + "  " + kk.getKey());
                        userList.add(userDetailsposo);


                    }
                if (userList.size() > 0) {
                    MyListAdapter adapter = new MyListAdapter(userList, getApplicationContext(),RecyclerActivity.this);
                    // recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(RecyclerActivity.this));
                    recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(48));
                    //or
                    recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext()));
                    //or
                    recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), R.drawable.divider));
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void delete() {

    }

   /* @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();
         if(item.getTitle().equals("Update")){

             Toast.makeText(this,"Data Updated",Toast.LENGTH_SHORT).show();
         }

        if(item.getTitle().equals("Delete"))
        {
            adapter.remove(adapter.getItem(info.position));


            Toast.makeText(this, "You have chosen the " + getResources().getString(R.string .delete) +
                            " context menu option for " + userList.get(info.position), Toast.LENGTH_SHORT).show();

            return true;

        }
        return super.onContextItemSelected(item);
*/
   // }


}

