import kotlin.text.appendLine

enum class Nivel {BASICO, INTERMEDIARIO, AVANCADO}

class Usuario (val id: Int, val nome: String, val idade: Int)

data class ConteudoEducacional(val nome: String, val duracao: Int = 60)

data class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>, var nivel: Nivel){
    val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario: Usuario){
        //utilize o parametro $usuario para simular uma matricula (usar a lista de $inscritos).
       if (inscritos.any() {it.id == usuario.id}){
           println("Usuário ${usuario.nome} já está matriculado na formação ${nome} de Nível: ${nivel}")
           return
       }
        inscritos.add(usuario)
        println("Usuário ${usuario.nome} matriculado com sucesso na formação ${nome} de Nível: ${nivel}")
    }

    fun listarInscritosnaFormacaoImpressos() {
        println("\nInscritos na formação '${nome}':")
        inscritos.forEach {
            println(" id:${it.id} - ${it.nome} - idade:${it.idade}")
        }
    }
    fun listarInscritos(): List<Usuario> = inscritos.toList()

    fun cargaHorariaTotal(): Int = conteudos.sumOf { it.duracao }

     fun listarQuantidadeInscritos(): String = buildString {
         appendLine(" ${inscritos.size}")
         appendLine("Formação: $nome")
    }

    fun listarConteudoFormacao(): String = buildString  {
        appendLine("Formação: $nome")
        appendLine("Nível: $nivel")
        appendLine("Conteúdos: (${conteudos.size}):")
        conteudos.forEachIndexed { indice , c ->
            appendLine("${indice +1} - ${c.nome} - ${c.duracao} minutos")
        }
    }

    fun adicionarConteudosFormacao(conteudosNovos: List<ConteudoEducacional>) {
        conteudos = conteudos + conteudosNovos
    }


}



fun main() {
    //analise as classes modeladas para este domínio de aplicação e pense em formas de evoluí-las.
    //simule alguns cenários de teste. Para isso, crie alguns objetos usando as classes em questão.

    val u1 = Usuario(1, "João", 33)
    val u2 = Usuario(2, "Breno", 55)
    val u3 = Usuario(3, "Leandro", 28)

    // Conteúdos
    val c1 = ConteudoEducacional("Introdução ao Kotlin", 30)
    val c2 = ConteudoEducacional("POO em Kotlin", 60)
    val c3 = ConteudoEducacional("Exceções", 20)
    val c4 = ConteudoEducacional("Concorrência", 30)

    // Formação: Kotlin Developer
    val formacaoKotlin = Formacao(
        nome = "Kotlin Developer",
        nivel = Nivel.INTERMEDIARIO,
        conteudos = listOf(c1, c2, c3)
    )

    println(formacaoKotlin.listarConteudoFormacao())
    formacaoKotlin.adicionarConteudosFormacao(listOf(c4))


    // Cenário de matrícula
    println(formacaoKotlin.listarConteudoFormacao())

    formacaoKotlin.matricular(u1)
    formacaoKotlin.matricular(u2)
    formacaoKotlin.matricular(u2) // tentar matricular de novo (deve avisar)
    formacaoKotlin.matricular(u3)

    formacaoKotlin.listarInscritosnaFormacaoImpressos()
    println("Quantidade de Alunos Inscritos: ${formacaoKotlin.listarQuantidadeInscritos()}")
    println("\nCarga horária total: ${formacaoKotlin.cargaHorariaTotal()} minutos")


}
