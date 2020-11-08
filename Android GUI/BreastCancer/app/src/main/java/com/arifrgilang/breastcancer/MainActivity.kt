package com.arifrgilang.breastcancer

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var listAge: Array<String>
    private lateinit var listMenopause: Array<String>
    private lateinit var listTumorSize: Array<String>
    private lateinit var listLymphNode: Array<String>
    private lateinit var listNodeCaps: Array<String>
    private lateinit var listMalignance: Array<String>
    private lateinit var listBreastSide: Array<String>
    private lateinit var listBreastQuad: Array<String>
    private lateinit var listIrradiant: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        importList()

        etAge.setOnClickListener {
            CustomSpinnerDialog(
                "Age",
                listAge.toList(),
                object: CustomSpinnerDialog.OnSpinnerItemSelected {
                    override fun onSelected(itemName: String) {
                        etAge.setText(itemName)
                    }
                }
            ).show(supportFragmentManager, "AgeSpinnerDialog")
        }

        etMenopause.setOnClickListener {
            CustomSpinnerDialog(
                "Menopause Status",
                listMenopause.toList(),
                object: CustomSpinnerDialog.OnSpinnerItemSelected {
                    override fun onSelected(itemName: String) {
                        etMenopause.setText(itemName)
                    }
                }
            ).show(supportFragmentManager, "MenopauseSpinnerDialog")
        }

        etTumorSize.setOnClickListener {
            CustomSpinnerDialog(
                "Tumor Size",
                listTumorSize.toList(),
                object: CustomSpinnerDialog.OnSpinnerItemSelected {
                    override fun onSelected(itemName: String) {
                        etTumorSize.setText(itemName)
                    }
                }
            ).show(supportFragmentManager, "TumorSizeSpinnerDialog")
        }

        etLymphNode.setOnClickListener {
            CustomSpinnerDialog(
                "Axillary Lymph Node",
                listLymphNode.toList(),
                object: CustomSpinnerDialog.OnSpinnerItemSelected {
                    override fun onSelected(itemName: String) {
                        etLymphNode.setText(itemName)
                    }
                }
            ).show(supportFragmentManager, "LymphNodeSpinnerDialog")
        }

        etNodeCaps.setOnClickListener {
            CustomSpinnerDialog(
                "Node Caps Penetration",
                listNodeCaps.toList(),
                object: CustomSpinnerDialog.OnSpinnerItemSelected {
                    override fun onSelected(itemName: String) {
                        etNodeCaps.setText(itemName)
                    }
                }
            ).show(supportFragmentManager, "NodeCapsSpinnerDialog")
        }

        etMalignancy.setOnClickListener {
            CustomSpinnerDialog(
                "Malignancy",
                listMalignance.toList(),
                object: CustomSpinnerDialog.OnSpinnerItemSelected {
                    override fun onSelected(itemName: String) {
                        etMalignancy.setText(itemName)
                    }
                }
            ).show(supportFragmentManager, "MalignancySpinnerDialog")
        }

        etBreastSide.setOnClickListener {
            CustomSpinnerDialog(
                "Breast Side",
                listBreastSide.toList(),
                object: CustomSpinnerDialog.OnSpinnerItemSelected {
                    override fun onSelected(itemName: String) {
                        etBreastSide.setText(itemName)
                    }
                }
            ).show(supportFragmentManager, "BreastSideSpinnerDialog")
        }

        etBreastQuad.setOnClickListener {
            CustomSpinnerDialog(
                "Breast Quad",
                listBreastQuad.toList(),
                object: CustomSpinnerDialog.OnSpinnerItemSelected {
                    override fun onSelected(itemName: String) {
                        etBreastQuad.setText(itemName)
                    }
                }
            ).show(supportFragmentManager, "BreastQuadSpinnerDialog")
        }

        etIrradiation.setOnClickListener {
            CustomSpinnerDialog(
                "Irradiation",
                listIrradiant.toList(),
                object: CustomSpinnerDialog.OnSpinnerItemSelected {
                    override fun onSelected(itemName: String) {
                        etIrradiation.setText(itemName)
                    }
                }
            ).show(supportFragmentManager, "IrradiationSpinnerDialog")
        }

        btnAnalyze.setOnClickListener {
            if(isFormFilled()){
//                Toast.makeText(this, "Form filled", Toast.LENGTH_SHORT).show()
                if(isHasRecurrenceEvent()) {
                    showDiagnoseDialog("Recurrence-events", "You may have recurrence-events of breast cancer. Please go to doctor immediately.")
                } else {
                    showDiagnoseDialog("No recurrence-events", "You are healthy and have no recurrence-events indicated. Keep up the healthy life!")
                }
            } else {
                Toast.makeText(this, "Please fill all form", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun importList() {
        listAge = resources.getStringArray(R.array.list_age)
        listMenopause = resources.getStringArray(R.array.list_menopause)
        listTumorSize = resources.getStringArray(R.array.list_tumorsize)
        listLymphNode = resources.getStringArray(R.array.list_lymph_nodes)
        listNodeCaps = resources.getStringArray(R.array.list_node_caps)
        listMalignance = resources.getStringArray(R.array.list_malignance)
        listBreastSide = resources.getStringArray(R.array.list_breast)
        listBreastQuad = resources.getStringArray(R.array.list_breast_quad)
        listIrradiant = resources.getStringArray(R.array.list_irradiant)
    }

    private fun isFormFilled() : Boolean {
        return etAge.text!!.isNotEmpty() &&
                etMenopause.text!!.isNotEmpty() &&
                etTumorSize.text!!.isNotEmpty() &&
                etLymphNode.text!!.isNotEmpty() &&
                etNodeCaps.text!!.isNotEmpty() &&
                etMalignancy.text!!.isNotEmpty() &&
                etBreastSide.text!!.isNotEmpty() &&
                etBreastQuad.text!!.isNotEmpty() &&
                etIrradiation.text!!.isNotEmpty()
    }

    private fun isHasRecurrenceEvent() : Boolean {
        // Hardcoded model from python
        val bias = -1.0320000000000016
        val weight = listOf(0.7921256221192998, 0.4561256221192995, 0.35212562211929943,
            0.17612562211929927, 0.3441256221192994, 0.6001256221192997, -0.06387437788070083,
            0.5441256221192996, -0.1358743778807009, 0.5201256221192996, 0.48012562211929954,
            0.5281256221192996, 0.5681256221192996, 0.6401256221192997, 0.5921256221192996,
            0.4321256221192995, 0.5361256221192996, 0.8001256221192998, 0.7921256221192998,
            0.7921256221192998, 0.2961256221192994, 0.5521256221192996, 0.7921256221192998,
            0.7921256221192998, 0.2961256221192994, 0.2961256221192994, 0.48812562211929955,
            -0.29587437788070103, -0.1518743778807009, 0.10412562211929921, 0.16012562211929926,
            0.08012562211929919, -0.20787437788070096, -0.23987437788070098, 0.3441256221192994,
            0.5361256221192996, 0.4001256221192995, 0.25612562211929935, 0.39212562211929947,
            -0.23987437788070098, -0.20787437788070096)

        // String input
        val age = etAge.text.toString()
        val menopause = etMenopause.text.toString()
        val tumorSize = etTumorSize.text.toString()
        val lymphNode = etLymphNode.text.toString()
        val nodeCaps = etNodeCaps.text.toString()
        val malignancy = etMalignancy.text.toString()
        val breastSide = etBreastSide.text.toString()
        val breastQuad = etBreastQuad.text.toString()
        val irradiation = etIrradiation.text.toString()

        // Creating Columns
        val columns = mutableListOf<Double>()
        columns.add(if (age == "20-29") 1.0 else 0.0)
        columns.add(if (age == "30-39") 1.0 else 0.0)
        columns.add(if (age == "40-49") 1.0 else 0.0)
        columns.add(if (age == "50-59") 1.0 else 0.0)
        columns.add(if (age == "60-69") 1.0 else 0.0)
        columns.add(if (age == "70-79") 1.0 else 0.0)
        columns.add(if (menopause == "lt40") 1.0 else 0.0)
        columns.add(if (menopause == "ge40") 1.0 else 0.0)
        columns.add(if (menopause == "premeno") 1.0 else 0.0)
        columns.add(if (tumorSize == "0-4") 1.0 else 0.0)
        columns.add(if (tumorSize == "10-14") 1.0 else 0.0)
        columns.add(if (tumorSize == "15-19") 1.0 else 0.0)
        columns.add(if (tumorSize == "20-24") 1.0 else 0.0)
        columns.add(if (tumorSize == "25-29") 1.0 else 0.0)
        columns.add(if (tumorSize == "30-34") 1.0 else 0.0)
        columns.add(if (tumorSize == "35-39") 1.0 else 0.0)
        columns.add(if (tumorSize == "40-44") 1.0 else 0.0)
        columns.add(if (tumorSize == "45-49") 1.0 else 0.0)
        columns.add(if (tumorSize == "50-54") 1.0 else 0.0)
        columns.add(if (tumorSize == "5-9") 1.0 else 0.0)
        columns.add(if (lymphNode == "0-2") 1.0 else 0.0)
        columns.add(if (lymphNode == "12-14") 1.0 else 0.0)
        columns.add(if (lymphNode == "15-17") 1.0 else 0.0)
        columns.add(if (lymphNode == "24-26") 1.0 else 0.0)
        columns.add(if (lymphNode == "3-5") 1.0 else 0.0)
        columns.add(if (lymphNode == "6-8") 1.0 else 0.0)
        columns.add(if (lymphNode == "9-11") 1.0 else 0.0)
        columns.add(if (nodeCaps == "no") 1.0 else 0.0)
        columns.add(if (nodeCaps == "yes") 1.0 else 0.0)
        columns.add(if (malignancy == "1") 1.0 else 0.0)
        columns.add(if (malignancy == "3") 1.0 else 0.0)
        columns.add(if (malignancy == "2") 1.0 else 0.0)
        columns.add(if (breastSide == "left") 1.0 else 0.0)
        columns.add(if (breastSide == "right") 1.0 else 0.0)
        columns.add(if (breastQuad == "central") 1.0 else 0.0)
        columns.add(if (breastQuad == "left-low") 1.0 else 0.0)
        columns.add(if (breastQuad == "left-up") 1.0 else 0.0)
        columns.add(if (breastQuad == "right-low") 1.0 else 0.0)
        columns.add(if (breastQuad == "right-up") 1.0 else 0.0)
        columns.add(if (irradiation == "no") 1.0 else 0.0)
        columns.add(if (irradiation == "yes") 1.0 else 0.0)

        // Predict function
        Log.d("COLUMNS", columns.toString())
        var total = 0.0
        for(i in 0 until columns.size){
            total += weight[i] * columns[i]
            Log.d("TOTAL_WEIGHT", total.toString())
        }
        total += bias

        Log.d("TOTAL_PREDICT", total.toString())

        // Binary step
        // Return true if total>=0, else return false
        return total>=0
    }

    private fun showDiagnoseDialog(title: String, content: String) {
        DiagnoseDialogFragment(title, content).apply {
            show(supportFragmentManager, "Diagnose Dialog")
        }
    }
}