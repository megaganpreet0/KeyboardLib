package com.gagan.keyboardlib.demo;
 
import com.gagan.keyboardlib.KeyboardListener;
import com.gagan.keyboardlib.MyKeyboard;
import com.gagan.keyboardlib.R;
import com.gagan.keyboardlib.R.id;
import com.gagan.keyboardlib.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ExampleActivity extends Activity implements OnClickListener {

	private Button btnSignup, btnLogin;
	private boolean state = false;
	private View root;
	private EditText etName,etUsername,etPassword;
	private TextView tvForgotPassword;
	private MyKeyboard keyboard;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		root = findViewById(R.id.root);
		keyboard = new MyKeyboard(ExampleActivity.this, root, new KeyboardListener() {
			
			@Override
			public void isKeyboardShowing(boolean isShowing) {
				if(!isShowing)
				hideFields();
			}
		});
		
		initialiseLayout();
		btnLogin.setOnClickListener(this);
		btnSignup.setOnClickListener(this);
		root.setOnClickListener(this);
		tvForgotPassword.setOnClickListener(this);
	}

	private void initialiseLayout() {
		etName= (EditText) findViewById(R.id.etName);
		etUsername= (EditText) findViewById(R.id.etUsername);;
		etPassword = (EditText) findViewById(R.id.etPassword);;
		btnSignup = (Button) findViewById(R.id.btnSignup);;
		btnLogin = (Button) findViewById(R.id.btnLogin);;
		tvForgotPassword= (TextView) findViewById(R.id.tvForgotPassword);;
	}

	


	protected void hideFields() {
		keyboard.setVisibility(View.GONE , etName,etUsername,etPassword,tvForgotPassword);
	}
	

	
	protected void conditionSignup() {
		keyboard.setVisibility(View.VISIBLE, etName,etUsername,etPassword);
		keyboard.setVisibility(View.GONE,tvForgotPassword);
		etName.requestFocus();		
		keyboard.showKeyboard(etName);
	}
	

	protected void conditionLogin() {
		keyboard.setVisibility(View.GONE, etName);
		keyboard.setVisibility(View.VISIBLE, etUsername,etPassword,tvForgotPassword);
		etUsername.requestFocus();
		keyboard.showKeyboard(etUsername);
	}
	
	
	
	@Override
	public void onClick(View v) {

		keyboard.stopKeyboardCallbackIfWorking();
		
		switch (v.getId()) {
		case R.id.btnSignup:
			state = true;
			conditionSignup();
			break;

		case R.id.btnLogin:
			state = false;
			conditionLogin();
			break;
			

		case R.id.root:
			hideFields();
			keyboard.hideKeyboard();
			break;
			
			
		case R.id.tvForgotPassword:
			break;
			
		}
	}
	
	
}
