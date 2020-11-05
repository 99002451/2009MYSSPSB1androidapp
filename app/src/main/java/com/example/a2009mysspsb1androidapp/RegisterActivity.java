private void CreateAccout()
        {
        String name = InputName.getText().toString();
        String phone = InputPhoneNumber.getText().toString();
        String password = InputPassword.getText().toString();

        if (TextUtils.isEmpty(name))
        {
        Toast.makeText(this, "Please Write Your Name...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(phone))
        {
        Toast.makeText(this, "Please Write Your Phone Number...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password))
        {
        Toast.makeText(this, "Please Write Your Password...", Toast.LENGTH_SHORT).show();
        }
        else
        {
        loadingBar.setTitle("Create Account");
        loadingBar.setMessage("Please wait while we are checking the details");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        ValidatephoneNumber(name, phone, password);
        }
        }

private void ValidatephoneNumber(final String name, final String phone, final String password)
        {
final DatabaseReference RootRef;

        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
@Override
public void onDataChange(@NonNull DataSnapshot dataSnapshot)
        {
        if (!(dataSnapshot.child("Users").child(phone).exists()))
        {
        HashMap<String, Object> userdataMap = new HashMap<>();
        userdataMap.put("phone", phone);
        userdataMap.put("password", password);
        userdataMap.put("name", name);

        RootRef.child("Users").child(phone).updateChildren(userdataMap)
        .addOnCompleteListener(new OnCompleteListener<Void>() {
@Override
public void onComplete(@NonNull Task<Void> task)
        {
        if (task.isSuccessful())
        {
        Toast.makeText(RegisterActivity.this, "Congratulations, your account has been created", Toast.LENGTH_SHORT).show();
        loadingBar.dismiss();

        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        }
        else
        {
        loadingBar.dismiss();
        Toast.makeText(RegisterActivity.this, "Network Error: Please try again after sometime...", Toast.LENGTH_SHORT).show();
        }
        }
        });
           