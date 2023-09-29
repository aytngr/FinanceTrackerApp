package gr.aytn.pocket.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import gr.aytn.pocket.databinding.BottomSheetCategoryBinding

class CategoryBottomSheet: BottomSheetDialogFragment() {
    private var _binding: BottomSheetCategoryBinding? = null
    private val binding get() = _binding!!
    private var operand1 = ""
    private var operand2 = ""
    private var text = StringBuilder()
    private var operator = ' '
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomSheetCategoryBinding.inflate(inflater,container,false)


        initializeButtons()

        return binding.root
    }

    private fun initializeButtons(){
        numericalButtons()
        operationButtons()
        functionalButtons()
    }

    private fun appendNumber(number: Char){
        if(text.isEmpty() && (number == '0' || number == '.')) return
        if(text.toString() == "0" && number == '0') return
        if(operator == ' '){
            if(number == '.' && (operand1.isEmpty() || operand1.contains('.'))) return
            operand1 += number
        }else{
            if(number == '.' && (operand2.isEmpty() || operand2.contains('.'))) return
            operand2 += number
        }
        appendText(number)

//        if(dotAdded) return
//        if(operationAdded){
//            if(number == "0" && operand2.length == 1 && operand2[0] == '0') return
//            if(number == "." && operand2.isEmpty()) return
//            if(number == "." && operand2[operand2.lastIndex] == '.') return
//            operand2.append(number)
//        }else{
//            if(number == "0" && operand1.length == 1 && operand1[0] == '0') return
//            if(number == "." && operand1.isEmpty()) return
//            if(number == "." && operand1[operand1.lastIndex] == '.') return
//            operand1.append(number)
//        }
//        text.append(number)
//        binding.edittextAmount.setText(text)
    }

    private fun appendText(character: Char){
        text.append(character)
        binding.edittextAmount.setText(text)
        if(operator != ' ' || text[text.lastIndex] == '.'){
            binding.btnEqual.visibility = View.VISIBLE
            binding.btnDone.visibility = View.GONE
        }else{
            binding.btnEqual.visibility = View.GONE
            binding.btnDone.visibility = View.VISIBLE
        }
    }

    private fun appendOperation(operation: Char){
        if (operator == ' ' && operand1.isNotEmpty()){
            appendText(operation)
            operator = operation
        }else if(operator != ' ' && operand1.isNotEmpty() && operand2.isNotEmpty()){
            doOperation()
            appendOperation(operation)
        }else if(operator != ' ' && operand1.isNotEmpty() && operand2.isEmpty()){
            text.deleteCharAt(text.lastIndex)
            operator = ' '
            appendOperation(operation)
        }
    }

    private fun functionalButtons(){
        binding.btnEqual.setOnClickListener {
            doOperation()
        }
        binding.btnDelete.setOnClickListener {
            clearLastCharacter()
        }
        binding.btnDone.setOnClickListener {
            addAmount()
        }
    }

    private fun clearLastCharacter(){
        if(text.isNotEmpty()){
            val lastChar = text[text.lastIndex]
            text.deleteCharAt(text.lastIndex)
            binding.edittextAmount.setText(text)
            if(lastChar in listOf('+','-','*','/') ) operator = ' '
            detectOperands(text.toString())
        }
    }
    private fun detectOperands(text: String){
        if(operator == ' '){
            operand1 = text
        }else{
            val operandList = text.split(operator)
            operand1 = operandList[0]
            operand2 = operandList[1]
        }
    }
    private fun addAmount(){

    }

    private fun doOperation(){
        if(operand1.isNotEmpty() && operand2.isNotEmpty()){
            val value1 = operand1.toFloat()
            val value2 = operand2.toFloat()
            val result = when (operator){
                '+' -> value1 + value2
                '-' -> value1 - value2
                '*' -> value1 * value2
                '/' -> {
                    if (value2 == 0.0f) {
                        return
                    }
                    value1 / value2
                }
                else -> 0.0
            }
            text.clear()
            val resultString = result.toString().removeSuffix(".0")
            if (result.toString() != "0") text.append(resultString)
            operand1 = resultString
            operand2 = ""
            operator = ' '
            binding.edittextAmount.setText("$text AZN")
            binding.btnEqual.visibility = View.GONE
            binding.btnDone.visibility = View.VISIBLE
        }

//        if (operand1.isEmpty() || operand2.isEmpty()) return
//        Log.d("tagg",operand1.toString() + " " + operand2.toString())
//        val result = when(operator){
//            '+' -> operand1.toString().toDouble() + operand2.toString().toDouble()
//            '-' -> operand1.toString().toDouble() - operand2.toString().toDouble()
//            '*' -> operand1.toString().toDouble() * operand2.toString().toDouble()
//            '/' -> {
//                if(operand2.toString().toDouble() == 0.0) return
//                operand1.toString().toDouble() / operand2.toString().toDouble()
//            }
//            else -> 0.0
//        }
//        text.clear()
//        if (result.toString() != "0") text.append(result.toString().removeSuffix(".0"))
//        binding.edittextAmount.setText("$text AZN")
//        operand1.replace(0,operand1.length,result.toString())
//        operand2.clear()
//        operationAdded = false
//        dotAdded = false
    }

    private fun operationButtons(){
        binding.btnAdd.setOnClickListener {
            appendOperation('+')
        }
        binding.btnMultiplication.setOnClickListener {
            appendOperation('*')
        }
        binding.btnDivision.setOnClickListener {
            appendOperation('/')
        }
        binding.btnMinus.setOnClickListener {
            appendOperation('-')
        }

    }

    private fun numericalButtons() {
        binding.btn1.setOnClickListener {
            appendNumber('1')
        }
        binding.btn2.setOnClickListener {
            appendNumber('2')
        }
        binding.btn3.setOnClickListener {
            appendNumber('3')
        }
        binding.btn4.setOnClickListener {
            appendNumber('4')
        }
        binding.btn5.setOnClickListener {
            appendNumber('5')
        }
        binding.btn6.setOnClickListener {
            appendNumber('6')
        }
        binding.btn7.setOnClickListener {
            appendNumber('7')
        }
        binding.btn8.setOnClickListener {
            appendNumber('8')
        }
        binding.btn9.setOnClickListener {
            appendNumber('9')
        }
        binding.btn0.setOnClickListener {
          appendNumber('0')
        }
        binding.btnDot.setOnClickListener {
            appendNumber('.')
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}