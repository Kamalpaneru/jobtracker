package com.example.dillichalise.jt;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by kamal on 8/6/17.
 */

public class JobListAdapter extends RecyclerView.Adapter<JobListAdapter.JobViewHolder> {

    private List<Form> forms;

    public JobListAdapter(List<Form> forms){

        this.forms = forms;
    }


    @Override
    public JobViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.job_list_item,parent,false);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JobViewHolder holder, int position) {
        holder.jobTitle.setText(forms.get(position).getJobtitle());
        holder.skills.setText(forms.get(position).getSkills());
        holder.companyName.setText(forms.get(position).getCompany());
        holder.phoneNu.setText(forms.get(position).getMn());
        holder.location.setText(forms.get(position).getLocation());

            //eta bind garxau sabai kura.. holder.jobTitle.setText(forms.get(position).getJobTitle();)
    }

    @Override
    public int getItemCount() {
        return forms.size();
    }
     static class JobViewHolder extends RecyclerView.ViewHolder {
         TextView jobTitle;
         TextView skills;
         TextView companyName;
         TextView phoneNu;
         TextView location;

        public JobViewHolder(View itemView) {
            super(itemView);

              jobTitle= (TextView) itemView.findViewById(R.id.title);
             skills= (TextView) itemView.findViewById(R.id.skills);
             companyName= (TextView) itemView.findViewById(R.id.company_name);
             phoneNu= (TextView) itemView.findViewById(R.id.phone_number);
            location= (TextView) itemView.findViewById(R.id.location);

        }
    }

}
