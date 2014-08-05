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

public class SecondaryListFragment extends Fragment {

    public interface OnSecondaryListItemClickedListener {
        public void onSecondaryListItemClick(int id, String url);
    }

    private ListView listView;
    private ArrayList<String> navItems = new ArrayList<String>(Arrays.asList(new String[] {
            "Section 1-- long text here to make the view wrap" + "even longer text to make the view wrap to 3+ lines, used to test performance." +
            "even longer text to make the view wrap to 3+ lines, used to test performance.", "Section 2-- long text here to make the view wrap", "Section 3-- long text here to make the view wrap",
            "Section 4-- long text here to make the view wrap", "Section 5-- even longer text to make the view wrap to 3+ lines, used to test performance." +
            "even longer text to make the view wrap to 3+ lines, used to test performance" + " even longer text to make the view wrap to 3+ lines, used to test performance",
            "Section 6-- long text here to make the view wrap" + " even longer text to make the view wrap to 3+ lines, used to test performance", "Section 7-- long text here to make the view wrap", "Section 8--long text here to make the view wrap",
            "Section 9-- long text here to make the view wrap", "Section 10-- long text here to make the view wrap", "Section 11-- even longer text to make the view wrap to 3+ lines, used to test performance." +
            "even longer text to make the view wrap to 3+ lines, used to test performance" + " even longer text to make the view wrap to 3+ lines, used to test performance",
            "Section 12-- even longer text to make the view wrap to 3+ lines, used to test performance." + "even longer text to make the view wrap to 3+ lines, used to test performance" +
            " even longer text to make the view wrap to 3+ lines, used to test performance",
            "Section 1-- long text here to make the view wrap" + "even longer text to make the view wrap to 3+ lines, used to test performance." +
            "even longer text to make the view wrap to 3+ lines, used to test performance.", "Section 2-- long text here to make the view wrap", "Section 3-- long text here to make the view wrap",
            "Section 4-- long text here to make the view wrap", "Section 5-- even longer text to make the view wrap to 3+ lines, used to test performance." +
            "even longer text to make the view wrap to 3+ lines, used to test performance" + " even longer text to make the view wrap to 3+ lines, used to test performance",
            "Section 6-- long text here to make the view wrap" + " even longer text to make the view wrap to 3+ lines, used to test performance", "Section 7-- long text here to make the view wrap", "Section 8--long text here to make the view wrap",
            "Section 9-- long text here to make the view wrap", "Section 10-- long text here to make the view wrap", "Section 11-- even longer text to make the view wrap to 3+ lines, used to test performance." +
            "even longer text to make the view wrap to 3+ lines, used to test performance" + " even longer text to make the view wrap to 3+ lines, used to test performance",
            "Section 12-- even longer text to make the view wrap to 3+ lines, used to test performance." + "even longer text to make the view wrap to 3+ lines, used to test performance" +
            " even longer text to make the view wrap to 3+ lines, used to test performance",
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
                String url = "http://www.google.com/design/";
                ((OnSecondaryListItemClickedListener) activity).onSecondaryListItemClick(pos, url);
                view.requestFocus();
            }
        });
        return rootView;
    }
}
