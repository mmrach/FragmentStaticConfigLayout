package course.examples.FragmentProgLayout;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import course.examples.FragmentProgLayout.R;

public class QuoteViewerActivity extends Activity implements TitlesFragment.ListSelectionListener {

	public static String[] mTitleArray; //Array de títulos
	public static String[] mQuoteArray; //Array de citas.
	private QuotesFragment mQuotesFragment;  //Declaramos el Fragment para mostrar las citas.

	private static final String TAG = "MainActivity";

	private int currentIndex=-1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Log.d(TAG, getClass().getSimpleName() + ":Begin of onCreate();");

		//Cargamos los arrays de títulos y citas
		mTitleArray = getResources().getStringArray(R.array.Titles);
		mQuoteArray = getResources().getStringArray(R.array.Quotes);

		//Establecemos el layout principal
		setContentView(R.layout.main);

		//Instanciamos el Fragment para mostrar las citas. Usamos el getFragmentMangar()
		mQuotesFragment = (QuotesFragment) getFragmentManager().findFragmentById(R.id.details);
		Log.d(TAG, getClass().getSimpleName() + ":end of onCreate()");


	}

	//Implementamos los métodos de la interfaz ------------------------------
		public void onListSelection(int index) {
			//Este es el método que tenemos que codificar de la interfaz que hemos implementado
			//Lo llamaremos cuando recibamos el callback onListItemClick en la lista del TilesFragmente
			//Entonces lo que tenemos que hacer es pedire al QuotesFragment que muestre la cita
			//para el indice que llega, sólo si no se está mostrando ya, claro.
			Log.d(TAG, getClass().getSimpleName() + ":onListSelection() interface method");
			currentIndex = index;
			if (mQuotesFragment.getShownIndex() != index) {
				mQuotesFragment.showQuote(index,mQuoteArray[index]);
			}


		}

		public String[] getTitles(){
			//Este método es llamado por el fragment para obtener la lista de titulos a añadir en
			//el ListView de titulos
			Log.d(TAG, getClass().getSimpleName() + ":Getting Titles() interface method");
			return mTitleArray;
		}
	//----------------------------------------------------------------------


	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("currentIndex",currentIndex);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ) {
			Toast.makeText(this,"Estamos en Portrait Mode",Toast.LENGTH_SHORT).show();
		}
		else{
			Toast.makeText(this,"Estamos en Landscape Mode",Toast.LENGTH_SHORT).show();
		}

		currentIndex=savedInstanceState.getInt("currentIndex");
		if (currentIndex!= -1) {
			mQuotesFragment.showQuote(currentIndex,mQuoteArray[currentIndex]);
		}


	}

	@Override
	protected void onDestroy() {
		Log.d(TAG, getClass().getSimpleName() + ":entered onDestroy()");
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		Log.d(TAG, getClass().getSimpleName() + ":entered onPause()");
		super.onPause();
	}

	@Override
	protected void onRestart() {
		Log.d(TAG, getClass().getSimpleName() + ":entered onRestart()");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		Log.d(TAG, getClass().getSimpleName() + ":entered onResume()");
		super.onResume();
	}

	@Override
	protected void onStart() {
		Log.d(TAG, getClass().getSimpleName() + ":entered onStart()");
		super.onStart();
	}

	@Override
	protected void onStop() {
		Log.d(TAG, getClass().getSimpleName() + ":entered onStop()");
		super.onStop();
	}

}