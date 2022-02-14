package com.nxet.emstelltask.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nxet.emstelltask.ClinicDetailActivity
import com.nxet.emstelltask.DataClasses.Doctor
import com.nxet.emstelltask.DoctorDetailActivity
import com.nxet.emstelltask.databinding.RecyclerItemBinding



class DoctorsAdapter : RecyclerView.Adapter<DoctorsAdapter.DocotorsViewHolder>() {
    inner class DocotorsViewHolder(val binding : RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root){

    }

    private val diffCallback = object : DiffUtil.ItemCallback<Doctor>(){
        override fun areContentsTheSame(oldItem: Doctor, newItem: Doctor): Boolean {
          return oldItem.doctor_id == newItem.doctor_id
        }

        override fun areItemsTheSame(oldItem: Doctor, newItem: Doctor): Boolean {
            return oldItem == newItem
        }
    }


    private val differ =  AsyncListDiffer(this,diffCallback)
    var doctors : List<Doctor>
    get() = differ.currentList
    set(value) {differ.submitList(value)}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocotorsViewHolder {
        return DocotorsViewHolder(RecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
           parent,
            false)
        )
    }

    override fun onBindViewHolder(holder: DocotorsViewHolder, position: Int) {

        holder.binding.apply {
            val doc = doctors[position]
            tvCard.text = doc.name
            itemview.setOnClickListener {
                val i = Intent(holder.itemView.context, DoctorDetailActivity::class.java)
                i.putExtra("doctor_id",doc.doctor_id)
                holder.itemView.context.startActivity(i)
            }
        }

    }

    override fun getItemCount(): Int {
      return doctors.size
    }

}