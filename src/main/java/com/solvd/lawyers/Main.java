package com.solvd.lawyers;

import com.solvd.lawyers.characteristic.*;
import com.solvd.lawyers.exception.NameInvalidException;
import com.solvd.lawyers.inheritance.*;
import com.solvd.lawyers.worktime.VisitTime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static final String TEXT_FILE = "src/main/resources/textFile.txt";

    public static void main(String[] args) {
        Address address = new Address("Spain", "Madrid", 10);
        LOGGER.info(address.getCountry() + " " + address.getCity() + " " + address.getHouseNumber());

        Address address1 = new Address("Belarus", "Paris", 12);
        Address address2 = new Address("Belarus", "London", 101);
        Address address3 = new Address("Belarus", "Brest", 148);
        Address address4 = new Address("Belarus", "Porto", 101);

        Lawyer<? extends IIncreaseRating> lawyer1 = new Lawyer<>("Rachel Green", address1, LocalDate.of(1990, 6, 2));
        lawyer1.setRating(9);
        Lawyer<? extends IIncreaseRating> lawyer2 = new Lawyer<>("Monica Geller", address2, LocalDate.of(1986, 12, 8));
        lawyer2.setRating(8);
        Lawyer<? extends IIncreaseRating> lawyer3 = new Lawyer<>("John", address3, LocalDate.of(1990, 11, 1));
        lawyer3.setRating(7);
        Lawyer<? extends IIncreaseRating> lawyer4 = new Lawyer<>("Nikita", address4, LocalDate.of(2000, 3, 4));
        lawyer4.setRating(10);
        Lawyer<? extends IIncreaseRating> lawyer5 = new Lawyer<>("Valentin", address4, LocalDate.of(2001, 2, 1));
        lawyer5.setRating(4);

        List<Lawyer<? extends IIncreaseRating>> lawyers = new ArrayList<>();
        lawyers.add(lawyer1);
        lawyers.add(lawyer2);
        lawyers.add(lawyer3);
        lawyers.stream()
                .map(lawyer -> lawyer.getRating() + 2)
                .sorted()
                .collect(Collectors.toList())
                .forEach(rating -> LOGGER.info("New rating: " + rating));

        LOGGER.info("Lawyers size: " + lawyers.size());

        Staff staff = new Staff(lawyers);
        staff.showAllLawyers(lawyers);

        staff.allLawyersBirthdays(lawyers);

        LOGGER.info("Quantity of available lawyers: " + Lawyer.getQuantityLawyers());

        ClientCase divorce = new ClientCase("High", BigDecimal.valueOf(450), "Divorce process");
        LocalDate prevDate1 = LocalDate.of(2021, 5, 15);
        divorce.caseWaiting(prevDate1);
        ClientCase aliments = new ClientCase("Medium", BigDecimal.valueOf(370), "Aliments");
        LocalDate prevDate2 = LocalDate.of(2019, 8, 10);
        aliments.caseWaiting(prevDate2);

        Client<? extends IIncreaseRating> client1 = new Client<>("Elton", divorce, BigDecimal.valueOf(1500));
        Client<? extends IIncreaseRating> client2 = new Client<>("Valera", aliments, BigDecimal.valueOf(1350));
        Client<? extends IIncreaseRating> client3 = new Client<>("Vera", aliments, BigDecimal.valueOf(1050));


        LOGGER.info("----------------------------------");
        lawyer1.setSalary(BigDecimal.valueOf(150));
        lawyer1.isAvailable();

        List<Client<? extends IIncreaseRating>> clients = new ArrayList<>();
        clients.add(client1);
        clients.add(client2);
        clients.add(client3);
        clients.stream()
                .filter(client -> client.getName() != null)
                .peek(LOGGER::info)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Client doesn't exist"));

        VisitTime visit = new VisitTime(30, 2);
        LOGGER.info("Required time for the case: " + visit.totalMinutes() + " min");

        Service service1 = new Service(visit);
        Service service2 = new Service("ASAP", BigDecimal.valueOf(150), "bill the client for the case");
        Service service3 = new Service("ASAP", BigDecimal.valueOf(170), "Solve the case");

        List<Service> services = new ArrayList<>();
        services.add(service1);
        services.add(service2);
        services.add(service3);
        services.stream()
                .peek(LOGGER::info)
                .findFirst();

        LawyerOffice redClearing = new LawyerOffice("RedClearing",
                LocalDate.of(2002, 2, 1), address, staff, clients, services);

        LOGGER.info("----------------------------------");
        redClearing.startCase();
        redClearing.startCase(true);
        redClearing.setClients(clients);

        LOGGER.info("Quantity of clients: " + redClearing.countClients());
        LOGGER.info(redClearing.getAddress());

        LOGGER.info("----------------------------------");
        redClearing.setServices(services);
        redClearing.showServices();

        ZonedDateTime now = ZonedDateTime.now();
        LOGGER.info(now);

        LOGGER.info("----------------------------------");

        Address addressCourtHouse = new Address("Italy", "Rome", 100);
        CourtHouse<? extends AbonementFullDay> courtHouse = new CourtHouse<>(addressCourtHouse);
        courtHouse.bookCourtHouse(LocalDateTime.of(2022, 12, 1, 7, 30));
        courtHouse.checkOpen();
        courtHouse.startTrial();

        LOGGER.info(courtHouse.getAddress());

        LawyerOfficeUtils.getBetterLawyer(redClearing);

        LOGGER.info("----------------------------------");

        Address addressItaly = new Address("Italy", "Rome", 1);
        CompetitorLawyersOffice competitorLawyersOffice = new CompetitorLawyersOffice("BadLawyers", LocalDate.of(1975, 4, 5), addressItaly);
        competitorLawyersOffice.read();
        competitorLawyersOffice.stealInformation();
        redClearing.issueLicense();

        Set<Human<?>> humans = new HashSet<>();
        humans.add(client1);
        humans.add(client2);
        humans.add(lawyer3);
        humans.stream()
                .skip(1)
                .limit(1)
                .forEach(human -> LOGGER.info(human.getName() + " Finished the University"));

        LOGGER.info("----------------------------------");
        LawyerOfficeUtils.print(redClearing, competitorLawyersOffice);

        LawyerOfficeUtils.contactLawyer(client1, lawyer1);

        try {
            redClearing.setName("RedClearing");
        } catch (NameInvalidException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            LOGGER.info("Name is set");
        }

        redClearing.setDateOfFoundation(LocalDate.of(2020, 5, 1));

        try (TextFile textFile = new TextFile()) {
            LOGGER.info("This is a idempotent");
        }

        Lawyer<RatingStar> lawyer = new Lawyer<>("Joey", address1, LocalDate.of(1991, 6, 2));
        lawyer.setRating(9);
        lawyer.setStar(new RatingStar());

        printStars(lawyer);

        CourtHouse<AbonementFullDay> courtHouse1 = new CourtHouse<>(address1);
        courtHouse1.setAccess(new AbonementFullDay());

        Building<?> building = new Building<>(new Address("Mongolia", "Ulan Bator", 15));
        building.setSchemaBuilding(Building.SchemaBuilding.SECURITY_ROOM);

        switch (building.getSchemaBuilding()) {
            case CHECKPOINT:
                LOGGER.info("visitor registered");
                break;
            case SECURITY_ROOM:
                LOGGER.info("inspection of personal belongings");
                break;
            case COURTROOM:
                LOGGER.info("hearing in progress");
                break;
            default:
                break;
        }

        ProcessName processName = ProcessName.CRIMINAL_CASE;
        LOGGER.info(processName);
        LOGGER.info(processName.name());
        LOGGER.info(processName.toString());
        LOGGER.info(ProcessName.valueOf("CRIMINAL_CASE"));
        LOGGER.info(Arrays.toString(ProcessName.values()));
        LOGGER.info(ProcessName.ADMINISTRATIVE_CASE.getName());
        LOGGER.info(ProcessName.ADMINISTRATIVE_CASE.checkOpen());

        courtHouse.setDayOfWeek(CourtHouse.DayOfWeek.SUNDAY);
        LOGGER.info(courtHouse.getDayOfWeek());

        if (courtHouse.getDayOfWeek() == CourtHouse.DayOfWeek.SATURDAY || courtHouse.getDayOfWeek() == CourtHouse.DayOfWeek.SUNDAY) {
            LOGGER.info("Courthouse is closed");
        } else {
            LOGGER.info("Courthouse is open");
        }

        FileReader.countWords(TEXT_FILE);
        FileReader.writeSortedWordsToFile("newFIle.txt");

        divorce.confirmCase();

        ICheckClients<Client> checkClients = client -> client.isClientArrived(clients);
        redClearing.meetClient(checkClients);

        ICheckStaff<Lawyer<? extends IIncreaseRating>> checkStaff = x -> x.isLawyersPresent(lawyers);
        staff.getAvailable(checkStaff);

        try {
            Class<?> addressClass = Class.forName("com.solvd.lawyers.Address");
            LOGGER.info(addressClass);

            Class<? extends Address> addressObjClass = address1.getClass();
            LOGGER.info(addressObjClass);

            Field[] declaredFields = addressObjClass.getDeclaredFields();
            Arrays.stream(declaredFields)
                    .forEach(LOGGER::info);

            Field countryField = addressObjClass.getDeclaredField("country");
            LOGGER.info(countryField);

            Field[] fields = addressObjClass.getFields();
            Arrays.stream(fields)
                    .forEach(LOGGER::info);

            Constructor<?> addressConstructor = addressClass.getDeclaredConstructor(String.class, String.class, Integer.class);
            Address address5 = (Address) addressConstructor.newInstance("Belarus", "Minsk", 1);
            LOGGER.info(address5);
            Method getCountry = addressClass.getDeclaredMethod("getCountry");
            LOGGER.info(getCountry.invoke(address5));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void printStars(Human<RatingStar> human) {
        LOGGER.info(human.getStar().increaseRating());
    }
}



