package com.example.intentio.ui.main.order_intention

import android.R
import android.content.DialogInterface
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.example.intentio.data.*
import com.example.intentio.databinding.FragmentOrderIntentionBinding
import com.example.intentio.ui.main.Repository

class OrderIntentionFragment : Fragment() {

    // w zależności od wybranej karty w poprzednim fragmencie
    lateinit var intentType: IntentType

    // wybrany dzień
    lateinit var date: String

    // wybrana godzina
    lateinit var hour: String

    // rodzaj
    lateinit var kind: IntentionKind

    // zebrane info
    lateinit var intentionContent: IntentionContent


    // user aktualnie zalogowany - domyślnie w IntentionContent - od kogo ale można zmienić i wpisać custom w IntentionContent -> from
    lateinit var user: User

    //jeżeli przydzielony do mszy Priest
    private var priest: Priest? = null

    lateinit var mass: HolyMass

    companion object {
        fun newInstance() = OrderIntentionFragment()
    }

    private lateinit var viewModel: OrderIntentionViewModel
    private lateinit var binding: FragmentOrderIntentionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        viewModel = ViewModelProvider(this).get(OrderIntentionViewModel::class.java)
        binding = FragmentOrderIntentionBinding.inflate(inflater, container, false)

        setDateSpinner()

        binding.orderIntentionButton.setOnClickListener { orderIntention() }
        binding.other.setOnClickListener { showConfirmationRequirementDialog() }

        return binding.root
    }

    private fun orderIntention() {
        resolveIntentionData()
        showAcceptDialog()
    }

    private fun showAcceptDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Zamawiana intencja")
            .setMessage("")
            .setPositiveButton("WYŚLIJ INTENCJĘ") { dialog, _ -> sendIntentionData(dialog)}
            .setNegativeButton("WRÓĆ") {dialog,_ -> dialog.dismiss()}
            .create()
            .show()
    }

    private fun resolveIntentionData() {
        setMass()
        setPriest()
    }

    private fun setDateSpinner() {
        val masses = viewModel.masses.value?.filter { it.freeIntentCounter > 0 }?.map { it.date }?.toSet()?.toList()
        masses?.let {
            val adapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, masses)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.date.adapter = adapter
            binding.date.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    setIntentionDate(masses[position])
                    setHourSpinner()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        }
    }

    private fun setHourSpinner() {
        val masses = viewModel.masses.value?.filter { it.freeIntentCounter > 0 && it.date == date }?.map { it.hour }
        masses?.let {
            val adapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, masses)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.hour.adapter = adapter
            binding.hour.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    setIntentionHour(masses[position])
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        }
    }


    private fun setMass(){
        viewModel.masses.value?.find { it.date == date && it.hour == hour }?.let {
            mass = it
        }
    }

    private fun setPriest() {
        //default first priest on the list
        priest = if(mass.priests.isNotEmpty()) mass.priests.first() else null
    }

    private fun setContent() {
        intentionContent = IntentionContent(binding.contentLabel.text.toString(), " ")
    }

    private fun setIntentionDate(textDate: String) {
        date = textDate
    }

    private fun setIntentionHour(textDate: String) {
        hour = textDate
    }

    private fun sendIntentionData(dialog: DialogInterface) {
        setContent()
        val newIntention = Intent(IntentType.SINGLE, date, hour, IntentionKind.ANNIVERSARY, intentionContent, Repository.user, priest,  )
        viewModel.orderIntention(newIntention)
        dialog.dismiss()
    }

    private fun showConfirmationRequirementDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Uwaga!")
            .setMessage("Jeżeli nie korzystasz z predefiniowanej bazy intencji treść twojej intencji będzie musiała zostać potwierdzona przez kapłana. ")
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss()}
            .create()
            .show()
    }

}