package br.com.britosoft.login.activity;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.britosoft.login.R;
import br.com.britosoft.login.model.CrachaValida;
import br.com.britosoft.login.service.ServiceAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText et_cracha;
    private TextInputLayout input_layout_cracha;
    private Button btn_cracha;
    boolean teste;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        input_layout_cracha = (TextInputLayout) findViewById( R.id.input_layout_cracha );
        btn_cracha          = (Button) findViewById( R.id.btn_cracha );
        et_cracha           = (EditText) findViewById( R.id.et_cracha );

       // et_cracha.addTextChangedListener( new MyTextWatcher( et_cracha ) );

        btn_cracha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateCracha();
            }
        });


    }



    private boolean validateCracha(  ){

        if( et_cracha.getText().toString().trim().isEmpty() ){
            input_layout_cracha.setError( getString( R.string.crachaEmpty ) );
            requestFocus( et_cracha );
            teste = false;
        } else {
            input_layout_cracha.setErrorEnabled( false );

            ServiceAPI serviceAPI = ServiceAPI.retrofit.create( ServiceAPI.class );
            Call<CrachaValida> crachaValidaCall = serviceAPI.getRetorno( "C", et_cracha.getText().toString() );
            crachaValidaCall.enqueue(new Callback<CrachaValida>() {
                @Override
                public void onResponse(Call<CrachaValida> call, Response<CrachaValida> response) {
                    if( response.isSuccessful() ){

                        CrachaValida crachaValida = response.body();
                        if( crachaValida.getSuccess() == 1 ){

                            teste = true;

                            mensagemToast( "Possui cadastro" );

                            if( crachaValida.getAtivo() == -1 ){

                                Intent intent = new Intent( getApplicationContext(), CriaSenhaActivity.class );
                                intent.putExtra( "cracha", et_cracha.getText().toString() );
                                startActivity( intent );

                            }

                        }
                        else{

                            teste = false;
                            input_layout_cracha.setError( getString( R.string.crachaNot ) );
                            requestFocus( et_cracha );
                            mensagemToast( "Crachá não encontrado" );

                        }

                    }
                }

                @Override
                public void onFailure(Call<CrachaValida> call, Throwable t) {
                    mensagemToast( "Ocorreu um problema ao conectar: \n"+t.getMessage() );
                }
            });


        }

        return teste;

    }



    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.et_cracha:
                    validateCracha();
                    break;
            }
        }
    }

    private void mensagemToast( String mensagem ){

        Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();

    }
}
