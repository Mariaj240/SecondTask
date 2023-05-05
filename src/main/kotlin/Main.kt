import java.io.File
import java.io.FileWriter
import java.nio.file.Files
import kotlin.math.roundToInt

class LS() {

    companion object {
        fun execute(args: Array<String>) {
            Parser().parseArg(args)
//    function1(
//        "input/",
//        true, false, false, ""
//    )
        }
    }
}

fun main(args: Array<String>) {
    LS.execute(args)
}


fun function1(inputName: String, l: Boolean, h: Boolean, r: Boolean, outputName: String = ""): String {
    val file = File(inputName)
    if (file.exists()) {
        var result = ""
        if (file.isDirectory) {
            val list = file.list()
            if (r) {
                list!!.sortDescending()
            } else {
                list!!.sort()
            }
            if (outputName.isEmpty()) {
                for (i in list) {
                    val file2 = File(inputName + i)
                    if (l) {
                        val read = file2.canRead()
                        val write = file2.canWrite()
                        val execute = file2.canExecute()
                        var str = "Permissions: " + if (read) "1" else "0"
                        str += if (write) "1" else "0"
                        str += if (execute) "1" else "0"
                        result +=
                            file2.name + " " +
                                    " " + Files.getLastModifiedTime(file2.toPath()) + " " + str +
                                    " " + Files.size(file2.toPath()) + "\n"
                    } else if (h) {
                        val read = file2.canRead()
                        val write = file2.canWrite()
                        val execute = file2.canExecute()
                        var str = "Permissions: " + if (read) "read " else ""
                        str += if (write) "write " else ""
                        str += if (execute) "execute" else ""
                        result += file2.name + " " + convert(Files.size(file2.toPath()).toString()) +
                                " " + str + "\n"
                    }
                }
                return result
            } else {
                val file1 = FileWriter(outputName)
                for (i in list) {
                    val file2 = File(inputName + i)
                    if (l) {
                        val read = file2.canRead()
                        val write = file2.canWrite()
                        val execute = file2.canExecute()
                        var str = "Permissions: " + if (read) "1" else "0"
                        str += if (write) "1" else "0"
                        str += if (execute) "1" else "0"
                        file1.write (
                            file2.name + " " +
                                    " " + Files.getLastModifiedTime(file2.toPath()) + " " + str +
                                    " " + Files.size(file2.toPath()) + "\n"
                        )
                    } else if (h) {
                        val read = file2.canRead()
                        val write = file2.canWrite()
                        val execute = file2.canExecute()
                        var str = "Permissions: " + if (read) "read " else ""
                        str += if (write) "write " else ""
                        str += if (execute) "execute" else ""
                        file1.write (file2.name + " " + convert(Files.size(file2.toPath()).toString()) +
                                " " + str + "\n")
                    }
                }
                file1.close()
            }
        } else {
            if (outputName.isEmpty()) {
                if (l) {
                    val read = file.canRead()
                    val write = file.canWrite()
                    val execute = file.canExecute()
                    var str = "Permissions: " + if (read) "1" else "0"
                    str += if (write) "1" else "0"
                    str += if (execute) "1" else "0"
                    return (
                        file.name + " " +
                                " " + Files.getLastModifiedTime(file.toPath()) + " " + str +
                                " " + Files.size(file.toPath())
                    )
                } else if (h) {
                    val read = file.canRead()
                    val write = file.canWrite()
                    val execute = file.canExecute()
                    var str = "Permissions: " + if (read) "read " else ""
                    str += if (write) "write " else ""
                    str += if (execute) "execute" else ""
                    return (file.name + " " + convert(Files.size(file.toPath()).toString()) + " " + str)
                }
            } else {
                val file1 = FileWriter(outputName)
                if (l) {
                    val read = file.canRead()
                    val write = file.canWrite()
                    val execute = file.canExecute()
                    var str = "Permissions: " + if (read) "1" else "0"
                    str += if (write) "1" else "0"
                    str += if (execute) "1" else "0"
                    file1.write (
                        file.name + " " +
                                " " + Files.getLastModifiedTime(file.toPath()) + " " + str +
                                " " + Files.size(file.toPath()) + "\n"
                    )
                } else if (h) {
                    val read = file.canRead()
                    val write = file.canWrite()
                    val execute = file.canExecute()
                    var str = "Permissions: " + if (read) "read " else ""
                    str += if (write) "write " else ""
                    str += if (execute) "execute" else ""
                    file1.write (file.name + " " + convert(Files.size(file.toPath()).toString()) +
                            " " + str + "\n")
                }
                file1.close()
            }
        }
    }
    return ""
}


fun convert(str: String): String {
    if (str.toDouble() / 1024 / 1024 / 1024 >= 1) {
        return roundN(str.toDouble() / 1024 / 1024 / 1024).toString() + "Гб"
    } else if (str.toDouble() / 1024 / 1024 >= 1) {
        return roundN(str.toDouble() / 1024 / 1024).toString() + "Мб"
    } else if (str.toDouble() / 1024 >= 1) {
        return roundN(str.toDouble() / 1024).toString() + "Кб"
    } else {
        return roundN(str.toDouble()).toString() + "байт"
    }
}

fun roundN(number: Double): Double {
    return (number * 100.0).roundToInt() / 100.0
}