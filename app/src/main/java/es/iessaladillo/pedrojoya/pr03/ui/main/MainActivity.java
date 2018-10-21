package es.iessaladillo.pedrojoya.pr03.ui.main;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import es.iessaladillo.pedrojoya.pr03.R;
import es.iessaladillo.pedrojoya.pr03.data.local.Database;
import es.iessaladillo.pedrojoya.pr03.data.local.model.Avatar;
import es.iessaladillo.pedrojoya.pr03.utils.KeyboardUtils;
import es.iessaladillo.pedrojoya.pr03.utils.ValidationUtils;

public class MainActivity extends AppCompatActivity {

    private Database database = Database.getInstance();
    private ImageView imgAvatar;
    private TextView lblAvatar;
    private TextView lblName;
    private EditText txtName;
    private TextView lblEmail;
    private EditText txtEmail;
    private ImageView imgEmail;
    private TextView lblPhonenumber;
    private EditText txtPhonenumber;
    private ImageView imgPhonenumber;
    private TextView lblAddress;
    private EditText txtAddress;
    private ImageView imgAddress;
    private TextView lblWeb;
    private EditText txtWeb;
    private ImageView imgWeb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        // TODO
    }

    private void initViews() {
        imgAvatar = ActivityCompat.requireViewById(this, R.id.imgAvatar);
        lblAvatar = ActivityCompat.requireViewById(this, R.id.lblAvatar);
        imgAvatar.setImageResource(database.getDefaultAvatar().getImageResId());
        imgAvatar.setTag(database.getDefaultAvatar().getImageResId());
        lblAvatar.setText(database.getDefaultAvatar().getName());
        lblName = ActivityCompat.requireViewById(this, R.id.lblName);
        txtName = ActivityCompat.requireViewById(this, R.id.txtName);
        lblEmail = findViewById(R.id.lblEmail);
        txtEmail = findViewById(R.id.txtEmail);
        imgEmail = findViewById(R.id.imgEmail);
        lblPhonenumber = findViewById(R.id.lblPhonenumber);
        txtPhonenumber = findViewById(R.id.txtPhonenumber);
        imgPhonenumber = findViewById(R.id.imgPhonenumber);
        lblAddress = findViewById(R.id.lblAddress);
        txtAddress = findViewById(R.id.txtAddress);
        imgAddress = findViewById(R.id.imgAddress);
        lblWeb = findViewById(R.id.lblWeb);
        txtWeb = findViewById(R.id.txtWeb);
        imgWeb = findViewById(R.id.imgWeb);
        onFocus();
        addChangeListener();
        txtWeb.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                save();
                return true;
            }
            return false;
        });
    }

    // DO NOT TOUCH
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // DO NOT TOUCH
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mnuSave) {
            save();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Checks if form is valid or not and shows a Snackbar accordingly
     **/

    private void changeAvatar() {
        Avatar avatar = database.getRandomAvatar();
        imgAvatar.setImageResource(avatar.getImageResId());
        imgAvatar.setTag(avatar.getImageResId());
        lblAvatar.setText(avatar.getName());
    }

    private void onFocus() {
        imgAvatar.setOnClickListener(v -> changeAvatar());
        lblAvatar.setOnClickListener(v -> changeAvatar());
        txtName.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus)
                lblName.setTypeface(Typeface.DEFAULT_BOLD);
            else
                lblName.setTypeface(Typeface.DEFAULT);
        });
        txtEmail.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus)
                lblEmail.setTypeface(Typeface.DEFAULT_BOLD);
            else
                lblEmail.setTypeface(Typeface.DEFAULT);
        });
        txtAddress.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus)
                lblAddress.setTypeface(Typeface.DEFAULT_BOLD);
            else
                lblAddress.setTypeface(Typeface.DEFAULT);
        });
        txtPhonenumber.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus)
                lblPhonenumber.setTypeface(Typeface.DEFAULT_BOLD);
            else
                lblPhonenumber.setTypeface(Typeface.DEFAULT);
        });
        txtWeb.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus)
                lblWeb.setTypeface(Typeface.DEFAULT_BOLD);
            else
                lblWeb.setTypeface(Typeface.DEFAULT);
        });
    }

    private void addChangeListener() {

        txtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (txtName.getText().toString().isEmpty()) {
                    txtName.setError(getString(R.string.main_invalid_data));
                    lblName.setTextColor(getResources().getColor(R.color.colorError));
                } else {
                    lblName.setTextColor(getResources().getColor(R.color.color_state_selector));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        txtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!ValidationUtils.isValidEmail(txtEmail.getText().toString())) {
                    txtEmail.setError(getString(R.string.main_invalid_data));
                    imgEmail.setEnabled(false);
                    lblEmail.setTextColor(getResources().getColor(R.color.colorError));
                } else {
                    imgEmail.setEnabled(true);
                    lblEmail.setTextColor(getResources().getColor(R.color.color_state_selector));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtPhonenumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!ValidationUtils.isValidPhone(txtPhonenumber.getText().toString())) {
                    txtPhonenumber.setError(getString(R.string.main_invalid_data));
                    imgPhonenumber.setEnabled(false);
                    lblPhonenumber.setTextColor(getResources().getColor(R.color.colorError));
                } else {
                    imgPhonenumber.setEnabled(true);
                    lblPhonenumber.setTextColor(getResources().getColor(R.color.color_state_selector));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (txtAddress.getText().toString().isEmpty()) {
                    txtAddress.setError(getString(R.string.main_invalid_data));
                    imgAddress.setEnabled(false);
                    lblAddress.setTextColor(getResources().getColor(R.color.colorError));
                } else {
                    imgAddress.setEnabled(true);
                    lblAddress.setTextColor(getResources().getColor(R.color.color_state_selector));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtWeb.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!ValidationUtils.isValidUrl(txtWeb.getText().toString())) {
                    txtWeb.setError(getString(R.string.main_invalid_data));
                    imgWeb.setEnabled(false);
                    lblWeb.setTextColor(getResources().getColor(R.color.colorError));
                } else {
                    imgWeb.setEnabled(true);
                    lblWeb.setTextColor(getResources().getColor(R.color.color_state_selector));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void save() {
        // TODO
        String mesage;
        boolean valid = true;
        if (txtName.getText().toString().isEmpty()) {
            txtName.setError(getString(R.string.main_invalid_data));
            valid = false;
        }
        if (!ValidationUtils.isValidEmail(txtEmail.getText().toString())) {
            txtEmail.setError(getString(R.string.main_invalid_data));
            valid = false;
        }
        if (!ValidationUtils.isValidPhone(txtPhonenumber.getText().toString())) {
            txtPhonenumber.setError(getString(R.string.main_invalid_data));
            valid = false;
        }
        if (txtAddress.getText().toString().isEmpty()) {
            txtAddress.setError(getString(R.string.main_invalid_data));
            valid = false;
        }
        if (!ValidationUtils.isValidUrl(txtWeb.getText().toString())) {
            txtWeb.setError(getString(R.string.main_invalid_data));
            valid = false;
        }

        if (valid) {
            mesage = getString(R.string.main_saved_succesfully);
        } else {
            mesage = getString(R.string.main_error_saving);
        }


        KeyboardUtils.hideSoftKeyboard(this);
        Snackbar.make(lblWeb, mesage, Snackbar.LENGTH_LONG).show();

    }

}
