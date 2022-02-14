package com.nxet.emstelltask.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nxet.emstelltask.ClinicDetailActivity
import com.nxet.emstelltask.DataClasses.Clinic
import com.nxet.emstelltask.R
import com.nxet.emstelltask.databinding.RecyclerItemBinding


class ClinicsAdapter : RecyclerView.Adapter<ClinicsAdapter.ClinicsViewHolder>() {
    inner class ClinicsViewHolder(val binding : RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root){

    }

    private val diffCallback = object : DiffUtil.ItemCallback<Clinic>(){
        override fun areContentsTheSame(oldItem: Clinic, newItem: Clinic): Boolean {
          return oldItem.clinic_id == newItem.clinic_id
        }

        override fun areItemsTheSame(oldItem: Clinic, newItem: Clinic): Boolean {
            return oldItem == newItem
        }
    }


    private val differ =  AsyncListDiffer(this,diffCallback)
    var clinic : List<Clinic>
    get() = differ.currentList
    set(value) {differ.submitList(value)}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClinicsViewHolder {
        return ClinicsViewHolder(RecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
           parent,
            false)
        )
    }

    override fun onBindViewHolder(holder: ClinicsViewHolder, position: Int) {


        holder.binding.apply {
            val clinic = clinic[position]
            iconCard.setImageResource(R.drawable.ic_round_local_hospital_24)
            tvCard.text = clinic.name

            itemview.setOnClickListener {
                val i = Intent(holder.itemView.context, ClinicDetailActivity::class.java)
                i.putExtra("clinic_id",clinic.clinic_id)
                holder.itemView.context.startActivity(i)
            }
        }



    }

    override fun getItemCount(): Int {
      return clinic.size
    }

}