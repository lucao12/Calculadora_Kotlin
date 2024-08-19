package Regras

class Calculadora {
    private var operando1: String = "0"
    private var operando2: String = "0"
    private var operador: String = ""
    private var isOperando1Decimal: Boolean = false
    private var isOperando2Decimal: Boolean = false
    private val maxLength = 10

    fun input(value: String): String {
        return when {
            value in listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9") -> handleNumberInput(value)
            value == "." -> handleDecimalInput()
            value in listOf("+", "-", "*", "/") -> handleOperatorInput(value)
            value == "=" -> calculate()
            value == "C" -> clear()
            else -> "Erro"
        }
    }

    private fun handleNumberInput(value: String): String {
        if (operador.isEmpty()) {
            if (operando1.length < maxLength) {
                operando1 = if (isOperando1Decimal) {
                    operando1 + value
                } else {
                    if (operando1 == "0") value else operando1 + value
                }
            }
            return operando1
        } else {
            if (operando2.length < maxLength) {
                operando2 = if (isOperando2Decimal) {
                    operando2 + value
                } else {
                    if (operando2 == "0") value else operando2 + value
                }
            }
            return operando2
        }
    }

    private fun handleDecimalInput(): String {
        if (operador.isEmpty()) {
            if (!isOperando1Decimal && operando1.length < maxLength) {
                isOperando1Decimal = true
                operando1 += "."
            }
            return operando1
        } else {
            if (!isOperando2Decimal && operando2.length < maxLength) {
                isOperando2Decimal = true
                operando2 += "."
            }
            return operando2
        }
    }

    private fun handleOperatorInput(value: String): String {
        operador = value
        return operador
    }

    private fun calculate(): String {
        val result = when (operador) {
            "+" -> operando1.toDouble() + operando2.toDouble()
            "-" -> operando1.toDouble() - operando2.toDouble()
            "*" -> operando1.toDouble() * operando2.toDouble()
            "/" -> if (operando2.toDouble() != 0.0) operando1.toDouble() / operando2.toDouble() else return "Erro"
            else -> 0.0
        }
        clear()
        operando1 = result.toString()
        return formatResult(result)
    }

    private fun clear(): String {
        operando1 = "0"
        operando2 = "0"
        operador = ""
        isOperando1Decimal = false
        isOperando2Decimal = false
        return "0"
    }

    private fun formatResult(number: Double): String {
        return if (number % 1 == 0.0) number.toInt().toString() else number.toString()
    }
}
