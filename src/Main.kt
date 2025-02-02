class Cuenta(val numCuenta: Int, var saldo: Double) {

    fun ingresarDinero(cantidad: Double) {
        require(cantidad > 0) { "La cantidad a ingresar debe ser positiva" }
        saldo += cantidad
    }

    fun retirarDinero(cantidad: Double): Boolean {
        return if (saldo >= cantidad) {
            saldo -= cantidad
            true
        } else {
            println("Saldo insuficiente para retirar $cantidad euros.")
            false
        }
    }

    fun transferirDinero(destino: Cuenta, cantidad: Double): Boolean {
        return if (retirarDinero(cantidad)) {
            destino.ingresarDinero(cantidad)
            println("Transferencia de $cantidad€ realizada con éxito de cuenta $numCuenta a cuenta ${destino.numCuenta}.")
            true
        } else {
            println("Transferencia fallida. No hay suficiente saldo en la cuenta $numCuenta.")
            false
        }
    }
}

class Persona(val dni: String) {
    private val cuentas = arrayOfNulls<Cuenta>(3)

    init {
        require(dni.length == 9) { "El DNI debe tener 9 caracteres" }
    }

    fun añadirCuenta(nuevaCuenta: Cuenta): Boolean {
        for (i in cuentas.indices) {
            if (cuentas[i] == null) {
                cuentas[i] = nuevaCuenta
                return true
            }
        }
        println("No se pueden añadir más cuentas. Límite de 3 alcanzado.")
        return false
    }

    fun esMorosa(): Boolean {
        return cuentas.any { it?.saldo ?: 0.0 < 0 }
    }

    fun obtenerCuenta(numCuenta: Int): Cuenta? {
        return cuentas.find { it?.numCuenta == numCuenta }
    }

    fun mostrarCuentas() {
        println("Cuentas de $dni:")
        for (cuenta in cuentas) {
            cuenta?.let { println("Cuenta ${it.numCuenta}: ${it.saldo}€") }
        }
    }
}

fun main() {
    val persona = Persona("58454762K")

    val cuenta1 = Cuenta(1, 0.0)
    val cuenta2 = Cuenta(2, 700.0)

    persona.añadirCuenta(cuenta1)
    persona.añadirCuenta(cuenta2)

    cuenta1.ingresarDinero(1100.0)

    cuenta2.retirarDinero(750.0)

    println("¿La persona es morosa? ${if (persona.esMorosa()) "Sí" else "No"}")

    cuenta1.transferirDinero(cuenta2, 250.0)

    persona.mostrarCuentas()
}
