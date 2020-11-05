}
        else
        {
        Toast.makeText(RegisterActivity.this, "This " + phone + " Already exists...", Toast.LENGTH_SHORT).show();
        loadingBar.dismiss();

        Toast.makeText(RegisterActivity.this, "Please try using another phone number", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
        }
        }

@Override
public void onCancelled(@NonNull DatabaseError databaseError) {

        }
        });
        }
        }
