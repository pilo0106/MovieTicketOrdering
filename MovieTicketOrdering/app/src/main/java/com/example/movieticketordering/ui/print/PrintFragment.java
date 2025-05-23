package com.example.movieticketordering.ui.print;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.movieticketordering.GodzillaDetail;
import com.example.movieticketordering.MainActivity;
import com.example.movieticketordering.Panda4Detail;
import com.example.movieticketordering.R;
import com.example.movieticketordering.TheFullGuyDetail;
import com.example.movieticketordering.VolleyBallDetail;
import com.example.movieticketordering.databinding.FragmentPrintBinding;
import com.example.movieticketordering.ui.order.OrderFragment;
import com.example.movieticketordering.ui.seat.SeatFragment;

public class PrintFragment extends Fragment {

    private TheFullGuyDetail.SharedViewModel fallGuy;
    private GodzillaDetail.SharedViewModel Godzilla;
    private Panda4Detail.SharedViewModel Panda4;
    private VolleyBallDetail.SharedViewModel Volley;
    public static class SharedViewModel extends ViewModel {
        private static MutableLiveData<String> movieTitle = new MutableLiveData<>();

        public static void setTicketTitle(String title) {
            movieTitle.setValue(title);
        }

        public LiveData<String> getTitle() {
            return movieTitle;
        }
    }

    String title="";

    private FragmentPrintBinding binding;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Godzilla = new ViewModelProvider(requireActivity()).get(GodzillaDetail.SharedViewModel.class);
        fallGuy = new ViewModelProvider(requireActivity()).get(TheFullGuyDetail.SharedViewModel.class);
        Panda4 = new ViewModelProvider(requireActivity()).get(Panda4Detail.SharedViewModel.class);
        Volley = new ViewModelProvider(requireActivity()).get(VolleyBallDetail.SharedViewModel.class);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PrintViewModel notificationsViewModel =
                new ViewModelProvider(this).get(PrintViewModel.class);

        binding = FragmentPrintBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        fallGuy.getTitle().observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        title = s;
                    }
                });
        Godzilla.getTitle().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                title = s;
            }
        });
        Panda4.getTitle().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                title = s;
            }
        });
        Volley.getTitle().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                title = s;
            }
        });


        binding.backBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SeatFragment SeatFragment = new SeatFragment();
                        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.layout, SeatFragment);
                        transaction.commit();
                    }
                });

        binding.saveTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Ticket saved!", Toast.LENGTH_SHORT).show();
                PrintFragment.SharedViewModel.setTicketTitle(title);
                if (!(getActivity() instanceof MainActivity)) {
                    // Navigate to MainActivity
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    getActivity().finish(); // Finish the current Activity
                }
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}