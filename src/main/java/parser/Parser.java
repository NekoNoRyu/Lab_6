package parser;

import collectionClasses.*;
import com.opencsv.RFC4180Parser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

public class Parser {
    public static ArrayList<String> parseCSVToArrayList(String x) throws InvalidDataException{
        try {
            return new ArrayList<>(Arrays.asList((new RFC4180Parser()).parseLine(x)));
        } catch (IOException e) {
            throw new InvalidDataException("Wrong CSV string");
        }
    }

    public static StudyGroup parseArrayListToStudyGroup(ArrayList<String> x) throws InvalidDataException{
        if (x.size() != 9) {
            throw new InvalidDataException("StudyGroup can only contain 9 elements");
        }
        String name = Parser.parseName(x.get(0));
        Coordinates coordinates = Coordinates.builder().
                x(Parser.parseCoordinateX(x.get(1))).
                y(Parser.parseCoordinateY(x.get(2))).
                build();
        Date creationDate = new Date();
        long studentsCount = Parser.parseStudentsCount(x.get(3));
        FormOfEducation formOfEducation = Parser.parseFormOfEducation(x.get(4));
        if (formOfEducation.equals(FormOfEducation.NULL)) {
            formOfEducation = null;
        }
        Semester semester = Parser.parseSemester(x.get(5));
        Person groupAdmin = null;
        String groupAdminName = Parser.parseGroupAdminName(x.get(6));
        if (!groupAdminName.equals("")) {
            groupAdmin = Person.builder().
                    groupAdminName(groupAdminName).
                    groupAdminBirthday(Parser.parseGroupAdminBirthday(x.get(7))).
                    groupAdminWeight(Parser.parseGroupAdminWeight(x.get(8))).
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



    public static String parseEntryToCSV(Map.Entry<Long, StudyGroup> x) {
        ArrayList<String> toCSV = new ArrayList<>();
        toCSV.add(x.getKey().toString());
        StudyGroup studyGroup = x.getValue();
        toCSV.add(studyGroup.getName());
        toCSV.add(studyGroup.getCoordinates().getX().toString());
        toCSV.add(Double.valueOf(studyGroup.getCoordinates().getY()).toString());
        toCSV.add(Long.valueOf(studyGroup.getStudentsCount()).toString());
        if (studyGroup.getFormOfEducation() == null) {
            toCSV.add("");
        } else {
            toCSV.add(studyGroup.getFormOfEducation().toString());
        }
        toCSV.add(studyGroup.getSemester().toString());
        if (studyGroup.getGroupAdmin() == null) {
            toCSV.add("");
            toCSV.add("");
            toCSV.add("");
        } else {
            toCSV.add(studyGroup.getGroupAdmin().getGroupAdminName());
            ZonedDateTime birthday = studyGroup.getGroupAdmin().getGroupAdminBirthday();
            String zone = birthday.getZone().toString();
            for (Map.Entry<String,String> entry : ZoneId.SHORT_IDS.entrySet()) {
                if (zone.equals(entry.getValue())) {
                    zone = entry.getKey();
                    break;
                }
            }
            toCSV.add(birthday.toLocalDateTime() + zone);
            toCSV.add(Integer.valueOf(studyGroup.getGroupAdmin().getGroupAdminWeight()).toString());
        }
        RFC4180Parser rfc4180Parser = new RFC4180Parser();
        return rfc4180Parser.parseToLine(toCSV.toArray(new String[0]), false);
    }



    public static long parseId(String x) throws InvalidDataException {
        try {
            long result = Long.parseLong(x);
            if (result <= 0) {
                throw (new InvalidDataException("Id can only be higher than 0"));
            }
            return result;
        } catch (NumberFormatException e) {
            throw (new InvalidDataException("Id can only be long"));
        }
    }

    public static Long parseKey(String x) throws InvalidDataException {
        try {
            Long result = Long.parseLong(x);
            return result;
        } catch (NumberFormatException e) {
            throw (new InvalidDataException("Key can only be long"));
        }
    }

    public static String parseName(String x) throws InvalidDataException {
        if (x.equals("")) {
            throw (new InvalidDataException("Name can not be empty"));
        }
        return x;
    }

    public static Double parseCoordinateX(String x) throws InvalidDataException {
        try {
            Double result = Double.parseDouble(x);
            if (result <= -682) {
                throw (new InvalidDataException("CoordinateX can only be higher than -682"));
            }
            return result;
        } catch (NumberFormatException e) {
            throw (new InvalidDataException("CoordinateX can only be a double"));
        }
    }

    public static Double parseCoordinateY(String x) throws InvalidDataException {
        try {
            return Double.parseDouble(x);
        } catch (NumberFormatException e) {
            throw (new InvalidDataException("CoordinateY can only be a double"));
        }
    }

    public static Long parseStudentsCount(String x) throws InvalidDataException {
        try {
            Long result = Long.parseLong(x);
            if (result <= 0) {
                throw (new InvalidDataException("StudentsCount can only be higher than 0"));
            }
            return result;
        } catch (NumberFormatException e) {
            throw (new InvalidDataException("StudentsCount can only be long"));
        }
    }

    //if this field is FormOfEducation.NULL, formOfEducation will be null
    public static FormOfEducation parseFormOfEducation(String x) throws InvalidDataException {
        try {
            if (x.equals("")) {
                return FormOfEducation.NULL;
            }
            return FormOfEducation.valueOf(x);
        } catch (IllegalArgumentException e) {
            throw (new InvalidDataException("FormOfEducation can only be DISTANCE_EDUCATION, FULL_TIME_EDUCATION, EVENING_CLASSES or null"));
        }
    }

    public static Semester parseSemester(String x) throws InvalidDataException {
        try {
            return Semester.valueOf(x);
        } catch (IllegalArgumentException e) {
            throw (new InvalidDataException("Semester can only be FIRST, SECOND, FOURTH, SIXTH or EIGHTH"));
        }
    }

    //if this field is "", groupAdmin will be null
    public static String parseGroupAdminName(String x) {
        return x;
    }

    public static ZonedDateTime parseGroupAdminBirthday(String x) throws InvalidDataException {
        try {
            int year = Integer.parseInt(x.substring(0, 4));
            int month = Integer.parseInt(x.substring(5, 7));
            int day = Integer.parseInt(x.substring(8, 10));
            int hour = Integer.parseInt(x.substring(11, 13));
            int minute = Integer.parseInt(x.substring(14, 16));
            ZoneId zoneId = ZoneId.of(ZoneId.SHORT_IDS.get(x.substring(16, 19)));
            LocalDateTime localDateTime = LocalDateTime.of(year, month, day, hour, minute);
            ZonedDateTime result = ZonedDateTime.of(localDateTime, zoneId);
            return result;
        } catch (Exception e) {
            throw (new InvalidDataException("Format: yyyy-MM-ddThh:mm + ZoneId.SHORT_IDS key"));
        }
    }

    public static Integer parseGroupAdminWeight(String x) throws InvalidDataException {
        try {
            Integer result = Integer.parseInt(x);
            if (result <= 0) {
                throw (new InvalidDataException("GroupAdminWeight can only be higher than 0"));
            }
            return result;
        } catch (NumberFormatException e) {
            throw (new InvalidDataException("GroupAdminWeight can only be a integer"));
        }
    }
}