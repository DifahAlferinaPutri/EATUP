package com.example.eatup.ui.list;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eatup.R;
import com.example.eatup.databinding.FragmentListRestaurantBinding;
import com.example.eatup.ui.adapter.ListRestoAdapter;
import com.example.eatup.ui.model.ListResto;
import com.example.eatup.ui.model.RestaurantItem;
import com.example.eatup.ui.model.RestaurantResponse;
import com.example.eatup.ui.retrofit.PortalClient;
import com.example.eatup.ui.retrofit.TypeInformation;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TypeRestaurantFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TypeRestaurantFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TypeRestaurantFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TypeRestaurantFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TypeRestaurantFragment newInstance(String param1, String param2) {
        TypeRestaurantFragment fragment = new TypeRestaurantFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    RecyclerView rvListResto;
    private FragmentListRestaurantBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentListRestaurantBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ArrayList<ListResto> listResto = new ArrayList<ListResto>();
        ListRestoAdapter adapter = new ListRestoAdapter();

        rvListResto = root.findViewById(R.id.rvList2Resto);
        rvListResto.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        rvListResto.setLayoutManager(layoutManager);
        Log.d("v", "1");
        //Membuat Object client
        String API_BASE_URL = "http://172.20.10.6/EatUp/public/";

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder
                .client(httpClient.build())
                .build();

        PortalClient client = retrofit.create(PortalClient.class);
        //Type preferences
        SharedPreferences preferences =
                getActivity().getSharedPreferences("com.example.eatup.PREFS", getActivity().MODE_PRIVATE);
        int type = preferences.getInt("TYPE", -1);



//in your OnCreate() method
        TypeInformation typeInformation = new TypeInformation();
        typeInformation.setType(type);
        Call<RestaurantResponse> call = client.getTypeRestaurantResponse(typeInformation);

        Log.d("", String.valueOf(call));
        call.enqueue(new Callback<RestaurantResponse>() {
            @Override
            public void onResponse(Call<RestaurantResponse> call, Response<RestaurantResponse> response) {
                RestaurantResponse restaurantResponse = response.body();

                if(restaurantResponse != null){
                    List<RestaurantItem> listRestaurantItem = restaurantResponse.getRestaurant();

                    for(RestaurantItem restaurant : listRestaurantItem){
                        String uri = restaurant.getAvatar();  // where myresource (without the extension) is the file
                        int imageResource = getResources().getIdentifier(uri, null, getActivity().getPackageName());
                        Drawable resource =  getResources().getDrawable(imageResource);
                        ListResto resto = new ListResto(
                                restaurant.getId(),
                                restaurant.getRestaurantName(),
                                restaurant.getAddress(),
                                restaurant.getRating(),
                                restaurant.getStatus(),
                                resource
                        );
                        listResto.add(resto);
                    }
                }else{
                    Toast.makeText(getActivity().getApplicationContext(), "Gagal mendapatkan data restaurant", Toast.LENGTH_SHORT).show();
                }
                adapter.setListData(listResto);
            }

            @Override
            public void onFailure(Call<RestaurantResponse> call, Throwable t) {
                Log.d("H12345", t.getMessage());
                Toast.makeText(getActivity().getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });





        return root;
    }


}