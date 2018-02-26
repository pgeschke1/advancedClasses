package com.paulgeschke;

import com.sun.xml.internal.ws.util.StringUtils;
import java.text.NumberFormat;

public class Main {

    public static void main(String[] args) {
        Employee emp1 = new Employee("Jane Doe", PositionTitle.ADMINISTRATION, true, 27.00, 1);
        Employee emp3 = new Employee("John Smith", PositionTitle.MAINTENANCE, false, 21.00, 3);
        Employee emp2 = new Employee("Larry Jackson", PositionTitle.PRODUCTION, false, 22.00, 2);

        emp1.calculate(45);
        emp1.display();
        emp2.calculate(50);
        emp2.display();
        emp3.calculate(35);
        emp3.display();
    }
}
enum PositionTitle {
    ADMINISTRATION, PRODUCTION, SALES, MAINTENANCE, TECHNICAL, SECRETARIAL
}

class Employee {
    private String employeeName;
    private PositionTitle employeePosition;
    private boolean employeeSalary;
    private double employeePayRate;
    private int employeeShift;
    private double employeePay;


    Employee(String name, PositionTitle position, boolean salary, double payRate, int shift){
        setEmployeeName(name);
        setEmployeePosition(position);
        setEmployeeSalary(salary);
        setEmployeePayRate(payRate);
        setEmployeeShift(shift);
    }

    private String getEmployeeName() { return employeeName; }
    private PositionTitle getEmployeePosition() { return employeePosition; }
    private boolean isEmployeeSalary() { return employeeSalary; }
    private double getEmployeePayRate() { return employeePayRate; }
    private int getEmployeeShift() { return employeeShift; }
    private double getEmployeePay() { return employeePay; }

    private void setEmployeeName(String employeeName) { this.employeeName = employeeName; }
    private void setEmployeePosition(PositionTitle employeePosition) { this.employeePosition = employeePosition; }
    private void setEmployeeSalary(boolean employeeSalary) { this.employeeSalary = employeeSalary; }
    private void setEmployeePayRate(double employeePayRate) { this.employeePayRate = employeePayRate; }
    private void setEmployeeShift(int employeeShift) { this.employeeShift = employeeShift; }
    private void setEmployeePay(double employeePay) { this.employeePay = employeePay; }

    public void display(){
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setGroupingUsed(true);
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);

        String tempPosition = getEmployeePosition().toString().toLowerCase();
        String tempPosition1 = StringUtils.capitalize(tempPosition);
        System.out.println(getEmployeeName());
        System.out.println(tempPosition1 + " Is salary: " + isEmployeeSalary() + " shift: " + getEmployeeShift());
        System.out.println("Rate of pay is: $" + nf.format(getEmployeePayRate()) + " per hour.");
        System.out.println(employeeName + " earned $" + nf.format(getEmployeePay()) + " this week.");
        System.out.println("");

    }

    public void calculate(double hoursIn){
        double tempPay;
        double tempPayRate;
        double overtime;

        if (getEmployeeShift() == 2){
            tempPayRate = getEmployeePayRate() * 1.05;
        }else if (getEmployeeShift() == 3){
            tempPayRate = getEmployeePayRate() * 1.1;
        }else{
            tempPayRate = getEmployeePayRate();
        }

        if (hoursIn > 40){
            if (!isEmployeeSalary()){
                overtime = (hoursIn-40) * 1.5;
                tempPay = (tempPayRate * overtime) + (40 * tempPayRate) ;
            }else {
                tempPay = tempPayRate * hoursIn;
            }
        }else{
            tempPay = tempPayRate * hoursIn;
        }
        setEmployeePay(tempPay);
    }
}