package com.piececonnect.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.piececonnect.R;
import com.piececonnect.data.model.User;
import com.piececonnect.data.repo.AuthRepository;
import com.piececonnect.data.repo.UserRepository;
import com.piececonnect.ui.main.MainActivity;

public class AuthActivity extends AppCompatActivity {
	private AuthRepository authRepository;
	private UserRepository userRepository;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_auth);
		authRepository = new AuthRepository();
		userRepository = new UserRepository();

		EditText inputEmail = findViewById(R.id.input_email);
		EditText inputPassword = findViewById(R.id.input_password);
		EditText inputName = findViewById(R.id.input_name);
		Button btnLogin = findViewById(R.id.btn_login);
		Button btnRegister = findViewById(R.id.btn_register);

		btnLogin.setOnClickListener(v -> {
			String email = inputEmail.getText().toString().trim();
			String password = inputPassword.getText().toString().trim();
			if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
				Toast.makeText(this, "Email and password required", Toast.LENGTH_SHORT).show();
				return;
			}
			authRepository.signIn(email, password).addOnSuccessListener(res -> goToMain()).addOnFailureListener(e -> Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show());
		});

		btnRegister.setOnClickListener(v -> {
			String email = inputEmail.getText().toString().trim();
			String password = inputPassword.getText().toString().trim();
			String name = inputName.getText().toString().trim();
			if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(name)) {
				Toast.makeText(this, "Name, email and password required", Toast.LENGTH_SHORT).show();
				return;
			}
			authRepository.signUp(email, password).addOnSuccessListener(res -> {
				String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
				User user = new User(uid, name, email, "jobseeker");
				userRepository.upsertUser(user).addOnSuccessListener(v2 -> goToMain()).addOnFailureListener(e -> Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show());
			}).addOnFailureListener(e -> Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show());
		});
	}

	private void goToMain() {
		startActivity(new Intent(this, MainActivity.class));
		finish();
	}
}