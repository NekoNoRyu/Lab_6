package parser;

import collectionClasses.*;
import patternCommand.Application;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class InteractiveParser {
    Application application;

    public InteractiveParser(Application application) {
        this.application = application;
    }

    public StudyGroup parseStudyGroup() {
        String name = parseName();
        Coordinates coordinates = Coordinates.builder().
                x(parseCoordinateX()).
                y(parseCoordinateY()).
                build();
        Date creationDate = new Date();
        long studentsCount = parseStudentsCount();
        FormOfEducation formOfEducation = parseFormOfEducation();
        if (formOfEducation.equals(FormOfEducation.NULL)) {
            formOfEducation = null;
        }
        Semester semester = parseSemester();
        Person groupAdmin = null;
        String groupAdminName = parseGroupAdminName();
        if (!groupAdminName.equals("")) {
            groupAdmin = Person.builder().
                    groupAdminName(groupAdminName).
                    groupAdminBirthday(parseGroupAdminBirthday()).
                    groupAdminWeight(parseGroupAdminWeight()).
                    build();
        }
        return StudyGroup.builder().
                id(0).
                name(name).
                coordinates(coordinates).
                creationDate(creationDate).
                studentsCount(studentsCount).
                formOfEducation(formOfEducation).
                semester(semester).
                groupAdmin(groupAdmin).
                build();
    }

    public Long parseKey() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Long key;
            try {
                if (!application.getCommands().isEmpty()) {
                    key = Parser.parseKey(application.getCommands().pollFirst().trim());
                } else {
                    System.out.println("Enter key (it can only be long)");
                    key = Parser.parseKey(scanner.nextLine().trim());
                }
            } catch (NoSuchElementException e) {
                return -9223372036854775808L;
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
                continue;
            }
            return key;
        }
    }

    public long parseId() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            long id;
            try {
                if (!application.getCommands().isEmpty()) {
                    id = Parser.parseId(application.getCommands().pollFirst().trim());
                } else {
                    System.out.println("Enter id (it can only be long and higher than 0)");
                    id = Parser.parseId(scanner.nextLine().trim());
                }
            } catch (NoSuchElementException e) {
                return 0;
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
                continue;
            }
            return id;
        }
    }

    public String parseName() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String name;
            try {
                if (!application.getCommands().isEmpty()) {
                    name = Parser.parseName(application.getCommands().pollFirst().trim());
                } else {
                    System.out.println("Enter name (it can not be empty)");
                    name = Parser.parseName(scanner.nextLine().trim());
                }
            } catch (NoSuchElementException e) {
                return "0";
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
                continue;
            }
            return name;
        }
    }

    public Double parseCoordinateX() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Double coordinateX;
            try {
                if (!application.getCommands().isEmpty()) {
                    coordinateX = Parser.parseCoordinateX(application.getCommands().pollFirst().trim());
                } else {
                    System.out.println("Enter coordinate x (it can only be double and higher than -682)");
                    coordinateX = Parser.parseCoordinateX(scanner.nextLine().trim());
                }
            } catch (NoSuchElementException e) {
                return 0D;
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
                continue;
            }
            return coordinateX;
        }
    }

    public Double parseCoordinateY() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Double coordinateY;
            try {
                if (!application.getCommands().isEmpty()) {
                    coordinateY = Parser.parseCoordinateY(application.getCommands().pollFirst().trim());
                } else {
                    System.out.println("Enter coordinates y (it can only be double)");
                    coordinateY = Parser.parseCoordinateY(scanner.nextLine().trim());
                }
            } catch (NoSuchElementException e) {
                return 0D;
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
                continue;
            }
            return coordinateY;
        }
    }

    public Long parseStudentsCount() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Long studentsCount;
            try {
                if (!application.getCommands().isEmpty()) {
                    studentsCount = Parser.parseStudentsCount(application.getCommands().pollFirst().trim());
                } else {
                    System.out.println("Enter studentsCount (it can only be long and higher than 0)");
                    studentsCount = Parser.parseStudentsCount(scanner.nextLine().trim());
                }
            } catch (NoSuchElementException e) {
                return 0L;
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
                continue;
            }
            return studentsCount;
        }
    }

    public FormOfEducation parseFormOfEducation() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            FormOfEducation formOfEducation;
            try {
                if (!application.getCommands().isEmpty()) {
                    formOfEducation = Parser.parseFormOfEducation(application.getCommands().pollFirst().trim());
                } else {
                    System.out.println("Enter formOfEducation " +
                            "(it can only be DISTANCE_EDUCATION, FULL_TIME_EDUCATION, EVENING_CLASSES or empty(null))");
                    formOfEducation = Parser.parseFormOfEducation(scanner.nextLine().trim());
                }
            } catch (NoSuchElementException e) {
                return FormOfEducation.NULL;
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
                continue;
            }
            return formOfEducation;
        }
    }

    public Semester parseSemester() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Semester semester;
            try {
                if (!application.getCommands().isEmpty()) {
                    semester = Parser.parseSemester(application.getCommands().pollFirst().trim());
                } else {
                    System.out.println("Enter semester (it can only be FIRST, SECOND, FOURTH, SIXTH or EIGHTH)");
                    semester = Parser.parseSemester(scanner.nextLine().trim());
                }
            } catch (NoSuchElementException e) {
                return Semester.FIRST;
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
                continue;
            }
            return semester;
        }
    }


    public String parseGroupAdminName() {
        Scanner scanner = new Scanner(System.in);
        String groupAdminName;
        try {
            if (!application.getCommands().isEmpty()) {
                groupAdminName = Parser.parseGroupAdminName(application.getCommands().pollFirst().trim());
            } else {
                System.out.println("Enter groupAdminName (if this field is empty, groupAdmin will be null)");
                groupAdminName = Parser.parseGroupAdminName(scanner.nextLine().trim());
            }
        } catch (NoSuchElementException e) {
            return "";
        }
        return groupAdminName;
    }

    public ZonedDateTime parseGroupAdminBirthday() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            ZonedDateTime groupAdminBirthday;
            try {
                if (!application.getCommands().isEmpty()) {
                    groupAdminBirthday = Parser.parseGroupAdminBirthday(application.getCommands().pollFirst().trim());
                } else {
                    System.out.println("Enter groupAdminBirthday (Format: yyyy-MM-ddThh:mm + ZoneId.SHORT_IDS key)");
                    groupAdminBirthday = Parser.parseGroupAdminBirthday(scanner.nextLine().trim());
                }
            } catch (NoSuchElementException e) {
                return ZonedDateTime.of(LocalDateTime.of(0, 0, 0, 0, 0), ZoneId.of("ACT"));
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
                continue;
            }
            return groupAdminBirthday;
        }
    }

    public Integer parseGroupAdminWeight() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Integer groupAdminWeight;
            try {
                if (!application.getCommands().isEmpty()) {
                    groupAdminWeight = Parser.parseGroupAdminWeight(application.getCommands().pollFirst().trim());
                } else {
                    System.out.println("Enter groupAdminWeight (it can only be integer and higher than 0)");
                    groupAdminWeight = Parser.parseGroupAdminWeight(scanner.nextLine().trim());
                }
            } catch (NoSuchElementException e) {
                return 0;
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
                continue;
            }
            return groupAdminWeight;
        }
    }
}
