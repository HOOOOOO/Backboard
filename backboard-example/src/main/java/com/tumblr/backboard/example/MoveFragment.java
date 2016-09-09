package com.tumblr.backboard.example;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_move, container, false);

		new Actor.Builder(SpringSystem.create(), rootView.findViewById(R.id.circle))
				// Track是按下时是否移到中点 Follow是是否立即跟随
				.addTranslateMotion(Imitator.TRACK_DELTA, Imitator.TRACK_DELTA, MotionProperty.X)
				.addTranslateMotion(Imitator.TRACK_DELTA, Imitator.TRACK_DELTA, MotionProperty.Y)
				.build();

		return rootView;
	}
}
