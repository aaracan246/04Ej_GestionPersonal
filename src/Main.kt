import java.text.DecimalFormat
/**Clase base Persona:
 * Propiedades: nombre(String) y edad(Int)
 *
 * Métodos: toString() -> Devuelve info de la persona // celebrarCumple() -> incrementa edad en 1 y retorna un mensaje de felicitación
 *
 * */
open class Persona(nombre: String, edad: Int){

    val nombre = nombre
    var edad = edad
    fun celebrarCumple(){
        edad++

        println(edad)
    }
    override fun toString() = "$nombre, $edad."
}


/**Clase derivada Empleado (de Persona):
 * Propiedades: salarioBase(Double) y porcentajeImpuestos(Double) (también debe ser aceptado en Int y default de 10.0)
 *
 * Métodos: calcularSalario() -> devuelve salarioBase aplicando impuestos // toString() -> Devuelve info de la persona + la de empleado // trabajar() -> retorna un mensaje de que el empleado está trabajando para la empresa
 *
 * */
open class Empleado(nombre: String, edad: Int, salarioBase: Double, porcentajeImpuestos: Double = 10.0): Persona(nombre, edad){  // No recuerdo cómo hacer que acepte Int también sorry

    val salarioBase = salarioBase
    var porcentajeImpuestos = porcentajeImpuestos

    open fun calcularSalario() = salarioBase * (100 - porcentajeImpuestos)/100

       // return "%, .2f".format(salarioFormateado).toDouble() <- no termino de sacarlo de esta manera. Con el bigDecimals sí sé hacerlo pero quiero preguntar si existe una forma más cómoda que tener que importar dependencias, etc.


    fun trabajar() = println("Actualmente ${this.nombre} está trabajando en la empresa.")

    override fun toString() = "Nombre: ${this.nombre}, edad: ${this.edad}, salario: ${this.calcularSalario()}."
}

/**Clase derivada Gerente (de Empleado):
 * Propiedades: bonus(Double) y exentoImpuestos(Boolean) (false default) // porcentajeImpuestos debe ser sobreescrito para que sea 33.99%.
 *
 * Métodos: calcularSalario() -> devuelve salarioBase aplicando impuestos o sin aplicarlos si exentoImpuestos = true // toString() -> Devuelve info de la persona + la de empleado + la de gerente // administrar() -> retorna un mensaje de que el gerente está administrando la empresa
 *
 * */
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