package com.cj.uiapplication.view.layoutmanager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cj.uiapplication.R;
import com.cj.uiapplication.adapter.layoutmanager.CustomAdapter;
import com.cj.uiapplication.adapter.layoutmanager.CustomLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustomLayoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomLayoutFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CustomLayoutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CustomLayoutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CustomLayoutFragment newInstance(String param1, String param2) {
        CustomLayoutFragment fragment = new CustomLayoutFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        strings.clear();
        for (int i=0;i<20;i++){
            strings.add("第"+i+"个item");
        }
    }
    private Button btn;
    private RecyclerView recyclerView;
    private List<String> strings = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_custom_layout, container, false);
        btn = view.findViewById(R.id.btn);
        recyclerView = view.findViewById(R.id.rv);
        CustomLayoutManager customLayoutManager = new CustomLayoutManager();
        CustomAdapter adapter = new CustomAdapter(strings);
        recyclerView.setLayoutManager(customLayoutManager);
        recyclerView.setAdapter(adapter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.smoothScrollToPosition(5);
            }
        });
        return view;
    }
}