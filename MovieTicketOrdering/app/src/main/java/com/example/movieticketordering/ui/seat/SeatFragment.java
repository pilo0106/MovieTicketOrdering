package com.example.movieticketordering.ui.seat;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.movieticketordering.R;
import com.example.movieticketordering.databinding.FragmentSeatBinding;
import com.example.movieticketordering.ui.order.OrderFragment;
import com.example.movieticketordering.ui.print.PrintFragment;

public class SeatFragment extends Fragment {

    private OrderFragment.SharedViewModel sharedViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(OrderFragment.SharedViewModel.class);
    }


    private FragmentSeatBinding binding;
    boolean c11Pressed = false;
    boolean c12Pressed = false;
    boolean c13Pressed = false;
    boolean c21Pressed = false;
    boolean c22Pressed = false;
    boolean c23Pressed = false;
    boolean c31Pressed = false;
    boolean c32Pressed = false;
    boolean c33Pressed = false;

    int  ticketTotal = 0;
    int vipTicketTotal = 0;

    private void updateButtonColor() {
        if (c11Pressed) {
            binding.c11.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.white)));
            binding.c11.setTextColor(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        } else {
            binding.c11.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.holo_purple)));
            binding.c11.setTextColor(ColorStateList.valueOf(getResources().getColor(android.R.color.white)));
        }
        if (c12Pressed) {
            binding.c12.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.white)));
            binding.c12.setTextColor(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        } else {
            binding.c12.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.holo_purple)));
            binding.c12.setTextColor(ColorStateList.valueOf(getResources().getColor(android.R.color.white)));
        }
        if (c13Pressed) {
            binding.c13.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.white)));
            binding.c13.setTextColor(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        } else {
            binding.c13.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.holo_purple)));
            binding.c13.setTextColor(ColorStateList.valueOf(getResources().getColor(android.R.color.white)));
        }
        if (c21Pressed) {
            binding.c21.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.white)));
            binding.c21.setTextColor(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        } else {
            binding.c21.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.holo_purple)));
            binding.c21.setTextColor(ColorStateList.valueOf(getResources().getColor(android.R.color.white)));
        }
        if (c22Pressed) {
            binding.c22.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.white)));
            binding.c22.setTextColor(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        } else {
            binding.c22.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.holo_purple)));
            binding.c22.setTextColor(ColorStateList.valueOf(getResources().getColor(android.R.color.white)));
        }
        if (c23Pressed) {
            binding.c23.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.white)));
            binding.c23.setTextColor(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        } else {
            binding.c23.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.holo_purple)));
            binding.c23.setTextColor(ColorStateList.valueOf(getResources().getColor(android.R.color.white)));
        }
        if (c31Pressed) {
            binding.c31.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.white)));
            binding.c31.setTextColor(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        } else {
            binding.c31.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.holo_purple)));
            binding.c31.setTextColor(ColorStateList.valueOf(getResources().getColor(android.R.color.white)));
        }
        if (c32Pressed) {
            binding.c32.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.white)));
            binding.c32.setTextColor(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        } else {
            binding.c32.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.holo_purple)));
            binding.c32.setTextColor(ColorStateList.valueOf(getResources().getColor(android.R.color.white)));
        }
        if (c33Pressed) {
            binding.c33.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.white)));
            binding.c33.setTextColor(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        } else {
            binding.c33.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.holo_purple)));
            binding.c33.setTextColor(ColorStateList.valueOf(getResources().getColor(android.R.color.white)));
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        sharedViewModel.getTicketTotal().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer tTicket) {
                // Update UI with the new value of tTicket
                ticketTotal = tTicket;
                TextView tv1 = (TextView) container.findViewById(R.id.ticketSum);
                tv1.setText(String.valueOf(tTicket));
            }
        });
        sharedViewModel.getVipTicketTotal().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer vipTotal) {
                vipTicketTotal = vipTotal;
                TextView tv1 = (TextView) container.findViewById(R.id.ticketSumVIP);
                tv1.setText(String.valueOf(vipTotal));
            }
        });

        SeatViewModel seatViewModel =
                new ViewModelProvider(this).get(SeatViewModel.class);

        binding = FragmentSeatBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        updateButtonColor();

        binding.c11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (c11Pressed) {
                    c11Pressed = false;
                    vipTicketTotal++;
                } else if (vipTicketTotal > 0) {
                    c11Pressed = true;
                    vipTicketTotal--;
                }
                updateButtonColor(); // Update the button color
                sharedViewModel.setTicketTotal(ticketTotal,vipTicketTotal); // Update the shared ViewModel
            }
        });

        binding.c12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (c12Pressed) {
                    c12Pressed = false;
                    ticketTotal++;
                } else if (ticketTotal > 0) {
                    c12Pressed = true;
                    ticketTotal--;
                }
                updateButtonColor(); // Update the button color
                sharedViewModel.setTicketTotal(ticketTotal,vipTicketTotal); // Update the shared ViewModel
            }
        });

        binding.c13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (c13Pressed) {
                    c13Pressed = false;
                    ticketTotal++;
                } else if (ticketTotal > 0) {
                    c13Pressed = true;
                    ticketTotal--;
                }
                updateButtonColor(); // Update the button color
                sharedViewModel.setTicketTotal(ticketTotal,vipTicketTotal); // Update the shared ViewModel
            }
        });

        binding.c21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (c21Pressed) {
                    c21Pressed = false;
                    vipTicketTotal++;
                } else if (vipTicketTotal > 0) {
                    c21Pressed = true;
                    vipTicketTotal--;
                }
                updateButtonColor(); // Update the button color
                sharedViewModel.setTicketTotal(ticketTotal,vipTicketTotal); // Update the shared ViewModel
            }
        });

        binding.c22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (c22Pressed) {
                    c22Pressed = false;
                    ticketTotal++;
                } else if (ticketTotal > 0) {
                    c22Pressed = true;
                    ticketTotal--;
                }
                updateButtonColor(); // Update the button color
                sharedViewModel.setTicketTotal(ticketTotal, vipTicketTotal); // Update the shared ViewModel
            }
        });

        binding.c23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (c23Pressed) {
                    c23Pressed = false;
                    ticketTotal++;
                } else if (ticketTotal > 0) {
                    c23Pressed = true;
                    ticketTotal--;
                }
                updateButtonColor(); // Update the button color
                sharedViewModel.setTicketTotal(ticketTotal,vipTicketTotal); // Update the shared ViewModel
            }
        });

        binding.c31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (c31Pressed) {
                    c31Pressed = false;
                    vipTicketTotal++;
                } else if (vipTicketTotal > 0) {
                    c31Pressed = true;
                    vipTicketTotal--;
                }
                updateButtonColor(); // Update the button color
                sharedViewModel.setTicketTotal(ticketTotal,vipTicketTotal); // Update the shared ViewModel
            }
        });

        binding.c32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (c32Pressed) {
                    c32Pressed = false;
                    ticketTotal++;
                } else if (ticketTotal > 0) {
                    c32Pressed = true;
                    ticketTotal--;
                }
                updateButtonColor(); // Update the button color
                sharedViewModel.setTicketTotal(ticketTotal,vipTicketTotal); // Update the shared ViewModel
            }
        });

        binding.c33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (c33Pressed) {
                    c33Pressed = false;
                    ticketTotal++;
                } else if (ticketTotal > 0) {
                    c33Pressed = true;
                    ticketTotal--;
                }
                updateButtonColor(); // Update the button color
                sharedViewModel.setTicketTotal(ticketTotal,vipTicketTotal); // Update the shared ViewModel
            }
        });

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderFragment OrderFragment = new OrderFragment();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.layout, OrderFragment);
                transaction.commit();
            }
        });

        binding.printTIcket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrintFragment PrintFragment = new PrintFragment();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.layout, PrintFragment);
                transaction.commit();
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