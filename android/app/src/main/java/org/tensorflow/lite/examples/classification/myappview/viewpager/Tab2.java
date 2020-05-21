package org.tensorflow.lite.examples.classification.myappview.viewpager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.tensorflow.lite.examples.classification.R;

import pl.droidsonroids.gif.GifImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tab2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab2 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Tab2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab2.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab2 newInstance(String param1, String param2) {
        Tab2 fragment = new Tab2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        return inflater.inflate(R.layout.fragment_tab2, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {

        GifImageView gifImageView = (GifImageView) view.findViewById(R.id.gifImageViewSignTab2);

        Button button_N = (Button) view.findViewById(R.id.buttonN);
        Button button_O = (Button) view.findViewById(R.id.buttonO);
        Button button_OO = (Button) view.findViewById(R.id.buttonOO);
        Button button_P = (Button) view.findViewById(R.id.buttonP);
        Button button_R = (Button) view.findViewById(R.id.buttonR);
        Button button_S = (Button) view.findViewById(R.id.buttonS);
        Button button_SS = (Button) view.findViewById(R.id.buttonSS);
        Button button_T = (Button) view.findViewById(R.id.buttonT);
        Button button_U = (Button) view.findViewById(R.id.buttonU);
        Button button_UU = (Button) view.findViewById(R.id.buttonUU);
        Button button_V = (Button) view.findViewById(R.id.buttonV);
        Button button_Y = (Button) view.findViewById(R.id.buttonY);
        Button button_Z = (Button) view.findViewById(R.id.buttonZ);

        button_N.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_n);
            }
        });

        button_O.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_o);
            }
        });

        button_OO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_oo);
            }
        });

        button_P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_p);
            }
        });

        button_R.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_r);
            }
        });

        button_S.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_s);
            }
        });

        button_SS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_ss);
            }
        });

        button_T.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_t);
            }
        });

        button_U.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_u);
            }
        });

        button_UU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_uu);
            }
        });

        button_V.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_v);
            }
        });

        button_Y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_y);
            }
        });

        button_Z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_z);
            }
        });
    }
}
