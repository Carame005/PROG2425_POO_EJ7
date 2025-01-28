class Cuenta(var numCuenta : Int, var saldo : Double){
    fun esMorosa(): Boolean{
        if (saldo <= 0){
            return true
        }
        return false
    }
    fun transferirDinero(persona1: Persona, persona2: Persona, cuenta1: Cuenta,cuenta2: Cuenta,saldo: Double) : Boolean{
        if ((saldo + cuenta1.saldo >= 0) && (cuenta2.saldo - saldo >= 0)){
            return true
        }
        return false
    }
}

class Persona( var dni : String, cuentas : Array<Cuenta>){
    private val maxCuentas : Int = 3
    init {
        require(dni.length == 9){"El DNI debe ser de 9 digitos"}
        require(cuentas.size <= maxCuentas){"No puedes tener más de 3 cuentas"}
        }

    fun añadirCuenta(nuevaCuenta : Cuenta){

    }

}



fun main() {

}