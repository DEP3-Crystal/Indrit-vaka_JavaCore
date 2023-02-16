package CovariantReturnType;

public class Main{

    // 1. We can change the return type to a subclass of current return type
    // no matter how deep you go with subclasses
    //          like from shape to square
    // 2. We can not change the accessibility level
}
class Shape{
    public Shape drawShape(){
        System.out.println("shape not set");
        return new Shape();
    }
}
class Square extends Shape{
    @Override
    public Square drawShape(){
        System.out.println("Square");
        return new Square();
    }
}
class Rectangle extends Square{
    @Override
    public Rectangle drawShape() {
        return new Rectangle();
    }
}