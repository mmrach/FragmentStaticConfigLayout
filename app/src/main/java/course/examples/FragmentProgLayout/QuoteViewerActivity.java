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
	//El TitlesFRagment no lo declaramos porque en tiempo de ejecución no accedemos a el.
	//Es el usuario el que actua sobre él y nosotros capturamos el click de la selección, pero no lo llammamos.

	//Declaramos el TAG para los log.d
	private static final String TAG = "MainActivity";

	//Para mantener el índice del Title seleccionado
	private int currentIndex=-1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Log.d(TAG, getClass().getSimpleName() + ":Begin of onCreate();");

		//Cargamos los arrays de títulos y citas, en este caso desde arrays en Recursos.
		mTitleArray = getResources().getStringArray(R.array.Titles);
		mQuoteArray = getResources().getStringArray(R.array.Quotes);

		//Establecemos el layout principal
		setContentView(R.layout.main);

		//Instanciamos el Fragment para mostrar las citas. Usamos el getFragmentManager()
		mQuotesFragment = (QuotesFragment) getFragmentManager().findFragmentById(R.id.details);
		Log.d(TAG, getClass().getSimpleName() + ":end of onCreate()");
	}

	//-----------------------------------------------------------------------
	//Implementamos los métodos de la interfaz ------------------------------
		public void onListSelection(int index) {
			//Este es el método que tenemos que codificar de la interfaz que hemos implementado
			//Lo llamaremos cuando recibamos el callback onListItemClick en la lista del TilesFragmente
			//Entonces lo que tenemos que hacer es pedire al QuotesFragment que muestre la cita
			//para el indice que llega, sólo si no se está mostrando ya, claro.
			Log.d(TAG, getClass().getSimpleName() + ":onListSelection() interface method");

			//Cuando el usuario selecciona un Title de la lista actualizamos su índice.
			currentIndex = index;

			//Preguntamos al fragment Quotes que índice está mostrando y si es distinto del
			//que el usuario acaba de selecionar le indicamos que lo muestre.
			if (mQuotesFragment.getShownIndex() != index) {
				//Pasamos el texto de la cita (Quote) a mostrar
				mQuotesFragment.showQuote(index,mQuoteArray[index]);
			}
		}

		public String[] getTitles(){
			//Este método es llamado por el Titles Fragment para obtener la lista de titulos a añadir en
			//el ListView de titulos
			Log.d(TAG, getClass().getSimpleName() + ":Getting Titles() interface method");
			return mTitleArray;
		}
	//----------------------------------------------------------------------
	//-----------------------------------------------------------------------


	@Override
	protected void onSaveInstanceState(Bundle outState) {
		//Instanciando este callback podemos guardar información en el Bundle que nos pasa el sistema
		//ante una destrucción de la activiti (por rotación por ejemplo)
		super.onSaveInstanceState(outState);
		//Guardamos el currentIdex
		outState.putInt("currentIndex",currentIndex);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		//Si instanciamos este método podemos recuperar los datos que vienen en el Bundle.
		super.onRestoreInstanceState(savedInstanceState);

		//Mostramos un mensaje en fucnión de la orientación que tiene el dispositivo.
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ) {
			Toast.makeText(this,"Estamos en Portrait Mode",Toast.LENGTH_SHORT).show();
		}
		else{
			Toast.makeText(this,"Estamos en Landscape Mode",Toast.LENGTH_SHORT).show();
		}

		//Recuperamos el current index del Title seleccionado.
		currentIndex=savedInstanceState.getInt("currentIndex");
		//Si no se había seleccionado nada, el current index seguirá siendo -1 como se inicializó
		if (currentIndex!= -1) {
			//Indicamos al Quotes Fragment que muestre la cita correspondiente.
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