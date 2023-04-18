package edu.uw.tcss450.uiandnavigationlab.ui.blog;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.uw.tcss450.uiandnavigationlab.R;
import edu.uw.tcss450.uiandnavigationlab.databinding.FragmentBlogListBinding;


public class BlogListFragment extends Fragment {
    private BlogListViewModel mModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blog_list, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mModel = new ViewModelProvider(getActivity()).get(BlogListViewModel.class);
        mModel.connectGet();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        FragmentBlogListBinding binding = FragmentBlogListBinding.bind(getView());

        mModel.addBlogListObserver(getViewLifecycleOwner(), blogList -> {
            if (!blogList.isEmpty()) {
                binding.listRoot.setAdapter(
                        new BlogRecyclerViewAdapter(blogList)
                );
                binding.layoutWait.setVisibility(View.GONE);
            }
        });
    }



}