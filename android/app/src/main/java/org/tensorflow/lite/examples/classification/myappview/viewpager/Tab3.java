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
 * Use the {@link Tab3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab3 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Tab3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab3.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab3 newInstance(String param1, String param2) {
        Tab3 fragment = new Tab3();
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
        return inflater.inflate(R.layout.fragment_tab3, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {

        GifImageView gifImageView = (GifImageView) view.findViewById(R.id.gifImageViewSignTab3);

        Button button_0 = (Button) view.findViewById(R.id.button0);
        Button button_1 = (Button) view.findViewById(R.id.button1);
        Button button_2 = (Button) view.findViewById(R.id.button2);
        Button button_3 = (Button) view.findViewById(R.id.button3);
        Button button_4 = (Button) view.findViewById(R.id.button4);
        Button button_5 = (Button) view.findViewById(R.id.button5);
        Button button_6 = (Button) view.findViewById(R.id.button6);
        Button button_7 = (Button) view.findViewById(R.id.button7);
        Button button_8 = (Button) view.findViewById(R.id.button8);
        Button button_9 = (Button) view.findViewById(R.id.button9);
        Button button_10 = (Button) view.findViewById(R.id.button10);
        Button button_100 = (Button) view.findViewById(R.id.button100);

        button_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_0);
            }
        });

        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_1);
            }
        });

        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_2);
            }
        });

        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_3);
            }
        });

        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_4);
            }
        });

        button_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_5);
            }
        });

        button_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_6);
            }
        });

        button_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_7);
            }
        });

        button_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_8);
            }
        });

        button_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_9);
            }
        });

        button_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_10);
            }
        });

        button_100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifImageView.setImageResource(R.drawable.sign_100);
            }
        });
    }
}
