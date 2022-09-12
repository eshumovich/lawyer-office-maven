package com.solvd.lawyers;

import com.solvd.lawyers.characteristic.Client;
import com.solvd.lawyers.characteristic.Lawyer;
import com.solvd.lawyers.characteristic.Staff;
import com.solvd.lawyers.inheritance.Human;
import com.solvd.lawyers.inheritance.IIncreaseRating;
import com.solvd.lawyers.inheritance.Printable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public final class LawyerOfficeUtils {

    private static final Logger LOGGER = LogManager.getLogger(LawyerOfficeUtils.class);

    public static void getBetterLawyer(LawyerOffice lawyerOffice) {
        Staff staff = lawyerOffice.getStaff();
        List<Lawyer<? extends IIncreaseRating>> lawyers = staff.getLawyers();
        lawyers.stream()
                .filter(lawyer -> lawyer.getRating() > 8)
                .forEach(lawyer -> LOGGER.info(lawyer.getName() + " with rating " + lawyer.getRating() + " is chosen"));
    }

    public static void showEducation(Human<?>... humans) {
        Arrays.stream(humans)
                .forEach(Human::enterUniversity);
    }

    public static void print(Printable... objects) {
        Arrays.stream(objects)
                .forEach(Printable::print);
    }

    public static void contactLawyer(Client<? extends IIncreaseRating> client, Lawyer<? extends IIncreaseRating> lawyer) {
        while (client.isMoneyRemained()) {
            lawyer.provideDefendence(client);
        }
    }
}
