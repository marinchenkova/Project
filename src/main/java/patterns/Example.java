package patterns;

/**
 * Created by Валентин on 12.10.2016.
 */
public class Example extends MementoPattern {
    public static void main(String[] args) {
        MementoPattern.Originator originator = new  MementoPattern(). new Originator();
        MementoPattern.Caretaker caretaker = new  MementoPattern(). new Caretaker();

        originator.setState("on");
        System.out.println("State is " + originator.getState());
        caretaker.setMemento(originator.saveState());

        originator.setState("off");
        System.out.println("State is " + originator.getState());

        originator.restoreState(caretaker.getMemento());
        System.out.println("State is " + originator.getState());
    }
}
