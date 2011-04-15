package irdc.ex03_01;

import android.app.Activity;
import android.os.Bundle;

public class WhacAMole extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ImageManager.init(this);
		resetGame();
	}

	public void resetGame() {
		setContentView(new WhacAmoleView(this));
	}
}