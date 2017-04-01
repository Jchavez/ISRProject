package com.proyectos.isrproject.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.proyectos.isrproject.R;
import com.proyectos.isrproject.adapter.QueryAdapter;
import com.proyectos.isrproject.model.Query;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class History extends Fragment {

    @BindView(R.id.queries) RecyclerView recyclerView;

    private List<Query> queryList = new ArrayList<>();
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    String uid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this, view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        adapter = new QueryAdapter(queryList);
        recyclerView.setAdapter(adapter);

        /*loadUserProfile();
        retrieveData();*/
        prepareMovieData();

        return view;
    }

    private void loadUserProfile() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            uid = user.getUid();
        }
    }

    /*private void retrieveData() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("user-queries/" + uid);

        // Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Query query = dataSnapshot.getValue(Query.class);
                queryList.add(query);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("loadPost:onCancelled", databaseError.toException());
            }
        });

        adapter.notifyDataSetChanged();
    }*/

    private void prepareMovieData() {
        Query movie = new Query("73093-1", "Jonathan Chavez", 423, 423, 44, 132, "12/03/2017");
        queryList.add(movie);
        Query movie2 = new Query("73093-1", "Jonathan Chavez", 423, 423, 44, 132, "14/03/2017");
        queryList.add(movie2);
        Query movie3 = new Query("73093-1", "Jonathan Chavez", 423, 423, 44, 132, "15/03/2017");
        queryList.add(movie3);
        Query movie4 = new Query("73093-1", "Jonathan Chavez", 423, 423, 44, 132, "16/03/2017");
        queryList.add(movie4);
        Query movie5 = new Query("73093-1", "Jonathan Chavez", 423, 423, 44, 132, "18/03/2017");
        queryList.add(movie5);
        Query movie6 = new Query("73093-1", "Jonathan Chavez", 423, 423, 44, 132, "22/03/2017");
        queryList.add(movie6);
        Query movie7 = new Query("73093-1", "Jonathan Chavez", 423, 423, 44, 132, "131/03/2017");
        queryList.add(movie7);
        Query movie8 = new Query("73093-1", "Jonathan Chavez", 423, 423, 44, 132, "12/03/2017");
        queryList.add(movie8);
        Query movie9 = new Query("73093-1", "Jonathan Chavez", 423, 423, 44, 132, "12/03/2017");
        queryList.add(movie9);
        Query movie10 = new Query("73093-1", "Jonathan Chavez", 423, 423, 44, 132, "12/03/2017");
        queryList.add(movie10);
        Query movie11 = new Query("73093-1", "Jonathan Chavez", 423, 423, 44, 132, "12/03/2017");
        queryList.add(movie11);

        adapter.notifyDataSetChanged();
    }

}
