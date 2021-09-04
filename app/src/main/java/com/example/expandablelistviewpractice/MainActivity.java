package com.example.expandablelistviewpractice;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;

import java.util.Map;



import android.app.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.TextView;

public class MainActivity extends Activity {

    private List<String> mGroup = new ArrayList<String>();
    private Map<String, List<String>> mChild = new HashMap<String, List<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main );

        init();
        initView();
    }

    private void init(){
        mGroup.add( "Vegitable" );
        mGroup.add( "Fruit" );

        List<String> vegList = new ArrayList<String>();
        vegList.add( "Potato" );
        vegList.add( "Brocoli" );
        vegList.add( "Onion" );
        vegList.add( "Chilli" );
        vegList.add( "Carrot" );
        mChild.put( "Vegitable", vegList );

        List<String> fruitList = new ArrayList<String>();
        fruitList.add( "Strawberry" );
        fruitList.add( "Blueberry" );
        fruitList.add( "Grapes" );
        fruitList.add( "Banana" );
        mChild.put( "Fruit", fruitList );
    }

    private void initView(){

        ExpandableListView lv = (ExpandableListView) findViewById( R.id.ex_listview );
        ExpandableListAdapter adapter = new ExpandableListAdapter();
        lv.setAdapter( adapter );

        lv.setOnChildClickListener( new OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                return false;
            }
        });

        lv.setOnGroupClickListener( new OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        lv.setOnGroupExpandListener( new OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
            }
        });

        lv.setOnGroupCollapseListener( new OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
            }
        });
    }


    private class ExpandableListAdapter extends BaseExpandableListAdapter{

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return mChild.get( mGroup.get( groupPosition ) ).get( childPosition );
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            if ( convertView == null )
                convertView = LayoutInflater.from( MainActivity.this ).inflate( R.layout.view_list_item, null );
            ((TextView)convertView.findViewById( R.id.list_item )).setText( mChild.get( mGroup.get( groupPosition ) ).get( childPosition ) );
            return convertView;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return mChild.get( mGroup.get( groupPosition ) ).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return mGroup.get( groupPosition );
        }

        @Override
        public int getGroupCount() {
            return mGroup.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            if ( convertView == null )
                convertView = LayoutInflater.from( MainActivity.this ).inflate( R.layout.view_list_group, null );
            ((TextView)convertView.findViewById( R.id.group_item )).setText( mGroup.get( groupPosition ) );
            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}
