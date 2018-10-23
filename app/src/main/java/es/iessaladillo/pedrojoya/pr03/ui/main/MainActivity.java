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
        initAvatar(database.getDefaultAvatar());
        lblAvatar.setOnClickListener(v -> changeAvatar());
        imgAvatar.setOnClickListener(v -> changeAvatar());
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

    private void initViews() {
        imgAvatar = ActivityCompat.requireViewById(this, R.id.imgAvatar);
        lblAvatar = ActivityCompat.requireViewById(this, R.id.lblAvatar);
        lblName = ActivityCompat.requireViewById(this, R.id.lblName);
        txtName = ActivityCompat.requireViewById(this, R.id.txtName);
        lblPhonenumber = ActivityCompat.requireViewById(this, R.id.lblPhonenumber);
        txtPhonenumber = ActivityCompat.requireViewById(this, R.id.txtPhonenumber);
        imgPhonenumber = ActivityCompat.requireViewById(this, R.id.imgPhonenumber);
        lblEmail = ActivityCompat.requireViewById(this, R.id.lblEmail);
        txtEmail = ActivityCompat.requireViewById(this, R.id.txtEmail);
        imgEmail = ActivityCompat.requireViewById(this, R.id.imgEmail);
        lblAddress = ActivityCompat.requireViewById(this, R.id.lblAddress);
        txtAddress = ActivityCompat.requireViewById(this, R.id.txtAddress);
        imgAddress = ActivityCompat.requireViewById(this, R.id.imgAddress);
        lblWeb = ActivityCompat.requireViewById(this, R.id.lblWeb);
        txtWeb = ActivityCompat.requireViewById(this, R.id.txtWeb);
        imgWeb = ActivityCompat.requireViewById(this, R.id.imgWeb);
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

    private void initAvatar(Avatar defaultAvatar) {
        imgAvatar.setImageResource(defaultAvatar.getImageResId());
        imgAvatar.setTag(defaultAvatar.getImageResId());
        lblAvatar.setText(defaultAvatar.getName());
    }

    private void changeAvatar() {
        Avatar avatar = database.getRandomAvatar();
        initAvatar(avatar);
    }

    private void onFocus() {
        txtName.setOnFocusChangeListener((v, hasFocus) -> {
            setLblToBold(hasFocus, lblName);
        });
        txtEmail.setOnFocusChangeListener((v, hasFocus) -> {
            setLblToBold(hasFocus, lblEmail);
        });
        txtAddress.setOnFocusChangeListener((v, hasFocus) -> {
            setLblToBold(hasFocus, lblAddress);
        });
        txtPhonenumber.setOnFocusChangeListener((v, hasFocus) -> {
            setLblToBold(hasFocus, lblPhonenumber);
        });
        txtWeb.setOnFocusChangeListener((v, hasFocus) -> {
            setLblToBold(hasFocus, lblWeb);
        });
    }

    private void setLblToBold(boolean hasFocus, TextView lbl) {
        if (hasFocus)
            lbl.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        else
            lbl.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
    }

    private void addChangeListener() {

        txtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                if (txtName.getText().toString().isEmpty()) {
                    txtName.setError(getString(R.string.main_invalid_data));
                    lblName.setEnabled(false);
                } else {
                    lblName.setEnabled(true);
                }
            }
        });


        txtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!ValidationUtils.isValidEmail(txtEmail.getText().toString())) {
                    txtEmail.setError(getString(R.string.main_invalid_data));
                    lblEmail.setEnabled(false);
                    imgEmail.setEnabled(false);
                } else {
                    lblEmail.setEnabled(true);
                    imgEmail.setEnabled(true);
                }
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
                    lblPhonenumber.setEnabled(false);
                    imgPhonenumber.setEnabled(false);
                } else {
                    lblPhonenumber.setEnabled(true);
                    imgPhonenumber.setEnabled(true);
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
                    lblAddress.setEnabled(false);
                    imgAddress.setEnabled(false);
                } else {
                    lblAddress.setEnabled(true);
                    imgAddress.setEnabled(true);
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
                    lblWeb.setEnabled(false);
                    imgWeb.setEnabled(false);
                } else {
                    lblWeb.setEnabled(true);
                    imgWeb.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void save() {
        // TODO
        String message;
        boolean valid = checkAllFields();

        if (valid) {
            message = getString(R.string.main_saved_succesfully);
        } else {
            message = getString(R.string.main_error_saving);
        }


        KeyboardUtils.hideSoftKeyboard(this);
        Snackbar.make(lblName, message, Snackbar.LENGTH_LONG).show();

    }

    private boolean checkAllFields() {
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
        return valid;
    }

}
