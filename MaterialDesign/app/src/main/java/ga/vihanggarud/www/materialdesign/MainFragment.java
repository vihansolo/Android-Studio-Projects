package ga.vihanggarud.www.materialdesign;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    Button btnFade;
    Button btnRotate;
    Button btnScale;
    Button btnSlide;
    Button btnSharedElement;
    Button btnBottomSheet;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        findViews(view);
    }

    private void findViews(View view) {

        btnFade = view.findViewById(R.id.btnFade);
        btnRotate = view.findViewById(R.id.btnRotate);
        btnScale = view.findViewById(R.id.btnScale);
        btnSlide = view.findViewById(R.id.btnSlide);
        btnSharedElement = view.findViewById(R.id.btnSharedElement);
        btnBottomSheet = view.findViewById(R.id.btnBottomSheet);

        //Set onClickListeners

        btnFade.setOnClickListener(this);
        btnRotate.setOnClickListener(this);
        btnScale.setOnClickListener(this);
        btnSlide.setOnClickListener(this);
        btnSharedElement.setOnClickListener(this);
        btnBottomSheet.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {

//        switch (view.getId()) {
//
//            case R.id.btnFade :
//                startAnimationActivity(Constant.MI_ANIMATION_FADE);
//                break;
//
//            case R.id.btnScale :
//                startAnimationActivity(Constant.MI_ANIMATION_SCALE);
//                break;
//
//            case R.id.btnRotate :
//                startAnimationActivity(Constant.MI_ANIMATION_ROTATE);
//                break;
//
//            case R.id.btnSlide :
//                startAnimationActivity(Constant.MI_ANIMATION_SLIDE);
//                break;
//
//            case R.id.btnSharedElement :
//                startAnimationActivity(Constant.MI_ANIMATION_SHARED_ELEMENT);
//                break;
//
//            case R.id.btnBottomSheet :
//                break;
//
//            default:
//                break;
//        }
    }

//    private void startAnimationActivity(int AnimationType) {
//
//        Intent intent = new Intent(getActivity(), AnimationActivity.class);
//        Bundle bundle = new Bundle();
//
//        bundle.putInt(Constant.MSTR_ANIMATION_TYPE_KEY,AnimationType);
//        intent.putExtras(bundle);
//
//        startActivity(intent);
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
