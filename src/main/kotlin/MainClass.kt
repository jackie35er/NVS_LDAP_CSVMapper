import java.io.File
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Path

fun main() {
    val students = Files.lines(
        Path.of("src/main/resources/5BHIF.csv"),
        Charset.forName("windows-1252")
    ).use { line ->
            line.map {
                CSVStudentMapper.mapFromCsv(it)
            }.toList()
        }
    val ldapString = """
        dn: cn=%s,ou=users,o=if,dc=goetz,dc=lan
        gidNumber: %d
        uidNumber: %d
        uid: %d
        objectClass: inetOrgPerson
        objectClass: organizationalPerson
        objectClass: person
        objectClass: posixAccount
        objectClass: top
        homeDirectory: %d
        sn: %d
        cn: %d%n
    """.trimIndent()

    val ldapStrings = students.joinToString(separator = "\n") {
        ldapString.format(
            it.firstName + " " + it.lastName,
            it.id,
            it.id,
            it.id,
            it.id,
            it.id,
            it.id
        )
    }

    val outputFile = Path.of("src/main/resources/5BHIF.ldap").toFile()
    outputFile.writeText(ldapStrings)
}