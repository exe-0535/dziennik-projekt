package com.example.dziennik.helpers;

import com.example.dziennik.dao.LessonDao;
import com.example.dziennik.model.Lesson;
import com.example.dziennik.model.User;
import javafx.beans.property.*;

import java.util.List;
import java.util.Optional;

public class LessonRow {
    private final LongProperty nr;
    private final StringProperty godzina;
    private final StringProperty monday;
    private final StringProperty tuesday;
    private final StringProperty wednesday;
    private final StringProperty thursday;
    private final StringProperty friday;

    public LessonRow(Long nr, String godzina, String monday, String tuesday, String wednesday, String thursday, String friday) {
        this.nr = new SimpleLongProperty(nr);
        this.godzina = new SimpleStringProperty(godzina);
        this.monday = new SimpleStringProperty(monday);
        this.tuesday = new SimpleStringProperty(tuesday);
        this.wednesday = new SimpleStringProperty(wednesday);
        this.thursday = new SimpleStringProperty(thursday);
        this.friday = new SimpleStringProperty(friday);
    }

    public LessonRow() {
        this.nr = new SimpleLongProperty();
        this.godzina = new SimpleStringProperty();
        this.monday = new SimpleStringProperty();
        this.tuesday = new SimpleStringProperty();
        this.wednesday = new SimpleStringProperty();
        this.thursday = new SimpleStringProperty();
        this.friday = new SimpleStringProperty();
    }

    public long getNr() {
        return nr.get();
    }

    public LongProperty nrProperty() {
        return nr;
    }

    public void setNr(long nr) {
        this.nr.set(nr);
    }

    public String getGodzina(long nr) {
        return switch ((int) nr) {
            case 1 -> "7:10-7:55";
            case 2 -> "8:00-8:45";
            case 3 -> "8:55-9:40";
            case 4 -> "9:50-10:35";
            case 5 -> "10:50-11:35";
            case 6 -> "11:45-12:30";
            case 7 -> "12:40-13:25";
            case 8 -> "13:35-14:20";
            case 9 -> "14:25-15:10";
            case 10 -> "15:15-16:00";
            default -> "Invalid nr";
        };
    }

    public StringProperty godzinaProperty() {
        return godzina;
    }

    public void setGodzina(String godzina) {
        this.godzina.set(godzina);
    }

    public String getMonday(long nr, List<Lesson> l) {
        return getDayOfTheWeek(nr,l,"Monday");
    }

    public StringProperty mondayProperty() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday.set(monday);
    }

    public String getTuesday(long nr, List<Lesson> l) {
        return getDayOfTheWeek(nr,l,"Tuesday");
    }

    public StringProperty tuesdayProperty() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday.set(tuesday);
    }

    public String getWednesday(long nr, List<Lesson> l) {
        return getDayOfTheWeek(nr,l,"Wednesday");
    }

    public StringProperty wednesdayProperty() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday.set(wednesday);
    }

    public String getThursday(long nr, List<Lesson> l) {
        return getDayOfTheWeek(nr,l,"Thursday");
    }

    public StringProperty thursdayProperty() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday.set(thursday);
    }

    public String getFriday(long nr, List<Lesson> l) {
        return getDayOfTheWeek(nr,l,"Friday");
    }

    public StringProperty fridayProperty() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday.set(friday);
    }


    public String getDayOfTheWeek(long nr, List<Lesson> l, String day) {
        for (Lesson ls: l
        ) {
            if(ls.getNr() == nr && ls.getDay().equals(day)) {
                if(CurrentUser.getCurrentUser().getRole() == User.Role.TEACHER) {
                    if(ls.getModified()) {
                        return "ZASTĘPSTWO: " + ls.getUnit().getName() + " - " + ls.getClassNumber() + "\n" + ls.getSubject();
                    }
                    return ls.getUnit().getName() + " - " + ls.getClassNumber() + "\n" + ls.getSubject();
                }
                if(CurrentUser.getCurrentUser().getRole() == User.Role.STUDENT) {
                    if(ls.getModified()) {
                        return "ZASTĘPSTWO: " + getInitialsFromEmail(ls.getUser().getEmail()) + " - " + ls.getClassNumber() + "\n" + ls.getSubject();
                    }
                    return getInitialsFromEmail(ls.getUser().getEmail()) + " - " + ls.getClassNumber() + "\n" + ls.getSubject();
                }
            }
        }
        return "";
    }

    public String getInitialsFromEmail(String email) {
        if (email == null || email.isEmpty()) {
            // Handle null or empty email as needed
            return "";
        }

        // Remove any leading/trailing whitespaces
        email = email.trim();

        // Extract the first two characters
        String initials = email.substring(0, Math.min(2, email.length()));

        // Convert to uppercase
        return initials.toUpperCase();
    }
}
