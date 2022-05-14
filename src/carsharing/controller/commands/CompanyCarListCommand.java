package carsharing.controller.commands;

import carsharing.model.CompanyCar;
import carsharing.model.dto.CompanyDto;

import java.util.List;
import java.util.Scanner;

public class CompanyCarListCommand implements Command {
    private Receiver receiver;
    private Scanner scanner;
    private String companyName;

    public CompanyCarListCommand(Receiver receiver, Scanner scanner, String companyName) {
        this.receiver = receiver;
        this.scanner = scanner;
        this.companyName = companyName;
    }


    @Override
    public int execute() {
        List<CompanyCar> companyCarList = receiver.getCompanyCars(companyName);
        int companyCarDisplayResult = displayCompanyCars(companyCarList);

        if(companyCarDisplayResult == -1) {
            return -1;
        }


        return 0;
    }

    private int displayCompanyCars(List<CompanyCar> companyCarList) {
        //To see if check is necessary
//        if(companyCarList.size() == 0) {
//            System.out.println("The company list is empty!");
//            return -1;
//        }

        int count = 1;
        System.out.println("Choose a car:\n");
        for(CompanyCar companyCar : companyCarList) {
            System.out.println(String.format("%d. %s", count++, companyCar.getCarName()));
        }
        System.out.println("0. Back");

        return 0;
    }
}
