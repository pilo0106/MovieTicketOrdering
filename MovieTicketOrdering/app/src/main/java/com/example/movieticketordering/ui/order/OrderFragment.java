package com.example.movieticketordering.ui.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.movieticketordering.MainActivity;
import com.example.movieticketordering.R;
import com.example.movieticketordering.databinding.FragmentOrderBinding;
import com.example.movieticketordering.ui.seat.SeatFragment;


public class OrderFragment extends Fragment {

    private FragmentOrderBinding binding;

    public static class SharedViewModel extends ViewModel {
        private static MutableLiveData<Integer> ticketTotal = new MutableLiveData<>();
        private static MutableLiveData<Integer> vipTicketTotal = new MutableLiveData<>();

        public static void setTicketTotal(int total, int vipTotal) {
            ticketTotal.setValue(total);
            vipTicketTotal.setValue(vipTotal);
        }

        public LiveData<Integer> getTicketTotal() {
            return ticketTotal;
        }
        public LiveData<Integer> getVipTicketTotal() {
            return vipTicketTotal;
        }

    }

    int Total = 0;
    int tTicket = 0;
    int tTicketV = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        OrderViewModel orderViewModel =
                new ViewModelProvider(this).get(OrderViewModel.class);

        binding = FragmentOrderBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.submitCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText re =  (EditText) container.findViewById(R.id.orR);
                EditText vi=  (EditText) container.findViewById(R.id.orV);
                EditText po =  (EditText) container.findViewById(R.id.orP);
                EditText dr =  (EditText) container.findViewById(R.id.orD);
                String Re = re.getText().toString();
                String Vi = vi.getText().toString();
                String Po = po.getText().toString();
                String Dr = dr.getText().toString();
                int Regular = Integer.parseInt(Re);
                int VIP = Integer.parseInt(Vi);
                int Popcorn = Integer.parseInt(Po);
                int Drink = Integer.parseInt(Dr);
                int tRegular = Regular * 12;
                int tVIP = VIP * 18;
                int tPopcorn = Popcorn * 15;
                int tDrink = Drink * 10;
                Total = tRegular + tVIP + tPopcorn + tDrink;

                tTicket = Regular;
                tTicketV = VIP;

                SharedViewModel.setTicketTotal(tTicket, tTicketV);

                TextView tv1 = (TextView) container.findViewById(R.id.intSum);
                tv1.setText("$"+String.valueOf(Total));

            }
        });

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(getActivity() instanceof MainActivity)) {
                    // Navigate to MainActivity
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    getActivity().finish(); // Finish the current Activity
                }
            }
        });

        binding.checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if Total is greater than 0
                if (Total > 0) {
                    // Create an instance of the new fragment
                    SeatFragment seatFragment = new SeatFragment();

                    // Get the FragmentManager
                    FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

                    // Begin the fragment transaction
                    FragmentTransaction transaction = fragmentManager.beginTransaction();

                    // Replace the current fragment with the new one
                    transaction.replace(R.id.layout, seatFragment);

                    // Commit the transaction
                    transaction.commit();
                } else {
                    // Display a Toast indicating that Total is not above 0
                    Toast.makeText(getActivity(), "Press the COMFIRM button first before proceeding.", Toast.LENGTH_SHORT).show();
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