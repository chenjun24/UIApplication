package com.cj.uiapplication.view.layoutmanager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cj.uiapplication.R;
import com.cj.uiapplication.adapter.layoutmanager.LinearAdapter;
import com.cj.uiapplication.adapter.layoutmanager.LinearAdapter1;
import com.cj.uiapplication.adapter.layoutmanager.MyDiffCallback;
import com.cj.uiapplication.adapter.layoutmanager.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LinearLayoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LinearLayoutFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LinearLayoutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LinearLayoutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LinearLayoutFragment newInstance(String param1, String param2) {
        LinearLayoutFragment fragment = new LinearLayoutFragment();
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
        personList.clear();
        Person person1 = new Person("张三","111",1);
        Person person2 = new Person("张三","112",2);
        Person person3 = new Person("张三","113",3);
        Person person4 = new Person("张三","114",4);
        Person person5 = new Person("张三","115",5);
        Person person6 = new Person("张三","116",6);
        Person person7 = new Person("张三","117",7);
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);
        personList.add(person5);
        personList.add(person6);
        personList.add(person7);

        newList.clear();
        Person person11 = new Person("张三","111",1);
        Person person22 = new Person("张三","112",2);
        Person person33 = new Person("张三","113",3);
        Person person44 = new Person("李四","114",4);
        Person person55 = new Person("张三","115",5);
        Person person66 = new Person("张三","116",6);
        Person person77 = new Person("张三","117",7);
        Person person88 = new Person("张三88","118",8);
        newList.add(person11);
        newList.add(person22);
        newList.add(person33);
        newList.add(person44);
        //newList.add(person55);
        newList.add(person66);
        newList.add(person88);
        newList.add(person77);
    }

    private Button button;
    private RecyclerView recyclerView;
    private List<Person> personList = new ArrayList<>();
    private List<Person> newList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_linear_layout, container, false);
        recyclerView = view.findViewById(R.id.rv);
        button = view.findViewById(R.id.refresh);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        //LinearAdapter adapter = new LinearAdapter(personList);
        LinearAdapter1 adapter = new LinearAdapter1();
        adapter.setPersonList(personList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("junchen", "onClick: ");
//                List<Person> oldPersonList = adapter.getPersonList();
//                DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffCallback(oldPersonList, newList),true);
//                adapter.setPersonList(newList);
//                diffResult.dispatchUpdatesTo(adapter);

                adapter.setPersonList(newList);

            }
        });
        return view;
    }
}