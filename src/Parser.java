public class Parser {

    public String solve(String line) throws IllegalStateException, IllegalArgumentException {
        State state; //Состояние выражения
        String errorMessage = ""; //Сообщение ошибки
        String[] strings = new String[0]; //Пустой инициализатор чисел
        line = line.replaceAll(" ", ""); //Удаляем все пробелы из строки
        //Ищем какой знак
        if (line.contains("+")) {
            strings = line.split("\\+");
        }

        if (line.contains("-")) {
            strings = line.split("-");
        }

        if (line.contains("*")) {
            strings = line.split("\\*");
        }

        if (line.contains("/")) {
            strings = line.split("/");
        }
        //Если было обнаружено не 2 числа, то это либо знака нет, либо их больше
        if (strings.length != 2) {
            throw new IllegalStateException("В выражении не 2 числа, либо не найден знак");
        }

        //String сконвертировать в Integer
        try {
            Integer.parseInt(strings[0]);
            Integer.parseInt(strings[1]);
            if (Integer.parseInt(strings[0]) > 10 || Integer.parseInt(strings[0] )<= 0) {
                throw new IllegalStateException("Число больше 10 или меньше 0");
            }
            if (Integer.parseInt(strings[1]) > 10 || Integer.parseInt(strings[1] )<= 0) {
                throw new IllegalStateException("Число больше 10 или меньше 0");
            }
            //Если мы здесь, то два числа это целые числа, ставим состояние программы как состояние целых чисел
            state = State.DECIMAL_STATE;
        } catch (NumberFormatException e) {
            try {
                //Если не удалось сконверитировать целые числа, то мы попадём сюда
                Roman.romanToArabic(strings[0]);
                Roman.romanToArabic(strings[1]);
                if (Roman.romanToArabic(strings[0]) < 0 || Roman.romanToArabic(strings[0]) > 10) {
                    throw new IllegalStateException("Число больше 10 или меньше 0");
                }
                if (Roman.romanToArabic(strings[1]) < 0 || Roman.romanToArabic(strings[1]) > 10) {
                    throw new IllegalStateException("Число больше 10 или меньше 0");
                }
                //Если мы здесь, то римские цифры удачно сконвертировались. Ставим состояние римских цифр
                state = State.ROMAN_STATE;
            } catch (IllegalArgumentException ex) {
                //Если мы здесь, то значит что в программе нашлось либо по одному из каждых типов чисел, либо полностью неправильный ввод был
                errorMessage = ex.getMessage(); //Запоминаем ошибку
                state = State.INCORRECT_STATE; //Выставляем некорректное состояние программы
            }
        }
        switch (state) {
            case ROMAN_STATE:
                return romanSolving(line); //В случае римских цифр отправляем строку на решение в метод решения римских цифр
            case DECIMAL_STATE: //В случае целх чисел отправляем строку в
                return String.valueOf(integerSolving(line));
            case INCORRECT_STATE:
                throw new IllegalStateException("Введенная строка содержит ошибки: " + errorMessage);
        }
        throw new IllegalStateException("Некорректное состояние строки");
    }

    private String romanSolving(String line) throws IllegalArgumentException {
        line = line.replaceAll(" ", "");
        if (line.contains("+")) {
            String[] strings = line.split("\\+");
            return Roman.arabicToRoman(Roman.romanToArabic((strings[0])) + Roman.romanToArabic(strings[1]));
        }
        if (line.contains("-")) {
            String[] strings = line.split("-");
            return Roman.arabicToRoman(Roman.romanToArabic((strings[0])) - Roman.romanToArabic(strings[1]));
        }

        if (line.contains("*")) {
            String[] strings = line.split("\\*");
            return Roman.arabicToRoman( Roman.romanToArabic((strings[0])) * Roman.romanToArabic(strings[1]));
        }

        if (line.contains("/")) {
            String[] strings = line.split("/");
            return Roman.arabicToRoman(Roman.romanToArabic((strings[0])) / Roman.romanToArabic(strings[1]));
        }
        throw new IllegalStateException("Не найден знак действия");
    }

    private int integerSolving(String line) throws NumberFormatException {
        line = line.replaceAll(" ", "");
        if (line.contains("+")) {
            String[] strings = line.split("\\+");
            return Integer.parseInt(strings[0]) + Integer.parseInt(strings[1]);
        }

        if (line.contains("-")) {
            String[] strings = line.split("-");
            return Integer.parseInt(strings[0]) - Integer.parseInt(strings[1]);
        }

        if (line.contains("*")) {
            String[] strings = line.split("\\*");
            return Integer.parseInt(strings[0]) * Integer.parseInt(strings[1]);
        }

        if (line.contains("/")) {
            String[] strings = line.split("/");
            return Integer.parseInt(strings[0]) / Integer.parseInt(strings[1]);
        }
        throw new IllegalStateException("Не найден знак действия");
    }
}
