package br.com.britosoft.login.activity;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.britosoft.login.R;

public class CriaSenhaActivity extends AppCompatActivity {

    private TextInputLayout inputLayoutSenha;
    private TextInputLayout inputLayoutRepetirSenha;
    private Button btn_criar_senha;
    private EditText senha;
    private EditText reSenha;
    private TextView tv_cracha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cria_senha);
        setTitle( getString( R.string.titleCriarSenha ) );

        inputLayoutSenha = (TextInputLayout) findViewById( R.id.input_layout_senha );
        inputLayoutSenha = (TextInputLayout) findViewById( R.id.input_layout_repita_senha );
        btn_criar_senha  = (Button) findViewById( R.id.btn_criar_senha );
        senha = ( EditText ) findViewById( R.id.et_senha );
        reSenha = ( EditText ) findViewById( R.id.et_repita_senha );
        tv_cracha = (TextView) findViewById( R.id.tv_cracha );
        reSenha.addTextChangedListener( new MyTextWatcher( reSenha )  );
        senha.addTextChangedListener( new MyTextWatcher( senha )  );

        Bundle bundle = getIntent().getExtras();

        String cracha = bundle.getString( "cracha" );

        tv_cracha.setText( cracha );

        btn_criar_senha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vaidarSenha();
            }
        });


    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private void senhasIguais(){
        if( senha.getText().toString().equals( reSenha.getText().toString() ) ){
            btn_criar_senha.setEnabled(true);
        }else{
            inputLayoutRepetirSenha.setError( getString( R.string.senhaEmpty ) );
            inputLayoutSenha.setError( getString( R.string.senhaEmpty ) );
            btn_criar_senha.setEnabled( false );
        }
    }

    private void vaidarSenha(  ){

        if( senha.getText().toString().trim().isEmpty()){
            inputLayoutSenha.setError( getString( R.string.senhaEmpty ) );
            requestFocus( senha );
        }else if( reSenha.getText().toString().trim().isEmpty() ){
            inputLayoutRepetirSenha.setError( getString( R.string.senhaEmpty ) );
            requestFocus( reSenha );
        }else{

            if( senha.getText().toString().equals( reSenha.getText().toString() ) ){
                //abrir activity
            }else{

                inputLayoutRepetirSenha.setError( getString( R.string.senhaEmpty ) );
                inputLayoutSenha.setError( getString( R.string.senhaEmpty ) );
            }

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
                case R.id.et_senha:
                    senhasIguais();
                    break;
                case R.id.et_repita_senha:
                    senhasIguais();
                    break;
            }
        }
    }
}
