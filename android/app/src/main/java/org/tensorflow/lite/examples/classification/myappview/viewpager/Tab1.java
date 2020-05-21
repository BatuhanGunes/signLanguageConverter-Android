package org.tensorflow.lite.examples.classification.myappview.viewpager;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import org.tensorflow.lite.examples.classification.R;

import pl.droidsonroids.gif.GifImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tab1#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class Tab1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab1.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab1 newInstance(String param1, String param2) {
        Tab1 fragment = new Tab1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public Tab1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab1, container, false);
    }


    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {

        GifImageView gifImageView = (GifImageView) view.findViewById(R.id.gifImageViewSign);

        Button button_A = (Button) view.findViewById(R.id.buttonA);
        Button button_B = (Button) view.findViewById(R.id.buttonB);
        Button button_C = (Button) view.findViewById(R.id.buttonC);
        Button button_CC = (Button) view.findViewById(R.id.buttonCC);
        Button button_D = (Button) view.findViewById(R.id.buttonD);
        Button button_E = (Button) view.findViewById(R.id.buttonE);
        Button button_F = (Button) view.findViewById(R.id.buttonF);
        Button button_G = (Button) view.findViewById(R.id.buttonG);
        Button button_GG = (Button) view.findViewById(R.id.buttonGG);
        Button button_H = (Button) view.findViewById(R.id.buttonH);
        Button button_I = (Button) view.findViewById(R.id.buttonI);
        Button button_II = (Button) view.findViewById(R.id.buttonII);
        Button button_J = (Button) view.findViewById(R.id.buttonJ);
        Button button_K = (Button) view.findViewById(R.id.buttonK);
        Button button_L = (Button) view.findViewById(R.id.buttonL);
        Button button_M = (Button) view.findViewById(R.id.buttonM);

        button_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_a);
            }
        });

        button_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_b);
            }
        });

        button_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_c);
            }
        });

        button_CC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_cc);
            }
        });

        button_D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_d);
            }
        });

        button_E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_e);
            }
        });

        button_F.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_f);
            }
        });

        button_G.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_g);
            }
        });

        button_GG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_gg);
            }
        });

        button_H.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_h);
            }
        });

        button_I.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_i);
            }
        });

        button_II.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_ii);
            }
        });

        button_J.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_j);
            }
        });

        button_K.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_k);
            }
        });

        button_L.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_l);
            }
        });

        button_M.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_m);
            }
        });
    }
}
