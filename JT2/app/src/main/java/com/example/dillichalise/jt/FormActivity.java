package com.example.dillichalise.jt;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class FormActivity extends Fragment {

    EditText jobtitle, skills, company, location, email,mn;
    EditText latLongTV;
    Button submitButton;
    TextView refresh;
    String jobTitleStr, skillsstr, companystr, emailStr, locationstr, mnStr;
    DatabaseHelper myDB;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_form, container, false);
        jobtitle = (EditText) view.findViewById(R.id.jTitle);
        skills = (EditText) view.findViewById(R.id.skills);
        company = (EditText) view.findViewById(R.id.company);
        email = (EditText) view.findViewById(R.id.email);
        mn = (EditText) view.findViewById(R.id.mn);
        location = (EditText) view.findViewById(R.id.location);
        myDB = new DatabaseHelper(getActivity());
        final DatabaseHelperForm formhelper = new DatabaseHelperForm(getActivity());
        submitButton = (Button) view.findViewById(R.id.submitButton);

//        refresh = (TextView) view.findViewById(R.id.refresh);
//
//        refresh.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(getActivity(),DetailsActivity.class);
//                startActivity(intent);
//            }
//        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.submitButton) {
                      jobTitleStr = jobtitle.getText().toString();
                      skillsstr = skills.getText().toString();
                      companystr = company.getText().toString();
                      emailStr = email.getText().toString();
                      mnStr = mn.getText().toString();
                      locationstr = location.getText().toString();

                    if (!validate()) {
                        Toast pass = Toast.makeText(getActivity(), "Invalid content !!", Toast.LENGTH_SHORT);
                        pass.show();
                    }
                    else {

                        Form c = new Form();
                        c.setJobtitle(jobTitleStr);
                        c.setSkills(skillsstr);
                        c.setCompany(companystr);
                        c.setEmail(emailStr);
                        c.setMn(mnStr);
                        c.setLocation(locationstr);
                        formhelper.insertForm(c);
                        Toast pass1 = Toast.makeText(getActivity(), "Form saved", Toast.LENGTH_SHORT);
                        pass1.show();

                        Intent intent=new Intent(getActivity(),DetailsActivity.class);
                        startActivity(intent);


                        jobtitle.setText("");
                        skills.setText("");
                        company.setText("");
                        location.setText("");
                        email.setText("");
                        mn.setText("");

                        //GeocodingLocation locationAddress = new GeocodingLocation();
                        //locationAddress.getAddressFromLocation(locationstr, getApplicationContext(), new GeocoderHandler());
                    }

                }

            }

        });
        return view;
    }



    /*public class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("locationStr");
                    break;
                default:
                    locationAddress = null;
            }
            latLongTV.setText(locationAddress);
        }
    }*/

   public boolean validate(){
       boolean valid = true;
       if (jobTitleStr.equals("")){
           jobtitle.setError("Please enter valid Job Title !!");
           valid = false;
       }

       if (skillsstr.equals("")){
           skills.setError("Please enter valid skills !!");
           valid = false;
       }

       if (companystr.equals("")){
           company.setError("Please enter valid Company Name !!");
           valid = false;
       }

       if (email.equals("")||!Patterns.EMAIL_ADDRESS.matcher(emailStr).matches()){
           email.setError("Please enter valid email address !!");
           valid = false;
       }


       if (locationstr.equals("")){
           location.setError("Please enter valid Location !!");
           valid = false;
       }

       return valid;
   }
}