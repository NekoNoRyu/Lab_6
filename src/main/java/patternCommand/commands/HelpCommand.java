package patternCommand.commands;

import patternCommand.Application;
import patternCommand.Command;

public class HelpCommand extends Command {
    public HelpCommand(Application application) {
        super(application);
    }

    @Override
    public void execute() {
        if (!getApplication().getCommandArg().equals("")) {
            System.out.println("help : argument can only be empty");
            return;
        }
        System.out.println("""
                {element} вводится по одному полю в строке:
                name,coordinatesX,coordinatesY,studentsCount,formOfEducation,semesterEnum,groupAdminName,groupAdminBirthday,groupAdminWeight
                [path] идет из директории проекта Lab_5_new, например: example/csv
                help : вывести справку по доступным командам
                info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
                show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении
                insert [key] {element} : добавить новый элемент с заданным ключом
                update [id] {element} : обновить значение элемента коллекции, id которого равен заданному
                remove_key [key] : удалить элемент из коллекции по его ключу
                clear : очистить коллекцию
                save : сохранить коллекцию в файл
                execute_script [path] : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
                exit : завершить программу (без сохранения в файл)
                remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный
                replace_if_lower [key] {element} : заменить значение по ключу, если новое значение меньше старого
                remove_greater_key [key] : удалить из коллекции все элементы, ключ которых превышает заданный
                remove_any_by_form_of_education [formOfEducation] : удалить из коллекции один элемент, значение поля formOfEducation которого эквивалентно заданному
                count_by_form_of_education [formOfEducation] : вывести количество элементов, значение поля formOfEducation которых равно заданному
                filter_greater_than_students_count [studentsCount] : вывести элементы, значение поля studentsCount которых больше заданного""");
        System.out.println("""
                SHORT_IDS
                ACT: Australia/Darwin
                AET: Australia/Sydney
                AGT: America/Argentina/Buenos_Aires
                ART: Africa/Cairo
                AST: America/Anchorage
                BET: America/Sao_Paulo
                BST: Asia/Dhaka
                CAT: Africa/Harare
                CNT: America/St_Johns
                CST: America/Chicago
                CTT: Asia/Shanghai
                EAT: Africa/Addis_Ababa
                ECT: Europe/Paris
                IET: America/Indiana/Indianapolis
                IST: Asia/Kolkata
                JST: Asia/Tokyo
                MIT: Pacific/Apia
                NET: Asia/Yerevan
                NST: Pacific/Auckland
                PLT: Asia/Karachi
                PNT: America/Phoenix
                PRT: America/Puerto_Rico
                PST: America/Los_Angeles
                SST: Pacific/Guadalcanal
                VST: Asia/Ho_Chi_Minh
                EST: -05:00
                MST: -07:00
                HST: -10:00"""
        );
    }
}
