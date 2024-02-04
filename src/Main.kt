import java.text.DecimalFormat

open class Persona(nombre: String, edad: Int){

    val nombre = nombre
    var edad = edad
    fun celebrarCumple(){
        edad++

        println(edad)
    }
    override fun toString() = "$nombre, $edad."
}
open class Empleado(nombre: String, edad: Int, salarioBase: Double, porcentajeImpuestos: Double = 10.0): Persona(nombre, edad){

    val salarioBase = salarioBase
    var porcentajeImpuestos = porcentajeImpuestos

    open fun calcularSalario() = salarioBase * (100 - porcentajeImpuestos)/100

       // return "%, .2f".format(salarioFormateado).toDouble() <- no termino de sacarlo de esta manera. Con el bigDecimals sí se hacerlo pero quiero preguntar si existe una forma más cómoda que tener que importar dependencias, etc.


    fun trabajar() = println("Actualmente ${this.nombre} está trabajando en la empresa.")

    override fun toString() = "Nombre: ${this.nombre}, edad: ${this.edad}, salario: ${this.calcularSalario()}."
}

class Gerente(nombre: String, edad: Int, salarioBase: Double, porcentajeImpuestos: Double = 33.99, var bonus: Double, exentoImpuestos: Boolean = false): Empleado(nombre, edad, salarioBase, porcentajeImpuestos){


    init {
        if (exentoImpuestos != false){
            this.porcentajeImpuestos = 0.0
        }
    }

    override fun calcularSalario() = (salarioBase * (100 - porcentajeImpuestos)/100) + bonus

    fun administrar() = println("Actualmente ${this.nombre} está administrando la empresa.")

    override fun toString() = "Nombre: ${this.nombre}, edad: ${this.edad}, salario: ${this.calcularSalario()}, bonus: ${this.bonus}."

}

fun main() {
    val persona1 = Persona("Noe", 22)
    val persona2 = Empleado("Miguel", 33, 5000.0, 20.0)
    val persona3 = Gerente("Víctor", 22, 12000.0, 20.0, 300.0)

    println(persona1.toString())
    persona1.celebrarCumple().also { println("Feliz cumple ${persona1.nombre}! Ahora tienes ${persona1.edad} añitos!!") } // <- Técnicamente esto debería ir en el método celebrarCumple() pero quería usar el also asfgpojkg

    println("------")

    println(persona2.calcularSalario())
    println(persona2.toString())
    persona2.trabajar()

    println("------")

    println(persona3.toString())
    persona3.administrar()

}