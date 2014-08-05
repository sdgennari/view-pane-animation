package com.willowtree.test.viewpaneanimation.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.willowtree.test.viewpaneanimation.R;

import java.util.ArrayList;
import java.util.Arrays;

public class PrimaryListFragment extends Fragment {

    public interface OnPrimaryListItemClickedListener {
        public void onPrimaryListItemClick(int id);
    }

    private ListView listView;
    private ArrayList<String> navItems = new ArrayList<String>(Arrays.asList(new String[] {
        "Chapter 1", "Chapter 2", "Chapter 3-- long text here to make the view wrap",
        "Chapter 4", "Chapter 5", "Chapter 6-- even longer text to make the view wrap to 3+ lines, used to test performance",
        "Chapter 7", "Chapter 8", "Chapter 9", "Chapter 10", "Chapter 11", "Chapter 12",
    }));

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        final Activity activity = this.getActivity();
        listView = (ListView) rootView.findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, R.layout.list_item);
        adapter.addAll(navItems);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                ((OnPrimaryListItemClickedListener) activity).onPrimaryListItemClick(pos);
            }
        });

        return rootView;
    }
}
