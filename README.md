# KeyboardLib

The below steps implentation can be found in "ExampleActivity.java". "ExampleActivity.java" from package "com.gagan.keyboardlib.demo" is safe to delete.


This Library will give you callback whenever your keyboard is opened or closed.

To use the library you need to follow steps (steps are used in ExampleActivity.java)
1) Make Instantiate of  "MyKeyboard" class like shown below

        public class ExampleActivity extends Activity {
            
            private MyKeyboard keyboard;
            
            @Override
            	protected void onCreate(Bundle savedInstanceState) {
            		super.onCreate(savedInstanceState);
            		setContentView(R.layout.activity_main);
            		...
            		keyboard = new MyKeyboard(ExampleActivity.this, root, new KeyboardListener() {
            			@Override
            			public void isKeyboardShowing(boolean isShowing) {
            	    ...
            			}
            		});
                  ...         		
            	}
              ...
              }
              
              
2) To hide or show any Field DO NOT USE VIEW.VISIBLE OR VIEW.GONE . Instead use keyboard method setVisibility(int,View);

    example :- keyboard.setVisibility(View.GONE , etName,etUsername,etPassword,tvForgotPassword);

3) To show keybord use keyboard.showKeyboard(View).

    keyboard.showKeyboard(etUsername); // etUsername is the View on which you want to request focus 

4) To show keybord use keyboard.hideKeyboard().

    keyboard.showKeyboard();
    
    

If anybody get any issue while using. Please let me know.

    ---------------------------------------THE END------------------------------------------------------------
