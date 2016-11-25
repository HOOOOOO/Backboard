package com.tumblr.backboard.example;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.facebook.rebound.SpringSystem;
import com.tumblr.backboard.Actor;
import com.tumblr.backboard.MotionProperty;
import com.tumblr.backboard.imitator.Imitator;

/**
 * Demonstrates a draggable view that bounces back when released.
 * <p/>
 * Created by ericleong on 5/7/14.
 */
public class MoveFragment extends Fragment {

	private View mCircleV;
	private int mTrackStrategy, mFollowStrategy;
	private Actor mActor;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_move, container, false);
		mCircleV = rootView.findViewById(R.id.circle);

		mTrackStrategy = Imitator.TRACK_DELTA;
		mFollowStrategy = Imitator.FOLLOW_SPRING;
		setMoveActor();

		RadioGroup trackRg = (RadioGroup) rootView.findViewById(R.id.track_rg);
		trackRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				mTrackStrategy = checkedId;
				setMoveActor();
			}
		});

		RadioGroup followRg = (RadioGroup) rootView.findViewById(R.id.follow_rg);
		followRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				mFollowStrategy = checkedId - 2;
				setMoveActor();
			}
		});

		return rootView;
	}

	private void setMoveActor(){
		if (mActor != null){
			mActor.getMotions().clear();
		}
		mActor = new Actor.Builder(SpringSystem.create(), mCircleV)
				// Track是按下时是否移到中点 Follow是是否立即跟随
				.addTranslateMotion(mTrackStrategy, mFollowStrategy, MotionProperty.X)
				.addTranslateMotion(mTrackStrategy, mFollowStrategy, MotionProperty.Y)
				.build();
	}
}
