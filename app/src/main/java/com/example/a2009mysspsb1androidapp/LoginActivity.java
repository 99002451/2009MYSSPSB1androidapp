package com.example.a2009mysspsb1androidapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerce.Model.Users;
import com.example.ecommerce.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.widget.CheckBox;

import io.paperdb.Paper;


    NotAdminLink.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view)
        {
        LoginButton.setText("Login");
        AdminLink.setVisibility(View.VISIBLE);
        NotAdminLink.setVisibility(View.INVISIBLE);
        parentDbName = "Users";
        }
        });
        }

private void LoginUser() {
        String phone = InputPhoneNumber.getText().toString();
        String password = InputPassword.getText().toString();

        if (TextUtils.isEmpty(phone)) {
        Toast.makeText(this, "Please Write Your Phone Number...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
        Toast.makeText(this, "Please Write Your Password...", Toast.LENGTH_SHORT).show();
        }
        else
        {
        loadingBar.setTitle("Login Account");
        loadingBar.setMessage("Please wait while we are checking the details");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        AllowAccessToAccount(phone, password);
        }

        }

private void AllowAccessToAccount(final String phone, final String password)
        {
        if (chkBoxRememberMe.isChecked())
        {
        Paper.book().write(Prevalent.UserPhoneKey, phone);
        Paper.book().write(Prevalent.UserPasswordKey, password);
        }


final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();


        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
@Override
public void onDataChange(@NonNull DataSnapshot dataSnapshot)
        {
        if (dataSnapshot.child(parentDbName).child(phone).exists())
        {
        Users usersData = dataSnapshot.child(parentDbName).child(phone).getValue(Users.class);

        if (usersData.getPhone().equals(phone))
        {
        if (usersData.getPassword().equals(password))
        {
        if (parentDbName.equals("Admins"))
        {
        String name=usersData.getName();
        Toast.makeText(LoginActivity.this, "Welcome "+ name +", you are logged in successfully...", Toast.LENGTH_SHORT).show();
        loadingBar.dismiss();

        Intent intent = new Intent(LoginActivity.this, AdminCategoryActivity.class);
        startActivity(intent);

        }
        else if (parentDbName.equals("Users"))
        {
        String name=usersData.getName();
        Toast.makeText(LoginActivity.this, "Welcome "+ name +", you are logged in successfully...", Toast.LENGTH_SHORT).show();
        loadingBar.dismiss();

        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        Prevalent.currentOnlineUsers = usersData;
        startActivity(intent);

        }
        }
        else
        {
        loadingBar.dismiss();
        Toast.makeText(LoginActivity.this, "Password is incorrect...", Toast.LENGTH_SHORT).show();
        }
        }
        }
        else
        {
        Toast.makeText(LoginActivity.this, "Account with this " + phone + " does not exists.", Toast.LENGTH_SHORT).show();
        loadingBar.dismiss();
        }
        }

@Override
public void onCancelled(@NonNull DatabaseError databaseError) {

        }
        });
        }
        }
