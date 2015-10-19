package com.paul_nikki.cse5236.appointmentpal;

import android.support.v4.app.Fragment;

public class TimeFragment extends Fragment {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_time, container, false);
        return v;
    }

}