import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

internal class MainKtTest {

    @Test
    fun files() {
        assertEquals(
            "file1 883.0байт Permissions: read write execute",
            function1("input/file1",
                false, true, true, "")
        )
        assertEquals(
            "file1  2023-04-20T13:40:35.5054761Z Permissions: 111 883",
            function1("input/file1",
                true, false, true, "")
        )
        assertEquals(
            "file2 1.06Кб Permissions: read write execute",
            function1("input/file2",
                false, true, false, "")
        )
        assertEquals(
            "file2  2023-04-20T13:42:36.9510344Z Permissions: 111 1082",
            function1("input/file2",
                true, false, false, "")
        )
    }

    @Test
    fun directory() {
        assertEquals(
            "file1  2023-04-20T13:40:35.5054761Z Permissions: 111 883\n" +
                    "file2  2023-04-20T13:42:36.9510344Z Permissions: 111 1082\n",
            function1("input/",
                true, false, false, "")
        )
        assertEquals(
            "file1 883.0байт Permissions: read write execute\n" +
                    "file2 1.06Кб Permissions: read write execute\n",
            function1( "input/",
                false, true, false, "")
        )
        assertEquals(
            "file2  2023-04-20T13:42:36.9510344Z Permissions: 111 1082\n" +
                "file1  2023-04-20T13:40:35.5054761Z Permissions: 111 883\n",
            function1("input/",
                true, false, true, "")
        )
        assertEquals(
            "file2 1.06Кб Permissions: read write execute\n" +
                    "file1 883.0байт Permissions: read write execute\n",
            function1("input/",
                false, true, true, "")
            )
    }

    @Test
    fun mainTest() {
        LS.execute("-l -r -o output/output.txt input/".split(" ").toTypedArray())
        var file = File("output/output.txt")
        """asff
            |fasffsa
        """.trimMargin()
        assertEquals("""file2  2023-04-20T13:42:36.9510344Z Permissions: 111 1082
        |file1  2023-04-20T13:40:35.5054761Z Permissions: 111 883
        |""".trimMargin(),
            file.readText()
            )
    }

}