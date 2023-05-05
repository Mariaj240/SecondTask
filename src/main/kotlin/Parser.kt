import org.kohsuke.args4j.Argument
import org.kohsuke.args4j.CmdLineParser
import org.kohsuke.args4j.Option

class Parser {
    @Option(name = "-l", forbids = ["-h"])
    private var longFormat = false

    @Option(name = "-h", forbids = ["-l"])
    private var humanFormat = false

    @Option(name = "-r")
    private var reverse = false

    @Option(name = "-o")
    private var out: String? = null

    @Argument
    private var arguments: List<String> = ArrayList()

    fun parseArg(args: Array<String>) {
        val parser = CmdLineParser(this)
        try {
            parser.parseArgument(*args)
        }
        catch(e: Exception) {
            println(e.stackTrace)
        }
        val input = arguments[0]
        if (out == null || out == "") {
            function1(input, longFormat, humanFormat, reverse)
        }
        function1(input, longFormat, humanFormat, reverse, out!!)
    }
}
