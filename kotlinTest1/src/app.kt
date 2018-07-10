/**
 * Created by xinxin on 2018/5/3.
 */

fun main(args:Array<String>){
    val b = Array(3,{i->(i*2)})
    for(i in b) print(i)

    println()

    val x:IntArray = intArrayOf(1,2,4)
    x[0] = x[1] + x[2]
    for(i in x) print(i)

    println()

    //多行字符串
    val text1 = """
            |第一行
                    |第二行
        |第三行
    """.trimMargin()
    print(text1)
    //单行字符串|开头可去除左侧的空格和制表符
    println()
    val test2 = "            |xxxxxxx           yyy".trimMargin()
    println(test2)

    //原生字符串和转移字符串内部都支持模板，
    var price = """${'$'}9.99"""
    println(price)

    //不需要java的三元操作符，可以把表达式结果赋值到其他对象、
    var m=2
    var n=1
    val c = if(m>n) m else n

    //when代提switch
    when(m){
        1 -> print("x==1")
        2 -> print("x==2")
        else -> {
            print("x 不是1，也不是2")
        }
    }
    var xx = 2
    when(xx){
        0,1 -> print("x==0 or x==1")
        in 2..5 -> print("x in 2..5")
        else -> print("otherwise")
    }
    fun hasPrefic(x:Any)=when(x){
        is String ->x.startsWith("prefix")
        else ->false
    }

    print(1%2==1)

    
}
