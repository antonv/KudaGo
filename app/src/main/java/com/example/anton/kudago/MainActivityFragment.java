package com.example.anton.kudago;

import android.app.Fragment;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class ExpandableListAdapter implements ListAdapter { // BaseExpandableListAdapter {

    private Context              mContext = null;
    private List<NewsLoader>     mNews = null;
    private List<CommentsLoader> mComments = null;

    public ExpandableListAdapter(Context context, List<NewsLoader> news, List<CommentsLoader> comments) {
        this.mContext = context;
        this.mNews = news;
        this.mComments = comments;
    }
    boolean isf = true;

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {    }

    @Override
    public int getCount() {
        return mNews.size() + mComments.size();
    }

    @Override
    public Object getItem(int position) {
        if (position >= mNews.size())
            return mComments.get(position - mNews.size());

        return mNews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsLoader     newsData = null;
        CommentsLoader commentData = null;

        Object headerData = getItem(position);

        if (headerData instanceof NewsLoader) {
            newsData = (NewsLoader)headerData;
        }
        else if (headerData instanceof CommentsLoader) {
            commentData = (CommentsLoader)headerData;
        }
        else {
            return null;
        }

        LayoutInflater infalInflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (newsData != null)
        {
            convertView = infalInflater.inflate(
                    isf == true ? R.layout.list_group0 : R.layout.list_group,
                    null);
            isf = false;


            TextView titleText = (TextView) convertView.findViewById(R.id.newsTitleText);
            TextView contentText = (TextView) convertView.findViewById(R.id.newsContentText);
            ImageView newsImage = (ImageView) convertView.findViewById(R.id.newsImageView);

            titleText.setText(newsData.getTitle());
            titleText.setTypeface(null, Typeface.BOLD);

            contentText.setText(newsData.getContent());

            if (newsImage != null) {
                newsImage.setImageResource(R.drawable.news1);//.setImageDrawable(newsData.getNewsIcon());
            }
        }
        else if (commentData != null)
        {
            convertView = infalInflater.inflate(R.layout.list_comments, null);

            TextView titleText = (TextView) convertView.findViewById(R.id.newsTitleText);
            TextView contentText = (TextView) convertView.findViewById(R.id.newsContentText);
            ImageView newsImage = (ImageView) convertView.findViewById(R.id.newsImageView);

            titleText.setText(commentData.getAuthor());
            titleText.setTypeface(null, Typeface.BOLD);

            contentText.setText(commentData.getContent());

            if (newsImage != null) {
                newsImage.setImageResource(R.drawable.news2);//.setImageDrawable(newsData.getNewsIcon());
            }
        }

        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return 0;

        if (position >= mNews.size())
            return 1;

        return 2;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }
}

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    ExpandableListAdapter mListAdapter;
    ListView                mExpListView;

    private int currentIndex;
    private int messageCount = 4;

    private List<CommentsLoader> prepareCommentListData() {
        List<CommentsLoader> listData = new ArrayList<CommentsLoader>();

        listData.add(new SimpleCommentsLoader("Elena",
                "Comment text 1",
                new Date(),
                getResources().getDrawable(R.drawable.news1, null)));
        listData.add(new SimpleCommentsLoader("Elena 1",
                "Comment text 2",
                new Date(),
                getResources().getDrawable(R.drawable.news1, null)));
        listData.add(new SimpleCommentsLoader("Elena 2",
                "Comment text 3",
                new Date(),
                getResources().getDrawable(R.drawable.news1, null)));
        listData.add(new SimpleCommentsLoader("Elena 3",
                "Comment text 4",
                new Date(),
                getResources().getDrawable(R.drawable.news1, null)));
        listData.add(new SimpleCommentsLoader("Elena 4",
                "Comment text 5",
                new Date(),
                getResources().getDrawable(R.drawable.news1, null)));

        return listData;
    }

    private List<NewsLoader> prepareNewsListData() {
         List<NewsLoader> listData = new ArrayList<NewsLoader>();

        listData.add(new SimpleNewsLoader("What F1 Cars Would Look Like",
                "There once was a time when the racing world was ruled by savage beasts. They were" +
                        " captured just before the snowy season, when noble brave men had one winter" +
                        " to tame this creature. After months of championship battle, a handful of the " +
                        "best animals were kept for another winter of training, while the others were set free again. ",
                getResources().getDrawable(R.drawable.news1, null)));

        listData.add(new SimpleNewsLoader("The Bloody Illegal World of Sand Mining",
                "Photographer Adam Ferguson documents the environmental and human costs of illegal" +
                        " sand mining in India where rapid growth fuels a sometimes violent black market" +
                        " for one of the most basic raw building materials.",
                getResources().getDrawable(R.drawable.news2, null)));

        listData.add(new SimpleNewsLoader("Your Phone Aint as Safe as You Think",
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

        final View fragamentView = inflater.inflate(R.layout.fragment_main, container, false);

        mExpListView = (ListView)fragamentView.findViewById(R.id.commentsListView);

        mListAdapter = new ExpandableListAdapter(fragamentView.getContext(),
                prepareNewsListData(), prepareCommentListData());

        final View headerView = inflater.inflate(R.layout.header, container, false);
        final View footerView = inflater.inflate(R.layout.footer, container, false);

        ImageView imageView = (ImageView) headerView.findViewById(R.id.imageView2);

        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setAdjustViewBounds(true);
        imageView.setImageResource(R.drawable.rootspb1);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_OUTSIDE == event.getAction())
                {
                    return false;
                }
                return false;
            }
        });

        mExpListView.addHeaderView(headerView);
        mExpListView.addFooterView(footerView);

        // setting list adapter
        mExpListView.setAdapter(mListAdapter);

        return fragamentView;
    }
}
