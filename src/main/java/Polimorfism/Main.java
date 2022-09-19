package Polimorfism;

public class Main {

    // 1. compile time polymorphism is Method Overloading
    // 2. Runtime polymorphism is method Overwriting.


    public static void main(String[] args) {
        //1. Compile time
        Shape shape = new Shape();
        //Method Overloading
        shape.drawShape(); //"shape not set"
        shape.drawShape(5); //"shape not set"

        // 2. Runtime polymorphism Method Overwriting

        Shape shape1 = new Rectangle();
        shape1.drawShape(); //"Rectangle" we have overwritten now it shows Rectangle instead of shape not set
    }
}

class Shape {
    public Shape drawShape() {
        System.out.println("shape not set");
        return new Shape();
    }

    public Shape drawShape(int copy) {
        System.out.println("shape not set");
        return new Shape();
    }
}

class Square extends Shape {

    //Runtime polymorphism
    @Override
    public Square drawShape() {
        System.out.println("Square");
        return new Square();
    }
}

class Rectangle extends Square {
    //Runtime polymorphism
    @Override
    public Rectangle drawShape() {
        System.out.println("Rectangle");
        return new Rectangle();
    }
}