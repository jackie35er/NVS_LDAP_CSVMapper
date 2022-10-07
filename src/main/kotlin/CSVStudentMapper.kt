object CSVStudentMapper {

    fun mapFromCsv(csvString: String): Student{
        val splits = csvString.split(";")

        return Student(
                Integer.parseInt(splits[0]),
                splits[1].trim(),
                splits[2].trim(),
        )
    }
}