package com.gagan.keyboardlib;

import android.content.Context;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class MyKeyboard {

	private boolean threadStarted = false;
	private boolean isProcessing = false;
	private View root; 
	private KeyboardListener keyboardListener;
	private EditText input = null;
	private InputMethodManager inputMethodManager ;
	
	
	public MyKeyboard(Context context ,View root,KeyboardListener  listener) {
		this.root = root;
		inputMethodManager = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
		this.keyboardListener = listener;
	}
	
	public void addGlobalCallbackListener(){
		if(!threadStarted){
			threadStarted = true;
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					root.getViewTreeObserver().addOnGlobalLayoutListener(global);
					threadStarted = false;
				}
			}).start();		
		}
	}
	
	
	private OnGlobalLayoutListener global=new OnGlobalLayoutListener() {
		@Override
		public void onGlobalLayout() {
			if(!isProcessing){
				isProcessing = true;
				 int heightDiff = root.getRootView().getHeight() - root.getHeight();
//				 	if(heightDiff>100){
////			        	keyboard is showing
//			        }else{
////			        	keyboard is not showing
////			        	hideFields();
//			        }	
				 	
				 	keyboardListener.isKeyboardShowing(heightDiff>100);
			        isProcessing= false;
			}
		}
	};
	
	
	private void removeGlobalChangeListener(){
		root.getViewTreeObserver().removeOnGlobalLayoutListener(global);
	}
	
	public void stopKeyboardCallbackIfWorking(){
		if(isProcessing){
			removeGlobalChangeListener();
		}
	} 
	
	public void setVisibility(int visibility,View... views){
		removeGlobalChangeListener();
		for(int  i=0;i<views.length ;i++){
			views[i].setVisibility(visibility);
		}
		addGlobalCallbackListener();
	}
	
	public void showKeyboard(EditText edittext){
		input = edittext;
		if(inputMethodManager!=null)
		{
			inputMethodManager.showSoftInput(edittext, InputMethodManager.SHOW_IMPLICIT);
		}
		
		addGlobalCallbackListener();
	}

	public void hideKeyboard(){
		if (input==null) return;
		input.setInputType(0);
		    inputMethodManager.hideSoftInputFromWindow(input.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
	}
	
}
