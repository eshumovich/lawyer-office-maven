package com.solvd.lawyers;

import com.solvd.lawyers.characteristic.Client;
import com.solvd.lawyers.characteristic.Lawyer;
import com.solvd.lawyers.characteristic.Staff;
import com.solvd.lawyers.inheritance.Human;
import com.solvd.lawyers.inheritance.IIncreaseRating;
import com.solvd.lawyers.inheritance.Printable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public final class LawyerOfficeUtils {

    private static final Logger LOGGER = LogManager.getLogger(LawyerOfficeUtils.class);

    public static void getBetterLawyer(LawyerOffice lawyerOffice) {
        Staff staff = lawyerOffice.getStaff();
        List<Lawyer<? extends IIncreaseRating>> lawyers  = staff.getLawyers();
        for (Lawyer<? extends IIncreaseRating> lawyer: lawyers) {
            if (lawyer.getRating() > 9) {
                LOGGER.info(lawyer.getName() + " with rating " + lawyer.getRating() + " is chosen");
            }
        }
    }

    public static void showEducation(Human<?>... humans) {
        for (Human<?> human : humans) {
            human.enterUniversity();
        }
    }

    public static void print(Printable...objects) {
        for(Printable object : objects) {
            object.print();
        }
    }

    public static void contactLawyer(Client<? extends IIncreaseRating> client, Lawyer<? extends IIncreaseRating>... lawyers) {
        while (client.isMoneyRemained()) {
            for(Lawyer<? extends IIncreaseRating> lawyer : lawyers) {
                lawyer.provideDefendence(client);
            }
        }
    }
}
