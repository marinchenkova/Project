package patterns;

/**
 * Created by Валентин on 12.10.2016.
 */
class MementoPattern {

    public class Memento{
        private final String state;

        public Memento(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }
    }

    public class Caretaker {
        private Memento memento;

        public Memento getMemento() {
            return memento;
        }

        public void setMemento(Memento memento) {
            this.memento = memento;
        }
    }

    public class Originator{
        private String state;

        public void setState(String state) {
            this.state = state;
        }

        public String getState(){
            return state;
        }

        public Memento saveState(){
            return new Memento(state);
        }

        public void restoreState(Memento memento) {
            this.state = memento.getState();
        }
    }
}
