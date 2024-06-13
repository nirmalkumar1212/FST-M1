public class Car {
   
    String color;
    String transmission;
    int make;
    int tyres;
    int doors;

Car()
{
    tyres=4;
    doors=4;
}

public void displayCharaceristics()
{
    System.out.println("Color of the car: " +color);
    System.out.println("Make of the car: " +make);
    System.out.println("Transmission of the car: " +transmission);
    System.out.println("Number of doors on the car: " +doors);
    System.out.println("Number of tyres on the car: " +tyres);
}
public void accelerate()
{
    System.out.println("Car is moving Forward.");
}
public void brake()
{
    System.out.println("Car has Stopped.");
}

}
