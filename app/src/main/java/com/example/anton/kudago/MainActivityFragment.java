package com.example.anton.kudago;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<NewsLoader> mContentLoader;

    public ExpandableListAdapter(Context context, List<NewsLoader> loader) {
        this._context = context;
        this.mContentLoader = loader;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        if (mContentLoader.get(groupPosition) != null && mContentLoader.get(groupPosition).getComments() != null) {
            return this.mContentLoader.get(groupPosition).getComments().get(childPosititon);
        }

        return null;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
//
//        final String childText = (String) getChild(groupPosition, childPosition);
//
//        if (convertView == null) {
//            LayoutInflater infalInflater = (LayoutInflater) this._context
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = infalInflater.inflate(R.layout.list_item, null);
//        }
//
//        TextView titleText = (TextView) convertView.findViewById(R.id.newsTitleEditText);
//        TextView contentText = (TextView) convertView.findViewById(R.id.newContentEditText);
//
//        titleText.setText("Title asdasd");
//        contentText.setText("ddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");

        return null; //convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (mContentLoader.get(groupPosition) != null && mContentLoader.get(groupPosition).getComments() != null) {
            return mContentLoader.get(groupPosition).getComments().size();
        }

        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.mContentLoader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.mContentLoader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    boolean isf = true;
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        NewsLoader headerData = (NewsLoader) getGroup(groupPosition);

        if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(
                        isf == true ? R.layout.list_group0 : R.layout.list_group,
                        null);
                isf = false;
        }

        TextView titleText = (TextView) convertView.findViewById(R.id.newsTitleText);
        TextView contentText = (TextView) convertView.findViewById(R.id.newsContentText);
        ImageView newsImage = (ImageView) convertView.findViewById(R.id.newsImageView);

        titleText.setText(headerData.getNewsTitle());
        titleText.setTypeface(null, Typeface.BOLD);

        contentText.setText(headerData.getNewsContent());

        if (newsImage != null) {
            newsImage.setImageDrawable(headerData.getNewsIcon());
        }

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

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;

    private int currentIndex;
    private int messageCount = 4;

    private List<NewsLoader> prepareListData() {
         List<NewsLoader> listData = new ArrayList<NewsLoader>();

        listData.add(new AsyncNewsLoader("What F1 Cars Would Look Like",
                "There once was a time when the racing world was ruled by savage beasts. They were" +
                        " captured just before the snowy season, when noble brave men had one winter" +
                        " to tame this creature. After months of championship battle, a handful of the " +
                        "best animals were kept for another winter of training, while the others were set free again. ",
                getResources().getDrawable(R.drawable.news1, null)));

        listData.add(new AsyncNewsLoader("The Bloody Illegal World of Sand Mining",
                "Photographer Adam Ferguson documents the environmental and human costs of illegal" +
                        " sand mining in India where rapid growth fuels a sometimes violent black market" +
                        " for one of the most basic raw building materials.",
                getResources().getDrawable(R.drawable.news2, null)));

        listData.add(new AsyncNewsLoader("Your Phone Aint as Safe as You Think",
                "Another week chock-full of hacks and vulns, and if you thought your password manager " +
                        "and cell phone were safe, youll want to pay close attention to the LastPass breach.",
                getResources().getDrawable(R.drawable.news3, null)));

        return listData;
    }

    int imageIds[]={
            R.drawable.rootspb1,
            R.drawable.rootspb2,
            R.drawable.rootspb3,
            R.drawable.rootspb4};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragamentView = inflater.inflate(R.layout.fragment_main, container, false);

        expListView = (ExpandableListView)fragamentView.findViewById(R.id.commentsListView);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(fragamentView.getContext(), prepareListData());

        // setting list adapter
        expListView.setAdapter(listAdapter);

        //////////////////////////////////////////////////////////////////////////////////////

        final ImageSwitcher imageSwitcher = (ImageSwitcher) fragamentView.findViewById(R.id.rootImageSwitcher);

        // Set the ViewFactory of the ImageSwitcher that will create ImageView object when asked
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {

            public View makeView() {
                // Create a new ImageView set it's properties
                ImageView imageView = new ImageView(getActivity().getBaseContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setAdjustViewBounds(true);
                return imageView;
            }
        });

        // Declare the animations and initialize them
        Animation in = AnimationUtils.loadAnimation(getActivity().getBaseContext(), android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(getActivity().getBaseContext(),android.R.anim.slide_out_right);

        // set the animation type to imageSwitcher
        imageSwitcher.setInAnimation(in);
        imageSwitcher.setOutAnimation(out);
        imageSwitcher.setImageResource(R.drawable.rootspb1);

        imageSwitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentIndex++;

                // If index reaches maximum reset it
                if (currentIndex == messageCount)
                    currentIndex = 0;

                imageSwitcher.setImageResource(imageIds[currentIndex]);
            }
        });

        return fragamentView;
    }
}
