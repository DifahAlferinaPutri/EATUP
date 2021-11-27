package com.example.eatup.ui.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eatup.MainActivity;
import com.example.eatup.R;
import com.example.eatup.SliderAdapter;
import com.example.eatup.databinding.FragmentHomeBinding;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class HomeFragment extends Fragment {

    Button btn_japan, btn_dessert, btn_coffee, btn_pastry, btn_korean, btn_fastFood;

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    SliderView sliderView;
    Toolbar toolbar;
    MainActivity mainActivity;
    TextView textView;
    int[] images = {R.drawable.psuggest,
            R.drawable.japan,
            R.drawable.korea,
            };

    RecyclerView cardRecycler;
    RecyclerView.Adapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sliderView = root.findViewById(R.id.image_slider);

        SliderAdapter sliderAdapter = new SliderAdapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();
        //Hooks
//        cardRecycler = root.findViewById(R.id.my_recycler);
//        cardRecycler();

//       ((MainActivity) getActivity()).FragmentMethod();
        btn_japan = (Button) root.findViewById(R.id.button10);
        btn_japan.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v){

                SharedPreferences preferences =
                        getActivity().getSharedPreferences("com.example.eatup.PREFS", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putInt("TYPE", 1);
                editor.apply();

                Navigation.findNavController(root)
                        .navigate(R.id.nav_type);

            }
        });
        btn_dessert = (Button) root.findViewById(R.id.button11);
        btn_dessert.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v){

                SharedPreferences preferences =
                        getActivity().getSharedPreferences("com.example.eatup.PREFS", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putInt("TYPE", 2);
                editor.apply();

                Navigation.findNavController(root)
                        .navigate(R.id.nav_type);

            }
        });
        btn_coffee = (Button) root.findViewById(R.id.button13);
        btn_coffee.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v){

                SharedPreferences preferences =
                        getActivity().getSharedPreferences("com.example.eatup.PREFS", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putInt("TYPE", 3);
                editor.apply();

                Navigation.findNavController(root)
                        .navigate(R.id.nav_type);

            }
        });
        btn_pastry = (Button) root.findViewById(R.id.button14);
        btn_pastry.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v){

                SharedPreferences preferences =
                        getActivity().getSharedPreferences("com.example.eatup.PREFS", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putInt("TYPE", 4);
                editor.apply();

                Navigation.findNavController(root)
                        .navigate(R.id.nav_type);

            }
        });
        btn_korean = (Button) root.findViewById(R.id.button9);
        btn_korean.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v){

                SharedPreferences preferences =
                        getActivity().getSharedPreferences("com.example.eatup.PREFS", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putInt("TYPE", 5);
                editor.apply();

                Navigation.findNavController(root)
                        .navigate(R.id.nav_type);

            }
        });

        btn_fastFood = (Button) root.findViewById(R.id.button16);
        btn_fastFood.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v){

                SharedPreferences preferences =
                        getActivity().getSharedPreferences("com.example.eatup.PREFS", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putInt("TYPE", 6);
                editor.apply();

                Navigation.findNavController(root)
                        .navigate(R.id.nav_type);

            }
        });

        return root;
    }

//    private void cardRecycler() {
//
//        //All Gradients
//        GradientDrawable gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffd4cbe5, 0xffd4cbe5});
//        GradientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xff7adccf, 0xff7adccf});
//        GradientDrawable gradient3 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xfff7c59f, 0xFFf7c59f});
//        GradientDrawable gradient4 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffb8d7f5, 0xffb8d7f5});
//
//
//        cardRecycler.setHasFixedSize(true);
//        cardRecycler.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//
//        ArrayList<cardHelper> phonelocations = new ArrayList<>();
//        phonelocations.add(new cardHelper(gradient1, R.drawable.japanfood, "japanfood"));
//        phonelocations.add(new cardHelper(gradient3, R.drawable.dessert, "dessert"));
//        phonelocations.add(new cardHelper(gradient2, R.drawable.pastry, "pastry"));
//        phonelocations.add(new cardHelper(gradient4, R.drawable.coffe, "coffe"));
//
//
//        adapter = new adapterCard(phonelocations,this);
//        cardRecycler.setAdapter(adapter);
//
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}