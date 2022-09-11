package abstraction;
abstract class Animal{
    public abstract void eat();
    public void bark(){
        System.out.println("Bark!");
    }
}
interface dogInterface{
    public void doSomething();
}
class Dog extends Animal implements dogInterface{
    @Override
    public void eat() {
        System.out.println("Eating");
    }

    @Override
    public void doSomething() {
        System.out.println("Doing something");
    }
}
public class Abstraction {
    public static void main(String[] args) {
        Animal animal = new Dog();
        animal.bark();
        animal.eat();
        ((Dog)animal).doSomething();
    }
}
