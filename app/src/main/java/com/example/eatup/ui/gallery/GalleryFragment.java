package com.example.eatup.ui.gallery;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.eatup.R;
import com.example.eatup.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {
    private Toolbar toolbar;
    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //Profil preferences
        SharedPreferences preferences =
                getActivity().getSharedPreferences("com.example.eatup.PREFS", getActivity().MODE_PRIVATE);
        String email = preferences.getString("EMAIL", "");
        String first_name = preferences.getString("FIRST_NAME", "");
        String last_name = preferences.getString("LAST_NAME", "");
        TextView profileName = root.findViewById((R.id.profileName));
        profileName.setText(first_name+" "+last_name);
        TextView profileEmail = root.findViewById((R.id.profileEmail));
        profileEmail.setText(email);

        int red = Color.parseColor("#70D8F8");
        toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(red);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}