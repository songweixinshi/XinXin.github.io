/**
 * Created by xinxin on 2018/6/19.
 */

class Greeter(val name:String){
    fun greet(){
        println("Hello,$name");
    }
}

fun jia(a:Int,b:Int) = a+b; //非public不需要写明返回值，自动判断

public fun jia2(a:Int,b:Int) = a+b;

public fun jia3(a:Int,b:Int):Int{
   return a + b
}

public fun jia4(a:Int,b:Int):Unit {//Unit相当于返回void，可以省略
    print(a+b)
}
//不定长参数
fun sum(vararg v:Int):Int{
    var sum:Int=0;
    for (vr in v){
        sum+=vr
    }
    return sum;
}
//变量
var a:Int=1 // 方法外必须初始化
var b=2   //自动判定是Int
//常量
val c:Int=2
val d=3


fun main(args:Array<String>){
    var c:Int
    c=6
    Greeter("World!").greet()
    println(jia(1,2)+jia3(1,2))
    println(sum(1,2,3,4,5,6,6))

    //匿名函数表达式
    val sumlLambda:(String,String)->String={x,y->x+y};
    println(sumlLambda("aa","bbb"))

    //字符串模板
    var a = 1
    val aaa = "a is $a"
    a=2
    val aaaaa="${aaa.replace("is","was")},but now is $a"

    //bbb可以为null
    var bbb:String?="123"

    //如果bbb=null,ccc=null，不跑异常
    var ccc = bbb?.toInt()
    println(ccc)
    //如果bbb=null,抛异常
    var cccc=bbb!!.toInt();
    println(cccc)

    var bbbbb:String?=null;
    var ccccc=bbbbb?:-2
    println(ccccc)

    //  当一个引用可能为null时，对应的类型声明必须明确的标记为可为null
    fun isNull(abc:String):Int?{
        return abc.toInt();
    }

    val x = 1
    val y = null
    //判断不为null会自动初始化成声明类型
    fun checkxy(x:Int,y:Int){
        if(x!=null && y!=null){
            println("---------------,${x*y}")
        }
    }

    //判断数据类型类似java中instanceof
    fun getStringLength(obj:Any):Int?{
        if(obj is String){//自动转为String
            return obj.length
        }
        if(obj !is String&&obj is Int){
            return obj
        }
        //这里的obj任然是any类型的引用
        return null;
    }

    fun getStringLength2(obj:Any):Int?{
        if(obj !is String){
            return null
        }
        return obj.length
    }
    fun getStringLength3(obj:Any):Int?{
        //&&右侧就会把obj转为string
        if(obj is String && obj.length>0){
            return obj.length
        }
        return null
    }
    // 使用step指定步长
    for(i in 1..4) print(i) //输出“1234”
    // downTo倒序
    for(i in 4 downTo 1 step 2) print(i) //输出“42”

    for(i in 1 until 10) println(i) //i in [1,10)排除10
}
